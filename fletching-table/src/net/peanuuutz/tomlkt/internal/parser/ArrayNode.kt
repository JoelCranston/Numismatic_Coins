package net.peanuuutz.tomlkt.internal.parser

import java.util.ArrayList

internal class ArrayNode(key: String) : TreeNode(key) {
   public final val children: MutableList<KeyNode> = (new ArrayList()) as java.util.List
   public final val annotations: MutableList<List<Annotation>> = (new ArrayList()) as java.util.List

   public fun add(child: KeyNode, childAnnotations: List<Annotation> = CollectionsKt.emptyList()) {
      this.children.add(child);
      this.annotations.add(childAnnotations);
   }

   public operator fun get(index: Int): KeyNode {
      return this.children.get(index);
   }
}
