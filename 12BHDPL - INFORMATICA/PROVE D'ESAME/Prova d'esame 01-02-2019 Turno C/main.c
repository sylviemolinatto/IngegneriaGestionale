#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#define MAX_AUTO 100
typedef struct
{
    int ora_ingresso;
    int minuti_ingresso;
    int ora_uscita;
    int minuti_uscita;
    int durata;
    char stato;
    char targa[8];
}macchina;
void Inizializza_struttura(macchina macchine[MAX_AUTO]);
int main(int argc,char *argv[])
{
    macchina macchine[MAX_AUTO];
    int i,ora,minuti,conta=0,num_auto,max_durata,presenti=0;
    int hh,mm;
    char stato;
    char targa[8];
    FILE *fp;
    sscanf(argv[2],"%d:%d",&hh,&mm);

    if(argc!=3)
    {
        printf("Errore parametri!\n");
        return -1;
    }
    fp=fopen(argv[1],"r");
    if(fp==NULL)
    {
        printf("Errore nell'apertura del file!\n");
        return -1;
    }
    Inizializza_struttura(macchine);
    while((fscanf(fp,"%d:%d %c %s",&ora,&minuti,&stato,targa)!=EOF)&&conta<MAX_AUTO)
    {
            if(stato=='I')
            {
               strcpy(macchine[conta].targa,targa);
               macchine[conta].ora_ingresso=ora;
               macchine[conta].minuti_ingresso=minuti;
               conta++;
               num_auto=conta;
            }
            if(stato=='U')
            {
                for(i=0;i<MAX_AUTO;i++)
                {
                    if(strcmp(targa,macchine[i].targa)==0)
                    {
                        macchine[i].ora_uscita=ora;
                        macchine[i].minuti_uscita=minuti;
                    }
                }

            }
    }
    fclose(fp);
    for(i=0;i<num_auto;i++)
    {
        macchine[i].durata=((macchine[i].ora_uscita*60+macchine[i].minuti_uscita)-(macchine[i].ora_ingresso*60+macchine[i].minuti_ingresso));
    }
    max_durata=macchine[0].durata;
    for(i=1;i<num_auto;i++)
    {
        if(macchine[i].durata>max_durata)
            max_durata=macchine[i].durata;
    }
    for(i=0;i<num_auto;i++)
    {
        if(((hh-macchine[i].ora_ingresso>0)||((hh-macchine[i].ora_ingresso==0)&&(mm-macchine[i].minuti_ingresso>0)))&&((macchine[i].ora_uscita>hh)||((macchine[i].ora_uscita==hh)&&(macchine[i].minuti_uscita>mm))))
        {
            if(macchine[i].durata<120)
            {
                presenti++;
            }
        }
    }
    printf("Alle ore %d:%d nel parcheggio erano presenti %d veicoli con tempo di sosta inferiore alle due ore\n",hh,mm,presenti);
    printf("Durata massima di permanenza nell'arco di tutta la giornata : %d minuti",max_durata);
    return 0;
}
void Inizializza_struttura(macchina macchine[MAX_AUTO])
{
    int i;
    for(i=0;i<MAX_AUTO;i++)
    {
        macchine[i].targa[8]='\0';
        macchine[i].stato='x';
        macchine[i].ora_ingresso=0;
        macchine[i].minuti_ingresso=0;
        macchine[i].ora_uscita=0;
        macchine[i].minuti_uscita=0;
        macchine[i].durata=0;
    }
}
