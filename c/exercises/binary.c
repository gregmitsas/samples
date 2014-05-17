#include <stdio.h>
#include <conio.h>

void toBinary(int);
void pauseProgram();

int main()
{
    toBinary(1743);
    printf("\t/* reversed */");
    pauseProgram();
    return 0;
}

void toBinary(int input)
{
     while(input>0)
     {
                   if((input%2) == 0)
                   {
                              printf("%d", 0);
                   }
                   else
                   {
                       printf("%d", 1);
                   }
                   input/=2;
    }
}

void pauseProgram()
{
     printf("\n\nPress any key to exit...");
     getch();
}
