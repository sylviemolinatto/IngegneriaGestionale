#include <stdio.h>
#include <stdlib.h>

int main()
{
    int riga,colonna,lato;
    printf("Inserisci il numero del lato: \n");
    scanf("%d",&lato);
    if(lato<0)
        printf("errore, il lato deve essere maggiore di 0\n");

    else
    {
        riga=0;

        for(riga=0;riga<lato;riga++)
        {
            for(colonna=0;colonna<lato;colonna++)
            {
                printf("*");

            }
            printf("\n");

        }

        return 0;
    }
}
