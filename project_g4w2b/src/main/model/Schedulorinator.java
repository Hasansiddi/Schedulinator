package model;

import exceptions.UpdatedTooFrequentlyException;
import observer.ScheduleObserver;

import java.io.*;
import java.util.*;

public abstract class Schedulorinator implements Load, Save, Serializable, ScheduleObserver {

    //attributes
    String goal;
    Date prevDate;
    Date currentDate;
    Calendar prevCalendar = Calendar.getInstance();
    Calendar currCalendar = Calendar.getInstance();
    List<Person> personList = new ArrayList<>();

    //constructor
    public Schedulorinator(String goal) {
        this.goal = goal;
        prevCalendar.add(Calendar.DATE,-1);
        prevDate = prevCalendar.getTime();
        currentDate = currCalendar.getTime();
    }

    //****METHODS****

    //Modifies: this
    //EFFECTS: increments your streak
    public abstract void update(String userInput) throws UpdatedTooFrequentlyException, IOException;

    //Effects: an output message whenever schedule is added to person (Observer)
    @Override
    public void update(Schedulorinator schedulorinator) {
        System.out.println(schedulorinator.getGoal() + " has been added as a goal for " + personList.get(0).getName());
    }

    //EFFECTS: returns string that holds your current streak
    public abstract String displayStreak();

    public String getGoal() {
        return goal;
    }

    //REQUIRES: class to exist
    //EFFECTS: loads Object from file
    public abstract Schedulorinator load() throws Exception;

    //Requires: Class to exist
    //Modifies: file of this object
    //EFFECTS: Saves object to a file
    public abstract void save() throws Exception;

    //Modifies: this
    //Effects: adds person to person list
    public void addPerson(Person person) {
        if (!personList.contains(person)) {
            personList.add(person);
            person.add(this);
        }
    }

    //Modieifes: this
    //Effects: removes person from person list
    public void removePerson(Person person) {
        if (personList.contains(person)) {
            personList.remove(person);
            person.remove(this);
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
        Schedulorinator that = (Schedulorinator) o;
        return goal.equals(that.goal) && prevDate.equals(that.prevDate) && currentDate.equals(that.currentDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(goal, prevDate, currentDate);
    }
}
