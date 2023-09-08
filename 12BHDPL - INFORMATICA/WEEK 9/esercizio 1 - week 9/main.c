#include <stdio.h>
#include <stdlib.h>
#define R 2
#define C 3
int main()
{
    int matrice[R][C];
    int i,j,pari=0,dispari=0;
    printf("Matrice");
    for(i=1;i<=R;i++)
    {
        printf("Riga %d: \n",i);
        for(j=0;j<C;j++)
        {
            scanf("%d",&matrice[i][j]);
        }
    }

    for(i=0;i<R;i++)
    {
        for(j=0;j<C;j++)
            if(matrice[i][j]%2==0)
        {
            pari++;
        }
        else
        {
            dispari++;
        }
        printf("Nella riga %d ci sono %d numeri pari e %d numeri dispari\n",i,pari,dispari);
        pari=0;
        dispari=0;

    }


    return 0;
}
