@file:SourceDebugExtension(["SMAP\nSerializersModuleBuilders.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SerializersModuleBuilders.kt\nkotlinx/serialization/modules/SerializersModuleBuildersKt\n*L\n1#1,263:1\n31#1,3:264\n*S KotlinDebug\n*F\n+ 1 SerializersModuleBuilders.kt\nkotlinx/serialization/modules/SerializersModuleBuildersKt\n*L\n15#1:264,3\n*E\n"])

package kotlinx.serialization.modules

import kotlin.jvm.functions.Function1
import kotlin.jvm.internal.Intrinsics
import kotlin.jvm.internal.SourceDebugExtension
import kotlin.reflect.KClass
import kotlinx.serialization.KSerializer
import kotlinx.serialization.modules.SerializersModuleBuildersKt.polymorphic.1

public fun <T : Any> serializersModuleOf(kClass: KClass<T>, serializer: KSerializer<T>): SerializersModule {
   val `builder$iv`: SerializersModuleBuilder = new SerializersModuleBuilder();
   `builder$iv`.contextual(kClass, serializer);
   return `builder$iv`.build();
}

@JvmSynthetic
public inline fun <reified T : Any> serializersModuleOf(serializer: KSerializer<T>): SerializersModule {
   Intrinsics.reifiedOperationMarker(4, "T");
   return serializersModuleOf(Any::class, serializer);
}

public inline fun SerializersModule(builderAction: (SerializersModuleBuilder) -> Unit): SerializersModule {
   val builder: SerializersModuleBuilder = new SerializersModuleBuilder();
   builderAction.invoke(builder);
   return builder.build();
}

public fun EmptySerializersModule(): SerializersModule {
   return SerializersModuleKt.getEmptySerializersModule();
}

@JvmSynthetic
public inline fun <reified T : Any> SerializersModuleBuilder.contextual(serializer: KSerializer<T>) {
   Intrinsics.reifiedOperationMarker(4, "T");
   `$this$contextual`.contextual(Any::class, serializer);
}

public inline fun <Base : Any> SerializersModuleBuilder.polymorphic(
   baseClass: KClass<Base>,
   baseSerializer: KSerializer<Base>? = null,
   builderAction: (PolymorphicModuleBuilder<Base>) -> Unit = 1.INSTANCE as Function1
) {
   val builder: PolymorphicModuleBuilder = new PolymorphicModuleBuilder(baseClass, baseSerializer);
   builderAction.invoke(builder);
   builder.buildTo(`$this$polymorphic`);
}

@JvmSynthetic
fun SerializersModuleBuilder.`polymorphic$default`(
   baseClass: KClass, baseSerializer: KSerializer, builderAction: Function1, `$i$f$polymorphic`: Int, builder: Any
) {
   if ((`$i$f$polymorphic` and 2) != 0) {
      baseSerializer = null;
   }

   if ((`$i$f$polymorphic` and 4) != 0) {
      builderAction = 1.INSTANCE;
   }

   builder = new PolymorphicModuleBuilder(baseClass, baseSerializer);
   builderAction.invoke(builder);
   builder.buildTo(`$this$polymorphic_u24default`);
}
