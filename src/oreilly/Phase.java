package oreilly;

public class Phase
{
    private int phaseNum;
    private int duration;
    private String timedate;

    public Phase(int phaseNum, int duration, String timedate)
    {
        this.phaseNum = phaseNum;
        this.duration = duration;
        this.timedate = timedate;
    }

    public int getPhaseNum()
    {
        return phaseNum;
    }

    public void setPhaseNum(int phaseNum)
    {
        this.phaseNum = phaseNum;
    }

    public int getDuration()
    {
        return duration;
    }

    public void setDuration(int duration)
    {
        this.duration = duration;
    }

    public String getTimedate()
    {
        return timedate;
    }

    public void setTimedate(String timedate)
    {
        this.timedate = timedate;
    }
    
    @Override
    public String toString()
    {
        String b = "";
        b += "Phase " + phaseNum + " for " + duration + "s at " + timedate;
        return b;
    }
}
