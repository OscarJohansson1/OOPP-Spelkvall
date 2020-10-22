package program.model;

import java.io.IOException;

/**
 * An interface that makes it possible for an IObservable to send information to an IObserver without making the
 * IObservable dependant of the IObserver.
 */
public interface IObservable {

    /**
     * Method that adds an observer that should be called when specific event happen in the IObservable class.
     *
     * @param observer The new observer that will observe the class.
     */
    void addObserver(IObserver observer);

    /**
     * Method that call observers when a specific event happen.
     *
     * @param object The object that might be of relevance for the specific event.
     * @throws IOException Throws error if for example the server is down.
     */
    void notifyObservers(Object object) throws IOException;

    /**
     * Method that removes an IObserver.
     *
     * @param observer The IObserver that should be removed.
     */
    void removeObserver(IObserver observer);

}
