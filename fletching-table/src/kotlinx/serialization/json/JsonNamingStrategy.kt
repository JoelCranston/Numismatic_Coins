package kotlinx.serialization.json

import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.json.JsonNamingStrategy.Builtins.SnakeCase.1

@ExperimentalSerializationApi
public fun interface JsonNamingStrategy {
   public abstract fun serialNameForJson(descriptor: SerialDescriptor, elementIndex: Int, serialName: String): String {
   }

   @ExperimentalSerializationApi
   @SourceDebugExtension(["SMAP\nJsonNamingStrategy.kt\nKotlin\n*S Kotlin\n*F\n+ 1 JsonNamingStrategy.kt\nkotlinx/serialization/json/JsonNamingStrategy$Builtins\n+ 2 _Strings.kt\nkotlin/text/StringsKt___StringsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,178:1\n1179#2:179\n1180#2:181\n1#3:180\n*S KotlinDebug\n*F\n+ 1 JsonNamingStrategy.kt\nkotlinx/serialization/json/JsonNamingStrategy$Builtins\n*L\n149#1:179\n149#1:181\n*E\n"])
   public companion object Builtins {
      @ExperimentalSerializationApi
      public final val SnakeCase: JsonNamingStrategy = (new 1()) as JsonNamingStrategy

      @ExperimentalSerializationApi
      public final val KebabCase: JsonNamingStrategy = (new kotlinx.serialization.json.JsonNamingStrategy.Builtins.KebabCase.1()) as JsonNamingStrategy

      private fun convertCamelCase(serialName: String, delimiter: Char): String {
         val var4: StringBuilder = new StringBuilder(serialName.length() * 2);
         val `$this$convertCamelCase_u24lambda_u241`: StringBuilder = var4;
         var bufferedChar: Any = null;
         var previousUpperCharsCount: Int = 0;
         val `$this$forEach$iv`: java.lang.CharSequence = serialName;

         for (int var11 = 0; var11 < $this$forEach$iv.length(); var11++) {
            val `element$iv`: Char = `$this$forEach$iv`.charAt(var11);
            if (Character.isUpperCase(`element$iv`)) {
               if (previousUpperCharsCount == 0
                  && `$this$convertCamelCase_u24lambda_u241`.length() > 0
                  && StringsKt.last(`$this$convertCamelCase_u24lambda_u241`) != delimiter) {
                  `$this$convertCamelCase_u24lambda_u241`.append(delimiter);
               }

               if (bufferedChar != null) {
                  `$this$convertCamelCase_u24lambda_u241`.append(bufferedChar.charValue());
               }

               previousUpperCharsCount++;
               bufferedChar = Character.toLowerCase(`element$iv`);
            } else {
               if (bufferedChar != null) {
                  if (previousUpperCharsCount > 1 && Character.isLetter(`element$iv`)) {
                     `$this$convertCamelCase_u24lambda_u241`.append(delimiter);
                  }

                  `$this$convertCamelCase_u24lambda_u241`.append(bufferedChar.charValue());
                  previousUpperCharsCount = 0;
                  bufferedChar = null;
               }

               `$this$convertCamelCase_u24lambda_u241`.append(`element$iv`);
            }
         }

         if (bufferedChar != null) {
            `$this$convertCamelCase_u24lambda_u241`.append(bufferedChar.charValue());
         }

         return var4.toString();
      }
   }
}
