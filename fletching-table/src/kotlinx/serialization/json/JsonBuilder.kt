package kotlinx.serialization.json

import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.modules.SerializersModule

@SourceDebugExtension(["SMAP\nJson.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Json.kt\nkotlinx/serialization/json/JsonBuilder\n+ 2 _Strings.kt\nkotlin/text/StringsKt___StringsKt\n*L\n1#1,724:1\n1069#2,2:725\n*S KotlinDebug\n*F\n+ 1 Json.kt\nkotlinx/serialization/json/JsonBuilder\n*L\n687#1:725,2\n*E\n"])
public class JsonBuilder internal constructor(json: Json) {
   public final var encodeDefaults: Boolean
      internal set

   public final var explicitNulls: Boolean
      internal set

   public final var ignoreUnknownKeys: Boolean
      internal set

   public final var isLenient: Boolean
      internal set

   public final var prettyPrint: Boolean
      internal set

   @ExperimentalSerializationApi
   public final var prettyPrintIndent: String

   public final var coerceInputValues: Boolean
      internal set

   public final var classDiscriminator: String
      internal set

   @ExperimentalSerializationApi
   public final var classDiscriminatorMode: ClassDiscriminatorMode

   public final var useAlternativeNames: Boolean
      internal set

   @ExperimentalSerializationApi
   public final var namingStrategy: JsonNamingStrategy?

   @ExperimentalSerializationApi
   public final var decodeEnumsCaseInsensitive: Boolean

   @ExperimentalSerializationApi
   public final var allowTrailingComma: Boolean

   @ExperimentalSerializationApi
   public final var allowComments: Boolean

   public final var allowSpecialFloatingPointValues: Boolean
      internal set

   public final var allowStructuredMapKeys: Boolean
      internal set

   public final var useArrayPolymorphism: Boolean
      internal set

   public final var serializersModule: SerializersModule
      internal set

   init {
      this.encodeDefaults = json.getConfiguration().getEncodeDefaults();
      this.explicitNulls = json.getConfiguration().getExplicitNulls();
      this.ignoreUnknownKeys = json.getConfiguration().getIgnoreUnknownKeys();
      this.isLenient = json.getConfiguration().isLenient();
      this.prettyPrint = json.getConfiguration().getPrettyPrint();
      this.prettyPrintIndent = json.getConfiguration().getPrettyPrintIndent();
      this.coerceInputValues = json.getConfiguration().getCoerceInputValues();
      this.classDiscriminator = json.getConfiguration().getClassDiscriminator();
      this.classDiscriminatorMode = json.getConfiguration().getClassDiscriminatorMode();
      this.useAlternativeNames = json.getConfiguration().getUseAlternativeNames();
      this.namingStrategy = json.getConfiguration().getNamingStrategy();
      this.decodeEnumsCaseInsensitive = json.getConfiguration().getDecodeEnumsCaseInsensitive();
      this.allowTrailingComma = json.getConfiguration().getAllowTrailingComma();
      this.allowComments = json.getConfiguration().getAllowComments();
      this.allowSpecialFloatingPointValues = json.getConfiguration().getAllowSpecialFloatingPointValues();
      this.allowStructuredMapKeys = json.getConfiguration().getAllowStructuredMapKeys();
      this.useArrayPolymorphism = json.getConfiguration().getUseArrayPolymorphism();
      this.serializersModule = json.getSerializersModule();
   }

   internal fun build(): JsonConfiguration {
      if (this.useArrayPolymorphism) {
         if (!(this.classDiscriminator == "type")) {
            throw new IllegalArgumentException("Class discriminator should not be specified when array polymorphism is specified".toString());
         }

         if (this.classDiscriminatorMode != ClassDiscriminatorMode.POLYMORPHIC) {
            throw new IllegalArgumentException(
               "useArrayPolymorphism option can only be used if classDiscriminatorMode in a default POLYMORPHIC state.".toString()
            );
         }
      }

      if (!this.prettyPrint) {
         if (!(this.prettyPrintIndent == "    ")) {
            throw new IllegalArgumentException("Indent should not be specified when default printing mode is used".toString());
         }
      } else if (!(this.prettyPrintIndent == "    ")) {
         val var9: java.lang.CharSequence = this.prettyPrintIndent;
         var var4: Int = 0;

         var var10000: Boolean;
         while (true) {
            if (var4 >= var9.length()) {
               var10000 = true;
               break;
            }

            val `element$iv`: Char = var9.charAt(var4);
            if (`element$iv` != ' ' && `element$iv` != '\t' && `element$iv` != '\r' && `element$iv` != '\n') {
               var10000 = false;
               break;
            }

            var4++;
         }

         if (!var10000) {
            throw new IllegalArgumentException(
               ("Only whitespace, tab, newline and carriage return are allowed as pretty print symbols. Had ${this.prettyPrintIndent}").toString()
            );
         }
      }

      return new JsonConfiguration(
         this.encodeDefaults,
         this.ignoreUnknownKeys,
         this.isLenient,
         this.allowStructuredMapKeys,
         this.prettyPrint,
         this.explicitNulls,
         this.prettyPrintIndent,
         this.coerceInputValues,
         this.useArrayPolymorphism,
         this.classDiscriminator,
         this.allowSpecialFloatingPointValues,
         this.useAlternativeNames,
         this.namingStrategy,
         this.decodeEnumsCaseInsensitive,
         this.allowTrailingComma,
         this.allowComments,
         this.classDiscriminatorMode
      );
   }
}
