#include <stdio.h>
#include <math.h>
#include <conio.h>

void convert_to_polar(double, double, double*, double*);
void pauseProgram();

int main()
{
    double x = 2.3;
    double y = 3.1;
    double r = 0;
    double theta = 0;
    
    convert_to_polar(x, y, &r, &theta);
    
    printf("r = %g\n", r);
    printf("theta = %g\n", theta);
    
    pauseProgram();
    return 0;
}

void convert_to_polar(double x, double y, double *r, double *theta)
{
     *r = sqrt(pow(x,2) + pow(y,2));
     if(*r != 0)
     {
           *theta = atan(y / x);
           // or *theta = asin(y / *r);
           // or *theta = acos(x / *r);
     }
}

void pauseProgram()
{
     printf("\n\nPress any key to exit..");
     getch();
}
