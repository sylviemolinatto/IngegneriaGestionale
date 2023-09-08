#include <stdio.h>
#include <stdlib.h>

int main()
{
   const int N;
   printf("Inserisci N: ");
   scanf("%d",&N);
   int v[N],i,j;
   for(i=0;i<N;i++)
    {
        printf("Inserisci l'elemento in posizione %d: ",i);
        scanf("%d",&v[i]);
    }
   for(j=0;j<N-2;j++)
   {
       if ((v[j]<v[j+1])&&(v[j+1]<v[j+2]))
        printf("Ho trovato la serie crescente composta da %d %d %d in posizione &d\n",v[j],v[j+1],v[j+2],j);
       else
        printf("Non ho trovato una serie crescente di 3 numeri consecutivi\n");
   }



    return 0;
}
