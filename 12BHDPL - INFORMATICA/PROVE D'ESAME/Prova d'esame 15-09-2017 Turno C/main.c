#include <stdio.h>
#include <stdlib.h>

typedef enum {TRUE,FALSE} boolean;

int main(int argc,char *argv[])
{
    FILE *fin,*fout;
    int i1,i2,numero,conta,numero_letto,i;
    boolean primo;


    if(argc!=4)
    {
        printf("Errore parametri\n");
        return -1;
    }
    sscanf(argv[2],"%d",&i1);
    sscanf(argv[3],"%d",&i2);
    fin=fopen(argv[1],"r");
    if(fin==NULL)
    {
        printf("Errore nell'apertura del file\n");
    }
    fout=fopen("numeri_filtrati.txt","w");
    conta=0;

    while(fscanf(fin,"%d",&numero_letto)!=EOF)
    {
        if((numero_letto>=i1)&&(numero_letto<=i2))
        {
            numero=numero_letto;
            primo=TRUE;
            while(numero>0&&primo==TRUE)
            {
                if(numero==1)
                    primo=FALSE;
                else
                {
                    for(i=2; (i<numero/2)&&(primo==TRUE); i++)
                    {
                        if(numero%i==0)
                            primo=FALSE;
                    }
                }
                if(primo==TRUE)
                    numero=numero/10;
            }
            if(primo==TRUE)
            {
                conta++;
                fprintf(fout,"%d\n",numero_letto);
            }
        }
    }
    fclose(fin);
    fclose(fout);
    if(conta==0)
    {
        printf("Nessun numero trovato\n");
    }
    else
    {
        printf("Ci sono %d numeri troncabili a destra compresi tra %d e %d",conta,i1,i2);
    }
    return 0;
}
