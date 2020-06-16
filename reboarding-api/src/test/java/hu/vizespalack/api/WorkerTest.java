package hu.vizespalack.api;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class WorkerTest {

    Worker worker1;
    Worker worker2;


    @Before
    public void setUp() throws Exception {
        this.worker1 = new Worker("John");
        this.worker2 = new Worker("Julia");
    }

    @Test
    public void getId() {
       assertEquals("John", worker1.getId());
       assertEquals("Julia", worker2.getId());
       assertNotEquals("John", worker2.getId());
       assertNotEquals("Julia", worker1.getId());
    }

    @Test
    public void unequivocal(){
        assertNotEquals(worker1, worker2);
        assertNotEquals(worker1.getId(), worker2.getId());
    }

}
