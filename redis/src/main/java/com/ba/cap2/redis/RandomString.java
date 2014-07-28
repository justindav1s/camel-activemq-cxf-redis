package com.ba.cap2.redis;

import java.util.Random;

/**
 * Created by u760245 on 22/07/2014.
 */
public class RandomString {

    private static char[] symbols;

    static {
        StringBuilder tmp = new StringBuilder();
        for (char ch = '0'; ch <= '9'; ++ch)
            tmp.append(ch);
        for (char ch = 'a'; ch <= 'z'; ++ch)
            tmp.append(ch);
        symbols = tmp.toString().toCharArray();
    }

    private final Random random = new Random();

    private char[] buf;

    public RandomString() {
    }

    public String nextString(int length) {
        buf = new char[length];
        for (int idx = 0; idx < buf.length; ++idx)
            buf[idx] = symbols[random.nextInt(symbols.length)];
        return new String(buf);
    }
}
