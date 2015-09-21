package com.akx2.engine;

public class GameTimerIndex extends GameTimer {

    int min;
    int max;

    int index;
    boolean doRocking = false;
    boolean doReverse = false;

    boolean isRocking = true;

    public GameTimerIndex(float speed, int min, int max) {
        super(speed);

        this.min = min;
        this.max = max;

        index = 0;
    }

    public void setSpeed (float speed)
    {
        this.speed = speed;
    }

    public float getCompleted ()
    {
        return ((float)(index - min) / (float)(max - min));
    }

    public void setRocking (boolean doRocking)
    {
        this.doRocking = doRocking;
    }

    public boolean getRocking ()
    {
        return this.doRocking;
    }

    public void setReverse (boolean doReverse)
    {
        this.doReverse = doReverse;
    }

    public boolean getReverse()
    {
        return this.doReverse;
    }

    public int getIndex()
    {
        return index;
    }

    @Override
    public void reset ()
    {
        super.reset();
        index = 0;
    }

    @Override
    public boolean update (float delta)
    {
        if (super.update(delta))
        {
            if (doReverse)
            {
                if ((doRocking) && (isRocking)) {
                    increment();
                } else {
                    decrement();
                }
            } else {
                if ((doRocking) && (isRocking)) {
                    decrement();
                } else {
                    increment();
                }
            }

            super.reset();
            return true;
        } else {
            return false;
        }
    }

    private void increment ()
    {
        index ++;

        if (index == (max+1)) {
            if (doRocking)
            {
                isRocking = !isRocking;
                index --;
            } else {
                index = min;
            }
        }
    }

    private void decrement ()
    {
        index --;

        if (index == (min-1)) {
            if (doRocking)
            {
                isRocking = !isRocking;
                index ++;
            } else {
                index = max;
            }
        }
    }
}
