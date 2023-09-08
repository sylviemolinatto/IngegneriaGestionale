#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <float.h>
#define MAX_ALB 40
#define MAX_ZONE 4

typedef struct
{
    char nome[40+1];
    char zona[5+1];
    float prezzo;
    int disponibile;
}albergo;
void print_all();
int main(int argc,char *argv[])
{
    FILE *fp;
    albergo alberghi[MAX_ALB];
    char zona[6];
    float prezzo_min;
    int choice,n_alb,i,pos,trovato;
    if(argc!=2)
    {
        printf("Errore parametri\n");
        return -1;
    }
    fp=fopen(argv[1],"r");
    if(fp==NULL)
    {
        printf("Errore nell'apertura del file\n");
        return -2;
    }
    i=0;
    while(fscanf(fp,"%s %s %f",alberghi[i].nome,alberghi[i].zona,&alberghi[i].prezzo)!=EOF)
    {
        alberghi[i].disponibile=1;
        i++;
    }
    n_alb=i;
    fclose(fp);
    do
    {
        print_all();
        printf("scegliere un comando: \n");
        scanf("%d",&choice);
        switch(choice)
        {
        case 1:
            printf("Inserire una zona: \n");
            scanf("%s",zona);
            prezzo_min=FLT_MAX;
            trovato=0;
            for(i=0;i<n_alb;i++)
            {
                if((strcmp(zona,alberghi[i].zona)==0)&&(alberghi[i].disponibile)==1)
                {
                  if(alberghi[i].prezzo<prezzo_min)
                  {
                      prezzo_min=alberghi[i].prezzo;
                      pos=i;
                      trovato=1;
                  }
                }
            }
            if(trovato==1)
            {
                printf("%s %.2f\n",alberghi[pos].nome,alberghi[pos].prezzo);
                alberghi[i].disponibile=0;
            }
            if(trovato==0)
            {
                printf("Non ci sono alberghi disponibili nella zona richiesta\n");
            }

            break;
        case 2:
            for(i=0;i<n_alb;i++)
            {
                if(alberghi[i].disponibile==1)
                {
                    printf("%s %s %.2f\n",alberghi[i].nome,alberghi[i].zona,alberghi[i].prezzo);
                }
            }
            break;
        case 3:
            printf("Arrivederci!\n");
            break;
        default:
            printf("Comando inesistente\n");
            break;
        }
    }while(choice!=3);
    return 0;
}
void print_all()
{
    printf("1) Prenotazione di un hotel per una notte\n");
    printf("2) Stampa a video degli hotel ancora disponibili con relative zone e prezzi\n");
    printf("3) Uscita dal programma\n");

    return;
}
