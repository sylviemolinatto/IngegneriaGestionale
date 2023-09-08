#include <stdio.h>
#include <stdlib.h>

int main()
{
   const int N;
   printf("Inserisci N: \n");
   scanf("%d",&N);
   int i, tmp, ultimo;
   int alto=N; /* elemN è il numero degli elementi del vettore da ordinare */
   ultimo=alto;
   int array[N];
   for(i=0;i<N;i++)
   {
       printf("Inserisci elemento %d del vettore: \n",i);
       scanf("%d",&array[i]);
   }
   while (alto >= 0) /* in questo modo si evita 1 passaggio*/
     {
         ultimo = -1;
         for (i=0; i<alto-1; i++) /* si mostra che un semplice array come
                                     int nums[elemN] = {4,3,2,1};
                                     non viene ordinato correttamente a causa della condizione i<alto-1
                                  */
         {
           if (array[i]>array[i+1]) /* sostituire ">" con "<" per avere un ordinamento decrescente */
           {
             tmp = array[i];
             array[i] = array[i+1];
             array[i+1] = tmp;
             ultimo = i;
           }
         }
     alto = ultimo;
     }
     for(i=0;i<N;i++)
     {
       printf("%d",array[i]);
       printf(" ");
     }

    return 0;
}
