package io.ktor.utils.io

@InternalAPI
public fun <T : Any> JvmSerializerReplacement(serializer: JvmSerializer<Any>, value: Any): Any {
   return new DefaultJvmSerializerReplacement<>(serializer, value);
}

/** @deprecated */
@InternalAPI
@JvmSynthetic
fun `JvmSerializable$annotations`() {
}
