package io.ktor.http.cio

import io.ktor.http.cio.HttpHeadersMap.getAll.1
import io.ktor.http.cio.internals.CharArrayBuilder
import io.ktor.http.cio.internals.CharsKt
import kotlin.jvm.internal.SourceDebugExtension

@SourceDebugExtension(["SMAP\nHttpHeadersMap.kt\nKotlin\n*S Kotlin\n*F\n+ 1 HttpHeadersMap.kt\nio/ktor/http/cio/HttpHeadersMap\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,295:1\n1#2:296\n*E\n"])
public class HttpHeadersMap internal constructor(builder: CharArrayBuilder) {
   private final val builder: CharArrayBuilder

   public final var size: Int
      private set

   private final var headerCapacity: Int
   private final var headersData: HeadersData

   init {
      this.builder = builder;
      this.headersData = HttpHeadersMapKt.access$getHeadersDataPool$p().borrow() as HeadersData;
   }

   private fun thresholdReached(): Boolean {
      return this.size >= this.headerCapacity * 0.75;
   }

   @Deprecated(message = "Use getAll instead", replaceWith = @ReplaceWith(expression = "getAll(name)", imports = []))
   public fun find(name: String, fromIndex: Int = 0): Int {
      if (this.size == 0) {
         return -1;
      } else {
         var offset: Int = this.idxToOffset(fromIndex);

         for (int nextIndex = fromIndex; this.headersData.at(offset + 0) != -1; offset = offset / 6 % this.headerCapacity) {
            if (this.headerHasName(name, offset)) {
               return nextIndex;
            }

            nextIndex++;
         }

         return -1;
      }
   }

   public operator fun get(name: String): CharSequence? {
      if (this.size == 0) {
         return null;
      } else {
         for (int headerIndex = Math.abs(CharsKt.hashCodeLowerCase$default(name, 0, 0, 3, null)) % this.headerCapacity;
            this.headersData.at(headerIndex * 6 + 0) != -1;
            headerIndex = (headerIndex + 1) % this.headerCapacity
         ) {
            if (this.headerHasName(name, headerIndex * 6)) {
               return this.valueAtOffset(headerIndex * 6);
            }
         }

         return null;
      }
   }

   public fun getAll(name: String): Sequence<CharSequence> {
      return SequencesKt.sequence(new 1(this, name, null));
   }

   public fun offsets(): Sequence<Int> {
      return this.headersData.headersStarts();
   }

   @Deprecated(message = "Use put without `nameHash` and `valueHash` instead", replaceWith = @ReplaceWith(expression = "put(nameStartIndex, nameEndIndex, valueStartIndex, valueEndIndex)", imports = []))
   public fun put(nameHash: Int, valueHash: Int, nameStartIndex: Int, nameEndIndex: Int, valueStartIndex: Int, valueEndIndex: Int) {
      this.put(nameStartIndex, nameEndIndex, valueStartIndex, valueEndIndex);
   }

   public fun put(nameStartIndex: Int, nameEndIndex: Int, valueStartIndex: Int, valueEndIndex: Int) {
      if (this.thresholdReached()) {
         this.resize();
      }

      val hash: Int = Math.abs(CharsKt.hashCodeLowerCase(this.builder, nameStartIndex, nameEndIndex));
      val name: java.lang.CharSequence = this.builder.subSequence(nameStartIndex, nameEndIndex);
      var headerIndex: Int = hash % this.headerCapacity;

      var sameNameHeaderIndex: Int;
      for (sameNameHeaderIndex = -1; this.headersData.at(headerIndex * 6 + 0) != -1; headerIndex = (headerIndex + 1) % this.headerCapacity) {
         if (this.headerHasName(name, headerIndex * 6)) {
            sameNameHeaderIndex = headerIndex;
         }
      }

      val headerOffset: Int = headerIndex * 6;
      this.headersData.set(headerIndex * 6 + 0, hash);
      this.headersData.set(headerOffset + 1, nameStartIndex);
      this.headersData.set(headerOffset + 2, nameEndIndex);
      this.headersData.set(headerOffset + 3, valueStartIndex);
      this.headersData.set(headerOffset + 4, valueEndIndex);
      this.headersData.set(headerOffset + 5, -1);
      if (sameNameHeaderIndex != -1) {
         this.headersData.set(sameNameHeaderIndex * 6 + 5, headerIndex);
      }

      val var10: Int = this.size++;
   }

   private fun idxToOffset(idx: Int): Int {
      if (idx < 0) {
         throw new IllegalArgumentException("Failed requirement.".toString());
      } else if (idx >= this.size) {
         throw new IllegalArgumentException("Failed requirement.".toString());
      } else {
         return SequencesKt.last(SequencesKt.take(this.offsets(), idx + 1)).intValue();
      }
   }

   @Deprecated(message = "Use nameAtOffset instead", replaceWith = @ReplaceWith(expression = "nameAtOffset", imports = []))
   public fun nameAt(idx: Int): CharSequence {
      return this.nameAtOffset(this.idxToOffset(idx));
   }

   @Deprecated(message = "Use valueAtOffset instead", replaceWith = @ReplaceWith(expression = "valueAtOffset", imports = []))
   public fun valueAt(idx: Int): CharSequence {
      return this.valueAtOffset(this.idxToOffset(idx));
   }

   private fun resize() {
      val prevSize: Int = this.size;
      val prevData: HeadersData = this.headersData;
      this.size = 0;
      this.headerCapacity = this.headerCapacity * 2 or 128;
      var var3: Any = HttpHeadersMapKt.access$getHeadersDataPool$p().borrow();
      (var3 as HeadersData).prepare(prevData.arraysCount() * 2 or 1);
      this.headersData = var3 as HeadersData;
      var3 = prevData.headersStarts().iterator();

      while (var3.hasNext()) {
         val var8: Int = (var3.next() as java.lang.Number).intValue();
         this.put(prevData.at(var8 + 1), prevData.at(var8 + 2), prevData.at(var8 + 3), prevData.at(var8 + 4));
      }

      HttpHeadersMapKt.access$getHeadersDataPool$p().recycle(prevData);
      if (prevSize != this.size) {
         throw new IllegalArgumentException("Failed requirement.".toString());
      }
   }

   private fun headerHasName(name: CharSequence, headerOffset: Int): Boolean {
      return CharsKt.equalsLowerCase(this.builder, this.headersData.at(headerOffset + 1), this.headersData.at(headerOffset + 2), name);
   }

   public fun nameAtOffset(headerOffset: Int): CharSequence {
      return this.builder.subSequence(this.headersData.at(headerOffset + 1), this.headersData.at(headerOffset + 2));
   }

   public fun valueAtOffset(headerOffset: Int): CharSequence {
      return this.builder.subSequence(this.headersData.at(headerOffset + 3), this.headersData.at(headerOffset + 4));
   }

   public fun release() {
      this.size = 0;
      this.headerCapacity = 0;
      HttpHeadersMapKt.access$getHeadersDataPool$p().recycle(this.headersData);
      this.headersData = HttpHeadersMapKt.access$getHeadersDataPool$p().borrow() as HeadersData;
   }

   public override fun toString(): String {
      val var1: StringBuilder = new StringBuilder();
      HttpHeadersMapKt.dumpTo(this, "", var1);
      return var1.toString();
   }
}
