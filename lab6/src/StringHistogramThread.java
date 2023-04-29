public class StringHistogramThread extends Thread{
    private char[] text;
    public int[] myHistogram;
    private int myStart;
    private int myStop;


    public StringHistogramThread(int myId, char[] text, int[] myHistogram, int textSize, int numThreads){
        this.text = text;
        this.myHistogram = myHistogram;
        myStart = myId * (textSize / numThreads);
        myStop = myStart + (textSize / numThreads);
        if (myId == numThreads - 1)myStop = textSize;
    }
    public void run(){
        for (int i =0; i<myHistogram.length; i++){
            myHistogram[i] = 0;
        }
        int index = 0;
        for (int i=myStart; i< myStop; i++){
            System.out.println(index);
            if ((int) text[i] < myHistogram.length) myHistogram[(int) text[i]]++;

        }
    }
}
