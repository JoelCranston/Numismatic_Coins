package it.krzeminski.snakeyaml.engine.kmp.api

import it.krzeminski.snakeyaml.engine.kmp.common.FlowStyle
import it.krzeminski.snakeyaml.engine.kmp.common.NonPrintableStyle
import it.krzeminski.snakeyaml.engine.kmp.common.ScalarStyle
import it.krzeminski.snakeyaml.engine.kmp.common.SpecVersion
import it.krzeminski.snakeyaml.engine.kmp.emitter.Emitter
import it.krzeminski.snakeyaml.engine.kmp.exceptions.EmitterException
import it.krzeminski.snakeyaml.engine.kmp.exceptions.YamlEngineException
import it.krzeminski.snakeyaml.engine.kmp.nodes.Tag
import it.krzeminski.snakeyaml.engine.kmp.schema.Schema
import it.krzeminski.snakeyaml.engine.kmp.schema.SchemaKt
import it.krzeminski.snakeyaml.engine.kmp.serializer.AnchorGenerator
import it.krzeminski.snakeyaml.engine.kmp.serializer.NumberAnchorGenerator

public class DumpSettings(isExplicitStart: Boolean = false,
   isExplicitEnd: Boolean = false,
   explicitRootTag: Tag? = null,
   anchorGenerator: AnchorGenerator = (new NumberAnchorGenerator(0, 1, null)) as AnchorGenerator,
   yamlDirective: SpecVersion? = null,
   tagDirective: Map<String, String> = MapsKt.emptyMap(),
   defaultFlowStyle: FlowStyle = FlowStyle.AUTO,
   defaultScalarStyle: ScalarStyle = ScalarStyle.PLAIN,
   nonPrintableStyle: NonPrintableStyle = NonPrintableStyle.ESCAPE,
   schema: Schema = SchemaKt.getDEFAULT_SCHEMA() as Schema,
   isCanonical: Boolean = false,
   isMultiLineFlow: Boolean = false,
   isUseUnicodeEncoding: Boolean = true,
   indent: Int = 2,
   indicatorIndent: Int = 0,
   width: Int = 80,
   bestLineBreak: String = "\n",
   isSplitLines: Boolean = true,
   maxSimpleKeyLength: Int = 128,
   customProperties: Map<SettingKey, Any> = MapsKt.emptyMap(),
   indentWithIndicator: Boolean = false,
   dumpComments: Boolean = false,
   isDereferenceAliases: Boolean = false
) {
   public final val isExplicitStart: Boolean
   public final val isExplicitEnd: Boolean
   public final val explicitRootTag: Tag?
   public final val anchorGenerator: AnchorGenerator
   public final val yamlDirective: SpecVersion?
   public final val tagDirective: Map<String, String>
   public final val defaultFlowStyle: FlowStyle
   public final val defaultScalarStyle: ScalarStyle
   public final val nonPrintableStyle: NonPrintableStyle
   public final val schema: Schema
   public final val isCanonical: Boolean
   public final val isMultiLineFlow: Boolean
   public final val isUseUnicodeEncoding: Boolean
   public final val indent: Int
   public final val indicatorIndent: Int
   public final val width: Int
   public final val bestLineBreak: String
   public final val isSplitLines: Boolean
   public final val maxSimpleKeyLength: Int
   public final val customProperties: Map<SettingKey, Any>
   public final val indentWithIndicator: Boolean
   public final val dumpComments: Boolean
   public final val isDereferenceAliases: Boolean

   init {
      this.isExplicitStart = isExplicitStart;
      this.isExplicitEnd = isExplicitEnd;
      this.explicitRootTag = explicitRootTag;
      this.anchorGenerator = anchorGenerator;
      this.yamlDirective = yamlDirective;
      this.tagDirective = tagDirective;
      this.defaultFlowStyle = defaultFlowStyle;
      this.defaultScalarStyle = defaultScalarStyle;
      this.nonPrintableStyle = nonPrintableStyle;
      this.schema = schema;
      this.isCanonical = isCanonical;
      this.isMultiLineFlow = isMultiLineFlow;
      this.isUseUnicodeEncoding = isUseUnicodeEncoding;
      this.indent = indent;
      this.indicatorIndent = indicatorIndent;
      this.width = width;
      this.bestLineBreak = bestLineBreak;
      this.isSplitLines = isSplitLines;
      this.maxSimpleKeyLength = maxSimpleKeyLength;
      this.customProperties = customProperties;
      this.indentWithIndicator = indentWithIndicator;
      this.dumpComments = dumpComments;
      this.isDereferenceAliases = isDereferenceAliases;
      var var24: IntRange = Emitter.VALID_INDICATOR_INDENT_RANGE;
      if (Emitter.VALID_INDICATOR_INDENT_RANGE.getFirst() > this.indicatorIndent || this.indicatorIndent > var24.getLast()) {
         throw new EmitterException("Indicator indent must be in range ${Emitter.VALID_INDICATOR_INDENT_RANGE}");
      } else if (this.maxSimpleKeyLength > 1024) {
         throw new YamlEngineException("The simple key must not span more than 1024 stream characters. See https://yaml.org/spec/1.2/spec.html#id2798057");
      } else {
         var24 = Emitter.VALID_INDENT_RANGE;
         if (Emitter.VALID_INDENT_RANGE.getFirst() > this.indent || this.indent > var24.getLast()) {
            throw new EmitterException("Indent must be at in range ${Emitter.VALID_INDENT_RANGE}");
         }
      }
   }

   fun DumpSettings() {
      this(false, false, null, null, null, null, null, null, null, null, false, false, false, 0, 0, 0, null, false, 0, null, false, false, false, 8388607, null);
   }
}
