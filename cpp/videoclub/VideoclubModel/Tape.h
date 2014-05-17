#pragma once

#include "Movie.h"

class Tape : public Movie
{
public:
	Tape(int id, string title, float rating, float price, int length);
	~Tape();
	void printData();
	float getTotalCost();
};