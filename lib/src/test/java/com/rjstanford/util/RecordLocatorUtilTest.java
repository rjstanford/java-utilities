package com.rjstanford.util;

import org.junit.Test;
import static org.junit.Assert.*;

public class RecordLocatorUtilTest {


    @Test public void testLong() {
        checkLong(1,"Z42G5R6MI45PZK");
        checkLong(123,"Y5ST8Z6KZ8L86E");
        checkLong(Long.MAX_VALUE,"YLLCX83GE5YBU9");
        checkLong(Long.MIN_VALUE,"YYYYYYYYYYYYYY");
    }


    @Test public void testInt() {
        checkInt(1, "649VT7A");
        checkInt(123, "Z5ZDG2O");
        checkInt(Integer.MAX_VALUE,"3SFQJQC");
        checkInt(Integer.MIN_VALUE + 2,"ZAYLDRE");
        checkInt(Integer.MIN_VALUE,"YYYYYYY");
    }

    @Test public void testShort() {
        checkShort((short)1, "2FMR");
        checkShort((short)123, "YH5Z");
        checkShort(Short.MAX_VALUE,"ZCB6");
        checkShort((short)(Short.MIN_VALUE + 2),"YHA8");
        checkShort((short)(Short.MIN_VALUE),"YYYY");
    }

    @Test public void testCleanup() {
        assertEquals(122, (int) RecordLocatorUtil.toInteger("YHZ2Q6Y"));
        assertEquals(122, (int) RecordLocatorUtil.toInteger("YH-Z2-Q6-Y"));
        assertEquals(122, (int) RecordLocatorUtil.toInteger("yh-Z2-Q6-Y"));
        assertEquals(24601L, (long) RecordLocatorUtil.toLong("Z3TWSOKHPPFZ2I"));
        assertEquals(24601L, (long) RecordLocatorUtil.toLong("Z3TWS0KHPPFZ21"));
    }

    void checkLong(long number, String string) {
        assertEquals(string, RecordLocatorUtil.toString(number));
        assertEquals(number, RecordLocatorUtil.toLong(string).longValue());
    }

    void checkShort(short number, String string) {
        assertEquals(string, RecordLocatorUtil.toString(number));
        assertEquals(number, RecordLocatorUtil.toShort(string).shortValue());
    }

    void checkInt(int number, String string) {
        assertEquals(string, RecordLocatorUtil.toString(number));
        assertEquals(number, RecordLocatorUtil.toInteger(string).intValue());
    }

}
