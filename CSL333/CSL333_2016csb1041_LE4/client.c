#include <sys/ipc.h> 
#include <sys/shm.h> 
#include <stdio.h> 
#include<string.h>
#include <unistd.h>
#include<stdlib.h>
#include <time.h>

void client_function();

// Driver main function : 
int main() 
{ 	

    printf("You can press Ctrl-C anytime you want to exit.\n");	
	client_function();
    return 0; 
} 


// Client function to do given job :
void client_function()
{
	char *temp;

	// ftok to generate unique key 
	key_t key = ftok("shmfile",65); 

	// shmget returns an identifier in shmid 
    int shmid = shmget(key,1024,0666|IPC_CREAT); 
    char *ss = (char*) malloc(sizeof(char) * 11);;
    int k=0;
 
    // shmat to attach to shared memory 		
    char *str = (char*) shmat(shmid,(void*)0,0);
    
    // shared variable a :	
    strcpy(str,"a");  
             		
    		
    // while loop waits only for user response :
    while(1)		
    	{

    		// Calculate time :
    		time_t t = time(NULL);
			struct tm tm = *localtime(&t);
			printf("[ %d-%d-%d %d:%d:%d ] ", tm.tm_year + 1900, tm.tm_mon + 1, tm.tm_mday, tm.tm_hour, tm.tm_min, tm.tm_sec);

    		printf("Currency code (e.g. USD, GBP, etc.): ");
    		scanf("%s",ss);
    		
    		strcpy(str,ss);
    		
			// Calculate time :
    		time_t t1 = time(NULL);
			struct tm tm1 = *localtime(&t1);
			printf("[ %d-%d-%d %d:%d:%d ] ", tm1.tm_year + 1900, tm1.tm_mon + 1, tm1.tm_mday, tm1.tm_hour, tm1.tm_min, tm1.tm_sec);

    		printf("Waiting for server’s response...\n");

    		
			//waiting for server :
    		while(str[0]>'9' || str[0]<'0' )			 
  			{
  				sleep(1);  
  			}
    		
    		fflush(stdin); 
   			time_t t2 = time(NULL);
			struct tm tm2 = *localtime(&t2);
			printf("[ %d-%d-%d %d:%d:%d ] ", tm2.tm_year + 1900, tm2.tm_mon + 1, tm2.tm_mday, tm2.tm_hour, tm2.tm_min, tm2.tm_sec);

   			printf("Conversion rate wrt INR: : %s\n\n",str);
   			
    	
    	}








}
