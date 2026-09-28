package io.ktor.websocket

import io.ktor.utils.io.InternalAPI
import java.util.LinkedHashMap
import kotlin.enums.EnumEntries
import kotlin.jvm.internal.SourceDebugExtension

public data class CloseReason(code: Short, message: String) {
   public final val code: Short
   public final val message: String

   public final val knownReason: io.ktor.websocket.CloseReason.Codes?
      public final get() {
         return CloseReason.Codes.Companion.byCode(this.code);
      }


   init {
      this.code = code;
      this.message = message;
   }

   public constructor(code: io.ktor.websocket.CloseReason.Codes, message: String) : this(code.getCode(), message)
   public override fun toString(): String {
      val var10000: StringBuilder = new StringBuilder().append("CloseReason(reason=");
      var var10001: Any = this.getKnownReason();
      if (var10001 == null) {
         var10001 = this.code;
      }

      return var10000.append(var10001).append(", message=").append(this.message).append(')').toString();
   }

   public operator fun component1(): Short {
      return this.code;
   }

   public operator fun component2(): String {
      return this.message;
   }

   public fun copy(code: Short = this.code, message: String = this.message): CloseReason {
      return new CloseReason(code, message);
   }

   public override fun hashCode(): Int {
      return java.lang.Short.hashCode(this.code) * 31 + this.message.hashCode();
   }

   public override operator fun equals(other: Any?): Boolean {
      if (this === other) {
         return true;
      } else if (other !is CloseReason) {
         return false;
      } else {
         val var2: CloseReason = other as CloseReason;
         if (this.code != (other as CloseReason).code) {
            return false;
         } else {
            return this.message == var2.message;
         }
      }
   }

   @SourceDebugExtension(["SMAP\nCloseReason.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CloseReason.kt\nio/ktor/websocket/CloseReason$Codes\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,83:1\n1208#2,2:84\n1236#2,4:86\n*S KotlinDebug\n*F\n+ 1 CloseReason.kt\nio/ktor/websocket/CloseReason$Codes\n*L\n58#1:84,2\n58#1:86,4\n*E\n"])
   public enum class Codes(code: Short) {
      NORMAL((short)1000),
      GOING_AWAY((short)1001),
      PROTOCOL_ERROR((short)1002),
      CANNOT_ACCEPT((short)1003),
      /** @deprecated */
      @Deprecated(message = "This code MUST NOT be set as a status code in a Close control frame by an endpoint")
      @InternalAPI
      CLOSED_ABNORMALLY((short)1006),
      NOT_CONSISTENT((short)1007),
      VIOLATED_POLICY((short)1008),
      TOO_BIG((short)1009),
      NO_EXTENSION((short)1010),
      INTERNAL_ERROR((short)1011),
      SERVICE_RESTART((short)1012),
      TRY_AGAIN_LATER((short)1013)
      public final val code: Short
      @JvmStatic
      public CloseReason.Codes.Companion Companion = new CloseReason.Codes.Companion(null);
      @JvmStatic
      private java.util.Map<java.lang.Short, CloseReason.Codes> byCodeMap;
      /** @deprecated */
      @JvmField
      @JvmStatic
      public CloseReason.Codes UNEXPECTED_CONDITION;

      init {
         this.code = code;
      }

      @JvmStatic
      fun getEntries(): EnumEntries<CloseReason.Codes> {
         return $ENTRIES;
      }

      @JvmStatic
      fun {
         val `$this$associateBy$iv`: java.lang.Iterable = getEntries();
         val `destination$iv$iv`: java.util.Map = new LinkedHashMap(
            RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(`$this$associateBy$iv`, 10)), 16)
         );

         for (Object element$iv$iv : $this$associateBy$iv) {
            `destination$iv$iv`.put((`element$iv$iv` as CloseReason.Codes).code, `element$iv$iv`);
         }

         byCodeMap = `destination$iv$iv`;
         UNEXPECTED_CONDITION = INTERNAL_ERROR;
      }

      public companion object {
         private final val byCodeMap: Map<Short, io.ktor.websocket.CloseReason.Codes>

         @Deprecated(
            message = "Use INTERNAL_ERROR instead.",
            replaceWith = @ReplaceWith(
               expression = "INTERNAL_ERROR",
               imports = {"io.ktor.websocket.CloseReason.Codes.INTERNAL_ERROR"}
            ),
            level = DeprecationLevel.ERROR
         )
         public final val UNEXPECTED_CONDITION: io.ktor.websocket.CloseReason.Codes

         public fun byCode(code: Short): io.ktor.websocket.CloseReason.Codes? {
            return CloseReason.Codes.access$getByCodeMap$cp().get(code) as CloseReason.Codes;
         }
      }
   }
}
