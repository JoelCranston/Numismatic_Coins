package kotlinx.coroutines

import java.util.concurrent.CancellationException

internal class JobCancellationException(message: String, cause: Throwable?, job: Job) : CancellationException(message),
   CopyableThrowable<JobCancellationException> {
   private final val _job: Job?

   internal final val job: Job
      internal final get() {
         var var10000: Job = this._job;
         if (this._job == null) {
            var10000 = NonCancellable.INSTANCE;
         }

         return var10000;
      }


   init {
      this._job = job;
      if (cause != null) {
         this.initCause(cause);
      }
   }

   public override fun fillInStackTrace(): Throwable {
      if (DebugKt.getDEBUG()) {
         return super.fillInStackTrace();
      } else {
         this.setStackTrace(new StackTraceElement[0]);
         return this;
      }
   }

   public open fun createCopy(): JobCancellationException? {
      if (DebugKt.getDEBUG()) {
         val var10002: java.lang.String = this.getMessage();
         return new JobCancellationException(var10002, this, this.getJob$kotlinx_coroutines_core());
      } else {
         return null;
      }
   }

   public override fun toString(): String {
      return "${super.toString()}; job=${this.getJob$kotlinx_coroutines_core()}";
   }

   public override operator fun equals(other: Any?): Boolean {
      return other === this
         || other is JobCancellationException
            && (other as JobCancellationException).getMessage() == this.getMessage()
            && (other as JobCancellationException).getJob$kotlinx_coroutines_core() == this.getJob$kotlinx_coroutines_core()
            && (other as JobCancellationException).getCause() == this.getCause();
   }

   public override fun hashCode(): Int {
      val var10000: java.lang.String = this.getMessage();
      val var1: Int = var10000.hashCode() * 31;
      val var10001: Job = this.getJob$kotlinx_coroutines_core();
      val var2: Int = (var1 + (if (var10001 != null) var10001.hashCode() else 0)) * 31;
      val var3: java.lang.Throwable = this.getCause();
      return var2 + (if (var3 != null) var3.hashCode() else 0);
   }
}
