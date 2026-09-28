package kotlinx.coroutines.scheduling

import kotlinx.coroutines.DebugStringsKt

private class TaskImpl(block: Runnable, submissionTime: Long, taskContext: Boolean) : Task(submissionTime, taskContext) {
   public final val block: Runnable

   init {
      this.block = block;
   }

   public override fun run() {
      this.block.run();
   }

   public override fun toString(): String {
      return "Task[${DebugStringsKt.getClassSimpleName(this.block)}@${DebugStringsKt.getHexAddress(this.block)}, ${this.submissionTime}, ${TasksKt.access$taskContextString(
         this.taskContext
      )}]";
   }
}
