package com.charleskorn.kaml

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable

@Serializable(with = YamlNodeSerializer::class)
public sealed class YamlNode protected constructor(path: YamlPath) {
   public open val path: YamlPath

   public final val location: Location
      public final get() {
         return this.getPath().getEndLocation();
      }


   init {
      this.path = path;
   }

   public abstract fun equivalentContentTo(other: YamlNode): Boolean {
   }

   public abstract fun contentToString(): String {
   }

   public abstract fun withPath(newPath: YamlPath): YamlNode {
   }

   protected fun replacePathOnChild(child: YamlNode, newParentPath: YamlPath): YamlPath {
      return new YamlPath(
         CollectionsKt.plus(newParentPath.getSegments(), CollectionsKt.drop(child.getPath().getSegments(), this.getPath().getSegments().size()))
      );
   }

   public companion object {
      public fun serializer(): KSerializer<YamlNode> {
         return YamlNodeSerializer.INSTANCE;
      }
   }
}
