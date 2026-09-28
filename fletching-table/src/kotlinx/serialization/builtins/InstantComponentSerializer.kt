package kotlinx.serialization.builtins

import kotlin.jvm.internal.SourceDebugExtension
import kotlin.time.ExperimentalTime
import kotlin.time.Instant
import kotlinx.serialization.KSerializer
import kotlinx.serialization.MissingFieldException
import kotlinx.serialization.SerializationException
import kotlinx.serialization.descriptors.ClassSerialDescriptorBuilder
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptorsKt
import kotlinx.serialization.encoding.CompositeDecoder
import kotlinx.serialization.encoding.CompositeEncoder
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.internal.LongSerializer

@ExperimentalTime
@SourceDebugExtension(["SMAP\nInstantComponentSerializer.kt\nKotlin\n*S Kotlin\n*F\n+ 1 InstantComponentSerializer.kt\nkotlinx/serialization/builtins/InstantComponentSerializer\n+ 2 Decoding.kt\nkotlinx/serialization/encoding/DecodingKt\n+ 3 Encoding.kt\nkotlinx/serialization/encoding/EncodingKt\n+ 4 SerialDescriptors.kt\nkotlinx/serialization/descriptors/SerialDescriptorsKt\n*L\n1#1,62:1\n571#2,4:63\n476#3,4:67\n347#4,8:71\n347#4,8:79\n*S KotlinDebug\n*F\n+ 1 InstantComponentSerializer.kt\nkotlinx/serialization/builtins/InstantComponentSerializer\n*L\n29#1:63,4\n53#1:67,4\n23#1:71,8\n24#1:79,8\n*E\n"])
public object InstantComponentSerializer : KSerializer<Instant> {
   public open val descriptor: SerialDescriptor =
      SerialDescriptorsKt.buildClassSerialDescriptor(
         "kotlinx.serialization.InstantComponentSerializer", new SerialDescriptor[0], InstantComponentSerializer::descriptor$lambda$0
      )

   public open fun deserialize(decoder: Decoder): Instant {
      val `descriptor$iv`: SerialDescriptor = this.getDescriptor();
      val `composite$iv`: CompositeDecoder = decoder.beginStructure(`descriptor$iv`);
      val `result$iv`: CompositeDecoder = `composite$iv`;
      var epochSecondsNotSeen: Boolean = true;
      var epochSeconds: Long = 0L;
      var nanosecondsOfSecond: Int = 0;

      while (true) {
         val index: Int = `result$iv`.decodeElementIndex(INSTANCE.getDescriptor());
         switch (index) {
            case -1:
               if (epochSecondsNotSeen) {
                  throw new MissingFieldException("epochSeconds", INSTANCE.getDescriptor().getSerialName());
               }

               val var13: Any = Instant.Companion.fromEpochSeconds(epochSeconds, nanosecondsOfSecond);
               `composite$iv`.endStructure(`descriptor$iv`);
               return (Instant)var13;
            case 0:
               epochSecondsNotSeen = false;
               epochSeconds = `result$iv`.decodeLongElement(INSTANCE.getDescriptor(), 0);
               break;
            case 1:
               nanosecondsOfSecond = `result$iv`.decodeIntElement(INSTANCE.getDescriptor(), 1);
               break;
            default:
               throw new SerializationException("Unexpected index: $index");
         }
      }
   }

   public open fun serialize(encoder: Encoder, value: Instant) {
      val `descriptor$iv`: SerialDescriptor = this.getDescriptor();
      val `composite$iv`: CompositeEncoder = encoder.beginStructure(`descriptor$iv`);
      `composite$iv`.encodeLongElement(INSTANCE.getDescriptor(), 0, value.getEpochSeconds());
      if (value.getNanosecondsOfSecond() != 0 || `composite$iv`.shouldEncodeElementDefault(INSTANCE.getDescriptor(), 1)) {
         `composite$iv`.encodeIntElement(INSTANCE.getDescriptor(), 1, value.getNanosecondsOfSecond());
      }

      `composite$iv`.endStructure(`descriptor$iv`);
   }

   @JvmStatic
   fun ClassSerialDescriptorBuilder.`descriptor$lambda$0`(): Unit {
      `$this$buildClassSerialDescriptor`.element("epochSeconds", LongSerializer.INSTANCE.getDescriptor(), CollectionsKt.emptyList(), false);
      `$this$buildClassSerialDescriptor`.element("nanosecondsOfSecond", LongSerializer.INSTANCE.getDescriptor(), CollectionsKt.emptyList(), true);
      return Unit.INSTANCE;
   }
}
