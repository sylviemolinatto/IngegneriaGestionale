#include <stdio.h>
#include <stdlib.h>

int main()
{
    const int N;
    printf("Inserisci N: ");
    scanf("%d",&N);
    int v[N],i,coppia=0;
    for(i=0;i<N;i++)
    {
        printf("Inserisci l'elemento %d del vettore: ",i);
        scanf("%d",&v[i]);
    }
    for(i=1;i<N;i++)
    {
        if(v[i]==v[i-1])
            coppia++;
    }
    printf("Nel vettore ci sono %d coppie adiacenti uguali",coppia);
    return 0;
}
