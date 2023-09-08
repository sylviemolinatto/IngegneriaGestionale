#include <stdio.h>
#include <stdlib.h>
#define N 4
#define M 3
int main()
{
    FILE *fp;
    int matrice[N][M],i,j;
    fp=fopen("matrice.txt","r");
    if(fp==NULL)
    {
        printf("Errore nell'apertura\n");
    }
    else
        {
            for(i=0;i<N;i++)
            {
                    for(j=0;j<M;j++)
                    {
                      fscanf(fp,"%d",&matrice[i][j]);
                    }
            }
        }
    for(i=0;i<N;i++)
    {
        for(j=0;j<M;j++)
        {
            printf("%d",matrice[i][j]);
        }
        printf("\n");
    }

    return 0;
}
