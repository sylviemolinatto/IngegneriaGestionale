#include <stdio.h>
#include <stdlib.h>
#define MAX 100
typedef struct
{
    int numero;
    int posti;
    int disponibile;
}camera;

int main(int argc,char*argv[])
{
    FILE *fp;
    camera camere[MAX],prenotazione[MAX];
    int cam_hot,i,j,k,n_cam,richieste[MAX],trovato,pos;

    if(argc<3)
    {
        printf("Errore parametri\n");
        return -1;
    }
    for(i=0;i<MAX;i++)
    {
        prenotazione[i].numero=0;
        prenotazione[i].posti=0;
        prenotazione[i].disponibile=0;
    }
    fp=fopen("camere.txt","r");
    if(fp==NULL)
    {
        printf("Errore nell'apertura del file\n");
        return -2;
    }
    fscanf(fp,"%d",&cam_hot);
    for(i=0;i<cam_hot;i++)
    {
        fscanf(fp,"%d %d %d",&camere[i].numero,&camere[i].posti,&camere[i].disponibile);
    }
    fclose(fp);
    sscanf(argv[1],"%d",&n_cam);
    for(i=2;i<argc;i++)
    {
        sscanf(argv[i],"%d",&richieste[i-2]);
    }
    k=0;
    for(j=0;j<argc-2;j++)
    {
        if(n_cam<=cam_hot)
        {
            trovato=0;
            for(i=0;i<cam_hot&&(trovato==0);i++)
            {
              if(richieste[j]==camere[i].posti&&camere[i].disponibile==0)
              {
                  pos=i;
                  camere[pos].disponibile=1;
                  prenotazione[k].numero=camere[i].numero;
                  prenotazione[k].posti=camere[i].posti;
                  prenotazione[k].disponibile=1;
                  trovato=1;
                  k++;
              }
            }
        }
    }

    if(k==argc-2)
    {
        for(i=0;i<argc-2;i++)
        {
            printf("camera %d - %d persone\n",prenotazione[i].numero,prenotazione[i].posti);
            for(j=0;j<MAX;j++)
            {
                if(prenotazione[i].numero==camere[j].numero)
                {
                    camere[i].disponibile=1;
                }
            }
        }
    fp=fopen("camere.txt","w");
    if(fp==NULL)
    {
        printf("Errore dell'apertura del file\n");
        return -1;
    }
    fprintf(fp,"%d\n",cam_hot);
    for(i=0;i<cam_hot;i++)
    {
        fprintf(fp,"%d %d %d\n",camere[i].numero,camere[i].posti,camere[i].disponibile);
    }
    fclose(fp);
    printf("File aggiornato con successo\n");

    }
    else
        {
            printf("Prenotazione non effettuata - numero di camere libere insufficiente\n");
            printf("Il file delle camere non e' stato aggiornato\n");
        }


    return 0;
}
