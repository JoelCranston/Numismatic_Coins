package it.krzeminski.snakeyaml.engine.kmp.api

import it.krzeminski.snakeyaml.engine.kmp.exceptions.YamlEngineException
import it.krzeminski.snakeyaml.engine.kmp.nodes.Node
import kotlin.jvm.internal.SourceDebugExtension

@SourceDebugExtension(["SMAP\nConstructNode.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ConstructNode.kt\nit/krzeminski/snakeyaml/engine/kmp/api/ConstructNode\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,49:1\n1#2:50\n*E\n"])
public interface ConstructNode {
   public abstract fun construct(node: Node?): Any? {
   }

   public open fun constructRecursive(node: Node, `object`: Any) {
      if (node.isRecursive()) {
         throw new IllegalStateException(("Not implemented in ${this.getClass()::class}").toString());
      } else {
         throw new YamlEngineException("Unexpected recursive structure for Node: $node");
      }
   }

   // $VF: Class flags could not be determined
   internal class DefaultImpls {
      @Deprecated
      @JvmStatic
      fun constructRecursive(`$this`: ConstructNode, node: Node, `object`: Any) {
         ConstructNode.access$constructRecursive$jd(`$this`, node, `object`);
      }
   }
}
