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

    /** ??????????????, ???????????????????????? ???????????????????????????? ?????????? ?????????? ?????? ?????????? n */
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

    /** ?????????????? ?????? ???????????????? ?? ???????????? ????????????*/
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

    /** ?????????????? ?????????????????? ???? ???????????????????? ???????????? ???????????????????? ???????? ?? ?????????????? RGB/RGBA */
    public static boolean validColor(String color){
        //???????????????? ???????????????? ???? ???????????? "rgb".
        if(!color.contains("rgb"))return false;
        boolean isRgba = false;
        //???????????????? ???????????? ???????????????? "rgb" ?????? "rgba"? ?? ?????????? ?????????????? ???????? ?????????? ????????????.
        if(color.contains("rgba")){
            isRgba = true;
            color = color.substring(4);
        } else {
            color = color.substring(3);
        }
        //???????????????? ???????????? ???? ?????????????? ???????????? ?? ?????????? ?????????????? ????.
        if(color.charAt(0) != '(' || color.charAt(color.length() - 1) != ')')return false;
        color = color.substring(1, color.length() - 1);
        //???????????????? ???????????????????? ?????????? ???? ???????????? ??????????.
        String[] numbers = color.split(",");
        //???????????????? ???? ???? ?????? ???????? ???? ?? ?????????????? RGBA, ?? ?????????? ???? ??????.
        if(!isRgba && numbers.length != 3)return false;
        //?????????????? ???????????????? ?????? ???????????? ?????????? ?? ?????????? ???????????????? ???? ????, ?????? ?????? ?? ?????????????????? [0, 255].
        for(int i = 0; i < 3; i++){
            try{
                int num = Integer.parseInt(numbers[i]);
                if(num < 0 || num > 255)return false;
            } catch (NumberFormatException e){
                return false;
            }
        }
        //?????????? ???????? ?? ?????????????? RGBA ???????????????? ???????????????? ?? ?????????????????? ?????????????????? ?????????????? ?????????? ???? ???????????????? [0, 1.0].
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

    /** ?????????????? ?????? ???????????????????? ?????????????????????????? ???????????????????? ???? ???????????? URL, ?? ?????????? ?????? ???????????????? ?????????????????????? ????????????????????*/
    public static String stripUrlParams(String url, String[] ...subParamsToStrip){
        //???????????? ?????? ?? ?????? ?? ???????????? '?' ?????????? ???????????????????? ???????????? url, ???????????? ??????????????????.
        int askIdx = url.indexOf('?');
        String exc = "";
        //???????? ???????????????????? ??????, ???? ?????????????? ?????????? url.
        if(askIdx == -1)return url;
        //???????????????? ???????????? url.
        String cleanUrl = url.substring(0, askIdx);
        //???????????????? ?????????????????? ???????????????? ???????????? ?????????? ???????????? ???? '&'.
        String[] params = url.substring(askIdx + 1).split("&");
        int excludedLength = params.length;
        //?????????????????? ???? ???????????? ???????????? ????????????????????.
        int excludedPtr = 0;
        //?????????????????? ???????????? ????????????????????, ?????? ?????????? - ???????????????????????? ?????????? ????????????????????, ??.?? ??????-???? ???????????????????? + ??????-???? ????????????????????.
        String[] excluded;
        //???????? ???????? ????????????????????.
        if(subParamsToStrip.length != 0){
            //?????????? ?????????????? ???????????????????? ??????????????????????????.
            excludedLength += subParamsToStrip[0].length;
            //???????? ?????????? ???????????????????? ???????????? ??????????????, ???? ?????????????????? ???????????? ??????????????????????.
            if(subParamsToStrip.length > 1) return "Error";
            //?????????????? ???????????? ????????????????????.
            excluded = new String[excludedLength];
            //?????????????????? ???????????????????? ?? ???????????? ????????????????????.
            for(int i = 0; i < subParamsToStrip[0].length; i++){
                excluded[excludedPtr] = subParamsToStrip[0][i];
                excludedPtr++;
            }
        } else {
            //???????? ?????? ????????????????????, ???? ???????????? ???????????????????? ?????????? ????????????.
            excluded = new String[excludedLength];
        }
        StringBuilder answer = new StringBuilder();
        //???????????????????????????? ???????????? ?????? ?????????????????? ??????????????????, ?? ?????????? ???????????????????? ?????? ?????????? ?? ????????????????.
        String paramWord;
        String paramVal;
        //?????????????????? ???? ????????????????????.
        for(int i = 0; i < params.length; i++){
            //???????????????? ???????? ???? ??????????????????.
            paramWord = params[i].split("=")[0];
            //???????? ???????????????? ?????? ?? ??????????????????????, ???? ???????????????????? ??????.
            if(Arrays.asList(excluded).contains(paramWord))continue;
            //???????? ???????????????????????????? + "=" ?? ?????????????? ????????????????????, ?? ??????????, ?????????? ???????????????? ?????????????????? ????????????????.
            String toFind = paramWord + "=";
            //ptr - ?????????????????? ???? ???????????????? ?????????????????? ?? ??????????.
            int ptr = params.length - 1;
            int paramIdx = params[ptr].indexOf(toFind);
            while(ptr > -1 && paramIdx != 0){
                paramIdx = params[ptr].indexOf(toFind);
                ptr--;
            }
            //?????????????????? ?????????????????? ???????????????? ?????????????????? ?? ????????????.
            paramVal = params[ptr].substring(paramIdx + toFind.length());
            //???????? ???????????????? ????????????, ???? ?????????????????? ? ?????????? &.
            if(i == 0)answer.append("?");
            else answer.append("&");
            //?????????????????? ???????????????? ?? ??????????.
            answer.append(paramWord).append("=").append(paramVal);
            //?????????????????? ???????????????? ?? ????????????????????.
            excluded[excludedPtr] = paramWord;
            excludedPtr++;
        }
        answer.insert(0, cleanUrl);
        return answer.toString();
    }

    /** ?????????????? ???????????????????? ??????????, ?????????????????????? ?? ???????????? */
    public static String findWord(String word){
        //???????????????????? ??????????????.
        String allowed = "qwertyuiopasdfghjklzxcvbnm";
        int wordLength = word.length();
        //??????????, ???? ???????????????? ?????????? ?????????????????? ??????????????????.
        String lowerWord = word.toLowerCase();
        //?????????????????? ???? ???????????? ?? ?????????? ??????????.
        int lIdx = 0; 
        int rIdx = wordLength - 1;
        //???????? ?? ???????????? ?????????????? ???????????????????? ??????????.
        while(allowed.indexOf(lowerWord.charAt(rIdx))== -1 && rIdx > -1)rIdx--;
        //???????? ???? ???????????? ?????????????? ?????????????? ??????????.
        while(allowed.indexOf(lowerWord.charAt(lIdx))== -1 && lIdx < wordLength)lIdx++;
        //???????????????????? ??????????.
        return word.substring(lIdx, rIdx + 1);
    }

    /** ?????????????? ???????????????????? ?????? ?????????? ???????????????????????? ?????????? ?? ???????????? ???????????????? */
    public static String[] getHashTags(String seq){
        int MAX_WORDS_COUNT = 3;
        String[] words = seq.split(" ");
        int len = words.length;
        //???????? ???????? ???? ???????????? ????????, ???? ???????????????????? ????.
        if(len == 0)return new String[0];
        if(len == 1)return new String[]{"#" + words[0].toLowerCase()};
        if(len == 2)return new String[]{"#" + words[0].toLowerCase(), "#" + words[1].toLowerCase()};
        //???????????? ?????? ?????????????? ??????????.
        String[] maxWords = new String[MAX_WORDS_COUNT];
        for(int i = 0; i < MAX_WORDS_COUNT; i++){
            maxWords[i] = "";
        }
        //?????????????????? ?? ??????????.
        for (String word : words) {
            word = findWord(word).toLowerCase();
            //???????? ?????????? ???????????? ?????????????? ???? ????????????????, ???? ???????????????? ?????????????????? ?????????? ?? ???????????????????? ??????????.
            if (word.length() > (maxWords[0].length() - 1)) {
                maxWords[2] = maxWords[1];
                maxWords[1] = maxWords[0];
                maxWords[0] = "#" + word;
                //???????? ?????????? ???????????? ??????????????, ???? ???????????????? ?????????? ?????????? ???????? ?? ???????????????????? ??????????.
            } else if (word.length() > (maxWords[1].length() - 1)) {
                maxWords[2] = maxWords[1];
                maxWords[1] = "#" + word;
                //???????? ?????????? ???????????? ????????????????, ???? ???????????????????? ???????? ??????????.
            } else if (word.length() > (maxWords[2].length() - 1)) {
                maxWords[2] = "#" + word;
            }
        }
        return maxWords;
    }

    /** ?????????????? ?????????????????? n-???? ?????????? ???????????????????????????????????? ?????????? */
    public static int ulam(int n){
        //???????? ?????????? ???????????? ????????, ???? ???????????????????? ?????? ????????????????.
        if(n < 1)return 0;
        if(n == 1)return 1;
        if(n == 2)return 2;
        //?????????????? ???????????? ?????? ???????????????? ?????????? ??????????.
        int[] ulamSeq = new int[n];
        //???????????? ???????????? ????????????????.
        ulamSeq[0] = 1;
        ulamSeq[1] = 2;
        int ulamSeqIdx = 2;
        int ulamNum = 3;
        //?????? ?????? ?????????? ???????????? ???????????????????? ???? ?????????? ?? ??????????(?????????? ??????), ???? ???? ???????????????????? ?????????????????????????????? ?????? ?????????? ???? 3 ???? n.
        //?????????????????? ?? ?????????? ???????? ?????????? ?????????? ?????????? ???? ?????????? n.
        while(ulamSeqIdx < n){
            //?????????????? ?????????????????? ?????????????????? ?????????? ?????????????????????? ??????.
            int counter = 0;
            for(int i = 0; i < ulamSeqIdx; i++){
                for(int j = i + 1; j < ulamSeqIdx; j++){
                    if(ulamSeq[i] + ulamSeq[j] == ulamNum)counter++;
                }
            }
            //???????? ???????????? ????????, ???? ?????? ?????????? ??????????, ???????????????????? ?????????? ?? ???????????? ?? ?????????????????????? ????????????.
            if(counter == 1){
                ulamSeq[ulamSeqIdx] = ulamNum;
                ulamSeqIdx++;
            }
            //?????????????????????? ??????.
            ulamNum++;
        }
        return ulamSeq[n - 1];
    }

    /** ??????????????, ?????????????????? ?????????? ??????????????, ?????????????????????????????? ???????????????????? ???????????????????????????????????? ?? ???????????? */
    public static String longestNonrepeatingSubstring(String seq){
        int seqLength = seq.length();
        if(seqLength == 0)return "";
        if(seqLength == 1)return seq;
        //?????????????????????????? ???????????????????? ???????????????????????? ?????????? ???????????????????????????????????? ?? ?????????????? ???? ????????????.
        int maxLen = 1;
        int maxIdx = 0;
        //?????????????????????????? ???????????????????? ?????????????? ?????????? ???????????????????????????????????? ?? ?????????????? ???? ????????????.
        int currLen = 1;
        int currIdx = 0;
        StringBuilder usedChars = new StringBuilder();
        usedChars.append(seq.charAt(0));
        for(int i = 1; i < seqLength; i++){
            //???????????????? ???????????? ???????????????? ???????????????????????? ??????????????.
            int charIdx = usedChars.indexOf(String.valueOf(seq.charAt(i)));
            //???????? ?????? ?????? ?? ?????????????????????? ????????????????????????????????????, ???? ?????????????????? ?????? ???????? ?? ???????? ????????????.
            if(charIdx == -1){
                currLen++;
                usedChars.append(seq.charAt(i));
            } else {
                //???????? ???? ???? ?????? ????????, ???? ???????????????????? ???????????????????????????????????? ??????????????????????, ?? ???? ?????????????????? ???????????????????????? ???? ??????.
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
        //?????????????????? ????????????????, ??.??. ???????????????????????????????????? ?????????? ?????????????????????? ?? ?????????? ????????????.
        if(currLen > maxLen){
            maxLen = currLen;
            maxIdx = currIdx;
        }
        return seq.substring(maxIdx, maxIdx + maxLen);
    }

    /** ?????????????? ?????? ???????????????? ?????????? ?? ???????????? ?????????????? ???????? */
    public static String convertToRoman(int num){
        if(num < 1 || num > 3999)return "Error";
        StringBuilder answer = new StringBuilder();
        int[] nums = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] romans = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        int i = 0;
        //?????????????????? ???? ?????????????? ?????????? ?????????????? ??????????????, ???????????? ??????.
        while(num > 0){
            /*
            * ???????? ?????????? ???????????? ???????????????? ??????, ???? ?????????????? ?????????? ???? ?????? ?????????????????????? ?? ????????????, ??
            * ???? ?????????? ???????????????????? ??????, ???????? ???? ?????????? ???????????? ??????, ???? ?????????????????? ?? ???????????????????? ??????.
            */
            if(num >= nums[i]){
                answer.append(romans[i]);
                num -= nums[i];
            } else i++;
        }
        return answer.toString();
    }

    /** ?????????????? ?????????????????? ???????????? ?? ???????????????? ???? ???????????????????????????? ??????????????????????????*/
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

    /** ?????????????? ?????????????????? ???????????????? ???? ????????????, ???????????????????? ???????????? ??????????, ?????????????????????? */
    public static boolean isPalindrome(String palindrome){
        if(palindrome.length() == 0)return false;
        if(palindrome.length() % 2 != 0)return false;
        return palindrome.substring(0, palindrome.length() / 2).equals(new StringBuilder(palindrome.substring(palindrome.length() / 2)).reverse().toString());
    }

    /** ?????????????? ?????????????????? ???????????????? ???? ?????????? ?????? ?????? ?????????????? ?????????????????????? */
    public static boolean palindromedescendant(int digit){
        //?????????????????????? ?????????? ?? ????????????.
        String num = String.valueOf(digit);
        //???????? ???????????? ???????????????? ????????????, ???? ?????? ??????????????.
        if(num.length() % 2 != 0)return false;
        StringBuilder nw = new StringBuilder();
        //???????? ???? ????????????
        while(num.length() > 1){
            //???????????????? ???? ??????????????????.
            if(isPalindrome(num))return true;
            //???????????????? ???? ?????????? ?? ?????????? 2.
            for(int i = 0; i < num.length() / 2; i++){
                //???????????????? ?????????? ?????????? ?????? ???????? 2.
                int first = Character.getNumericValue(num.charAt(i * 2));
                //???????????????? ???????????? ?????????? ?????? ???????? 2.
                int second = Character.getNumericValue(num.charAt(i * 2 + 1));
                //?????????????????? ?????????? ?????????? ?? ????????????.
                nw.append(first + second);
            }
            //?????????????????? ???????????????????????? ???????????? ?? num.
            num = nw.toString();
            //?????????????? ????????????.
            nw = new StringBuilder();
        }
        return false;
    }
}
