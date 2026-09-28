package kotlinx.io

import kotlin.contracts.InvocationKind

@PublishedApi
@JvmSynthetic
internal inline fun <T> Buffer.seek(fromIndex: Long, lambda: (Segment?, Long) -> T): T {
   contract {
      callsInPlace(lambda, InvocationKind.EXACTLY_ONCE)
   }

   if (`$this$seek`.getHead() == null) {
      return (T)lambda.invoke(null, -1L);
   } else if (`$this$seek`.getSize() - fromIndex < fromIndex) {
      var var10: Segment = `$this$seek`.getTail();

      var var11: Long;
      for (offset = $this$seek.getSize(); s != null && offset > fromIndex; s = s.getPrev()) {
         var11 -= var10.getLimit() - var10.getPos();
         if (var11 <= fromIndex) {
            break;
         }
      }

      return (T)lambda.invoke(var10, var11);
   } else {
      var s: Segment = `$this$seek`.getHead();
      var offset: Long = 0L;

      while (s != null) {
         val nextOffset: Long = offset + (s.getLimit() - s.getPos());
         if (nextOffset > fromIndex) {
            break;
         }

         s = s.getNext();
         offset = nextOffset;
      }

      return (T)lambda.invoke(s, offset);
   }
}
