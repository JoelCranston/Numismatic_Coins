@file:SourceDebugExtension(["SMAP\nPlatform.common.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Platform.common.kt\nkotlinx/serialization/internal/Platform_commonKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 ArraysJVM.kt\nkotlin/collections/ArraysKt__ArraysJVMKt\n+ 4 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,190:1\n1#2:191\n37#3:192\n36#3,3:193\n1803#4,3:196\n*S KotlinDebug\n*F\n+ 1 Platform.common.kt\nkotlinx/serialization/internal/Platform_commonKt\n*L\n74#1:192\n74#1:193,3\n160#1:196,3\n*E\n"])

package kotlinx.serialization.internal

import java.util.HashSet
import kotlin.jvm.internal.SourceDebugExtension
import kotlin.reflect.KClass
import kotlin.reflect.KClassifier
import kotlin.reflect.KType
import kotlin.reflect.KTypeParameter
import kotlin.reflect.KTypeProjection
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerializationException
import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.descriptors.SerialDescriptor

private final val EMPTY_DESCRIPTOR_ARRAY: Array<SerialDescriptor>
private SerialDescriptor[] EMPTY_DESCRIPTOR_ARRAY = new SerialDescriptor[0];

internal fun SerialDescriptor.cachedSerialNames(): Set<String> {
   if (`$this$cachedSerialNames` is CachedNames) {
      return (`$this$cachedSerialNames` as CachedNames).getSerialNames();
   } else {
      val result: HashSet = new HashSet(`$this$cachedSerialNames`.getElementsCount());
      var i: Int = 0;

      for (int var3 = $this$cachedSerialNames.getElementsCount(); i < var3; i++) {
         result.add(`$this$cachedSerialNames`.getElementName(i));
      }

      return result;
   }
}

internal fun List<SerialDescriptor>?.compactArray(): Array<SerialDescriptor> {
   val var10000: java.util.List = if (`$this$compactArray` as java.util.Collection != null && !`$this$compactArray`.isEmpty()) `$this$compactArray` else null;
   if (var10000 != null) {
      val var6: Array<SerialDescriptor> = var10000.toArray(new SerialDescriptor[0]);
      if (var6 != null) {
         return var6;
      }
   }

   return EMPTY_DESCRIPTOR_ARRAY;
}

@PublishedApi
internal inline fun <T> KSerializer<*>.cast(): KSerializer<T> {
   return `$this$cast`;
}

@PublishedApi
internal inline fun <T> SerializationStrategy<*>.cast(): SerializationStrategy<T> {
   return `$this$cast`;
}

@PublishedApi
internal inline fun <T> DeserializationStrategy<*>.cast(): DeserializationStrategy<T> {
   return `$this$cast`;
}

internal fun KClass<*>.serializerNotRegistered(): Nothing {
   throw new SerializationException(notRegisteredMessage(`$this$serializerNotRegistered`));
}

internal fun KClass<*>.notRegisteredMessage(): String {
   var var10000: java.lang.String = `$this$notRegisteredMessage`.getSimpleName();
   if (var10000 == null) {
      var10000 = "<local class name not available>";
   }

   return notRegisteredMessage(var10000);
}

internal fun notRegisteredMessage(className: String): String {
   return "Serializer for class '$className' is not found.\nPlease ensure that class is marked as '@Serializable' and that the serialization compiler plugin is applied.\n";
}

internal fun KType.kclass(): KClass<Any> {
   val t: KClassifier = `$this$kclass`.getClassifier();
   if (t is KClass) {
      return t as KClass<Object>;
   } else if (t is KTypeParameter) {
      throw new IllegalArgumentException(
         "Captured type parameter $t from generic non-reified function. Such functionality cannot be supported because $t is erased, either specify serializer explicitly or make calling function inline with reified $t."
      );
   } else {
      throw new IllegalArgumentException("Only KClass supported as classifier, got $t");
   }
}

internal fun KTypeProjection.typeOrThrow(): KType {
   val var10000: KType = `$this$typeOrThrow`.getType();
   if (var10000 == null) {
      throw new IllegalArgumentException(("Star projections in type arguments are not allowed, but had ${`$this$typeOrThrow`.getType()}").toString());
   } else {
      return var10000;
   }
}

internal inline fun <T, K> Iterable<T>.elementsHashCodeBy(selector: (T) -> K): Int {
   var `accumulator$iv`: Int = 1;

   for (Object element$iv : $this$elementsHashCodeBy) {
      val var10000: Int = 31 * `accumulator$iv`;
      val var10001: Any = selector.invoke(`element$iv`);
      `accumulator$iv` = var10000 + (if (var10001 != null) var10001.hashCode() else 0);
   }

   return `accumulator$iv`;
}
