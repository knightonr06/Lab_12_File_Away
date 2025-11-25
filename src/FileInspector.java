
import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.file.StandardOpenOption.CREATE;

public class FileInspector {

    public static void main(String[] args) {
        JFileChooser chooser = new JFileChooser();
        File selectedFile;
        String rec = "";

        try {

            File workingDirectory = new File(System.getProperty("user.dir"));

            File srcDir = new File(workingDirectory.getPath() + "\\src");

            if(srcDir.exists()){
                chooser.setCurrentDirectory(srcDir);
            } else {
                chooser.setCurrentDirectory(workingDirectory);
            }


            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                selectedFile = chooser.getSelectedFile();
                Path file = selectedFile.toPath();

                InputStream in =
                        new BufferedInputStream(Files.newInputStream(file, CREATE));
                BufferedReader reader =
                        new BufferedReader(new InputStreamReader(in));

                // Initialize counters
                int lineCount = 0;
                int wordCount = 0;
                int charCount = 0;

                System.out.println("File Content:");
                System.out.println("---------------------------------");

                // Loop while the reader has data
                while (reader.ready()) {
                    rec = reader.readLine();
                    lineCount++;

                    // Echo the line to the screen
                    System.out.printf("Line %4d %-60s \n", lineCount, rec);

                    charCount += rec.length();

                    String[] words = rec.split(" ");
                    wordCount += words.length;
                }

                reader.close(); // Close the file to seal it and flush buffer

                // Print the Summary Report
                System.out.println("\n---------------------------------");
                System.out.println("Summary Report");
                System.out.println("---------------------------------");
                System.out.println("File Name:            " + selectedFile.getName());
                System.out.println("Number of Lines:      " + lineCount);
                System.out.println("Number of Words:      " + wordCount);
                System.out.println("Number of Characters: " + charCount);

            } else {
                System.out.println("No file selected!!! ... exiting.\nRun the program again and select a file.");
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found!!!");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
