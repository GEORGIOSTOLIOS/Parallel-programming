import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class BruteForceStringMatchPar {
    static int matchCount = 0;
    static int numThreads = 0;

   static Lock lock = new ReentrantLock();
    public static void main(String args[]) throws IOException {

        if (args.length != 3) {
            System.out.println("BruteForceStringMatch  <file name> <pattern> <numThreads>");
            System.exit(1);
        }

        try {
            numThreads = Integer.parseInt(args[2]);
        }
        catch (NumberFormatException nfe) {
            System.out.println("Integer argument expected");
            System.exit(1);
        }
        if (numThreads == 0) numThreads = Runtime.getRuntime().availableProcessors();

        String fileString = new String(Files.readAllBytes(Paths.get(args[0])));//, StandardCharsets.UTF_8);
        char[] text = new char[fileString.length()];
        int n = fileString.length();
        for (int i = 0; i < n; i++) {
            text[i] = fileString.charAt(i);
        }

        String patternString = new String(args[1]);
        char[] pattern = new char[patternString.length()];
        int m = patternString.length();
        for (int i = 0; i < m; i++) {
            pattern[i] = patternString.charAt(i);
        }

        int matchLength = n - m;
        char[] match = new char[matchLength+1];
        for (int i = 0; i <= matchLength; i++) {
            match[i] = '0';
        }

        long start = System.currentTimeMillis();
        /*ArrayList<Integer> match = new ArrayList<Integer>(); */
        StringMatchThread[] threads = new StringMatchThread[numThreads];
        for (int i = 0; i < numThreads; i++) {
            threads[i] = new StringMatchThread(i,match,pattern,text,matchLength,m,numThreads);
            threads[i].start();
        }

        for(int i = 0; i < numThreads; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        long elapsedTimeMillis = System.currentTimeMillis()-start;
        System.out.println("time in ms = "+ elapsedTimeMillis);

        for (int i = 0; i < matchLength; i++) {
            if (match[i] == '1') System.out.print(i+" ");
        }
        System.out.println();
        System.out.println("Total matches " + matchCount);

    }
}
