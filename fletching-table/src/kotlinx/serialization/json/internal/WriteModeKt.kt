@file:SourceDebugExtension(["SMAP\nWriteMode.kt\nKotlin\n*S Kotlin\n*F\n+ 1 WriteMode.kt\nkotlinx/serialization/json/internal/WriteModeKt\n*L\n1#1,53:1\n36#1,9:54\n*S KotlinDebug\n*F\n+ 1 WriteMode.kt\nkotlinx/serialization/json/internal/WriteModeKt\n*L\n26#1:54,9\n*E\n"])

package kotlinx.serialization.json.internal

import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.serialization.descriptors.ContextAwareKt
import kotlinx.serialization.descriptors.PolymorphicKind
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.SerialKind
import kotlinx.serialization.descriptors.StructureKind
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule

internal fun Json.switchMode(desc: SerialDescriptor): WriteMode {
   val var2: SerialKind = desc.getKind();
   val var10000: WriteMode;
   if (var2 is PolymorphicKind) {
      var10000 = WriteMode.POLY_OBJ;
   } else if (var2 == StructureKind.LIST.INSTANCE) {
      var10000 = WriteMode.LIST;
   } else if (var2 == StructureKind.MAP.INSTANCE) {
      val `keyDescriptor$iv`: SerialDescriptor = carrierDescriptor(desc.getElementDescriptor(0), `$this$switchMode`.getSerializersModule());
      val `keyKind$iv`: SerialKind = `keyDescriptor$iv`.getKind();
      if (`keyKind$iv` !is PrimitiveKind && !(`keyKind$iv` == SerialKind.ENUM.INSTANCE)) {
         if (!`$this$switchMode`.getConfiguration().getAllowStructuredMapKeys()) {
            throw JsonExceptionsKt.InvalidKeyKindException(`keyDescriptor$iv`);
         }

         var10000 = WriteMode.LIST;
      } else {
         var10000 = WriteMode.MAP;
      }
   } else {
      var10000 = WriteMode.OBJ;
   }

   return var10000;
}

internal inline fun <T, R1 : T, R2 : T> Json.selectMapMode(mapDescriptor: SerialDescriptor, ifMap: () -> R1, ifList: () -> R2): T {
   val keyDescriptor: SerialDescriptor = carrierDescriptor(mapDescriptor.getElementDescriptor(0), `$this$selectMapMode`.getSerializersModule());
   val keyKind: SerialKind = keyDescriptor.getKind();
   val var10000: Any;
   if (keyKind !is PrimitiveKind && !(keyKind == SerialKind.ENUM.INSTANCE)) {
      if (!`$this$selectMapMode`.getConfiguration().getAllowStructuredMapKeys()) {
         throw JsonExceptionsKt.InvalidKeyKindException(keyDescriptor);
      }

      var10000 = ifList.invoke();
   } else {
      var10000 = ifMap.invoke();
   }

   return (T)var10000;
}

internal fun SerialDescriptor.carrierDescriptor(module: SerializersModule): SerialDescriptor {
   var var3: SerialDescriptor;
   if (`$this$carrierDescriptor`.getKind() == SerialKind.CONTEXTUAL.INSTANCE) {
      var3 = ContextAwareKt.getContextualDescriptor(module, `$this$carrierDescriptor`);
      if (var3 != null) {
         var3 = carrierDescriptor(var3, module);
         if (var3 != null) {
            return var3;
         }
      }

      var3 = `$this$carrierDescriptor`;
   } else {
      var3 = if (`$this$carrierDescriptor`.isInline())
         carrierDescriptor(`$this$carrierDescriptor`.getElementDescriptor(0), module)
         else
         `$this$carrierDescriptor`;
   }

   return var3;
}
