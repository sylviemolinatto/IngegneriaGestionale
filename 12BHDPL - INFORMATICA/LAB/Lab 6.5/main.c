#include <stdio.h>
#include <stdlib.h>
int power(int base,int esponente);
int main()
{
    int x,y,risultato;
    printf("x: \n");
    scanf("%d",&x);
    printf("y: \n");
    scanf("%d",&y);
    risultato=power(x,y);
    printf("Il risultato e' %d",risultato);
    return 0;
}

int power(int base,int esponente)
{
    int i,risultato=base;

        if(esponente==0)
        risultato=1;
        else
        {
            for(i=1;i<esponente;i++)
            {
                risultato=risultato*base;
            }
        }
    return risultato;
}
