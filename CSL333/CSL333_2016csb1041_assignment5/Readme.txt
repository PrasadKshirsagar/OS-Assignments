Name : Prasad Kshirsagar
Entry No : 2016csb1041
Course : CSL333
Topic : Assignment 5


----------------------------------------------------------------------------------------------------------------------------
HOW TO COMPILE & RUN
----------------------------------------------------------------------------------------------------------------------------

$ cd path/to/my/folder

$ python3 test.py CL1

==> CL1 : data file containing reference addresses (ex; data.txt)


---------------------------------------------------------------------------------------------------------------------------
Example Run :
---------------------------------------------------------------------------------------------------------------------------

$ python3 test.py data.txt

Please enter Size of virtual addresses in bits : 32
Please enter RAM size in MB : 3
Please enter Page size in kB : 1024
Please enter Choice of page replacement algorithm ex; FIFO or MFU or LFU : MFU

 ======> OUTPUT 


---------------------------------------------------------------------------------------------------------------------------
What does this program do :
---------------------------------------------------------------------------------------------------------------------------

- The aim of this program is to write a module in Python which can simulate the virtual memory with demand paging.
- This module implements the paging mechanism using inverted page table.
- It exposes a function that has given structure.
- It takes virtual addresses in bits, RAM size in MB, Page size in kB, Choice of page replacement algorithm, and set of reference addresses   as input to the simulate module.
- 'test.py' is a test file to test this module.


---------------------------------------------------------------------------------------------------------------------------
Logic behind the program :
---------------------------------------------------------------------------------------------------------------------------

- Main alogorithm is to using inverted page table for demand paging.  
- Used python dictionaries for hashing purposes.
- Used queue for FIFO algorithm and time-stamp mechanism for MFU, LFU algorithms. 



