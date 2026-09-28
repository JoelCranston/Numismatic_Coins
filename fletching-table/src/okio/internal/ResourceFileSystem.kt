package okio.internal

import java.io.File
import java.io.FileNotFoundException
import java.io.IOException
import java.io.InputStream
import java.net.JarURLConnection
import java.net.URI
import java.net.URL
import java.net.URLConnection
import java.util.ArrayList
import java.util.Collections
import java.util.Enumeration
import java.util.LinkedHashSet
import kotlin.jvm.internal.SourceDebugExtension
import okio.FileHandle
import okio.FileMetadata
import okio.FileSystem
import okio.Okio
import okio.Path
import okio.Sink
import okio.Source

@SourceDebugExtension(["SMAP\nResourceFileSystem.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ResourceFileSystem.kt\nokio/internal/ResourceFileSystem\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,215:1\n774#2:216\n865#2,2:217\n1563#2:219\n1634#2,3:220\n774#2:223\n865#2,2:224\n1563#2:226\n1634#2,3:227\n1617#2,9:230\n1869#2:239\n1870#2:241\n1626#2:242\n1617#2,9:243\n1869#2:252\n1870#2:254\n1626#2:255\n1#3:240\n1#3:253\n*S KotlinDebug\n*F\n+ 1 ResourceFileSystem.kt\nokio/internal/ResourceFileSystem\n*L\n75#1:216\n75#1:217,2\n76#1:219\n76#1:220,3\n91#1:223\n91#1:224,2\n92#1:226\n92#1:227,3\n178#1:230,9\n178#1:239\n178#1:241\n178#1:242\n179#1:243,9\n179#1:252\n179#1:254\n179#1:255\n178#1:240\n179#1:253\n*E\n"])
internal class ResourceFileSystem internal constructor(classLoader: ClassLoader, indexEagerly: Boolean, systemFileSystem: FileSystem = FileSystem.SYSTEM)
   : FileSystem {
   private final val classLoader: ClassLoader
   private final val systemFileSystem: FileSystem

   private final val roots: List<Pair<FileSystem, Path>>
      private final get() {
         return this.roots$delegate.getValue() as MutableList<Pair<FileSystem, Path>>;
      }


   init {
      this.classLoader = classLoader;
      this.systemFileSystem = systemFileSystem;
      this.roots$delegate = LazyKt.lazy(ResourceFileSystem::roots_delegate$lambda$0);
      if (indexEagerly) {
         this.getRoots().size();
      }
   }

   public override fun canonicalize(path: Path): Path {
      return this.canonicalizeInternal(path);
   }

   private fun canonicalizeInternal(path: Path): Path {
      return ROOT.resolve(path, true);
   }

   public override fun list(dir: Path): List<Path> {
      val relativePath: java.lang.String = this.toRelativePath(dir);
      val result: java.util.Set = new LinkedHashSet();
      var foundAny: Boolean = false;

      for (Pair var6 : this.getRoots()) {
         val fileSystem: FileSystem = var6.component1() as FileSystem;
         val base: Path = var6.component2() as Path;

         try {
            val var9: java.util.Collection = result;
            var `$this$map$iv`: java.lang.Iterable = fileSystem.list(base.resolve(relativePath));
            var `destination$iv$iv`: java.util.Collection = new ArrayList();

            for (Object element$iv$iv : $this$map$iv) {
               if (ResourceFileSystem.Companion.access$keepPath(Companion, `item$iv$iv` as Path)) {
                  `destination$iv$iv`.add(`item$iv$iv`);
               }
            }

            `$this$map$iv` = `destination$iv$iv` as java.util.List;
            `destination$iv$iv` = new ArrayList(CollectionsKt.collectionSizeOrDefault(`destination$iv$iv` as java.util.List, 10));

            for (Object item$iv$iv : $this$map$iv) {
               `destination$iv$iv`.add(Companion.removeBase(var27 as Path, base));
            }

            CollectionsKt.addAll(var9, `destination$iv$iv` as java.util.List);
            foundAny = true;
         } catch (var20: IOException) {
         }
      }

      if (!foundAny) {
         throw new FileNotFoundException("file not found: $dir");
      } else {
         return CollectionsKt.toList(result);
      }
   }

   public override fun listOrNull(dir: Path): List<Path>? {
      val relativePath: java.lang.String = this.toRelativePath(dir);
      val result: java.util.Set = new LinkedHashSet();
      var foundAny: Boolean = false;

      for (Pair var6 : this.getRoots()) {
         val fileSystem: FileSystem = var6.component1() as FileSystem;
         val base: Path = var6.component2() as Path;
         val var10: java.util.List = fileSystem.listOrNull(base.resolve(relativePath));
         val var10000: java.util.List;
         if (var10 == null) {
            var10000 = null;
         } else {
            var `$this$map$iv`: java.lang.Iterable = var10;
            var `destination$iv$iv`: java.util.Collection = new ArrayList();

            for (Object element$iv$iv : $this$map$iv) {
               if (ResourceFileSystem.Companion.access$keepPath(Companion, `item$iv$iv` as Path)) {
                  `destination$iv$iv`.add(`item$iv$iv`);
               }
            }

            `$this$map$iv` = `destination$iv$iv` as java.util.List;
            `destination$iv$iv` = new ArrayList(CollectionsKt.collectionSizeOrDefault(`destination$iv$iv` as java.util.List, 10));

            for (Object item$iv$iv : $this$map$iv) {
               `destination$iv$iv`.add(Companion.removeBase(var26 as Path, base));
            }

            var10000 = `destination$iv$iv` as java.util.List;
         }

         if (var10000 != null) {
            CollectionsKt.addAll(result, var10000);
            foundAny = true;
         }
      }

      return if (foundAny) CollectionsKt.toList(result) else null;
   }

   public override fun openReadOnly(file: Path): FileHandle {
      if (!ResourceFileSystem.Companion.access$keepPath(Companion, file)) {
         throw new FileNotFoundException("file not found: $file");
      } else {
         val relativePath: java.lang.String = this.toRelativePath(file);

         for (Pair var4 : this.getRoots()) {
            val fileSystem: FileSystem = var4.component1() as FileSystem;
            val base: Path = var4.component2() as Path;

            try {
               return fileSystem.openReadOnly(base.resolve(relativePath));
            } catch (var8: FileNotFoundException) {
            }
         }

         throw new FileNotFoundException("file not found: $file");
      }
   }

   public override fun openReadWrite(file: Path, mustCreate: Boolean, mustExist: Boolean): FileHandle {
      throw new IOException("resources are not writable");
   }

   public override fun metadataOrNull(path: Path): FileMetadata? {
      if (!ResourceFileSystem.Companion.access$keepPath(Companion, path)) {
         return null;
      } else {
         val relativePath: java.lang.String = this.toRelativePath(path);

         for (Pair var4 : this.getRoots()) {
            val var10000: FileMetadata = (var4.component1() as FileSystem).metadataOrNull((var4.component2() as Path).resolve(relativePath));
            if (var10000 != null) {
               return var10000;
            }
         }

         return null;
      }
   }

   public override fun source(file: Path): Source {
      if (!ResourceFileSystem.Companion.access$keepPath(Companion, file)) {
         throw new FileNotFoundException("file not found: $file");
      } else {
         val var10000: URL = this.classLoader.getResource(Path.resolve$default(ROOT, file, false, 2, null).relativeTo(ROOT).toString());
         if (var10000 == null) {
            throw new FileNotFoundException("file not found: $file");
         } else {
            val urlConnection: URLConnection = var10000.openConnection();
            if (urlConnection is JarURLConnection) {
               (urlConnection as JarURLConnection).setUseCaches(false);
            }

            val var5: InputStream = urlConnection.getInputStream();
            return Okio.source(var5);
         }
      }
   }

   public override fun sink(file: Path, mustCreate: Boolean): Sink {
      throw new IOException("$this is read-only");
   }

   public override fun appendingSink(file: Path, mustExist: Boolean): Sink {
      throw new IOException("$this is read-only");
   }

   public override fun createDirectory(dir: Path, mustCreate: Boolean) {
      throw new IOException("$this is read-only");
   }

   public override fun atomicMove(source: Path, target: Path) {
      throw new IOException("$this is read-only");
   }

   public override fun delete(path: Path, mustExist: Boolean) {
      throw new IOException("$this is read-only");
   }

   public override fun createSymlink(source: Path, target: Path) {
      throw new IOException("$this is read-only");
   }

   private fun Path.toRelativePath(): String {
      return this.canonicalizeInternal(`$this$toRelativePath`).relativeTo(ROOT).toString();
   }

   private fun ClassLoader.toClasspathRoots(): List<Pair<FileSystem, Path>> {
      val var10000: Enumeration = `$this$toClasspathRoots`.getResources("");
      val var30: ArrayList = Collections.list(var10000);
      var `$this$mapNotNull$iv`: java.lang.Iterable = var30;
      var `destination$iv$iv`: java.util.Collection = new ArrayList();

      for (Object element$iv$iv$iv : $this$mapNotNull$iv) {
         val it: URL = `element$iv$iv$iv` as URL;
         val var31: Pair = this.toFileRoot(it);
         if (var31 != null) {
            `destination$iv$iv`.add(var31);
         }
      }

      val var32: java.util.Collection = `destination$iv$iv` as java.util.List;
      val var10001: Enumeration = `$this$toClasspathRoots`.getResources("META-INF/MANIFEST.MF");
      val var34: ArrayList = Collections.list(var10001);
      `$this$mapNotNull$iv` = var34;
      `destination$iv$iv` = new ArrayList();

      for (Object element$iv$iv$ivx : $this$mapNotNull$iv) {
         val var26: URL = `element$iv$iv$ivx` as URL;
         val var33: Pair = this.toJarRoot(var26);
         if (var33 != null) {
            `destination$iv$iv`.add(var33);
         }
      }

      return CollectionsKt.plus(var32, `destination$iv$iv`);
   }

   private fun URL.toFileRoot(): Pair<FileSystem, Path>? {
      return if (!(`$this$toFileRoot`.getProtocol() == "file"))
         null
         else
         TuplesKt.to(this.systemFileSystem, Path.Companion.get$default(Path.Companion, new File(`$this$toFileRoot`.toURI()), false, 1, null));
   }

   private fun URL.toJarRoot(): Pair<FileSystem, Path>? {
      val var10000: java.lang.String = `$this$toJarRoot`.toString();
      if (!StringsKt.startsWith$default(var10000, "jar:file:", false, 2, null)) {
         return null;
      } else {
         val suffixStart: Int = StringsKt.lastIndexOf$default(var10000, "!", 0, false, 6, null);
         if (suffixStart == -1) {
            return null;
         } else {
            val var6: Path.Companion = Path.Companion;
            val var10003: java.lang.String = var10000.substring(4, suffixStart);
            return TuplesKt.to(
               ZipFilesKt.openZip(
                  Path.Companion.get$default(var6, new File(URI.create(var10003)), false, 1, null),
                  this.systemFileSystem,
                  ResourceFileSystem::toJarRoot$lambda$0
               ),
               ROOT
            );
         }
      }
   }

   @JvmStatic
   fun `roots_delegate$lambda$0`(`this$0`: ResourceFileSystem): java.util.List {
      return `this$0`.toClasspathRoots(`this$0`.classLoader);
   }

   @JvmStatic
   fun `toJarRoot$lambda$0`(entry: ZipEntry): Boolean {
      return ResourceFileSystem.Companion.access$keepPath(Companion, entry.getCanonicalPath());
   }

   private companion object {
      public final val ROOT: Path

      public fun Path.removeBase(base: Path): Path {
         return this.getROOT()
            .resolve(StringsKt.replace$default(StringsKt.removePrefix(`$this$removeBase`.toString(), base.toString()), '\\', '/', false, 4, null));
      }

      private fun keepPath(path: Path): Boolean {
         return !StringsKt.endsWith(path.name(), ".class", true);
      }
   }
}
