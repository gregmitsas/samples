#include "Dvd.h"

Dvd::Dvd(int id, string title, float rating, float price, int length) : Movie(id, title, rating, price, length)
{
}

Dvd::~Dvd()
{
}

void Dvd::printData()
{
	Movie::printData();
	cout << "Quality: DVD - 480p" << endl;
	cout << "Cost: " << getTotalCost() << endl;
	cout << "---------" << endl;
}

float Dvd::getTotalCost()
{
	return (float)(getPrice()*1.18) + 1;
}