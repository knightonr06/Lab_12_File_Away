import java.util.Scanner;

public class SafeInput {
    public static String getNonZeroLenString(Scanner pipe, String prompt) {
        String retString = ""; // Set this to zero length. Loop runs until it isn't
        do {
            System.out.print("\n" + prompt + ": "); // show prompt add space
            retString = pipe.nextLine();
        } while (retString.length() == 0);
        return retString;
    }

    public static int getInt(Scanner pipe, String prompt) {
        int retVal = 0;
        String trash = "";
        boolean done = false;

        do {
            System.out.print("\n" + prompt + ": ");
            if (pipe.hasNextInt()) {
                retVal = pipe.nextInt();
                pipe.nextLine(); // clear the newline buffer
                done = true;
            } else {
                trash = pipe.nextLine();
                System.out.println("Invalid input. You must enter an integer. You entered: " + trash);
            }
        } while (!done);

        return retVal;
    }
    public static double getDouble(Scanner pipe, String prompt) {
        double retVal = 0;
        String trash = "";
        boolean done = false;

        do {
            System.out.print("\n" + prompt + ": ");
            if (pipe.hasNextDouble()) {
                retVal = pipe.nextDouble();
                pipe.nextLine(); // clear the newline buffer
                done = true;
            } else {
                trash = pipe.nextLine();
                System.out.println("Invalid input. You must enter an integer. You entered: " + trash);
            }
        } while (!done);

        return retVal;
    }
    public static int getRangedInt(Scanner pipe, String prompt, int low, int high) {
        int retVal = 0;
        String trash = "";
        boolean done = false;

        do {
            // Append the range to the prompt as required by the lab
            System.out.print("\n" + prompt + " [" + low + " - " + high + "]: ");

            if (pipe.hasNextInt()) {
                retVal = pipe.nextInt();
                pipe.nextLine(); // clear buffer

                if (retVal >= low && retVal <= high) {
                    done = true;
                } else {
                    System.out.println("Input out of range. Must be between " + low + " and " + high + ".");
                }
            } else {
                trash = pipe.nextLine();
                System.out.println("Invalid input. You must enter an integer. You entered: " + trash);
            }
        } while (!done);

        return retVal;
    }
    public static double getRangedDouble(Scanner pipe, String prompt, double low, double high) {
        double retVal = 0;
        String trash = "";
        boolean done = false;

        do {
            // Append the range to the prompt
            System.out.print("\n" + prompt + " [" + low + " - " + high + "]: ");

            if (pipe.hasNextDouble()) {
                retVal = pipe.nextDouble();
                pipe.nextLine(); // clear buffer

                if (retVal >= low && retVal <= high) {
                    done = true;
                } else {
                    System.out.println("Input out of range. Must be between " + low + " and " + high + ".");
                }
            } else {
                trash = pipe.nextLine();
                System.out.println("Invalid input. You must enter a double. You entered: " + trash);
            }
        } while (!done);

        return retVal;
    }
    public static boolean getYNConfirm(Scanner pipe, String prompt) {
        String input = "";
        boolean retVal = false;
        boolean done = false;

        do {
            System.out.print("\n" + prompt + " [Y/N]: ");
            input = pipe.nextLine();

            if (input.equalsIgnoreCase("Y")) {
                retVal = true;
                done = true;
            } else if (input.equalsIgnoreCase("N")) {
                retVal = false;
                done = true;
            } else {
                System.out.println("Invalid input. Please enter 'Y' or 'N'.");
            }
        } while (!done);

        return retVal;
    }
    public static String getRegExString(Scanner pipe, String prompt, String regEx) {
        String retVal = "";
        boolean done = false;

        do {
            System.out.print("\n" + prompt + ": ");
            retVal = pipe.nextLine();

            if (retVal.matches(regEx)) {
                done = true;
            } else {
                System.out.println("Invalid input. Does not match the pattern: " + regEx);
            }
        } while (!done);

        return retVal;
    }

}
