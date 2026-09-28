package io.ktor.http.cio

import io.ktor.http.Headers
import java.util.LinkedHashSet
import kotlin.jvm.internal.SourceDebugExtension
import kotlin.jvm.internal.markers.KMappedMarker

@SourceDebugExtension(["SMAP\nCIOHeaders.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CIOHeaders.kt\nio/ktor/http/cio/CIOHeaders\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,42:1\n1#2:43\n*E\n"])
public class CIOHeaders(headers: HttpHeadersMap) : Headers {
   private final val headers: HttpHeadersMap

   private final val names: Set<String>
      private final get() {
         return this.names$delegate.getValue() as MutableSet<java.lang.String>;
      }


   public open val caseInsensitiveName: Boolean
      public open get() {
         return true;
      }


   init {
      this.headers = headers;
      this.names$delegate = LazyKt.lazy(LazyThreadSafetyMode.NONE, CIOHeaders::names_delegate$lambda$0);
   }

   public override fun names(): Set<String> {
      return this.getNames();
   }

   public override operator fun get(name: String): String? {
      val var10000: java.lang.CharSequence = this.headers.get(name);
      return if (var10000 != null) var10000.toString() else null;
   }

   public override fun getAll(name: String): List<String>? {
      val var2: java.util.List = SequencesKt.toList(SequencesKt.map(this.headers.getAll(name), CIOHeaders::getAll$lambda$0));
      return if (!var2.isEmpty()) var2 else null;
   }

   public override fun isEmpty(): Boolean {
      return this.headers.getSize() == 0;
   }

   public override fun entries(): Set<kotlin.collections.Map.Entry<String, List<String>>> {
      return SequencesKt.toSet(SequencesKt.map(this.headers.offsets(), CIOHeaders::entries$lambda$0));
   }

   @JvmStatic
   fun `names_delegate$lambda$0`(`this$0`: CIOHeaders): LinkedHashSet {
      val var1: LinkedHashSet = new LinkedHashSet(`this$0`.headers.getSize());
      val `$this$names_delegate_u24lambda_u240_u240`: LinkedHashSet = var1;
      val var4: java.util.Iterator = `this$0`.headers.offsets().iterator();

      while (var4.hasNext()) {
         `$this$names_delegate_u24lambda_u240_u240`.add(`this$0`.headers.nameAtOffset((var4.next() as java.lang.Number).intValue()).toString());
      }

      return var1;
   }

   @JvmStatic
   fun `getAll$lambda$0`(it: java.lang.CharSequence): java.lang.String {
      return it.toString();
   }

   @JvmStatic
   fun `entries$lambda$0`(`this$0`: CIOHeaders, idx: Int): CIOHeaders.Entry {
      return `this$0`.new Entry((int)`this$0`, idx);
   }

   private inner class Entry(offset: Int) : java.util.Map.Entry<java.lang.String, java.util.List<? extends java.lang.String>>, KMappedMarker {
      private final val offset: Int

      public open val key: String
         public open get() {
            return CIOHeaders.access$getHeaders$p(this.this$0).nameAtOffset(this.offset).toString();
         }


      public open val value: List<String>
         public open get() {
            return CollectionsKt.listOf(CIOHeaders.access$getHeaders$p(this.this$0).valueAtOffset(this.offset).toString());
         }


      init {
         this.this$0 = `this$0`;
         this.offset = offset;
      }

      fun setValue(newValue: MutableList<java.lang.String>): MutableList<java.lang.String> {
         throw new UnsupportedOperationException("Operation is not supported for read-only collection");
      }
   }
}
