#include <stdio.h>
#include <stdlib.h>
#define N 7
#define M 9
typedef struct
{
    int riga;
    int lunghezza;
}riga;
typedef struct
{
    int colonna;
    int lunghezza;
}colonna;

int main(int argc,char *argv[])
{
    riga ostacoli_righe[N];
    colonna ostacoli_colonne[M];
    riga max_righe;
    colonna max_colonne;
    int i,j,conta=0;
    int mappa[N][M];
    FILE *fp;
    if (argc!=2)
    {
        printf("Errore parametri\n");
        return -1;
    }
    for(i=0;i<N;i++)
    {
        ostacoli_righe[i].riga=0;
        ostacoli_righe[i].lunghezza=0;
    }
    for(i=0;i<M;i++)
    {
        ostacoli_colonne[i].colonna=0;
        ostacoli_colonne[i].lunghezza=0;
    }
    fp=fopen(argv[1],"r");
    if(fp==NULL)
    {
        printf("Errore nell'apertura del file\n");
        return -1;
    }
    for(i=0;i<N;i++)
    {
        for(j=0;j<M;j++)
        {
            fscanf(fp,"%d",&mappa[i][j]);
        }
    }
    fclose(fp);
    for(i=0;i<N;i++)
    {
        for(j=0;j<M;j++)
        {
            if(mappa[i][j]==1)
            {
                conta++;
                ostacoli_righe[i].riga=i;
                if(conta>ostacoli_righe[i].lunghezza)
                {
                    ostacoli_righe[i].lunghezza=conta;
                }
            }
            if(mappa[i][j]==0)
            {
                conta=0;
            }
        }
    }
    max_righe.lunghezza=ostacoli_righe[0].lunghezza;
    for(i=1;i<N;i++)
    {
        if(ostacoli_righe[i].lunghezza>max_righe.lunghezza)
            {
                max_righe.lunghezza=ostacoli_righe[i].lunghezza;
                max_righe.riga=i;
            }

    }


    printf("ostacolo massimo di dimensione %d posizionato sulla riga %d\n",max_righe.lunghezza,max_righe.riga);
     for(j=0;j<M;j++)
    {
        for(i=0;i<N;i++)
        {
            if(mappa[i][j]==1)
            {
                conta++;
                ostacoli_colonne[j].colonna=j;
                if(conta>ostacoli_colonne[j].lunghezza)
                {
                    ostacoli_colonne[j].lunghezza=conta;
                }
            }
            if(mappa[i][j]==0)
            {
                conta=0;
            }
        }
    }
    max_colonne.lunghezza=ostacoli_colonne[0].lunghezza;
    for(j=1;i<M;j++)
    {
        if(ostacoli_colonne[j].lunghezza>max_colonne.lunghezza)
            {
                max_colonne.lunghezza=ostacoli_colonne[j].lunghezza;
                max_colonne.colonna=j;
            }

    }


    printf("ostacolo massimo di dimensione %d posizionato sulla colonna %d\n",max_colonne.lunghezza,max_colonne.colonna);

    return 0;
}
