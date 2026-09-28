@file:JvmName(name = "-SegmentedByteString")

@file:SourceDebugExtension(["SMAP\nSegmentedByteString.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SegmentedByteString.kt\nokio/internal/-SegmentedByteString\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,250:1\n63#1,12:252\n85#1,14:264\n85#1,14:278\n85#1,14:292\n85#1,14:306\n63#1,12:320\n1#2:251\n*S KotlinDebug\n*F\n+ 1 SegmentedByteString.kt\nokio/internal/-SegmentedByteString\n*L\n147#1:252,12\n160#1:264,14\n182#1:278,14\n202#1:292,14\n219#1:306,14\n239#1:320,12\n*E\n"])

package okio.internal

import kotlin.jvm.internal.SourceDebugExtension
import okio.Buffer
import okio.ByteString
import okio.Segment
import okio.SegmentedByteString

internal fun IntArray.binarySearch(value: Int, fromIndex: Int, toIndex: Int): Int {
   var left: Int = fromIndex;
   var right: Int = toIndex - 1;

   while (left <= right) {
      val mid: Int = left + right ushr 1;
      val midVal: Int = `$this$binarySearch`[left + right ushr 1];
      if (`$this$binarySearch`[left + right ushr 1] < value) {
         left = mid + 1;
      } else {
         if (midVal <= value) {
            return mid;
         }

         right = mid - 1;
      }
   }

   return -left - 1;
}

internal fun SegmentedByteString.segment(pos: Int): Int {
   val i: Int = binarySearch(`$this$segment`.getDirectory$okio(), pos + 1, 0, (`$this$segment`.getSegments$okio() as Array<Any>).length);
   return if (i >= 0) i else i.inv();
}

internal inline fun SegmentedByteString.forEachSegment(action: (ByteArray, Int, Int) -> Unit) {
   val segmentCount: Int = (`$this$forEachSegment`.getSegments$okio() as Array<Any>).length;
   var s: Int = 0;

   for (int pos = 0; s < segmentCount; s++) {
      val segmentPos: Int = `$this$forEachSegment`.getDirectory$okio()[segmentCount + s];
      val nextSegmentOffset: Int = `$this$forEachSegment`.getDirectory$okio()[s];
      action.invoke(`$this$forEachSegment`.getSegments$okio()[s], segmentPos, nextSegmentOffset - pos);
      pos = nextSegmentOffset;
   }
}

private inline fun SegmentedByteString.forEachSegment(beginIndex: Int, endIndex: Int, action: (ByteArray, Int, Int) -> Unit) {
   var s: Int = segment(`$this$forEachSegment`, beginIndex);

   for (int pos = beginIndex; pos < endIndex; s++) {
      val segmentOffset: Int = if (s == 0) 0 else `$this$forEachSegment`.getDirectory$okio()[s - 1];
      val segmentSize: Int = `$this$forEachSegment`.getDirectory$okio()[s] - segmentOffset;
      val segmentPos: Int = `$this$forEachSegment`.getDirectory$okio()[(`$this$forEachSegment`.getSegments$okio() as Array<Any>).length + s];
      val byteCount: Int = Math.min(endIndex, segmentOffset + segmentSize) - pos;
      action.invoke(`$this$forEachSegment`.getSegments$okio()[s], segmentPos + (pos - segmentOffset), byteCount);
      pos += byteCount;
   }
}

internal inline fun SegmentedByteString.commonSubstring(beginIndex: Int, endIndex: Int): ByteString {
   val endIndexx: Int = okio.-SegmentedByteString.resolveDefaultParameter(`$this$commonSubstring`, endIndex);
   if (beginIndex < 0) {
      throw new IllegalArgumentException(("beginIndex=$beginIndex < 0").toString());
   } else if (endIndexx > `$this$commonSubstring`.size()) {
      throw new IllegalArgumentException(("endIndex=$endIndexx > length(${`$this$commonSubstring`.size()})").toString());
   } else {
      val subLen: Int = endIndexx - beginIndex;
      if (endIndexx - beginIndex < 0) {
         throw new IllegalArgumentException(("endIndex=$endIndexx < beginIndex=$beginIndex").toString());
      } else if (beginIndex == 0 && endIndexx == `$this$commonSubstring`.size()) {
         return `$this$commonSubstring`;
      } else if (beginIndex == endIndexx) {
         return ByteString.EMPTY;
      } else {
         val beginSegment: Int = segment(`$this$commonSubstring`, beginIndex);
         val endSegment: Int = segment(`$this$commonSubstring`, endIndexx - 1);
         val newSegments: Array<ByteArray> = ArraysKt.copyOfRange(
            (byte[][])(`$this$commonSubstring`.getSegments$okio() as Array<Any>), beginSegment, endSegment + 1
         );
         val newDirectory: IntArray = new int[(newSegments as Array<Any>).length * 2];
         var index: Int = 0;
         var segmentOffset: Int = beginSegment;
         if (beginSegment <= endSegment) {
            while (true) {
               newDirectory[index] = Math.min(`$this$commonSubstring`.getDirectory$okio()[segmentOffset] - beginIndex, subLen);
               newDirectory[index++ + (newSegments as Array<Any>).length] = `$this$commonSubstring`.getDirectory$okio()[segmentOffset
                  + (`$this$commonSubstring`.getSegments$okio() as Array<Any>).length];
               if (segmentOffset == endSegment) {
                  break;
               }

               segmentOffset++;
            }
         }

         newDirectory[(newSegments as Array<Any>).length] = newDirectory[(newSegments as Array<Any>).length]
            + (beginIndex - (if (beginSegment == 0) 0 else `$this$commonSubstring`.getDirectory$okio()[beginSegment - 1]));
         return new SegmentedByteString(newSegments, newDirectory);
      }
   }
}

internal inline fun SegmentedByteString.commonInternalGet(pos: Int): Byte {
   okio.-SegmentedByteString.checkOffsetAndCount(
      (long)`$this$commonInternalGet`.getDirectory$okio()[(`$this$commonInternalGet`.getSegments$okio() as Array<Any>).length - 1], (long)pos, 1L
   );
   val segment: Int = segment(`$this$commonInternalGet`, pos);
   return `$this$commonInternalGet`.getSegments$okio()[segment][pos
      - (if (segment == 0) 0 else `$this$commonInternalGet`.getDirectory$okio()[segment - 1])
      + `$this$commonInternalGet`.getDirectory$okio()[segment + (`$this$commonInternalGet`.getSegments$okio() as Array<Any>).length]];
}

internal inline fun SegmentedByteString.commonGetSize(): Int {
   return `$this$commonGetSize`.getDirectory$okio()[(`$this$commonGetSize`.getSegments$okio() as Array<Any>).length - 1];
}

internal inline fun SegmentedByteString.commonToByteArray(): ByteArray {
   val result: ByteArray = new byte[`$this$commonToByteArray`.size()];
   var resultPos: Int = 0;
   val `$this$forEachSegment$iv`: SegmentedByteString = `$this$commonToByteArray`;
   val `segmentCount$iv`: Int = (`$this$commonToByteArray`.getSegments$okio() as Array<Any>).length;
   var `s$iv`: Int = 0;

   for (int pos$iv = 0; s$iv < segmentCount$iv; s$iv++) {
      val `segmentPos$iv`: Int = `$this$forEachSegment$iv`.getDirectory$okio()[`segmentCount$iv` + `s$iv`];
      val `nextSegmentOffset$iv`: Int = `$this$forEachSegment$iv`.getDirectory$okio()[`s$iv`];
      val var10000: ByteArray = `$this$forEachSegment$iv`.getSegments$okio()[`s$iv`];
      val byteCount: Int = `nextSegmentOffset$iv` - `pos$iv`;
      ArraysKt.copyInto(var10000, result, resultPos, `segmentPos$iv`, `segmentPos$iv` + (`nextSegmentOffset$iv` - `pos$iv`));
      resultPos += byteCount;
      `pos$iv` = `nextSegmentOffset$iv`;
   }

   return result;
}

internal inline fun SegmentedByteString.commonWrite(buffer: Buffer, offset: Int, byteCount: Int) {
   val `$this$forEachSegment$iv`: SegmentedByteString = `$this$commonWrite`;
   val `endIndex$iv`: Int = offset + byteCount;
   var `s$iv`: Int = segment(`$this$commonWrite`, offset);

   for (int pos$iv = offset; pos$iv < endIndex$iv; s$iv++) {
      val `segmentOffset$iv`: Int = if (`s$iv` == 0) 0 else `$this$forEachSegment$iv`.getDirectory$okio()[`s$iv` - 1];
      val `segmentSize$iv`: Int = `$this$forEachSegment$iv`.getDirectory$okio()[`s$iv`] - `segmentOffset$iv`;
      val `segmentPos$iv`: Int = `$this$forEachSegment$iv`.getDirectory$okio()[(`$this$forEachSegment$iv`.getSegments$okio() as Array<Any>).length + `s$iv`];
      val `byteCount$iv`: Int = Math.min(`endIndex$iv`, `segmentOffset$iv` + `segmentSize$iv`) - `pos$iv`;
      val segment: Segment = new Segment(
         `$this$forEachSegment$iv`.getSegments$okio()[`s$iv`],
         `segmentPos$iv` + (`pos$iv` - `segmentOffset$iv`),
         `segmentPos$iv` + (`pos$iv` - `segmentOffset$iv`) + `byteCount$iv`,
         true,
         false
      );
      if (buffer.head == null) {
         segment.prev = segment;
         segment.next = segment.prev;
         buffer.head = segment.next;
      } else {
         val var10000: Segment = buffer.head;
         val var21: Segment = var10000.prev;
         var21.push(segment);
      }

      `pos$iv` += `byteCount$iv`;
   }

   buffer.setSize$okio(buffer.size() + (long)byteCount);
}

internal inline fun SegmentedByteString.commonRangeEquals(offset: Int, other: ByteString, otherOffset: Int, byteCount: Int): Boolean {
   if (offset >= 0 && offset <= `$this$commonRangeEquals`.size() - byteCount) {
      var var22: Int = otherOffset;
      val `$this$forEachSegment$iv`: SegmentedByteString = `$this$commonRangeEquals`;
      val `endIndex$iv`: Int = offset + byteCount;
      var `s$iv`: Int = segment(`$this$commonRangeEquals`, offset);

      for (int pos$iv = offset; pos$iv < endIndex$iv; s$iv++) {
         val `segmentOffset$iv`: Int = if (`s$iv` == 0) 0 else `$this$forEachSegment$iv`.getDirectory$okio()[`s$iv` - 1];
         val `segmentSize$iv`: Int = `$this$forEachSegment$iv`.getDirectory$okio()[`s$iv`] - `segmentOffset$iv`;
         val `segmentPos$iv`: Int = `$this$forEachSegment$iv`.getDirectory$okio()[(`$this$forEachSegment$iv`.getSegments$okio() as Array<Any>).length + `s$iv`];
         val `byteCount$iv`: Int = Math.min(`endIndex$iv`, `segmentOffset$iv` + `segmentSize$iv`) - `pos$iv`;
         if (!other.rangeEquals(var22, `$this$forEachSegment$iv`.getSegments$okio()[`s$iv`], `segmentPos$iv` + (`pos$iv` - `segmentOffset$iv`), `byteCount$iv`)
            )
          {
            return false;
         }

         var22 += `byteCount$iv`;
         `pos$iv` += `byteCount$iv`;
      }

      return true;
   } else {
      return false;
   }
}

internal inline fun SegmentedByteString.commonRangeEquals(offset: Int, other: ByteArray, otherOffset: Int, byteCount: Int): Boolean {
   if (offset >= 0 && offset <= `$this$commonRangeEquals`.size() - byteCount && otherOffset >= 0 && otherOffset <= other.length - byteCount) {
      var var22: Int = otherOffset;
      val `$this$forEachSegment$iv`: SegmentedByteString = `$this$commonRangeEquals`;
      val `endIndex$iv`: Int = offset + byteCount;
      var `s$iv`: Int = segment(`$this$commonRangeEquals`, offset);

      for (int pos$iv = offset; pos$iv < endIndex$iv; s$iv++) {
         val `segmentOffset$iv`: Int = if (`s$iv` == 0) 0 else `$this$forEachSegment$iv`.getDirectory$okio()[`s$iv` - 1];
         val `segmentSize$iv`: Int = `$this$forEachSegment$iv`.getDirectory$okio()[`s$iv`] - `segmentOffset$iv`;
         val `segmentPos$iv`: Int = `$this$forEachSegment$iv`.getDirectory$okio()[(`$this$forEachSegment$iv`.getSegments$okio() as Array<Any>).length + `s$iv`];
         val `byteCount$iv`: Int = Math.min(`endIndex$iv`, `segmentOffset$iv` + `segmentSize$iv`) - `pos$iv`;
         if (!okio.-SegmentedByteString.arrayRangeEquals(
            `$this$forEachSegment$iv`.getSegments$okio()[`s$iv`], `segmentPos$iv` + (`pos$iv` - `segmentOffset$iv`), other, var22, `byteCount$iv`
         )) {
            return false;
         }

         var22 += `byteCount$iv`;
         `pos$iv` += `byteCount$iv`;
      }

      return true;
   } else {
      return false;
   }
}

internal inline fun SegmentedByteString.commonCopyInto(offset: Int, target: ByteArray, targetOffset: Int, byteCount: Int) {
   okio.-SegmentedByteString.checkOffsetAndCount((long)`$this$commonCopyInto`.size(), (long)offset, (long)byteCount);
   okio.-SegmentedByteString.checkOffsetAndCount((long)target.length, (long)targetOffset, (long)byteCount);
   var var22: Int = targetOffset;
   val `$this$forEachSegment$iv`: SegmentedByteString = `$this$commonCopyInto`;
   val `endIndex$iv`: Int = offset + byteCount;
   var `s$iv`: Int = segment(`$this$commonCopyInto`, offset);

   for (int pos$iv = offset; pos$iv < endIndex$iv; s$iv++) {
      val `segmentOffset$iv`: Int = if (`s$iv` == 0) 0 else `$this$forEachSegment$iv`.getDirectory$okio()[`s$iv` - 1];
      val `segmentSize$iv`: Int = `$this$forEachSegment$iv`.getDirectory$okio()[`s$iv`] - `segmentOffset$iv`;
      val `segmentPos$iv`: Int = `$this$forEachSegment$iv`.getDirectory$okio()[(`$this$forEachSegment$iv`.getSegments$okio() as Array<Any>).length + `s$iv`];
      val `byteCount$iv`: Int = Math.min(`endIndex$iv`, `segmentOffset$iv` + `segmentSize$iv`) - `pos$iv`;
      ArraysKt.copyInto(
         `$this$forEachSegment$iv`.getSegments$okio()[`s$iv`],
         target,
         var22,
         `segmentPos$iv` + (`pos$iv` - `segmentOffset$iv`),
         `segmentPos$iv` + (`pos$iv` - `segmentOffset$iv`) + `byteCount$iv`
      );
      var22 += `byteCount$iv`;
      `pos$iv` += `byteCount$iv`;
   }
}

internal inline fun SegmentedByteString.commonEquals(other: Any?): Boolean {
   return other === `$this$commonEquals`
      || other is ByteString
         && (other as ByteString).size() == `$this$commonEquals`.size()
         && `$this$commonEquals`.rangeEquals(0, other as ByteString, 0, `$this$commonEquals`.size());
}

internal inline fun SegmentedByteString.commonHashCode(): Int {
   var var16: Int = `$this$commonHashCode`.getHashCode$okio();
   if (var16 != 0) {
      return var16;
   } else {
      var16 = 1;
      val `$this$forEachSegment$iv`: SegmentedByteString = `$this$commonHashCode`;
      val `segmentCount$iv`: Int = (`$this$commonHashCode`.getSegments$okio() as Array<Any>).length;
      var `s$iv`: Int = 0;

      for (int pos$iv = 0; s$iv < segmentCount$iv; s$iv++) {
         val `segmentPos$iv`: Int = `$this$forEachSegment$iv`.getDirectory$okio()[`segmentCount$iv` + `s$iv`];
         val `nextSegmentOffset$iv`: Int = `$this$forEachSegment$iv`.getDirectory$okio()[`s$iv`];
         val var10000: ByteArray = `$this$forEachSegment$iv`.getSegments$okio()[`s$iv`];
         val byteCount: Int = `nextSegmentOffset$iv` - `pos$iv`;
         val data: ByteArray = var10000;
         var i: Int = `segmentPos$iv`;

         for (int limit = segmentPos$iv + byteCount; i < limit; i++) {
            var16 = 31 * var16 + data[i];
         }

         `pos$iv` = `nextSegmentOffset$iv`;
      }

      `$this$commonHashCode`.setHashCode$okio(var16);
      return var16;
   }
}
