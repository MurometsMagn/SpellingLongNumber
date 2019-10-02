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
        } else if (inputNumber < 0) {
            outputData += "minus ";
        }
        long correctableInputNumber = Math.abs(inputNumber); //в дальнейшем изменяем только это число


        List<Integer> digits = new ArrayList<>();
        while (correctableInputNumber != 0) {
            digits.add((int) (correctableInputNumber % 10));
            correctableInputNumber /= 10;
        }


        //почему не получились константы?
        final String spellingOnesList[] = new String[]{"", "one ", "two ", "three ", "four ", "five ", "six ", "seven ",
                "eight ", "nine ", "ten ", "eleven ", "twelve ", "thirteen ", "fourteen ", "fifteen ", "sixteen ",
                "seventeen ", "eighteen ", "nineteen "};
        final String spellingTensList[] = new String[]{"", "", "twenty ", "thirty ", "forty ", "fifty ", "sixty ",
                "seventy ", "eighty ", "ninety "};
        final String spellingThousansList[] = new String[]{"", "", "thousand and ", "million and ", "billion and ",
                "trillion and ", "quadrillion and "};


        //создать переменную "количество троек, в т.ч. неполных", неполные наполнить нолями (максимум две)
        //число разбиваем  на разряды по три цифры: единицы, десятки, сотни
        int thousandsSeparators = digits.size() / 3;
        if ((digits.size() % 3) > 0) {// одна или две значащих цифры в разряде
            thousandsSeparators++;
            digits.add(0);
            digits.add(0);
        }

        for (int i = thousandsSeparators; i > 0; i--) {
            int ones = digits.get(i * 3 - 3);
            int tens = digits.get(i * 3 - 2);
            int hundreds = digits.get(i * 3 - 1);

            if (hundreds != 0) {
                outputData = outputData.concat(spellingOnesList[hundreds] + "hundred ");
            }
            if ((tens == 0 && ones != 0) || tens == 1) {
                outputData = outputData.concat(spellingOnesList[tens * 10 + ones]);
            } else if (tens >= 2 && tens <= 9) {
                outputData = outputData.concat(spellingTensList[tens] + spellingOnesList[ones]);
            }

            if (ones != 0 || tens != 0 || hundreds != 0) {
                outputData = outputData.concat(spellingThousansList[i]);
            }
        }

        outputData = outputData.trim();
        String[] words = outputData.split(" ");
        String lastWord = words[words.length - 1];
        if (lastWord.equals("and")) {
            words[words.length - 1] = "";
            outputData = "";
            for (String word : words) {
                outputData = outputData.concat(word + " ");
            }
        }

        System.out.println(outputData);
    }
}
