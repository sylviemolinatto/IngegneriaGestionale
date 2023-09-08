#include <stdio.h>
#include <stdlib.h>

int main()
{
 signed int A;
 signed int B;
  int D;
  scanf("%d",&A);
  scanf("%d",&B);
  if (A>=B)
  {
     D=A-B;
     printf("%d e' il risultato della differenza in valore assoluto di A e B\n",D);
  }
  else
  {
      D=B-A;
      printf("%d e' il risultato della differenza in valore assoluto di A e B\n",D);
  }
    return 0;
}
