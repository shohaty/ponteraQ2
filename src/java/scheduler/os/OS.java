package scheduler.os;

import scheduler.exception.mySchedulerException;
import scheduler.job.Job;
import scheduler.job.JobTimeStructure;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.logging.Logger;

public class OS {
    // Add additional functions and data members as you find fit
    private static final long MIN_TIME_PASS = 1;
    private static final long TIME_TO_AWAKE = 0;
    // You should define & implement the data structures below.
    private static PriorityQueue<JobTimeStructure> sleepingJobs = new PriorityQueue<JobTimeStructure>();
    private static LinkedList<JobTimeStructure> runningJobs = new LinkedList<JobTimeStructure>();

    public static PriorityQueue<JobTimeStructure> getSleepingJobs() {
        return sleepingJobs;
    }

    public static LinkedList<JobTimeStructure> getRunningJobs() {
        return runningJobs;
    }

    private static final Logger LOGGER = Logger.getLogger(OS.class.getName());

    /**
     * Should put the job in the 'sleepingJobs' data structuhhre.
     *
     * @param job
     * @param milliSeconds
     */
    public static void sleep(Job job, long milliSeconds) throws mySchedulerException {
        //if the milliSeconds is less than 0, throw an exception
        if (milliSeconds < 0) {
            throw new mySchedulerException("The milliSeconds is less than 0");
        }
        LOGGER.info("Sleeping job " + job.getJobId() + " for " + milliSeconds + " milliseconds");
        JobTimeStructure newJobTimeStructure = new JobTimeStructure(job, milliSeconds);
        sleepingJobs.add(newJobTimeStructure);
    }

    /**
     * Should move the jobs from 'sleepingJobs' to 'runningJobs' when the time to sleep is up.
     */
    public static void awake() throws mySchedulerException {

        if (sleepingJobs.isEmpty()) {
            //print that the queue is empty
            System.out.println("The sleeping queue is empty");
            return;
        } else {
            //if the first job in the queue is ready to run enter runOrSleep
            if (sleepingJobs.peek().getCurrentTime() == TIME_TO_AWAKE) {
                LOGGER.info("Awaking jobs");
                runOrSleep();
            } else //reduce the time of the first job in the queue by 1
            {
                sleepingJobs.peek().setCurrentTime(sleepingJobs.peek().getCurrentTime() - MIN_TIME_PASS);
            }
            awake();
        }
    }

    public static void runOrSleep() throws mySchedulerException {
        //add lock to the function
        final Object lock = new Object();
        synchronized (lock) {
            try {
                long currentTime = sleepingJobs.peek().getOriginalTime();
                Iterator<JobTimeStructure> sleepingQueueIterator = sleepingJobs.iterator();
                while (sleepingQueueIterator.hasNext()) {
                    JobTimeStructure currentJob = sleepingQueueIterator.next();
                    if (currentJob.getOriginalTime() == currentTime) {
                        Job job = (Job) currentJob.getJob();
                        LOGGER.info("transfer from sleepjobs to Runningjobs, job id: " + job.getJobId());
                        runningJobs.add(currentJob);
                        sleepingQueueIterator.remove();
                    } else //reduce the original time and the current time of the iterator by current time
                    {
                        currentJob.setOriginalTime(currentJob.getOriginalTime() - currentTime);
                        currentJob.setCurrentTime(currentJob.getCurrentTime() - currentTime);
                    }
                }
            } catch (Exception e) {
                LOGGER.severe("Error in runOrSleep function");
                throw new mySchedulerException("Error in runOrSleep");
            } finally {
                lock.notify();
            }
        }
    }
}