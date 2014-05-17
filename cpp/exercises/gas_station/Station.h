#pragma once

#define Q 5

#include <iostream>
#include <string>

using namespace std;

class Station
{
public:
	Station();
	Station(string);
	~Station();
	float getTotalQuantity();
	void printData();
private:
	int day;
	int month;
	int type;
	float quantity[Q];
};

