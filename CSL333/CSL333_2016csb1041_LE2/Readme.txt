Name : Prasad Kshirsagar
Entry No : 2016csb1041
Course : CSL333
Topic : LAB EXAM 2


----------------------------------------------------------------------------------------------------------------------------
HOW TO COMPILE
----------------------------------------------------------------------------------------------------------------------------

$ cd path/to/my/folder

$ gcc lab.c


----------------------------------------------------------------------------------------------------------------------------
HOW TO RUN
----------------------------------------------------------------------------------------------------------------------------

$ ./a.out


---------------------------------------------------------------------------------------------------------------------------
What does this program do :
---------------------------------------------------------------------------------------------------------------------------

- The aim of this program is to compare read/write performance on large sized array.
- We checked for all 48 combinations of pairs & compared their respective speeds.
- We also allocated memory on heap during the execution for large sized array.
- N varies as 10, 20, 40, 80, 160, 320, 640, 1280 MB.
- For each case of N above, K is varied as: 10, 20, 30, 40, 50, 60 bytes.


---------------------------------------------------------------------------------------------------------------------------
Logic behind the program :
---------------------------------------------------------------------------------------------------------------------------

- Main alogorithm is nothing but using two for loops for respective combination of n & k.  
- Inside for each pair reading (accessing a element) & writing (writing dummy '1') to array position.
- unsigned char was used for to get a byte array.


---------------------------------------------------------------------------------------------------------------------------
Conclusions (Explanation) :
---------------------------------------------------------------------------------------------------------------------------

- From performing a lot of runs of the given program, we conclude that maximum performance is observed at K=60 but N may vary.
- This is due to we attempt(read/write) more bytes (60) in each iteration, thus time taken for the loop decreases and thus,
  effective speed is maximum at Kmax i.e. K = 60.  

--------------------------------------------------------------------------------------------------------------------------

