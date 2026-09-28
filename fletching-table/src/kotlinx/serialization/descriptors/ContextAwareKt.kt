@file:SourceDebugExtension(["SMAP\nContextAware.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ContextAware.kt\nkotlinx/serialization/descriptors/ContextAwareKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,111:1\n1#2:112\n1563#3:113\n1634#3,3:114\n*S KotlinDebug\n*F\n+ 1 ContextAware.kt\nkotlinx/serialization/descriptors/ContextAwareKt\n*L\n76#1:113\n76#1:114,3\n*E\n"])

package kotlinx.serialization.descriptors

import java.util.ArrayList
import kotlin.jvm.internal.SourceDebugExtension
import kotlin.reflect.KClass
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.internal.SerialDescriptorForNullable
import kotlinx.serialization.modules.SerialModuleImpl
import kotlinx.serialization.modules.SerializersModule

@ExperimentalSerializationApi
public final val capturedKClass: KClass<*>?
   public final get() {
      return if (`$this$capturedKClass` is ContextDescriptor)
         (`$this$capturedKClass` as ContextDescriptor).kClass
         else
         (
            if (`$this$capturedKClass` is SerialDescriptorForNullable)
               getCapturedKClass((`$this$capturedKClass` as SerialDescriptorForNullable).getOriginal$kotlinx_serialization_core())
               else
               null
         );
   }


@ExperimentalSerializationApi
public fun SerializersModule.getContextualDescriptor(descriptor: SerialDescriptor): SerialDescriptor? {
   val var10000: KClass = getCapturedKClass(descriptor);
   val var5: SerialDescriptor;
   if (var10000 != null) {
      val var4: KSerializer = SerializersModule.getContextual$default(`$this$getContextualDescriptor`, var10000, null, 2, null);
      var5 = if (var4 != null) var4.getDescriptor() else null;
   } else {
      var5 = null;
   }

   return var5;
}

@ExperimentalSerializationApi
public fun SerializersModule.getPolymorphicDescriptors(descriptor: SerialDescriptor): List<SerialDescriptor> {
   val var10000: KClass = getCapturedKClass(descriptor);
   if (var10000 == null) {
      return CollectionsKt.emptyList();
   } else {
      val var13: java.util.Map = (`$this$getPolymorphicDescriptors` as SerialModuleImpl).polyBase2Serializers.get(var10000);
      var var14: java.util.Collection = if (var13 != null) var13.values() else null;
      if (var14 == null) {
         var14 = CollectionsKt.emptyList();
      }

      val `$this$map$iv`: java.lang.Iterable = var14;
      val `destination$iv$iv`: java.util.Collection = new ArrayList(CollectionsKt.collectionSizeOrDefault(var14, 10));

      for (Object item$iv$iv : $this$map$iv) {
         `destination$iv$iv`.add((`item$iv$iv` as KSerializer).getDescriptor());
      }

      return `destination$iv$iv` as MutableList<SerialDescriptor>;
   }
}

internal fun SerialDescriptor.withContext(context: KClass<*>): SerialDescriptor {
   return new ContextDescriptor(`$this$withContext`, context);
}
