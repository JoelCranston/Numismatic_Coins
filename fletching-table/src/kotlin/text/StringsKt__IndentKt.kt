package kotlin.text

import java.util.ArrayList
import kotlin.internal.IntrinsicConstEvaluation
import kotlin.jvm.functions.Function1
import kotlin.jvm.internal.SourceDebugExtension

@SourceDebugExtension(["SMAP\nIndent.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Indent.kt\nkotlin/text/StringsKt__IndentKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 4 _Strings.kt\nkotlin/text/StringsKt___StringsKt\n*L\n1#1,129:1\n119#1,2:131\n121#1,4:146\n126#1,2:159\n119#1,2:168\n121#1,4:183\n126#1,2:190\n1#2:130\n1#2:156\n1#2:187\n1#2:211\n1583#3,11:133\n1878#3,2:144\n1880#3:157\n1594#3:158\n774#3:161\n865#3,2:162\n1563#3:164\n1634#3,3:165\n1583#3,11:170\n1878#3,2:181\n1880#3:188\n1594#3:189\n1583#3,11:198\n1878#3,2:209\n1880#3:212\n1594#3:213\n158#4,6:150\n158#4,6:192\n*S KotlinDebug\n*F\n+ 1 Indent.kt\nkotlin/text/StringsKt__IndentKt\n*L\n42#1:131,2\n42#1:146,4\n42#1:159,2\n83#1:168,2\n83#1:183,4\n83#1:190,2\n42#1:156\n83#1:187\n120#1:211\n42#1:133,11\n42#1:144,2\n42#1:157\n42#1:158\n79#1:161\n79#1:162,2\n80#1:164\n80#1:165,3\n83#1:170,11\n83#1:181,2\n83#1:188\n83#1:189\n120#1:198,11\n120#1:209,2\n120#1:212\n120#1:213\n43#1:150,6\n107#1:192,6\n*E\n"])
internal class StringsKt__IndentKt : StringsKt__AppendableKt {
   @IntrinsicConstEvaluation
   @JvmStatic
   public fun String.trimMargin(marginPrefix: String = "|"): String {
      return StringsKt.replaceIndentByMargin(`$this$trimMargin`, "", marginPrefix);
   }

   @JvmStatic
   public fun String.replaceIndentByMargin(newIndent: String = "", marginPrefix: String = "|"): String {
      if (StringsKt.isBlank(marginPrefix)) {
         throw new IllegalArgumentException("marginPrefix must be non-blank string.".toString());
      } else {
         val lines: java.util.List = StringsKt.lines(`$this$replaceIndentByMargin`);
         val `resultSizeEstimate$iv`: Int = `$this$replaceIndentByMargin`.length() + newIndent.length() * lines.size();
         val `indentAddFunction$iv`: Function1 = getIndentFunction$StringsKt__IndentKt(newIndent);
         val `lastIndex$iv`: Int = CollectionsKt.getLastIndex(lines);
         val `$this$mapIndexedNotNull$iv$iv`: java.lang.Iterable = lines;
         val `destination$iv$iv$iv`: java.util.Collection = new ArrayList();
         var `index$iv$iv$iv$iv`: Int = 0;

         for (Object item$iv$iv$iv$iv : $this$mapIndexedNotNull$iv$iv) {
            val var19: Int = `index$iv$iv$iv$iv`++;
            if (var19 < 0) {
               CollectionsKt.throwIndexOverflow();
            }

            val `value$iv`: java.lang.String = `item$iv$iv$iv$iv` as java.lang.String;
            var var41: java.lang.String;
            if ((var19 == 0 || var19 == `lastIndex$iv`) && StringsKt.isBlank(`item$iv$iv$iv$iv` as java.lang.String)) {
               var41 = null;
            } else {
               label84: {
                  val `$this$indexOfFirst$iv`: java.lang.CharSequence = `value$iv`;
                  var `index$iv`: Int = 0;
                  val var31: Int = `$this$indexOfFirst$iv`.length();

                  while (true) {
                     if (`index$iv` >= var31) {
                        var39 = -1;
                        break;
                     }

                     if (!CharsKt.isWhitespace(`$this$indexOfFirst$iv`.charAt(`index$iv`))) {
                        var39 = `index$iv`;
                        break;
                     }

                     `index$iv`++;
                  }

                  if (var39 == -1) {
                     var41 = null;
                  } else if (StringsKt.startsWith$default(`value$iv`, marginPrefix, var39, false, 4, null)) {
                     val var38: Int = var39 + marginPrefix.length();
                     var41 = `value$iv`.substring(var38);
                  } else {
                     var41 = null;
                  }

                  if (var41 != null) {
                     var41 = `indentAddFunction$iv`.invoke(var41) as java.lang.String;
                     if (var41 != null) {
                        break label84;
                     }
                  }

                  var41 = `value$iv`;
               }
            }

            if (var41 != null) {
               `destination$iv$iv$iv`.add(var41);
            }
         }

         return (CollectionsKt.joinTo$default(
               `destination$iv$iv$iv` as java.util.List, new StringBuilder(`resultSizeEstimate$iv`), "\n", null, null, 0, null, null, 124, null
            ) as StringBuilder)
            .toString();
      }
   }

   @IntrinsicConstEvaluation
   @JvmStatic
   public fun String.trimIndent(): String {
      return StringsKt.replaceIndent(`$this$trimIndent`, "");
   }

   @JvmStatic
   public fun String.replaceIndent(newIndent: String = ""): String {
      val lines: java.util.List = StringsKt.lines(`$this$replaceIndent`);
      var `resultSizeEstimate$iv`: java.lang.Iterable = lines;
      var `lastIndex$iv`: java.util.Collection = new ArrayList();

      for (Object element$iv$iv : resultSizeEstimate$iv) {
         if (!StringsKt.isBlank(`$this$mapIndexedNotNullTo$iv$iv$iv` as java.lang.String)) {
            `lastIndex$iv`.add(`$this$mapIndexedNotNullTo$iv$iv$iv`);
         }
      }

      `resultSizeEstimate$iv` = `lastIndex$iv` as java.util.List;
      `lastIndex$iv` = new ArrayList(CollectionsKt.collectionSizeOrDefault(`lastIndex$iv` as java.util.List, 10));

      for (Object item$iv$iv : resultSizeEstimate$iv) {
         `lastIndex$iv`.add(indentWidth$StringsKt__IndentKt(var41 as java.lang.String));
      }

      val var10000: Int = CollectionsKt.minOrNull(`lastIndex$iv`);
      val minCommonIndent: Int = (int)(var10000 ?: 0);
      val var32: Int = `$this$replaceIndent`.length() + newIndent.length() * lines.size();
      val var34: Function1 = getIndentFunction$StringsKt__IndentKt(newIndent);
      val var36: Int = CollectionsKt.getLastIndex(lines);
      val var38: java.lang.Iterable = lines;
      val var43: java.util.Collection = new ArrayList();
      var `index$iv$iv$iv$iv`: Int = 0;

      for (Object item$iv$iv$iv$iv : $this$mapIndexedNotNull$iv$iv) {
         val var19: Int = `index$iv$iv$iv$iv`++;
         if (var19 < 0) {
            CollectionsKt.throwIndexOverflow();
         }

         val `value$iv`: java.lang.String = `item$iv$iv$iv$iv` as java.lang.String;
         var var48: java.lang.String;
         if ((var19 == 0 || var19 == var36) && StringsKt.isBlank(`item$iv$iv$iv$iv` as java.lang.String)) {
            var48 = null;
         } else {
            label78: {
               val var47: java.lang.String = StringsKt.drop(`value$iv`, minCommonIndent);
               if (var47 != null) {
                  var48 = var34.invoke(var47) as java.lang.String;
                  if (var48 != null) {
                     break label78;
                  }
               }

               var48 = `value$iv`;
            }
         }

         if (var48 != null) {
            var43.add(var48);
         }
      }

      return (CollectionsKt.joinTo$default(var43 as java.util.List, new StringBuilder(var32), "\n", null, null, 0, null, null, 124, null) as StringBuilder)
         .toString();
   }

   @JvmStatic
   public fun String.prependIndent(indent: String = "    "): String {
      return SequencesKt.joinToString$default(
         SequencesKt.map(StringsKt.lineSequence(`$this$prependIndent`), StringsKt__IndentKt::prependIndent$lambda$0$StringsKt__IndentKt),
         "\n",
         null,
         null,
         0,
         null,
         null,
         62,
         null
      );
   }

   @JvmStatic
   private fun String.indentWidth(): Int {
      val `$this$indexOfFirst$iv`: java.lang.CharSequence = `$this$indentWidth`;
      var var3: Int = 0;
      val var4: Int = `$this$indexOfFirst$iv`.length();

      var var10000: Int;
      while (true) {
         if (var3 >= var4) {
            var10000 = -1;
            break;
         }

         if (!CharsKt.isWhitespace(`$this$indexOfFirst$iv`.charAt(var3))) {
            var10000 = var3;
            break;
         }

         var3++;
      }

      return if (var10000 == -1) `$this$indentWidth`.length() else var10000;
   }

   @JvmStatic
   private fun getIndentFunction(indent: String): (String) -> String {
      return if (indent.length() == 0)
         StringsKt__IndentKt::getIndentFunction$lambda$0$StringsKt__IndentKt
         else
         StringsKt__IndentKt::getIndentFunction$lambda$1$StringsKt__IndentKt;
   }

   @JvmStatic
   private inline fun List<String>.reindent(resultSizeEstimate: Int, indentAddFunction: (String) -> String, indentCutFunction: (String) -> String?): String {
      val lastIndex: Int = CollectionsKt.getLastIndex(`$this$reindent`);
      val `$this$mapIndexedNotNull$iv`: java.lang.Iterable = `$this$reindent`;
      val `destination$iv$iv`: java.util.Collection = new ArrayList();
      var `index$iv$iv$iv`: Int = 0;

      for (Object item$iv$iv$iv : $this$mapIndexedNotNull$iv) {
         val var16: Int = `index$iv$iv$iv`++;
         if (var16 < 0) {
            CollectionsKt.throwIndexOverflow();
         }

         val value: java.lang.String = `item$iv$iv$iv` as java.lang.String;
         var var26: java.lang.String;
         if ((var16 == 0 || var16 == lastIndex) && StringsKt.isBlank(`item$iv$iv$iv` as java.lang.String)) {
            var26 = null;
         } else {
            label44: {
               var26 = indentCutFunction.invoke(value) as java.lang.String;
               if (var26 != null) {
                  var26 = indentAddFunction.invoke(var26) as java.lang.String;
                  if (var26 != null) {
                     break label44;
                  }
               }

               var26 = value;
            }
         }

         if (var26 != null) {
            `destination$iv$iv`.add(var26);
         }
      }

      return (CollectionsKt.joinTo$default(
            `destination$iv$iv` as java.util.List, new StringBuilder(resultSizeEstimate), "\n", null, null, 0, null, null, 124, null
         ) as StringBuilder)
         .toString();
   }

   @JvmStatic
   fun `prependIndent$lambda$0$StringsKt__IndentKt`(`$indent`: java.lang.String, it: java.lang.String): java.lang.String {
      return if (StringsKt.isBlank(it)) (if (it.length() < `$indent`.length()) `$indent` else it) else "$`$indent`$it";
   }

   @JvmStatic
   fun `getIndentFunction$lambda$0$StringsKt__IndentKt`(line: java.lang.String): java.lang.String {
      return line;
   }

   @JvmStatic
   fun `getIndentFunction$lambda$1$StringsKt__IndentKt`(`$indent`: java.lang.String, line: java.lang.String): java.lang.String {
      return "$`$indent`$line";
   }

   open fun StringsKt__IndentKt() {
   }
}
