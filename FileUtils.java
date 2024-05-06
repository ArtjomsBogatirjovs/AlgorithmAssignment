/*
 * @author ArtjomsBogatirjovs
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FileUtils {
    //Artjoms Bogatirjovs PD1

    //reading files using java.nio.file.*
    public static List<String> getFilesPaths(String directoryPath) {
        try {
            return Files.walk(Paths.get(directoryPath))
                    .filter(Files::isRegularFile)
                    .map(filePath -> filePath.toString())
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public static int[] fileToIntArray(String filePath) throws IOException {
        List<Integer> list = new ArrayList<>();
        Path tempPath = Paths.get(filePath);
        TimeCounter tempTimeCounter = new TimeCounter("Reading file " + tempPath.getFileName());
        tempTimeCounter.start();
        try (BufferedReader reader = Files.newBufferedReader(tempPath)) {

            String line;
            while ((line = reader.readLine()) != null) {
                list.add(Integer.parseInt(line.trim()));
            }
        }

        int[] numbers = list.stream().mapToInt(i -> i).toArray();
        tempTimeCounter.stop();
        return numbers;
    }
    //get file name by path
    public static String getFileName(String filePath) {
        Path path = Paths.get(filePath);
        return path.getFileName().toString();
    }
}
