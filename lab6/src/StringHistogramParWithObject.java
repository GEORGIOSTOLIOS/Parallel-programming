import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;


public class StringHistogramParWithObject {
    static int numThreads = 0;
    public static void main(String args[]) throws IOException {

        if (args.length != 2) {
            System.out.println("StringHistogram <file name> <numThreads>");
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
        char[] text = new char[fileString.length()];
        int n = fileString.length();
        for (int i = 0; i < n; i++) {
            text[i] = fileString.charAt(i);
        }

        int alphabetSize = 256;
        int[] histogram = new int[alphabetSize];
        for (int i = 0; i < alphabetSize; i++) {
            histogram[i] = 0;
        }
        long start = System.currentTimeMillis();

        StringHistogramThreadWithObject[] threads = new StringHistogramThreadWithObject[numThreads];
        HistogramClass histObject = new HistogramClass(histogram);
        for (int i = 0; i < numThreads; i++) {
            threads[i] = new StringHistogramThreadWithObject(i,text,histObject,n,numThreads);
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

        for (int i = 0; i < alphabetSize; i++) {
            System.out.println((char)i+": "+histObject.getHistogram()[i]);
        }
        System.out.println("time in ms = "+ elapsedTimeMillis);
    }
}
