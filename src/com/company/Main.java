package com.company;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Random;

public class Main {
    Random rand = new Random();
    private int totalDiceRolls = 0;

    private int[] rollDiceRecursive(int numberOfDice, int[] currentResults, int index, int sum){
        if(numberOfDice == 0){
            System.out.println("Done! Total sum is " + sum + " and " + totalDiceRolls + " dice were rolled.");
            return currentResults;
        }
        //roll a die
        currentResults[index] = rollD6();
        System.out.println("You rolled a " + currentResults[index] + " on roll number " + totalDiceRolls + ".");

        //check result of rolled die
        if(currentResults[index] == 1){
            System.out.println("Your roll will not be counted in sum and instead two new dice will be rolled.");
            //resize the array
            int newArraySize = currentResults.length + 2;
            int[] newArray = new int[newArraySize];
            for(int i = 0; i < currentResults.length; i++){ newArray[i] = currentResults[i]; }
            currentResults = newArray;

            return rollDiceRecursive(numberOfDice + 1, currentResults, index++, sum);
        }
        else{
            int newSum = sum + currentResults[index];
            System.out.println("Current sum is " + newSum + ".");
            return rollDiceRecursive(numberOfDice - 1, currentResults, index++, newSum);
        }
    }

    private int rollD6(){
        totalDiceRolls++;
        int result = rand.nextInt(6) + 1;
        return result;
    }

    private void play(Scanner sc) throws InputMismatchException{
        totalDiceRolls = 0; //reset dice rolls
        int[] results = null; //reset results

        System.out.println("Dice game written by Alexander Broms on the 18th of May, 2020:");
        System.out.println("Please select how many dice you'd like to roll:");
        int numberOfDice = sc.nextInt();

        if(numberOfDice < 1 || numberOfDice > 5){
            System.out.println("Sorry, you have to roll 1-5 dice.");
            play(sc);
        }

        results = new int[numberOfDice];
        results = rollDiceRecursive(numberOfDice, results, 0, 0);

        askToPlayAgain(sc);
    }

    private void askToPlayAgain(Scanner sc){
        System.out.println("Would you like to roll again? y/n");
        String answer = sc.next();
        if(answer.equals("y")){
            play(sc);
        }
        else if(answer.equals("n")){
            System.out.println("Ok. Goodbye!");
        }
        else{
            System.out.println("Sorry, I didn't recognize that input.");
            askToPlayAgain(sc);
        }
    }

    public static void main(String[] args) {
	// write your code here
        Main main = new Main();
        Scanner in = new Scanner(System.in);
        main.play(in);
    }
}