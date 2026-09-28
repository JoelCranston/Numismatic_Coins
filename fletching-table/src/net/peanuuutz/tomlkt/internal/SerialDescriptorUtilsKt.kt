package net.peanuuutz.tomlkt.internal

import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.BuiltinSerializersKt
import kotlinx.serialization.descriptors.ContextAwareKt
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.SerialKind
import net.peanuuutz.tomlkt.TomlArray
import net.peanuuutz.tomlkt.TomlConfig
import net.peanuuutz.tomlkt.TomlElement
import net.peanuuutz.tomlkt.TomlLiteral
import net.peanuuutz.tomlkt.TomlNull
import net.peanuuutz.tomlkt.TomlTable

private final val UnsignedIntegerDescriptors: Set<SerialDescriptor> =
   SetsKt.setOf(
      new SerialDescriptor[]{
         BuiltinSerializersKt.serializer(UByte.Companion).getDescriptor(),
         BuiltinSerializersKt.serializer(UShort.Companion).getDescriptor(),
         BuiltinSerializersKt.serializer(UInt.Companion).getDescriptor(),
         BuiltinSerializersKt.serializer(ULong.Companion).getDescriptor()
      }
   )
   private final val TomlElementSerializers: Set<KSerializer<*>> =
   SetsKt.setOf(
      new KSerializer[]{
         TomlElement.Companion.serializer(),
         TomlNull.INSTANCE.serializer(),
         TomlLiteral.Companion.serializer(),
         TomlArray.Companion.serializer(),
         TomlTable.Companion.serializer()
      }
   )

internal final val isPrimitiveLike: Boolean
   internal final get() {
      val kind: SerialKind = `$this$isPrimitiveLike`.getKind();
      return kind is PrimitiveKind || kind == SerialKind.ENUM.INSTANCE;
   }


internal final val isUnsignedInteger: Boolean
   internal final get() {
      return `$this$isUnsignedInteger`.isInline() && UnsignedIntegerDescriptors.contains(`$this$isUnsignedInteger`);
   }


internal final val isTomlElement: Boolean
   internal final get() {
      return CollectionsKt.contains(TomlElementSerializers, `$this$isTomlElement`);
   }


internal final val isTomlElement: Boolean
   internal final get() {
      return CollectionsKt.contains(TomlElementSerializers, `$this$isTomlElement`);
   }


internal fun SerialDescriptor.findRealDescriptor(config: TomlConfig): SerialDescriptor {
   val var10000: SerialDescriptor;
   if (`$this$findRealDescriptor`.getKind() == SerialKind.CONTEXTUAL.INSTANCE) {
      val var2: SerialDescriptor = ContextAwareKt.getContextualDescriptor(config.getSerializersModule(), `$this$findRealDescriptor`);
      if (var2 != null) {
         val var3: SerialDescriptor = findRealDescriptor(var2, config);
         if (var3 != null) {
            return var3;
         }
      }

      var10000 = `$this$findRealDescriptor`;
   } else {
      var10000 = if (`$this$findRealDescriptor`.isInline())
         findRealDescriptor(`$this$findRealDescriptor`.getElementDescriptor(0), config)
         else
         `$this$findRealDescriptor`;
   }

   return var10000;
}
