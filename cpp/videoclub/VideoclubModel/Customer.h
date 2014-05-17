#pragma once

#include <vector>
#include <string>

class Order;

using namespace std;

class Customer
{
public:
	Customer();
	~Customer();
	void setId(int);
	int getId();
	void setName(string);
	string getName();
	void addOrder(Order*);
	void printData();
private:
	int id;
	string name;
	vector<Order*> orders;
};

