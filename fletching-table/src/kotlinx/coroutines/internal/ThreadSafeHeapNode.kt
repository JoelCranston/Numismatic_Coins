package kotlinx.coroutines.internal

import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
public interface ThreadSafeHeapNode {
   public var heap: ThreadSafeHeap<*>?
      internal final set

   public var index: Int
      internal final set
}
