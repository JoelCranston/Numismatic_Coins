@file:SourceDebugExtension(["SMAP\nHexFormat.kt\nKotlin\n*S Kotlin\n*F\n+ 1 HexFormat.kt\nkotlin/text/HexFormatKt\n+ 2 _Strings.kt\nkotlin/text/StringsKt___StringsKt\n*L\n1#1,845:1\n1088#2,2:846\n*S KotlinDebug\n*F\n+ 1 HexFormat.kt\nkotlin/text/HexFormatKt\n*L\n843#1:846,2\n*E\n"])

package kotlin.text

import kotlin.internal.InlineOnly
import kotlin.jvm.internal.Intrinsics
import kotlin.jvm.internal.SourceDebugExtension
import kotlin.text.HexFormat.Builder

@WasExperimental(markerClass = [ExperimentalStdlibApi::class])
@SinceKotlin(version = "2.2")
@InlineOnly
public inline fun HexFormat(builderAction: (Builder) -> Unit): HexFormat {
   val var1: HexFormat.Builder = new HexFormat.Builder();
   builderAction.invoke(var1);
   return var1.build();
}

private fun String.isCaseSensitive(): Boolean {
   val `$this$any$iv`: java.lang.CharSequence = `$this$isCaseSensitive`;
   var var3: Int = 0;

   var var10000: Boolean;
   while (true) {
      if (var3 >= `$this$any$iv`.length()) {
         var10000 = false;
         break;
      }

      val `element$iv`: Char = `$this$any$iv`.charAt(var3);
      if (Intrinsics.compare(`element$iv`, 128) >= 0 || Character.isLetter(`element$iv`)) {
         var10000 = true;
         break;
      }

      var3++;
   }

   return var10000;
}

@JvmSynthetic
fun `access$isCaseSensitive`(`$receiver`: java.lang.String): Boolean {
   return isCaseSensitive(`$receiver`);
}
