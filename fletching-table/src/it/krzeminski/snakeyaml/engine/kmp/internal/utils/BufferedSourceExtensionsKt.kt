@file:SourceDebugExtension(["SMAP\nBufferedSourceExtensions.kt\nKotlin\n*S Kotlin\n*F\n+ 1 BufferedSourceExtensions.kt\nit/krzeminski/snakeyaml/engine/kmp/internal/utils/BufferedSourceExtensionsKt\n*L\n1#1,108:1\n19#1,9:109\n86#1,2:118\n31#1,13:120\n86#1,2:133\n44#1,18:135\n86#1,2:153\n67#1,6:155\n95#1,11:161\n74#1,8:172\n86#1,2:180\n86#1,2:182\n86#1,2:184\n95#1,11:186\n*S KotlinDebug\n*F\n+ 1 BufferedSourceExtensions.kt\nit/krzeminski/snakeyaml/engine/kmp/internal/utils/BufferedSourceExtensionsKt\n*L\n11#1:109,9\n11#1:118,2\n11#1:120,13\n11#1:133,2\n11#1:135,18\n11#1:153,2\n11#1:155,6\n11#1:161,11\n11#1:172,8\n27#1:180,2\n43#1:182,2\n61#1:184,2\n72#1:186,11\n*E\n"])

package it.krzeminski.snakeyaml.engine.kmp.internal.utils

import kotlin.jvm.internal.SourceDebugExtension
import okio.BufferedSource

internal inline fun BufferedSource.readUtf8WithLimit(limitBytes: Long): String {
   val buffer: BufferedSource = `$this$readUtf8WithLimit`;
   val `originalSize$iv`: Long = if (`$this$readUtf8WithLimit`.request(limitBytes)) limitBytes else `$this$readUtf8WithLimit`.getBuffer().size();
   var var10000: Long;
   if (`originalSize$iv` == 0L) {
      var10000 = 0L;
   } else {
      val `byte$iv`: Byte = `$this$readUtf8WithLimit`.getBuffer().getByte(`originalSize$iv` - 1L);
      if ((`byte$iv` and 192) != 128) {
         var10000 = `originalSize$iv` - (if (`byte$iv` < 0) 1 else 0);
      } else if (!`$this$readUtf8WithLimit`.request(`originalSize$iv` + 1L)) {
         var10000 = `originalSize$iv`;
      } else if ((`$this$readUtf8WithLimit`.getBuffer().getByte(`originalSize$iv`) and 192) != 128) {
         var10000 = `originalSize$iv`;
      } else {
         val var29: Long = `originalSize$iv`;
         val var27: Byte = 3;
         var `asInt$iv$iv`: Int = 0;

         while (true) {
            if (`asInt$iv$iv` >= var27) {
               var10000 = `originalSize$iv`;
               break;
            }

            if (--var29 == 0L) {
               var10000 = `originalSize$iv`;
               break;
            }

            val `byte$ivx`: Byte = buffer.getBuffer().getByte(var29 - 1L);
            if ((`byte$ivx` and 192) != 128) {
               var10000 = if (`byte$ivx` >= 0)
                  `originalSize$iv`
                  else
                  (
                     if ((if ((`byte$ivx` and 224) == 192) 1 else (if ((`byte$ivx` and 240) == 224) 2 else (if ((`byte$ivx` and 248) == 240) 3 else 0)))
                           < `asInt$iv$iv` + 1)
                        `originalSize$iv`
                        else
                        var29 - 1L
                  );
               break;
            }

            `asInt$iv$iv`++;
         }
      }
   }

   return `$this$readUtf8WithLimit`.readUtf8(var10000);
}

private inline fun BufferedSource.sizeOfFullValidUtf8String(limitBytes: Long): Long {
   val originalSize: Long = if (`$this$sizeOfFullValidUtf8String`.request(limitBytes)) limitBytes else `$this$sizeOfFullValidUtf8String`.getBuffer().size();
   if (originalSize == 0L) {
      return 0L;
   } else {
      val var7: Byte = `$this$sizeOfFullValidUtf8String`.getBuffer().getByte(originalSize - 1L);
      if ((var7 and 192) != 128) {
         return originalSize - (if (var7 < 0) 1 else 0);
      } else if (!`$this$sizeOfFullValidUtf8String`.request(originalSize + 1L)) {
         return originalSize;
      } else if ((`$this$sizeOfFullValidUtf8String`.getBuffer().getByte(originalSize) and 192) != 128) {
         return originalSize;
      } else {
         val var22: Long = originalSize;
         val var20: Byte = 3;

         for (int asInt$iv = 0; asInt$iv < var20; asInt$iv++) {
            if (--var22 == 0L) {
               return originalSize;
            }

            val bytex: Byte = `$this$sizeOfFullValidUtf8String`.getBuffer().getByte(var22 - 1L);
            if ((bytex and 192) != 128) {
               return if (bytex >= 0)
                  originalSize
                  else
                  (
                     if ((if ((bytex and 224) == 192) 1 else (if ((bytex and 240) == 224) 2 else (if ((bytex and 248) == 240) 3 else 0))) < `asInt$iv` + 1)
                        originalSize
                        else
                        var22 - 1L
                  );
            }
         }

         return originalSize;
      }
   }
}

private inline fun isContinuationByte(byte: Byte): Boolean {
   return (var0 and 192) == 128;
}

private inline fun continuationBytesCountFor(byte: Byte): Int {
   return if ((var0 and 224) == 192) 1 else (if ((var0 and 240) == 224) 2 else (if ((var0 and 248) == 240) 3 else 0));
}
