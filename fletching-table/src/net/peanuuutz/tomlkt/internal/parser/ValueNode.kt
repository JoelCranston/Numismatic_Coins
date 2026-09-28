package net.peanuuutz.tomlkt.internal.parser

import net.peanuuutz.tomlkt.TomlElement

internal class ValueNode(key: String, element: TomlElement) : TreeNode(key) {
   public final val element: TomlElement

   init {
      this.element = element;
   }
}
