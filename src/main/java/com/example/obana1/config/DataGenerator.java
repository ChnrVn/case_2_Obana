package com.example.obana1.config;

import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class DataGenerator {

    public Map<String, List<Integer>> parseRule(String rule) {
        String[] rules = rule.split("&");
        int length = Integer.parseInt(rules[rules.length - 1].split("=")[1]);

        Map<String, List<Integer>> arguments = new HashMap<>();
        int left;
        int right;

        for (int i = 1; i < rules.length; i = i + 2) {
            String[] elements = rules[i].split("-");

            if (elements.length == 2 && elements[1].contains("[")) {
                String[] tableAndValue = elements[1].split("=");
                String[] borders = tableAndValue[1].split(" ");
                left = Integer.parseInt(borders[0].substring(1));
                right = Integer.parseInt(borders[1].substring(0, borders[1].length() - 1));

                System.out.println(left);
                System.out.println(right);

                arguments.put(tableAndValue[0], generateRandomNumber(left, right, length));
            }
            else if(elements.length == 2) {
                String[] tableAndValue = elements[1].split("=");
                int l = Integer.parseInt(tableAndValue[1]);

                System.out.println(l);

                arguments.put(tableAndValue[0], generateRandomNumber(l, length));
            }

        }

        arguments.keySet().forEach(System.out::println);
        return arguments;
    }



    public List<String> generateRandomChar(int length, int count) {
        Random random = new Random();
        String characters = "abcdefghijklmnopqrstuvwxyz" +  "abcdefghijklmnopqrstuvwxyz".toUpperCase();
        List<String> listOfStrings = new ArrayList<>();

        for (int j = 0; j < count; j++) {
            char[] text = new char[length];
            for (int i = 0; i < length; i++)
            {
                text[i] = characters.charAt(random.nextInt(characters.length()));
            }
            listOfStrings.add(new String(text));
        }

        return  listOfStrings;
    }

    public List<String> generateRandomChar(int left, int right, int count) {
        Random random = new Random();
        int length;
        String characters = "abcdefghijklmnopqrstuvwxyz" +  "abcdefghijklmnopqrstuvwxyz".toUpperCase();
        List<String> listOfStrings = new ArrayList<>();

        for (int j = 0; j < count; j++) {
            length = random.nextInt(left, right);
            char[] text = new char[length];
            for (int i = 0; i < length; i++)
            {
                text[i] = characters.charAt(random.nextInt(characters.length()));
            }
            listOfStrings.add(new String(text));
        }

        return  listOfStrings;
    }

    public List<Integer> generateRandomNumber(int left, int right, int count) {
        List<Integer> listOfNumbers = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < count; i++) {
            listOfNumbers.add(random.nextInt(left, right));
        }

        return listOfNumbers;
    }

    public List<Integer> generateRandomNumber(int length, int count) {
        List<Integer> listOfNumbers = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < count; i++) {
            listOfNumbers.add(random.nextInt(
                    (int) Math.pow(10,length - 1) ,
                    ( (int) Math.pow(10,length) ) -1 )
            );
        }

        return listOfNumbers;
    }
}
