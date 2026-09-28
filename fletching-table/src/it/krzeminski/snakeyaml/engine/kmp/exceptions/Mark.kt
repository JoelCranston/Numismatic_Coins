package it.krzeminski.snakeyaml.engine.kmp.exceptions

import it.krzeminski.snakeyaml.engine.kmp.common.CharConstants
import it.krzeminski.snakeyaml.engine.kmp.internal.utils.AppendableExtensionsKt
import it.krzeminski.snakeyaml.engine.kmp.internal.utils.CharSequenceExtensionsKt
import java.util.ArrayList
import kotlin.jvm.internal.SourceDebugExtension

@SourceDebugExtension(["SMAP\nMark.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Mark.kt\nit/krzeminski/snakeyaml/engine/kmp/exceptions/Mark\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,167:1\n944#2,15:168\n967#2,7:183\n*S KotlinDebug\n*F\n+ 1 Mark.kt\nit/krzeminski/snakeyaml/engine/kmp/exceptions/Mark\n*L\n122#1:168,15\n127#1:183,7\n*E\n"])
public class Mark @JvmOverloads  public constructor(name: String, index: Int, line: Int, column: Int, codepoints: List<Int>, pointer: Int = 0) {
   public final val name: String
   public final val index: Int
   public final val line: Int
   public final val column: Int
   public final val codepoints: List<Int>
   public final val pointer: Int

   @Deprecated(
      message = "Converted to a List<Int>, replace with `codepoints` (may change semantics)",
      replaceWith = @ReplaceWith(
         expression = "codepoints",
         imports = {}
      )
   )
   public final val buffer: IntArray
      public final get() {
         return CollectionsKt.toIntArray(this.codepoints);
      }


   init {
      this.name = name;
      this.index = index;
      this.line = line;
      this.column = column;
      this.codepoints = codepoints;
      this.pointer = pointer;
   }

   @Deprecated(message = "No longer used - please convert CharSequence to codepoints")
   @JvmOverloads
   internal constructor(name: String, index: Int, line: Int, column: Int, str: CharSequence, pointer: Int = 0) : this(
         name, index, line, column, CharSequenceExtensionsKt.toCodePoints(str), pointer
      )
   @Deprecated(message = "No longer used - please use a List<Int> instead of IntArray")
   @JvmOverloads
   internal constructor(name: String, index: Int, line: Int, column: Int, buffer: IntArray, pointer: Int = 0) : this(
         name, index, line, column, ArraysKt.toList(buffer), pointer
      )
   private fun isLineBreak(c: Int): Boolean {
      return CharConstants.NULL_OR_LINEBR.has(c);
   }

   @JvmOverloads
   public fun createSnippet(indentSize: Int = 4, maxLength: Int = 75): String {
      val halfMaxLength: Int = maxLength / 2;
      val lineAfterPointer: java.util.List = CollectionsKt.take(this.codepoints, this.pointer);
      var var10000: java.util.List;
      if (lineAfterPointer.isEmpty()) {
         var10000 = CollectionsKt.emptyList();
      } else {
         label68: {
            val tail: java.util.ListIterator = lineAfterPointer.listIterator(lineAfterPointer.size());

            while (iterator$iv.hasPrevious()) {
               if (this.isLineBreak((tail.previous() as java.lang.Number).intValue())) {
                  tail.next();
                  val `$this$createSnippet_u24lambda_u242`: Int = lineAfterPointer.size() - tail.nextIndex();
                  if (`$this$createSnippet_u24lambda_u242` == 0) {
                     var10000 = CollectionsKt.emptyList();
                  } else {
                     val var11: ArrayList = new ArrayList(`$this$createSnippet_u24lambda_u242`);
                     val var12: ArrayList = var11;

                     while (iterator$iv.hasNext()) {
                        var12.add(tail.next());
                     }

                     var10000 = var11;
                  }
                  break label68;
               }
            }

            var10000 = CollectionsKt.toList(lineAfterPointer);
         }
      }

      val lineBeforePointer: java.lang.String = AppendableExtensionsKt.joinCodepointsToString(var10000);
      val var15: java.lang.Iterable = CollectionsKt.drop(this.codepoints, this.pointer);
      val var19: ArrayList = new ArrayList();

      for (Object item$iv : var15) {
         if (this.isLineBreak((var23 as java.lang.Number).intValue())) {
            break;
         }

         var19.add(var23);
      }

      val var14: java.lang.String = AppendableExtensionsKt.joinCodepointsToString(var19);
      val var16: java.lang.String = if (lineBeforePointer.length() > halfMaxLength)
         " ... ${StringsKt.drop(StringsKt.takeLast(lineBeforePointer, halfMaxLength), 5)}"
         else
         lineBeforePointer;
      val var18: java.lang.String = if (var14.length() > halfMaxLength) "${StringsKt.dropLast(StringsKt.take(var14, halfMaxLength), 5)} ... " else var14;
      val var20: java.lang.String = StringsKt.repeat(" ", indentSize);
      val var22: StringBuilder = new StringBuilder();
      var22.append(var20);
      var22.append(var16);
      var22.append(var18);
      var22.append('\n');
      var22.append(var20);
      var22.append(StringsKt.repeat(" ", var16.length()));
      var22.append("^");
      return var22.toString();
   }

   public override fun toString(): String {
      return StringsKt.trimMargin$default(
         "\n            | in ${StringsKt.trim(this.name).toString()}, line ${this.line + 1}, column ${this.column + 1}:\n            |${createSnippet$default(
            this, 0, 0, 3, null
         )}\n        ",
         null,
         1,
         null
      );
   }

   @JvmOverloads
   fun Mark(name: java.lang.String, index: Int, line: Int, column: Int, codepoints: MutableList<Int>) {
      this(name, index, line, column, codepoints, 0, 32, null);
   }

   /** @deprecated */
   @Deprecated(message = "No longer used - please convert CharSequence to codepoints")
   @JvmOverloads
   fun Mark(name: java.lang.String, index: Int, line: Int, column: Int, str: java.lang.CharSequence) {
      this(name, index, line, column, str, 0, 32, null);
   }

   /** @deprecated */
   @Deprecated(message = "No longer used - please use a List<Int> instead of IntArray")
   @JvmOverloads
   fun Mark(name: java.lang.String, index: Int, line: Int, column: Int, buffer: IntArray) {
      this(name, index, line, column, buffer, 0, 32, null);
   }

   @JvmOverloads
   fun createSnippet(indentSize: Int): java.lang.String {
      return createSnippet$default(this, indentSize, 0, 2, null);
   }

   @JvmOverloads
   fun createSnippet(): java.lang.String {
      return createSnippet$default(this, 0, 0, 3, null);
   }

   public companion object {
      private const val SNIPPET_OVERFLOW: String
   }
}
