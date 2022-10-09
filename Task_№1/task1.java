public class task1 {
    public static void main(String[] args) throws Exception {
        task_name(1, "remainder");
        System.out.println(remainder(1, 3));
        System.out.println(remainder(3, 4));
        System.out.println(remainder(-9, 45));
        System.out.println(remainder(5, 5));

        task_name(2, "triArea");
        System.out.println(triArea(3, 2));
        System.out.println(triArea(7, 4));
        System.out.println(triArea(10, 10));

        task_name(3, "animals");
        System.out.println(animals(2, 3, 5)); 
        System.out.println(animals(1, 2, 3));
        System.out.println(animals(5, 2, 8)); 

        task_name(4, "profitableGamble");
        System.out.println(profitableGamble(0.2, 50, 9));
        System.out.println(profitableGamble(0.9, 1, 2)); 
        System.out.println(profitableGamble(0.9, 3, 2));

        task_name(5, "operation");
        System.out.println(operation(24, 15, 9)); 
        System.out.println(operation(24, 26, 2)); 
        System.out.println(operation(15, 11, 11));

        task_name(6, "ctoa");
        System.out.println(ctoa('A'));
        System.out.println(ctoa('m')); 
        System.out.println(ctoa('[')); 
        System.out.println(ctoa('\\')); 

        task_name(7, "addUpTo");
        System.out.println(addUpTo(3)); 
        System.out.println(addUpTo(10));
        System.out.println(addUpTo(7));

        task_name(8, "nextEdge");
        System.out.println(nextEdge(8, 10));
        System.out.println(nextEdge(5, 7)); 
        System.out.println(nextEdge(9, 2)); 

        task_name(9, "sumOfCubes");
        System.out.println(sumOfCubes(new int[] { 1, 5, 9 })); 
        System.out.println(sumOfCubes(new int[] { 3, 4, 5 })); 
        System.out.println(sumOfCubes(new int[] { 2 })); 
        System.out.println(sumOfCubes(new int[] {}));

        task_name(10, "abcmath");
        System.out.println(abcmath(42, 5, 10));
        System.out.println(abcmath(5, 2, 1)); 
        System.out.println(abcmath(1, 2, 3)); 
    }

    private static void task_name(int task, String name) {
        System.out.println("--- Task #" + task + " --- " + name + " ---");
    }

    public static int remainder(int x, int y) {
        return x % y;
    }

    public static double triArea(int x, int y) {
        return x * y * 0.5;
    }

    public static int animals(int chikens, int cows, int pigs) {
        return chikens * 2 + (cows + pigs) * 4;
    }

    public static boolean profitableGamble(double prob, double prize, double pay) {
        return prob * prize > pay ? true : false;
    }

    public static String operation(int N, int a, int b) {
        String s = "none";
        if (a + b == N)
            s = "added";
        if (a - b == N || b - a == N)
            s = "subtracted";
        if (a * b == N)
            s = "product";
        if (a / b == N || b / a == N)
            s = "division";
        return s;
    }

    public static int ctoa(char c) {
        return (int) c;
    }

    public static int addUpTo(int n) {
        return (1 + n) * n / 2;
    }

    public static int nextEdge(int a, int b) {
        return a + b - 1;
    }

    public static int sumOfCubes(int[] arr) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i] * arr[i] * arr[i];
        }
        return sum;
    }

    public static boolean abcmath(int a, int b, int c) {
        int sum = a;
        for (int i = 0; i < b; i++) {
            sum += sum;
        }
        return remainder(sum, c) == 0 ? true : false;
    }
}