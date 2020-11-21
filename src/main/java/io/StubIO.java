package io;

import java.util.ArrayList;

class StubIO implements IO {

    private String[] inputs;
    private int inputPointer;
    private ArrayList<String> outputs;

    public StubIO(String[] inputs) {
        this.inputs = inputs;
        this.inputPointer = 0;
        this.outputs = new ArrayList<>();
    }

    @Override
    public void printOutput(String output) {
        outputs.add(output);
    }

    @Override
    public String readInput(String promptForUser) {
        String input = this.inputs[this.inputPointer];
        this.inputPointer++;
        return input;
    }
    
    public ArrayList<String> getOutputs() {
        return this.outputs;
    }
}
