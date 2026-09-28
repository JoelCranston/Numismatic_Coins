package net.peanuuutz.tomlkt.internal.parser

import java.util.LinkedHashMap

internal class KeyNode(key: String, isLast: Boolean) : TreeNode(key) {
   public final val isLast: Boolean
   public final val children: MutableMap<String, TreeNode>
   public final val annotations: MutableMap<String, List<Annotation>>

   init {
      this.isLast = isLast;
      this.children = new LinkedHashMap<>();
      this.annotations = new LinkedHashMap<>();
   }

   public fun add(child: TreeNode, childAnnotations: List<Annotation> = CollectionsKt.emptyList()) {
      this.children.put(child.getKey(), child);
      this.annotations.put(child.getKey(), childAnnotations);
   }

   public operator fun get(key: String): TreeNode? {
      return this.children.get(key);
   }
}
