#include<stdio.h>
#include<conio.h>
int main ()
{
	int count=0;
	char n[10][10];
	printf("entre no.\n");
	for(int i=0;i<=8;i++)
	{ 
	   	scanf("%s",n[i]);
	}
	//check
	char c[10]={"123456789"},cc[10]={"123456789"};
	char s[10];
	
	
	
	
	
	
	
	
	//row
	for(int i=0;i<=8;i++)
	{
		//copy in s
		for (int j=0;j<=8;j++)
		{
			s[j]=n[i][j];
		}
		
		
		
		//check row
		 count=0;
		for (int q=0;q<=8;q++)
		{
			for (int w=0;w<=8;w++)
			{
				if (s[q]==c[w])
				{
					count++;
				    c[w]=0;
		     	}
			}
		}
			if(count !=9)
			{
				printf("wrong sudoku");
				return 1;
			}
			for(int w=0;w<=9;w++)
			{ 
			    c[w]=cc[w];
			}
			
			
			
			
	}
	
	
	
	
	
	
	
	
	//column
	 count=0;
	for(int i=0; i<=8;i++)
	{
		for(int j=0;j<=8;j++)
		{
			s[i]=n[j][i];
		}
		
		
		
			 count=0;
		for (int q=0;q<=8;q++)
		{
			for (int w=0;w<=8;w++)
			{
				if (s[q]==c[w])
				{
					count++;
				    c[w]=0;
		     	}
			}
		}
			if(count !=9)
			{
				printf("wrong sudoku");
				return 2;
			}
			for(int w=0;w<=9;w++)
			{ 
			    c[w]=cc[w];
			}
			
			
	
    }
    
    
    
    
    
    //last block check
    
    
    for (int x=0;x<=2;x++)
    {
    	for(int y=0;y<=2;y++)
    	{
    		for(int a=0;a<=2;a++)
    		{
    			for (int b=0;b<=2;b++)
    			{
    				s[a*3+b]=n[x*3+a][y*3+b];
    			}
    			
    		}
    			
    			//check
    			
    				 count=0;
		for (int q=0;q<=8;q++)
		{
			for (int w=0;w<=8;w++)
			{
				if (s[q]==c[w])
				{
					count++;
				    c[w]=0;
		     	}
			}
		}
			if(count !=9)
			{
				printf("wrong sudoku");
				return 3;
			}
			for(int w=0;w<=9;w++)
			{ 
			    c[w]=cc[w];
			}
			
			
		}
	}
	
	
	
	
	
	
	
     	printf ("solved correctly");
	return 0;
}
