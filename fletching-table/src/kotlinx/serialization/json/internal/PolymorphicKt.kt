package kotlinx.serialization.json.internal

import java.lang.annotation.Annotation
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.PolymorphicSerializerKt
import kotlinx.serialization.SealedClassSerializer
import kotlinx.serialization.SerializationException
import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.descriptors.PolymorphicKind
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.SerialKind
import kotlinx.serialization.descriptors.StructureKind
import kotlinx.serialization.internal.AbstractPolymorphicSerializer
import kotlinx.serialization.internal.JsonInternalDependenciesKt
import kotlinx.serialization.json.ClassDiscriminatorMode
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonClassDiscriminator
import kotlinx.serialization.json.JsonDecoder
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonElementKt
import kotlinx.serialization.json.JsonEncoder
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive

internal inline fun <T> JsonEncoder.encodePolymorphically(serializer: SerializationStrategy<T>, value: T, ifPolymorphic: (String, String) -> Unit) {
   if (`$this$encodePolymorphically`.getJson().getConfiguration().getUseArrayPolymorphism()) {
      serializer.serialize(`$this$encodePolymorphically`, value);
   } else {
      val isPolymorphicSerializer: Boolean = serializer is AbstractPolymorphicSerializer;
      var var10000: Boolean;
      if (serializer is AbstractPolymorphicSerializer) {
         var10000 = `$this$encodePolymorphically`.getJson().getConfiguration().getClassDiscriminatorMode() != ClassDiscriminatorMode.NONE;
      } else {
         switch (PolymorphicKt.WhenMappings.$EnumSwitchMapping$0[$this$encodePolymorphically.getJson().getConfiguration().getClassDiscriminatorMode().ordinal()]) {
            case 1:
            case 2:
               var10000 = false;
               break;
            case 3:
               val actual: SerialKind = serializer.getDescriptor().getKind();
               var10000 = actual == StructureKind.CLASS.INSTANCE || actual == StructureKind.OBJECT.INSTANCE;
               break;
            default:
               throw new NoWhenBranchMatchedException();
         }
      }

      val baseClassDiscriminator: java.lang.String = if (var10000)
         classDiscriminator(serializer.getDescriptor(), `$this$encodePolymorphically`.getJson())
         else
         null;
      val var15: SerializationStrategy;
      if (isPolymorphicSerializer) {
         val casted: AbstractPolymorphicSerializer = serializer as AbstractPolymorphicSerializer;
         if (value == null) {
            throw new IllegalArgumentException(
               ("Value for serializer ${(serializer as AbstractPolymorphicSerializer).getDescriptor()} should always be non-null. Please report issue to the kotlinx.serialization tracker.")
                  .toString()
            );
         }

         val var12: SerializationStrategy = PolymorphicSerializerKt.findPolymorphicSerializer(casted, `$this$encodePolymorphically`, value);
         if (baseClassDiscriminator != null) {
            access$validateIfSealed(serializer, var12, baseClassDiscriminator);
            checkKind(var12.getDescriptor().getKind());
         }

         var15 = var12;
      } else {
         var15 = serializer;
      }

      if (baseClassDiscriminator != null) {
         ifPolymorphic.invoke(baseClassDiscriminator, var15.getDescriptor().getSerialName());
      }

      var15.serialize(`$this$encodePolymorphically`, value);
   }
}

private fun validateIfSealed(serializer: SerializationStrategy<*>, actualSerializer: SerializationStrategy<*>, classDiscriminator: String) {
   if (serializer is SealedClassSerializer) {
      if (JsonInternalDependenciesKt.jsonCachedSerialNames(actualSerializer.getDescriptor()).contains(classDiscriminator)) {
         throw new IllegalStateException(
            ("Sealed class '${actualSerializer.getDescriptor().getSerialName()}' cannot be serialized as base class '${(serializer as SealedClassSerializer)
                  .getDescriptor()
                  .getSerialName()}' because it has property name that conflicts with JSON class discriminator '$classDiscriminator'. You can either change class discriminator in JsonConfiguration, rename property with @SerialName annotation or fall back to array polymorphism")
               .toString()
         );
      }
   }
}

internal fun checkKind(kind: SerialKind) {
   if (kind is SerialKind.ENUM) {
      throw new IllegalStateException(
         "Enums cannot be serialized polymorphically with 'type' parameter. You can use 'JsonBuilder.useArrayPolymorphism' instead".toString()
      );
   } else if (kind is PrimitiveKind) {
      throw new IllegalStateException(
         "Primitives cannot be serialized polymorphically with 'type' parameter. You can use 'JsonBuilder.useArrayPolymorphism' instead".toString()
      );
   } else if (kind is PolymorphicKind) {
      throw new IllegalStateException("Actual serializer for polymorphic cannot be polymorphic itself".toString());
   }
}

internal inline fun <T> JsonDecoder.decodeSerializableValuePolymorphic(deserializer: DeserializationStrategy<T>, path: () -> String): T {
   if (deserializer is AbstractPolymorphicSerializer && !`$this$decodeSerializableValuePolymorphic`.getJson().getConfiguration().getUseArrayPolymorphism()) {
      val discriminator: java.lang.String = classDiscriminator(
         (deserializer as AbstractPolymorphicSerializer).getDescriptor(), `$this$decodeSerializableValuePolymorphic`.getJson()
      );
      val type: JsonElement = `$this$decodeSerializableValuePolymorphic`.decodeJsonElement();
      val actualSerializer: java.lang.String = (deserializer as AbstractPolymorphicSerializer).getDescriptor().getSerialName();
      if (type !is JsonObject) {
         throw JsonExceptionsKt.JsonDecodingException(
            -1,
            "Expected ${(JsonObject::class).getSimpleName()}, but had ${(type.getClass()::class).getSimpleName()} as the serialized body of $actualSerializer at element: ${path.invoke() as java.lang.String}",
            type.toString()
         );
      } else {
         var jsonTree: JsonObject;
         var var14: java.lang.String;
         label27: {
            jsonTree = type as JsonObject;
            val var10000: JsonElement = (type as JsonObject).get((Object)discriminator) as JsonElement;
            if (var10000 != null) {
               val var13: JsonPrimitive = JsonElementKt.getJsonPrimitive(var10000);
               if (var13 != null) {
                  var14 = JsonElementKt.getContentOrNull(var13);
                  break label27;
               }
            }

            var14 = null;
         }

         val var12: java.lang.String = var14;

         var var9: DeserializationStrategy;
         try {
            var9 = PolymorphicSerializerKt.findPolymorphicSerializer(
               deserializer as AbstractPolymorphicSerializer, `$this$decodeSerializableValuePolymorphic`, var12
            );
         } catch (var11: SerializationException) {
            val var10001: java.lang.String = var11.getMessage();
            throw JsonExceptionsKt.JsonDecodingException(-1, var10001, jsonTree.toString());
         }

         return (T)TreeJsonDecoderKt.readPolymorphicJson(`$this$decodeSerializableValuePolymorphic`.getJson(), discriminator, jsonTree, var9);
      }
   } else {
      return (T)deserializer.deserialize(`$this$decodeSerializableValuePolymorphic`);
   }
}

internal fun SerialDescriptor.classDiscriminator(json: Json): String {
   for (Annotation annotation : $this$classDiscriminator.getAnnotations()) {
      if (annotation is JsonClassDiscriminator) {
         return (annotation as JsonClassDiscriminator).discriminator();
      }
   }

   return json.getConfiguration().getClassDiscriminator();
}

internal fun throwJsonElementPolymorphicException(serialName: String?, element: JsonElement): Nothing {
   throw new JsonEncodingException(
      "Class with serial name $serialName cannot be serialized polymorphically because it is represented as ${(element.getClass()::class).getSimpleName()}. Make sure that its JsonTransformingSerializer returns JsonObject, so class discriminator can be added to it."
   );
}

@JvmSynthetic
fun `access$validateIfSealed`(serializer: SerializationStrategy, actualSerializer: SerializationStrategy, classDiscriminator: java.lang.String) {
   validateIfSealed(serializer, actualSerializer, classDiscriminator);
}
// $VF: Class flags could not be determined
@JvmSynthetic
internal class WhenMappings {
   @JvmStatic
   fun {
      val var0: IntArray = new int[ClassDiscriminatorMode.values().length];

      try {
         var0[ClassDiscriminatorMode.NONE.ordinal()] = 1;
      } catch (var4: NoSuchFieldError) {
      }

      try {
         var0[ClassDiscriminatorMode.POLYMORPHIC.ordinal()] = 2;
      } catch (var3: NoSuchFieldError) {
      }

      try {
         var0[ClassDiscriminatorMode.ALL_JSON_OBJECTS.ordinal()] = 3;
      } catch (var2: NoSuchFieldError) {
      }

      $EnumSwitchMapping$0 = var0;
   }
}
