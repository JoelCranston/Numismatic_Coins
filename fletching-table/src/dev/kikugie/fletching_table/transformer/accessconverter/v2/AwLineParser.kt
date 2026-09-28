package dev.kikugie.fletching_table.transformer.accessconverter.v2

import dev.kikugie.fletching_table.transformer.accessconverter.AccessWidener
import dev.kikugie.fletching_table.transformer.accessconverter.AwTokenType
import dev.kikugie.fletching_table.transformer.accessconverter.AccessWidener.ClassEntry
import dev.kikugie.fletching_table.transformer.accessconverter.AccessWidener.Entry
import dev.kikugie.fletching_table.transformer.accessconverter.AccessWidener.FieldEntry
import dev.kikugie.fletching_table.transformer.accessconverter.AccessWidener.MethodEntry
import dev.kikugie.fletching_table.transformer.accessconverter.v2.AwLineParser.parse.1.entries.1
import java.io.BufferedReader
import java.io.Closeable
import java.io.Reader
import kotlin.jvm.internal.SourceDebugExtension

@SourceDebugExtension(["SMAP\nAwLineParser.kt\nKotlin\n*S Kotlin\n*F\n+ 1 AwLineParser.kt\ndev/kikugie/fletching_table/transformer/accessconverter/v2/AwLineParser\n+ 2 ReadWrite.kt\nkotlin/io/TextStreamsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,92:1\n57#2:93\n1#3:94\n1#3:95\n*S KotlinDebug\n*F\n+ 1 AwLineParser.kt\ndev/kikugie/fletching_table/transformer/accessconverter/v2/AwLineParser\n*L\n13#1:93\n13#1:94\n*E\n"])
internal object AwLineParser {
   private const val TRANSITIVE: String = "transitive-"
   private const val ACCESSIBLE: String = "accessible"
   private const val EXTENDABLE: String = "extendable"
   private const val MUTABLE: String = "mutable"

   public fun parse(reader: Reader): AccessWidener {
      label24: {
         val var4: Closeable = if (reader is BufferedReader) reader as BufferedReader else new BufferedReader(reader, 8192);
         var var15: java.lang.Throwable = null;

         try {
            try {
               new AccessWidener(
                  new AccessWidener.Header(-1, "NONE"),
                  SequencesKt.toList(SequencesKt.mapNotNull(TextStreamsKt.lineSequence(var4 as BufferedReader), new 1(INSTANCE)))
               );
            } catch (var11: java.lang.Throwable) {
               var15 = var11;
               throw var11;
            }
         } catch (var12: java.lang.Throwable) {
            CloseableKt.closeFinally(var4, var15);
         }

         CloseableKt.closeFinally(var4, null);
      }
   }

   private fun parseLine(line: String): Entry? {
      val transitive: java.lang.String = if (StringsKt.startsWith$default(line, "transitive-", false, 2, null)) "transitive-" else "";
      val var10000: AwTokenType;
      if (StringsKt.startsWith$default(line, "accessible", transitive.length(), false, 4, null)) {
         var10000 = AwTokenType.ACCESSIBLE;
      } else if (StringsKt.startsWith$default(line, "extendable", transitive.length(), false, 4, null)) {
         var10000 = AwTokenType.EXTENDABLE;
      } else {
         if (!StringsKt.startsWith$default(line, "mutable", transitive.length(), false, 4, null)) {
            return null;
         }

         var10000 = AwTokenType.MUTABLE;
      }

      val var5: AwLineLexerImpl = new AwLineLexerImpl(null);
      var5.reset(line, transitive.length() + var10000.name().length(), line.length(), 0);
      return this.parseEntry(var5, line, transitive.length() > 0, var10000);
   }

   private fun parseEntry(lexer: AwLineLexerImpl, line: String, transitive: Boolean, modifier: AwTokenType): Entry? {
      val var10000: AwTokenType = lexer.advance();
      var var5: AccessWidener.Entry;
      switch (var10000 == null ? -1 : AwLineParser.WhenMappings.$EnumSwitchMapping$0[var10000.ordinal()]) {
         case 1:
            var5 = this.parseClass(lexer, line, transitive, modifier);
            break;
         case 2:
            var5 = this.parseField(lexer, line, transitive, modifier);
            break;
         case 3:
            var5 = this.parseMethod(lexer, line, transitive, modifier);
            break;
         default:
            var5 = null;
      }

      return var5;
   }

   private fun parseClass(lexer: AwLineLexerImpl, line: String, transitive: Boolean, modifier: AwTokenType): ClassEntry? {
      if (lexer.advance() != AwTokenType.CLASS_NAME) {
         return null;
      } else {
         val var10000: java.lang.String = line.substring(lexer.getTokenStart(), lexer.getTokenEnd());
         return new AccessWidener.ClassEntry(transitive, modifier, var10000);
      }
   }

   private fun parseField(lexer: AwLineLexerImpl, line: String, transitive: Boolean, modifier: AwTokenType): FieldEntry? {
      if (lexer.advance() != AwTokenType.CLASS_NAME) {
         return null;
      } else {
         var var10000: java.lang.String = line.substring(lexer.getTokenStart(), lexer.getTokenEnd());
         if (lexer.advance() != AwTokenType.ELEMENT_NAME) {
            return null;
         } else {
            var10000 = line.substring(lexer.getTokenStart(), lexer.getTokenEnd());
            if (!this.isDescriptor(lexer.advance())) {
               return null;
            } else {
               val var9: java.lang.String = line.substring(lexer.getTokenStart(), lexer.getTokenEnd());
               return new AccessWidener.FieldEntry(transitive, modifier, var10000, var10000, var9);
            }
         }
      }
   }

   private fun parseMethod(lexer: AwLineLexerImpl, line: String, transitive: Boolean, modifier: AwTokenType): MethodEntry? {
      if (lexer.advance() != AwTokenType.CLASS_NAME) {
         return null;
      } else {
         val var10000: java.lang.String = line.substring(lexer.getTokenStart(), lexer.getTokenEnd());
         label13:
         if (lexer.advance() != AwTokenType.ELEMENT_NAME) {
            return null;
         } else {
            val var8: java.lang.String = line.substring(lexer.getTokenStart(), lexer.getTokenEnd());
            val var9: java.lang.String = this.parseMethodDescriptor(lexer, line);
            return if (var9 == null) null else new AccessWidener.MethodEntry(transitive, modifier, var10000, var8, var9);
         }
      }
   }

   private fun parseMethodDescriptor(lexer: AwLineLexerImpl, line: String): String? {
      if (lexer.advance() != AwTokenType.LB) {
         return null;
      } else {
         val var5: AwTokenType;
         do {
            var5 = lexer.advance();
         } while (this.isDescriptor(var5));

         if (var5 != AwTokenType.RB) {
            return null;
         } else {
            val var10000: java.lang.String;
            if (!this.isDescriptor(lexer.advance())) {
               var10000 = null;
            } else {
               var10000 = line.substring(lexer.getTokenStart(), lexer.getTokenEnd());
            }

            return var10000;
         }
      }
   }

   private fun AwTokenType?.isDescriptor(): Boolean {
      var var10000: Boolean;
      switch ($this$isDescriptor == null ? -1 : AwLineParser.WhenMappings.$EnumSwitchMapping$0[$this$isDescriptor.ordinal()]) {
         case 4:
         case 5:
            var10000 = true;
            break;
         default:
            var10000 = false;
      }

      return var10000;
   }
}
