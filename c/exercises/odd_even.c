#include <stdio.h>
#include <conio.h>

#define MAX 10

void getTotalOddEven(int*, int*, int*);
void assignOddEven(int*, int*, int*);
void printData(int*, int*, int, int);
void pauseProgram();

int main()
{
    srand(time(NULL));
    
    int digits[MAX];
    int i;
    for(i=0; i<MAX; i++)
    {
            digits[i] = (rand()%100) + 1;
    }
    
    int totalOdd = 0;
    int totalEven = 0;
    getTotalOddEven(digits, &totalOdd, &totalEven);
    
    int *odd = (int*)malloc(sizeof(int)*totalOdd);
    int *even = (int*)malloc(sizeof(int)*totalEven);
    
    assignOddEven(digits, odd, even);
    printData(odd, even, totalOdd, totalEven);
    
    pauseProgram();
    return 0;
}

void getTotalOddEven(int *inputArray, int *totalOdd, int *totalEven)
{
     int i;
     for(i=0; i<MAX; i++)
     {
              if((inputArray[i] % 2) == 0)
              {
                                (*totalEven)++;
              }
              else
              {
                  (*totalOdd)++;
              }
     }
}

void assignOddEven(int *init, int *odd, int *even)
{
     int i;
     for(i=0; i<MAX; i++)
     {
             if((init[i] % 2) == 0)
             {
                     *(even++) = init[i];
             }
             else
             {
                 *(odd++) = init[i];
             }
     }
}

void printData(int *odd, int *even, int oddSize, int evenSize)
{
     int i;
     for(i=0; i<oddSize; i++)
     {
              printf("odd:%d\n", odd[i]);
     }
     for(i=0; i<evenSize; i++)
     {
              printf("even:%d\n", even[i]);
     }
}

void pauseProgram()
{
     printf("\n\nPress any key to exit..");
     getch();
}
