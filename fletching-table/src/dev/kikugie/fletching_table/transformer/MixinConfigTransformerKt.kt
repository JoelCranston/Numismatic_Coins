package dev.kikugie.fletching_table.transformer

import dev.kikugie.fletching_table.annotation.MixinEnvironment
import dev.kikugie.fletching_table.annotation.MixinEnvironment.Env

private fun Env.category(default: Env): String {
   return if (`$this$category` != MixinEnvironment.Env.DEFAULT)
      category(`$this$category`)
      else
      (if (default != MixinEnvironment.Env.DEFAULT) category(default) else "mixins");
}

private fun Env.category(): String {
   var var10000: java.lang.String;
   switch (MixinConfigTransformerKt.WhenMappings.$EnumSwitchMapping$0[$this$category.ordinal()]) {
      case 1:
         var10000 = "mixins";
         break;
      case 2:
         var10000 = "client";
         break;
      case 3:
         var10000 = "server";
         break;
      default:
         throw new IllegalStateException("Default should have been resolved beforehand".toString());
   }

   return var10000;
}

@JvmSynthetic
fun `access$category`(`$receiver`: MixinEnvironment.Env, var1: MixinEnvironment.Env): java.lang.String {
   return category(`$receiver`, var1);
}
// $VF: Class flags could not be determined
@JvmSynthetic
internal class WhenMappings {
   @JvmStatic
   fun {
      val var0: IntArray = new int[MixinEnvironment.Env.values().length];

      try {
         var0[MixinEnvironment.Env.MAIN.ordinal()] = 1;
      } catch (var4: NoSuchFieldError) {
      }

      try {
         var0[MixinEnvironment.Env.CLIENT.ordinal()] = 2;
      } catch (var3: NoSuchFieldError) {
      }

      try {
         var0[MixinEnvironment.Env.SERVER.ordinal()] = 3;
      } catch (var2: NoSuchFieldError) {
      }

      $EnumSwitchMapping$0 = var0;
   }
}
