package tcpip_connectpool;

import java.io.*;
import java.net.*;
import java.util.concurrent.*;

public class TcpConnectionPool {
	
	    public static BlockingQueue<Socket> connectionPool;
	    private final String serverAddress;
	    private final int serverPort;
	    private final int poolSize;
	
	    public TcpConnectionPool(String serverAddress, int serverPort, int poolSize) {
	        this.serverAddress = serverAddress;
	        this.serverPort = serverPort;
	        this.poolSize = poolSize;
	        this.connectionPool = new LinkedBlockingQueue<>(poolSize);
	        initializePool();
	    }
	
	    // 초기 소켓 연결 생성
	    private void initializePool() {
	        try {
	            for (int i = 0; i < poolSize; i++) {
	                connectionPool.offer(createNewConnection());
	            }
	        } catch (IOException e) {
	            throw new RuntimeException("Error initializing TCP connection pool", e);
	        }
	    }
	
	    // 새 연결 생성
	    private Socket createNewConnection() throws IOException {
	        return new Socket(serverAddress, serverPort);
	    }
	
	    // 사용 가능한 연결을 가져옴
	    public static Socket borrowConnection() throws InterruptedException, IOException {
	    	Socket socket = connectionPool.take();
	        
	        if (socket.isClosed() || !socket.isConnected()) {
	            throw new IOException("연결이 끊어졌습니다. 재연결을 시도합니다.");
	        }
	        return socket;
	    }
	 
	    // 사용 후 다시 반납
	    public static void returnConnection(Socket socket) {
	        if (socket != null) {
	            connectionPool.offer(socket);
	        }
	    }
	
	    // 모든 연결 닫기
	    public static void closeAllConnections() {
	        for (Socket socket : connectionPool) {
	            try {
	                socket.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	        connectionPool.clear();
	    }

}