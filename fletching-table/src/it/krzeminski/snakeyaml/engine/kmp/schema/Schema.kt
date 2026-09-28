package it.krzeminski.snakeyaml.engine.kmp.schema

import it.krzeminski.snakeyaml.engine.kmp.api.ConstructNode
import it.krzeminski.snakeyaml.engine.kmp.nodes.Tag
import it.krzeminski.snakeyaml.engine.kmp.resolver.ScalarResolver

public interface Schema {
   public val scalarResolver: ScalarResolver
   public val schemaTagConstructors: Map<Tag, ConstructNode>
}
