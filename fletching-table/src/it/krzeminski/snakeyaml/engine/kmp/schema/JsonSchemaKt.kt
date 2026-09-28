package it.krzeminski.snakeyaml.engine.kmp.schema

import it.krzeminski.snakeyaml.engine.kmp.api.ConstructNode
import it.krzeminski.snakeyaml.engine.kmp.constructor.ConstructYamlNull
import it.krzeminski.snakeyaml.engine.kmp.constructor.json.ConstructYamlBinary
import it.krzeminski.snakeyaml.engine.kmp.constructor.json.ConstructYamlJsonBool
import it.krzeminski.snakeyaml.engine.kmp.constructor.json.ConstructYamlJsonFloat
import it.krzeminski.snakeyaml.engine.kmp.constructor.json.ConstructYamlJsonInt
import it.krzeminski.snakeyaml.engine.kmp.nodes.Tag
import it.krzeminski.snakeyaml.engine.kmp.resolver.ScalarResolver

private fun defaultSchemaTagConstructors(scalarResolver: ScalarResolver): Map<Tag, ConstructNode> {
   val var1: java.util.Map = MapsKt.createMapBuilder();
   var1.put(Tag.NULL, new ConstructYamlNull());
   var1.put(Tag.BOOL, new ConstructYamlJsonBool());
   var1.put(Tag.INT, new ConstructYamlJsonInt());
   var1.put(Tag.FLOAT, new ConstructYamlJsonFloat());
   var1.put(Tag.BINARY, new ConstructYamlBinary());
   var1.putAll(JsonSchema_jvmKt.targetSchemaTagConstructors(scalarResolver));
   return MapsKt.build(var1);
}

@JvmSynthetic
fun `access$defaultSchemaTagConstructors`(scalarResolver: ScalarResolver): java.util.Map {
   return defaultSchemaTagConstructors(scalarResolver);
}
