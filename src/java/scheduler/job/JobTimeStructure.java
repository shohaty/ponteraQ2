package scheduler.job;

public class JobTimeStructure<Job, Long> implements Comparable
{

    private Job job;
    private long originalTime;
    private long currentTime;
    public JobTimeStructure(Job job, long time)
    {
        this.job = job;
        this.currentTime = time;
        this.originalTime = time;
    }
    public Job getJob()
    {
        return job;
    }

    public long getOriginalTime()
    {
        return originalTime;
    }
    public long getCurrentTime()
    {
        return currentTime;
    }
    public void setOriginalTime(long time)
    {
        this.originalTime = time;
    }

    public void setCurrentTime(long time)
    {
        this.currentTime = time;
    }
    @Override
    public int compareTo(Object o)
    {
        JobTimeStructure pair = (JobTimeStructure) o;
        if (this.originalTime > pair.originalTime)
            return 1;
        else if (this.originalTime < pair.originalTime)
            return -1;
        return 0;
    }

    @Override
    public String toString()
    {
        return "job.Job: " + job + " Time: " + originalTime + " Current Time: " + currentTime;
    }
}