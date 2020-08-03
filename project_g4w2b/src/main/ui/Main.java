package ui;

import exceptions.UpdatedTooFrequentlyException;
import model.DailySchedulorinator;
import model.Person;
import model.Schedulorinator;
import model.WeeklySchedulorinator;
import network.ReadWebPage;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        Gui gui = new Gui();
        String userInput;
        String exit = "69";
        Scanner in = new Scanner(System.in);
//        ReadWebPage readWebPage = new ReadWebPage();
//        readWebPage.welcome();
        while (!exit.equals("0")) {
            Schedulorinator schedule = null;
            gui.display("If you already have a goal please enter it's name, "
                    +
                    "else enter anything to create a goal: ");
            userInput = in.nextLine();
            schedule = checkFileExsits(userInput, schedule, gui);
            update(schedule, gui);
            gui.display(schedule.displayStreak());
            schedule.save();
            gui.display("To exit the program please enter 0, otherwise enter anything to start over!");
            exit = in.nextLine();
        }
    }

    public static void update(Schedulorinator schedule, Gui gui) throws IOException {
        Scanner in = new Scanner(System.in);
        String userInput;
        gui.display("Have you completed today's goal? Please enter 1 if you have: ");
        userInput = in.nextLine();
        try {
            schedule.update(userInput);
        } catch (UpdatedTooFrequentlyException e) {
            gui.display("You have already updated the item today, please update later!");
        }
    }

    public static Schedulorinator chooseSchedulinator(String goal, Gui gui) throws Exception {
        Scanner in = new Scanner(System.in);
        int userInput;
        Schedulorinator schedule;
        Person person = createPerson();
        gui.display("Select 1 for a daily goal and 2 for a weekly goal: ");
        userInput = in.nextInt();
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

    public static Person createPerson() {
        Scanner in = new Scanner(System.in);
        String userInput;
        System.out.println("Please enter your name: ");
        userInput = in.nextLine();
        Person person = new Person(userInput);
        return person;
    }

    public static Schedulorinator checkFileExsits(String userInput, Schedulorinator schedule,Gui gui) throws Exception {
        File f = new File(userInput + " Schedulorinator.dat");
        schedule = isDailyOrWeekly(userInput, gui);
        String goal;
        Scanner in = new Scanner(System.in);
        if (f.exists() && !f.isDirectory()) {
            schedule = schedule.load();
            gui.display(schedule.getGoal() + " schedule has been loaded!");
            return schedule;
        } else {
            gui.display("Please enter the name of the goal you would like to enter: ");
            goal = in.nextLine();
            schedule = chooseSchedulinator(goal, gui);
            return schedule;
        }
    }

    public static Schedulorinator isDailyOrWeekly(String userInput1, Gui gui) {
        String userInput;
        Scanner in = new Scanner(System.in);
        gui.display("If you are trying to enter a daily goal enter 1, else enter 2 for weekly");
        userInput = in.nextLine();
        if (userInput.equals("1")) {
            return new DailySchedulorinator(userInput1);
        } else if (userInput.equals("2")) {
            return new WeeklySchedulorinator(userInput1);
        } else {
            return null;
        }
    }
}
