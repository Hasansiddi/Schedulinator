package ui;

import exceptions.UpdatedTooFrequentlyException;
import model.DailySchedulorinator;
import model.Person;
import model.Schedulorinator;
import model.WeeklySchedulorinator;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class GuiEz {

    public static void main(String[] args) throws Exception {
        String userInput;
        String exit = "69";
        new ImageDisplayWelcome();
        while (!exit.equals("0")) {
            Schedulorinator schedule = null;
            userInput = JOptionPane.showInputDialog("If you already have a goal please enter it's name, "
                    +
                    "else enter anything to create a goal: ");
            schedule = checkFileExsits(userInput, schedule);
            update(schedule);
            JOptionPane.showMessageDialog(null, schedule.displayStreak());
            schedule.save();
            exit = JOptionPane.showInputDialog("To exit the program please enter 0, "
                    +
                    "otherwise enter anything to start over!");
        }
        new ImageDisplayCelebration();
    }

    //Effects: updates the schedule with necessary input/output and a try block to catch exceptions
    public static void update(Schedulorinator schedule) throws IOException {
        String userInput;
        userInput = JOptionPane.showInputDialog("Have you completed today's goal? Please enter 1 if you have: ");
        try {
            schedule.update(userInput);
        } catch (UpdatedTooFrequentlyException e) {
            JOptionPane.showMessageDialog(null,"You have already updated the item today, please update later!");
        }
    }

    //Requires: user doesn't have a schedule they would like to load or not have one at all
    //Modifies: schedule object
    //Requires: returns a schedule object, weekly or daily schedule, based on users request
    public static Schedulorinator chooseSchedulinator(String goal) throws Exception {
        Scanner in = new Scanner(System.in);
        int userInput;
        Schedulorinator schedule;
        Person person = createPerson();
        userInput = Integer.parseInt(JOptionPane.showInputDialog("Select 1 for a daily goal and 2 for a weekly goal:"));
        if (userInput == 1) {
            schedule = new DailySchedulorinator(goal);
            schedule.save();
            person.add(schedule);
            return schedule;
        } else if (userInput == 2) {
            schedule = new WeeklySchedulorinator(goal);
            schedule.save();
            return schedule;
        } else {
            return null;
        }
    }

    //Effects: creates a person class and returns it
    public static Person createPerson() {
        Scanner in = new Scanner(System.in);
        String userInput;
        userInput = JOptionPane.showInputDialog("Please enter your name: ");
        Person person = new Person(userInput);
        return person;
    }

    //Modifies: schedule object
    //Effects: checks if file exists, if it does then loads it to a schedule object then returns it, otherwise
    //it calls method to create a new schedule
    public static Schedulorinator checkFileExsits(String userInput, Schedulorinator schedule) throws Exception {
        File f = new File(userInput + " Schedulorinator.dat");
        schedule = isDailyOrWeekly(userInput);
        String goal;
        if (f.exists() && !f.isDirectory()) {
            schedule = schedule.load();
            JOptionPane.showMessageDialog(null, schedule.getGoal() + " schedule has been loaded!");
            return schedule;
        } else {
            goal = JOptionPane.showInputDialog("Please enter the name of the goal you would like to enter: ");
            schedule = chooseSchedulinator(goal);
            return schedule;
        }
    }

    //Effects: asks the user if they want a daily or weekly schedule and returns one accordingly, mostly used for
    //loading what kind of file the user actually wants
    public static Schedulorinator isDailyOrWeekly(String userInput1) {
        String userInput;
        userInput = JOptionPane.showInputDialog("If you are trying to enter a daily goal enter 1, "
                +
                "else enter 2 for weekly");
        if (userInput.equals("1")) {
            return new DailySchedulorinator(userInput1);
        } else if (userInput.equals("2")) {
            return new WeeklySchedulorinator(userInput1);
        } else {
            return null;
        }
    }
}
