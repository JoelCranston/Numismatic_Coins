package kotlinx.serialization

import java.util.LinkedHashMap
import java.util.Map.Entry
import kotlin.jvm.internal.SourceDebugExtension
import kotlin.jvm.internal.StringCompanionObject
import kotlin.reflect.KClass
import kotlinx.serialization.SealedClassSerializer.special..inlined.groupingBy.1
import kotlinx.serialization.builtins.BuiltinSerializersKt
import kotlinx.serialization.descriptors.ClassSerialDescriptorBuilder
import kotlinx.serialization.descriptors.PolymorphicKind
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptorsKt
import kotlinx.serialization.descriptors.SerialKind
import kotlinx.serialization.encoding.CompositeDecoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.internal.AbstractPolymorphicSerializer

@InternalSerializationApi
@SourceDebugExtension(["SMAP\nSealedSerializer.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SealedSerializer.kt\nkotlinx/serialization/SealedClassSerializer\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 Grouping.kt\nkotlin/collections/GroupingKt__GroupingKt\n+ 4 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n+ 5 Platform.common.kt\nkotlinx/serialization/internal/Platform_commonKt\n+ 6 _Maps.kt\nkotlin/collections/MapsKt___MapsKt\n*L\n1#1,154:1\n1550#2:155\n1252#2,4:165\n53#3:156\n80#3,6:157\n463#4:163\n413#4:164\n82#5:169\n216#6,2:170\n*S KotlinDebug\n*F\n+ 1 SealedSerializer.kt\nkotlinx/serialization/SealedClassSerializer\n*L\n130#1:155\n140#1:165,4\n131#1:156\n131#1:157,6\n140#1:163\n140#1:164\n151#1:169\n109#1:170,2\n*E\n"])
public class SealedClassSerializer<T>(serialName: String, baseClass: KClass<Any>, vararg subclasses: Any, vararg subclassSerializers: Any)
   : AbstractPolymorphicSerializer<T> {
   public open val baseClass: KClass<Any>
   private final var _annotations: List<Annotation>

   public open val descriptor: SerialDescriptor
      public open get() {
         return this.descriptor$delegate.getValue() as SerialDescriptor;
      }


   private final val class2Serializer: Map<KClass<out Any>, KSerializer<out Any>>
   private final val serialName2Serializer: Map<String, KSerializer<out Any>>

   init {
      this.baseClass = baseClass;
      this._annotations = CollectionsKt.emptyList();
      this.descriptor$delegate = LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, SealedClassSerializer::descriptor_delegate$lambda$3);
      if (subclasses.length != subclassSerializers.length) {
         throw new IllegalArgumentException("All subclasses of sealed class ${this.getBaseClass().getSimpleName()} should be marked @Serializable");
      } else {
         this.class2Serializer = MapsKt.toMap(ArraysKt.zip(subclasses, subclassSerializers));
         val var23: Grouping = new 1(this.class2Serializer.entrySet());
         val `$this$mapValuesTo$iv$iv`: Grouping = var23;
         var `destination$iv$iv`: java.util.Map = new LinkedHashMap();
         val `$this$associateByTo$iv$iv$iv`: java.util.Iterator = var23.sourceIterator();

         while ($this$associateByTo$iv$iv$iv.hasNext()) {
            val `$i$f$associateByTo`: Any = `$this$associateByTo$iv$iv$iv`.next();
            val `key$iv$iv`: Any = `$this$mapValuesTo$iv$iv`.keyOf(`$i$f$associateByTo`);
            val `element$iv$iv$iv`: Any = `destination$iv$iv`.get(`key$iv$iv`);
            if (`element$iv$iv$iv` == null && !`destination$iv$iv`.containsKey(`key$iv$iv`)) {
            }

            val element: Entry = `$i$f$associateByTo` as Entry;
            val var16: Entry = `element$iv$iv$iv` as Entry;
            val it: java.lang.String = `key$iv$iv` as java.lang.String;
            if (var16 != null) {
               throw new IllegalStateException(
                  ("Multiple sealed subclasses of '${this.getBaseClass()}' have the same serial name '$it': '${var16.getKey()}', '${element.getKey()}'")
                     .toString()
               );
            }

            `destination$iv$iv`.put(`key$iv$iv`, element);
         }

         `destination$iv$iv` = new LinkedHashMap(MapsKt.mapCapacity(`destination$iv$iv`.size()));

         for (Object element$iv$iv$ivx : var29) {
            `destination$iv$iv`.put((`element$iv$iv$ivx` as Entry).getKey(), ((`element$iv$iv$ivx` as Entry).getValue() as Entry).getValue() as KSerializer);
         }

         this.serialName2Serializer = `destination$iv$iv`;
      }
   }

   @PublishedApi
   internal constructor(serialName: String, baseClass: KClass<Any>, vararg subclasses: Any, vararg subclassSerializers: Any, vararg classAnnotations: Any) : this(
         serialName, baseClass, subclasses, subclassSerializers
      ) {
      this._annotations = ArraysKt.asList(classAnnotations);
   }

   public override fun findPolymorphicSerializerOrNull(decoder: CompositeDecoder, klassName: String?): DeserializationStrategy<Any>? {
      val var10000: KSerializer = this.serialName2Serializer.get(klassName);
      return (DeserializationStrategy<T>)(if (var10000 != null) var10000 else super.findPolymorphicSerializerOrNull(decoder, klassName));
   }

   public override fun findPolymorphicSerializerOrNull(encoder: Encoder, value: Any): SerializationStrategy<Any>? {
      val var10000: KSerializer = this.class2Serializer.get(value.getClass()::class);
      val var5: SerializationStrategy = if (var10000 != null) var10000 else super.findPolymorphicSerializerOrNull(encoder, value);
      return var5 ?: null;
   }

   @JvmStatic
   fun `descriptor_delegate$lambda$3$lambda$2$lambda$1`(`this$0`: SealedClassSerializer, `$this$buildSerialDescriptor`: ClassSerialDescriptorBuilder): Unit {
      for (Entry element$iv : this$0.serialName2Serializer.entrySet()) {
         ClassSerialDescriptorBuilder.element$default(
            `$this$buildSerialDescriptor`,
            `element$iv`.getKey() as java.lang.String,
            (`element$iv`.getValue() as KSerializer).getDescriptor(),
            null,
            false,
            12,
            null
         );
      }

      return Unit.INSTANCE;
   }

   @JvmStatic
   fun `descriptor_delegate$lambda$3$lambda$2`(`this$0`: SealedClassSerializer, `$this$buildSerialDescriptor`: ClassSerialDescriptorBuilder): Unit {
      ClassSerialDescriptorBuilder.element$default(
         `$this$buildSerialDescriptor`, "type", BuiltinSerializersKt.serializer(StringCompanionObject.INSTANCE).getDescriptor(), null, false, 12, null
      );
      ClassSerialDescriptorBuilder.element$default(
         `$this$buildSerialDescriptor`,
         "value",
         SerialDescriptorsKt.buildSerialDescriptor(
            "kotlinx.serialization.Sealed<${`this$0`.getBaseClass().getSimpleName()}>",
            SerialKind.CONTEXTUAL.INSTANCE,
            new SerialDescriptor[0],
            SealedClassSerializer::descriptor_delegate$lambda$3$lambda$2$lambda$1
         ),
         null,
         false,
         12,
         null
      );
      `$this$buildSerialDescriptor`.setAnnotations(`this$0`._annotations);
      return Unit.INSTANCE;
   }

   @JvmStatic
   fun `descriptor_delegate$lambda$3`(`$serialName`: java.lang.String, `this$0`: SealedClassSerializer): SerialDescriptor {
      return SerialDescriptorsKt.buildSerialDescriptor(
         `$serialName`, PolymorphicKind.SEALED.INSTANCE, new SerialDescriptor[0], SealedClassSerializer::descriptor_delegate$lambda$3$lambda$2
      );
   }
}
