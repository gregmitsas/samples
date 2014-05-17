#include <stdio.h>
#include <stdlib.h>
#include <conio.h>
#include <time.h>

#define N 9

void task_info(int[], int, int**, int**, int*, int*);
void pauseProgram();

int main()
{
    
    int a[3*N];
    int code;
    int *b;
    int *c;
    int count_b = 0;
    int count_c = 0;
    
    srand((unsigned int)time(0));
    
    int i;
    for (i=0; i<3*N; i+=3)
    {
		a[i]=(rand()%99)+1;
    }
    
	for (i=1; i<3*N; i+=3)
    {
		a[i]=(rand()%9)+1;
    }
    
	for (i=2; i<3*N; i+=3)
    {
		a[i]=(rand()%2)+1;
    }
    
    do
    {
        printf("Give code: ");
        scanf("%d", &code);
    }while(code<=0 || code>=9);
    
    for(i=0; i<3*N; i++)
    {
             printf("pos %d = %d\n", i+1, a[i]);
    }
    
    task_info(a, code, &b, &c, &count_b, &count_c);
    
    for(i=0; i<count_b; i++)
    {
             printf("b[%d] = %d\n", i, b[i]);
    }
    
    for(i=0; i<count_c; i++)
    {
             printf("c[%d] = %d\n", i, c[i]);
    }
    
    pauseProgram();
    return 0;
}

void task_info(int a[3*N], int code, int **b, int **c, int *count_b, int *count_c)
{
     int i;
     
     for(i=1; i<3*N; i+=3)
     {
              if(a[i] == code)
              {
                      if(a[i+1] == 1)
                      {
                              (*count_b)++;
                      }
                      else if(a[i+1] == 2)
                      {
                           (*count_c)++;
                      }       
              }
     }
     
     *b = (int*)malloc(sizeof(int)*(*count_b));
     *c = (int*)malloc(sizeof(int)*(*count_c));
     
     int temp_count_b = 0;
     int temp_count_c = 0;
     for(i=1; i<3*N; i+=3)
     {
              if(a[i] == code)
              {
                      if(a[i+1] == 1)
                      {
                              *(*b+temp_count_b) = a[i-1];
                              temp_count_b++;
                      }
                      else if(a[i+1] == 2)
                      {
                           *(*c+temp_count_c) = a[i-1];
                           temp_count_c++;
                      }       
              }
     }
}

void pauseProgram()
{
     printf("\n\nPress any key to exit...");
     getch();
}
