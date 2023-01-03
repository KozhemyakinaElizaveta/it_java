import java.util.Arrays;

public class task6 {
    public static void main(String[] args) throws Exception{
        task_name(1, "bell");
        System.out.println(bell(1));
        System.out.println(bell(2));
        System.out.println(bell(3));
        System.out.println(bell(4));

        task_name(2, "translateWord");
        System.out.println(translateWord("flag"));
        System.out.println(translateWord("Apple"));
        System.out.println(translateWord("button"));
        System.out.println(translateWord(""));
        System.out.println(translateSentence("I like to eat honey waffles."));
        System.out.println(translateSentence("Do you think it is going to rain today?"));

        task_name(3, "validColor");
        System.out.println(validColor("rgb(0,0,0)"));
        System.out.println(validColor("rgb(0,,0)"));  
        System.out.println(validColor("rgb(255,256,255)")); 
        System.out.println(validColor("rgba(0,0,0,0.123456789)"));

        task_name(4,  "stripUrlParams");
        System.out.println(stripUrlParams("https://edabit.com?a=1&b=2&a=2"));
        System.out.println(stripUrlParams("https://edabit.com?a=1&b=2&a=2", new String[] {"b"}));
        System.out.println(stripUrlParams("https://edabit.com", new String[] {"b"}));

        task_name(5, "getHashTags");
        System.out.println(Arrays.toString(getHashTags("How the Avocado Became the Fruit of the Global Trade")));
        System.out.println(Arrays.toString(getHashTags("Why You Will Probably Pay More for Your Christmas Tree This Year")));
        System.out.println(Arrays.toString(getHashTags("Hey Parents, Surprise, Fruit Juice Is Not Fruit")));
        System.out.println(Arrays.toString(getHashTags("Visualizing Science")));

        task_name(6, "ulam");
        System.out.println(ulam(4));
        System.out.println(ulam(9));
        System.out.println(ulam(206)); 

        task_name(7, "longestNonrepeatingSubstring");
        System.out.println(longestNonrepeatingSubstring("abcabcbb"));
        System.out.println(longestNonrepeatingSubstring("aaaaaa"));
        System.out.println(longestNonrepeatingSubstring("abcde"));
        System.out.println(longestNonrepeatingSubstring("abcda"));

        task_name(8, "convertToRoman");
        System.out.println(convertToRoman(2));
        System.out.println(convertToRoman(12));
        System.out.println(convertToRoman(16));
        System.out.println(convertToRoman(66));
        System.out.println(convertToRoman(46));
        System.out.println(convertToRoman(996));
        System.out.println(convertToRoman(2499));

        task_name(9, "formula");
        System.out.println(formula("6 * 4 = 24"));
        System.out.println(formula("18 / 17 = 2")); 
        System.out.println(formula("16 * 10 = 160 = 14 + 120"));

        task_name(10, "palindromedescendant");
        System.out.println(palindromedescendant(11211230));
        System.out.println(palindromedescendant(13001120));
        System.out.println(palindromedescendant(23336014));
        System.out.println(palindromedescendant(1211));
    }

    private static void task_name(int task, String name) {
        System.out.println("--- Task #" + task + " --- " + name + " ---");
    }

    /** Функция, возвращающая соответсвующее число Белла для числа n */
    public static int bell(int n) {
        int[] arr1 = new int[n];
        int[] arr2 = new int[n];
        int b = 1;
        arr1[0] = 1;
        for (int i = 1; i < n; i++) {
            arr2[0] = b;
            for (int j = 1; j < i + 1; j++) {
                arr2[j] = b + arr1[j-1];
                b = arr2[j];
            }
            b = arr2[i];
            for (int j = 0; j < n; j++) {
                arr1[j] = arr2[j];
            }
        }
        return b;
    }

    /** Функция для перевода в свиную латынь*/
    public static String translateWord(String s) {
        String letters = "aeyuioAEYUIO";
        //String str = new String();
        int n = 0;
        if (s.isEmpty()) {
            return "";
        }
        if (letters.indexOf(s.charAt(0)) != -1) {
            s += "yay";
        }
        else {
            while (letters.indexOf(s.charAt(n)) == -1) {
                n++;
            }
            if (Character.isUpperCase(s.charAt(0))) {
                s = s.substring(n, n + 1).toUpperCase() + s.substring(n + 1) + s.substring(0, n).toLowerCase() + "ay";
            } 
            else s = s.substring(n) + s.substring(0, n) + "ay";
        }
        return s;
    }

    public static String translateSentence(String s) {
        String sign = s.substring(s.length()-1);
        String str = new String();
        String[] arr = s.substring(0,s.length()-1).split(" ");
        str = translateWord(arr[0]);
        for (int i = 1; i < arr.length; i++) {
            str += " " + translateWord(arr[i]);
        }
        return str + sign;
    }

    /** Функция проверяет на валидность строку содержащую цвет в формате RGB/RGBA */
    public static boolean validColor(String color){
        //Проверка содержит ли строка "rgb".
        if(!color.contains("rgb"))return false;
        boolean isRgba = false;
        //Проверка строка содержит "rgb" или "rgba"? И затем обрезка этой части строки.
        if(color.contains("rgba")){
            isRgba = true;
            color = color.substring(4);
        } else {
            color = color.substring(3);
        }
        //Проверка строки на наличие скобок и затем обрезка их.
        if(color.charAt(0) != '(' || color.charAt(color.length() - 1) != ')')return false;
        color = color.substring(1, color.length() - 1);
        //Разборка оставшихся чисел на массив строк.
        String[] numbers = color.split(",");
        //Проверка на то что цвет не в формате RGBA, а чисел не три.
        if(!isRgba && numbers.length != 3)return false;
        //Попытка спарсить три первых числа и сразу проверка на то, что они в интервале [0, 255].
        for(int i = 0; i < 3; i++){
            try{
                int num = Integer.parseInt(numbers[i]);
                if(num < 0 || num > 255)return false;
            } catch (NumberFormatException e){
                return false;
            }
        }
        //Когда цвет в формате RGBA пытаемся спарсить и проверить четвёртое дробное число на интервал [0, 1.0].
        if(numbers.length == 4){
            try{
                double num = Double.parseDouble(numbers[3]);
                if(num < 0 || num > 1)return false;
            } catch (NumberFormatException e){
                return false;
            }
        }
        return true;
    }

    /** Фукнция для исключения повторяющихся параметров из строки URL, а также для удаления исключённых параметров*/
    public static String stripUrlParams(String url, String[] ...subParamsToStrip){
        //Узнаём где у нас в строке '?' слева получается чистый url, справа параметры.
        int askIdx = url.indexOf('?');
        String exc = "";
        //Если параметров нет, то ответом будет url.
        if(askIdx == -1)return url;
        //Получаем чистый url.
        String cleanUrl = url.substring(0, askIdx);
        //Получаем параметры разбивая правую часть строки по '&'.
        String[] params = url.substring(askIdx + 1).split("&");
        int excludedLength = params.length;
        //Указатель на пустую ячейку исключений.
        int excludedPtr = 0;
        //Объявляем массив исключений, его длина - максимальное число исключений, т.е кол-во параметров + кол-во исключений.
        String[] excluded;
        //Если есть исключения.
        if(subParamsToStrip.length != 0){
            //Длина массива исключений увеличивается.
            excludedLength += subParamsToStrip[0].length;
            //Если длина исключений больше единицы, то параметры заданы неправильно.
            if(subParamsToStrip.length > 1) return "Error";
            //Создаём массив исключений.
            excluded = new String[excludedLength];
            //Добавляем исключения в массив исключений.
            for(int i = 0; i < subParamsToStrip[0].length; i++){
                excluded[excludedPtr] = subParamsToStrip[0][i];
                excludedPtr++;
            }
        } else {
            //Если нет исключения, то массив исключений будет пустым.
            excluded = new String[excludedLength];
        }
        StringBuilder answer = new StringBuilder();
        //Инициализируем массив для разбиения параметра, а также переменную для ключа и значения.
        String paramWord;
        String paramVal;
        //Итерируем по параметрам.
        for(int i = 0; i < params.length; i++){
            //Получаем ключ из параметра.
            paramWord = params[i].split("=")[0];
            //Если параметр уже в исключениях, то пропускаем его.
            if(Arrays.asList(excluded).contains(paramWord))continue;
            //Ищем СловоПараметра + "=" в массиве параметров, с конца, чтобы получить последнее значение.
            String toFind = paramWord + "=";
            //ptr - указатель на значение параметра с конца.
            int ptr = params.length - 1;
            int paramIdx = params[ptr].indexOf(toFind);
            while(ptr > -1 && paramIdx != 0){
                paramIdx = params[ptr].indexOf(toFind);
                ptr--;
            }
            //Сохраняем последнее значение параметра в строку.
            paramVal = params[ptr].substring(paramIdx + toFind.length());
            //Если параметр первый, то добавляем ? иначе &.
            if(i == 0)answer.append("?");
            else answer.append("&");
            //Добавляем параметр в ответ.
            answer.append(paramWord).append("=").append(paramVal);
            //Добавляем параметр в исключения.
            excluded[excludedPtr] = paramWord;
            excludedPtr++;
        }
        answer.insert(0, cleanUrl);
        return answer.toString();
    }

    /** Функция возвращает слово, находящееся в строке */
    public static String findWord(String word){
        //Допущенные символы.
        String allowed = "qwertyuiopasdfghjklzxcvbnm";
        int wordLength = word.length();
        //Слово, по которому будет проходить обработка.
        String lowerWord = word.toLowerCase();
        //Указатель на начало и конец слова.
        int lIdx = 0; 
        int rIdx = wordLength - 1;
        //Ищем с какого индекса начинается слово.
        while(allowed.indexOf(lowerWord.charAt(rIdx))== -1 && rIdx > -1)rIdx--;
        //Ищем до какого индекса длиться слово.
        while(allowed.indexOf(lowerWord.charAt(lIdx))== -1 && lIdx < wordLength)lIdx++;
        //Возвращаем слово.
        return word.substring(lIdx, rIdx + 1);
    }

    /** Функция возвращает три слова максимальной длины в нижнем регистре */
    public static String[] getHashTags(String seq){
        int MAX_WORDS_COUNT = 3;
        String[] words = seq.split(" ");
        int len = words.length;
        //Если слов не больше трёх, то возвращаем их.
        if(len == 0)return new String[0];
        if(len == 1)return new String[]{"#" + words[0].toLowerCase()};
        if(len == 2)return new String[]{"#" + words[0].toLowerCase(), "#" + words[1].toLowerCase()};
        //Храним три больших слова.
        String[] maxWords = new String[MAX_WORDS_COUNT];
        for(int i = 0; i < MAX_WORDS_COUNT; i++){
            maxWords[i] = "";
        }
        //Итерируем в цикле.
        for (String word : words) {
            word = findWord(word).toLowerCase();
            //Если слово больше первого по величине, то сдвигаем остальные слова и записываем новое.
            if (word.length() > (maxWords[0].length() - 1)) {
                maxWords[2] = maxWords[1];
                maxWords[1] = maxWords[0];
                maxWords[0] = "#" + word;
                //Если слово больше второго, то сдвигаем слово после него и записываем новое.
            } else if (word.length() > (maxWords[1].length() - 1)) {
                maxWords[2] = maxWords[1];
                maxWords[1] = "#" + word;
                //Если слово меньше третьего, то записываем туда новое.
            } else if (word.length() > (maxWords[2].length() - 1)) {
                maxWords[2] = "#" + word;
            }
        }
        return maxWords;
    }

    /** Функция находящая n-ое число последовательности улама */
    public static int ulam(int n){
        //Если число меньше трёх, то возвращаем его значение.
        if(n < 1)return 0;
        if(n == 1)return 1;
        if(n == 2)return 2;
        //Создаём массив для хранения чисел улама.
        int[] ulamSeq = new int[n];
        //Вносим первые элементы.
        ulamSeq[0] = 1;
        ulamSeq[1] = 2;
        int ulamSeqIdx = 2;
        int ulamNum = 3;
        //Так как сумма всегда возрастает от числа к числу(Далее СЧУ), то мы перебираем последовательно все числа от 3 до n.
        //Итерируем в цикле пока номер числа улана не будет n.
        while(ulamSeqIdx < n){
            //Считаем сколькими способами можно представить СЧУ.
            int counter = 0;
            for(int i = 0; i < ulamSeqIdx; i++){
                for(int j = i + 1; j < ulamSeqIdx; j++){
                    if(ulamSeq[i] + ulamSeq[j] == ulamNum)counter++;
                }
            }
            //Если способ один, то это число улама, записываем число в массив и увеличиваем индекс.
            if(counter == 1){
                ulamSeq[ulamSeqIdx] = ulamNum;
                ulamSeqIdx++;
            }
            //Увеличиваем СЧУ.
            ulamNum++;
        }
        return ulamSeq[n - 1];
    }

    /** Функция, находящая самую длинную, неповторяющуюся символьную последовательность в строке */
    public static String longestNonrepeatingSubstring(String seq){
        int seqLength = seq.length();
        if(seqLength == 0)return "";
        if(seqLength == 1)return seq;
        //Инициализация переменных максимальной длины последовательности и индекса её начала.
        int maxLen = 1;
        int maxIdx = 0;
        //Инициализация переменных текущей длины последовательности и индекса её начала.
        int currLen = 1;
        int currIdx = 0;
        StringBuilder usedChars = new StringBuilder();
        usedChars.append(seq.charAt(0));
        for(int i = 1; i < seqLength; i++){
            //Получаем индекс текущего итерируемого символа.
            int charIdx = usedChars.indexOf(String.valueOf(seq.charAt(i)));
            //Если его нет в сохранённой последовательности, то добавляем его туда и идём дальше.
            if(charIdx == -1){
                currLen++;
                usedChars.append(seq.charAt(i));
            } else {
                //Если же он там есть, то уникальная последовательность закончилась, и мы сохраняем максимальную из них.
                if(currLen > maxLen){
                    maxLen = currLen;
                    maxIdx = currIdx;
                }
                currLen = 1;
                currIdx = i;
                usedChars = new StringBuilder();
                usedChars.append(seq.charAt(i));
            }
        }
        //Последняя проверка, т.к. последовательность может закончиться в конце строки.
        if(currLen > maxLen){
            maxLen = currLen;
            maxIdx = currIdx;
        }
        return seq.substring(maxIdx, maxIdx + maxLen);
    }

    /** Функция для перевода числа в строку римских цифр */
    public static String convertToRoman(int num){
        if(num < 1 || num > 3999)return "Error";
        StringBuilder answer = new StringBuilder();
        int[] nums = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] romans = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        int i = 0;
        //Итерируем по массиву чисел кратным римским, дальше ЧТР.
        while(num > 0){
            /*
            * Если число больше текущего ЧТР, то римское число по ЧТР добавляется в строку, а
            * из числа вычитается ЧТР, если же число меньше ЧТР, то переходим к следующему ЧТР.
            */
            if(num >= nums[i]){
                answer.append(romans[i]);
                num -= nums[i];
            } else i++;
        }
        return answer.toString();
    }

    /** Функция проверяет строку с формулой на математическую достоверность*/
    public static boolean formula(String str) {
        String[] arr = str.split(" [+-/=\\*] ");
        if (str.substring(str.indexOf("=") + 1).contains("=")) {
            return false;
        }
        int a = Integer.parseInt(arr[0]);
        int b = Integer.parseInt(arr[1]);
        int c = Integer.parseInt(arr[2]);
        if (a + b == c) {
            return true;
        }
        else if (a - b == c) {
            return true;
        }
        else if (a * b == c) {
            return true;
        }
        else if (a / b == c) {
            return true;
        }
        return false;
    }

    /** Функция проверяет является ли строка, содержащая чётное число, палиндромом */
    public static boolean isPalindrome(String palindrome){
        if(palindrome.length() == 0)return false;
        if(palindrome.length() % 2 != 0)return false;
        return palindrome.substring(0, palindrome.length() / 2).equals(new StringBuilder(palindrome.substring(palindrome.length() / 2)).reverse().toString());
    }

    /** Функция проверяет является ли число или его потомок палиндромом */
    public static boolean palindromedescendant(int digit){
        //Преобразуем число в строку.
        String num = String.valueOf(digit);
        //Если строка нечётная длиной, то уже неверно.
        if(num.length() % 2 != 0)return false;
        StringBuilder nw = new StringBuilder();
        //Цикл по строке
        while(num.length() > 1){
            //Проверка на палиндром.
            if(isPalindrome(num))return true;
            //Итерация по числу с шагом 2.
            for(int i = 0; i < num.length() / 2; i++){
                //Получаем левую цифру при шаге 2.
                int first = Character.getNumericValue(num.charAt(i * 2));
                //Получаем правую цифру при шаге 2.
                int second = Character.getNumericValue(num.charAt(i * 2 + 1));
                //Добавляем новое число в строку.
                nw.append(first + second);
            }
            //Переносим получившуюся строку в num.
            num = nw.toString();
            //Очищаем строку.
            nw = new StringBuilder();
        }
        return false;
    }
}
