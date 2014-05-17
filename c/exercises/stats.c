#include <stdio.h>
#include <math.h>
#include <conio.h>

#define N 5

void summary(float*);
void pauseProgram();

int main()
{
    float numbers[] = {3.1, 77.6, 19.2, 54.9, 18.6};
    summary(numbers);

    pauseProgram();
    return 0;
}

void summary(float *array)
{
    float max = array[0], min = array[0], sum = 0, mean = 0, variance = 0, stdDev = 0;

    int i;
    for(i=0; i<N; i++)
    {
        if(array[i] > max)
        {
            max = array[i];
        }
        if(array[i] < min)
        {
            min = array[i];
        }
        sum += array[i];
    }
    mean = sum / N;

    for(i=0; i<N; i++)
    {
        variance += (array[i] - mean) * (array[i] - mean);
    }
    variance /= N;

    stdDev = sqrt(variance);

    printf("Statistical summary:\n");
    printf("%d numbers read\n", N);
    printf("\tMaximum:\t%f\n", max);
    printf("\tMinimum:\t%f\n", min);
    printf("\tSum:\t\t\%f\n", sum);
    printf("\tMean:\t\t%f\n", mean);
    printf("\tVariance:\t%f\n", variance);
    printf("\tStdDev:\t\t%f\n", stdDev);
}

void pauseProgram()
{
     printf("\n\nPress any key to exit...");
     getch();
}
