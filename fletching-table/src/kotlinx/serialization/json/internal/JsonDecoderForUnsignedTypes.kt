package kotlinx.serialization.json.internal

import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.AbstractDecoder
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule

@SourceDebugExtension(["SMAP\nStreamingJsonDecoder.kt\nKotlin\n*S Kotlin\n*F\n+ 1 StreamingJsonDecoder.kt\nkotlinx/serialization/json/internal/JsonDecoderForUnsignedTypes\n+ 2 StreamingJsonDecoder.kt\nkotlinx/serialization/json/internal/StreamingJsonDecoderKt\n*L\n1#1,392:1\n385#2,5:393\n385#2,5:398\n385#2,5:403\n385#2,5:408\n*S KotlinDebug\n*F\n+ 1 StreamingJsonDecoder.kt\nkotlinx/serialization/json/internal/JsonDecoderForUnsignedTypes\n*L\n378#1:393,5\n379#1:398,5\n380#1:403,5\n381#1:408,5\n*E\n"])
internal class JsonDecoderForUnsignedTypes(lexer: AbstractJsonLexer, json: Json) : AbstractDecoder {
   private final val lexer: AbstractJsonLexer
   public open val serializersModule: SerializersModule

   init {
      this.lexer = lexer;
      this.serializersModule = json.getSerializersModule();
   }

   public override fun decodeElementIndex(descriptor: SerialDescriptor): Int {
      throw new IllegalStateException("unsupported".toString());
   }

   public override fun decodeInt(): Int {
      val `$this$parseString$iv`: AbstractJsonLexer = this.lexer;
      val `input$iv`: java.lang.String = this.lexer.consumeStringLenient();

      try {
         return UStringsKt.toUInt(`input$iv`);
      } catch (var7: IllegalArgumentException) {
         AbstractJsonLexer.fail$default(`$this$parseString$iv`, "Failed to parse type 'UInt' for input '$`input$iv`'", 0, null, 6, null);
         throw new KotlinNothingValueException();
      }
   }

   public override fun decodeLong(): Long {
      val `$this$parseString$iv`: AbstractJsonLexer = this.lexer;
      val `input$iv`: java.lang.String = this.lexer.consumeStringLenient();

      try {
         return UStringsKt.toULong(`input$iv`);
      } catch (var7: IllegalArgumentException) {
         AbstractJsonLexer.fail$default(`$this$parseString$iv`, "Failed to parse type 'ULong' for input '$`input$iv`'", 0, null, 6, null);
         throw new KotlinNothingValueException();
      }
   }

   public override fun decodeByte(): Byte {
      val `$this$parseString$iv`: AbstractJsonLexer = this.lexer;
      val `input$iv`: java.lang.String = this.lexer.consumeStringLenient();

      try {
         return UStringsKt.toUByte(`input$iv`);
      } catch (var7: IllegalArgumentException) {
         AbstractJsonLexer.fail$default(`$this$parseString$iv`, "Failed to parse type 'UByte' for input '$`input$iv`'", 0, null, 6, null);
         throw new KotlinNothingValueException();
      }
   }

   public override fun decodeShort(): Short {
      val `$this$parseString$iv`: AbstractJsonLexer = this.lexer;
      val `input$iv`: java.lang.String = this.lexer.consumeStringLenient();

      try {
         return UStringsKt.toUShort(`input$iv`);
      } catch (var7: IllegalArgumentException) {
         AbstractJsonLexer.fail$default(`$this$parseString$iv`, "Failed to parse type 'UShort' for input '$`input$iv`'", 0, null, 6, null);
         throw new KotlinNothingValueException();
      }
   }
}
