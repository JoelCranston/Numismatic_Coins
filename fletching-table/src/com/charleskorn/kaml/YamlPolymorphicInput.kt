package com.charleskorn.kaml

import com.charleskorn.kaml.YamlPolymorphicInput.getKnownTypesForOpenType.1
import java.util.LinkedHashSet
import kotlin.enums.EnumEntries
import kotlin.jvm.internal.Intrinsics
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.SerializationException
import kotlinx.serialization.descriptors.PolymorphicKind
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptorKt
import kotlinx.serialization.descriptors.SerialKind
import kotlinx.serialization.encoding.CompositeDecoder
import kotlinx.serialization.modules.SerializersModule

@SourceDebugExtension(["SMAP\nYamlPolymorphicInput.kt\nKotlin\n*S Kotlin\n*F\n+ 1 YamlPolymorphicInput.kt\ncom/charleskorn/kaml/YamlPolymorphicInput\n*L\n1#1,167:1\n92#1,3:168\n89#1,6:171\n89#1,6:177\n89#1,6:183\n89#1,6:189\n89#1,6:195\n89#1,6:201\n89#1,6:207\n89#1,6:213\n89#1,6:219\n92#1,3:225\n89#1,6:228\n92#1,3:234\n*S KotlinDebug\n*F\n+ 1 YamlPolymorphicInput.kt\ncom/charleskorn/kaml/YamlPolymorphicInput\n*L\n64#1:168,3\n65#1:171,6\n66#1:177,6\n67#1:183,6\n68#1:189,6\n69#1:195,6\n70#1:201,6\n71#1:207,6\n72#1:213,6\n73#1:219,6\n74#1:225,3\n75#1:228,6\n89#1:234,3\n*E\n"])
internal class YamlPolymorphicInput(typeName: String,
   typeNamePath: YamlPath,
   contentNode: YamlNode,
   yaml: Yaml,
   context: SerializersModule,
   configuration: YamlConfiguration
) : YamlInput(contentNode, yaml, context, configuration) {
   public final val typeName: String
   private final val typeNamePath: YamlPath
   public final val contentNode: YamlNode
   private final var currentField: com.charleskorn.kaml.YamlPolymorphicInput.CurrentField
   private final lateinit var contentDecoder: YamlInput

   init {
      this.typeName = typeName;
      this.typeNamePath = typeNamePath;
      this.contentNode = contentNode;
      this.currentField = YamlPolymorphicInput.CurrentField.NotStarted;
   }

   public override fun getCurrentLocation(): Location {
      return this.contentNode.getLocation();
   }

   public override fun getCurrentPath(): YamlPath {
      return this.contentNode.getPath();
   }

   public override fun decodeElementIndex(descriptor: SerialDescriptor): Int {
      var var10000: Byte;
      switch (YamlPolymorphicInput.WhenMappings.$EnumSwitchMapping$0[this.currentField.ordinal()]) {
         case 1:
            this.currentField = YamlPolymorphicInput.CurrentField.Type;
            var10000 = 0;
            break;
         case 2:
            if (this.contentNode is YamlScalar) {
               this.contentDecoder = new YamlScalarInput(this.contentNode as YamlScalar, this.getYaml(), this.getSerializersModule(), this.getConfiguration());
            } else if (this.contentNode is YamlNull) {
               this.contentDecoder = new YamlNullInput(this.contentNode as YamlNull, this.getYaml(), this.getSerializersModule(), this.getConfiguration());
            }

            this.currentField = YamlPolymorphicInput.CurrentField.Content;
            var10000 = 1;
            break;
         case 3:
            var10000 = -1;
            break;
         default:
            throw new NoWhenBranchMatchedException();
      }

      return var10000;
   }

   public override fun decodeNotNullMark(): Boolean {
      var var6: Boolean;
      switch (YamlPolymorphicInput.WhenMappings.$EnumSwitchMapping$0[this.currentField.ordinal()]) {
         case 1:
         case 2:
            var6 = true;
            break;
         case 3:
            var var10000: YamlInput = this.contentDecoder;
            if (this.contentDecoder == null) {
               Intrinsics.throwUninitializedPropertyAccessException("contentDecoder");
               var10000 = null;
            }

            var6 = var10000.decodeNotNullMark();
            break;
         default:
            throw new NoWhenBranchMatchedException();
      }

      return var6;
   }

   public override fun decodeNull(): Nothing? {
      switch (YamlPolymorphicInput.WhenMappings.$EnumSwitchMapping$0[this.currentField.ordinal()]) {
         case 1:
         case 2:
            throw new UnsupportedOperationException("Can't call decodeNull() on type field");
         case 3:
            var var10000: YamlInput = this.contentDecoder;
            if (this.contentDecoder == null) {
               Intrinsics.throwUninitializedPropertyAccessException("contentDecoder");
               var10000 = null;
            }

            return var10000.decodeNull();
         default:
            throw new NoWhenBranchMatchedException();
      }
   }

   public override fun decodeBoolean(): Boolean {
      switch (YamlPolymorphicInput.WhenMappings.$EnumSwitchMapping$0[this.currentField.ordinal()]) {
         case 1:
         case 2:
            throw new UnsupportedOperationException("Can't call decodeBoolean() on type field");
         case 3:
            var var10000: YamlInput = this.contentDecoder;
            if (this.contentDecoder == null) {
               Intrinsics.throwUninitializedPropertyAccessException("contentDecoder");
               var10000 = null;
            }

            return var10000.decodeBoolean();
         default:
            throw new NoWhenBranchMatchedException();
      }
   }

   public override fun decodeByte(): Byte {
      switch (YamlPolymorphicInput.WhenMappings.$EnumSwitchMapping$0[this.currentField.ordinal()]) {
         case 1:
         case 2:
            throw new UnsupportedOperationException("Can't call decodeByte() on type field");
         case 3:
            var var10000: YamlInput = this.contentDecoder;
            if (this.contentDecoder == null) {
               Intrinsics.throwUninitializedPropertyAccessException("contentDecoder");
               var10000 = null;
            }

            return var10000.decodeByte();
         default:
            throw new NoWhenBranchMatchedException();
      }
   }

   public override fun decodeShort(): Short {
      switch (YamlPolymorphicInput.WhenMappings.$EnumSwitchMapping$0[this.currentField.ordinal()]) {
         case 1:
         case 2:
            throw new UnsupportedOperationException("Can't call decodeShort() on type field");
         case 3:
            var var10000: YamlInput = this.contentDecoder;
            if (this.contentDecoder == null) {
               Intrinsics.throwUninitializedPropertyAccessException("contentDecoder");
               var10000 = null;
            }

            return var10000.decodeShort();
         default:
            throw new NoWhenBranchMatchedException();
      }
   }

   public override fun decodeInt(): Int {
      switch (YamlPolymorphicInput.WhenMappings.$EnumSwitchMapping$0[this.currentField.ordinal()]) {
         case 1:
         case 2:
            throw new UnsupportedOperationException("Can't call decodeInt() on type field");
         case 3:
            var var10000: YamlInput = this.contentDecoder;
            if (this.contentDecoder == null) {
               Intrinsics.throwUninitializedPropertyAccessException("contentDecoder");
               var10000 = null;
            }

            return var10000.decodeInt();
         default:
            throw new NoWhenBranchMatchedException();
      }
   }

   public override fun decodeLong(): Long {
      switch (YamlPolymorphicInput.WhenMappings.$EnumSwitchMapping$0[this.currentField.ordinal()]) {
         case 1:
         case 2:
            throw new UnsupportedOperationException("Can't call decodeLong() on type field");
         case 3:
            var var10000: YamlInput = this.contentDecoder;
            if (this.contentDecoder == null) {
               Intrinsics.throwUninitializedPropertyAccessException("contentDecoder");
               var10000 = null;
            }

            return var10000.decodeLong();
         default:
            throw new NoWhenBranchMatchedException();
      }
   }

   public override fun decodeFloat(): Float {
      switch (YamlPolymorphicInput.WhenMappings.$EnumSwitchMapping$0[this.currentField.ordinal()]) {
         case 1:
         case 2:
            throw new UnsupportedOperationException("Can't call decodeFloat() on type field");
         case 3:
            var var10000: YamlInput = this.contentDecoder;
            if (this.contentDecoder == null) {
               Intrinsics.throwUninitializedPropertyAccessException("contentDecoder");
               var10000 = null;
            }

            return var10000.decodeFloat();
         default:
            throw new NoWhenBranchMatchedException();
      }
   }

   public override fun decodeDouble(): Double {
      switch (YamlPolymorphicInput.WhenMappings.$EnumSwitchMapping$0[this.currentField.ordinal()]) {
         case 1:
         case 2:
            throw new UnsupportedOperationException("Can't call decodeDouble() on type field");
         case 3:
            var var10000: YamlInput = this.contentDecoder;
            if (this.contentDecoder == null) {
               Intrinsics.throwUninitializedPropertyAccessException("contentDecoder");
               var10000 = null;
            }

            return var10000.decodeDouble();
         default:
            throw new NoWhenBranchMatchedException();
      }
   }

   public override fun decodeChar(): Char {
      switch (YamlPolymorphicInput.WhenMappings.$EnumSwitchMapping$0[this.currentField.ordinal()]) {
         case 1:
         case 2:
            throw new UnsupportedOperationException("Can't call decodeChar() on type field");
         case 3:
            var var10000: YamlInput = this.contentDecoder;
            if (this.contentDecoder == null) {
               Intrinsics.throwUninitializedPropertyAccessException("contentDecoder");
               var10000 = null;
            }

            return var10000.decodeChar();
         default:
            throw new NoWhenBranchMatchedException();
      }
   }

   public override fun decodeString(): String {
      var var6: java.lang.String;
      switch (YamlPolymorphicInput.WhenMappings.$EnumSwitchMapping$0[this.currentField.ordinal()]) {
         case 1:
         case 2:
            var6 = this.typeName;
            break;
         case 3:
            var var10000: YamlInput = this.contentDecoder;
            if (this.contentDecoder == null) {
               Intrinsics.throwUninitializedPropertyAccessException("contentDecoder");
               var10000 = null;
            }

            var6 = var10000.decodeString();
            break;
         default:
            throw new NoWhenBranchMatchedException();
      }

      return var6;
   }

   public override fun decodeEnum(enumDescriptor: SerialDescriptor): Int {
      switch (YamlPolymorphicInput.WhenMappings.$EnumSwitchMapping$0[this.currentField.ordinal()]) {
         case 1:
         case 2:
            throw new UnsupportedOperationException("Can't call decodeEnum() on type field");
         case 3:
            var var10000: YamlInput = this.contentDecoder;
            if (this.contentDecoder == null) {
               Intrinsics.throwUninitializedPropertyAccessException("contentDecoder");
               var10000 = null;
            }

            return var10000.decodeEnum(enumDescriptor);
         default:
            throw new NoWhenBranchMatchedException();
      }
   }

   public override fun beginStructure(descriptor: SerialDescriptor): CompositeDecoder {
      switch (YamlPolymorphicInput.WhenMappings.$EnumSwitchMapping$0[this.currentField.ordinal()]) {
         case 1:
         case 2:
            return super.beginStructure(descriptor);
         case 3:
            this.contentDecoder = YamlInput.Companion
               .createFor$kaml(this.contentNode, this.getYaml(), this.getSerializersModule(), this.getConfiguration(), descriptor);
            var var10000: YamlInput = this.contentDecoder;
            if (this.contentDecoder == null) {
               Intrinsics.throwUninitializedPropertyAccessException("contentDecoder");
               var10000 = null;
            }

            return var10000;
         default:
            throw new NoWhenBranchMatchedException();
      }
   }

   private inline fun <T> maybeCallOnContent(functionName: String, blockOnContent: (YamlInput) -> T): T {
      switch (YamlPolymorphicInput.WhenMappings.$EnumSwitchMapping$0[this.currentField.ordinal()]) {
         case 1:
         case 2:
            throw new UnsupportedOperationException("Can't call $functionName() on type field");
         case 3:
            var var10001: YamlInput = this.contentDecoder;
            if (this.contentDecoder == null) {
               Intrinsics.throwUninitializedPropertyAccessException("contentDecoder");
               var10001 = null;
            }

            return (T)blockOnContent.invoke(var10001);
         default:
            throw new NoWhenBranchMatchedException();
      }
   }

   private inline fun <T> maybeCallOnContent(blockOnType: () -> T, blockOnContent: (YamlInput) -> T): T {
      var var10000: Any;
      switch (YamlPolymorphicInput.WhenMappings.$EnumSwitchMapping$0[this.currentField.ordinal()]) {
         case 1:
         case 2:
            var10000 = blockOnType.invoke();
            break;
         case 3:
            var var10001: YamlInput = this.contentDecoder;
            if (this.contentDecoder == null) {
               Intrinsics.throwUninitializedPropertyAccessException("contentDecoder");
               var10001 = null;
            }

            var10000 = blockOnContent.invoke(var10001);
            break;
         default:
            throw new NoWhenBranchMatchedException();
      }

      return (T)var10000;
   }

   public override fun <T> decodeSerializableValue(deserializer: DeserializationStrategy<T>): T {
      try {
         return (T)super.decodeSerializableValue(deserializer);
      } catch (var3: SerializationException) {
         this.throwIfUnknownPolymorphicTypeException(var3, deserializer);
         throw var3;
      }
   }

   private fun throwIfUnknownPolymorphicTypeException(e: Exception, deserializer: DeserializationStrategy<*>) {
      val var10000: java.lang.String = e.getMessage();
      if (var10000 != null) {
         val var9: MatchResult = unknownPolymorphicTypeExceptionMessage.matchAt(var10000, 0);
         if (var9 != null) {
            val unknownType: java.lang.String = var9.getGroupValues().get(1);
            val className: java.lang.String = var9.getGroupValues().get(2);
            val var8: SerialKind = deserializer.getDescriptor().getKind();
            val var10: java.util.Set;
            if (var8 == PolymorphicKind.SEALED.INSTANCE) {
               var10 = this.getKnownTypesForSealedType(deserializer);
            } else {
               if (!(var8 == PolymorphicKind.OPEN.INSTANCE)) {
                  throw new IllegalArgumentException("Can't get known types for descriptor of kind ${deserializer.getDescriptor().getKind()}");
               }

               var10 = this.getKnownTypesForOpenType(className);
            }

            throw new UnknownPolymorphicTypeException(unknownType, var10, this.typeNamePath, e);
         }
      }
   }

   private fun getKnownTypesForSealedType(deserializer: DeserializationStrategy<*>): Set<String> {
      return CollectionsKt.toSet(SerialDescriptorKt.getElementNames(deserializer.getDescriptor().getElementDescriptor(1)));
   }

   private fun getKnownTypesForOpenType(className: String): Set<String> {
      val knownTypes: java.util.Set = new LinkedHashSet();
      this.getSerializersModule().dumpTo(new 1(className, knownTypes));
      return knownTypes;
   }

   public companion object {
      private final val unknownPolymorphicTypeExceptionMessage: Regex
   }

   private enum class CurrentField {
      NotStarted,
      Type,
      Content
      @JvmStatic
      fun getEntries(): EnumEntries<YamlPolymorphicInput.CurrentField> {
         return $ENTRIES;
      }
   }
}
