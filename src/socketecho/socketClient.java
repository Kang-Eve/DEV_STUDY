package socketecho;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;


public class socketClient {
    private Socket clientSocket;
    private BufferedReader br;
    private PrintWriter pw;
    private Scanner sc;

    public static void main(String[] args) {
        new socketClient();
    }

    public socketClient() {
        init();
    }

    public void init() {
        try {
            clientSocket = new Socket("localhost", 9999);
            System.out.println("Server Connect");

            br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            pw = new PrintWriter(clientSocket.getOutputStream());

            sc = new Scanner(System.in);

            String inputData = "";

//            do while 찾아보기
            while(!inputData.equals("exit")) {
                System.out.print("to Server: ");
                pw.println(sc.next());
                pw.flush();

                System.out.println("from Server: " + br.readLine());
            }
            clientSocket.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
