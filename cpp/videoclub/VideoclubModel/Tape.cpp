#include "Tape.h"

Tape::Tape(int id, string title, float rating, float price, int length) : Movie(id, title, rating, price, length)
{
}

Tape::~Tape()
{
}

void Tape::printData()
{
	Movie::printData();
	cout << "Quality: Tape - 240p" << endl;
	cout << "Cost: " << getTotalCost() << endl;
	cout << "---------" << endl;
}

float Tape::getTotalCost()
{
	return (float)(getPrice()*1.18);
}