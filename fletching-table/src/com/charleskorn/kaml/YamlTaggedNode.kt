package com.charleskorn.kaml

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable

@Serializable(with = YamlTaggedNodeSerializer::class)
public data class YamlTaggedNode(tag: String, innerNode: YamlNode) : YamlNode(innerNode.getPath()) {
   public final val tag: String
   public final val innerNode: YamlNode

   init {
      this.tag = tag;
      this.innerNode = innerNode;
   }

   public override fun equivalentContentTo(other: YamlNode): Boolean {
      if (other !is YamlTaggedNode) {
         return false;
      } else {
         return this.tag == (other as YamlTaggedNode).tag && this.innerNode.equivalentContentTo((other as YamlTaggedNode).innerNode);
      }
   }

   public override fun contentToString(): String {
      return "!${this.tag} ${this.innerNode.contentToString()}";
   }

   public override fun withPath(newPath: YamlPath): YamlNode {
      return copy$default(this, null, this.innerNode.withPath(newPath), 1, null);
   }

   public override fun toString(): String {
      return "tagged '${this.tag}': ${this.innerNode}";
   }

   public operator fun component1(): String {
      return this.tag;
   }

   public operator fun component2(): YamlNode {
      return this.innerNode;
   }

   public fun copy(tag: String = this.tag, innerNode: YamlNode = this.innerNode): YamlTaggedNode {
      return new YamlTaggedNode(tag, innerNode);
   }

   public override fun hashCode(): Int {
      return this.tag.hashCode() * 31 + this.innerNode.hashCode();
   }

   public override operator fun equals(other: Any?): Boolean {
      if (this === other) {
         return true;
      } else if (other !is YamlTaggedNode) {
         return false;
      } else {
         val var2: YamlTaggedNode = other as YamlTaggedNode;
         if (!(this.tag == (other as YamlTaggedNode).tag)) {
            return false;
         } else {
            return this.innerNode == var2.innerNode;
         }
      }
   }

   public companion object {
      public fun serializer(): KSerializer<YamlTaggedNode> {
         return YamlTaggedNodeSerializer.INSTANCE;
      }
   }
}
