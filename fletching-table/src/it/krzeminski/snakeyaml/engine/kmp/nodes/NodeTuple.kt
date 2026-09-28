package it.krzeminski.snakeyaml.engine.kmp.nodes

public class NodeTuple(keyNode: Node, valueNode: Node) {
   public final val keyNode: Node
   public final val valueNode: Node

   init {
      this.keyNode = keyNode;
      this.valueNode = valueNode;
   }

   public operator fun component1(): Node {
      return this.keyNode;
   }

   public operator fun component2(): Node {
      return this.valueNode;
   }

   public override fun toString(): String {
      return "<NodeTuple keyNode=${this.keyNode}; valueNode=${this.valueNode}>";
   }
}
