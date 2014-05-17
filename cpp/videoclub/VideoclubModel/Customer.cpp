#include "Customer.h"

#include "Order.h"

Customer::Customer()
{
}

Customer::~Customer()
{
}

void Customer::setId(int id)
{
	this->id = id;
}

int Customer::getId()
{
	return id;
}

void Customer::setName(string name)
{
	this->name = name;
}

string Customer::getName()
{
	return name;
}

void Customer::addOrder(Order *order)
{
	orders.push_back(order);
	order->setCustomer(this);
}

void Customer::printData()
{
	cout << "--CUSTOMER--" << endl;
	cout << "ID: " << id << endl;
	cout << "Name: " << name << endl;
	for(Order *order : orders)
	{
		cout << "Order ID: " << order->getId() << endl;
	}
	cout << "------------" << endl;
}