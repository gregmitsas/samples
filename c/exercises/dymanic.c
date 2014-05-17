#include <stdio.h>
#include <stdlib.h>
#include <conio.h>

#define MAX 5

void fill_array(int[], int**, int*);

int main()
{
    
    int a[MAX] = {1,2,3,4,5};
    int *b;
    int count_b = 0;
    
    fill_array(a, &b, &count_b);
    
    int i;
    for(i=0; i<MAX; i++)
    {
             printf("b[%d] = %d\n", i, b[i]);
    }
    
    printf("count = %d\n", count_b);
    
    printf("\n\nPress any key to exit...");
    getch();
    return 0;
}

void fill_array(int a[MAX], int **b, int *count_b)
{
     *b = (int*)malloc(sizeof(int)*MAX);
     int i;
     for(i=0; i<MAX; i++)
     {
              *(*b+(*count_b)) = a[i];
              (*count_b)++;
     }
}
