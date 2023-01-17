package scheduler.job;

import scheduler.os.OS;

public class  Job {
    // Add additional functions and data members as you find fit
    private final long jobId;

    public Job(long jobId) {
        this.jobId = jobId;
    }

    public void sleep(long ms)
    {
        OS.sleep(this, ms);
    }

    public Long getJobId() {
        return jobId;
    }
}
