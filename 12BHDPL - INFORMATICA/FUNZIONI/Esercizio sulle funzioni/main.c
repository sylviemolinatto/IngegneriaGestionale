#include <stdio.h>
#include <stdlib.h>
#define N 10
int MinMax(int vett[],const int n,int x,int*r1,int*r2);
int main()
{
    int vett[N];
    int i,x,min,max,a;
    for(i=0;i<N;i++)
    {
        printf("Inserisci l'elemento %d del vettore: \n",i);
        scanf("%d",&vett[i]);
    }

    printf("Inserisci x: \n");
    scanf("%d",&x);
    a=MinMax(vett,N,x,&min,&max);
    if(a==1)
        printf("Il vettore contiene %d\n",x);
    else
        printf("Il vettore non contiene %d\n",x);
    printf("Il minimo e' %d\n",min);
    printf("Il massimo e' %d\n",max);
    return 0;
}
int MinMax(int vett[],const int n,int x,int*r1,int*r2)
{
    int i,min,max,k=0;
    min=vett[0];
    max=vett[0];
    for(i=1;i<n;i++)
    {
        if(vett[i]>=max)
            max=vett[i];
        if(vett[i]<=min)
            min=vett[i];
    }
    *r1=max;
    *r2=min;
    for(i=0;i<n;i++)
    {
        if(vett[i]==x)
            k=1;
    }
    if(k==1)
        return 1;
    else
        return 0;
}
