package com.charleskorn.kaml

import kotlin.collections.Map.Entry
import kotlin.jvm.internal.Intrinsics
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.CompositeDecoder
import kotlinx.serialization.modules.SerializersModule

internal class YamlMapInput(map: YamlMap, yaml: Yaml, context: SerializersModule, configuration: YamlConfiguration) : YamlMapLikeInputBase(
      map, yaml, context, configuration
   ) {
   private final val entriesList: List<Entry<YamlScalar, YamlNode>>
   private final var nextIndex: Int
   private final lateinit var currentEntry: Entry<YamlScalar, YamlNode>

   init {
      this.entriesList = CollectionsKt.toList(map.getEntries().entrySet());
   }

   public override fun decodeElementIndex(descriptor: SerialDescriptor): Int {
      if (this.nextIndex == this.entriesList.size() * 2) {
         return -1;
      } else {
         this.currentEntry = this.entriesList.get(this.nextIndex / 2);
         var var10001: java.util.Map.Entry = this.currentEntry;
         if (this.currentEntry == null) {
            Intrinsics.throwUninitializedPropertyAccessException("currentEntry");
            var10001 = null;
         }

         this.setCurrentKey(var10001.getKey() as YamlScalar);
         this.setCurrentlyReadingValue(this.nextIndex % 2 != 0);
         var var10000: YamlMapInput = this;
         val var3: Boolean = this.getCurrentlyReadingValue();
         val var8: YamlInput;
         if (var3) {
            val var6: YamlMapInput = this;

            var var4: YamlInput;
            try {
               var10000 = var6;
               var var10002: java.util.Map.Entry = this.currentEntry;
               if (this.currentEntry == null) {
                  Intrinsics.throwUninitializedPropertyAccessException("currentEntry");
                  var10002 = null;
               }

               var4 = YamlInput.Companion
                  .createFor$kaml(
                     var10002.getValue() as YamlNode, this.getYaml(), this.getSerializersModule(), this.getConfiguration(), descriptor.getElementDescriptor(1)
                  );
            } catch (var7: IncorrectTypeException) {
               throw new InvalidPropertyValueException(this.getPropertyName(), var7.getMessage(), var7.getPath(), var7);
            }

            var8 = var4;
         } else {
            if (var3) {
               throw new NoWhenBranchMatchedException();
            }

            var8 = YamlInput.Companion
               .createFor$kaml(this.getCurrentKey(), this.getYaml(), this.getSerializersModule(), this.getConfiguration(), descriptor.getElementDescriptor(0));
         }

         var10000.setCurrentValueDecoder(var8);
         return this.nextIndex++;
      }
   }

   public override fun beginStructure(descriptor: SerialDescriptor): CompositeDecoder {
      return if (this.getHaveStartedReadingEntries()) this.fromCurrentValue(YamlMapInput::beginStructure$lambda$0) else super.beginStructure(descriptor);
   }

   @JvmStatic
   fun `beginStructure$lambda$0`(`$descriptor`: SerialDescriptor, `$this$fromCurrentValue`: YamlInput): CompositeDecoder {
      return `$this$fromCurrentValue`.beginStructure(`$descriptor`);
   }
}
