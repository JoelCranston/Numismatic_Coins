package com.charleskorn.kaml

import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.CompositeDecoder
import kotlinx.serialization.modules.SerializersModule

@SourceDebugExtension(["SMAP\nYamlContextualInput.kt\nKotlin\n*S Kotlin\n*F\n+ 1 YamlContextualInput.kt\ncom/charleskorn/kaml/YamlContextualInput\n*L\n1#1,53:1\n46#1,4:54\n46#1,4:58\n46#1,4:62\n46#1,4:66\n46#1,4:70\n46#1,4:74\n46#1,4:78\n46#1,4:82\n46#1,4:86\n46#1,4:90\n*S KotlinDebug\n*F\n+ 1 YamlContextualInput.kt\ncom/charleskorn/kaml/YamlContextualInput\n*L\n26#1:54,4\n27#1:58,4\n28#1:62,4\n29#1:66,4\n30#1:70,4\n31#1:74,4\n32#1:78,4\n33#1:82,4\n34#1:86,4\n35#1:90,4\n*E\n"])
internal class YamlContextualInput(node: YamlNode, yaml: Yaml, context: SerializersModule, configuration: YamlConfiguration) : YamlInput(
      node, yaml, context, configuration
   ) {
   public override fun decodeString(): String {
      val var3: YamlNode = this.getNode();
      if (var3 is YamlScalar) {
         return new YamlScalarInput(this.getNode() as YamlScalar, this.getYaml(), this.getSerializersModule(), this.getConfiguration()).decodeString();
      } else if (var3 is YamlNull) {
         throw new UnexpectedNullValueException((this.getNode() as YamlNull).getPath());
      } else {
         throw new IllegalStateException("Must call beginStructure() and use returned Decoder");
      }
   }

   public override fun decodeInt(): Int {
      val var3: YamlNode = this.getNode();
      if (var3 is YamlScalar) {
         return new YamlScalarInput(this.getNode() as YamlScalar, this.getYaml(), this.getSerializersModule(), this.getConfiguration()).decodeInt();
      } else if (var3 is YamlNull) {
         throw new UnexpectedNullValueException((this.getNode() as YamlNull).getPath());
      } else {
         throw new IllegalStateException("Must call beginStructure() and use returned Decoder");
      }
   }

   public override fun decodeLong(): Long {
      val var3: YamlNode = this.getNode();
      if (var3 is YamlScalar) {
         return new YamlScalarInput(this.getNode() as YamlScalar, this.getYaml(), this.getSerializersModule(), this.getConfiguration()).decodeLong();
      } else if (var3 is YamlNull) {
         throw new UnexpectedNullValueException((this.getNode() as YamlNull).getPath());
      } else {
         throw new IllegalStateException("Must call beginStructure() and use returned Decoder");
      }
   }

   public override fun decodeShort(): Short {
      val var3: YamlNode = this.getNode();
      if (var3 is YamlScalar) {
         return new YamlScalarInput(this.getNode() as YamlScalar, this.getYaml(), this.getSerializersModule(), this.getConfiguration()).decodeShort();
      } else if (var3 is YamlNull) {
         throw new UnexpectedNullValueException((this.getNode() as YamlNull).getPath());
      } else {
         throw new IllegalStateException("Must call beginStructure() and use returned Decoder");
      }
   }

   public override fun decodeByte(): Byte {
      val var3: YamlNode = this.getNode();
      if (var3 is YamlScalar) {
         return new YamlScalarInput(this.getNode() as YamlScalar, this.getYaml(), this.getSerializersModule(), this.getConfiguration()).decodeByte();
      } else if (var3 is YamlNull) {
         throw new UnexpectedNullValueException((this.getNode() as YamlNull).getPath());
      } else {
         throw new IllegalStateException("Must call beginStructure() and use returned Decoder");
      }
   }

   public override fun decodeDouble(): Double {
      val var3: YamlNode = this.getNode();
      if (var3 is YamlScalar) {
         return new YamlScalarInput(this.getNode() as YamlScalar, this.getYaml(), this.getSerializersModule(), this.getConfiguration()).decodeDouble();
      } else if (var3 is YamlNull) {
         throw new UnexpectedNullValueException((this.getNode() as YamlNull).getPath());
      } else {
         throw new IllegalStateException("Must call beginStructure() and use returned Decoder");
      }
   }

   public override fun decodeFloat(): Float {
      val var3: YamlNode = this.getNode();
      if (var3 is YamlScalar) {
         return new YamlScalarInput(this.getNode() as YamlScalar, this.getYaml(), this.getSerializersModule(), this.getConfiguration()).decodeFloat();
      } else if (var3 is YamlNull) {
         throw new UnexpectedNullValueException((this.getNode() as YamlNull).getPath());
      } else {
         throw new IllegalStateException("Must call beginStructure() and use returned Decoder");
      }
   }

   public override fun decodeBoolean(): Boolean {
      val var3: YamlNode = this.getNode();
      if (var3 is YamlScalar) {
         return new YamlScalarInput(this.getNode() as YamlScalar, this.getYaml(), this.getSerializersModule(), this.getConfiguration()).decodeBoolean();
      } else if (var3 is YamlNull) {
         throw new UnexpectedNullValueException((this.getNode() as YamlNull).getPath());
      } else {
         throw new IllegalStateException("Must call beginStructure() and use returned Decoder");
      }
   }

   public override fun decodeChar(): Char {
      val var3: YamlNode = this.getNode();
      if (var3 is YamlScalar) {
         return new YamlScalarInput(this.getNode() as YamlScalar, this.getYaml(), this.getSerializersModule(), this.getConfiguration()).decodeChar();
      } else if (var3 is YamlNull) {
         throw new UnexpectedNullValueException((this.getNode() as YamlNull).getPath());
      } else {
         throw new IllegalStateException("Must call beginStructure() and use returned Decoder");
      }
   }

   public override fun decodeEnum(enumDescriptor: SerialDescriptor): Int {
      val var4: YamlNode = this.getNode();
      if (var4 is YamlScalar) {
         return new YamlScalarInput(this.getNode() as YamlScalar, this.getYaml(), this.getSerializersModule(), this.getConfiguration())
            .decodeEnum(enumDescriptor);
      } else if (var4 is YamlNull) {
         throw new UnexpectedNullValueException((this.getNode() as YamlNull).getPath());
      } else {
         throw new IllegalStateException("Must call beginStructure() and use returned Decoder");
      }
   }

   public override fun decodeElementIndex(descriptor: SerialDescriptor): Int {
      throw new IllegalStateException("Must call beginStructure() and use returned Decoder");
   }

   public override fun beginStructure(descriptor: SerialDescriptor): CompositeDecoder {
      return YamlInput.Companion.createFor$kaml(this.getNode(), this.getYaml(), this.getSerializersModule(), this.getConfiguration(), descriptor);
   }

   public override fun getCurrentLocation(): Location {
      return this.getNode().getLocation();
   }

   public override fun getCurrentPath(): YamlPath {
      return this.getNode().getPath();
   }

   private inline fun <T> delegateToYamlScalarInput(block: (YamlScalarInput) -> T): T {
      val var3: YamlNode = this.getNode();
      if (var3 is YamlScalar) {
         return (T)block.invoke(new YamlScalarInput(this.getNode() as YamlScalar, this.getYaml(), this.getSerializersModule(), this.getConfiguration()));
      } else if (var3 is YamlNull) {
         throw new UnexpectedNullValueException((this.getNode() as YamlNull).getPath());
      } else {
         throw new IllegalStateException("Must call beginStructure() and use returned Decoder");
      }
   }
}
