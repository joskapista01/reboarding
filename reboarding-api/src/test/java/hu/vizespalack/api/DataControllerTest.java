package hu.vizespalack.api;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import java.sql.DriverManager;

public class DataControllerTest {
/*
    DataController controller;
    Worker worker1;
    Worker worker2;
    EntryDate day;
    EntryDate anotherDay;

    @Before
    public void setUp() throws Exception {

        worker1 = new Worker("id1");
        worker2 = new Worker("id2");
        day = new EntryDate("2001-01-01");
        anotherDay = new EntryDate("2002-02-02");

        SimpleDriverDataSource source = new SimpleDriverDataSource();
        source.setDriver(DriverManager.getDriver("org.h2.Driver"));
        source.setUrl("jdbc:h2:mem:test");
        source.setUsername("user");
        source.setPassword("pw");

        controller = new DataController(new H2Backend(new JdbcTemplate(source)));

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void registerWorkerToDay() {
        Assert.assertEquals(1, controller.registerWorkerToDay(worker1,day).intValue());
        Assert.assertEquals(2, controller.registerWorkerToDay(worker2,day).intValue());

        Assert.assertEquals(1, controller.registerWorkerToDay(worker1,anotherDay).intValue());
        Assert.assertEquals(2, controller.registerWorkerToDay(worker2,anotherDay).intValue());
    }

    @Test
    public void getPositionOnDay() {
        Assert.assertEquals(1,controller.getPositionOnDay(day, worker1).intValue());
        Assert.assertEquals(2,controller.getPositionOnDay(day, worker2).intValue());
        Assert.assertEquals(1,controller.getPositionOnDay(anotherDay, worker1).intValue());
        Assert.assertEquals(2,controller.getPositionOnDay(anotherDay, worker2).intValue());
    }

    @Test
    public void getCurrentPosition() {
        Assert.assertEquals(1,controller.getCurrentPosition(worker1).intValue());
        Assert.assertEquals(2,controller.getCurrentPosition(worker2).intValue());
    }

    @Test
    public void getAllPosition() {
    }

    @Test
    public void isPermittedToEnter() {

    }

    @Test
    public void exitWorkerFromOffice() {
    }*/
}
