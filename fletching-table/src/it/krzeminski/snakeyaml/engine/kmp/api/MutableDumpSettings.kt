package it.krzeminski.snakeyaml.engine.kmp.api

import it.krzeminski.snakeyaml.engine.kmp.common.FlowStyle
import it.krzeminski.snakeyaml.engine.kmp.common.NonPrintableStyle
import it.krzeminski.snakeyaml.engine.kmp.common.ScalarStyle
import it.krzeminski.snakeyaml.engine.kmp.common.SpecVersion
import it.krzeminski.snakeyaml.engine.kmp.nodes.Tag
import it.krzeminski.snakeyaml.engine.kmp.schema.Schema
import it.krzeminski.snakeyaml.engine.kmp.serializer.AnchorGenerator

public interface MutableDumpSettings {
   public var isExplicitStart: Boolean
      internal final set

   public var isExplicitEnd: Boolean
      internal final set

   public var explicitRootTag: Tag?
      internal final set

   public var anchorGenerator: AnchorGenerator
      internal final set

   public var yamlDirective: SpecVersion?
      internal final set

   public var tagDirective: Map<String, String>
      internal final set

   public var defaultFlowStyle: FlowStyle
      internal final set

   public var defaultScalarStyle: ScalarStyle
      internal final set

   public var nonPrintableStyle: NonPrintableStyle
      internal final set

   public var schema: Schema
      internal final set

   public var isCanonical: Boolean
      internal final set

   public var isMultiLineFlow: Boolean
      internal final set

   public var isUseUnicodeEncoding: Boolean
      internal final set

   public var indent: Int
      internal final set

   public var indicatorIndent: Int
      internal final set

   public var width: Int
      internal final set

   public var bestLineBreak: String
      internal final set

   public var isSplitLines: Boolean
      internal final set

   public var maxSimpleKeyLength: Int
      internal final set

   public var customProperties: Map<SettingKey, Any>
      internal final set

   public var indentWithIndicator: Boolean
      internal final set

   public var dumpComments: Boolean
      internal final set

   public var isDereferenceAliases: Boolean
      internal final set
}
