#include <stdio.h>
#include <stdlib.h>

int main()
{
  char c;
  int n;
  printf("Inserisci un carattere: ");
  scanf("%c",&c);
  printf ("\nInserisci un numero: ");
  scanf("%d",&n);
  printf("\n\n");
  if (c=='m')
  {
      printf("%d",n=n*3);
  }
  else if (c=='e')
  {
      printf("%d",n=n*n);
      }
  else if (c=='d')
  {
      printf("%d",n=n/4);
  }
  else
  {
     printf("un cazzo\n");
  }
    return 0;
}
