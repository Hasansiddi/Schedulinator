package observer;

import model.Schedulorinator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Subject implements Serializable {
    protected List<ScheduleObserver> observers = new ArrayList<>();

    public void addObserver(ScheduleObserver scheduleObserver) {
        if (!observers.contains(scheduleObserver)) {
            observers.add(scheduleObserver);
        }
    }

    public void removeObserver(ScheduleObserver scheduleObserver) {
        if (observers.contains(scheduleObserver)) {
            observers.remove(scheduleObserver);
        }
    }

    public void notifyObservers(Schedulorinator  schedule) {
        for (ScheduleObserver scheduleObserver: observers) {
            scheduleObserver.update(schedule);
        }
    }
}
