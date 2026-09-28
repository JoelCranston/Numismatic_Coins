package kotlinx.serialization.internal

import kotlin.jvm.internal.Ref
import kotlin.jvm.internal.SourceDebugExtension
import kotlin.reflect.KClass
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.PolymorphicSerializerKt
import kotlinx.serialization.SerializationException
import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.CompositeDecoder
import kotlinx.serialization.encoding.CompositeEncoder
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@InternalSerializationApi
@SourceDebugExtension(["SMAP\nAbstractPolymorphicSerializer.kt\nKotlin\n*S Kotlin\n*F\n+ 1 AbstractPolymorphicSerializer.kt\nkotlinx/serialization/internal/AbstractPolymorphicSerializer\n+ 2 Encoding.kt\nkotlinx/serialization/encoding/EncodingKt\n+ 3 Platform.common.kt\nkotlinx/serialization/internal/Platform_commonKt\n+ 4 Decoding.kt\nkotlinx/serialization/encoding/DecodingKt\n+ 5 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,115:1\n476#2,2:116\n478#2,2:119\n82#3:118\n571#4,2:121\n573#4,2:124\n1#5:123\n*S KotlinDebug\n*F\n+ 1 AbstractPolymorphicSerializer.kt\nkotlinx/serialization/internal/AbstractPolymorphicSerializer\n*L\n33#1:116,2\n33#1:119,2\n35#1:118\n39#1:121,2\n39#1:124,2\n*E\n"])
public abstract class AbstractPolymorphicSerializer<T> : KSerializer<T> {
   public abstract val baseClass: KClass<Any>

   public override fun serialize(encoder: Encoder, value: Any) {
      val actualSerializer: SerializationStrategy = PolymorphicSerializerKt.findPolymorphicSerializer(this, encoder, (T)value);
      val `descriptor$iv`: SerialDescriptor = this.getDescriptor();
      val `composite$iv`: CompositeEncoder = encoder.beginStructure(`descriptor$iv`);
      `composite$iv`.encodeStringElement(this.getDescriptor(), 0, actualSerializer.getDescriptor().getSerialName());
      val var10001: SerialDescriptor = this.getDescriptor();
      `composite$iv`.encodeSerializableElement(var10001, 1, actualSerializer, value);
      `composite$iv`.endStructure(`descriptor$iv`);
   }

   public override fun deserialize(decoder: Decoder): Any {
      val `descriptor$iv`: SerialDescriptor = this.getDescriptor();
      val `composite$iv`: CompositeDecoder = decoder.beginStructure(`descriptor$iv`);
      val `result$iv`: CompositeDecoder = `composite$iv`;
      val klassName: Ref.ObjectRef = new Ref.ObjectRef();
      var value: Any = null;
      var var18: Any;
      if (`composite$iv`.decodeSequentially()) {
         var18 = (SerializationException)access$decodeSequentially(this, `composite$iv`);
      } else {
         label29:
         while (true) {
            val index: Int = `result$iv`.decodeElementIndex(this.getDescriptor());
            switch (index) {
               case -1:
                  var18 = (SerializationException)value;
                  if (value == null) {
                     throw new IllegalArgumentException(("Polymorphic value has not been read for class ${klassName.element as java.lang.String}").toString());
                  }

                  break label29;
               case 0:
                  klassName.element = (T)`result$iv`.decodeStringElement(this.getDescriptor(), index);
                  break;
               case 1:
                  if (klassName.element == null) {
                     throw new IllegalArgumentException("Cannot read polymorphic value before its type token".toString());
                  }

                  klassName.element = klassName.element;
                  value = CompositeDecoder.decodeSerializableElement$default(
                     `result$iv`,
                     this.getDescriptor(),
                     index,
                     PolymorphicSerializerKt.findPolymorphicSerializer(this, `result$iv`, klassName.element as java.lang.String),
                     null,
                     8,
                     null
                  );
                  break;
               default:
                  var18 = new SerializationException;
                  val var10002: StringBuilder = new StringBuilder().append("Invalid index in polymorphic deserialization of ");
                  var var10003: java.lang.String = klassName.element as java.lang.String;
                  if (klassName.element as java.lang.String == null) {
                     var10003 = "unknown class";
                  }

                  var18./* $VF: Unable to resugar constructor */<init>(
                     var10002.append(var10003).append("\n Expected 0, 1 or DECODE_DONE(-1), but found ").append(index).toString()
                  );
                  throw var18;
            }
         }
      }

      `composite$iv`.endStructure(`descriptor$iv`);
      return (T)var18;
   }

   private fun decodeSequentially(compositeDecoder: CompositeDecoder): Any {
      return (T)CompositeDecoder.decodeSerializableElement$default(
         compositeDecoder,
         this.getDescriptor(),
         1,
         PolymorphicSerializerKt.findPolymorphicSerializer(this, compositeDecoder, compositeDecoder.decodeStringElement(this.getDescriptor(), 0)),
         null,
         8,
         null
      );
   }

   @InternalSerializationApi
   public open fun findPolymorphicSerializerOrNull(decoder: CompositeDecoder, klassName: String?): DeserializationStrategy<Any>? {
      return decoder.getSerializersModule().getPolymorphic(this.getBaseClass(), klassName);
   }

   @InternalSerializationApi
   public open fun findPolymorphicSerializerOrNull(encoder: Encoder, value: Any): SerializationStrategy<Any>? {
      return encoder.getSerializersModule().getPolymorphic(this.getBaseClass(), (T)value);
   }
}
