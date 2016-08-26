
import com.sun.istack.internal.logging.Logger;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author kann
 */
public class EchoServer {
    
    static String ip = "localhost";
    static int portNum = 8080;
    
    
    
    public static void main(String[] args) throws IOException {
        
        
        if(args.length == 2)
        {
            ip = args[0];
            portNum = Integer.parseInt(args[1]);
        }
        
        ServerSocket ss = new ServerSocket();
        ss.bind(new InetSocketAddress(ip, portNum));
        System.out.println("Server started - listening on port " + portNum + " bound to ip " + ip);
        
        while(true)
        {
            Socket link = ss.accept();
            System.out.println("New client connection");
            handleClient(link);
        }
    }
    
        public static void handleClient(Socket s) throws IOException {
         
            try {
                Scanner scan = new Scanner(s.getInputStream());
                PrintWriter print = new PrintWriter(s.getOutputStream(), true);
                String msg = "";
               
                while(!msg.equals("STOP!")){
                msg = scan.nextLine();
                print.println(msg);
                
                if(msg.contains("UPPER#")){
                    print.println(msg.toUpperCase().substring(6));
                }
                
                if(msg.contains("LOWER#")){
                    print.println(msg.toLowerCase().substring(6));
                }
                
                if(msg.contains("REVERSE#")){
                    print.println(new StringBuilder(msg.substring(8)).reverse().toString());
                }
                
                if(msg.contains("TRANSLATE# hund")){
                    print.println("dog");
                }
                
                if(msg.contains("TRANSLATE# kat")){
                    print.println("cat");
                }
                   
            }
                scan.close();
                print.close();
                s.close();
            } 
            catch (IOException ex) {
                System.out.println(ex);
            }
        }
}
