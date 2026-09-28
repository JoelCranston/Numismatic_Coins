package it.krzeminski.snakeyaml.engine.kmp.nodes

import kotlin.enums.EnumEntries

public enum class NodeType {
   SCALAR,
   SEQUENCE,
   MAPPING,
   ANCHOR
   @JvmStatic
   fun getEntries(): EnumEntries<NodeType> {
      return $ENTRIES;
   }
}
