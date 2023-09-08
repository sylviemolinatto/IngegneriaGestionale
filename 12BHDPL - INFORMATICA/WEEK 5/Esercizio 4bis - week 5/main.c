#include <stdio.h>
#include <stdlib.h>
#include <math.h>

#define BASE 2

int main()
{   int N,bit,peso,numero;
    printf("immetti il numero di bit del numero binario: ");
    scanf("%d",&N);
    printf("immetti il numero binario a partire dal bit meno significativo: \n");
    numero=0;
    for(peso=0;peso<N;peso++){
        printf("immetti la cifra binaria 2^%d: ",peso);
        scanf("%d",&bit);
        numero = numero + bit * pow(BASE,peso);
    }
    printf("\n");
    printf("il numero decimale corrispondente e' %d\n",numero);
    return 0;
}
