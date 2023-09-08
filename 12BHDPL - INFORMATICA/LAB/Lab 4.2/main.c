#include <stdio.h>
#include <stdlib.h>

int main()
{
    int gg,mese,anno;
    printf("Inserisci gg/mese/anno:           \n");
    scanf("%d%d%d",&gg,&mese,&anno);
    switch(mese){
    case 1:
        printf("%d Gennaio %d\n",gg,anno);
        break;
    case 2:
        printf("%d Febbraio %d\n",gg,anno);
        break;
    case 3:
        printf("%d Marzo %d\n",gg,anno);
        break;
    case 4:
        printf("%d Aprile %d\n",gg,anno);
        break;
    case 5:
        printf("%d Maggio %d\n",gg,anno);
        break;
    case 6:
        printf("%d Giugno %d\n",gg,anno);
        break;
    case 7:
        printf("%d Luglio %d\n",gg,anno);
        break;
    case 8:
        printf("%d Agosto %d\n",gg,anno);
        break;
    case 9:
        printf("%d Settembre %d\n",gg,anno);
        break;
    case 10:
        printf("%d Ottobre %d\n",gg,anno);
        break;
    case 11:
        printf("%d Novembre %d\n",gg,anno);
        break;
    case 12:
        printf("%d Dicembre %d\n",gg,anno);
        break;
    default:
        printf("Errore\n");
        break;

    }

    return 0;
}
