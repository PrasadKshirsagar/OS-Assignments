Name : Prasad Kshirsagar
Entry No : 2016csb1041
Topic : Lab assignment 2 
Language : Java


============================================================================================================================
1. What does this program do
============================================================================================================================
This program is basically fast unique number generator. It generates numbers using multipliers. With different bases we get differnt numbers. This program is multithreaded to get faster output. It uses set to distinguish a unique number from previous seen number. There are different implementations for real device & unreal device.


============================================================================================================================


----------------------------------------------------------------------------------------------------------------------------
HOW TO COMPILE 
----------------------------------------------------------------------------------------------------------------------------
$ cd path/to/my/folder

$ javac assign.java 


----------------------------------------------------------------------------------------------------------------------------
HOW TO RUN
----------------------------------------------------------------------------------------------------------------------------


NOTE : 5 command line arguments are required.
       1. The target count of unique numbers to be generated (i.e. n)
       2. The number of threads to use in the program
       3. Device type (i.e. real or unreal)
       4. Compute delay in ms.
       5. Device boot delay in ms.



Example Run:
===================================================================

$ java assign 20 3 real 1 1

$ java assign 100 10 unreal 2 1

====================================================================



---------------------------------------------------------------------------------------------------------------------------
About File Structure
---------------------------------------------------------------------------------------------------------------------------

- assign.java is the main java file.




