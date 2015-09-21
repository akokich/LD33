package com.akx2.engine;

import java.util.Random;

public class Util {
    public static Random rnd = new Random();

    public static float getDistance (float x1, float y1, float x2, float y2)
    {
        return (float)Math.sqrt (((x2-x1)*(x2-x1)) + ((y2-y1)*(y2-y1)));
    }

    public static float ratioN (float n1, float d1, float n2)
    {
        return (d1 * n2) / n1;
    }

    public static boolean isPointInBox (float x1, float y1, float x2, float y2, float w2, float h2)
    {
        if ((x1 > x2) &&
            (x1 < x2 + w2) &&
                (y1 < y2) &&
                (y1 > y2 - h2))
        {
            return true;
        }

        return false;
    }
}
