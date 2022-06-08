package server;

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

public class textServer {

   int port = 12345;
   ServerSocket server = null;
   Socket child = null;
   
   HashMap<String, PrintWriter> hm;
   static ArrayList<String> idList;
   
   public textServer() {
      chatServerThread sr;
      idList = new ArrayList<String>();
      Thread t;
      
      try {
         server = new ServerSocket(port);
         System.out.println("*************Chat Server************");
         System.out.println("waiting for client ...");
         hm = new HashMap<String, PrintWriter>();
         
         while(true) {
            child = server.accept();
            System.out.println(child);
            if(child != null) {
               sr = new chatServerThread(child, hm);
               t = new Thread(sr);
               t.start();
            }
            System.out.println(idList);
         }
      }catch(Exception e) {
         e.printStackTrace();
      }
   }
   
   public static void main(String[] args) {
      new textServer();
   }
}