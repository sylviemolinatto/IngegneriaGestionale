#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#define LUNG_NOMI 20
#define MAX_DATI 25
#define NOME_FILE "pedaggi.txt"
#define SOGLIA_TRATTE 3

typedef struct
        {
        char casello1[LUNG_NOMI+1];
        char casello2[LUNG_NOMI+1];
        float distanza;
        } tipo_dati;

typedef enum {FALSE, TRUE} boolean;

int main(int argc, char *argv[])
{
tipo_dati tratte[MAX_DATI];
char entrata[LUNG_NOMI+1], uscita[LUNG_NOMI+1];
int num_tratte, i, max_tratte;
FILE *fp;
boolean trovato1, trovato2;
float distanza;

if(argc != 3)
    {
    printf("Errore numero parametri\n");
    exit(EXIT_FAILURE);
    }
if((fp = fopen(NOME_FILE, "r")) == NULL)
    {
    printf("Errore: il file %s non e' presente\n", NOME_FILE);
    exit(EXIT_FAILURE);
    }
i = 0;
while(fscanf(fp, "%s%s%f", tratte[i].casello1, tratte[i].casello2, &tratte[i].distanza) != EOF)
    i++;
max_tratte = i;
fclose(fp);
strcpy(entrata, argv[1]);
strcpy(uscita, argv[2]);
if(strcmp(entrata, uscita) == 0)
    {
    printf("Errore: il casello d'entrata non puo' coincidere col casello d'uscita\n");
    exit(EXIT_FAILURE);
    }
/* verifico la presenza dell'entrata e dell'uscita nella base dati */
trovato1 = trovato2 = FALSE;
for(i = 0; (i < max_tratte) && (!trovato1 || !trovato2); i++)
    {
    if(strcmp(tratte[i].casello1, entrata) == 0)
        {
        trovato1 = TRUE;
        }
    if(strcmp(tratte[i].casello2, uscita) == 0)
        trovato2 = TRUE;
    }
if(trovato1 && trovato2)
    {
/* In questa soluzione si tiene conto del fatto che la base dati e' corretta e completa,
    e si e' verificata la correttezza dell'ingresso e dell'uscita impostate */
    distanza = 0.0;
    num_tratte = 0;
    trovato1 = FALSE;
    i = 0;
    while(num_tratte < SOGLIA_TRATTE && !trovato1)
        {
        if(strcmp(tratte[i].casello1, entrata) == 0)

            {
            num_tratte++;
            distanza = distanza + tratte[i].distanza;
            if(strcmp(tratte[i].casello2, uscita) == 0)
                {
                trovato1 = TRUE;
                }
            else
                {
                strcpy(entrata, tratte[i].casello2);
                i = 0;  /* riinizializza la scansione */
                }
            }
        else
            i++;
        }
    if(trovato1)
        printf("Destinazione raggiunta in %d tratte. Distanza complessiva: %.2f km\n", num_tratte, distanza);
    else
        printf("Impossibile raggiungere la destinazione percorrendo un massimo di %d tratte\n", SOGLIA_TRATTE);
    }
else
    {
    if(!trovato1)
        printf("Errore: l'entrata non e' presente nella base dati\n");
    if(!trovato2)
        printf("Errore: l'uscita non e' presente nella base dati\n");
    }
return 0;
}

