#include <stdio.h>
#include <stdlib.h>
#define N 6
int mult(int v[],int n,int x);
int main()
{
    int i;
    int vett[N],x;
    for(i=0;i<N;i++)
    {
        printf("Inserisci l'elemento %d del vettore: \n",i);
        scanf("%d",&vett[i]);
    }
    printf("Inserisci x: \n");
    scanf("%d",&x);

    mult(vett,N,x);

    for(i=0;i<N;i++)
    {
        printf("%d",vett[i]);
        printf(" ");
    }
    return 0;
}

int mult(int v[],int n,int x)
{
    int i;
    for(i=0; i<n; i++)
    {
        v[i]=v[i]*x;
    }
    return v[i];
}
