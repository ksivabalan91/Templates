package temps;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        // default host
        String host = "localhost";
        // default port
        int port = 1025;
        boolean exit = false;
        Scanner scan = new Scanner(System.in);
        String content = "";

        if (args.length == 1) {
            // set port number string parsed to integer
            port = Integer.parseInt(args[0]);
        } else if (args.length == 2) {
            port = Integer.parseInt(args[1]);
            host = args[0];
        }

        try (Socket s = new Socket(host, port)) {
            // connect to server with host and port number
            System.out.println("\nConnected to shopping cart server at " + host + " on port " + port + "\n");

            // initiallize input and output streams
            OutputStream os = s.getOutputStream();
            DataOutputStream dos = new DataOutputStream(os);
            InputStream is = s.getInputStream();
            DataInputStream dis = new DataInputStream(is);

            dos.writeUTF("Batman");
            System.out.println(dis.readUTF());

            while (!exit) {
                System.out.printf(">");
                String command = scan.next();

                // send command to ClientHandler
                dos.writeUTF(command);
                // check command, first word entered in console
                switch (command.trim()) {
                    case "capitalise":
                        content = scan.nextLine();
                        // send items string to ClientHandler
                        dos.writeUTF(content);
                        // print string recieved from ClientHandler
                        System.out.println(dis.readUTF());
                        break;
                    case "decap":
                        content = scan.nextLine();
                        // send items string to ClientHandler
                        dos.writeUTF(content);
                        // print string recieved from ClientHandler
                        System.out.println(dis.readUTF());
                        break;
                    case "integer":
                        int intContent = scan.nextInt();
                        dos.writeInt(intContent);
                        System.out.println(dis.readInt());
                        break;
                    case "exit":
                        exit = true;
                        System.out.println("exiting");
                        break;
                    default:
                        System.out.println("enter valid input");
                        break;
                }
            }
            scan.close();

            System.out.println("Closing connection...\n");

            try {
                // close connections
                s.close();
                System.out.println("Done\n");
            } catch (IOException ex) {
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
