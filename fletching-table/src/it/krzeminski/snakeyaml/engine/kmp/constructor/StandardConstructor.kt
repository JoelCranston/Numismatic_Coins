package it.krzeminski.snakeyaml.engine.kmp.constructor

import it.krzeminski.snakeyaml.engine.kmp.api.ConstructNode
import it.krzeminski.snakeyaml.engine.kmp.api.LoadSettings
import it.krzeminski.snakeyaml.engine.kmp.constructor.StandardConstructor.validateDuplicateKeys..inlined.groupingBy.1
import it.krzeminski.snakeyaml.engine.kmp.env.EnvConfig
import it.krzeminski.snakeyaml.engine.kmp.exceptions.ConstructorException
import it.krzeminski.snakeyaml.engine.kmp.exceptions.DuplicateKeyException
import it.krzeminski.snakeyaml.engine.kmp.exceptions.Mark
import it.krzeminski.snakeyaml.engine.kmp.exceptions.MissingEnvironmentVariableException
import it.krzeminski.snakeyaml.engine.kmp.exceptions.YamlEngineException
import it.krzeminski.snakeyaml.engine.kmp.internal.System_jvmKt
import it.krzeminski.snakeyaml.engine.kmp.nodes.MappingNode
import it.krzeminski.snakeyaml.engine.kmp.nodes.Node
import it.krzeminski.snakeyaml.engine.kmp.nodes.NodeTuple
import it.krzeminski.snakeyaml.engine.kmp.nodes.SequenceNode
import it.krzeminski.snakeyaml.engine.kmp.nodes.Tag
import it.krzeminski.snakeyaml.engine.kmp.resolver.BaseScalarResolver
import java.util.LinkedHashMap
import kotlin.jvm.internal.SourceDebugExtension
import kotlin.jvm.internal.TypeIntrinsics

@SourceDebugExtension(["SMAP\nStandardConstructor.kt\nKotlin\n*S Kotlin\n*F\n+ 1 StandardConstructor.kt\nit/krzeminski/snakeyaml/engine/kmp/constructor/StandardConstructor\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 Grouping.kt\nkotlin/collections/GroupingKt__GroupingKt\n*L\n1#1,242:1\n1550#2:243\n211#3:244\n53#3:245\n80#3,4:246\n213#3:250\n85#3:251\n214#3:252\n*S KotlinDebug\n*F\n+ 1 StandardConstructor.kt\nit/krzeminski/snakeyaml/engine/kmp/constructor/StandardConstructor\n*L\n65#1:243\n69#1:244\n69#1:245\n69#1:246,4\n69#1:250\n69#1:251\n69#1:252\n*E\n"])
public open class StandardConstructor(settings: LoadSettings) : BaseConstructor(settings) {
   protected open val tagConstructors: Map<Tag, ConstructNode>

   init {
      val var2: java.util.Map = MapsKt.createMapBuilder();
      var2.put(Tag.SET, new StandardConstructor.ConstructYamlSet(this));
      var2.put(Tag.STR, new StandardConstructor.ConstructYamlStr(this));
      var2.put(Tag.SEQ, new StandardConstructor.ConstructYamlSeq(this));
      var2.put(Tag.MAP, new StandardConstructor.ConstructYamlMap(this));
      var2.put(Tag.ENV_TAG, new StandardConstructor.ConstructEnv(this));
      var2.putAll(settings.getSchema().getSchemaTagConstructors());
      var2.putAll(settings.getTagConstructors());
      this.tagConstructors = MapsKt.build(var2);
   }

   protected override fun constructMapping2ndStep(node: MappingNode, mapping: MutableMap<Any?, Any?>) {
      this.validateDuplicateKeys(node);
      super.constructMapping2ndStep(node, mapping);
   }

   protected override fun constructSet2ndStep(node: MappingNode, set: MutableSet<Any?>) {
      this.validateDuplicateKeys(node);
      super.constructSet2ndStep(node, set);
   }

   private fun validateDuplicateKeys(node: MappingNode) {
      if (!this.settings.getAllowDuplicateKeys()) {
         val groupedByKey: Grouping = new 1(node.getValue(), this, node);
         val `$this$aggregateTo$iv$iv$iv`: Grouping = groupedByKey;
         val `destination$iv$iv$iv`: java.util.Map = new LinkedHashMap();
         val var10: java.util.Iterator = groupedByKey.sourceIterator();

         while (var10.hasNext()) {
            val `e$iv$iv$iv`: Any = var10.next();
            val `key$iv$iv$iv`: Any = `$this$aggregateTo$iv$iv$iv`.keyOf(`e$iv$iv$iv`);
            if (`destination$iv$iv$iv`.get(`key$iv$iv$iv`) != null || `destination$iv$iv$iv`.containsKey(`key$iv$iv$iv`)) {
               val tuple: NodeTuple = `e$iv$iv$iv` as NodeTuple;
               val var10002: Mark = node.getStartMark();
               throw new DuplicateKeyException(var10002, `key$iv$iv$iv`, tuple.getKeyNode().getStartMark());
            }

            `destination$iv$iv$iv`.put(`key$iv$iv$iv`, `e$iv$iv$iv`);
         }
      }
   }

   private fun constructKey(keyNode: Node, contextMark: Mark?, problemMark: Mark?): Any? {
      val key: Any = this.constructObject(keyNode);

      try {
         if (key != null) {
            key.hashCode();
         }

         return key;
      } catch (var6: Exception) {
         throw new ConstructorException("while constructing a mapping", contextMark, "found unacceptable key $key", problemMark, var6);
      }
   }

   public inner class ConstructEnv : ConstructScalar {
      public override fun construct(node: Node?): Any {
         val scalar: java.lang.String = this.constructScalar(node);
         val config: EnvConfig = this.this$0.settings.getEnvConfig();
         var var11: java.lang.String;
         if (config != null) {
            val var10000: MatchResult = BaseScalarResolver.ENV_FORMAT.matchEntire(scalar);
            if (var10000 == null) {
               throw new IllegalStateException("failed to match scalar".toString());
            }

            val var5: MatchResult.Destructured = var10000.getDestructured();
            val name: java.lang.String = var5.getMatch().getGroupValues().get(1);
            val separator: java.lang.String = var5.getMatch().getGroupValues().get(2);
            val value: java.lang.String = var5.getMatch().getGroupValues().get(3);
            val env: java.lang.String = System_jvmKt.getEnvironmentVariable(name);
            val overruled: java.lang.String = config.getValueFor(name, separator, value, env);
            var11 = overruled;
            if (overruled == null) {
               var11 = this.apply(name, separator, value, env);
            }
         } else {
            var11 = scalar;
         }

         return var11;
      }

      private fun apply(name: String, separator: String?, value: String, environment: String?): String {
         if (environment != null && environment.length() != 0) {
            return environment;
         } else if (separator == null) {
            return "";
         } else {
            switch (separator.hashCode()) {
               case 45:
                  if (separator.equals("-")) {
                     return if (environment == null) value else "";
                  }
                  break;
               case 63:
                  if (separator.equals("?")) {
                     if (environment == null) {
                        throw MissingEnvironmentVariableException.Companion.forMissingVariable$snakeyaml_engine_kmp(name, value);
                     }

                     return "";
                  }
                  break;
               case 1843:
                  if (separator.equals(":-")) {
                     return if (environment as java.lang.CharSequence == null || environment.length() == 0) value else "";
                  }
                  break;
               case 1861:
                  if (separator.equals(":?")) {
                     if (environment == null) {
                        throw MissingEnvironmentVariableException.Companion.forMissingVariable$snakeyaml_engine_kmp(name, value);
                     }

                     if (environment.length() == 0) {
                        throw MissingEnvironmentVariableException.Companion.forEmptyVariable$snakeyaml_engine_kmp(name, value);
                     }

                     return value;
                  }
               default:
            }

            return "";
         }
      }
   }

   public inner class ConstructYamlMap : ConstructNode {
      public open fun construct(node: Node?): Map<*, *> {
         return if ((node as MappingNode).isRecursive())
            this.this$0.createEmptyMapFor(node as MappingNode)
            else
            this.this$0.constructMapping(node as MappingNode);
      }

      public override fun constructRecursive(node: Node, `object`: Any) {
         if (node.isRecursive()) {
            this.this$0.constructMapping2ndStep(node as MappingNode, TypeIntrinsics.asMutableMap(`object`));
         } else {
            throw new YamlEngineException("Unexpected recursive mapping structure. Node: $node");
         }
      }
   }

   public inner class ConstructYamlSeq : ConstructNode {
      public open fun construct(node: Node?): List<*> {
         return if ((node as SequenceNode).isRecursive())
            this.this$0.createEmptyListForNode(node as SequenceNode)
            else
            this.this$0.constructSequence(node as SequenceNode);
      }

      public override fun constructRecursive(node: Node, `object`: Any) {
         if (node.isRecursive()) {
            this.this$0.constructSequenceStep2(node as SequenceNode, TypeIntrinsics.asMutableList(`object`));
         } else {
            throw new YamlEngineException("Unexpected recursive sequence structure. Node: $node");
         }
      }
   }

   public inner class ConstructYamlSet : ConstructNode {
      public override fun construct(node: Node?): Any {
         if (node !is MappingNode) {
            throw new IllegalArgumentException("Failed requirement.".toString());
         } else {
            var var10000: Any;
            if ((node as MappingNode).isRecursive()) {
               var10000 = this.this$0.constructedObjects.get(node);
               if (var10000 == null) {
                  var10000 = this.this$0.createEmptySetForNode(node as MappingNode);
               }
            } else {
               var10000 = this.this$0.constructSet(node as MappingNode);
            }

            return var10000;
         }
      }

      public override fun constructRecursive(node: Node, `object`: Any) {
         if (node.isRecursive()) {
            this.this$0.constructSet2ndStep(node as MappingNode, TypeIntrinsics.asMutableSet(`object`));
         } else {
            throw new YamlEngineException("Unexpected recursive set structure. Node: $node");
         }
      }
   }

   public inner class ConstructYamlStr : ConstructScalar {
      public open fun construct(node: Node?): String {
         return this.constructScalar(node);
      }
   }
}
