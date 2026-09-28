package io.ktor.network.sockets

@JvmInline
public inline class TypeOfService {
   public final val value: UByte

   public final val intValue: Int
      public final inline get() {
         return var0 and 255;
      }


   @JvmStatic
   fun `constructor-impl`(value: Int): Byte {
      return constructor-impl(UByte.constructor-impl((byte)value));
   }

   @JvmStatic
   fun `toString-impl`(var0: Byte): java.lang.String {
      return "TypeOfService(value=${UByte.toString-impl(var0)})";
   }

   public override fun toString(): String {
      return toString-impl(this.value);
   }

   @JvmStatic
   fun `hashCode-impl`(var0: Byte): Int {
      return UByte.hashCode-impl(var0);
   }

   public override fun hashCode(): Int {
      return hashCode-impl(this.value);
   }

   @JvmStatic
   fun `equals-impl`(var0: Byte, other: Any): Boolean {
      if (other !is TypeOfService) {
         return false;
      } else {
         return var0 == (other as TypeOfService).unbox-impl();
      }
   }

   public override operator fun equals(other: Any?): Boolean {
      return equals-impl(this.value, other);
   }

   @JvmStatic
   fun `constructor-impl`(var0: Byte): Byte {
      return var0;
   }

   @JvmStatic
   fun `equals-impl0`(p1: Byte, p2: Byte): Boolean {
      return UByte.equals-impl0(p1, p2);
   }

   public companion object {
      public final val UNDEFINED: TypeOfService
      public final val IPTOS_LOWCOST: TypeOfService
      public final val IPTOS_RELIABILITY: TypeOfService
      public final val IPTOS_THROUGHPUT: TypeOfService
      public final val IPTOS_LOWDELAY: TypeOfService
   }
}
