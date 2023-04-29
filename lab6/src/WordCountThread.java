import java.util.Map;
import java.util.TreeMap;

public class WordCountThread extends Thread {


    private String[] words;
    private int myStart;
    private int myStop;
    private Map<String, Integer> map;

    public WordCountThread(int myId, String[] words, Map<String, Integer> map, int numThreads ){
        this.words = words;
        myStart = myId * (words.length / numThreads);
        myStop = myStart + (words.length / numThreads);
        this.map = map;
    }

    public void run(){
        for (int wordCounter = myStart; wordCounter < myStop; wordCounter++) {
            String key = words[wordCounter].toLowerCase();
            if (key.length() > 0) {
                if (map.get(key) == null) {
                    map.put(key, 1);
                }
                else {
                    int value = map.get(key).intValue();
                    value++;
                    map.put(key, value);
                }
            }
        }
    }


}
