#include <stdio.h>
void prnt ( char game [][11] );
void scan ( char game [][11] );
int give_one_coor (  char game[][11], int coor [][2] );
void row ( char game [][11] ,  int coor [][2] , int z);
void cloumn ( char game [][11] ,  int coor [][2] , int z);
int main ()
{
	int z=0,coor [100][2];
	char game[10][11];
	scan ( game );
	printf ( "\n\n");
	z=give_one_coor ( game , coor );
	row ( game, coor,z );
	cloumn ( game ,  coor,z );
	prnt ( game );
	return 0;
}

void scan ( char game [][11] )
{
	int x,y;
	for ( x=0;x<=9;x++)
	{
		scanf ( "%s",game[x]);
	}
}



void prnt ( char game [][11] )
{
	int x,y;
	for ( x=0;x<=9;x++)
	{
		printf ( "%s\n",game[x]);
	}
}



int give_one_coor (  char game[][11], int coor [][2] )
{
	int x=0,y=0,z=0;
	for ( x=0;x<=9;x++)
	{
		for ( y=0;y<=9;y++)
		{
			if (game [x][y]=='1')
			{
				coor [z][0]=x;
				coor [z][1]=y;
				z++;
			}
		}
	}
	return z;
}


void row ( char game [][11] ,  int coor [][2] , int z)
{
	int zz=0,zzz=0;
	for ( zz=0;zz<=z-1;zz++)
	{
		for (zzz=0;zzz<=9;zzz++)
		{
			game[coor [zz][0]][zzz]='1';
		}
	}
}


void cloumn ( char game [][11] ,  int coor [][2] , int z)
{
	int zz=0,zzz=0;
	for ( zz=0;zz<=z-1;zz++)
	{
		for (zzz=0;zzz<=9;zzz++)
		{
			game[zzz][coor[zz][1]]='1';
		}
	}
}
				
