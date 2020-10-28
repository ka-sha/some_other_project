import com.dropbox.core.*;
import com.dropbox.core.v2.*;

public class Main {
    public static void main(String[] args) {
        String ACCESS_TOKEN = "sl.AkMZIQSOBy_jVXsBSdv5XOB8OPu-Vsh7uDEbEqJst29BHmVZ063rWRwLzgFtYQYMK-ClCEB7gmtAuLJ8oh5k5sNJIggzWDnWyFIxKVH3Nk0AP9_5vsc_1h77pozbqlqN1TjCQcQ";
        DbxRequestConfig config = DbxRequestConfig.newBuilder("dropbox/java-tutorial").build();
        DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);
        JavaSoundRecorder rec = new JavaSoundRecorder(client);
        rec.recordAudio(30000);
    }
}