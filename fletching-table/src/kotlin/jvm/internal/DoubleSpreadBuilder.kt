package kotlin.jvm.internal

public class DoubleSpreadBuilder(size: Int) : PrimitiveSpreadBuilder(size) {
   private final val values: DoubleArray

   init {
      this.values = new double[size];
   }

   protected open fun DoubleArray.getSize(): Int {
      return `$this$getSize`.length;
   }

   public fun add(value: Double) {
      val var10000: DoubleArray = this.values;
      val var3: Int = this.getPosition();
      this.setPosition(var3 + 1);
      var10000[var3] = value;
   }

   public fun toArray(): DoubleArray {
      return this.toArray(this.values, new double[this.size()]);
   }
}
