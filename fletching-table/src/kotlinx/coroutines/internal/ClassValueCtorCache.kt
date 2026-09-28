package kotlinx.coroutines.internal

import kotlinx.coroutines.internal.ClassValueCtorCache.cache.1
import org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement

@IgnoreJRERequirement
private object ClassValueCtorCache : CtorCache {
   private final val cache: 1 = new 1()

   public override fun get(key: Class<out Throwable>): (Throwable) -> Throwable? {
      return cache.get(key);
   }
}
