
public class SimClock {
    
    private static double now;
    
    public SimClock()
    {
        now = 0.0;
    }
    
    public double time() {
        return now;
    }

    public void time(double tm) {
        now = tm;
    }
}
