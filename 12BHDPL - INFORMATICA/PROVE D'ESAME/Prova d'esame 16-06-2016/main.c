#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#define MAX_MOSSE 64
#define N 8

int Ricerca_configurazione_vincente(char scacchiera[N][N]);
int Ricerca_ultima_mossa_vincente(char scacchiera[N][N]);

int main(int argc, char *argv[])
{
    char scacchiera[N][N];
    char primo,prossimo;
    int ascissa,ordinata;
    int i,j,rossi=0,gialli=0;
    char color;
    FILE *fp;
    if(argc!=3)
    {
        printf("Errore parametri!\n");
        return -1;
    }
    fp=fopen(argv[1],"r");
    if(fp==NULL)
    {
        printf("Errore nell'apertura del file!\n");
        return -1;
    }
    for(i=0; i<N; i++)
    {
        for(j=0; j<N; j++)
        {
            scacchiera[i][j]='a';
        }
    }
    sscanf(argv[2],"%c",&primo);
    if(primo!='G'||primo!='R')
    {
        printf("Errore primo giocatore\n");
        return -1;
    }
    while(feof(fp)==0)
    {
        fscanf(fp,"%c %d %d*c",&color,&ordinata,&ascissa);
        if(ascissa<0||ascissa>7||ordinata<0||ordinata>7)
        {
            printf("Input errato");
            return -1;
        }
        if(scacchiera[ascissa][ordinata]=='a')
        {
            scacchiera[ascissa][ordinata]=color;
            if(color=='R')
                rossi++;
            if(color=='G')
                gialli++;
        }
        else
        {
            printf("Input errato : ripetizione della stessa posizione\n");
            return -1;
        }


    }
    fclose(fp);
    if((rossi==gialli)||((primo=='R')&&(rossi==gialli+1))||((primo=='G')&&(gialli==rossi+1)))
    {
        printf("Input corretto : numero di mosse coerente\n");
        if(rossi==gialli)
            prossimo=primo;
        if((rossi==gialli+1)&&primo=='R')
            prossimo='G';
        if((gialli==rossi+1)&&primo=='G')
            prossimo='R';
    }
    else
    {
        printf("Input errato : violazione regola 3\n");
    }
    if(Ricerca_configurazione_vincente(scacchiera)==1)
        printf("Trovata configurazione vincente");
    if(Ricerca_ultima_mossa_vincente(scacchiera,prossimo)==1)
        printf("Il giocatore %c puo' vincere la partita con una sola ulteriore mosssa!\n",prossimo);
    else
        printf("%c non puo' vincere!\n",prossimo);


    return 0;
}

int Ricerca_configurazione_vincente(char scacchiera[N][N])
{
    int i,j,trovato=0;
    for(i=0; i<N&&trovato==0; i++)
    {
        for(j=0; j<N; j++)
        {
            if(scacchiera[i][j]=='R'&&scacchiera[i][j+1]=='R'&&scacchiera[i][j+2]=='R'&&scacchiera[i][j+3]=='R')
            {
                trovato=1;
            }
            if(scacchiera[i][j]=='G'&&scacchiera[i][j+1]=='G'&&scacchiera[i][j+2]=='G'&&scacchiera[i][j+3]=='G')
            {
                trovato=1;
            }
        }
    }
    return trovato;
}
int Ricerca_ultima_mossa_vincente(char scacchiera[N][N],char prossimo)
{
    int i,j,k,l,trovato=0;
    for(i=0; i<N&&trovato==0; i++)
    {
        for(j=0; j<N&&trovato==0; j++)
        {
            for(k=0; k<4&&trovato==0; k++)
            {
                trovato=1;
                if(scacchiera[i][j+k]=='a')
                {
                    for(l=j; l<j+4; l++)
                    {
                        if((l != j + k)&&(scacchiera[i][l] != prossimo))
                        {
                            trovato = 0;
                        }
                    }
                }
            }
        }
    }
    return trovato;
}
