#include <stdio.h>
#include <stdlib.h>

#define N 10

int main()
{
    FILE *fp;
    int riga_rif,colon_rif,altezza,corona;
    int mappa[N][N],riga,colonna,somma;
    float media;

    for(riga=0; riga<N; riga++)
    {
        for(colonna=0; colonna<N; colonna++)
        {
            mappa[riga][colonna]=0;
        }
    }
    fp=fopen("linden.txt","r");
    while(fscanf(fp,"%d %d %d",&riga_rif,&colon_rif,&altezza)!=EOF)
    {
        riga_rif--;
        colon_rif--;
        mappa[riga_rif][colon_rif]=altezza;
        altezza--;
        corona=1;
        while((altezza > 0) && (corona < N))
        {
            /* segmento in alto */
            for(colonna = colon_rif-corona; colonna <= colon_rif+corona; colonna++)
                if(((riga_rif-corona) >= 0) && (colonna >= 0) && (colonna < N) && (mappa[riga_rif-corona][colonna] < altezza))
                    mappa[riga_rif-corona][colonna] = altezza;
            /* segmento a destra */
            for(riga = riga_rif-corona; riga <= riga_rif+corona; riga++)
                if(((colon_rif+corona) < N) && (riga >= 0) && (riga < N) && (mappa[riga][colon_rif+corona] < altezza))
                    mappa[riga][colon_rif+corona] = altezza;
            /* segmento in basso */
            for(colonna = colon_rif-corona; colonna <= colon_rif+corona; colonna++)
                if(((riga_rif+corona) < N) && (colonna >= 0) && (colonna < N) && (mappa[riga_rif+corona][colonna] < altezza))
                    mappa[riga_rif+corona][colonna] = altezza;
            /* segmento a sinistra */
            for(riga = riga_rif-corona; riga <= riga_rif+corona; riga++)
                if(((colon_rif-corona) >= 0) && (riga >= 0) && (riga < N) && (mappa[riga][colon_rif-corona] < altezza))
                    mappa[riga][colon_rif-corona] = altezza;
            altezza--;
            corona++;
        }
    }
    for(riga = 0; riga < N; riga++)
    {
    for(colonna = 0; colonna < N; colonna++)
        printf("%2d", mappa[riga][colonna]);
    printf("\n");
    }
    somma=0;
    for(riga=0;riga<N;riga++)
    {
        for(colonna=0;colonna<N;colonna++)
        {
            somma=somma+mappa[riga][colonna];
        }
    }
    media=(float)somma/(N*N);
    printf("L'altezza media della zona e' : %.2f metri",media);
    return 0;
}
