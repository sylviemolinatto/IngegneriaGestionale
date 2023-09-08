#include <stdio.h>
#include <stdlib.h>

int main()
{
    int N,i,j,k;
    do{
            scanf("%d",&N);
    }while(N%2==0);
    for (i=0;i<(N+1)/2;i++)
    {
        for(j=0;j<i;j++)
        {
            printf(" ");
        }
        for(k=0;k<(N-(2*i));k++)
        {
            printf("*");
        }
        printf("\n");
    }
    return 0;
}
