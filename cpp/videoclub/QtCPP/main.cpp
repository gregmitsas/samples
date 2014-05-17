#include <Qt\qapplication.h>
#include <QtGui\qlayout.h>
#include <Qt\qtablewidget.h>

#include <exception>
#include <iostream>

#include "Movie.h"
#include "Bluray.h"
#include "Dvd.h"
#include "Tape.h"
#include "Customer.h"
#include "Order.h"

#include <vector>
#include <time.h>
#include <numeric>
#include <algorithm>

using namespace std;

void playWithConsole(vector<Movie*>);
bool isHighRated(float);
bool isMoreThanTwoHours(int);
char getFinalS(int);
vector<string> getMovieColumnValues(Movie*);


int main(int argc, char* argv[])
{
	QApplication app(argc, argv);
	QWidget window;

	QGridLayout *layout;
	QTableWidget *tableWidget;

	vector<Movie*> movies;

	try
	{
		QStringList columnHeaders;
		columnHeaders << "Id" << "Title" << "Rating" << "Price" << "Length";

		movies.push_back(new Bluray(1, "Mulholland Dr.", 8.0f, 1.5f, 147));
		movies.push_back(new Dvd(2, "Donnie Darko", 8.1f, 1.0f, 113));
		movies.push_back(new Tape(3, "The Usual Suspects", 8.7f, .5f, 106));
		movies.push_back(new Bluray(4, "Mr. Nobody", 7.9f, 1.5f, 141));
		movies.push_back(new Dvd(5, "Dogville", 8.0f, 1.0f, 178));
		movies.push_back(new Tape(6, "Goodfellas", 8.7f, .5f, 146));

		playWithConsole(movies);
		
		int rows = movies.size();
		int columns = columnHeaders.size();

		tableWidget = new QTableWidget(rows, columns);
		tableWidget->setHorizontalHeaderLabels(columnHeaders);

		for(int i=0; i<rows; i++)
		{
			Movie *movie = movies.at(i);
			vector<string> rowValues = getMovieColumnValues(movie);
			for(int j=0; j<columns; j++)
			{
				tableWidget->setItem(i, j, new QTableWidgetItem(QString::fromStdString(rowValues.at(j))));
			}
		}

		layout = new QGridLayout();
		layout->addWidget(tableWidget);

		window.setLayout(layout);
		window.setMinimumSize(tableWidget->size());
		window.show();

		return app.exec();
	}
	catch(...)
	{
		cerr << "An error occurred." << endl;
	}

	return 0;
}

void playWithConsole(vector<Movie*> movies)
{
	Customer *customer = new Customer();
	customer->setId(1);
	customer->setName("greg");

	Order *order1 = new Order(1, customer);
	order1->addMovie(movies.at(0));
	order1->addMovie(movies.at(1));

	customer->printData();
	order1->printData();

	Order *order2 = new Order(2, customer);
	order2->addMovie(movies.at(2));

	customer->printData();
	order2->printData();

	delete order1;
	delete order2;
	delete customer;

	vector<string> titles;
	vector<float> ratings;
	vector<float> lengths;
	for(Movie *movie : movies)
	{
		titles.push_back(movie->getTitle());
		ratings.push_back(movie->getRating());
		lengths.push_back(movie->getLength());
	}

	cout << endl << endl;
	cout << "===[numeric/accumulate]===" << endl;
	srand((unsigned int)time(0));

	vector<int> numbers;
	for(int i=0; i<10; i++)
	{
		numbers.push_back((rand()%30)-10);
	}

	double sum = accumulate(numbers.begin(), numbers.end(), 0.0);
	cout << "Sum: " << sum << endl;

	int numberToCheck = 5;
	int counter = count(numbers.begin(), numbers.end(), numberToCheck);
	cout << "Found number '" << numberToCheck << "', "  << counter << " time" << getFinalS(counter) << endl;
	cout << "==========================" << endl;

	cout << endl << endl;
	cout << "===[algorithm/sort]===" << endl;
	sort(titles.begin(), titles.end());
	for(string t : titles)
	{
		cout << t << endl;
	}
	cout << "======================" << endl;

	cout << endl << endl;
	cout << "===[algorithm/count_if]===" << endl;
	int totalHighRatedMovies = count_if(ratings.begin(), ratings.end(), isHighRated);
	cout << "Total high rated movies: " << totalHighRatedMovies << endl;
	int totalMoreThanTwoHoursMovies = count_if(lengths.begin(), lengths.end(), isMoreThanTwoHours);
	cout << "Total movies with more than 2 hours length: " << totalMoreThanTwoHoursMovies << endl;
	cout << "==========================" << endl;
}

bool isHighRated(float rating)
{
	return (rating > 7.9f);
}

bool isMoreThanTwoHours(int length)
{
	return (length >= 120);
}

char getFinalS(int input)
{
	return input == 1 ? '\0' : 's';
}

vector<string> getMovieColumnValues(Movie *movie)
{
	vector<string> values;
	values.push_back(to_string(movie->getId()));
	values.push_back(movie->getTitle());
	values.push_back(to_string(movie->getRating()));
	values.push_back(to_string(movie->getPrice()));
	values.push_back(to_string(movie->getLength()));
	return values;
}