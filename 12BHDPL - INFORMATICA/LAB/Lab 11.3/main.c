#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#define MAX 20
typedef struct
{
    char materia[MAX];
    char nome_prof[MAX];
    char cognome_prof[MAX];
    char periodo[MAX];
    char crediti[MAX];
    char percentuale[MAX];
}corso;

int main(int argc,char *argv[])
{
    int i,pos_max,j,pos_min1,pos_min2,pos_min3,pos_min4;
    corso corsi[80];
    char cognome[MAX];
    int max,min1=100,min2=100,min3=100,min4=100;
    FILE *fp;
    if(argc!=2)
    {
        printf("Errore parametri\n");
        return -1;
    }
    fp=fopen(argv[1],"r");
    if(fp==NULL)
    {
        printf("Errore nell'apertura del file\n");
    }
    while(!feof(fp))
    {
        fscanf(fp,"%s%s%s%s%s%s",corsi[i].materia,corsi[i].nome_prof,corsi[i].cognome_prof,corsi[i].periodo,corsi[i].crediti,corsi[i].percentuale);
    }
    max=atoi(corsi[0].crediti);
    for(i=0;i<80;i++)
    {
        if((atoi(corsi[i].crediti)>max))
            {
                max=atoi(corsi[i].crediti);
                pos_max=i;
            }
    }


    printf("La materia che assegna piu' crediti in assoluto e': \n");
    for(i=0;i<80;i++)
    {
        if(strcmp(corsi[i].periodo,"1"))
        {
           if(atoi(corsi[i].percentuale)<min1)
           {
               min1=atoi(corsi[i].percentuale);
               pos_min1=i;
           }
        }
        if(strcmp(corsi[i].periodo,"2"))
        {
           if(atoi(corsi[i].percentuale)<min2)
           {
               min1=atoi(corsi[i].percentuale);
               pos_min2=i;
           }
        }
        if(strcmp(corsi[i].periodo,"3"))
        {
           if(atoi(corsi[i].percentuale)<min3)
           {
               min1=atoi(corsi[i].percentuale);
               pos_min3=i;
           }
        }
        if(strcmp(corsi[i].periodo,"4"))
        {
           if(atoi(corsi[i].percentuale)<min4)
           {
               min1=atoi(corsi[i].percentuale);
               pos_min4=i;
           }
        }
    }


    printf("La materia più difficile da superare del periodo 1 e' %s\n",corsi[pos_min1].materia);
    printf("La materia più difficile da superare del periodo 2 e' %s\n",corsi[pos_min2].materia);
    printf("La materia più difficile da superare del periodo 3 e' %s\n",corsi[pos_min3].materia);
    printf("La materia più difficile da superare del periodo 4 e' %s\n",corsi[pos_min4].materia);

    printf("Inserisci il cognome di un professore : ");
    scanf("%s",cognome);
    for(i=0;(i<80);i++)
    {
        if(strcmp(cognome,corsi[i].cognome_prof)==0)
        {
            printf("La percentuale di superamento degli esami del professor %s e' %d",cognome,atoi(corsi[i].cognome_prof));
        }
        else
            printf("\nProfessore non trovato\n");
    }

    return 0;
}
