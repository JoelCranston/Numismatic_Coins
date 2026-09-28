package kotlinx.io.bytestring

import kotlin.jvm.functions.Function1

public fun ByteStringBuilder.append(byte: UByte) {
   `$this$append_u2dEK_u2d6454`.append(var1);
}

public fun ByteStringBuilder.append(byteString: ByteString) {
   ByteStringBuilder.append$default(`$this$append`, byteString.getBackingArrayReference(), 0, 0, 6, null);
}

public fun ByteStringBuilder.append(bytes: ByteArray) {
   ByteStringBuilder.append$default(`$this$append`, bytes, 0, 0, 6, null);
}

public inline fun buildByteString(capacity: Int = 0, builderAction: (ByteStringBuilder) -> Unit): ByteString {
   val var3: ByteStringBuilder = new ByteStringBuilder(capacity);
   builderAction.invoke(var3);
   return var3.toByteString();
}

@JvmSynthetic
fun `buildByteString$default`(capacity: Int, builderAction: Function1, `$i$f$buildByteString`: Int, var3: Any): ByteString {
   if ((`$i$f$buildByteString` and 1) != 0) {
      capacity = 0;
   }

   var3 = new ByteStringBuilder(capacity);
   builderAction.invoke(var3);
   return var3.toByteString();
}
