package model;

import exceptions.UpdatedTooFrequentlyException;

import java.io.*;
import java.util.Calendar;

public class DailySchedulorinator extends Schedulorinator {
    protected int streak = 0;
    protected Calendar lastUpdate;
    protected boolean newGoal = true;

    public DailySchedulorinator(String goal) {
        super(goal);
        lastUpdate = Calendar.getInstance();
    }

    //Requires: class to not have been updated today
    //Modiefies: this
    //Effects: If not updated today it updates streak and saves, otherwise an exception is thrown
    public void update(String userInput) throws UpdatedTooFrequentlyException,IOException {
        currCalendar = Calendar.getInstance();
        if (userInput.equals("1")) {
            if (lastUpdate.get(Calendar.DAY_OF_YEAR) == currCalendar.get(Calendar.DAY_OF_YEAR)
                    &&
                    lastUpdate.get(Calendar.YEAR) == currCalendar.get(Calendar.YEAR) && newGoal == false) {
                throw new UpdatedTooFrequentlyException();
            } else {
                newGoal = false;
                save();
                lastUpdate.equals(currentDate);
                streak++;
            }
        }
    }

    //Effects: Returns string that tells how much your streak is
    @Override
    public String displayStreak() {
        return ("Your current streak is : " + streak + " days!");
    }

    //Requires: class must exist
    //Effects: Object is loaded from file
    public Schedulorinator load() throws Exception {
        ObjectInputStream in  = new ObjectInputStream(new FileInputStream(super.goal + " Schedulorinator.dat"));
        Schedulorinator load = (Schedulorinator)in.readObject();
        in.close();
        return load;
    }

    //Requires: Class to exist
    //Modifies: file of this object
    //EFFECTS: Saves object to a file
    public void save() throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(super.goal + " Schedulorinator.dat"));
        out.writeObject(this);
        out.close();
    }
}
