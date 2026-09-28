package dev.kikugie.fletching_table.transformer.language

import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable

@Serializable
@SourceDebugExtension(["SMAP\nJsonLanguageVisitor.kt\nKotlin\n*S Kotlin\n*F\n+ 1 JsonLanguageVisitor.kt\ndev/kikugie/fletching_table/transformer/language/RichTranslationProperties\n*L\n1#1,167:1\n109#1,2:168\n109#1,2:170\n109#1,2:172\n109#1,2:174\n109#1,2:176\n*S KotlinDebug\n*F\n+ 1 JsonLanguageVisitor.kt\ndev/kikugie/fletching_table/transformer/language/RichTranslationProperties\n*L\n98#1:168,2\n99#1:170,2\n100#1:172,2\n101#1:174,2\n102#1:176,2\n*E\n"])
private data class RichTranslationProperties(text: String,
   color: String? = null,
   bold: Boolean = false,
   italic: Boolean = false,
   underlines: Boolean = false,
   strikethrough: Boolean = false
) {
   public final val text: String
   public final val color: String?
   public final val bold: Boolean
   public final val italic: Boolean
   public final val underlines: Boolean
   public final val strikethrough: Boolean

   init {
      this.text = text;
      this.color = color;
      this.bold = bold;
      this.italic = italic;
      this.underlines = underlines;
      this.strikethrough = strikethrough;
   }

   public fun flatten(): String {
      val var1: StringBuilder = new StringBuilder();
      if (this.color != null) {
         var1.append(this.matchColorCode(this.color));
         0++;
      }

      if (this.bold) {
         var1.append("§l");
         0++;
      }

      if (this.italic) {
         var1.append("§o");
         0++;
      }

      if (this.underlines) {
         var1.append("§n");
         0++;
      }

      if (this.strikethrough) {
         var1.append("§m");
         0++;
      }

      var1.append(this.text);
      if (0 > 0) {
         var1.append("§r");
      }

      return var1.toString();
   }

   private inline fun StringBuilder.appendCount(str: String): Int {
      `$this$appendCount`.append(str);
      return 1;
   }

   private fun matchColorCode(name: String): String {
      var var10000: java.lang.String;
      switch (name.hashCode()) {
         case -1852648987:
            if (!name.equals("dark_aqua")) {
               throw new IllegalStateException(("Unable to match color $name to a color code").toString());
            }

            var10000 = "§3";
            break;
         case -1852623997:
            if (!name.equals("dark_blue")) {
               throw new IllegalStateException(("Unable to match color $name to a color code").toString());
            }

            var10000 = "§1";
            break;
         case -1852469876:
            if (!name.equals("dark_gray")) {
               throw new IllegalStateException(("Unable to match color $name to a color code").toString());
            }

            var10000 = "§8";
            break;
         case -1846156123:
            if (!name.equals("dark_purple")) {
               throw new IllegalStateException(("Unable to match color $name to a color code").toString());
            }

            var10000 = "§5";
            break;
         case -1591987974:
            if (!name.equals("dark_green")) {
               throw new IllegalStateException(("Unable to match color $name to a color code").toString());
            }

            var10000 = "§2";
            break;
         case -734239628:
            if (!name.equals("yellow")) {
               throw new IllegalStateException(("Unable to match color $name to a color code").toString());
            }

            var10000 = "§e";
            break;
         case 112785:
            if (!name.equals("red")) {
               throw new IllegalStateException(("Unable to match color $name to a color code").toString());
            }

            var10000 = "§c";
            break;
         case 3002044:
            if (!name.equals("aqua")) {
               throw new IllegalStateException(("Unable to match color $name to a color code").toString());
            }

            var10000 = "§b";
            break;
         case 3027034:
            if (!name.equals("blue")) {
               throw new IllegalStateException(("Unable to match color $name to a color code").toString());
            }

            var10000 = "§9";
            break;
         case 3178592:
            if (!name.equals("gold")) {
               throw new IllegalStateException(("Unable to match color $name to a color code").toString());
            }

            var10000 = "§6";
            break;
         case 3181155:
            if (!name.equals("gray")) {
               throw new IllegalStateException(("Unable to match color $name to a color code").toString());
            }

            var10000 = "§7";
            break;
         case 93818879:
            if (!name.equals("black")) {
               throw new IllegalStateException(("Unable to match color $name to a color code").toString());
            }

            var10000 = "§0";
            break;
         case 98619139:
            if (!name.equals("green")) {
               throw new IllegalStateException(("Unable to match color $name to a color code").toString());
            }

            var10000 = "§a";
            break;
         case 113101865:
            if (!name.equals("white")) {
               throw new IllegalStateException(("Unable to match color $name to a color code").toString());
            }

            var10000 = "§f";
            break;
         case 1331038981:
            if (!name.equals("light_purple")) {
               throw new IllegalStateException(("Unable to match color $name to a color code").toString());
            }

            var10000 = "§d";
            break;
         case 1741368392:
            if (name.equals("dark_red")) {
               var10000 = "§4";
               break;
            }

            throw new IllegalStateException(("Unable to match color $name to a color code").toString());
         default:
            throw new IllegalStateException(("Unable to match color $name to a color code").toString());
      }

      return var10000;
   }

   public operator fun component1(): String {
      return this.text;
   }

   public operator fun component2(): String? {
      return this.color;
   }

   public operator fun component3(): Boolean {
      return this.bold;
   }

   public operator fun component4(): Boolean {
      return this.italic;
   }

   public operator fun component5(): Boolean {
      return this.underlines;
   }

   public operator fun component6(): Boolean {
      return this.strikethrough;
   }

   public fun copy(
      text: String = this.text,
      color: String? = this.color,
      bold: Boolean = this.bold,
      italic: Boolean = this.italic,
      underlines: Boolean = this.underlines,
      strikethrough: Boolean = this.strikethrough
   ): RichTranslationProperties {
      return new RichTranslationProperties(text, color, bold, italic, underlines, strikethrough);
   }

   public override fun toString(): String {
      return "RichTranslationProperties(text=${this.text}, color=${this.color}, bold=${this.bold}, italic=${this.italic}, underlines=${this.underlines}, strikethrough=${this.strikethrough})";
   }

   public override fun hashCode(): Int {
      return (
               (
                        ((this.text.hashCode() * 31 + (if (this.color == null) 0 else this.color.hashCode())) * 31 + java.lang.Boolean.hashCode(this.bold))
                              * 31
                           + java.lang.Boolean.hashCode(this.italic)
                     )
                     * 31
                  + java.lang.Boolean.hashCode(this.underlines)
            )
            * 31
         + java.lang.Boolean.hashCode(this.strikethrough);
   }

   public override operator fun equals(other: Any?): Boolean {
      if (this === other) {
         return true;
      } else if (other !is RichTranslationProperties) {
         return false;
      } else {
         val var2: RichTranslationProperties = other as RichTranslationProperties;
         if (!(this.text == (other as RichTranslationProperties).text)) {
            return false;
         } else if (!(this.color == var2.color)) {
            return false;
         } else if (this.bold != var2.bold) {
            return false;
         } else if (this.italic != var2.italic) {
            return false;
         } else if (this.underlines != var2.underlines) {
            return false;
         } else {
            return this.strikethrough == var2.strikethrough;
         }
      }
   }

   public companion object {
      public fun serializer(): KSerializer<RichTranslationProperties> {
         return RichTranslationProperties.$serializer.INSTANCE;
      }
   }
}
