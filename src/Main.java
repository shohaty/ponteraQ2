

import scheduler.exception.mySchedulerException;
import scheduler.job.Job;
import scheduler.job.JobTimeStructure;
import scheduler.os.OS;


public class Main {
    public static void main(String[] args) {
        //create an array of jobs
        Job[] jobs = {new Job(1), new Job(2), new Job(3), new Job(4), new Job(5), new Job(6), new Job(7), new Job(8), new Job(9), new Job(10)};

        try {
            jobs[0].sleep(10);
            jobs[1].sleep(6);
            jobs[2].sleep(2);
            jobs[3].sleep(1);
            jobs[4].sleep(4);
            jobs[5].sleep(9);
            System.out.println(jobs[0] + "," + jobs[1] + "," + jobs[2] + "," + jobs[3] + "," + jobs[4] + "," + jobs[5]);
            System.out.println(OS.getSleepingJobs());

            OS.awake();
        } catch (mySchedulerException e) {
            throw new RuntimeException(e);
        }
        for (JobTimeStructure pair : OS.getRunningJobs()) {
            System.out.println(pair.getJob());
        }
    }
}