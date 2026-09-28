package kotlin.io.path

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.Closeable
import java.io.InputStream
import java.io.InputStreamReader
import java.io.OutputStream
import java.io.OutputStreamWriter
import java.nio.Buffer
import java.nio.ByteBuffer
import java.nio.CharBuffer
import java.nio.charset.Charset
import java.nio.charset.CharsetEncoder
import java.nio.file.Files
import java.nio.file.OpenOption
import java.nio.file.Path
import java.nio.file.StandardOpenOption
import java.util.Arrays
import kotlin.contracts.InvocationKind
import kotlin.internal.InlineOnly
import kotlin.jvm.internal.InlineMarker
import kotlin.jvm.internal.SourceDebugExtension

@SourceDebugExtension(["SMAP\nPathReadWrite.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PathReadWrite.kt\nkotlin/io/path/PathsKt__PathReadWriteKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 ReadWrite.kt\nkotlin/io/TextStreamsKt\n+ 4 _Sequences.kt\nkotlin/sequences/SequencesKt___SequencesKt\n*L\n1#1,327:1\n1#2:328\n1#2:330\n57#3:329\n1321#4,2:331\n*S KotlinDebug\n*F\n+ 1 PathReadWrite.kt\nkotlin/io/path/PathsKt__PathReadWriteKt\n*L\n208#1:330\n208#1:329\n208#1:331,2\n*E\n"])
internal class PathsKt__PathReadWriteKt {
   @SinceKotlin(version = "1.5")
   @InlineOnly
   @Throws(java/io/IOException::class)
   @JvmStatic
   public inline fun Path.reader(charset: Charset = Charsets.UTF_8, vararg options: OpenOption): InputStreamReader {
      return new InputStreamReader(Files.newInputStream(`$this$reader`, Arrays.copyOf(options, options.length)), charset);
   }

   @SinceKotlin(version = "1.5")
   @InlineOnly
   @Throws(java/io/IOException::class)
   @JvmStatic
   public inline fun Path.bufferedReader(charset: Charset = Charsets.UTF_8, bufferSize: Int = 8192, vararg options: OpenOption): BufferedReader {
      return new BufferedReader(
         new InputStreamReader(Files.newInputStream(`$this$bufferedReader`, Arrays.copyOf(options, options.length)), charset), bufferSize
      );
   }

   @SinceKotlin(version = "1.5")
   @InlineOnly
   @Throws(java/io/IOException::class)
   @JvmStatic
   public inline fun Path.writer(charset: Charset = Charsets.UTF_8, vararg options: OpenOption): OutputStreamWriter {
      return new OutputStreamWriter(Files.newOutputStream(`$this$writer`, Arrays.copyOf(options, options.length)), charset);
   }

   @SinceKotlin(version = "1.5")
   @InlineOnly
   @Throws(java/io/IOException::class)
   @JvmStatic
   public inline fun Path.bufferedWriter(charset: Charset = Charsets.UTF_8, bufferSize: Int = 8192, vararg options: OpenOption): BufferedWriter {
      return new BufferedWriter(
         new OutputStreamWriter(Files.newOutputStream(`$this$bufferedWriter`, Arrays.copyOf(options, options.length)), charset), bufferSize
      );
   }

   @SinceKotlin(version = "1.5")
   @InlineOnly
   @Throws(java/io/IOException::class)
   @JvmStatic
   public inline fun Path.readBytes(): ByteArray {
      val var10000: ByteArray = Files.readAllBytes(`$this$readBytes`);
      return var10000;
   }

   @SinceKotlin(version = "1.5")
   @InlineOnly
   @Throws(java/io/IOException::class)
   @JvmStatic
   public inline fun Path.writeBytes(array: ByteArray, vararg options: OpenOption) {
      Files.write(`$this$writeBytes`, array, Arrays.copyOf(options, options.length));
   }

   @SinceKotlin(version = "1.5")
   @InlineOnly
   @Throws(java/io/IOException::class)
   @JvmStatic
   public inline fun Path.appendBytes(array: ByteArray) {
      Files.write(`$this$appendBytes`, array, StandardOpenOption.APPEND);
   }

   @SinceKotlin(version = "1.5")
   @Throws(java/io/IOException::class)
   @JvmStatic
   public fun Path.readText(charset: Charset = Charsets.UTF_8): String {
      label19: {
         val var3: Array<OpenOption> = new OpenOption[0];
         val var2: Closeable = new InputStreamReader(Files.newInputStream(`$this$readText`, Arrays.copyOf(var3, var3.length)), charset);
         var var10: java.lang.Throwable = null;

         try {
            try {
               val var11: java.lang.String = TextStreamsKt.readText(var2 as InputStreamReader);
            } catch (var6: java.lang.Throwable) {
               var10 = var6;
               throw var6;
            }
         } catch (var7: java.lang.Throwable) {
            CloseableKt.closeFinally(var2, var10);
         }

         CloseableKt.closeFinally(var2, null);
      }
   }

   @SinceKotlin(version = "1.5")
   @Throws(java/io/IOException::class)
   @JvmStatic
   public fun Path.writeText(text: CharSequence, charset: Charset = Charsets.UTF_8, vararg options: OpenOption) {
      label49: {
         val var4: Closeable = Files.newOutputStream(`$this$writeText`, Arrays.copyOf(options, options.length));
         var var5: java.lang.Throwable = null;

         try {
            try {
               val out: OutputStream = var4 as OutputStream;
               if (text is java.lang.String) {
                  FilesKt.writeTextImpl(out, text as java.lang.String, charset);
               } else {
                  val encoder: CharsetEncoder = FilesKt.newReplaceEncoder(charset);
                  val charBuffer: CharBuffer = if (text is CharBuffer) (text as CharBuffer).asReadOnlyBuffer() else CharBuffer.wrap(text);
                  val var10000: Int = Math.min(text.length(), 8192);
                  val byteBuffer: ByteBuffer = FilesKt.byteBufferForEncoding(var10000, encoder);

                  while (charBuffer.hasRemaining()) {
                     if (encoder.encode(charBuffer, byteBuffer, true).isError()) {
                        throw new IllegalStateException("Check failed.");
                     }

                     out.write(byteBuffer.array(), 0, byteBuffer.position());
                     ((Buffer)byteBuffer).clear();
                  }
               }
            } catch (var14: java.lang.Throwable) {
               var5 = var14;
               throw var14;
            }
         } catch (var15: java.lang.Throwable) {
            CloseableKt.closeFinally(var4, var5);
         }

         CloseableKt.closeFinally(var4, null);
      }
   }

   @SinceKotlin(version = "1.5")
   @Throws(java/io/IOException::class)
   @JvmStatic
   public fun Path.appendText(text: CharSequence, charset: Charset = Charsets.UTF_8) {
      PathsKt.writeText(`$this$appendText`, text, charset, new OpenOption[]{StandardOpenOption.APPEND});
   }

   @SinceKotlin(version = "1.5")
   @InlineOnly
   @Throws(java/io/IOException::class)
   @JvmStatic
   public inline fun Path.forEachLine(charset: Charset = Charsets.UTF_8, action: (String) -> Unit) {
      label28: {
         val var10000: BufferedReader = Files.newBufferedReader(`$this$forEachLine`, charset);
         val var5: Closeable = var10000 as BufferedReader;
         var var6: java.lang.Throwable = null;

         try {
            try {
               val it: Sequence;
               for (Object element$iv : it) {
                  action.invoke(`element$iv`);
               }
            } catch (var15: java.lang.Throwable) {
               var6 = var15;
               throw var15;
            }
         } catch (var16: java.lang.Throwable) {
            InlineMarker.finallyStart(1);
            CloseableKt.closeFinally(var5, var6);
            InlineMarker.finallyEnd(1);
         }

         InlineMarker.finallyStart(1);
         CloseableKt.closeFinally(var5, null);
         InlineMarker.finallyEnd(1);
      }
   }

   @SinceKotlin(version = "1.5")
   @InlineOnly
   @Throws(java/io/IOException::class)
   @JvmStatic
   public inline fun Path.inputStream(vararg options: OpenOption): InputStream {
      val var10000: InputStream = Files.newInputStream(`$this$inputStream`, Arrays.copyOf(options, options.length));
      return var10000;
   }

   @SinceKotlin(version = "1.5")
   @InlineOnly
   @Throws(java/io/IOException::class)
   @JvmStatic
   public inline fun Path.outputStream(vararg options: OpenOption): OutputStream {
      val var10000: OutputStream = Files.newOutputStream(`$this$outputStream`, Arrays.copyOf(options, options.length));
      return var10000;
   }

   @SinceKotlin(version = "1.5")
   @InlineOnly
   @Throws(java/io/IOException::class)
   @JvmStatic
   public inline fun Path.readLines(charset: Charset = Charsets.UTF_8): List<String> {
      val var10000: java.util.List = Files.readAllLines(`$this$readLines`, charset);
      return var10000;
   }

   @SinceKotlin(version = "1.5")
   @InlineOnly
   @Throws(java/io/IOException::class)
   @JvmStatic
   public inline fun <T> Path.useLines(charset: Charset = Charsets.UTF_8, block: (Sequence<String>) -> T): T {
      contract {
         callsInPlace(block, InvocationKind.EXACTLY_ONCE)
      }

      label19: {
         val var3: Closeable = Files.newBufferedReader(`$this$useLines`, charset);
         var var4: java.lang.Throwable = null;

         try {
            try {
               var it: BufferedReader = var3 as BufferedReader;
               it = (BufferedReader)block.invoke(TextStreamsKt.lineSequence(it));
            } catch (var7: java.lang.Throwable) {
               var4 = var7;
               throw var7;
            }
         } catch (var8: java.lang.Throwable) {
            InlineMarker.finallyStart(1);
            CloseableKt.closeFinally(var3, var4);
            InlineMarker.finallyEnd(1);
         }

         InlineMarker.finallyStart(1);
         CloseableKt.closeFinally(var3, null);
         InlineMarker.finallyEnd(1);
      }
   }

   @SinceKotlin(version = "1.5")
   @InlineOnly
   @Throws(java/io/IOException::class)
   @JvmStatic
   public inline fun Path.writeLines(lines: Iterable<CharSequence>, charset: Charset = Charsets.UTF_8, vararg options: OpenOption): Path {
      val var10000: Path = Files.write(`$this$writeLines`, lines, charset, Arrays.copyOf(options, options.length));
      return var10000;
   }

   @SinceKotlin(version = "1.5")
   @InlineOnly
   @Throws(java/io/IOException::class)
   @JvmStatic
   public inline fun Path.writeLines(lines: Sequence<CharSequence>, charset: Charset = Charsets.UTF_8, vararg options: OpenOption): Path {
      val var10000: Path = Files.write(`$this$writeLines`, SequencesKt.asIterable(lines), charset, Arrays.copyOf(options, options.length));
      return var10000;
   }

   @SinceKotlin(version = "1.5")
   @InlineOnly
   @Throws(java/io/IOException::class)
   @JvmStatic
   public inline fun Path.appendLines(lines: Iterable<CharSequence>, charset: Charset = Charsets.UTF_8): Path {
      val var10000: Path = Files.write(`$this$appendLines`, lines, charset, StandardOpenOption.APPEND);
      return var10000;
   }

   @SinceKotlin(version = "1.5")
   @InlineOnly
   @Throws(java/io/IOException::class)
   @JvmStatic
   public inline fun Path.appendLines(lines: Sequence<CharSequence>, charset: Charset = Charsets.UTF_8): Path {
      val var10000: Path = Files.write(`$this$appendLines`, SequencesKt.asIterable(lines), charset, StandardOpenOption.APPEND);
      return var10000;
   }

   open fun PathsKt__PathReadWriteKt() {
   }
}
