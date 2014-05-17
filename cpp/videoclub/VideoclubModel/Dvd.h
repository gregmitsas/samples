#pragma once

#include "Movie.h"

class Dvd : public Movie
{
public:
	Dvd(int id, string title, float rating, float price, int length);
	~Dvd();
	void printData();
	float getTotalCost();
};