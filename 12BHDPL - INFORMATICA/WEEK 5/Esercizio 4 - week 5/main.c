#include <stdio.h>
#include <stdlib.h>
#include <math.h>

#define BASE 2

int main()
{
  int N,bit,peso,numero;
  peso=0;
  numero=0;
 printf("immetti il numero di bit del numero binario: ");
  scanf("%d",&N);
  printf("immetti il numero binario partendo dal bit meno significativo \n");
  while(peso<N){
    printf("immetti la cifra binaria 2^%d:",peso);
    scanf("%d",&bit);
    numero = numero + bit *pow(BASE,peso);
    peso=peso+1;
  }
printf ("\n");
printf ("la cifra decimale calcolata è: %d\n", numero);

return 0;
}
