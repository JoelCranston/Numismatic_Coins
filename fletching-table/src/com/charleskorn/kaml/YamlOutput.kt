package com.charleskorn.kaml

import it.krzeminski.snakeyaml.engine.kmp.api.DumpSettings
import it.krzeminski.snakeyaml.engine.kmp.api.StreamDataWriter
import it.krzeminski.snakeyaml.engine.kmp.comments.CommentType
import it.krzeminski.snakeyaml.engine.kmp.common.FlowStyle
import it.krzeminski.snakeyaml.engine.kmp.common.ScalarStyle
import it.krzeminski.snakeyaml.engine.kmp.emitter.Emitter
import it.krzeminski.snakeyaml.engine.kmp.events.CommentEvent
import it.krzeminski.snakeyaml.engine.kmp.events.DocumentEndEvent
import it.krzeminski.snakeyaml.engine.kmp.events.DocumentStartEvent
import it.krzeminski.snakeyaml.engine.kmp.events.ImplicitTuple
import it.krzeminski.snakeyaml.engine.kmp.events.MappingEndEvent
import it.krzeminski.snakeyaml.engine.kmp.events.MappingStartEvent
import it.krzeminski.snakeyaml.engine.kmp.events.ScalarEvent
import it.krzeminski.snakeyaml.engine.kmp.events.SequenceEndEvent
import it.krzeminski.snakeyaml.engine.kmp.events.SequenceStartEvent
import it.krzeminski.snakeyaml.engine.kmp.events.StreamEndEvent
import it.krzeminski.snakeyaml.engine.kmp.events.StreamStartEvent
import java.util.ArrayList
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.serialization.descriptors.PolymorphicKind
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.SerialKind
import kotlinx.serialization.descriptors.StructureKind
import kotlinx.serialization.encoding.AbstractEncoder
import kotlinx.serialization.encoding.CompositeEncoder
import kotlinx.serialization.modules.SerializersModule

@SourceDebugExtension(["SMAP\nYamlOutput.kt\nKotlin\n*S Kotlin\n*F\n+ 1 YamlOutput.kt\ncom/charleskorn/kaml/YamlOutput\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,253:1\n111#1,2:265\n113#1:278\n111#1,2:279\n113#1:292\n111#1,2:293\n113#1:306\n808#2,11:254\n808#2,11:267\n808#2,11:281\n808#2,11:295\n*S KotlinDebug\n*F\n+ 1 YamlOutput.kt\ncom/charleskorn/kaml/YamlOutput\n*L\n126#1:265,2\n126#1:278\n127#1:279,2\n127#1:292\n181#1:293,2\n181#1:306\n112#1:254,11\n126#1:267,11\n127#1:281,11\n181#1:295,11\n*E\n"])
internal class YamlOutput(writer: StreamDataWriter, serializersModule: SerializersModule, configuration: YamlConfiguration) : AbstractEncoder, AutoCloseable {
   public open val serializersModule: SerializersModule
   private final val configuration: YamlConfiguration
   private final val settings: DumpSettings
   private final val emitter: Emitter
   private final var shouldReadTypeName: Boolean
   private final var currentTypeName: String?
   private final var forcedSingleLineScalarStyle: SingleLineStringStyle?
   private final var forcedMultiLineScalarStyle: MultiLineStringStyle?

   private final val flowStyle: FlowStyle
      private final get() {
         var var10000: FlowStyle;
         switch (YamlOutput.WhenMappings.$EnumSwitchMapping$1[$this$flowStyle.ordinal()]) {
            case 1:
               var10000 = FlowStyle.BLOCK;
               break;
            case 2:
               var10000 = FlowStyle.FLOW;
               break;
            default:
               throw new NoWhenBranchMatchedException();
         }

         return var10000;
      }


   private final val scalarStyle: ScalarStyle
      private final get() {
         var var10000: ScalarStyle;
         switch (YamlOutput.WhenMappings.$EnumSwitchMapping$2[$this$scalarStyle.ordinal()]) {
            case 1:
               var10000 = ScalarStyle.DOUBLE_QUOTED;
               break;
            case 2:
               var10000 = ScalarStyle.SINGLE_QUOTED;
               break;
            case 3:
               var10000 = ScalarStyle.LITERAL;
               break;
            case 4:
               var10000 = ScalarStyle.FOLDED;
               break;
            case 5:
               var10000 = ScalarStyle.PLAIN;
               break;
            default:
               throw new NoWhenBranchMatchedException();
         }

         return var10000;
      }


   private final val scalarStyle: ScalarStyle
      private final get() {
         var var10000: ScalarStyle;
         switch (YamlOutput.WhenMappings.$EnumSwitchMapping$3[$this$scalarStyle.ordinal()]) {
            case 1:
               var10000 = ScalarStyle.DOUBLE_QUOTED;
               break;
            case 2:
               var10000 = ScalarStyle.SINGLE_QUOTED;
               break;
            case 3:
               var10000 = ScalarStyle.PLAIN;
               break;
            case 4:
               var10000 = ScalarStyle.PLAIN;
               break;
            default:
               throw new NoWhenBranchMatchedException();
         }

         return var10000;
      }


   private final val scalarStyle: ScalarStyle
      private final get() {
         var var10000: ScalarStyle;
         switch (YamlOutput.WhenMappings.$EnumSwitchMapping$4[$this$scalarStyle.ordinal()]) {
            case 1:
               var10000 = ScalarStyle.DOUBLE_QUOTED;
               break;
            case 2:
               var10000 = ScalarStyle.SINGLE_QUOTED;
               break;
            default:
               throw new NoWhenBranchMatchedException();
         }

         return var10000;
      }


   init {
      this.serializersModule = serializersModule;
      this.configuration = configuration;
      this.settings = new DumpSettings(
         false,
         false,
         null,
         null,
         null,
         null,
         null,
         null,
         null,
         null,
         false,
         false,
         false,
         this.configuration.getEncodingIndentationSize$kaml(),
         this.configuration.getSequenceBlockIndent$kaml(),
         this.configuration.getBreakScalarsAt$kaml(),
         null,
         false,
         0,
         null,
         this.configuration.getSequenceBlockIndent$kaml() > 0,
         true,
         false,
         5185535,
         null
      );
      this.emitter = new Emitter(this.settings, writer);
      this.emitter.emit(new StreamStartEvent());
      this.emitter.emit(new DocumentStartEvent(false, null, MapsKt.emptyMap(), null, null, 24, null));
   }

   public override fun shouldEncodeElementDefault(descriptor: SerialDescriptor, index: Int): Boolean {
      return this.configuration.getEncodeDefaults$kaml();
   }

   public override fun encodeNull() {
      this.emitPlainScalar("null");
   }

   public override fun encodeBoolean(value: Boolean) {
      this.emitPlainScalar(java.lang.String.valueOf(value));
   }

   public override fun encodeByte(value: Byte) {
      this.emitPlainScalar(java.lang.String.valueOf((int)value));
   }

   public override fun encodeChar(value: Char) {
      this.emitQuotedScalar(java.lang.String.valueOf(value), this.getScalarStyle(this.configuration.getSingleLineStringStyle$kaml()));
   }

   public override fun encodeDouble(value: Double) {
      this.emitPlainScalar(java.lang.String.valueOf(value));
   }

   public override fun encodeFloat(value: Float) {
      this.emitPlainScalar(java.lang.String.valueOf(value));
   }

   public override fun encodeInt(value: Int) {
      this.emitPlainScalar(java.lang.String.valueOf(value));
   }

   public override fun encodeLong(value: Long) {
      this.emitPlainScalar(java.lang.String.valueOf(value));
   }

   public override fun encodeShort(value: Short) {
      this.emitPlainScalar(java.lang.String.valueOf((int)value));
   }

   public override fun encodeString(value: String) {
      if (this.shouldReadTypeName) {
         this.currentTypeName = value;
         this.shouldReadTypeName = false;
      } else {
         var var10000: ScalarStyle;
         label32: {
            if (this.forcedSingleLineScalarStyle != null) {
               var10000 = this.getScalarStyle(this.forcedSingleLineScalarStyle);
               if (var10000 != null) {
                  break label32;
               }
            }

            var10000 = this.getScalarStyle(this.configuration.getSingleLineStringStyle$kaml());
         }

         label27: {
            if (this.forcedMultiLineScalarStyle != null) {
               var10000 = this.getScalarStyle(this.forcedMultiLineScalarStyle);
               if (var10000 != null) {
                  break label27;
               }
            }

            var10000 = this.getScalarStyle(this.configuration.getMultiLineStringStyle$kaml());
         }

         if (StringsKt.contains$default(value, '\n', false, 2, null)) {
            this.emitScalar(value, var10000);
         } else if (this.configuration.getSingleLineStringStyle$kaml() === SingleLineStringStyle.PlainExceptAmbiguous && this.isAmbiguous(value)) {
            this.emitQuotedScalar(value, this.getScalarStyle(this.configuration.getAmbiguousQuoteStyle$kaml()));
         } else {
            this.emitScalar(value, var10000);
         }
      }
   }

   public override fun encodeEnum(enumDescriptor: SerialDescriptor, index: Int) {
      this.emitQuotedScalar(enumDescriptor.getElementName(index), this.getScalarStyle(this.configuration.getSingleLineStringStyle$kaml()));
   }

   private fun emitPlainScalar(value: String) {
      this.emitScalar(value, ScalarStyle.PLAIN);
   }

   private fun emitQuotedScalar(value: String, scalarStyle: ScalarStyle) {
      this.emitScalar(value, scalarStyle);
   }

   public override fun encodeElement(descriptor: SerialDescriptor, index: Int): Boolean {
      this.encodeComment(descriptor, index);
      if (descriptor.getKind() is StructureKind.CLASS) {
         var var23: java.lang.String;
         label41: {
            val elementName: java.lang.String = descriptor.getElementName(index);
            val var10000: YamlNamingStrategy = this.configuration.getYamlNamingStrategy$kaml();
            if (var10000 != null) {
               var23 = var10000.serialNameForYaml(elementName);
               if (var23 != null) {
                  break label41;
               }
            }

            var23 = elementName;
         }

         this.emitPlainScalar(var23);
      }

      var `$this$filterIsInstance$iv$iv`: java.lang.Iterable = descriptor.getElementAnnotations(index);
      var `destination$iv$iv$iv`: java.util.Collection = new ArrayList();

      for (Object element$iv$iv$iv : $this$filterIsInstance$iv$iv) {
         if (`element$iv$iv$iv` is YamlSingleLineStringStyle) {
            `destination$iv$iv$iv`.add(`element$iv$iv$iv`);
         }
      }

      val var10001: YamlSingleLineStringStyle = CollectionsKt.firstOrNull(`destination$iv$iv$iv` as MutableList<YamlSingleLineStringStyle>);
      this.forcedSingleLineScalarStyle = if (var10001 != null) var10001.singleLineStringStyle() else null;
      `$this$filterIsInstance$iv$iv` = descriptor.getElementAnnotations(index);
      `destination$iv$iv$iv` = new ArrayList();

      for (Object element$iv$iv$ivx : $this$filterIsInstance$iv$iv) {
         if (`element$iv$iv$ivx` is YamlMultiLineStringStyle) {
            `destination$iv$iv$iv`.add(`element$iv$iv$ivx`);
         }
      }

      val var24: YamlMultiLineStringStyle = CollectionsKt.firstOrNull(`destination$iv$iv$iv` as MutableList<YamlMultiLineStringStyle>);
      this.forcedMultiLineScalarStyle = if (var24 != null) var24.multiLineStringStyle() else null;
      return super.encodeElement(descriptor, index);
   }

   public override fun beginStructure(descriptor: SerialDescriptor): CompositeEncoder {
      val var2: SerialKind = descriptor.getKind();
      if (var2 is PolymorphicKind) {
         this.shouldReadTypeName = true;
      } else if (var2 == StructureKind.LIST.INSTANCE) {
         this.emitter.emit(new SequenceStartEvent(null, null, true, this.getFlowStyle(this.configuration.getSequenceStyle$kaml()), null, null, 48, null));
      } else if (var2 == StructureKind.MAP.INSTANCE || var2 == StructureKind.CLASS.INSTANCE || var2 == StructureKind.OBJECT.INSTANCE) {
         val typeName: java.lang.String = this.getAndClearTypeName();
         switch (YamlOutput.WhenMappings.$EnumSwitchMapping$0[this.configuration.getPolymorphismStyle$kaml().ordinal()]) {
            case 1:
               this.emitter.emit(new MappingStartEvent(null, typeName, typeName == null, FlowStyle.BLOCK, null, null, 48, null));
               break;
            case 2:
               this.emitter.emit(new MappingStartEvent(null, null, true, FlowStyle.BLOCK, null, null, 48, null));
               if (typeName != null) {
                  this.emitPlainScalar(this.configuration.getPolymorphismPropertyName$kaml());
                  this.emitQuotedScalar(typeName, this.getScalarStyle(SingleLineStringStyle.DoubleQuoted));
               }
               break;
            case 3:
               this.emitter.emit(new MappingStartEvent(null, null, true, FlowStyle.BLOCK, null, null, 48, null));
               break;
            default:
               throw new NoWhenBranchMatchedException();
         }
      }

      return super.beginStructure(descriptor);
   }

   public override fun endStructure(descriptor: SerialDescriptor) {
      val var2: SerialKind = descriptor.getKind();
      if (var2 == StructureKind.LIST.INSTANCE) {
         this.emitter.emit(new SequenceEndEvent());
      } else if (var2 == StructureKind.MAP.INSTANCE || var2 == StructureKind.CLASS.INSTANCE || var2 == StructureKind.OBJECT.INSTANCE) {
         this.emitter.emit(new MappingEndEvent());
      }
   }

   public override fun close() {
      this.emitter.emit(new DocumentEndEvent(false, null, null, 6, null));
      this.emitter.emit(new StreamEndEvent());
   }

   private fun encodeComment(descriptor: SerialDescriptor, index: Int) {
      val `$this$filterIsInstance$iv$iv`: java.lang.Iterable = descriptor.getElementAnnotations(index);
      val `destination$iv$iv$iv`: java.util.Collection = new ArrayList();

      for (Object element$iv$iv$iv : $this$filterIsInstance$iv$iv) {
         if (`element$iv$iv$iv` is YamlComment) {
            `destination$iv$iv$iv`.add(`element$iv$iv$iv`);
         }
      }

      val var10000: YamlComment = CollectionsKt.firstOrNull(`destination$iv$iv$iv` as MutableList<YamlComment>);
      if (var10000 != null) {
         for (java.lang.String line : var10000.lines()) {
            this.emitter.emit(new CommentEvent(CommentType.BLOCK, " $line", null, null));
         }
      }
   }

   private fun emitScalar(value: String, style: ScalarStyle) {
      val tag: java.lang.String = this.getAndClearTypeName();
      if (tag != null && this.configuration.getPolymorphismStyle$kaml() != PolymorphismStyle.Tag) {
         throw new IllegalStateException(
            "Cannot serialize a polymorphic value that is not a YAML object when using ${(PolymorphismStyle::class).getSimpleName()}.${this.configuration
               .getPolymorphismStyle$kaml()}."
         );
      } else {
         this.emitter.emit(new ScalarEvent(null, tag, if (tag != null) ALL_EXPLICIT else ALL_IMPLICIT, value, style, null, null, 96, null));
      }
   }

   private fun getAndClearTypeName(): String? {
      val typeName: java.lang.String = this.currentTypeName;
      this.currentTypeName = null;
      return typeName;
   }

   private fun String.isAmbiguous(): Boolean {
      return `$this$isAmbiguous`.length() == 0
         || StringsKt.startsWith$default(`$this$isAmbiguous`, "0x", false, 2, null)
         || StringsKt.startsWith$default(`$this$isAmbiguous`, "0o", false, 2, null)
         || StringsKt.toDoubleOrNull(`$this$isAmbiguous`) != null
         || StringsKt.startsWith$default(`$this$isAmbiguous`, "#", false, 2, null)
         || CollectionsKt.listOf(
               new java.lang.String[]{
                  "~",
                  "-",
                  ".inf",
                  ".Inf",
                  ".INF",
                  "-.inf",
                  "-.Inf",
                  "-.INF",
                  ".nan",
                  ".NaN",
                  ".NAN",
                  "-.nan",
                  "-.NaN",
                  "-.NAN",
                  "null",
                  "Null",
                  "NULL",
                  "true",
                  "True",
                  "TRUE",
                  "false",
                  "False",
                  "FALSE"
               }
            )
            .contains(`$this$isAmbiguous`);
   }

   public companion object {
      private final val ALL_IMPLICIT: ImplicitTuple
      private final val ALL_EXPLICIT: ImplicitTuple
   }
}
