#include <stdio.h>
#include <stdlib.h>

int main()
{
    const int N;
    printf("Inserisci N: \n");
    scanf("%d",&N);
    int v1[N],v2[N],i,j,k=0;
    for(i=0;i<N;i++)
    {
        printf("Inserisci l'elemento %d del vettore 1: \n",i);
        scanf("%d",&v1[i]);
    }
    for(j=0;j<N;j++)
    {
        printf("Inserisci l'elemento %d del vettore 2: \n",j);
        scanf("%d",&v2[j]);
    }
    for(i=0;i<N;i++)
    {
        for(j=0;j<N;j++)
        {
            if(v1[i]==v2[j])
            k++;

        }
    }
    if(k==N)
    {
        printf("I vettori contengono gli stessi elementi\n");
    }
    else
    {
        printf("I vettori contengono elementi diversi\n");
    }
    return 0;
}
