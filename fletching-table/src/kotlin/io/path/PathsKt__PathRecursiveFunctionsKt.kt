package kotlin.io.path

import java.io.Closeable
import java.io.IOException
import java.nio.file.CopyOption
import java.nio.file.DirectoryStream
import java.nio.file.FileSystemException
import java.nio.file.FileSystemLoopException
import java.nio.file.FileVisitResult
import java.nio.file.Files
import java.nio.file.LinkOption
import java.nio.file.NoSuchFileException
import java.nio.file.Path
import java.nio.file.SecureDirectoryStream
import java.nio.file.StandardCopyOption
import java.nio.file.attribute.BasicFileAttributeView
import java.nio.file.attribute.BasicFileAttributes
import java.util.ArrayList
import java.util.Arrays
import kotlin.io.path.PathsKt__PathRecursiveFunctionsKt.copyToRecursively.1
import kotlin.io.path.PathsKt__PathRecursiveFunctionsKt.copyToRecursively.3
import kotlin.io.path.PathsKt__PathRecursiveFunctionsKt.copyToRecursively.5.2
import kotlin.jvm.functions.Function3
import kotlin.jvm.internal.SourceDebugExtension
import kotlin.jvm.internal.SpreadBuilder

@SourceDebugExtension(["SMAP\nPathRecursiveFunctions.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PathRecursiveFunctions.kt\nkotlin/io/path/PathsKt__PathRecursiveFunctionsKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,532:1\n378#1,2:536\n386#1:538\n386#1:539\n380#1,4:540\n378#1,2:544\n386#1:546\n380#1,4:547\n386#1:551\n378#1,6:552\n378#1,2:558\n386#1:560\n380#1,4:561\n1#2:533\n1869#3,2:534\n*S KotlinDebug\n*F\n+ 1 PathRecursiveFunctions.kt\nkotlin/io/path/PathsKt__PathRecursiveFunctionsKt\n*L\n394#1:536,2\n409#1:538\n412#1:539\n394#1:540,4\n420#1:544,2\n421#1:546\n420#1:547,4\n432#1:551\n440#1:552,6\n463#1:558,2\n464#1:560\n463#1:561,4\n314#1:534,2\n*E\n"])
internal class PathsKt__PathRecursiveFunctionsKt : PathsKt__PathReadWriteKt {
   @ExperimentalPathApi
   @SinceKotlin(version = "1.8")
   @JvmStatic
   public fun Path.copyToRecursively(
      target: Path,
      onError: (Path, Path, Exception) -> OnErrorResult = 1.INSTANCE as Function3,
      followLinks: Boolean,
      overwrite: Boolean
   ): Path {
      return if (overwrite)
         PathsKt.copyToRecursively(
            `$this$copyToRecursively`,
            target,
            onError,
            followLinks,
            PathsKt__PathRecursiveFunctionsKt::copyToRecursively$lambda$0$PathsKt__PathRecursiveFunctionsKt
         )
         else
         PathsKt.copyToRecursively$default(`$this$copyToRecursively`, target, onError, followLinks, null, 8, null);
   }

   @ExperimentalPathApi
   @SinceKotlin(version = "1.8")
   @JvmStatic
   public fun Path.copyToRecursively(
      target: Path,
      onError: (Path, Path, Exception) -> OnErrorResult = 3.INSTANCE as Function3,
      followLinks: Boolean,
      copyAction: (CopyActionContext, Path, Path) -> CopyActionResult = PathsKt__PathRecursiveFunctionsKt::copyToRecursively$lambda$1$PathsKt__PathRecursiveFunctionsKt
   ): Path {
      var stack: Array<LinkOption> = LinkFollowing.INSTANCE.toLinkOptions(followLinks);
      stack = Arrays.copyOf(stack, stack.length);
      if (!Files.exists(`$this$copyToRecursively`, Arrays.copyOf(stack, stack.length))) {
         throw new NoSuchFileException(`$this$copyToRecursively`.toString(), target.toString(), "The source file doesn't exist.");
      } else {
         var var10001: Array<LinkOption> = new LinkOption[0];
         if (Files.exists(`$this$copyToRecursively`, Arrays.copyOf(var10001, var10001.length))
            && (followLinks || !Files.isSymbolicLink(`$this$copyToRecursively`))) {
            var10001 = new LinkOption[0];
            val normalizedTarget: Boolean = Files.exists(target, Arrays.copyOf(var10001, var10001.length)) && !Files.isSymbolicLink(target);
            if (!normalizedTarget || !Files.isSameFile(`$this$copyToRecursively`, target)) {
               val var13: Boolean;
               if (!(`$this$copyToRecursively`.getFileSystem() == target.getFileSystem())) {
                  var13 = false;
               } else if (normalizedTarget) {
                  var13 = target.toRealPath().startsWith(`$this$copyToRecursively`.toRealPath());
               } else {
                  val var10000: Path = target.getParent();
                  if (var10000 == null) {
                     var13 = false;
                  } else {
                     var10001 = new LinkOption[0];
                     var13 = Files.exists(var10000, Arrays.copyOf(var10001, var10001.length))
                        && var10000.toRealPath().startsWith(`$this$copyToRecursively`.toRealPath());
                  }
               }

               if (var13) {
                  throw new FileSystemException(
                     `$this$copyToRecursively`.toString(), target.toString(), "Recursively copying a directory into its subdirectory is prohibited."
                  );
               }
            }
         }

         PathsKt.visitFileTree$default(
            `$this$copyToRecursively`, 0, followLinks, PathsKt__PathRecursiveFunctionsKt::copyToRecursively$lambda$3$PathsKt__PathRecursiveFunctionsKt, 1, null
         );
         return target;
      }
   }

   @ExperimentalPathApi
   @JvmStatic
   private fun CopyActionResult.toFileVisitResult(): FileVisitResult {
      var var10000: FileVisitResult;
      switch (PathsKt__PathRecursiveFunctionsKt.WhenMappings.$EnumSwitchMapping$0[$this$toFileVisitResult.ordinal()]) {
         case 1:
            var10000 = FileVisitResult.CONTINUE;
            break;
         case 2:
            var10000 = FileVisitResult.TERMINATE;
            break;
         case 3:
            var10000 = FileVisitResult.SKIP_SUBTREE;
            break;
         default:
            throw new NoWhenBranchMatchedException();
      }

      return var10000;
   }

   @ExperimentalPathApi
   @JvmStatic
   private fun OnErrorResult.toFileVisitResult(): FileVisitResult {
      var var10000: FileVisitResult;
      switch (PathsKt__PathRecursiveFunctionsKt.WhenMappings.$EnumSwitchMapping$1[$this$toFileVisitResult.ordinal()]) {
         case 1:
            var10000 = FileVisitResult.TERMINATE;
            break;
         case 2:
            var10000 = FileVisitResult.SKIP_SUBTREE;
            break;
         default:
            throw new NoWhenBranchMatchedException();
      }

      return var10000;
   }

   @ExperimentalPathApi
   @SinceKotlin(version = "1.8")
   @JvmStatic
   public fun Path.deleteRecursively() {
      if (!deleteRecursivelyImpl$PathsKt__PathRecursiveFunctionsKt(`$this$deleteRecursively`).isEmpty()) {
         val var2: FileSystemException = new FileSystemException("Failed to delete one or more files. See suppressed exceptions for details.");
         val `$this$deleteRecursively_u24lambda_u240`: FileSystemException = var2;

         val `$this$forEach$iv`: java.lang.Iterable;
         for (Object element$iv : $this$forEach$iv) {
            ExceptionsKt.addSuppressed(`$this$deleteRecursively_u24lambda_u240`, `element$iv` as Exception);
         }

         throw var2 as java.lang.Throwable;
      }
   }

   @JvmStatic
   private fun Path.deleteRecursivelyImpl(): List<Exception> {
      label52: {
         val collector: ExceptionsCollector = new ExceptionsCollector(0, 1, null);
         var var17: Boolean = true;
         var var10000: Path = `$this$deleteRecursivelyImpl`.getFileName();
         if (var10000 != null) {
            val fileName: Path = var10000;
            var10000 = `$this$deleteRecursivelyImpl`.getParent();
            if (var10000 == null) {
               var10000 = `$this$deleteRecursivelyImpl`.getFileSystem().getPath("");
            }

            val parent: Path = var10000;

            var var6: DirectoryStream;
            try {
               var6 = Files.newDirectoryStream(parent);
            } catch (var11: java.lang.Throwable) {
               var6 = null;
            }

            if (var6 != null) {
               val var18: Closeable = var6;
               var var7: java.lang.Throwable = null;

               try {
                  try {
                     val stream: DirectoryStream = var18 as DirectoryStream;
                     if (var18 as DirectoryStream is SecureDirectoryStream) {
                        var17 = false;
                        collector.setPath(parent);
                        handleEntry$PathsKt__PathRecursiveFunctionsKt(stream as SecureDirectoryStream<Path>, fileName, null, collector);
                     }
                  } catch (var12: java.lang.Throwable) {
                     var7 = var12;
                     throw var12;
                  }
               } catch (var13: java.lang.Throwable) {
                  CloseableKt.closeFinally(var18, var7);
               }

               CloseableKt.closeFinally(var18, null);
            }
         }

         if (var17) {
            insecureHandleEntry$PathsKt__PathRecursiveFunctionsKt(`$this$deleteRecursivelyImpl`, null, collector);
         }

         return collector.getCollectedExceptions();
      }
   }

   @JvmStatic
   private inline fun collectIfThrows(collector: ExceptionsCollector, function: () -> Unit) {
      try {
         function.invoke();
      } catch (var4: Exception) {
         collector.collect(var4);
      }
   }

   @JvmStatic
   private inline fun <R> tryIgnoreNoSuchFileException(function: () -> R): R? {
      var var2: Any;
      try {
         var2 = function.invoke();
      } catch (var4: NoSuchFileException) {
         var2 = null;
      }

      return (R)var2;
   }

   @JvmStatic
   private fun SecureDirectoryStream<Path>.handleEntry(name: Path, parent: Path?, collector: ExceptionsCollector) {
      collector.enterEntry(name);

      try {
         if (parent != null) {
            val var10000: Path = collector.getPath();
            PathsKt.checkFileName(var10000);
            checkNotSameAs$PathsKt__PathRecursiveFunctionsKt(var10000, parent);
         }

         if (isDirectory$PathsKt__PathRecursiveFunctionsKt(`$this$handleEntry`, name, LinkOption.NOFOLLOW_LINKS)) {
            val var17: Int = collector.getTotalExceptions();
            enterDirectory$PathsKt__PathRecursiveFunctionsKt(`$this$handleEntry`, name, collector);
            if (var17 == collector.getTotalExceptions()) {
               try {
                  `$this$handleEntry`.deleteDirectory(name);
               } catch (var14: NoSuchFileException) {
               }
            }
         } else {
            try {
               `$this$handleEntry`.deleteFile(name);
            } catch (var13: NoSuchFileException) {
            }
         }
      } catch (var15: Exception) {
         collector.collect(var15);
      }

      collector.exitEntry(name);
   }

   @JvmStatic
   private fun SecureDirectoryStream<Path>.enterDirectory(name: Path, collector: ExceptionsCollector) {
      label50: {
         try {
            var var9: SecureDirectoryStream;
            try {
               var9 = `$this$enterDirectory`.newDirectoryStream(name, LinkOption.NOFOLLOW_LINKS);
            } catch (var14: NoSuchFileException) {
               var9 = null;
            }

            if (var9 != null) {
               val var22: Closeable = var9;
               var var23: java.lang.Throwable = null;

               try {
                  try {
                     val var24: SecureDirectoryStream = var22 as SecureDirectoryStream;
                     val var10000: java.util.Iterator = (var22 as SecureDirectoryStream).iterator();
                     val var10: java.util.Iterator = var10000;

                     while (var10.hasNext()) {
                        val var10001: Path = (var10.next() as Path).getFileName();
                        handleEntry$PathsKt__PathRecursiveFunctionsKt(var24, var10001, collector.getPath(), collector);
                     }
                  } catch (var15: java.lang.Throwable) {
                     var23 = var15;
                     throw var15;
                  }
               } catch (var16: java.lang.Throwable) {
                  CloseableKt.closeFinally(var22, var23);
               }

               CloseableKt.closeFinally(var22, null);
            }
         } catch (var17: Exception) {
            collector.collect(var17);
         }
      }
   }

   @JvmStatic
   private fun SecureDirectoryStream<Path>.isDirectory(entryName: Path, vararg options: LinkOption): Boolean {
      var var5: java.lang.Boolean;
      try {
         var5 = `$this$isDirectory`.getFileAttributeView(entryName, BasicFileAttributeView.class, Arrays.copyOf(options, options.length))
            .readAttributes()
            .isDirectory();
      } catch (var7: NoSuchFileException) {
         var5 = null;
      }

      return var5 != null && var5;
   }

   @JvmStatic
   private fun insecureHandleEntry(entry: Path, parent: Path?, collector: ExceptionsCollector) {
      try {
         if (parent != null) {
            PathsKt.checkFileName(entry);
            checkNotSameAs$PathsKt__PathRecursiveFunctionsKt(entry, parent);
         }

         val var7: Array<LinkOption> = new LinkOption[]{LinkOption.NOFOLLOW_LINKS};
         if (Files.isDirectory(entry, Arrays.copyOf(var7, var7.length))) {
            val preEnterTotalExceptions: Int = collector.getTotalExceptions();
            insecureEnterDirectory$PathsKt__PathRecursiveFunctionsKt(entry, collector);
            if (preEnterTotalExceptions == collector.getTotalExceptions()) {
               Files.deleteIfExists(entry);
            }
         } else {
            Files.deleteIfExists(entry);
         }
      } catch (var9: Exception) {
         collector.collect(var9);
      }
   }

   @JvmStatic
   private fun insecureEnterDirectory(path: Path, collector: ExceptionsCollector) {
      label50: {
         try {
            var directoryStream: DirectoryStream;
            try {
               directoryStream = Files.newDirectoryStream(path);
            } catch (var13: NoSuchFileException) {
               directoryStream = null;
            }

            if (directoryStream != null) {
               val var21: Closeable = directoryStream;
               var var22: java.lang.Throwable = null;

               try {
                  try {
                     val var10000: java.util.Iterator = (var21 as DirectoryStream).iterator();
                     val var10: java.util.Iterator = var10000;

                     while (var10.hasNext()) {
                        val entry: Path = var10.next() as Path;
                        insecureHandleEntry$PathsKt__PathRecursiveFunctionsKt(entry, path, collector);
                     }
                  } catch (var14: java.lang.Throwable) {
                     var22 = var14;
                     throw var14;
                  }
               } catch (var15: java.lang.Throwable) {
                  CloseableKt.closeFinally(var21, var22);
               }

               CloseableKt.closeFinally(var21, null);
            }
         } catch (var16: Exception) {
            collector.collect(var16);
         }
      }
   }

   @JvmStatic
   internal fun Path.checkFileName() {
      val fileName: java.lang.String = PathsKt.getName(`$this$checkFileName`);
      switch (fileName.hashCode()) {
         case 46:
            if (fileName.equals(".")) {
               throw new IllegalFileNameException(`$this$checkFileName`);
            }
            break;
         case 1472:
            if (fileName.equals("..")) {
               throw new IllegalFileNameException(`$this$checkFileName`);
            }
            break;
         case 1473:
            if (fileName.equals("./")) {
               throw new IllegalFileNameException(`$this$checkFileName`);
            }
            break;
         case 1518:
            if (fileName.equals(".\\")) {
               throw new IllegalFileNameException(`$this$checkFileName`);
            }
            break;
         case 45679:
            if (fileName.equals("../")) {
               throw new IllegalFileNameException(`$this$checkFileName`);
            }
            break;
         case 45724:
            if (fileName.equals("..\\")) {
               throw new IllegalFileNameException(`$this$checkFileName`);
            }
         default:
      }
   }

   @JvmStatic
   private fun Path.checkNotSameAs(parent: Path) {
      if (!Files.isSymbolicLink(`$this$checkNotSameAs`) && Files.isSameFile(`$this$checkNotSameAs`, parent)) {
         throw new FileSystemLoopException(`$this$checkNotSameAs`.toString());
      }
   }

   @JvmStatic
   fun `copyToRecursively$lambda$0$PathsKt__PathRecursiveFunctionsKt`(
      `$followLinks`: Boolean, `$this$copyToRecursively`: CopyActionContext, src: Path, dst: Path
   ): CopyActionResult {
      val options: Array<LinkOption> = LinkFollowing.INSTANCE.toLinkOptions(`$followLinks`);
      val var7: Array<LinkOption> = new LinkOption[]{LinkOption.NOFOLLOW_LINKS};
      val dstIsDirectory: Boolean = Files.isDirectory(dst, Arrays.copyOf(var7, var7.length));
      val var10001: Array<LinkOption> = Arrays.copyOf(options, options.length);
      if (!Files.isDirectory(src, Arrays.copyOf(var10001, var10001.length)) || !dstIsDirectory) {
         if (dstIsDirectory) {
            PathsKt.deleteRecursively(dst);
         }

         val var8: SpreadBuilder = new SpreadBuilder(2);
         var8.addSpread(options);
         var8.add(StandardCopyOption.REPLACE_EXISTING);
         val var9: Array<CopyOption> = var8.toArray(new CopyOption[var8.size()]) as Array<CopyOption>;
      }

      return CopyActionResult.CONTINUE;
   }

   @JvmStatic
   fun `copyToRecursively$lambda$1$PathsKt__PathRecursiveFunctionsKt`(`$followLinks`: Boolean, var1: CopyActionContext, src: Path, dst: Path): CopyActionResult {
      return var1.copyToIgnoringExistingDirectory(src, dst, `$followLinks`);
   }

   @JvmStatic
   fun `copyToRecursively$destination$PathsKt__PathRecursiveFunctionsKt`(`$this_copyToRecursively`: Path, `$target`: Path, normalizedTarget: Path, source: Path): Path {
      val destination: Path = `$target`.resolve(PathsKt.relativeTo(source, `$this_copyToRecursively`).toString());
      if (!destination.normalize().startsWith(normalizedTarget)) {
         throw new IllegalFileNameException(
            source,
            destination,
            "Copying files to outside the specified target directory is prohibited. The directory being recursively copied might contain an entry with an illegal name."
         );
      } else {
         return destination;
      }
   }

   @JvmStatic
   fun `copyToRecursively$error$PathsKt__PathRecursiveFunctionsKt`(
      `$onError`: (Path?, Path?, Exception?) -> OnErrorResult,
      `$this_copyToRecursively`: Path,
      `$target`: Path,
      normalizedTarget: Path,
      source: Path,
      exception: Exception
   ): FileVisitResult {
      return toFileVisitResult$PathsKt__PathRecursiveFunctionsKt(
         `$onError`.invoke(
            source, copyToRecursively$destination$PathsKt__PathRecursiveFunctionsKt(`$this_copyToRecursively`, `$target`, normalizedTarget, source), exception
         ) as OnErrorResult
      );
   }

   @JvmStatic
   fun `copyToRecursively$copy$PathsKt__PathRecursiveFunctionsKt`(
      stack: ArrayList<Path>,
      `$copyAction`: (CopyActionContext?, Path?, Path?) -> CopyActionResult,
      `$this_copyToRecursively`: Path,
      `$target`: Path,
      normalizedTarget: Path,
      `$onError`: (Path?, Path?, Exception?) -> OnErrorResult,
      source: Path,
      attributes: BasicFileAttributes
   ): FileVisitResult {
      var var8: FileVisitResult;
      try {
         if (!stack.isEmpty()) {
            PathsKt.checkFileName(source);
            val var10001: Any = CollectionsKt.last(stack);
            checkNotSameAs$PathsKt__PathRecursiveFunctionsKt(source, var10001 as Path);
         }

         var8 = toFileVisitResult$PathsKt__PathRecursiveFunctionsKt(
            `$copyAction`.invoke(
               DefaultCopyActionContext.INSTANCE,
               source,
               copyToRecursively$destination$PathsKt__PathRecursiveFunctionsKt(`$this_copyToRecursively`, `$target`, normalizedTarget, source)
            ) as CopyActionResult
         );
      } catch (var10: Exception) {
         var8 = copyToRecursively$error$PathsKt__PathRecursiveFunctionsKt(`$onError`, `$this_copyToRecursively`, `$target`, normalizedTarget, source, var10);
      }

      return var8;
   }

   @JvmStatic
   fun `copyToRecursively$lambda$3$PathsKt__PathRecursiveFunctionsKt`(
      `$stack`: ArrayList,
      `$copyAction`: Function3,
      `$this_copyToRecursively`: Path,
      `$target`: Path,
      `$normalizedTarget`: Path,
      `$onError`: Function3,
      `$this$visitFileTree`: FileVisitorBuilder
   ): Unit {
      `$this$visitFileTree`.onPreVisitDirectory(PathsKt__PathRecursiveFunctionsKt::copyToRecursively$lambda$3$0$PathsKt__PathRecursiveFunctionsKt);
      `$this$visitFileTree`.onVisitFile(new 2(`$stack`, `$copyAction`, `$this_copyToRecursively`, `$target`, `$normalizedTarget`, `$onError`));
      `$this$visitFileTree`.onVisitFileFailed(
         new kotlin.io.path.PathsKt__PathRecursiveFunctionsKt.copyToRecursively.5.3(`$onError`, `$this_copyToRecursively`, `$target`, `$normalizedTarget`)
      );
      `$this$visitFileTree`.onPostVisitDirectory(PathsKt__PathRecursiveFunctionsKt::copyToRecursively$lambda$3$1$PathsKt__PathRecursiveFunctionsKt);
      return Unit.INSTANCE;
   }

   @JvmStatic
   fun `copyToRecursively$lambda$3$0$PathsKt__PathRecursiveFunctionsKt`(
      `$stack`: ArrayList,
      `$copyAction`: Function3,
      `$this_copyToRecursively`: Path,
      `$target`: Path,
      `$normalizedTarget`: Path,
      `$onError`: Function3,
      directory: Path,
      attributes: BasicFileAttributes
   ): FileVisitResult {
      val var8: FileVisitResult = copyToRecursively$copy$PathsKt__PathRecursiveFunctionsKt(
         `$stack`, `$copyAction`, `$this_copyToRecursively`, `$target`, `$normalizedTarget`, `$onError`, directory, attributes
      );
      if (var8 === FileVisitResult.CONTINUE) {
         `$stack`.add(directory);
      }

      return var8;
   }

   @JvmStatic
   fun `copyToRecursively$lambda$3$1$PathsKt__PathRecursiveFunctionsKt`(
      `$stack`: ArrayList,
      `$onError`: Function3,
      `$this_copyToRecursively`: Path,
      `$target`: Path,
      `$normalizedTarget`: Path,
      directory: Path,
      exception: IOException
   ): FileVisitResult {
      CollectionsKt.removeLast(`$stack`);
      return if (exception == null)
         FileVisitResult.CONTINUE
         else
         copyToRecursively$error$PathsKt__PathRecursiveFunctionsKt(`$onError`, `$this_copyToRecursively`, `$target`, `$normalizedTarget`, directory, exception);
   }

   open fun PathsKt__PathRecursiveFunctionsKt() {
   }
}
