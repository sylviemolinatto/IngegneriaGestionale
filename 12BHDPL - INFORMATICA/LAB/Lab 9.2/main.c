#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#define N 6
#define M 30
#define UNUSED_LINE -2
void stampa_opzioni();
int insert_product(char warehouse[][M], float price[], int n, char new_product[],float price_new_product);
void print_all(char warehouse[][M], float price[], int n, float *avg, float *max);
int update_product(char warehouse[][M], float price[], int n, char product[], int new_price);
int remove_product(char warehouse[][M], float price[], int n, char old_product[]);
int main()
{
    int i,opzione=0,risultato;
    char warehouse[N][M];
    char nuovo_prodotto[M];
    char prodotto[N];
    int new_price;
    float price[N];
    float prezzo_prodotto;
    float avg,max;
    for(i=0;i<N;i++)
        price[i]=UNUSED_LINE;
    do
    {
        stampa_opzioni();
        printf("Inserisci un comando: \n");
        scanf("%d",&opzione);
        switch(opzione)
        {
            case 1:
                printf("Inserisci il nome del prodotto nuovo: \n");
                scanf("%s",nuovo_prodotto);
                printf("Inserisci il suo prezzo: \n");
                scanf("%f",&prezzo_prodotto);
                risultato=insert_product(warehouse,price,N,nuovo_prodotto,prezzo_prodotto);
                if(risultato==1)
                    printf("L'inserimento nel listino e' avvenuto con successo!\n");
                if(risultato==0)
                    printf("Il prodotto e' gia' presente nel listino\n");
                if(risultato==2)
                    printf("Il listino e' pieno\n");
            break;

            case 2:
                print_all(warehouse,price,N,&avg,&max);

                printf("\n\nPrezzo medio:%.2f\n",avg);
                printf("Prezzo massimo:%.2f\n",max);
                break;

            break;

            case 3:
                printf("Arrivederci!\n");
                break;
            case 4:
                printf("Prodotto da aggiornare: \n");
                scanf("%s",prodotto);
                printf("Nuovo prezzo: \n");
                scanf("%d",&new_price);
                risultato=update_product( warehouse,price,N,prodotto,new_price);
                if(risultato==0)
                    printf("Il prodotto non esiste nel listino!\n");
                if(risultato==1)
                    printf("L'aggiornamento e' avvenuto con successo\n");
            break;
            case 5:
                printf("Prodotto da rimuovere: \n");
                scanf("%s",prodotto);
                risultato= remove_product(warehouse,price,N,prodotto);
                if(risultato==1)
                    printf("La rimozione e' avvenuta correttamente\n");
                if(risultato==0)
                    printf("Il prodotto non esiste\n");


            default:
                printf("Comando non disponibile.\n");


        }
    }while(opzione!=3);


    return 0;

}
void stampa_opzioni()
{
    printf("1)Inserimento di un nuovo prodotto e relativo prezzo\n");
    printf("2)Stampa listino attale\n");
    printf("3)Uscita dal programma\n");
    printf("4)Aggiornamento prezzo prodotto\n");
    printf("5)Rimozione prodotto\n");
    return;
}
int insert_product(char warehouse[][M], float price[], int n, char new_product[],float price_new_product)
{
    int i=0,trovato=0,pieno=1,valore;

    for(i=0;i<n&&trovato==0;i++)
    {
        if((price[i]!=-2)&&(strcmp(new_product,warehouse[i])==0))
            trovato=1;
    }
        if(trovato==0)
        {
            for(i=0;i<n&&pieno==1;i++)
            {
              if(price[i]==-2)
                    pieno=0;
            }
            if(pieno==0)
            {
                strcpy(warehouse[i-1],new_product);
                price[i-1]=price_new_product;
                valore=1;
            }
            else
                valore=2;
        }
        else
            valore=0;

return valore;
}

void print_all(char warehouse[][M], float price[], int n, float *avg, float *max)
{
    int i,defined_prices=0;
    float somma=0;
    for(i=0;i<n;i++)
    {
        if(price[i]!=UNUSED_LINE)
        {
            printf("%20s : %8.2f (euro)\n",warehouse[i],price[i]);
            somma=somma+price[i];
            defined_prices++;
        }

    }
    *max=price[0];
    for(i=1;i<n;i++)
    {
        if (price[i]>*max)
            *max=price[i];
    }
    if(defined_prices>0)
    {
        *avg=somma/defined_prices;
    }
    else
        *avg=-1;
}
int update_product(char warehouse[][M], float price[], int n, char product[], int new_price)
{

    int i;
    int returnvalue=0;

    for (i=0;i<n && returnvalue==0;i++)
    {
        if (UNUSED_LINE!=price[i] && strcmp(warehouse[i],product)==0)
        {
            price[i] = new_price;
            returnvalue=1;
        }
    }

    return returnvalue;

}
int remove_product(char warehouse[][M], float price[], int n, char old_product[])
{
   int i;
    int returnvalue=0;

    for (i=0;i<n && returnvalue==0;i++)
    {
        if (price[i]!=UNUSED_LINE && strcmp(warehouse[i],old_product)==0)
        {
            price[i] = UNUSED_LINE;
            returnvalue=1;
        }
    }

    return returnvalue;

}
