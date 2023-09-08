#include <stdio.h>
#include <stdlib.h>

int main()
{
    int N,temp,max,min;
    printf("Indica quanti numeri saranno inseriti: ");
    scanf("%d",&N);
    if(N<=0){
        printf("Errore: non sara' inserito nessun numero\n");
    }
    else{
        printf("Inserisci un numero: ");
        scanf("%d",&temp);
        N=N-1;
        max=temp;
        min=temp;

    while(N>0){
        printf("Inserisci un numero: ");
        scanf("%d",&temp);
        if(temp>max)
            max=temp;
        else
        {
        if(temp<min)
                min=temp;
        }
        N=N-1;
    }
    printf("\n");
    printf("temperatura minima %d temperatura massima %d\n", min, max);
    }
    return 0;
}
