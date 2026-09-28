package okio

import java.io.File
import java.nio.file.Paths
import java.util.ArrayList
import kotlin.jvm.internal.SourceDebugExtension
import okio.internal.-Path

@SourceDebugExtension(["SMAP\nPath.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Path.kt\nokio/Path\n+ 2 Path.kt\nokio/internal/-Path\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,132:1\n39#2,3:133\n47#2,28:136\n53#2,22:168\n106#2:190\n111#2:191\n116#2,6:192\n133#2,5:198\n143#2:203\n148#2,25:204\n188#2:229\n193#2,11:230\n198#2,6:241\n193#2,11:247\n198#2,6:258\n222#2,41:264\n267#2:305\n281#2:306\n286#2:307\n291#2:308\n296#2:309\n1563#3:164\n1634#3,3:165\n*S KotlinDebug\n*F\n+ 1 Path.kt\nokio/Path\n*L\n44#1:133,3\n47#1:136,28\n50#1:168,22\n53#1:190\n56#1:191\n60#1:192,6\n64#1:198,5\n68#1:203\n72#1:204,25\n75#1:229\n78#1:230,11\n81#1:241,6\n87#1:247,11\n90#1:258,6\n95#1:264,41\n97#1:305\n104#1:306\n106#1:307\n108#1:308\n110#1:309\n47#1:164\n47#1:165,3\n*E\n"])
public class Path internal constructor(bytes: ByteString) : java.lang.Comparable<Path> {
   internal final val bytes: ByteString

   public final val root: Path?
      public final get() {
         val `rootLength$iv`: Int = -Path.access$rootLength(this);
         return if (`rootLength$iv` == -1) null else new Path(this.getBytes$okio().substring(0, `rootLength$iv`));
      }


   public final val segments: List<String>
      public final get() {
         val `$this$map$iv$iv`: Path = this;
         val `$this$mapTo$iv$iv$iv`: java.util.List = new ArrayList();
         var `destination$iv$iv$iv`: Int = -Path.access$rootLength(this);
         if (`destination$iv$iv$iv` == -1) {
            `destination$iv$iv$iv` = 0;
         } else if (`destination$iv$iv$iv` < this.getBytes$okio().size() && this.getBytes$okio().getByte(`destination$iv$iv$iv`) == 92) {
            `destination$iv$iv$iv`++;
         }

         var `$i$f$mapTo`: Int = `destination$iv$iv$iv`;

         for (int var8 = this.getBytes$okio().size(); i$iv$iv < var8; i$iv$iv++) {
            if (`$this$map$iv$iv`.getBytes$okio().getByte(`$i$f$mapTo`) == 47 || `$this$map$iv$iv`.getBytes$okio().getByte(`$i$f$mapTo`) == 92) {
               `$this$mapTo$iv$iv$iv`.add(`$this$map$iv$iv`.getBytes$okio().substring(`destination$iv$iv$iv`, `$i$f$mapTo`));
               `destination$iv$iv$iv` = `$i$f$mapTo` + 1;
            }
         }

         if (`destination$iv$iv$iv` < `$this$map$iv$iv`.getBytes$okio().size()) {
            `$this$mapTo$iv$iv$iv`.add(`$this$map$iv$iv`.getBytes$okio().substring(`destination$iv$iv$iv`, `$this$map$iv$iv`.getBytes$okio().size()));
         }

         val var13: java.lang.Iterable = `$this$mapTo$iv$iv$iv`;
         val var15: java.util.Collection = new ArrayList(CollectionsKt.collectionSizeOrDefault(`$this$mapTo$iv$iv$iv`, 10));

         for (Object item$iv$iv$iv : var13) {
            var15.add((`item$iv$iv$iv` as ByteString).utf8());
         }

         return var15 as MutableList<java.lang.String>;
      }


   public final val segmentsBytes: List<ByteString>
      public final get() {
         val `$this$commonSegmentsBytes$iv`: Path = this;
         val `result$iv`: java.util.List = new ArrayList();
         var `segmentStart$iv`: Int = -Path.access$rootLength(this);
         if (`segmentStart$iv` == -1) {
            `segmentStart$iv` = 0;
         } else if (`segmentStart$iv` < this.getBytes$okio().size() && this.getBytes$okio().getByte(`segmentStart$iv`) == 92) {
            `segmentStart$iv`++;
         }

         var `i$iv`: Int = `segmentStart$iv`;

         for (int var6 = this.getBytes$okio().size(); i$iv < var6; i$iv++) {
            if (`$this$commonSegmentsBytes$iv`.getBytes$okio().getByte(`i$iv`) == 47 || `$this$commonSegmentsBytes$iv`.getBytes$okio().getByte(`i$iv`) == 92) {
               `result$iv`.add(`$this$commonSegmentsBytes$iv`.getBytes$okio().substring(`segmentStart$iv`, `i$iv`));
               `segmentStart$iv` = `i$iv` + 1;
            }
         }

         if (`segmentStart$iv` < `$this$commonSegmentsBytes$iv`.getBytes$okio().size()) {
            `result$iv`.add(`$this$commonSegmentsBytes$iv`.getBytes$okio().substring(`segmentStart$iv`, `$this$commonSegmentsBytes$iv`.getBytes$okio().size()));
         }

         return `result$iv`;
      }


   public final val isAbsolute: Boolean
      public final get() {
         return -Path.access$rootLength(this) != -1;
      }


   public final val isRelative: Boolean
      public final get() {
         return -Path.access$rootLength(this) == -1;
      }


   public final val volumeLetter: Char?
      public final get() {
         val var10000: Character;
         if (ByteString.indexOf$default(this.getBytes$okio(), -Path.access$getSLASH$p(), 0, 2, null) != -1) {
            var10000 = null;
         } else if (this.getBytes$okio().size() < 2) {
            var10000 = null;
         } else if (this.getBytes$okio().getByte(1) != 58) {
            var10000 = null;
         } else {
            val `c$iv`: Char = (char)this.getBytes$okio().getByte(0);
            var10000 = if (('a' > `c$iv` || `c$iv` >= '{') && ('A' > `c$iv` || `c$iv` >= '[')) null else `c$iv`;
         }

         return var10000;
      }


   public final val nameBytes: ByteString
      public final get() {
         val `lastSlash$iv`: Int = -Path.access$getIndexOfLastSlash(this);
         return if (`lastSlash$iv` != -1)
            ByteString.substring$default(this.getBytes$okio(), `lastSlash$iv` + 1, 0, 2, null)
            else
            (if (this.volumeLetter() != null && this.getBytes$okio().size() == 2) ByteString.EMPTY else this.getBytes$okio());
      }


   public final val name: String
      public final get() {
         return this.nameBytes().utf8();
      }


   public final val parent: Path?
      public final get() {
         val var10000: Path;
         if (!(this.getBytes$okio() == -Path.access$getDOT$p())
            && !(this.getBytes$okio() == -Path.access$getSLASH$p())
            && !(this.getBytes$okio() == -Path.access$getBACKSLASH$p())
            && !-Path.access$lastSegmentIsDotDot(this)) {
            val `lastSlash$iv`: Int = -Path.access$getIndexOfLastSlash(this);
            var10000 = if (`lastSlash$iv` == 2 && this.volumeLetter() != null)
               (if (this.getBytes$okio().size() == 3) null else new Path(ByteString.substring$default(this.getBytes$okio(), 0, 3, 1, null)))
               else
               (
                  if (`lastSlash$iv` == 1 && this.getBytes$okio().startsWith(-Path.access$getBACKSLASH$p()))
                     null
                     else
                     (
                        if (`lastSlash$iv` == -1 && this.volumeLetter() != null)
                           (if (this.getBytes$okio().size() == 2) null else new Path(ByteString.substring$default(this.getBytes$okio(), 0, 2, 1, null)))
                           else
                           (
                              if (`lastSlash$iv` == -1)
                                 new Path(-Path.access$getDOT$p())
                                 else
                                 (
                                    if (`lastSlash$iv` == 0)
                                       new Path(ByteString.substring$default(this.getBytes$okio(), 0, 1, 1, null))
                                       else
                                       new Path(ByteString.substring$default(this.getBytes$okio(), 0, `lastSlash$iv`, 1, null))
                                 )
                           )
                     )
               );
         } else {
            var10000 = null;
         }

         return var10000;
      }


   public final val isRoot: Boolean
      public final get() {
         return -Path.access$rootLength(this) == this.getBytes$okio().size();
      }


   init {
      this.bytes = bytes;
   }

   @JvmName(name = "resolve")
   public operator fun div(child: String): Path {
      return -Path.commonResolve(this, -Path.toPath(new Buffer().writeUtf8(child), false), false);
   }

   @JvmName(name = "resolve")
   public operator fun div(child: ByteString): Path {
      return -Path.commonResolve(this, -Path.toPath(new Buffer().write(child), false), false);
   }

   @JvmName(name = "resolve")
   public operator fun div(child: Path): Path {
      return -Path.commonResolve(this, child, false);
   }

   public fun resolve(child: String, normalize: Boolean = false): Path {
      return -Path.commonResolve(this, -Path.toPath(new Buffer().writeUtf8(child), false), normalize);
   }

   public fun resolve(child: ByteString, normalize: Boolean = false): Path {
      return -Path.commonResolve(this, -Path.toPath(new Buffer().write(child), false), normalize);
   }

   public fun resolve(child: Path, normalize: Boolean = false): Path {
      return -Path.commonResolve(this, child, normalize);
   }

   public fun relativeTo(other: Path): Path {
      if (!(this.getRoot() == other.getRoot())) {
         throw new IllegalArgumentException(("Paths of different roots cannot be relative to each other: $this and $other").toString());
      } else {
         val `thisSegments$iv`: java.util.List = this.getSegmentsBytes();
         val `otherSegments$iv`: java.util.List = other.getSegmentsBytes();
         var `firstNewSegmentIndex$iv`: Int = 0;
         val `minSegmentsSize$iv`: Int = Math.min(`thisSegments$iv`.size(), `otherSegments$iv`.size());

         while (firstNewSegmentIndex$iv < minSegmentsSize$iv && thisSegments$iv.get(firstNewSegmentIndex$iv) == otherSegments$iv.get(firstNewSegmentIndex$iv)) {
            `firstNewSegmentIndex$iv`++;
         }

         val var10000: Path;
         if (`firstNewSegmentIndex$iv` == `minSegmentsSize$iv` && this.getBytes$okio().size() == other.getBytes$okio().size()) {
            var10000 = Path.Companion.get$default(Companion, ".", false, 1, null);
         } else {
            if (`otherSegments$iv`.subList(`firstNewSegmentIndex$iv`, `otherSegments$iv`.size()).indexOf(-Path.access$getDOT_DOT$p()) != -1) {
               throw new IllegalArgumentException(("Impossible relative path to resolve: $this and $other").toString());
            }

            if (other.getBytes$okio() == -Path.access$getDOT$p()) {
               var10000 = this;
            } else {
               val `buffer$iv`: Buffer = new Buffer();
               var var19: ByteString = -Path.access$getSlash(other);
               if (var19 == null) {
                  var19 = -Path.access$getSlash(this);
                  if (var19 == null) {
                     var19 = -Path.access$toSlash(DIRECTORY_SEPARATOR);
                  }
               }

               val `slash$iv`: ByteString = var19;
               var `i$iv`: Int = `firstNewSegmentIndex$iv`;

               for (int var12 = otherSegments$iv.size(); i$iv < var12; i$iv++) {
                  `buffer$iv`.write(-Path.access$getDOT_DOT$p());
                  `buffer$iv`.write(`slash$iv`);
               }

               `i$iv` = `firstNewSegmentIndex$iv`;

               for (int var18 = thisSegments$iv.size(); i$iv < var18; i$iv++) {
                  `buffer$iv`.write(`thisSegments$iv`.get(`i$iv`) as ByteString);
                  `buffer$iv`.write(`slash$iv`);
               }

               var10000 = -Path.toPath(`buffer$iv`, false);
            }
         }

         return var10000;
      }
   }

   public fun normalized(): Path {
      return Companion.get(this.toString(), true);
   }

   public fun toFile(): File {
      return new File(this.toString());
   }

   public fun toNioPath(): java.nio.file.Path {
      val var10000: java.nio.file.Path = Paths.get(this.toString());
      return var10000;
   }

   public open operator fun compareTo(other: Path): Int {
      return this.getBytes$okio().compareTo(other.getBytes$okio());
   }

   public override operator fun equals(other: Any?): Boolean {
      return other is Path && (other as Path).getBytes$okio() == this.getBytes$okio();
   }

   public override fun hashCode(): Int {
      return this.getBytes$okio().hashCode();
   }

   public override fun toString(): String {
      return this.getBytes$okio().utf8();
   }

   @JvmStatic
   fun {
      val var10000: java.lang.String = File.separator;
      DIRECTORY_SEPARATOR = var10000;
   }

   public companion object {
      public final val DIRECTORY_SEPARATOR: String

      @JvmName(name = "get")
      @JvmOverloads
      public fun String.toPath(normalize: Boolean = ...): Path {
         return -Path.commonToPath(`$this$toPath`, normalize);
      }

      @JvmName(name = "get")
      @JvmOverloads
      public fun File.toOkioPath(normalize: Boolean = ...): Path {
         val var10001: java.lang.String = `$this$toOkioPath`.toString();
         return this.get(var10001, normalize);
      }

      @JvmName(name = "get")
      @JvmOverloads
      public fun java.nio.file.Path.toOkioPath(normalize: Boolean = ...): Path {
         return this.get(`$this$toOkioPath`.toString(), normalize);
      }

      @JvmName(name = "get")
      @JvmOverloads
      fun java.lang.String.get(): Path {
         return get$default(this, `$this$toPath`, false, 1, null);
      }

      @JvmName(name = "get")
      @JvmOverloads
      fun File.get(): Path {
         return get$default(this, `$this$toOkioPath`, false, 1, null);
      }

      @JvmName(name = "get")
      @JvmOverloads
      fun java.nio.file.Path.get(): Path {
         return get$default(this, `$this$toOkioPath`, false, 1, null);
      }
   }
}
