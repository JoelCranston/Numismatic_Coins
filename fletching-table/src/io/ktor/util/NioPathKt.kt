@file:SourceDebugExtension(["SMAP\nNioPath.kt\nKotlin\n*S Kotlin\n*F\n+ 1 NioPath.kt\nio/ktor/util/NioPathKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,60:1\n1#2:61\n346#3,8:62\n*S KotlinDebug\n*F\n+ 1 NioPath.kt\nio/ktor/util/NioPathKt\n*L\n40#1:62,8\n*E\n"])

package io.ktor.util

import java.io.File
import java.nio.file.InvalidPathException
import java.nio.file.Path
import kotlin.jvm.internal.SourceDebugExtension

public fun Path.combineSafe(relativePath: Path): Path {
   val normalized: Path = normalizeAndRelativize(relativePath);
   if (normalized.startsWith("..")) {
      throw new InvalidPathException(relativePath.toString(), "Relative path $relativePath beginning with .. is invalid");
   } else if (normalized.isAbsolute()) {
      throw new IllegalStateException(("Bad relative path $relativePath").toString());
   } else if (`$this$combineSafe`.getNameCount() == 0) {
      return normalized;
   } else {
      val var10000: Path = `$this$combineSafe`.resolve(normalized);
      return var10000;
   }
}

public fun Path.normalizeAndRelativize(): Path {
   var var10000: Path = `$this$normalizeAndRelativize`.getRoot();
   if (var10000 != null) {
      var10000 = var10000.relativize(`$this$normalizeAndRelativize`);
      if (var10000 != null) {
         var10000 = var10000.normalize();
         if (var10000 != null) {
            var10000 = dropLeadingTopDirs(var10000);
            if (var10000 != null) {
               return var10000;
            }
         }
      }
   }

   var10000 = `$this$normalizeAndRelativize`.normalize();
   return dropLeadingTopDirs(var10000);
}

private fun Path.dropLeadingTopDirs(): Path {
   val `$this$indexOfFirst$iv`: java.lang.Iterable = `$this$dropLeadingTopDirs`;
   var `index$iv`: Int = 0;
   val var5: java.util.Iterator = `$this$indexOfFirst$iv`.iterator();

   var var10000: Int;
   while (true) {
      if (!var5.hasNext()) {
         var10000 = -1;
         break;
      }

      val `item$iv`: Any = var5.next();
      if (`index$iv` < 0) {
         kotlin.collections.CollectionsKt.throwIndexOverflow();
      }

      if (!((`item$iv` as Path).toString() == "..")) {
         var10000 = `index$iv`;
         break;
      }

      `index$iv`++;
   }

   if (var10000 <= 0) {
      return `$this$dropLeadingTopDirs`;
   } else {
      val var9: Path = `$this$dropLeadingTopDirs`.subpath(var10000, `$this$dropLeadingTopDirs`.getNameCount());
      return var9;
   }
}

public fun File.combineSafe(relativePath: Path): File {
   val normalized: Path = normalizeAndRelativize(relativePath);
   if (normalized.startsWith("..")) {
      throw new InvalidPathException(relativePath.toString(), "Relative path $relativePath beginning with .. is invalid");
   } else if (normalized.isAbsolute()) {
      throw new IllegalStateException(("Bad relative path $relativePath").toString());
   } else {
      return new File(`$this$combineSafe`, normalized.toString());
   }
}
