#include <stdio.h>
#include <stdlib.h>
#define N 4

int main()
{
    float matrice[N][N],diagonale_1=0,diagonale_2=0;
    int i,j;
    for(i=0;i<N;i++)
    {
        printf("Inserisci la riga %d: \n",i);
        for(j=0;j<N;j++)
            scanf("%f",&matrice[i][j]);
    }
    i=0;
    j=0;
    do
    {
        diagonale_1 = diagonale_1 + matrice[i][j];
        i++;
        j++;

    }
    while((i<N)&&(j<N));
    printf("diagonale 1 = %.2f\n",diagonale_1);

    i=0;
    j=N-1;
    do
    {
        diagonale_2 = diagonale_2 + matrice[i][j];
        i++;
        j--;
    }
    while((i<N)&&(j>=0));
    printf("diagonale 2 = %.2f\n",diagonale_2);

    if(diagonale_1>diagonale_2)
        printf("%.2f e' maggiore di %.2f\n",diagonale_1,diagonale_2);
    else if (diagonale_1<diagonale_2)
        printf("%.2f e' minore di %.2f\n",diagonale_1,diagonale_2);
    else
    {
        printf("Le due diagonali sono uguali\n");
    }

    return 0;
}
