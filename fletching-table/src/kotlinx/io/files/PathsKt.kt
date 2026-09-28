@file:SourceDebugExtension(["SMAP\nPaths.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Paths.kt\nkotlinx/io/files/PathsKt\n+ 2 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n*L\n1#1,177:1\n13346#2,2:178\n*S KotlinDebug\n*F\n+ 1 Paths.kt\nkotlinx/io/files/PathsKt\n*L\n84#1:178,2\n*E\n"])

package kotlinx.io.files

import java.util.Arrays
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.io.CoreKt
import kotlinx.io.Sink
import kotlinx.io.Source

public fun Path(base: String, vararg parts: String): Path {
   val var2: StringBuilder = new StringBuilder();
   val `$this$Path_u24lambda_u241`: StringBuilder = var2;
   var2.append(base);

   for (Object element$iv : parts) {
      if (`$this$Path_u24lambda_u241`.length() > 0 && !StringsKt.endsWith$default(`$this$Path_u24lambda_u241`, PathsJvmKt.SystemPathSeparator, false, 2, null)) {
         `$this$Path_u24lambda_u241`.append(PathsJvmKt.SystemPathSeparator);
      }

      `$this$Path_u24lambda_u241`.append((java.lang.String)`element$iv`);
   }

   val var10000: java.lang.String = var2.toString();
   return PathsJvmKt.Path(var10000);
}

public fun Path(base: Path, vararg parts: String): Path {
   return Path(base.toString(), Arrays.copyOf(parts, parts.length));
}

@Deprecated(message = "Use FileSystem.source instead", replaceWith = @ReplaceWith(expression = "SystemFileSystem.source(this).buffered()", imports = ["kotlinx.io.files.FileSystem"]), level = DeprecationLevel.WARNING)
@JvmName(name = "sourceDeprecated")
public fun Path.source(): Source {
   return CoreKt.buffered(FileSystemJvmKt.SystemFileSystem.source(`$this$source`));
}

@Deprecated(message = "Use FileSystem.sink instead", replaceWith = @ReplaceWith(expression = "SystemFileSystem.sink(this).buffered()", imports = ["kotlinx.io.files.FileSystem"]), level = DeprecationLevel.WARNING)
@JvmName(name = "sinkDeprecated")
public fun Path.sink(): Sink {
   return CoreKt.buffered(FileSystem.sink$default(FileSystemJvmKt.SystemFileSystem, `$this$sink`, false, 2, null));
}

internal fun removeTrailingSeparators(path: String, isWindows_: Boolean = FileSystemJvmKt.isWindows()): String {
   return if (isWindows_)
      removeTrailingSeparatorsWindows(if (path.length() > 1) (if (path.charAt(1) == ':') 3 else (if (isUnc(path)) 2 else 1)) else 1, path)
      else
      removeTrailingSeparatorsUnix(path);
}

@JvmSynthetic
fun `removeTrailingSeparators$default`(var0: java.lang.String, var1: Boolean, var2: Int, var3: Any): java.lang.String {
   if ((var2 and 2) != 0) {
      var1 = FileSystemJvmKt.isWindows();
   }

   return removeTrailingSeparators(var0, var1);
}

private fun isUnc(path: String): Boolean {
   if (path.length() < 2) {
      return false;
   } else if (StringsKt.startsWith$default(path, "\\\\", false, 2, null)) {
      return true;
   } else {
      return StringsKt.startsWith$default(path, "//", false, 2, null);
   }
}

private fun removeTrailingSeparatorsUnix(path: String): String {
   var idx: Int = path.length();

   while (idx > 1 && path.charAt(idx - 1) == '/') {
      idx--;
   }

   val var10000: java.lang.String = path.substring(0, idx);
   return var10000;
}

private fun removeTrailingSeparatorsWindows(suffixLength: Int, path: String): String {
   if (suffixLength < 1) {
      throw new IllegalArgumentException("Failed requirement.".toString());
   } else {
      var idx: Int;
      for (idx = path.length(); idx > suffixLength; idx--) {
         val c: Char = path.charAt(idx - 1);
         if (c != '\\' && c != '/') {
            break;
         }
      }

      val var10000: java.lang.String = path.substring(0, idx);
      return var10000;
   }
}
