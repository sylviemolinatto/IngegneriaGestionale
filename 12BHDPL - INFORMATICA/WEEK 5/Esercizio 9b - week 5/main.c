#include <stdio.h>
#include <stdlib.h>
#define L 5
int main()
{
    int riga,colonna;
    for(riga=1;riga<=L;riga++)
    {
        for(colonna=riga;colonna<=L;colonna++)
        {
            printf("*");
        }
        printf("\n");
    }

    return 0;
}
