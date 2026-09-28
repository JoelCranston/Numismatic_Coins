package kotlin.random

import kotlin.random.FallbackThreadLocalRandom.implStorage.1

internal class FallbackThreadLocalRandom : AbstractPlatformRandom {
   private final val implStorage: 1 = new 1()

   public open val impl: java.util.Random
      public open get() {
         val var10000: Any = this.implStorage.get();
         return var10000 as java.util.Random;
      }

}
