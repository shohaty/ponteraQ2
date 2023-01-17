## pontera Q2 - scheduler OS Sleep() and Awake()
### The code implements a job scheduler for the OS. Each Job is sent to a sleep(Long) method with the time it will sleep in the "Sleeping queue".
### When the time is up, the job will wake up and will be transferred to the Running queue.

#### In Every 1ms the awake() method is called, if the first job in the sleeping queue is ready to wake up, it will enter the runOrSleep() method and check if there are more jobs to transfer and will update all the jobs in the queue. 
(Due to code efficiency considerations the queue will be updated only when the time of first job is up)  
#### When ever a job is waking up, the Logger inform that a job.id woke up and transferred to the running queue.

#### 🌐 Technicals
   * Written in Java
