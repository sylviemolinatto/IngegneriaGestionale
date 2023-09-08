#include <stdio.h>
#include <stdlib.h>

int main()
{
    int A,B,resto;
    printf("Inserisci il valore di A: \n");
    printf("Inserisci il valore di B: \n");
    scanf("%d%d",&A,&B);
    if((A>0)&&(B>0))
    {
       do
        {
          resto=A%B;
          A=B;
          B=resto;

        } while(resto!=0);

    printf("Il MCD e' %d",A);
    }

    else
    printf("Errore: i numeri A e B devono essere positivi\n");


    return 0;
}
