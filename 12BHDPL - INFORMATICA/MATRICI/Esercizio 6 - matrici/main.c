#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#define VOCI 100
#define CAR_NOME 40
#define CAR_TEL 20
void stampa_opzioni();

int main()
{
    char nomi[VOCI][CAR_NOME+1];
    char numeri[VOCI][CAR_TEL+1];
    char nome[CAR_NOME+1];
    char numero[CAR_TEL+1];
    int opzione,conta=0,trovato,uguale;
    int i,j,z;
    do
    {
        stampa_opzioni();
        printf("Inserisci il comando: ");
        scanf("%d%*c",&opzione);
        switch(opzione)
        {
        case 1:
            printf("Inserisci il nome della nuova voce in rubrica: ");
            gets(nome);
            printf("Inserisci il numero della nuova voce in rubrica: ");
            gets(numero);
            if(conta==VOCI)
                printf("Rubrica piena!\n");
            else
            {
                trovato=0;
                for(i=0; (i<conta)&&(trovato==0); i++)
                {
                    if(strcmp(nomi[i],nome)==0)
                        trovato=1;
                }
                if(trovato==1)
                    printf("Nome duplicato!\n");
                else
                {
                    strcpy(nomi[conta],nome);
                    strcpy(numeri[conta],numero);
                    conta++;
                }
            }
            break;
        case 2:
            printf("Nome da cercare: ");
            gets(nome);
            trovato=0;
            for(i=0;(i<conta)&&(trovato=0);i++)
            {
                if(strcmp(nomi[i],nome))
                    trovato=1;
            }
            if(trovato==1)
                printf("Il numero di %s e': %s \n",nome,numeri[i-1]);
            else
                printf("%s non e' presente in rubrica!\n",nome);
            break;
        case 3:
            printf("Nome da cercare: ");
            gets(nome);
            trovato=0;
            uguale=0;
            for(i=0;(i<conta)&&(trovato==0);i++)
            {
                for(j=0;(j<(strlen(nomi[i])-strlen(nome))&&(uguale==0));j++)

                    uguale=1;
                    for(z=0;z<(strlen(nome))&&(uguale==1);z++)
                    {
                        if(nomi[i][j+z]!=nome[z])
                            uguale=0;
                    }
                    if(uguale==1)
                        trovato=1;
            }
                if(trovato==1)
                {
                    printf("Somiglia a : %s\n",nomi[i-1]);
                    printf("Il telefono di %s e': %s\n",nomi[i-1],numeri[i-1]);
                }
                else
                    printf("%s non e' presente in rubrica!\n",nome);

                break;

        case 4:
            printf("Contenuto della rubrica: \n");
            for(i=0;i<conta;i++)
                printf("%s: %s\n",nomi[i],numeri[i]);
            break;

        case 0:
            printf("Arrivederci!\n");
            break;

        default:
            printf("Comando non disponibile\n");

        }
        printf("\n\n");
    }while(opzione!=0);

    return 0;
}
void stampa_opzioni()
{
    printf("1) Aggiungi nuova voce in rubrica\n");
    printf("2) Ricerca esatta per nome\n");
    printf("3) Ricerca approssimata per nome\n");
    printf("4) Stampa completa rubrica\n");
    printf("5) Esci dal programma\n");

    return;
}
