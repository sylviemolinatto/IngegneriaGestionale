#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>
#define N 4

int main(int argc,char *argv[])
{
   int ascisse[N];
   int ordinate[N];
   float distanza1,distanza2,distanza3,distanza4,distanza,distanza_minima;
   int i;
   for(i=1;i<=N;i++)
   {
       printf("Ascissa del punto %d = \n",i);
       scanf("%d",&ascisse[i]);
       printf("Ordinata del punto %d = \n",i);
       scanf("%d",&ordinate[i]);
   }
   if (argc!=2)
    printf("Errore!\n");
   for(i=0;i<argc-1;i++)
   {
       if(argv[i][0]=='-')
       {
           switch(argv[i][1])
       {
           case 'm':
           distanza1=sqrt((pow(ascisse[1],2)-pow(ascisse[2],2)+(pow(ordinate[1],2)-pow(ordinate[2],2))));
           distanza2=sqrt((pow(ascisse[2],2)-pow(ascisse[3],2)+(pow(ordinate[2],2)-pow(ordinate[3],2))));
           distanza3=sqrt((pow(ascisse[3],2)-pow(ascisse[4],2)+(pow(ordinate[3],2)-pow(ordinate[4],2))));
           distanza4=sqrt((pow(ascisse[4],2)-pow(ascisse[1],2)+(pow(ordinate[4],2)-pow(ordinate[1],2))));
           distanza=distanza1+distanza2+distanza3+distanza4;
           printf("Lunghezza del percorso = %f",distanza);

           break;

           case 'a':
           distanza1=sqrt((pow(ascisse[1],2)-pow(ascisse[2],2)+(pow(ordinate[1],2)-pow(ordinate[2],2))));
           distanza2=sqrt((pow(ascisse[2],2)-pow(ascisse[3],2)+(pow(ordinate[2],2)-pow(ordinate[3],2))));
           distanza3=sqrt((pow(ascisse[3],2)-pow(ascisse[4],2)+(pow(ordinate[3],2)-pow(ordinate[4],2))));
           distanza4=sqrt((pow(ascisse[4],2)-pow(ascisse[1],2)+(pow(ordinate[4],2)-pow(ordinate[1],2))));
           distanza_minima=distanza1;
           if(distanza2<distanza_minima)
           distanza_minima=distanza2;
           if(distanza3<distanza_minima)
            distanza_minima=distanza3;
           if(distanza4<distanza_minima)
            distanza_minima=distanza4;
           printf("Distanza minima = %f",distanza_minima);


           break;

           default:
            printf("Errore comando\n");


       }
       }
   }


    return 0;
}
