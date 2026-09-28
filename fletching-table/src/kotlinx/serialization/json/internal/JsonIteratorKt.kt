package kotlinx.serialization.json.internal

import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.json.DecodeSequenceMode
import kotlinx.serialization.json.Json

internal fun <T> JsonIterator(mode: DecodeSequenceMode, json: Json, lexer: ReaderJsonLexer, deserializer: DeserializationStrategy<T>): Iterator<T> {
   var var10000: java.util.Iterator;
   switch (JsonIteratorKt.WhenMappings.$EnumSwitchMapping$0[determineFormat(lexer, mode).ordinal()]) {
      case 1:
         var10000 = new JsonIteratorWsSeparated(json, lexer, deserializer);
         break;
      case 2:
         var10000 = new JsonIteratorArrayWrapped(json, lexer, deserializer);
         break;
      case 3:
         throw new IllegalStateException("AbstractJsonLexer.determineFormat must be called beforehand.".toString());
      default:
         throw new NoWhenBranchMatchedException();
   }

   return var10000;
}

private fun AbstractJsonLexer.determineFormat(suggested: DecodeSequenceMode): DecodeSequenceMode {
   var var10000: DecodeSequenceMode;
   switch (JsonIteratorKt.WhenMappings.$EnumSwitchMapping$0[suggested.ordinal()]) {
      case 1:
         var10000 = DecodeSequenceMode.WHITESPACE_SEPARATED;
         break;
      case 2:
         if (!tryConsumeStartArray(`$this$determineFormat`)) {
            val `expected$iv`: java.lang.String = AbstractJsonLexerKt.tokenDescription((byte)8);
            val `position$iv`: Int = `$this$determineFormat`.currentPosition - 1;
            AbstractJsonLexer.fail$default(
               `$this$determineFormat`,
               "Expected $`expected$iv`, but had '${if (`$this$determineFormat`.currentPosition
                        != AbstractJsonLexer.access$getSource(`$this$determineFormat`).length()
                     && `position$iv` >= 0)
                  java.lang.String.valueOf(AbstractJsonLexer.access$getSource(`$this$determineFormat`).charAt(`position$iv`))
                  else
                  "EOF"}' instead",
               `position$iv`,
               null,
               4,
               null
            );
            throw new KotlinNothingValueException();
         }

         var10000 = DecodeSequenceMode.ARRAY_WRAPPED;
         break;
      case 3:
         var10000 = if (tryConsumeStartArray(`$this$determineFormat`)) DecodeSequenceMode.ARRAY_WRAPPED else DecodeSequenceMode.WHITESPACE_SEPARATED;
         break;
      default:
         throw new NoWhenBranchMatchedException();
   }

   return var10000;
}

private fun AbstractJsonLexer.tryConsumeStartArray(): Boolean {
   if (`$this$tryConsumeStartArray`.peekNextToken() == 8) {
      `$this$tryConsumeStartArray`.consumeNextToken((byte)8);
      return true;
   } else {
      return false;
   }
}
// $VF: Class flags could not be determined
@JvmSynthetic
internal class WhenMappings {
   @JvmStatic
   fun {
      val var0: IntArray = new int[DecodeSequenceMode.values().length];

      try {
         var0[DecodeSequenceMode.WHITESPACE_SEPARATED.ordinal()] = 1;
      } catch (var4: NoSuchFieldError) {
      }

      try {
         var0[DecodeSequenceMode.ARRAY_WRAPPED.ordinal()] = 2;
      } catch (var3: NoSuchFieldError) {
      }

      try {
         var0[DecodeSequenceMode.AUTO_DETECT.ordinal()] = 3;
      } catch (var2: NoSuchFieldError) {
      }

      $EnumSwitchMapping$0 = var0;
   }
}
