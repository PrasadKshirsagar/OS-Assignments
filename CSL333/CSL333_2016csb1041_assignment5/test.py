# test file for simulate module

import sys
from simulate import simulate

# CL1
file_name = sys.argv[1]
theFile = open(file_name, "r")
ref_addresses = []
for val in theFile.read().split():
	ref_addresses.append(int(val))
theFile.close()


# scanning required arguments from user
bits = input("Please enter Size of virtual addresses in bits : ")
ram = input("Please enter RAM size in MB : ")
psize = input("Please enter Page size in kB : ")
algo = input("Please enter Choice of page replacement algorithm ex; FIFO or MFU or LFU : ")

print('===========================================================================\n')
simulate(int(bits),int(psize),int(ram),algo,ref_addresses)


