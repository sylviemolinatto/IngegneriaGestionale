#include <stdio.h>
#include <stdlib.h>

int main()
{
    float a,b,somma;
    float media;
    scanf("%f%f",&a,&b);
    somma=a+b;
    media=(float)(somma/2);
    printf("La media tra a e b e': %f\n",media);
    return 0;
}
