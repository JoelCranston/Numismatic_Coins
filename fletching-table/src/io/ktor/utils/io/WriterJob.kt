package io.ktor.utils.io

import kotlinx.coroutines.Job

public class WriterJob internal constructor(channel: ByteReadChannel, job: Job) : ChannelJob {
   public final val channel: ByteReadChannel
   public open val job: Job

   init {
      this.channel = channel;
      this.job = job;
   }
}
