#include <stdio.h>
#include <time.h>
#include <conio.h>

#define MAX 20

int sumArrays(int*, int*);
void pauseProgram();

int main()
{
    int firstArray[MAX];
    int secondArray[MAX];
    
    srand(time(NULL));
    
    int i;
    for(i=0; i<MAX; i++)
    {
             firstArray[i] = (rand()%100) + (i+1);
             secondArray[i] = (rand()%100) + (i+2);
    }
    
    int sum = sumArrays(firstArray, secondArray);
    
    printf("sum = %d\n", sum);
    
    pauseProgram();
    return 0;
}

int sumArrays(int *array1, int *array2)
{
     int sum = 0;
     
     int i;
     for(i=0; i<MAX; i++)
     {
              sum += (*array1++) + (*array2++);
     }
     
     return sum;
}

void pauseProgram()
{
     printf("\n\nPress any key to exit..");
     getch();
}
