#include <stdio.h>
#include <stdlib.h>

int main()
{
    const int N=7;
    float v[N],somma=0,media,min,max,diff;
    int j,i;
    for(i=0;i<N;i++)
    {
        scanf("%f",&v[i]);
    }
    max=v[0];
    min=v[0];
    for(i=0;i<N;i++)
    {
        somma=somma+v[i];
        if(v[i]>1)
        {
            j++;
        }
        if(v[i]>max)
        {
            max=v[i];
        }
        if(v[i]<min)
        {
            min=v[i];
        }
    }
    printf("I giorni con piovosita' superiore a 1 sono %d\n",j);
    media= somma/N;
    printf("La piovosita' media e' %f\n",media);
    diff=max-min;
    printf("La differenza tra l'indice del giorno piu' piovoso e quello meno piovoso e' %f\n",diff);

    return 0;
}
