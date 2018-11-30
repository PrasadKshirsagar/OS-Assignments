import dev.*;
import java.io.*;
import java.lang.*;
import java.util.*;
import java.util.Queue; 

public class threads extends Thread
{
	
	public globalized_class obj;
	public assign obj1;	
	public Device d;

   public threads(globalized_class obj,assign obj1, Device d)
   {
   		this.obj = obj;
   		this.obj1 = obj1;
   		this.d = d;

  
   }

    public void run() 
    { 
         try
        { 
        	while (!obj1.done) 
		{
			obj.incrementCount();
			if(obj.base == -1 || obj.base == -2)
			{
				continue;
			}

            
            for (int i = 0; i < obj1.endLoop; i++) 
			{
				obj.incrementCount2(i);

				if (obj1.uniqueNumGenerated.size() >= obj1.n) 
				{
					obj1.done = true;
					break;
				}
			}
  
        }
    }

    catch (Exception e) 
        { 
            System.out.println ("Exception is caught"); 
        } 


    } 
	

}


