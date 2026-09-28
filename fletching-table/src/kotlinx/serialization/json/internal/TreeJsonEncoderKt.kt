package kotlinx.serialization.json.internal

import kotlin.jvm.internal.Intrinsics
import kotlin.jvm.internal.Ref
import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.SerialKind
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement

private final val requiresTopLevelTag: Boolean
   private final get() {
      return `$this$requiresTopLevelTag`.getKind() is PrimitiveKind || `$this$requiresTopLevelTag`.getKind() === SerialKind.ENUM.INSTANCE;
   }


internal const val PRIMITIVE_TAG: String = "primitive"

@JsonFriendModuleApi
public fun <T> writeJson(json: Json, value: T, serializer: SerializationStrategy<T>): JsonElement {
   val result: Ref.ObjectRef = new Ref.ObjectRef();
   new JsonTreeEncoder(json, TreeJsonEncoderKt::writeJson$lambda$0).encodeSerializableValue(serializer, value);
   val var10000: JsonElement;
   if (result.element == null) {
      Intrinsics.throwUninitializedPropertyAccessException("result");
      var10000 = null;
   } else {
      var10000 = result.element as JsonElement;
   }

   return var10000;
}

@JvmSynthetic
internal inline fun <reified T : JsonElement> cast(value: JsonElement, serialName: String, path: () -> String): T {
   Intrinsics.reifiedOperationMarker(3, "T");
   if (value !is JsonElement) {
      val var10001: StringBuilder = new StringBuilder().append("Expected ");
      Intrinsics.reifiedOperationMarker(4, "T");
      throw JsonExceptionsKt.JsonDecodingException(
         -1,
         var10001.append((JsonElement::class).getSimpleName())
            .append(", but had ")
            .append((value.getClass()::class).getSimpleName())
            .append(" as the serialized body of ")
            .append(serialName)
            .append(" at element: ")
            .append(path.invoke() as java.lang.String)
            .toString(),
         value.toString()
      );
   } else {
      return (T)value;
   }
}

fun `writeJson$lambda$0`(`$result`: Ref.ObjectRef, it: JsonElement): Unit {
   `$result`.element = (T)it;
   return Unit.INSTANCE;
}

@JvmSynthetic
fun `access$getRequiresTopLevelTag`(`$receiver`: SerialDescriptor): Boolean {
   return getRequiresTopLevelTag(`$receiver`);
}
