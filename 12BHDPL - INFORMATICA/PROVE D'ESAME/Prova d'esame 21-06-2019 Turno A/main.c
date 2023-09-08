#include <stdio.h>
#include <stdlib.h>

#define N 4
#define M 4

typedef enum {TRUE,FALSE} boolean;

int main(int argc,char *argv[])
{
    int matr_clip_nota[M][N], vett[N], num_righe_nota;
    boolean trovato;
    int riga, colonna, num_match, max_match, max_max_match, riga_file, colonna_file,riga_max_match;
    int dato, indice;
    char sep;
    FILE *fp;

    if(argc != 3)
    {
        printf("Errore numero parametri\n");
        exit(EXIT_FAILURE);
    }
    /* leggo il file clip nota */
    if((fp = fopen(argv[1], "r")) == NULL)
    {
        printf("Errore: il file %s non e' presente\n", argv[2]);
        exit(EXIT_FAILURE);
    }

    for(riga=0;riga<N;riga++)
        for(colonna=0;colonna<M;colonna++)
            fscanf(fp,"%d, ",&matr_clip_nota[riga][colonna]);
    num_righe_nota = riga;
    fclose(fp);
    /* leggo il file clip incognita */
    if((fp = fopen(argv[2], "r")) == NULL)
    {
        printf("Errore: il file %s non e' presente\n", argv[1]);
        exit(EXIT_FAILURE);
    }
    riga_file = 0;
    colonna_file = 0;
    max_max_match = 0;
    while(fscanf(fp, "%d%c", &dato, &sep) != EOF)
    {
        vett[colonna_file] = dato;
        colonna_file++;
        if(colonna_file == N)
        {
            riga_file++;
            max_match = 0;
            for(riga = 0; riga < num_righe_nota; riga++)
            {
                num_match = 0;
                for(colonna = 0; colonna < N; colonna++)
                {
                    trovato = FALSE;
                    for(indice = 0; indice < N && !trovato; indice++)
                        trovato = (vett[colonna] == matr_clip_nota[riga][indice]);
                    if(trovato)
                        num_match++;
                }
                if(num_match > max_match)
                    max_match = num_match;
            }
            if(max_match > max_max_match)
            {
                max_max_match = max_match;
                riga_max_match = riga_file;
            }
            colonna_file = 0;
        }
    }
    if(max_max_match > 3)
        printf("Clip potenzialmente presente nel brano alla riga %d (corrispondenza perfetta)\n", riga_max_match);
    else if(max_max_match == 3)
        printf("Clip potenzialmente presente nel brano alla riga %d (corrispondenza non perfetta)\n", riga_max_match);
    else
        printf("Clip non presente nel brano\n");
    return 0;
}
