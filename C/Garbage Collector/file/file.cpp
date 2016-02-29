#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
#include <conio.h>
#include <stdlib.h>
#include <time.h>
#include <windows.h>
//using namespace std;

void clrscr()
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

void gotoxy(int x, int y)
{
COORD coord;
coord.X = x;
coord.Y = y;
SetConsoleCursorPosition(GetStdHandle(STD_OUTPUT_HANDLE), coord);
}
void initiallize();
void bubbleSort(int *array, int length);
int play(int scr, int life);
int status(int bx, int dmp, int dx, int dy, int scr, int life);
int gbtypes(int dmp, int dx, int dy);
int specialcases(int);
int mainmenu();
int life; //lifes/hit points
int scr=0; // score
int dx; // dropping x-coordinate
int dy;
int hyplnk = 0;//menu's hyperlinks
int bx; // bin's x-coordinate
int lsr;//left scan range /scoring range
int rsr; //right sr
char ct; //controls;
//void textcolor(int color);
int i, j, k;
int y;
int dmp = 999;
char name[20];
int highscore(int scr);
int score_record();
int info();
int score[50];
int jrk_score[30];
int max,max1,max2,max3,max4;
char maxx[30],maxx1[30],maxx2[30],maxx3[30],maxx4[30];
char namescore[30][30];



int main()
{

hyplnk = 0;
while(hyplnk != 49)
{
mainmenu();

switch (hyplnk)
{
case 49: break;
case 50: clrscr(); info();break;
case 51: clrscr(); score_record();
}
clrscr();
}
gotoxy(2, 20); printf("Enter the player name : "); scanf("%s", &name); printf("\t\tPress any key to start");
	
	
initiallize();
play(scr, life);
getch();


return 0;

}
int info()
{
clrscr();

printf("________________________________________________________________________________");
printf("  //  // ///// //   //  ////// /////   ///// //    //|| \\\\ //        \n");
printf(" ////// // // // / //    //   // //   //,,/ //    //_||  \\//\n");
printf("//  // ///// /// ///    //   /////   //    ///// //  ||  //        \n");
printf("\n");
printf("________________________________________________________________________________");
printf("\n                                               3 %c will be granted at the start\n       Collect the garbage grants 10 point // fail to do so takes away 5 points\n",3);
printf("          Got ran over by an animal takes away %c // Game Over when %c is ran out", 3,3);
printf("\n_________________________________________________________\n");
printf("|||||| \\\\// |||||| ||'''' ;||||                      \n");
printf("  ||    //  ||,,|| ||||||  '',,                        \n");
printf("  ||   //   ||     ||,,,, ||||:                       \n");
printf("           of Garbages                                    \n\n");
printf("---------------------------------------------------------\n");  
printf("\t\t   |||||| |||||| ||||||  ||     ;|||| \n");
printf("\t\t   ||       ||   ||,,||  ||      :;. \n");
printf("\t\t   ||       ||   ||\\\\    ||        ;: \n");
printf("\t\t   ||||||   ||   || \\\\   |||||| ||||: \n");

gotoxy(7, 5);
printf("Collect the garbages while avoid geting ran over by animals");

gotoxy(32, 11);
printf("fishbone: (%c|++<  Battery", 248); //fishbone+battery
gotoxy(32, 12);
printf("Bottle  :%c%c%c%c%c\t  %c%c%c%c%c%c%c", 205, 185, 219, 219, 219, 201, 202, 205, 205, 205, 202, 187);
gotoxy(32, 13);
printf("Leaf    : -(>>\t  %c+   -%c", 186, 186);//leaf
gotoxy(32, 14);
printf("Bone    : 8%c%c%c3\t  %c     %c", 205, 205, 205, 186, 186);//bone
gotoxy(32, 15);
printf("Candy   : >O<\t  %c%c%c%c%c%c%c", 200, 205, 205, 205, 205, 205, 188);//candy


gotoxy(3, 17); //max(65,?)
printf("A : Going  Left");
gotoxy(3, 18);
printf("D : Going Right");
gotoxy(3, 19);
printf("S :       Hover");
gotoxy(3, 20);
printf("] :       Pause");
gotoxy(25, 21);
printf("\t\t\t\t\tPress 1 to start");
gotoxy(20, 22);
printf("\t\t\t\t   Press 2 to return to mainmenu");

gotoxy(2, 23);
printf("Mahidol University Internation College, Department of Computer Engineering.");
hyplnk = _getch();
return 0;
}

int mainmenu()
{

printf("\n\n\n\n\n________________________________________________________________________________");
printf("////// //  // //''''   //////  //||  ||||||   ||'''||   //||  |||||||  ||'''' \n");
printf(" //   ////// //////   // ___  //_||  ||,,,||  ||====   //_||  || ___   ||||||\n");
printf("//   //  // //,,,,   //,,,// //  ||  ||  \\\\   ||,,,|| //  ||  ||,,,||  ||,,,,\n");
printf("\n");
printf("        ////// /////// //     //     //''''  ////// ////// ////// ///// \n");
printf("       //     //   // //     //     //////  //       //   //  // //,,//\n");
printf("      ////// /////// ////// ////// //,,,,  //////   //   ////// //  \\\\\n");
printf("________________________________________________________________________________");
gotoxy(35, 15);
printf("MAINMENU");
gotoxy(30, 16);
printf("1. Start the game");
gotoxy(30, 17);
printf("2. How to play");
gotoxy(30, 18);
printf("3. Score Record");

gotoxy(2, 23);
printf("Mahidol University Internation College, Department of Computer Engineering.");
hyplnk = _getch();
return hyplnk;
}

void initiallize()
{
scr = 0;
life = 3;
bx = 25;
dx = 27;
}

int play(int scr, int life)
{
int dmp = 999; //dumping garbage
while (life>0)
{
ct = _getch();

while ((!_kbhit()) && (life>0))
{
if (dy == 20)
{

dy = 0;
srand(time(NULL));
dx = rand() % 30 + 20;


if (((dx >= bx + lsr) && (dx <= bx +13+ rsr ))) //scan range
{
switch (dmp)
{
case 999:scr = scr - 10; break;
case 6: life--; scr = scr - 10;printf("\a\a");break; //rat
case 7: life++; break;//heart
case 8: life--; scr = scr - 10;printf("\a\a");break; //cat

} scr = scr + 10;
}
else
{
switch (dmp)
{
case 999:scr = scr + 5; break;
case 6:scr = scr + 5; break; //rat
case 8:scr = scr + 5; break; //cat

} scr = scr - 5;
}

dmp = rand() % 9;
}
clrscr();
gotoxy(0, 0); printf("Score : %d\t\t\t\t\t\t\tLife : ", scr, life);

for (i = 0; i < life; i++)
{
if (i<9)
	
cprintf("%c", 3);
if (i == 9) life = 9;
}
gotoxy(dx, dy);
gbtypes(dmp, dx, dy);

switch (ct)
{
case'a': {Sleep(0); bx = bx - 2; if (bx < 10) bx = bx + 2; break; }
case'd': {Sleep(0); bx = bx + 2; if (bx > 60) bx = bx - 2; break; }
case']': {gotoxy(20, 20); printf("Game Pause--Press any key to resume");system("color 6");ct = _getch();system("color 7"); }
}

gotoxy(bx, 20);
_cprintf(" |            |");
gotoxy(bx, 21);
_cprintf(" |            |");
gotoxy(bx, 22);
_cprintf(" |____________|");
Sleep(50);
dy++;

}
}
clrscr();
clrscr();
printf("     \n\n");
printf("________________________________________________________________________________\n");
printf("   ||||||| |||||| |||  ||| ||''''    ,|||,   \\\\   // ||'''' |||||||   \n");
printf("   || ___  ||__|| || \\/ || ||||||   ||   ||   \\\\ //  |||||| ||,,,||    \n");
printf("   ||,,,|| ||  || ||    || ||,,,,    '|||'     \\\\/   ||,,,, ||  \\\\      \n");
printf("________________________________________________________________________________");
gotoxy(40, 13);
printf("GAMEOVER");\
gotoxy(40, 14);
printf("Score %d\n\n\t press any key Return to Main Menu", scr);
getch();
getch();
highscore(scr);
clrscr();
main();
return 0;
}

int status(int bx, int dmp, int dx, int dy, int scr, int life)
{
if (((dx >= bx) && (dx <= bx + 5)) && (dy == 20)) scr = scr + 10;
else scr = scr - 5; life--;
return 0;
}

int gbtypes(int dmp, int dx, int dy)
{
srand(time(NULL));
switch (dmp)
{
case 0: printf("(%c|++<", 248); lsr = 0; rsr = 0; break; //fishbone
case 1: printf("%c%c%c%c%c\n", 205, 185, 219, 219, 219); lsr = 0; rsr = 0; break; //bottle flag for mod <PET bottle>
case 2: printf("-(>>"); lsr = 0; rsr = 0; break;//leaf
case 3: printf("8%c%c%c3", 205, 205, 205); lsr = 0; rsr = 0; break;//bone
case 4: printf(">O<"); lsr = 0; rsr = 0; break;//candy

case 5: printf("%c%c%c%c%c%c%c", 201, 202, 205, 205, 205, 202, 187);
gotoxy(dx, dy + 1); printf("%c+   -%c", 186, 186);
gotoxy(dx, dy + 2); printf("%c     %c", 186, 186);
gotoxy(dx, dy + 3); printf("%c%c%c%c%c%c%c", 200, 205, 205, 205, 205, 205, 188); lsr = 0; rsr = 0; break; //battery
case 6: printf("  _    _ ");
gotoxy(dx, dy + 1); printf(" (o)__(o)  ");
gotoxy(dx, dy + 2); printf("   \\../  ");
gotoxy(dx, dy + 3); printf("  ==\\/==  ");
gotoxy(dx, dy + 4); printf("  (m  m)  ");
gotoxy(dx, dy + 5); printf("m(______)m"); break; lsr = -5; rsr = 5; //mouse/frog
case 7: printf("%c", 3); lsr = 0; rsr = 12; break; //extra life
case 8: printf("  /)                 ");
gotoxy(dx, dy + 1); printf(" // _,,,_   A.-.A        ");
gotoxy(dx, dy + 2); printf("||/       \\/ o o \\       ");
gotoxy(dx, dy + 3); printf("||         =;    /=      ");
gotoxy(dx, dy + 4); printf(" \\_ \\\",,,,  ,,v,,  ");
gotoxy(dx, dy + 5); printf("   /_,))  |_,) )"); lsr = -9; rsr = 9;  break;
}
return 0;

}

int highscore(int scr)
{

	FILE *jrk, *fp, *jrk1, *fp1;;
	time_t current_time;
    char* c_time_string;
 
	current_time = time(NULL);       
    c_time_string = ctime(&current_time);
	
	
     
if((jrk=fopen("userscore.txt","w+"))==NULL)
{
printf("This file does not exist\n");
getch();
exit(1);
}

fprintf(jrk,"Time: %s", c_time_string);
fprintf(jrk,"%s %d",name,scr);
fclose(jrk);
jrk=fopen("userscore.txt","r+");
if((fp=fopen("allscores.txt","a+"))==NULL)
{
printf("This file does not exist\n");
getch();
exit(1);
}
if ((jrk1 = fopen("PlayerNames.txt", "a+")) == NULL)
{
	printf("This file does not exist\n");
	getch();
	exit(1);
}
if ((fp1 = fopen("PlayerScores.txt", "a+")) == NULL)
{
	printf("This file does not exist\n");
	getch();
	exit(1);
}

char stringg[30];	
while(!feof(jrk))
	{
		fgets(stringg,30,jrk);
		fprintf(fp,"%s",stringg);
	}
fprintf(fp,"\n");
fprintf(fp,"-----------------------------------\n\n");
fclose(jrk);
fclose(fp);

fprintf(jrk1,"%s\n",name);
fclose(jrk1);
fprintf(fp1,"%d\n",scr);
fclose(fp1);

return 0;
}

void bubbleSort(int *array, int length) {
	int i, j, temp;
	int sorted; /*use this only if unsure whether the list is already sorted or not*/
	for (i = length - 1; i > 0; i--) {
		sorted = 0;
		for (j = 0; j < i; j++) {
			if (array[j] < array[j + 1]) {
				temp = array[j]; /* swap array[j] and array[j+1] */
				array[j] = array[j + 1];
				array[j + 1] = temp;
				sorted = 1;
			}
		} /*end for j*/
		if (sorted == 0) break; /*will exit if the list is sorted!*/
	} /*end for i*/
} /*end bubbleSort*/


int score_record()
{
	FILE *fp1, *fp;
	FILE *jrk1;
	char stringg2[BUFSIZ][30], string2[BUFSIZ][30];
	fp1 = fopen("PlayerScores.txt", "r+");
	if((jrk1 = fopen("PlayerNames.txt", "r+"))==NULL)
	{
		printf("Pre-Records have been deleted. First Play\n");
		getch();
		info();
	}
	int  r[BUFSIZ], rr[BUFSIZ];
	int yy = 0;

	while (!feof(fp1))
	{
		fscanf(fp1, "%d", &r[yy]);
		fgets(string2[yy], 30, jrk1);
		rr[yy] = r[yy];
		yy++;
	}
	bubbleSort(r, yy);
	char naam0[30], naam1[30], naam2[30], naam3[30], naam4[30];
	for (int i = 0; i < yy; i++)
	{
		if (rr[i] == r[0])strcpy(naam0, string2[i]);
		if (rr[i] == r[1])strcpy(naam1, string2[i]);
		if (rr[i] == r[2])strcpy(naam2, string2[i]);
		if (rr[i] == r[3])strcpy(naam3, string2[i]);
		if (rr[i] == r[4])strcpy(naam4, string2[i]);
	}

int bb;
gotoxy(30, 16);
printf("1. ALL THE SCORES");
gotoxy(30, 17);
printf("2. HIGHEST SCORE");
bb=getch();
switch(bb)
{
case 49:clrscr(); fp=fopen("allscores.txt","r+");
	
	while(!feof(fp))
	{	
		fgets(stringg2[0],30,fp);
		printf("%s",stringg2);
		
	}
	break;
case 50:
	clrscr();
	printf("    ||   || || ||||||| ||   ||     ;|||| ||||||  ,|||,  ||||||| ||''''' ;||||\n");
	printf("    ||||||| || || ___  |||||||      :;.  ||     ||   || ||,,,|| |||||||  :;.  \n");
	printf("    ||   || || ||,,,|| ||   ||     ||||: ||||||  '|||'  ||  \\\\  ||,,,,, ||||: \n");
	if (r[0] > 0 && naam0){
		printf("\n\t%s scored the highest [score=%d]\n\n", naam0, r[0]);
		printf("\n\t%s scored second highest [score=%d]\n\n", naam1, r[1]);
		printf("\n\t%s scored third highest [score=%d]\n\n", naam2, r[2]);
		printf("\n\t%s scored fourth highest [score=%d]\n\n", naam3, r[3]);
		printf("\n\t%s scored fifth highest [score=%d]", naam4, r[4]);
	}
	else printf("\n\n\n\n\n\n\n\t\t\t\tNeed Atleast 5 scores\n\n\n\n\n\n\n\n\n");
	break;
}
printf("\t\t\t     Press 1 to start\t");
printf("\t\t\t\t\t         Press 2 to return to mainmenu");
printf("\n\n");
printf("  Mahidol University Internation College, Department of Computer Engineering.");
hyplnk = _getch();
	return 0;
}


