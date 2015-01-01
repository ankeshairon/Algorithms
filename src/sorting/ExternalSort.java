package sorting;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class ExternalSort {
    private static final int PAGE_SIZE = 1000;


    public static void main(String[] args) throws IOException {
        ExternalSort driver = new ExternalSort();
        final int originalFileName = driver.generateRandomData();
        int sortedFileName = driver.externalSort(originalFileName);
        driver.checkIfSorted(originalFileName, sortedFileName);
        driver.clean(originalFileName, sortedFileName);

    }

    private void clean(int originalFileName, int sortedFileName) {
        File file;
        for (int i = originalFileName; i <= sortedFileName; i++) {
            file = new File(String.valueOf(i));
            if (file.exists())
                file.delete();
        }

    }

    private void checkIfSorted(int originalFileName, int sortedFileName) throws IOException {
        final int expectedCount = getNumberOfLines(originalFileName);
        final int actualCount = getNumberOfLines(sortedFileName);
        if (expectedCount != actualCount) {
            System.out.println("Error! Found " + actualCount + " lines instead of " + expectedCount);
            return;
        }

        final BufferedReader outputReader = getReader(sortedFileName);
        String l1, l2;
        l1 = outputReader.readLine();
        l2 = outputReader.readLine();
        if (l1 == null)
            return;

        while (l2 != null) {
            if (l1.compareTo(l2) > 0) {
                System.out.println("Error! Data not sorted!");
                return;
            }
            l1 = l2;
            l2 = outputReader.readLine();
        }
        System.out.println("Well done my friend! External sort implemented successfully!");
    }

    private int getNumberOfLines(int originalFileName) throws IOException {
        LineNumberReader lineNumberReader = new LineNumberReader(new FileReader(String.valueOf(originalFileName)));
        lineNumberReader.skip(Long.MAX_VALUE);
        int lineCount = lineNumberReader.getLineNumber() + 1;
        lineNumberReader.close();
        return lineCount;
    }

    public int externalSort(int originalFileName) throws IOException {
        final BufferedReader originalSource = getReader(originalFileName);
        int mergeSourceLastFileName = sort(originalSource);
        return mergeFromTo(originalFileName + 1, mergeSourceLastFileName);
    }

    private int mergeFromTo(int firstSource, int lastSource) throws IOException {
        if (firstSource == lastSource) {
            return firstSource;
        }

        int firstDestination = lastSource + 1;
        int lastDestination = lastSource;


        BufferedReader reader1, reader2;
        String s1, s2;
        int c;
        BufferedWriter destinationWriter;
        int source = firstSource;
        while (true) {
            if (source <= lastSource)
                reader1 = getReader(source++);
            else
                break;
            if (source <= lastSource)
                reader2 = getReader(source++);
            else {
                rename(source - 1, ++lastDestination);
                break;
            }
            destinationWriter = getWriter(++lastDestination);
            s1 = reader1.readLine();
            s2 = reader2.readLine();
            while (s1 != null && s2 != null) {
                c = s1.compareTo(s2);
                if (c <= 0) {
                    writeLine(destinationWriter, s1);
                    s1 = reader1.readLine();
                } else {
                    writeLine(destinationWriter, s2);
                    s2 = reader2.readLine();
                }
            }
            while (s1 != null) {
                writeLine(destinationWriter, s1);
                s1 = reader1.readLine();
            }
            while (s2 != null) {
                writeLine(destinationWriter, s2);
                s2 = reader2.readLine();
            }
            destinationWriter.flush();
            destinationWriter.close();
        }
        return mergeFromTo(firstDestination, lastDestination);
    }

    private void writeLine(BufferedWriter writer, String line) throws IOException {
        writer.write(line);
        writer.newLine();
    }

    private BufferedWriter getWriter(int destinationFileName) throws IOException {
        return new BufferedWriter(new FileWriter(new File(String.valueOf(destinationFileName))));
    }

    private void rename(int oldName, int newName) {
        File file = new File(String.valueOf(oldName));
        file.renameTo(new File(String.valueOf(newName)));
    }

    private int sort(BufferedReader source) throws IOException {
        List<String> page;
        String entry = "";
        int destinationFileName = 0;
        int i;
        while (entry != null) {
            page = new ArrayList<>();
            for (i = 0; i < PAGE_SIZE; i++) {
                if ((entry = source.readLine()) == null)
                    break;
                page.add(entry);
            }
            if (i == 0)
                break;
            Collections.sort(page);
            writeToFile(page, ++destinationFileName);
        }
        return destinationFileName;
    }

    private void writeToFile(List<String> pageContent, int destinationFileName) throws IOException {
        BufferedWriter writer = getWriter(destinationFileName);
        for (String content : pageContent) {
            writeLine(writer, content);
        }
        writer.flush();
        writer.close();
    }

    private BufferedReader getReader(int filename) throws FileNotFoundException {
        final File file = new File(String.valueOf(filename));
        return file.exists() ? new BufferedReader(new FileReader(file)) : null;
    }

    private int generateRandomData() throws IOException {
        int filename = 0;
        final File file = new File(String.valueOf(filename));
        if (!file.exists())
            try (
                    BufferedWriter writer = new BufferedWriter(new FileWriter(file))
            ) {
                Random random = new Random(20l);
                for (int i = 0; i < 10_000; i++) {
                    writeLine(writer, String.valueOf(random.nextLong()));
                }
                writer.flush();
            }
        return filename;
    }

}
