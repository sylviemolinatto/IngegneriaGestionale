#include <stdio.h>
#include <stdlib.h>

int main()
{
    int N,n,j=1,somma=0;
    float media=0;
    printf("Inserisci un numero N: \n");
    scanf("%d",&N);
    do
    {
        scanf("%d",&n);
        j++;
        somma=somma+n;
        media=(float)somma/j;
    } while(j<10&&media<=N);
    printf("la media e' %.2f",media);


    return 0;
}
