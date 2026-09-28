package io.ktor.utils.io

import kotlinx.coroutines.Job

public fun ByteChannel.attachJob(job: Job) {
   job.invokeOnCompletion(ByteChannelUtilsKt::attachJob$lambda$0);
}

public fun ByteChannel.attachJob(job: ChannelJob) {
   attachJob(`$this$attachJob`, job.getJob());
}

fun `attachJob$lambda$0`(`$this_attachJob`: ByteChannel, it: java.lang.Throwable): Unit {
   if (it != null) {
      `$this_attachJob`.cancel(it);
   }

   return Unit.INSTANCE;
}
