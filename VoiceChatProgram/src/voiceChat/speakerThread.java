package voiceChat;

import java.io.DataInputStream;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

public class speakerThread extends Thread {
	DataInputStream in = null;
	boolean running = true;
	SourceDataLine speaker;

	public speakerThread(DataInputStream in) {
		this.in = in;
		start();
	}

	public void setRunning(boolean running) {
		this.running = running;
	}

	@Override
	public void run() {
		AudioFormat format = new AudioFormat(22050.F, 16, 1, true, false);
		DataLine.Info speakerInfo = new DataLine.Info(SourceDataLine.class, format);

		speaker = null;
		try {
			speaker = (SourceDataLine) AudioSystem.getLine(speakerInfo);
			speaker.open(format);
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
		speaker.start();
		byte[] data = new byte[12000];
		while (running) {
			try {
				if (in.available() <= 0)
					continue;
			} catch (IOException e) {
				e.printStackTrace();
			}
			int readCount = 0;
			try {
				readCount = in.read(data, 0, data.length);
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println(readCount);
			if (readCount > 0) {
				speaker.write(data, 0, readCount);
			}
		}
	}

	public void close() throws IOException {
		speaker.drain();
		speaker.close();
	}
}