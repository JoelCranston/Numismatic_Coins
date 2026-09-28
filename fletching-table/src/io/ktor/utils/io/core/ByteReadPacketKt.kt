package io.ktor.utils.io.core

import io.ktor.utils.io.pool.ObjectPool
import kotlin.jdk7.AutoCloseableKt
import kotlinx.io.Buffer
import kotlinx.io.Sink
import kotlinx.io.Source
import kotlinx.io.SourcesKt

public final val ByteReadPacketEmpty: Source = (new Buffer()) as Source

public final val remaining: Long
   public final get() {
      return `$this$remaining`.getBuffer().getSize();
   }


public fun ByteReadPacket(array: ByteArray, offset: Int = 0, length: Int = array.length): Source {
   val var3: Buffer = new Buffer();
   var3.write(array, offset, offset + length);
   return var3;
}

@JvmSynthetic
fun `ByteReadPacket$default`(var0: ByteArray, var1: Int, var2: Int, var3: Int, var4: Any): Source {
   if ((var3 and 2) != 0) {
      var1 = 0;
   }

   if ((var3 and 4) != 0) {
      var2 = var0.length;
   }

   return ByteReadPacket(var0, var1, var2);
}

@Deprecated(message = "Use Buffer instead", replaceWith = @ReplaceWith(expression = "Buffer()", imports = ["kotlinx.io.Buffer"]))
public fun Sink(pool: ObjectPool<*>): Buffer {
   return new Buffer();
}

@Deprecated(message = "Use Buffer instead", replaceWith = @ReplaceWith(expression = "Buffer()", imports = ["kotlinx.io.Buffer"]))
public fun Sink(): Buffer {
   return new Buffer();
}

public fun Source.readAvailable(out: Buffer): Int {
   val result: Long = `$this$readAvailable`.getBuffer().getSize();
   out.transferFrom(`$this$readAvailable`);
   return (int)result;
}

@Deprecated(message = "Use peek() or buffer.copy() instead, depending on your use case.", replaceWith = @ReplaceWith(expression = "peek()", imports = ["kotlinx.io.Source"]))
public fun Source.copy(): Source {
   return `$this$copy`.peek();
}

public fun Source.readShortLittleEndian(): Short {
   return SourcesKt.readShortLe(`$this$readShortLittleEndian`.getBuffer());
}

public fun Source.discard(count: Long = java.lang.Long.MAX_VALUE): Long {
   `$this$discard`.request(count);
   val countToDiscard: Long = Math.min(count, getRemaining(`$this$discard`));
   `$this$discard`.getBuffer().skip(countToDiscard);
   return countToDiscard;
}

@JvmSynthetic
fun `discard$default`(var0: Source, var1: Long, var3: Int, var4: Any): Long {
   if ((var3 and 1) != 0) {
      var1 = java.lang.Long.MAX_VALUE;
   }

   return discard(var0, var1);
}

public fun Source.takeWhile(block: (Buffer) -> Boolean) {
   while (!$this$takeWhile.exhausted() && block.invoke($this$takeWhile.getBuffer())) {
   }
}

public fun Source.readFully(out: ByteArray, offset: Int = 0, length: Int = out.length - offset) {
   SourcesKt.readTo(`$this$readFully`, out, offset, offset + length);
}

@JvmSynthetic
fun `readFully$default`(var0: Source, var1: ByteArray, var2: Int, var3: Int, var4: Int, var5: Any) {
   if ((var4 and 2) != 0) {
      var2 = 0;
   }

   if ((var4 and 4) != 0) {
      var3 = var1.length - var2;
   }

   readFully(var0, var1, var2, var3);
}

public fun <T> Source.preview(function: (Source) -> Any): Any {
   label19: {
      val var2: AutoCloseable = `$this$preview`.getBuffer().peek();
      var var3: java.lang.Throwable = null;

      try {
         try {
            val var4: Any = function.invoke(var2);
         } catch (var6: java.lang.Throwable) {
            var3 = var6;
            throw var6;
         }
      } catch (var7: java.lang.Throwable) {
         AutoCloseableKt.closeFinally(var2, var3);
      }

      AutoCloseableKt.closeFinally(var2, null);
   }
}

public fun <T> Sink.preview(function: (Source) -> Any): Any {
   label19: {
      val var2: AutoCloseable = `$this$preview`.getBuffer().peek();
      var var3: java.lang.Throwable = null;

      try {
         try {
            val var4: Any = function.invoke(var2);
         } catch (var6: java.lang.Throwable) {
            var3 = var6;
            throw var6;
         }
      } catch (var7: java.lang.Throwable) {
         AutoCloseableKt.closeFinally(var2, var3);
      }

      AutoCloseableKt.closeFinally(var2, null);
   }
}

@Deprecated(message = "Use close instead", replaceWith = @ReplaceWith(expression = "this.close()", imports = []))
public fun Source.release() {
   `$this$release`.close();
}

/** @deprecated */
@Deprecated(message = "Use Source instead", replaceWith = @ReplaceWith(expression = "Source", imports = ["kotlinx.io.Source"]))
@JvmSynthetic
fun `ByteReadPacket$annotations`() {
}
