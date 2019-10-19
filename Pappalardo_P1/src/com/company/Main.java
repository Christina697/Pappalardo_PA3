package com.company;
import java.util.Random;
import java.util.Scanner;

public class Main{
    //checks the users solution to see if they are correct and adds either 1 or 0 depending if the users answer was correct
    private static int checkForCorrectness(double rand1, double rand2, double solution, int type){
        double correctAnswer;
        if (type == 1) {
            correctAnswer = rand1 + rand2;
            if (solution == correctAnswer) {
                posResponses();
                return +1;
            }
            negResponses();
            return +0;
        }
        else if (type == 2) {
            correctAnswer = rand1 * rand2;
            if (solution == correctAnswer) {
                posResponses();
                return +1;
            }
            negResponses();
            return +0;
        }
        else if (type == 3) {
            correctAnswer = rand1 - rand2;
            if (solution == correctAnswer) {
                posResponses();
                return +1;
            }
            negResponses();
            return +0;
        }
        else {
            //divide by 0 is undefined, for the purpose of this program divide by 0 = 0
            if (rand2 == 0.0){
                correctAnswer = 0;
                if (correctAnswer == solution) {
                    posResponses();
                    return +1;
                }
                else {
                    negResponses();
                    return +0;
                }
            }
            else
                correctAnswer = rand1 / rand2;
            //manipulating Math.round to allow the user to get the answer correct when rounding to two decimals
            double roundedAnswer = Math.round(correctAnswer * 100.0 ) / 100.0;
            if (solution == roundedAnswer) {
                posResponses();
                return +1;
            }
            negResponses();
            return +0;
        }
    }

    private static void posResponses(){
        int caseNum = getOnetoFour();
        switch (caseNum) {
            case 1:
                System.out.println("Very good!");
                break;
            case 2:
                System.out.println("Excellent!");
                break;
            case 3:
                System.out.println("Nice work!");
                break;
            case 4:
                System.out.println("Keep up the good work!");
                break;
            default:
                System.out.println("Very good!");
                break;
        }
    }

    private static void negResponses(){
        int caseNum = getOnetoFour();
        switch (caseNum) {
            case 1:
                System.out.println("No. Please try again.");
                break;
            case 2:
                System.out.println("Wrong. Try once more.");
                break;
            case 3:
                System.out.println("Don’t give up!");
                break;
            case 4:
                System.out.println("No. Keep trying.");
                break;
            default:
                System.out.println("No. Please try again.");
                break;
        }
    }

    //gets a random number based on the users level of difficulty choice
    private static int getRandomNumber(double level){
        Random random = new Random();
        if(level ==1 ) {
            return random.nextInt(10);
        }
        else if(level == 2){
            return random.nextInt(100);
        }
        else if(level == 3){
            return random.nextInt(1000);
        }
        else {
            return random.nextInt(10000);
        }
    }

    //gets a random number between 1 and 4
    private static int getOnetoFour(){
        Random random = new Random();
        return random.nextInt(5)+1;
    }

    //asks the user a question based on the type of problem they chose and returns the type based on
    //the users previous type choice to be used for grading
    private static int getQuestion(double rand1, double rand2, int type){
        if(type == 1) {
            System.out.println("How much is " + rand1 + " plus " + rand2 + "?");
            return 1;
        }
        else if(type == 2) {
            System.out.println("How much is " + rand1 + " times " + rand2 + "?");
            return 2;
        }
        else if(type == 3) {
            System.out.println("How much is " + rand1 + " minus " + rand2 + "?");
            return 3;
        }
        else if(type == 4) {
            System.out.println("How much is " + rand1 + " divided by " + rand2 + "?");
            System.out.println("Where division by zero equals 0. Round to 2 decimal places.");
            return 4;
        }
        //returns the function to randomly generate question types
        else
            return getRandomQuestions(rand1, rand2);
    }

    //function to randomly generate function type
    private static int getRandomQuestions(double rand1, double rand2){
        int type = getOnetoFour();
        if(type == 1) {
            System.out.println("How much is " + rand1 + " plus " + rand2 + "?");
            return 1;
        }
        else if(type == 2) {
            System.out.println("How much is " + rand1 + " times " + rand2 + "?");
            return 2;
        }
        else if(type == 3) {
            System.out.println("How much is " + rand1 + " minus " + rand2 + "?");
            return 3;
        }
        else{
            System.out.println("How much is "+ rand1 + " divided by " + rand2 + "?");
            System.out.println("Where division by zero equals 0. Round to 2 decimal places.");
            return 4;
        }
    }
    private static String askAndAnswer(){
        Scanner scnr = new Scanner(System.in);
        //correctCounter to be used to calculate percentage
        double correctCounter = 0;
        //for when user decides to reset or not
        String yesOrNO;

        System.out.println("Please enter a difficulty level from 1 to 4.");
        int diffLevel = scnr.nextInt();

        System.out.println("Please pick a type of problem 1 through 5:");
        System.out.println("1 is addition only, 2 is multiplication only, 3 is subtraction only, 4 is division only, and 5 is a random mixture.");
        int typeOfProblem = scnr.nextInt();

        //runs through the various functions 10 times
        for(int i=0; i<10; i++) {
            double rand1 = getRandomNumber(diffLevel);
            double rand2 = getRandomNumber(diffLevel);
            int chosenTypeReturn = getQuestion(rand1, rand2, typeOfProblem);
            double userSolution = scnr.nextDouble();
            correctCounter = correctCounter + checkForCorrectness(rand1, rand2, userSolution, chosenTypeReturn);
        }

        System.out.printf("You got %.0f problems correct.\n", correctCounter);
        System.out.printf("You got %.0f problems incorrect.\n", (10 - correctCounter));

        double percentage = (correctCounter/10)*100;

        if(percentage < 75){
            System.out.println("Please ask your teacher for extra help.");
        }
        else{
            System.out.println("Congratulations, you are ready to go to the next level!");
        }

        System.out.println("Would you like to restart? Y or N");
        yesOrNO = scnr.next();

        return yesOrNO;
    }
    public static void main(String[] args) {
        //main has basically been used just to either repeat the program or not
        String yesOrNO = askAndAnswer();

        while(yesOrNO.equals("Y")){
           yesOrNO = askAndAnswer();
        }

    }
}
