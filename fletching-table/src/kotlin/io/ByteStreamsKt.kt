@file:JvmName(name = "ByteStreamsKt")

package kotlin.io

import java.io.BufferedInputStream
import java.io.BufferedOutputStream
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.InputStream
import java.io.InputStreamReader
import java.io.OutputStream
import java.io.OutputStreamWriter
import java.io.Reader
import java.io.Writer
import java.nio.charset.Charset
import kotlin.internal.InlineOnly
import kotlin.io.ByteStreamsKt.iterator.1

public operator fun BufferedInputStream.iterator(): ByteIterator {
   return new 1(`$this$iterator`);
}

@InlineOnly
public inline fun String.byteInputStream(charset: Charset = Charsets.UTF_8): ByteArrayInputStream {
   val var10002: ByteArray = `$this$byteInputStream`.getBytes(charset);
   return new ByteArrayInputStream(var10002);
}

@JvmSynthetic
fun java.lang.String.`byteInputStream$default`(charset: Charset, var2: Int, var3: Any): ByteArrayInputStream {
   if ((var2 and 1) != 0) {
      charset = Charsets.UTF_8;
   }

   val var10002: ByteArray = `$this$byteInputStream_u24default`.getBytes(charset);
   return new ByteArrayInputStream(var10002);
}

@InlineOnly
public inline fun ByteArray.inputStream(): ByteArrayInputStream {
   return new ByteArrayInputStream(`$this$inputStream`);
}

@InlineOnly
public inline fun ByteArray.inputStream(offset: Int, length: Int): ByteArrayInputStream {
   return new ByteArrayInputStream(`$this$inputStream`, offset, length);
}

@InlineOnly
public inline fun InputStream.buffered(bufferSize: Int = 8192): BufferedInputStream {
   return if (`$this$buffered` is BufferedInputStream) `$this$buffered` as BufferedInputStream else new BufferedInputStream(`$this$buffered`, bufferSize);
}

@JvmSynthetic
fun InputStream.`buffered$default`(bufferSize: Int, var2: Int, var3: Any): BufferedInputStream {
   if ((var2 and 1) != 0) {
      bufferSize = 8192;
   }

   return if (`$this$buffered_u24default` is BufferedInputStream)
      `$this$buffered_u24default` as BufferedInputStream
      else
      new BufferedInputStream(`$this$buffered_u24default`, bufferSize);
}

@InlineOnly
public inline fun InputStream.reader(charset: Charset = Charsets.UTF_8): InputStreamReader {
   return new InputStreamReader(`$this$reader`, charset);
}

@JvmSynthetic
fun InputStream.`reader$default`(charset: Charset, var2: Int, var3: Any): InputStreamReader {
   if ((var2 and 1) != 0) {
      charset = Charsets.UTF_8;
   }

   return new InputStreamReader(`$this$reader_u24default`, charset);
}

@InlineOnly
public inline fun InputStream.bufferedReader(charset: Charset = Charsets.UTF_8): BufferedReader {
   val var2: Reader = new InputStreamReader(`$this$bufferedReader`, charset);
   return if (var2 is BufferedReader) var2 as BufferedReader else new BufferedReader(var2, 8192);
}

@JvmSynthetic
fun InputStream.`bufferedReader$default`(charset: Charset, var2: Int, var3: Any): BufferedReader {
   if ((var2 and 1) != 0) {
      charset = Charsets.UTF_8;
   }

   val var4: Reader = new InputStreamReader(`$this$bufferedReader_u24default`, charset);
   return if (var4 is BufferedReader) var4 as BufferedReader else new BufferedReader(var4, 8192);
}

@InlineOnly
public inline fun OutputStream.buffered(bufferSize: Int = 8192): BufferedOutputStream {
   return if (`$this$buffered` is BufferedOutputStream) `$this$buffered` as BufferedOutputStream else new BufferedOutputStream(`$this$buffered`, bufferSize);
}

@JvmSynthetic
fun OutputStream.`buffered$default`(bufferSize: Int, var2: Int, var3: Any): BufferedOutputStream {
   if ((var2 and 1) != 0) {
      bufferSize = 8192;
   }

   return if (`$this$buffered_u24default` is BufferedOutputStream)
      `$this$buffered_u24default` as BufferedOutputStream
      else
      new BufferedOutputStream(`$this$buffered_u24default`, bufferSize);
}

@InlineOnly
public inline fun OutputStream.writer(charset: Charset = Charsets.UTF_8): OutputStreamWriter {
   return new OutputStreamWriter(`$this$writer`, charset);
}

@JvmSynthetic
fun OutputStream.`writer$default`(charset: Charset, var2: Int, var3: Any): OutputStreamWriter {
   if ((var2 and 1) != 0) {
      charset = Charsets.UTF_8;
   }

   return new OutputStreamWriter(`$this$writer_u24default`, charset);
}

@InlineOnly
public inline fun OutputStream.bufferedWriter(charset: Charset = Charsets.UTF_8): BufferedWriter {
   val var2: Writer = new OutputStreamWriter(`$this$bufferedWriter`, charset);
   return if (var2 is BufferedWriter) var2 as BufferedWriter else new BufferedWriter(var2, 8192);
}

@JvmSynthetic
fun OutputStream.`bufferedWriter$default`(charset: Charset, var2: Int, var3: Any): BufferedWriter {
   if ((var2 and 1) != 0) {
      charset = Charsets.UTF_8;
   }

   val var4: Writer = new OutputStreamWriter(`$this$bufferedWriter_u24default`, charset);
   return if (var4 is BufferedWriter) var4 as BufferedWriter else new BufferedWriter(var4, 8192);
}

public fun InputStream.copyTo(out: OutputStream, bufferSize: Int = 8192): Long {
   var bytesCopied: Long = 0L;
   val buffer: ByteArray = new byte[bufferSize];

   for (int bytes = $this$copyTo.read(buffer); bytes >= 0; bytes = $this$copyTo.read(buffer)) {
      out.write(buffer, 0, bytes);
      bytesCopied += bytes;
   }

   return bytesCopied;
}

@JvmSynthetic
fun `copyTo$default`(var0: InputStream, var1: OutputStream, var2: Int, var3: Int, var4: Any): Long {
   if ((var3 and 2) != 0) {
      var2 = 8192;
   }

   return copyTo(var0, var1, var2);
}

@Deprecated(message = "Use readBytes() overload without estimatedSize parameter", replaceWith = @ReplaceWith(expression = "readBytes()", imports = []))
@DeprecatedSinceKotlin(warningSince = "1.3", errorSince = "1.5")
public fun InputStream.readBytes(estimatedSize: Int = 8192): ByteArray {
   val buffer: ByteArrayOutputStream = new ByteArrayOutputStream(Math.max(estimatedSize, `$this$readBytes`.available()));
   copyTo$default(`$this$readBytes`, buffer, 0, 2, null);
   val var10000: ByteArray = buffer.toByteArray();
   return var10000;
}

/** @deprecated */
@JvmSynthetic
fun `readBytes$default`(var0: InputStream, var1: Int, var2: Int, var3: Any): ByteArray {
   if ((var2 and 1) != 0) {
      var1 = 8192;
   }

   return readBytes(var0, var1);
}

@SinceKotlin(version = "1.3")
public fun InputStream.readBytes(): ByteArray {
   val buffer: ByteArrayOutputStream = new ByteArrayOutputStream(Math.max(8192, `$this$readBytes`.available()));
   copyTo$default(`$this$readBytes`, buffer, 0, 2, null);
   val var10000: ByteArray = buffer.toByteArray();
   return var10000;
}
