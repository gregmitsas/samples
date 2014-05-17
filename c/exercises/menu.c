#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <conio.h>

#define ROWS 3
#define COLUMNS 2

void printArray(char**);
void printArrayElements(char**, int, int, int, int);
void printArrayElementsRange(char**, int, int);
void pauseProgram();

typedef struct
{
    int x;
    int y;
}Variables;

typedef struct
{
    char option1[10];
    char option2[10];
    char option3[10];
    char option4[10];
    char option5[10];
    char option6[10];
    char option7[10];
    char option8[10];
    char option9[10];
    char option10[10];
}Options;

int main()
{
    int smallArray[5] = {1,2,3,4,5};
    int array[ROWS][COLUMNS] = { {1,smallArray[4]}, {3,4}, {5,6} };

    int i;
    int j;

    for(i=0; i<ROWS; i++)
    {
        printf("ROW[%d]: ", i+1);
        for(j=0; j<COLUMNS; j++)
        {
            printf("[%d]", array[i][j]);
        }
        printf("\n");
    }


    Options o;
    strcpy(o.option1, "Menu1");
    strcpy(o.option2, "Menu2");
    strcpy(o.option3, "Menu3");
    strcpy(o.option4, "Menu4");
    strcpy(o.option5, "Menu5");
    strcpy(o.option6, "Menu6");
    strcpy(o.option7, "Menu7");
    strcpy(o.option8, "Menu8");
    strcpy(o.option9, "Menu9");
    strcpy(o.option10, "Menu10");

    char *menuArray[10] = {o.option1, o.option2, o.option3, o.option4, o.option5, o.option6, o.option7, o.option8, o.option9, o.option10};
    printf("\nPrinting full array..\n");
    printArray(menuArray);
    printf("\n---------------------\n");
    printf("\nPrinting specific elements..\n");
    printArrayElements(menuArray, 0, 1, 2, 3);
    printf("\n---------------------\n");
    printf("\nPrinting specific elements..\n");
    printArrayElements(menuArray, 1, 2, 3, 4);
    printf("\n---------------------\n");
    printArrayElementsRange(menuArray, 6, 8);
    printf("\n---------------------\n");

    pauseProgram();
    return 0;
}

void printArray(char *array[10])
{
    int i;
    for(i=0; i<10; i++)
    {
        printf("%s\n", array[i]);
    }
}

void printArrayElements(char *array[10], int n1, int n2, int n3, int n4)
{
    printf("%s\n", array[n1]);
    printf("%s\n", array[n2]);
    printf("%s\n", array[n3]);
    printf("%s\n", array[n4]);
}

void printArrayElementsRange(char *array[10], int startIndex, int finishIndex)
{
    int i;
    for(i=startIndex; i<=finishIndex; i++)
    {
        printf("%s\n", array[i]);
    }
}

void pauseProgram()
{
    printf("\nPress any key to exit..");
    getch();
}
