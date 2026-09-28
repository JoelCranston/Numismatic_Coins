@file:SourceDebugExtension(["SMAP\nByteOrder.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ByteOrder.kt\nio/ktor/utils/io/bits/ByteOrderKt\n+ 2 ByteOrderJvm.kt\nio/ktor/utils/io/bits/ByteOrderJVMKt\n*L\n1#1,74:1\n11#2:75\n19#2:76\n27#2:77\n*S KotlinDebug\n*F\n+ 1 ByteOrder.kt\nio/ktor/utils/io/bits/ByteOrderKt\n*L\n47#1:75\n54#1:76\n61#1:77\n*E\n"])

package io.ktor.utils.io.bits

import kotlin.jvm.internal.SourceDebugExtension

public final val highByte: Byte
   public final inline get() {
      return (byte)(`$this$highByte` ushr 8);
   }


public final val lowByte: Byte
   public final inline get() {
      return (byte)(`$this$lowByte` and 255);
   }


public final val highShort: Short
   public final inline get() {
      return (short)(`$this$highShort` ushr 16);
   }


public final val lowShort: Short
   public final inline get() {
      return (short)(`$this$lowShort` and '\uffff');
   }


public final val highInt: Int
   public final inline get() {
      return (int)(`$this$highInt` ushr 32);
   }


public final val lowInt: Int
   public final inline get() {
      return (int)(`$this$lowInt` and 4294967295L);
   }


public fun UShort.reverseByteOrder(): UShort {
   return UShort.constructor-impl(java.lang.Short.reverseBytes(var0));
}

public fun UInt.reverseByteOrder(): UInt {
   return UInt.constructor-impl(Integer.reverseBytes(var0));
}

public fun ULong.reverseByteOrder(): ULong {
   return ULong.constructor-impl(java.lang.Long.reverseBytes(var0));
}
