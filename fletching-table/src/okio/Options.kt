package okio

import java.util.ArrayList
import java.util.Arrays
import java.util.RandomAccess
import kotlin.jvm.internal.SourceDebugExtension

public class Options private constructor(vararg byteStrings: Any, trie: IntArray) : AbstractList<ByteString>, RandomAccess {
   internal final val byteStrings: Array<out ByteString>
   internal final val trie: IntArray

   public open val size: Int
      public open get() {
         return this.byteStrings.length;
      }


   init {
      this.byteStrings = byteStrings;
      this.trie = trie;
   }

   public open operator fun get(index: Int): ByteString {
      return this.byteStrings[index];
   }

   @SourceDebugExtension(["SMAP\nOptions.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Options.kt\nokio/Options$Companion\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n+ 4 Util.kt\nokio/-SegmentedByteString\n*L\n1#1,238:1\n1#2:239\n13870#3,3:240\n73#4:243\n73#4:244\n*S KotlinDebug\n*F\n+ 1 Options.kt\nokio/Options$Companion\n*L\n48#1:240,3\n153#1:243\n210#1:244\n*E\n"])
   public companion object {
      private final val intCount: Long
         private final get() {
            return `$this$intCount`.size() / 4;
         }


      public fun of(vararg byteStrings: ByteString): Options {
         if (byteStrings.length == 0) {
            return new Options(new ByteString[0], new int[]{0, -1}, null);
         } else {
            val list: java.util.List = ArraysKt.toMutableList(byteStrings);
            CollectionsKt.sort(list);
            val a: Int = list.size();
            val trieBytes: ArrayList = new ArrayList(a);

            for (int trie = 0; trie < a; trie++) {
               trieBytes.add(-1);
            }

            val indexes: java.util.List = trieBytes;
            var var23: Int = 0;

            for (Object item$iv : byteStrings) {
               indexes.set(CollectionsKt.binarySearch$default(list, var28 as java.lang.Comparable, 0, 0, 6, null), var23++);
            }

            if ((list.get(0) as ByteString).size() <= 0) {
               throw new IllegalArgumentException("the empty byte string is not a supported option".toString());
            } else {
               for (int ax = 0; ax < list.size(); ax++) {
                  val var19: ByteString = list.get(ax) as ByteString;
                  var23 = ax + 1;

                  while (index$iv < list.size()) {
                     val var25: ByteString = list.get(var23) as ByteString;
                     if (!var25.startsWith(var19)) {
                        break;
                     }

                     if (var25.size() == var19.size()) {
                        throw new IllegalArgumentException(("duplicate option: $var25").toString());
                     }

                     if ((indexes.get(var23) as java.lang.Number).intValue() > (indexes.get(ax) as java.lang.Number).intValue()) {
                        list.remove(var23);
                        (indexes.remove(var23) as java.lang.Number).intValue();
                     } else {
                        var23++;
                     }
                  }
               }

               val var20: Buffer = new Buffer();
               buildTrieRecursive$default(this, 0L, var20, 0, list, 0, 0, indexes, 53, null);
               var var26: Int = 0;
               val var27: Int = (int)this.getIntCount(var20);

               val var31: IntArray;
               for (var31 = new int[var27]; var26 < var27; var26++) {
                  var31[var26] = var20.readInt();
               }

               val var10002: Array<Any> = Arrays.copyOf(byteStrings, byteStrings.length);
               return new Options(var10002 as Array<ByteString>, var31, null);
            }
         }
      }

      private fun buildTrieRecursive(
         nodeOffset: Long = 0L,
         node: Buffer,
         byteStringOffset: Int = 0,
         byteStrings: List<ByteString>,
         fromIndex: Int = 0,
         toIndex: Int = byteStrings.size(),
         indexes: List<Int>
      ) {
         if (fromIndex >= toIndex) {
            throw new IllegalArgumentException("Failed requirement.".toString());
         } else {
            for (int i = fromIndex; i < toIndex; i++) {
               if ((byteStrings.get(fromIndex) as ByteString).size() < byteStringOffset) {
                  throw new IllegalArgumentException("Failed requirement.".toString());
               }
            }

            var var21: Int = fromIndex;
            var from: ByteString = byteStrings.get(fromIndex) as ByteString;
            val var23: ByteString = byteStrings.get(toIndex - 1) as ByteString;
            var prefixIndex: Int = -1;
            if (byteStringOffset == from.size()) {
               prefixIndex = (indexes.get(fromIndex) as java.lang.Number).intValue();
               var21 = fromIndex + 1;
               from = byteStrings.get(fromIndex + 1) as ByteString;
            }

            if (from.getByte(byteStringOffset) != var23.getByte(byteStringOffset)) {
               var scanByteCount: Int = 1;

               for (int ix = fromIndex + 1; ix < toIndex; ix++) {
                  if ((byteStrings.get(ix - 1) as ByteString).getByte(byteStringOffset) != (byteStrings.get(ix) as ByteString).getByte(byteStringOffset)) {
                     scanByteCount++;
                  }
               }

               val var25: Long = nodeOffset + this.getIntCount(node) + 2 + scanByteCount * 2;
               node.writeInt(scanByteCount);
               node.writeInt(prefixIndex);

               for (int ixx = fromIndex; ixx < toIndex; ixx++) {
                  val rangeStart: Byte = (byteStrings.get(ixx) as ByteString).getByte(byteStringOffset);
                  if (ixx == var21 || rangeStart != (byteStrings.get(ixx - 1) as ByteString).getByte(byteStringOffset)) {
                     node.writeInt(rangeStart and 255);
                  }
               }

               val var28: Buffer = new Buffer();
               var var31: Int = var21;

               while (rangeStart < toIndex) {
                  val `$this$and$iv`: Byte = (byteStrings.get(var31) as ByteString).getByte(byteStringOffset);
                  var var34: Int = toIndex;

                  for (int ixxx = rangeStart + 1; ixxx < toIndex; ixxx++) {
                     if (`$this$and$iv` != (byteStrings.get(ixxx) as ByteString).getByte(byteStringOffset)) {
                        var34 = ixxx;
                        break;
                     }
                  }

                  if (var31 + 1 == var34 && byteStringOffset + 1 == (byteStrings.get(var31) as ByteString).size()) {
                     node.writeInt((indexes.get(var31) as java.lang.Number).intValue());
                  } else {
                     node.writeInt(-1 * (int)(var25 + this.getIntCount(var28)));
                     this.buildTrieRecursive(var25, var28, byteStringOffset + 1, byteStrings, var31, var34, indexes);
                  }

                  var31 = var34;
               }

               node.writeAll(var28);
            } else {
               var var24: Int = 0;
               var ixxxx: Int = byteStringOffset;

               for (int var15 = Math.min(from.size(), to.size()); ixxxx < var15 && from.getByte(ixxxx) == to.getByte(ixxxx); ixxxx++) {
                  var24++;
               }

               val var27: Long = nodeOffset + this.getIntCount(node) + 2 + var24 + 1L;
               node.writeInt(-var24);
               node.writeInt(prefixIndex);
               var ixxxxx: Int = byteStringOffset;

               for (int var32 = byteStringOffset + scanByteCount; ixxxxx < var32; ixxxxx++) {
                  node.writeInt(from.getByte(ixxxxx) and 255);
               }

               if (var21 + 1 == toIndex) {
                  if (byteStringOffset + var24 != (byteStrings.get(var21) as ByteString).size()) {
                     throw new IllegalStateException("Check failed.");
                  }

                  node.writeInt((indexes.get(var21) as java.lang.Number).intValue());
               } else {
                  val var30: Buffer = new Buffer();
                  node.writeInt(-1 * (int)(var27 + this.getIntCount(var30)));
                  this.buildTrieRecursive(var27, var30, byteStringOffset + var24, byteStrings, var21, toIndex, indexes);
                  node.writeAll(var30);
               }
            }
         }
      }
   }
}
