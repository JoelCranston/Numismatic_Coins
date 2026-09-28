package kotlin.jvm.internal

public abstract class PrimitiveSpreadBuilder<T> {
   private final val size: Int

   protected final var position: Int
      internal set

   private final val spreads: Array<Any?>

   open fun PrimitiveSpreadBuilder(size: Int) {
      this.size = size;
      this.spreads = (T[])(new Object[this.size]);
   }

   protected abstract fun Any.getSize(): Int {
   }

   public fun addSpread(spreadArgument: Any) {
      this.spreads[this.position++] = (T)spreadArgument;
   }

   protected fun size(): Int {
      var totalLength: Int = 0;
      var i: Int = 0;
      val var3: Int = this.size - 1;
      if (0 <= this.size - 1) {
         while (true) {
            totalLength += if (this.spreads[i] != null) this.getSize(this.spreads[i]) else 1;
            if (i == var3) {
               break;
            }

            i++;
         }
      }

      return totalLength;
   }

   protected fun toArray(values: Any, result: Any): Any {
      var dstIndex: Int = 0;
      var copyValuesFrom: Int = 0;
      var i: Int = 0;
      val var6: Int = this.size - 1;
      if (0 <= this.size - 1) {
         while (true) {
            val spreadArgument: Any = this.spreads[i];
            if (this.spreads[i] != null) {
               if (copyValuesFrom < i) {
                  System.arraycopy(values, copyValuesFrom, result, dstIndex, i - copyValuesFrom);
                  dstIndex += i - copyValuesFrom;
               }

               val spreadSize: Int = this.getSize((T)spreadArgument);
               System.arraycopy(spreadArgument, 0, result, dstIndex, spreadSize);
               dstIndex += spreadSize;
               copyValuesFrom = i + 1;
            }

            if (i == var6) {
               break;
            }

            i++;
         }
      }

      if (copyValuesFrom < this.size) {
         System.arraycopy(values, copyValuesFrom, result, dstIndex, this.size - copyValuesFrom);
      }

      return (T)result;
   }
}
