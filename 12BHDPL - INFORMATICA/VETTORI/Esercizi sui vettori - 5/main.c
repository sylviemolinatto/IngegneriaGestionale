#include <stdio.h>
#include <stdlib.h>
#define N 6
int main()
{
    int v[N],i,num,pos=0;
    for(i=0; i<N; i++)
    {
        printf("Inserisci l'elemento %d del vettore: ",i);
        scanf("%d",&v[i]);
    }
    printf("Inserisci num: ");
    scanf("%d",&num);
    for(i=1; i<=N; i++)
    {
        if(v[i]==num)
        {
            pos=i;
        }
    }
    if(pos==0)
        printf("%d non esiste nel vettore\n",num);
    else
        printf("L'elemento %d del vettore e' uguale a %d\n",pos,num);
    return 0;
}
