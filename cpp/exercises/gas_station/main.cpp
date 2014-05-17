#include <iostream>
#include <fstream>
#include <conio.h>

#include "Station.h"

#define N 5

using namespace std;

void readFile(string, Station*);
void pauseProgram();

int main()
{
	string filePath = "stations.txt";
    Station stations[N];
    
    readFile(filePath, stations);
    
    for(int i=0; i<N; i++)
    {
		stations[i].printData();
    }

	pauseProgram();
	return 0;
}

void readFile(string filePath, Station *stations)
{
    fstream inputFile;
    inputFile.open(filePath.c_str(), fstream::in);

    if(inputFile.fail())
    {
        cerr << "no such file: " << filePath << endl;
        return;
    }

    string line = "";
    int counter = 0;

	Station *st;

    while(!inputFile.eof())
    {
        inputFile >> line;
        st = new Station(line);
        if(st->getTotalQuantity() >= 10.0)
        {
			stations[counter] = *st;
        }
        counter++;
    }

    inputFile.close();
}

void pauseProgram()
{
	cout << "\n\nPress any key to exit..";
	_getch();
}