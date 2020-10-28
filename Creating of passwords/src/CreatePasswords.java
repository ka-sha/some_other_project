import java.io.*;
import java.util.*;

public class CreatePasswords {
    public static void main(String[] args) throws IOException {
        BufferedReader r = new BufferedReader(new FileReader("dictionary.txt"));
        FileWriter w = new FileWriter("generated_passwords1.txt");
        HashMap<Byte, ArrayList<Pair<Byte, Double>>> chain = new HashMap<>();

        for (String line = r.readLine(); line != null; line = r.readLine()) {
            String[] s = line.replaceAll("\\s+","").replaceAll("[\\[|,\\]]+", " ").split(" ");
            byte key = Byte.valueOf(s[0], 10);
            chain.put(key, new ArrayList<>());

            for (int i = 1; i < s.length; i++)
                chain.get(key).add(new Pair<>(Byte.valueOf(s[i], 10), Double.valueOf(s[++i])));
        }
        r.close();

        byte curChar = 10;
        int count = 0;
        long startTime = System.nanoTime();
        while (count < 5000000) {
            if (curChar == 10)
                count++;
            double d = Math.abs(new Random().nextDouble()) % 1;
            w.write((char) curChar);
            for (Pair<Byte, Double> pair : chain.get(curChar)) {
                if (d < pair.getElement2()) {
                    curChar = pair.getElement1();
                    break;
                }
            }
        }
        w.close();
        System.out.println((System.nanoTime() - startTime) / 1000000000 + " seconds");
    }
}