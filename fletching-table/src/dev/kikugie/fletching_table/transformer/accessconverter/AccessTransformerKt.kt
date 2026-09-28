package dev.kikugie.fletching_table.transformer.accessconverter

private final val modifier: String
   private final get() {
      var var10000: java.lang.String;
      switch (AccessTransformerKt.WhenMappings.$EnumSwitchMapping$0[$this$modifier.ordinal()]) {
         case 1:
            var10000 = "public";
            break;
         case 2:
            var10000 = "protected";
            break;
         case 3:
            var10000 = "default";
            break;
         default:
            var10000 = "";
      }

      return var10000;
   }


private final val modifier: String
   private final get() {
      return "${getModifier(`$this$modifier`.getVisibility())}${if (`$this$modifier`.getNonFinal()) "-f" else ""}";
   }


@JvmSynthetic
fun `access$getModifier`(`$receiver`: AccessTransformer.Entry): java.lang.String {
   return getModifier(`$receiver`);
}
// $VF: Class flags could not be determined
@JvmSynthetic
internal class WhenMappings {
   @JvmStatic
   fun {
      val var0: IntArray = new int[AwTokenType.values().length];

      try {
         var0[AwTokenType.ACCESSIBLE.ordinal()] = 1;
      } catch (var4: NoSuchFieldError) {
      }

      try {
         var0[AwTokenType.EXTENDABLE.ordinal()] = 2;
      } catch (var3: NoSuchFieldError) {
      }

      try {
         var0[AwTokenType.MUTABLE.ordinal()] = 3;
      } catch (var2: NoSuchFieldError) {
      }

      $EnumSwitchMapping$0 = var0;
   }
}
