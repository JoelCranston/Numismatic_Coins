package kotlinx.serialization.json.internal

import kotlin.jvm.internal.SourceDebugExtension

@SourceDebugExtension(["SMAP\nArrayPools.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ArrayPools.kt\nkotlinx/serialization/json/internal/ByteArrayPoolBase\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,90:1\n1#2:91\n*E\n"])
internal open class ByteArrayPoolBase {
   private final val arrays: ArrayDeque<ByteArray> = new ArrayDeque()
   private final var bytesTotal: Int

   protected fun take(size: Int): ByteArray {
      var var11: ByteArray;
      synchronized (this) {
         var11 = this.arrays.removeLastOrNull();
         if (var11 != null) {
            this.bytesTotal -= var11.length / 2;
            var11 = var11;
         } else {
            var11 = null;
         }

         var11 = var11;
      }

      var11 = var11;
      if (var11 == null) {
         var11 = new byte[size];
      }

      return var11;
   }

   protected fun releaseImpl(array: ByteArray) {
      synchronized (this) {
         if (this.bytesTotal + array.length < ArrayPoolsKt.access$getMAX_CHARS_IN_POOL$p()) {
            this.bytesTotal += array.length / 2;
            this.arrays.addLast(array);
         }
      }
   }
}
