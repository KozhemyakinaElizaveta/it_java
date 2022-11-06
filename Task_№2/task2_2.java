public class task2_2 {
    public static void main(String[] args){
        System.out.println(sr(new int[] {1, 2, 3, 4}));
    }
    public static boolean sr(int[] arr) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        return (sum/arr.length) % 1 == 0;
    }
}
