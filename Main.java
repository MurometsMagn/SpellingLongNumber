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


        Map<Integer, String> spellingMap = new HashMap<>();
        spellingMap.put(1, "one ");
        spellingMap.put(2, "two ");
        spellingMap.put(3, "three ");
        spellingMap.put(4, "four ");
        spellingMap.put(5, "five ");
        spellingMap.put(6, "six ");
        spellingMap.put(7, "seven ");
        spellingMap.put(8, "eight ");
        spellingMap.put(9, "nine ");
        spellingMap.put(10, "ten ");
        spellingMap.put(11, "eleven ");
        spellingMap.put(12, "twelve ");
        spellingMap.put(13, "thirteen ");
        spellingMap.put(14, "fourteen ");
        spellingMap.put(15, "fifteen ");
        spellingMap.put(16, "sixteen ");
        spellingMap.put(17, "seventeen ");
        spellingMap.put(18, "eighteen ");
        spellingMap.put(19, "nineteen ");
        spellingMap.put(20, "twenty ");
        spellingMap.put(30, "thirty ");
        spellingMap.put(40, "forty ");
        spellingMap.put(50, "fifty ");
        spellingMap.put(60, "sixty ");
        spellingMap.put(70, "seventy ");
        spellingMap.put(80, "eighty ");
        spellingMap.put(90, "ninety ");


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
                outputData = outputData.concat(spellingMap.get(hundreds) + "hundred ");
            }
            if ((tens == 0 && ones != 0) || tens == 1) {
                outputData = outputData.concat(spellingMap.get(tens * 10 + ones));
            } else if (tens >= 2 && tens <= 9) {
                outputData = outputData.concat(spellingMap.get(tens * 10) + spellingMap.get(ones));
            }

            //триллион, квадриллион, квинтиллион, секстиллион, септиллион, октиллион, нониллион и дециллион
            if (i == 6) outputData += "quadrillion ";
            else if (i == 5) outputData += "trillion ";
            else if (i == 4) outputData += "billion ";
            else if (i == 3) outputData += "million ";
            else if (i == 2) outputData += "thousand ";
        }
        System.out.println(outputData);
    }
}
