@file:SourceDebugExtension(["SMAP\nJsonElement.kt\nKotlin\n*S Kotlin\n*F\n+ 1 JsonElement.kt\nkotlinx/serialization/json/JsonElementKt\n*L\n1#1,350:1\n337#1,4:351\n329#1,4:355\n337#1,4:359\n329#1,4:363\n*S KotlinDebug\n*F\n+ 1 JsonElement.kt\nkotlinx/serialization/json/JsonElementKt\n*L\n259#1:351,4\n269#1:355,4\n278#1:359,4\n284#1:363,4\n*E\n"])

package kotlinx.serialization.json

import kotlin.jvm.internal.SourceDebugExtension
import kotlin.jvm.internal.StringCompanionObject
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.builtins.BuiltinSerializersKt
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.internal.InlineClassDescriptorKt
import kotlinx.serialization.json.internal.JsonDecodingException
import kotlinx.serialization.json.internal.JsonEncodingException
import kotlinx.serialization.json.internal.StringJsonLexer
import kotlinx.serialization.json.internal.StringOpsKt
import kotlinx.serialization.json.internal.SuppressAnimalSniffer

internal final val jsonUnquotedLiteralDescriptor: SerialDescriptor =
   InlineClassDescriptorKt.InlinePrimitiveDescriptor(
      "kotlinx.serialization.json.JsonUnquotedLiteral", BuiltinSerializersKt.serializer(StringCompanionObject.INSTANCE)
   )

public final val jsonPrimitive: JsonPrimitive
   public final get() {
      val var10000: JsonPrimitive = `$this$jsonPrimitive` as? JsonPrimitive;
      if ((`$this$jsonPrimitive` as? JsonPrimitive) == null) {
         error(`$this$jsonPrimitive`, "JsonPrimitive");
         throw new KotlinNothingValueException();
      } else {
         return var10000;
      }
   }


public final val jsonObject: JsonObject
   public final get() {
      val var10000: JsonObject = `$this$jsonObject` as? JsonObject;
      if ((`$this$jsonObject` as? JsonObject) == null) {
         error(`$this$jsonObject`, "JsonObject");
         throw new KotlinNothingValueException();
      } else {
         return var10000;
      }
   }


public final val jsonArray: JsonArray
   public final get() {
      val var10000: JsonArray = `$this$jsonArray` as? JsonArray;
      if ((`$this$jsonArray` as? JsonArray) == null) {
         error(`$this$jsonArray`, "JsonArray");
         throw new KotlinNothingValueException();
      } else {
         return var10000;
      }
   }


public final val jsonNull: JsonNull
   public final get() {
      val var10000: JsonNull = `$this$jsonNull` as? JsonNull;
      if ((`$this$jsonNull` as? JsonNull) == null) {
         error(`$this$jsonNull`, "JsonNull");
         throw new KotlinNothingValueException();
      } else {
         return var10000;
      }
   }


public final val int: Int
   public final get() {
      var var5: Long;
      try {
         var5 = parseLongImpl(`$this$int`);
      } catch (var7: JsonDecodingException) {
         throw new NumberFormatException(var7.getMessage());
      }

      if (-2147483648L > var5 || var5 > 2147483647L) {
         throw new NumberFormatException("${`$this$int`.getContent()} is not an Int");
      } else {
         return (int)var5;
      }
   }


public final val intOrNull: Int?
   public final get() {
      var var5: java.lang.Long;
      try {
         var5 = parseLongImpl(`$this$intOrNull`);
      } catch (var6: JsonDecodingException) {
         var5 = null;
      }

      if (var5 != null) {
         val result: Long = var5;
         return if (-2147483648L > result || result > 2147483647L) null else (int)result;
      } else {
         return null;
      }
   }


public final val long: Long
   public final get() {
      var var3: Long;
      try {
         var3 = parseLongImpl(`$this$long`);
      } catch (var5: JsonDecodingException) {
         throw new NumberFormatException(var5.getMessage());
      }

      return var3;
   }


public final val longOrNull: Long?
   public final get() {
      var var3: java.lang.Long;
      try {
         var3 = parseLongImpl(`$this$longOrNull`);
      } catch (var4: JsonDecodingException) {
         var3 = null;
      }

      return var3;
   }


public final val double: Double
   public final get() {
      return java.lang.Double.parseDouble(`$this$double`.getContent());
   }


public final val doubleOrNull: Double?
   public final get() {
      return StringsKt.toDoubleOrNull(`$this$doubleOrNull`.getContent());
   }


public final val float: Float
   public final get() {
      return java.lang.Float.parseFloat(`$this$float`.getContent());
   }


public final val floatOrNull: Float?
   public final get() {
      return StringsKt.toFloatOrNull(`$this$floatOrNull`.getContent());
   }


public final val boolean: Boolean
   public final get() {
      val var10000: java.lang.Boolean = StringOpsKt.toBooleanStrictOrNull(`$this$boolean`.getContent());
      if (var10000 != null) {
         return var10000;
      } else {
         throw new IllegalStateException("$`$this$boolean` does not represent a Boolean");
      }
   }


public final val booleanOrNull: Boolean?
   public final get() {
      return StringOpsKt.toBooleanStrictOrNull(`$this$booleanOrNull`.getContent());
   }


public final val contentOrNull: String?
   public final get() {
      return if (`$this$contentOrNull` is JsonNull) null else `$this$contentOrNull`.getContent();
   }


public fun JsonPrimitive(value: Boolean?): JsonPrimitive {
   return if (value == null) JsonNull.INSTANCE else new JsonLiteral(value, false, null, 4, null);
}

public fun JsonPrimitive(value: Number?): JsonPrimitive {
   return if (value == null) JsonNull.INSTANCE else new JsonLiteral(value, false, null, 4, null);
}

@ExperimentalSerializationApi
public fun JsonPrimitive(value: UByte): JsonPrimitive {
   return JsonPrimitive-VKZWuLQ(ULong.constructor-impl((long)value and 255L));
}

@ExperimentalSerializationApi
public fun JsonPrimitive(value: UShort): JsonPrimitive {
   return JsonPrimitive-VKZWuLQ(ULong.constructor-impl((long)value and 65535L));
}

@ExperimentalSerializationApi
public fun JsonPrimitive(value: UInt): JsonPrimitive {
   return JsonPrimitive-VKZWuLQ(ULong.constructor-impl((long)value and 4294967295L));
}

@ExperimentalSerializationApi
@SuppressAnimalSniffer
public fun JsonPrimitive(value: ULong): JsonPrimitive {
   return JsonUnquotedLiteral(java.lang.Long.toUnsignedString(value));
}

public fun JsonPrimitive(value: String?): JsonPrimitive {
   return if (value == null) JsonNull.INSTANCE else new JsonLiteral(value, true, null, 4, null);
}

@ExperimentalSerializationApi
public fun JsonPrimitive(value: Nothing?): JsonNull {
   return JsonNull.INSTANCE;
}

@ExperimentalSerializationApi
public fun JsonUnquotedLiteral(value: String?): JsonPrimitive {
   val var10000: JsonPrimitive;
   if (value == null) {
      var10000 = JsonNull.INSTANCE;
   } else {
      if (value == JsonNull.INSTANCE.getContent()) {
         throw new JsonEncodingException(
            "Creating a literal unquoted value of 'null' is forbidden. If you want to create JSON null literal, use JsonNull object, otherwise, use JsonPrimitive"
         );
      }

      var10000 = new JsonLiteral(value, false, jsonUnquotedLiteralDescriptor);
   }

   return var10000;
}

private fun JsonElement.error(element: String): Nothing {
   throw new IllegalArgumentException("Element ${`$this$error`.getClass()::class} is not a $element");
}

private inline fun <T> exceptionToNull(f: () -> T): T? {
   var var2: Any;
   try {
      var2 = f.invoke();
   } catch (var4: JsonDecodingException) {
      var2 = null;
   }

   return (T)var2;
}

private inline fun <T> exceptionToNumberFormatException(f: () -> T): T {
   try {
      return (T)f.invoke();
   } catch (var4: JsonDecodingException) {
      throw new NumberFormatException(var4.getMessage());
   }
}

@PublishedApi
internal fun unexpectedJson(key: String, expected: String): Nothing {
   throw new IllegalArgumentException("Element $key is not a $expected");
}

internal fun JsonPrimitive.parseLongImpl(): Long {
   return new StringJsonLexer(`$this$parseLongImpl`.getContent()).consumeNumericLiteralFully();
}
