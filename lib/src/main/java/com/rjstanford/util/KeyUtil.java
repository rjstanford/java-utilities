package com.rjstanford.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Optional;

/**
 *  Reliably and efficiently convert a number into a rapidly changing, bidirectional string representation
 *  The strings use base 34, which is case insensitive and where i/1 and 0/o are the same
 *  Furthermore, output strings are mapped 0 -> Y and 1 -> Z to reduce confusion
 */
public class KeyUtil {

    public static String toString(Short input) {
        if (input == null) {
            return null;
        }
        var offset = BigInteger.valueOf(input).add(BigInteger.valueOf(Short.MAX_VALUE));
        // This gives us a maximum value of 2^16 which is smaller than 4^34
        return toString(offset, 5, 4);
    }

    public static Short toShort(String input) {
        if (input == null) {
            return null;
        }
        input = normalizeBase34(input);
        if (input.length() != 4) {
            throw new IllegalArgumentException("Only 4 character keys can become Shorts");
        }
        try {
            return toBigInteger(input, 5).subtract(BigInteger.valueOf(Short.MAX_VALUE)).shortValueExact();
        } catch (ArithmeticException e) {
            throw new IllegalArgumentException("Invalid Key");
        }
    }

    public static String toString(Integer input) {
        if (input == null) {
            return null;
        }
        var offset = BigInteger.valueOf(input).add(BigInteger.valueOf(Integer.MAX_VALUE));
        // This gives us a maximum value of 2^32 which is smaller than 7^34
        return toString(offset, 10, 7);
    }

    public static Integer toInteger(String input) {
        if (input == null) {
            return null;
        }
        input = normalizeBase34(input);
        if (input.length() != 7) {
            throw new IllegalArgumentException("Only 7 character keys can become Integers");
        }
        var offset = toBigInteger(input, 10);
        var max = BigInteger.valueOf(Integer.MAX_VALUE);
        var value = offset.subtract(max);
        try {
            return value.intValueExact();
        } catch (ArithmeticException e) {
            throw new IllegalArgumentException("Invalid Key");
        }
    }

    public static String toString(Long input) {
        if (input == null) {
            return null;
        }
        BigInteger offset = BigInteger.valueOf(input).add(BigInteger.valueOf(Long.MAX_VALUE));
        // This gives us a maximum value of 2^64 which is smaller than 13^34
        return toString(offset, 20, 13);
    }

    public static Long toLong(String input) {
        if (input == null) {
            return null;
        }
        input = normalizeBase34(input);
        if (input.length() != 13) {
            throw new IllegalArgumentException("Only 13 character keys can become Longs");
        }
        try {
            return toBigInteger(input, 20).subtract(BigInteger.valueOf(Long.MAX_VALUE)).longValueExact();
        } catch (ArithmeticException e) {
            throw new IllegalArgumentException("Invalid Key");
        }

    }

    private static BigInteger toBigInteger(String base34, int numberLength) {
        var reversed = fromBase34(base34);
        var padded = leftPad(reversed.toString(), numberLength);
        var ordered = new StringBuilder(padded).reverse().toString();
        return new BigInteger(ordered);
    }

    private static String toString(BigInteger input, int inputLength, int outputLength) {
        var padded = leftPad(String.valueOf(input), inputLength);
        var reversed = new BigInteger(new StringBuilder(padded).reverse().toString());
        return KeyUtil.toBase34(reversed, outputLength);
    }

    private static String leftPad(String input, int length) {
        final var pads = length - input.length();
        final var buf = new char[pads];
        for (var i = pads - 1; i >= 0; i--) {
            buf[i] = '0';
        }
        return new String(buf).concat(input);
    }

    private static String toBase34(final BigInteger in, final int finishedLength) {
        var base34 = in.toString(34);
        var len = base34.length();
        StringBuilder accum = new StringBuilder();
        var ycount = Math.max(finishedLength - len, 0);
        accum.append("Y".repeat(ycount));
        for (var i = 0; i < len; i++) {
            var c = base34.charAt(i);
            accum.append(switch(c) {
               case '0' -> 'Y';
               case '1' -> 'Z';
               default -> Character.toUpperCase(c);
            });
        }
        return accum.toString();
    }

    private static BigInteger fromBase34(String in) {
        return new BigInteger(in, 34);
    }

    private static String normalizeBase34(String in) {
        if (in == null) {
            return null;
        }
        in = in.replaceAll("[^a-zA-Z0-9]", "");
        StringBuilder sb = new StringBuilder();
        for (var i = 0; i < in.length(); ++i) {
            var c = in.charAt(i);
            sb.append(switch(c) {
                case '0' -> 'O';
                case '1' -> 'I';
                case 'y', 'Y' -> '0';
                case 'Z', 'z' -> '1';
                default -> c;
            });
        }
        return sb.toString();
    }
}
