#pragma once

#include "Movie.h"

class Bluray : public Movie
{
public:
	Bluray(int id, string title, float rating, float price, int length);
	~Bluray();
	void printData();
	float getTotalCost();
};