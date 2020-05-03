
public class QuickSort {
    int coreCount;
    static int runningCores;
    public QuickSort(int coreCount){
        this.coreCount = coreCount;
        runningCores = 0;
    }
    synchronized void incRunningCores(int a){
        runningCores+=a;
    }
    int partition(int arr[], int low, int high){
        //System.out.println(high);
        int pivot = arr[high];
        int i = (low-1);
        for(int j = low; j < high; j++){
            if (arr[j] < pivot){
                i++;

                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[i+1];
        arr[i+1] = arr[high];
        arr[high] = temp;
        return i+1;
    }

    void sort(int arr[], int low, int high){
        
        if (low < high){
            int pivot = partition(arr, low, high);
            MyThread t1 = new MyThread(this, arr, low, pivot-1);
            MyThread t2 = new MyThread(this, arr, pivot+1, high);
            if ((runningCores < coreCount)){
                incRunningCores(1);
                t1.start();
                //System.out.println(runningCores);
                
                boolean second;
                if ((runningCores < coreCount)){
                    incRunningCores(1);
                    t2.start();
                    second = true;
                }
                else {
                    second = false;
                    sort(arr, pivot+1, high);
                }
                try {t1.join();
                    incRunningCores(-1);
                    if(second){
                        t2.join();
                        incRunningCores(-1);
                    }   
                }
                catch(InterruptedException e){
                    System.err.println(e);
                }
            }
            else if ((runningCores >= coreCount)) 
            { 
                sort(arr, low, pivot-1); 
                sort(arr, pivot+1, high); 
            } 
        }
    }

}


/*class QuickSort 
{ boolean b = true;
    This function takes last element as pivot, 
       places the pivot element at its correct 
       position in sorted array, and places all 
       smaller (smaller than pivot) to left of 
       pivot and all greater elements to right 
       of pivot
    int partition(int arr[], int low, int high) 
    { 
        int pivot = arr[high];  
        int i = (low-1); // index of smaller element 
        for (int j=low; j<high; j++) 
        { 
            // If current element is smaller than the pivot 
            if (arr[j] < pivot) 
            { 
                i++; 
  
                // swap arr[i] and arr[j] 
                int temp = arr[i]; 
                arr[i] = arr[j]; 
                arr[j] = temp; 
            } 
        } 
  
        // swap arr[i+1] and arr[high] (or pivot) 
        int temp = arr[i+1]; 
        arr[i+1] = arr[high]; 
        arr[high] = temp; 
  
        return i+1; 
    } 
  
  
     The main function that implements QuickSort() 
      arr[] --> Array to be sorted, 
      low  --> Starting index, 
      high  --> Ending index
    void sort(int arr[], int low, int high) 
    {   MyThread t1;
        MyThread t2;
        if((low < high)){
        int pi = partition(arr, low, high); 

        if (b){
            t1 = new MyThread(this, arr, low, pi-1);
            t2 = new MyThread(this, arr, pi+1, high);
            b = false;
            t1.start();
            t2.start();
            try {
                t1.join();
                t2.join();}
                catch(InterruptedException e){
                    //System.err.println(e);
            }
        }
        if (!b) 
        { 
            /* pi is partitioning index, arr[pi] is  
              now at right place
            
  
            // Recursively sort elements before 
            // partition and after partition 
            sort(arr, low, pi-1); 
            sort(arr, pi+1, high); 
        } 
    }

    } 
}
  
// 
//            sort(arr, pivot+1, high);*/