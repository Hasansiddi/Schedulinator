package model;

import observer.Subject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Person extends Subject {
    protected List<Schedulorinator> scheduleList = new ArrayList<>();
    private String name;

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    //Modifeis: this
    //Effects: Adds a schedule to the schedule list
    public void add(Schedulorinator schedule) {
        if (!scheduleList.contains(schedule)) {
            scheduleList.add(schedule);
            schedule.addPerson(this);
            addObserver(schedule);
            notifyObservers(schedule);
        }
    }

    //Modifes: this
    //Effects: removes a schedule from the schedule list
    public void remove(Schedulorinator schedule) {
        if (scheduleList.contains(schedule)) {
            scheduleList.remove(schedule);
            schedule.removePerson(this);
            removeObserver(schedule);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Person person = (Person) o;
        return name.equals(person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
