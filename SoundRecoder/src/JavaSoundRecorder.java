import com.dropbox.core.v2.*;
import javax.sound.sampled.*;
import java.io.*;
import java.text.*;
import java.util.*;

/**
 * A sample program is to demonstrate how to record sound in Java
 * author: www.codejava.net
 */
public class JavaSoundRecorder
{
    private DbxClientV2 client;
    private AudioFileFormat.Type fileType = AudioFileFormat.Type.WAVE;
    private AudioFormat format;
    private DataLine.Info info;
    private TargetDataLine line;

    public JavaSoundRecorder(DbxClientV2 client) {
        this.client = client;
        format = getAudioFormat();
        info = new DataLine.Info(TargetDataLine.class, format);
    }

    public void recordAudio(int milliseconds) {
        String fileName = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()) + ".wav";
        File file = new File(fileName);
        start(file);
        stop(file, milliseconds);
    }

    private void start(File file) {
        Thread thread = new Thread(() -> {
            try {
                if (!AudioSystem.isLineSupported(info)) {
                    System.out.println("Line not supported");
                    System.exit(0);
                }
                line = (TargetDataLine) AudioSystem.getLine(info);
                line.open(format);
                line.start();   // start capturing

                AudioInputStream ais = new AudioInputStream(line);
                AudioSystem.write(ais, fileType, file);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        thread.start();
    }

    private void stop(File file, int milliseconds) {
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(milliseconds);
                line.stop();
                line.close();
                recordAudio(milliseconds);
                //TODO: upload file to Dropbox
                InputStream in = new FileInputStream(file.getName());
                client.files().uploadBuilder("/" + file.getName()).uploadAndFinish(in);
                //TODO: delete file
                in.close();
                file.delete();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        });
        thread.start();
    }

    private AudioFormat getAudioFormat() {
        float sampleRate = 16000;
        int sampleSizeInBits = 8;
        int channels = 2;
        boolean signed = true;
        boolean bigEndian = true;
        return new AudioFormat(
                sampleRate,
                sampleSizeInBits,
                channels,
                signed,
                bigEndian
        );
    }
}