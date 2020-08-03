package observer;

import model.DailySchedulorinator;
import model.Schedulorinator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SubjectTest {
    Subject subject;
    Schedulorinator schedulorinator;

    @BeforeEach
    void setUp(){
        subject = new Subject();
        schedulorinator  = new DailySchedulorinator("Stub");
    }

    @Test
    void addObserver() {
        subject.addObserver(schedulorinator);
        subject.addObserver(schedulorinator);
    }

    @Test
    void removeObserver() {
        subject.addObserver(schedulorinator);
        subject.removeObserver(schedulorinator);
        subject.removeObserver(schedulorinator);
    }

    @Test
    void notifyObservers() {
        subject.addObserver(schedulorinator);
        subject.notifyObservers(schedulorinator);
    }
}