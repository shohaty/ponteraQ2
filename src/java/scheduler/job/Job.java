package scheduler.job;

import scheduler.exception.mySchedulerException;
import scheduler.os.OS;

public class  Job {
    // Add additional functions and data members as you find fit
    private final long jobId;

    public Job(long jobId) {
        this.jobId = jobId;
    }

    public void sleep(long ms)
    {
        try {
            OS.sleep(this, ms);
        }
        catch (mySchedulerException e)
        {
            throw new RuntimeException(e);
        }
    }

    public Long getJobId() {
        return jobId;
    }
}
