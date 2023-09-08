#include <stdio.h>
#include <stdlib.h>
float avgVect (int v[], int n);
int upperLimit (int v[], int n, float limit);
int main()
{
    const int N=6;
    int vett[N];
    int i,soglia;
    float media;
    printf("Inserisci gli elementi del vettore\n");
    for(i=0;i<N;i++)
    {
        printf("Elemento %d: ",i);
        scanf("%d",&vett[i]);
    }
    media=avgVect(vett,N);
    soglia=upperLimit(vett,N,media);
    printf("Media: %f\n",media);
    printf("Numero di elementi che superano la soglia: %d\n",soglia);

    return 0;
}
    float avgVect (int v[], int n)
    {
        int i;
        float somma=0,media;

        for(i=0;i<n;i++)
        {
          somma=somma+v[i];
        }
        media=(float)somma/n;
        return media;
    }
    int upperLimit (int v[], int n, float limit)
    {
        int i,k=0;
        for(i=0;i<n;i++)
        {
            if(v[i]>limit)
                k++;

        }
        return k;

    }

