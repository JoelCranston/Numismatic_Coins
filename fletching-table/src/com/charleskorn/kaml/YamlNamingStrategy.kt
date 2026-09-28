package com.charleskorn.kaml

import com.charleskorn.kaml.YamlNamingStrategy.Builtins.SnakeCase.1
import kotlin.jvm.internal.SourceDebugExtension

public fun interface YamlNamingStrategy {
   public abstract fun serialNameForYaml(serialName: String): String {
   }

   @SourceDebugExtension(["SMAP\nYamlNamingStrategy.kt\nKotlin\n*S Kotlin\n*F\n+ 1 YamlNamingStrategy.kt\ncom/charleskorn/kaml/YamlNamingStrategy$Builtins\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,98:1\n1#2:99\n*E\n"])
   public companion object Builtins {
      public final val SnakeCase: YamlNamingStrategy = (new 1()) as YamlNamingStrategy
      public final val KebabCase: YamlNamingStrategy = (new com.charleskorn.kaml.YamlNamingStrategy.Builtins.KebabCase.1()) as YamlNamingStrategy
      public final val PascalCase: YamlNamingStrategy = (new com.charleskorn.kaml.YamlNamingStrategy.Builtins.PascalCase.1()) as YamlNamingStrategy
      public final val CamelCase: YamlNamingStrategy = (new com.charleskorn.kaml.YamlNamingStrategy.Builtins.CamelCase.1()) as YamlNamingStrategy

      private fun String.toDelimitedCase(delimiter: Char): String {
         val var4: StringBuilder = new StringBuilder(`$this$toDelimitedCase`.length() * 2);
         val `$this$toDelimitedCase_u24lambda_u240`: StringBuilder = var4;
         var bufferedChar: Character = null;
         var previousCaseCharsCount: Int = 0;
         var var9: Int = 0;

         for (int var10 = $this$toDelimitedCase.length(); var9 < var10; var9++) {
            val character: Char = `$this$toDelimitedCase`.charAt(var9);
            if (Character.isUpperCase(character)) {
               if (previousCaseCharsCount == 0
                  && `$this$toDelimitedCase_u24lambda_u240`.length() > 0
                  && StringsKt.last(`$this$toDelimitedCase_u24lambda_u240`) != delimiter) {
                  `$this$toDelimitedCase_u24lambda_u240`.append(delimiter);
               }

               if (bufferedChar != null) {
                  `$this$toDelimitedCase_u24lambda_u240`.append(bufferedChar.charValue());
               }

               previousCaseCharsCount++;
               bufferedChar = Character.toLowerCase(character);
            } else {
               if (bufferedChar != null) {
                  if (previousCaseCharsCount > 1 && Character.isLetter(character)) {
                     `$this$toDelimitedCase_u24lambda_u240`.append(delimiter);
                  }

                  `$this$toDelimitedCase_u24lambda_u240`.append(bufferedChar.charValue());
                  previousCaseCharsCount = 0;
                  bufferedChar = null;
               }

               `$this$toDelimitedCase_u24lambda_u240`.append(character);
            }
         }

         if (bufferedChar != null) {
            `$this$toDelimitedCase_u24lambda_u240`.append(bufferedChar.charValue());
         }

         return var4.toString();
      }
   }
}
