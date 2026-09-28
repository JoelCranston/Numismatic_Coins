package kotlinx.serialization.json.internal

import java.util.ArrayList
import java.util.LinkedHashMap
import kotlin.coroutines.Continuation
import kotlin.coroutines.intrinsics.IntrinsicsKt
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonConfiguration
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonLiteral
import kotlinx.serialization.json.JsonNull
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.internal.JsonTreeReader.readDeepRecursive.1
import kotlinx.serialization.json.internal.JsonTreeReader.readObject.2

@SourceDebugExtension(["SMAP\nJsonTreeReader.kt\nKotlin\n*S Kotlin\n*F\n+ 1 JsonTreeReader.kt\nkotlinx/serialization/json/internal/JsonTreeReader\n+ 2 AbstractJsonLexer.kt\nkotlinx/serialization/json/internal/AbstractJsonLexer\n*L\n1#1,121:1\n27#1,25:122\n27#1,25:147\n517#2,3:172\n*S KotlinDebug\n*F\n+ 1 JsonTreeReader.kt\nkotlinx/serialization/json/internal/JsonTreeReader\n*L\n19#1:122,25\n24#1:147,25\n64#1:172,3\n*E\n"])
internal class JsonTreeReader(configuration: JsonConfiguration, lexer: AbstractJsonLexer) {
   private final val lexer: AbstractJsonLexer
   private final val isLenient: Boolean
   private final val trailingCommaAllowed: Boolean
   private final var stackDepth: Int

   init {
      this.lexer = lexer;
      this.isLenient = configuration.isLenient();
      this.trailingCommaAllowed = configuration.getAllowTrailingComma();
   }

   private fun readObject(): JsonElement {
      val `this_$iv`: JsonTreeReader = this;
      var `lastToken$iv`: Byte = this.lexer.consumeNextToken((byte)6);
      if (this.lexer.peekNextToken() == 4) {
         AbstractJsonLexer.fail$default(this.lexer, "Unexpected leading comma", 0, null, 6, null);
         throw new KotlinNothingValueException();
      } else {
         val `result$iv`: LinkedHashMap = new LinkedHashMap();

         while (this_$iv.lexer.canConsumeValue()) {
            val `key$iv`: java.lang.String = if (`this_$iv`.isLenient) `this_$iv`.lexer.consumeStringLenient() else `this_$iv`.lexer.consumeString();
            `this_$iv`.lexer.consumeNextToken((byte)5);
            `result$iv`.put(`key$iv`, this.read());
            `lastToken$iv` = `this_$iv`.lexer.consumeNextToken();
            if (`lastToken$iv` != 4) {
               if (`lastToken$iv` != 7) {
                  AbstractJsonLexer.fail$default(`this_$iv`.lexer, "Expected end of the object or comma", 0, null, 6, null);
                  throw new KotlinNothingValueException();
               }
               break;
            }
         }

         if (`lastToken$iv` == 6) {
            `this_$iv`.lexer.consumeNextToken((byte)7);
         } else if (`lastToken$iv` == 4) {
            if (!`this_$iv`.trailingCommaAllowed) {
               JsonExceptionsKt.invalidTrailingComma$default(`this_$iv`.lexer, null, 1, null);
               throw new KotlinNothingValueException();
            }

            `this_$iv`.lexer.consumeNextToken((byte)7);
         }

         return new JsonObject(`result$iv`);
      }
   }

   private suspend fun DeepRecursiveScope<Unit, JsonElement>.readObject(): JsonElement {
      var `$continuation`: Continuation;
      label63: {
         if (`$completion` is 2) {
            `$continuation` = `$completion` as 2;
            if (((`$completion` as 2).label and Integer.MIN_VALUE) != 0) {
               `$continuation`.label -= Integer.MIN_VALUE;
               break label63;
            }
         }

         `$continuation` = new 2(this, `$completion`);
      }

      var `this_$iv`: JsonTreeReader;
      var `result$iv`: LinkedHashMap;
      var var13: Byte;
      label57: {
         val `$result`: Any = `$continuation`.result;
         val var12: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
         var `$i$f$readObjectImpl`: Int;
         switch ($continuation.label) {
            case 0:
               ResultKt.throwOnFailure(`$result`);
               `this_$iv` = this;
               `$i$f$readObjectImpl` = 0;
               var13 = this.lexer.consumeNextToken((byte)6);
               if (this.lexer.peekNextToken() == 4) {
                  AbstractJsonLexer.fail$default(this.lexer, "Unexpected leading comma", 0, null, 6, null);
                  throw new KotlinNothingValueException();
               }

               `result$iv` = new LinkedHashMap();
               break;
            case 1:
               val `element$iv`: Int = `$continuation`.I$1;
               var13 = `$continuation`.B$0;
               `$i$f$readObjectImpl` = `$continuation`.I$0;
               val `key$iv`: java.lang.String = `$continuation`.L$3 as java.lang.String;
               `result$iv` = `$continuation`.L$2 as LinkedHashMap;
               `this_$iv` = `$continuation`.L$1 as JsonTreeReader;
               `$this$readObject` = `$continuation`.L$0 as DeepRecursiveScope;
               ResultKt.throwOnFailure(`$result`);
               `result$iv`.put(`key$iv`, `$result` as JsonElement);
               var13 = `this_$iv`.lexer.consumeNextToken();
               if (var13 != 4) {
                  if (var13 != 7) {
                     AbstractJsonLexer.fail$default(`this_$iv`.lexer, "Expected end of the object or comma", 0, null, 6, null);
                     throw new KotlinNothingValueException();
                  }
                  break label57;
               }
               break;
            default:
               throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
         }

         while (this_$iv.lexer.canConsumeValue()) {
            val var14: java.lang.String = if (`this_$iv`.isLenient) `this_$iv`.lexer.consumeStringLenient() else `this_$iv`.lexer.consumeString();
            `this_$iv`.lexer.consumeNextToken((byte)5);
            val var10001: Unit = Unit.INSTANCE;
            `$continuation`.L$0 = `$this$readObject`;
            `$continuation`.L$1 = `this_$iv`;
            `$continuation`.L$2 = `result$iv`;
            `$continuation`.L$3 = var14;
            `$continuation`.I$0 = `$i$f$readObjectImpl`;
            `$continuation`.B$0 = var13;
            `$continuation`.I$1 = 0;
            `$continuation`.label = 1;
            val var10000: Any = `$this$readObject`.callRecursive(var10001, `$continuation`);
            if (var10000 === var12) {
               return var12;
            }

            `result$iv`.put(var14, var10000 as JsonElement);
            var13 = `this_$iv`.lexer.consumeNextToken();
            if (var13 != 4) {
               if (var13 != 7) {
                  AbstractJsonLexer.fail$default(`this_$iv`.lexer, "Expected end of the object or comma", 0, null, 6, null);
                  throw new KotlinNothingValueException();
               }
               break;
            }
         }
      }

      if (var13 == 6) {
         `this_$iv`.lexer.consumeNextToken((byte)7);
      } else if (var13 == 4) {
         if (!`this_$iv`.trailingCommaAllowed) {
            JsonExceptionsKt.invalidTrailingComma$default(`this_$iv`.lexer, null, 1, null);
            throw new KotlinNothingValueException();
         }

         `this_$iv`.lexer.consumeNextToken((byte)7);
      }

      return new JsonObject(`result$iv`);
   }

   private inline fun readObjectImpl(reader: () -> JsonElement): JsonObject {
      var lastToken: Byte = this.lexer.consumeNextToken((byte)6);
      if (this.lexer.peekNextToken() == 4) {
         AbstractJsonLexer.fail$default(this.lexer, "Unexpected leading comma", 0, null, 6, null);
         throw new KotlinNothingValueException();
      } else {
         val result: LinkedHashMap = new LinkedHashMap();

         while (this.lexer.canConsumeValue()) {
            val key: java.lang.String = if (this.isLenient) this.lexer.consumeStringLenient() else this.lexer.consumeString();
            this.lexer.consumeNextToken((byte)5);
            result.put(key, reader.invoke() as JsonElement);
            lastToken = this.lexer.consumeNextToken();
            if (lastToken != 4) {
               if (lastToken != 7) {
                  AbstractJsonLexer.fail$default(this.lexer, "Expected end of the object or comma", 0, null, 6, null);
                  throw new KotlinNothingValueException();
               }
               break;
            }
         }

         if (lastToken == 6) {
            this.lexer.consumeNextToken((byte)7);
         } else if (lastToken == 4) {
            if (!this.trailingCommaAllowed) {
               JsonExceptionsKt.invalidTrailingComma$default(this.lexer, null, 1, null);
               throw new KotlinNothingValueException();
            }

            this.lexer.consumeNextToken((byte)7);
         }

         return new JsonObject(result);
      }
   }

   private fun readArray(): JsonElement {
      var lastToken: Byte = this.lexer.consumeNextToken();
      if (this.lexer.peekNextToken() == 4) {
         AbstractJsonLexer.fail$default(this.lexer, "Unexpected leading comma", 0, null, 6, null);
         throw new KotlinNothingValueException();
      } else {
         val result: ArrayList = new ArrayList();

         while (this.lexer.canConsumeValue()) {
            result.add(this.read());
            lastToken = this.lexer.consumeNextToken();
            if (lastToken != 4 && lastToken != 9) {
               AbstractJsonLexer.fail$default(this.lexer, "Expected end of the array or comma", this.lexer.currentPosition, null, 4, null);
               throw new KotlinNothingValueException();
            }
         }

         if (lastToken == 8) {
            this.lexer.consumeNextToken((byte)9);
         } else if (lastToken == 4) {
            if (!this.trailingCommaAllowed) {
               JsonExceptionsKt.invalidTrailingComma(this.lexer, "array");
               throw new KotlinNothingValueException();
            }

            this.lexer.consumeNextToken((byte)9);
         }

         return new JsonArray(result);
      }
   }

   private fun readValue(isString: Boolean): JsonPrimitive {
      val string: java.lang.String = if (!this.isLenient && isString) this.lexer.consumeString() else this.lexer.consumeStringLenient();
      return if (!isString && string == "null") JsonNull.INSTANCE else new JsonLiteral(string, isString, null, 4, null);
   }

   public fun read(): JsonElement {
      val token: Byte = this.lexer.peekNextToken();
      val var10000: JsonElement;
      if (token == 1) {
         var10000 = this.readValue(true);
      } else if (token == 0) {
         var10000 = this.readValue(false);
      } else if (token == 6) {
         this.stackDepth++;
         val result: JsonElement = if (this.stackDepth == 200) this.readDeepRecursive() else this.readObject();
         this.stackDepth += -1;
         var10000 = result;
      } else {
         if (token != 8) {
            AbstractJsonLexer.fail$default(
               this.lexer, "Cannot read Json element because of unexpected ${AbstractJsonLexerKt.tokenDescription(token)}", 0, null, 6, null
            );
            throw new KotlinNothingValueException();
         }

         var10000 = this.readArray();
      }

      return var10000;
   }

   private fun readDeepRecursive(): JsonElement {
      return DeepRecursiveKt.invoke(new DeepRecursiveFunction<>(new 1(this, null)), Unit.INSTANCE);
   }
}
