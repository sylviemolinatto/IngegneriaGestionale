#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#define N 4
#define MAX 100

int main(int argc,char *argv[])
{
    FILE *fp;
    int i,j,colonne,conta,max,pos;
    char mappa[2*N][MAX+1];
    char riga[2*N][MAX+1];


    if(argc!=3)
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
    colonne=0;
    for(i=0;i<2*N;i++)
    {
        fgets(riga[i],MAX+1,fp);
        if(strlen(riga[i])>colonne)
           {
               colonne=strlen(riga[i]);
           }
    }
    fclose(fp);
    for(i=0;i<2*N;i++)
    {
        for(j=0;j<colonne;j++)
        {
            if(riga[i][j]==' ');
               mappa[i][j]='0';
            if(riga[i][j]=='X')
               mappa[i][j]='X';
        }
    }
   fp=fopen(argv[2],"w");
   conta=0;
   max=0;
   for(j=0;j<colonne;j++)
   {
       for(i=0;i<N;i++)
       {
           if(mappa[i][j]=='X')
           {
               conta++;
           }
       }
       if(conta>max)
       {
           max=conta;
           pos=j;
       }
       for(i=N;i<2*N;i++)
       {
           if(mappa[i][j]=='X')
           {
               conta--;
           }
       }
       fprintf(fp,"%d ",conta);
       conta=0;
   }
   fclose(fp);
   printf("Il massimo valore e' %d, in posizione %d",max,pos+1);
    return 0;
}
