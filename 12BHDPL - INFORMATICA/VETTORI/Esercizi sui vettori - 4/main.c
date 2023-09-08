#include <stdio.h>
#include <stdlib.h>
#define N 6
int main()
{
    int v[N],i,numero,div,k=0,stop=0;
    for(i=0;(i<N)&&(stop==0); i++)
    {
        printf("Inserisci num: ");
        scanf("%d",&numero);
        if (v[i]!=0)
            v[i]=numero;
        else
            stop=1;
    }
    printf("Hai letto %d numeri\n",i);
    stop=i;
    printf("Inserisci div: ");
    scanf("%d",&div);
    for(i=0; i<stop; i++)
    {
        if((v[i]%div)==0)
            k++;
    }

    printf("Gli elementi multipli di %d sono %d",div,k-1);

    return 0;
}
