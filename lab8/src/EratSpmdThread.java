public class EratSpmdThread extends Thread{

    int myId;
    boolean [] prime;
    int size;
    int limit;

    int myStart;

    int myStop;

    public EratSpmdThread(int myId, boolean[] prime, int size, int numThreads){
        this.myId = myId;
        this.prime = prime;
        this.size = size;
        this.limit = limit;
        myStart = myId * (size/numThreads);
        myStop = myStart + (size/numThreads);
        if (myId == 0)myStart = 2;
        if (myId == numThreads - 1)myStop = size;
    }

    public void run(){
        for (int p = myStart; p <= myStop; p++)
        {
            // If prime[p] is not changed, then it is a prime
            if(prime[p] == true)
            {
                // Update all multiples of p
                for (int i = p*p; i <= size; i += p)
                    prime[i] = false;
            }
        }
    }
}
