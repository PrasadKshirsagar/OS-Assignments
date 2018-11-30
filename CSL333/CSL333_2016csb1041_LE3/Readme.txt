Name : Prasad Kshirsagar
Entry No : 2016csb1041
Course : CSL333
Topic : Lab Exam 3


----------------------------------------------------------------------------------------------------------------------------
HOW TO COMPILE 
----------------------------------------------------------------------------------------------------------------------------

$ cd path/to/my/folder

$ javac assign.java


----------------------------------------------------------------------------------------------------------------------------
HOW TO RUN
----------------------------------------------------------------------------------------------------------------------------

$ java assign CL1

==> CL1 : initial head position


---------------------------------------------------------------------------------------------------------------------------
Example Run :
---------------------------------------------------------------------------------------------------------------------------

$ java assign 3500

======> OUTPUT 

Total amout of head movement for SSTF is : 8475
Total amout of head movement for C-Look is : 9951
Total amout of head movement for C-Scan is : 9993


---------------------------------------------------------------------------------------------------------------------------
What does this program do :
---------------------------------------------------------------------------------------------------------------------------

- The aim of this program is to implement the following disk-scheduling algorithms.
- In this we implement 3 algorithms as follows :
  1. SSTF
  2. C-SCAN
  3. C-LOOK
- Initially 1000 cylinder numbers are generated randomly.
- Initial head position is passed as command line argument. 


---------------------------------------------------------------------------------------------------------------------------
Logic behind the program :
---------------------------------------------------------------------------------------------------------------------------

- In SSTF, Basic idea is the tracks which are closer to current disk head position should be serviced first in order to minimise the seek operations.  

- In C-SCAN, disk arm instead of reversing its direction goes to the other end of the disk and starts servicing the requests from there. So, the disk arm moves in a circular fashion and this algorithm is also similar to SCAN algorithm and hence it is known as C-SCAN (Circular SCAN).

- In C-LOOK, the disk arm inspite of going to the end goes only to the last request to be serviced in front of the head and then from there goes to the other end’s last request. Thus, it also prevents the extra delay which occurred due to unnecessary traversal to the end of the disk.



