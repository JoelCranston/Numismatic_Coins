package dev.kikugie.fletching_table.transformer.accessconverter

import dev.kikugie.fletching_table.transformer.accessconverter.AccessWidener.Entry
import dev.kikugie.fletching_table.transformer.accessconverter.AccessWidener.Header
import java.util.Arrays
import kotlin.jvm.internal.SourceDebugExtension

@SourceDebugExtension(["SMAP\nAwParser.kt\nKotlin\n*S Kotlin\n*F\n+ 1 AwParser.kt\ndev/kikugie/fletching_table/transformer/accessconverter/AwParser\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,252:1\n1#2:253\n*E\n"])
internal class AwParser(lexer: AwLexer) {
   private final val lexer: AwLexer
   private final var hasFailed: Boolean
   private final var lastFailPos: Int

   init {
      this.lexer = lexer;
      this.lastFailPos = Integer.MIN_VALUE;
   }

   public fun parse(): AccessWidener {
      val header: AccessWidener.Header = this.parseHeader();
      return new AccessWidener(header, this.parseEntries(header));
   }

   private fun parseHeader(): Header {
      var state: Int = 0;
      val builder: AwParser.HeaderBuilder = new AwParser.HeaderBuilder(0, null, 3, null);

      while (!this.lexer.isDone() && state >= 0) {
         state = this.consumeHeader(state, builder);
         this.lexer.advance();
      }

      if (!builder.isComplete()) {
         this.report("Access widener file must start with `accessWidener <version> <namespace>`", 1);
      }

      if (this.hasFailed) {
         throw new AwProcessingException();
      } else {
         return builder.build();
      }
   }

   private fun consumeHeader(state: Int, builder: dev.kikugie.fletching_table.transformer.accessconverter.AwParser.HeaderBuilder): Int {
      val var3: Int = AwParser.WhenMappings.$EnumSwitchMapping$0[this.lexer.getTokenType().ordinal()];
      val var10000: Int;
      if (var3 == 1) {
         var10000 = state;
      } else if (var3 == 2 || var3 == 3) {
         var10000 = -1;
      } else if (var3 == 4 && state == 0) {
         var10000 = 1;
      } else if (var3 == 5 && state == 1) {
         builder.setVersion(Integer.parseInt(StringsKt.drop(this.lexer.getTokenText(), 1)));
         var10000 = 2;
      } else if (var3 == 6 && state == 2) {
         builder.setNamespace(this.lexer.getTokenText());
         var10000 = 3;
      } else {
         this.reportUnexpectedToken(state);
         var10000 = state;
      }

      return var10000;
   }

   private fun parseEntries(header: Header): List<Entry> {
      val var2: java.util.List = CollectionsKt.createListBuilder();
      val `$this$parseEntries_u24lambda_u240`: java.util.List = var2;

      while (!this.lexer.isDone()) {
         val var10000: AccessWidener.Entry = this.parseEntry(header);
         if (var10000 != null) {
            `$this$parseEntries_u24lambda_u240`.add(var10000);
         }
      }

      if (this.hasFailed) {
         throw new AwProcessingException();
      } else {
         return CollectionsKt.build(var2);
      }
   }

   private fun parseEntry(header: Header): Entry? {
      var state: Int = 0;
      val builder: AwParser.EntryBuilder = new AwParser.EntryBuilder(false, null, null, null, null, null, 63, null);

      while (!this.lexer.isDone() && state >= 0) {
         state = this.consumeEntry(state, builder, header);
         this.lexer.advance();
      }

      return builder.build();
   }

   private fun consumeEntry(state: Int, builder: dev.kikugie.fletching_table.transformer.accessconverter.AwParser.EntryBuilder, header: Header): Int {
      val type: AwTokenType = this.lexer.getTokenType();
      var var10000: Int;
      if (type === AwTokenType.WHITESPACE) {
         var10000 = state;
      } else if (type != AwTokenType.LINE_BREAK && type != AwTokenType.EOF) {
         if (type === AwTokenType.TRANSITIVE) {
            if (state == 0) {
               if (header.getVersion() < 2) {
                  this.report("Modifier `transitive-` is not supported for access widener v1");
               }

               if (builder.getTransitive()) {
                  this.report("Duplicate transitive modifier");
               }

               builder.setTransitive(true);
               var10000 = 0;
            } else {
               this.reportUnexpectedToken(state);
               var10000 = state;
            }
         } else if (type != AwTokenType.ACCESSIBLE && type != AwTokenType.EXTENDABLE && type != AwTokenType.MUTABLE) {
            if (type === AwTokenType.CLASS || type === AwTokenType.METHOD || type === AwTokenType.FIELD) {
               switch (state) {
                  case 0:
                     this.report("Expected access modifier `accessible | extendable | mutable`");
                     var10000 = 0;
                     break;
                  case 1:
                     if (type === AwTokenType.CLASS && builder.getModifier() === AwTokenType.MUTABLE) {
                        this.report("Modifier `mutable` can't be applied to `class`");
                     } else if (type === AwTokenType.METHOD && builder.getModifier() === AwTokenType.MUTABLE) {
                        this.report("Modifier `mutable` can't be applied to `method`");
                     } else if (type === AwTokenType.FIELD && builder.getModifier() === AwTokenType.EXTENDABLE) {
                        this.report("Modifier `extendable` can't be applied to `field`");
                     }

                     builder.setElement(type);
                     var10000 = 2;
                     break;
                  default:
                     this.reportUnexpectedToken(state);
                     var10000 = state;
               }
            } else if (type === AwTokenType.CLASS_NAME && state == 2) {
               val var13: Int = if (builder.getElement() === AwTokenType.CLASS) 6 else 3;
               builder.setClassName(this.lexer.getTokenText());
               var10000 = var13;
            } else if (type === AwTokenType.ELEMENT_NAME && state == 3) {
               val var12: Int = if (builder.getElement() === AwTokenType.METHOD) 4 else 5;
               if (builder.getElement() === AwTokenType.FIELD && this.lexer.getTokenText() == "<init>") {
                  this.report("Element `<init>` can't be used with a `field` entry");
               }

               builder.setElementName(this.lexer.getTokenText());
               var10000 = var12;
            } else if (type === AwTokenType.LB && state == 4) {
               builder.setElementDesc(this.parseMethodDescriptor());
               var10000 = 6;
            } else if (type === AwTokenType.PRIMITIVE && state == 5) {
               builder.setElementDesc(this.lexer.getTokenText());
               var10000 = 6;
            } else if (type === AwTokenType.REFERENCE && state == 5) {
               builder.setElementDesc(this.lexer.getTokenText());
               var10000 = 6;
            } else if (state == 7) {
               var10000 = state;
            } else if (this.handleIncompleteEntry(state)) {
               var10000 = 7;
            } else {
               this.reportUnexpectedToken(state);
               var10000 = state;
            }
         } else if (state == 0) {
            builder.setModifier(type);
            var10000 = 1;
         } else {
            this.reportUnexpectedToken(state);
            var10000 = state;
         }
      } else if (state == 0) {
         var10000 = 0;
      } else {
         this.handleIncompleteEntry(state);
         var10000 = -1;
      }

      return var10000;
   }

   private fun handleIncompleteEntry(state: Int): Boolean {
      var var10000: Boolean;
      switch (state) {
         case 1:
            this.report("Expect element type `class | method | field`");
            var10000 = true;
            break;
         case 2:
            this.report("Expected class name");
            var10000 = true;
            break;
         case 3:
            this.report("Expected element name");
            var10000 = true;
            break;
         case 4:
         case 5:
            this.report("Expected element descriptor");
            var10000 = true;
            break;
         default:
            var10000 = false;
      }

      return var10000;
   }

   private fun parseMethodDescriptor(): String {
      val var1: StringBuilder = new StringBuilder();
      val `$this$parseMethodDescriptor_u24lambda_u240`: StringBuilder = var1;
      var state: Int = 0;

      while (!this.lexer.isDone()) {
         val var5: Int = this.consumeMethodDescriptor(state, `$this$parseMethodDescriptor_u24lambda_u240`);
         if (var5 < 0 || var5 == 3) {
            break;
         }

         state = var5;
         this.lexer.advance();
      }

      return var1.toString();
   }

   private fun consumeMethodDescriptor(state: Int, builder: StringBuilder): Int {
      val var3: AwTokenType = this.lexer.getTokenType();
      val var10000: Int;
      if (var3 === AwTokenType.WHITESPACE) {
         var10000 = state;
      } else if (var3 === AwTokenType.LINE_BREAK || var3 === AwTokenType.EOF) {
         this.handleIncompleteDescriptor(state);
         var10000 = -1;
      } else if (var3 === AwTokenType.LB && state == 0) {
         builder.append('(');
         var10000 = 1;
      } else if (var3 === AwTokenType.RB && state == 1) {
         builder.append(')');
         var10000 = 2;
      } else if (var3 === AwTokenType.PRIMITIVE && 1 <= state && state < 3) {
         val var7: Int = if (state == 1) 1 else 3;
         builder.append(this.lexer.getTokenText());
         var10000 = var7;
      } else if (var3 === AwTokenType.REFERENCE && 1 <= state && state < 3) {
         val var4: Int = if (state == 1) 1 else 3;
         builder.append(this.lexer.getTokenText());
         var10000 = var4;
      } else if (state == 4) {
         var10000 = state;
      } else if (this.handleIncompleteDescriptor(state)) {
         var10000 = 4;
      } else {
         this.reportUnexpectedToken(state);
         var10000 = state;
      }

      return var10000;
   }

   private fun handleIncompleteDescriptor(state: Int): Boolean {
      val var10000: Boolean;
      if (state == 2) {
         this.report("Expected method return type");
         var10000 = true;
      } else {
         var10000 = false;
      }

      return var10000;
   }

   private fun reportUnexpectedToken(state: Int) {
      val range: IntRange = this.lexer.getTokenRange();
      if (range.getFirst() != this.lastFailPos) {
         val var4: Array<Any> = new Object[]{this.lexer.getTokenType(), state};
         val var10001: java.lang.String = java.lang.String.format("Unmatched token %s at state %d", Arrays.copyOf(var4, var4.length));
         this.report(var10001);
      }

      this.lastFailPos = range.getLast() + 1;
   }

   private fun report(message: String) {
      AwProcessingExceptionKt.report$default(this.lexer, message, null, 4, null);
      this.hasFailed = true;
   }

   private fun report(message: String, line: Int) {
      AwProcessingExceptionKt.report$default(this.lexer.getFile(), message, line, 0, null, 24, null);
      this.hasFailed = true;
   }

   private class EntryBuilder(transitive: Boolean = false,
      modifier: AwTokenType = AwTokenType.INVALID,
      element: AwTokenType = AwTokenType.INVALID,
      className: String = "",
      elementName: String = "",
      elementDesc: String = ""
   ) {
      public final var transitive: Boolean
         internal set

      public final var modifier: AwTokenType
         internal set

      public final var element: AwTokenType
         internal set

      public final var className: String
         internal set

      public final var elementName: String
         internal set

      public final var elementDesc: String
         internal set

      init {
         this.transitive = transitive;
         this.modifier = modifier;
         this.element = element;
         this.className = className;
         this.elementName = elementName;
         this.elementDesc = elementDesc;
      }

      public fun build(): Entry? {
         var var10000: AccessWidener.Entry;
         switch (AwParser.EntryBuilder.WhenMappings.$EnumSwitchMapping$0[this.element.ordinal()]) {
            case 1:
               var10000 = new AccessWidener.ClassEntry(this.transitive, this.modifier, this.className);
               break;
            case 2:
               var10000 = new AccessWidener.FieldEntry(this.transitive, this.modifier, this.className, this.elementName, this.elementDesc);
               break;
            case 3:
               var10000 = new AccessWidener.MethodEntry(this.transitive, this.modifier, this.className, this.elementName, this.elementDesc);
               break;
            default:
               var10000 = null;
         }

         return var10000;
      }

      fun EntryBuilder() {
         this(false, null, null, null, null, null, 63, null);
      }
   }

   private class HeaderBuilder(version: Int = -1, namespace: String = "") {
      public final var version: Int
         internal set

      public final var namespace: String
         internal set

      public final val isComplete: Boolean
         public final get() {
            return this.version >= 1 && this.namespace.length() > 0;
         }


      init {
         this.version = version;
         this.namespace = namespace;
      }

      public fun build(): Header {
         return new AccessWidener.Header(this.version, this.namespace);
      }

      fun HeaderBuilder() {
         this(0, null, 3, null);
      }
   }
}
