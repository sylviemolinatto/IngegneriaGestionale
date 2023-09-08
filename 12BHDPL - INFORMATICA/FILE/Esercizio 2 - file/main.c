#include <stdio.h>
#include <stdlib.h>
#define FILENAME "pitagora.txt"
int main()
{
    FILE * fp;
    int i, j;
    fp = fopen(FILENAME, "w");
    if(fp == NULL)
    {
        printf("Errore nell'apertura del file \n");
        return -1;
    }
    for(i = 1; i <= 10; i++)
    {
        for(j = 1; j <= 10; j++)
        {
            fprintf(fp, "%3d ", i*j);
        }
        fprintf(fp, "\n");
    }
    fclose(fp);
    return 0;
}
