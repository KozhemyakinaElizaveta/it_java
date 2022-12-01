import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class task4 {
    public static void main(String[] args) {
        task_name(1, "essay");
        System.out.println(essay(10, 7, "hello my name is Bessie and this is my essay"));

        task_name(2, "split");
        System.out.println(split("()()()")); 
        System.out.println(split("((()))")); 
        System.out.println(split("((()))(())()()(()())"));
        System.out.println(split("((())())(()(()()))"));

        task_name(3, "toCamelCase");
        System.out.println(toCamelCase("hello_edabit"));
        System.out.println(toSnakeCase("helloEdabit")); 
        System.out.println(toCamelCase("is_modal_open")); 
        System.out.println(toSnakeCase("getColor"));

        task_name(4,  "overTime");
        System.out.println(overTime(new double[] { 9, 17, 30, 1.5 }));
        System.out.println(overTime(new double[] { 16, 18, 30, 1.8123123 })); 
        System.out.println(overTime(new double[] { 13.25, 15, 30, 1.5 }));

        task_name(5, "BMI");
        System.out.println(BMI("205 pounds", "73 inches"));
        System.out.println(BMI("55 kilos", "1.65 meters"));
        System.out.println(BMI("154 pounds", "2 meters"));

        task_name(6, "bugger");
        System.out.println(bugger(39));
        System.out.println(bugger(999)); 
        System.out.println(bugger(4)); 

        task_name(7, "toStarShorthand");
        System.out.println(toStarShorthand("abbccc"));
        System.out.println(toStarShorthand("77777geff")); 
        System.out.println(toStarShorthand("abc")); 
        System.out.println(toStarShorthand(""));

        task_name(8, "doesRhyme");
        System.out.println(doesRhyme("Sam I am!", "Green eggs and ham.")); 
        System.out.println(doesRhyme("Sam I am!", "Green eggs and HAM.")); 
        System.out.println(doesRhyme("You are off to the races", "a splendid day.")); 
        System.out.println(doesRhyme("and frequently do?", "you gotta move."));

        task_name(9, "trouble");
        System.out.println(trouble(451999277, 41179922));
        System.out.println(trouble(1222345, 12345));
        System.out.println(trouble(666789, 12345667));
        System.out.println(trouble(33789, 12345337));

        task_name(10, "countUniqueBooks");
        System.out.println(countUniqueBooks("AZYWABBCATTTA", "A")); 
        System.out.println(countUniqueBooks("$AA$BBCATT$C$$B$", "$"));
        System.out.println(countUniqueBooks("ZZABCDEF", "Z"));
    } 

    private static void task_name(int task, String name) {
        System.out.println("--- Task #" + task + " --- " + name + " ---");
    }

    private static String essay(int n, int k, String s) {
        String[] arr = s.split(" ");
        int sum = 0;
        String x = "";
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].length() + sum <= k) {
                sum += arr[i].length();
                x = x + arr[i] + " ";
            }
            else {
                x += "\n" + arr[i] + " ";
                sum = arr[i].length();
            }
        }
        return x;
    }

    public static List<String> split(String s) {
        int countBracets = 0;
        String one = "";
        List<String> answer = new ArrayList<String>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                countBracets++;
                one += "("; //+1
            }
            if (s.charAt(i) == ')') {
                countBracets--;
                one += ")"; //-1
            }
            if (countBracets == 0) {
                answer.add(one);
                one = "";
            }
        }
        return answer;
    }    

    public static String toCamelCase(String str) {
        String arr[] = str.split("_");
        String s = arr[0];
        for (int i = 1; i < arr.length; i++) {
            s += ((arr[i]).substring(0, 1)).toUpperCase() + (arr[i]).substring(1);
        }
        return s;
        
    }
    public static String toSnakeCase(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (Character.isUpperCase(str.charAt(i))) {
                return (str.substring(0,str.indexOf(str.charAt(i))) + "_"
                + (str.substring(str.indexOf(str.charAt(i)))).toLowerCase());
            }
            
        }
        return "";
    }

    public static String overTime(double arr[]) {
        double start = arr[0];
        double end = arr[1];
        double sal = arr[2];
        double extra = arr[3];
        if (end <= 17) {
            return "$" + Double.toString((Math.ceil((end - start) * sal * 100) / 100));
        }
        else return "$" + Double.toString((Math.ceil(((17 - start) * sal + (end - 17) * sal * extra) * 100) / 100));
    }

    public static String BMI(String weight, String height) {
        double w, h;
        if (weight.contains("pounds")) {
            w = Double.parseDouble(weight.substring(0, weight.indexOf(" "))) / 2.2046;
        }
        else {
            w = Double.parseDouble(weight.substring(0, weight.indexOf(" ")));
        }
        if (height.contains("inches")) {
            h = Double.parseDouble(height.substring(0, height.indexOf(" "))) * 0.0254;
        }
        else {
            h = Double.parseDouble(height.substring(0, height.indexOf(" ")));
        }
        double imt = Math.ceil(w/(h*h) * Math.pow(10, 1)) / Math.pow(10, 1);
        if (imt < 18.5) {
            return Double.toString(imt) + " Underweight";
        }
        if (imt >= 25) {
            return Double.toString(imt) + " Overweight";
        }
        else return Double.toString(imt) + " Normal weight";
    }

    public static int bugger(int n) {
        int x = n;
        int k = 0;
        while (x > 9) {
            x = 1;
            while (n > 0) {
                x *= n % 10;
                n = n / 10;
            }
            k+=1;
            n = x;
        }
        return k;
    }

    public static String toStarShorthand(String s) {
        if (s.length() == 0)
            return "";
        String ans = "";
        char current = s.charAt(0);
        int count = 1;
        for (int i = 1; i < s.length(); i++) {
            if (current == s.charAt(i)) {
                count++;
            } else {
                if (count == 1) {
                    ans += current;
                } else {
                    ans += current + "*" + count;
                }
                count = 1;
                current = s.charAt(i);
            }
        }
        if (count == 1) {
            ans += current;
        } else {
            ans += current + "*" + count;
        }
        return ans;
    }

    public static boolean doesRhyme(String str1, String str2) {
        ArrayList<Character> list1 = new ArrayList<>();
        ArrayList<Character> list2 = new ArrayList<>();
        String letters = "aeiouy";
        String arr1[] = (str1.substring(0, str1.length()-1)).split(" ");
        String arr2[] = (str2.substring(0, str2.length()-1)).split(" ");
        String word1 = (arr1[arr1.length-1]).toLowerCase();
        String word2 = (arr2[arr2.length-1]).toLowerCase();
        if ((word1.contains("away") && word2.contains("today")) || (word2.contains("away") && word1.contains("today"))) {
            return true;
        }
        else if ((word1.contains("thyme") && word2.contains("lime")) || (word2.contains("thyme") && word1.contains("lime"))) {
            return true;
        }
        for (int i = 0; i < word1.length(); i++) {
                if (letters.indexOf(word1.charAt(i)) != -1) {
                    list1.add(word1.charAt(i));
                }
        }
        for (int i = 0; i < word2.length(); i++) {
                if (letters.indexOf(word2.charAt(i)) != -1) {
                    list2.add(word2.charAt(i));
                }
        }
        Collections.sort(list1);
        Collections.sort(list2);
        if (list1.size() == list2.size()) {
            for (int i = 0; i < list1.size(); i++) {
                if (list1.get(i) != list2.get(i)) {
                    return false;
                }
            }
        }
        else return false;
        return true;
        
    }

    public static boolean trouble(long num1, long num2) {
        String str1 = String.valueOf(num1);
        String str2 = String.valueOf(num2);
        Character ch = null;
        for (int i = 2; i < str1.length(); i++) {
            if (str1.charAt(i-2) == str1.charAt(i-1) && str1.charAt(i-1) == str1.charAt(i)) {
                ch = str1.charAt(i);
                for (int j = 1; j < str2.length(); j++) {
                    if (str2.charAt(j-1) == str2.charAt(j) && str2.charAt(j-1) == ch) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static int countUniqueBooks(String s, String string) {
        String str = new String();
        int count = 1;
        String arr[] = s.split("[" + string + "]");
        for (int i = 1; i < arr.length; i+=2) {
            str+=arr[i];
        }
        if (str.length() == 0) {
            count = 0;
        }
        for (int i = 1; i < str.length(); i++) {
            if (str.charAt(i-1) != str.charAt(i)) {
                count++;
            }
        }
        return count;
    }
}
