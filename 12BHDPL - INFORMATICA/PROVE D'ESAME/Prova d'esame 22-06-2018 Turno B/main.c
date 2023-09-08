#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#define N 4
#define M 2

typedef enum {TRUE,FALSE} boolean;

int main(int argc, char *argv[])
{
    FILE *fp;
    int altezze[N*M];
    int i,altezza,n_altezze,k,escursione,intervallo,n_int;
    float int_m;
    boolean primo;
    if(argc!=3)
    {
        printf("Errore parametri\n");
        return -1;
    }
    fp=fopen(argv[1],"r");
    if(fp==NULL)
    {
        printf("Errore nell'apertura del file\n");
        return -2;
    }
    primo=TRUE;
    i=0;
    while(fscanf(fp,"%d",&altezza)!=EOF)
    {
        if(primo==TRUE)
        {
            primo=FALSE;
            altezze[0]=altezza;
        }
        else
        {
            if(altezza!=altezze[i])
            {
                i++;
                altezze[i]=altezza;
            }
        }
    }
    n_altezze=i+1;
    fclose(fp);
    sscanf(argv[2],"%d",&k);
    escursione=altezze[n_altezze-1]-altezze[0];
    intervallo=ceil((double)escursione/k);
    int_m =altezze[0]+intervallo;
    primo = TRUE;
    n_int = 1;
    i = 0;
    while (i<n_altezze)
    {
        if (primo==TRUE)
        {
            primo = FALSE;
            printf("\n%d Intervallo: ", n_int);
        }
        if (altezze[i]<=int_m)
        {
            printf("%d ", altezze[i]);
            i++;
        }
        else
        {
            int_m = int_m+intervallo;
            primo = TRUE;
            n_int++;
        }
    }
    printf("\n");
    return 0;
}
