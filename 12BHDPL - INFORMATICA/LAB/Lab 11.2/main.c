#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#define MAX 5
typedef struct
{
    char nome[20];
    char cognome[20];
    char fisso[20];
    char mobile[20];
} Nomi;

int main()
{
    int i,j=0,choice,trovato=0;
    Nomi nuovo_nome;
    Nomi rubrica[MAX];
    char risposta[3];
    printf("1)Inserisci un nuovo nominativo\n");
    scanf("%d",&choice);
    do
    {
        switch(choice)
        {
        case 1:
            printf("Nome: ");
            scanf("%s",nuovo_nome.nome);
            printf("\nCognome: ");
            scanf("%s",nuovo_nome.cognome);
            for(i=0; (i<MAX)&&(trovato==0); i++)
            {
                if((strcmp(nuovo_nome.nome,rubrica[i].nome)==0)&&(strcmp(nuovo_nome.cognome,rubrica[i].cognome)==0))
                {
                    trovato=1;
                }
            }
            if(trovato==1)
            {
                printf("Il nominativo e' gia' presente, desidera comunque inserirlo?\n");
                scanf("%s",risposta);
                if(strcmp(risposta,"si")==0)
                {
                    printf("Numero di telefono fisso = ");
                    scanf("%s",nuovo_nome.fisso);
                    printf("\nNumero di telefono mobile = ");
                    scanf("%s",nuovo_nome.mobile);
                    rubrica[j]=nuovo_nome;
                    j++;
                    printf("Inserimento avvenuto con successo!\n");

                }
            }
            else
            {
                printf("Numero di telefono fisso = ");
                scanf("%s",nuovo_nome.fisso);
                printf("\nNumero di telefono mobile = ");
                scanf("%s",nuovo_nome.mobile);
                rubrica[j]=nuovo_nome;
                j++;
                printf("Inserimento avvenuto con successo!\n");

            }

            break;

        default:
            printf("Hai inserito una scelta non valida\n");
            break;
        }
    }while(j<MAX);

    for(i=0;i<MAX;i++)
        printf("%s %s\n%s\n%s\n",rubrica[i].nome,rubrica[i].cognome,rubrica[i].fisso,rubrica[i].mobile);

        return 0;
    }
