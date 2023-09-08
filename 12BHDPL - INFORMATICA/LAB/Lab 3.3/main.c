#include <stdio.h>
#include <stdlib.h>
#define K 10
int main()
{
    float a,b,c,d;
    float x;
    scanf("%f%f%f%f",&a,&b,&c,&d);
    x=(-d*K)/(a+b*c);
    printf("%f e' il risultato dell'equazione\n",x);
    return 0;
}
