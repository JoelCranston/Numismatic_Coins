package it.krzeminski.snakeyaml.engine.kmp.schema

import it.krzeminski.snakeyaml.engine.kmp.api.ConstructNode
import it.krzeminski.snakeyaml.engine.kmp.constructor.json.ConstructOptionalClass
import it.krzeminski.snakeyaml.engine.kmp.constructor.json.ConstructUuidClass
import it.krzeminski.snakeyaml.engine.kmp.nodes.Tag
import it.krzeminski.snakeyaml.engine.kmp.resolver.ScalarResolver

internal fun targetSchemaTagConstructors(scalarResolver: ScalarResolver): Map<Tag, ConstructNode> {
   return MapsKt.mapOf(
      new Pair[]{
         TuplesKt.to(Tag.Companion.forType("java.util.UUID"), new ConstructUuidClass()),
         TuplesKt.to(Tag.Companion.forType("java.util.Optional"), new ConstructOptionalClass(scalarResolver))
      }
   );
}
