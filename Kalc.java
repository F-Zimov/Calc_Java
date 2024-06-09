import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int x = 0, y = 0;
        String s, o = "";
        Scanner scanner = new Scanner(System.in);
        s = scanner.nextLine();
        int count = s.length();
        if (count < 3) {
            throw new RuntimeException("Строка не является математической операцией.");
        }
        if (s.charAt(0) == 'I' || s.charAt(0) == 'V' || s.charAt(0) == 'X') {
            rom(count, s);
            return;
        } else {
            if (s.charAt(0) > '0' && s.charAt(0) <= '9') {
                arabic(count, s);
                return;
            } else {
                throw new RuntimeException("Неккоректный ввод данных");
            }
        }
    }

    public static int romnummer(String x1) {
        switch (x1) {
            case "I":
                return 1;
            case "II":
                return 2;
            case "III":
                return 3;
            case "IV":
                return 4;
            case "V":
                return 5;
            case "VI":
                return 6;
            case "VII":
                return 7;
            case "VIII":
                return 8;
            case "IX":
                return 9;
            case "X":
                return 10;
            default:
                return 101;
        }
    }

    public static String nummerrom(int result) {
        int g = 0;
        String c = "";
        if (result < 1) {
            throw new RuntimeException("Результат меньше еденицы!");
        }
        if (result / 100 > 0) {
            c += "C";
            result = result % 100;
        }
        if (result / 50 > 0) {
            c += "L";
            result = result % 50;
        }
        if (result / 10 > 0) {
            for (g = 1; g <= result / 10; g++) {
                c += "X";
            }
            result = result % 10;
        }
        if (result / 5 > 0) {
            c += "V";
            result -= 5;
        }
        if (result > 0) {
            for (g = 1; g <= result; g++) {
                c += "I";
            }
        }
        return c;
    }

    public static void arabic(int count, String s) {
        int j = 0, a = 0, x = 0, y = 0;
        String o = "";
        int result = 0;
        while (j < count) {
            if (s.charAt(j) >= '0' && s.charAt(j) <= '9' && j < 2) {
                x = x * 10 + (s.charAt(j) - '0');
            } else {
                if (s.charAt(j) >= '0' && s.charAt(j) <= '9' && j > 1) {
                    y = y * 10 + (s.charAt(j) - '0');
                }
                if (s.charAt(j) == '+' || s.charAt(j) == '-' || s.charAt(j) == '*' || s.charAt(j) == '/') {
                    o = String.valueOf(s.charAt(j));
                    a++;
                } else {
                    if (s.charAt(j) == 'I' || s.charAt(j) == 'V' || s.charAt(j) == 'X') {
                        throw new RuntimeException("Используются одновременно разные системы счисления.");
                    }
                }
            }
            j++;
        }
        if (x < 0 || x > 10 || y < 0 || y > 10) {
            throw new RuntimeException("Неккоректный ввод данных");
        }
        if (a > 1) {
            throw new RuntimeException("Формат математической операции не удовлетворяет заданию");
        }
        result = calc(x, y, o);
        if (result <= 100) {
            System.out.print(result);
        } else {
            return;
        }
    }

    public static void rom(int count, String s) {
        int k = 0, a = 0, b = 0;
        String o = "", x1 = "", y1 = "";
        while (k < count) {
            if ((s.charAt(k) == 'X' || s.charAt(k) == 'I' || s.charAt(k) == 'V') && b == 0) {
                x1 += s.charAt(k);
            } else {
                if ((s.charAt(k) == 'X' || s.charAt(k) == 'I' || s.charAt(k) == 'V') && b >= 1) {
                    y1 += s.charAt(k);
                } else {
                    if (s.charAt(k) == '+' || s.charAt(k) == '-' || s.charAt(k) == '*' || s.charAt(k) == '/') {
                        o = String.valueOf(s.charAt(k));
                        a++;
                    } else {
                        if (s.charAt(k) == ' ') {
                            b++;
                        } else {
                            if (s.charAt(k) >= '0' && s.charAt(k) <= '9') {
                                throw new RuntimeException("Используются одновременно разные системы счисления.");
                            }
                        }
                    }
                }
            }
            k++;
        }
        if (a > 1) {
            throw new RuntimeException("Формат математической операции не удовлетворяет заданию.");
        }
        int x = romnummer(x1);
        int y = romnummer(y1);
        if (x > 10 || y > 10) {
            throw new RuntimeException("Некорректный ввод данных.");
        }
        int result = calc(x, y, o);
        if (result < 0) {
            throw new RuntimeException("В римской системе нет отрицательных чисел.");
        } else {
            if (result == 101) {
                throw new RuntimeException("Некорректный ввод данных.");
            } else {
                System.out.println(nummerrom(result));
            }
        }
    }

    public static int calc(int x, int y, String o) {
        switch (o) {
            case "+":
                return x + y;
            case "-":
                return x - y;
            case "*":
                return x * y;
            case "/":
                if (y == 0) {
                    throw new RuntimeException("На ноль делить нельзя!");
                }
                return x / y;
            default:
                return 101;
        }
    }
}


