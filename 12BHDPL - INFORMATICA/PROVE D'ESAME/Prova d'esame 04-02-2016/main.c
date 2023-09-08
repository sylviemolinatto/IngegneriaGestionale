#include <stdio.h>
#include <stdlib.h>
#include <string.h>
typedef struct
{
    char data[11];
    char ora[9];
    char username[11];
    char status;
}accessi;

int main(int argc,char *argv[])
{
    int i,anno,mese,giorno,ora,minuti,secondi,anno_utente,mese_utente,giorno_utente,ora_utente,minuti_utente,secondi_utente;
    int anomalia=0;
    accessi accesso;
    char utente[10][11];
    char username[11];
    FILE *fp;
    if(argc!=4)
    {
        printf("Errore parametri!\n");
        return -1;
    }
    fp=fopen("access.txt","r");
    if(fp==NULL)
    {
        printf("Errore nell'apertura del file!\n");
        return -1;
    }
    for(i=0;i<3;i++)
    {
        strcpy(argv[i+1],utente[i]);
    }
    printf("Anomalie rilevate:\n");
    while(feof(fp)==0)
    {
        fscanf(fp,"%s %s %s %c",accesso.data,accesso.ora,accesso.username,&accesso.status);
        sscanf(accesso.data,"%d-%d-%d",&anno,&mese,&giorno);
        sscanf(accesso.ora,"%d:%d:%d",&ora,&minuti,&secondi);
        if((strcmp(username,accesso.username)==0)&&(accesso.status=='F'&&(anno_utente==anno)&&(mese_utente==mese)&&(giorno_utente==giorno)&&(ora_utente==ora)&&((minuti-minuti_utente)<=1)))
        {
            printf("%s %d-%d-%d  %d:%d:%d  %d:%d:%d",accesso.username,anno,mese,giorno,ora_utente,minuti_utente,secondi_utente,ora,minuti,secondi);
            anomalia++;
        }
        for(i=0;i<10;i++)
        {
            if(strcmp(utente[i],accesso.username)==0)
            {
                if(accesso.status=='F')
                {
                    strcpy(accesso.username,username);
                    anno_utente=anno;
                    mese_utente=mese;
                    giorno_utente=giorno;
                    ora_utente=ora;
                    minuti_utente=minuti;
                    secondi_utente=secondi;

                }
            }
        }
    }
    if(anomalia>0)
    {
        printf("Numero totale di anomalie: %d",anomalia);
    }

    return 0;
}
