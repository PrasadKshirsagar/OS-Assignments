Name : Prasad Kshirsagar
Entry No : 2016csb1041
Course : CSL333
Topic : assignment 4



----------------------------------------------------------------------------------------------------------------------------
HOW TO COMPILE
----------------------------------------------------------------------------------------------------------------------------

$ cd path/to/my/folder

$ gcc mem_test.c mylib.c


NOTE : mem_test.c  ==> test file
       mylib.c     ==> functions file


----------------------------------------------------------------------------------------------------------------------------
HOW TO RUN
----------------------------------------------------------------------------------------------------------------------------

$ ./a.out


---------------------------------------------------------------------------------------------------------------------------
What does this program do
---------------------------------------------------------------------------------------------------------------------------

- The aim of this program is to design our own memory allocator.
- We create our own three functions csl333_malloc, csl333_free and csl333_realloc by using sbrk subroutine as tool.
- Various test cases were covered during this implementation as done by real malloc, free & realloc functions.
- Segmentation fault will the output if any unautorized access happens.


---------------------------------------------------------------------------------------------------------------------------
Logic behind the program
---------------------------------------------------------------------------------------------------------------------------

- Main alogorithm used to check for first free block is 'first fit' algorithm.  
- if the requested size cannot be allocated, then NULL will be returned. 
- sbrk is used as internal subroutine as suggested in the problem statement.


---------------------------------------------------------------------------------------------------------------------------
Assumptions
---------------------------------------------------------------------------------------------------------------------------

- coalesce of free bolcks was not implemented due to change in memory address of two consecutive free blocks. 

---------------------------------------------------------------------------------------------------------------------------


