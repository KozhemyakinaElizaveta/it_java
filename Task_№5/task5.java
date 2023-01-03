import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class task5 {
    public static void main(String[] args) throws Exception{
        task_name(1, "encrypt");
        System.out.println(Arrays.toString(encrypt("Hello")));
        System.out.println(decrypt(new int[]{72, 33, -73, 84, -12, -3, 13, -13, -68}));
        System.out.println(Arrays.toString(encrypt("Sunshine")));

        task_name(2, "canMove");
        System.out.println(canMove("Rook", "A8", "H8"));
        System.out.println(canMove("Bishop", "A7", "G1"));
        System.out.println(canMove("Queen", "C4", "D6"));

        task_name(3, "canComplete");
        System.out.println(canComplete("butl", "beautiful"));
        System.out.println(canComplete("butlz", "beautiful"));
        System.out.println(canComplete("tulb", "beautiful"));
        System.out.println(canComplete("bbutl", "beautiful"));

        task_name(4,  "sumDigProd");
        System.out.println(sumDigProd(16, 28));
        System.out.println(sumDigProd(0));
        System.out.println(sumDigProd(1, 2, 3, 4, 5, 6));

        task_name(5, "sameVowelGroup");
        System.out.println(sameVowelGroup(new String [] {"toe", "ocelot", "maniac"}));
        System.out.println(sameVowelGroup(new String [] {"many", "carriage", "emit", "apricot", "animal"}));
        System.out.println(sameVowelGroup(new String [] {"hoops", "chuff", "bot", "bottom"}));

        task_name(6, "validateCard");
        System.out.println(validateCard(1234567890123456L));
        System.out.println(validateCard(1234567890123452L)); 

        task_name(7, "numToEng");
        System.out.println(numToEng(18));
        System.out.println(numToEng(117));
        System.out.println(numToEng(0));
        System.out.println(numToRus(18));
        System.out.println(numToRus(356));
        System.out.println(numToRus(0));

        task_name(8, "getSha256Hash");
        System.out.println(getSha256Hash("password123"));
        System.out.println(getSha256Hash("Fluffy@home"));
        System.out.println(getSha256Hash("Hey dude!"));

        task_name(9, "correctTitle");
        System.out.println(correctTitle("jOn SnoW, kINg IN thE noRth."));
        System.out.println(correctTitle("sansa stark, lady of winterfell."));
        System.out.println(correctTitle("TYRION LANNISTER, HAND OF THE QUEEN."));

        task_name(10, "hexLattice");
        System.out.println(hexLattice(1));
        System.out.println(hexLattice(7));
        System.out.println(hexLattice(19));
        System.out.println(hexLattice(37));
    }

    private static void task_name(int task, String name) {
        System.out.println("--- Task #" + task + " --- " + name + " ---");
    }

    /** Шифрует сообщение, где цифра символа зависит от разницы текущего символа и предыдущего*/
    public static int[] encrypt(String word) {
        int[] encrypted = new int[word.length()];
        encrypted[0] = word.charAt(0);
        int prev = encrypted[0];
        for (int i = 1; i < word.length(); i++) {
            encrypted[i] = word.charAt(i) - prev;
            prev = word.charAt(i);
        }
        return encrypted;
    }

    /** Функция дешифрует сообщения, зашифрованные функцией encrypt*/
    public static String decrypt(int[] encrypted) {
        String decrypted = "" + (char) encrypted[0];
        int prev = encrypted[0];
        for (int i = 1; i < encrypted.length; i++) {
            decrypted += (char) (encrypted[i] + prev);
            prev = encrypted[i] + prev;
        }
        return decrypted;
    }

    /** Шахматы, много шахмат */
    public static boolean canMove(String figure, String start, String end) {
        int startRow = start.charAt(0) - 'A';
        int startCol = start.charAt(1) - '1';
        int endRow = end.charAt(0) - 'A';
        int endCol = end.charAt(1) - '1';
        if (startRow < 0 || startRow > 7 || startCol < 0 || startCol > 7 || endRow < 0 || endRow > 7 || endCol < 0 || endCol > 7)
            return false;
        switch (figure) {
            case "Pawn":
                return startRow == endRow && (endCol - startCol == 1 || (startCol == 1 && endCol - startCol == 2));
            case "Rook":
                return startRow == endRow || startCol == endCol;
            case "Knight":
                return Math.abs(startRow - endRow) == 2 && Math.abs(startCol - endCol) == 1 || Math.abs(startRow - endRow) == 1 && Math.abs(startCol - endCol) == 2;
            case "Bishop":
                return Math.abs(startRow - endRow) == Math.abs(startCol - endCol);
            case "Queen":
                return startRow == endRow || startCol == endCol || Math.abs(startRow - endRow) == Math.abs(startCol - endCol);
            case "King":
                return Math.abs(startRow - endRow) <= 1 && Math.abs(startCol - endCol) <= 1;
            default:
                return false;
        }
    }

    /** Функция проверяет все ли символы строки subword последовательно находятся в строке word*/
    public static boolean canComplete(String subword, String word) {
        int subwordLength = subword.length();
        int subNum = 0;
        for (int i = 0; i < word.length(); i++){
            if (word.charAt(i) == subword.charAt(subNum)) {
                subNum += 1;
            }
        }
        if (subNum == subwordLength){
            return true;
        }
        else {
            return false;
        }
    }

    /** Функция перемножает цифры суммы всех чисел до тех пор, пока не получится однозначное число */
    public static int sumDigProd(int... numbers) {
        int sum = 0;
        for (int i = 0; i < numbers.length; i++) {
            sum += numbers[i];
        }
        while (sum > 9) {
            int prod = 1;
            while (sum > 0) {
                prod *= (sum % 10);
                sum /= 10;
            }
            sum = prod;
        }
        return sum;
    }

    /** Функция выбирает все слова с одинаковым набором гласных */
    public static ArrayList<String> sameVowelGroup(String arr[]) {
        String letters = "aeyuio";
        ArrayList<Character> list1 = new ArrayList<>();
        ArrayList<Character> list2 = new ArrayList<>();
        ArrayList<String> list3 = new ArrayList<>();
        for (int i = 0; i < arr[0].length(); i++) {
            if (letters.indexOf(arr[0].charAt(i)) != -1) {
                list1.add(arr[0].charAt(i));
            }
        }
        Collections.sort(list1);
        for (int i = 1; i < list1.size(); i++) {
            if ((list1.get(i)).equals(list1.get(i-1))) {
                list1.remove(i-1);
            }
        }
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length(); j++) {
                if (letters.indexOf(arr[i].charAt(j)) != -1) {
                    list2.add(arr[i].charAt(j));
                }
                Collections.sort(list2);
                for (int n = 1; n < list2.size(); n++) {
                    if ((list2.get(n)).equals(list2.get(n-1))) {
                        list2.remove(n-1);
                    }
                }
            }
            if (list1.size() == list2.size()) {
                for (int n = 0; n < list2.size(); n++) {
                    if (list1.get(n) != list2.get(n)) {
                        break;
                    }
                    else {
                        list3.add(arr[i]);
                    }
                }
            }
            else break;
            list2.clear();
        }
        for (int i = 1; i < list3.size(); i++) {
            if ((list3.get(i)).equals(list3.get(i-1))) {
                list3.remove(i-1);
            }
        }
        return list3;
    }

    /** Функция для валидации банковских карт */
    public static boolean validateCard(long number) {
        String num = number + "";
        if (num.length() < 14 || num.length() > 19) return false;
        int sum = 0;
        int last = Integer.parseInt(num.charAt(num.length() - 1) + ""); //последняя/контрольная цифра
        for (int i = 0; i < num.length() - 1; i++) {
            int digit = Integer.parseInt(num.charAt(i) + "");
            if (i % 2 == 0) {
                digit *= 2;
                if (digit > 9) digit -= 9;
            }
            sum += digit;
        }
        return (10 - sum % 10) == last;
    }

    /** Функция для преобразования числа в его строковое представление на английском языке */
    public static String numToEng(int n) {
        String s = new String();
        int a, b, c;
        Map<Integer, String> numbers = new HashMap<Integer, String>();
        numbers.put(0, "zero");
        numbers.put(1, "one");
        numbers.put(2, "two");
        numbers.put(3, "three");
        numbers.put(4, "four");
        numbers.put(5, "five");
        numbers.put(6, "six");
        numbers.put(7, "seven");
        numbers.put(8, "eight");
        numbers.put(9, "nine");
        numbers.put(10, "ten");
        numbers.put(11, "eleven");
        numbers.put(12, "twelve");
        numbers.put(13, "thirteen");
        numbers.put(14, "fourteen");
        numbers.put(15, "fifteen");
        numbers.put(16, "sixteen");
        numbers.put(17, "seventeen");
        numbers.put(18, "eighteen");
        numbers.put(19, "nineteen");
        numbers.put(20, "twenty");
        numbers.put(30, "thirty");
        numbers.put(40, "forty");
        numbers.put(50, "fifty");
        numbers.put(60, "sixty");
        numbers.put(70, "seventy");
        numbers.put(80, "eighty");
        numbers.put(90, "ninety");
        numbers.put(100, "hundred");
        if (n >= 0 && n <= 20) {
            s = numbers.get(n);
        }
        if (n > 20 && n < 100) {
            a = n % 10;
            b = (n / 10) * 10;
            if (a != 0) s = numbers.get(b) + " " + numbers.get(a);
            else s = numbers.get(b);
        }
        if (n >= 100 && n <= 999) {
            a = n % 10;
            b = ((n % 100) / 10) * 10;
            c = n / 100;
            if (n % 100 == 0) s = numbers.get(c) + " " + numbers.get(100);
            else if (a == 0) s = numbers.get(c) + " " + numbers.get(100) + " " + numbers.get(b);
            else if (b == 0) s = numbers.get(c) + " " + numbers.get(100) + " " + numbers.get(a);
            else s = numbers.get(c) + " " + numbers.get(100) + " " + numbers.get(b) + " " + numbers.get(a);
        }
        return s;
    }

    public static String numToRus(int n) {
        String s = new String();
        int a, b, c;
        Map<Integer, String> numbers = new HashMap<Integer, String>();
        numbers.put(0, "ноль");
        numbers.put(1, "один");
        numbers.put(2, "два");
        numbers.put(3, "три");
        numbers.put(4, "четыре");
        numbers.put(5, "пять");
        numbers.put(6, "шесть");
        numbers.put(7, "семь");
        numbers.put(8, "восемь");
        numbers.put(9, "девять");
        numbers.put(10, "десять");
        numbers.put(11, "одиннадцать");
        numbers.put(12, "двенадцать");
        numbers.put(13, "тринадцать");
        numbers.put(14, "четырнадцать");
        numbers.put(15, "пятнадцать");
        numbers.put(16, "шестнадцать");
        numbers.put(17, "семнадцать");
        numbers.put(18, "восемнадцать");
        numbers.put(19, "девятнадцать");
        numbers.put(20, "двадцать");
        numbers.put(30, "тридцать");
        numbers.put(40, "сорок");
        numbers.put(50, "пятьдесят");
        numbers.put(60, "шестьдесят");
        numbers.put(70, "семьдесят");
        numbers.put(80, "восемьдесят");
        numbers.put(90, "девяносто");
        numbers.put(100, "сто");
        numbers.put(200, "двести");
        numbers.put(300, "триста");
        numbers.put(400, "четыреста");
        numbers.put(500, "пятьсот");
        numbers.put(600, "шестьсот");
        numbers.put(700, "семьсот");
        numbers.put(800, "восемьсот");
        numbers.put(900, "девятьсот");
        if (n >= 0 && n <= 20) {
            s = numbers.get(n);
        }
        if (n > 20 && n < 100) {
            a = n % 10;
            b = (n / 10) * 10;
            if (a != 0) s = numbers.get(b) + " " + numbers.get(a);
            else s = numbers.get(b);
        }
        if (n >= 100 && n <= 999) {
            a = n % 10;
            b = ((n % 100) / 10) * 10;
            c = n - n % 100;
            if (n % 100 == 0) s = numbers.get(c);
            else if (a == 0) s = numbers.get(c) + " " + numbers.get(b);
            else if (b == 0) s = numbers.get(c) + " " + numbers.get(a);
            else s = numbers.get(c) + " " + numbers.get(b) + " " + numbers.get(a);
        }
        return s;
    }

    /** Функция для получения зашифрованной строки на основе входной при помощи SHA-2 256 */
    public static String getSha256Hash(String s) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");//создание экземпляра класса MessageDigest
        md.update(s.getBytes());//кодирует данную строку в последовательность байтов
        byte[] hash = md.digest();
        StringBuffer sb = new StringBuffer();
        for (byte b : hash) {
            sb.append(String.format("%02x", b & 0xff));
        }
        return sb.toString();
    }

    /** Функция исправляет размеры слов за исключением the, of, in, and */
    public static String correctTitle(String input){
        String[] words = input.split(" ");
        List<String> exceptions = Arrays.stream(new String[]{"the", "of", "in", "and"}).toList();
        for(int i = 0; i < words.length; i++){
            String word = words[i].toLowerCase();
            if(exceptions.contains(word)){
                words[i] = word;
            }
            else{
                words[i] = word.substring(0, 1).toUpperCase() + word.substring(1);
            }
        }
        return String.join(" ", words);
    }

    public static String hexLattice(int num) {
        int cur = 1;
        int i = 0;
        int step = 6;
        // Проверяем является ли число центрированным шестиугольным
        // находим количество итераций
        while (cur < num) {
            i++;
            cur += step * i;
        }
        if (cur == num) {
            String ans = "";
            int space = i;
            // Строки до центра включая
            for (int j = 0; j < i + 1; j++) {
                String line = "";
                line += " ".repeat(space - j);
                String middle = "";
                middle += "o ".repeat(i + 1 + j);
                middle = middle.strip();
                line += middle;
                line += " ".repeat(space - j) + "\n";
                ans += line;
            }
            // Строки от центра
            for (int j = i - 1; j >= 0; j--) {
                String line = "";
                line += " ".repeat(space - j);
                String middle = "";
                middle += "o ".repeat(i + 1 + j);
                middle = middle.strip();
                line += middle;
                line += " ".repeat(space - j) + "\n";
                ans += line;
            }
            return ans;
        } else {
            return "Invalid";
        }
    }
}
