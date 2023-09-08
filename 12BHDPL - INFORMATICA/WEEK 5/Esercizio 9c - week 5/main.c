#include <stdio.h>
#include <stdlib.h>

int main()
{
    int L;
    printf("inserisci un numero: ");
    scanf("%d",&L);
    int riga,colonna;
    for(riga=1;riga<=L;riga++)
    {
        for(colonna=1;colonna<=L-riga;colonna++)
        {
            printf(" ");
        }
        for(colonna=1;colonna<=riga;colonna++)
        {
            printf("*");
        }
      printf("\n");

    }
    return 0;
}
