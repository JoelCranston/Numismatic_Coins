@file:SourceDebugExtension(["SMAP\nSerializersModule.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SerializersModule.kt\nkotlinx/serialization/modules/SerializersModuleKt\n+ 2 SerializersModuleBuilders.kt\nkotlinx/serialization/modules/SerializersModuleBuildersKt\n*L\n1#1,245:1\n31#2,3:246\n31#2,3:249\n*S KotlinDebug\n*F\n+ 1 SerializersModule.kt\nkotlinx/serialization/modules/SerializersModuleKt\n*L\n97#1:246,3\n109#1:249,3\n*E\n"])

package kotlinx.serialization.modules

import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.serialization.modules.SerializersModuleKt.overwriteWith.1.1

@Deprecated(
   message = "Deprecated in the favour of 'EmptySerializersModule()'",
   replaceWith = @ReplaceWith(
      expression = "EmptySerializersModule()",
      imports = {}
   ),
   level = DeprecationLevel.WARNING
)
public final val EmptySerializersModule: SerializersModule =
   (new SerialModuleImpl(MapsKt.emptyMap(), MapsKt.emptyMap(), MapsKt.emptyMap(), MapsKt.emptyMap(), MapsKt.emptyMap(), false)) as SerializersModule

public operator fun SerializersModule.plus(other: SerializersModule): SerializersModule {
   val `builder$iv`: SerializersModuleBuilder = new SerializersModuleBuilder();
   `builder$iv`.include(`$this$plus`);
   `builder$iv`.include(other);
   return `builder$iv`.build();
}

public infix fun SerializersModule.overwriteWith(other: SerializersModule): SerializersModule {
   val `builder$iv`: SerializersModuleBuilder = new SerializersModuleBuilder();
   `builder$iv`.include(`$this$overwriteWith`);
   other.dumpTo(new 1(`builder$iv`));
   return `builder$iv`.build();
}
