package hu.vizespalack.api;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import org.threeten.bp.LocalDate;

import static org.junit.Assert.*;

public class WaitingListEntryTest {

    WaitingListEntry entry1;
    WaitingListEntry entry2;

    @Before
    public void setUp() throws Exception {

        entry1 = new WaitingListEntry(new Worker("id1"), new EntryDate("2000-01-01"),1);
        entry2 = new WaitingListEntry(new Worker("id2"), new EntryDate("2000-01-01"),2);
    }

    @Test
    public void getWorkerId() {
        assertEquals("id1",entry1.getWorkerId());
        assertEquals("id2",entry2.getWorkerId());
    }

    @Test
    public void getDateString() {
        assertEquals("2000-01-01", entry1.getDateString());
        assertEquals("2000-01-01", entry2.getDateString());
    }

    //TOOD ?
    @Test
    public void setListPosition() {

    }

    @Test
    public void getListPosition() {
        assertEquals(1, entry1.getListPosition().intValue());
        assertEquals(2, entry2.getListPosition().intValue());
    }
}
