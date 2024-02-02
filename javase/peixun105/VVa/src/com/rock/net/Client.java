package com.rock.net;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
public static void main(String[] args)throws UnknownHostException {
		Socket socket=new Socket("127.0.0.1",1010);//套接字技术
		BufferedWriter writer=new BufferedWriter(new
				OutputStreamWriter(socket.getOutputStream()));
	}
	String speak="小样，困死我了";
	writer.write(speak);
	writer.close();
	socket.close();
}

