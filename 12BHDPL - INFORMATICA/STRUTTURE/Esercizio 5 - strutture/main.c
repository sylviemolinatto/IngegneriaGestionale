#include <stdio.h>
#include <stdlib.h>
typedef struct
{
    float Re;
    float Im;
}numero_complesso;
numero_complesso somma(numero_complesso x,numero_complesso y);
numero_complesso prodotto(numero_complesso x,numero_complesso y);
int main()
{
    numero_complesso a,b,res1,res2;
    printf("Parte reale 1: ");
    scanf("%f",&a.Re);
    printf("\nParte immaginaria 1: ");
    scanf("%f",&a.Im);
    printf("\nParte reale 2: ");
    scanf("%f",&b.Re);
    printf("\nParte immaginaria 2: ");
    scanf("%f",&b.Im);
    res1=somma(a,b);
    printf("somma= %.2f+%.2fi\n",res1.Re,res1.Im);
    res2=prodotto(a,b);
    printf("prodotto=%.2f+%.2fi\n",res2.Re,res2.Im);
    return 0;
}
numero_complesso somma(numero_complesso x,numero_complesso y)
{
    numero_complesso risultato;
    risultato.Re=x.Re+y.Re;
    risultato.Im=x.Im+y.Im;
    return risultato;
}
numero_complesso prodotto(numero_complesso x,numero_complesso y)
{
    numero_complesso risultato;
    risultato.Re=((x.Re*y.Re)-(x.Im*y.Im));
    risultato.Im=((x.Im*y.Re)+(x.Re*y.Im));
    return risultato;
}
