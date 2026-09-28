package kotlinx.coroutines.scheduling

internal abstract class Task : Runnable {
   public final var submissionTime: Long
      private set

   public final var taskContext: Boolean
      private set

   open fun Task(submissionTime: Long, taskContext: Boolean) {
      this.submissionTime = submissionTime;
      this.taskContext = taskContext;
   }

   open fun Task() {
      this(0L, false);
   }
}
