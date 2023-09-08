#include <stdio.h>
#include <stdlib.h>


int main()
{ int lato,riga,colonna;
  printf("Inserisci il numero del lato: \n");
  scanf("%d",&lato);
  if(lato<0)
    printf("errore, il lato deve essere maggiore di 0\n");

  else
  {riga=0;

    while(riga<lato)
    {colonna=0;

       while(colonna<lato)
       {
          printf("*");
          colonna=colonna+1;
       }

     printf("\n");
     riga=riga+1;
    }

  }
    return 0;
}
