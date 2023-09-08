#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
#define GIOC 600

int main(int argc,char *argv)
{
   char nomi[GIOC][16];
   char cognomi [GIOC][16];
   char ruolo[GIOC];
   float prob[GIOC];
   float voto[GIOC];
   int goal[GIOC];
   int cg[GIOC];
   int cr[GIOC];
   float prestazioni[GIOC];
   char n[16],c[16];
   char r;
   float p,v;
   int g,ccg,ccr,conta;
   int giocatore1,giocatore2;
   int i;
   FILE*fp;
   if((argc!=3)&&(argc!=6))
   {
       printf("Errore parametri\n");
       return -1;
   }
   fp=fopen("statistiche.dat","r");
   if(fp==NULL)
   {
       printf("Errore nell'apertura\n");
       return -1;
   }
   conta=0;
   while((conta<GIOC)&&(fscanf(fp,"%s%s%c%f%f%d%d%d",n,c,&r,&p,&v,&g,&ccg,&ccr)!=EOF))
   {
       strcpy(nomi[conta],n);
       strcpy(cognomi[conta],c);
       ruolo[conta]=r;
       prob[conta]=p;
       voto[conta]=v;
       goal[conta]=g;
       cg[conta]=ccg;
       cr[conta]=ccr;
       prestazioni[conta]=p*(v*0.5+g*0.5-ccg*0.2-ccr*0.4);
       conta++;
   }
   fclose(fp);
   if(strcmp(argv[1],"-C")==0)
   {
       if(argc!=6)
       {
           printf("Errore parametri\n");
           return -1;
       }
   }
   giocatore1=601;
   giocatore2=601;

   for(i=0;(i<conta)&&((giocatore1==601)||(giocatore2==601));i++)
   {

       if((strcmp(argv[2],nomi[i])==0)&&(strcmp(argv[3],cognomi[i])==0))
        giocatore1=i;
       if((strcmp(argv[4],nomi[i])==0)&&(strcmp(argv[5],cognomi[i])==0))
        giocatore2=i;
   }
   if(ruolo[giocatore1]!=ruolo[giocatore2])
    printf("Non posso confrontarli\n");
   if(prestazioni[giocatore1]>=prestazioni[giocatore2])
    printf("%s %s\n",nomi[giocatore1],cognomi[giocatore1]);
   else
    printf("%s %s/n",nomi[giocatore2],cognomi[giocatore2]);
   if(strcmp(argv[1],"-R")==0)
   {
       if(argc!=3)
       {
           printf("Errore parametri\n");
           return -1;
       }
   }
   int migliorgiocatore=601;
   int migliorprestazione=0;
   for(i=0;i<conta;i++)
   {
       if(ruolo[i]==argv[2])
       {
           if(prestazioni[i]>migliorprestazione)
           {
               migliorprestazione=prestazioni[i];
               migliorgiocatore=i;
           }
       }
   }
   printf("Miglior giocatore: %s %s\n",nomi[migliorgiocatore],cognomi[migliorgiocatore]);
    return 0;
}
