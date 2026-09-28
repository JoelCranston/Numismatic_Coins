package com.charleskorn.kaml

import java.util.LinkedHashMap
import kotlin.collections.Map.Entry
import kotlin.jvm.internal.Intrinsics
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.CompositeDecoder
import kotlinx.serialization.modules.SerializersModule

@SourceDebugExtension(["SMAP\nYamlObjectInput.kt\nKotlin\n*S Kotlin\n*F\n+ 1 YamlObjectInput.kt\ncom/charleskorn/kaml/YamlObjectInput\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,90:1\n1208#2,2:91\n1236#2,4:93\n*S KotlinDebug\n*F\n+ 1 YamlObjectInput.kt\ncom/charleskorn/kaml/YamlObjectInput\n*L\n34#1:91,2\n34#1:93,4\n*E\n"])
internal class YamlObjectInput(map: YamlMap, yaml: Yaml, context: SerializersModule, configuration: YamlConfiguration) : YamlMapLikeInputBase(
      map, yaml, context, configuration
   ) {
   private final val entriesList: List<Entry<YamlScalar, YamlNode>>
   private final var nextIndex: Int
   private final lateinit var pairedPropertyNames: Map<String, Int>

   init {
      this.entriesList = CollectionsKt.toList(map.getEntries().entrySet());
   }

   public override fun decodeElementIndex(descriptor: SerialDescriptor): Int {
      if (this.pairedPropertyNames == null) {
         val currentEntry: java.lang.Iterable = RangesKt.until(0, descriptor.getElementsCount());
         val `destination$iv$iv`: java.util.Map = new LinkedHashMap(
            RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(currentEntry, 10)), 16)
         );

         for (Object element$iv$iv : currentEntry) {
            var var20: java.lang.String;
            label50: {
               val elementName: java.lang.String = descriptor.getElementName((`element$iv$iv` as java.lang.Number).intValue());
               val var10000: YamlNamingStrategy = this.getConfiguration().getYamlNamingStrategy$kaml();
               if (var10000 != null) {
                  var20 = var10000.serialNameForYaml(elementName);
                  if (var20 != null) {
                     break label50;
                  }
               }

               var20 = elementName;
            }

            `destination$iv$iv`.put(var20, `element$iv$iv`);
         }

         this.pairedPropertyNames = `destination$iv$iv`;
      }

      while (this.nextIndex != this.entriesList.size()) {
         this.setCurrentKey(this.entriesList.get(this.nextIndex).getKey());
         var var21: java.util.Map = this.pairedPropertyNames;
         if (this.pairedPropertyNames == null) {
            Intrinsics.throwUninitializedPropertyAccessException("pairedPropertyNames");
            var21 = null;
         }

         val var22: Int = var21.get(this.getPropertyName()) as Int;
         val var17: Int = (int)(var22 ?: -3);
         if (var17 != -3) {
            try {
               this.setCurrentValueDecoder(
                  YamlInput.Companion
                     .createFor$kaml(
                        this.entriesList.get(this.nextIndex).getValue(),
                        this.getYaml(),
                        this.getSerializersModule(),
                        this.getConfiguration(),
                        descriptor.getElementDescriptor(var17)
                     )
               );
            } catch (var15: IncorrectTypeException) {
               throw new InvalidPropertyValueException(this.getPropertyName(), var15.getMessage(), var15.getPath(), var15);
            }

            this.setCurrentlyReadingValue(true);
            val var19: Int = this.nextIndex++;
            return var17;
         }

         if (this.getConfiguration().getStrictMode$kaml()) {
            val var23: UnknownPropertyException = new UnknownPropertyException;
            val var10002: java.lang.String = this.getPropertyName();
            var var10003: java.util.Map = this.pairedPropertyNames;
            if (this.pairedPropertyNames == null) {
               Intrinsics.throwUninitializedPropertyAccessException("pairedPropertyNames");
               var10003 = null;
            }

            var23./* $VF: Unable to resugar constructor */<init>(var10002, var10003.keySet(), this.getCurrentKey().getPath());
            throw var23;
         }

         val var18: Int = this.nextIndex++;
      }

      return -1;
   }

   public override fun beginStructure(descriptor: SerialDescriptor): CompositeDecoder {
      return if (this.getHaveStartedReadingEntries()) this.fromCurrentValue(YamlObjectInput::beginStructure$lambda$0) else super.beginStructure(descriptor);
   }

   @JvmStatic
   fun `beginStructure$lambda$0`(`$descriptor`: SerialDescriptor, `$this$fromCurrentValue`: YamlInput): CompositeDecoder {
      return `$this$fromCurrentValue`.beginStructure(`$descriptor`);
   }
}
