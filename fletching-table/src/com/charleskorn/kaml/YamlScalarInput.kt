package com.charleskorn.kaml

import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.modules.SerializersModule

@SourceDebugExtension(["SMAP\nYamlScalarInput.kt\nKotlin\n*S Kotlin\n*F\n+ 1 YamlScalarInput.kt\ncom/charleskorn/kaml/YamlScalarInput\n+ 2 _Sequences.kt\nkotlin/sequences/SequencesKt___SequencesKt\n*L\n1#1,69:1\n209#2,8:70\n*S KotlinDebug\n*F\n+ 1 YamlScalarInput.kt\ncom/charleskorn/kaml/YamlScalarInput\n*L\n50#1:70,8\n*E\n"])
internal class YamlScalarInput(scalar: YamlScalar, yaml: Yaml, context: SerializersModule, configuration: YamlConfiguration) : YamlInput(
      scalar, yaml, context, configuration
   ) {
   public final val scalar: YamlScalar

   init {
      this.scalar = scalar;
   }

   public override fun decodeString(): String {
      return this.scalar.getContent();
   }

   public override fun decodeInt(): Int {
      return this.scalar.toInt();
   }

   public override fun decodeLong(): Long {
      return this.scalar.toLong();
   }

   public override fun decodeShort(): Short {
      return this.scalar.toShort();
   }

   public override fun decodeByte(): Byte {
      return this.scalar.toByte();
   }

   public override fun decodeDouble(): Double {
      return this.scalar.toDouble();
   }

   public override fun decodeFloat(): Float {
      return this.scalar.toFloat();
   }

   public override fun decodeBoolean(): Boolean {
      return this.scalar.toBoolean();
   }

   public override fun decodeChar(): Char {
      return this.scalar.toChar();
   }

   public override fun decodeEnum(enumDescriptor: SerialDescriptor): Int {
      val index: Int = enumDescriptor.getElementIndex(this.scalar.getContent());
      if (index != -3) {
         return index;
      } else {
         val choices: Sequence = SequencesKt.map(
            CollectionsKt.asSequence(RangesKt.until(0, enumDescriptor.getElementsCount())), YamlScalarInput::decodeEnum$lambda$0
         );
         if (this.getConfiguration().getDecodeEnumCaseInsensitive$kaml()) {
            var `index$iv`: Int = 0;

            var var10000: Int;
            label29: {
               for (Object item$iv : choices) {
                  if (`index$iv` < 0) {
                     CollectionsKt.throwIndexOverflow();
                  }

                  if (StringsKt.equals(`item$iv` as java.lang.String, this.scalar.getContent(), true)) {
                     var10000 = `index$iv`;
                     break label29;
                  }

                  `index$iv`++;
               }

               var10000 = -1;
            }

            if (var10000 != -1) {
               return var10000;
            }
         }

         throw new YamlScalarFormatException(
            "Value ${this.scalar.contentToString()} is not a valid option, permitted choices are: ${SequencesKt.joinToString$default(
               SequencesKt.sorted(choices), ", ", null, null, 0, null, null, 62, null
            )}",
            this.scalar.getPath(),
            this.scalar.getContent()
         );
      }
   }

   public override fun getCurrentLocation(): Location {
      return this.scalar.getLocation();
   }

   public override fun getCurrentPath(): YamlPath {
      return this.scalar.getPath();
   }

   public override fun decodeElementIndex(descriptor: SerialDescriptor): Int {
      return 0;
   }

   @JvmStatic
   fun `decodeEnum$lambda$0`(`$enumDescriptor`: SerialDescriptor, it: Int): java.lang.String {
      return `$enumDescriptor`.getElementName(it);
   }
}
