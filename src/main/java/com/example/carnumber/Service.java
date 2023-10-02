package com.example.carnumber;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@org.springframework.stereotype.Service
public class Service {

    private final char[] carLetters = {'А', 'В', 'Е', 'К', 'М', 'Н', 'О', 'Р', 'С', 'Т', 'У', 'Х'};
    private final int MIN_VALUE_NUMBER_CAR = 0;
    private final int MAX_VALUE_NUMBER_CAR = 10;

    private static final int NINETY_NINE = 99;
    private static final String region = " 116 RUS";
    private final String str = new String(carLetters);
    private final List<String> listNumbers = new ArrayList<>();

    public String random() {
        generateTheSecondLetterOfTheNumber();
        generateTheThirdLetterOfTheNumber();
        generateTheFirstLetterOfTheNumber();
        generationOfFiguresOfNumber();
        String number = getNumber();
        listNumbers.add(number);
        return number;
    }

    public String next() {
        if (listNumbers.isEmpty()) return null;
        String number = listNumbers.get(listNumbers.size() - 1);
        String firstLetter = number.substring(0, 1);
        String innerNumberString = number.substring(1, 4);
        String theRest = number.substring(4);
        StringBuilder theRestSb = new StringBuilder(theRest);
        String innerNumberWithZero = null;
        int innerNumber = Integer.parseInt(innerNumberString);
        if (innerNumber == NINETY_NINE) {
            innerNumber++;
            innerNumberWithZero = String.valueOf(innerNumber);
        } else {
            innerNumber++;
            if (oneDigit(innerNumber)) {
                innerNumberWithZero = "00" + innerNumber;
            } else if (twoDigits(innerNumber)) {
                innerNumberWithZero = "0" + innerNumber;
            } else if (threeDigits(innerNumber)) {
                innerNumberWithZero = String.valueOf(innerNumber);
            } else if (fourDigits(innerNumber)) {
                int foundThirdLetter = getLetterIndex(theRest, 1);
                if (foundThirdLetter != carLetters.length - 1) {
                    changeLetter(foundThirdLetter, theRestSb, 1);
                } else {
                    int foundSecondLetter = getLetterIndex(theRest, 0);
                    changeLetter(foundSecondLetter, theRestSb, 0);
                }
                innerNumberWithZero = "000";
            }
        }
        String nextNumber = firstLetter + innerNumberWithZero + theRestSb;
        listNumbers.add(nextNumber);
        return nextNumber;
    }

    private void changeLetter(int foundThirdLetter, StringBuilder theRestSb, int index) {
        foundThirdLetter++;
        theRestSb.setCharAt(index, carLetters[foundThirdLetter]);
    }

    private int getLetterIndex(String theRest, int index) {
        char charNeededSecondLetter = theRest.charAt(index);
        return IntStream.range(0, carLetters.length)
                .filter(i -> carLetters[i] == charNeededSecondLetter)
                .findFirst()
                .orElse(-1);
    }

    //первая буква номера
    public char generateTheSecondLetterOfTheNumber() {
        int X = (int) (Math.random() * str.length());
        return carLetters[X];
    }

    //генерация цифр номера
    public int generationOfFiguresOfNumber() {
        return (int) ((Math.random() * (MAX_VALUE_NUMBER_CAR - MIN_VALUE_NUMBER_CAR)) + MIN_VALUE_NUMBER_CAR);
    }

    //вторая буква номера
    public char generateTheThirdLetterOfTheNumber() {
        int Y = (int) (Math.random() * str.length());
        return carLetters[Y];
    }

    //третья буква номера
    public char generateTheFirstLetterOfTheNumber() {
        int Z = (int) (Math.random() * str.length());
        return carLetters[Z];
    }

    //вызов на экран
    public String getNumber() {
        String number = String.format("%s%d%d%d%s%s%s", generateTheSecondLetterOfTheNumber(),
                generationOfFiguresOfNumber(), generationOfFiguresOfNumber(),
                generationOfFiguresOfNumber(), generateTheThirdLetterOfTheNumber(),
                generateTheFirstLetterOfTheNumber(), region);
        System.out.println(number);
        return number;
    }

    public boolean twoDigits(int x) {
        return String.valueOf(x).chars().toArray().length == 2;
    }

    public boolean oneDigit(int x) {
        return String.valueOf(x).chars().toArray().length == 1;
    }

    public boolean threeDigits(int x) {
        return String.valueOf(x).chars().toArray().length == 3;
    }

    public boolean fourDigits(int x) {
        return String.valueOf(x).chars().toArray().length == 4;
    }
}
