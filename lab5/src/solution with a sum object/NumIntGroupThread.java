public class NumIntGroupThread extends Thread {
    sumObj totalSum;
    private int myStart;
    private int myStop;
    private double step;

    // constructor
    public NumIntGroupThread(int myId, int numThreads, sumObj sum, int size, double step) {
        totalSum = sum;
        this.step = step;
        myStart = myId * (size / numThreads);
        myStop = myStart + (size / numThreads);
        if (myId == (numThreads - 1)) myStop = size;
    }

    // thread code
    public void run() {
        double sum = 0;
        for (int i = myStart; i < myStop; i++) {
            double x = ((double)i+0.5)*step;
            sum += 4.0/(1.0+x*x);
        }
        totalSum.add(sum);

    }
}
