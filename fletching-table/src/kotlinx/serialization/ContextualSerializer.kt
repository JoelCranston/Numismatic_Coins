package kotlinx.serialization

import kotlin.reflect.KClass
import kotlinx.serialization.descriptors.ClassSerialDescriptorBuilder
import kotlinx.serialization.descriptors.ContextAwareKt
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptorsKt
import kotlinx.serialization.descriptors.SerialKind
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.internal.Platform_commonKt
import kotlinx.serialization.internal.PluginHelperInterfacesKt
import kotlinx.serialization.modules.SerializersModule

@ExperimentalSerializationApi
public class ContextualSerializer<T>(serializableClass: KClass<Any>, fallbackSerializer: KSerializer<Any>?, vararg typeArgumentsSerializers: Any) :
   KSerializer<T> {
   private final val serializableClass: KClass<Any>
   private final val fallbackSerializer: KSerializer<Any>?
   private final val typeArgumentsSerializers: List<KSerializer<*>>
   public open val descriptor: SerialDescriptor

   init {
      this.serializableClass = serializableClass;
      this.fallbackSerializer = fallbackSerializer;
      this.typeArgumentsSerializers = ArraysKt.asList(typeArgumentsSerializers);
      this.descriptor = ContextAwareKt.withContext(
         SerialDescriptorsKt.buildSerialDescriptor(
            "kotlinx.serialization.ContextualSerializer", SerialKind.CONTEXTUAL.INSTANCE, new SerialDescriptor[0], ContextualSerializer::descriptor$lambda$0
         ),
         this.serializableClass
      );
   }

   private fun serializer(serializersModule: SerializersModule): KSerializer<Any> {
      var var10000: KSerializer = serializersModule.getContextual(this.serializableClass, this.typeArgumentsSerializers);
      if (var10000 == null) {
         var10000 = this.fallbackSerializer;
         if (this.fallbackSerializer == null) {
            Platform_commonKt.serializerNotRegistered(this.serializableClass);
            throw new KotlinNothingValueException();
         }
      }

      return var10000;
   }

   public constructor(serializableClass: KClass<Any>) : this(serializableClass, null, PluginHelperInterfacesKt.EMPTY_SERIALIZER_ARRAY)
   public override fun serialize(encoder: Encoder, value: Any) {
      encoder.encodeSerializableValue(this.serializer(encoder.getSerializersModule()), (T)value);
   }

   public override fun deserialize(decoder: Decoder): Any {
      return decoder.decodeSerializableValue(this.serializer(decoder.getSerializersModule()));
   }

   @JvmStatic
   fun `descriptor$lambda$0`(`this$0`: ContextualSerializer, `$this$buildSerialDescriptor`: ClassSerialDescriptorBuilder): Unit {
      var var2: java.util.List;
      label16: {
         if (`this$0`.fallbackSerializer != null) {
            val var10001: SerialDescriptor = `this$0`.fallbackSerializer.getDescriptor();
            if (var10001 != null) {
               var2 = var10001.getAnnotations();
               break label16;
            }
         }

         var2 = null;
      }

      if (var2 == null) {
         var2 = CollectionsKt.emptyList();
      }

      `$this$buildSerialDescriptor`.setAnnotations(var2);
      return Unit.INSTANCE;
   }
}
