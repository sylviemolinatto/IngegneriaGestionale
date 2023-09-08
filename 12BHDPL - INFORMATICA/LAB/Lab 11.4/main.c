#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#define MAX 20
typedef struct
{
    char stazione_partenza[MAX];
    char ora_partenza[MAX];
    char stazione_arrivo[MAX];
    char ora_arrivo[MAX];
}linea;
int main(int argc,char *argv[])
{
    linea treno;
    int hp,mp,ha,ma;
    char citta[MAX];
    int conta1=0,conta2=0;
    FILE *fp;
    if(argc!=3)
    {
        printf("Errore parametri\n");
        return -1;
    }
    fp=fopen(argv[1],"r");
    if(fp==NULL)
    {
        printf("Errore nell'apertura del file\n");
        return -1;
    }

    strcpy(argv[2],citta);
    while(feof(fp)==0)
    {
        fscanf(fp,"%s%s%s%s",treno.stazione_partenza,treno.ora_partenza,treno.stazione_arrivo,treno.ora_arrivo);
        if(strcmp(citta,treno.stazione_partenza)==0)
        {
            sscanf(treno.ora_partenza,"%d:%d",&hp,&mp);
            sscanf(treno.ora_arrivo,"%d:%d",&ha,&ma);
            conta1++;
            printf("Treno in partenza alle ore %d:%d da %s che arriva a %s alle ore %d:%d",hp,mp,treno.stazione_partenza,treno.stazione_arrivo,ha,ma);
        }
        if(strcmp(citta,treno.stazione_arrivo)==0)
        {
            sscanf(treno.ora_partenza,"%d:%d",&hp,&mp);
            sscanf(treno.ora_arrivo,"%d:%d",&ha,&ma);
            conta2++;
            printf("Treno in arrivo alle ore %d:%d a %s che viene da %s alle ore %d:%d",ha,ma,treno.stazione_arrivo,treno.stazione_partenza,hp,mp);

        }

    }
    printf("I treni in partenza da %s sono %d",citta,conta1);
    printf("I treni in arrivo a %s sono %d",citta,conta2);
    return 0;
}
