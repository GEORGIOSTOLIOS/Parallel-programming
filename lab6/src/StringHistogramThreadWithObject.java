public class StringHistogramThreadWithObject extends Thread{
    HistogramClass histObject;
    private char[] text;
    private int textSize;
    private int myStart;
    private int myStop;


    public StringHistogramThreadWithObject(int myId, char[] text, HistogramClass histObject, int textSize, int numThreads){
        this.text = text;
        this.textSize = textSize;
        this.histObject = histObject;
        myStart = myId * (textSize / numThreads);
        myStop = myStart + (textSize / numThreads);
        if (myId == numThreads - 1)myStop = textSize;
    }
    public void run(){
        for (int i =myStart; i<myStop; i++){
            histObject.add((int) text[i]);
        }
    }

    }

