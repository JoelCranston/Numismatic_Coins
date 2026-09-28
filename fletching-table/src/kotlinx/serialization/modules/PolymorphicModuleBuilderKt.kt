package kotlinx.serialization.modules

import kotlin.jvm.internal.Intrinsics
import kotlin.jvm.internal.MagicApiIntrinsics
import kotlin.reflect.KClass
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerializersKt

@JvmSynthetic
public inline fun <Base : Any, reified T : Base> PolymorphicModuleBuilder<Base>.subclass(serializer: KSerializer<T>) {
   Intrinsics.reifiedOperationMarker(4, "T");
   `$this$subclass`.subclass(Any::class, serializer);
}

@JvmSynthetic
public inline fun <Base : Any, reified T : Base> PolymorphicModuleBuilder<Base>.subclass(clazz: KClass<T>) {
   Intrinsics.reifiedOperationMarker(6, "T");
   MagicApiIntrinsics.voidMagicApiCall("kotlinx.serialization.serializer.simple");
   `$this$subclass`.subclass(clazz, SerializersKt.serializer(null));
}
