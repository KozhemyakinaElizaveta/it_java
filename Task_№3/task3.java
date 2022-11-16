import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class task3 {
    public static void main(String[] args) {
        task_name(1, "solutions");
        System.out.println(solutions(new int[] {1, 0, -1}));
        System.out.println(solutions(new int[] {1, 0, 0}));
        System.out.println(solutions(new int[] {1, 0, 1}));

        task_name(2, "findZip");
        System.out.println(findZip("all zip files are zipped"));
        System.out.println(findZip("all zip files are compressed"));

        task_name(3, "checkPerfect");
        System.out.println(checkPerfect(6));
        System.out.println(checkPerfect(28));
        System.out.println(checkPerfect(496));
        System.out.println(checkPerfect(12));
        System.out.println(checkPerfect(97));

        task_name(4, "flipEndChars");
        System.out.println(flipEndChars("Cat, dog, and mouse.")); 
        System.out.println(flipEndChars("ada")); 
        System.out.println(flipEndChars("Ada"));
        System.out.println(flipEndChars("z"));

        task_name(5, "isValidHexCode");
        System.out.println(isValidHexCode("#CD5C5C"));
        System.out.println(isValidHexCode("#EAECEE"));
        System.out.println(isValidHexCode("#eaecee"));
        System.out.println(isValidHexCode("#CD5C58C"));
        System.out.println(isValidHexCode("#CD5C5Z"));
        System.out.println(isValidHexCode("#CD5C&C"));
        System.out.println(isValidHexCode("CD5C5C"));

        task_name(6, "same");
        System.out.println(same(new Integer[] { 1, 3, 4, 4, 4 }, new Integer[] { 2, 5, 7 }));
        System.out.println(same(new Integer[] { 9, 8, 7, 6 }, new Integer[] { 4, 4, 3, 1 }));
        System.out.println(same(new Integer[] { 2 }, new Integer[] { 3, 3, 3, 3, 3 })); 

        task_name(7, "isKaprekar");
        System.out.println(isKaprekar(3));
        System.out.println(isKaprekar(5));
        System.out.println(isKaprekar(297));

        task_name(8, "longestZero");
        System.out.println(longestZero("01100001011000"));
        System.out.println(longestZero("100100100"));
        System.out.println(longestZero("11111"));

        task_name(9, "nextPrime");
        System.out.println(nextPrime(12));
        System.out.println(nextPrime(24));
        System.out.println(nextPrime(11));

        task_name(10, "rightTriangle");
        System.out.println(rightTriangle(3, 4, 5));
        System.out.println(rightTriangle(145, 105, 100));
        System.out.println(rightTriangle(70, 130, 110));
    }

    private static void task_name(int task, String name) {
        System.out.println("--- Task #" + task + " --- " + name + " ---");
    }

    //число решений уравнения
    private static int solutions(int[] arr) {
        if ((arr[1]*arr[1] - 4 * arr[0]*arr[2]) > 0) return 2;
        else if ((arr[1]*arr[1] - 4 * arr[0]*arr[2]) == 0) return 1;
        else return 0;
    }

    //позиция второго вхождения zip
    private static int findZip(String name) {
        boolean first = false;
        for (int i = 0; i < name.length(); i++) {
            if (name.charAt(i) == 'z' && name.charAt(i + 1) == 'i' && name.charAt(i + 2) == 'p'){
                if (first)
                    return i;
                first = true;
            }
        }
        return -1;
    }

    //является ли число совершенным
    private static boolean checkPerfect(int x) {
        int sum = 0;
        for (int i = 1; i < x; i++) {
            if (x % i == 0) {
                sum += i;
            }
        }
        return (sum == x) ? true : false;
    }

    //возвращает новую строку с заменой ее первого и последнего символов
    private static String flipEndChars(String s) {
        if (s.length() < 2) {
            return "Incompatible.";
        }
        if (s.charAt(0) == s.charAt(s.length() - 1)) {
            return "Two's a pair.";
        }
        return s.charAt(s.length() - 1) + "" + s.subSequence(1, s.length() - 1) + s.charAt(0);
    }

    //является ли строка допустимым шестнадцатеричным кодом
    public static boolean isValidHexCode(String str) {
        String s = "abcdef1234567890";
        String substr = (str.toLowerCase()).substring(1);
        if (str.charAt(0) == '#' && substr.length() == 6) {
            for (int i = 0; i < substr.length(); i++) {
                if (s.indexOf(substr.charAt(i)) != -1) continue;
                else return false;
            }
        }
        else return false;
        return true;
    }

    //одинаковое количество уникальных элементов
    public static boolean same(Integer[] arr1, Integer[] arr2) {
        int distinctIntegers1 = 0;
        int distinctIntegers2 = 0;
        for (int j = 0; j < arr1.length; j++) {
            //Get the next integer to check
            int thisInt = arr1[j];

            //Check if we've seen it before (by checking all array indexes below j)
            boolean seenThisIntBefore = false;
            for (int i = 0; i < j; i++) {
                if (thisInt == arr1[i]) {
                    seenThisIntBefore = true;
                }
            }
            //If we have not seen the integer before, increment the running total of distinct integers
            if (!seenThisIntBefore) {
                distinctIntegers1++;
            }
        } 
        for (int j = 0; j < arr2.length; j++) {
            int thisInt = arr2[j];
            boolean seenThisIntBefore = false;
            for (int i = 0; i < j; i++) {
                if (thisInt == arr2[i]) {
                    seenThisIntBefore = true;
                }
            }
            if (!seenThisIntBefore) {
                distinctIntegers2++;
            }
        }
        return (distinctIntegers1 == distinctIntegers2) ? true : false;
    }

    //число Капрекара
    public static boolean isKaprekar(int x) {
        if (x == 0 || x == 1)
            return true;
        if (x == 3) {
            return false;
        }
        Integer xx = x * x;
        String s = xx.toString();
        String left = "";
        String right = "";
        if (s.length() % 2 == 0) {
            left = String.copyValueOf(s.toCharArray(), 0, (s.length()) / 2);
            right = String.copyValueOf(s.toCharArray(), (s.length()) / 2, s.length() / 2);
        } else {
            left += String.copyValueOf(s.toCharArray(), 0, s.length() / 2);
            right += String.copyValueOf(s.toCharArray(), (s.length()) / 2, s.length() / 2 + 1);
        }
        int ans = Integer.parseInt(left) + Integer.parseInt(right);
        return ans == x ? true : false;
    }

    //возвращает самую длинную последовательность последовательных нулей в двоичной строке
    public static String longestZero(String s) {
        int max_count = 0;
        int x = 0;
        for(int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '0') {
                x++;
                if (x > max_count) max_count = x;
            }
            else {
                x = 0;
            }
        }
        String answer = "0";
        String sRepeated = IntStream.range(0, max_count).mapToObj(i -> answer).collect(Collectors.joining(""));
        return sRepeated;
    }

    //возвращает следующее простое число
    public static int nextPrime(int x) {
        boolean isPrime = false;
        while (isPrime != true) {
            for(int i = 2; i <= Math.sqrt(x); i++) {
                if ((x % i) == 0) {
                    isPrime = false;
                    x++;
                }
                else {
                    isPrime = true;
                }
            }   
        }
        return x;
    }

    //являются ли они ребрами прямоугольного треугольника
    public static boolean rightTriangle(int a, int b, int c) {
        int gip = 0;
        int kat1 = 0;
        int kat2 = 0;
        if (a >= b && a >= c) {
            gip = a;
            kat1 = b;
            kat2 = c;
        }
        if (b >= a && b >= c) {
            gip = b;
            kat1 = a;
            kat2 = c;
        }
        if (c >= b && c >= a) {
            gip = c;
            kat1 = b;
            kat2 = a;
        }
        return gip * gip == kat1 * kat1 + kat2 * kat2 ? true : false;
    }
}
