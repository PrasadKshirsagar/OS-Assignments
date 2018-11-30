import dev.*;
import java.io.*;
import java.lang.*;
import java.util.*;
import java.util.Queue; 



public class globalized_class
{

	public long j = 0;
	public long prev = -1;
	public long base = 2;
	public long temp = 0;

    public assign obj1;
    public Device d;

    public globalized_class(assign obj1, Device d)
   {
        this.obj1 = obj1;
        this.d = d;

    }    

	public synchronized void incrementCount() 
    {
    	
        	base = obj1.a[(int)j];
        	if(base != -1 && base != -2)
        	{
        		prev = prev + 1;
        		j = j+ 1;
        	}
            else if(base == -2)
            {
                j = j+ 1;
            }


    }




    public synchronized void incrementCount2(int i) 
    {    

    	 try
        { 
        
        	obj1.nxtNumber = obj1.d.f(base, i);
			if(obj1.uniqueNumGenerated.contains(obj1.nxtNumber) != true)
			{
				temp = (prev * obj1.endLoop) + i + 1;
        		//System.out.println(temp);
        		if(temp < 20*obj1.n)
        		{
        			obj1.a[(int)temp] = obj1.nxtNumber;
        			obj1.uniqueNumGenerated.add(obj1.nxtNumber);
        		}

			}
			else
			{
				temp = (prev * obj1.endLoop) + i + 1;
        		if(temp < 20*obj1.n)
        		{
        			obj1.a[(int)temp] = -2;
        		}

			}


		}

		catch (Exception e) 
        { 
            System.out.println ("Exception is caught"); 
        } 
        		   
    }



}	
