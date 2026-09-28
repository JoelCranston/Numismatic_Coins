package dev.kikugie.fletching_table.transformer.language

import dev.kikugie.fletching_table.transformer.LanguageFileTransformer.TransformArgs
import dev.kikugie.fletching_table.transformer.language.visitor.UtilKt
import java.util.Map.Entry
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonNull
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonObjectBuilder
import kotlinx.serialization.json.JsonPrimitive

@SourceDebugExtension(["SMAP\nJsonLanguageVisitor.kt\nKotlin\n*S Kotlin\n*F\n+ 1 JsonLanguageVisitor.kt\ndev/kikugie/fletching_table/transformer/language/RegularJsonLanguageVisitor\n+ 2 JsonElementBuilders.kt\nkotlinx/serialization/json/JsonElementBuildersKt\n+ 3 JsonLanguageVisitor.kt\ndev/kikugie/fletching_table/transformer/language/JsonLanguageVisitorKt\n+ 4 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 5 ControlFlow.kt\ndev/kikugie/commons/ControlFlowKt\n*L\n1#1,167:1\n29#2,3:168\n40#3,5:171\n45#3:177\n46#3:179\n40#3,5:180\n45#3:186\n46#3:188\n1#4:176\n1#4:185\n15#5:178\n15#5:187\n*S KotlinDebug\n*F\n+ 1 JsonLanguageVisitor.kt\ndev/kikugie/fletching_table/transformer/language/RegularJsonLanguageVisitor\n*L\n60#1:168,3\n65#1:171,5\n65#1:177\n65#1:179\n66#1:180,5\n66#1:186\n66#1:188\n65#1:176\n66#1:185\n65#1:178\n66#1:187\n*E\n"])
private open class RegularJsonLanguageVisitor(args: TransformArgs, keys: KeyStack = KeyArray.box-impl(KeyArray.constructor-impl$default(null, 1, null))) :
   JsonLanguageVisitor {
   public open val args: TransformArgs
   public open val keys: KeyStack

   init {
      this.args = args;
      this.keys = keys;
   }

   public open fun visitNull(it: JsonNull): JsonObject {
      return JsonLanguageVisitorKt.access$jsonObject(this.getKeys().getPath(), it);
   }

   public open fun visitPrimitive(it: JsonPrimitive): JsonObject {
      return JsonLanguageVisitorKt.access$jsonObject(this.getKeys().getPath(), it);
   }

   public open fun visitArray(it: JsonArray): JsonObject {
      var var10000: JsonObject;
      switch (RegularJsonLanguageVisitor.WhenMappings.$EnumSwitchMapping$0[this.getArgs().getFlattening().ordinal()]) {
         case 1:
            var10000 = JsonLanguageVisitorKt.access$jsonObject(this.getKeys().getPath(), it);
            break;
         case 2:
            var10000 = JsonLanguageVisitorKt.access$jsonObject(this.getKeys().getPath(), UtilKt.accept(it, new RichTranslationVisitor("")));
            break;
         case 3:
            var10000 = JsonLanguageVisitorKt.access$jsonObject(this.getKeys().getPath(), UtilKt.accept(it, new RichTranslationVisitor("\n")));
            break;
         default:
            throw new NoWhenBranchMatchedException();
      }

      return var10000;
   }

   public open fun visitObject(it: JsonObject): JsonObject {
      val `builder$iv`: JsonObjectBuilder = new JsonObjectBuilder();
      val `$this$visitObject_u24lambda_u240`: JsonObjectBuilder = `builder$iv`;

      for (Entry var7 : it.entrySet()) {
         JsonLanguageVisitorKt.access$plusAssign(
            `$this$visitObject_u24lambda_u240`, this.process(var7.getKey() as java.lang.String, var7.getValue() as JsonElement)
         );
      }

      return `builder$iv`.build();
   }

   protected fun process(key: String, value: JsonElement): JsonObject {
      val var3: Boolean = StringsKt.contains$default(key, '$', false, 2, null);
      val var10000: JsonObject;
      if (var3) {
         val `$this$with$iv`: KeyStack = this.getKeys();
         val `key$iv`: Key = new TemplateKey(key, null, 2, null);
         if (`key$iv` is StringKey && (`key$iv` as StringKey).unbox-impl() == ".") {
            var10000 = UtilKt.accept(value, new TemplateJsonLanguageVisitor(this.getArgs(), this.getKeys()));
         } else {
            `$this$with$iv`.push(`key$iv`);
            val `next$iv$iv`: JsonObject = UtilKt.accept(value, new TemplateJsonLanguageVisitor(this.getArgs(), this.getKeys()));
            val `last$iv`: Key = `$this$with$iv`.pop();
            if (!(`key$iv` == `last$iv`)) {
               throw new IllegalStateException(("Unbalanced stack; expected: $`key$iv`, received: $`last$iv`").toString());
            }

            var10000 = `next$iv$iv`;
         }
      } else {
         if (var3) {
            throw new NoWhenBranchMatchedException();
         }

         val var14: KeyStack = this.getKeys();
         val var15: Key = StringKey.box-impl(StringKey.constructor-impl(key));
         if (var15.unbox-impl() == ".") {
            var10000 = UtilKt.accept(value, this);
         } else {
            var14.push(var15);
            val var21: JsonObject = UtilKt.accept(value, this);
            val var24: Key = var14.pop();
            if (!(var15 == var24)) {
               throw new IllegalStateException(("Unbalanced stack; expected: $var15, received: $var24").toString());
            }

            var10000 = var21;
         }
      }

      return var10000;
   }
}
