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
            if(riga==1||riga==4)
            {
                printf("*");
            }
            else if(colonna==1||colonna==8)
            {
                printf("*");
            }
            else
                printf(" ");
        }
        printf("\n");

    }
    return 0;
}
