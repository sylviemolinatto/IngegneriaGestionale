#include <stdio.h>
#include <stdlib.h>
#define N 10

int main()
{
    int v[N],i=0,crescente=-1,decrescente=-1,monotona=1,k;
    printf("Inserisci il primo valore del vettore: \n");
    scanf("%d",&v[i]);
    i++;
    do
        {
            printf("Inserisci un numero: \n");
            scanf("%d",&v[i]);
            if(v[i]>v[i-1])
               {
                 crescente=1;
               }

            else if(v[i]<v[i-1])
            {
                decrescente=1;
            }

            if (crescente-decrescente==0)
            {
                monotona=0;
            }

            else
            {
                i++;
            }

        } while((i<N)&&(monotona==1));

        printf("Il vettore contiene i seguenti valori: \n");
        for(k=0;k<i;k++)
        {
            printf("%d",v[k]);
        }
        printf("\n");



    return 0;
}
