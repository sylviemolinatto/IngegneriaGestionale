#include <stdio.h>
#include <stdlib.h>
#include <string.h>
typedef struct
{
    char nome_prod[16];
    int quantita;
}prodotto;

int main(int argc,char *argv[])
{
    prodotto prodotti[100];
    prodotto richieste_sup[100];
    char richiesta[16];
    char prod_non_presenti[100][16];
    int i,j,k=0,l=0,sup=0,n_prod,n_prod_sup,n_prod_non_presenti;
    FILE *fm,*fr;
    if(argc!=3)
    {
        printf("Errore parametri!\n");
        return -1;
    }
    fm=fopen(argv[1],"r");
    if(fm==NULL)
    {
        printf("Errore nell'apertura del file\n");
        return -1;
    }
    for(i=0;(i<100)&&(feof(fm)==0);i++)
    {
        fscanf(fm,"%s %d",prodotti[i].nome_prod,&prodotti[i].quantita);
        n_prod=i;
    }
    fclose(fm);
    fm=fopen(argv[1],"w");
    if(fm==NULL)
    {
        printf("Errore nell'apertura del file!\n");
        return -1;
    }
    fr=fopen("argv[2]","r");
    if(fr==NULL)
    {
        printf("Errore nell'apertura del file!\n");
        return -1;
    }
    while(feof(fr)==0)
    {
        fscanf(fr,"%s",richiesta);
        for(i=0;i<100;i++)
        {
            if(strcmp(richiesta,prodotti[i].nome_prod)==0)
            {
                if(prodotti[i].quantita>0)
                {
                    prodotti[i].quantita--;
                    fflush(fm);
                    for(i=0;i<n_prod;i++)
                    {
                        fprintf(fm,"%s %d",prodotti[i].nome_prod,prodotti[i].quantita);
                    }
                }
                else
                {
                   strcpy(richiesta,richieste_sup[l].nome_prod);
                   richieste_sup[l].quantita=sup++;
                   l++;
                   n_prod_sup=l;
                }
            }
            else
            {
                for(j=0;j<100;j++)
                {
                    if(strcmp(richiesta,prod_non_presenti[j])!=0)
                    {
                        strcpy(richiesta,prod_non_presenti[k]);
                        k++;
                        n_prod_non_presenti=k;
                    }
                }

            }
        }
    }
    fclose(fm);
    fclose(fr);
    for(i=0;i<n_prod_sup;i++)
    {
        printf("La quantita eccedente di prodotti di tipo %s richiesta e' %d",richieste_sup[i].nome_prod,richieste_sup[i].quantita);
    }
    printf("I seguenti prodotti non sono presenti in magazzino: \n");
    for(i=0;i<n_prod_non_presenti;i++)
    {
        printf("%s",prod_non_presenti[i]);
    }
    return 0;
}
