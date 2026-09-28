package io.ktor.http

import io.ktor.util.StringValues
import java.util.ArrayList
import kotlin.collections.Map.Entry
import kotlin.jvm.internal.SourceDebugExtension

@SourceDebugExtension(["SMAP\nUrlDecodedParametersBuilder.kt\nKotlin\n*S Kotlin\n*F\n+ 1 UrlDecodedParametersBuilder.kt\nio/ktor/http/UrlDecodedParametersBuilder\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,88:1\n1563#2:89\n1634#2,3:90\n1563#2:93\n1634#2,3:94\n1563#2:97\n1634#2,3:98\n1563#2:101\n1634#2,3:102\n*S KotlinDebug\n*F\n+ 1 UrlDecodedParametersBuilder.kt\nio/ktor/http/UrlDecodedParametersBuilder\n*L\n18#1:89\n18#1:90,3\n26#1:93\n26#1:94,3\n44#1:97\n44#1:98,3\n50#1:101\n50#1:102,3\n*E\n"])
internal class UrlDecodedParametersBuilder(encodedParametersBuilder: ParametersBuilder) : ParametersBuilder {
   private final val encodedParametersBuilder: ParametersBuilder
   public open val caseInsensitiveName: Boolean

   init {
      this.encodedParametersBuilder = encodedParametersBuilder;
      this.caseInsensitiveName = this.encodedParametersBuilder.getCaseInsensitiveName();
   }

   public override fun build(): Parameters {
      return UrlDecodedParametersBuilderKt.decodeParameters(this.encodedParametersBuilder);
   }

   public override fun getAll(name: String): List<String>? {
      val var2: java.util.List = this.encodedParametersBuilder.getAll(CodecsKt.encodeURLParameter$default(name, false, 1, null));
      val var10000: java.util.List;
      if (var2 != null) {
         val `$this$map$iv`: java.lang.Iterable = var2;
         val `destination$iv$iv`: java.util.Collection = new ArrayList(CollectionsKt.collectionSizeOrDefault(var2, 10));

         for (Object item$iv$iv : $this$map$iv) {
            `destination$iv$iv`.add(CodecsKt.decodeURLQueryComponent$default(`item$iv$iv` as java.lang.String, 0, 0, true, null, 11, null));
         }

         var10000 = `destination$iv$iv` as java.util.List;
      } else {
         var10000 = null;
      }

      return var10000;
   }

   public override operator fun contains(name: String): Boolean {
      return this.encodedParametersBuilder.contains(CodecsKt.encodeURLParameter$default(name, false, 1, null));
   }

   public override fun contains(name: String, value: String): Boolean {
      return this.encodedParametersBuilder.contains(CodecsKt.encodeURLParameter$default(name, false, 1, null), CodecsKt.encodeURLParameterValue(value));
   }

   public override fun names(): Set<String> {
      val `$this$map$iv`: java.lang.Iterable = this.encodedParametersBuilder.names();
      val `destination$iv$iv`: java.util.Collection = new ArrayList(CollectionsKt.collectionSizeOrDefault(`$this$map$iv`, 10));

      for (Object item$iv$iv : $this$map$iv) {
         `destination$iv$iv`.add(CodecsKt.decodeURLQueryComponent$default(`item$iv$iv` as java.lang.String, 0, 0, false, null, 15, null));
      }

      return CollectionsKt.toSet(`destination$iv$iv`);
   }

   public override fun isEmpty(): Boolean {
      return this.encodedParametersBuilder.isEmpty();
   }

   public override fun entries(): Set<Entry<String, List<String>>> {
      return UrlDecodedParametersBuilderKt.decodeParameters(this.encodedParametersBuilder).entries();
   }

   public override operator fun set(name: String, value: String) {
      this.encodedParametersBuilder.set(CodecsKt.encodeURLParameter$default(name, false, 1, null), CodecsKt.encodeURLParameterValue(value));
   }

   public override operator fun get(name: String): String? {
      val var10000: java.lang.String = this.encodedParametersBuilder.get(CodecsKt.encodeURLParameter$default(name, false, 1, null));
      return if (var10000 != null) CodecsKt.decodeURLQueryComponent$default(var10000, 0, 0, true, null, 11, null) else null;
   }

   public override fun append(name: String, value: String) {
      this.encodedParametersBuilder.append(CodecsKt.encodeURLParameter$default(name, false, 1, null), CodecsKt.encodeURLParameterValue(value));
   }

   public override fun appendAll(stringValues: StringValues) {
      UrlDecodedParametersBuilderKt.access$appendAllEncoded(this.encodedParametersBuilder, stringValues);
   }

   public override fun appendAll(name: String, values: Iterable<String>) {
      val var10000: ParametersBuilder = this.encodedParametersBuilder;
      val var13: java.lang.String = CodecsKt.encodeURLParameter$default(name, false, 1, null);
      val `destination$iv$iv`: java.util.Collection = new ArrayList(CollectionsKt.collectionSizeOrDefault(values, 10));

      for (Object item$iv$iv : values) {
         `destination$iv$iv`.add(CodecsKt.encodeURLParameterValue(`item$iv$iv` as java.lang.String));
      }

      var10000.appendAll(var13, `destination$iv$iv`);
   }

   public override fun appendMissing(stringValues: StringValues) {
      this.encodedParametersBuilder.appendMissing(UrlDecodedParametersBuilderKt.encodeParameters(stringValues).build());
   }

   public override fun appendMissing(name: String, values: Iterable<String>) {
      val var10000: ParametersBuilder = this.encodedParametersBuilder;
      val var13: java.lang.String = CodecsKt.encodeURLParameter$default(name, false, 1, null);
      val `destination$iv$iv`: java.util.Collection = new ArrayList(CollectionsKt.collectionSizeOrDefault(values, 10));

      for (Object item$iv$iv : values) {
         `destination$iv$iv`.add(CodecsKt.encodeURLParameterValue(`item$iv$iv` as java.lang.String));
      }

      var10000.appendMissing(var13, `destination$iv$iv`);
   }

   public override fun remove(name: String) {
      this.encodedParametersBuilder.remove(CodecsKt.encodeURLParameter$default(name, false, 1, null));
   }

   public override fun remove(name: String, value: String): Boolean {
      return this.encodedParametersBuilder.remove(CodecsKt.encodeURLParameter$default(name, false, 1, null), CodecsKt.encodeURLParameterValue(value));
   }

   public override fun removeKeysWithNoEntries() {
      this.encodedParametersBuilder.removeKeysWithNoEntries();
   }

   public override fun clear() {
      this.encodedParametersBuilder.clear();
   }
}
