package kotlinx.serialization.modules

import java.util.HashMap
import java.util.Map.Entry
import kotlin.jvm.functions.Function1
import kotlin.jvm.internal.SourceDebugExtension
import kotlin.reflect.KClass
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.internal.PlatformKt

@SourceDebugExtension(["SMAP\nSerializersModuleBuilders.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SerializersModuleBuilders.kt\nkotlinx/serialization/modules/SerializersModuleBuilder\n+ 2 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,263:1\n382#2,7:264\n382#2,7:271\n1#3:278\n*S KotlinDebug\n*F\n+ 1 SerializersModuleBuilders.kt\nkotlinx/serialization/modules/SerializersModuleBuilder\n*L\n196#1:264,7\n197#1:271,7\n*E\n"])
public class SerializersModuleBuilder @PublishedApi  internal constructor() : SerializersModuleCollector {
   private final val class2ContextualProvider: MutableMap<KClass<*>, ContextualProvider> = (new HashMap()) as java.util.Map
   private final val polyBase2Serializers: MutableMap<KClass<*>, MutableMap<KClass<*>, KSerializer<*>>> = (new HashMap()) as java.util.Map
   private final val polyBase2DefaultSerializerProvider: MutableMap<KClass<*>, (*) -> SerializationStrategy<*>?> = (new HashMap()) as java.util.Map
   private final val polyBase2NamedSerializers: MutableMap<KClass<*>, MutableMap<String, KSerializer<*>>> = (new HashMap()) as java.util.Map
   private final val polyBase2DefaultDeserializerProvider: MutableMap<KClass<*>, (String?) -> DeserializationStrategy<*>?> = (new HashMap()) as java.util.Map
   private final var hasInterfaceContextualSerializers: Boolean

   public override fun <T : Any> contextual(kClass: KClass<T>, serializer: KSerializer<T>) {
      registerSerializer$default(this, kClass, new ContextualProvider.Argless(serializer), false, 4, null);
   }

   public override fun <T : Any> contextual(kClass: KClass<T>, provider: (List<KSerializer<*>>) -> KSerializer<*>) {
      registerSerializer$default(this, kClass, new ContextualProvider.WithTypeArguments(provider), false, 4, null);
   }

   public override fun <Base : Any, Sub : Base> polymorphic(baseClass: KClass<Base>, actualClass: KClass<Sub>, actualSerializer: KSerializer<Sub>) {
      registerPolymorphicSerializer$default(this, baseClass, actualClass, actualSerializer, false, 8, null);
   }

   public override fun <Base : Any> polymorphicDefaultSerializer(baseClass: KClass<Base>, defaultSerializerProvider: (Base) -> SerializationStrategy<Base>?) {
      this.registerDefaultPolymorphicSerializer(baseClass, defaultSerializerProvider, false);
   }

   public override fun <Base : Any> polymorphicDefaultDeserializer(
      baseClass: KClass<Base>,
      defaultDeserializerProvider: (String?) -> DeserializationStrategy<Base>?
   ) {
      this.registerDefaultPolymorphicDeserializer(baseClass, defaultDeserializerProvider, false);
   }

   public fun include(module: SerializersModule) {
      module.dumpTo(this);
   }

   @JvmName(name = "registerSerializer")
   internal fun <T : Any> registerSerializer(forClass: KClass<T>, provider: ContextualProvider, allowOverwrite: Boolean = false) {
      if (!allowOverwrite) {
         val previous: ContextualProvider = this.class2ContextualProvider.get(forClass);
         if (previous != null && !(previous == provider)) {
            throw new SerializerAlreadyRegisteredException("Contextual serializer or serializer provider for $forClass already registered in this module");
         }
      }

      this.class2ContextualProvider.put(forClass, provider);
      if (PlatformKt.isInterface(forClass)) {
         this.hasInterfaceContextualSerializers = true;
      }
   }

   @JvmName(name = "registerDefaultPolymorphicSerializer")
   internal fun <Base : Any> registerDefaultPolymorphicSerializer(
      baseClass: KClass<Base>,
      defaultSerializerProvider: (Base) -> SerializationStrategy<Base>?,
      allowOverwrite: Boolean
   ) {
      val previous: Function1 = this.polyBase2DefaultSerializerProvider.get(baseClass);
      if (previous != null && !(previous == defaultSerializerProvider) && !allowOverwrite) {
         throw new IllegalArgumentException("Default serializers provider for $baseClass is already registered: $previous");
      } else {
         this.polyBase2DefaultSerializerProvider.put(baseClass, defaultSerializerProvider);
      }
   }

   @JvmName(name = "registerDefaultPolymorphicDeserializer")
   internal fun <Base : Any> registerDefaultPolymorphicDeserializer(
      baseClass: KClass<Base>,
      defaultDeserializerProvider: (String?) -> DeserializationStrategy<Base>?,
      allowOverwrite: Boolean
   ) {
      val previous: Function1 = this.polyBase2DefaultDeserializerProvider.get(baseClass);
      if (previous != null && !(previous == defaultDeserializerProvider) && !allowOverwrite) {
         throw new IllegalArgumentException("Default deserializers provider for $baseClass is already registered: $previous");
      } else {
         this.polyBase2DefaultDeserializerProvider.put(baseClass, defaultDeserializerProvider);
      }
   }

   @JvmName(name = "registerPolymorphicSerializer")
   internal fun <Base : Any, Sub : Base> registerPolymorphicSerializer(
      baseClass: KClass<Base>,
      concreteClass: KClass<Sub>,
      concreteSerializer: KSerializer<Sub>,
      allowOverwrite: Boolean = false
   ) {
      val name: java.lang.String = concreteSerializer.getDescriptor().getSerialName();
      var names: java.util.Map = this.polyBase2Serializers;
      val previousClass: Any = this.polyBase2Serializers.get(baseClass);
      var var10000: Any;
      if (previousClass == null) {
         val var23: Any = new HashMap();
         names.put(baseClass, var23);
         var10000 = (Entry)var23;
      } else {
         var10000 = (Entry)previousClass;
      }

      val baseClassSerializers: java.util.Map = var10000 as java.util.Map;
      val previousSerializer: java.util.Map = this.polyBase2NamedSerializers;
      val `value$ivx`: Any = this.polyBase2NamedSerializers.get(baseClass);
      if (`value$ivx` == null) {
         val var25: Any = new HashMap();
         previousSerializer.put(baseClass, var25);
         var10000 = var25;
      } else {
         var10000 = `value$ivx`;
      }

      names = var10000 as java.util.Map;
      val var19: KSerializer = baseClassSerializers.get(concreteClass) as KSerializer;
      if (var19 != null && !(var19 == concreteSerializer)) {
         if (!allowOverwrite) {
            throw new SerializerAlreadyRegisteredException(baseClass, concreteClass);
         }

         var10000 = names.remove(var19.getDescriptor().getSerialName()) as KSerializer;
      }

      val var20: KSerializer = names.get(name) as KSerializer;
      if (var20 != null && !(var20 == concreteSerializer)) {
         val var14: java.util.Iterator = MapsKt.asSequence(baseClassSerializers).iterator();

         while (true) {
            if (var14.hasNext()) {
               val var15: Any = var14.next();
               if ((var15 as Entry).getValue() != var20) {
                  continue;
               }

               var10000 = var15;
            } else {
               var10000 = null;
            }

            var10000 = var10000 as Entry;
            if (var10000 as Entry == null) {
               throw new IllegalStateException(("Name $name is registered in the module but no Kotlin class is associated with it.").toString());
            }

            val var30: KClass = var10000.getKey() as KClass;
            if (var30 == null) {
               throw new IllegalStateException(("Name $name is registered in the module but no Kotlin class is associated with it.").toString());
            }

            if (!allowOverwrite) {
               throw new IllegalArgumentException(
                  "Multiple polymorphic serializers in a scope of '$baseClass' have the same serial name '$name': $concreteSerializer for '$concreteClass' and $var20 for '$var30'"
               );
            }

            val var31: KSerializer = baseClassSerializers.remove(var30) as KSerializer;
            break;
         }
      }

      baseClassSerializers.put(concreteClass, concreteSerializer);
      names.put(name, concreteSerializer);
   }

   @PublishedApi
   internal fun build(): SerializersModule {
      return new SerialModuleImpl(
         this.class2ContextualProvider,
         this.polyBase2Serializers,
         this.polyBase2DefaultSerializerProvider,
         this.polyBase2NamedSerializers,
         this.polyBase2DefaultDeserializerProvider,
         this.hasInterfaceContextualSerializers
      );
   }

   /** @deprecated */
   @Deprecated(message = "Deprecated in favor of function with more precise name: polymorphicDefaultDeserializer", replaceWith = @ReplaceWith(expression = "polymorphicDefaultDeserializer(baseClass, defaultDeserializerProvider)", imports = []), level = DeprecationLevel.WARNING)
   override fun <Base> polymorphicDefault(baseClass: KClass<Base>, defaultDeserializerProvider: (java.lang.String?) -> DeserializationStrategy<? extends Base>) {
      SerializersModuleCollector.super.polymorphicDefault(baseClass, defaultDeserializerProvider);
   }
}
