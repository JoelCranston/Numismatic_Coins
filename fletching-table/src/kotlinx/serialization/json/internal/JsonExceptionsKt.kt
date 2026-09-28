package kotlinx.serialization.json.internal

import kotlinx.serialization.descriptors.SerialDescriptor

internal fun JsonDecodingException(offset: Int, message: String): JsonDecodingException {
   return new JsonDecodingException(if (offset >= 0) "Unexpected JSON token at offset $offset: $message" else message);
}

internal fun JsonDecodingException(offset: Int, message: String, input: CharSequence): JsonDecodingException {
   return JsonDecodingException(offset, "$message\nJSON input: ${minify(input, offset)}");
}

internal fun InvalidFloatingPointEncoded(value: Number, output: String): JsonEncodingException {
   return new JsonEncodingException(
      "Unexpected special floating-point value $value. By default, non-finite floating point values are prohibited because they do not conform JSON specification. It is possible to deserialize them using 'JsonBuilder.allowSpecialFloatingPointValues = true'\nCurrent output: ${minify$default(
         output, 0, 1, null
      )}"
   );
}

internal fun AbstractJsonLexer.throwInvalidFloatingPointDecoded(result: Number): Nothing {
   AbstractJsonLexer.fail$default(
      `$this$throwInvalidFloatingPointDecoded`,
      "Unexpected special floating-point value $result. By default, non-finite floating point values are prohibited because they do not conform JSON specification",
      0,
      "It is possible to deserialize them using 'JsonBuilder.allowSpecialFloatingPointValues = true'",
      2,
      null
   );
   throw new KotlinNothingValueException();
}

internal fun AbstractJsonLexer.invalidTrailingComma(entity: String = "object"): Nothing {
   `$this$invalidTrailingComma`.fail(
      "Trailing comma before the end of JSON $entity",
      `$this$invalidTrailingComma`.currentPosition - 1,
      "Trailing commas are non-complaint JSON and not allowed by default. Use 'allowTrailingComma = true' in 'Json {}' builder to support them."
   );
   throw new KotlinNothingValueException();
}

@JvmSynthetic
fun `invalidTrailingComma$default`(var0: AbstractJsonLexer, var1: java.lang.String, var2: Int, var3: Any): Void {
   if ((var2 and 1) != 0) {
      var1 = "object";
   }

   return invalidTrailingComma(var0, var1);
}

internal fun InvalidKeyKindException(keyDescriptor: SerialDescriptor): JsonEncodingException {
   return new JsonEncodingException(
      "Value of type '${keyDescriptor.getSerialName()}' can't be used in JSON as a key in the map. It should have either primitive or enum kind, but its kind is '${keyDescriptor.getKind()}'.\nUse 'allowStructuredMapKeys = true' in 'Json {}' builder to convert such maps to [key1, value1, key2, value2,...] arrays."
   );
}

internal fun InvalidFloatingPointEncoded(value: Number, key: String, output: String): JsonEncodingException {
   return new JsonEncodingException(unexpectedFpErrorMessage(value, key, output));
}

internal fun InvalidFloatingPointDecoded(value: Number, key: String, output: String): JsonDecodingException {
   return JsonDecodingException(-1, unexpectedFpErrorMessage(value, key, output));
}

private fun unexpectedFpErrorMessage(value: Number, key: String, output: String): String {
   return "Unexpected special floating-point value $value with key $key. By default, non-finite floating point values are prohibited because they do not conform JSON specification. It is possible to deserialize them using 'JsonBuilder.allowSpecialFloatingPointValues = true'\nCurrent output: ${minify$default(
      output, 0, 1, null
   )}";
}

internal fun CharSequence.minify(offset: Int = -1): CharSequence {
   if (`$this$minify`.length() < 200) {
      return `$this$minify`;
   } else {
      label23:
      if (offset == -1) {
         val var9: Int = `$this$minify`.length() - 60;
         return if (var9 <= 0) `$this$minify` else ".....${`$this$minify`.subSequence(var9, `$this$minify`.length()).toString()}";
      } else {
         return "${if (offset - 30 <= 0) "" else "....."}${`$this$minify`.subSequence(
               RangesKt.coerceAtLeast(offset - 30, 0), RangesKt.coerceAtMost(offset + 30, `$this$minify`.length())
            )
            .toString()}${if (offset + 30 >= `$this$minify`.length()) "" else "....."}";
      }
   }
}

@JvmSynthetic
fun `minify$default`(var0: java.lang.CharSequence, var1: Int, var2: Int, var3: Any): java.lang.CharSequence {
   if ((var2 and 1) != 0) {
      var1 = -1;
   }

   return minify(var0, var1);
}
