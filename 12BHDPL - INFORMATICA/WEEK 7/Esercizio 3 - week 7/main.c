#include <stdio.h>
#include <stdlib.h>
#define N 4
#define M 2

int main()
{
    int v1[N],v2[M],i,j,k,indici[M];
    for(i=0; i<N; i++)
    {
        printf("Inserisci l'elemento %d del vettore 1: \n",i);
        scanf("%d",&v1[i]);
    }
    for(i=0; i<M; i++)
    {
        printf("Inserisci l'elemento %d del vettore 2: \n",i);
        scanf("%d",&v2[i]);
    }

    k=0;
    i=0;
    j=0;
    while(j<M)
    {
        if(v1[i]==v2[j])
           {
               indici[j]=i;
               i++;
               j++;
           }
        else
        {
            i++;
        }
    }
    for(i=0;i<M-1;i++)
    {
        if(indici[i]==indici[i+1]-1)
        {
           k++;
        }
    }
    if(k==M-1)
    {
        printf("v1 contiene gli elementi di v2 in sequenza\n");
    }
    else
    {
        printf("v1 non contiene gli elementi di v2 in sequenza\n");
    }

            return 0;
}
