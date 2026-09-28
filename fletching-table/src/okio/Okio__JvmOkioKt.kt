package okio

import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.InputStream
import java.io.OutputStream
import java.nio.file.Files
import java.nio.file.OpenOption
import java.security.MessageDigest
import java.util.Arrays
import javax.crypto.Cipher
import javax.crypto.Mac
import okio.internal.DefaultSocket
import okio.internal.PipeSocket
import okio.internal.ResourceFileSystem
import okio.internal.SocketAsyncTimeout

@JvmSynthetic
internal class Okio__JvmOkioKt {
   @JvmStatic
   public fun OutputStream.sink(): Sink {
      return new OutputStreamSink(`$this$sink`, new Timeout());
   }

   @JvmStatic
   public fun InputStream.source(): Source {
      return new InputStreamSource(`$this$source`, new Timeout());
   }

   @Throws(java/io/IOException::class)
   @JvmStatic
   public fun java.net.Socket.sink(): Sink {
      val timeout: SocketAsyncTimeout = new SocketAsyncTimeout(`$this$sink`);
      val var10002: OutputStream = `$this$sink`.getOutputStream();
      return timeout.sink(new OutputStreamSink(var10002, timeout));
   }

   @Throws(java/io/IOException::class)
   @JvmStatic
   public fun java.net.Socket.source(): Source {
      val timeout: SocketAsyncTimeout = new SocketAsyncTimeout(`$this$source`);
      val var10002: InputStream = `$this$source`.getInputStream();
      return timeout.source(new InputStreamSource(var10002, timeout));
   }

   @JvmName(name = "socket")
   @JvmStatic
   public fun java.net.Socket.asOkioSocket(): Socket {
      return new DefaultSocket(`$this$asOkioSocket`);
   }

   @JvmStatic
   public fun inMemorySocketPair(maxBufferSize: Long): Array<Socket> {
      val ab: Pipe = new Pipe(maxBufferSize);
      val ba: Pipe = new Pipe(maxBufferSize);
      return new Socket[]{new PipeSocket(ab, ba), new PipeSocket(ba, ab)};
   }

   @JvmOverloads
   @Throws(java/io/FileNotFoundException::class)
   @JvmStatic
   public fun File.sink(append: Boolean = false): Sink {
      return Okio.sink(new FileOutputStream(`$this$sink`, append));
   }

   @Throws(java/io/FileNotFoundException::class)
   @JvmStatic
   public fun File.appendingSink(): Sink {
      return Okio.sink(new FileOutputStream(`$this$appendingSink`, true));
   }

   @Throws(java/io/FileNotFoundException::class)
   @JvmStatic
   public fun File.source(): Source {
      return new InputStreamSource(new FileInputStream(`$this$source`), Timeout.NONE);
   }

   @Throws(java/io/IOException::class)
   @JvmStatic
   public fun java.nio.file.Path.sink(vararg options: OpenOption): Sink {
      val var10000: OutputStream = Files.newOutputStream(`$this$sink`, Arrays.copyOf(options, options.length));
      return Okio.sink(var10000);
   }

   @Throws(java/io/IOException::class)
   @JvmStatic
   public fun java.nio.file.Path.source(vararg options: OpenOption): Source {
      val var10000: InputStream = Files.newInputStream(`$this$source`, Arrays.copyOf(options, options.length));
      return Okio.source(var10000);
   }

   @JvmStatic
   public fun Sink.cipherSink(cipher: Cipher): CipherSink {
      return new CipherSink(Okio.buffer(`$this$cipherSink`), cipher);
   }

   @JvmStatic
   public fun Source.cipherSource(cipher: Cipher): CipherSource {
      return new CipherSource(Okio.buffer(`$this$cipherSource`), cipher);
   }

   @JvmStatic
   public fun Sink.hashingSink(mac: Mac): HashingSink {
      return new HashingSink(`$this$hashingSink`, mac);
   }

   @JvmStatic
   public fun Source.hashingSource(mac: Mac): HashingSource {
      return new HashingSource(`$this$hashingSource`, mac);
   }

   @JvmStatic
   public fun Sink.hashingSink(digest: MessageDigest): HashingSink {
      return new HashingSink(`$this$hashingSink`, digest);
   }

   @JvmStatic
   public fun Source.hashingSource(digest: MessageDigest): HashingSource {
      return new HashingSource(`$this$hashingSource`, digest);
   }

   @JvmStatic
   public fun ClassLoader.asResourceFileSystem(): FileSystem {
      return new ResourceFileSystem(`$this$asResourceFileSystem`, true, null, 4, null);
   }

   @JvmOverloads
   @Throws(java/io/FileNotFoundException::class)
   @JvmStatic
   fun File.sink(): Sink {
      return Okio.sink$default(`$this$sink`, false, 1, null);
   }
}
