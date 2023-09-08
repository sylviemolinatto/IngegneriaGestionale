#include <stdio.h>
#include <stdlib.h>
int pari(int n);
int main()
{
    int x;
    scanf("%d",&x);
    printf("Il numero %d e' ",x);
    if(pari(x)==0)
        printf("pari");
    else
        printf("dispari");

    return 0;
}
int pari(int n)
{
    if (n%2==0)
        return 0;
    else
        return 1;
}
