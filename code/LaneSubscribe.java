import java.util.Vector;
import java.util.Iterator;
import java.util.HashMap;
import java.util.Date;


public class LaneSubscribe extends Thread {

    private Vector subscribers;

    public LaneSubscribe() {
        subscribers = new Vector();
    }

    /** subscribe
     *
     * Method that will add a subscriber
     *
     * @param subscribe	Observer that is to be added
     */

    public void subscribe( LaneObserver adding ) {
        subscribers.add( adding );
    }

    /** unsubscribe
     *
     * Method that unsubscribes an observer from this object
     *
     * @param removing	The observer to be removed
     */

    public void unsubscribe( LaneObserver removing ) {
        subscribers.remove( removing );
    }

    /** publish
     *
     * Method that publishes an event to subscribers
     *
     * @param event	Event that is to be published
     */

    public void publish( LaneEvent event ) {
        if( subscribers.size() > 0 ) {
            Iterator eventIterator = subscribers.iterator();

            while ( eventIterator.hasNext() ) {
                ( (LaneObserver) eventIterator.next()).receiveLaneEvent( event );
            }
        }
    }

}