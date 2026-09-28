package it.krzeminski.snakeyaml.engine.kmp.api

import it.krzeminski.snakeyaml.engine.kmp.api.LoadSettingsCopyDslKt.copy.result.1

public fun LoadSettings.copy(modifications: (MutableLoadSettings) -> Unit): LoadSettings {
   val var3: 1 = new 1(`$this$copy`);
   modifications.invoke(var3);
   return new LoadSettings(
      var3.getLabel(),
      var3.getTagConstructors(),
      var3.getDefaultList(),
      var3.getDefaultSet(),
      var3.getDefaultMap(),
      var3.getVersionFunction(),
      var3.getBufferSize(),
      var3.getAllowDuplicateKeys(),
      var3.getAllowRecursiveKeys(),
      var3.getMaxAliasesForCollections(),
      var3.getUseMarks(),
      var3.getCustomProperties(),
      var3.getEnvConfig(),
      var3.getParseComments(),
      var3.getCodePointLimit(),
      var3.getSchema()
   );
}
