package it.krzeminski.snakeyaml.engine.kmp.common

import java.util.ArrayList
import java.util.LinkedHashMap
import java.util.Map.Entry
import kotlin.jvm.internal.SourceDebugExtension

@SourceDebugExtension(["SMAP\nCharConstants.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CharConstants.kt\nit/krzeminski/snakeyaml/engine/kmp/common/CharConstants\n+ 2 _Strings.kt\nkotlin/text/StringsKt___StringsKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,147:1\n975#2:148\n1046#2,3:149\n1193#3,2:152\n1267#3,4:154\n*S KotlinDebug\n*F\n+ 1 CharConstants.kt\nit/krzeminski/snakeyaml/engine/kmp/common/CharConstants\n*L\n23#1:148\n23#1:149,3\n110#1:152,2\n110#1:154,4\n*E\n"])
public class CharConstants private constructor(content: String) {
   public final val contains: BooleanArray

   init {
      val `$this$mapTo$iv$iv`: java.lang.CharSequence = content;
      val `destination$iv$iv`: java.util.Collection = new ArrayList(content.length());

      for (int var8 = 0; var8 < $this$mapTo$iv$iv.length(); var8++) {
         `destination$iv$iv`.add(Integer.valueOf(`$this$mapTo$iv$iv`.charAt(var8)));
      }

      val contentCodes: java.util.List = `destination$iv$iv` as java.util.List;
      var var13: Int = 0;

      val var14: BooleanArray;
      for (var14 = new boolean[128]; var13 < 128; var13++) {
         var14[var13] = contentCodes.contains(var13);
      }

      this.contains = var14;
   }

   public fun has(c: Int): Boolean {
      return c < 128 && this.contains[c];
   }

   public fun hasNo(c: Int): Boolean {
      return !this.has(c);
   }

   public fun has(c: Int, additional: String): Boolean {
      return this.has(c) || StringsKt.contains$default(additional, (char)c, false, 2, null);
   }

   public fun hasNo(c: Int, additional: String): Boolean {
      return !this.has(c, additional);
   }

   @JvmStatic
   fun {
      val var13: java.lang.Iterable = ESCAPE_REPLACEMENTS.entrySet();
      val `destination$iv$iv`: java.util.Map = new LinkedHashMap(
         RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(var13, 10)), 16)
      );

      for (Object element$iv$iv : $this$associate$iv) {
         val var15: Pair = TuplesKt.to((`element$iv$iv` as Entry).getValue() as java.lang.String, (`element$iv$iv` as Entry).getKey() as Character);
         `destination$iv$iv`.put(var15.getFirst(), var15.getSecond());
      }

      escapedReplacements = `destination$iv$iv`;
      ESCAPE_CODES = MapsKt.mapOf(new Pair[]{TuplesKt.to('x', 2), TuplesKt.to('u', 4), TuplesKt.to('U', 8)});
   }

   public companion object {
      private const val ALPHA_S: String
      private const val LINEBR_S: String
      private const val FULL_LINEBR_S: String
      private const val NULL_OR_LINEBR_S: String
      private const val NULL_BL_LINEBR_S: String
      private const val NULL_BL_T_LINEBR_S: String
      private const val NULL_BL_T_S: String
      private const val URI_CHARS_SUFFIX_S: String
      public final val LINEBR: CharConstants
      public final val NULL_OR_LINEBR: CharConstants
      public final val NULL_BL_LINEBR: CharConstants
      public final val NULL_BL_T_LINEBR: CharConstants
      public final val NULL_BL_T: CharConstants
      public final val URI_CHARS_FOR_TAG_PREFIX: CharConstants
      public final val URI_CHARS_FOR_TAG_SUFFIX: CharConstants
      public final val ALPHA: CharConstants
      private const val ASCII_SIZE: Int
      public final val ESCAPE_REPLACEMENTS: Map<Char, String>
      private final val escapedReplacements: Map<String, Char>
      public final val ESCAPE_CODES: Map<Char, Int>

      public fun escapeChar(char: Char): String {
         val charString: java.lang.String = java.lang.String.valueOf(var1);
         label11:
         if (StringsKt.contains$default(" /\"", var1, false, 2, null)) {
            return charString;
         } else {
            val var10000: Character = CharConstants.access$getEscapedReplacements$cp().get(charString) as Character;
            return if (var10000 != null) "\${var10000}" else charString;
         }
      }
   }
}
