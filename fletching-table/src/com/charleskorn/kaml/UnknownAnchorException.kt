package com.charleskorn.kaml

public class UnknownAnchorException(anchorName: String, path: YamlPath) : YamlException("Unknown anchor '$anchorName'.", path, null, 4) {
   public final val anchorName: String

   init {
      this.anchorName = anchorName;
   }
}
