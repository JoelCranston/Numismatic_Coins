package dev.kikugie.fletching_table.transformer.language.visitor

import com.charleskorn.kaml.YamlList
import com.charleskorn.kaml.YamlMap
import com.charleskorn.kaml.YamlNode
import com.charleskorn.kaml.YamlNull
import com.charleskorn.kaml.YamlScalar
import com.charleskorn.kaml.YamlTaggedNode
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonNull
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import net.peanuuutz.tomlkt.TomlArray
import net.peanuuutz.tomlkt.TomlElement
import net.peanuuutz.tomlkt.TomlLiteral
import net.peanuuutz.tomlkt.TomlNull
import net.peanuuutz.tomlkt.TomlTable

internal fun <T> TomlElement.accept(visitor: TomlVisitor<T>): T {
   val var10000: Any;
   if (`$this$accept` is TomlNull) {
      var10000 = visitor.visitNull(`$this$accept` as TomlNull);
   } else if (`$this$accept` is TomlLiteral) {
      var10000 = visitor.visitLiteral(`$this$accept` as TomlLiteral);
   } else if (`$this$accept` is TomlArray) {
      var10000 = visitor.visitArray(`$this$accept` as TomlArray);
   } else {
      if (`$this$accept` !is TomlTable) {
         throw new NoWhenBranchMatchedException();
      }

      var10000 = visitor.visitTable(`$this$accept` as TomlTable);
   }

   return (T)var10000;
}

internal fun <T> JsonElement.accept(visitor: JsonVisitor<T>): T {
   val var10000: Any;
   if (`$this$accept` is JsonNull) {
      var10000 = visitor.visitNull(`$this$accept` as JsonNull);
   } else if (`$this$accept` is JsonPrimitive) {
      var10000 = visitor.visitPrimitive(`$this$accept` as JsonPrimitive);
   } else if (`$this$accept` is JsonArray) {
      var10000 = visitor.visitArray(`$this$accept` as JsonArray);
   } else {
      if (`$this$accept` !is JsonObject) {
         throw new NoWhenBranchMatchedException();
      }

      var10000 = visitor.visitObject(`$this$accept` as JsonObject);
   }

   return (T)var10000;
}

internal fun <T> YamlNode.accept(visitor: YamlVisitor<T>): T {
   val var10000: Any;
   if (`$this$accept` is YamlNull) {
      var10000 = visitor.visitNull(`$this$accept` as YamlNull);
   } else if (`$this$accept` is YamlScalar) {
      var10000 = visitor.visitScalar(`$this$accept` as YamlScalar);
   } else if (`$this$accept` is YamlList) {
      var10000 = visitor.visitList(`$this$accept` as YamlList);
   } else if (`$this$accept` is YamlMap) {
      var10000 = visitor.visitMap(`$this$accept` as YamlMap);
   } else {
      if (`$this$accept` !is YamlTaggedNode) {
         throw new NoWhenBranchMatchedException();
      }

      var10000 = visitor.visitTagged(`$this$accept` as YamlTaggedNode);
   }

   return (T)var10000;
}

internal fun YamlScalar.toBooleanOrNull(): Boolean? {
   var var1: java.lang.Boolean;
   try {
      var1 = `$this$toBooleanOrNull`.toBoolean();
   } catch (var3: java.lang.Throwable) {
      var1 = null;
   }

   return var1;
}

internal fun YamlScalar.toDoubleOrNull(): Double? {
   var var1: java.lang.Double;
   try {
      var1 = `$this$toDoubleOrNull`.toDouble();
   } catch (var3: java.lang.Throwable) {
      var1 = null;
   }

   return var1;
}

internal fun YamlScalar.toLongOrNull(): Long? {
   var var1: java.lang.Long;
   try {
      var1 = `$this$toLongOrNull`.toLong();
   } catch (var3: java.lang.Throwable) {
      var1 = null;
   }

   return var1;
}
