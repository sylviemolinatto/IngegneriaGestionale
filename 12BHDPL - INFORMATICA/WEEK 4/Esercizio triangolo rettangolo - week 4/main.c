#include <stdio.h>
#include <stdlib.h>

int main()
{
   int a,b,c;
   scanf("%d",&a);
   scanf("%d",&b);
   scanf("%d",&c);
   if ((a*a==b*b+c*c)||
       (b*b==a*a+c*c)||
       (c*c==a*a+b*b))
    printf ("si\n");
   else
    printf ("no\n");
    return 0;
}
