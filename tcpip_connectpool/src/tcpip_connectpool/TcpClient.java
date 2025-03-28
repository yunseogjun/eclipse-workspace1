package tcpip_connectpool;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;

public class TcpClient {
    public void getconnection() 
    {
        for (int i = 0; i < 10; i++) {
                try {
                    Socket socket = TcpConnectionPool.borrowConnection();
                    sendMessage(socket, "Hello from Client!"+i+"\n");
                    TcpConnectionPool.returnConnection(socket);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
    	}

    private void sendMessage(Socket socket, String message) throws IOException {
		try {
			BufferedReader in = null;
			PrintWriter out = null;		
			String str = null;
			str = message;
			
			in = new BufferedReader(new InputStreamReader(socket.getInputStream())); // 입력스트림 생성
			out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))); // 출력스트림 생성
			
			System.out.println("=============================================");
			out.write(str); // print(str)와 비슷합니다.
			out.flush();
			System.out.println("server로 보낸 정보=" + str);

			str = in.readLine();
			System.out.println("server에서 받은 정보=" + str);
			System.out.println("=============================================");
			
	    	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("여기에서 죽니");
			TcpConnectionPool.closeAllConnections();
			TcpConnectionPool pool = new TcpConnectionPool("127.0.0.1", 4444, 10);
			e.printStackTrace();
		}
    }
}