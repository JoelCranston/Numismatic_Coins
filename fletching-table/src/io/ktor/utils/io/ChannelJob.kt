package io.ktor.utils.io

import kotlinx.coroutines.Job

public interface ChannelJob {
   public val job: Job
}
