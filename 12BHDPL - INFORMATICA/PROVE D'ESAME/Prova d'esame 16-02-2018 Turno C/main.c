#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>

#define N 11
#define M 4
int cerca_percorso(char mappa[N][N+1],int pos_x,int pos_y);
int main(int argc,char *argv[])
{
    FILE *fp;
    char mappa[N][N+1];
    int n_percorso,nuovo_percorso,conta,lunghezza;
    int pos_x,pos_y,i,j,lunghezza_rif,somma;
    float media;
    if(argc!=2)
    {
        printf("Errore parametri\n");
        return -1;
    }
    n_percorso=atoi(argv[1]);
    fp=fopen("bath.txt","r");
    if(fp==NULL)
    {
        printf("Errore nell'apertura del file\n");
        return -2;
    }

    for(i=0; i<N; i++)
    {
        fscanf(fp,"%s",mappa[i]);
    }
    fclose(fp);
    somma=0;
    conta=0;
    for(i=0; i<N; i++)
    {
        for(j=0; j<N; j++)
        {
            if(isdigit(mappa[i][j]))
            {
                conta++;
                nuovo_percorso=mappa[i][j]-'0';
                lunghezza=cerca_percorso(mappa,i,j);
                somma=somma+lunghezza;
                if(nuovo_percorso==n_percorso)
                {
                    lunghezza_rif=lunghezza;
                }
            }
        }
    }

    media=(float)somma/conta;

    printf("La lunghezza del percorso %d e' di %d unita'\n",n_percorso,lunghezza);
    printf("La lunghezza media dei percorsi e' di %.2f unita'",media);

    return 0;
}

int cerca_percorso(char mappa[N][N+1],int pos_x,int pos_y)
{
    int lung,riga,colonna,trovato;
    lung=1;
    trovato=1;
    while(trovato==1)
    {
        trovato=0;
        for(riga=pos_x - 1; (riga <= pos_x + 1) && trovato==0; riga++)
        {
            for(colonna = pos_y -1; (colonna <= pos_y + 1) && trovato==0; colonna++)
            {
                if((riga >= 0) && (riga < N) && (colonna >= 0) && (colonna < N) &&((riga == pos_x) || (colonna == pos_y)))
                {
                    if(mappa[riga][colonna] == '+')
                    {
                        trovato = 1;
                        lung++;
                        mappa[riga][colonna] = '-';
                        pos_x = riga;
                        pos_y = colonna;
                    }

                }
            }
         }
    }
return lung;
}
