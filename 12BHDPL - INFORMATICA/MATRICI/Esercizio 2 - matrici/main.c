#include <stdio.h>
#include <stdlib.h>
#define R 1000
#define C 6
int main()
{
    char matrice[R][C];
    int i;
    for(i=0; i<R; i++)
    {
        printf("Inserisci la riga %d: \n",i);
            scanf("%c",matrice[i]);
            if(matrice[i][**]=='fine')
                i=R;

    }

    return 0;
}
