package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {
    Person person;
    Schedulorinator schedule1;
    Schedulorinator schedule2;
    Schedulorinator schedule3;

    @BeforeEach
    void setUp(){
        person = new Person("Test");
        schedule1 = new DailySchedulorinator("Testgoal1");
        schedule2 = new DailySchedulorinator("Testgoal2");
        schedule3 = new DailySchedulorinator("Testgoal3");
    }

    @Test
    void testAdd() {
        person.add(schedule1);
        person.add(schedule2);
        person.add(schedule3);
        person.add(schedule3);
        assertEquals(3,person.scheduleList.size());

    }

    @Test
    void testRemove() {
        person.add(schedule1);
        person.add(schedule2);
        person.add(schedule3);
        person.add(schedule3);
        person.remove(schedule3);
        person.remove(schedule2);
        person.remove(schedule1);
        assertEquals(0,person.scheduleList.size());
    }

    @Test
    void equals1() {
        Person personDupe = person;
        Person personStub = null;
        Person otherPerson = new Person("Otherperson");
        if (person.equals(otherPerson)) {
            fail();
        }
        if (!person.equals(personDupe)) {
            fail();
        }
        assertFalse(person.equals(otherPerson));
        assertFalse(person.equals(personStub));
    }

    @Test
    void hashCode1() {
        assertEquals(person.hashCode(),person.hashCode());
    }
}