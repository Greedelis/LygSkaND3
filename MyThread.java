public class MyThread extends Thread{
    QuickSort qc;
    int arr[];
    int low, high;
    public MyThread(QuickSort qc, int arr[], int low, int high){
        this.qc = qc;
        this.arr = arr;
        this.high = high;
        this.low = low;
    }

    public void run( ){
        qc.sort(arr, low, high);
    }
    
}