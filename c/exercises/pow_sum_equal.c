#include <stdio.h>
#include <stdbool.h>
#include <conio.h>

int getPower(int);
bool powSumIsEqual(int);
void pauseProgram();

int main()
{
    
    int i;
    int counter = 0;
    for(i=0; i<100; i++)
    {
             if(powSumIsEqual(i))
             {
                                 printf("%d\n", i);
                                 counter++;
             }
    }
    printf("total occurrences: %d", counter);
    
    pauseProgram();
    return 0;
}

int getPower(int base)
{
    return base*base;
}

bool powSumIsEqual(int input)
{
     if(input<10)
     {
                 if(getPower(input) >= 10)
                 {
                                    if(((getPower(input)/10)%10) + (getPower(input)%10) == input)
                                    {
                                                                 return true;
                                    }
                 }
     }
     else if(input>=10)
     {
          if(getPower(input) <= 999)
          {
                             if(((getPower(input)/100)%10) + (getPower(input)%100) == input)
                             {
                                                       return true;
                             }
          }
          else if(getPower(input) > 999)
          {
               if(((getPower(input)/100)%100) + (getPower(input)%100) == input)
               {
                                          return true;
               }
          }
     }
     return false;
}

void pauseProgram()
{
     printf("\n\nPress any key to exit...");
     getch();
}
