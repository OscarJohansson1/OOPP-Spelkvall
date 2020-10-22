package program.model;

import java.io.IOException;

/**
 * An interface that makes it possible to send objects to the IObserver without making the IObservable dependant of the
 * IObserver.
 */
public interface IObserver {

    /**
     * Method that sends an object to the IObserver. The IObserver can later handle the object however they want.
     *
     * @param object An object that is of relevance for the IObserver.
     * @throws IOException Throws error if for example the server is down.
     */
    void sendObject(Object object) throws IOException;

}
