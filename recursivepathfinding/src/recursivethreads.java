import java.util.TreeMap;


public class recursivethreads  implements  Runnable {
    String threadDir;
    Thread methodThread;

    int x = 0;
    int y = 0;
    int c = 0;

    recursivethreads(String dir) {
        threadDir = dir;
        methodThread = new Thread(this, threadDir);
        System.out.print("PLEASE DONT BREAK");
        methodThread.start();
    }




    public void run() {
        try {


        } catch (Exception e) {
            System.out.println();

        }

    }
}
