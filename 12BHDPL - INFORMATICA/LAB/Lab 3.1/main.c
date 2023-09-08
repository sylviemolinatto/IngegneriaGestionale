#include <stdio.h>
#include <stdlib.h>

int main()
{
    int int_1,int_2;
    float float_1,float_2;
    scanf("%d%d%f%f",&int_1,&int_2,&float_1,&float_2);
    printf("variable       value\n");
    printf("int_1 int_2    %d  %d\n",int_1,int_2);
    printf("float_1        %.2f\n",float_1);
    printf("float_2        %.3f\n",float_2);
    return 0;
}
