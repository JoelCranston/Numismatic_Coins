package io.ktor.websocket

import kotlin.enums.EnumEntries
import kotlin.jvm.internal.SourceDebugExtension

@SourceDebugExtension(["SMAP\nFrameType.kt\nKotlin\n*S Kotlin\n*F\n+ 1 FrameType.kt\nio/ktor/websocket/FrameType\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,66:1\n1999#2,14:67\n669#2,11:81\n*S KotlinDebug\n*F\n+ 1 FrameType.kt\nio/ktor/websocket/FrameType\n*L\n52#1:67,14\n54#1:81,11\n*E\n"])
public enum class FrameType(controlFrame: Boolean, opcode: Int) {
   TEXT(false, 1),
   BINARY(false, 2),
   CLOSE(true, 8),
   PING(true, 9),
   PONG(true, 10)
   public final val controlFrame: Boolean
   public final val opcode: Int
   @JvmStatic
   public FrameType.Companion Companion = new FrameType.Companion(null);
   @JvmStatic
   private int maxOpcode;
   @JvmStatic
   private FrameType[] byOpcodeArray;

   init {
      this.controlFrame = controlFrame;
      this.opcode = opcode;
   }

   @JvmStatic
   fun getEntries(): EnumEntries<FrameType> {
      return $ENTRIES;
   }

   @JvmStatic
   fun {
      val `iterator$iv`: java.util.Iterator = getEntries().iterator();
      var var10000: Any;
      if (!`iterator$iv`.hasNext()) {
         var10000 = null;
      } else {
         var `maxElem$iv`: Any = `iterator$iv`.next();
         if (!`iterator$iv`.hasNext()) {
            var10000 = `maxElem$iv`;
         } else {
            var var19: Int = (`maxElem$iv` as FrameType).opcode;

            do {
               val var21: Any = `iterator$iv`.next();
               if (var19 < (var21 as FrameType).opcode) {
                  `maxElem$iv` = var21;
                  var19 = (var21 as FrameType).opcode;
               }
            } while (iterator$iv.hasNext());

            var10000 = `maxElem$iv`;
         }
      }

      maxOpcode = (var10000 as FrameType).opcode;
      var var15: Int = 0;
      val var16: Int = maxOpcode + 1;

      for (var17 = new FrameType[maxOpcode + 1]; var15 < var16; var15++) {
         val var18: Int = var15;
         val var20: java.lang.Iterable = getEntries();
         var var24: Any = null;
         var var25: Boolean = false;

         label43: {
            for (Object element$iv : var20) {
               if ((`element$iv` as FrameType).opcode == var18) {
                  if (var25) {
                     var10000 = null;
                     break label43;
                  }

                  var24 = `element$iv`;
                  var25 = true;
               }
            }

            var10000 = if (!var25) null else var24;
         }

         var17[var15] = (FrameType)var10000;
      }

      byOpcodeArray = var17;
   }

   public companion object {
      private final val maxOpcode: Int
      private final val byOpcodeArray: Array<FrameType?>

      public operator fun get(opcode: Int): FrameType? {
         return if (0 <= opcode && opcode <= FrameType.access$getMaxOpcode$cp()) FrameType.access$getByOpcodeArray$cp()[opcode] else null;
      }
   }
}
