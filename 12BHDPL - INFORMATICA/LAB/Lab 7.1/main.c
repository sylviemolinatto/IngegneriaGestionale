#include <stdio.h>
#include <stdlib.h>
int power(int base,int exponent);
int main()
{
    const int N;
    printf("Inserisci N: \n");
    scanf("%d",&N);
    int vbase[N],vexponent[N],vres[N];
    int i,j;
    for(i=0; i<N; i++)
    {
        printf("Inserisci l'elemento %d del vettore vbase: \n",i);
        scanf("%d",&vbase[i]);
    }
    for(i=0; i<N; i++)
    {
        printf("Inserisci l'elemento %d del vettore vexponent: \n",i);
        scanf("%d",&vexponent[i]);
    }

    for(i=0; i<N; i++)
    {
            vres[i]=power(vbase[i],vexponent[i]);
    }
    for(i=0; i<N; i++)
    {
        printf("%d",vres[i]);
        printf(" ");
    }

    return 0;
}
int power(int base,int exponent)
{
    int result=base,i;
    if (exponent==0)
        result=1;
    for(i=1;i<exponent;i++)
    {
        result= result*base;
    }
    return result;
}
