@file:SourceDebugExtension(["SMAP\nPath.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Path.kt\nio/ktor/util/PathKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Strings.kt\nkotlin/text/StringsKt___StringsKt\n*L\n1#1,102:1\n1#2:103\n413#3,4:104\n*S KotlinDebug\n*F\n+ 1 Path.kt\nio/ktor/util/PathKt\n*L\n46#1:104,4\n*E\n"])

package io.ktor.util

import java.io.File
import kotlin.jvm.internal.SourceDebugExtension

public fun File.combineSafe(relativePath: String): File {
   return combineSafe(`$this$combineSafe`, new File(relativePath));
}

public fun File.normalizeAndRelativize(): File {
   return dropLeadingTopDirs(notRooted(FilesKt.normalize(`$this$normalizeAndRelativize`)));
}

private fun combineSafe(dir: File, relativePath: File): File {
   val normalized: File = normalizeAndRelativize(relativePath);
   if (FilesKt.startsWith(normalized, "..")) {
      throw new IllegalArgumentException("Bad relative path $relativePath");
   } else if (normalized.isAbsolute()) {
      throw new IllegalStateException(("Bad relative path $relativePath").toString());
   } else {
      return new File(dir, normalized.getPath());
   }
}

private fun File.notRooted(): File {
   if (!FilesKt.isRooted(`$this$notRooted`)) {
      return `$this$notRooted`;
   } else {
      var current: File = `$this$notRooted`;

      while (true) {
         val var10000: File = current.getParentFile();
         if (var10000 == null) {
            val var10: java.lang.String = `$this$notRooted`.getPath();
            val var9: java.lang.String = StringsKt.drop(var10, current.getName().length());
            var `index$iv`: Int = 0;
            val var5: Int = var9.length();

            while (true) {
               if (`index$iv` >= var5) {
                  var11 = "";
                  break;
               }

               val it: Char = var9.charAt(`index$iv`);
               if (it != '\\' && it != '/') {
                  var11 = var9.substring(`index$iv`);
                  break;
               }

               `index$iv`++;
            }

            return new File(var11);
         }

         current = var10000;
      }
   }
}

internal fun dropLeadingTopDirs(path: String): Int {
   var startIndex: Int = 0;
   val lastIndex: Int = path.length() - 1;

   while (startIndex <= lastIndex) {
      val first: Char = path.charAt(startIndex);
      if (isPathSeparator(first)) {
         startIndex++;
      } else {
         if (first != '.') {
            break;
         }

         if (startIndex == lastIndex) {
            startIndex++;
            break;
         }

         val second: Char = path.charAt(startIndex + 1);
         val var10001: Byte;
         if (isPathSeparator(second)) {
            var10001 = 2;
         } else {
            if (second != '.') {
               break;
            }

            if (startIndex + 2 == path.length()) {
               var10001 = 2;
            } else {
               if (!isPathSeparator(path.charAt(startIndex + 2))) {
                  break;
               }

               var10001 = 3;
            }
         }

         startIndex += var10001;
      }
   }

   return startIndex;
}

private fun Char.isPathSeparator(): Boolean {
   return `$this$isPathSeparator` == '\\' || `$this$isPathSeparator` == '/';
}

private fun Char.isPathSeparatorOrDot(): Boolean {
   return `$this$isPathSeparatorOrDot` == '.' || isPathSeparator(`$this$isPathSeparatorOrDot`);
}

private fun File.dropLeadingTopDirs(): File {
   var var10000: java.lang.String = `$this$dropLeadingTopDirs`.getPath();
   if (var10000 == null) {
      var10000 = "";
   }

   val startIndex: Int = dropLeadingTopDirs(var10000);
   if (startIndex == 0) {
      return `$this$dropLeadingTopDirs`;
   } else if (startIndex >= `$this$dropLeadingTopDirs`.getPath().length()) {
      return new File(".");
   } else {
      var var10002: java.lang.String = `$this$dropLeadingTopDirs`.getPath();
      var10002 = var10002.substring(startIndex);
      return new File(var10002);
   }
}
