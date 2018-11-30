Name : Prasad Kshirsagar
Entry No : 2016csb1041
Course : CSL333
Topic : Lab Exam 5


----------------------------------------------------------------------------------------------------------------------------
HOW TO COMPILE & RUN
----------------------------------------------------------------------------------------------------------------------------

$ cd path/to/my/folder

=====> For Server :

	$ javac server.java 
	$ java server ip 127.0.0.1 -port 8080 -f 10


=====> For Client :

	$ javac client.java 
	$ java client ip 127.0.0.1 -port 8080 -f 10



---------------------------------------------------------------------------------------------------------------------------
What does this program do :
---------------------------------------------------------------------------------------------------------------------------

- The aim of this program is to implement the client-server application.

- Client program:
	1.It takes as CLI argument the following items:
		a) Frequency at which to send datagram messages to the server. E.g. 10/s
		b) IP address and port number of the server to which the datagrams need to be sent.

	2.It sends as payload the following three values (x, y and z):
		a) int n, float x; float y; String z;
		b) Here n is the client specific sequence no. of the datagram. Values of x, y and z can be randomly chosen.

	3. Prints the response from the server.

- Server program:
	1.On receipt of an incoming datagram the server program should extract the payload (described below), and returns the value computed by a function fn_server().

	2.Server is scalable, i.e., it should be able to handle large number of concurrent requests.
	
	3.Operation of function fn_server() is stateless.


---------------------------------------------------------------------------------------------------------------------------
Logic behind the program :
---------------------------------------------------------------------------------------------------------------------------

- We used distributed IPC using UDP sockets to implement this program.
- A datagram socket is a type of network socket which provides a connectionless point for sending or receiving data packets.[2] Each packet sent or received on a datagram socket is individually addressed and routed. Order and reliability are not guaranteed with datagram sockets, so multiple packets sent from one machine or process to another may arrive in any order or might not arrive at all. 
- Datagram loss & average time to reach server is calculated at the end.

