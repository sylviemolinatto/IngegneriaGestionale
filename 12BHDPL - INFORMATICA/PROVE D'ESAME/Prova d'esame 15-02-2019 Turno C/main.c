#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#define MAX_CODICI 50
typedef struct
{
    char codice_software[11];
    char descrizione[101];
    char prima_versione[9];
    int n_dispositivi;
}versione;

void Inizializza_versione(versione versioni[MAX_CODICI]);

int main(int argc,char *argv[])
{
    versione versioni[100];
    int i,numero_codici;
    char codice_dispositivo[9];
    char chiave1[21],chiave2[21],chiave3[21];
    char codice_software[11];
    char parola_chiave[21];
    FILE *fl,*fv;
    if(argc!=4)
    {
        printf("Errore parametri\n");
        return -1;
    }
    strcpy(argv[2],parola_chiave);
    fl=fopen(argv[1],"r");
    if(fl==NULL)
    {
        printf("Errore nell'apertura del file\n");
        return -1;
    }
    fv=fopen(argv[3],"r");
    if(fv==NULL)
    {
        printf("Errore nell'apertura del file\n");
        return -1;
    }
    Inizializza_versione(versioni);
    for(i=0;(i<MAX_CODICI)&&(feof(fv)==0);i++)
    {
       fscanf(fv,"%s\n%s",versioni[i].codice_software,versioni[i].descrizione);
       numero_codici=i;
    }
    fclose(fv);
    while(feof(fl)==0)
    {
        fscanf(fl,"%s\n%s %s %s\n%s",codice_dispositivo,chiave1,chiave2,chiave3,codice_software);
        if((strcmp(chiave1,parola_chiave)==0)||(strcmp(chiave2,parola_chiave)==0)||(strcmp(chiave3,parola_chiave)==0))
        {
            printf("%s %s",codice_dispositivo,codice_software);
            for(i=0;i<numero_codici;i++)
            {
                if(strcmp(codice_software,versioni[i].codice_software)==0)
                    {
                        printf("%s",versioni[i].descrizione);
                        versioni[i].n_dispositivi++;
                        if(strlen(versioni[i].prima_versione)==0)
                            strcpy(codice_dispositivo,versioni[i].prima_versione);
                    }
                else
                    printf("Descrizione software non disponibile\n");
            }
        }
    }
    fclose(fl);
    for(i=0;i<numero_codici;i++)
    {
        if(versioni[i].n_dispositivi>0)
        {
            printf("%s usato da %d dispositivi, prima versione: %s\n",versioni[i].codice_software,versioni[i].n_dispositivi,versioni[i].prima_versione);
        }
        else
            printf("%s non usato da alcun dispositivo\n",versioni[i].codice_software);
    }
    return 0;
}
void Inizializza_versione(versione versioni[MAX_CODICI])
{
    int i;
    for(i=0;i<MAX_CODICI;i++)
    {
        versioni[i].codice_software[11]='\0';
        versioni[i].descrizione[101]='\0';
        versioni[i].prima_versione[9]='\0';
        versioni[i].n_dispositivi=0;
    }
}
