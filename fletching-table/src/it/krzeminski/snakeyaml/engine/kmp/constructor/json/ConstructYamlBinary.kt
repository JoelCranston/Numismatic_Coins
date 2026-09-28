package it.krzeminski.snakeyaml.engine.kmp.constructor.json

import it.krzeminski.snakeyaml.engine.kmp.constructor.ConstructScalar
import it.krzeminski.snakeyaml.engine.kmp.nodes.Node
import okio.ByteString

public class ConstructYamlBinary : ConstructScalar {
   public open fun construct(node: Node?): ByteArray {
      val var10000: ByteString = ByteString.Companion.decodeBase64(ConstructYamlBinaryKt.access$getSPACES_PATTERN$p().replace(this.constructScalar(node), ""));
      if (var10000 != null) {
         val var6: ByteArray = var10000.toByteArray();
         if (var6 != null) {
            return var6;
         }
      }

      return new byte[0];
   }
}
