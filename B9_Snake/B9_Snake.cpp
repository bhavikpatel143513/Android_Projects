#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <conio.h>
#include <unistd.h>
#include <time.h>
# define R '6'
# define L '4'
# define U '8'
# define D '2'
void prnt ( char game [][161] );
void x_y ( char dir, int *x,int *y);
void frame ( char game[][161]);
int you ( int snake[][2],char dir );
int main ()
{
	int snake [21][2],z=0,x=0,y=0,game_over=0,score=-1;
//	                   
	char game [30][161],dir=R,blank[161];
	for(z=1;z<160;z++)
	{
		blank[z]=32;
	}
	blank[z]='\0';
	for ( z = 0;z<=29;z++)
	{
		strcpy ( game [z], blank);
	}
	for ( z=20;z<39;z++)
	{
		game [20][z]=-37;
		snake[z-20][0]=20;
		snake[z-20][1]=z;
	}
	game [20][z]=-78;
	snake [z-20][0]=20;
	snake [z-20][1]=z;   
	frame ( game );     
    while ( game_over ==0)
	{
		prnt ( game );
		do
		{
			dir = getch ();
		}while ( dir !='6'&&dir !='4'&&dir!='8'&&dir!='2'||you(snake,dir));
		x_y ( dir , &x, &y );
		snake[20][0]=snake[19][0]+x;
		snake[20][1]=snake[19][1]+y;
		if( game [snake [20][0]][snake[20][1]] == 32 )
		{
			z=19;
			while ( z>=0)
			{
				
				game[snake[z+1][0]][snake[z+1][1]]=game[snake[z][0]][snake[z][1]];
				z--;
			}
			game[snake[0][0]][snake[0][1]]=32;
			z=0;
			while ( z<=19)
			{
				snake[z][0]=snake[z+1][0];
				snake[z][1]=snake[z+1][1];
				z++;
			}
			for ( z=0;z<=-1;z++)
			{
				
				do
				{
					srand ( time ( NULL));
					x=rand()%30;
					y=rand()%160;
				}while ( game [x][y]!=32);
				game [x][y]=-37;
			}
		}
		else  game_over =1;
		system ( "cls" );
		score++;
	}
	printf ("*******GAME OVER ********");
	printf ("*******SCORE = %d *****",score);
	return 0;
}
	
		
	
	
	

void prnt ( char game [][161]  )
{
	int z=0;
	printf ("\n");
	for (z=0;z<30;z++)
	{
		printf ("   %s\n",game[z]);
	}
}



void x_y ( char dir, int *x,int *y)
{
	if (dir == R)
	{*x=0;*y=1;}
	if ( dir == L )
	{*y=-1;*x=0;}
	if ( dir == D )
	{*x=1;*y=0;}
	if ( dir == U )
	{*x=-1;*y=0;}
}

void frame ( char game[][161])
{
	int i=0,j=0;
	for (i=0;i<=29;i++)
	{
		for (j=0;j<=159;j++)
		{
			if ( i==0 || j==0 || i==29 || j==159 )
			{
				game[i][j]=-37;
			}
		}
	}
}

int you ( int snake[][2],char dir )
{
	int x=0,y=0;
	x_y ( dir,&x,&y);
	if ( snake[19][0]+x == snake [18][0] && snake [19][1] + y ==snake [18][1] )
	{
		return 1;
	} else return 0;
}







































	
		
