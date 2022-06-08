package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class voiceServer {
	int port = 12346;
	ServerSocket server = null;
	Socket child = null;
	static ArrayList<voiceClientHandler> handlers = new ArrayList<>();

	public voiceServer() throws IOException {
		try {
			server = new ServerSocket(port);

			System.out.println("서버 시작");

			while (true) {
				child = server.accept();
				voiceClientHandler clientHandler = null;
				if (child != null) {
					System.out.println(child);
					addMemberThread addClient = new addMemberThread(child);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
		}
	}

	static void start(DataInputStream dataIn, DataOutputStream dataOut) {
		for (int i = 0; i < handlers.size(); i++) {
			if (dataIn == handlers.get(i).in) {
				continue;
			}
			clientVoiceHandler clientVoiceHandler = new clientVoiceHandler(dataOut, handlers.get(i).in);
			clientVoiceHandler clientVoiceHandler1 = new clientVoiceHandler(handlers.get(i).out, dataIn);
		}
	}

	public static void main(String[] args) throws IOException {
		new voiceServer();
	}

}
