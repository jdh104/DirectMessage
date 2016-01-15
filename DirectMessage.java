
import java.util.Scanner;
import java.net.Socket;
import java.net.ServerSocket;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import javax.swing.JOptionPane;

public class DirectMessage{
    
    private static final int PORT = 9091;
    private static ServerSocket server;
    private static Listener listener;
    
    public static void main(String[] args){
        try{
            server = new ServerSocket(PORT);
            listener = new Listener(server);
            listener.start();
        } catch(Exception e){
            //DO NOTHING
        }
        Scanner stdin = new Scanner(System.in);
        String ip, message;
        while (true){
            System.out.print("Enter ip to send message to: ");
            ip = stdin.nextLine();
            System.out.print("Enter message: ");
            message = stdin.nextLine();
            sendMessage(ip,message);
        }
    }
    
    public static void sendMessage(String ip, String message){
        try{
            Socket s = new Socket(ip,PORT);
            PrintWriter out = new PrintWriter(s.getOutputStream(), true);
            out.println(message);
        } catch(Exception e){
            JOptionPane.showMessageDialog(null,"Message failed to send");
        }
    }
    
    private static class Listener extends Thread{
        public Listener(ServerSocket s){
            
        }
        
        public void run(){
            String receive;
            while (true){
                try{
                    Socket s = server.accept();
                    BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
                    receive = in.readLine();
                    JOptionPane.showMessageDialog(null,receive);
                } catch(Exception e){
                    //DO NOTHING
                }
            }
        }
    }
}
