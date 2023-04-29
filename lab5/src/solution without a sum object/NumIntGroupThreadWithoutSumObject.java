
public class NumIntGroupThreadWithoutSumObject extends Thread {
    double[] sums;

    double mysum = 0.0;
    int myId;
    private int myStart;
    private int myStop;
    private double step;


    // constructor
    public NumIntGroupThreadWithoutSumObject(int myId, int numThreads, double[] sums, int size, double step) {
        this.sums = sums;
        this.step = step;
        this.myId = myId;
        myStart = myId * (size / numThreads);
        myStop = myStart + (size / numThreads);
        if (myId == (numThreads - 1)) myStop = size;
    }

    // thread code
    public void run() {
        for (int i = myStart; i < myStop; i++) {
            double x = ((double)i+0.5)*step;
            mysum += 4.0/(1.0+x*x);
        }

        sums[myId] = mysum;




    }
}
