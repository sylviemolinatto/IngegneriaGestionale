#include <stdio.h>
#include <stdlib.h>
#define MAX 100
typedef struct
{
    int numero;
    int PostiLetto;
    int occupata;
}camera;

int main(int argc,char *argv[])
{
    FILE *fp;
    camera camere[MAX];
    int c,i,j;
    int numero_camere,numero_ospiti,conta=0;
    const int n=atoi(argv[1]);
    int prenotazione[n];//vettore in cui salvo il numero delle camere per completare l'intera prenotazione
    if(argc<3)
    {
        printf("Errore parametri!\n");
        return -1;
    }
    numero_camere=atoi(argv[1]);
    if(argc!=(2+numero_camere))
    {
        printf("Errore parametri!\n");
        return -1;
    }
    fp=fopen("camere.txt","r");
    if(fp==NULL)
    {
        printf("Errore nell'apertura del file!\n");
        return -1;
    }
    fscanf(fp,"%d",&c); //nella prima riga del file si trova il numero di camere dell'hotel
    for(i=0;i<c;i++)
    {
        fscanf(fp,"%d %d %d",&camere[i].numero,&camere[i].PostiLetto,&camere[i].occupata);
    }
    fclose(fp);

    for(i=2;i<=(numero_camere+1);i++) //da argv[2] a argv[numero_camere+1] ci sono i numeri degli ospiti per camera
    {
        numero_ospiti=atoi(argv[i]);
        for(j=0;j<c;j++)//effettuo la ricerca della disponibilità della camera richiesta sia per posti letto che per disponibilita
        {
            if((numero_ospiti==camere[j].PostiLetto)&&(camere[j].occupata==0))
            {
                camere[j].occupata=1;//modifico il vettore di strutture affinchè la camera non sia più libera
                prenotazione[j]=j++;//salvo il numero della camera prenotata in un vettore
                conta++;//incremento una variabile contatore per capire successivamente se ci sono abbastanza camere disponibili per soddisfare la richiesta della prenotazione
            }

        }
    }
    fp=fopen("camere.txt","w");
    if(conta==n)//se ho trovato esattamente il numero di camere richiesto da linea di comando
    {
        printf("Prenotazione effettuata con successo\n");
        for(i=0;i<n;i++)
        {
            printf("Camera %d - %d persone\n",prenotazione[i],camere[prenotazione[i]-1].PostiLetto);
        }
        for(i=0;i<c;i++)
        {
            fprintf(fp,"%d %d %d",camere[i].numero,camere[i].PostiLetto,camere[i].occupata);
        }
        printf("Il file delle camere e' stato aggiornato con successo\n");
    }

    else // non ho abbastanza camere disponibili per soddisfare la richiesta della prenotazione
    {
        for(i=0;i<n;i++)
        {
            camere[prenotazione[i]-1].occupata=0;
        }
        printf("Prenotazione non effettuata – numero di camere libere insufficiente\n");
        printf("Il file delle camere non e' stato aggiornato");

    }
    fclose(fp);
    return 0;
}
