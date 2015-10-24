

import java.io.*;

public class canalysis {

public static void main(String[] args) {

    File inFile = null;

    if (0 < args.length) {
        // text file will be passed during run time
        inFile = new File(args[0]);
    } else {
        //System.out.println("Cant Find The File Specified : " + inFile);
        System.out.println(0);
    }

    BufferedReader br = null;
    String sCurrentLine = null;
    int a = 0, b = 0, c = 0;
    boolean commentStarted = false;

    try {

        // passing the text file location for FileReader.

        br = new BufferedReader(new FileReader(inFile));

        // Looping through the text file

        while ((sCurrentLine = br.readLine()) != null) {
            sCurrentLine = sCurrentLine.trim();
            // avoid multi-line comments and one line comments and new
            // lines.
            //System.out.println(sCurrentLine);
            if(sCurrentLine.endsWith("*/")) {
                b++;
                commentStarted = false;
            }
            else if(commentStarted && sCurrentLine.contains("*/")) {
                commentStarted = false;
                b++;
            }
            else if(commentStarted) {
                b++;
            }
            else if(sCurrentLine.startsWith("/*")) {
                commentStarted = true;
                b++;
            }
            else if(sCurrentLine.startsWith("//")) {
                b++;
            }
            else if(commentStarted && sCurrentLine.isEmpty()) {
                b++;
            }
            else {
                commentStarted = false;
                a++;
            }
        }

        // excluding the number of lines that has comments and new lines

        c = a + b;
        //System.out.println("Number of Lines are : " + c);
        //System.out.println("Number of comments: " + b);
        float cPercent = (float)(b*100/c);
        System.out.println((int)cPercent);
    }
    catch (IOException e) {
        //System.out.println(e.getMessage());
        System.out.println(0);
    }

    finally {

        try {

            // close bufferReader

            if (br != null) {

                br.close();

            }

        } catch (IOException ex) {

            //System.out.println(ex.getMessage());
            System.out.println(0);
        }

      }

    }
}
