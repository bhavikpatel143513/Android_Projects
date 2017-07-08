#include <stdio.h>
#include <conio.h>
#include <time.h>
#include <stdlib.h>
//ran_num
int ran_num (void);
//ques
int ques (int );
//validity
int validity (int * );
//amt
int amt (int );
int main ()
{
	int m,ans,v,i,status=0,plyr_ans ,check[10]={0,1,2,3,4,5,6,7,8,9};
	
	for (m=0;1;m++)
	{
		if(validity(check))
		return 0;
	 	i=ran_num ()%10;
		if(check[i]==i)
		{
			ans=ques (i);printf ("\n\ntype your ans  =  ");
			scanf("%d",&plyr_ans,fflush(stdin));fflush(stdin);
			if (ans==plyr_ans)
			{
				status++;
				printf ("\namount win = %d \n\n\n",amt(status));
			}
			else 
			{
				printf ("\n\nyou lose .....you win %d ",amt(status));
				return 0;
			}
				
			check[i]=10;
		}
	}
	return 0;
}



//validity

int validity ( int *check)
{
	int i=0,n=0;
	for (i=0;i<=9;i++)
	{
		if(*(check+i)==10)
		{
			n++;
		}
	} 
	return((n==10)?1:0);
}





//ran_num


int ran_num ()
{
	srand (time (NULL));
	return rand();
}



//ques

int ques (int i)
{
	switch (i)
	{
		case 0:{			printf("teri nak kitni h\n1) 1\t\t\t2) 2\n3) 3\t\t\t4) 4\n");return 2;}
		case 1:{			printf("tere per kitne h\n1) 1\t\t\t2) 2\n3) 3\t\t\t4) 4\n");return 2;}
		case 2:{			printf("teri aakh kitni h\n1) 1\t\t\t2) 2\n3) 3\t\t\t4) 4\n");return 2;}
		case 3:{			printf("tere haath kitne h\n1) 1\t\t\t2) 2\n3) 3\t\t\t4) 4\n");return 2;}
		case 4:{			printf("tere kaan kitne h\n1) 1\t\t\t2) 2\n3) 3\t\t\t4) 4\n");return 2;}
		case 5:{			printf("teri jeeb kitni h\n1) 1\t\t\t2) 2\n3) 3\t\t\t4) 4\n");return 2;}
		case 6:{			printf("teri dost kitni h\n1) 1\t\t\t2) 2\n3) 3\t\t\t4) 4\n");return 2;}
		case 7:{			printf("tere muh kitne h\n1) 1\t\t\t2) 2\n3) 3\t\t\t4) 4\n");return 2;}
		case 8:{			printf("tere phone kitne h\n1) 1\t\t\t2) 2\n3) 3\t\t\t4) 4\n");return 2;}
		case 9:{			printf("tere bag kitne h\n1) 1\t\t\t2) 2\n3) 3\t\t\t4) 4\n");return 2;}
	}
}



//amt

int amt (int status)
{
	switch (status)
	{
		case 0:	return 0;
		case 1:	return 5000;
		case 2:	return 5000*2;
		case 3:	return 5000*10;
		case 4:	return 5000*50;
		case 5:	return 5000*100;
		case 6:	return 5000*500;
		case 7:	return 5000*1000;
		case 8:	return 5000*5000;
		case 9:	return 5000*10000;
		case 10:return 5000*10000;
	}
}
