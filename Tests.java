import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * @author ArtjomsBogatirjovs
 */
public class Tests {
    //Artjoms Bogatirjovs PD1
    //Here i didn't write much comments, because this is optional thing - just for me to make sure that my methods at least works correctly.

    //constants for roots
    private static final String PATH_TO_TEST_SOURCE = Main.PATH_TO_PROJECT + "testSource\\";
    private static final String PATH_TO_TEST_SOURCE_2 = Main.PATH_TO_PROJECT + "testSource2\\";

    //run all tests
    public static void runTests() throws Exception {
        System.out.println("TEST STARTING");
        test1();
        test2();
        test3();
        test4();
        System.out.println("TEST END\n");
    }
    //test that brute force finds correct number
    private static void test1() throws Exception {
        int[] numbers = {180, 50, 60, 90, 80, 170, 30, 40};

        int expectedResult = 120;
        int actualResult = Main.findBiggestIncreaseBruteForce(numbers);

        if (expectedResult != actualResult) {
            handleException("Test findBiggestIncreaseBruteForce() failed!", "findBiggestIncreaseBruteForce()");
        } else {
            System.out.println("Test findBiggestIncreaseBruteForce() passed!");
        }
    }
    //test that linear finds correct number
    private static void test2() throws Exception {
        int[] numbers = {180, 50, 60, 90, 80, 170, 30, 40};

        int expectedResult = 120;
        int actualResult = Main.findBiggestIncreaseLinear(numbers);

        if (expectedResult != actualResult) {
            handleException("Test findBiggestIncreaseLinear() failed!", "findBiggestIncreaseLinear()");
        } else {
            System.out.println("Test findBiggestIncreaseLinear() passed!");
        }
    }
    //test to make sure that file reading works correctly
    private static void test3() throws Exception {
        int[] expectedResult = {180, 50, 60, 90, 80, 170, 30, 40};
        int[] actualResult = FileUtils.fileToIntArray(PATH_TO_TEST_SOURCE_2 + "testFile.txt");
        if (!Arrays.equals(expectedResult, actualResult)) {
            handleException("Test fileToIntArray() failed!", "fileToIntArray()");
        } else {
            System.out.println("Test fileToIntArray() passed!");
        }
    }
    //test that make sure that find correct files from root
    private static void test4() throws Exception {
        List<String> expectedResult = new ArrayList<>();
        expectedResult.add(PATH_TO_TEST_SOURCE + "tempFile1");
        expectedResult.add(PATH_TO_TEST_SOURCE + "tempFile2");

        List<String> actualResult = FileUtils.getFilesPaths(PATH_TO_TEST_SOURCE);

        if (!expectedResult.equals(actualResult)) {
            handleException("Test getFilesPaths() failed!", "getFilesPaths()");
        } else {
            System.out.println("Test getFilesPaths() passed!");
        }
    }

    //if test fails inform about this
    private static void handleException(String message, String testName) throws Exception {
        System.out.println(message);
        throw new Exception("Test" + testName + " failed!");
    }
}
