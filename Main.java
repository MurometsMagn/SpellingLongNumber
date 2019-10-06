import java.util.*;

/**
 * Created by Smolentsev Il'ya on 27.09.2019.
 */

/**
 * Задача:
 * Пользователь вводит чсло (любое, которое можно записать в тип long).
 * Программа должна выдать запись этого числа на английском языке.
 * Пример:
 * Входные данные:
 * 1234
 * Выходные данные:
 * one thousand two hundred thirty four
 */


public class Main {
    public static void main(String[] args) {

        long inputNumber = 0;
        String outputData = "";


        Scanner input = new Scanner(System.in);
        boolean continueInput = true; //флаг корректности ввода
        do {
            try {
                System.out.print("Enter an integer: ");
                inputNumber = input.nextLong();
                System.out.println("The number entered is " + inputNumber);
                continueInput = false;
            } catch (InputMismatchException ex) {
                System.out.println("Try again. (" + "Incorrect input: an integer is required)");
                input.nextLine(); //при некорректном вводе -> для перевода курсора с ошибочной позиции
            }
        } while (continueInput);


        if (inputNumber == 0) {
            System.out.println("zero");
            System.exit(0);
        }
        long correctableInputNumber = Math.abs(inputNumber); //в дальнейшем изменяем только это число


        //почему не получились константы?
        final String spellingOnesList[] = new String[]{"", "one ", "two ", "three ", "four ", "five ", "six ", "seven ",
                "eight ", "nine ", "ten ", "eleven ", "twelve ", "thirteen ", "fourteen ", "fifteen ", "sixteen ",
                "seventeen ", "eighteen ", "nineteen "};
        final String spellingTensList[] = new String[]{"", "", "twenty ", "thirty ", "forty ", "fifty ", "sixty ",
                "seventy ", "eighty ", "ninety "};
        final String spellingThousansList[] = new String[]{"", "thousand ", "million ", "billion ",
                "trillion ", "quadrillion "};


        //проход по числу тройками справа налево.
        //как лучше оформить цикл? По итератору i или по correctableInputNumber?
        boolean isAnd = false;
        for (int i = 0; correctableInputNumber > 0; i++, correctableInputNumber /= 1000) {
            int threeDigit;
            String strThreeDigit = "";

            if (correctableInputNumber >= 1000) {
                threeDigit = (int) correctableInputNumber % 1000;
            } else {
                threeDigit = (int) correctableInputNumber;
            }

            int hundreds = (threeDigit - (threeDigit % 100)) / 100;
            int tens = (threeDigit - hundreds * 100 - (threeDigit % 10)) / 10;
            int ones = threeDigit - hundreds * 100 - tens * 10;

            if (hundreds != 0) {
                strThreeDigit = strThreeDigit.concat(spellingOnesList[hundreds] + "hundred ");
            }

            if ((tens == 0 && ones != 0) || tens == 1) {
                strThreeDigit = strThreeDigit.concat(spellingOnesList[tens * 10 + ones]);
            } else if (tens >= 2 && tens <= 9) {
                strThreeDigit = strThreeDigit.concat(spellingTensList[tens] + spellingOnesList[ones]);
            }

            if (ones != 0 || tens != 0 || hundreds != 0) {
                strThreeDigit = strThreeDigit.concat(spellingThousansList[i]);
            }

            if (isAnd) {
                strThreeDigit = strThreeDigit.concat("and ");
            }
            if (threeDigit != 0) {
                isAnd = true;
            } else isAnd = false;

            outputData = strThreeDigit.concat(outputData);
        }

        if (inputNumber < 0) outputData = "minus " + outputData; //конкатенацию?

        System.out.println(outputData);
    }
}
