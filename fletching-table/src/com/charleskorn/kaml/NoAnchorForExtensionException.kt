package com.charleskorn.kaml

public class NoAnchorForExtensionException(key: String, extensionDefinitionPrefix: String, path: YamlPath) : YamlException(
      "The key '$key' starts with the extension definition prefix '$extensionDefinitionPrefix' but does not define an anchor.", path, null, 4
   ) {
   public final val key: String
   public final val extensionDefinitionPrefix: String

   init {
      this.key = key;
      this.extensionDefinitionPrefix = extensionDefinitionPrefix;
   }
}
