package io.ktor.utils.io

import java.util.ArrayList
import kotlin.jvm.internal.SourceDebugExtension

@JvmInline
@InternalAPI
@SourceDebugExtension(["SMAP\nLineEndingMode.kt\nKotlin\n*S Kotlin\n*F\n+ 1 LineEndingMode.kt\nio/ktor/utils/io/LineEndingMode\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,78:1\n774#2:79\n865#2,2:80\n*S KotlinDebug\n*F\n+ 1 LineEndingMode.kt\nio/ktor/utils/io/LineEndingMode\n*L\n43#1:79\n43#1:80,2\n*E\n"])
public inline class LineEndingMode {
   private final val mode: Int

   @JvmStatic
   public operator fun contains(other: LineEndingMode): Boolean {
      return (var0 or var1) == var0;
   }

   @JvmStatic
   public operator fun plus(other: LineEndingMode): LineEndingMode {
      return constructor-impl(var0 or var1);
   }

   @JvmStatic
   public open fun toString(): String {
      val var10000: java.lang.String;
      if (equals-impl0(var0, CR)) {
         var10000 = "CR";
      } else if (equals-impl0(var0, LF)) {
         var10000 = "LF";
      } else if (equals-impl0(var0, CRLF)) {
         var10000 = "CRLF";
      } else {
         val `$this$filter$iv`: java.lang.Iterable = values;
         val `destination$iv$iv`: java.util.Collection = new ArrayList();

         for (Object element$iv$iv : $this$filter$iv) {
            if (contains-lTjpP64(var0, (`element$iv$iv` as LineEndingMode).unbox-impl())) {
               `destination$iv$iv`.add(`element$iv$iv`);
            }
         }

         var10000 = (`destination$iv$iv` as java.util.List).toString();
      }

      return var10000;
   }

   override fun toString(): java.lang.String {
      return toString-impl(this.mode);
   }

   @JvmStatic
   fun `hashCode-impl`(var0: Int): Int {
      return Integer.hashCode(var0);
   }

   public override fun hashCode(): Int {
      return hashCode-impl(this.mode);
   }

   @JvmStatic
   fun `equals-impl`(var0: Int, other: Any): Boolean {
      if (other !is LineEndingMode) {
         return false;
      } else {
         return var0 == (other as LineEndingMode).unbox-impl();
      }
   }

   public override operator fun equals(other: Any?): Boolean {
      return equals-impl(this.mode, other);
   }

   @JvmStatic
   fun `constructor-impl`(mode: Int): Int {
      return mode;
   }

   @JvmStatic
   fun `equals-impl0`(p1: Int, p2: Int): Boolean {
      return p1 == p2;
   }

   public companion object {
      public final val CR: LineEndingMode
      public final val LF: LineEndingMode
      public final val CRLF: LineEndingMode
      public final val Any: LineEndingMode
      private final val values: List<LineEndingMode>
   }
}
