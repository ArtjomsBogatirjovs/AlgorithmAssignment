import java.util.List;

/*
 * @author ArtjomsBogatirjovs
 */
public class Main {
    //Artjoms Bogatirjovs PD1


    //Change this constant to your project folder
    public static final String PATH_TO_PROJECT = "C:\\Users\\arbog\\IdeaProjects\\AlgorithmAssignment\\"; //TODO Before run change source root!
    public static final String PATH_TO_SOURCE = PATH_TO_PROJECT + "sources\\";
    //constants to process only one file
    private static final String INTS_10_NAME = PATH_TO_SOURCE + "ints_10.txt";
    private static final String INTS_100_NAME = PATH_TO_SOURCE + "ints_100.txt";
    private static final String INTS_1K_NAME = PATH_TO_SOURCE + "ints_1K.txt";
    private static final String INTS_10K_NAME = PATH_TO_SOURCE + "ints_10K.txt";
    private static final String INTS_100K_NAME = PATH_TO_SOURCE + "ints_100K.txt";
    private static final String INTS_1M_NAME = PATH_TO_SOURCE + "ints_1M.txt";
    private static final String INTS_10M_NAME = PATH_TO_SOURCE + "ints_10M.txt";
    //end of constants to process only one file
    private static String runningFilePath = null; //IF YOU WANT TO START ONLY ONE FILE PLEASE WRITE ONE OF CONSTANTS
    private static boolean useLinearAlgorithm = true; //choose one of 2 algorithm, Linear if true, Brute force if false.

    public static void main(String[] args) throws Exception {
        Tests.runTests(); //test to make sure that program works correctly
        if (runningFilePath == null) {//if this variable is null run all files
            List<String> filesPaths = FileUtils.getFilesPaths(PATH_TO_SOURCE); //get all files paths from 'sources' root
            for (String filePath : filesPaths) {//iterate through all files paths
                try {
                    int result;
                    int[] numbers = FileUtils.fileToIntArray(filePath); //convert file to int array
                    if (useLinearAlgorithm) {
                        result = findBiggestIncreaseLinear(numbers);
                    } else {
                        result = findBiggestIncreaseBruteForce(numbers);
                    }
                    System.out.println("Answer for " + FileUtils.getFileName(filePath) + " : " + result);
                } catch (Exception e) {
                    System.out.println("Error in processing " + filePath);
                }

            }
        } else {
            int[] numbers = FileUtils.fileToIntArray(runningFilePath); //convert file to int array
            int result;
            if (useLinearAlgorithm) {
                result = findBiggestIncreaseLinear(numbers);
            } else {
                result = findBiggestIncreaseBruteForce(numbers);
            }
            System.out.println("Answer for " + FileUtils.getFileName(runningFilePath) + " : " + result);
        }
    }

    //brute solution that we looked in the class, complexity O(n^2), really slow
    //here is really common idea, just get value and compare with all others values.
    // I think here not needed comments, just 2 for loops and find max increase. 10M * 10M iterates in worst case
    //Truly I don't know how much time takes in Java to calculate this, I wait more than 10min(10M), then I go to find answer using linear method
    //100K takes 2.5 sec, 1M 252 sec, so 10M I thinks takes 252 * 100 sec.
    public static int findBiggestIncreaseBruteForce(int[] numbers) {
        TimeCounter tempTimeCounter = new TimeCounter("Brute force ");//time counter init
        tempTimeCounter.start();//start of timer
        int maxIncrease = 0;
        for (int i = 0; i < numbers.length; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                int increase = numbers[j] - numbers[i];
                if (increase > maxIncrease) {
                    maxIncrease = increase;
                }
            }
        }
        tempTimeCounter.stop();
        return maxIncrease;
    }

    //this solution should be faster with complexity O(n)
    //Our goal to find the minimal value value and save always max increase with next values.
    //If in next steps min value become smaller we compare always max increase.
    //10M 0.007 sec, better result than in brute force :)
    public static int findBiggestIncreaseLinear(int[] numbers) {
        TimeCounter tempTimeCounter = new TimeCounter("Linear");//time counter init
        tempTimeCounter.start();//start of timer

        int minElement = Integer.MAX_VALUE; // set min element max integer value or also can be first number of array, but this way easier :)
        int maxIncrease = 0;

        for (int number : numbers) {
            if (number < minElement) {
                minElement = number; // always try to find min element
            } else { //if not less than minimum try to compare
                int increase = number - minElement;
                if (increase > maxIncrease) { //and here save max increase
                    maxIncrease = increase;
                }
            }
        }
        tempTimeCounter.stop();
        return maxIncrease;
    }
}
