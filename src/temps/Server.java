package temps;

import java.io.EOFException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    public static void main(String[] args) throws IOException, EOFException{
        
        int port = 1025;                                                            // default port
        Boolean exit = false;
        ExecutorService executorService = Executors.newCachedThreadPool();
        
        if (args.length>0){
            port = Integer.parseInt(args[0]);                                       // set port number string parsed to integer
        }        
        try {
            ServerSocket ss = new ServerSocket(port);
            while(!exit){
                System.out.println("waiting for connection on port: "+port);
                Socket s = ss.accept();
                System.out.println("connection recieved");
                ClientHandler handleMe = new ClientHandler(s);
                executorService.submit(handleMe);
            }
            executorService.shutdown();
            ss.close();
        } catch(IOException e){}
    }
}
