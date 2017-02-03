package exercises.xf;

import java.util.Scanner;

/**
 * Simple application class that waits for the user input to build the mine field,
 * validating it and then retrieving the corresponding hints.
 *
 * Created by guisil on 03/02/2017.
 */
public class App {

    public static void main(String[] args) {

        MineSweeper mineSweeper = new ActualMineSweeper();
        boolean isMineFieldValid = false;

        try(Scanner scanner = new Scanner(System.in)) {
            while (!isMineFieldValid) {
                System.out.print("Welcome to MineSweeper!\n\n");
                System.out.print("Please insert your mine field, line by line (type 'end' once it's finished).\n\n");
                StringBuilder builder = new StringBuilder();

                while (!scanner.hasNext("end")) {
                    if (builder.length() != 0) {
                        builder.append("\n");
                    }
                    builder.append(scanner.nextLine());
                }
                scanner.nextLine();
                try {
                    mineSweeper.setMineField(builder.toString().trim());
                    isMineFieldValid = true;
                } catch (IllegalArgumentException ex) {
                    System.out.print("Invalid mine field. Please insert your minefield again.");
                }
            }
        }
        System.out.print("\nHere are your hints:\n\n" + mineSweeper.getHintField() + "\n\n");
    }
}
