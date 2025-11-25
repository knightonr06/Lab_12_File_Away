
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import static java.nio.file.StandardOpenOption.CREATE;

public class DataSaver {

    public static void main(String[] args) {

        Scanner pipe = new Scanner(System.in);
        ArrayList<String> recs = new ArrayList<>();

        boolean done = false;
        String firstName, lastName, idNum, email, csvRecord;
        int yob;

        do {
            System.out.println("\nEntering data for a new Person:");

            firstName = SafeInput.getNonZeroLenString(pipe, "Enter First Name");
            lastName = SafeInput.getNonZeroLenString(pipe, "Enter Last Name");

            idNum = SafeInput.getRegExString(pipe, "Enter ID (6 digits)", "\\d{6}");

            email = SafeInput.getNonZeroLenString(pipe, "Enter Email");
            yob = SafeInput.getRangedInt(pipe, "Enter Year of Birth", 1000, 9999);

            csvRecord = String.format("%s, %s, %s, %s, %d", firstName, lastName, idNum, email, yob);
            recs.add(csvRecord);

            done = SafeInput.getYNConfirm(pipe, "Are you done entering records?");

        } while (!done);

        String fileName = SafeInput.getNonZeroLenString(pipe, "Enter the name of the file to save (do not include .csv)");
        fileName = fileName + ".csv";

        File workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory.getPath() + "\\src\\" + fileName);

        try {
            OutputStream out =
                    new BufferedOutputStream(Files.newOutputStream(file, CREATE));
            BufferedWriter writer =
                    new BufferedWriter(new OutputStreamWriter(out));

            for (String rec : recs) {
                writer.write(rec, 0, rec.length());
                writer.newLine();
            }
            writer.close();
            System.out.println("Data successfully saved to: " + file.getFileName());
            System.out.println("Full path: " + file.toAbsolutePath());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
