package kotlinx.serialization.internal

import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.descriptors.SerialDescriptor

@InternalSerializationApi
public abstract class NamedValueDecoder : TaggedDecoder<java.lang.String> {
   protected fun SerialDescriptor.getTag(index: Int): String {
      return this.nested(this.elementName(`$this$getTag`, index));
   }

   protected fun nested(nestedName: String): String {
      var var10001: java.lang.String = this.getCurrentTagOrNull();
      if (var10001 == null) {
         var10001 = "";
      }

      return this.composeName(var10001, nestedName);
   }

   protected open fun elementName(descriptor: SerialDescriptor, index: Int): String {
      return descriptor.getElementName(index);
   }

   protected open fun composeName(parentName: String, childName: String): String {
      return if (parentName.length() == 0) childName else "$parentName.$childName";
   }

   protected fun renderTagStack(): String {
      return if (this.getTagStack$kotlinx_serialization_core().isEmpty())
         "$"
         else
         CollectionsKt.joinToString$default(this.getTagStack$kotlinx_serialization_core(), ".", "$.", null, 0, null, null, 60, null);
   }
}
