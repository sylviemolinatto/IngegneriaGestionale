#include <stdio.h>
#include <stdlib.h>

int main()
{
    int x,somma;
    somma=0;
    do
    {
        scanf("%d",&x);
        somma=somma+x;

    } while (x!=0);
     printf("la somma e' %d\n",somma);
    return 0;
}
