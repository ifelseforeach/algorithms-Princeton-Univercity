package week1;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TestingPercolation {

    public static void main(String[] args) // test client (optional)
    {

        TestingPercolation percApp = new TestingPercolation();
        List<String> fileList = percApp.getFile("input6.txt");
        Percolation percolation = percApp.initializePercolation(fileList);
        if (percApp.openAndCheckPercolation(fileList, percolation))
            System.out.println("Percolates!");
        else
            System.out.println("Nope");

    }

    private List<String> getFile(String fileName) {

        List<String> fileList = new ArrayList<String>();
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                fileList.add(line);
            }
            scanner.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return fileList;

    }

    private Percolation initializePercolation(List<String> fileList) {

        if (fileList != null && fileList.size() > 0) {
            int percolationSize = new Integer(fileList.get(0)).intValue();
            Percolation percolation = new Percolation(percolationSize);
            return percolation;
        }
        return null;

    }

    private boolean openAndCheckPercolation(List<String> fileList, Percolation percolation) {

        if (fileList != null && fileList.size() > 0) {
            for (int i = 1; i < fileList.size(); i++) {
                String line = fileList.get(i).trim();
                String[] identifiersStr = line.split("\\s+");
                int row = 0;
                int column = 0;
                if (identifiersStr != null && identifiersStr.length == 2) {
                    row = new Integer(identifiersStr[0].trim()).intValue();
                    column = new Integer(identifiersStr[1].trim()).intValue();
                }
                if (row > 0 && column > 0) {
                    percolation.open(row, column);
                }

            }

        }
        return percolation.percolates();
    }

}
