package it.krzeminski.snakeyaml.engine.kmp.internal.utils

internal fun CharSequence.codePointCount(beginIndex: Int = 0, endIndex: Int = `$this$codePointCount`.length()): Int {
   val var10000: CharArray = `$this$codePointCount`.toString().toCharArray();
   return codePointCount(var10000, beginIndex, endIndex);
}

@JvmSynthetic
fun `codePointCount$default`(var0: java.lang.CharSequence, var1: Int, var2: Int, var3: Int, var4: Any): Int {
   if ((var3 and 1) != 0) {
      var1 = 0;
   }

   if ((var3 and 2) != 0) {
      var2 = var0.length();
   }

   return codePointCount(var0, var1, var2);
}

internal fun CharArray.codePointCount(beginIndex: Int = 0, endIndex: Int = `$this$codePointCount`.length): Int {
   if (beginIndex < 0) {
      throw new IndexOutOfBoundsException("beginIndex must not be less than 0, but was $beginIndex");
   } else if (endIndex > `$this$codePointCount`.length) {
      throw new IndexOutOfBoundsException("endIndex must not be greater than size (${`$this$codePointCount`.length}), but was $endIndex");
   } else if (beginIndex > endIndex) {
      throw new IndexOutOfBoundsException("beginIndex must not be greater than endIndex ($endIndex), but was $beginIndex");
   } else {
      var index: Int = beginIndex;

      var count: Int;
      for (count = 0; index < endIndex; count++) {
         if (java.lang.Character.isHighSurrogate(`$this$codePointCount`[index])
            && ++index < endIndex
            && java.lang.Character.isLowSurrogate(`$this$codePointCount`[index])) {
            index++;
         }
      }

      return count;
   }
}

@JvmSynthetic
fun `codePointCount$default`(var0: CharArray, var1: Int, var2: Int, var3: Int, var4: Any): Int {
   if ((var3 and 1) != 0) {
      var1 = 0;
   }

   if ((var3 and 2) != 0) {
      var2 = var0.length;
   }

   return codePointCount(var0, var1, var2);
}

internal fun CharSequence.toCodePoints(): List<Int> {
   val var1: java.util.List = CollectionsKt.createListBuilder(`$this$toCodePoints`.length());
   val `$this$toCodePoints_u24lambda_u240`: java.util.List = var1;
   var i: Int = 0;

   for (int c = 0; i < $this$toCodePoints.length(); c++) {
      val cp: Int = codePointAt(`$this$toCodePoints`, i);
      `$this$toCodePoints_u24lambda_u240`.add(cp);
      i += Character.INSTANCE.charCount$snakeyaml_engine_kmp(cp);
   }

   return CollectionsKt.build(var1);
}

internal fun CharSequence.codePointAt(index: Int): Int {
   if (0 > index || index >= `$this$codePointAt`.length()) {
      throw new IndexOutOfBoundsException("index $index was not in range ${StringsKt.getIndices(`$this$codePointAt`)}");
   } else {
      val firstChar: Char = `$this$codePointAt`.charAt(index);
      if (java.lang.Character.isHighSurrogate(firstChar)) {
         val nextChar: java.lang.Character = StringsKt.getOrNull(`$this$codePointAt`, index + 1);
         if (nextChar != null && java.lang.Character.isLowSurrogate(nextChar)) {
            return Character.INSTANCE.toCodePoint$snakeyaml_engine_kmp(firstChar, nextChar);
         }
      }

      return firstChar;
   }
}
