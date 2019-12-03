package Game;

/**
 * This class is responsible for the clock cycles of the game.
 */

public class Clock {
    // The number of milliseconds that make up a cycle
    private float millisPerCycle;
    // The last time the clock was updated
    private float lastUpdate;
    // The number of elapsed cycles
    private float elapsedCycles;
    // The amount of excess time until next elapsed cycle
    private float excessCycles;
    // If game is paused or not
    private boolean isPaused;

    /**
     * Create new clock and set it's cycles per second
     * @param cyclesPerSec The number of elapsed cycles per second
     */
    public Clock(float cyclesPerSec) {
        setCyclesPerSec(cyclesPerSec);
        reset();
    }

    /**
     * Sets the number of elapsed cycles per second
     * @param cyclesPerSec The number of cycles per second
     */
    public void setCyclesPerSec(float cyclesPerSec) {
        this.millisPerCycle = (1.0f / cyclesPerSec) * 1000;
    }

    /**
     * Resets the clock.
     * The lastUpdate time will be set to current time.
     */
    public void reset() {
        this.elapsedCycles = 0;
        this.excessCycles = 0.0f;
        this.lastUpdate = getCurretnTime();
        this.isPaused = false;
    }

    /**
     * Pauses or unpauses the clock. While paused the clock will not update
     * elapsed cycles or excess cycles.
     * @param pause If clock is paused or not
     */
    public void setPaused(boolean pause) {
        this.isPaused = pause;
    }

    /**
     * Checks to see if a cycle has elapsed for this clock yet.
     * If yes, decrement elapsedCycles.
     * @return If cycle has elapsed or not
     */
    public boolean hasElapsedCycle() {
        if(elapsedCycles > 0) {
            this.elapsedCycles--;
            return true;
        }
        return false;
    }

    /**
     * Check to see if cycle has elapsed or not.
     * Doesn't decrement.
     * @return If cycle has elapsed or not
     */
    public boolean peekElapsedCycle() {
        return (elapsedCycles > 0);
    }

    /**
     * Calculates the current time in milliseconds.
     * More accurate than {@code System.getCurrentTimeMillis()}
     * @return The current time in milliseconds
     */
    private static final long getCurretnTime() {
        return (System.nanoTime() / 1000000L);
    }


}
