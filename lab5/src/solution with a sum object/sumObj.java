public class sumObj {
    private double sum;

    public sumObj (){
        this.sum = 0;
    }

    public double getSum() {
        return sum;
    }

    public synchronized void add (double localsum){
        this.sum = this.sum + localsum;
    }
}
