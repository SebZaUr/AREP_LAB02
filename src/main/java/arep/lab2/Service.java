package arep.lab2;

import java.io.IOException;

@FunctionalInterface
public interface Service {
    public String getValue(String request) throws IOException;
}
