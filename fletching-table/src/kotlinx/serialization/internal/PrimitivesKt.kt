@file:SourceDebugExtension(["SMAP\nPrimitives.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Primitives.kt\nkotlinx/serialization/internal/PrimitivesKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,133:1\n1#2:134\n*E\n"])

package kotlinx.serialization.internal

import kotlin.jvm.internal.SourceDebugExtension
import kotlin.reflect.KClass
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.SerialDescriptor

private final val BUILTIN_SERIALIZERS: Map<KClass<*>, KSerializer<*>> = PlatformKt.initBuiltins()

internal fun PrimitiveDescriptorSafe(serialName: String, kind: PrimitiveKind): SerialDescriptor {
   checkNameIsNotAPrimitive(serialName);
   return new PrimitiveSerialDescriptor(serialName, kind);
}

internal fun checkNameIsNotAPrimitive(serialName: String) {
   for (KSerializer primitive : BUILTIN_SERIALIZERS.values()) {
      if (serialName == primitive.getDescriptor().getSerialName()) {
         throw new IllegalArgumentException(
            StringsKt.trimIndent(
               "\n                The name of serial descriptor should uniquely identify associated serializer.\n                For serial name $serialName there already exists ${(primitive.getClass()::class)
                  .getSimpleName()}.\n                Please refer to SerialDescriptor documentation for additional information.\n            "
            )
         );
      }
   }
}

private fun String.capitalize(): String {
   val var7: java.lang.String;
   if (`$this$capitalize`.length() > 0) {
      val var10000: StringBuilder = new StringBuilder();
      val it: Char = `$this$capitalize`.charAt(0);
      val var6: StringBuilder = var10000.append(if (Character.isLowerCase(it)) CharsKt.titlecase(it) else java.lang.String.valueOf(it));
      val var10001: java.lang.String = `$this$capitalize`.substring(1);
      var7 = var6.append(var10001).toString();
   } else {
      var7 = `$this$capitalize`;
   }

   return var7;
}

internal fun <T : Any> KClass<T>.builtinSerializerOrNull(): KSerializer<T>? {
   return BUILTIN_SERIALIZERS.get(`$this$builtinSerializerOrNull`) as KSerializer<T>;
}
