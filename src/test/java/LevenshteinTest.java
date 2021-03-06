import me.stephenk.EditOperations.AtomicOperation;
import me.stephenk.EditOperations.OperationType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import me.stephenk.EditOperations.Levenshtein;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.List;


public class LevenshteinTest {

    @Test
    public void min3Test() {
        int input_1 = 12;
        int input_2 = 22;
        int input_3 = 42;
        int expected = 12;

        var output = Levenshtein.min3(input_1, input_2, input_3);

        Assertions.assertEquals(expected, output);
    }

    @ParameterizedTest
    @CsvSource({"1,2,3,1", "-2,1,12,-2", "15,16,0,0"})
    public void min3multiTest(int a, int b, int c, int expected) {
        int computedValue = Levenshtein.min3(a, b, c);
        Assertions.assertEquals(computedValue, expected);
    }

    @Test
    public void distanceBasicTest() {
        String input_1 = "hoLa";
        String input_2 = "hola";
        int expected = 1;

        var output = Levenshtein.distance(input_1, input_2);

        Assertions.assertEquals(expected, output);
    }

    @Test
    public void distanceBasic2Test() {
        String input_1 = "hoLa1";
        String input_2 = "hola";
        int expected = 2;

        var output = Levenshtein.distance(input_1, input_2);

        Assertions.assertEquals(expected, output);
    }

    @Test
    public void failingTest(){
        Assertions.assertEquals(1,2);
    }

    @Test
    public void atomicOperationsTest() {
        String input_1 = "hoLa";
        String input_2 = "hola";

        List<AtomicOperation> expected = List.of(
                new AtomicOperation(OperationType.substitution, 3, "L")
        );

        var output = Levenshtein.distance(input_1, input_2);

        Assertions.assertEquals(1, output);
    }

}
