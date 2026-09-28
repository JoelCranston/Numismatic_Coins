package it.krzeminski.snakeyaml.engine.kmp.api

import it.krzeminski.snakeyaml.engine.kmp.api.DumpSettingsCopyDslKt.copy.result.1

public fun DumpSettings.copy(modifications: (MutableDumpSettings) -> Unit): DumpSettings {
   val var3: 1 = new 1(`$this$copy`);
   modifications.invoke(var3);
   return new DumpSettings(
      var3.isExplicitStart(),
      var3.isExplicitEnd(),
      var3.getExplicitRootTag(),
      var3.getAnchorGenerator(),
      var3.getYamlDirective(),
      var3.getTagDirective(),
      var3.getDefaultFlowStyle(),
      var3.getDefaultScalarStyle(),
      var3.getNonPrintableStyle(),
      var3.getSchema(),
      var3.isCanonical(),
      var3.isMultiLineFlow(),
      var3.isUseUnicodeEncoding(),
      var3.getIndent(),
      var3.getIndicatorIndent(),
      var3.getWidth(),
      var3.getBestLineBreak(),
      var3.isSplitLines(),
      var3.getMaxSimpleKeyLength(),
      var3.getCustomProperties(),
      var3.getIndentWithIndicator(),
      var3.getDumpComments(),
      var3.isDereferenceAliases()
   );
}
