#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define LUNG_STR 8+1
typedef enum {FALSE,TRUE}boolean;


int main(int argc, char *argv[])
{
    char orario1[LUNG_STR], orario2[LUNG_STR];
    float val1, val2;
    boolean finito1, finito2;
    FILE *fp1, *fp2, *fp3;

    if(argc != 3)
    {
        printf("Errore numero parametri\n");
        exit(EXIT_FAILURE);
    }
    if((fp1 = fopen(argv[1], "r")) == NULL)
    {
        printf("Errore apertura file %s\n", argv[1]);
        exit(EXIT_FAILURE);
    }
    if((fp2 = fopen(argv[2], "r")) == NULL)
    {
        printf("Errore apertura file %s\n", argv[2]);
        exit(EXIT_FAILURE);
    }
    if((fp3 = fopen("burton.txt", "w")) == NULL)
    {
        printf("Errore apertura file burton.txt\n");
        exit(EXIT_FAILURE);
    }

    finito1 = FALSE;
    finito2 = FALSE;
    fscanf(fp1,"%s%f", orario1, &val1);
    fscanf(fp2,"%s%f", orario2, &val2);
    while ( (!finito1) && (!finito2) )
    {
        if (strcmp(orario1, orario2) < 0)
        {
            fprintf(fp3, "%s %.2f\n", orario1, val1);
            finito1 = (fscanf(fp1,"%s%f", orario1, &val1) == EOF);

        }
        else
        {
            if (strcmp(orario1, orario2) > 0)
            {
                fprintf(fp3, "%s %.2f\n", orario2, val2);
                finito2 = (fscanf(fp2,"%s%f", orario2, &val2) == EOF);

            }
            else
            {
                fprintf(fp3, "%s %.2f\n", orario1, (val1 + val2)/ 2);

                finito1 = (fscanf(fp1,"%s%f", orario1, &val1) == EOF);

                finito2 = (fscanf(fp2,"%s%f", orario2, &val2) == EOF);

            }
        }
    }
    if (finito1)
    {
        while ( !finito2 )
        {
            fprintf(fp3, "%s %.2f\n", orario2, val2);
            finito2 = (fscanf(fp2,"%s%f", orario2, &val2) == EOF);

        }
    }
    else
    {
        while ( !finito1 )
        {

            fprintf(fp3, "%s %.2f\n", orario1, val1);
            finito1 = (fscanf(fp1,"%s%f", orario1, &val1) == EOF);

        }
    }
    fclose(fp1);
    fclose(fp2);
    fclose(fp3);
    return EXIT_SUCCESS;
}
