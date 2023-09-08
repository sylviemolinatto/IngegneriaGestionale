#include <stdio.h>
#include <stdlib.h>

int main()
{
    float T[8];
    float max,min,esc_max,tmpT;
    int H[8];
    int i,j,tmpH;
    for(i=0; i<8; i++)
    {
        printf("Inserisci l'elemento %d del vettore temperature: \n",i);
        scanf("%f",&T[i]);
    }
    for(i=0; i<8; i++)
    {
        printf("Inserisci l'elemento %d del vettore orari: \n",i);
        scanf("%d",&H[i]);
    }
    max=T[0];
    for(i=0; i<8; i++)
    {
        if(T[i]>max)
            max=T[i];

    }
    min=T[0];
    for(i=0; i<8; i++)
    {
        if(T[i]<min)
            min=T[i];
    }
    esc_max=max-min;
    printf("L'escursione massima e' %.1f\n",esc_max);
    printf("Ordino in base alle temperature crescenti\n");

    for(i=0; i<8; i++)
    {
        tmpT=T[i];
        tmpH=H[i];
        for(j=i-1; (j>=0)&&(T[j]>=tmpT); j--)
        {
            T[j+1]=T[j];
            H[j+1]=H[j];
        }
        T[j+1]=tmpT;
        H[j+1]=tmpH;
    }

    for(i=0; i<8; i++)
    {
        printf("%.1f %d\n",T[i],H[i]);
    }

    return 0;
}

