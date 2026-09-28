package com.charleskorn.kaml

public final val yamlScalar: YamlScalar
   public final get() {
      val var10000: YamlScalar = `$this$yamlScalar` as? YamlScalar;
      if ((`$this$yamlScalar` as? YamlScalar) == null) {
         error(`$this$yamlScalar`, "YamlScalar");
         throw new KotlinNothingValueException();
      } else {
         return var10000;
      }
   }


public final val yamlNull: YamlNull
   public final get() {
      val var10000: YamlNull = `$this$yamlNull` as? YamlNull;
      if ((`$this$yamlNull` as? YamlNull) == null) {
         error(`$this$yamlNull`, "YamlNull");
         throw new KotlinNothingValueException();
      } else {
         return var10000;
      }
   }


public final val yamlList: YamlList
   public final get() {
      val var10000: YamlList = `$this$yamlList` as? YamlList;
      if ((`$this$yamlList` as? YamlList) == null) {
         error(`$this$yamlList`, "YamlList");
         throw new KotlinNothingValueException();
      } else {
         return var10000;
      }
   }


public final val yamlMap: YamlMap
   public final get() {
      val var10000: YamlMap = `$this$yamlMap` as? YamlMap;
      if ((`$this$yamlMap` as? YamlMap) == null) {
         error(`$this$yamlMap`, "YamlMap");
         throw new KotlinNothingValueException();
      } else {
         return var10000;
      }
   }


public final val yamlTaggedNode: YamlTaggedNode
   public final get() {
      val var10000: YamlTaggedNode = `$this$yamlTaggedNode` as? YamlTaggedNode;
      if ((`$this$yamlTaggedNode` as? YamlTaggedNode) == null) {
         error(`$this$yamlTaggedNode`, "YamlTaggedNode");
         throw new KotlinNothingValueException();
      } else {
         return var10000;
      }
   }


private fun error(node: YamlNode, expectedType: String): Nothing {
   throw new IncorrectTypeException("Expected element to be $expectedType but is ${(node.getClass()::class).getSimpleName()}", node.getPath());
}
