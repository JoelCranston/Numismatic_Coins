package it.krzeminski.snakeyaml.engine.kmp.schema

import it.krzeminski.snakeyaml.engine.kmp.api.ConstructNode
import it.krzeminski.snakeyaml.engine.kmp.nodes.Tag
import it.krzeminski.snakeyaml.engine.kmp.resolver.FailsafeScalarResolver
import it.krzeminski.snakeyaml.engine.kmp.resolver.ScalarResolver

public class FailsafeSchema : Schema {
   public open val scalarResolver: ScalarResolver = (new FailsafeScalarResolver()) as ScalarResolver
   public open val schemaTagConstructors: Map<Tag, ConstructNode> = MapsKt.emptyMap()
}
