package kotlinx.coroutines

private class ChildHandleNode(childJob: ChildJob) : JobNode, ChildHandle {
   public final val childJob: ChildJob

   public open val parent: Job
      public open get() {
         return this.getJob();
      }


   public open val onCancelling: Boolean
      public open get() {
         return true;
      }


   init {
      this.childJob = childJob;
   }

   public override fun invoke(cause: Throwable?) {
      this.childJob.parentCancelled(this.getJob());
   }

   public override fun childCancelled(cause: Throwable): Boolean {
      return this.getJob().childCancelled(cause);
   }
}
