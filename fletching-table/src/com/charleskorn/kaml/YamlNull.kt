package com.charleskorn.kaml

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable

@Serializable(with = YamlNullSerializer::class)
public data class YamlNull(path: YamlPath) : YamlNode(path) {
   public open val path: YamlPath

   init {
      this.path = path;
   }

   public override fun equivalentContentTo(other: YamlNode): Boolean {
      return other is YamlNull;
   }

   public override fun contentToString(): String {
      return "null";
   }

   public open fun withPath(newPath: YamlPath): YamlNull {
      return new YamlNull(newPath);
   }

   public override fun toString(): String {
      return "null @ ${this.getPath()}";
   }

   public operator fun component1(): YamlPath {
      return this.path;
   }

   public fun copy(path: YamlPath = this.path): YamlNull {
      return new YamlNull(path);
   }

   public override fun hashCode(): Int {
      return this.path.hashCode();
   }

   public override operator fun equals(other: Any?): Boolean {
      if (this === other) {
         return true;
      } else if (other !is YamlNull) {
         return false;
      } else {
         return this.path == (other as YamlNull).path;
      }
   }

   public companion object {
      public fun serializer(): KSerializer<YamlNull> {
         return YamlNullSerializer.INSTANCE;
      }
   }
}
