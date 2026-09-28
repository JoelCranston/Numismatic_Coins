@file:SourceDebugExtension(["SMAP\nHttpUrlEncoded.kt\nKotlin\n*S Kotlin\n*F\n+ 1 HttpUrlEncoded.kt\nio/ktor/http/HttpUrlEncodedKt\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 Parameters.kt\nio/ktor/http/Parameters$Companion\n+ 4 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,82:1\n1563#2:83\n1634#2,3:84\n295#2,2:87\n1869#2,2:90\n1374#2:93\n1460#2,2:94\n1563#2:96\n1634#2,3:97\n1462#2,3:100\n1374#2:103\n1460#2,2:104\n1563#2:106\n1634#2,3:107\n1462#2,3:110\n31#3:89\n1#4:92\n*S KotlinDebug\n*F\n+ 1 HttpUrlEncoded.kt\nio/ktor/http/HttpUrlEncodedKt\n*L\n16#1:83\n16#1:84,3\n18#1:87,2\n22#1:90,2\n61#1:93\n61#1:94,2\n61#1:96\n61#1:97,3\n61#1:100,3\n78#1:103\n78#1:104,2\n79#1:106\n79#1:107,3\n78#1:110,3\n21#1:89\n*E\n"])

package io.ktor.http

import io.ktor.utils.io.charsets.CharsetJVMKt
import java.nio.charset.Charset
import java.util.ArrayList
import java.util.Map.Entry
import kotlin.jvm.internal.SourceDebugExtension

public fun String.parseUrlEncodedParameters(defaultEncoding: Charset = Charsets.UTF_8, limit: Int = 1000): Parameters {
   val var20: java.lang.Iterable = StringsKt.split$default(`$this$parseUrlEncodedParameters`, new java.lang.String[]{"&"}, false, limit, 2, null);
   val `$i$f$build`: java.util.Collection = new ArrayList(CollectionsKt.collectionSizeOrDefault(var20, 10));

   for (Object item$iv$iv : $this$map$iv) {
      `$i$f$build`.add(
         TuplesKt.to(
            StringsKt.substringBefore$default(var10 as java.lang.String, "=", null, 2, null), StringsKt.substringAfter(var10 as java.lang.String, "=", "")
         )
      );
   }

   val parameters: java.util.List = `$i$f$build` as java.util.List;
   val var26: java.util.Iterator = (`$i$f$build` as java.util.List).iterator();

   var var35: Any;
   while (true) {
      if (var26.hasNext()) {
         val var28: Any = var26.next();
         if (!((var28 as Pair).getFirst() == "_charset_")) {
            continue;
         }

         var35 = (Pair)var28;
         break;
      }

      var35 = null;
      break;
   }

   label28: {
      var35 = var35;
      if (var35 != null) {
         var35 = var35.getSecond() as java.lang.String;
         if (var35 != null) {
            break label28;
         }
      }

      var35 = CharsetJVMKt.getName(defaultEncoding);
   }

   val var22: Charset = CharsetJVMKt.forName(Charsets.INSTANCE, (java.lang.String)var35);
   val var23: Parameters.Companion = Parameters.Companion;
   val var27: ParametersBuilder = ParametersKt.ParametersBuilder$default(0, 1, null);
   val var29: ParametersBuilder = var27;

   val var33: java.lang.Iterable;
   for (Object element$iv : var33) {
      var29.append(
         CodecsKt.decodeURLQueryComponent$default((`element$iv` as Pair).component1() as java.lang.String, 0, 0, false, var22, 7, null),
         CodecsKt.decodeURLQueryComponent$default((`element$iv` as Pair).component2() as java.lang.String, 0, 0, false, var22, 7, null)
      );
   }

   return var27.build();
}

@JvmSynthetic
fun `parseUrlEncodedParameters$default`(var0: java.lang.String, var1: Charset, var2: Int, var3: Int, var4: Any): Parameters {
   if ((var3 and 1) != 0) {
      var1 = Charsets.UTF_8;
   }

   if ((var3 and 2) != 0) {
      var2 = 1000;
   }

   return parseUrlEncodedParameters(var0, var1, var2);
}

public fun List<Pair<String, String?>>.formUrlEncode(): String {
   val var1: StringBuilder = new StringBuilder();
   formUrlEncodeTo(`$this$formUrlEncode`, var1);
   return var1.toString();
}

public fun List<Pair<String, String?>>.formUrlEncodeTo(out: Appendable) {
   CollectionsKt.joinTo$default(`$this$formUrlEncodeTo`, out, "&", null, null, 0, null, HttpUrlEncodedKt::formUrlEncodeTo$lambda$0, 60, null);
}

public fun Parameters.formUrlEncode(): String {
   val `$this$flatMap$iv`: java.lang.Iterable = `$this$formUrlEncode`.entries();
   val `destination$iv$iv`: java.util.Collection = new ArrayList();

   for (Object element$iv$iv : $this$flatMap$iv) {
      val `list$iv$iv`: Entry = `element$iv$iv` as Entry;
      val `$this$map$iv`: java.lang.Iterable = (`element$iv$iv` as Entry).getValue() as java.lang.Iterable;
      val `destination$iv$ivx`: java.util.Collection = new ArrayList(CollectionsKt.collectionSizeOrDefault(`$this$map$iv`, 10));

      for (Object item$iv$iv : $this$map$iv) {
         `destination$iv$ivx`.add(TuplesKt.to(`list$iv$iv`.getKey(), `item$iv$iv` as java.lang.String));
      }

      CollectionsKt.addAll(`destination$iv$iv`, `destination$iv$ivx` as java.util.List);
   }

   return formUrlEncode(`destination$iv$iv` as MutableList<Pair<java.lang.String, java.lang.String>>);
}

public fun Parameters.formUrlEncodeTo(out: Appendable) {
   formUrlEncodeTo(`$this$formUrlEncodeTo`.entries(), out);
}

internal fun ParametersBuilder.formUrlEncodeTo(out: Appendable) {
   formUrlEncodeTo(`$this$formUrlEncodeTo`.entries(), out);
}

internal fun Set<kotlin.collections.Map.Entry<String, List<String>>>.formUrlEncodeTo(out: Appendable) {
   val `$this$flatMap$iv`: java.lang.Iterable = `$this$formUrlEncodeTo`;
   val `destination$iv$iv`: java.util.Collection = new ArrayList();

   for (Object element$iv$iv : $this$flatMap$iv) {
      val key: java.lang.String = (`element$iv$iv` as Entry).getKey() as java.lang.String;
      val value: java.util.List = (`element$iv$iv` as Entry).getValue() as java.util.List;
      val var10000: java.util.List;
      if (value.isEmpty()) {
         var10000 = CollectionsKt.listOf(TuplesKt.to(key, null));
      } else {
         val `$this$map$iv`: java.lang.Iterable = value;
         val `destination$iv$ivx`: java.util.Collection = new ArrayList(CollectionsKt.collectionSizeOrDefault(value, 10));

         for (Object item$iv$iv : $this$map$iv) {
            `destination$iv$ivx`.add(TuplesKt.to(key, `item$iv$iv` as java.lang.String));
         }

         var10000 = `destination$iv$ivx` as java.util.List;
      }

      CollectionsKt.addAll(`destination$iv$iv`, var10000);
   }

   formUrlEncodeTo(`destination$iv$iv` as MutableList<Pair<java.lang.String, java.lang.String>>, out);
}

fun `formUrlEncodeTo$lambda$0`(it: Pair): java.lang.CharSequence {
   val key: java.lang.String = CodecsKt.encodeURLParameter(it.getFirst() as java.lang.String, true);
   return if (it.getSecond() == null) key else "$key=${CodecsKt.encodeURLParameterValue(java.lang.String.valueOf(it.getSecond()))}";
}
