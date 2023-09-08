#include <stdio.h>
#include <stdlib.h>

int main()
{
    FILE *fp;
    int x,y,i,j,matrice[10][10],risultato;
    printf("Inserisci x: \n");
    scanf("%d",&x);
    printf("Inserisci y: \n");
    scanf("%d",&y);
    fp=fopen("pitagora.txt","r");
    for(i=0;i<10;i++)
    {
        for(j=0;j<10;j++)
        {
            fscanf(fp,"%d",&matrice[i][j]);
        }
    }

    risultato=matrice[x-1][y-1];
    printf("%d x %d=%d",x,y,risultato);

    return 0;
}
