#include <stdio.h>
#include <stdlib.h>

int main()
{
    int a,b,c,delta;
    printf("Inserisci 3 numeri a,b,c:   ");
    scanf("%d%d%d",&a,&b,&c);
    delta=b*b-4*a*c;
    if(delta>0)
    {
       printf("le soluzioni sono reali e distinte\n");
    }
    else if(delta==0)
    {
        printf("le soluzioni sono reali e coincidenti\n");
    }
    else
    {
        printf("le soluzioni sono immaginarie\n");
    }
    return 0;
}
