package it.krzeminski.snakeyaml.engine.kmp.constructor

import it.krzeminski.snakeyaml.engine.kmp.api.ConstructNode
import it.krzeminski.snakeyaml.engine.kmp.api.LoadSettings
import it.krzeminski.snakeyaml.engine.kmp.exceptions.ConstructorException
import it.krzeminski.snakeyaml.engine.kmp.exceptions.YamlEngineException
import it.krzeminski.snakeyaml.engine.kmp.nodes.MappingNode
import it.krzeminski.snakeyaml.engine.kmp.nodes.Node
import it.krzeminski.snakeyaml.engine.kmp.nodes.NodeTuple
import it.krzeminski.snakeyaml.engine.kmp.nodes.ScalarNode
import it.krzeminski.snakeyaml.engine.kmp.nodes.SequenceNode
import it.krzeminski.snakeyaml.engine.kmp.nodes.Tag
import java.util.ArrayList
import java.util.LinkedHashMap
import java.util.LinkedHashSet

public abstract class BaseConstructor {
   protected final val settings: LoadSettings
   protected abstract val tagConstructors: Map<Tag, ConstructNode>
   protected final val constructedObjects: MutableMap<Node, Any?>
   private final val recursiveObjects: MutableSet<Node>
   private final val maps2fill: MutableList<
      it.krzeminski.snakeyaml.engine.kmp.constructor.BaseConstructor.RecursiveTuple<
            MutableMap<Any?, Any?>,
            it.krzeminski.snakeyaml.engine.kmp.constructor.BaseConstructor.RecursiveTuple<Any?, Any?>
         >
   >
   private final val sets2fill: MutableList<it.krzeminski.snakeyaml.engine.kmp.constructor.BaseConstructor.RecursiveTuple<MutableSet<Any?>, Any?>>

   open fun BaseConstructor(settings: LoadSettings) {
      this.settings = settings;
      this.constructedObjects = new LinkedHashMap<>();
      this.recursiveObjects = new LinkedHashSet<>();
      this.maps2fill = new ArrayList<>();
      this.sets2fill = new ArrayList<>();
   }

   public fun constructSingleDocument(optionalNode: Node?): Any? {
      var var3: Any;
      if (optionalNode != null && !(Tag.NULL == optionalNode.getTag())) {
         var3 = (ConstructNode)this.construct(optionalNode);
      } else {
         var3 = this.getTagConstructors().get(Tag.NULL);
         if (var3 == null) {
            throw new IllegalStateException(("missing NULL constructor in tagConstructors ${this.getTagConstructors()}").toString());
         }

         var3 = (ConstructNode)var3.construct(optionalNode);
      }

      return var3;
   }

   public fun construct(node: Node): Any? {
      label22: {
         try {
            try {
               val data: Any = this.constructObject(node);
               this.fillRecursive();
            } catch (var4: YamlEngineException) {
               throw var4;
            } catch (var5: RuntimeException) {
               throw new YamlEngineException(var5);
            }
         } catch (var6: java.lang.Throwable) {
            this.constructedObjects.clear();
            this.recursiveObjects.clear();
         }

         this.constructedObjects.clear();
         this.recursiveObjects.clear();
      }
   }

   private fun fillRecursive() {
      if (!this.maps2fill.isEmpty()) {
         for (BaseConstructor.RecursiveTuple entry : this.maps2fill) {
            val var3: BaseConstructor.RecursiveTuple = value.getValue2() as BaseConstructor.RecursiveTuple;
            (value.getValue1() as java.util.Map).put(var3.component1(), var3.component2());
         }

         this.maps2fill.clear();
      }

      if (!this.sets2fill.isEmpty()) {
         for (BaseConstructor.RecursiveTuple value : this.sets2fill) {
            (var7.getValue1() as java.util.Set).add(var7.getValue2());
         }

         this.sets2fill.clear();
      }
   }

   protected fun constructObject(node: Node): Any? {
      var var10000: Any = this.constructedObjects.get(node);
      if (var10000 == null) {
         var10000 = this.constructObjectNoCheck(node);
      }

      return var10000;
   }

   private fun constructObjectNoCheck(node: Node): Any? {
      if (this.recursiveObjects.contains(node)) {
         throw new ConstructorException(null, null, "found unconstructable recursive node", node.getStartMark(), null, 16, null);
      } else {
         this.recursiveObjects.add(node);
         val var10000: ConstructNode = this.findConstructorFor(node);
         if (var10000 == null) {
            throw new ConstructorException(null, null, "could not determine a constructor for the tag ${node.getTag()}", node.getStartMark(), null, 16, null);
         } else {
            var var4: Any = this.constructedObjects.get(node);
            if (var4 == null) {
               var4 = var10000.construct(node);
            }

            this.constructedObjects.put(node, var4);
            this.recursiveObjects.remove(node);
            if (node.isRecursive()) {
               var10000.constructRecursive(node, var4);
            }

            return var4;
         }
      }
   }

   protected open fun findConstructorFor(node: Node): ConstructNode? {
      val tag: Tag = node.getTag();
      var var10000: ConstructNode = this.settings.getTagConstructors().get(tag);
      if (var10000 == null) {
         var10000 = this.getTagConstructors().get(tag);
      }

      return var10000;
   }

   protected fun constructScalar(node: ScalarNode): String {
      return node.getValue();
   }

   protected fun createEmptyListForNode(node: SequenceNode): List<Any?> {
      return this.settings.getDefaultList().invoke(node.getValue().size());
   }

   protected fun createEmptySetForNode(node: MappingNode): Set<Any?> {
      return this.settings.getDefaultSet().invoke(node.getValue().size());
   }

   protected fun createEmptyMapFor(node: MappingNode): Map<Any?, Any?> {
      return this.settings.getDefaultMap().invoke(node.getValue().size());
   }

   protected fun constructSequence(node: SequenceNode): List<Any?> {
      val result: java.util.List = this.settings.getDefaultList().invoke(node.getValue().size());
      this.constructSequenceStep2(node, result);
      return result;
   }

   protected fun constructSequenceStep2(node: SequenceNode, collection: MutableCollection<Any?>) {
      for (Node child : node.getValue()) {
         collection.add(this.constructObject(child));
      }
   }

   protected fun constructSet(node: MappingNode): Set<Any?> {
      val set: java.util.Set = this.settings.getDefaultSet().invoke(node.getValue().size());
      this.constructSet2ndStep(node, set);
      return set;
   }

   protected fun constructMapping(node: MappingNode): Map<Any?, Any?> {
      val mapping: java.util.Map = this.settings.getDefaultMap().invoke(node.getValue().size());
      this.constructMapping2ndStep(node, mapping);
      return mapping;
   }

   protected open fun constructMapping2ndStep(node: MappingNode, mapping: MutableMap<Any?, Any?>) {
      for (NodeTuple tuple : node.getValue()) {
         val keyNode: Node = tuple.getKeyNode();
         val valueNode: Node = tuple.getValueNode();
         val key: Any = this.constructObject(keyNode);
         if (key != null) {
            try {
               key.hashCode();
            } catch (var11: Exception) {
               throw new ConstructorException(
                  "while constructing a mapping", node.getStartMark(), "found unacceptable key $key", tuple.getKeyNode().getStartMark(), var11
               );
            }
         }

         val value: Any = this.constructObject(valueNode);
         if (keyNode.isRecursive()) {
            if (!this.settings.getAllowRecursiveKeys()) {
               throw new YamlEngineException("Recursive key for mapping is detected but it is not configured to be allowed.");
            }

            this.postponeMapFilling(mapping, key, value);
         } else {
            mapping.put(key, value);
         }
      }
   }

   private fun postponeMapFilling(mapping: MutableMap<Any?, Any?>, key: Any?, value: Any?) {
      this.maps2fill.add(0, new BaseConstructor.RecursiveTuple<>(mapping, new BaseConstructor.RecursiveTuple<>(key, value)));
   }

   protected open fun constructSet2ndStep(node: MappingNode, set: MutableSet<Any?>) {
      for (NodeTuple tuple : node.getValue()) {
         val keyNode: Node = tuple.getKeyNode();
         val key: Any = this.constructObject(keyNode);
         if (key != null) {
            try {
               key.hashCode();
            } catch (var9: Exception) {
               throw new ConstructorException(
                  "while constructing a Set", node.getStartMark(), "found unacceptable key $key", tuple.getKeyNode().getStartMark(), var9
               );
            }
         }

         if (keyNode.isRecursive()) {
            if (!this.settings.getAllowRecursiveKeys()) {
               throw new YamlEngineException("Recursive key for mapping is detected but it is not configured to be allowed.");
            }

            this.postponeSetFilling(set, key);
         } else {
            set.add(key);
         }
      }
   }

   private fun postponeSetFilling(set: MutableSet<Any?>, key: Any?) {
      this.sets2fill.add(0, new BaseConstructor.RecursiveTuple<>(set, key));
   }

   private data class RecursiveTuple<T, K>(value1: Any, value2: Any) {
      public final val value1: Any
      public final val value2: Any

      init {
         this.value1 = (T)value1;
         this.value2 = (K)value2;
      }

      public operator fun component1(): Any {
         return this.value1;
      }

      public operator fun component2(): Any {
         return this.value2;
      }

      public fun copy(value1: Any = this.value1, value2: Any = this.value2): it.krzeminski.snakeyaml.engine.kmp.constructor.BaseConstructor.RecursiveTuple<
            Any,
            Any
         > {
         return new BaseConstructor.RecursiveTuple<>((T)value1, (K)value2);
      }

      public override fun toString(): String {
         return "RecursiveTuple(value1=${this.value1}, value2=${this.value2})";
      }

      public override fun hashCode(): Int {
         return (if (this.value1 == null) 0 else this.value1.hashCode()) * 31 + (if (this.value2 == null) 0 else this.value2.hashCode());
      }

      public override operator fun equals(other: Any?): Boolean {
         if (this === other) {
            return true;
         } else if (other !is BaseConstructor.RecursiveTuple) {
            return false;
         } else {
            val var2: BaseConstructor.RecursiveTuple = other as BaseConstructor.RecursiveTuple;
            if (!(this.value1 == (other as BaseConstructor.RecursiveTuple).value1)) {
               return false;
            } else {
               return this.value2 == var2.value2;
            }
         }
      }
   }
}
