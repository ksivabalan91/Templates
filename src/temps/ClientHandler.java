package temps;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ClientHandler implements Runnable {

    private Socket conn;
    private boolean exit = false;
    
    // constructor
    public ClientHandler(Socket conn){
        this.conn = conn;
    }

    // main method
    @Override
    public void run(){

        String temp;
        String content;
        
        try{
            // initiallize input and output streams from connection
            
            InputStream in = conn.getInputStream();
            DataInputStream dis = new DataInputStream(in);
            OutputStream os = conn.getOutputStream();
            DataOutputStream dos = new DataOutputStream(os);
    
            String initiate = dis.readUTF();                            // recieves user name string from client via readUTF
            dos.writeUTF(initiate+" Begins");
            
            while(!exit){
                // reading datainputstream from client
                String command = dis.readUTF().trim();                  // recieve command from client via readUTF
                
                switch(command){                                        // check command type with swtich statement
                    //shopping cart methods
                    case "capitalise":
                        content = dis.readUTF().trim();
                        temp = content.toUpperCase();
                        dos.writeUTF(temp);
                        dos.flush();
                        break;
                    case "decap":
                        content = dis.readUTF().trim();
                        temp = content.toLowerCase();
                        dos.writeUTF(temp);
                        dos.flush();
                        break;
                    case "integer":
                        int intContent = dis.readInt();
                        dos.writeInt(intContent+1_000_000);
                        dos.flush();
                        break;
                    case "exit":
                        exit = true;
                        break;
                }
            }
        } catch(IOException e){}
    }    
}
