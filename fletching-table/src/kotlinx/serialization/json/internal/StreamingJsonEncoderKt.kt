package kotlinx.serialization.json.internal

import kotlinx.serialization.builtins.BuiltinSerializersKt
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.json.JsonElementKt

private final val unsignedNumberDescriptors: Set<SerialDescriptor> =
   SetsKt.setOf(
      new SerialDescriptor[]{
         BuiltinSerializersKt.serializer(UInt.Companion).getDescriptor(),
         BuiltinSerializersKt.serializer(ULong.Companion).getDescriptor(),
         BuiltinSerializersKt.serializer(UByte.Companion).getDescriptor(),
         BuiltinSerializersKt.serializer(UShort.Companion).getDescriptor()
      }
   )

internal final val isUnsignedNumber: Boolean
   internal final get() {
      return `$this$isUnsignedNumber`.isInline() && unsignedNumberDescriptors.contains(`$this$isUnsignedNumber`);
   }


internal final val isUnquotedLiteral: Boolean
   internal final get() {
      return `$this$isUnquotedLiteral`.isInline() && `$this$isUnquotedLiteral` == JsonElementKt.getJsonUnquotedLiteralDescriptor();
   }

