package dev.kikugie.commons.serialization

import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptorsKt
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@SourceDebugExtension(["SMAP\nRangeSerialization.kt\nKotlin\n*S Kotlin\n*F\n+ 1 RangeSerialization.kt\ndev/kikugie/commons/serialization/IntRangeSerializer\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,48:1\n1#2:49\n*E\n"])
public object IntRangeSerializer : KSerializer<IntRange> {
   public open val descriptor: SerialDescriptor = SerialDescriptorsKt.PrimitiveSerialDescriptor("kotlin.ranges.IntRange", PrimitiveKind.STRING.INSTANCE)

   public open fun serialize(encoder: Encoder, value: IntRange) {
      encoder.encodeString("${value.getFirst()}..${value.getLast()}");
   }

   public open fun deserialize(decoder: Decoder): IntRange {
      val parts: java.util.List = StringsKt.split$default(decoder.decodeString(), new java.lang.String[]{".."}, false, 2, 2, null);
      if (parts.size() != 2) {
         throw new IllegalStateException("Missing range delimiter".toString());
      } else {
         val var5: java.lang.String = parts.get(0) as java.lang.String;
         val end: java.lang.String = parts.get(1) as java.lang.String;
         return if (StringsKt.startsWith$default(end, '<', false, 2, null))
            RangesKt.until(Integer.parseInt(var5), Integer.parseInt(StringsKt.drop(end, 1)))
            else
            new IntRange(Integer.parseInt(var5), Integer.parseInt(end));
      }
   }
}
