#include "Station.h"

Station::Station()
{
	day = 0;
	month = 0;
	type = 0;
	for(int i=0; i<Q; i++)
	{
		quantity[i] = 0;
	}
}

Station::Station(string input)
{
	string chunks[7];
	int chunksCounter = 0;
                            
	for(int i=0; i<input.length(); i++)
	{
		if(input[i] != ',')
		{
			chunks[chunksCounter] += input[i];
		}
        else
        {
			chunksCounter++;
		}
	}
                            
	string strDay = chunks[0].substr(0,2);
	string strMonth = chunks[0].substr(2,4);
                            
    day = atoi(strDay.c_str());
    month = atoi(strMonth.c_str());
    type = atoi(chunks[1].c_str());
                            
    for(int i=0; i<Q; i++)
    {
		quantity[i] = atof(chunks[i+2].c_str());
	}
}

Station::~Station()
{
}

float Station::getTotalQuantity()
{
	float total = 0.0;
    for(int i=0; i<Q; i++)
    {
		total += quantity[i];
	}
    return total;
}

void Station::printData()
{
	cout << "Day: " << day << endl;
    cout << "Month: " << month << endl;
    cout << "Type: " << type << endl;
    for(int i=0; i<Q; i++)
    {
		printf("Quantity Value %d: %.1f\n", i+1, quantity[i]);
	}
    printf("Total Quantity: %.1f\n", getTotalQuantity());
}
