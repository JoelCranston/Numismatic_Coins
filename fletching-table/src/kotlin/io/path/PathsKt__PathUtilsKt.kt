package kotlin.io.path

import java.io.Closeable
import java.net.URI
import java.nio.file.CopyOption
import java.nio.file.DirectoryStream
import java.nio.file.FileAlreadyExistsException
import java.nio.file.FileStore
import java.nio.file.FileVisitOption
import java.nio.file.FileVisitor
import java.nio.file.Files
import java.nio.file.LinkOption
import java.nio.file.Path
import java.nio.file.Paths
import java.nio.file.StandardCopyOption
import java.nio.file.attribute.FileAttribute
import java.nio.file.attribute.FileTime
import java.nio.file.attribute.PosixFilePermission
import java.nio.file.attribute.UserPrincipal
import java.util.Arrays
import kotlin.contracts.InvocationKind
import kotlin.internal.InlineOnly
import kotlin.jvm.internal.InlineMarker
import kotlin.jvm.internal.SourceDebugExtension

@SourceDebugExtension(["SMAP\nPathUtils.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PathUtils.kt\nkotlin/io/path/PathsKt__PathUtilsKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,1195:1\n1#2:1196\n1869#3,2:1197\n*S KotlinDebug\n*F\n+ 1 PathUtils.kt\nkotlin/io/path/PathsKt__PathUtilsKt\n*L\n415#1:1197,2\n*E\n"])
internal class PathsKt__PathUtilsKt : PathsKt__PathRecursiveFunctionsKt {
   @SinceKotlin(
      version = "1.5"
   )
   public final val name: String
      public final get() {
         val var10000: Path = `$this$name`.getFileName();
         var var1: java.lang.String = if (var10000 != null) var10000.toString() else null;
         if (var1 == null) {
            var1 = "";
         }

         return var1;
      }


   @SinceKotlin(
      version = "1.5"
   )
   public final val nameWithoutExtension: String
      public final get() {
         val var10000: Path = `$this$nameWithoutExtension`.getFileName();
         if (var10000 != null) {
            val var1: java.lang.String = var10000.toString();
            if (var1 != null) {
               val var2: java.lang.String = StringsKt.substringBeforeLast$default(var1, ".", null, 2, null);
               if (var2 != null) {
                  return var2;
               }
            }
         }

         return "";
      }


   @SinceKotlin(
      version = "1.5"
   )
   public final val extension: String
      public final get() {
         val var10000: Path = `$this$extension`.getFileName();
         if (var10000 != null) {
            val var1: java.lang.String = var10000.toString();
            if (var1 != null) {
               val var2: java.lang.String = StringsKt.substringAfterLast(var1, '.', "");
               if (var2 != null) {
                  return var2;
               }
            }
         }

         return "";
      }


   @SinceKotlin(
      version = "1.5"
   )
   @InlineOnly
   public final val pathString: String
      public final inline get() {
         return `$this$pathString`.toString();
      }


   @SinceKotlin(
      version = "1.5"
   )
   public final val invariantSeparatorsPathString: String
      public final get() {
         val separator: java.lang.String = `$this$invariantSeparatorsPathString`.getFileSystem().getSeparator();
         var var2: java.lang.String;
         if (!(separator == "/")) {
            var2 = `$this$invariantSeparatorsPathString`.toString();
            var2 = StringsKt.replace$default(var2, separator, "/", false, 4, null);
         } else {
            var2 = `$this$invariantSeparatorsPathString`.toString();
         }

         return var2;
      }


   @Deprecated(
      message = "Use invariantSeparatorsPathString property instead.",
      replaceWith = @ReplaceWith(
         expression = "invariantSeparatorsPathString",
         imports = {}
      ),
      level = DeprecationLevel.ERROR
   )
   @SinceKotlin(
      version = "1.4"
   )
   @ExperimentalPathApi
   @InlineOnly
   public final val invariantSeparatorsPath: String
      public final inline get() {
         return PathsKt.getInvariantSeparatorsPathString(`$this$invariantSeparatorsPath`);
      }


   @SinceKotlin(version = "1.5")
   @InlineOnly
   @JvmStatic
   public inline fun Path.absolute(): Path {
      val var10000: Path = `$this$absolute`.toAbsolutePath();
      return var10000;
   }

   @SinceKotlin(version = "1.5")
   @InlineOnly
   @JvmStatic
   public inline fun Path.absolutePathString(): String {
      return `$this$absolutePathString`.toAbsolutePath().toString();
   }

   @SinceKotlin(version = "1.5")
   @JvmStatic
   public fun Path.relativeTo(base: Path): Path {
      try {
         return PathRelativizer.INSTANCE.tryRelativeTo(`$this$relativeTo`, base);
      } catch (var4: IllegalArgumentException) {
         throw new IllegalArgumentException("${var4.getMessage()}\nthis path: $`$this$relativeTo`\nbase path: $base", var4);
      }
   }

   @SinceKotlin(version = "1.5")
   @JvmStatic
   public fun Path.relativeToOrSelf(base: Path): Path {
      var var10000: Path = PathsKt.relativeToOrNull(`$this$relativeToOrSelf`, base);
      if (var10000 == null) {
         var10000 = `$this$relativeToOrSelf`;
      }

      return var10000;
   }

   @SinceKotlin(version = "1.5")
   @JvmStatic
   public fun Path.relativeToOrNull(base: Path): Path? {
      var var2: Path;
      try {
         var2 = PathRelativizer.INSTANCE.tryRelativeTo(`$this$relativeToOrNull`, base);
      } catch (var4: IllegalArgumentException) {
         var2 = null;
      }

      return var2;
   }

   @SinceKotlin(version = "1.5")
   @InlineOnly
   @Throws(java/io/IOException::class)
   @JvmStatic
   public inline fun Path.copyTo(target: Path, overwrite: Boolean = false): Path {
      val var10000: Array<CopyOption> = if (overwrite) new CopyOption[]{StandardCopyOption.REPLACE_EXISTING} else new CopyOption[0];
      val var5: Path = Files.copy(`$this$copyTo`, target, Arrays.copyOf(var10000, var10000.length));
      return var5;
   }

   @SinceKotlin(version = "1.5")
   @InlineOnly
   @Throws(java/io/IOException::class)
   @JvmStatic
   public inline fun Path.copyTo(target: Path, vararg options: CopyOption): Path {
      val var10000: Path = Files.copy(`$this$copyTo`, target, Arrays.copyOf(options, options.length));
      return var10000;
   }

   @SinceKotlin(version = "1.5")
   @InlineOnly
   @JvmStatic
   public inline fun Path.exists(vararg options: LinkOption): Boolean {
      return Files.exists(`$this$exists`, Arrays.copyOf(options, options.length));
   }

   @SinceKotlin(version = "1.5")
   @InlineOnly
   @JvmStatic
   public inline fun Path.notExists(vararg options: LinkOption): Boolean {
      return Files.notExists(`$this$notExists`, Arrays.copyOf(options, options.length));
   }

   @SinceKotlin(version = "1.5")
   @InlineOnly
   @JvmStatic
   public inline fun Path.isRegularFile(vararg options: LinkOption): Boolean {
      return Files.isRegularFile(`$this$isRegularFile`, Arrays.copyOf(options, options.length));
   }

   @SinceKotlin(version = "1.5")
   @InlineOnly
   @JvmStatic
   public inline fun Path.isDirectory(vararg options: LinkOption): Boolean {
      return Files.isDirectory(`$this$isDirectory`, Arrays.copyOf(options, options.length));
   }

   @SinceKotlin(version = "1.5")
   @InlineOnly
   @JvmStatic
   public inline fun Path.isSymbolicLink(): Boolean {
      return Files.isSymbolicLink(`$this$isSymbolicLink`);
   }

   @SinceKotlin(version = "1.5")
   @InlineOnly
   @JvmStatic
   public inline fun Path.isExecutable(): Boolean {
      return Files.isExecutable(`$this$isExecutable`);
   }

   @SinceKotlin(version = "1.5")
   @InlineOnly
   @Throws(java/io/IOException::class)
   @JvmStatic
   public inline fun Path.isHidden(): Boolean {
      return Files.isHidden(`$this$isHidden`);
   }

   @SinceKotlin(version = "1.5")
   @InlineOnly
   @JvmStatic
   public inline fun Path.isReadable(): Boolean {
      return Files.isReadable(`$this$isReadable`);
   }

   @SinceKotlin(version = "1.5")
   @InlineOnly
   @JvmStatic
   public inline fun Path.isWritable(): Boolean {
      return Files.isWritable(`$this$isWritable`);
   }

   @SinceKotlin(version = "1.5")
   @InlineOnly
   @Throws(java/io/IOException::class)
   @JvmStatic
   public inline fun Path.isSameFileAs(other: Path): Boolean {
      return Files.isSameFile(`$this$isSameFileAs`, other);
   }

   @SinceKotlin(version = "1.5")
   @Throws(java/io/IOException::class)
   @JvmStatic
   public fun Path.listDirectoryEntries(glob: String = "*"): List<Path> {
      label19: {
         val var2: Closeable = Files.newDirectoryStream(`$this$listDirectoryEntries`, glob);
         var var3: java.lang.Throwable = null;

         try {
            try {
               val it: DirectoryStream = var2 as DirectoryStream;
               val var10: java.util.List = CollectionsKt.toList(it);
            } catch (var6: java.lang.Throwable) {
               var3 = var6;
               throw var6;
            }
         } catch (var7: java.lang.Throwable) {
            CloseableKt.closeFinally(var2, var3);
         }

         CloseableKt.closeFinally(var2, null);
      }
   }

   @SinceKotlin(version = "1.5")
   @InlineOnly
   @Throws(java/io/IOException::class)
   @JvmStatic
   public inline fun <T> Path.useDirectoryEntries(glob: String = "*", block: (Sequence<Path>) -> T): T {
      label19: {
         val var3: Closeable = Files.newDirectoryStream(`$this$useDirectoryEntries`, glob);
         var var4: java.lang.Throwable = null;

         try {
            try {
               var it: DirectoryStream = var3 as DirectoryStream;
               it = (DirectoryStream)block.invoke(CollectionsKt.asSequence(it));
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
   public inline fun Path.forEachDirectoryEntry(glob: String = "*", action: (Path) -> Unit) {
      label28: {
         val var3: Closeable = Files.newDirectoryStream(`$this$forEachDirectoryEntry`, glob);
         var var4: java.lang.Throwable = null;

         try {
            try {
               val it: DirectoryStream = var3 as DirectoryStream;

               val `$this$forEach$iv`: java.lang.Iterable;
               for (Object element$iv : $this$forEach$iv) {
                  action.invoke(`element$iv`);
               }
            } catch (var11: java.lang.Throwable) {
               var4 = var11;
               throw var11;
            }
         } catch (var12: java.lang.Throwable) {
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
   public inline fun Path.fileSize(): Long {
      return Files.size(`$this$fileSize`);
   }

   @SinceKotlin(version = "1.5")
   @InlineOnly
   @Throws(java/io/IOException::class)
   @JvmStatic
   public inline fun Path.deleteExisting() {
      Files.delete(`$this$deleteExisting`);
   }

   @SinceKotlin(version = "1.5")
   @InlineOnly
   @Throws(java/io/IOException::class)
   @JvmStatic
   public inline fun Path.deleteIfExists(): Boolean {
      return Files.deleteIfExists(`$this$deleteIfExists`);
   }

   @SinceKotlin(version = "1.5")
   @InlineOnly
   @Throws(java/io/IOException::class)
   @JvmStatic
   public inline fun Path.createDirectory(vararg attributes: FileAttribute<*>): Path {
      val var10000: Path = Files.createDirectory(`$this$createDirectory`, Arrays.copyOf(attributes, attributes.length));
      return var10000;
   }

   @SinceKotlin(version = "1.5")
   @InlineOnly
   @Throws(java/io/IOException::class)
   @JvmStatic
   public inline fun Path.createDirectories(vararg attributes: FileAttribute<*>): Path {
      val var10000: Path = Files.createDirectories(`$this$createDirectories`, Arrays.copyOf(attributes, attributes.length));
      return var10000;
   }

   @SinceKotlin(version = "1.9")
   @Throws(java/io/IOException::class)
   @JvmStatic
   public fun Path.createParentDirectories(vararg attributes: FileAttribute<*>): Path {
      val parent: Path = `$this$createParentDirectories`.getParent();
      if (parent != null) {
         var var10001: Array<LinkOption> = new LinkOption[0];
         if (!Files.isDirectory(parent, Arrays.copyOf(var10001, var10001.length))) {
            try {
               val var9: Array<FileAttribute> = Arrays.copyOf(attributes, attributes.length);
            } catch (var7: FileAlreadyExistsException) {
               var10001 = new LinkOption[0];
               if (!Files.isDirectory(parent, Arrays.copyOf(var10001, var10001.length))) {
                  throw var7;
               }
            }
         }
      }

      return `$this$createParentDirectories`;
   }

   @SinceKotlin(version = "1.5")
   @InlineOnly
   @Throws(java/io/IOException::class)
   @JvmStatic
   public inline fun Path.moveTo(target: Path, vararg options: CopyOption): Path {
      val var10000: Path = Files.move(`$this$moveTo`, target, Arrays.copyOf(options, options.length));
      return var10000;
   }

   @SinceKotlin(version = "1.5")
   @InlineOnly
   @Throws(java/io/IOException::class)
   @JvmStatic
   public inline fun Path.moveTo(target: Path, overwrite: Boolean = false): Path {
      val var10000: Array<CopyOption> = if (overwrite) new CopyOption[]{StandardCopyOption.REPLACE_EXISTING} else new CopyOption[0];
      val var5: Path = Files.move(`$this$moveTo`, target, Arrays.copyOf(var10000, var10000.length));
      return var5;
   }

   @SinceKotlin(version = "1.5")
   @InlineOnly
   @Throws(java/io/IOException::class)
   @JvmStatic
   public inline fun Path.fileStore(): FileStore {
      val var10000: FileStore = Files.getFileStore(`$this$fileStore`);
      return var10000;
   }

   @SinceKotlin(version = "1.5")
   @InlineOnly
   @Throws(java/io/IOException::class)
   @JvmStatic
   public inline fun Path.getAttribute(attribute: String, vararg options: LinkOption): Any? {
      return Files.getAttribute(`$this$getAttribute`, attribute, Arrays.copyOf(options, options.length));
   }

   @SinceKotlin(version = "1.5")
   @InlineOnly
   @Throws(java/io/IOException::class)
   @JvmStatic
   public inline fun Path.setAttribute(attribute: String, value: Any?, vararg options: LinkOption): Path {
      val var10000: Path = Files.setAttribute(`$this$setAttribute`, attribute, value, Arrays.copyOf(options, options.length));
      return var10000;
   }

   @PublishedApi
   @JvmStatic
   internal fun fileAttributeViewNotAvailable(path: Path, attributeViewClass: Class<*>): Nothing {
      throw new UnsupportedOperationException("The desired attribute view type $attributeViewClass is not available for the file $path.");
   }

   @SinceKotlin(version = "1.5")
   @InlineOnly
   @Throws(java/io/IOException::class)
   @JvmStatic
   public inline fun Path.readAttributes(attributes: String, vararg options: LinkOption): Map<String, Any?> {
      val var10000: java.util.Map = Files.readAttributes(`$this$readAttributes`, attributes, Arrays.copyOf(options, options.length));
      return var10000;
   }

   @SinceKotlin(version = "1.5")
   @InlineOnly
   @Throws(java/io/IOException::class)
   @JvmStatic
   public inline fun Path.getLastModifiedTime(vararg options: LinkOption): FileTime {
      val var10000: FileTime = Files.getLastModifiedTime(`$this$getLastModifiedTime`, Arrays.copyOf(options, options.length));
      return var10000;
   }

   @SinceKotlin(version = "1.5")
   @InlineOnly
   @Throws(java/io/IOException::class)
   @JvmStatic
   public inline fun Path.setLastModifiedTime(value: FileTime): Path {
      val var10000: Path = Files.setLastModifiedTime(`$this$setLastModifiedTime`, value);
      return var10000;
   }

   @SinceKotlin(version = "1.5")
   @InlineOnly
   @Throws(java/io/IOException::class)
   @JvmStatic
   public inline fun Path.getOwner(vararg options: LinkOption): UserPrincipal? {
      return Files.getOwner(`$this$getOwner`, Arrays.copyOf(options, options.length));
   }

   @SinceKotlin(version = "1.5")
   @InlineOnly
   @Throws(java/io/IOException::class)
   @JvmStatic
   public inline fun Path.setOwner(value: UserPrincipal): Path {
      val var10000: Path = Files.setOwner(`$this$setOwner`, value);
      return var10000;
   }

   @SinceKotlin(version = "1.5")
   @InlineOnly
   @Throws(java/io/IOException::class)
   @JvmStatic
   public inline fun Path.getPosixFilePermissions(vararg options: LinkOption): Set<PosixFilePermission> {
      val var10000: java.util.Set = Files.getPosixFilePermissions(`$this$getPosixFilePermissions`, Arrays.copyOf(options, options.length));
      return var10000;
   }

   @SinceKotlin(version = "1.5")
   @InlineOnly
   @Throws(java/io/IOException::class)
   @JvmStatic
   public inline fun Path.setPosixFilePermissions(value: Set<PosixFilePermission>): Path {
      val var10000: Path = Files.setPosixFilePermissions(`$this$setPosixFilePermissions`, value);
      return var10000;
   }

   @SinceKotlin(version = "1.5")
   @InlineOnly
   @Throws(java/io/IOException::class)
   @JvmStatic
   public inline fun Path.createLinkPointingTo(target: Path): Path {
      val var10000: Path = Files.createLink(`$this$createLinkPointingTo`, target);
      return var10000;
   }

   @SinceKotlin(version = "1.5")
   @InlineOnly
   @Throws(java/io/IOException::class)
   @JvmStatic
   public inline fun Path.createSymbolicLinkPointingTo(target: Path, vararg attributes: FileAttribute<*>): Path {
      val var10000: Path = Files.createSymbolicLink(`$this$createSymbolicLinkPointingTo`, target, Arrays.copyOf(attributes, attributes.length));
      return var10000;
   }

   @SinceKotlin(version = "1.5")
   @InlineOnly
   @Throws(java/io/IOException::class)
   @JvmStatic
   public inline fun Path.readSymbolicLink(): Path {
      val var10000: Path = Files.readSymbolicLink(`$this$readSymbolicLink`);
      return var10000;
   }

   @SinceKotlin(version = "1.5")
   @InlineOnly
   @Throws(java/io/IOException::class)
   @JvmStatic
   public inline fun Path.createFile(vararg attributes: FileAttribute<*>): Path {
      val var10000: Path = Files.createFile(`$this$createFile`, Arrays.copyOf(attributes, attributes.length));
      return var10000;
   }

   @SinceKotlin(version = "1.5")
   @InlineOnly
   @Throws(java/io/IOException::class)
   @JvmStatic
   public inline fun createTempFile(prefix: String? = null, suffix: String? = null, vararg attributes: FileAttribute<*>): Path {
      val var10000: Path = Files.createTempFile(prefix, suffix, Arrays.copyOf(attributes, attributes.length));
      return var10000;
   }

   @SinceKotlin(version = "1.5")
   @Throws(java/io/IOException::class)
   @JvmStatic
   public fun createTempFile(directory: Path?, prefix: String? = null, suffix: String? = null, vararg attributes: FileAttribute<*>): Path {
      val var10000: Path;
      if (directory != null) {
         var10000 = Files.createTempFile(directory, prefix, suffix, Arrays.copyOf(attributes, attributes.length));
      } else {
         var10000 = Files.createTempFile(prefix, suffix, Arrays.copyOf(attributes, attributes.length));
      }

      return var10000;
   }

   @SinceKotlin(version = "1.5")
   @InlineOnly
   @Throws(java/io/IOException::class)
   @JvmStatic
   public inline fun createTempDirectory(prefix: String? = null, vararg attributes: FileAttribute<*>): Path {
      val var10000: Path = Files.createTempDirectory(prefix, Arrays.copyOf(attributes, attributes.length));
      return var10000;
   }

   @SinceKotlin(version = "1.5")
   @Throws(java/io/IOException::class)
   @JvmStatic
   public fun createTempDirectory(directory: Path?, prefix: String? = null, vararg attributes: FileAttribute<*>): Path {
      val var10000: Path;
      if (directory != null) {
         var10000 = Files.createTempDirectory(directory, prefix, Arrays.copyOf(attributes, attributes.length));
      } else {
         var10000 = Files.createTempDirectory(prefix, Arrays.copyOf(attributes, attributes.length));
      }

      return var10000;
   }

   @SinceKotlin(version = "1.5")
   @InlineOnly
   @JvmStatic
   public inline operator fun Path.div(other: Path): Path {
      val var10000: Path = `$this$div`.resolve(other);
      return var10000;
   }

   @SinceKotlin(version = "1.5")
   @InlineOnly
   @JvmStatic
   public inline operator fun Path.div(other: String): Path {
      val var10000: Path = `$this$div`.resolve(other);
      return var10000;
   }

   @SinceKotlin(version = "1.5")
   @InlineOnly
   @JvmStatic
   public inline fun Path(path: String): Path {
      val var10000: Path = Paths.get(path);
      return var10000;
   }

   @SinceKotlin(version = "1.5")
   @InlineOnly
   @JvmStatic
   public inline fun Path(base: String, vararg subpaths: String): Path {
      val var10000: Path = Paths.get(base, Arrays.copyOf(subpaths, subpaths.length));
      return var10000;
   }

   @SinceKotlin(version = "1.5")
   @InlineOnly
   @JvmStatic
   public inline fun URI.toPath(): Path {
      val var10000: Path = Paths.get(`$this$toPath`);
      return var10000;
   }

   @WasExperimental(markerClass = [ExperimentalPathApi::class])
   @SinceKotlin(version = "2.1")
   @JvmStatic
   public fun Path.walk(vararg options: PathWalkOption): Sequence<Path> {
      return new PathTreeWalk(`$this$walk`, options);
   }

   @WasExperimental(markerClass = [ExperimentalPathApi::class])
   @SinceKotlin(version = "2.1")
   @JvmStatic
   public fun Path.visitFileTree(visitor: FileVisitor<Path>, maxDepth: Int = Integer.MAX_VALUE, followLinks: Boolean = false) {
      Files.walkFileTree(`$this$visitFileTree`, if (followLinks) SetsKt.setOf(FileVisitOption.FOLLOW_LINKS) else SetsKt.emptySet(), maxDepth, visitor);
   }

   @WasExperimental(markerClass = [ExperimentalPathApi::class])
   @SinceKotlin(version = "2.1")
   @JvmStatic
   public fun Path.visitFileTree(maxDepth: Int = Integer.MAX_VALUE, followLinks: Boolean = false, builderAction: (FileVisitorBuilder) -> Unit) {
      contract {
         callsInPlace(builderAction, InvocationKind.EXACTLY_ONCE)
      }

      PathsKt.visitFileTree(`$this$visitFileTree`, PathsKt.fileVisitor(builderAction), maxDepth, followLinks);
   }

   @WasExperimental(markerClass = [ExperimentalPathApi::class])
   @SinceKotlin(version = "2.1")
   @JvmStatic
   public fun fileVisitor(builderAction: (FileVisitorBuilder) -> Unit): FileVisitor<Path> {
      contract {
         callsInPlace(builderAction, InvocationKind.EXACTLY_ONCE)
      }

      val var1: FileVisitorBuilderImpl = new FileVisitorBuilderImpl();
      builderAction.invoke(var1);
      return var1.build();
   }

   open fun PathsKt__PathUtilsKt() {
   }
}
