package model;

import exceptions.UpdatedTooFrequentlyException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class DailySchedulorinatorTest {
    DailySchedulorinator schedule;

    @BeforeEach
    void setUp(){
        schedule = new DailySchedulorinator("test");
    }

    @Test
    void testConstructor(){
        assertEquals("test",schedule.getGoal());
    }

    @Test
    void update() throws IOException {
        try{
            schedule.update("1");
            schedule.update("1");
            fail();
        } catch (UpdatedTooFrequentlyException e){

        }
    }

    @Test
    void loadAndSave() throws Exception {
        schedule.save();
        assertEquals("test",schedule.load().getGoal());
    }
}