import java.io.*;
import java.nio.file.*;

public class SearchStrangeThings {
    public static void main(String[] args) throws IOException {
        byte b = 67, c = 120;
        System.out.println("" + (char) b + (char) c);
        /*BufferedReader r = new BufferedReader(new FileReader("only_passwords.txt"));
        for (String s = r.readLine(); s != null; s = r.readLine()) {
            if (s.length() == 132)
                System.out.println(s.length() + " символа: " + s);
            if (s.length() == 136)
                System.out.println(s.length() + " символов: " + s);
        }*/
        /*BufferedReader r = new BufferedReader(new FileReader("only_passwords.txt"));
        Writer w = new FileWriter("final_only_passwords.txt");
        for (String s = r.readLine(); s != null; s = r.readLine()) {
            byte[] ba = s.getBytes();
            boolean flag = false;
            for (byte b : ba)
                if (b == -17 || b == -65 || b == -67) {
                    flag = true;
                    break;
                }
            if (!flag)
                w.write(s + "\n");
        }
        w.close();*/
        /*Path path = Paths.get("final_only_passwords.txt");
        byte[] symbols = Files.readAllBytes(path);
        int count = 0;
        int line = 0;
        for (int i = 0; i < symbols.length; i++) {
            if (symbols[i] == '\n')
                line++;
            if (symbols[i] == -17 || symbols[i] == -65 || symbols[i] == -67) {
                System.out.println("woy" + line + " " + i);
                System.out.println((char) symbols[i]);
                count++;
            }
        }
        System.out.println(count);*/
        //System.out.println((char) -17 + " " + (char) -65 + " " + (char) -67);
        //System.out.println(MarkovChain.markov("ex.txt", 1, 4));
        /*String s = "asf@yahoo.com:jfsancca";
        int i = s.indexOf("@yahoo.com:");
        System.out.println(i);*/

        /*BufferedReader r = new BufferedReader(new FileReader("only_passwords.txt"));
        Writer w = new FileWriter("final_only_passwords.txt");
        for (String s = r.readLine(); s != null; s = r.readLine()) {
            s = s.replaceAll("[\t\r]", "");
            int i = s.indexOf("@yahoo.com:");
            if (i != -1)
                w.write(s.substring(i + "@yahoo.com:".length()));
            else if (s.length() >= 1) {
                w.write(s + "\n");
            }
        }*/

        /*int i = 1;
        BufferedReader r = new BufferedReader(new FileReader("only_passwords.txt"));
        for (String s = r.readLine(); s != null; s = r.readLine()) {
            if (s.length() >= 100)
                System.out.println(i + " " + s);
            i++;
        }*/

        /*System.out.println(r.readLine().replaceFirst("^([ A-z0-9~`!#$%^&*()_+-=\\[\\]{}|;':\",./<>?]+)@([a-z]+)\\.([cmoru]{2,3})", "chicki-bricki"));*/
        /*String s = "picolo2345@gmail.com:picolo2345@gmail.com".replaceFirst("^([ A-z0-9-!\"#$%&'()*+,./:;<=>?@-\\[\\]_`{|}~]+)@([A-z]+)\\.([A-z]{2,3})", "");
        //System.out.println(s);
        if (s.equals(""))
            System.out.println(1);*/
        /*Reader r = new FileReader("only_passwords.txt");
        int cp = r.read();
        for (int c = r.read(), i = 1; c != -1; c = r.read()) {
            if (c == '\n')
                i++;
            if (c == '\n' && cp == '\n')
                System.out.println("wait, what? " + i);
            cp = c;
        }*/
    }
}