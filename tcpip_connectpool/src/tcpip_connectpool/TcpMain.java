package tcpip_connectpool;

public class TcpMain {
	public static void main(String[] args) {
        	TcpConnectionPool pool = new TcpConnectionPool("127.0.0.1", 4444, 10);
        	TcpClient tcpclient = new TcpClient();
		    tcpclient.getconnection();
		    
		    try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    
		    tcpclient.getconnection();

		    try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    
		    tcpclient.getconnection();

		    try {
				Thread.sleep(1000000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}
