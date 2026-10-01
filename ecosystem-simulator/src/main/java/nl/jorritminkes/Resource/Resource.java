package nl.jorritminkes.Resource;

import nl.jorritminkes.Position;

public abstract class Resource {
    private Position position;

    public Resource(Position position) {
        this.position = position;
    }
}
