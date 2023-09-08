#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#define M 30

int main()
{
    char tmp[M];
    char prodotto1[M], prodotto2[M], prodotto3[M], prodotto[M];
    int prezzo1, prezzo2, prezzo3, quantita;
    int askAgain;

    printf("inserisci il prodotto 1 e il suo prezzo: \n");
    if (gets(tmp)==NULL)
    {
        printf("Errore nella lettura del prodotto 1\n");
        return -1;
    }
    if (sscanf(tmp,"%s%d", prodotto1, &prezzo1)!=2)
    {
        printf("sia il nome che il prezzo del prodotto 1 non sono stati inseriti correttamente\n");
        return 1;
    }

    printf("inserisci il prodotto 2 e il suo prezzo: \n");
    if (gets(tmp)==NULL)
    {
        printf("Errore nella lettura del prodotto 2\n");
        return -2;
    }
    if (sscanf(tmp,"%s%d", prodotto2, &prezzo2)!=2)
    {
        printf("sia il nome che il prezzo del prodotto non sono stati inseriti corretamente\n");
        return 2;
    }

    printf("Inserisci il prodotto 3 e il suo prezzo: \n");
    if (gets(tmp)==NULL)
    {
        printf("Errore nella lettura del prodotto 3\n");
        return -3;
    }
    if (sscanf(tmp,"%s%d", prodotto3, &prezzo3)!=2)
    {
        printf("Sia il nome che il prezzo del prodotto non sono stati inseriti correttamente\n");
        return 3;
    }

    do
    {
        printf("Inserisci un prodotto e la sua quantita da cercare: ");
        if (gets(tmp)==NULL)
        {
            printf("Errore\n");
            return -4;
        }
        if (sscanf(tmp,"%s%d", prodotto, &quantita)!=2)
        {
            printf("Il nome o la quantita del prodotto che si sta cercando non e' stato inserito correttamente\n");
            return 4;
        }

        askAgain = 0;
        if (strcmp(prodotto1, prodotto)==0)
            printf("\nPer %d quantita del prodotto %s devi pagare %d\n", quantita, prodotto, quantita*prezzo1);
        else if (strcmp(prodotto2, prodotto)==0)
            printf("\nPer %d quantita del prodotto %s devi pagare %d\n", quantita, prodotto, quantita*prezzo2);
        else if (strcmp(prodotto3, prodotto)==0)
            printf("\nPer %d quantita del prodotto %s devi pagare %d\\n", quantita, prodotto, quantita*prezzo3);
        else
        {
            printf("\nIl prodotto che stai cercando non e' disponibile\n");
            askAgain = 1;
        }
    }
    while (askAgain == 1);


    return 0;
}
