#include <stdio.h>
#include <stdlib.h>

int main()
{
    const int N;
    printf("Inserisci N: ");
    scanf("%d",&N);
    int v[N],i,pari=0,somma=0;
    for(i=0;i<N;i++)
    {
        printf("Inserisci l'elemento %d vel vettore: ",i);
        scanf("%d",&v[i]);
    }
    for(i=0;i<N;i++)
    {
        somma=somma+v[i];
        if(v[i]%2==0)
            pari++;
    }
    printf("Nel vettore ci sono %d elementi pari",pari);
    printf("La somma degli elementi del vettore e' %d",somma);
    return 0;
}
