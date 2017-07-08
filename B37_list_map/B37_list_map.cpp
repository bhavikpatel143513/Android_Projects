#include <stdio.h>
#include <stdlib.h>
#include <conio.h>
#include <string.h>









// *************************struct used ********************************











struct slist_num
{
	struct slist_num * next;
	int * data;
};
struct slist_alpha
{
	struct slist_alpha * next;
	char *data;
};
struct slist_num_name
{
	struct slist_num_name * next;
	char * name;
//	struct slist_num * slist_num_head;
//	char * slist_num_head;
	char * head;
};
struct slist_alpha_name
{
	struct slist_alpha_name * next;
	char * name;
//	struct slist_alpha * slist_alpha_head;
//	char * slist_alpha_head;
	char * head;
};
struct list_type
{
	struct list_type * next;
	char * name;
//	struct slist_num_name * num_head;
//	struct slist_alpha_name * alpha_head;
	char * num_head;
	char * alpha_head;
};
struct start 
{
	struct start * next;
	char *name ;
	char *head;
};













// ************************DECLARATIONS******************************













struct slist_num * slist_num_head = NULL;
struct slist_alpha * slist_alpha_head = NULL;
struct slist_num_name * slist_num_name_head = NULL;
struct slist_alpha_name * slist_alpha_name_head = NULL;
struct list_type * list_type_head = NULL;
struct start * start_head = NULL;
int end = 0;













//*************ADD_FIRST_FUNCTIONS*****************













struct start * addf_start ( struct start * start_head , struct list_type * list_type_head )
{
	struct start * newnode=NULL;
	newnode = ( struct start * ) malloc ( sizeof ( struct start ) );
//	newnode->next = NULL;
	newnode->head = (char *)list_type_head ;
//	if ( start_head == NULL )
//	{
//		start_head = newnode;
//		newnode->next=NULL;
//	}
//	else
//	{
		newnode->next=start_head;
		start_head = newnode;
//	}
	return start_head;
}
struct list_type * addf_list_type ( struct list_type * list_type_head , char * name )
{
	struct list_type * newnode = NULL;
	newnode = ( struct list_type * ) malloc ( sizeof ( struct list_type ) );
	newnode->name = name;
	newnode->num_head=NULL;
	newnode->alpha_head=NULL;
//	newnode->next=NULL;
//	if ( start_head->head == NULL)
//	{
//		start_head->head = newnode;
//		newnode->next=NULL;
//	}
//	else
//	{
//		newnode->next=start_head->head;
//		start_head->head = newnode;
//	}


//	if ( list_type_head == NULL )
//	{
//		newnode->next = NULL;
//		list_type_head = newnode;
//	}
//	else
//	{
		newnode->next = list_type_head;
		list_type_head = newnode;
//	}
	return list_type_head;
}
struct slist_num_name * addf_slist_num_name ( struct slist_num_name * slist_num_name_head , char * name )
{
	struct slist_num_name * newnode = NULL;
	newnode = ( struct slist_num_name * ) malloc ( sizeof ( struct slist_num_name ) );
	newnode->name = name;
	newnode->head = NULL;
	newnode->next = slist_num_name_head;
	slist_num_name_head = newnode;
	return slist_num_name_head;
}
struct slist_alpha_name * addf_slist_alpha_name ( struct slist_alpha_name * slist_alpha_name_head , char * name )
{
	struct slist_alpha_name * newnode = NULL;
	newnode = ( struct slist_alpha_name * ) malloc ( sizeof ( struct slist_alpha_name ) );
	newnode->name = name;
	newnode->head = NULL;
	newnode->next = slist_alpha_name_head;
	slist_alpha_name_head = newnode;
	return slist_alpha_name_head;
}
struct slist_num * addf_slist_num ( struct slist_num * slist_num_head , int * data )
{
	struct slist_num * newnode = NULL;
	newnode = ( struct slist_num *) malloc ( sizeof ( struct slist_num ) );
	newnode->data = data;
	newnode->next = slist_num_head;
	slist_num_head = newnode;
	return slist_num_head;
}
struct slist_alpha * addf_slist_alpha ( struct slist_alpha * slist_alpha_head , char * data )
{
	struct slist_alpha * newnode = NULL;
	newnode = ( struct slist_alpha * ) malloc ( sizeof ( struct slist_alpha ) );
	newnode->data = data;
	newnode->next = slist_alpha_head;
	slist_alpha_head = newnode;
	return slist_alpha_head;
}















//*******************************show functions************************************
















int Show_slist_num ( struct slist_num * slist_num_head )
{
	struct slist_num * temp_slist_num_head = NULL;
	temp_slist_num_head = slist_num_head;
	if ( temp_slist_num_head == NULL )
	{
		printf ( "\n\t\t\t\t\t	Number slist is empty ");
		return 1;
	}
	else 
	{
		int number=0;
		while ( temp_slist_num_head != NULL )
		{
			number += 1;
			printf("\n\t\t\t\t\t	%d. %d ",number,*temp_slist_num_head->data);
			temp_slist_num_head = temp_slist_num_head->next;
		}
		return 0;
	}
}

int Show_slist_alpha ( struct slist_alpha * slist_alpha_head )
{
	struct slist_alpha * temp_slist_alpha_head = NULL;
	temp_slist_alpha_head = slist_alpha_head;
	if ( temp_slist_alpha_head == NULL )
	{
		printf ( "\n\t\t\t\t\t	Alphabet slist is empty ");
		return 1;
	}
	else 
	{
		int number=0;
		while ( temp_slist_alpha_head != NULL )
		{
			number += 1;
			printf("\n\t\t\t\t\t	%d. %c ",number,*temp_slist_alpha_head->data);
			temp_slist_alpha_head = temp_slist_alpha_head->next;
		}
		return 0;
	}
}

int Show_slist_num_name ( )
{
	struct slist_num_name * temp_slist_num_name_head = NULL;
	temp_slist_num_name_head = slist_num_name_head;
	if ( temp_slist_num_name_head == NULL )
	{
		printf ( "\n\t\t\t\t\t	Number slist is empty ");
		return 1;
	}
	else 
	{
		int number=0;
		while ( temp_slist_num_name_head != NULL )
		{
			number += 1;
			printf("\n\t\t\t\t\t	%d. %s ",number,temp_slist_num_name_head->name);
			temp_slist_num_name_head = temp_slist_num_name_head->next;
		}
		return 0;
	}
}

int Show_slist_alpha_name ( )
{
	struct slist_alpha_name * temp_slist_alpha_name_head = NULL;
	temp_slist_alpha_name_head = slist_alpha_name_head;
	if ( temp_slist_alpha_name_head == NULL )
	{
		printf ( "\n\t\t\t\t\t	Alphabet slist is empty ");
		return 1;
	}
	else 
	{
		int number=0;
		while ( temp_slist_alpha_name_head != NULL )
		{
			number += 1;
			printf("\n\t\t\t\t\t	%d. %s ",number,temp_slist_alpha_name_head->name);
			temp_slist_alpha_name_head = temp_slist_alpha_name_head->next;
		}
		return 0;
	}
}

int Show_list_type ( )
{
	struct list_type * temp_list_type_head = NULL;
	temp_list_type_head = list_type_head;
	if ( temp_list_type_head == NULL )
	{
		printf ( "\n\t\t\t\t\t	List type is empty ");
		return 1;
	}
	else 
	{
		int number=0;
		while ( temp_list_type_head != NULL )
		{
			number += 1;
			printf("\n\t\t\t\t\t	%d. %s ",number,temp_list_type_head->name);
			temp_list_type_head = temp_list_type_head->next;
		}
		return 0;
	}
}
















//**************************deletep functions******************************














struct slist_num * deletep_slist_num ( struct slist_num * slist_num_head , int p )
{
	int choise =1;
	struct slist_num * temp1_slist_num_head = NULL;
	struct slist_num * temp2_slist_num_head = NULL;
	temp1_slist_num_head = slist_num_head ;
	if( p<=0 || temp1_slist_num_head == NULL )
	{
		printf ("\n\t\t\t\t\t	Number slist is empty ");
	}
	else 
	{
		while ( choise < p && temp1_slist_num_head->next != NULL )
		{
			choise++;
			temp2_slist_num_head = temp1_slist_num_head;
			temp1_slist_num_head = temp1_slist_num_head->next;
		}
		if (choise == p )
		{
			char sure ='y';
			do
			{
				if (sure != 'y' && sure != 'n')
				{
					printf ("\n\t\t\t\t\t	Choose from give options ");
				}
				printf("\n\t\t\t\t\t	Are you sure to delete this slist \"y\",\"n\" : ");
				fflush ( stdin);
				scanf("%c",&sure );
			}while ( sure != 'y' && sure != 'n');
			if(sure == 'y')
			{	
				temp2_slist_num_head->next = temp1_slist_num_head->next;
				free ( temp1_slist_num_head );
			}
		}
		else if ( temp1_slist_num_head->next == NULL )
		{
			printf ("\n\t\t\t\t\t	Invalid input : number greater than size");
		}
	}
}

struct slist_alpha * deletep_slist_alpha ( struct slist_alpha * slist_alpha_head , int p )
{
	int choise =1;
	struct slist_alpha * temp1_slist_alpha_head = NULL;
	struct slist_alpha * temp2_slist_alpha_head = NULL;
	temp1_slist_alpha_head = slist_alpha_head ;
	if( p<=0 || temp1_slist_alpha_head == NULL )
	{
		printf ("\n\t\t\t\t\t	Alphabet slist is empty ");
	}
	else 
	{
		while ( choise < p && temp1_slist_alpha_head->next != NULL )
		{
			choise++;
			temp2_slist_alpha_head = temp1_slist_alpha_head;
			temp1_slist_alpha_head = temp1_slist_alpha_head->next;
		}
		if (choise == p )
		{
			char sure ='y';
			do
			{
				if (sure != 'y' && sure != 'n')
				{
					printf ("\n\t\t\t\t\t	Choose from give options ");
				}
				printf("\n\t\t\t\t\t	Are you sure to delete this slist \"y\",\"n\" : ");
				fflush ( stdin);
				scanf("%c",&sure );
			}while ( sure != 'y' && sure != 'n');
			if(sure == 'y')
			{
				temp2_slist_alpha_head->next = temp1_slist_alpha_head->next;
				free ( temp1_slist_alpha_head );
			}
		}
		else if ( temp1_slist_alpha_head->next == NULL )
		{
			printf ("\n\t\t\t\t\t	Invalid input : number greater than size");
		}
	}
}

struct slist_num_name * deletep_slist_num_name (  int p )
{
	int choise =1,show = 0 ;
	struct slist_num_name * temp1_slist_num_name_head = NULL;
	struct slist_num_name * temp2_slist_num_name_head = NULL;
	temp1_slist_num_name_head = slist_num_name_head ;
	if( p<=0 || temp1_slist_num_name_head == NULL )
	{
		printf ("\n\t\t\t\t\t	Number slist is empty ");
	}
	else 
	{
		while ( choise < p && temp1_slist_num_name_head->next != NULL )
		{
			choise++;
			temp2_slist_num_name_head = temp1_slist_num_name_head;
			temp1_slist_num_name_head = temp1_slist_num_name_head->next;
		}
		if (choise == p )
		{
			printf ("\n\t\t\t\t\t	slist is : ");
			 show = Show_slist_num ( (struct slist_num*)temp1_slist_num_name_head->head );
			char sure ='y';
			do
			{
				if (sure != 'y' && sure != 'n')
				{
					printf ("\n\t\t\t\t\t	Choose from give options ");
				}
				printf("\n\t\t\t\t\t	Are you sure to delete this slist \"y\",\"n\" : ");
				fflush ( stdin);
				scanf("%c",&sure );
			}while ( sure != 'y' && sure != 'n');
			if(sure == 'y')
			{
				if (p==1)
				{
					slist_num_name_head = NULL;
					free ( temp1_slist_num_name_head );
				}
				else
				{
					temp2_slist_num_name_head->next = temp1_slist_num_name_head->next;
					free ( temp1_slist_num_name_head );
				}			
			}	
		}
		else if ( temp1_slist_num_name_head->next == NULL )
		{
			printf ("\n\t\t\t\t\t	Invalid input : number greater than size");
		}
	}
}

struct slist_alpha_name * deletep_slist_alpha_name ( int p )
{
	int choise =1,show =0;
	struct slist_alpha_name * temp1_slist_alpha_name_head = NULL;
	struct slist_alpha_name * temp2_slist_alpha_name_head = NULL;
	temp1_slist_alpha_name_head = slist_alpha_name_head ;
	if( p<=0 || temp1_slist_alpha_name_head == NULL )
	{
		printf ("\n\t\t\t\t\t	Alphabet slist is empty ");
	}
	else 
	{
		while ( choise < p && temp1_slist_alpha_name_head->next != NULL )
		{
			choise++;
			temp2_slist_alpha_name_head = temp1_slist_alpha_name_head;
			temp1_slist_alpha_name_head = temp1_slist_alpha_name_head->next;
		}
		if (choise == p )
		{
			printf ("\n\t\t\t\t\t	slist is : ");
			show = Show_slist_alpha ((struct slist_alpha*) temp1_slist_alpha_name_head->head );
			char sure ='y';
			do
			{
				if (sure != 'y' && sure != 'n')
				{
					printf ("\n\t\t\t\t\t	Choose from give options ");
				}
				printf("\n\t\t\t\t\t	Are you sure to delete this slist \"y\",\"n\" : ");
				fflush ( stdin);
				scanf("%c",&sure );
			}while ( sure != 'y' && sure != 'n');
			if ( sure == 'y')
			{
				if (p==1)
				{
					slist_alpha_name_head = NULL;
					free ( temp1_slist_alpha_name_head );
				}
				else
				{
					temp2_slist_alpha_name_head->next = temp1_slist_alpha_name_head->next;
					free ( temp1_slist_alpha_name_head );
				}
			}
		}
		else if ( temp1_slist_alpha_name_head->next == NULL )
		{
			printf ("\n\t\t\t\t\t	Invalid input : number greater than size");
		}
	}
}

struct list_type * deletep_list_type ( int p )
{
	int choise =1,show =0;
	struct list_type * temp1_list_type_head = NULL;
	struct list_type * temp2_list_type_head = NULL;
	temp1_list_type_head = list_type_head ;
	if( p<=0 || temp1_list_type_head == NULL )
	{
		printf ("\n\t\t\t\t\t	 List type is empty ");
	}
	else 
	{
		while ( choise < p && temp1_list_type_head->next != NULL )
		{
			choise++;
			temp2_list_type_head = temp1_list_type_head;
			temp1_list_type_head = temp1_list_type_head->next;
		}
		if (choise == p )
		{
			printf ("\n\t\t\t\t\t	Number slist is : ");
			show = Show_slist_num_name ();//( temp1_list_type_head->num_head);
			printf ("\n\t\t\t\t\t	Character slist is : ");
			show = Show_slist_alpha_name ();//( temp1_list_type_head->alpha_head);
			char sure ='y';
			do
			{
				if (sure != 'y' && sure != 'n')
				{
					printf ("\n\t\t\t\t\t	Choose from give options ");
				}
				printf("\n\t\t\t\t\t	Are you sure to delete this slist \"y\",\"n\" : ");
				fflush ( stdin);
				scanf("%c",&sure );
			}while ( sure != 'y' && sure != 'n');
			if ( sure == 'y')
			{
				if (p==1)
				{
					list_type_head = NULL;
					free ( temp1_list_type_head );
				}
				else 
				{
					temp2_list_type_head->next = temp1_list_type_head->next;
					free ( temp1_list_type_head );
				}
			}
		}
		else if ( temp1_list_type_head->next == NULL )
		{
			printf ("\n\t\t\t\t\t	Invalid input : number greater than size");
		}
	}
}
struct start * deletep_start ( int p )
{
	int choise =1,show =0;
	struct start * temp1_start_head = NULL;
	struct start * temp2_start_head = NULL;
	temp1_start_head = start_head ;
	if( p<=0 || temp1_start_head == NULL )
	{
		printf ("\n\t\t\t\t\t	 start is empty ");
	}
	else 
	{
		while ( choise < p && temp1_start_head->next != NULL )
		{
			choise++;
			temp2_start_head = temp1_start_head;
			temp1_start_head = temp1_start_head->next;
		}
		if (choise == p )
		{
			printf ("\n\t\t\t\t\t	 list type is : ");
			show = Show_list_type ();
			char sure ='y';
			do
			{
				if (sure != 'y' && sure != 'n')
				{
					printf ("\n\t\t\t\t\t	Choose from give options ");
				}
				printf("\n\t\t\t\t\t	Are you sure to delete this slist \"y\",\"n\" : ");
				fflush ( stdin);
				scanf("%c",&sure );
			}while ( sure != 'y' && sure != 'n');
			if ( sure == 'y')
			{
				if (p==1)
				{
					start_head = NULL;
					free ( temp1_start_head );
				}
				else
				{
					temp2_start_head->next = temp1_start_head->next;
					free ( temp1_start_head );
				}
			}
		}
		else if ( temp1_start_head->next == NULL )
		{
			printf ("\n\t\t\t\t\t	Invalid input : number greater than size");
		}
	}
}














//****************ADD_LAST_FUNCTIONS*******************














struct slist_num * addl_slist_num ( struct slist_num * slist_num_head , int * data )
{
	struct slist_num * newnode = NULL;
	newnode = ( struct slist_num * ) malloc ( sizeof ( struct slist_num ) );
	newnode->data = data;
	newnode->next = NULL;
	struct slist_num * temp_slist_num_head = NULL;
	temp_slist_num_head = slist_num_head;
	if ( temp_slist_num_head == NULL )
	{
		printf("\n\t\t\t\t\t	Number slist is empty ");
	}
	else 
	{
		while  ( temp_slist_num_head->next != NULL )
		{
			temp_slist_num_head = temp_slist_num_head->next;
		}
		temp_slist_num_head->next = newnode;
	}
	return slist_num_head;
}
struct slist_alpha * addl_slist_alpha ( struct slist_alpha * slist_alpha_head , char * data )
{
	struct slist_alpha * newnode = NULL;
	newnode = ( struct slist_alpha * ) malloc ( sizeof ( struct slist_alpha ) );
	newnode->data = data;
	newnode->next = NULL;
	struct slist_alpha * temp_slist_alpha_head = NULL;
	temp_slist_alpha_head = slist_alpha_head;
	if ( temp_slist_alpha_head == NULL )
	{
		printf("\n\t\t\t\t\t	Alphabet slist is empty ");
	}
	else 
	{
		while  ( temp_slist_alpha_head->next != NULL )
		{
			temp_slist_alpha_head = temp_slist_alpha_head->next;
		}
		temp_slist_alpha_head->next = newnode;
	}
	return slist_alpha_head;
}
















//*********************get & make buffer list***********************

















void get_num ( int **num , int ** num_of_numbers )
{
	int non=0;
	*num_of_numbers = ( int * ) malloc ( sizeof ( int ) );
	printf ("\n\t\t\t\t\t	Enter number of numbers you want to input : ");
	fflush(stdin);
	scanf ("%d",*num_of_numbers);
	non = **num_of_numbers;
	*num = (int * ) malloc ( sizeof ( int ) * (non ) );
	printf ("\n\t\t\t\t\t	Enter numbers : ");
	non = 0;
	while ( **num_of_numbers > non)
	{
		fflush(stdin);
		scanf ("%d", (*num+non) );
		printf("\t\t\t\t\t	                ");
		non++;
	}
	return;
}
void get_alpha ( char ** alpha )
{
	int num_of_alphabets = 0;
	printf ("\n\t\t\t\t\t	Enter number of alphabets you want to input : ");
	fflush(stdin);
	scanf ("%d",&num_of_alphabets);
	*alpha = ( char *) malloc (sizeof ( char )*(num_of_alphabets+1));
	*(*alpha+num_of_alphabets)='\0';
	printf ("\n\t\t\t\t\t	Enter alphabets : ");
	fflush(stdin);
	while ( num_of_alphabets > 0 )
	{
		scanf("%c",*alpha+num_of_alphabets-1);
		num_of_alphabets = num_of_alphabets-1;
	}
}

struct slist_num * make_slist_num ( int * num , int * num_of_numbers )
{
	int number=(*num_of_numbers)-1;
	struct slist_num * slist_num_head=NULL;
	while ( number != -1  )
	{
		slist_num_head = addf_slist_num ( slist_num_head , num+number );
		number--;
	}
	return slist_num_head;
}
struct slist_alpha * make_slist_alpha ( char * alpha )
{
	char * temp=NULL;
	temp=alpha;
	struct slist_alpha * slist_alpha_head = NULL;
	while ( *temp != '\0' )
	{
		slist_alpha_head = addf_slist_alpha ( slist_alpha_head , temp );
		temp += 1;
	}
	return slist_alpha_head;
}











/*




//***********************FUNCTIONS_TO_PRINT*************************













/*

void print_slist_num (struct slist_num * slist_num_head )
{
	struct slist_num * temp = NULL;
	temp = slist_num_head;
	printf ("\n\t\t\t\t\t	");
//	if ( temp == NULL )
//	{
//		printf ("\n\t\t\t\t\t	list is empty");
//	}
//	else
//	{
//		do
//		{
//			printf ("%d",temp->data);
//			temp = temp->next;
//		}while ( temp != NULL )
//	}
	while ( temp != NULL ) 
	{
//		printf( " aa*************aa ");
		printf ("%d",*temp->data);
		temp = temp->next;
	}
}
void print_slist_num_name (struct slist_num_name * slist_num_name_head )
{
	struct slist_num_name * temp = NULL;
	temp = slist_num_name_head ;
	printf ("\n\t\t\t\t\t	");
	while ( temp != NULL ) 
	{
		printf ("%s",temp->name);
		temp = temp->next;
	}
}
void print_slist_alpha ( struct slist_alpha * slist_alpha_head )
{
	struct slist_alpha * temp = NULL;
	temp = slist_alpha_head;
	printf ("\n\t\t\t\t\t	");
	while ( temp != NULL ) 
	{
		printf ("%c",*temp->data);
		temp = temp->next;
	}
}


*/











//****************************refer to p ************************************









struct slist_num* Refer_slist_num ( struct slist_num * slist_num_head ,int choose )
{
		int option =1;
		struct slist_num *temp_slist_num_head  = slist_num_head;
		while ( option < choose )
		{
			option += 1;
//			printf("\n\t\t\t\t\t	%d. %d",option,*temp_slist_num_head->data);
			temp_slist_num_head = temp_slist_num_head->next;
		}
	
	return temp_slist_num_head ;
}


struct slist_alpha* Refer_slist_alpha ( struct slist_alpha * slist_alpha_head ,int   choose )
{
		int option =1;
		struct slist_alpha *temp_slist_alpha_head  = slist_alpha_head;
		while ( option < choose )
		{
			option += 1;
//			printf("\n\t\t\t\t\t	%d. %d",option,*temp_slist_alpha_head->data);
			temp_slist_alpha_head = temp_slist_alpha_head->next;
		}
	
	return temp_slist_alpha_head ;
}


struct slist_num_name * Refer_slist_num_name (int choose  )
{
		int option =1;
		struct slist_num_name *temp_slist_num_name_head  = slist_num_name_head;
		while ( option < choose )
		{
			option += 1;
//			printf("\n\t\t\t\t\t	%d. %s",option,temp_slist_num_name_head->name);
			temp_slist_num_name_head = temp_slist_num_name_head->next;
		}
	
	return temp_slist_num_name_head ;
}

struct slist_alpha_name* Refer_slist_alpha_name ( int choose )
{
		int option =1;
		struct slist_alpha_name * temp_slist_alpha_name_head  = slist_alpha_name_head;
		while ( option < choose )
		{
			option += 1;
//			printf("\n\t\t\t\t\t	%d. %s",option,temp_slist_alpha_name_head->name);
			temp_slist_alpha_name_head = temp_slist_alpha_name_head->next;
		}
	
	return temp_slist_alpha_name_head ;
}



struct list_type * Refer_list_type ( int choose )
{
		int option =1;
		struct list_type * temp_list_type_head  = list_type_head;
		while ( option < choose )
		{
			option += 1;
//			printf("\n\t\t\t\t\t	%d. %s",option,temp_list_type_head->name);
			temp_list_type_head = temp_list_type_head->next;
		}
	
	return temp_list_type_head ;
}

struct start * Refer_start (int choose )
{
	    int	option =1;
		struct start * temp_start_head  = start_head;
		while ( option < choose )
		{
			option += 1;
//			printf("\n\t\t\t\t\t	%d. %s",option,temp_start_head->name);
			temp_start_head = temp_start_head->next;
		}
	
	return temp_start_head ;
}


//***************************choose option*******************************
















int Choose_slist_num ( struct slist_num * slist_num_head )
{
	int choose =1;
	int option =0;
	struct slist_num * temp_slist_num_head  = NULL;
	temp_slist_num_head  = slist_num_head;
	if ( temp_slist_num_head ==NULL )
	{
		printf("\n\t\t\t\t\t	Number slist is empty ");
		return 0;
	}
	else
	{
		while ( temp_slist_num_head != NULL )
		{
			option += 1;
			printf("\n\t\t\t\t\t	%d. %d",option,*temp_slist_num_head->data);
			temp_slist_num_head = temp_slist_num_head->next;
		}
		do
		{
			if ( choose >option || choose < 1)
			{
				printf ( "\n\t\t\t\t\t	Choose from given option only");
			}
			printf("\n\t\t\t\t\t	Choose option : ");
			fflush ( stdin);
			scanf ("%d",&choose);
		}while( choose >option || choose < 1);
//		option =1;
//		temp_slist_num_head  = slist_num_head;
//		while ( option < choose )
//		{
//			option += 1;
//			printf("\n\t\t\t\t\t	%d. %d",option,*temp_slist_num_head->data);
//			temp_slist_num_head = temp_slist_num__head->next;
//		}
	}
	return	choose;//(int*)temp_slist_num_head->data ;
}
		

int Choose_slist_alpha ( struct slist_alpha * slist_alpha_head )
{
	int choose =1;
	int option =0;
	struct slist_alpha * temp_slist_alpha_head  = NULL;
	temp_slist_alpha_head  = slist_alpha_head;
	if ( temp_slist_alpha_head ==NULL )
	{
		printf("\n\t\t\t\t\t	Alphabet slist is empty ");
		return 0;
	}
	else
	{
		while ( temp_slist_alpha_head != NULL )
		{
			option += 1;
			printf("\n\t\t\t\t\t	%d. %d",option,*temp_slist_alpha_head->data);
			temp_slist_alpha_head = temp_slist_alpha_head->next;
		}
		do
		{
			if ( choose >option || choose < 1)
			{
				printf ( "\n\t\t\t\t\t	Choose from given option only");
			}
			printf("\n\t\t\t\t\t	Choose option : ");
			fflush ( stdin);
			scanf ("%d",&choose);
		}while( choose >option || choose < 1);
//		option =1;
//		temp_slist_alpha_head  = slist_alpha_head;
//		while ( option < choose )
//		{
//			option += 1;
//			printf("\n\t\t\t\t\t	%d. %d",option,*temp_slist_alpha_head->data);
//			temp_slist_alpha_head = temp_slist_alpha_head->next;
//		}
	}
	return choose;//(char*)temp_slist_alpha_head->data ;
}


int  Choose_slist_num_name (  )
{
	int choose =1;
	int option =0;
	struct slist_num_name * temp_slist_num_name_head  = NULL;
	temp_slist_num_name_head  = slist_num_name_head;
	if ( temp_slist_num_name_head ==NULL )
	{
		printf("\n\t\t\t\t\t	Number slist is empty ");
		return 0;
	}
	else
	{
		while ( temp_slist_num_name_head != NULL )
		{
			option += 1;
			printf("\n\t\t\t\t\t	%d. %s",option,temp_slist_num_name_head->name);
			temp_slist_num_name_head = temp_slist_num_name_head->next;
		}
		do
		{
			if ( choose >option || choose < 1)
			{
				printf ( "\n\t\t\t\t\t	Choose from given option only");
			}
			printf("\n\t\t\t\t\t	Choose option : ");
			fflush ( stdin);
			scanf ("%d",&choose);
		}while( choose >option || choose < 1);
//		option =1;
//		temp_slist_num_name_head  = slist_num_name_head;
//		while ( option < choose )
//		{
//			option += 1;
//			printf("\n\t\t\t\t\t	%d. %s",option,temp_slist_num_name_head->name);
//			temp_slist_num_name_head = temp_slist_num_name_head->next;
//		}
	}
	return choose;//temp_slist_num_name_head->head ;
}

int Choose_slist_alpha_name (  )
{
	int choose =1;
	int option =0;
	struct slist_alpha_name * temp_slist_alpha_name_head  = NULL;
	temp_slist_alpha_name_head  = slist_alpha_name_head;
	if ( temp_slist_alpha_name_head ==NULL )
	{
		printf("\n\t\t\t\t\t	Alphabet slist is empty ");
		return 0;
	}
	else
	{
		while ( temp_slist_alpha_name_head != NULL )
		{
			option += 1;
			printf("\n\t\t\t\t\t	%d. %s",option,temp_slist_alpha_name_head->name);
			temp_slist_alpha_name_head = temp_slist_alpha_name_head->next;
		}
		do
		{
			if ( choose >option || choose < 1)
			{
				printf ( "\n\t\t\t\t\t	Choose from given option only");
			}
			printf("\n\t\t\t\t\t	Choose option : ");
			fflush ( stdin);
			scanf ("%d",&choose);
		}while( choose >option || choose < 1);
//		option =1;
//		temp_slist_alpha_name_head  = slist_alpha_name_head;
//		while ( option < choose )
//		{
//			option += 1;
//			printf("\n\t\t\t\t\t	%d. %s",option,temp_slist_alpha_name_head->name);
//			temp_slist_alpha_name_head = temp_slist_alpha_name_head->next;
//		}
	}
	return choose;//temp_slist_alpha_name_head->head ;
}



int Choose_list_type (  )
{
	int choose =1;
	int option =0;
	struct list_type * temp_list_type_head  = NULL;
	temp_list_type_head  = list_type_head;
	if ( temp_list_type_head ==NULL )
	{
		printf("\n\t\t\t\t\t	List type is empty ");
		return 0;
	}
	else
	{
		while ( temp_list_type_head != NULL )
		{
			option += 1;
			printf("\n\t\t\t\t\t	%d. %s",option,temp_list_type_head->name);
			temp_list_type_head = temp_list_type_head->next;
		}
		do
		{
			if ( choose >option || choose < 1)
			{
				printf ( "\n\t\t\t\t\t	Choose from given option only");
			}
			printf("\n\t\t\t\t\t	Choose option : ");
			fflush ( stdin);
			scanf ("%d",&choose);
		}while( choose >option || choose < 1);
//		option =1;
//		temp_list_type_head  = list_type_head;
//		while ( option < choose )
//		{
//			option += 1;
//			printf("\n\t\t\t\t\t	%d. %s",option,temp_list_type_head->name);
//			temp_list_type_head = temp_list_type_head->next;
//		}
	}
	return choose;//temp_list_type_head ;
}

int Choose_start ( struct start * start_head )
{
	int choose =1;
	int option =0;
	struct stat * temp_start_head  = NULL;
	temp_start_head  = start_head;
	if ( temp_start_head ==NULL )
	{
		printf("\n\t\t\t\t\t	start is empty ");
		return 0;
	}
	else
	{
		while ( temp_start_head != NULL )
		{
			option += 1;
			printf("\n\t\t\t\t\t	%d. %s",option,temp_start_head->name);
			temp_start_head = temp_start_head->next;
		}
		do
		{
			if ( choose >option || choose < 1)
			{
				printf ( "\n\t\t\t\t\t	Choose from given option only");
			}
			printf("\n\t\t\t\t\t	Choose option : ");
			fflush ( stdin);
			scanf ("%d",&choose);
		}while( choose >option || choose < 1);
//		option =1;
//		temp_start_head  = start_head;
//		while ( option < choose )
//		{
//			option += 1;
//			printf("\n\t\t\t\t\t	%d. %s",option,temp_start_head->name);
//			temp_start_head = temp_start_head->next;
//		}
	}
	return choose;//temp_start_head->head ;
}
















//8888888888888888888888888888888888888888888888888888888888888888888888888888888888
void Edit_number_clist ()
{
	printf("\n\t\t\t\t\t	programme not there!!!!");
	return ;	 
}
void Edit_character_clist ()
{
	printf("\n\t\t\t\t\t	programme not there!!!!");
	return ;	 
}

void Edit_clist ()
{
	int option =4;
	printf ("\n\t\t\t\t\t	Choose option : ");
	printf ("\n\t\t\t\t\t	1. Number clist ");
	printf ("\n\t\t\t\t\t	2. Character clist ");
	printf ("\n\t\t\t\t\t	3. Retrun ");
//	printf ("\n\t\t\t\t\t	4. Home ");
	printf ("\n\t\t\t\t\t	== ");
	fflush (stdin);
	scanf ("%d",&option);
	switch (option)
	{
		case 1:Edit_number_clist ();return;
		case 2:Edit_character_clist ();return;
		case 3:return;
//		case 4:Start ();return;
		default : 
		{
			printf("\n\t\t\t\t\t	invalid input ");
			return;
		}
	}
}
void Edit_number_dlist ()
{
	printf("\n\t\t\t\t\t	programme not there!!!!");
	return ;	 
}
void Edit_character_dlist ()
{
	printf("\n\t\t\t\t\t	programme not there!!!!");
	return ;	 
}

void Edit_dlist ()
{
	int option =4;
	printf ("\n\t\t\t\t\t	Choose option : ");
	printf ("\n\t\t\t\t\t	1. Number dlist ");
	printf ("\n\t\t\t\t\t	2. Character dlist ");
	printf ("\n\t\t\t\t\t	3. Return ");
//	printf ("\n\t\t\t\t\t	4. Home ");
	printf ("\n\t\t\t\t\t	== ");
	fflush (stdin);
	scanf ("%d",&option);
	switch (option)
	{
		case 1:Edit_number_dlist ();return;
		case 2:Edit_character_dlist ();return;
		case 3:return;
//		case 4:Start ();return;
		default : 
		{
			printf("\n\t\t\t\t\t	invalid input ");
			return;
		}
	}
}

void Deletep_slist_num (struct slist_num_name * temp_,int p)
{
	char another = 'y';
	struct slist_num * temp =(slist_num *) temp_->head;
	struct slist_num * tempfree = temp;
	if ( temp_->head == NULL)
	printf ("\n\t\t\t\t\t	number slist is empty ");
	else
	temp = Refer_slist_num ( temp , p-1 );
	tempfree = Refer_slist_num ( tempfree ,p);
	temp->next = tempfree->next ;
	free ( tempfree);
	do 
	{
		if(another !='y' && another != 'n')
		printf("\n\t\t\t\t\t	invalid input : ");
		printf("\n\t\t\t\t\t	want to delete another p'th numbers \"y\",\"n\"");
		fflush ( stdin);
		scanf("%c",&another );
	}while ( another !='y' && another != 'n');
	if ( another == 'y' ) 
	Deletep_slist_num ( temp_ ,p);
	else
	return ;	 
		
}


void Deletel_slist_num (struct slist_num_name * temp_)
{
	char another = 'y';
	struct slist_num * temp =(slist_num *) temp_->head;
	struct slist_num * tempfree = NULL;
	if ( temp_->head == NULL)
	printf ("\n\t\t\t\t\t	number slist is empty ");
	else
	while ( temp->next->next!=NULL )
	temp = temp->next;
	tempfree = temp->next;
	temp->next = NULL;
	free ( tempfree);
	do 
	{
		if(another !='y' && another != 'n')
		printf("\n\t\t\t\t\t	invalid input : ");
		printf("\n\t\t\t\t\t	want to delete another last numbers \"y\",\"n\"");
		fflush ( stdin);
		scanf("%c",&another );
	}while ( another !='y' && another != 'n');
	if ( another == 'y' ) 
	Deletel_slist_num ( temp_ );
	else
	return ;	 
		
}
void Deletef_slist_num (struct slist_num_name * temp_)
{
	char another = 'y';
	struct slist_num * temp =(slist_num *) temp_->head;
	if ( temp_->head == NULL)
	printf ("\n\t\t\t\t\t	number slist is empty ");
	else
	{	temp_->head = (char*)temp->next;
		free ( temp );
	}
	do 
	{
		if(another !='y' && another != 'n')
		printf("\n\t\t\t\t\t	invalid input : ");
		printf("\n\t\t\t\t\t	want to delete another first numbers \"y\",\"n\"");
		fflush ( stdin);
		scanf("%c",&another );
	}while ( another !='y' && another != 'n');
	if ( another == 'y' ) 
	Deletef_slist_num ( temp_ );
	else
	return ;	 
		
}

void Addp_slist_num ( struct slist_num_name * temp_,int p )
{
	char another = 'y';
	struct slist_num * temp = (slist_num *)temp_->head;
	int *num = NULL;
	int *non = NULL;
	get_num ( &num,&non );
	struct slist_num * newhead = make_slist_num ( num,non );
	struct slist_num * newtail = Refer_slist_num ( newhead , *non );
	struct slist_num * temp1 = Refer_slist_num (temp,p);
	struct slist_num * temp2 = Refer_slist_num (temp,p-1);
	newtail->next = temp1;
	temp2->next = newhead;
	do 
	{
		if(another !='y' && another != 'n')
		printf("\n\t\t\t\t\t	invalid input : ");
		printf("\n\t\t\t\t\t	want to add more p numbers \"y\",\"n\"");
		fflush ( stdin);
		scanf("%c",&another );
	}while ( another !='y' && another != 'n');
	if ( another == 'y' ) 
	Addp_slist_num ( temp_ ,p);
	else
	return ;	 
}

void Addl_slist_num ( struct slist_num_name * temp_ )
{
	char another = 'y';
	struct slist_num * temp =(slist_num *) temp_->head;
	int *num = NULL;
	int *non = NULL;
	get_num ( &num,&non );
	struct slist_num * newhead = make_slist_num ( num,non );
	struct slist_num * newtail = Refer_slist_num ( newhead , *non );
	newtail->next = NULL;
	while ( temp->next!=NULL)
	temp = temp->next;
	temp->next = newhead;
	do 
	{
		if(another !='y' && another != 'n')
		printf("\n\t\t\t\t\t	invalid input : ");
		printf("\n\t\t\t\t\t	want to add more numbers at last \"y\",\"n\"");
		fflush ( stdin);
		scanf("%c",&another );
	}while ( another !='y' && another != 'n');
	if ( another == 'y' ) 
	Addl_slist_num ( temp_ );
	else
	return ;	 
}


void  Addf_slist_num ( struct slist_num_name * temp_ )
{
	char another = 'y';
	struct slist_num * temp =(slist_num *) temp_->head;
	int *num = NULL;
	int *non = NULL;
	get_num ( &num,&non );
	struct slist_num * newhead = make_slist_num ( num,non );
	struct slist_num * newtail = Refer_slist_num ( newhead ,* non );
	newtail->next = temp;
	temp_->head = (char*)newhead;
	do 
	{
		if(another !='y' && another != 'n')
		printf("\n\t\t\t\t\t	invalid input : ");
		printf("\n\t\t\t\t\t	want to add more numbers at first\"y\",\"n\"");
		fflush ( stdin);
		scanf("%c",&another );
	}while ( another !='y' && another != 'n');
	if ( another == 'y' ) 
	Addf_slist_num ( temp_ );
	else
	return ;	 
}


void Changename_slist_num (struct slist_num_name * temp_ )
{
	char another = 'y';
	char * name = NULL;
	get_alpha ( &name );
	temp_->name = name;
	do 
	{
		if(another !='y' && another != 'n')
		printf("\n\t\t\t\t\t	invalid input : ");
		printf("\n\t\t\t\t\t	want to change it again\"y\",\"n\"");
		fflush ( stdin);
		scanf("%c",&another );
	}while ( another !='y' && another != 'n');
	if ( another == 'y' ) 
	Changename_slist_num (temp_);
	else
	return ;	 
}


void Deletep_slist_alpha (struct slist_alpha_name * temp_,int p)
{
	char another  = 'y';
	struct slist_alpha * temp = (slist_alpha *)temp_->head;
	struct slist_alpha * tempfree = temp;
	if ( temp_->head == NULL)
	printf ("\n\t\t\t\t\t	character slist is empty ");
	else
	temp = Refer_slist_alpha ( temp , p-1 );
	tempfree = Refer_slist_alpha ( tempfree ,p);
	temp->next = tempfree->next ;
	free ( tempfree);
	do 
	{
		if(another !='y' && another != 'n')
		printf("\n\t\t\t\t\t	invalid input : ");
		printf("\n\t\t\t\t\t	want to delete another p'th character \"y\",\"n\"");
		fflush ( stdin);
		scanf("%c",&another );
	}while ( another !='y' && another != 'n');
	if ( another == 'y' ) 
	Deletep_slist_alpha ( temp_ ,p);
	else
	return ;	 
		
}


void Deletel_slist_alpha (struct slist_alpha_name * temp_)
{
	char another = 'y';
	struct slist_alpha * temp = (slist_alpha *)temp_->head;
	struct slist_alpha * tempfree = NULL;
	if ( temp_->head == NULL)
	printf ("\n\t\t\t\t\t	character slist is empty ");
	else
	while ( temp->next->next!=NULL )
	temp = temp->next;
	tempfree = temp->next;
	temp->next = NULL;
	free ( tempfree);
	do 
	{
		if(another !='y' && another != 'n')
		printf("\n\t\t\t\t\t	invalid input : ");
		printf("\n\t\t\t\t\t	want to delete another last character \"y\",\"n\"");
		fflush ( stdin);
		scanf("%c",&another );
	}while ( another !='y' && another != 'n');
	if ( another == 'y' ) 
	Deletel_slist_alpha ( temp_ );
	else
	return ;	 
		
}
void Deletef_slist_alpha (struct slist_alpha_name * temp_)
{
	char another ='y';
	struct slist_alpha * temp = (slist_alpha *)temp_->head;
	if ( temp_->head == NULL)
	printf ("\n\t\t\t\t\t	character slist is empty ");
	else
	{	temp_->head = (char*)temp->next;
		free ( temp );
	}
	do 
	{
		if(another !='y' && another != 'n')
		printf("\n\t\t\t\t\t	invalid input : ");
		printf("\n\t\t\t\t\t	want to delete another first character \"y\",\"n\"");
		fflush ( stdin);
		scanf("%c",&another );
	}while ( another !='y' && another != 'n');
	if ( another == 'y' ) 
	Deletef_slist_alpha ( temp_ );
	else
	return ;	 
		
}

void Addp_slist_alpha ( struct slist_alpha_name * temp_,int p )
{
	char another ='y';
	struct slist_alpha * temp = (slist_alpha *)temp_->head;
	char * alpha = NULL;
	get_alpha ( &alpha );
	struct slist_alpha * newhead = make_slist_alpha ( alpha );
	struct slist_alpha * newtail = newhead;
	while ( newtail->next!=NULL)
	newtail = newtail->next;
	temp = Refer_slist_alpha ( temp , p-1);
	newtail->next = temp->next;
	temp->next = newhead;
	do 
	{
		if(another !='y' && another != 'n')
		printf("\n\t\t\t\t\t	invalid input : ");
		printf("\n\t\t\t\t\t	want to add more characters at p'th place \"y\",\"n\"");
		fflush ( stdin);
		scanf("%c",&another );
	}while ( another !='y' && another != 'n');
	if ( another == 'y' ) 
	Addp_slist_alpha ( temp_ ,p);
	else
	return ;	 
}

void Addl_slist_alpha ( struct slist_alpha_name * temp_ )
{
	char another ='y';
	struct slist_alpha * temp =(slist_alpha *) temp_->head;
	char * alpha = NULL;
	get_alpha ( &alpha );
	struct slist_alpha * newhead = make_slist_alpha ( alpha );
	struct slist_alpha * newtail = newhead;
	while ( newtail->next!=NULL)
	newtail = newtail->next; 
	newtail->next = NULL;
	while ( temp->next!=NULL)
	temp = temp->next;
	temp->next = newhead;
	do 
	{
		if(another !='y' && another != 'n')
		printf("\n\t\t\t\t\t	invalid input : ");
		printf("\n\t\t\t\t\t	want to add more character at last \"y\",\"n\"");
		fflush ( stdin);
		scanf("%c",&another );
	}while ( another !='y' && another != 'n');
	if ( another == 'y' ) 
	Addl_slist_alpha ( temp_ );
	else
	return ;	 
}


void  Addf_slist_alpha ( struct slist_alpha_name * temp_ )
{
	char another ='y';
	struct slist_alpha * temp =( slist_alpha *) temp_->head;
	char * alpha = NULL;
	get_alpha ( &alpha );
	struct slist_alpha * newhead = make_slist_alpha ( alpha );
	struct slist_alpha * newtail = newhead;
	while ( newtail->next!=NULL)
	newtail = newtail->next; 
	newtail->next = temp;
	temp_->head =(char*) newhead;
	do 
	{
		if(another !='y' && another != 'n')
		printf("\n\t\t\t\t\t	invalid input : ");
		printf("\n\t\t\t\t\t	want to add more character at first\"y\",\"n\"");
		fflush ( stdin);
		scanf("%c",&another );
	}while ( another !='y' && another != 'n');
	if ( another == 'y' ) 
	Addf_slist_alpha ( temp_ );
	else
	return ;	 
}
void Reverse_name ( char *name )
{
	char * temp = NULL;
	int length = strlen ( name );
	temp = ( char *) malloc (sizeof(char )*(length+1));
	strcpy(temp , name);
	int count =0;
	while ( count < length )
	{
		*(name+count )= *(temp + length-1-count );
		count++;
	}
}

void Changename_slist_alpha (struct slist_alpha_name * temp_ )
{
	char another = 'y';
	char * name = NULL;
	get_alpha ( &name );
	Reverse_name ( name );
	temp_->name = name;
	do 
	{
		if(another !='y' && another != 'n')
		printf("\n\t\t\t\t\t	invalid input : ");
		printf("\n\t\t\t\t\t	want to change it again\"y\",\"n\"");
		fflush ( stdin);
		scanf("%c",&another );
	}while ( another !='y' && another != 'n');
	if ( another == 'y' ) 
	Changename_slist_alpha (temp_);
	else
	return ;	 
}


void Edit_character_slist ()
{
	int show=0;
	int option =8;
//	show = Show_slist_alpha_name();
//	if ( show == 1 )
//	{return ;}getch();
	int choose = Choose_slist_alpha_name();
	if ( choose ==0)
	{return ;}
	struct slist_alpha_name * temp_ = Refer_slist_alpha_name (choose);
	struct slist_alpha * temp = ( slist_alpha *) temp_->head;
	printf("\n\t\t\t\t\t	%s",temp_->name);
	show = Show_slist_alpha (temp);
	if ( show  ==1 )
	{return ;}
	printf("\n\t\t\t\t\t	Choose option : ");
	printf("\n\t\t\t\t\t	1. Change name");
	printf("\n\t\t\t\t\t	2. Add at first place");
	printf("\n\t\t\t\t\t	3. Add at last place");
	printf("\n\t\t\t\t\t	4. Add at p'th place");
	printf("\n\t\t\t\t\t	5. Delete first place");
	printf("\n\t\t\t\t\t	6. Delete at last place");
	printf("\n\t\t\t\t\t	7. Delete p'th place");
	printf("\n\t\t\t\t\t	8. Return");
	printf ("\n\t\t\t\t\t	== ");
//	printf("\n\t\t\t\t\t	9. Home");
	do 
	{
		if ( option <1 && option > 8 )
		printf ("\n\t\t\t\t\t	invalid input : ");
		fflush(stdin);
		scanf("%d",&option );
	}while(option <1 && option > 8 );
	switch (option)
	{
		case 1:
		Changename_slist_alpha (temp_);return;
		case 2:
		Addf_slist_alpha ( temp_ );
		return;
		case 3:
		Addl_slist_alpha (temp_);return;
		case 4:
		int p;
		printf("\n\t\t\t\t\t	give p  : ");
		fflush (stdin);
		scanf("%d",&p);
		Addp_slist_alpha (temp_,p);return;
		case 5:
		Deletef_slist_alpha (temp_);return;
		case 6:
		Deletel_slist_alpha (temp_);return;
		case 7:
		printf("\n\t\t\t\t\t	give p  : ");
		fflush (stdin);
		scanf("%d",&p);
		Deletep_slist_alpha (temp_,p);return;
		case 8:
		return ;	 
//		case 9:
//		Start ();return;
		default:return ;	 
	}
}



void Edit_number_slist ()
{
	int option =8,show =0;
//	show = Show_slist_num_name();
//	if ( show == 1 )
//	return ;
	int choose = Choose_slist_num_name();
	if ( choose == 0)
	{return ;}
	struct slist_num_name * temp_ = Refer_slist_num_name (choose);
	struct slist_num * temp =(slist_num *) temp_->head;
	printf("\n\t\t\t\t\t	%s",temp_->name);
	show = Show_slist_num (temp);
	if ( show == 1 )
	return ;
	printf("\n\t\t\t\t\t	Choose option : ");
	printf("\n\t\t\t\t\t	1. Change name");
	printf("\n\t\t\t\t\t	2. Add at first place");
	printf("\n\t\t\t\t\t	3. Add at last place");
	printf("\n\t\t\t\t\t	4. Add at p'th place");
	printf("\n\t\t\t\t\t	5. Delete first place");
	printf("\n\t\t\t\t\t	6. Delete at last place");
	printf("\n\t\t\t\t\t	7. Delete p'th place");
	printf("\n\t\t\t\t\t	8. Return");
	printf ("\n\t\t\t\t\t	== ");
//	printf("\n\t\t\t\t\t	9. Home");
	do 
	{
		if ( option <1 && option > 8 )
		printf ("\n\t\t\t\t\t	invalid input : ");
		fflush(stdin);
		scanf("%d",&option );
	}while(option <1 && option > 8 );
	switch (option)
	{
		case 1:
		Changename_slist_num (temp_);return;
		case 2:
		Addf_slist_num ( temp_ );
		return;
		case 3:
		Addl_slist_num (temp_);return;
		case 4:
		int p;
		printf("\n\t\t\t\t\t	give p  : ");
		fflush (stdin);
		scanf("%d",&p);
		Addp_slist_num (temp_,p);return;
		case 5:
		Deletef_slist_num (temp_);return;
		case 6:
		Deletel_slist_num (temp_);return;
		case 7:
//		int p;
		printf("\n\t\t\t\t\t	give p  : ");
		fflush (stdin);
		scanf("%d",&p);
		Deletep_slist_num (temp_,p);return;
		case 8:
		return;
//		case 9:
//		return;
		default:return ;	 ;
	}
}


void Edit_slist ()
{
	int option =4;
	printf ("\n\t\t\t\t\t	Choose option : ");
	printf ("\n\t\t\t\t\t	1. Number slist ");
	printf ("\n\t\t\t\t\t	2. Character slist ");
	printf ("\n\t\t\t\t\t	3. Return");
//	printf ("\n\t\t\t\t\t	4. Home ");
	printf ("\n\t\t\t\t\t	== ");
	fflush (stdin);
	scanf ("%d",&option);
	switch (option)
	{
		case 1:Edit_number_slist ();return;
		case 2:Edit_character_slist ();return;
//		case 3:Edit_list ();return;
//		case 4:Start ();return;
		default : 
		{
			printf("\n\t\t\t\t\t	invalid input ");
			Edit_slist ();
			return;
		}
	}
}





void Edit_list ()
{
	int option=4;
	printf ("\n\t\t\t\t\t	Choose option : ");
	printf ("\n\t\t\t\t\t	1. Slist ");
	printf ("\n\t\t\t\t\t	2. Dlist ");
	printf ("\n\t\t\t\t\t	3. Clist ");
//	printf ("\n\t\t\t\t\t	4. Return");
	printf ("\n\t\t\t\t\t	== ");
	fflush (stdin);
	scanf ("%d",&option);
	switch (option)
	{
		case 1:Edit_slist ();return;
		case 2:Edit_dlist ();return;
		case 3:Edit_clist ();return;
//		case 4:Start ();return;
		default : 
		{
			printf("\n\t\t\t\t\t	invalid input ");
			Edit_list ();
			return;
		}
	}
}

//8888888888888888888888888888888888888

void Delete_character_clist ()
{
	printf("\n\t\t\t\t\t	programme not there!!!!");
	return ;	 
}

void Delete_number_clist ()
{
	printf("\n\t\t\t\t\t	programme not there!!!!");
	return ;	
}
void Delete_clist ()
{
	int option =4;
	printf ("\n\t\t\t\t\t	Choose option : ");
	printf ("\n\t\t\t\t\t	1. Number clist ");
	printf ("\n\t\t\t\t\t	2. Character clist ");
	printf ("\n\t\t\t\t\t	3. Return ");
//	printf ("\n\t\t\t\t\t	4. Home ");
	printf ("\n\t\t\t\t\t	== ");
	fflush (stdin);
	scanf ("%d",&option);
	switch (option)
	{
		case 1:Delete_number_clist ();return;
		case 2:Delete_character_clist ();return;
		case 3:return;
//		case 4:Start ();return;
		default : 
		{
			printf("\n\t\t\t\t\t	invalid input ");
			Delete_clist ();
			return;
		}
	}
}



void Delete_character_dlist ()
{
	printf("\n\t\t\t\t\t	programme not there!!!!");
	return ;	 
}

void Delete_number_dlist ()
{
	printf("\n\t\t\t\t\t	programme not there!!!!");
	return ;	 
}



void Delete_dlist ()
{
	int option =3;
	printf ("\n\t\t\t\t\t	Choose option : ");
	printf ("\n\t\t\t\t\t	1. Number dlist ");
	printf ("\n\t\t\t\t\t	2. Character dlist ");
	printf ("\n\t\t\t\t\t	3. Return ");
//	printf ("\n\t\t\t\t\t	4. Home ");
	printf ("\n\t\t\t\t\t	== ");
	fflush (stdin);
	scanf ("%d",&option);
	switch (option)
	{
		case 1:Delete_number_dlist ();return;
		case 2:Delete_character_dlist ();return;
		case 3:return;
//		case 4:Start ();return;
		default : 
		{
			printf("\n\t\t\t\t\t	invalid input ");
			Delete_dlist ();
			return;
		}
	}
}



void Delete_character_slist () 
{
	int show =0;
//	show = Show_slist_alpha_name ();
//	if ( show ==1)
//	return ;
	int choose = Choose_slist_alpha_name ();
	if ( choose == 0 )
	{return ;}
	deletep_slist_alpha_name ( choose );
}

void Delete_number_slist () 
{
	int show =0;
//	show = Show_slist_num_name ();
//	if ( show ==1 )
//	return ;
	int choose = Choose_slist_num_name ();
	if(choose == 0 )
	{return ;}
	deletep_slist_num_name ( choose );
}
	
	
	
	
	
void Delete_slist ()
{
	int option=4;
	printf ("\n\t\t\t\t\t	Choose option : ");
	printf ("\n\t\t\t\t\t	1. Number slist ");
	printf ("\n\t\t\t\t\t	2. Character slist ");
	printf ("\n\t\t\t\t\t	3. Return ");
//	printf ("\n\t\t\t\t\t	4. Home ");
	printf ("\n\t\t\t\t\t	== ");
	fflush (stdin);
	scanf ("%d",&option);
	switch (option)
	{
		case 1:Delete_number_slist ();return;
		case 2:Delete_character_slist ();return;
		case 3:return;
//		case 4:Start ();return;
		default : 
		{
			printf("\n\t\t\t\t\t	invalid input ");
			Delete_slist ();
			return;
		}
	}
}




void Delete_list ()
{
	int option =4;
	printf ("\n\t\t\t\t\t	Choose option : ");
	printf ("\n\t\t\t\t\t	1. Slist ");
	printf ("\n\t\t\t\t\t	2. Dlist ");
	printf ("\n\t\t\t\t\t	3. Clist ");
	printf ("\n\t\t\t\t\t	4. Return ");
	printf ("\n\t\t\t\t\t	== ");
	fflush (stdin);
	scanf ("%d",&option);
	switch (option)
	{
		case 1:Delete_slist ();return;
		case 2:Delete_dlist ();return;
		case 3:Delete_clist ();return;
		case 4:return;
		default : 
		{
			printf("\n\t\t\t\t\t	invalid input ");
			Delete_list ();
			return;
		}
	}
}

//888888888888888888888888888888888888
void New_number_clist ()
{
	printf("\n\t\t\t\t\t	programme not there!!!!");
	return ;	 
}

void New_character_clist ()
{
	printf("\n\t\t\t\t\t	programme not there!!!!");
	return ;	 
}


void New_clist ()
{
	int option =3;
	printf ("\n\t\t\t\t\t	Choose option : ");
	printf ("\n\t\t\t\t\t	1. Number clist ");
	printf ("\n\t\t\t\t\t	2. Character clist ");
	printf ("\n\t\t\t\t\t	3. Return ");
//	printf ("\n\t\t\t\t\t	4. Home ");
	printf ("\n\t\t\t\t\t	== ");
	fflush (stdin);
	scanf ("%d",&option);
	switch (option)
	{
		case 1:New_number_clist ();return;
		case 2:New_character_clist ();return;
		case 3:return;
//		case 4:Start();return;
		default : 
		{
			printf("\n\t\t\t\t\t	invalid input ");
			New_clist ();
			return;
		}
	}
}


void New_character_dlist ()
{
	printf("\n\t\t\t\t\t	programme not there!!!!");
	return ;	 
}

void New_number_dlist ()
{
	printf("\n\t\t\t\t\t	programme not there!!!!");
	return ;	 
}

void New_dlist ()
{
	int option =3;
	printf ("\n\t\t\t\t\t	Choose option : ");
	printf ("\n\t\t\t\t\t	1. Number dlist ");
	printf ("\n\t\t\t\t\t	2. Character dlist ");
	printf ("\n\t\t\t\t\t	3. Return ");
//	printf ("\n\t\t\t\t\t	4. Home ");
	printf ("\n\t\t\t\t\t	== ");
	fflush (stdin);
	scanf ("%d",&option);
	switch (option)
	{
		case 1:New_number_dlist ();return;
		case 2:New_character_dlist ();return;
		case 3:return;
//		case 4:Start();return;
		default : 
		{
			printf("\n\t\t\t\t\t	invalid input ");
			New_dlist ();
			return;
		}
	}
}




void New_character_slist ()
{
	char another='n';
	char * alpha = NULL;
	get_alpha ( &alpha );
	slist_alpha_head = make_slist_alpha ( alpha );    
	char * name = NULL;
	printf ( "\n\t\t\t\t\t	give a name to this number slist : ");
	get_alpha ( &name );
	Reverse_name ( name );
	slist_alpha_name_head = addf_slist_alpha_name ( slist_alpha_name_head , name );
	slist_alpha_name_head->head = (char*)slist_alpha_head;
	printf("\n\t\t\t\t\t	New character slist created successfully ");
	do
	{
		if(another!='y'&&another!='n')
		printf("\n\t\t\t\t\t	invalid input ");
		printf("\n\t\t\t\t\t	Want to make another number slist \"y\",\"n\"");
		fflush(stdin);
		scanf("%c",&another );
	}while ( another!='y'&&another!='n');
	if  (another == 'y')
	New_character_slist ();
	else
	return ;	 	  
}


void New_number_slist ()
{
	char another='n';
	int * num = NULL;
	int * num_of_numbers = NULL;
	get_num ( &num , &num_of_numbers );
	slist_num_head = make_slist_num ( num , num_of_numbers );    
	char * name = NULL;
	printf ( "\n\t\t\t\t\t	give a name to this number slist : ");
	get_alpha ( &name );
	Reverse_name ( name );
	slist_num_name_head = addf_slist_num_name ( slist_num_name_head , name );
	slist_num_name_head->head = (char*)slist_num_head;
	printf("\n\t\t\t\t\t	New number slist created successfully ");
	do
	{
		if(another!='y'&&another!='n')
		printf("\n\t\t\t\t\t	invalid input ");
		printf("\n\t\t\t\t\t	Want to make another number slist \"y\",\"n\"");
		fflush(stdin);
		scanf("%c",&another );
	}while ( another!='y'&&another!='n');
	if  (another == 'y')
	New_number_slist ();
	else
	return ;	  
}
	
	
void New_slist ()
{
	int option =3;
	printf ("\n\t\t\t\t\t	Choose option : ");
	printf ("\n\t\t\t\t\t	1. Number slist ");
	printf ("\n\t\t\t\t\t	2. Character slist ");
	printf ("\n\t\t\t\t\t	3. Return ");
//	printf ("\n\t\t\t\t\t	4. Home ");
	printf ("\n\t\t\t\t\t	== ");
	fflush (stdin);
	scanf ("%d",&option);
	switch (option)
	{
		case 1:New_number_slist ();return;
		case 2:New_character_slist ();return;
		case 3:return;
//		case 4:Start();return;
		default : 
		{
			printf("\n\t\t\t\t\t	invalid input ");
			New_slist ();
			return;
		}
	}
}
		
void New_list ()
{
	int option =3;
	printf ("\n\t\t\t\t\t	Choose option : ");
	printf ("\n\t\t\t\t\t	1. Slist ");
	printf ("\n\t\t\t\t\t	2. Dlist ");
	printf ("\n\t\t\t\t\t	3. Clist ");
	printf ("\n\t\t\t\t\t	4. Return ");
	printf ("\n\t\t\t\t\t	== ");
	fflush (stdin);
	scanf ("%d",&option);
	switch (option)
	{
		case 1:New_slist ();return;
		case 2:New_dlist ();return;
		case 3:New_clist ();return;
		case 4:return;
		default : 
		{
			printf("\n\t\t\t\t\t	invalid input ");
			New_list ();
			return;
		}
	}
}



//88888888888888888888888888888888888

		
void Start()
{
	int option=5;
	printf ("\n\t\t\t\t\t	Choose option : ");
	printf ("\n\t\t\t\t\t	1. New list ");
	printf ("\n\t\t\t\t\t	2. Delete list ");
	printf ("\n\t\t\t\t\t	3. Edit list ");
	printf ("\n\t\t\t\t\t	4. Exit ");
//	printf ("\n\t\t\t\t\t	5. Exit ");
	printf ("\n\t\t\t\t\t	== ");
	fflush (stdin);
	scanf ("%d",&option);
	switch (option)
	{
		case 1:New_list ();return;
		case 2:Delete_list ();return;
		case 3:Edit_list ();return;
		case 4:end = 1;return;
//		case 5:end = 1;return;
		default : 
		{
			printf("\n\t\t\t\t\t	invalid input ");
			Start ();
			return;
		}
	}
}




























	
int main ()
{
	start_head = ( struct start * ) malloc ( sizeof ( struct start ) );
	start_head->next = NULL;
	start_head->name =(char*) "list";
	list_type_head = ( struct list_type *) malloc ( sizeof ( struct list_type ) );
	start_head->head = (char*)list_type_head;
	struct list_type * newnode2 = ( struct list_type *) malloc ( sizeof ( struct list_type ) );
	struct list_type * newnode3 = ( struct list_type *) malloc ( sizeof ( struct list_type ) );
	newnode3->next = NULL;
	newnode3->name = (char*) "slist";
	newnode2->next = newnode3;
	newnode2->name = (char*) "dlist";
	list_type_head->next = newnode2;
	list_type_head->name = (char*) "clist";
	while (1)
	{
		Start ();
		if ( end == 1 )
		return 0;
	}
	return 0;
	
}














/*
	int * num = NULL;
	int * num_of_numbers = NULL;
	struct slist_num * slist_num_head = NULL;
	get_num ( num , num_of_numbers );
	slist_num_head = make_slist_num ( num , num_of_numbers );     // SLIST_NUM_HEAD cerated with numbers added
	char * alpha = NULL;
	get_alpha ( alpha );
	struct slist_num_name * slist_num_name_head = NULL;
	slist_num_name_head = addf_slist_num_name ( slist_num_name_head , alpha );     // SLIST_NUM_NAME_HEAD created with name
	slist_num_name_head->head = slist_num_head;
	struct list_type * list_type_head = NULL;
	list_type_head = addf_list_type ( list_type_head , "slist" );    // LIST_TYPE_HEAD created with name "slist"
	list_type_head->num_head = slist_num_name_head;
	struct start * start_head = NULL;
	start_head = addf_start ( start_head , list_type_head );    // start_head created 
*/


/*


//***********************start*************************














/*

void Edit_number_slist ()
{
	Show_slist_num_name ( slist_num_name_head );
	printf(

void Delete_number_slist ()
{
	 struct list_type * temp_list_type_head = NULL;
	 temp_list_type_head = list_type_head ;
	 while ( temp_list_type_head->next != NULL && templlist_type_head->name != "slist" )
	 {
	 	temp_list_type_head = temp_list_type_head->next;
	 }
	 if ( temp_list_type_head->name == "slist" )
	 {
	 	int option=0;
	 	char another = 'y';
	 	struct slist_num_name * temp_slist_num_name_head = NULL;
	 	do
	 	{
		 	temp_slist_num_name_head = slist_num_name_head;
		 	printf ("\n\t\t\t\t\t	Choose option ");
		 	while ( temp_slist_num_name_head != NULL )
		 	{
		 		option += 1;
	 			printf("\n\t\t\t\t\t	%d. %s",option,temp_slist_num_name_head->name);
	 			temp_slist_num_name_head = temp_slist_num_name_head->next;
		 	}
		 	fflush ( stdin );
	 		scanf ("%d",&option);
		 	deletep_slist_num_name ( option );
		 	printf ("\n\t\t\t\t\t	Remaining slist is : ");
	 		temp_slist_num_name_head = slist_num_name_head;
		 	while ( temp_slist_num_name_head != NULL )
		 	{
	 			option += 1;
		 		printf("\n\t\t\t\t\t	%d. %s",option,temp_slist_num_name_head->name);
		 		temp_slist_num_name_head = temp_slist_num_name_head->next;
			}
		 	while(another != 'y'&& another != 'n')
		 	{
	 			printf ("\n\t\t\t\t\t	Want to delete more slist \"y\",\"n\"");
	 			scanf ("%c",&another);
		 		if(another != 'y'&& another != 'n')
		 		printf ("\n\t\t\t\t\t	Please choose only given options ");
	 		}
	 	}while (another == 'y');
	 }
	 else if ( temp_list_type_head->next == NULL )
	 {
	 	printf ("\n\t\t\t\t\t	slist features not there ! Please update your programe ");
	 	start ();
	 }
}
void Delete_alpha_slist ()
{
	 struct list_type * temp_list_type_head = NULL;
	 temp_list_type_head = list_type_head ;
	 while ( temp_list_type_head->next != NULL && temp_list_type_head->name != "slist" )
	 {
	 	temp_list_type_head = temp_list_type_head->next;
	 }
	 if ( temp_list_type_head->name == "slist" )
	 {
	 	int option=0;
	 	char another = 'y';
	 	struct slist_alpha_name * temp_slist_alpha_name_head = NULL;
	 	do
	 	{
		 	temp_slist_alpha_name_head = slist_alpha_name_head;
		 	printf ("\n\t\t\t\t\t	Choose option ");
		 	while ( temp_slist_alpha_name_head != NULL )
		 	{
		 		option += 1;
	 			printf("\n\t\t\t\t\t	%d. %s",option,temp_slist_alpha_name_head->name);
	 			temp_slist_alpha_name_head = temp_slist_alpha_name_head->next;
		 	}
		 	fflush ( stdin );
	 		scanf ("%d",&option);
		 	deletep_slist_alpha_name ( option );
		 	printf ("\n\t\t\t\t\t	Remaining slist is : ");
	 		temp_slist_alpha_name_head = slist_alpha_name_head;
		 	while ( temp_slist_alpha_name_head != NULL )
		 	{
	 			option += 1;
		 		printf("\n\t\t\t\t\t	%d. %s",option,temp_slist_alpha_name_head->name);
		 		temp_slist_alpha_name_head = temp_slist_alpha_name_head->next;
			}
		 	while(another != 'y'&& another != 'n')
		 	{
	 			printf ("\n\t\t\t\t\t	Want to delete more slist \"y\",\"n\"");
	 			scanf ("%c",&another);
		 		if(another != 'y'&& another != 'n')
		 		printf ("\n\t\t\t\t\t	Please choose only given options ");
	 		}
	 	}while (another == 'y');
	 }
	 else if ( temp_list_type_head->next == NULL )
	 {
	 	printf ("\n\t\t\t\t\t	slist features not there ! Please update your programe ");
	 	start ();
	 }
}
	 	
void New_number_slist ()
{
	int * num = NULL;
	int * num_of_numbers = NULL;
	get_num (  &num , &num_of_numbers );
	slist_num_head = make_slist_num ( num , num_of_numbers );     
	char * alpha = NULL;
	get_alpha ( &alpha );
	slist_num_name_head = addf_slist_num_name ( slist_num_name_head , alpha );          // SLIST_NUM_NAME_HEAD created with name
	slist_num_name_head->head = (char*)slist_num_head;   
}

void New_alphabet_slist ()
{
	char * alpha = NULL;
	int * num_of_alphabets = NULL;
	get_alpha ( &alpha );
	slist_alpha_head = make_slist_alpha ( alpha );
	char * name = NULL;
	get_alpha ( &name );
	slist_alpha_name_head = addf_slist_alpha_name ( slist_num_name_head , name );
	slist_alpha_name_head->head = (char*)slist_alpha_head;
}

/*
void Start ()
{
	
	Show_start ( start_head );
	Choose_start ( start_head );
	





/*
void Edit_slist ()
{
	int option;
	printf ("\n\t\t\t\t\t	Choose option : ");
	printf ("\n\t\t\t\t\t	1. Number slist ");
	printf ("\n\t\t\t\t\t	2. Alphabet slist ");
	printf ("\n\t\t\t\t\t	3. Back ");
	printf ("\n\t\t\t\t\t	4. Home ");
	printf ("\n\t\t\t\t\t	== ");
	scanf ("%d",&option);
	switch option
	{
		case 1: Edit_number_slist ();return;
		case 2: Edit_alphabet_slist ();return;
		case 3: Slist ();return;
		case 4: Start ();return;
		default : 
			printf("\n\t\t\t\t\t	invalid input ");
			New_slist ();
			return;
	}
}
void Delete_slist ()
{
	int option;
	printf ("\n\t\t\t\t\t	Choose option : ");
	printf ("\n\t\t\t\t\t	1. Number slist ");
	printf ("\n\t\t\t\t\t	2. Alphabet slist ");
	printf ("\n\t\t\t\t\t	3. Back ");
	printf ("\n\t\t\t\t\t	4. Home ");
	printf ("\n\t\t\t\t\t	== ");
	scanf ("%d",&option);
	switch option
	{
		case 1: Delete_number_slist ();return;
		case 2: Delete_alphabet_slist ();return;
		case 3: Slist ();return;
		case 4: Start ();return;
		default : 
			printf("\n\t\t\t\t\t	invalid input ");
			New_slist ();
			return;
	}
}
void New_slist ()
{
	int option;
	printf ("\n\t\t\t\t\t	Choose option : ");
	printf ("\n\t\t\t\t\t	1. Number slist ");
	printf ("\n\t\t\t\t\t	2. Alphabet slist ");
	printf ("\n\t\t\t\t\t	3. Back ");
	printf ("\n\t\t\t\t\t	4. Home ");
	printf ("\n\t\t\t\t\t	== ");
	scanf ("%d",&option);
	switch (option)
	{
		case 1: New_number_slist ();return;
		case 2: New_alphabet_slist ();return;
		case 3: Slist ();return;
		case 4: Start ();return;
		default : 
			printf("\n\t\t\t\t\t	invalid input ");
			New_slist ();
			return;
	}
}



void Slist ()
{
	int option;
	printf ("\n\t\t\t\t\t	Choose option : ");
	printf ("\n\t\t\t\t\t	1. New slist ");
	printf ("\n\t\t\t\t\t	2. Delete slist ");
	printf ("\n\t\t\t\t\t	3. Edit slist ");
	printf ("\n\t\t\t\t\t	4. Back ");
	printf ("\n\t\t\t\t\t	== ");
	scanf ("%d",&option);
	switch (option)
	{
		case 1: New_slist ();return;
		case 2: Delete_slist ();return;
		case 3: Edit_slist ();return;
		case 4: Start ();return;
		default : 
			printf("\n\t\t\t\t\t	invalid input ");
			Slist ();
			return;
	}
}
void Dlist ()
{
	int option;
	printf ("\n\t\t\t\t\t	Choose option : ");
	printf ("\n\t\t\t\t\t	1. New dlist ");
	printf ("\n\t\t\t\t\t	2. Delete dlist ");
	printf ("\n\t\t\t\t\t	3. Edit dlist ");
	printf ("\n\t\t\t\t\t	4. Back ");
	printf ("\n\t\t\t\t\t	== ");
	scanf ("%d",&option);
	switch option
	{
		case 1: New_dlist ();return;
		case 2: Delete_dlist ();return;
		case 3: Edit_dlist ();return;
		case 4: Start ();return;
		default : 
			printf("\n\t\t\t\t\t	invalid input ");
			Slist ();
			return;
	}
}
void Clist ()
{
	int option;
	printf ("\n\t\t\t\t\t	Choose option : ");
	printf ("\n\t\t\t\t\t	1. New clist ");
	printf ("\n\t\t\t\t\t	2. Delete clist ");
	printf ("\n\t\t\t\t\t	3. Edit clist ");
	printf ("\n\t\t\t\t\t	4. Back ");
	printf ("\n\t\t\t\t\t	== ");
	scanf ("%d",&option);
	switch option
	{
		case 1: New_clist ();return;
		case 2: Delete_clist ();return;
		case 3: Edit_clist ();return;
		case 4: Start ();return;
		default : 
			printf("\n\t\t\t\t\t	invalid input ");
			Slist ();
			return;
	}
}*/

//xxxxxzxzx
