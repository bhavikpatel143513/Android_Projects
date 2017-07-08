#include <stdio.h>
#include <time.h>
#include <unistd.h>
#include <stdlib.h>

//match
void match ( int );
//remove
int remove ( int );
//m_1
void m_1 ( int  );
//m_2
void m_2 ( int  );
//p
int p ( int  );
//m_n
void m_n ( int );
int main ()
{
	int y_n,left=21,val=0,switc=0,ct=0,cwin=0;
	printf ("\n\n***************WANT TO PLAY A GAME*****************\n\n");sleep (2);
	printf ("*************WE HAVE 21 MATCH STICKS***************\n\n");sleep (2);
	printf ("******WE CAN PICK 1,2,3 OR 4 STICKS AT A TIME******\n\n");sleep (2);
	printf ("*******ONE WHO PICKS THE LAST STICK LOOSES*********\n\n");sleep (2);
	printf ("***WOULD YOU LIKE TO PICK FIRST    YES=1   NO=0 ***\n\n");
	scanf  ("%d",&y_n);sleep (2);
	printf ("\n\n*********************LETS START********************\n\n");sleep (2);
	while ( left != 1 )
	{
		if ( y_n== 1)
		{
			
			while ( y_n == 1 )
			{
				
				match  (left);
				y_n=p (y_n);
				scanf  ("%d",&val);
				sleep (3);
				left = left - val ;
				switc = ((left ==1 || left == 6 || left == 11 || left == 16 ||left == 21)?0:1);
			}
			
		}
		else if ( y_n == 0 )
		{
			while ( y_n == 0 )
			{
				match  (left);
				y_n=p(y_n);
				if (( switc++ == 0 )&&(left ==1 || left == 6 || left == 11 || left == 16 ||left == 21))
				{
					cwin++;
					srand ( time(NULL));
		        	val = rand ()%4+1;
		        	left = left - val ;
		        	printf ("**********COMPUTER PICKS    %d ***********\n\n",val);sleep (2);
		        }
		        
		        else 
		        {
		        	ct=0;
		        	do
		        	{
		        		ct++;
		        		left--;
		        	}while  (ct!=4&&!(left ==1 || left == 6 || left == 11 || left == 16 ));
		        	printf ("**********COMPUTER PICKS   %d ***********\n\n",ct);sleep (2);
		        	
		        }
		    }
		}
	}
	match (left);
	if ( cwin == 4 )
	{
		printf ("**********************GAME OVER*******************************\n\n");
		printf ("***********************YOU WIN*******************************\n\n");
		return 0;
	}
	printf ("**********************GAME OVER*******************************\n\n");
	printf ("***********************YOU LOSE*******************************\n\n");
	return 0;
}
//match
void match ( int i )
{
	int x=1;
	{
		m_1 (i);
		m_2 (i);
		m_n (i);
	}
}
//m_1
void m_1 ( int i )
{
	int x=1;
	for ( ; x<=i;x++)
	printf("%5c",-37);
	printf ("\n");
}
//m_2
void m_2 ( int i )
{
	int x=1;
	for ( ; x<=i;x++)
	printf("%5c",-70);
	printf ("\n");
}	
//p
int p ( int y_n )
{
	if ( y_n == 1)
	{
		printf ("\n*********TYPE THE NUMBER OF STICKS YOU WANT TO PICK **********\n\n");
		y_n--;
	}
	else if ( y_n == 0 )
	{
		printf ("\n*************COMPUTER TURN *** PLEASE WAIT ***************\n\n");
		y_n++;
		sleep (4);
		
	}
	return y_n;
}
//m_n
void m_n (int i )
{
	int x=1;
	for ( ; x<=i;x++)
	printf("%5d",x);
	printf ("\n");
}





