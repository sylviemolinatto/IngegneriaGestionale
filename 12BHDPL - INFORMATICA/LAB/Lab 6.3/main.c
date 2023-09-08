#include <stdio.h>
#include <stdlib.h>

int main()
{
    const int N;
    printf("Inserisci N: \n");
    scanf("%d",&N);
    int v[N],occorrenze[N],i,j,k;
    for(k=0;k<N;k++)
    {
        occorrenze[k]=1;
    }
    for(i=0;i<N;i++)
    {
        printf("Inserisci l'elemento %d del vettore: \n",i);
        scanf("%d",&v[i]);
    }
    for (i=0;i<N;i++)
    {
        for(j=0;j<i&&occorrenze[i]==1;j++)
        {
            if(v[j]==v[i])
            {
                occorrenze[j]++;
                occorrenze[i]=0;

            }
        }

    }
   for(i=0;i<N;i++)
   {
       if(occorrenze[i]>1)
       {
           printf("Elemento %d replicato, prima posizione: %d, numero di occorrenze: %d\n",v[i],i+1,occorrenze[i]);
       }
   }
    return 0;
}
