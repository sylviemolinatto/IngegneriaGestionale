#include <stdio.h>
#include <stdlib.h>
#define R 5
#define C 5
int main()
{
    int i,j,k=1;
    int matrice[R][C];
    for(i=0;i<R;i++)
    {
        printf("Inserisci gli elementi della riga %d: \n",i);
        for(j=0;j<C;j++)
        {
            scanf("%d",&matrice[i][j]);
        }
    }
    for(i=0;i<R;i++)
    {
        for(j=0;j<C;j++)
        {
            if(matrice[i][j]==0&&matrice[i][j+1]==0)
            {
                k++;
            }
        }
         printf("Nella riga %d ci sono %d elementi adiacenti uguali a 0\n",i,k);
         k=0;


    }

    return 0;
}
