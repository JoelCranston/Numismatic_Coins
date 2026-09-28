package kotlinx.io.bytestring

@ExperimentalStdlibApi
public fun ByteString.toHexString(format: HexFormat = HexFormat.Companion.getDefault()): String {
   return HexExtensionsKt.toHexString(`$this$toHexString`.getBackingArrayReference(), 0, `$this$toHexString`.getBackingArrayReference().length, format);
}

@JvmSynthetic
fun `toHexString$default`(var0: ByteString, var1: HexFormat, var2: Int, var3: Any): java.lang.String {
   if ((var2 and 1) != 0) {
      var1 = HexFormat.Companion.getDefault();
   }

   return toHexString(var0, var1);
}

@ExperimentalStdlibApi
public fun ByteString.toHexString(startIndex: Int = 0, endIndex: Int = `$this$toHexString`.getSize(), format: HexFormat = HexFormat.Companion.getDefault()): String {
   return HexExtensionsKt.toHexString(`$this$toHexString`.getBackingArrayReference(), startIndex, endIndex, format);
}

@JvmSynthetic
fun `toHexString$default`(var0: ByteString, var1: Int, var2: Int, var3: HexFormat, var4: Int, var5: Any): java.lang.String {
   if ((var4 and 1) != 0) {
      var1 = 0;
   }

   if ((var4 and 2) != 0) {
      var2 = var0.getSize();
   }

   if ((var4 and 4) != 0) {
      var3 = HexFormat.Companion.getDefault();
   }

   return toHexString(var0, var1, var2, var3);
}

@ExperimentalStdlibApi
public fun String.hexToByteString(format: HexFormat = HexFormat.Companion.getDefault()): ByteString {
   return ByteString.Companion.wrap$kotlinx_io_bytestring(HexExtensionsKt.hexToByteArray(`$this$hexToByteString`, format));
}

@JvmSynthetic
fun `hexToByteString$default`(var0: java.lang.String, var1: HexFormat, var2: Int, var3: Any): ByteString {
   if ((var2 and 1) != 0) {
      var1 = HexFormat.Companion.getDefault();
   }

   return hexToByteString(var0, var1);
}
