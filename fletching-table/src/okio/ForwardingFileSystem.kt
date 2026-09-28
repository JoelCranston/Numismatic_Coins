package okio

import java.util.ArrayList
import kotlin.jvm.internal.SourceDebugExtension

@SourceDebugExtension(["SMAP\nForwardingFileSystem.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ForwardingFileSystem.kt\nokio/ForwardingFileSystem\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,252:1\n1634#2,3:253\n1634#2,3:256\n*S KotlinDebug\n*F\n+ 1 ForwardingFileSystem.kt\nokio/ForwardingFileSystem\n*L\n170#1:253,3\n178#1:256,3\n*E\n"])
public abstract class ForwardingFileSystem : FileSystem {
   public final val delegate: FileSystem

   open fun ForwardingFileSystem(delegate: FileSystem) {
      this.delegate = delegate;
   }

   public open fun onPathParameter(path: Path, functionName: String, parameterName: String): Path {
      return path;
   }

   public open fun onPathResult(path: Path, functionName: String): Path {
      return path;
   }

   @Throws(java/io/IOException::class)
   public override fun canonicalize(path: Path): Path {
      return this.onPathResult(this.delegate.canonicalize(this.onPathParameter(path, "canonicalize", "path")), "canonicalize");
   }

   @Throws(java/io/IOException::class)
   public override fun metadataOrNull(path: Path): FileMetadata? {
      val var10000: FileMetadata = this.delegate.metadataOrNull(this.onPathParameter(path, "metadataOrNull", "path"));
      label11:
      if (var10000 == null) {
         return null;
      } else {
         return if (var10000.getSymlinkTarget() == null)
            var10000
            else
            FileMetadata.copy$default(
               var10000, false, false, this.onPathResult(var10000.getSymlinkTarget(), "metadataOrNull"), null, null, null, null, null, 251, null
            );
      }
   }

   @Throws(java/io/IOException::class)
   public override fun list(dir: Path): List<Path> {
      val `$this$mapTo$iv`: java.lang.Iterable = this.delegate.list(this.onPathParameter(dir, "list", "dir"));
      val `destination$iv`: java.util.Collection = new ArrayList();

      for (Object item$iv : $this$mapTo$iv) {
         `destination$iv`.add(this.onPathResult(`item$iv` as Path, "list"));
      }

      val paths: java.util.List = `destination$iv` as java.util.List;
      CollectionsKt.sort(`destination$iv` as java.util.List);
      return paths;
   }

   public override fun listOrNull(dir: Path): List<Path>? {
      val var10000: java.util.List = this.delegate.listOrNull(this.onPathParameter(dir, "listOrNull", "dir"));
      if (var10000 == null) {
         return null;
      } else {
         val `$this$mapTo$iv`: java.lang.Iterable = var10000;
         val `destination$iv`: java.util.Collection = new ArrayList();

         for (Object item$iv : $this$mapTo$iv) {
            `destination$iv`.add(this.onPathResult(`item$iv` as Path, "listOrNull"));
         }

         val paths: java.util.List = `destination$iv` as java.util.List;
         CollectionsKt.sort(`destination$iv` as java.util.List);
         return paths;
      }
   }

   public override fun listRecursively(dir: Path, followSymlinks: Boolean): Sequence<Path> {
      return SequencesKt.map(
         this.delegate.listRecursively(this.onPathParameter(dir, "listRecursively", "dir"), followSymlinks), ForwardingFileSystem::listRecursively$lambda$0
      );
   }

   @Throws(java/io/IOException::class)
   public override fun openReadOnly(file: Path): FileHandle {
      return this.delegate.openReadOnly(this.onPathParameter(file, "openReadOnly", "file"));
   }

   @Throws(java/io/IOException::class)
   public override fun openReadWrite(file: Path, mustCreate: Boolean, mustExist: Boolean): FileHandle {
      return this.delegate.openReadWrite(this.onPathParameter(file, "openReadWrite", "file"), mustCreate, mustExist);
   }

   @Throws(java/io/IOException::class)
   public override fun source(file: Path): Source {
      return this.delegate.source(this.onPathParameter(file, "source", "file"));
   }

   @Throws(java/io/IOException::class)
   public override fun sink(file: Path, mustCreate: Boolean): Sink {
      return this.delegate.sink(this.onPathParameter(file, "sink", "file"), mustCreate);
   }

   @Throws(java/io/IOException::class)
   public override fun appendingSink(file: Path, mustExist: Boolean): Sink {
      return this.delegate.appendingSink(this.onPathParameter(file, "appendingSink", "file"), mustExist);
   }

   @Throws(java/io/IOException::class)
   public override fun createDirectory(dir: Path, mustCreate: Boolean) {
      this.delegate.createDirectory(this.onPathParameter(dir, "createDirectory", "dir"), mustCreate);
   }

   @Throws(java/io/IOException::class)
   public override fun atomicMove(source: Path, target: Path) {
      this.delegate.atomicMove(this.onPathParameter(source, "atomicMove", "source"), this.onPathParameter(target, "atomicMove", "target"));
   }

   @Throws(java/io/IOException::class)
   public override fun delete(path: Path, mustExist: Boolean) {
      this.delegate.delete(this.onPathParameter(path, "delete", "path"), mustExist);
   }

   @Throws(java/io/IOException::class)
   public override fun createSymlink(source: Path, target: Path) {
      this.delegate.createSymlink(this.onPathParameter(source, "createSymlink", "source"), this.onPathParameter(target, "createSymlink", "target"));
   }

   @Throws(java/io/IOException::class)
   public override fun close() {
      this.delegate.close();
   }

   public override fun toString(): String {
      return "${(this.getClass()::class).getSimpleName()}(${this.delegate})";
   }

   @JvmStatic
   fun `listRecursively$lambda$0`(`this$0`: ForwardingFileSystem, it: Path): Path {
      return `this$0`.onPathResult(it, "listRecursively");
   }
}
