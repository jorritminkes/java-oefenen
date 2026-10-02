package nl.jorritminkes.resource;

public class Plant extends Resource {
    public Plant() {
        super(20);
    }

    @Override
    public Resource reproduce() {
        return new Plant();
    }
}