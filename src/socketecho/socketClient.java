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

//    socketClient 클래스를 실행할 수 있는 "main" 매소드
//    public - "main" 메소드의 접근제어자, 누구나 접근 가능하다는 의미
//    static 지정하면 해당 메소드는 인스턴스 생성 없이 실행 가능
//    void - 메소드의 리턴값이 없음
//    args - String[] 자료형의 변수명
    public static void main(String[] args) {
//        socketClient 객체를 만듦
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
////            do while 찾아보기
            while(true) {
                System.out.print("to Server: ");
                pw.println(sc.next());
                pw.flush();

                String received = br.readLine();

                System.out.println("from Server: " + received);

                if (received.equals("exit")) {
                    break;
                }

            }
            clientSocket.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
