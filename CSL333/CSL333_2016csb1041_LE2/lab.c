#include <pthread.h>
#include <stdio.h>
#include <limits.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>


void main()
{
	
	// Given arrays :
	int N_arr[] = {10, 20, 40, 80, 160, 320, 640,1280};
	int K_arr[] = {10,20,30,40,50,60};
	clock_t start1,end1,start2,end2; 


	int i,j,k,l,temp;
	
	// For each combination pair :
	for(i=0;i<8;i++)
	{
		printf("\n------------------------------For N = %d -----------------------------------------\n\n",N_arr[i]);

		for(j=0;j<6;j++)
		{

			
			char a[(unsigned)N_arr[i] * 1024];

			// Writing case :

			start1 = clock();
			for(k=0;k<sizeof(a);k=k+K_arr[j])
			{
				for(l=k;l<k+K_arr[j];l++)
				{
					
					if(l < sizeof(a))
					{
						a[l] = '1';
					}

				}			

			}
			start1 = clock() - start1;
			double time_taken = ((double)start1)/CLOCKS_PER_SEC; // in seconds
			double speed1 = ((double)N_arr[i])/((double)time_taken);
			speed1 = speed1/10.0;
			printf("Writing speed (%d , %d) is : %f MB/sec\n",N_arr[i],K_arr[j],speed1);


			// Reading case :

			start2 = clock();
			for(k=0;k<sizeof(a);k=k+K_arr[j])
			{	
				for(l=k;l<k+K_arr[j];l++)
				{
					if(l < sizeof(a))
						temp = a[l];
				}	

			}

			start2 = clock() - start2;
			double time_taken2 = ((double)start2)/CLOCKS_PER_SEC; // in seconds
			double speed2 = ((double)N_arr[i])/((double)time_taken2);
			speed2 = speed2/15.0;
			printf("Reading speed (%d , %d) is : %f MB/sec\n\n",N_arr[i],K_arr[j],speed2);

		}
		
	}

	

	return;
}
