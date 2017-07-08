#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <conio.h>
#define E '5'
#define R '6'
#define L '4'
#define U '8'
#define D '2'
#define B01 1
#define B11 11
#define B21 21
#define B31 31
#define B41 41
#define B51 51
#define S00 00
#define W01 -1
#define W11 -11
#define W21 -21
#define W31 -31
#define W41 -41
#define W51 -51
void fsenik ( int z,int c);
void fhathi ( int z,int c);
void fghoda ( int z,int c);
void fuut   ( int z,int c);
void frani  ( int z,int c);
void fraja ( int z,int c);
void fblank ( int z,int c);
void x_y ( char dir, int *x,int *y);
void fraja_ ( int z,int c);
void frani_ ( int z,int c);
void fuut_ ( int z,int c);
void fghoda_ ( int z,int c);
void fhathi_ ( int z,int c);
void fsenik_ ( int z,int c);
int main ()
{
    int selected=0;
	int a=0,b=0,aa=0,bb=0; 
	char dir = R;
	int z=0,zz=0,zzz=0,WW=-37,BB=32,k,bor,x=0,y=5;
	int cube [8][8]={    {B11,B21,B31,B41,B51,B31,B21,B11},
	                     {B01,B01,B01,B01,B01,B01,B01,B01},
	                     {S00,S00,S00,S00,S00,S00,S00,S00},
	                     {S00,S00,S00,S00,S00,S00,S00,S00},
	                     {S00,S00,S00,S00,S00,S00,S00,S00},
	                     {S00,S00,S00,S00,S00,S00,S00,S00},
	                     {W01,W01,W01,W01,W01,W01,W01,W01},
	                     {W11,W21,W31,W41,W51,W31,W21,W11}    };
	while(1)
	{                    
	if (x==0)//UPPER BORDER OF HIGHLIGHTER BOX
	{
						                      
		for( bor=1;bor<1+y*10;bor++)
		printf("%c",-78);
		for( bor=1+y*10;bor<1+y*10+10;bor++)
		printf("%c",-41);
		for( bor=1+y*10+10;bor<81;bor++)
		printf("%c",-78);
		printf("\n"); 
	}
	else//IN CASE OF NO UPPER BORDER OF HIGHLIGHTER BOX
	{
		for( bor=0;bor<=79;bor++)
		printf("%c",-78);
		printf("\n");  
	}
	for ( z=0;z<=7;z++)//Z REPRESENTS ROW NO. OF CHESS BOARD
	{
		for ( zz=0;zz<=3;zz++)//ZZ REPRESENTS ROW NO. OF BOX(WHITE OR BLACK) OF CHESS
		{
			for ( zzz=0;zzz<=7;zzz++)//ZZZ REPRESENTS COLUMN NO. OF CHESS BOARD
			{
				k=cube[z][zzz];//K MEANS KATI 
				if ( x==z&&zzz==y)//FOR FORMING LEFT BORDER OF HIGHLIGHTER BOX { ( X,Y) GIVES CURRENT LOCATION OF HIGHLIGHTER BOX}
				{
					printf ("%c",-41);
				}
				else
				{
					printf ("%c",-78);
				}
				switch (k)
				{
					case B01:
					{
						fsenik ( zz,(z+zzz)%2);
						
					}
					break;
					case B11:
					{
						fhathi ( zz,(z+zzz)%2);
					}
					break;
					case B21:
					{
						fghoda ( zz,(z+zzz)%2);
					}
					break;
					case B31:
					{
						fuut ( zz,(z+zzz)%2);
					}
					break;
					case B41:
					{
						frani ( zz,(z+zzz)%2);
					}
					break;
					case B51:
					{
						fraja ( zz,(z+zzz)%2);
					}
					break;
					case S00:
					{
						fblank ( zz,(z+zzz)%2);
					}
					break;
					case W11:
					{
						fhathi_ ( zz,(z+zzz)%2);
					}
					break;
					case W21:
					{
						fghoda_ ( zz,(z+zzz)%2);
					}
					break;
					case W31:
					{
						fuut_ ( zz,(z+zzz)%2);
					}
					break;
					case W41:
					{
						frani_ ( zz,(z+zzz)%2);
					}
					break;
					case W51:
					{
						fraja_ ( zz,(z+zzz)%2);
					}
					break;
					case W01:
					{
						fsenik_ ( zz,(z+zzz)%2);
					}
					break;
				}
				if (x==z&&y==zzz)//FOR FORMING RIGHT BORDER OF HIGHLIGHTER BOX { ( X,Y) GIVES CURRENT LOCATION OF HIGHLIGHTER BOX}
				{
					printf("%c",-41);
				}
				else
				{
					printf ("%c",-78);
				}
			}
			printf("\n");
		}
		if (x==z||x-1==z)//LOWER BORDER OF HIGHLIGHTER BOX
		{
						                      
			for( bor=1;bor<1+y*10;bor++)
			printf("%c",-78);
			for( bor=1+y*10;bor<1+y*10+10;bor++)
			printf("%c",-41);
			for( bor=1+y*10+10;bor<81;bor++)
			printf("%c",-78);
			printf("\n"); 
		}
		else//IN CASE OF NO LOWER BORDER OF HIGHLIGHTER BOX
		{
			for( bor=0;bor<=79;bor++)
			printf("%c",-78);
			printf("\n");  
		}
	}
	if ( selected ==1)
	printf("********************************SELECTED**********************************");
	
	dir = getch();
	x_y (dir,&a,&b); 
	if(x==7&&a==1||x==0&&a==-1);//TO MOVE ONLY WITHIN CHESS
	else
	{
		x=x+a;
	}
	if (y==7&&b==1||y==0&&b==-1);//TO MOVE ONLY WITHIN CHESS
	else 
	{
		y=y+b;
	}
	
	if ( a==0&&b==0&&selected==0)// WHEN '5' IS TYPED
	{
		aa=x;
		bb=y;
		selected=1;
		
	}
	else if ( a==0&&b==0&&selected==1)
	{
		selected =0;
		if ( cube [x][y]%2==1&&cube[aa][bb]==0||cube [x][y]%2==-1&&cube[aa][bb]==0||cube [x][y]%2==0&&cube[aa][bb]==1||cube [x][y]%2==0&&cube[aa][bb]==-1)
        {	
		a=cube [x][y];
		cube[x][y]=cube[aa][bb];
		cube[aa][bb]=a;
		a=0;
		}
//		if (cube [x][y]%2==1&&cube[aa][bb]==1||cube [x][y]%2==-1&&cube[aa][bb]==-1)
//		{
//			selected=1;
//		}
		if (cube [x][y]%2==1&&cube[aa][bb]==-1||cube [x][y]%2==-1&&cube[aa][bb]==1)
		{
			a=cube [x][y];
			cube[x][y]=cube[aa][bb];
			cube[aa][bb]=S00;
			a=0;
		}
	}
	
//	printf("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
	
	
		system ("cls");
	
	
	
	
	
	}
	
	
	
	
	
	
	
	return 0;
}



void fsenik ( int z,int c)
{
	int WW,BB;
	if ( c==0)
	{
		WW=-37;
		BB=32;
	}
	else
	{
		WW=32;
		BB=-37;
	}
	struct kati
	{
		char pic [4][9];
		int label;
	};
	struct kati senik={  {BB, BB, BB, BB, BB, BB, BB, BB, '\0',
	                      BB, BB, BB, WW, WW, BB, BB, BB, '\0',
	                      BB, BB, BB, WW, WW, BB, BB, BB, '\0',
	                      BB, BB, BB, WW, WW, BB, BB, BB, '\0'},0};
	                      
    printf("%s",senik.pic[z]);
    
}



void fhathi ( int z,int c)
{
	int WW,BB;
	if ( c==0)
	{
		WW=-37;
		BB=32;
	}
	else
	{
		WW=32;
		BB=-37;
	}
	struct kati
	{
		char pic [4][9];
		int label;
	};
	struct kati hathi={  {BB, BB, BB, BB, BB, BB, BB, BB, '\0',
	                      BB, BB, WW, BB, WW, BB, WW, BB, '\0',
	                      BB, BB, WW, WW, WW, WW, WW, BB, '\0',
	                      BB, BB, BB, WW, WW, WW, BB, BB, '\0'},1};
	printf("%s",hathi.pic[z]);
    
}
	
void fuut ( int z,int c)
{
	int WW,BB;
	if ( c==0)
	{
		WW=-37;
		BB=32;
	}
	else
	{
		WW=32;
		BB=-37;
	}
	struct kati
	{
		char pic [4][9];
		int label;
	};	
    struct kati uut  ={  {BB, BB, BB, BB, BB, BB, BB, BB, '\0',
	                      BB, BB, BB, BB, WW, BB, BB, BB, '\0',
	                      BB, BB, BB, WW, WW, WW, BB, BB, '\0',
	                      BB, BB, WW, WW, WW, WW, WW, BB, '\0'},3};    
    printf("%s",uut.pic[z]);
    
}
void fghoda ( int z, int c)
{
	int WW,BB;
	if ( c==0)
	{
		WW=-37;
		BB=32;
	}
	else
	{
		WW=32;
		BB=-37;
	}
	struct kati
	{
		char pic [4][9];
		int label;
	};	
    struct kati ghoda={  {BB, BB, BB, BB, BB, BB, BB, BB, '\0',
	                      BB, WW, WW, WW, WW, BB, BB, BB, '\0',
	                      BB, WW, WW, WW, WW, WW, WW, BB, '\0',
	                      BB, WW, WW, BB, BB, BB, BB, BB, '\0'},2};    
    printf("%s",ghoda.pic[z]);
    
}
void fraja ( int z,int c)
{
	int WW,BB;
	if ( c==0)
	{
		WW=-37;
		BB=32;
	}
	else
	{
		WW=32;
		BB=-37;
	}
	struct kati
	{
		char pic [4][9];
		int label;
	};	
	struct kati raja ={  {BB, BB, BB, BB, BB, BB, BB, BB, '\0',
	                      BB, BB, BB, WW, WW, WW, BB, BB, '\0',
	                      BB, BB, WW, WW, WW, WW, WW, BB, '\0',
	                      BB, BB, BB, WW, BB, WW, BB, BB, '\0'},5};    	
	printf("%s",raja.pic[z]);
    
}

void frani ( int z,int c)
{
	int WW,BB;
	if ( c==0)
	{
		WW=-37;
		BB=32;
	}
	else
	{
		WW=32;
		BB=-37;
	}
	struct kati
	{
		char pic [4][9];
		int label;
	};	
    struct kati rani ={  {BB, BB, BB, BB, BB, BB, BB, BB, '\0',
	                      BB, BB, WW, BB, WW, BB, WW, BB, '\0',
	                      BB, BB, BB, WW, WW, WW, BB, BB, '\0',
	                      BB, BB, BB, WW, WW, WW, BB, BB, '\0'},4}; 
	printf("%s",rani.pic[z]);
    
}

void fblank ( int z,int c)
{
	int WW,BB;
	if ( c==0)
	{
		WW=-37;
		BB=32;
	}
	else
	{
		WW=32;
		BB=-37;
	}
	struct kati
	{
		char pic [4][9];
		int label;
	};	
   	struct kati blank={  {BB, BB, BB, BB, BB, BB, BB, BB, '\0',
	                      BB, BB, BB, BB, BB, BB, BB, BB, '\0',
	                      BB, BB, BB, BB, BB, BB, BB, BB, '\0',
	                      BB, BB, BB, BB, BB, BB, BB, BB, '\0'},0};
	printf("%s",blank.pic[z]);
    
}



void fsenik_ ( int z,int c)
{
	int WW,BB;
	if ( c==0)
	{
		WW=-80;
		BB=32;
	}
	else
	{
		WW=32;
		BB=-80;
	}
	struct kati
	{
		char pic [4][9];
		int label;
	};
	struct kati senik={  {BB, BB, BB, BB, BB, BB, BB, BB, '\0',
	                      BB, BB, BB, WW, WW, BB, BB, BB, '\0',
	                      BB, BB, BB, WW, WW, BB, BB, BB, '\0',
	                      BB, BB, BB, WW, WW, BB, BB, BB, '\0'},0};
	                      
    printf("%s",senik.pic[z]);
    
}
void fhathi_ ( int z,int c)
{
	int WW,BB;
	if ( c==0)
	{
		WW=-80;
		BB=32;
	}
	else
	{
		WW=32;
		BB=-80;
	}
	struct kati
	{
		char pic [4][9];
		int label;
	};
	struct kati hathi={  {BB, BB, BB, BB, BB, BB, BB, BB, '\0',
	                      BB, BB, WW, BB, WW, BB, WW, BB, '\0',
	                      BB, BB, WW, WW, WW, WW, WW, BB, '\0',
	                      BB, BB, BB, WW, WW, WW, BB, BB, '\0'},1};
	printf("%s",hathi.pic[z]);
    
}
void fghoda_ ( int z,int c)
{
	int WW,BB;
	if ( c==0)
	{
		WW=-80;
		BB=32;
	}
	else
	{
		WW=32;
		BB=-80;
	}
	struct kati
	{
		char pic [4][9];
		int label;
	};
	struct kati ghoda={  {BB, BB, BB, BB, BB, BB, BB, BB, '\0',
	                      BB, WW, WW, WW, WW, BB, BB, BB, '\0',
	                      BB, WW, WW, WW, WW, WW, WW, BB, '\0',
	                      BB, WW, WW, BB, BB, BB, BB, BB, '\0'},2};    
    printf("%s",ghoda.pic[z]);
    
}
void fuut_ ( int z,int c)
{
	int WW,BB;
	if ( c==0)
	{
		WW=-80;
		BB=32;
	}
	else
	{
		WW=32;
		BB=-80;
	}
	struct kati
	{
		char pic [4][9];
		int label;
	};
	struct kati uut  ={  {BB, BB, BB, BB, BB, BB, BB, BB, '\0',
	                      BB, BB, BB, BB, WW, BB, BB, BB, '\0',
	                      BB, BB, BB, WW, WW, WW, BB, BB, '\0',
	                      BB, BB, WW, WW, WW, WW, WW, BB, '\0'},3};    
    printf("%s",uut.pic[z]);
    
}
void frani_ ( int z,int c)
{
	int WW,BB;
	if ( c==0)
	{
		WW=-80;
		BB=32;
	}
	else
	{
		WW=32;
		BB=-80;
	}
	struct kati
	{
		char pic [4][9];
		int label;
	};
	struct kati rani ={  {BB, BB, BB, BB, BB, BB, BB, BB, '\0',
	                      BB, BB, WW, BB, WW, BB, WW, BB, '\0',
	                      BB, BB, BB, WW, WW, WW, BB, BB, '\0',
	                      BB, BB, BB, WW, WW, WW, BB, BB, '\0'},4}; 
	printf("%s",rani.pic[z]);
    
}
void fraja_ ( int z,int c)
{
	int WW,BB;
	if ( c==0)
	{
		WW=-80;
		BB=32;
	}
	else
	{
		WW=32;
		BB=-80;
	}
	struct kati
	{
		char pic [4][9];
		int label;
	};
	struct kati raja ={  {BB, BB, BB, BB, BB, BB, BB, BB, '\0',
	                      BB, BB, BB, WW, WW, WW, BB, BB, '\0',
	                      BB, BB, WW, WW, WW, WW, WW, BB, '\0',
	                      BB, BB, BB, WW, BB, WW, BB, BB, '\0'},5};    	
	printf("%s",raja.pic[z]);
    
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
	if ( dir == E )
	{*x=0;*y=0;}//printf("\n\n********************************SELECTED**********************************");}
}

