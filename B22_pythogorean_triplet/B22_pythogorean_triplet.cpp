#include <stdio.h>
int main ()
{
	int z,zz,zzz;
	for (z=1;z<=29;z++)
	{ 
		for (zz=z;zz<=29;zz++)
		{
			if(zz+z>30)
			for (zzz=zz-z;zzz<=30;zzz++)
		    {
		    	if (z*z+zz*zz==zzz*zzz)printf ("%d %d %d\n",z,zz,zzz);
		    }
		    else 
		    for (zzz=zz-z;zzz<=zz+z;zzz++)
		    {
		    	if (z*z+zz*zz==zzz*zzz)printf ("%d %d %d\n",z,zz,zzz);
		    }
		}
	}
	return 0;
}
