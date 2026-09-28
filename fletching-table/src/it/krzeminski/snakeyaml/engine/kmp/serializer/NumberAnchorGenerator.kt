package it.krzeminski.snakeyaml.engine.kmp.serializer

import it.krzeminski.snakeyaml.engine.kmp.common.Anchor
import it.krzeminski.snakeyaml.engine.kmp.nodes.Node
import kotlin.jvm.internal.SourceDebugExtension

@SourceDebugExtension(["SMAP\nNumberAnchorGenerator.kt\nKotlin\n*S Kotlin\n*F\n+ 1 NumberAnchorGenerator.kt\nit/krzeminski/snakeyaml/engine/kmp/serializer/NumberAnchorGenerator\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,42:1\n1#2:43\n*E\n"])
public class NumberAnchorGenerator(lastAnchorId: UInt = ...) : NumberAnchorGenerator(lastAnchorId), AnchorGenerator {
   private final var lastAnchorId: UInt

   fun NumberAnchorGenerator(lastAnchorId: Int) {
      this.lastAnchorId = lastAnchorId;
   }

   public override fun nextAnchor(node: Node): Anchor {
      val var10000: Anchor = node.getAnchor();
      if (var10000 != null) {
         return var10000;
      } else {
         this.lastAnchorId = UInt.constructor-impl(this.lastAnchorId + 1);
         return new Anchor("id${StringsKt.padStart(Integer.toUnsignedString(this.lastAnchorId), 3, '0')}");
      }
   }
}
