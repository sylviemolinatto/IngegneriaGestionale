#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
#define FILE_ORDINI "ordini.txt"
#define FILE_MAGAZZINO "magazzino.txt"

int main()
{
    char prodotto[29];
    char prodotti[100][29];
    char EU;
    int qta,trovato,i,num_prod;
    int quantita[100];
    FILE *fp;
    fp=fopen(FILE_ORDINI,"r");
    if(fp==NULL)
    {
        printf("Errore nell'apertura del file\n");
        return -1;
    }
    while(!feof(fp))
    {
        fscanf(fp,"%s %c %d",prodotto,&EU,&qta);
        if(EU == 'E')
        {
            trovato = 0;
            for(i = 0; (i < num_prod)&&(trovato == 0); i++)
                if(strcmp(prodotti[i], prodotto) == 0)
                    trovato = 1;
            if(trovato == 1)
                quantita[i-1] = quantita[i-1] + qta;
            else
            {
                if(num_prod < 100)
                {
                    strcpy(prodotti[num_prod], prodotto);
                    quantita[num_prod] = qta;
                    num_prod++;
                }
                else
                    printf("Magazzino pieno, non posso aggiungere nuovi prodotti \n");
                }
        }

        else if(EU == 'U')
        {
            trovato = 0;
            for(i = 0; (i < num_prod)&&(trovato == 0); i++)
                if(strcmp(prodotti[i], prodotto) == 0)
                    trovato = 1;
            if(trovato == 1)
            {
                if(quantita[i-1] - qta >= 0)
                    quantita[i-1] = quantita[i-1] - qta;
                else
                    printf("Non ci sono abbastanza prodotti in magazzino \n");
                }
            else
                printf("Prodotto non in magazzino \n");
        }
        else
            printf("Comando scorretto \n");
    }

fclose(fp);
fp = fopen(FILE_MAGAZZINO, "w");
if(fp == NULL)
{
    printf("Errore nell'apertura del file\n");
    return -1;
}

for(i = 0; i < num_prod; i++)
    fprintf(fp,"%s: %d \n", prodotti[i], quantita[i]);
fclose(fp);

return 0;
}
