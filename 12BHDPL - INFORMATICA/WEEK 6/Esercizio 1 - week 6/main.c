#include <stdio.h>
#include <stdlib.h>

int main()
{
    const int N=8;
    int v[N],i,j,n=0,somma=0;
    for(i=0;i<N;i++)
    {
        scanf("%d",&v[i]);
    }
    somma=v[0]+v[1]+v[2]+v[3]+v[4]+v[5]+v[6]+v[7];
    printf("la somma degli elementi e' %d\n",somma);
    for (j=0;j<N;j++)
    {
        if (v[j]%2==0)
        {
            n++;
        }
    }
    printf("gli elementi pari sono %d\n",n);


    return 0;
}
