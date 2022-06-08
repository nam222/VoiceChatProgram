package voiceChat;

import java.io.DataOutputStream;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.TargetDataLine;

public class sendVoiceThread extends Thread{
    DataOutputStream out = null;
    boolean running =true;
    TargetDataLine mic;
    public void setRunning(boolean running){
        this.running=running;
    }

    public sendVoiceThread(DataOutputStream out) {
        this.out=out;
        start();
    }

    @Override
    public void run() {
        AudioFormat format = new AudioFormat(22050.F, 16, 1, true, false);
        mic = null;
        try {
            mic = AudioSystem.getTargetDataLine(format);
            mic.open(format);
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
        System.out.println("Start recording");
        mic.start();

        byte[] bytes = new byte[12000];
        System.out.println(bytes);
        while (running) {
            int count = mic.read(bytes, 0, bytes.length);
            System.out.println(count);
            if(count>0){
                try {
                    out.write(bytes,0,count);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
    public void close() throws IOException {
    	mic.drain();
    	mic.close();
    }
}