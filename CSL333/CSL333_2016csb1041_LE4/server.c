#include <sys/ipc.h> 
#include <sys/shm.h> 
#include <stdio.h> 
#include<stdlib.h>
#include<string.h>
#include <unistd.h>
#include <time.h>
  
void server_function();


// function to read file :
char* read_(char* fi)
{

    FILE *file;
    char* res = (char*) malloc(1000 * sizeof(char));
    file = fopen(fi, "r");
    int c;
    int ct=0;

    while ((c = fgetc(file)) != EOF)
    {
       
      if((char)c == ':')
      {
        ct++;
      }
      
      if(ct == 6 && ((char)c == '.' || ((char)c >= '0' && (char)c <='9') ) )
      {
        int len = strlen(res);
        res[len++] = (char)c; 
        res[len] = '\0';

      }
    }
return res;

}


// function to get that value :
char* get_value()			
{
	  int k=0;
	  char *tmp;
	  char *line=NULL;
	  size_t l =0;
	  FILE *outfile; 
	  outfile = fopen ("input.json", "r+"); 
	  getline (&line,&l,outfile);
	  char* token = strtok(line, ":}{"); 
	  
	  // Tokenizing :
	  while (token != NULL) 
	  	{ 
			token = strtok(NULL, ":}{"); 
	 		if(k==1)
	  		tmp = token;
	  		k++;
		} 
	
	// Return value
  	return tmp;	
}  
  

// Driver main function :
int main() 
{ 

	printf("You can press Ctrl-C anytime you want to exit. \n");	
    server_function();
    return 0; 
} 


// Server function to do given job :
void server_function()
{
	char *temp;
	char *buffer = malloc(sizeof(char) * 200);
    char *temp11 = malloc(sizeof(char) * 200);

	// ftok to generate unique key
	key_t key = ftok("shmfile",65); 

	// shmget returns an identifier in shmid 
    int shmid = shmget(key,1024,0666|IPC_CREAT); 
  
	// shmat to attach to shared memory
    char *str = (char*) shmat(shmid,(void*)0,0); 
  
  
    
    // while loop waits only for user response :
    while(1)		
    	{  	
  			// Calculate time :
    		time_t t = time(NULL);
			struct tm tm = *localtime(&t);
			printf("[ %d-%d-%d %d:%d:%d ] ", tm.tm_year + 1900, tm.tm_mon + 1, tm.tm_mday, tm.tm_hour, tm.tm_min, tm.tm_sec);

  			printf("Waiting for next request...\n");
			
			// intializing request string :	
  			strcpy(buffer," wget -q 'http://free.currencyconverterapi.com/api/v5/convert?q=INR_");   
  			
			//waiting for client :
 			while((temp = (char*) shmat(shmid,(void*)0,0))[0]<'A' || (temp = (char*) shmat(shmid,(void*)0,0))[0]>'Z') 
  			{
  			 	sleep(1);
  			}  
   			
   			str = (char*) shmat(shmid,(void*)0,0);
   	  	
   	  		// Calculate time :
    			time_t t1 = time(NULL);
			struct tm tm1 = *localtime(&t1);
			printf("[ %d-%d-%d %d:%d:%d ] ", tm1.tm_year + 1900, tm1.tm_mon + 1, tm1.tm_mday, tm1.tm_hour, tm1.tm_min, tm1.tm_sec);

			printf("Request received for : %s\n",str);
   			
			time_t t2 = time(NULL);
			struct tm tm2 = *localtime(&t2);
			printf("[ %d-%d-%d %d:%d:%d ] ", tm2.tm_year + 1900, tm2.tm_mon + 1, tm2.tm_mday, tm2.tm_hour, tm2.tm_min, tm2.tm_sec);

   			printf("Contacting Web service...\n");
   			char s1[10]="INR_";
   			strcat(s1,str);
   			strcat(s1," conversion is ");
   				
   			strcat(buffer,str);
   			strcat(buffer,"&compact=y' -O input.json");
   			
			//downloading the json file using system call :
   			system(buffer);			
   			
   			char *no = get_value();

   			time_t t3 = time(NULL);
			struct tm tm3 = *localtime(&t3);
			printf("[ %d-%d-%d %d:%d:%d ] ", tm3.tm_year + 1900, tm3.tm_mon + 1, tm3.tm_mday, tm3.tm_hour, tm3.tm_min, tm3.tm_sec);

   			printf("%s %s\n\n",s1,no);
   			strcpy(str,no);

  }
  

}

