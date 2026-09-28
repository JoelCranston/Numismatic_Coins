package io.ktor.util

public fun Char.isLowerCase(): Boolean {
   return Character.toLowerCase(`$this$isLowerCase`) == `$this$isLowerCase`;
}

public fun String.toCharArray(): CharArray {
   var var1: Int = 0;
   val var2: Int = `$this$toCharArray`.length();

   val var3: CharArray;
   for (var3 = new char[var2]; var1 < var2; var1++) {
      var3[var1] = `$this$toCharArray`.charAt(var1);
   }

   return var3;
}
