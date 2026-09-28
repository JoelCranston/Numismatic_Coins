package com.charleskorn.kaml

import com.charleskorn.kaml.YamlScalar.toByte.1
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable

@Serializable(with = YamlScalarSerializer::class)
public data class YamlScalar(content: String, path: YamlPath) : YamlNode(path) {
   public final val content: String
   public open val path: YamlPath

   init {
      this.content = content;
      this.path = path;
   }

   public override fun equivalentContentTo(other: YamlNode): Boolean {
      return other is YamlScalar && this.content == (other as YamlScalar).content;
   }

   public override fun contentToString(): String {
      return "'${this.content}'";
   }

   public fun toByte(): Byte {
      return this.convertToIntegerLikeValue(1.INSTANCE, "byte").byteValue();
   }

   public fun toShort(): Short {
      return this.convertToIntegerLikeValue(com.charleskorn.kaml.YamlScalar.toShort.1.INSTANCE, "short").shortValue();
   }

   public fun toInt(): Int {
      return this.convertToIntegerLikeValue(com.charleskorn.kaml.YamlScalar.toInt.1.INSTANCE, "integer").intValue();
   }

   public fun toLong(): Long {
      return this.convertToIntegerLikeValue(com.charleskorn.kaml.YamlScalar.toLong.1.INSTANCE, "long").longValue();
   }

   internal fun toLongOrNull(): Long? {
      return this.convertToIntegerLikeValueOrNull(com.charleskorn.kaml.YamlScalar.toLongOrNull.1.INSTANCE);
   }

   private fun <T> convertToIntegerLikeValue(converter: (String, Int) -> T, description: String): T {
      val var10000: Any = this.convertToIntegerLikeValueOrNull(converter);
      if (var10000 == null) {
         throw new YamlScalarFormatException("Value '${this.content}' is not a valid $description value.", this.getPath(), this.content);
      } else {
         return (T)var10000;
      }
   }

   private fun <T : Any> convertToIntegerLikeValueOrNull(converter: (String, Int) -> T?): T? {
      var var2: Any;
      try {
         val var10000: Any;
         if (StringsKt.startsWith$default(this.content, "0x", false, 2, null)) {
            val var10001: java.lang.String = this.content.substring(2);
            var10000 = converter.invoke(var10001, 16);
         } else if (StringsKt.startsWith$default(this.content, "-0x", false, 2, null)) {
            val var5: java.lang.String = this.content.substring(3);
            var10000 = converter.invoke("-$var5", 16);
         } else if (StringsKt.startsWith$default(this.content, "0o", false, 2, null)) {
            val var6: java.lang.String = this.content.substring(2);
            var10000 = converter.invoke(var6, 8);
         } else if (StringsKt.startsWith$default(this.content, "-0o", false, 2, null)) {
            val var7: java.lang.String = this.content.substring(3);
            var10000 = converter.invoke("-$var7", 8);
         } else {
            var10000 = converter.invoke(this.content, 10);
         }

         var2 = var10000;
      } catch (var4: NumberFormatException) {
         var2 = null;
      }

      return (T)var2;
   }

   public fun toFloat(): Float {
      val var1: java.lang.String = this.content;
      switch (this.content.hashCode()) {
         case 1443027:
            if (var1.equals(".INF")) {
               return java.lang.Float.POSITIVE_INFINITY;
            }
            break;
         case 1444051:
            if (var1.equals(".Inf")) {
               return java.lang.Float.POSITIVE_INFINITY;
            }
            break;
         case 1447437:
            if (var1.equals(".NAN")) {
               return java.lang.Float.NaN;
            }
            break;
         case 1448429:
            if (var1.equals(".NaN")) {
               return java.lang.Float.NaN;
            }
            break;
         case 1474803:
            if (var1.equals(".inf")) {
               return java.lang.Float.POSITIVE_INFINITY;
            }
            break;
         case 1479213:
            if (var1.equals(".nan")) {
               return java.lang.Float.NaN;
            }
            break;
         case 43001472:
            if (var1.equals("-.INF")) {
               return java.lang.Float.NEGATIVE_INFINITY;
            }
            break;
         case 43002496:
            if (var1.equals("-.Inf")) {
               return java.lang.Float.NEGATIVE_INFINITY;
            }
            break;
         case 43033248:
            if (var1.equals("-.inf")) {
               return java.lang.Float.NEGATIVE_INFINITY;
            }
         default:
      }

      var var2: Float;
      try {
         var2 = java.lang.Float.parseFloat(this.content);
      } catch (var4: NumberFormatException) {
         throw new YamlScalarFormatException("Value '${this.content}' is not a valid floating point value.", this.getPath(), this.content);
      } catch (var5: IndexOutOfBoundsException) {
         throw new YamlScalarFormatException("Value '${this.content}' is not a valid floating point value.", this.getPath(), this.content);
      }

      return var2;
   }

   public fun toDouble(): Double {
      val var10000: java.lang.Double = this.toDoubleOrNull$kaml();
      if (var10000 != null) {
         return var10000;
      } else {
         throw new YamlScalarFormatException("Value '${this.content}' is not a valid floating point value.", this.getPath(), this.content);
      }
   }

   internal fun toDoubleOrNull(): Double? {
      val var1: java.lang.String = this.content;
      switch (this.content.hashCode()) {
         case 1443027:
            if (var1.equals(".INF")) {
               return java.lang.Double.POSITIVE_INFINITY;
            }
            break;
         case 1444051:
            if (var1.equals(".Inf")) {
               return java.lang.Double.POSITIVE_INFINITY;
            }
            break;
         case 1447437:
            if (var1.equals(".NAN")) {
               return java.lang.Double.NaN;
            }
            break;
         case 1448429:
            if (var1.equals(".NaN")) {
               return java.lang.Double.NaN;
            }
            break;
         case 1474803:
            if (var1.equals(".inf")) {
               return java.lang.Double.POSITIVE_INFINITY;
            }
            break;
         case 1479213:
            if (var1.equals(".nan")) {
               return java.lang.Double.NaN;
            }
            break;
         case 43001472:
            if (var1.equals("-.INF")) {
               return java.lang.Double.NEGATIVE_INFINITY;
            }
            break;
         case 43002496:
            if (var1.equals("-.Inf")) {
               return java.lang.Double.NEGATIVE_INFINITY;
            }
            break;
         case 43033248:
            if (var1.equals("-.inf")) {
               return java.lang.Double.NEGATIVE_INFINITY;
            }
         default:
      }

      var var2: java.lang.Double;
      try {
         var2 = java.lang.Double.parseDouble(this.content);
      } catch (var4: NumberFormatException) {
         var2 = null;
      } catch (var5: IndexOutOfBoundsException) {
         var2 = null;
      }

      return var2;
   }

   public fun toBoolean(): Boolean {
      val var10000: java.lang.Boolean = this.toBooleanOrNull$kaml();
      if (var10000 != null) {
         return var10000;
      } else {
         throw new YamlScalarFormatException(
            "Value '${this.content}' is not a valid boolean, permitted choices are: true or false", this.getPath(), this.content
         );
      }
   }

   internal fun toBooleanOrNull(): Boolean? {
      val var1: java.lang.String = this.content;
      switch (this.content.hashCode()) {
         case 2583950:
            if (var1.equals("TRUE")) {
               return true;
            }
            break;
         case 2615726:
            if (var1.equals("True")) {
               return true;
            }
            break;
         case 3569038:
            if (var1.equals("true")) {
               return true;
            }
            break;
         case 66658563:
            if (var1.equals("FALSE")) {
               return false;
            }
            break;
         case 67643651:
            if (var1.equals("False")) {
               return false;
            }
            break;
         case 97196323:
            if (var1.equals("false")) {
               return false;
            }
         default:
      }

      return null;
   }

   public fun toChar(): Char {
      val var10000: Character = this.toCharOrNull$kaml();
      if (var10000 != null) {
         return var10000;
      } else {
         throw new YamlScalarFormatException("Value '${this.content}' is not a valid character value.", this.getPath(), this.content);
      }
   }

   internal fun toCharOrNull(): Char? {
      return StringsKt.singleOrNull(this.content);
   }

   public open fun withPath(newPath: YamlPath): YamlScalar {
      return copy$default(this, null, newPath, 1, null);
   }

   public override fun toString(): String {
      return "scalar @ ${this.getPath()} : ${this.content}";
   }

   public operator fun component1(): String {
      return this.content;
   }

   public operator fun component2(): YamlPath {
      return this.path;
   }

   public fun copy(content: String = this.content, path: YamlPath = this.path): YamlScalar {
      return new YamlScalar(content, path);
   }

   public override fun hashCode(): Int {
      return this.content.hashCode() * 31 + this.path.hashCode();
   }

   public override operator fun equals(other: Any?): Boolean {
      if (this === other) {
         return true;
      } else if (other !is YamlScalar) {
         return false;
      } else {
         val var2: YamlScalar = other as YamlScalar;
         if (!(this.content == (other as YamlScalar).content)) {
            return false;
         } else {
            return this.path == var2.path;
         }
      }
   }

   public companion object {
      public fun serializer(): KSerializer<YamlScalar> {
         return YamlScalarSerializer.INSTANCE;
      }
   }
}
