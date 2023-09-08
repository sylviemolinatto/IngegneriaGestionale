#include <stdio.h>
#include <stdlib.h>

int main()
{
    int L,riga,colonna;
    printf("Inserisci un numero: ");
    scanf("%d",&L);
    for(riga=1;riga<=L;riga++)
    {
        for(colonna=1;colonna<riga;colonna++)
        {
            printf(" ");

        }
        for(colonna=1;colonna<=L-riga+1;colonna++)
        {
            printf("*");
        }

        printf("\n");
    }

    return 0;
}
