#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#define MAXR 5
#define MAXC 8
int main()
{
   FILE *fp;
   int i,j,k,max_riga,max_colonna,pos_x,pos_y,pos_c;
   int mappa[MAXR][MAXC];
   fp=fopen("mappa.txt","r");
   if(fp==NULL)
   {
       printf("Errore\n");
       return -1;
   }
   for(i=0;i<MAXR;i++)
   {
       for(j=0;j<MAXC;j++)
       {
           fscanf(fp,"%d",&mappa[i][j]);
       }
   }
   fclose(fp);
   for(i=0;i<MAXR;i++)
   {
       max_riga=0;
       for(j=0;j<MAXC;j++)
       {
         if(mappa[i][j]>max_riga)
         {
             max_riga=mappa[i][j];
             pos_x=i;
             pos_y=j;
         }
       }
       max_colonna=0;
       for(k=0;k<MAXR;k++)
       {
           if(mappa[k][pos_y]>max_colonna)
           {
               max_colonna=mappa[k][pos_y];
               pos_c=k;
           }
       }
       if((pos_x==pos_c)&&(max_colonna!=0))
        printf("Posizione valida: (%d;%d)\n",pos_c,pos_y);
   }
return 0;
}

