package pie_chart_observer_ufu;

import pie_chart_observer_ufu.observer.Observer;
import pie_chart_observer_ufu.observer.Subject;

import java.util.Vector;


public class CourseRecordSubject implements Subject<Vector<CourseRecord>> {
    /**
     * Constructs an CourseRecordSubject object
     */
    private final Vector<Observer<Vector<CourseRecord>>> observers;


    public CourseRecordSubject() {
        this.observers = new Vector<>();
    }


    /**
     * Attach to this Subject
     *
     * @param o the Observer that wishes to attach
     */
    @Override
    public void attach(Observer<Vector<CourseRecord>> o) {
        this.observers.addElement(o);
    }

    /**
     * Detach from this Subject
     *
     * @param e the Observer that wishes to detach
     */
    @Override
    public void detach(Observer<Vector<CourseRecord>> e) {
        for (int i = 0; i < observers.size(); i++) {
            if (observers.elementAt(i).equals(e))
                observers.removeElementAt(i);
        }
    }


    /**
     * Notify all Observers that Subject has changed
     */
    @Override
    public void notifyObservers(Vector<CourseRecord> e) {
        for (int i = 0; i < observers.size(); i++) {
            observers.elementAt(i).update(e);

        }
    }


}