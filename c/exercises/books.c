#include <stdio.h>
#include <stdlib.h>
#include <conio.h>

#define MAX 5

typedef struct
{
        char *title;
        int pages;
}Book;

typedef struct
{
        int am;
        char *name;
        Book *books[MAX];
        int booksCounter;
}Student;

void setBook(Book*, char*, int);
void printBook(Book*);
void addBook(Student*, Book*);
void setStudent(Student*, int, char*);
void printStudent(Student*);
void pauseProgram();

int main()
{
    Book book1, book2, book3, book4, book5, book6;
    setBook(&book1, "Java 6", 1250);
    setBook(&book2, "C++", 1000);
    setBook(&book3, "Visual Basic", 800);
    setBook(&book4, "C#", 1200);
    setBook(&book5, "PHP", 500);
    setBook(&book6, "Python", 850);
    
    Student student1;
    setStudent(&student1, 1, "greg");
    addBook(&student1, &book1);
    addBook(&student1, &book2);
    addBook(&student1, &book3);
    addBook(&student1, &book4);
    addBook(&student1, &book5);
    addBook(&student1, &book6);
    
    printStudent(&student1);
    
    pauseProgram();
    return 0;
}

void setBook(Book *book, char *title, int pages)
{
     book->title = title;
     book->pages = pages;
}

void printBook(Book *book)
{
     printf("--Book--\n");
     printf("Title: %s\n", book->title);
     printf("Pages: %d\n", book->pages);
     printf("---------\n");
}

void addBook(Student *student, Book *book)
{
     if(student->booksCounter < MAX)
     {
                      student->books[student->booksCounter] = book;
                      student->booksCounter++;
     }
}

void setStudent(Student *student, int am, char *name)
{
     student->am = am;
     student->name = name;
     student->booksCounter = 0;
}

void printStudent(Student *student)
{
     printf("--Student--\n");
     printf("AM: %d\n", student->am);
     printf("Name: %s\n", student->name);
     printf("\nTotal Books: %d\n", student->booksCounter);
     int i=0;
     for(i=0; i<student->booksCounter; i++)
     {
              printf("\n");
              printBook(student->books[i]);
              printf("\n");
     }
     printf("-----------\n");
}

void pauseProgram()
{
     printf("\n\nPress any key to exit...");
     getch();
}
