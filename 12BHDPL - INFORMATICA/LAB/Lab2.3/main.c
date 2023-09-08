#include <stdio.h>
#include <stdlib.h>

int main()
{
   float lenght;
   float width;
   float perimeter;
   scanf("%f",&lenght);
   scanf("%f",&width);
   perimeter=2*(lenght+width);
   printf("%f e' il perimentro del rettangolo\n",perimeter);
return 0;
}
