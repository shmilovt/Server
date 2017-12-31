package il.ac.bgu.finalproject.server.Domain.NLPHandlers;

public class TestCase< I, O> {
    private I input;
    private O output;

    public TestCase(){
    }

    public TestCase(I input, O output) {
        this.input = input;
        this.output = output;
    }

    public I getInput() {
        return input;
    }

    public void setInput(I input) {
        this.input = input;
    }

    public O getOutput() {
        return output;
    }

    public void setOutput(O output) {
        this.output = output;
    }
}
