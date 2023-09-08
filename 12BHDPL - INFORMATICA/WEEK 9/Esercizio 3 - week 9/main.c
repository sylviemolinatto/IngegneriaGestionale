#include <stdio.h>
#include <stdlib.h>
#define R 5
#define C 4

int main()
{
    int matrice[R][C];
    int i,j,l,k=0;
    for(i=0; i<R; i++)
    {
        printf("Riga %d: ",i);
        for(j=0; j<C; j++)
        {
            scanf("%d",&matrice[i][j]);
        }
    }

    for(i=0;i<R;i++)
    {
        for(j=0; j<C; j++)
        {
            for(l=1; l<R; l++)

            {
                if(matrice[i][j]==matrice[i+l][j])
                    k++;
            }
        }
    }



    if(k>=C)
    {
        printf("La matrice contiene almeno due righe uguali tra loro\n");
    }
    else
    {
        printf("La matrice non contiene righe uguali tra loro\n");
    }
    return 0;
}
