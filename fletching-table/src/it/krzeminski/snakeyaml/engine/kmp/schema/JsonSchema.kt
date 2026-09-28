package it.krzeminski.snakeyaml.engine.kmp.schema

import it.krzeminski.snakeyaml.engine.kmp.api.ConstructNode
import it.krzeminski.snakeyaml.engine.kmp.nodes.Tag
import it.krzeminski.snakeyaml.engine.kmp.resolver.JsonScalarResolver
import it.krzeminski.snakeyaml.engine.kmp.resolver.ScalarResolver

public open class JsonSchema @JvmOverloads  public constructor(scalarResolver: ScalarResolver = (new JsonScalarResolver()) as ScalarResolver,
      schemaTagConstructors: Map<Tag, ConstructNode> = JsonSchemaKt.access$defaultSchemaTagConstructors(scalarResolver)
   ) :
   Schema {
   public open val scalarResolver: ScalarResolver
   public open val schemaTagConstructors: Map<Tag, ConstructNode>

   init {
      this.scalarResolver = scalarResolver;
      this.schemaTagConstructors = schemaTagConstructors;
   }

   @JvmOverloads
   open fun JsonSchema(scalarResolver: ScalarResolver) {
      this(scalarResolver, null, 2, null);
   }

   @JvmOverloads
   open fun JsonSchema() {
      this(null, null, 3, null);
   }
}
