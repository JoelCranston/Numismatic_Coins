package com.charleskorn.kaml

import java.util.ArrayList
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable

@Serializable(with = YamlListSerializer::class)
@SourceDebugExtension(["SMAP\nYamlNode.kt\nKotlin\n*S Kotlin\n*F\n+ 1 YamlNode.kt\ncom/charleskorn/kaml/YamlList\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,317:1\n1740#2,3:318\n1563#2:321\n1634#2,3:322\n1878#2,2:325\n1869#2,2:327\n1880#2:329\n*S KotlinDebug\n*F\n+ 1 YamlNode.kt\ncom/charleskorn/kaml/YamlList\n*L\n148#1:318,3\n156#1:321\n156#1:322,3\n166#1:325,2\n169#1:327,2\n166#1:329\n*E\n"])
public data class YamlList(items: List<YamlNode>, path: YamlPath) : YamlNode(path) {
   public final val items: List<YamlNode>
   public open val path: YamlPath

   init {
      this.items = items;
      this.path = path;
   }

   public override fun equivalentContentTo(other: YamlNode): Boolean {
      if (other !is YamlList) {
         return false;
      } else if (this.items.size() != (other as YamlList).items.size()) {
         return false;
      } else {
         val `$this$all$iv`: java.lang.Iterable = CollectionsKt.zip(this.items, (other as YamlList).items);
         var var10000: Boolean;
         if (`$this$all$iv` is java.util.Collection && (`$this$all$iv` as java.util.Collection).isEmpty()) {
            var10000 = true;
         } else {
            val var4: java.util.Iterator = `$this$all$iv`.iterator();

            while (true) {
               if (!var4.hasNext()) {
                  var10000 = true;
                  break;
               }

               val var6: Pair = var4.next() as Pair;
               if (!(var6.component1() as YamlNode).equivalentContentTo(var6.component2() as YamlNode)) {
                  var10000 = false;
                  break;
               }
            }
         }

         return var10000;
      }
   }

   public operator fun get(index: Int): YamlNode {
      return this.items.get(index);
   }

   public override fun contentToString(): String {
      return "[${CollectionsKt.joinToString$default(this.items, ", ", null, null, 0, null, YamlList::contentToString$lambda$0, 30, null)}]";
   }

   public open fun withPath(newPath: YamlPath): YamlList {
      val `$this$map$iv`: java.lang.Iterable = this.items;
      val `destination$iv$iv`: java.util.Collection = new ArrayList(CollectionsKt.collectionSizeOrDefault(this.items, 10));

      for (Object item$iv$iv : $this$map$iv) {
         `destination$iv$iv`.add((`item$iv$iv` as YamlNode).withPath(this.replacePathOnChild(`item$iv$iv` as YamlNode, newPath)));
      }

      return new YamlList(`destination$iv$iv` as MutableList<YamlNode>, newPath);
   }

   public override fun toString(): String {
      val builder: StringBuilder = new StringBuilder();
      builder.append("list @ ${this.getPath()} (size: ${this.items.size()})").append('\n');
      val `$this$forEachIndexed$iv`: java.lang.Iterable = this.items;
      var `index$iv`: Int = 0;

      for (Object item$iv : $this$forEachIndexed$iv) {
         val var7: Int = `index$iv`++;
         if (var7 < 0) {
            CollectionsKt.throwIndexOverflow();
         }

         val item: YamlNode = `item$iv` as YamlNode;
         builder.append("- item $var7:").append('\n');

         val `$this$forEach$iv`: java.lang.Iterable;
         for (Object element$iv : $this$forEach$iv) {
            val line: java.lang.String = `element$iv` as java.lang.String;
            builder.append("  ");
            builder.append(line).append('\n');
         }
      }

      return StringsKt.trimEnd(builder).toString();
   }

   public operator fun component1(): List<YamlNode> {
      return this.items;
   }

   public operator fun component2(): YamlPath {
      return this.path;
   }

   public fun copy(items: List<YamlNode> = this.items, path: YamlPath = this.path): YamlList {
      return new YamlList(items, path);
   }

   public override fun hashCode(): Int {
      return this.items.hashCode() * 31 + this.path.hashCode();
   }

   public override operator fun equals(other: Any?): Boolean {
      if (this === other) {
         return true;
      } else if (other !is YamlList) {
         return false;
      } else {
         val var2: YamlList = other as YamlList;
         if (!(this.items == (other as YamlList).items)) {
            return false;
         } else {
            return this.path == var2.path;
         }
      }
   }

   @JvmStatic
   fun `contentToString$lambda$0`(it: YamlNode): java.lang.CharSequence {
      return it.contentToString();
   }

   public companion object {
      public fun serializer(): KSerializer<YamlList> {
         return YamlListSerializer.INSTANCE;
      }
   }
}
