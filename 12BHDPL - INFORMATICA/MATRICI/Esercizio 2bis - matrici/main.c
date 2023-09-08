#include <stdio.h>
#include <stdlib.h>
#define R 10
#define C 5

int main()
{
    char matrice[R][C];
    int i,j;
    for(i=0;i<R;i++)
    {
        printf("Inserisci gli elementi della riga %d: \n",i);
        for(j=0;j<C;j++)
        {
            scanf("%c",&matrice[i][j]);

        }
    }
    return 0;
}
