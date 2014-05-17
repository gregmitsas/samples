#pragma once

#include <iostream>
#include <vector>

#include "Customer.h"
#include "Movie.h"

using namespace std;

class Order
{
public:
	Order(int, Customer*);
	~Order();
	void setId(int);
	int getId();
	void setCustomer(Customer*);
	Customer* getCustomer();
	void addMovie(Movie*);
	void printData();
private:
	int id;
	Customer *customer;
	vector<Movie*> movies;
};