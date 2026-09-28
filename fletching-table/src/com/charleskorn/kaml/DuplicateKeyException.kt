package com.charleskorn.kaml

public class DuplicateKeyException(originalPath: YamlPath, duplicatePath: YamlPath, key: String) : YamlException(
      "Duplicate key $key. It was previously given at line ${originalPath.getEndLocation().getLine()}, column ${originalPath.getEndLocation().getColumn()}.",
      duplicatePath,
      null,
      4
   ) {
   public final val originalPath: YamlPath
   public final val duplicatePath: YamlPath
   public final val key: String
   public final val originalLocation: Location
   public final val duplicateLocation: Location

   init {
      this.originalPath = originalPath;
      this.duplicatePath = duplicatePath;
      this.key = key;
      this.originalLocation = this.originalPath.getEndLocation();
      this.duplicateLocation = this.duplicatePath.getEndLocation();
   }
}
