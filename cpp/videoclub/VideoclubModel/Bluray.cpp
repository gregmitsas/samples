#include "Bluray.h"

Bluray::Bluray(int id, string title, float rating, float price, int length) : Movie(id, title, rating, price, length)
{
}

Bluray::~Bluray()
{
}

void Bluray::printData()
{
	Movie::printData();
	cout << "Quality: Bluray - 1080p" << endl;
	cout << "Cost: " << getTotalCost() << endl;
	cout << "---------" << endl;
}

float Bluray::getTotalCost()
{
	return (float)(getPrice()*1.18) + 2;
}