package net.jewwis.betterlightning.ticktimer;

public class TickTimer {
    private int ticksTill;
    private final Runnable callback;

    public TickTimer(int ticks, Runnable flag) {
        this.ticksTill = ticks;
        this.callback = flag;
    }

    public void tick() {
        if (ticksTill > 0) {
            ticksTill--;
            if (ticksTill == 0) {
                callback.run();
            }
        }
    }

    public boolean timerComplete() {
        return ticksTill == 0;
    }
}
