#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#define M 9
typedef struct
{
    char nome[41];
    char cognome[41];
    char destinazione[41];
    char priorita;
    int imbarcato;
}prenotazione;

int main(int argc,char *argv[])
{
    FILE *fp;
    prenotazione p[M];
    argv[1]="in.txt";
    int capienza,i;
    if(argc!=4)
    {
        printf("Errore!\n");
        return -1;
    }
    fp=fopen(argv[1],"r");
    if(fp==NULL)
    {
        printf("Erroe nell'apertura del file!\n");
        return -1;
    }
    for(i=0;i<M;i++)
    {
        fscanf(fp,"%s %s %s %c",p[i].nome,p[i].cognome,p[i].destinazione,&p[i].priorita);
        p[i].imbarcato=0;
    }
    fclose(fp);
    fp=fopen("passeggeri.txt","w");
    if(fp==NULL)
    {
        printf("Errore nell'apertura del file!\n");
        return -1;
    }
    capienza=atoi(argv[3]);
    for(i=0;(i<M)&&(capienza>0);i++)
    {
        if((strcmp(argv[2],p[i].destinazione)==0)&&(p[i].priorita=='P'))
        {
            fprintf(fp,"%s %s %c\n",p[i].cognome,p[i].nome,p[i].priorita);
            capienza--;
            p[i].imbarcato=1;
        }
    }
    for(i=0;(i<M)&&(capienza>0);i++)
    {
        if((strcmp(argv[2],p[i].destinazione)==0)&&(p[i].imbarcato==0))
        {
            fprintf(fp,"%s %s %c\n",p[i].cognome,p[i].nome,p[i].priorita);
            capienza --;
            p[i].imbarcato=1;

        }
    }
    fclose(fp);
    for(i=0;i<M;i++)
    {
        if((strcmp(argv[2],p[i].destinazione)==0)&&(p[i].imbarcato==0))
            printf("%s %s\n",p[i].cognome,p[i].nome);
    }

    return 0;
}
