package kotlinx.io

internal fun Segment.indexOf(byte: Byte, startOffset: Int, endOffset: Int): Int {
   if (0 > startOffset || startOffset >= `$this$indexOf`.getSize()) {
      throw new IllegalArgumentException(java.lang.String.valueOf(startOffset).toString());
   } else if (startOffset > endOffset || endOffset > `$this$indexOf`.getSize()) {
      throw new IllegalArgumentException(java.lang.String.valueOf(endOffset).toString());
   } else {
      val p: Int = `$this$indexOf`.getPos();
      val data: ByteArray = `$this$indexOf`.dataAsByteArray(true);

      for (int idx = startOffset; idx < endOffset; idx++) {
         if (data[p + idx] == var1) {
            return idx;
         }
      }

      return -1;
   }
}

internal fun Segment.indexOfBytesInbound(bytes: ByteArray, startOffset: Int): Int {
   var offset: Int = startOffset;
   val limit: Int = `$this$indexOfBytesInbound`.getSize() - bytes.length + 1;
   val firstByte: Byte = bytes[0];

   for (byte[] data = $this$indexOfBytesInbound.dataAsByteArray(true); offset < limit; offset++) {
      val idx: Int = indexOf(`$this$indexOfBytesInbound`, firstByte, offset, limit);
      if (idx < 0) {
         return -1;
      }

      var found: Boolean = true;
      var innerIdx: Int = 1;

      for (int var10 = bytes.length; innerIdx < var10; innerIdx++) {
         if (data[`$this$indexOfBytesInbound`.getPos() + idx + innerIdx] != bytes[innerIdx]) {
            found = false;
            break;
         }
      }

      if (found) {
         return idx;
      }
   }

   return -1;
}

internal fun Segment.indexOfBytesOutbound(bytes: ByteArray, startOffset: Int): Int {
   var offset: Int = startOffset;

   for (byte firstByte = bytes[0]; 0 <= offset && offset < $this$indexOfBytesOutbound.getSize(); offset++) {
      if (indexOf(`$this$indexOfBytesOutbound`, firstByte, offset, `$this$indexOfBytesOutbound`.getSize()) < 0) {
         return -1;
      }

      var seg: Segment = `$this$indexOfBytesOutbound`;
      var data: ByteArray = `$this$indexOfBytesOutbound`.dataAsByteArray(true);
      var scanOffset: Int = offset;
      var found: Boolean = true;

      for (byte element : bytes) {
         if (scanOffset == seg.getSize()) {
            val var10000: Segment = seg.getNext();
            if (var10000 == null) {
               return -1;
            }

            seg = var10000;
            data = var10000.dataAsByteArray(true);
            scanOffset = 0;
         }

         if (element != data[seg.getPos() + scanOffset]) {
            found = false;
            break;
         }

         scanOffset++;
      }

      if (found) {
         return offset;
      }
   }

   return -1;
}

@PublishedApi
internal fun Segment.isEmpty(): Boolean {
   return `$this$isEmpty`.getSize() == 0;
}
