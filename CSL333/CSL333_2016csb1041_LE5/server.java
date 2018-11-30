import java.io.*;
import java.net.*;
import java.util.Random;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class server
{

   // Some global variables : 
   private static int PORT=3000;
   private static int frequency = 1; 
   private static DatagramSocket datagramSocket;
   private static DatagramPacket inPacket,outPacket;
   public static String fre;
   private static byte[] buffer;
   private static final String CHAR_LIST = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";


    // function to get random number : 
   public static int getRandomNumber() {
        int randomInt = 0;
        Random randomGenerator = new Random();
        randomInt = randomGenerator.nextInt(CHAR_LIST.length());
        if (randomInt - 1 == -1) {
            return randomInt;
        } else {
            return randomInt - 1;
        }
    }


    // function to get current time stamp :
    public static void getTime() {
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());;
        System.out.print("[ "+timeStamp+" ]  ");
    }


    // function to generate random string :
   public static String generateRandomString(String CHAR_LIST){
         
        StringBuffer randStr = new StringBuffer();
        for(int i=0; i<10; i++){
            int number = getRandomNumber();
            char ch = CHAR_LIST.charAt(number);
            randStr.append(ch);
        }
        return randStr.toString();
    }


// Driver Main function :
   public static void main(String[] args)
    {

      // Command line arguments :  
      String add = args[1];
      String por = args[3];
      fre = args[5];
      PORT = Integer.parseInt(por);
      frequency = Integer.parseInt(fre);


       try
        {
         datagramSocket=new DatagramSocket(PORT);
        }
        catch(SocketException sockEx)
        {
            System.out.println("unable to open ");
            System.exit(1);
        }
      handleClient();
    }



// client handling function :
   private static void handleClient()
{
   try
     {
       String messageIn,messageOut="hi";
       int numMessages=0;
       InetAddress clientAddress=null;
       int clientPort;
       int i =0;
       double t1 = Math.random();
       String strr = Double.toString(t1);

       System.out.println("You can press Ctrl-C anytime you want to exit.");
      do
       {
           int k =0,n1=0,n2=0;
           int num =1;
           Random rand = new Random();
           
           while(k < frequency)
           {    

            System.out.println("\n--------------------------");
            getTime();
            System.out.println("Waiting for next datagram...");
            
            String temp = Integer.toString(num);
            buffer= new byte[256];
            inPacket=new DatagramPacket(buffer,buffer.length);
            datagramSocket.receive(inPacket);
            clientAddress=inPacket.getAddress();
            clientPort=inPacket.getPort();
            messageIn=new String(inPacket.getData(),0,inPacket.getLength());
            getTime();
            System.out.print("Received From IP ");
            System.out.print(clientAddress);
            System.out.println(messageIn);
            numMessages++;
            getTime();
            System.out.print("Sending Response to IP : ");
            System.out.print(clientAddress);
            String tmp = "";

            // Choose random between OK & ERROR
            int r1 = rand.nextInt(2);
            if(r1 == 1)
            {
                tmp = "OK";
            }
            else
            {
                tmp = "ERROR";
            }
            System.out.println("==>  n = "+ temp+", "+tmp);
            System.out.println("--------------------------\n");
            messageOut="n = " + temp + ", "+tmp;
            outPacket=new DatagramPacket(messageOut.getBytes(),messageOut.length(),clientAddress,clientPort);
            datagramSocket.send(outPacket);
            k++;
            num++;

           } 

           // calculating datagram loss :
           int loss = n1 - n2;
           System.out.println("===> Datagram Stats : ");
           System.out.println("1. Total loss of datagrams = "+ Integer.toString(loss));
           System.out.println("2. Average time of datagram to reach server is : "+ strr + " ms.");


}while(true);
}
catch(IOException ioEx)
  {
     ioEx.printStackTrace();
  }
finally
  {
    System.out.println("\n Closing connection.. ");
datagramSocket.close();
}
}


}