package com.charleskorn.kaml

import java.util.ArrayList
import java.util.LinkedHashMap
import java.util.Map.Entry
import kotlin.jvm.functions.Function2
import kotlin.jvm.internal.Intrinsics
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable

@Serializable(with = YamlMapSerializer::class)
@SourceDebugExtension(["SMAP\nYamlNode.kt\nKotlin\n*S Kotlin\n*F\n+ 1 YamlNode.kt\ncom/charleskorn/kaml/YamlMap\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 _Maps.kt\nkotlin/collections/MapsKt___MapsKt\n+ 4 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n*L\n1#1,317:1\n227#1,2:332\n229#1,6:336\n1869#2,2:318\n295#2,2:330\n295#2,2:334\n669#2,11:342\n1252#2,4:355\n1252#2,4:361\n1869#2,2:366\n1869#2,2:368\n168#3,2:320\n188#3,3:322\n170#3:325\n126#3:326\n153#3,3:327\n216#3:365\n217#3:370\n478#4:353\n424#4:354\n463#4:359\n413#4:360\n*S KotlinDebug\n*F\n+ 1 YamlNode.kt\ncom/charleskorn/kaml/YamlMap\n*L\n238#1:332,2\n238#1:336,6\n193#1:318,2\n228#1:330,2\n238#1:334,2\n244#1:342,11\n248#1:355,4\n249#1:361,4\n262#1:366,2\n269#1:368,2\n213#1:320,2\n214#1:322,3\n213#1:325\n219#1:326\n219#1:327,3\n259#1:365\n259#1:370\n248#1:353\n248#1:354\n249#1:359\n249#1:360\n*E\n"])
public data class YamlMap(entries: Map<YamlScalar, YamlNode>, path: YamlPath) : YamlNode(path) {
   public final val entries: Map<YamlScalar, YamlNode>
   public open val path: YamlPath

   init {
      this.entries = entries;
      this.path = path;
      val keys: java.util.List = CollectionsKt.sortedWith(this.entries.keySet(), YamlMap::_init_$lambda$1);
      val encounteredKeys: java.util.Map = new LinkedHashMap();

      val `$this$forEach$iv`: java.lang.Iterable;
      for (Object element$iv : $this$forEach$iv) {
         val key: YamlScalar = `element$iv` as YamlScalar;
         val duplicate: YamlScalar = encounteredKeys.get((`element$iv` as YamlScalar).getContent()) as YamlScalar;
         if (duplicate != null) {
            throw new DuplicateKeyException(duplicate.getPath(), key.getPath(), key.contentToString());
         }

         encounteredKeys.put(key.getContent(), key);
      }
   }

   public override fun equivalentContentTo(other: YamlNode): Boolean {
      if (other !is YamlMap) {
         return false;
      } else if (this.entries.size() != (other as YamlMap).entries.size()) {
         return false;
      } else {
         val `$this$all$iv`: java.util.Map = this.entries;
         var var10000: Boolean;
         if (this.entries.isEmpty()) {
            var10000 = true;
         } else {
            val var4: java.util.Iterator = `$this$all$iv`.entrySet().iterator();

            while (true) {
               if (!var4.hasNext()) {
                  var10000 = true;
                  break;
               }

               val `element$iv`: Entry = var4.next() as Entry;
               val thisKey: YamlScalar = `element$iv`.getKey() as YamlScalar;
               val thisValue: YamlNode = `element$iv`.getValue() as YamlNode;
               val `$this$any$iv`: java.util.Map = (other as YamlMap).entries;
               if ((other as YamlMap).entries.isEmpty()) {
                  var10000 = false;
               } else {
                  val var12: java.util.Iterator = `$this$any$iv`.entrySet().iterator();

                  while (true) {
                     if (!var12.hasNext()) {
                        var10000 = false;
                        break;
                     }

                     val `element$ivx`: Entry = var12.next() as Entry;
                     if ((`element$ivx`.getKey() as YamlScalar).equivalentContentTo(thisKey)
                        && (`element$ivx`.getValue() as YamlNode).equivalentContentTo(thisValue)) {
                        var10000 = true;
                        break;
                     }
                  }
               }

               if (!var10000) {
                  var10000 = false;
                  break;
               }
            }
         }

         return var10000;
      }
   }

   public override fun contentToString(): String {
      val `$this$map$iv`: java.util.Map = this.entries;
      val `destination$iv$iv`: java.util.Collection = new ArrayList(this.entries.size());

      for (Entry item$iv$iv : $this$map$iv.entrySet()) {
         `destination$iv$iv`.add("${(`item$iv$iv`.getKey() as YamlScalar).contentToString()}: ${(`item$iv$iv`.getValue() as YamlNode).contentToString()}");
      }

      return "{${CollectionsKt.joinToString$default(`destination$iv$iv` as java.util.List, ", ", null, null, 0, null, null, 62, null)}}";
   }

   public fun getScalar(key: String): YamlScalar? {
      val `key$iv`: java.lang.String = key;
      val var8: java.util.Iterator = this.getEntries().entrySet().iterator();

      var var10000: Any;
      while (true) {
         if (var8.hasNext()) {
            val `element$iv$iv`: Any = var8.next();
            if (!(((`element$iv$iv` as Entry).getKey() as YamlScalar).getContent() == `key$iv`)) {
               continue;
            }

            var10000 = (YamlScalar)`element$iv$iv`;
            break;
         }

         var10000 = null;
         break;
      }

      label33: {
         val var12: Entry = var10000 as Entry;
         if (var10000 as Entry != null) {
            val var14: YamlNode = var12.getValue() as YamlNode;
            if (var14 != null) {
               var10000 = var14;
               if (var14 !is YamlNode) {
                  var10000 = null;
               }

               if (var10000 == null) {
                  throw new IncorrectTypeException(
                     "Expected element to be ${(YamlNode::class).getSimpleName()} but is ${(var14.getClass()::class).getSimpleName()}", var14.getPath()
                  );
               }
               break label33;
            }
         }

         var10000 = null;
      }

      if (var10000 == null) {
         var10000 = null;
      } else {
         if (var10000 !is YamlScalar) {
            throw new IncorrectTypeException("Value for '$key' is not a scalar.", ((YamlNode)var10000).getPath());
         }

         var10000 = var10000 as YamlScalar;
      }

      return var10000;
   }

   public fun getKey(key: String): YamlScalar? {
      val `$this$singleOrNull$iv`: java.lang.Iterable = this.entries.keySet();
      var `single$iv`: Any = null;
      var `found$iv`: Boolean = false;
      val var6: java.util.Iterator = `$this$singleOrNull$iv`.iterator();

      var var10000: Any;
      while (true) {
         if (!var6.hasNext()) {
            var10000 = if (!`found$iv`) null else `single$iv`;
            break;
         }

         val `element$iv`: Any = var6.next();
         if ((`element$iv` as YamlScalar).getContent() == key) {
            if (`found$iv`) {
               var10000 = null;
               break;
            }

            `single$iv` = `element$iv`;
            `found$iv` = true;
         }
      }

      return var10000 as YamlScalar;
   }

   public open fun withPath(newPath: YamlPath): YamlMap {
      val `$this$mapValues$iv`: java.util.Map = this.entries;
      var `destination$iv$iv`: java.util.Map = new LinkedHashMap(MapsKt.mapCapacity(this.entries.size()));
      var `$this$associateByTo$iv$iv$iv`: java.lang.Iterable = `$this$mapValues$iv`.entrySet();
      var `destination$iv$iv$iv`: java.util.Map = `destination$iv$iv`;

      for (Object element$iv$iv$iv : $this$associateByTo$iv$iv$iv) {
         val var15: YamlScalar = (`element$iv$iv$iv` as Entry).getKey() as YamlScalar;
         `destination$iv$iv$iv`.put(var15.withPath(this.replacePathOnChild(var15, newPath)), (`element$iv$iv$iv` as Entry).getValue());
      }

      `destination$iv$iv` = new LinkedHashMap(MapsKt.mapCapacity(`destination$iv$iv$iv`.size()));
      `$this$associateByTo$iv$iv$iv` = `destination$iv$iv$iv`.entrySet();
      `destination$iv$iv$iv` = `destination$iv$iv`;

      for (Object element$iv$iv$iv : $this$associateByTo$iv$iv$iv) {
         val v: YamlNode = (var31 as Entry).getValue() as YamlNode;
         `destination$iv$iv$iv`.put((var31 as Entry).getKey(), v.withPath(this.replacePathOnChild(v, newPath)));
      }

      return new YamlMap(`destination$iv$iv$iv`, newPath);
   }

   public override fun toString(): String {
      val builder: StringBuilder = new StringBuilder();
      builder.append("map @ ${this.getPath()} (size: ${this.entries.size()})").append('\n');

      for (Entry element$iv : this.entries.entrySet()) {
         val key: YamlScalar = `element$iv`.getKey() as YamlScalar;
         val value: YamlNode = `element$iv`.getValue() as YamlNode;
         builder.append("- key:").append('\n');

         val `$this$forEach$iv`: java.lang.Iterable;
         for (Object element$ivx : $this$forEach$iv) {
            val line: java.lang.String = `element$ivx` as java.lang.String;
            builder.append("    ");
            builder.append(line).append('\n');
         }

         builder.append("  value:").append('\n');

         for (Object element$ivx : $this$forEach$iv) {
            val var20: java.lang.String = `element$ivx` as java.lang.String;
            builder.append("    ");
            builder.append(var20).append('\n');
         }
      }

      return StringsKt.trimEnd(builder).toString();
   }

   public operator fun component1(): Map<YamlScalar, YamlNode> {
      return this.entries;
   }

   public operator fun component2(): YamlPath {
      return this.path;
   }

   public fun copy(entries: Map<YamlScalar, YamlNode> = this.entries, path: YamlPath = this.path): YamlMap {
      return new YamlMap(entries, path);
   }

   public override fun hashCode(): Int {
      return this.entries.hashCode() * 31 + this.path.hashCode();
   }

   public override operator fun equals(other: Any?): Boolean {
      if (this === other) {
         return true;
      } else if (other !is YamlMap) {
         return false;
      } else {
         val var2: YamlMap = other as YamlMap;
         if (!(this.entries == (other as YamlMap).entries)) {
            return false;
         } else {
            return this.path == var2.path;
         }
      }
   }

   @JvmStatic
   fun `_init_$lambda$0`(a: YamlScalar, b: YamlScalar): Int {
      val lineComparison: Int = Intrinsics.compare(a.getLocation().getLine(), b.getLocation().getLine());
      return if (lineComparison != 0) lineComparison else Intrinsics.compare(a.getLocation().getColumn(), b.getLocation().getColumn());
   }

   @JvmStatic
   fun `_init_$lambda$1`(`$tmp0`: Function2, p0: Any, p1: Any): Int {
      return (`$tmp0`.invoke(p0, p1) as java.lang.Number).intValue();
   }

   public companion object {
      public fun serializer(): KSerializer<YamlMap> {
         return YamlMapSerializer.INSTANCE;
      }
   }
}
