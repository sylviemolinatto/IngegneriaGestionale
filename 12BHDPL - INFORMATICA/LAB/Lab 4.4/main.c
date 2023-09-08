#include <stdio.h>
#include <stdlib.h>

int main()
{
    int N,riga,colonna;
    scanf("%d",&N);
    if (N>0&&N<=40)
    {
       for(riga=0;riga<N;riga++)
       {
          for(colonna=0;colonna<=riga;colonna++)
            {
                printf("*");

            }
            printf("\n");
       }

    }

    else
    {
        printf("Error\n");
    }
    return 0;
}
