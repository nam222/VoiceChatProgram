package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class voiceClientHandler extends Thread {
	DataInputStream in;
	DataOutputStream out;
	public voiceClientHandler(DataInputStream dataIn, DataOutputStream dataOut) {
		this.in = dataIn;
		this.out = dataOut;
	}
}