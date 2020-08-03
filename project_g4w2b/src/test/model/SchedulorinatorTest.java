package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SchedulorinatorTest {
    Schedulorinator schedule;
    Schedulorinator otherSchedule;
    Person person1;
    Person person2;
    Person person3;

    @BeforeEach
    void setUp(){
        schedule = new DailySchedulorinator("test");
        otherSchedule = new DailySchedulorinator("test2");
        person1 = new Person("Testguy1");
        person2 = new Person("Testguy2");
        person3 = new Person("Testguy3");
    }

    @Test
    void testConstructor(){
        assertEquals("test",schedule.getGoal());
    }

    @Test
    void displayGoal() {
        schedule.displayStreak();
    }

    @Test
    void testUpdate(){
        schedule.addPerson(person3);
        schedule.update(schedule);
    }

    @Test
    void testAdd() {
        schedule.addPerson(person1);
        schedule.addPerson(person2);
        schedule.addPerson(person3);
        schedule.addPerson(person2);
        assertEquals(3,schedule.personList.size());

    }

    @Test
    void testRemove() {
        schedule.addPerson(person1);
        schedule.addPerson(person2);
        schedule.addPerson(person3);
        schedule.addPerson(person2);
        schedule.removePerson(person2);
        schedule.removePerson(person3);
        schedule.removePerson(person1);
        assertEquals(0,schedule.personList.size());
    }

    @Test
    void equals1() {
        Schedulorinator scheduleDupe = schedule;
        Schedulorinator stub = null;
        if (schedule.equals(otherSchedule)){
            fail();
        }
        if (!scheduleDupe.equals(schedule)){
            fail();
        }
        assertFalse(schedule.equals(otherSchedule));
        assertFalse(schedule.equals(stub));
    }

    @Test
    void hashCode1() {
        assertEquals(schedule.hashCode(),schedule.hashCode());
    }
}