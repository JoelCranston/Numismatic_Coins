package kotlin.jvm.internal

public class ByteSpreadBuilder(size: Int) : PrimitiveSpreadBuilder(size) {
   private final val values: ByteArray

   init {
      this.values = new byte[size];
   }

   protected open fun ByteArray.getSize(): Int {
      return `$this$getSize`.length;
   }

   public fun add(value: Byte) {
      val var10000: ByteArray = this.values;
      val var2: Int = this.getPosition();
      this.setPosition(var2 + 1);
      var10000[var2] = value;
   }

   public fun toArray(): ByteArray {
      return this.toArray(this.values, new byte[this.size()]);
   }
}
