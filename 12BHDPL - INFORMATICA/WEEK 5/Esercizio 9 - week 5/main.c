#include <stdio.h>
#include <stdlib.h>
#define M 4
#define N 8

int main()
{
    int riga,colonna;
    for(riga=1;riga<=M;riga++)
    {
        for(colonna=1;colonna<=N;colonna++)
        {
             printf("*");
        }
        printf("\n");
    }

    return 0;
}
