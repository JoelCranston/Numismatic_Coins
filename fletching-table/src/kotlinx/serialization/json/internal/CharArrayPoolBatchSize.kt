package kotlinx.serialization.json.internal

import kotlin.jvm.internal.SourceDebugExtension

@SourceDebugExtension(["SMAP\nArrayPools.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ArrayPools.kt\nkotlinx/serialization/json/internal/CharArrayPoolBatchSize\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,90:1\n1#2:91\n*E\n"])
internal object CharArrayPoolBatchSize : CharArrayPoolBase {
   public fun take(): CharArray {
      return super.take(16384);
   }

   public fun release(array: CharArray) {
      if (array.length != 16384) {
         throw new IllegalArgumentException(("Inconsistent internal invariant: unexpected array size ${array.length}").toString());
      } else {
         this.releaseImpl(array);
      }
   }
}
