/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package com.rjstanford.util;

import org.junit.Test;
import static org.junit.Assert.*;

public class KeyUtilTest {


    @Test public void testLong() {
        checkLong(1,"XUY38TKDFDOI6");
        checkLong(123,"ZMD5GTHUJT7NY");
        checkLong(Long.MAX_VALUE,"HEUUNQE9G8BCT");
        checkLong(Long.MIN_VALUE + 2,"46G2IB24MQYHE");
        assertThrows(IllegalArgumentException.class, () -> KeyUtil.toString(Long.MIN_VALUE));
    }


    @Test public void testInt() {
        checkInt(1, "5G9L5AK");
        checkInt(123, "YHZ2Q6Y");
        checkInt(Integer.MAX_VALUE,"36FFTTM");
        checkInt(Integer.MIN_VALUE + 2,"YMYANUO");
        assertThrows(IllegalArgumentException.class, () -> KeyUtil.toString(Integer.MIN_VALUE));
    }

    @Test public void testShort() {
        checkShort((short)1, "27YN");
        checkShort((short)123, "Y8GV");
        checkShort(Short.MAX_VALUE,"Z3N2");
        checkShort((short)(Short.MIN_VALUE + 2),"Y8M4");
        assertThrows(IllegalArgumentException.class, () -> KeyUtil.toString(Short.MIN_VALUE));
    }

    @Test public void testCleanup() {
        assertEquals(123, (int) KeyUtil.toInteger("YHZ2Q6Y"));
        assertEquals(123, (int) KeyUtil.toInteger("YH-Z2-Q6-Y"));
        assertEquals(123, (int) KeyUtil.toInteger("yh-Z2-Q6-Y"));
        assertEquals(1, (long) KeyUtil.toLong("XUY38TKDFDOI6"));
        assertEquals(1, (long) KeyUtil.toLong("XUY38TKDFD016"));
    }

    void checkLong(long number, String string) {
        assertEquals(string, KeyUtil.toString(number));
        assertEquals(number, KeyUtil.toLong(string).longValue());
    }

    void checkShort(short number, String string) {
        assertEquals(string, KeyUtil.toString(number));
        assertEquals(number, KeyUtil.toShort(string).shortValue());
    }

    void checkInt(int number, String string) {
        assertEquals(string, KeyUtil.toString(number));
        assertEquals(number, KeyUtil.toInteger(string).intValue());
    }

}
