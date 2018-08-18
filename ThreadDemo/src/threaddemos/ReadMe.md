# Sequence of learning:
### Topics
#### Day 1: threads and race condition.
- Sem3 intro + Threads-1
- Hvad er concurrency og hvor i mit program skal jeg forholde mig til det (GUI update, DB transactions, CPU kernels uptimization, multiple timeconsuming I/O network operations)
- Hvor mange måder kan man lave og starte tråde på.(Thread(), runnable, callable, threadpool, with lambda.
- Hvad er sleep() og interrupt(), hvad er busy waiting.
- Hvad er join()
- Synchronized and the different scopes (method level, block level, instanse level, JVM level)
- Atomic access (read and write are atomic)
- Hvad er racecondition og hvornår opstår problemet i min kode. Hvordan løser jeg problemet. ACID modellen til databasesystemer
- Hvilke Objekter er brugbare i java concurrency apiet
- hvad er syncronized og Hvad er locks
- Hvad er immutable data store.

#### Day 2: producer-consumer and deadlocks
- 
-
-
-
-
-

#### Day 3: callables, futures, executor service
-
-
-
-
-
-
-
-


## Example code:  
1. ThreadDemo.java  
2. testConcurrency.Main.java    // This one shows the problem of memory consistency errors. When 2 threads interleave.
3. testAtomicInteger            // This shows the use of AtomicInteger to ensure memory consistency when using java.util.concurrent.atomic classes
4. testSynchronization.Main     // Shows how it is not possible to predict the sequence of events using multiple threads
5. testDeadLock.Processor       // Shows how to create deadlock by aquiring locks in wrong order. Also shows the tryLock() method
6. testIntrinsicLock.Main       // Shows problem with synchronized method and solution using another object to get an intrinsic lock
7. testInterrupted              // Shows how to interrupt a thread and use the InterruptedException.
8. testConsumerProducerPattern  // low level version: Shows how to use wait() and notify() to determine which thread gets access to a resource. (notifyAll())
8a. testConsumerProducerPattern // Shows how to use a BlockingQueue instead of wait-notify
9. testThreadPool               // Shows how to use a threadpool to 
10. testCountDownLatch          // Shows how to use a CountDownLatch to ensure certain threads have finished before resuming execution of the calling thread.
