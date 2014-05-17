#pragma once

#include <iostream>
#include <string>

using namespace std;

class Movie
{
public:
	Movie(int, string, float, float, int);
	~Movie();
	int getId();
	string getTitle();
	float getRating();
	float getPrice();
	int getLength();
	virtual void printData();
	virtual float getTotalCost()=0;
private:
	int id;
	string title;
	float rating;
	float price;
	int length;
};