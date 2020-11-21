package io;

import static org.junit.Assert.*;
import org.junit.Test;

public class IOTest {
    
    @Test
    public void stubIOWorking() {
        String[] testInputs = {"a", "b", "c"};
        
        StubIO io = new StubIO(testInputs);

        assertEquals("a", io.readInput("first:"));
        
        io.printOutput("it is working!");
        assertEquals("it is working!", io.getOutputs().get(0));
    }
    
    
}
