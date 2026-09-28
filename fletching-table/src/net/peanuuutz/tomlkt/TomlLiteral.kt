package net.peanuuutz.tomlkt

import kotlin.enums.EnumEntries
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import net.peanuuutz.tomlkt.internal.TomlLiteralSerializer

@Serializable(with = TomlLiteralSerializer::class)
public class TomlLiteral internal constructor(content: String, type: net.peanuuutz.tomlkt.TomlLiteral.Type) : TomlElement() {
   public open val content: String
   public final val type: net.peanuuutz.tomlkt.TomlLiteral.Type

   init {
      this.content = content;
      this.type = type;
   }

   public override operator fun equals(other: Any?): Boolean {
      if (this === other) {
         return true;
      } else if (other == null || this.getClass() != other.getClass()) {
         return false;
      } else if (this.type != (other as TomlLiteral).type) {
         return false;
      } else {
         return this.getContent() == (other as TomlLiteral).getContent();
      }
   }

   public override fun hashCode(): Int {
      return 31 * this.getContent().hashCode() + this.type.hashCode();
   }

   public override fun toString(): String {
      return this.getContent();
   }

   public companion object {
      public fun serializer(): KSerializer<TomlLiteral> {
         return TomlLiteralSerializer.INSTANCE;
      }
   }

   public enum class Type {
      Boolean,
      Integer,
      Float,
      String,
      LocalDateTime,
      OffsetDateTime,
      LocalDate,
      LocalTime
      @JvmStatic
      fun getEntries(): EnumEntries<TomlLiteral.Type> {
         return $ENTRIES;
      }
   }
}
