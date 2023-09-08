#include <stdio.h>
#include <stdlib.h>
#define R 4
#define C 6
int main()
{
    int matrice[R][C];
    char o;
    int n,i,j,somma;
    for(i=0;i<R;i++)
    {
        printf("Inserisci gli elementi della riga %d\n",i);
        for(j=0;j<C;j++)
        {
            scanf("%d",&matrice[i][j]);
        }
    }
    printf("Inserisci un numero: \n");
    scanf("%d",&n);
    getchar();
    printf("Inserisci un carattere: \n");
    scanf("%c",&o);
    if(o=='c')
    {
        somma=0;
        i=0;
        do
        {
            somma=somma+matrice[i][n];
            i++;
        }while(i<R);
        printf("La somma è %d",somma);
    }


     else if(o=='r')
    {
        somma=0;
        i=0;
        do
        {
            somma=somma+matrice[n][i];
            i++;
        }while(i<C);
        printf("La somma e' %d",somma);
    }

    else
        printf("Error\n");


    return 0;
}
