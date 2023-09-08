#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#define MAX_F2 100
typedef struct
{
    char nome[16];
    char cognome[16];
    char comune[31];
    char data_nascita[11];
    int giorno;
    int mese;
    int anno;
} persona;
typedef struct
{
    char comune[31];
    int nati;
    float eta_media;
} paese;
int leggi(FILE *fp, char str[], char terminatore);
void Inizializza_struttura(paese comuni[MAX_F2]);
int Calcola_eta(int gg,int mm,int aaaa,persona persone);
int main(int argc,char *argv[])
{
    paese comuni[MAX_F2];
    persona persone;
    int i,j=0,fine_comuni=0,indice=0;
    int gg,mm,aaaa;
    char data[11];
    FILE *f1,*f2;
    if(argc!=4)
    {
        printf("Errore parametri!\n");
        return -1;
    }
    strcpy(data,argv[3]);
    sscanf(data,"%d/%d/%d",&gg,&mm,&aaaa);
    f2=fopen(argv[2],"r");
    if(f2==NULL)
    {
        printf("Errore nell'apertura del secondo file!\n");
        return -1;
    }
    Inizializza_struttura(comuni);
    for(i=0;(i<MAX_F2)&&(feof(f2)==0);i++)
    {
        fscanf(f2,"%s",comuni[i].comune);
        indice=i;
    }

    fclose (f2);
    f1=fopen(argv[1],"r");
    if(f1==NULL)
    {
        printf("Errore nell'apertura del primo file!\n");
        return -1;
    }
    while(leggi(f1,persone.data_nascita, ';') != EOF)
    {
    leggi(f1,persone.nome, ',');
    leggi(f1, persone.cognome, ' ');
    leggi(f1,persone.comune, '\n');
    {
        if((strcmp(comuni[i].comune,persone.comune)==0)&&((aaaa-persone.anno>0)||((aaaa-persone.anno==0)&&(mm-persone.mese>0))||((aaaa-persone.anno==0)&&(mm-persone.mese==0)&&(gg-persone.giorno>0))))
        {
            strcpy(comuni[j].comune,comuni[i].comune);
            comuni[j].nati++;
            comuni[j].eta_media=((comuni[j].eta_media+Calcola_eta(gg,mm,aaaa,persone))/comuni[j].nati);
            fine_comuni=j;
            j++;
        }
    }
    }


fclose(f1);
printf("%-30s %s %s %s\n","Comune", "Nati", "Eta' media al", argv[3]);
for(i=0; i<indice; i++)
{
    if(comuni[i].nati>0)
    {
        printf("%-30s %3d %5.1f anni\n", comuni[i].comune,comuni[i].nati,comuni[i].eta_media);
    }
}
return 0;
}
void Inizializza_struttura(paese comuni[MAX_F2])
{
    int i;
    for(i=0; i<MAX_F2; i++)
    {
        strcpy(comuni[i].comune,"\0");
        comuni[i].nati=0;
        comuni[i].eta_media=0;
    }
}
int Calcola_eta(int gg,int mm,int aaaa,persona persone)
{
    int eta;
    if(aaaa >= persone.anno)
        eta = aaaa - persone.anno;
    if((mm < persone.mese) || ((mm == persone.mese) && (gg< persone.giorno)))
        eta = eta - 1;

    return eta;
}
int leggi(FILE *fp, char str[], char terminatore)
{
int carat;
int indice;

indice = 0;
while(((carat = fgetc(fp)) != EOF) && (carat != terminatore))
    {
    str[indice] = carat;
    indice++;
    }
str[indice] = '\0';
return carat;
}
