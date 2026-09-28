package kotlinx.serialization.json.internal

import kotlin.jvm.internal.SourceDebugExtension

@SourceDebugExtension(["SMAP\nArrayPools.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ArrayPools.kt\nkotlinx/serialization/json/internal/CharArrayPoolBase\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,90:1\n1#2:91\n*E\n"])
internal open class CharArrayPoolBase {
   private final val arrays: ArrayDeque<CharArray> = new ArrayDeque()
   private final var charsTotal: Int

   protected fun take(size: Int): CharArray {
      var var11: CharArray;
      synchronized (this) {
         var11 = this.arrays.removeLastOrNull();
         if (var11 != null) {
            this.charsTotal -= var11.length;
            var11 = var11;
         } else {
            var11 = null;
         }

         var11 = var11;
      }

      var11 = var11;
      if (var11 == null) {
         var11 = new char[size];
      }

      return var11;
   }

   protected fun releaseImpl(array: CharArray) {
      synchronized (this) {
         if (this.charsTotal + array.length < ArrayPoolsKt.access$getMAX_CHARS_IN_POOL$p()) {
            this.charsTotal += array.length;
            this.arrays.addLast(array);
         }
      }
   }
}
