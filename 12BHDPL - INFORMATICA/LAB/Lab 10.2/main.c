#include <stdio.h>
#include <stdlib.h>

int main(int argc,char *argv[])
{
    int val1,val2;
    if (argc!=4)
        {
            printf("Errore!\n");
            return -1;
        }

    val1=atoi(argv[1]);
    val2=atoi(argv[2]);
    printf("val1 = %d\n",val1);
    printf("val2 = %d\n",val2);
    switch(argv[3][0])
    {
    case 'a':

        printf("Somma = %d",val1+val2);
        break;
    case 'b':
        printf("Differenza = %d\n",val1-val2);
        break;
    case 'c':
        printf("Moltiplicazione = %d\n",val1*val2);
        break;
    case 'd':
        if(val2!=0)
            printf("Divisione = %f",(float)val1/val2);
        else
            printf("Errore: divisione per 0!\n");
        break;
    default:
        printf("Errore comandi!\n");
    }
    return 0;
}
