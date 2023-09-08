#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#define FASCE 3
#define MAX_CONTROL 10


int calcola_eta(int gg_v,int mm_v,int aaaa_v,int gg_n,int mm_n,int aaaa_n);
typedef struct
{
    char numero[6];
    int gg_v,mm_v,aaaa_v;
    int piu_visite;
} visit;

int main(int argc,char *argv[])
{
    FILE *fp;
    float prezzo[FASCE],incasso,durata_media;
    int visitatori,durata,anno_rif,eta;
    char numero[6];
    int gg_v,mm_v,aaaa_v,gg_n,mm_n,aaaa_n,differenza;
    int hh_in,min_in,ss_in,hh_out,min_out,ss_out;
    int i,j,conta;
    visit utente[MAX_CONTROL];

    if(argc<3||argc>13)
    {
        printf("ERRORE\n");
        return -1;
    }
    sscanf(argv[1],"%d",&anno_rif);
    fp=fopen("prezzi.txt","r");
    if(fp==NULL)
    {
        printf("Errore nell'apertura del primo file\n");
        return -1;
    }
    for(i=0; i<FASCE; i++)
    {
        fscanf(fp,"%f",&prezzo[i]);
    }
    fclose(fp);
    fp=fopen("visite.txt","r");
    if(fp==NULL)
    {
        printf("Errore nell'apertura del secondo file\n");
        return -1;
    }
    visitatori=0;
    incasso=0;
    durata=0;
    durata_media=0;
    j=0;
    if(argv[2][0]=='S')
    {
        while(fscanf(fp,"%s %d/%d/%d %d:%d:%d %d:%d:%d %d/%d/%d",numero,&gg_v,&mm_v,&aaaa_v,&hh_in,&min_in,&ss_in,&hh_out,&min_out,&ss_out,&gg_n,&mm_n,&aaaa_n)!=EOF)
        {
            if(aaaa_v==anno_rif)
            {
                visitatori++;
                eta=calcola_eta(gg_v,mm_v,aaaa_v,gg_n,mm_n,aaaa_n);
                if(eta>0&&eta<=10)
                    incasso=incasso+prezzo[0];
                if(eta>10&&eta<=17)
                    incasso=incasso+prezzo[1];
                if(eta>17)
                    incasso=incasso+prezzo[2];
                durata=durata+((hh_out*3600+min_out*60+ss_out)-(hh_in*3600+min_in*60+ss_in));
            }

        }
        durata_media=durata/visitatori;
        printf("Numero di visite del %d pari a %d\n",anno_rif,visitatori);
        printf("Incasso totale del %d pari a %.2f\n",anno_rif,incasso);
        printf("Durata media delle visite nel %d pari a %.0f minuti\n",anno_rif,durata_media/60);
    }

    if(argv[2][0]=='G')
    {
        j=0;
        for(i=0; i<argc-3; i++)
        {
            strcpy(utente[i].numero,argv[i+3]);
            j++;
        }
        for(i=0; i<MAX_CONTROL; i++)
        {
            utente[i].gg_v=0;
            utente[i].mm_v=0;
            utente[i].aaaa_v=0;
            utente[i].piu_visite=0;

        }
        while(fscanf(fp,"%s %d/%d/%d %d:%d:%d %d:%d:%d %d/%d/%d",numero,&gg_v,&mm_v,&aaaa_v,&hh_in,&min_in,&ss_in,&hh_out,&min_out,&ss_out,&gg_n,&mm_n,&aaaa_n)!=EOF)
        {
            if(aaaa_v==anno_rif)
            {
                for(i=0; i<argc-3; i++)
                {
                    if(strcmp(numero,utente[i].numero)==0)
                    {
                        if((utente[i].gg_v==0)&&(utente[i].mm_v==0)&&(utente[i].aaaa_v==0))
                        {
                            utente[i].gg_v=gg_v;
                            utente[i].mm_v=mm_v;
                            utente[i].aaaa_v=aaaa_v;
                        }
                        else
                        {   if(utente[i].mm_v==mm_v)
                            {
                              differenza=gg_v-utente[i].gg_v;
                              if(differenza<=2)
                              utente[i].piu_visite++;
                            }
                        }
                    }
                }
            }
        }
            conta=0;
            for(i=0; i<argc-3; i++)
            {
                if(utente[i].piu_visite>0)
                    conta++;
            }
            if(conta>0)
                printf("Ci sono %d utenti che hanno effettuato visite a distanza inferiore di due giorni\n",conta);

        }
        fclose(fp);



        return 0;
    }
    int calcola_eta(int gg_v,int mm_v,int aaaa_v,int gg_n,int mm_n,int aaaa_n)
    {
        int eta,a,m,g;
        m=mm_v-mm_n;
        g=gg_v-gg_n;
        a=aaaa_v-aaaa_n;
        if(m>0)
            eta=a;
        if(m<0)
            eta=a-1;
        if(m==0)
        {
            if(g>0)
                eta=a;
            if(g<0)
                eta=a-1;
        }

        return eta;
    }

