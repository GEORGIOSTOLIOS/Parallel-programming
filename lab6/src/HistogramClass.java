public class HistogramClass {
    private int[] histogram;
    public HistogramClass(int[] histogram){
        this.histogram = histogram;
    }

    public synchronized void add(int index){
        histogram[index]++;
    }

    public int[] getHistogram() {
        return histogram;
    }
}
