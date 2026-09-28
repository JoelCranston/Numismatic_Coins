@file:SourceDebugExtension(["SMAP\nSerialDescriptors.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SerialDescriptors.kt\nkotlinx/serialization/descriptors/SerialDescriptorsKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,393:1\n1#2:394\n*E\n"])

package kotlinx.serialization.descriptors

import kotlin.jvm.functions.Function1
import kotlin.jvm.internal.Intrinsics
import kotlin.jvm.internal.MagicApiIntrinsics
import kotlin.jvm.internal.SourceDebugExtension
import kotlin.reflect.KType
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.SerializersKt
import kotlinx.serialization.internal.ArrayListClassDesc
import kotlinx.serialization.internal.HashMapClassDesc
import kotlinx.serialization.internal.HashSetClassDesc
import kotlinx.serialization.internal.PrimitivesKt
import kotlinx.serialization.internal.SerialDescriptorForNullable

public final val nullable: SerialDescriptor
   public final get() {
      return if (`$this$nullable`.isNullable()) `$this$nullable` else new SerialDescriptorForNullable(`$this$nullable`);
   }


@ExperimentalSerializationApi
public final val nonNullOriginal: SerialDescriptor
   public final get() {
      return if (`$this$nonNullOriginal` is SerialDescriptorForNullable)
         (`$this$nonNullOriginal` as SerialDescriptorForNullable).getOriginal$kotlinx_serialization_core()
         else
         `$this$nonNullOriginal`;
   }


public fun buildClassSerialDescriptor(
   serialName: String,
   vararg typeParameters: SerialDescriptor,
   builderAction: (ClassSerialDescriptorBuilder) -> Unit = SerialDescriptorsKt::buildClassSerialDescriptor$lambda$0
): SerialDescriptor {
   if (StringsKt.isBlank(serialName)) {
      throw new IllegalArgumentException("Blank serial names are prohibited".toString());
   } else {
      val sdBuilder: ClassSerialDescriptorBuilder = new ClassSerialDescriptorBuilder(serialName);
      builderAction.invoke(sdBuilder);
      return new SerialDescriptorImpl(
         serialName, StructureKind.CLASS.INSTANCE, sdBuilder.getElementNames$kotlinx_serialization_core().size(), ArraysKt.toList(typeParameters), sdBuilder
      );
   }
}

@JvmSynthetic
fun `buildClassSerialDescriptor$default`(var0: java.lang.String, var1: Array<SerialDescriptor>, var2: Function1, var3: Int, var4: Any): SerialDescriptor {
   if ((var3 and 4) != 0) {
      var2 = SerialDescriptorsKt::buildClassSerialDescriptor$lambda$0;
   }

   return buildClassSerialDescriptor(var0, var1, var2);
}

public fun PrimitiveSerialDescriptor(serialName: String, kind: PrimitiveKind): SerialDescriptor {
   if (StringsKt.isBlank(serialName)) {
      throw new IllegalArgumentException("Blank serial names are prohibited".toString());
   } else {
      return PrimitivesKt.PrimitiveDescriptorSafe(serialName, kind);
   }
}

public fun SerialDescriptor(serialName: String, original: SerialDescriptor): SerialDescriptor {
   if (StringsKt.isBlank(serialName)) {
      throw new IllegalArgumentException("Blank serial names are prohibited".toString());
   } else if (serialName == original.getSerialName()) {
      throw new IllegalArgumentException(
         ("The name of the wrapped descriptor ($serialName) cannot be the same as the name of the original descriptor (${original.getSerialName()})")
            .toString()
      );
   } else {
      if (original.getKind() is PrimitiveKind) {
         PrimitivesKt.checkNameIsNotAPrimitive(serialName);
      }

      return new WrappedSerialDescriptor(serialName, original);
   }
}

@InternalSerializationApi
public fun buildSerialDescriptor(
   serialName: String,
   kind: SerialKind,
   vararg typeParameters: SerialDescriptor,
   builder: (ClassSerialDescriptorBuilder) -> Unit = SerialDescriptorsKt::buildSerialDescriptor$lambda$5
): SerialDescriptor {
   if (StringsKt.isBlank(serialName)) {
      throw new IllegalArgumentException("Blank serial names are prohibited".toString());
   } else if (kind == StructureKind.CLASS.INSTANCE) {
      throw new IllegalArgumentException("For StructureKind.CLASS please use 'buildClassSerialDescriptor' instead".toString());
   } else {
      val sdBuilder: ClassSerialDescriptorBuilder = new ClassSerialDescriptorBuilder(serialName);
      builder.invoke(sdBuilder);
      return new SerialDescriptorImpl(
         serialName, kind, sdBuilder.getElementNames$kotlinx_serialization_core().size(), ArraysKt.toList(typeParameters), sdBuilder
      );
   }
}

@JvmSynthetic
fun `buildSerialDescriptor$default`(var0: java.lang.String, var1: SerialKind, var2: Array<SerialDescriptor>, var3: Function1, var4: Int, var5: Any): SerialDescriptor {
   if ((var4 and 8) != 0) {
      var3 = SerialDescriptorsKt::buildSerialDescriptor$lambda$5;
   }

   return buildSerialDescriptor(var0, var1, var2, var3);
}

@JvmSynthetic
public inline fun <reified T> serialDescriptor(): SerialDescriptor {
   Intrinsics.reifiedOperationMarker(6, "T");
   MagicApiIntrinsics.voidMagicApiCall("kotlinx.serialization.serializer.simple");
   return SerializersKt.serializer(null).getDescriptor();
}

public fun serialDescriptor(type: KType): SerialDescriptor {
   return SerializersKt.serializer(type).getDescriptor();
}

@ExperimentalSerializationApi
public fun listSerialDescriptor(elementDescriptor: SerialDescriptor): SerialDescriptor {
   return new ArrayListClassDesc(elementDescriptor);
}

@ExperimentalSerializationApi
@JvmSynthetic
public inline fun <reified T> listSerialDescriptor(): SerialDescriptor {
   Intrinsics.reifiedOperationMarker(6, "T");
   MagicApiIntrinsics.voidMagicApiCall("kotlinx.serialization.serializer.simple");
   return listSerialDescriptor(SerializersKt.serializer(null).getDescriptor());
}

@ExperimentalSerializationApi
public fun mapSerialDescriptor(keyDescriptor: SerialDescriptor, valueDescriptor: SerialDescriptor): SerialDescriptor {
   return new HashMapClassDesc(keyDescriptor, valueDescriptor);
}

@ExperimentalSerializationApi
@JvmSynthetic
public inline fun <reified K, reified V> mapSerialDescriptor(): SerialDescriptor {
   Intrinsics.reifiedOperationMarker(6, "K");
   MagicApiIntrinsics.voidMagicApiCall("kotlinx.serialization.serializer.simple");
   val var10000: SerialDescriptor = SerializersKt.serializer(null).getDescriptor();
   Intrinsics.reifiedOperationMarker(6, "V");
   MagicApiIntrinsics.voidMagicApiCall("kotlinx.serialization.serializer.simple");
   return mapSerialDescriptor(var10000, SerializersKt.serializer(null).getDescriptor());
}

@ExperimentalSerializationApi
public fun setSerialDescriptor(elementDescriptor: SerialDescriptor): SerialDescriptor {
   return new HashSetClassDesc(elementDescriptor);
}

@ExperimentalSerializationApi
@JvmSynthetic
public inline fun <reified T> setSerialDescriptor(): SerialDescriptor {
   Intrinsics.reifiedOperationMarker(6, "T");
   MagicApiIntrinsics.voidMagicApiCall("kotlinx.serialization.serializer.simple");
   return setSerialDescriptor(SerializersKt.serializer(null).getDescriptor());
}

@JvmSynthetic
public inline fun <reified T> ClassSerialDescriptorBuilder.element(
   elementName: String,
   annotations: List<Annotation> = CollectionsKt.emptyList(),
   isOptional: Boolean = false
) {
   Intrinsics.reifiedOperationMarker(6, "T");
   MagicApiIntrinsics.voidMagicApiCall("kotlinx.serialization.serializer.simple");
   `$this$element`.element(elementName, SerializersKt.serializer(null).getDescriptor(), annotations, isOptional);
}

@JvmSynthetic
fun ClassSerialDescriptorBuilder.`element$default`(
   elementName: java.lang.String, annotations: java.util.List, isOptional: Boolean, `$i$f$element`: Int, descriptor: Any
) {
   if ((`$i$f$element` and 2) != 0) {
      annotations = CollectionsKt.emptyList();
   }

   if ((`$i$f$element` and 4) != 0) {
      isOptional = false;
   }

   Intrinsics.reifiedOperationMarker(6, "T");
   MagicApiIntrinsics.voidMagicApiCall("kotlinx.serialization.serializer.simple");
   `$this$element_u24default`.element(elementName, SerializersKt.serializer(null).getDescriptor(), annotations, isOptional);
}

fun `buildClassSerialDescriptor$lambda$0`(var0: ClassSerialDescriptorBuilder): Unit {
   return Unit.INSTANCE;
}

fun `buildSerialDescriptor$lambda$5`(var0: ClassSerialDescriptorBuilder): Unit {
   return Unit.INSTANCE;
}
