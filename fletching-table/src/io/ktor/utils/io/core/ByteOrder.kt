package io.ktor.utils.io.core

import kotlin.enums.EnumEntries

public enum class ByteOrder(nioOrder: java.nio.ByteOrder) {
   BIG_ENDIAN,
   LITTLE_ENDIAN
   public final val nioOrder: java.nio.ByteOrder
   @JvmStatic
   public ByteOrder.Companion Companion = new ByteOrder.Companion(null);
   @JvmStatic
   private ByteOrder native;

   init {
      this.nioOrder = nioOrder;
   }

   @JvmStatic
   fun getEntries(): EnumEntries<ByteOrder> {
      return $ENTRIES;
   }

   // $VF: Failed to inline enum fields
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   @JvmStatic
   fun {
      var var10004: java.nio.ByteOrder = java.nio.ByteOrder.BIG_ENDIAN;
      BIG_ENDIAN = new ByteOrder(var10004);
      var10004 = java.nio.ByteOrder.LITTLE_ENDIAN;
      LITTLE_ENDIAN = new ByteOrder(var10004);
      val var10000: java.nio.ByteOrder = java.nio.ByteOrder.nativeOrder();
      native = ByteOrderJVMKt.access$orderOf(var10000);
   }

   public companion object {
      private final val native: ByteOrder

      public fun of(nioOrder: java.nio.ByteOrder): ByteOrder {
         return ByteOrderJVMKt.access$orderOf(nioOrder);
      }

      public fun nativeOrder(): ByteOrder {
         return ByteOrder.access$getNative$cp();
      }
   }
}
