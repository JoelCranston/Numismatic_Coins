package it.krzeminski.snakeyaml.engine.kmp.resolver

import it.krzeminski.snakeyaml.engine.kmp.nodes.Tag

public fun interface ScalarResolver {
   public abstract fun resolve(value: String, implicit: Boolean): Tag {
   }
}
