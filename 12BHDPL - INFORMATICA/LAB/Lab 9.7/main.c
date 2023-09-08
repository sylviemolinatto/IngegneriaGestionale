#include <stdio.h>
#include <stdlib.h>
#define N 4

int main()
{
    int matrice[N][N];
    int i,j,tmp;
    for(i=0; i<N; i++)
    {
        for(j=0; j<N; j++)
        {
            matrice[i][j]=0;
        }
    }
    i=0;
    j=0;

        matrice[i][j]=1;
        j++;
        do
        {
            matrice[i][j]=matrice[i][j-1]+1;
            j++;
        }
        while(j<N);

        j--;
        do
        {
            i++;
            matrice[i][j]=matrice[i-1][j]+1;
        }
        while(i<N);

        i--;
        do
        {
            j--;
            matrice[i][j]=matrice[i][j+1]+1;
        }
        while(j>0);
        do
        {
            i--;
            matrice[i][j]=matrice[i+1][j]+1;
        }
        while(i>1);

        j++;
        matrice[i][j]=1;
        do
        {
            j++;
            matrice[i][j]=matrice[i][j-1]+1;
        }while(j<N-2);
        i++;
        matrice[i][j]=matrice[i-1][j]+1;
        tmp=matrice[i][j];
        j--;
        matrice[i][j]=tmp+1;

 for(i=0;i<N;i++)
    {
        for(j=0;j<N;j++)
        {
            printf("%d",matrice[i][j]);
            printf(" ");
        }
        printf("\n");
    }



    return 0;
}
