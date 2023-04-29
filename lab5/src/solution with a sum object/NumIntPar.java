public class NumIntPar {
    public static void main(String[] args) {
        int numSteps = 0;
        int numThreads = 0;

        /* parse command line */
        if (args.length != 2) {
            System.out.println("arguments:  number_of_steps, num_of_threads");
            System.exit(1);
        }
        try {
            numSteps = Integer.parseInt(args[0]);
            numThreads = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            System.out.println("argument "+ args[0] +" must be long int");
            System.exit(1);
        }
        if (numThreads == 0) numThreads = Runtime.getRuntime().availableProcessors();
        /* start timing */
        long startTime = System.currentTimeMillis();

        double step = 1.0 / (double)numSteps;
        NumIntGroupThread[] threads = new NumIntGroupThread[numThreads];
        sumObj sum = new sumObj();
        /* do computation */
        for (int i=0; i < numThreads; ++i) {
            threads[i] = new NumIntGroupThread(i,numThreads,sum,numSteps,step);
            threads[i].start();
        }

        for (int i = 0; i < numThreads; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {}
        }

        double pi = sum.getSum() * step;

        /* end timing and print result */
        long endTime = System.currentTimeMillis();

        System.out.printf("parallel program results with %d steps\n", numSteps);
        System.out.printf("computed pi = %22.20f\n" , pi);
        System.out.printf("difference between estimated pi and Math.PI = %22.20f\n", Math.abs(pi - Math.PI));
        System.out.printf("time to compute = %f seconds\n", (double) (endTime - startTime) / 1000);
    }
}
