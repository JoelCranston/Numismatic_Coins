package okio

import java.io.FileNotFoundException
import java.io.IOException
import java.io.InputStream
import java.io.InterruptedIOException
import java.io.OutputStream
import java.nio.channels.FileChannel
import java.nio.file.CopyOption
import java.nio.file.Files
import java.nio.file.LinkOption
import java.nio.file.NoSuchFileException
import java.nio.file.OpenOption
import java.nio.file.StandardCopyOption
import java.nio.file.StandardOpenOption
import java.nio.file.attribute.FileAttribute
import java.util.ArrayList
import java.util.Arrays
import kotlin.io.path.PathsKt
import kotlin.jvm.internal.SourceDebugExtension

@SourceDebugExtension(["SMAP\nNioFileSystemWrappingFileSystem.kt\nKotlin\n*S Kotlin\n*F\n+ 1 NioFileSystemWrappingFileSystem.kt\nokio/NioFileSystemWrappingFileSystem\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 4 ArraysJVM.kt\nkotlin/collections/ArraysKt__ArraysJVMKt\n*L\n1#1,196:1\n1634#2,3:197\n1#3:200\n37#4,2:201\n37#4,2:203\n37#4,2:205\n*S KotlinDebug\n*F\n+ 1 NioFileSystemWrappingFileSystem.kt\nokio/NioFileSystemWrappingFileSystem\n*L\n77#1:197,3\n104#1:201,2\n125#1:203,2\n138#1:205,2\n*E\n"])
internal class NioFileSystemWrappingFileSystem(nioFileSystem: java.nio.file.FileSystem) : NioSystemFileSystem {
   private final val nioFileSystem: java.nio.file.FileSystem

   init {
      this.nioFileSystem = nioFileSystem;
   }

   private fun Path.resolve(): java.nio.file.Path {
      val var10000: java.nio.file.Path = this.nioFileSystem.getPath(`$this$resolve`.toString());
      return var10000;
   }

   public override fun canonicalize(path: Path): Path {
      try {
         val var10000: Path.Companion = Path.Companion;
         val var10001: java.nio.file.Path = this.resolve(path).toRealPath();
         return Path.Companion.get$default(var10000, var10001, false, 1, null);
      } catch (var3: NoSuchFileException) {
         throw new FileNotFoundException("no such file: $path");
      }
   }

   public override fun metadataOrNull(path: Path): FileMetadata? {
      return this.metadataOrNull(this.resolve(path));
   }

   public override fun list(dir: Path): List<Path> {
      val var10000: java.util.List = this.list(dir, true);
      return var10000;
   }

   public override fun listOrNull(dir: Path): List<Path>? {
      return this.list(dir, false);
   }

   private fun list(dir: Path, throwOnFailure: Boolean): List<Path>? {
      val nioDir: java.nio.file.Path = this.resolve(dir);

      var result: java.util.List;
      try {
         result = PathsKt.listDirectoryEntries$default(nioDir, null, 1, null);
      } catch (var14: Exception) {
         if (throwOnFailure) {
            val var10001: Array<LinkOption> = new LinkOption[0];
            if (!Files.exists(nioDir, Arrays.copyOf(var10001, var10001.length))) {
               throw new FileNotFoundException("no such file: $dir");
            }

            throw new IOException("failed to list $dir");
         }

         return null;
      }

      val `$this$mapTo$iv`: java.lang.Iterable = result;
      val `destination$iv`: java.util.Collection = new ArrayList();

      for (Object item$iv : $this$mapTo$iv) {
         `destination$iv`.add(Path.Companion.get$default(Path.Companion, `item$iv` as java.nio.file.Path, false, 1, null));
      }

      result = `destination$iv` as java.util.List;
      CollectionsKt.sort(`destination$iv` as java.util.List);
      return result;
   }

   public override fun openReadOnly(file: Path): FileHandle {
      var var6: FileChannel;
      try {
         var6 = FileChannel.open(this.resolve(file), StandardOpenOption.READ);
      } catch (var5: NoSuchFileException) {
         throw new FileNotFoundException("no such file: $file");
      }

      return new NioFileSystemFileHandle(false, var6);
   }

   public override fun openReadWrite(file: Path, mustCreate: Boolean, mustExist: Boolean): FileHandle {
      if (mustCreate && mustExist) {
         throw new IllegalArgumentException("Cannot require mustCreate and mustExist at the same time.".toString());
      } else {
         val channel: java.util.List = CollectionsKt.createListBuilder();
         channel.add(StandardOpenOption.READ);
         channel.add(StandardOpenOption.WRITE);
         if (mustCreate) {
            channel.add(StandardOpenOption.CREATE_NEW);
         } else if (!mustExist) {
            channel.add(StandardOpenOption.CREATE);
         }

         val openOptions: java.util.List = CollectionsKt.build(channel);

         var var13: FileChannel;
         try {
            val var10000: java.nio.file.Path = this.resolve(file);
            val `$this$openReadWrite_u24lambda_u241`: Array<StandardOpenOption> = openOptions.toArray(new StandardOpenOption[0]);
            var13 = FileChannel.open(var10000, Arrays.copyOf(`$this$openReadWrite_u24lambda_u241`, `$this$openReadWrite_u24lambda_u241`.length));
         } catch (var10: NoSuchFileException) {
            throw new FileNotFoundException("no such file: $file");
         }

         return new NioFileSystemFileHandle(true, var13);
      }
   }

   public override fun source(file: Path): Source {
      try {
         val var10000: java.nio.file.Path = this.resolve(file);
         val var10001: Array<OpenOption> = new OpenOption[0];
         val var4: InputStream = Files.newInputStream(var10000, Arrays.copyOf(var10001, var10001.length));
         return Okio.source(var4);
      } catch (var3: NoSuchFileException) {
         throw new FileNotFoundException("no such file: $file");
      }
   }

   public override fun sink(file: Path, mustCreate: Boolean): Sink {
      val var4: java.util.List = CollectionsKt.createListBuilder();
      if (mustCreate) {
         var4.add(StandardOpenOption.CREATE_NEW);
      }

      val openOptions: java.util.List = CollectionsKt.build(var4);

      try {
         val var10: java.nio.file.Path = this.resolve(file);
         val e: Array<StandardOpenOption> = openOptions.toArray(new StandardOpenOption[0]);
         val var11: Array<OpenOption> = Arrays.copyOf(e, e.length);
         val var10000: OutputStream = Files.newOutputStream(var10, Arrays.copyOf(var11, var11.length));
         return Okio.sink(var10000);
      } catch (var9: NoSuchFileException) {
         throw new FileNotFoundException("no such file: $file");
      }
   }

   public override fun appendingSink(file: Path, mustExist: Boolean): Sink {
      val var4: java.util.List = CollectionsKt.createListBuilder();
      var4.add(StandardOpenOption.APPEND);
      if (!mustExist) {
         var4.add(StandardOpenOption.CREATE);
      }

      val openOptions: java.util.List = CollectionsKt.build(var4);
      val var9: java.nio.file.Path = this.resolve(file);
      val `$this$appendingSink_u24lambda_u240`: Array<StandardOpenOption> = openOptions.toArray(new StandardOpenOption[0]);
      val var10: Array<OpenOption> = Arrays.copyOf(`$this$appendingSink_u24lambda_u240`, `$this$appendingSink_u24lambda_u240`.length);
      val var10000: OutputStream = Files.newOutputStream(var9, Arrays.copyOf(var10, var10.length));
      return Okio.sink(var10000);
   }

   public override fun createDirectory(dir: Path, mustCreate: Boolean) {
      val var10000: FileMetadata = this.metadataOrNull(dir);
      val alreadyExist: Boolean = var10000 != null && var10000.isDirectory();
      if (alreadyExist && mustCreate) {
         throw new IOException("$dir already exists.");
      } else {
         try {
            val var6: java.nio.file.Path = this.resolve(dir);
            val var10001: Array<FileAttribute> = new FileAttribute[0];
         } catch (var5: IOException) {
            if (!alreadyExist) {
               throw new IOException("failed to create directory: $dir", var5);
            }
         }
      }
   }

   public override fun atomicMove(source: Path, target: Path) {
      try {
         val var3: java.nio.file.Path = this.resolve(source);
         val e: java.nio.file.Path = this.resolve(target);
         val var5: Array<CopyOption> = new CopyOption[]{StandardCopyOption.ATOMIC_MOVE, StandardCopyOption.REPLACE_EXISTING};
      } catch (var6: NoSuchFileException) {
         throw new FileNotFoundException(var6.getMessage());
      } catch (var7: UnsupportedOperationException) {
         throw new IOException("atomic move not supported");
      }
   }

   public override fun delete(path: Path, mustExist: Boolean) {
      if (Thread.interrupted()) {
         throw new InterruptedIOException("interrupted");
      } else {
         val nioPath: java.nio.file.Path = this.resolve(path);

         try {
            Files.delete(nioPath);
         } catch (var5: NoSuchFileException) {
            if (mustExist) {
               throw new FileNotFoundException("no such file: $path");
            }
         } catch (var6: IOException) {
            val var10001: Array<LinkOption> = new LinkOption[0];
            if (Files.exists(nioPath, Arrays.copyOf(var10001, var10001.length))) {
               throw new IOException("failed to delete $path");
            }
         }
      }
   }

   public override fun createSymlink(source: Path, target: Path) {
      val var10000: java.nio.file.Path = this.resolve(source);
      val var10001: java.nio.file.Path = this.resolve(target);
      val var10002: Array<FileAttribute> = new FileAttribute[0];
   }

   public override fun close() {
      this.nioFileSystem.close();
   }

   public override fun toString(): String {
      val var10000: java.lang.String = (this.nioFileSystem.getClass()::class).getSimpleName();
      return var10000;
   }
}
