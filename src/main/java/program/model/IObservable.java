package program.model;

import java.io.IOException;

public interface IObservable {

    void addObserver(IObserver observer);

    void notifyObservers(Object object) throws IOException;

    void removeObserver(IObserver observer);

}
