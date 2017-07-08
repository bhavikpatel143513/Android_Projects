#include <stdio.h>
int main ()
{
	int n=0,z,zz,zzz,a=1,b=1;
	for ( z=1;z<=1000000;z++,n=0)
	{
		for ( zz=1;zz*zz*zz<z;zz++)
		{
			for ( zzz=zz+1;zzz<=100;zzz++)
			{
				if ( zzz*zzz*zzz+zz*zz*zz==z&&zzz!=a&&zz!=b)
				{
					n++;
					a=zz;
					b=zzz;
//					printf ( "num = %d , a = %d , b = %d\t\t",z,zz,zzz);
				}
				if ( n==2)
				{
					printf("*****num = %d , a = %d , b = %d\n",z,zz,zzz);n=0;
					break;
				}
			}if (n==2)break;
		}
	}
	
	return 0;
}
