import me.stephenk.EditOperations.AtomicOperation;
import me.stephenk.EditOperations.OperationType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class OperationsTest {
    @Test
    public void testAtomic() {
        var a1 = new AtomicOperation(OperationType.none, 2, "s");
        Assertions.assertEquals("s", a1.value());
    }
}
