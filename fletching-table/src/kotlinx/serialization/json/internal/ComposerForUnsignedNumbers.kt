package kotlinx.serialization.json.internal

@SuppressAnimalSniffer
internal class ComposerForUnsignedNumbers(writer: InternalJsonWriter, forceQuoting: Boolean) : Composer(writer) {
   private final val forceQuoting: Boolean

   init {
      this.forceQuoting = forceQuoting;
   }

   public override fun print(v: Int) {
      if (this.forceQuoting) {
         this.printQuoted(Integer.toUnsignedString(UInt.constructor-impl(v)));
      } else {
         this.print(Integer.toUnsignedString(UInt.constructor-impl(v)));
      }
   }

   public override fun print(v: Long) {
      if (this.forceQuoting) {
         this.printQuoted(java.lang.Long.toUnsignedString(ULong.constructor-impl(v)));
      } else {
         this.print(java.lang.Long.toUnsignedString(ULong.constructor-impl(v)));
      }
   }

   public override fun print(v: Byte) {
      if (this.forceQuoting) {
         this.printQuoted(UByte.toString-impl(UByte.constructor-impl(v)));
      } else {
         this.print(UByte.toString-impl(UByte.constructor-impl(v)));
      }
   }

   public override fun print(v: Short) {
      if (this.forceQuoting) {
         this.printQuoted(UShort.toString-impl(UShort.constructor-impl(v)));
      } else {
         this.print(UShort.toString-impl(UShort.constructor-impl(v)));
      }
   }
}
