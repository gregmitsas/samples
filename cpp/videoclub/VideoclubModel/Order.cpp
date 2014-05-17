#include "Order.h"

Order::Order(int id, Customer *customer) : id(id), customer(customer)
{
	customer->addOrder(this);
}

Order::~Order()
{
}

void Order::setId(int id)
{
	this->id = id;
}

int Order::getId()
{
	return id;
}

void Order::setCustomer(Customer *customer)
{
	this->customer = customer;
}

Customer* Order::getCustomer()
{
	return customer;
}

void Order::addMovie(Movie *movie)
{
	movies.push_back(movie);
}

void Order::printData()
{
	cout << "--ORDER--" << endl;
	cout << "ID: " << id << endl;
	cout << "Customer Name: " << customer->getName() << endl;
	int totalMovies = movies.size();
	cout << "Total Movies: " << totalMovies << endl;
	if(totalMovies > 0)
	{
		int counter = 0;
		float totalCost = 0.0;
		for(Movie *movie : movies)
		{
			counter++;
			cout << "Movie " << counter << ": " << movie->getTitle() << endl;
			cout << "Cost: " << movie->getTotalCost() << endl;
			totalCost += movie->getTotalCost();
		}
		cout << "Total Cost: " << totalCost << endl;
	}
	cout << "---------" << endl;
}