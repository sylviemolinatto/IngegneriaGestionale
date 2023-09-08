#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>
#include <string.h>
#define N 100
int main()
{
    char str[N+1];
    int lunghezza,i,cifre=0,carattere=0,maiuscolo=0,spazio=0,parola=0;
    printf("Dammi la stringa: ");
    gets(str);
    lunghezza=strlen(str);
    for(i=0;i<lunghezza;i++)
    {
        if(isdigit(str[i])!=0)
            cifre++;
        if(isalpha(str[i])!=0)
            carattere++;
        if(isupper(str[i])!=0)
            maiuscolo++;
        if(isspace(str[i])!=0)
            spazio++;
    }
    for(i=0;i<lunghezza;i++)
    {
        if((isalpha(str[i])!=0)&&((str[i+1]==' ')||(str[i+1]=='\0')))
        {
          parola++;
        }
    }
    printf("Il numero di caratteri introdotti e' %d\n",lunghezza);
    printf("Il numero di cifre introdotte e' %d\n",cifre);
    printf("Il numero di caratteri alfabetici introdotti e' %d e quelli maiuscoli sono %d\n",carattere,maiuscolo);
    printf("Il numero di caratteri di spaziatura introdotti e' %d\n",spazio);
    printf("Il numero di parole introdotte e' %d",parola);
    return 0;
}
