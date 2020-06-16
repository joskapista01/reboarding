package hu.vizespalack.api;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.threeten.bp.LocalDate;

public class EntryDateTest {

    EntryDate entryDate1;
    EntryDate entryDate2;
    EntryDate entryDate3;

    @Before
    public void setUp() throws Exception {
        entryDate1 = new EntryDate("2001-01-01");
        entryDate2 = new EntryDate(new EntryDate("2001-01-01"));
        entryDate3 = new EntryDate(LocalDate.of(2001, 1, 1));
    }

    @Test
    public void getDateString() {
        Assert.assertEquals("2001-01-01", entryDate1.getDateString());
        Assert.assertEquals("2001-01-01", entryDate2.getDateString());
        Assert.assertEquals("2001-01-01", entryDate3.getDateString());
    }

    @Test
    public void getYear() {
        Assert.assertEquals(2001, entryDate1.getYear().intValue());
        Assert.assertEquals(2001, entryDate2.getYear().intValue());
        Assert.assertEquals(2001, entryDate3.getYear().intValue());
    }

    @Test
    public void getMonth() {
        Assert.assertEquals(1, entryDate1.getMonth().intValue());
        Assert.assertEquals(1, entryDate2.getMonth().intValue());
        Assert.assertEquals(1, entryDate3.getMonth().intValue());
    }

    @Test
    public void getDay() {
        Assert.assertEquals(1, entryDate1.getDay().intValue());
        Assert.assertEquals(1, entryDate2.getDay().intValue());
        Assert.assertEquals(1, entryDate3.getDay().intValue());
    }
}
