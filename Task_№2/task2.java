import java.util.Arrays;
import java.util.OptionalDouble;

public class task2 {
    public static void main(String[] args) {
        task_name(1, "repeat");
        System.out.println(repeat("mice", 5));
        System.out.println(repeat("hello", 3));
        System.out.println(repeat("stop", 1));

        task_name(2, "differenceMaxMin");
        System.out.println(differenceMaxMin(new int[] {10, 4, 1, 4, -10, -50, 32, 21}));
        System.out.println(differenceMaxMin(new int[] {44, 32, 86, 19}));

        task_name(3, "isAvgWhole");
        System.out.println(isAvgWhole(new int[] {1, 3}));
        System.out.println(isAvgWhole(new int[] {1, 2, 3, 4}));
        System.out.println(isAvgWhole(new int[] {1, 5, 6}));
        System.out.println(isAvgWhole(new int[] {1, 1, 1}));
        System.out.println(isAvgWhole(new int[] {9, 2, 2, 5}));

        task_name(4, "cumulutativeSum");
        System.out.println(Arrays.toString(cumulativeSum(new int[] {1, 2, 3})));
        System.out.println(Arrays.toString(cumulativeSum(new int[] {1, -2, 3})));
        System.out.println(Arrays.toString(cumulativeSum(new int[] {3, 3, -2, 408, 3, 3})));
        task_name(5, "getDecimalPlaces");
        System.out.println(getDecimalPlaces("43.20"));
        System.out.println(getDecimalPlaces("400"));
        System.out.println(getDecimalPlaces("3.1"));

        task_name(6, "fibbo");
        System.out.println(fibbo(3)); 
        System.out.println(fibbo(7)); 
        System.out.println(fibbo(12)); 

        task_name(7, "isValid");
        System.out.println(isValid("59001"));
        System.out.println(isValid("853a7"));
        System.out.println(isValid("732 32"));
        System.out.println(isValid("393939"));

        task_name(8, "isStrangePair");
        System.out.println(isStrangePair("ratio", "orator"));
        System.out.println(isStrangePair("sparkling", "groups"));
        System.out.println(isStrangePair("bush", "hubris"));
        System.out.println(isStrangePair("", ""));

        task_name(9, "isPrefix");
        System.out.println(isPrefix("automation", "auto-"));
        System.out.println(isSuffix("arachnophobia", "-phobia"));
        System.out.println(isPrefix("retrospect", "sub-"));
        System.out.println(isSuffix("vocation", "-logy"));

        task_name(10, "boxSeq");
        System.out.println(boxSeq(0));
        System.out.println(boxSeq(1));
        System.out.println(boxSeq(2));
    }

    private static void task_name(int task, String name) {
        System.out.println("--- Task #" + task + " --- " + name + " ---");
    }

    public static StringBuilder repeat(String x, int y) {
        StringBuilder new_str = new StringBuilder();
        for (int i = 0; i < x.length(); i++) {
            for (int j = 0; j < y; j++) {
                new_str.append(x.charAt(i));
            }
        }
        return new_str;
    }

    public static int differenceMaxMin(int[] arr) {
        Arrays.sort(arr);
        return arr[arr.length - 1] - arr[0];
    }

    public static boolean isAvgWhole(int[] arr) {
        double sum = 0;
        for (int i = 0; i < arr.length; i++)
            sum += arr[i];
        
        return (sum/arr.length) % 1 == 0;
    }

    public static int[] cumulativeSum(int[] arr){
        int[] new_arr = arr.clone();
        for (int i = 0; i < arr.length; i++)
            for (int k = 0; k < i; k++)
                new_arr[i] += arr[k];      
        
        return new_arr;
    }

    public static int getDecimalPlaces(String num) {
        String[] a = num.split("\\.");
        return a.length > 1 ? a[1].length() : 0;
    }

    public static int fibbo(int a){
        int[] arr = new int[a];
        arr[0] = 1;
        arr[1] = 2;
        for (int i = 2; i < arr.length; ++i) {
        arr[i] = arr[i - 1] + arr[i - 2];
        }
        return arr[arr.length - 1];
    }

    public static boolean isValid(String index) {
        return (index.length() <= 5) && index.matches("[\\d]+") ? true : false;
    }

    public static boolean isStrangePair(String a, String b) {
        if (a.equals("") && b.equals("")) {
            return true;
        }
        else {
            return (a.charAt(0) == b.charAt(b.length()-1) && b.charAt(0) == a.charAt(a.length()-1)) ? true : false;
        }
    }

    public static boolean isPrefix(String word, String prefix) {
        String a = prefix.substring(0, prefix.length()-1);
        String b = word.substring(0, a.length());
        return b.equals(a);
    }

    public static boolean isSuffix(String word, String suffix) {
        String a = suffix.substring(1, suffix.length());
        String b = word.substring(word.length() - a.length(), word.length());
        return b.equals(a);
    }

    public static int boxSeq(int x) {
        int s = 0;
        if (x == 0) {
            s = 0;
        }
        else if (x > 0 && x % 2 == 0) {
            s = x;
        }
        else {
            s = x + 2;
        }
        return s;
    }
}
