package pie_chart_observer_ufu.observer;

public interface Subject<Event> {
    void attach(Observer<Event> e);

    void detach(Observer<Event> e);

    void notifyObservers(Event e);
}
