package com.charleskorn.kaml

private data class WeightedNode(node: YamlNode, weight: UInt) : WeightedNode(node, weight) {
   public final val node: YamlNode
   public final val weight: UInt

   fun WeightedNode(node: YamlNode, weight: Int) {
      this.node = node;
      this.weight = weight;
   }

   public operator fun component1(): YamlNode {
      return this.node;
   }

   public operator fun component2(): UInt {
      return this.weight;
   }

   public fun copy(node: YamlNode = ..., weight: UInt = ...): WeightedNode {
      return new WeightedNode(node, var2, null);
   }

   public override fun toString(): String {
      return "WeightedNode(node=${this.node}, weight=${UInt.toString-impl(this.weight)})";
   }

   public override fun hashCode(): Int {
      return this.node.hashCode() * 31 + UInt.hashCode-impl(this.weight);
   }

   public override operator fun equals(other: Any?): Boolean {
      if (this === other) {
         return true;
      } else if (other !is WeightedNode) {
         return false;
      } else {
         val var2: WeightedNode = other as WeightedNode;
         if (!(this.node == (other as WeightedNode).node)) {
            return false;
         } else {
            return this.weight == var2.weight;
         }
      }
   }
}
