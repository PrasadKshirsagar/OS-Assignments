import java.io.*;
import java.lang.*;
import java.util.*;


public class DeadlockDetect
{
	public static void main(String[] args)
	{	
		String csvFile = args[0];
		BufferedReader br = null;
		BufferedReader br2 = null;
		String line = "";
		String cvsSplitBy = ",";

		int num_of_processes = 0;
		int num_of_resources = 0;
		int[] W;

		int cot = 0;
		try 
		{ 
			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) 
			{

				// use comma as separator
				String[] country = line.split(cvsSplitBy);
				if(cot == 0)
				{
					num_of_resources = country.length/3;
				}
				cot++;

			}
                
			num_of_processes = cot;
		}            	

			
		
		catch (Exception e) 
		{ 
			e.printStackTrace(); 
		} 


		// Pre-processing :
		int[][] P = new int[num_of_processes][num_of_resources];
		int[][] Q = new int[num_of_processes][num_of_resources];
		W = new int[num_of_resources];
		for(int k=0;k<num_of_resources;k++)
		{
			W[k] = 0;
		}

		int cot2=0;
		try 
		{ 
			br2 = new BufferedReader(new FileReader(csvFile));
			while ((line = br2.readLine()) != null)
			{
				// use comma as separator

				String[] country2 = line.split(cvsSplitBy);
				if(cot2 == 0)
				{
					for(int k=2*num_of_resources;k<country2.length;k++)
					{
						W[k - 2*num_of_resources] = Integer.parseInt(country2[k]);			
					}
				}	
				for(int k=0;k<num_of_resources;k++)
				{
					P[cot2][k] = Integer.parseInt(country2[k]);
				}
				for(int k=num_of_resources;k<2*num_of_resources;k++)
				{
					Q[cot2][k-num_of_resources] = Integer.parseInt(country2[k]);
				}

				cot2++;
				}
               
			}

		catch (Exception e) 
		{ 
			e.printStackTrace(); 
		} 


		int mark_table[] = new int[num_of_processes];
		for(int i=0;i<num_of_processes;i++)
		{
		 	if(check_for_all_zeroes(P[i],num_of_resources))
		 	{
		 		mark_table[i] = 1;
		 	}
		 	else
		 	{
		 		mark_table[i] = 0;
		 	}
		 	
		}


		int i=0;
		while(i<num_of_processes)
		{
		 	if(mark_table[i] == 0 && check_less_than(Q[i],W,num_of_resources))
		 	{
		 		W = adding_two_arrays(W,P[i],num_of_resources);
		 		mark_table[i] = 1;
		 		i = 0;
		 	} 
		 	else
		 	{
		 		i++;
		 	}
		}

		int count = 0;
		for(int j=0;j<num_of_processes;j++)
		{
		 	if(mark_table[j] == 1)
		 	{
		 		count++;
		 	}
		}

		// if deadlock does not happen :
		if(count == num_of_processes)
		{
		 	System.out.println("System state: Not Deadlocked.");
		}


		// if deadlock happens :
		else
		{
		 	System.out.println("System state: Deadlocked.");
		 	System.out.println("--------------------------------------- ");
		 	System.out.println("Deadlocked processes are : ");
		 	HashSet<Integer> hs = new HashSet<Integer>();
		 	List<Integer> list = new ArrayList<>();
		 	for(int j=0;j<num_of_processes;j++)
		 	{
		 		if(mark_table[j] == 0)
		 		{
		 			System.out.println("P"+ (j+1));
		 			list.add(j);
		 		}
		 	}
		 	System.out.println("--------------------------------------- ");
		 	System.out.println("Deadlocked resources are : ");
		 	for(int j=0;j<list.size();j++)
		 	{
		 		for(int k = 0;k<num_of_resources;k++)
		 		{
		 			if((Q[list.get(j)][k] > W[k]) && !hs.contains(k+1))
		 			{
		 				hs.add(k+1);
		 			}
		 		}
		 	}

		 	Iterator value = hs.iterator();
		 	while (value.hasNext()) 
		 	{ 
            	System.out.println("R"+(value.next())); 
         	} 
        }	 	



	}	


	// method to check if all elements of vector are zeroes
	public static Boolean check_for_all_zeroes(int[] A, int n)
	{
		Boolean f = true;
		for(int i=0;i<n;i++)
		{
			if(A[i] != 0)
			{
				f = false;
				break;
			}
		}
		return f;
	}


	// method to add two vectors of same size
	public static int[] adding_two_arrays(int[] A, int[] B, int n)
	{
		int[] C = new int[n];
		for(int i=0;i<n;i++)
		{
			C[i] = A[i] + B[i];
		}
		return C;
	}


	// method to check if a vector is less than other vector
	public static Boolean check_less_than(int[] A, int[] B, int n)
	{
		Boolean f = true;
		for(int i=0;i<n;i++)
		{
			if(A[i] > B[i])
			{
				f = false;
				break;
			}
		}
		return f;
	}

}	
