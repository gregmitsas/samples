#include "Movie.h"

Movie::Movie(int id, string title, float rating, float price, int length) : id(id), title(title), rating(rating), price(price), length(length)
{
}

Movie::~Movie()
{
}

int Movie::getId()
{
	return id;
}

string Movie::getTitle()
{
	return title;
}

float Movie::getRating()
{
	return rating;
}

float Movie::getPrice()
{
	return price;
}

int Movie::getLength()
{
	return length;
}

void Movie::printData()
{
	cout << "--MOVIE--" << endl;
	cout << "ID: " << id << endl;
	cout << "Title: " << title << endl;
	cout << "Rating: " << rating << endl;
	cout << "Length: " << length  << " min" << endl;
}