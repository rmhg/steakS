package core.netlink.socketAPI;

import java.io.IOException;
import java.io.InputStream;
import java.net.*;

public class Server extends ServerSocket{
	Socket Conn = null;
	public static String connectedIp = "";
	public Server(int backlog, String host , int port) throws Exception{
		super(port, backlog, InetAddress.getByName(host));
	}
	public Server(int port) throws Exception{
		super(port);
	}
	public void waitForConn() throws IOException {
		Conn = this.accept();
		connectedIp = Conn.getRemoteSocketAddress().toString().split(":")[0].substring(1);
	}
	public void sendData(byte[] data) throws IOException {
		Conn.getOutputStream().write(data);
	}
	public byte[] recvData() throws IOException {
		InputStream is = Conn.getInputStream();
		String data = "";
		byte b;
		while((b = (byte) is.read()) != -1) {
			data += (char)b;
			if(data.contains("\n\r\n")) break;
		}
		return data.getBytes();
	}
	public void CloseConn() throws IOException {
		Conn.close();
	}
}
