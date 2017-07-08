 # include <stdio.h>
 # include <string.h>
 # include <unistd.h>
 void prnt ( char game [][32] );
 void line_of ( char line[], char of );
 void game_of_line ( char game [][32]  , char line[] );
 void coor_r ( int coor[][2] , /* row vary from 0 to 30 */ int row );
 void coor_c ( int coor[][2] , /* column vary from 0 to 30 */ int column );
 void fill_of ( char game[][32],int coor[][2],char of,int all_one );
 void colour ( char game [][32],int coor [][2] ,int m);
 char shade ( int z );
 int main ()
 {
 	struct timespec gap ;
 	gap.tv_nsec=250*1000000;
 	gap.tv_sec=0;
 	int z=0,coor[31][2],m=0;
 	coor_r ( coor , 0 );
 	coor_c ( coor , -1 );
 	char game [31][32],line [32];
 	line_of ( line , 'A' );
 	game_of_line ( game , line );
 	getch ();
 	while (1)
 	{
	    printf ("\n\n\n\n\n\n");
	    colour ( game , coor,m%5 );
 		prnt ( game );
 		m++;
 		nanosleep ( &gap,NULL);
 		coor_r ( coor , 0 );
 		coor_c ( coor , -1 );
 		system ("cls");
 	}
 	
 	return 0;
 }
 
 
 void prnt ( char game [][32] )
 {
 	int z=0;
 	for ( z=0;z<=30;z++)
 	{
 		printf ("                                                                  %s\n",game[z]);
 	}
 }


void line_of ( char line[], char of )
{
	int z=0;
	for ( z=0;z<=30;z++)
 	{
 		line[z]=of;
 	}
 	line[z]='\0';
 }



void game_of_line ( char game [][32]  , char line[] )
{
	int z=0;
	for (z=0;z<=30;z++)
 	{
 		strcpy(game[z],line);
 	}
 }
 
void coor_r ( int coor[][2] , /* row vary from 0 to 30 */ int row )
{
	int z=0;
	for ( z=0;z<=30;z++)
	{
		coor[z][0]=row;
	}
}

void coor_c ( int coor[][2] , /* column vary from 0 to 30 */ int column )
{
	int z=0;
	for ( z=0;z<=30;z++)
	{
		coor[z][1]=column;
	}
}

void colour ( char game [][32],int coor [][2],int m )
{
	int z=0,q=1,i=1+m,                        x=15,y=15, zz=1; zz=zz*2-1;
	for ( q=1;q<=16;q++)
	{
		zz=q*2-1;
		coor[y][0]=x;coor[y][1]=y;
		for (z=y;z<=y+zz-1;z++)
		{
			coor [z][1]=z;
			coor [z][0]=x;
		}
		for (z=x;z<=x+zz-1;z++)
		{
			if ( i==5 ) i=1;
			coor_r ( coor , z );
			if ( z==x||z==x+zz-1)
			{
				fill_of ( game,coor,shade (i),111);
			}
			else fill_of ( game , coor,shade (i) ,11);
		}
		x--;y--;i++;
	}
}
	
	
	
	
void fill_of ( char game[][32],int coor[][2],char of,int all_one )
{
	int z=0;	
	for (z=0;z<=30;z++)
	{
		if ( coor[z][1]!=-1)
		{
			game [coor[z][0]][coor[z][1]]=of;
			if( all_one ==11)
			{
				z=(15-z)*2+z;
				game [coor[z][0]][coor[z][1]]=of;
			}
		}
	}
	
		
}
	
	
char shade ( int z )
{
	switch (z)
	{
		case 1:return -80;
		case 2:return -79;
		case 3:return -78;
		case 4:return -79;
	}
}
		
	



































 	
 	
