Name : Prasad Kshirsagar
Entry No : 2016csb1041
Course : CSL333
Topic : Lab Exam 4


----------------------------------------------------------------------------------------------------------------------------
HOW TO COMPILE & RUN
----------------------------------------------------------------------------------------------------------------------------

$ cd path/to/my/folder

=====> For Client :

	$ gcc client.c -o client
	$ ./client

=====> For Server :

	$ gcc server.c -o server
	$ ./server


---------------------------------------------------------------------------------------------------------------------------
What does this program do :
---------------------------------------------------------------------------------------------------------------------------

- The aim of this program is to implement the client-server application.

- Client program:
	1.It is an interactive program that keeps asking the user to supply a currency code (e.g. INR, USD, GBP, etc.) for which it fetches the conversion rate w.r.t INR. That is, if the user entered USD, then this program will report how many USD are there in one INR.
	2.It talks to the server program to fetch the conversion rate of the input currency.
When the user presses Ctrl-C then it exits.
	3. Client talks to server program via shared memory based IPC.

- Server program:
	1.Awaits for requests from the client program via shared memory IPC.
	2.Gets the currency conversion by making an HTP GET request to external service at: http://free.currencyconverterapi.com/api/v5/convert?q=EUR_USD&compact=y
	3.The first query parameter (q) of this service takes conversion request in the format: <From currency>_<To currency>. For example, to get how many USD are there in one INR then you will give the value of q as INR_USD.


---------------------------------------------------------------------------------------------------------------------------
Logic behind the program :
---------------------------------------------------------------------------------------------------------------------------

- We used Inter Process Communication through shared memory concept.
- Inter Process Communication through shared memory is a concept where two or more process can access the common memory. And communication is done via this shared memory where changes made by one process can be viewed by anther process.
- Wget was used to download 'input.json' file.

