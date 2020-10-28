import java.io.*;
import java.nio.file.*;
import java.util.*;

public class CreateDict {
    public static void main(String[] args) throws IOException {
        long startTime = System.nanoTime();
        Path path = Paths.get("only_passwords.txt");
        byte[] symbols = Files.readAllBytes(path);
        Writer w = new FileWriter("dictionary_abc.txt");
        HashMap<Byte, ArrayList<Pair<Byte, Integer>>> chain = new HashMap<>();
        for (int i = 1; i < symbols.length; i++) {
            if (!chain.containsKey(symbols[i - 1])) {
                chain.put(symbols[i - 1], new ArrayList<>());
                chain.get(symbols[i - 1]).add(new Pair<>(symbols[i], 1));
            } else {
                ArrayList<Pair<Byte, Integer>> a = chain.get(symbols[i - 1]);
                boolean flag = true;
                for (Pair<Byte, Integer> pair : a) {
                    if (pair.getElement1() == symbols[i]) {
                        int b = pair.getElement2() + 1;
                        pair.setElement2(b);
                        flag = false;
                    }
                }

                if (flag)
                    a.add(new Pair<>(symbols[i], 1));
            }
        }

        HashMap<Byte, ArrayList<Pair<Byte, Double>>> finalChain = new HashMap<>();
        chain.forEach((k, v) -> {
            double num = 0;
            double gape = 0;
            for (Pair<Byte, Integer> pair : v)
                num += pair.getElement2();

            finalChain.put(k, new ArrayList<>());
            for (int i = 0; i < v.size(); i++) {
                gape += ((double) v.get(i).getElement2()) / num;
                finalChain.get(k).add(new Pair<>(v.get(i).getElement1(), gape));
            }

            try {
                w.write(k + " " + finalChain.get(k).toString() + "\n");
            } catch (IOException e) {
                e.getStackTrace();
            }
        });

        w.close();
        long elapsedTime = System.nanoTime() - startTime;

        System.out.println(elapsedTime/1000000000 + " seconds");
    }
}