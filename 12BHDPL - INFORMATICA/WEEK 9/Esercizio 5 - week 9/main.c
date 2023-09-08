#include <stdio.h>
#include <stdlib.h>
#define R 4
#define C 5
#define r 2
#define c 2
int main()
{
    int matrice[R][C];
    int sottomatrice[r][c];
    int i,j,h=0,k=0,max,somma=0;
    for(i=0; i<R; i++)
    {
        printf("Inserisci gli elementi della riga %d",i);
        for(j=0; j<C; j++)
        {
            scanf("%d",&matrice[i][j]);
        }
    }
    for (i=0; i<R-r+1; i++)
    {
        for (j=0; j<C-c+1; j++)
        {
            somma=somma+matrice[i][j];

        }

    }



    return 0;
}
