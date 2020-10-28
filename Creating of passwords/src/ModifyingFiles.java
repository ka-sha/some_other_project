import java.io.*;

public class ModifyingFiles {
    public static void main(String[] args) {
        try {
            Writer allPasswords = new FileWriter("aonly_passwords.txt");
            transformFile("Gmail.txt", allPasswords);
            transformFile("Mail.txt", allPasswords);
            transformFile("Yahoo.txt", allPasswords);
            transformFile("Yandex.txt", allPasswords);
            allPasswords.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void transformFile(String src, Writer wr) {
        try {
            BufferedReader r = new BufferedReader(new FileReader(src));

            //int i = 0;
            for (String s = r.readLine(); s != null; s = r.readLine()) {
                s = s.replaceAll("[\t\r]", "");
                s = s.replaceFirst("^([ A-z0-9~`!#$%^&*()_+-=\\[\\]{}|;':\",./<>?]+)@([a-z]+)\\.([cmoru]{2,3})", "");
                /*if (str.equals(""))
                    str = ":" + s.substring(0, s.length() / 2);*/
                //System.out.println(s);
                if (s.length() > 1) {
                    /*String str = s.substring(1);
                    if (str.equals("\n"))
                        System.out.println(i);*/
                    wr.write(s.substring(1) + "\n");
                }
                /*else
                    System.out.println(i);*/
                //i++;
            }

            r.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}