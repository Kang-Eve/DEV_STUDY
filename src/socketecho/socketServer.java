// socketServer.java를 먼저 실행 후 socketClient.java를 실행
// src 하위로 package 폴더를 생성하고 해당 폴더 하위에 class로 생성하면 package [PAKAGE NAME] 이 자동 생성
package socketecho;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

// socketServer는 class 명으로 package와 함께 파일 처음 만들때 작성됨
public class socketServer {
    private ServerSocket ServerSocket;  // Server 소켓
    private BufferedReader br;  // Client 에서 전달 받은 메시지를 읽을 버퍼 메모리
    private PrintWriter pw;     // Client 로 메시지 보냄
    private Socket clientSocket;    // Client 소켓

    public static void main(String[] args) {
        new socketServer();
    }

    public socketServer() {
        init();
    }

    public void init() {
        try {
            ServerSocket = new ServerSocket(9999);  // 로컬의 9999포트 사용
            System.out.println("Server is ready");
            System.out.println("connect clinet...");
//            Client 소켓이 실행되면
            clientSocket = ServerSocket.accept();
            System.out.println("Client has accepted");

            br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));  // Client 에서 데이터 가져올 준비
            pw = new PrintWriter(clientSocket.getOutputStream());   // Client 로 데이터 보낼 준비


            while (true) {
                String readData = br.readLine();

                if (readData.equals("exit")) {
                    readData = "BYE";
                }
                System.out.println("from Client>" + readData);
                pw.println(readData);   // 가져온 메시지를 그대로 다시 Client 로 전달
                pw.flush();     // 메모리를 초기화 시켜야 데이터가 보내진다.

            }
//            clientSocket.close();   // 소켓 통신 완료시 close 메서드로 Client 연결 종료
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
