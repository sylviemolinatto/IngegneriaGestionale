#include <stdio.h>
#include <stdlib.h>
#define N 7
#define SOGLIA 10
int main()
{
    float v[N],somma=0,media,max,min,differenza;
    int i,sup=0;
    for(i=0;i<N;i++)
    {
        printf("Inserisci l'elemento %d del vettore: ",i);
        scanf("%f",&v[i]);
    }
    for(i=0;i<N;i++)
    {
        somma=somma+v[i];
        if(v[i]>SOGLIA)
            sup++;
    }
    max=v[0];
    min=v[0];
    for(i=1;i<N;i++)
    {
        if (v[i]>max)
            max=v[i];
    }
    for(i=1;i<N;i++)
    {
        if(v[i]<min)
            min=v[i];
    }
    printf("Il massimo e' %f e il minimo e' %f\n",max,min);
    media=somma/N;
    differenza=max-min;
    printf("La piovosita' media e' %f\n",media);
    printf("I giorni con piovosita' superiore alla soglia sono %d\n",sup);
    printf("La differenza tra il giorno piu' piovoso e quello meno piovoso e' %f\n",differenza);
    return 0;
}
