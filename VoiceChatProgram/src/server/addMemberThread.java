package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class addMemberThread extends Thread {
	DataInputStream dataIn;
	DataOutputStream dataOut;
	Socket child;
	voiceClientHandler clientHandler;

	public addMemberThread(Socket child) throws IOException {
		dataIn = new DataInputStream(child.getInputStream());
		dataOut = new DataOutputStream(child.getOutputStream());
		this.child = child;
		start();
	}

	public void run() {
		clientHandler = new voiceClientHandler(dataIn, dataOut);
		voiceServer.handlers.add(clientHandler);
		voiceServer.start(dataIn, dataOut);
		while (true) {
			if (!child.isClosed()) {
				voiceServer.handlers.remove(clientHandler);
			}
		}
	}
}
