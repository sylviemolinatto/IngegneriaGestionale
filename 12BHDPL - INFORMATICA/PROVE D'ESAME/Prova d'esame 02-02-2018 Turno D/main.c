#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define MAX_GIOC 20

int main(int argc,char *argv[])
{
    FILE *fp;
    char id[10+1];
    int num_gioc,estratti[6],tot_gioc[4];
    int i,j,gioc,vincitore,numero;

    if(argc!=8)
    {
        printf("ERRORE\n");
        return -1;
    }
    j=0;
    for(i=2; i<8; i++)
    {
        sscanf(argv[i],"%d",&estratti[j]);
        j++;
    }
    for(i=0; i<4; i++)
        tot_gioc[i]=0;
    fp=fopen(argv[1],"r");
    if(fp==NULL)
    {
        printf("ERRORE FILE\n");
        return -1;
    }

    vincitore=0;
    while(fscanf(fp,"%s %d",id,&num_gioc)!=EOF)
    {
        gioc=0;
        for(i=0; i<num_gioc; i++)
        {
            fscanf(fp,"%d",&numero);
            for(j=0; j<6; j++)
            {
                if(numero==estratti[j])
                    gioc++;
            }
        }

        if(gioc>=3)
        {
            printf("%s %d numeri indovinati\n",id,gioc);
            vincitore++;
            tot_gioc[gioc-3]++;
        }
    }
    fclose(fp);
    printf("Totale:\n");
    for(i=0; i<4; i++)
        printf("%d: %d vincitori\n",i+3,tot_gioc[i]);
    printf("Totale vincitori: %d\n",vincitore);
    return 0;
}
