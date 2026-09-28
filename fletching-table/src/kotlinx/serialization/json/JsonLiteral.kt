package kotlinx.serialization.json

import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.json.internal.StringOpsKt
import kotlinx.serialization.json.internal.SuppressAnimalSniffer

@SourceDebugExtension(["SMAP\nJsonElement.kt\nKotlin\n*S Kotlin\n*F\n+ 1 JsonElement.kt\nkotlinx/serialization/json/JsonLiteral\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,350:1\n1#2:351\n*E\n"])
internal class JsonLiteral internal constructor(body: Any, isString: Boolean, coerceToInlineType: SerialDescriptor? = null) : JsonPrimitive() {
   public open val isString: Boolean
   internal final val coerceToInlineType: SerialDescriptor?
   public open val content: String

   init {
      this.isString = isString;
      this.coerceToInlineType = coerceToInlineType;
      this.content = body.toString();
      if (this.coerceToInlineType != null && !this.coerceToInlineType.isInline()) {
         throw new IllegalArgumentException("Failed requirement.".toString());
      }
   }

   public override fun toString(): String {
      val var10000: java.lang.String;
      if (this.isString()) {
         val var1: StringBuilder = new StringBuilder();
         StringOpsKt.printQuoted(var1, this.getContent());
         var10000 = var1.toString();
      } else {
         var10000 = this.getContent();
      }

      return var10000;
   }

   public override operator fun equals(other: Any?): Boolean {
      if (this === other) {
         return true;
      } else if (other == null || this.getClass() != other.getClass()) {
         return false;
      } else if (this.isString() != (other as JsonLiteral).isString()) {
         return false;
      } else {
         return this.getContent() == (other as JsonLiteral).getContent();
      }
   }

   @SuppressAnimalSniffer
   public override fun hashCode(): Int {
      return 31 * java.lang.Boolean.hashCode(this.isString()) + this.getContent().hashCode();
   }
}
