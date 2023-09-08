#include <stdio.h>
#include <stdlib.h>

int main()
{
int N,temp,cont;
float somma,media;
somma=0;
cont=0;
printf("Inserisci un numero; ");
scanf("%d",&N);
while(cont<N){
    printf("inserisci una temperatura: ");
    scanf("%d",&temp);
    cont=cont+1;
    somma=somma+temp;
}
    media=somma/N;
    printf("la media e' %f\n",media);

    return 0;
}
