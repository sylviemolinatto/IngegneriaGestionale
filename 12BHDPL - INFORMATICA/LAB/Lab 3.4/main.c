#include <stdio.h>
#include <stdlib.h>
#define PB 100
#define C 40
#define M -20

int main()
{
    int price,features,months,years;
    printf("Inserisci la quantita' di caratteristiche del telefono: \n");
    printf("Inserisci il numero di anni per cui il cellulare e' stato posseduto: \n");
    scanf("%d%d",&features,&years);
    months=12*years;
    printf("il numero di mesi per cui il cellulare e' stato posseduto e': %d\n",months);
    price=PB+C*features+M*months;
    if(price<=0)
    {
        printf("non conviene comprare il telefono\n");
    }
    else
    {
       printf("il prezzo massimo spendibile per il cellulare e': %d\n",price);
    }

    return 0;
}
