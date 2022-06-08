package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class clientVoiceHandler extends Thread {
    DataInputStream in = null;
    DataOutputStream out = null;

    clientVoiceHandler(DataOutputStream out, DataInputStream in) {
        this.in = in;
        this.out = out;
        start();
    }

    @Override
    public void run() {
        byte[] data = new byte[16000];
        try {

            while (true) {

                if (in.available() <= 0)
                    continue;

                int readCount = 0;

                readCount = in.read(data, 0, data.length);

                System.out.println(readCount);
                if (readCount > 0) {

                    out.write(data, 0, readCount);
                    out.flush();

                }
            }
        } catch (IOException e){
            e.printStackTrace();
        }finally {
			try {
				if (in != null && out != null) {
					in.close();
					out.close();
				}
			} catch (Exception e) {
			}
        }

    }
}