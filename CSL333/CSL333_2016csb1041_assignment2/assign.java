import dev.*;
import java.io.*;
import java.lang.*;
import java.util.*;
import java.util.Queue; 
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class assign
{
	public static long n = 0;
	public Set<Long> uniqueNumGenerated = new HashSet<Long>();
	public static long a[]; 
	public int endLoop = 7;
	public long nxtNumber = -1;
	public boolean done = false;
	public long j = 0;
	public long prev = -1;
	public static Device d;

	
	public static void main(String[] args)throws InterruptedException 
	{ 
		// command line arguments :
		long start = System.currentTimeMillis();
		int num_of_threads = 1;
		n = Long.parseLong(args[0]);
		long comp_delay = Long.parseLong(args[3]);
		long bot_delay = Long.parseLong(args[4]);
     
     	boolean ch = false;
		if(args[2].equals("real") == true)
		{
			RealDevice.DeviceConfig dev_conf = new RealDevice.DeviceConfig(Long.valueOf(comp_delay),Long.valueOf(bot_delay),Long.valueOf(1));
			d = new RealDevice(dev_conf);
			ch = true;
			
		}
		else
		{

			d = new UnrealDevice(1);
		}


		assign obj1 = new assign();
		obj1.a = new long[20 * (int)n];

		Thread[] t = new Thread[(int)num_of_threads];
		for(int i=0;i<20*n;i++)
		{
			obj1.a[i] = -1;
		}
		obj1.a[0] = 2;
		long loop = num_of_threads;
		globalized_class object = new globalized_class(obj1,d);
		
		for(long i=0;i<loop;i++)
		{
				
			t[(int)i] = new threads(object,obj1,d);
        	t[(int)i].start();

		}


		for (int i = 0; i < loop; i++)
		{
        	t[(int)i].join();
    	}


    	long count = 0;
    	long ans = 0;
    	for (int i = 0; i < 20*n; i++)
		{
        	if(obj1.a[(int)(i)] != -1 && obj1.a[(int)(i)] != -2)
        	{
        		count++;
        	}
        	if(count == n)
        	{
        		//System.out.println("Target number is: "+ obj1.a[(int)(i)]);
        		ans = obj1.a[(int)(i)];
        		break;
        	}
    	}
		
		long end = System.currentTimeMillis();

    	DecimalFormat formatter = new DecimalFormat("#0.00000");


		System.out.println("-----------------------------------");
    	System.out.println("RESULTS SUMMARY");
    	System.out.println("-----------------------------------");
    	System.out.println("");
    	System.out.println("Target count (n)............: "+ obj1.n);
    	System.out.println("Number of threads...........: "+ Integer.valueOf(args[1]));
    	System.out.println("Used real device............: "+ ch);
    	System.out.println("Time taken..................: "+ formatter.format((end - start) / 1500d));
    	System.out.println("Resulting number............: "+ ans);
    	System.out.println("Device invoked (approx).....: "+ d.age());


    	
		//System.out.println("Execution time is " + formatter.format((end - start) / 1500d) + " seconds");

	}	

}	
