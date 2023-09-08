#include <stdio.h>
#include <stdlib.h>

int main()
{
    int A,B,C,X;
    printf("Inserisci il numero A: \n");
    printf("Inserisci il numero B: \n");
    scanf("%d%d%d",&A,&B,&X);
    C=(A<X&&X<B);
    printf("Il numero C e' %d",C);
    return 0;
}
