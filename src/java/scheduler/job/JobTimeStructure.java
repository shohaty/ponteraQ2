package scheduler.job;

public class JobTimeStructure<Job, Long> implements Comparable
{
    private Job job;
    private long sleepingTimeLeft;
    private long currentTime;
    public JobTimeStructure(Job job, long time)
    {
        this.job = job;
        this.currentTime = time;
        this.sleepingTimeLeft = time;
    }
    public Job getJob()
    {
        return job;
    }

    public long getSleepingTimeLeft()
    {
        return sleepingTimeLeft;
    }
    public long getCurrentTime()
    {
        return currentTime;
    }
    public void setSleepingTimeLeft(long time)
    {
        this.sleepingTimeLeft = time;
    }

    public void setCurrentTime(long time)
    {
        this.currentTime = time;
    }
    @Override
    public int compareTo(Object o)
    {
        JobTimeStructure pair = (JobTimeStructure) o;
        if (this.sleepingTimeLeft > pair.sleepingTimeLeft)
            return 1;
        else if (this.sleepingTimeLeft < pair.sleepingTimeLeft)
            return -1;
        return 0;
    }

    @Override
    public String toString()
    {
        return "job.Job: " + job + " Time: " + sleepingTimeLeft + " Current Time: " + currentTime;
    }
}