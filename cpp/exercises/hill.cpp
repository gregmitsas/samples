#include <iostream>
#include <string>
#include <vector>
#include <map>

#include "Alphabet.h"

using namespace std;

string encrypt(vector<int>, Alphabet*, string);
string decrypt(vector<int>, Alphabet*, string);

bool isOdd(int);
bool isValidKey(vector<int>, int);
int getKeyDeterminant(vector<int>);
bool areRelativelyPrime(int, int);
int haveCommonFactor(int, int);

int main()
{
	Alphabet *alphabet = new Alphabet();

	vector<int> key;
	key.push_back(2);
	key.push_back(3);
	key.push_back(1);
	key.push_back(4);

	isValidKey(key, alphabet->getLength());

	string message = "breathjavaeatcpp";
	cout << "message = [" << message << "]" << endl;

	string encrypted = encrypt(key, alphabet, message);
	cout << "encrypted = [" << encrypted << "]" << endl;

	string decrypted = decrypt(key, alphabet, encrypted);
	cout << "decrypted = [" << decrypted << "]" << endl;

	delete alphabet;

	return 0;
}

string encrypt(vector<int> key, Alphabet *alphabet, string message)
{
	string temp = message;
	int tempSize = temp.size();
	
	if(isOdd(tempSize))
	{
		temp += alphabet->getCharacterByIndex(63);
	}

	vector<int> couples;
	vector<vector<int>> indexes;
	for(int i=0; i<tempSize; i++)
	{
		couples.push_back(alphabet->getIndexByCharacter(temp[i]));
		if(isOdd(i))
		{
			indexes.push_back(couples);
			couples.clear();
		}
	}

	int n1=0;
	int n2=0;
	vector<int> semiEncryptedIndexes;
	for(vector<int> couple : indexes)
	{
		n1 = (couple.at(0) * key.at(0)) + (couple.at(1) * key.at(1));
		n2 = (couple.at(0) * key.at(2)) + (couple.at(1) * key.at(3));
		semiEncryptedIndexes.push_back(n1);
		semiEncryptedIndexes.push_back(n2);
	}

	vector<int> encryptedIndexes;
	int alphabetSize = alphabet->getLength();
	for(int n : semiEncryptedIndexes)
	{
		if(n < 0)
		{
			encryptedIndexes.push_back(alphabetSize+n);
		}
		else
		{
			encryptedIndexes.push_back(n%alphabetSize);
		}
	}

	temp = "";
	for(int n : encryptedIndexes)
	{
		temp += alphabet->getCharacterByIndex(n);
	}

	return temp;
}

string decrypt(vector<int> key, Alphabet *alphabet, string message)
{
	string temp = message;

	vector<int> invertedKey;
	invertedKey.push_back(key.at(3));
	invertedKey.push_back(-key.at(1));
	invertedKey.push_back(-key.at(2));
	invertedKey.push_back(key.at(0));
	
	int determinant = (invertedKey.at(0) * invertedKey.at(3)) - (invertedKey.at(1) * invertedKey.at(2));
	float fDeterminant = (float)determinant;
	float fInvMultiply = fDeterminant * (1.0f/fDeterminant);
	int invMultiply = (int)fInvMultiply;

	int alphabetSize = alphabet->getLength();
	int missingMultiplier = 1;
	int mod = 0;
	while(true)
	{
		mod = (determinant*missingMultiplier)%alphabetSize;
		if(mod == 1)
		{
			break;
		}
		missingMultiplier++;
	}

	vector<int> moduloInvertedKey;
	for(int n : invertedKey)
	{
		if(n < 0)
		{
			moduloInvertedKey.push_back((alphabetSize+n)*missingMultiplier);
		}
		else
		{
			moduloInvertedKey.push_back((n%alphabetSize)*missingMultiplier);
		}
	}

	vector<int> decryptionKey;
	for(int n : moduloInvertedKey)
	{
		if(n < 0)
		{
			decryptionKey.push_back(alphabetSize+n);
		}
		else
		{
			decryptionKey.push_back(n%alphabetSize);
		}
	}

	vector<int> couples;
	vector<vector<int>> indexes;
	int tempSize = temp.size();
	for(int i=0; i<tempSize; i++)
	{
		couples.push_back(alphabet->getIndexByCharacter(temp[i]));
		if(isOdd(i))
		{
			indexes.push_back(couples);
			couples.clear();
		}
	}

	int n1=0;
	int n2=0;
	vector<int> semiDecryptedIndexes;
	for(vector<int> couple : indexes)
	{
		n1 = (couple.at(0) * decryptionKey.at(0)) + (couple.at(1) * decryptionKey.at(1));
		n2 = (couple.at(0) * decryptionKey.at(2)) + (couple.at(1) * decryptionKey.at(3));
		semiDecryptedIndexes.push_back(n1);
		semiDecryptedIndexes.push_back(n2);
	}

	vector<int> decryptedIndexes;
	for(int n : semiDecryptedIndexes)
	{
		if(n < 0)
		{
			decryptedIndexes.push_back(alphabetSize+n);
		}
		else
		{
			decryptedIndexes.push_back(n%alphabetSize);
		}
	}

	temp = "";
	for(int n : decryptedIndexes)
	{
		temp += alphabet->getCharacterByIndex(n);
	}

	return temp;
}

bool isOdd(int number)
{
	return ((number%2) == 0) ? false : true;
}

bool isValidKey(vector<int> key, int alphabetSize)
{
	int keySize = key.size();
	if(keySize > 4)
	{
		cout << "Erroneous key length; must be equal to 4.\nTry again";
		return false;
	}

	int determinant = getKeyDeterminant(key);
	if(determinant != alphabetSize)
	{
		if(areRelativelyPrime(determinant, alphabetSize))
		{
			return true;
		}
	}
	
	return false;
}

int getKeyDeterminant(vector<int> key)
{
	return (key.at(0) * key.at(3)) - (key.at(1) * key.at(2));
}

bool areRelativelyPrime(int n1, int n2)
{
	int gcd = haveCommonFactor(n1, n2);
	if(gcd == 1)
	{
		return true;
	}
	return false;
}

int haveCommonFactor(int n1, int n2)
{
    int temp = 0;
    while(1)
    {
        temp = n1%n2;
        if(temp==0)
		{
			return n2;
		}
        n1 = n2;
        n2 = temp;
    }
}