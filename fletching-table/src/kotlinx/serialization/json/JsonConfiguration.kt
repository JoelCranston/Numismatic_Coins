package kotlinx.serialization.json

import kotlinx.serialization.ExperimentalSerializationApi

public class JsonConfiguration internal constructor(encodeDefaults: Boolean = false,
   ignoreUnknownKeys: Boolean = false,
   isLenient: Boolean = false,
   allowStructuredMapKeys: Boolean = false,
   prettyPrint: Boolean = false,
   explicitNulls: Boolean = true,
   prettyPrintIndent: String = "    ",
   coerceInputValues: Boolean = false,
   useArrayPolymorphism: Boolean = false,
   classDiscriminator: String = "type",
   allowSpecialFloatingPointValues: Boolean = false,
   useAlternativeNames: Boolean = true,
   namingStrategy: JsonNamingStrategy? = null,
   decodeEnumsCaseInsensitive: Boolean = false,
   allowTrailingComma: Boolean = false,
   allowComments: Boolean = false,
   classDiscriminatorMode: ClassDiscriminatorMode = ClassDiscriminatorMode.POLYMORPHIC
) {
   public final val encodeDefaults: Boolean
   public final val ignoreUnknownKeys: Boolean
   public final val isLenient: Boolean
   public final val allowStructuredMapKeys: Boolean
   public final val prettyPrint: Boolean
   public final val explicitNulls: Boolean

   @ExperimentalSerializationApi
   public final val prettyPrintIndent: String

   public final val coerceInputValues: Boolean
   public final val useArrayPolymorphism: Boolean
   public final val classDiscriminator: String
   public final val allowSpecialFloatingPointValues: Boolean
   public final val useAlternativeNames: Boolean

   @ExperimentalSerializationApi
   public final val namingStrategy: JsonNamingStrategy?

   @ExperimentalSerializationApi
   public final val decodeEnumsCaseInsensitive: Boolean

   @ExperimentalSerializationApi
   public final val allowTrailingComma: Boolean

   @ExperimentalSerializationApi
   public final val allowComments: Boolean

   @ExperimentalSerializationApi
   public final var classDiscriminatorMode: ClassDiscriminatorMode
      public final set(value) {
         this.classDiscriminatorMode = var1;
      }


   init {
      this.encodeDefaults = encodeDefaults;
      this.ignoreUnknownKeys = ignoreUnknownKeys;
      this.isLenient = isLenient;
      this.allowStructuredMapKeys = allowStructuredMapKeys;
      this.prettyPrint = prettyPrint;
      this.explicitNulls = explicitNulls;
      this.prettyPrintIndent = prettyPrintIndent;
      this.coerceInputValues = coerceInputValues;
      this.useArrayPolymorphism = useArrayPolymorphism;
      this.classDiscriminator = classDiscriminator;
      this.allowSpecialFloatingPointValues = allowSpecialFloatingPointValues;
      this.useAlternativeNames = useAlternativeNames;
      this.namingStrategy = namingStrategy;
      this.decodeEnumsCaseInsensitive = decodeEnumsCaseInsensitive;
      this.allowTrailingComma = allowTrailingComma;
      this.allowComments = allowComments;
      this.classDiscriminatorMode = classDiscriminatorMode;
   }

   public override fun toString(): String {
      val var1: StringBuilder = new StringBuilder();
      var1.append("JsonConfiguration(encodeDefaults=")
         .append(this.encodeDefaults)
         .append(", ignoreUnknownKeys=")
         .append(this.ignoreUnknownKeys)
         .append(", isLenient=")
         .append(this.isLenient)
         .append(", allowStructuredMapKeys=")
         .append(this.allowStructuredMapKeys)
         .append(", prettyPrint=")
         .append(this.prettyPrint)
         .append(", explicitNulls=")
         .append(this.explicitNulls)
         .append(", prettyPrintIndent='")
         .append(this.prettyPrintIndent)
         .append("', coerceInputValues=")
         .append(this.coerceInputValues)
         .append(", useArrayPolymorphism=")
         .append(this.useArrayPolymorphism)
         .append(", classDiscriminator='")
         .append(this.classDiscriminator)
         .append("', allowSpecialFloatingPointValues=")
         .append(this.allowSpecialFloatingPointValues)
         .append(", useAlternativeNames=");
      var1.append(this.useAlternativeNames)
         .append(", namingStrategy=")
         .append(this.namingStrategy)
         .append(", decodeEnumsCaseInsensitive=")
         .append(this.decodeEnumsCaseInsensitive)
         .append(", allowTrailingComma=")
         .append(this.allowTrailingComma)
         .append(", allowComments=")
         .append(this.allowComments)
         .append(", classDiscriminatorMode=")
         .append(this.classDiscriminatorMode)
         .append(')');
      return var1.toString();
   }

   fun JsonConfiguration() {
      this(false, false, false, false, false, false, null, false, false, null, false, false, null, false, false, false, null, 131071, null);
   }
}
