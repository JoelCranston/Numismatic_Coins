package okio

import kotlin.jvm.internal.Intrinsics
import kotlin.jvm.internal.SourceDebugExtension

@SourceDebugExtension(["SMAP\nAsyncTimeout.kt\nKotlin\n*S Kotlin\n*F\n+ 1 AsyncTimeout.kt\nokio/PriorityQueue\n*L\n1#1,514:1\n509#1,3:515\n509#1,3:518\n509#1,3:521\n509#1,3:524\n*S KotlinDebug\n*F\n+ 1 AsyncTimeout.kt\nokio/PriorityQueue\n*L\n415#1:515,3\n448#1:518,3\n481#1:521,3\n491#1:524,3\n*E\n"])
internal class PriorityQueue {
   internal final var size: Int
      private set

   internal final var array: Array<AsyncTimeout?>
      private set

   public fun first(): AsyncTimeout? {
      return this.array[1];
   }

   public fun add(node: AsyncTimeout) {
      val newSize: Int = this.size + 1;
      this.size += 1;
      if (newSize == this.array.length) {
         val doubledArray: Array<AsyncTimeout> = new AsyncTimeout[newSize * 2];
         ArraysKt.copyInto$default(this.array, doubledArray, 0, 0, 0, 14, null);
         this.array = doubledArray;
      }

      this.heapifyUp(newSize, node);
   }

   public fun remove(node: AsyncTimeout) {
      if (node.index == -1) {
         throw new IllegalArgumentException("Failed requirement.".toString());
      } else {
         val oldSize: Int = this.size;
         val removedIndex: Int = node.index;
         val var10000: AsyncTimeout = this.array[this.size];
         node.index = -1;
         this.array[oldSize] = null;
         this.size = oldSize - 1;
         if (node != var10000) {
            val nodeCompareToLast: Int = Intrinsics.compare(0L, var10000.getTimeoutAt$okio() - node.getTimeoutAt$okio());
            if (nodeCompareToLast == 0) {
               this.array[removedIndex] = var10000;
               var10000.index = removedIndex;
            } else if (nodeCompareToLast < 0) {
               this.heapifyDown(removedIndex, var10000);
            } else {
               this.heapifyUp(removedIndex, var10000);
            }
         }
      }
   }

   private fun heapifyUp(vacantIndex: Int, node: AsyncTimeout) {
      var vacantIndexx: Int = vacantIndex;

      while (true) {
         val parentIndex: Int = vacantIndexx shr 1;
         if (vacantIndexx shr 1 == 0) {
            break;
         }

         val var10000: AsyncTimeout = this.array[parentIndex];
         if (Intrinsics.compare(0L, node.getTimeoutAt$okio() - var10000.getTimeoutAt$okio()) <= 0) {
            break;
         }

         var10000.index = vacantIndexx;
         this.array[vacantIndexx] = var10000;
         vacantIndexx = parentIndex;
      }

      this.array[vacantIndexx] = node;
      node.index = vacantIndexx;
   }

   private fun heapifyDown(vacantIndex: Int, node: AsyncTimeout) {
      var vacantIndexx: Int = vacantIndex;

      while (true) {
         val leftIndex: Int = vacantIndexx shl 1;
         val rightIndex: Int = (vacantIndexx shl 1) + 1;
         var var20: AsyncTimeout;
         if ((vacantIndexx shl 1) + 1 <= this.size) {
            var20 = this.array[leftIndex];
            val var19: AsyncTimeout = this.array[rightIndex];
            var20 = if (Intrinsics.compare(0L, var19.getTimeoutAt$okio() - var20.getTimeoutAt$okio()) < 0) var20 else var19;
         } else {
            if (leftIndex > this.size) {
               break;
            }

            var20 = this.array[leftIndex];
         }

         if (Intrinsics.compare(0L, var20.getTimeoutAt$okio() - node.getTimeoutAt$okio()) <= 0) {
            break;
         }

         val var17: Int = var20.index;
         var20.index = vacantIndexx;
         this.array[vacantIndexx] = var20;
         vacantIndexx = var17;
      }

      this.array[vacantIndexx] = node;
      node.index = vacantIndexx;
   }

   private inline operator fun AsyncTimeout.compareTo(other: AsyncTimeout): Int {
      return Intrinsics.compare(0L, other.getTimeoutAt$okio() - `$this$compareTo`.getTimeoutAt$okio());
   }
}
