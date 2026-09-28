package kotlinx.serialization.json

import kotlin.jvm.functions.Function1
import kotlin.jvm.internal.Intrinsics
import kotlin.jvm.internal.MagicApiIntrinsics
import kotlinx.serialization.SerializersKt
import kotlinx.serialization.modules.SerializersModule

private const val defaultIndent: String = "    "
private const val defaultDiscriminator: String = "type"

public fun Json(from: Json = Json.Default as Json, builderAction: (JsonBuilder) -> Unit): Json {
   val builder: JsonBuilder = new JsonBuilder(from);
   builderAction.invoke(builder);
   return new JsonImpl(builder.build$kotlinx_serialization_json(), builder.getSerializersModule());
}

@JvmSynthetic
fun `Json$default`(var0: Json, var1: Function1, var2: Int, var3: Any): Json {
   if ((var2 and 1) != 0) {
      var0 = Json.Default;
   }

   return Json(var0, var1);
}

@JvmSynthetic
public inline fun <reified T> Json.encodeToJsonElement(value: T): JsonElement {
   val var3: SerializersModule = `$this$encodeToJsonElement`.getSerializersModule();
   Intrinsics.reifiedOperationMarker(6, "T");
   MagicApiIntrinsics.voidMagicApiCall("kotlinx.serialization.serializer.withModule");
   return `$this$encodeToJsonElement`.encodeToJsonElement(SerializersKt.serializer(var3, null), value);
}

@JvmSynthetic
public inline fun <reified T> Json.decodeFromJsonElement(json: JsonElement): T {
   val var3: SerializersModule = `$this$decodeFromJsonElement`.getSerializersModule();
   Intrinsics.reifiedOperationMarker(6, "T");
   MagicApiIntrinsics.voidMagicApiCall("kotlinx.serialization.serializer.withModule");
   return (T)`$this$decodeFromJsonElement`.decodeFromJsonElement(SerializersKt.serializer(var3, null), json);
}
