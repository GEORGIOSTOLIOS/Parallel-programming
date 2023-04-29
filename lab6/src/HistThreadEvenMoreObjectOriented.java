public class HistThreadEvenMoreObjectOriented extends Thread {
    HistTypeClass[] histObjectArray;
    private char[] text;
    private int textSize;
    private int myStart;
    private int myStop;


    public HistThreadEvenMoreObjectOriented(int myId, char[] text, HistTypeClass[] histObjectArray, int textSize, int numThreads) {
        this.text = text;
        this.textSize = textSize;
        this.histObjectArray = histObjectArray;
        myStart = myId * (textSize / numThreads);
        myStop = myStart + (textSize / numThreads);
        if (myId == numThreads - 1) myStop = textSize;
    }

    public void run(){
        for (int i = myStart; i< myStop; i++){
            for (int j = 0; j< histObjectArray.length; j++){
                if ((int)text[i] == histObjectArray[j].getId()) histObjectArray[j].add();
            }
        }
    }
}