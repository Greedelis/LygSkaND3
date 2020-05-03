import java.util.Random;
import java.util.stream.IntStream;

public class Main {
    static void is_sorted(int arr[]){
        for(int i = 0; i < arr.length-1; i++){
            if(arr[i] > arr[i+1]){
                System.out.println("ne sorted");
                return;
            }
        }
        System.out.println("sorted");
    }
    static void printArray(int arr[]){
        for (int i = 0; i < arr.length; i++){
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
    public static void main(String[] args) {
        //int coreCount = Integer.parseInt(args[0]);
        int coreCount=4;
        int[]  arr = IntStream.generate(() -> new Random().nextInt(9000000)).limit(1000000).toArray();
        
        for(int i = 0; i <= 16; i++){
            System.out.println("cores: "+i);
            int[] arr2 = arr.clone();
            //is_sorted(arr);
            //System.out.println(arr.length);
            long time0 = System.currentTimeMillis();
            QuickSort sor = new QuickSort(i);
            sor.sort(arr2, 0, arr.length-1);
            long time1 = System.currentTimeMillis();
            //printArray(arr);
            //is_sorted(arr);
            double dtime = (time1-time0)/1000.;
            System.out.println(dtime);
        }
    }

}


// long start = System.currentTimeMillis();
// // ...
// long finish = System.currentTimeMillis();
// long timeElapsed = finish - start;