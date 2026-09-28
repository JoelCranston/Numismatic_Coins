package kotlinx.serialization.internal

import java.util.Arrays
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.serialization.descriptors.SerialDescriptor

@PublishedApi
@SourceDebugExtension(["SMAP\nInlineClassDescriptor.kt\nKotlin\n*S Kotlin\n*F\n+ 1 InlineClassDescriptor.kt\nkotlinx/serialization/internal/InlineClassDescriptor\n+ 2 PluginGeneratedSerialDescriptor.kt\nkotlinx/serialization/internal/PluginGeneratedSerialDescriptorKt\n*L\n1#1,44:1\n107#2,10:45\n*S KotlinDebug\n*F\n+ 1 InlineClassDescriptor.kt\nkotlinx/serialization/internal/InlineClassDescriptor\n*L\n22#1:45,10\n*E\n"])
internal class InlineClassDescriptor(name: String, generatedSerializer: GeneratedSerializer<*>) : PluginGeneratedSerialDescriptor(name, generatedSerializer, 1) {
   public open val isInline: Boolean = true

   public override fun hashCode(): Int {
      return super.hashCode() * 31;
   }

   public override operator fun equals(other: Any?): Boolean {
      val `$this$equalsImpl$iv`: SerialDescriptor = this;
      val `other$iv`: Any = other;
      var var10000: Boolean;
      if (`$this$equalsImpl$iv` === other) {
         var10000 = true;
      } else if (other !is InlineClassDescriptor) {
         var10000 = false;
      } else if (!(`$this$equalsImpl$iv`.getSerialName() == (other as SerialDescriptor).getSerialName())) {
         var10000 = false;
      } else if (!(other as InlineClassDescriptor).isInline()
         || !Arrays.equals(
            (Object[])this.getTypeParameterDescriptors$kotlinx_serialization_core(),
            (Object[])(other as InlineClassDescriptor).getTypeParameterDescriptors$kotlinx_serialization_core()
         )) {
         var10000 = false;
      } else if (`$this$equalsImpl$iv`.getElementsCount() != (other as SerialDescriptor).getElementsCount()) {
         var10000 = false;
      } else {
         var `index$iv`: Int = 0;
         val var8: Int = `$this$equalsImpl$iv`.getElementsCount();

         while (true) {
            if (`index$iv` >= var8) {
               var10000 = true;
               break;
            }

            if (!(
               `$this$equalsImpl$iv`.getElementDescriptor(`index$iv`).getSerialName()
                  == (`other$iv` as SerialDescriptor).getElementDescriptor(`index$iv`).getSerialName()
            )) {
               var10000 = false;
               break;
            }

            if (!(
               `$this$equalsImpl$iv`.getElementDescriptor(`index$iv`).getKind() == (`other$iv` as SerialDescriptor).getElementDescriptor(`index$iv`).getKind()
            )) {
               var10000 = false;
               break;
            }

            `index$iv`++;
         }
      }

      return var10000;
   }
}
