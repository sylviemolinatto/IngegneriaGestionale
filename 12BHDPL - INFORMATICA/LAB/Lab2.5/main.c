#include <stdio.h>
#include <stdlib.h>

int main()
{
    float price;
    float tax;
    float receipt;
    scanf("%f",&tax);
    scanf("%f",&price);
    receipt=(price*(1+tax))/100;
    printf("%f e' il receipt\n",receipt);
    return 0;
}
