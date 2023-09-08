#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>

int main()
{
    int trovato,i,stop=0;
    char prodotti[100][30];
    int quantita[100];
    char prodotto[30];
    int qta,num_prod=0;
    char EU;
    do
    {
        scanf("%s",prodotto);
        if(strcmp(prodotto,"FINE")==0)
            stop=1;
        else
            scanf("%c%d",&EU,&qta);
            if(EU=='E')
            {
                trovato=0;
                for(i=0; (i<num_prod)&&(trovato==0); i++)
                {
                    if(strcmp(prodotto,prodotti[i])==0)
                        trovato=1;
                }
                if(trovato==1)
                    quantita[i-1]=quantita[i-1]+qta;
                else
                {
                    strcpy(prodotti[num_prod],prodotto);
                    quantita[num_prod]=qta;
                    num_prod++;
                }
            }
            else if(EU='U')
            {
                trovato=0;
                for(i=0; (i<num_prod)&&(trovato==0); i++)
                {
                    if(strcmp(prodotto,prodotti[i])==0)
                        trovato=1;
                }
                if(trovato==1)
                {
                    if(quantita[i-1] - qta >= 0)

                        quantita[i-1] = quantita[i-1] - qta;
                    else
                            printf("Non ci sono abbastanza prodotti \n");
                }
                else
                    printf("Prodotto non in magazzino \n");
            }
            else
                printf("Comando scorretto \n");
        }while(stop==0);
    return 0;
}
