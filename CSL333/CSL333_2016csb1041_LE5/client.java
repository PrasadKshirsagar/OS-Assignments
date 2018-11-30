import java.io.*;
import java.net.*;
import java.util.*;
import java.util.Random;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;


public class client
{

  // Some global variables :   
  private static InetAddress host;
  private static int PORT=3000;
  private static int frequency = 1; 
  public static String fre;
  private static DatagramSocket datagramSocket;
  private static DatagramPacket inPacket,outPacket;
  private static byte[] buffer;
  private static final String CHAR_LIST = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";


  // function to get current time stamp :
  public static void getTime() {
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());;
        System.out.print("[ "+timeStamp+" ]  ");
    }


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
            host = InetAddress.getByName(add);
        }
        catch(UnknownHostException uhEx)
        {
           System.out.println("HOST ID not found.. ");
           System.exit(1);
         }
            accessServer();
       }


       // server managing/accessing function :
     private static void accessServer()
{
    try
    {
      datagramSocket=new DatagramSocket();
      Scanner userEntry=new Scanner(System.in);
      String message="",response="";
      System.out.println("You can press Ctrl-C anytime you want to exit.");
            int k =0;
           int num =1;
          
           
           while(k < frequency)
           {
                    System.out.println("\n--------------------------");
                    getTime();
                    System.out.println(" Started Sending ...");
                    double x = Math.random();
                    String str1 = Double.toString(x);
                    double y = Math.random();
                    String str2 = Double.toString(y);
                    String z = generateRandomString(CHAR_LIST);
                    String n_String = Integer.toString(num);
                    message = "{ n = "+n_String+", x = "+str1+", y = "+str2+", z = "+z + " }"; 
                    outPacket=new DatagramPacket(message.getBytes(),message.length(),host,PORT);
                    datagramSocket.send(outPacket);
                    buffer=new byte[256];
                    inPacket=new DatagramPacket(buffer,buffer.length);
                    datagramSocket.receive(inPacket);
                    response=new String(inPacket.getData(),0,inPacket.getLength());
                    getTime();
                    System.out.println(" Received from server: " +response);
                    System.out.println("--------------------------\n");
                    k++;   
                    num++; 

            }

}
catch(IOException ioEx)
  {
   ioEx.printStackTrace();
  }

finally
  {
     System.out.println("\n closing connection.... ");
      datagramSocket.close();
   }
}




}