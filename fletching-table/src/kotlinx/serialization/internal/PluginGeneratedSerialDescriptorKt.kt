@file:SourceDebugExtension(["SMAP\nPluginGeneratedSerialDescriptor.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PluginGeneratedSerialDescriptor.kt\nkotlinx/serialization/internal/PluginGeneratedSerialDescriptorKt\n+ 2 Platform.common.kt\nkotlinx/serialization/internal/Platform_commonKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,134:1\n160#2:135\n160#2:139\n1803#3,3:136\n1803#3,3:140\n*S KotlinDebug\n*F\n+ 1 PluginGeneratedSerialDescriptor.kt\nkotlinx/serialization/internal/PluginGeneratedSerialDescriptorKt\n*L\n124#1:135\n125#1:139\n124#1:136,3\n125#1:140,3\n*E\n"])

package kotlinx.serialization.internal

import java.util.Arrays
import kotlin.jvm.internal.Intrinsics
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptorKt
import kotlinx.serialization.descriptors.SerialKind

@JvmSynthetic
internal inline fun <reified SD : SerialDescriptor> SD.equalsImpl(other: Any?, typeParamsAreEqual: (SD) -> Boolean): Boolean {
   if (`$this$equalsImpl` === other) {
      return true;
   } else {
      Intrinsics.reifiedOperationMarker(3, "SD");
      if (other !is SerialDescriptor) {
         return false;
      } else if (!(`$this$equalsImpl`.getSerialName() == (other as SerialDescriptor).getSerialName())) {
         return false;
      } else if (!typeParamsAreEqual.invoke(other) as java.lang.Boolean) {
         return false;
      } else if (`$this$equalsImpl`.getElementsCount() != (other as SerialDescriptor).getElementsCount()) {
         return false;
      } else {
         var index: Int = 0;

         for (int var5 = $this$equalsImpl.getElementsCount(); index < var5; index++) {
            if (!(`$this$equalsImpl`.getElementDescriptor(index).getSerialName() == (other as SerialDescriptor).getElementDescriptor(index).getSerialName())) {
               return false;
            }

            if (!(`$this$equalsImpl`.getElementDescriptor(index).getKind() == (other as SerialDescriptor).getElementDescriptor(index).getKind())) {
               return false;
            }
         }

         return true;
      }
   }
}

internal fun SerialDescriptor.hashCodeImpl(typeParams: Array<SerialDescriptor>): Int {
   val var21: Int = 31 * `$this$hashCodeImpl`.getSerialName().hashCode() + Arrays.hashCode((Object[])typeParams);
   val elementDescriptors: java.lang.Iterable = SerialDescriptorKt.getElementDescriptors(`$this$hashCodeImpl`);
   var `$i$f$fold`: Int = 1;

   for (Object element$iv$iv : elementDescriptors) {
      val var10000: Int = 31 * `$i$f$fold`;
      val var20: java.lang.String = (`element$iv$iv` as SerialDescriptor).getSerialName();
      `$i$f$fold` = var10000 + (if (var20 != null) var20.hashCode() else 0);
   }

   var `accumulator$iv$ivx`: Int = 1;

   for (Object element$iv$iv : elementDescriptors) {
      val var31: Int = 31 * `accumulator$iv$ivx`;
      val var30: SerialKind = (`element$iv$iv` as SerialDescriptor).getKind();
      `accumulator$iv$ivx` = var31 + (if (var30 != null) var30.hashCode() else 0);
   }

   return 31 * (31 * var21 + `$i$f$fold`) + `accumulator$iv$ivx`;
}

internal fun SerialDescriptor.toStringImpl(): String {
   return CollectionsKt.joinToString$default(
      RangesKt.until(0, `$this$toStringImpl`.getElementsCount()),
      ", ",
      "${`$this$toStringImpl`.getSerialName()}(",
      ")",
      0,
      null,
      PluginGeneratedSerialDescriptorKt::toStringImpl$lambda$2,
      24,
      null
   );
}

fun `toStringImpl$lambda$2`(`$this_toStringImpl`: SerialDescriptor, i: Int): java.lang.CharSequence {
   return "${`$this_toStringImpl`.getElementName(i)}: ${`$this_toStringImpl`.getElementDescriptor(i).getSerialName()}";
}
