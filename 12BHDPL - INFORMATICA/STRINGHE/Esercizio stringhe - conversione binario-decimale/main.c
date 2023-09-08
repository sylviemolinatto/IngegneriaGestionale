#include <stdio.h>
#include <stdlib.h>
#include <string.h>
int main()
{
    const int NBIT = 24;
    char binario[NBIT+1];
    int num_cifre, i, corretto, decimale;
    printf("Inserisci un numero binario (massimo 24 bit): ");
    gets(binario);
    num_cifre = strlen(binario);
    corretto = 1;
    for(i = 0; (i < num_cifre)&&(corretto == 1); i++)
    {
        if((binario[i]!='1')&&(binario[i]!='0'))
            corretto = 0;
    }
    if(corretto == 0)
        printf("Il numero binario inserito non e' valido \n");
    else
    {
        printf("Il numero binario inserito e' corretto e contiene %d cifre \n", num_cifre);
               decimale = 0;
               for(i = 0; i < num_cifre; i++)
    {
        if(binario[i] == '1')
                decimale = 2*decimale + 1;
            else
                decimale = 2*decimale;
        }
    }
    printf("Il valore decimale e' %d \n", decimale);
    return 0;
}

