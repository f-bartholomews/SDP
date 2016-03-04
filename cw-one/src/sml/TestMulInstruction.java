package sml;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Unit test class for MulInstruction.
 * Please refer to {@see TestBinaryInstruction} as that class holds the set up
 * of the mocking classes (Machine and Registers) and the arguments and return values
 * of methods in the execute() functionality (which is implemented in {@see BinaryInstruction}.
 *
 *
 * @author federico.bartolomei
 */
public class TestMulInstruction extends TestBinaryInstruction {
    private BinaryInstruction mulInstruction = new MulInstruction("f0", 1, 1, 1);

    /**
     * An instance of MulInstruction to be tested in {@see} TestBinaryInstruction
     *
     * @return a new instance of MulInstruction
     */
    @Override
    protected BinaryInstruction init() {
        return mulInstruction;
    }

    // -----------------------------------------------------------------------------------------------------------------

    @Test
    public void testConstructorShouldGetTheRightBinaryOp() {
        assertThat(mulInstruction.getOpcode(), is(BinaryOp.MUL));
    }

    // void execute(Machine m) -----------------------------------------------------------------------------------------

    @Test
    public void testExecuteWithDifferentRegsShouldGetTheRegistersAtRightIndexes() throws Exception {
        // should get registers at index 10 and 20
        new DivInstruction("f0", 0, 10, 20).execute(randomMachine);
        // two calls to Registers.getRegister(i) should get the two registers, in any order
        assertThat(op1, isOneOf(10, 20));
        assertThat(op2, isOneOf(10, 20));
        assertNotEquals(op1, op2);
    }

    @Test
    public void testExecuteWithSameRegsShouldGetTheRegistersAtRightIndexes() {
        // should get registers at index 10 and 20
        new MulInstruction("1", 0, 10, 10).execute(randomMachine);
        assertThat(op1, is(10));
        assertEquals(op1, op2);
    }

    @Test
    public void testExecuteShouldSetRegisterAtRightIndex() {
        // should set register 10
        Instruction test = new MulInstruction("f2", 10, 0, 1);
        test.execute(randomMachine);
        assertThat(args[0], is(10));
    }

    @Test
    public void testExecuteShouldSetRegisterWithRightValue() {
        // should set register 0 (with values of registers 50 and 10)
        new MulInstruction("f3", 0, 50, 10).execute(randomMachine);
        // the multiplication of values in registers 50 and 100
        int result = value1 * value2;
        // should be passed as second argument of setRegister()
        assertThat(args[1], is(result));
    }

    @Test
    public void testExecuteShouldSetRegisterAtRightIndexWithRightValue() {
        // should set register 100 (with values of registers 5 and 10)
        new MulInstruction("f3", 100, 5, 10).execute(randomMachine);
        // the multiplication of values in registers 5 and 10
        int result = value1 * value2;
        // should go to register 100
        assertThat(args[0], is(100));
        // and have the right value
        assertThat(args[1], is(result));
    }

    // String toString() -----------------------------------------------------------------------------------------------

    @Test
    public void testToStringShouldHaveCorrectLabelAndOpCode() {
        Instruction i = new MulInstruction("f0", 0, 0, 0);
        String s = i.toString();
        assertThat(s, containsString("f0: mul"));
    }

    @Test
    public void testToStringShouldHaveCorrectOperatorsAndResult() {
        Instruction i = new MulInstruction("f0", 0, 0, 0);
        String s = i.toString();
        assertThat(s, containsString("0 * 0 to 0"));
    }

    @Test
    public void testToStringShouldHaveCorrectLabelOpcodeOperatorsAndResult() {
        Instruction i = new MulInstruction("f0", 0, 0, 0);
        String s = i.toString();
        assertThat(s, containsString("f0: mul 0 * 0 to 0"));
    }

    // -----------------------------------------------------------------------------------------------------------------

}
