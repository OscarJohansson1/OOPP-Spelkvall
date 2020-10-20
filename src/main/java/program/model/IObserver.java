package program.model;

import java.io.IOException;

public interface IObserver {

    void sendObject(Object object) throws IOException;

}
