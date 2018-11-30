import java.io.*;
import java.lang.*;
import java.util.*;
import java.util.Arrays;


public class assign
{
	public static void main(String[] args)
	{	

		int head_int = Integer.parseInt(args[0]);
		int[] list = new int[1000];
		for (int i=0; i<1000; i++)
		{
			// request array (random numbers between 0 - 4999)
        	int n = (int)(Math.random()*4998);
        	list[i] = n;

    	}

    	// printing output :
    	SSTF(list, head_int);
		System.out.println("Total amout of head movement for C-Look is : " +clook(list,head_int,1000));
		System.out.println("Total amout of head movement for C-Scan is : " + cscan(list,head_int,1000));
		
		
	}


	// C-LOOK function : 
	public static int clook(int queue[],int head, int n)                                          
    { 
    	// sorting array
    	Arrays.sort(queue);
    	int i=0,ans=0,temp=-1;
    	ans = head - queue[0];

    	// finding first bigger number than head
    	for(i=0;i<n;i++)
    	{
    		if(queue[i] < head)
    			continue;

    		else
    		{
    			temp = queue[i];
    			break;
    		}
    	}

    	// adding (final - that found number)
    	ans += (queue[n-1] - temp);
    	// adding that gap
    	ans += (queue[n-1] - queue[0]);
    	
    	return ans ;

    	
    } 

    // C-SCAN function : 
	public static int cscan(int queue[],int head, int n)                                          
    { 
    	
    	// sorting array
    	Arrays.sort(queue);
    	int i=0,ans=0,temp=-1;
    	ans = head - 0;

    	// finding first bigger number than head
    	for(i=0;i<n;i++)
    	{
    		if(queue[i] < head)
    			continue;

    		else
    		{
    			temp = queue[i];
    			break;
    		}
    	}

    	// adding (final - that found number)
    	ans += (4999 - temp);
    	// adding that gap
    	ans += 4999;
    	
    	return ans ;

    	
    } 


    // Calculates difference of each track number with the head position 
	public static void calculateDifference(int queue[], int head, storage diff[])                                          
    { 
        for (int i = 0; i < diff.length; i++) 
            diff[i].distance = Math.abs(queue[i] - head); 
    } 


    // find unaccessed track which is at minimum distance from head 
     public static int findMin(storage diff[]) 
    { 
        int index = -1, minimum = Integer.MAX_VALUE; 
        for (int i = 0; i < diff.length; i++) { 
            if (!diff[i].accessed && minimum > diff[i].distance) { 
                minimum = diff[i].distance; 
                index = i; 
            } 
        } 
        return index; 
    } 

    // SSTF function : 
    public static void SSTF(int request[],int head) 
                                                       
    { 
        if (request.length == 0) 
            return; 
                 
        storage diff[] = new storage[request.length];  

        // initialize array 
        for (int i = 0; i < diff.length; i++)  
        diff[i] = new storage(); 

    	// count total number of seek operation 
        int seek_count = 0;  
        int[] seek_sequence = new int[request.length + 1];  
          
        for (int i = 0; i < request.length; i++) { 
              
            seek_sequence[i] = head; 
            calculateDifference(request, head, diff); 
            int index = findMin(diff); 
            diff[index].accessed = true; 

            // increase the total count 
            seek_count += diff[index].distance;  
            head = request[index];  
        } 
        
        seek_sequence[seek_sequence.length - 1] = head;  
          
        System.out.println("Total amout of head movement for SSTF is : " + seek_count); 
                                                      
    } 

}	

class storage
{         
    int distance = 0;   
    boolean accessed = false;  
} 
  
