public class HistTypeClass {
    private int id;
    private int counter;

    public HistTypeClass(int id){
        this.id = id;
        counter = 0;
    }

    public synchronized void add(){
        counter++;
    }

    public int getId() {
        return id;
    }

    public int getCounter() {
        return counter;
    }
}
