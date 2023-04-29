import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class StringMatchThread extends Thread{

    private int myId;
    private char[] match;
    private char[] pattern;
    private char[] text;
    private int myStart;
    private int myStop;
    private int patternLength;


    public StringMatchThread(int myId, char[] match, char[] pattern, char[] text, int matchLength, int patternLength, int numThreads){
        this.myId = myId;
        this.match = match;
        this.pattern = pattern;
        this.text = text;
        this.patternLength = patternLength;
        myStart = myId * (matchLength / numThreads);
        myStop = myStart + (matchLength / numThreads);
        if (myId == numThreads - 1)
        {   myStop = matchLength;
        }
    }

    public void run(){
        int threadMatchCounter = 0;
        for (int j = myStart; j < myStop; j++) {
            int i;
            for (i = 0; i < patternLength && pattern[i] == text[i + j]; i++);
            if (i >= patternLength) {
                match[j] = '1';
                threadMatchCounter ++;
                }
        }
        BruteForceStringMatchPar.lock.lock();
        try {
            BruteForceStringMatchPar.matchCount+= threadMatchCounter;
        }
        finally {
            BruteForceStringMatchPar.lock.unlock();
        }
    }
}
