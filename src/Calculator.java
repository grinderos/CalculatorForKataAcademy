import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String in = scanner.nextLine();
        System.out.println(Main.calc(in));
    }
}

class Main {
    static final String[] romNums = {
            ".", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X",
            "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX",
            "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX",
            "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL",
            "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L",
            "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX",
            "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX",
            "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX",
            "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC",
            "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"
    };

    public static String calc(String in) {
        String operation;
        Integer resultInt = 0;
        boolean ifRomans = false;
        List<Integer> numsList = new ArrayList<>();
        try {
            if (ifRomans = ifRomans(in)) {
                in = romansToArabs(in);
            }
            String line = in.replaceAll(" ", "");
            String regexSplitter = "\\d+";
            Pattern pat = Pattern.compile(regexSplitter);
            Matcher mat = pat.matcher(line);
            while (mat.find()) {
                numsList.add(Integer.parseInt(mat.group()));
            }
            operation = line.replaceAll(regexSplitter, "");
            if (numsList.size() != 2 || numsList.get(0) <= 0 || numsList.get(0) > 10
                    || numsList.get(0) <= 0 || numsList.get(0) > 10) {
                throw new Exception();
            }
            resultInt = doMathOperation(operation, numsList);
            if (ifRomans && resultInt <= 0 || resultInt == null) {
                throw new Exception();
            } else if (ifRomans) {
                return getRomanNum(resultInt);
            } else {
                return String.valueOf(resultInt);
            }
        } catch (Exception e) {
            System.out.print("throw Exception");
            return "";
        }
    }

    static Integer doMathOperation(String operation, List<Integer> nums) {
        switch (operation) {
            case "+":
                return nums.get(0) + nums.get(1);
            case "-":
                return nums.get(0) - nums.get(1);
            case "*":
                return nums.get(0) * nums.get(1);
            case "/":
                return nums.get(0) / nums.get(1);
            default:
                return null;
        }
    }

    static boolean ifRomans(String in) {
        String regex = "[IVXLC]";
        Pattern pat = Pattern.compile(regex);
        Matcher mat = pat.matcher(in);
        while (mat.find()) {
            return true;
        }
        return false;
    }

    static String romansToArabs(String in) {
        StringBuilder out = new StringBuilder();
        String line = in.replaceAll(" ", "");
        String regex = "[+\\-/*]";
        String[] nums = line.split(regex);
        Pattern pat = Pattern.compile(regex);
        Matcher mat = pat.matcher(line);
        while (mat.find()) {
            return getArabNum(nums[0]) + mat.group() + getArabNum(nums[1]);
        }
        return null;
    }

    static String getArabNum(String in) {
        return String.valueOf(Arrays.asList(romNums).indexOf(in));
    }

    static String getRomanNum(Integer in) {
        if (in <= 0 || in > 100) {
            return null;
        } else {
            return romNums[in];
        }
    }
}