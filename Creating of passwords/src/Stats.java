import java.io.*;
import java.util.*;

public class Stats {
    public static void main(String[] args) throws IOException {
            BufferedReader r = new BufferedReader(new FileReader("generated_passwords3.txt"));
            Reader r2 = new FileReader("generated_passwords3.txt");
            Writer w = new FileWriter("length_of_passwords3.txt");
            Writer w2 = new FileWriter("frequency_of_characters_in_passwords3.txt");
            Map<Integer, Integer> lengthAndAmount = new HashMap<>();
            Map<Character, Integer> frequency = new HashMap<>();
            //ArrayList<Pair<Integer, Integer>> orderedLength = new ArrayList<>();
            ArrayList<Pair<Character, Integer>> orderedFreq = new ArrayList<>();

            for (String password = r.readLine(); password != null; password = r.readLine()) {
                int len = password.length();
                if (len != 0) {
                    int num = lengthAndAmount.getOrDefault(len, 0);
                    lengthAndAmount.put(len, ++num);
                }
            }

            lengthAndAmount.forEach((key, value) -> {
                //orderedLength.add(new Pair<>(key, value));
                try {
                    w.write(key + " | " + value + "\n");
                } catch (IOException e) {
                    e.getMessage();
                }
            });

            //Collections.sort(orderedLength);
            //for (Pair<Integer, Integer> p : orderedLength)
              //  w.write(p.toString() + "\n");

            for (int c = r2.read(); c != -1; c = r2.read())
                if ((char) c != '\n') {
                    int count = frequency.getOrDefault((char) c, 0);
                    frequency.put((char) c, ++count);
                }

            frequency.forEach((key, value) -> {
                orderedFreq.add(new Pair<>(key, value));
                /*try {
                    w2.write(key + " | " + value + "\n");
                } catch (IOException e) {
                    e.getMessage();
                }*/
            });

            Collections.sort(orderedFreq);
            for (Pair<Character, Integer> p : orderedFreq)
                w2.write(p.toString() + "\n");

            w.close();
            w2.close();
            r.close();
            r2.close();
    }
}