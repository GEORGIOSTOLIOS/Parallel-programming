class eratosthenesSPMD
{    static int size = 0;
    static int numThreads = 0;
    public static void main(String[] args)
    {


        if (args.length != 2) {
            System.out.println("Usage: java SieveOfEratosthenes <size> <numThreads>");
            System.exit(1);
        }

        try {
            size = Integer.parseInt(args[0]);
        }
        catch (NumberFormatException nfe) {
            System.out.println("Integer argument expected");
            System.exit(1);
        }
        try {
            numThreads = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }
        if (numThreads == 0) numThreads = Runtime.getRuntime().availableProcessors();
        if (size <= 0) {
            System.out.println("size should be positive integer");
            System.exit(1);
        }

        boolean[] prime = new boolean[size+1];

        for(int i = 0; i < size+1; i++)
            prime[i] = true;

        // get current time
        long start = System.currentTimeMillis();

        //for (int p = 2; p*p <= size; p++)

        int limit = (int)Math.sqrt(size) + 1;
        EratSpmdThread[] threads = new EratSpmdThread[limit];
        for (int i = 0; i <= numThreads; i++)
        {
            threads[i] = new EratSpmdThread(i,prime,limit,numThreads);
            threads[i].start();
        }

        for (int i = 0; i<numThreads; i++){
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        // get current time and calculate elapsed time
        long elapsedTimeMillis = System.currentTimeMillis()-start;

        int count = 0;
        for(int i = 0; i < size+1; i++)
            if (prime[i] == true) {
                //System.out.println(i);
                count++;
            }

        System.out.println("number of primes "+count);
        System.out.println("time in ms = "+ elapsedTimeMillis);
    }

}
