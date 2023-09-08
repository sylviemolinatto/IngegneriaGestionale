#include <stdio.h>
#include <stdlib.h>

int main()
{
    const int N;
    int i,j,k=0;
    printf("Inserisci N: \n");
    scanf("%d",&N);
    int v[N];
    for(i=0;i<N;i++)
    {
        printf("Inserisci l'elemento %d del vettore: \n",i);
        scanf("%d",&v[i]);
    }
    for(j=0;j<N;j++)
    {
        if(v[j]==v[N-j-1])
            k++;

    }
    if(k==N)
        printf("La sequenza di numeri e' palindroma\n");
    else
        printf("La sequenza di numeri non e' palindroma\n");


    return 0;
}
