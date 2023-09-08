#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>
#include <string.h>
#define RIGHE 1000
#define CARATTERI 100


int main()
{
    char testo[RIGHE][CARATTERI+1];
    char riga[CARATTERI*3+1];
    int fine_testo, num_righe, num_caratteri, num_alfa, num_parole;
    int i, j;
    fine_testo = 0;
    num_righe = 0;


    printf("Inserisci il testo: \n");
    do
    {
        gets(riga);
        if(strlen(riga) > CARATTERI)
        {
            printf("La stringa e' troppo lunga");
        }
        else if(strcmp(riga, "FINE") == 0)
        {
            fine_testo = 1;
        }
        else
        {
            strcpy(testo[num_righe], riga);
            num_righe++;
        }
    }
    while((num_righe<RIGHE)&&(fine_testo == 0));
    printf("L'utente ha inserito %d righe \n", num_righe);

    num_caratteri = 0;
    for(i = 0; i < num_righe; i++)
        num_caratteri = num_caratteri + strlen(testo[i]);
    printf("L'utente ha inserito %d caratteri \n", num_caratteri);


    num_alfa = 0;
    for(i = 0; i < num_righe; i++)
        for(j = 0; j < strlen(testo[i]); j++)
            if(isalnum(testo[i][j]))
                num_alfa++;
    printf("L'utente ha inserito %d caratteri alfanumerici \n",
           num_alfa);

    num_parole = 0;
    for(i = 0; i < num_righe; i++)
        for(j = 0; j < strlen(testo[i]); j++)
        {
            if(isalpha(testo[i][j]) &&
                    (j == 0 || !isalpha(testo[i][j-1])))
                num_parole++;
        }
    printf("L'utente ha inserito %d parole \n", num_parole);
    return 0;
}
