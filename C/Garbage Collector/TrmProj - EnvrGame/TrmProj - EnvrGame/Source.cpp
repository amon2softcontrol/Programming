#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
#include <conio.h>
#include <stdlib.h>
#include <time.h>
#include <Windows.h>
using namespace std;
void clrscr() //use to clear screen instead of system("cls"), which makes the console flicking
{
	COORD coordScreen = { 0, 0 };
	DWORD cCharsWritten;
	CONSOLE_SCREEN_BUFFER_INFO csbi;
	DWORD dwConSize;
	HANDLE hConsole = GetStdHandle(STD_OUTPUT_HANDLE);
	GetConsoleScreenBufferInfo(hConsole, &csbi);
	dwConSize = csbi.dwSize.X * csbi.dwSize.Y;
	FillConsoleOutputCharacter(hConsole, TEXT(' '),
		dwConSize,
		coordScreen,
		&cCharsWritten);
	GetConsoleScreenBufferInfo(hConsole, &csbi);
	FillConsoleOutputAttribute(hConsole,
		csbi.wAttributes,
		dwConSize,
		coordScreen,
		&cCharsWritten);
	SetConsoleCursorPosition(hConsole, coordScreen);
}
void gotoxy(int x, int y) // use to print a function instead of array; gotoxy(x,y) where x,y are coordinates 
{
	COORD coord;
	coord.X = x;
	coord.Y = y;
	SetConsoleCursorPosition(GetStdHandle(STD_OUTPUT_HANDLE), coord);
}
void initiallize();// set start score and lifes
int play(); //gameplay
int status(); //updating lifes and score [planning]
int hp; //lifes/hit points
int scr; // score
int dx; // dropping x-coordinate
int dy;// dropping  y- coordinate
int bx; // bin's x-coordinate
int cr = 1;//color[don't work; finding the function]
char ct; //controls;
int i, j, k; // loops controls

int main()
{
	initiallize();
	while (true)// infinite loop is subject to change to "play till life==0"
	{
		play();
	//	status();
	}
}
void initiallize()
{
	scr = 0;
	hp = 3;
	bx = 25; //starting x-coordinate of box // y-coordinate is constant
	dx = 27;// starting x-coordinate of dropping garbage // y-coordinate is controled by the "play" function
}
int build(int bx) //unused function; the garbage dropper code prototype
{
	srand(time(NULL));
	dx = rand() % 70 + 10; gotoxy(dx, 3);
	for (i = 3; i < 20; i++)
	{
		Sleep(100); //"Sleep" used to halt the program // prevent it from refreshing too fast
		system("cls");
		gotoxy(dx, i);
		printf("O");
	}
	return 0;
}
int play()
{
	while (!_kbhit()!='q')
	{
		ct = _getch();

		while (!_kbhit())
		{
			clrscr();

			gotoxy(dx, dy);
			printf("O");

			switch (ct)
			{
			case'a': Sleep(0); bx--; {if (bx < 0) bx++; break; }
			case'd': Sleep(0); bx++; {if (bx > 70) bx--; break; }
			case'o': cr++; break;
			case'p': cr--; break;
			}
			gotoxy(bx, 20);
			
			
			_cprintf(" |    |");
			gotoxy(bx, 21);
			_cprintf(" |____|");
			Sleep(50); //control of garbage dropping speed
			dy++;
			if (dy == 20)
			{
				dy = 0;
				srand(time(NULL));
				dx = rand() % 30+20;
				
			}
		}
	}

	

	return 0;
}
int status()
{

	return 0;
}
	/*
int play2()
{
	{
		ct = _getch();
		system("cls");
		switch (ct)
		{
		case'a': bx--; {if (bx < 0) bx++; break; }
		case'd': bx++; {if (bx > 70) bx--; break; }
		}
		gotoxy(bx, 20);
		{
		printf(" |    |");
		gotoxy(bx, 21);
		printf(" |____|");
		}
		
	}
	return bx;
}
*/