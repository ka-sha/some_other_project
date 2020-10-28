import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class CreateSuperCoolDict {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("only_passwords.txt");
        byte[] symbols = Files.readAllBytes(path);
        FileWriter w = new FileWriter("generated_passwords3.txt");

        HashMap<String, ArrayList<Pair<String, Integer>>> chain = new HashMap<>();
        String s;
        long startTime = System.nanoTime();
        for (int i = 3; i < symbols.length; i++) {
            s = "" + (char) symbols[i -3] + (char) symbols[i - 2] + (char) symbols[i - 1];
            if (!chain.containsKey(s)) {
                chain.put(s, new ArrayList<>());
                chain.get(s).add(new Pair<>("" + (char) symbols[i], 1));
            } else {
                ArrayList<Pair<String, Integer>> a = chain.get(s);
                boolean flag = true;
                for (Pair<String, Integer> pair : a) {
                    if (pair.getElement1().equals("" + (char) symbols[i])) {
                        int b = pair.getElement2() + 1;
                        pair.setElement2(b);
                        flag = false;
                    }
                }

                if (flag)
                    a.add(new Pair<>("" + (char) symbols[i], 1));
            }

            if (symbols[i] == '\n' && i + 3 < symbols.length) {
                if (!chain.containsKey("\n"))
                    chain.put("\n", new ArrayList<>());
                String str = "" + (char) symbols[i + 1] + (char) symbols[i + 2] + (char) symbols[i + 3];
                ArrayList<Pair<String, Integer>> a = chain.get("\n");
                boolean flag = true;
                for (Pair<String, Integer> pair : a) {
                    if (pair.getElement1().equals(str)) {
                        int b = pair.getElement2() + 1;
                        pair.setElement2(b);
                        flag = false;
                    }
                }

                if (flag)
                    a.add(new Pair<>(str, 1));

                i += 3;
            }
        }

        HashMap<String, ArrayList<Pair<String, Double>>> finalChain = new HashMap<>();
        chain.forEach((k, v) -> {
            double num = 0;
            double gape = 0;
            for (Pair<String, Integer> pair : v)
                num += pair.getElement2();

            finalChain.put(k, new ArrayList<>());
            for (int i = 0; i < v.size(); i++)
                if (i != v.size() - 1) {
                    gape += ((double) v.get(i).getElement2()) / num;
                    finalChain.get(k).add(new Pair<>(v.get(i).getElement1(), gape));
                } else {
                    finalChain.get(k).add(new Pair<>(v.get(i).getElement1(), 1.0));
                }
        });

        System.out.println((System.nanoTime() - startTime) / 1000000000 + " seconds needs to create dictionary.");


        long startTime1 = System.nanoTime();
        String curStr = "\n";
        StringBuilder finalString = new StringBuilder();
        int count = 0;
        while (count < 5000000) {
            if (curStr.equals("\n"))
                count++;
            double d = Math.abs(new Random().nextDouble()) % 1;

            for (Pair<String, Double> pair : finalChain.get(curStr)) {
                if (d < pair.getElement2()) {
                    //System.out.print((char) curChar);
                    curStr = pair.getElement1();
                    finalString.append(curStr);
                    break;
                }
            }
            curStr = finalString.substring(finalString.length() - 3);
            if (curStr.charAt(2) == '\n')
                curStr = "\n";
        }
        w.write(finalString.toString());
        w.close();

        System.out.println((System.nanoTime() - startTime1) / 1000000000 + " seconds needs to create dictionary.");
    }
}
