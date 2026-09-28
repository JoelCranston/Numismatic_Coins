package io.ktor.utils.io.core

import kotlinx.io.Buffer
import kotlinx.io.Sink
import kotlinx.io.Source

public final val size: Int
   public final get() {
      return (int)`$this$size`.getBuffer().getSize();
   }


public fun BytePacketBuilder(): Sink {
   return new Buffer();
}

public fun Sink.append(value: CharSequence, startIndex: Int = 0, endIndex: Int = value.length()) {
   StringsKt.writeText$default(`$this$append`, value, startIndex, endIndex, null, 8, null);
}

@JvmSynthetic
fun `append$default`(var0: Sink, var1: java.lang.CharSequence, var2: Int, var3: Int, var4: Int, var5: Any) {
   if ((var4 and 2) != 0) {
      var2 = 0;
   }

   if ((var4 and 4) != 0) {
      var3 = var1.length();
   }

   append(var0, var1, var2, var3);
}

public fun Sink.build(): Source {
   return `$this$build`.getBuffer();
}

public fun Sink.writeFully(buffer: ByteArray, offset: Int = 0, length: Int = buffer.length - offset) {
   `$this$writeFully`.write(buffer, offset, offset + length);
}

@JvmSynthetic
fun `writeFully$default`(var0: Sink, var1: ByteArray, var2: Int, var3: Int, var4: Int, var5: Any) {
   if ((var4 and 2) != 0) {
      var2 = 0;
   }

   if ((var4 and 4) != 0) {
      var3 = var1.length - var2;
   }

   writeFully(var0, var1, var2, var3);
}

public fun Sink.writePacket(packet: Source) {
   `$this$writePacket`.transferFrom(packet);
}

/** @deprecated */
@Deprecated(message = "\n    We're migrating to the new kotlinx-io library.\n    This declaration is deprecated and will be removed in Ktor 4.0.0\n    If you have any problems with migration, please contact us in \n    https://youtrack.jetbrains.com/issue/KTOR-6030/Migrate-to-new-kotlinx.io-library\n    ", replaceWith = @ReplaceWith(expression = "Sink", imports = ["kotlinx.io.Sink"]))
@JvmSynthetic
fun `BytePacketBuilder$annotations`() {
}
