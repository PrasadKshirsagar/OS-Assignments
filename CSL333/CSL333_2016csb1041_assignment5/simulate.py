import math
import operator
import queue


# some global dictionaries required in the code :
address_count = {}
forward_page_table = {}
inverted_page_table = {}
forward_time_stamp = {}
reverse_time_stamp = {}



# simulate module to simulate virtual memory
def simulate(virtual_address_size, page_size, ram_size, page_rep_algo, ref_addresses):
	tm = 0

	page_size = int(page_size * 1024)

	# get offset size
	offset_size = math.log(page_size,2.0)

	v_add_size = virtual_address_size - offset_size

	# get number of pages 
	number_of_pages =  int(2 ** v_add_size)     

	ram_size = int(ram_size * 1024 * 1024)    
	total_physical_size = math.log(ram_size,2.0)

	m_add_size =  total_physical_size - offset_size

	# get number of frames
	number_of_frames = math.ceil(2 ** m_add_size)     


	n = len(ref_addresses)
	page_fault_list = [0 for x in range(n)]
	L = queue.Queue(maxsize=number_of_frames) 

	# for each address :
	for i in range(0,n):

		# tm is for time-stamp
		tm = tm + 1

		add_binary = bin(ref_addresses[i])
		page_num_binary = bin((ref_addresses[i]) >> int(offset_size))[2:]
		page_num_hex = hex(int(page_num_binary, 2))
		page_num_int = int(page_num_hex,16)

		offset_binary1 = bin(ref_addresses[i])[-1 * int(offset_size):]
		offset_hex = hex(int(offset_binary1, 2))
		offset_int = int(offset_binary1, 2)

		# if requested page is in memory, No page fault :
		if(page_num_int in forward_page_table and (forward_page_table[page_num_int] != -1)):
			address_count[page_num_int] = address_count[page_num_int] + 1

			# printing output :
			print('Given address : '+ str(ref_addresses[i]))
			print('Page number : ' + str(page_num_int) +' & Offset : ' + str(offset_int))
			print('Frame number : '+ str(forward_page_table[page_num_int])+' & Offset : '+ str(offset_int))
			print('Page fault status : NO')
			print('---------------------------------------------------------------')
			continue


		# we need to vacate some place (since no frame is vacant)
		# page fault will occur 
		if(len(inverted_page_table) == number_of_frames):
			page_fault_list[i] = 1

			# For FIFO :  (using queue L)	 
			if(page_rep_algo == 'FIFO'):
				front = L.get()
				t1 = inverted_page_table[front]
				forward_page_table[t1] = -1

				# updating inverted page table with new element
				inverted_page_table[front] = page_num_int
				forward_page_table[page_num_int] = front
				L.put(front)

				# printing output :
				print('Given address : '+ str(ref_addresses[i]))
				print('Page number : ' + str(page_num_int) +' & Offset : ' + str(offset_int))
				print('Frame number : '+ str(front)+' & Offset : '+ str(offset_int))
				print('Page fault status : YES')
				print('---------------------------------------------------------------')
				if(page_num_int in address_count):
					address_count[page_num_int] = address_count[page_num_int] + 1
				else :
					address_count[page_num_int] = 1	

			# For LFU :		
			elif(page_rep_algo == 'LFU'):

				temp = -1
				temp_=9223372036854775807  # this big number is just used for initial comparison     

				# if there are multiple elements with same frequency, FIFO rule was applied to choose target
				for key, value in address_count.items():
					if(key in forward_page_table and forward_page_table[key] != -1):
						if(value < temp_):
							temp_ = value
							temp = key


				supposed_min_val = address_count[temp]
				to_change = forward_page_table[temp]
				supposed_min_stamp = 9223372036854775807   
				for key, value in address_count.items():
					tmpp = forward_page_table[key]
					if(tmpp !=-1 and value <= supposed_min_val and forward_time_stamp[tmpp] < supposed_min_stamp):
						to_change = tmpp
						supposed_min_stamp = forward_time_stamp[tmpp]

				t2 = inverted_page_table[to_change]
				forward_page_table[t2] = -1
				address_count[t2] = 0

				# updating inverted page table with new element
				inverted_page_table[to_change] = page_num_int
				forward_page_table[page_num_int] = to_change
				forward_time_stamp[to_change] = tm
				reverse_time_stamp[tm] = to_change
				
				# printing output :
				print('Given address : '+ str(ref_addresses[i]))
				print('Page number : ' + str(page_num_int) +' & Offset : ' + str(offset_int))
				print('Frame number : '+ str(to_change)+' & Offset : '+ str(offset_int))
				print('Page fault status : YES')
				print('---------------------------------------------------------------')
				if(page_num_int in address_count):
					address_count[page_num_int] = address_count[page_num_int] + 1
				else :
					address_count[page_num_int] = 1	


			# For MFU :			
			elif(page_rep_algo == 'MFU'):
				temp2 = -1
				temp2_ = -1

				# if there are multiple elements with same frequency, FIFO rule was applied to choose target
				for key, value in address_count.items():
					if(key in forward_page_table and forward_page_table[key] != -1):
						if(value > temp2_):
							temp2_ = value
							temp2 = key


				supposed_max_val = address_count[temp2]
				to_change2 = forward_page_table[temp2]
				supposed_max_stamp = 9223372036854775807
				for key, value in address_count.items():
					tmpp2 = forward_page_table[key]
					if(tmpp2 != -1 and value >= supposed_max_val and forward_time_stamp[tmpp2] < supposed_max_stamp):
						to_change2 = tmpp2
						supposed_max_stamp = forward_time_stamp[tmpp2]
				
				t3 = inverted_page_table[to_change2]
				forward_page_table[t3] = -1
				address_count[t3] = 0

				# updating inverted page table with new element
				inverted_page_table[to_change2] = page_num_int
				forward_page_table[page_num_int] = to_change2
				forward_time_stamp[to_change2] = tm
				reverse_time_stamp[tm] = to_change2

				# printing output :
				print('Given address : '+ str(ref_addresses[i]))
				print('Page number : ' + str(page_num_int) +' & Offset : ' + str(offset_int))
				print('Frame number : '+ str(to_change2)+' & Offset : '+ str(offset_int))
				print('Page fault status : YES')
				print('---------------------------------------------------------------')
				if(page_num_int in address_count):
					address_count[page_num_int] = address_count[page_num_int] + 1
				else :
					address_count[page_num_int] = 1	

		else :
			
			# in beginning page fault will occur frequently as folows :
			page_fault_list[i] = 1		
			for j in range(0,int(number_of_frames)):
				if(not j in inverted_page_table):
					inverted_page_table[j] = page_num_int
					L.put(j)
					forward_page_table[page_num_int] = j
					forward_time_stamp[j] = tm   # mapping frame to a time stamp
					reverse_time_stamp[tm] = j	# its reverse mapping 
					
					# printing output :
					print('Given address : '+ str(ref_addresses[i]))
					print('Page number : ' + str(page_num_int) +' & Offset : ' + str(offset_int))
					print('Frame number : '+ str(j)+' & Offset : '+ str(offset_int))
					print('Page fault status : YES')
					print('---------------------------------------------------------------')
					if(page_num_int in address_count):
						address_count[page_num_int] = address_count[page_num_int] + 1
					else :
						address_count[page_num_int] = 1	
					break


	# count total page faults 				
	count=0				
	for i in range(0,n):
		if(page_fault_list[i] == 1):
			count = count + 1

	# page fault rate 		
	print('====> Page Fault Rate is : '+ str(count/n))		




























