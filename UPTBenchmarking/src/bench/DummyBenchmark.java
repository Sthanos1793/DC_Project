package bench;


public class DummyBenchmark implements IBenchmark {

    private boolean running;

    public void run(){

        try{

            Thread.sleep(1000);

        }

        catch (InterruptedException e){

            e.printStackTrace();

        }

    }

    public void run(Object ... params){

        int[] array = (int[]) params[0];
        running = true;
        bubbleSort(array);

    }

    private void bubbleSort(int[] array){

        int n = array.length;
        for (int i = 0; i < n && running; i++){

            for(int j = 0; j < n - i - 1; j++){

                if(array[j] > array[j+1]){

                    int temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;

                }

            }

        }

    }

    public void initialize(Object ... params){

        running = true;

        int size = (int) params[0];
        int[] array = new int[size];
        for (int i = 0; i< size; i++){

            array[i] = (int) (Math.random() * 1000);

        }

        params[0] = array;

    }

    public void clean(){



    }

    public void cancel(){

        running = false;

    }

}
