import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class WordCountPar {
    static int numThreads = 0;
    public static void main(String args[]) throws FileNotFoundException, IOException {

        if (args.length != 2) {
            System.out.println("WordCount  <file name> <numThreads>");
            System.exit(1);
        }

        try {
            numThreads = Integer.parseInt(args[1]);
        }
        catch (NumberFormatException nfe) {
            System.out.println("Integer argument expected");
            System.exit(1);
        }
        if (numThreads == 0) numThreads = Runtime.getRuntime().availableProcessors();

        String fileString = new String(Files.readAllBytes(Paths.get(args[0])));//, StandardCharsets.UTF_8);
        String[] words = fileString.split("[ \n\t\r.,;:!?(){}]");

        TreeMap<String, Integer> map = new TreeMap<String, Integer>();
        Map<String, Integer> sMap = Collections.synchronizedMap(map);
        WordCountThread[] threads = new WordCountThread[numThreads];
        //HashMap<String, Integer> map = new HashMap<String, Integer>();

        for (int i = 0; i<numThreads; i++) {
            threads[i] = new WordCountThread(i,words,sMap,numThreads);
            threads[i].start();
        }

        for(int i = 0; i < numThreads; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        for (String name: sMap.keySet()) {
            String key = name.toString();
            String value = sMap.get(name).toString();
            System.out.println(key + "\t " + value);
        }
    }
}
