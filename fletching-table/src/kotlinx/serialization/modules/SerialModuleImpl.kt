package kotlinx.serialization.modules

import java.util.Map.Entry
import kotlin.jvm.functions.Function1
import kotlin.jvm.internal.SourceDebugExtension
import kotlin.jvm.internal.TypeIntrinsics
import kotlin.reflect.KClass
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerializationStrategy

@SourceDebugExtension(["SMAP\nSerializersModule.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SerializersModule.kt\nkotlinx/serialization/modules/SerialModuleImpl\n+ 2 _Maps.kt\nkotlin/collections/MapsKt___MapsKt\n+ 3 Platform.common.kt\nkotlinx/serialization/internal/Platform_commonKt\n*L\n1#1,245:1\n216#2,2:246\n216#2:248\n216#2:249\n217#2:251\n217#2:252\n216#2,2:253\n216#2,2:255\n78#3:250\n*S KotlinDebug\n*F\n+ 1 SerializersModule.kt\nkotlinx/serialization/modules/SerialModuleImpl\n*L\n186#1:246,2\n196#1:248\n197#1:249\n197#1:251\n196#1:252\n206#1:253,2\n210#1:255,2\n201#1:250\n*E\n"])
internal class SerialModuleImpl(class2ContextualFactory: Map<KClass<*>, ContextualProvider>,
   polyBase2Serializers: Map<KClass<*>, Map<KClass<*>, KSerializer<*>>>,
   polyBase2DefaultSerializerProvider: Map<KClass<*>, (*) -> SerializationStrategy<*>?>,
   polyBase2NamedSerializers: Map<KClass<*>, Map<String, KSerializer<*>>>,
   polyBase2DefaultDeserializerProvider: Map<KClass<*>, (String?) -> DeserializationStrategy<*>?>,
   hasInterfaceContextualSerializers: Boolean
) : SerializersModule() {
   private final val class2ContextualFactory: Map<KClass<*>, ContextualProvider>
   public final val polyBase2Serializers: Map<KClass<*>, Map<KClass<*>, KSerializer<*>>>
   private final val polyBase2DefaultSerializerProvider: Map<KClass<*>, (*) -> SerializationStrategy<*>?>
   private final val polyBase2NamedSerializers: Map<KClass<*>, Map<String, KSerializer<*>>>
   private final val polyBase2DefaultDeserializerProvider: Map<KClass<*>, (String?) -> DeserializationStrategy<*>?>
   internal open val hasInterfaceContextualSerializers: Boolean

   init {
      this.class2ContextualFactory = class2ContextualFactory;
      this.polyBase2Serializers = polyBase2Serializers;
      this.polyBase2DefaultSerializerProvider = polyBase2DefaultSerializerProvider;
      this.polyBase2NamedSerializers = polyBase2NamedSerializers;
      this.polyBase2DefaultDeserializerProvider = polyBase2DefaultDeserializerProvider;
      this.hasInterfaceContextualSerializers = hasInterfaceContextualSerializers;
   }

   public override fun <T : Any> getPolymorphic(baseClass: KClass<in T>, value: T): SerializationStrategy<T>? {
      if (!baseClass.isInstance(value)) {
         return null;
      } else {
         val var10000: java.util.Map = this.polyBase2Serializers.get(baseClass);
         val var4: KSerializer = if (var10000 != null) var10000.get(value.getClass()::class) as KSerializer else null;
         val registered: SerializationStrategy = var4 as? SerializationStrategy;
         if ((var4 as? SerializationStrategy) != null) {
            return registered;
         } else {
            val var5: Any = this.polyBase2DefaultSerializerProvider.get(baseClass);
            val var6: Function1 = if (TypeIntrinsics.isFunctionOfArity(var5, 1)) var5 as Function1 else null;
            return if (var6 != null) var6.invoke(value) as SerializationStrategy else null;
         }
      }
   }

   public override fun <T : Any> getPolymorphic(baseClass: KClass<in T>, serializedClassName: String?): DeserializationStrategy<T>? {
      val var10000: java.util.Map = this.polyBase2NamedSerializers.get(baseClass);
      val var4: KSerializer = if (var10000 != null) var10000.get(serializedClassName) as KSerializer else null;
      val registered: KSerializer = if (var4 is KSerializer) var4 else null;
      if ((if (var4 is KSerializer) var4 else null) != null) {
         return registered;
      } else {
         val var5: Any = this.polyBase2DefaultDeserializerProvider.get(baseClass);
         val var6: Function1 = if (TypeIntrinsics.isFunctionOfArity(var5, 1)) var5 as Function1 else null;
         return if (var6 != null) var6.invoke(serializedClassName) as DeserializationStrategy else null;
      }
   }

   public override fun <T : Any> getContextual(kClass: KClass<T>, typeArgumentsSerializers: List<KSerializer<*>>): KSerializer<T>? {
      val var10000: ContextualProvider = this.class2ContextualFactory.get(kClass);
      val var3: KSerializer = if (var10000 != null) var10000.invoke(typeArgumentsSerializers) else null;
      return if (var3 is KSerializer) var3 else null;
   }

   public override fun dumpTo(collector: SerializersModuleCollector) {
      for (Entry element$iv : this.class2ContextualFactory.entrySet()) {
         val baseClass: KClass = `element$iv`.getKey() as KClass;
         val provider: ContextualProvider = `element$iv`.getValue() as ContextualProvider;
         if (provider is ContextualProvider.Argless) {
            val var10002: KSerializer = (provider as ContextualProvider.Argless).getSerializer();
            collector.contextual(baseClass, var10002);
         } else {
            if (provider !is ContextualProvider.WithTypeArguments) {
               throw new NoWhenBranchMatchedException();
            }

            collector.contextual(baseClass, (provider as ContextualProvider.WithTypeArguments).getProvider());
         }
      }

      for (Entry element$ivx : this.polyBase2Serializers.entrySet()) {
         val var35: KClass = `element$ivx`.getKey() as KClass;

         for (Entry element$ivxx : ((java.util.Map)element$ivx.getValue()).entrySet()) {
            val actualClass: KClass = `element$ivxx`.getKey() as KClass;
            val serializer: KSerializer = `element$ivxx`.getValue() as KSerializer;
            collector.polymorphic(var35, actualClass, serializer);
         }
      }

      for (Entry element$ivx : this.polyBase2DefaultSerializerProvider.entrySet()) {
         val var36: KClass = `element$ivx`.getKey() as KClass;
         val var39: Function1 = `element$ivx`.getValue() as Function1;
         collector.polymorphicDefaultSerializer(var36, TypeIntrinsics.beforeCheckcastToFunctionOfArity(var39, 1) as Function1);
      }

      for (Entry element$ivx : this.polyBase2DefaultDeserializerProvider.entrySet()) {
         val var37: KClass = `element$ivx`.getKey() as KClass;
         val var40: Function1 = `element$ivx`.getValue() as Function1;
         collector.polymorphicDefaultDeserializer(var37, TypeIntrinsics.beforeCheckcastToFunctionOfArity(var40, 1) as Function1);
      }
   }
}
