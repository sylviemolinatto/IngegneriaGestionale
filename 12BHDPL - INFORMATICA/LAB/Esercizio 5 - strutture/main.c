#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <string.h>
#include <ctype.h>
typedef struct {
    float Re;
    float Im;
}numero_complesso;
numero_complesso sommacompl(numero_complesso x,numero_complesso y);
numero_complesso prodottocompl(numero_complesso x,numero_complesso y);


int main()
{
    numero_complesso a,b,ris1;
    printf("Parte reale 1: ");
    scanf("%f",&a.Re);
    printf("\nParte immaginaria 1: ");
    scanf("%f",&a.Im);
    printf("\nParte reale 2: ");
    scanf("%f",&b.Re);
    printf("\nParte immaginaria 2: ");
    scanf("%f",&b.Im);
    ris1=sommacomp1(a,b);
    printf("\nLa somma dei due numeri complessi = %f + i%f\n",ris1.Re,ris1.Im);

    return 0;
}
numero_complesso sommacompl(numero_complesso x,numero_complesso y)
{
  numero_complesso risultato;
  risultato.Re=x.Re+y.Re;
  risultato.Im=x.Im+y.Im;
  return risultato;

};
numero_complesso prodottocompl(numero_complesso x,numero_complesso y)
{


}
