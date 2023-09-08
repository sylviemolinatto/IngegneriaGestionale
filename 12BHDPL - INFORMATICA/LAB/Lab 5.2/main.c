#include <stdio.h>
#include <stdlib.h>
#define N 10

int main()
{
    int v[N],i,j,somma=0,max,posmax;
    float media;
    for(i=0;i<N;i++)
    {
        printf("Inserisci i valori del vettore: \n");
        scanf("%d",&v[i]);
        somma=somma+v[i];
    }
    media=(float)somma/N;
    printf("la media e' %f\n",media);
    max=v[0];
    posmax=0;
    for (j=1;j<N;j++)
    {
        if(v[j]>max)
        {
            max=v[j];
            posmax=j;
        }
    }

    printf("Il valore massimo e' %d e si trova in posizione %d\n",max,posmax);

    return 0;
}
