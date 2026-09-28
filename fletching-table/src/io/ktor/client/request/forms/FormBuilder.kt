package io.ktor.client.request.forms

import io.ktor.http.Headers
import io.ktor.utils.io.InternalAPI
import java.util.ArrayList
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.io.Source

@SourceDebugExtension(["SMAP\nformDsl.kt\nKotlin\n*S Kotlin\n*F\n+ 1 formDsl.kt\nio/ktor/client/request/forms/FormBuilder\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,281:1\n1869#2,2:282\n*S KotlinDebug\n*F\n+ 1 formDsl.kt\nio/ktor/client/request/forms/FormBuilder\n*L\n180#1:282,2\n*E\n"])
public class FormBuilder internal constructor() {
   private final val parts: MutableList<FormPart<*>> = (new ArrayList()) as java.util.List

   @InternalAPI
   public fun <T : Any> append(key: String, value: Any, headers: Headers = Headers.Companion.getEmpty()) {
      this.parts.add(new FormPart<>(key, value, headers));
   }

   public fun append(key: String, value: String, headers: Headers = Headers.Companion.getEmpty()) {
      this.parts.add(new FormPart<>(key, value, headers));
   }

   public fun append(key: String, value: Number, headers: Headers = Headers.Companion.getEmpty()) {
      this.parts.add(new FormPart<>(key, value, headers));
   }

   public fun append(key: String, value: Boolean, headers: Headers = Headers.Companion.getEmpty()) {
      this.parts.add(new FormPart<>(key, value, headers));
   }

   public fun append(key: String, value: ByteArray, headers: Headers = Headers.Companion.getEmpty()) {
      this.parts.add(new FormPart<>(key, value, headers));
   }

   public fun append(key: String, value: InputProvider, headers: Headers = Headers.Companion.getEmpty()) {
      this.parts.add(new FormPart<>(key, value, headers));
   }

   public fun appendInput(key: String, headers: Headers = Headers.Companion.getEmpty(), size: Long? = null, block: () -> Source) {
      this.parts.add(new FormPart<>(key, new InputProvider(size, block), headers));
   }

   public fun append(key: String, value: Source, headers: Headers = Headers.Companion.getEmpty()) {
      this.parts.add(new FormPart<>(key, value, headers));
   }

   public fun append(key: String, values: Iterable<String>, headers: Headers = Headers.Companion.getEmpty()) {
      if (!StringsKt.endsWith$default(key, "[]", false, 2, null)) {
         throw new IllegalArgumentException(("Array parameter must be suffixed with square brackets ie `$key[]`").toString());
      } else {
         for (Object element$iv : values) {
            this.parts.add(new FormPart<>(key, `element$iv` as java.lang.String, headers));
         }
      }
   }

   public fun append(key: String, values: Array<String>, headers: Headers = Headers.Companion.getEmpty()) {
      this.append(key, ArraysKt.asIterable(values), headers);
   }

   public fun append(key: String, value: ChannelProvider, headers: Headers = Headers.Companion.getEmpty()) {
      this.parts.add(new FormPart<>(key, value, headers));
   }

   public fun <T : Any> append(part: FormPart<Any>) {
      this.parts.add(part);
   }

   internal fun build(): List<FormPart<*>> {
      return this.parts;
   }
}
