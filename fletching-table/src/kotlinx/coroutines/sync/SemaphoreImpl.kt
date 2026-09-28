package kotlinx.coroutines.sync

private class SemaphoreImpl(permits: Int, acquiredPermits: Int) : SemaphoreAndMutexImpl(permits, acquiredPermits), Semaphore
