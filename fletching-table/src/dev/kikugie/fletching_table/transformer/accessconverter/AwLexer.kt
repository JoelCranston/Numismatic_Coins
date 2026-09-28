package dev.kikugie.fletching_table.transformer.accessconverter

import java.io.Closeable
import java.io.Reader
import java.nio.file.Path
import kotlin.jvm.internal.InlineMarker
import kotlin.jvm.internal.SourceDebugExtension

@SourceDebugExtension(["SMAP\nAwLexer.kt\nKotlin\n*S Kotlin\n*F\n+ 1 AwLexer.kt\ndev/kikugie/fletching_table/transformer/accessconverter/AwLexer\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 ControlFlow.kt\ndev/kikugie/commons/ControlFlowKt\n+ 4 AwProcessingException.kt\ndev/kikugie/fletching_table/transformer/accessconverter/AwProcessingExceptionKt\n*L\n1#1,58:1\n1#2:59\n15#3:60\n15#3:61\n15#3:62\n14#4,2:63\n*S KotlinDebug\n*F\n+ 1 AwLexer.kt\ndev/kikugie/fletching_table/transformer/accessconverter/AwLexer\n*L\n14#1:60\n17#1:61\n29#1:62\n50#1:63,2\n*E\n"])
internal class AwLexer private constructor(file: Path, flex: AwLexerImpl) {
   public final val file: Path
   private final val flex: AwLexerImpl
   private final var tokenMarker: AwTokenType?
   private final var tokenStart: Int
   private final var tokenEnd: Int

   public final val tokenType: AwTokenType
      public final get() {
         this.locateToken();
         if (this.tokenMarker == null) {
            throw new IllegalStateException("Failed to locate a token".toString());
         } else {
            return this.tokenMarker;
         }
      }


   public final val tokenRange: IntRange
      public final get() {
         this.locateToken();
         return RangesKt.until(this.tokenStart, this.tokenEnd);
      }


   public final var tokenText: String
      private set

   public final var tokenLine: Int
      private set

   public final var tokenColumn: Int
      private set

   public final var isDone: Boolean
      public final get() {
         this.locateToken();
         return this.isDone;
      }

      private set

   init {
      this.file = file;
      this.flex = flex;
      this.tokenText = "";
      this.tokenLine = 1;
      this.tokenColumn = 1;
   }

   public constructor(file: Path, reader: Reader)  {
      val var3: AwLexerImpl = new AwLexerImpl(null);
      val text: java.lang.String = TextStreamsKt.readText(reader);
      var3.reset(text, 0, text.length(), 0);
      this(file, var3);
   }

   public fun advance() {
      if (this.tokenMarker === AwTokenType.EOF) {
         this.isDone = true;
      }

      this.locateToken();
      this.tokenMarker = null;
   }

   private fun locateToken() {
      if (this.tokenMarker == null) {
         try {
            var var10001: AwTokenType = this.flex.advance();
            if (var10001 == null) {
               var10001 = AwTokenType.EOF;
            }

            this.tokenMarker = var10001;
            this.tokenText = this.flex.yytext().toString();
            this.tokenStart = this.flex.getTokenStart();
            this.tokenEnd = this.flex.getTokenEnd();
            this.tokenLine = this.flex.yyline + 1;
            this.tokenColumn = this.flex.yycolumn + 1;
         } catch (var8: java.lang.Throwable) {
            AwProcessingExceptionKt.report(this.file, "Internal lexer failure", this.flex.yyline, this.flex.yycolumn, var8);
            throw new AwProcessingException();
         }
      }
   }

   @SourceDebugExtension(["SMAP\nAwLexer.kt\nKotlin\n*S Kotlin\n*F\n+ 1 AwLexer.kt\ndev/kikugie/fletching_table/transformer/accessconverter/AwLexer$Companion\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,58:1\n1#2:59\n*E\n"])
   public companion object {
      public inline fun <T> use(file: Path, reader: Reader, action: (AwLexer) -> T): T {
         label19: {
            val var5: Closeable = reader;
            var var6: java.lang.Throwable = null;

            try {
               try {
                  val var13: Any = action.invoke(new AwLexer(file, var5 as Reader));
               } catch (var9: java.lang.Throwable) {
                  var6 = var9;
                  throw var9;
               }
            } catch (var10: java.lang.Throwable) {
               InlineMarker.finallyStart(1);
               CloseableKt.closeFinally(var5, var6);
               InlineMarker.finallyEnd(1);
            }

            InlineMarker.finallyStart(1);
            CloseableKt.closeFinally(var5, null);
            InlineMarker.finallyEnd(1);
         }
      }
   }
}
