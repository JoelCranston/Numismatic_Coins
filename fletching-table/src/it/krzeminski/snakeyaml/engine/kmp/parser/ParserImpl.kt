package it.krzeminski.snakeyaml.engine.kmp.parser

import it.krzeminski.snakeyaml.engine.kmp.api.LoadSettings
import it.krzeminski.snakeyaml.engine.kmp.common.Anchor
import it.krzeminski.snakeyaml.engine.kmp.common.FlowStyle
import it.krzeminski.snakeyaml.engine.kmp.common.ScalarStyle
import it.krzeminski.snakeyaml.engine.kmp.common.SpecVersion
import it.krzeminski.snakeyaml.engine.kmp.events.AliasEvent
import it.krzeminski.snakeyaml.engine.kmp.events.CommentEvent
import it.krzeminski.snakeyaml.engine.kmp.events.DocumentEndEvent
import it.krzeminski.snakeyaml.engine.kmp.events.DocumentStartEvent
import it.krzeminski.snakeyaml.engine.kmp.events.Event
import it.krzeminski.snakeyaml.engine.kmp.events.ImplicitTuple
import it.krzeminski.snakeyaml.engine.kmp.events.MappingEndEvent
import it.krzeminski.snakeyaml.engine.kmp.events.MappingStartEvent
import it.krzeminski.snakeyaml.engine.kmp.events.ScalarEvent
import it.krzeminski.snakeyaml.engine.kmp.events.SequenceEndEvent
import it.krzeminski.snakeyaml.engine.kmp.events.SequenceStartEvent
import it.krzeminski.snakeyaml.engine.kmp.events.StreamEndEvent
import it.krzeminski.snakeyaml.engine.kmp.events.StreamStartEvent
import it.krzeminski.snakeyaml.engine.kmp.events.Event.ID
import it.krzeminski.snakeyaml.engine.kmp.exceptions.Mark
import it.krzeminski.snakeyaml.engine.kmp.exceptions.ParserException
import it.krzeminski.snakeyaml.engine.kmp.exceptions.YamlEngineException
import it.krzeminski.snakeyaml.engine.kmp.scanner.Scanner
import it.krzeminski.snakeyaml.engine.kmp.scanner.ScannerImpl
import it.krzeminski.snakeyaml.engine.kmp.scanner.StreamReader
import it.krzeminski.snakeyaml.engine.kmp.tokens.AliasToken
import it.krzeminski.snakeyaml.engine.kmp.tokens.AnchorToken
import it.krzeminski.snakeyaml.engine.kmp.tokens.BlockEntryToken
import it.krzeminski.snakeyaml.engine.kmp.tokens.CommentToken
import it.krzeminski.snakeyaml.engine.kmp.tokens.DirectiveToken
import it.krzeminski.snakeyaml.engine.kmp.tokens.ScalarToken
import it.krzeminski.snakeyaml.engine.kmp.tokens.StreamEndToken
import it.krzeminski.snakeyaml.engine.kmp.tokens.StreamStartToken
import it.krzeminski.snakeyaml.engine.kmp.tokens.TagToken
import it.krzeminski.snakeyaml.engine.kmp.tokens.TagTuple
import it.krzeminski.snakeyaml.engine.kmp.tokens.Token
import java.util.LinkedHashMap
import java.util.NoSuchElementException
import java.util.Map.Entry
import kotlin.jvm.internal.SourceDebugExtension

@SourceDebugExtension(["SMAP\nParserImpl.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ParserImpl.kt\nit/krzeminski/snakeyaml/engine/kmp/parser/ParserImpl\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,940:1\n1#2:941\n*E\n"])
public class ParserImpl(settings: LoadSettings, scanner: Scanner) : Parser {
   private final val settings: LoadSettings
   private final val scanner: Scanner
   private final val states: ArrayDeque<Production>
   private final val marksStack: ArrayDeque<Mark?>
   private final var currentEvent: Event?
   private final var state: Production?
   private final var directiveTags: MutableMap<String, String>

   init {
      this.settings = settings;
      this.scanner = scanner;
      this.states = new ArrayDeque<>(100);
      this.marksStack = new ArrayDeque<>(100);
      this.state = new ParserImpl.ParseStreamStart(this);
      this.directiveTags = MapsKt.toMutableMap(DEFAULT_TAGS);
   }

   public constructor(settings: LoadSettings, reader: StreamReader) : this(settings, new ScannerImpl(settings, reader))
   public override fun checkEvent(choice: ID): Boolean {
      this.peekEvent();
      return (if (this.currentEvent != null) this.currentEvent.getEventId() else null) === choice;
   }

   public override fun peekEvent(): Event {
      this.produce();
      if (this.currentEvent == null) {
         throw new NoSuchElementException("No more Events found.");
      } else {
         return this.currentEvent;
      }
   }

   public override operator fun next(): Event {
      val value: Event = this.peekEvent();
      this.currentEvent = null;
      return value;
   }

   public override operator fun hasNext(): Boolean {
      this.produce();
      return this.currentEvent != null;
   }

   private fun produce() {
      if (this.currentEvent == null) {
         if (this.state != null) {
            this.currentEvent = this.state.produce();
         }
      }
   }

   private fun produceCommentEvent(token: CommentToken): CommentEvent {
      return new CommentEvent(token.getCommentType(), token.getValue(), token.getStartMark(), token.getEndMark());
   }

   private fun processDirectives(): VersionTagsTuple {
      var yamlSpecVersion: SpecVersion = null;
      val tagHandles: java.util.Map = new LinkedHashMap();

      while (this.scanner.checkToken(Token.ID.Directive)) {
         val var10000: Token = this.scanner.next();
         val detectedTagHandles: DirectiveToken = var10000 as DirectiveToken;
         if ((var10000 as DirectiveToken).getValue() != null) {
            val var4: DirectiveToken.TokenValue = detectedTagHandles.getValue();
            if (var4 is DirectiveToken.TagDirective) {
               val var5: DirectiveToken.TokenValue = detectedTagHandles.getValue();
               val key: java.lang.String = (var5 as DirectiveToken.TagDirective).component1();
               val value: java.lang.String = (var5 as DirectiveToken.TagDirective).component2();
               if (tagHandles.containsKey(key)) {
                  throw new ParserException("duplicate tag handle $key", detectedTagHandles.getStartMark(), null, null, null, 28, null);
               }

               tagHandles.put(key, value);
            } else {
               if (var4 !is DirectiveToken.YamlDirective) {
                  throw new NoWhenBranchMatchedException();
               }

               if (yamlSpecVersion != null) {
                  throw new ParserException("found duplicate YAML directive", detectedTagHandles.getStartMark(), null, null, null, 28, null);
               }

               val var10: DirectiveToken.TokenValue = detectedTagHandles.getValue();
               yamlSpecVersion = this.settings
                  .getVersionFunction()
                  .invoke(new SpecVersion((var10 as DirectiveToken.YamlDirective).component1(), (var10 as DirectiveToken.YamlDirective).component2()));
            }
         }
      }

      val var8: java.util.Map = new LinkedHashMap();
      if (!tagHandles.isEmpty()) {
         var8.putAll(tagHandles);
      }

      for (Entry var11 : DEFAULT_TAGS.entrySet()) {
         val var13: java.lang.String = var11.getKey() as java.lang.String;
         val var15: java.lang.String = var11.getValue() as java.lang.String;
         if (!tagHandles.containsKey(var13)) {
            tagHandles.put(var13, var15);
         }
      }

      this.directiveTags = tagHandles;
      return new VersionTagsTuple(yamlSpecVersion, var8);
   }

   private fun parseFlowNode(): Event {
      return this.parseNode(false, false);
   }

   private fun parseBlockNodeOrIndentlessSequence(): Event {
      return this.parseNode(true, true);
   }

   private fun parseNode(block: Boolean, indentlessSequence: Boolean): Event {
      var startMark: Mark = null;
      var endMark: Mark = null;
      var tagMark: Mark = null;
      if (this.scanner.checkToken(Token.ID.Alias)) {
         val var35: Token = this.scanner.next();
         val var19: AliasToken = var35 as AliasToken;
         this.state = this.states.removeLast();
         return new AliasEvent(var19.getValue(), var19.getStartMark(), var19.getEndMark());
      } else {
         val var18: Anchor;
         val var20: TagTuple;
         if (this.scanner.checkToken(Token.ID.Anchor)) {
            var var10000: Token = this.scanner.next();
            val tag: AnchorToken = var10000 as AnchorToken;
            startMark = (var10000 as AnchorToken).getStartMark();
            endMark = (var10000 as AnchorToken).getEndMark();
            var18 = tag.getValue();
            if (this.scanner.checkToken(Token.ID.Tag)) {
               var10000 = this.scanner.next();
               val implicit: TagToken = var10000 as TagToken;
               tagMark = (var10000 as TagToken).getStartMark();
               endMark = (var10000 as TagToken).getEndMark();
               var20 = implicit.getValue();
            } else {
               var20 = null;
            }
         } else if (this.scanner.checkToken(Token.ID.Tag)) {
            var var30: Token = this.scanner.next();
            val var21: TagToken = var30 as TagToken;
            startMark = (var30 as TagToken).getStartMark();
            tagMark = startMark;
            endMark = var21.getEndMark();
            var20 = var21.getValue();
            if (this.scanner.checkToken(Token.ID.Anchor)) {
               var30 = this.scanner.next();
               endMark = (var30 as AnchorToken).getEndMark();
               var18 = (var30 as AnchorToken).getValue();
            } else {
               var18 = null;
            }
         } else {
            var20 = null;
            var18 = null;
         }

         val var23: java.lang.String;
         if (var20 != null) {
            val var25: java.lang.String = var20.getHandle();
            val var32: java.lang.String;
            if (var25 != null) {
               if (!this.directiveTags.containsKey(var25)) {
                  throw new ParserException("found undefined tag handle $var25", startMark, "while parsing a node", tagMark, null, 16, null);
               }

               var32 = "${this.directiveTags.get(var25)}${var20.getSuffix()}";
            } else {
               var32 = var20.getSuffix();
            }

            var23 = var32;
         } else {
            var23 = null;
         }

         if (startMark == null) {
            startMark = this.scanner.peekToken().getStartMark();
            endMark = startMark;
         }

         val var26: Boolean = var23 == null;
         val var34: Event;
         if (indentlessSequence && this.scanner.checkToken(Token.ID.BlockEntry)) {
            endMark = this.scanner.peekToken().getEndMark();
            this.state = new ParserImpl.ParseIndentlessSequenceEntryKey(this);
            var34 = new SequenceStartEvent(var18, var23, var26, FlowStyle.BLOCK, startMark, endMark);
         } else if (this.scanner.checkToken(Token.ID.Scalar)) {
            val var33: Token = this.scanner.next();
            val token: ScalarToken = var33 as ScalarToken;
            endMark = (var33 as ScalarToken).getEndMark();
            val implicitValues: ImplicitTuple = if ((var33 as ScalarToken).getPlain() && var23 == null)
               new ImplicitTuple(true, false)
               else
               (if (var23 == null) new ImplicitTuple(false, true) else new ImplicitTuple(false, false));
            this.state = this.states.removeLast();
            var34 = new ScalarEvent(var18, var23, implicitValues, token.getValue(), token.getStyle(), startMark, endMark);
         } else if (this.scanner.checkToken(Token.ID.FlowSequenceStart)) {
            endMark = this.scanner.peekToken().getEndMark();
            this.state = new ParserImpl.ParseFlowSequenceFirstEntry(this);
            var34 = new SequenceStartEvent(var18, var23, var26, FlowStyle.FLOW, startMark, endMark);
         } else if (this.scanner.checkToken(Token.ID.FlowMappingStart)) {
            endMark = this.scanner.peekToken().getEndMark();
            this.state = new ParserImpl.ParseFlowMappingFirstKey(this);
            var34 = new MappingStartEvent(var18, var23, var26, FlowStyle.FLOW, startMark, endMark);
         } else if (block && this.scanner.checkToken(Token.ID.BlockSequenceStart)) {
            endMark = this.scanner.peekToken().getStartMark();
            this.state = new ParserImpl.ParseBlockSequenceFirstEntry(this);
            var34 = new SequenceStartEvent(var18, var23, var26, FlowStyle.BLOCK, startMark, endMark);
         } else if (block && this.scanner.checkToken(Token.ID.BlockMappingStart)) {
            endMark = this.scanner.peekToken().getStartMark();
            this.state = new ParserImpl.ParseBlockMappingFirstKey(this);
            var34 = new MappingStartEvent(var18, var23, var26, FlowStyle.BLOCK, startMark, endMark);
         } else {
            if (var18 == null && var23 == null) {
               val var28: Token = this.scanner.peekToken();
               throw new ParserException(
                  "expected the node content, but found '${var28.getTokenId()}'",
                  startMark,
                  "while parsing a ${if (block) "block" else "flow"} node",
                  var28.getStartMark(),
                  null,
                  16,
                  null
               );
            }

            this.state = this.states.removeLast();
            var34 = new ScalarEvent(var18, var23, new ImplicitTuple(var26, false), "", ScalarStyle.PLAIN, startMark, endMark);
         }

         return var34;
      }
   }

   private fun processEmptyScalar(mark: Mark?): Event {
      return new ScalarEvent(null, null, new ImplicitTuple(true, false), "", ScalarStyle.PLAIN, mark, mark);
   }

   private fun markPop(): Mark? {
      return this.marksStack.removeLast();
   }

   private fun markPush(mark: Mark?) {
      this.marksStack.addLast(mark);
   }

   override fun remove() {
      throw new UnsupportedOperationException("Operation is not supported for read-only collection");
   }

   public companion object {
      private final val DEFAULT_TAGS: Map<String, String>
   }

   private inner class ParseBlockMappingFirstKey : Production {
      init {
         this.this$0 = `this$0`;
      }

      public override fun produce(): Event {
         ParserImpl.access$markPush(this.this$0, ParserImpl.access$getScanner$p(this.this$0).next().getStartMark());
         return this.this$0.new ParseBlockMappingKey(this.this$0).produce();
      }
   }

   private inner class ParseBlockMappingKey : Production {
      init {
         this.this$0 = `this$0`;
      }

      public override fun produce(): Event {
         val var5: Event;
         if (ParserImpl.access$getScanner$p(this.this$0).checkToken(Token.ID.Comment)) {
            ParserImpl.access$setState$p(this.this$0, this.this$0.new ParseBlockMappingKey(this.this$0));
            val var10000: ParserImpl = this.this$0;
            val var10001: Token = ParserImpl.access$getScanner$p(this.this$0).next();
            var5 = ParserImpl.access$produceCommentEvent(var10000, var10001 as CommentToken);
         } else if (ParserImpl.access$getScanner$p(this.this$0).checkToken(Token.ID.Key)) {
            val token: Token = ParserImpl.access$getScanner$p(this.this$0).next();
            if (!ParserImpl.access$getScanner$p(this.this$0).checkToken(Token.ID.Key, Token.ID.Value, Token.ID.BlockEnd)) {
               ParserImpl.access$getStates$p(this.this$0).addLast(this.this$0.new ParseBlockMappingValue(this.this$0));
               var5 = ParserImpl.access$parseBlockNodeOrIndentlessSequence(this.this$0);
            } else {
               ParserImpl.access$setState$p(this.this$0, this.this$0.new ParseBlockMappingValue(this.this$0));
               var5 = ParserImpl.access$processEmptyScalar(this.this$0, token.getEndMark());
            }
         } else {
            if (!ParserImpl.access$getScanner$p(this.this$0).checkToken(Token.ID.BlockEnd)) {
               val var4: Token = ParserImpl.access$getScanner$p(this.this$0).peekToken();
               throw new ParserException(
                  "expected <block end>, but found '${var4.getTokenId()}'",
                  ParserImpl.access$markPop(this.this$0),
                  "while parsing a block mapping",
                  var4.getStartMark(),
                  null,
                  16,
                  null
               );
            }

            val var3: Token = ParserImpl.access$getScanner$p(this.this$0).next();
            ParserImpl.access$setState$p(this.this$0, ParserImpl.access$getStates$p(this.this$0).removeLast() as Production);
            ParserImpl.access$markPop(this.this$0);
            var5 = new MappingEndEvent(var3.getStartMark(), var3.getEndMark());
         }

         return var5;
      }
   }

   private inner class ParseBlockMappingValue : Production {
      init {
         this.this$0 = `this$0`;
      }

      public override fun produce(): Event {
         if (ParserImpl.access$getScanner$p(this.this$0).checkToken(Token.ID.Value)) {
            val var3: Token = ParserImpl.access$getScanner$p(this.this$0).next();
            val var10000: Event;
            if (ParserImpl.access$getScanner$p(this.this$0).checkToken(Token.ID.Comment)) {
               val p: Production = this.this$0.new ParseBlockMappingValueComment(this.this$0);
               ParserImpl.access$setState$p(this.this$0, p);
               var10000 = p.produce();
            } else if (!ParserImpl.access$getScanner$p(this.this$0).checkToken(Token.ID.Key, Token.ID.Value, Token.ID.BlockEnd)) {
               ParserImpl.access$getStates$p(this.this$0).addLast(this.this$0.new ParseBlockMappingKey(this.this$0));
               var10000 = ParserImpl.access$parseBlockNodeOrIndentlessSequence(this.this$0);
            } else {
               ParserImpl.access$setState$p(this.this$0, this.this$0.new ParseBlockMappingKey(this.this$0));
               var10000 = ParserImpl.access$processEmptyScalar(this.this$0, var3.getEndMark());
            }

            return var10000;
         } else if (ParserImpl.access$getScanner$p(this.this$0).checkToken(Token.ID.Scalar)) {
            ParserImpl.access$getStates$p(this.this$0).addLast(this.this$0.new ParseBlockMappingKey(this.this$0));
            return ParserImpl.access$parseBlockNodeOrIndentlessSequence(this.this$0);
         } else {
            ParserImpl.access$setState$p(this.this$0, this.this$0.new ParseBlockMappingKey(this.this$0));
            return ParserImpl.access$processEmptyScalar(this.this$0, ParserImpl.access$getScanner$p(this.this$0).peekToken().getStartMark());
         }
      }
   }

   private inner class ParseBlockMappingValueComment : Production {
      private final val tokens: ArrayDeque<CommentToken>

      init {
         this.this$0 = `this$0`;
         this.tokens = new ArrayDeque<>();
      }

      public override fun produce(): Event {
         val var2: Event;
         if (ParserImpl.access$getScanner$p(this.this$0).checkToken(Token.ID.Comment)) {
            val var10000: ArrayDeque = this.tokens;
            val var10001: Token = ParserImpl.access$getScanner$p(this.this$0).next();
            var10000.add(var10001 as CommentToken);
            var2 = this.produce();
         } else if (!ParserImpl.access$getScanner$p(this.this$0).checkToken(Token.ID.Key, Token.ID.Value, Token.ID.BlockEnd)) {
            if (!this.tokens.isEmpty()) {
               var2 = ParserImpl.access$produceCommentEvent(this.this$0, this.tokens.removeFirst());
            } else {
               ParserImpl.access$getStates$p(this.this$0).addLast(this.this$0.new ParseBlockMappingKey(this.this$0));
               var2 = ParserImpl.access$parseBlockNodeOrIndentlessSequence(this.this$0);
            }
         } else {
            ParserImpl.access$setState$p(this.this$0, this.this$0.new ParseBlockMappingValueCommentList(this.this$0, this.tokens));
            var2 = ParserImpl.access$processEmptyScalar(this.this$0, ParserImpl.access$getScanner$p(this.this$0).peekToken().getStartMark());
         }

         return var2;
      }
   }

   private inner class ParseBlockMappingValueCommentList(tokens: ArrayDeque<CommentToken>) : Production {
      private final val tokens: ArrayDeque<CommentToken>

      init {
         this.this$0 = `this$0`;
         this.tokens = tokens;
      }

      public override fun produce(): Event {
         return if (!this.tokens.isEmpty())
            ParserImpl.access$produceCommentEvent(this.this$0, this.tokens.removeFirst())
            else
            this.this$0.new ParseBlockMappingKey(this.this$0).produce();
      }
   }

   private inner class ParseBlockNode : Production {
      init {
         this.this$0 = `this$0`;
      }

      public override fun produce(): Event {
         return ParserImpl.access$parseNode(this.this$0, true, false);
      }
   }

   private inner class ParseBlockSequenceEntryKey : Production {
      init {
         this.this$0 = `this$0`;
      }

      public override fun produce(): Event {
         val var4: Event;
         if (ParserImpl.access$getScanner$p(this.this$0).checkToken(Token.ID.Comment)) {
            ParserImpl.access$setState$p(this.this$0, this.this$0.new ParseBlockSequenceEntryKey(this.this$0));
            val var10000: ParserImpl = this.this$0;
            val var10001: Token = ParserImpl.access$getScanner$p(this.this$0).next();
            var4 = ParserImpl.access$produceCommentEvent(var10000, var10001 as CommentToken);
         } else if (ParserImpl.access$getScanner$p(this.this$0).checkToken(Token.ID.BlockEntry)) {
            val var5: Token = ParserImpl.access$getScanner$p(this.this$0).next();
            var4 = this.this$0.new ParseBlockSequenceEntryValue(this.this$0, var5 as BlockEntryToken).produce();
         } else {
            if (!ParserImpl.access$getScanner$p(this.this$0).checkToken(Token.ID.BlockEnd)) {
               val var3: Token = ParserImpl.access$getScanner$p(this.this$0).peekToken();
               throw new ParserException(
                  "expected <block end>, but found '${var3.getTokenId()}'",
                  ParserImpl.access$markPop(this.this$0),
                  "while parsing a block collection",
                  var3.getStartMark(),
                  null,
                  16,
                  null
               );
            }

            val var2: Token = ParserImpl.access$getScanner$p(this.this$0).next();
            ParserImpl.access$setState$p(this.this$0, ParserImpl.access$getStates$p(this.this$0).removeLast() as Production);
            ParserImpl.access$markPop(this.this$0);
            var4 = new SequenceEndEvent(var2.getStartMark(), var2.getEndMark());
         }

         return var4;
      }
   }

   private inner class ParseBlockSequenceEntryValue(token: BlockEntryToken) : Production {
      private final val token: BlockEntryToken

      init {
         this.this$0 = `this$0`;
         this.token = token;
      }

      public override fun produce(): Event {
         if (ParserImpl.access$getScanner$p(this.this$0).checkToken(Token.ID.Comment)) {
            ParserImpl.access$setState$p(this.this$0, this.this$0.new ParseBlockSequenceEntryValue(this.this$0, this.token));
            val var3: ParserImpl = this.this$0;
            val var10001: Token = ParserImpl.access$getScanner$p(this.this$0).next();
            return ParserImpl.access$produceCommentEvent(var3, var10001 as CommentToken);
         } else {
            val var2: Event;
            if (!ParserImpl.access$getScanner$p(this.this$0).checkToken(Token.ID.BlockEntry, Token.ID.BlockEnd)) {
               ParserImpl.access$getStates$p(this.this$0).addLast(this.this$0.new ParseBlockSequenceEntryKey(this.this$0));
               var2 = this.this$0.new ParseBlockNode(this.this$0).produce();
            } else {
               ParserImpl.access$setState$p(this.this$0, this.this$0.new ParseBlockSequenceEntryKey(this.this$0));
               var2 = ParserImpl.access$processEmptyScalar(this.this$0, this.token.getEndMark());
            }

            return var2;
         }
      }
   }

   private inner class ParseBlockSequenceFirstEntry : Production {
      init {
         this.this$0 = `this$0`;
      }

      public override fun produce(): Event {
         ParserImpl.access$markPush(this.this$0, ParserImpl.access$getScanner$p(this.this$0).next().getStartMark());
         return this.this$0.new ParseBlockSequenceEntryKey(this.this$0).produce();
      }
   }

   private inner class ParseDocumentContent : Production {
      init {
         this.this$0 = `this$0`;
      }

      public override fun produce(): Event {
         if (ParserImpl.access$getScanner$p(this.this$0).checkToken(Token.ID.Comment)) {
            ParserImpl.access$setState$p(this.this$0, this.this$0.new ParseDocumentContent(this.this$0));
            val var4: ParserImpl = this.this$0;
            val var10001: Token = ParserImpl.access$getScanner$p(this.this$0).next();
            return ParserImpl.access$produceCommentEvent(var4, var10001 as CommentToken);
         } else {
            val var3: Event;
            if (ParserImpl.access$getScanner$p(this.this$0).checkToken(Token.ID.Directive, Token.ID.DocumentStart, Token.ID.DocumentEnd, Token.ID.StreamEnd)) {
               ParserImpl.access$setState$p(this.this$0, ParserImpl.access$getStates$p(this.this$0).removeLast() as Production);
               var3 = ParserImpl.access$processEmptyScalar(this.this$0, ParserImpl.access$getScanner$p(this.this$0).peekToken().getStartMark());
            } else {
               var3 = this.this$0.new ParseBlockNode(this.this$0).produce();
            }

            return var3;
         }
      }
   }

   private inner class ParseDocumentEnd : Production {
      init {
         this.this$0 = `this$0`;
      }

      public override fun produce(): Event {
         val token: Token = ParserImpl.access$getScanner$p(this.this$0).peekToken();
         val startMark: Mark = token.getStartMark();
         val var6: Mark;
         val var7: Boolean;
         if (ParserImpl.access$getScanner$p(this.this$0).checkToken(Token.ID.DocumentEnd)) {
            var6 = ParserImpl.access$getScanner$p(this.this$0).next().getEndMark();
            var7 = true;
         } else {
            if (ParserImpl.access$getScanner$p(this.this$0).checkToken(Token.ID.Directive)) {
               throw new ParserException(
                  "expected '<document end>' before directives, but found '${ParserImpl.access$getScanner$p(this.this$0).peekToken().getTokenId()}'",
                  ParserImpl.access$getScanner$p(this.this$0).peekToken().getStartMark(),
                  null,
                  null,
                  null,
                  28,
                  null
               );
            }

            var6 = token.getStartMark();
            var7 = false;
         }

         ParserImpl.access$getDirectiveTags$p(this.this$0).clear();
         ParserImpl.access$setState$p(this.this$0, this.this$0.new ParseDocumentStart(this.this$0));
         return new DocumentEndEvent(var7, startMark, var6);
      }
   }

   private inner class ParseDocumentStart : Production {
      init {
         this.this$0 = `this$0`;
      }

      public override fun produce(): Event {
         if (ParserImpl.access$getScanner$p(this.this$0).checkToken(Token.ID.Comment)) {
            ParserImpl.access$setState$p(this.this$0, this.this$0.new ParseDocumentStart(this.this$0));
            val var7: ParserImpl = this.this$0;
            val var8: Token = ParserImpl.access$getScanner$p(this.this$0).next();
            return ParserImpl.access$produceCommentEvent(var7, var8 as CommentToken);
         } else {
            while (ParserImpl.access$getScanner$p(this.this$0).checkToken(Token.ID.DocumentEnd)) {
               ParserImpl.access$getScanner$p(this.this$0).next();
            }

            if (ParserImpl.access$getScanner$p(this.this$0).checkToken(Token.ID.Comment)) {
               ParserImpl.access$setState$p(this.this$0, this.this$0.new ParseDocumentStart(this.this$0));
               val var6: ParserImpl = this.this$0;
               val var10001: Token = ParserImpl.access$getScanner$p(this.this$0).next();
               return ParserImpl.access$produceCommentEvent(var6, var10001 as CommentToken);
            } else if (ParserImpl.access$getScanner$p(this.this$0).checkToken(Token.ID.StreamEnd)) {
               val var10000: Token = ParserImpl.access$getScanner$p(this.this$0).next();
               val var5: StreamEndToken = var10000 as StreamEndToken;
               if (!ParserImpl.access$getStates$p(this.this$0).isEmpty()) {
                  throw new YamlEngineException("Unexpected end of stream. States left: ${ParserImpl.access$getStates$p(this.this$0)}");
               } else if (!ParserImpl.access$getMarksStack$p(this.this$0).isEmpty()) {
                  throw new YamlEngineException("Unexpected end of stream. Marks left: ${ParserImpl.access$getMarksStack$p(this.this$0)}");
               } else {
                  ParserImpl.access$setState$p(this.this$0, null);
                  return new StreamEndEvent(var5.getStartMark(), var5.getEndMark());
               }
            } else {
               ParserImpl.access$getScanner$p(this.this$0).resetDocumentIndex();
               val token: VersionTagsTuple = ParserImpl.access$processDirectives(this.this$0);

               while (ParserImpl.access$getScanner$p(this.this$0).checkToken(Token.ID.Comment)) {
                  ParserImpl.access$getScanner$p(this.this$0).next();
               }

               if (!ParserImpl.access$getScanner$p(this.this$0).checkToken(Token.ID.StreamEnd)) {
                  if (!ParserImpl.access$getScanner$p(this.this$0).checkToken(Token.ID.DocumentStart)) {
                     throw new ParserException(
                        "expected '<document start>', but found '${ParserImpl.access$getScanner$p(this.this$0).peekToken().getTokenId()}'",
                        ParserImpl.access$getScanner$p(this.this$0).peekToken().getStartMark(),
                        null,
                        null,
                        null,
                        28,
                        null
                     );
                  } else {
                     val tokenx: Token = ParserImpl.access$getScanner$p(this.this$0).next();
                     val startMark: Mark = tokenx.getStartMark();
                     val endMark: Mark = tokenx.getEndMark();
                     ParserImpl.access$getStates$p(this.this$0).addLast(this.this$0.new ParseDocumentEnd(this.this$0));
                     ParserImpl.access$setState$p(this.this$0, this.this$0.new ParseDocumentContent(this.this$0));
                     return new DocumentStartEvent(true, token.getSpecVersion(), token.getTags(), startMark, endMark);
                  }
               } else {
                  throw new ParserException(
                     "expected '<document start>', but found '${ParserImpl.access$getScanner$p(this.this$0).peekToken().getTokenId()}'",
                     ParserImpl.access$getScanner$p(this.this$0).peekToken().getStartMark(),
                     null,
                     null,
                     null,
                     28,
                     null
                  );
               }
            }
         }
      }
   }

   private inner class ParseFlowEndComment : Production {
      init {
         this.this$0 = `this$0`;
      }

      public override fun produce(): Event {
         val var10000: ParserImpl = this.this$0;
         val var10001: Token = ParserImpl.access$getScanner$p(this.this$0).next();
         val event: CommentEvent = ParserImpl.access$produceCommentEvent(var10000, var10001 as CommentToken);
         if (!ParserImpl.access$getScanner$p(this.this$0).checkToken(Token.ID.Comment)) {
            ParserImpl.access$setState$p(this.this$0, ParserImpl.access$getStates$p(this.this$0).removeLast() as Production);
         }

         return event;
      }
   }

   private inner class ParseFlowMappingEmptyValue : Production {
      init {
         this.this$0 = `this$0`;
      }

      public override fun produce(): Event {
         ParserImpl.access$setState$p(this.this$0, this.this$0.new ParseFlowMappingKey((boolean)this.this$0, false));
         return ParserImpl.access$processEmptyScalar(this.this$0, ParserImpl.access$getScanner$p(this.this$0).peekToken().getStartMark());
      }
   }

   private inner class ParseFlowMappingFirstKey : Production {
      init {
         this.this$0 = `this$0`;
      }

      public override fun produce(): Event {
         ParserImpl.access$markPush(this.this$0, ParserImpl.access$getScanner$p(this.this$0).next().getStartMark());
         return this.this$0.new ParseFlowMappingKey((boolean)this.this$0, true).produce();
      }
   }

   private inner class ParseFlowMappingKey(first: Boolean) : Production {
      private final val first: Boolean

      init {
         this.this$0 = `this$0`;
         this.first = first;
      }

      public override fun produce(): Event {
         if (ParserImpl.access$getScanner$p(this.this$0).checkToken(Token.ID.Comment)) {
            ParserImpl.access$setState$p(this.this$0, this.this$0.new ParseFlowMappingKey((boolean)this.this$0, this.first));
            val var7: ParserImpl = this.this$0;
            val var8: Token = ParserImpl.access$getScanner$p(this.this$0).next();
            return ParserImpl.access$produceCommentEvent(var7, var8 as CommentToken);
         } else {
            if (!ParserImpl.access$getScanner$p(this.this$0).checkToken(Token.ID.FlowMappingEnd)) {
               if (!this.first) {
                  if (!ParserImpl.access$getScanner$p(this.this$0).checkToken(Token.ID.FlowEntry)) {
                     val var4: Token = ParserImpl.access$getScanner$p(this.this$0).peekToken();
                     throw new ParserException(
                        "expected ',' or '}', but got ${var4.getTokenId()}",
                        ParserImpl.access$markPop(this.this$0),
                        "while parsing a flow mapping",
                        var4.getStartMark(),
                        null,
                        16,
                        null
                     );
                  }

                  ParserImpl.access$getScanner$p(this.this$0).next();
                  if (ParserImpl.access$getScanner$p(this.this$0).checkToken(Token.ID.Comment)) {
                     ParserImpl.access$setState$p(this.this$0, this.this$0.new ParseFlowMappingKey((boolean)this.this$0, true));
                     val var6: ParserImpl = this.this$0;
                     val var10001: Token = ParserImpl.access$getScanner$p(this.this$0).next();
                     return ParserImpl.access$produceCommentEvent(var6, var10001 as CommentToken);
                  }
               }

               if (ParserImpl.access$getScanner$p(this.this$0).checkToken(Token.ID.Key)) {
                  val var3: Token = ParserImpl.access$getScanner$p(this.this$0).next();
                  val var5: Event;
                  if (!ParserImpl.access$getScanner$p(this.this$0).checkToken(Token.ID.Value, Token.ID.FlowEntry, Token.ID.FlowMappingEnd)) {
                     ParserImpl.access$getStates$p(this.this$0).addLast(this.this$0.new ParseFlowMappingValue(this.this$0));
                     var5 = ParserImpl.access$parseFlowNode(this.this$0);
                  } else {
                     ParserImpl.access$setState$p(this.this$0, this.this$0.new ParseFlowMappingValue(this.this$0));
                     var5 = ParserImpl.access$processEmptyScalar(this.this$0, var3.getEndMark());
                  }

                  return var5;
               }

               if (!ParserImpl.access$getScanner$p(this.this$0).checkToken(Token.ID.FlowMappingEnd)) {
                  ParserImpl.access$getStates$p(this.this$0).addLast(this.this$0.new ParseFlowMappingEmptyValue(this.this$0));
                  return ParserImpl.access$parseFlowNode(this.this$0);
               }
            }

            val token: Token = ParserImpl.access$getScanner$p(this.this$0).next();
            ParserImpl.access$markPop(this.this$0);
            ParserImpl.access$setState$p(
               this.this$0,
               if (!ParserImpl.access$getScanner$p(this.this$0).checkToken(Token.ID.Comment))
                  ParserImpl.access$getStates$p(this.this$0).removeLast() as Production
                  else
                  this.this$0.new ParseFlowEndComment(this.this$0)
            );
            return new MappingEndEvent(token.getStartMark(), token.getEndMark());
         }
      }
   }

   private inner class ParseFlowMappingValue : Production {
      init {
         this.this$0 = `this$0`;
      }

      public override fun produce(): Event {
         val var4: Event;
         if (ParserImpl.access$getScanner$p(this.this$0).checkToken(Token.ID.Value)) {
            val token: Token = ParserImpl.access$getScanner$p(this.this$0).next();
            if (!ParserImpl.access$getScanner$p(this.this$0).checkToken(Token.ID.FlowEntry, Token.ID.FlowMappingEnd)) {
               ParserImpl.access$getStates$p(this.this$0).addLast(this.this$0.new ParseFlowMappingKey((boolean)this.this$0, false));
               var4 = ParserImpl.access$parseFlowNode(this.this$0);
            } else {
               ParserImpl.access$setState$p(this.this$0, this.this$0.new ParseFlowMappingKey((boolean)this.this$0, false));
               var4 = ParserImpl.access$processEmptyScalar(this.this$0, token.getEndMark());
            }
         } else {
            ParserImpl.access$setState$p(this.this$0, this.this$0.new ParseFlowMappingKey((boolean)this.this$0, false));
            var4 = ParserImpl.access$processEmptyScalar(this.this$0, ParserImpl.access$getScanner$p(this.this$0).peekToken().getStartMark());
         }

         return var4;
      }
   }

   private inner class ParseFlowSequenceEntry(first: Boolean) : Production {
      private final val first: Boolean

      init {
         this.this$0 = `this$0`;
         this.first = first;
      }

      public override fun produce(): Event {
         if (ParserImpl.access$getScanner$p(this.this$0).checkToken(Token.ID.Comment)) {
            ParserImpl.access$setState$p(this.this$0, this.this$0.new ParseFlowSequenceEntry((boolean)this.this$0, this.first));
            val var4: ParserImpl = this.this$0;
            val var5: Token = ParserImpl.access$getScanner$p(this.this$0).next();
            return ParserImpl.access$produceCommentEvent(var4, var5 as CommentToken);
         } else {
            if (!ParserImpl.access$getScanner$p(this.this$0).checkToken(Token.ID.FlowSequenceEnd)) {
               if (!this.first) {
                  if (!ParserImpl.access$getScanner$p(this.this$0).checkToken(Token.ID.FlowEntry)) {
                     val var3: Token = ParserImpl.access$getScanner$p(this.this$0).peekToken();
                     throw new ParserException(
                        "expected ',' or ']', but got ${var3.getTokenId()}",
                        ParserImpl.access$markPop(this.this$0),
                        "while parsing a flow sequence",
                        var3.getStartMark(),
                        null,
                        16,
                        null
                     );
                  }

                  ParserImpl.access$getScanner$p(this.this$0).next();
                  if (ParserImpl.access$getScanner$p(this.this$0).checkToken(Token.ID.Comment)) {
                     ParserImpl.access$setState$p(this.this$0, this.this$0.new ParseFlowSequenceEntry((boolean)this.this$0, true));
                     val var10000: ParserImpl = this.this$0;
                     val var10001: Token = ParserImpl.access$getScanner$p(this.this$0).next();
                     return ParserImpl.access$produceCommentEvent(var10000, var10001 as CommentToken);
                  }
               }

               if (ParserImpl.access$getScanner$p(this.this$0).checkToken(Token.ID.Key)) {
                  val var2: Token = ParserImpl.access$getScanner$p(this.this$0).peekToken();
                  ParserImpl.access$setState$p(this.this$0, this.this$0.new ParseFlowSequenceEntryMappingKey(this.this$0));
                  return new MappingStartEvent(null, null, true, FlowStyle.FLOW, var2.getStartMark(), var2.getEndMark());
               }

               if (!ParserImpl.access$getScanner$p(this.this$0).checkToken(Token.ID.FlowSequenceEnd)) {
                  ParserImpl.access$getStates$p(this.this$0).addLast(this.this$0.new ParseFlowSequenceEntry((boolean)this.this$0, false));
                  return ParserImpl.access$parseFlowNode(this.this$0);
               }
            }

            val token: Token = ParserImpl.access$getScanner$p(this.this$0).next();
            ParserImpl.access$setState$p(
               this.this$0,
               if (!ParserImpl.access$getScanner$p(this.this$0).checkToken(Token.ID.Comment))
                  ParserImpl.access$getStates$p(this.this$0).removeLast() as Production
                  else
                  this.this$0.new ParseFlowEndComment(this.this$0)
            );
            ParserImpl.access$markPop(this.this$0);
            return new SequenceEndEvent(token.getStartMark(), token.getEndMark());
         }
      }
   }

   private inner class ParseFlowSequenceEntryMappingEnd : Production {
      init {
         this.this$0 = `this$0`;
      }

      public override fun produce(): Event {
         ParserImpl.access$setState$p(this.this$0, this.this$0.new ParseFlowSequenceEntry((boolean)this.this$0, false));
         val token: Token = ParserImpl.access$getScanner$p(this.this$0).peekToken();
         return new MappingEndEvent(token.getStartMark(), token.getEndMark());
      }
   }

   private inner class ParseFlowSequenceEntryMappingKey : Production {
      init {
         this.this$0 = `this$0`;
      }

      public override fun produce(): Event {
         val token: Token = ParserImpl.access$getScanner$p(this.this$0).next();
         val var3: Event;
         if (!ParserImpl.access$getScanner$p(this.this$0).checkToken(Token.ID.Value, Token.ID.FlowEntry, Token.ID.FlowSequenceEnd)) {
            ParserImpl.access$getStates$p(this.this$0).addLast(this.this$0.new ParseFlowSequenceEntryMappingValue(this.this$0));
            var3 = ParserImpl.access$parseFlowNode(this.this$0);
         } else {
            ParserImpl.access$setState$p(this.this$0, this.this$0.new ParseFlowSequenceEntryMappingValue(this.this$0));
            var3 = ParserImpl.access$processEmptyScalar(this.this$0, token.getEndMark());
         }

         return var3;
      }
   }

   private inner class ParseFlowSequenceEntryMappingValue : Production {
      init {
         this.this$0 = `this$0`;
      }

      public override fun produce(): Event {
         val var4: Event;
         if (ParserImpl.access$getScanner$p(this.this$0).checkToken(Token.ID.Value)) {
            val token: Token = ParserImpl.access$getScanner$p(this.this$0).next();
            if (!ParserImpl.access$getScanner$p(this.this$0).checkToken(Token.ID.FlowEntry, Token.ID.FlowSequenceEnd)) {
               ParserImpl.access$getStates$p(this.this$0).addLast(this.this$0.new ParseFlowSequenceEntryMappingEnd(this.this$0));
               var4 = ParserImpl.access$parseFlowNode(this.this$0);
            } else {
               ParserImpl.access$setState$p(this.this$0, this.this$0.new ParseFlowSequenceEntryMappingEnd(this.this$0));
               var4 = ParserImpl.access$processEmptyScalar(this.this$0, token.getEndMark());
            }
         } else {
            ParserImpl.access$setState$p(this.this$0, this.this$0.new ParseFlowSequenceEntryMappingEnd(this.this$0));
            var4 = ParserImpl.access$processEmptyScalar(this.this$0, ParserImpl.access$getScanner$p(this.this$0).peekToken().getStartMark());
         }

         return var4;
      }
   }

   private inner class ParseFlowSequenceFirstEntry : Production {
      init {
         this.this$0 = `this$0`;
      }

      public override fun produce(): Event {
         ParserImpl.access$markPush(this.this$0, ParserImpl.access$getScanner$p(this.this$0).next().getStartMark());
         return this.this$0.new ParseFlowSequenceEntry((boolean)this.this$0, true).produce();
      }
   }

   private inner class ParseImplicitDocumentStart : Production {
      init {
         this.this$0 = `this$0`;
      }

      public override fun produce(): Event {
         if (ParserImpl.access$getScanner$p(this.this$0).checkToken(Token.ID.Comment)) {
            ParserImpl.access$setState$p(this.this$0, this.this$0.new ParseImplicitDocumentStart(this.this$0));
            val var5: ParserImpl = this.this$0;
            val var10001: Token = ParserImpl.access$getScanner$p(this.this$0).next();
            return ParserImpl.access$produceCommentEvent(var5, var10001 as CommentToken);
         } else {
            val var4: Event;
            if (!ParserImpl.access$getScanner$p(this.this$0).checkToken(Token.ID.Directive, Token.ID.DocumentStart, Token.ID.StreamEnd)) {
               val startMark: Mark = ParserImpl.access$getScanner$p(this.this$0).peekToken().getStartMark();
               ParserImpl.access$getStates$p(this.this$0).addLast(this.this$0.new ParseDocumentEnd(this.this$0));
               ParserImpl.access$setState$p(this.this$0, this.this$0.new ParseBlockNode(this.this$0));
               var4 = new DocumentStartEvent(false, null, MapsKt.emptyMap(), startMark, startMark);
            } else {
               var4 = this.this$0.new ParseDocumentStart(this.this$0).produce();
            }

            return var4;
         }
      }
   }

   private inner class ParseIndentlessSequenceEntryKey : Production {
      init {
         this.this$0 = `this$0`;
      }

      public override fun produce(): Event {
         if (ParserImpl.access$getScanner$p(this.this$0).checkToken(Token.ID.Comment)) {
            ParserImpl.access$setState$p(this.this$0, this.this$0.new ParseIndentlessSequenceEntryKey(this.this$0));
            val var3: ParserImpl = this.this$0;
            val var10001: Token = ParserImpl.access$getScanner$p(this.this$0).next();
            return ParserImpl.access$produceCommentEvent(var3, var10001 as CommentToken);
         } else if (ParserImpl.access$getScanner$p(this.this$0).checkToken(Token.ID.BlockEntry)) {
            val var10000: Token = ParserImpl.access$getScanner$p(this.this$0).next();
            return this.this$0.new ParseIndentlessSequenceEntryValue(this.this$0, var10000 as BlockEntryToken).produce();
         } else {
            val token: Token = ParserImpl.access$getScanner$p(this.this$0).peekToken();
            ParserImpl.access$setState$p(this.this$0, ParserImpl.access$getStates$p(this.this$0).removeLast() as Production);
            return new SequenceEndEvent(token.getStartMark(), token.getEndMark());
         }
      }
   }

   private inner class ParseIndentlessSequenceEntryValue(token: BlockEntryToken) : Production {
      private final val token: BlockEntryToken

      init {
         this.this$0 = `this$0`;
         this.token = token;
      }

      public override fun produce(): Event {
         val var2: Event;
         if (ParserImpl.access$getScanner$p(this.this$0).checkToken(Token.ID.Comment)) {
            ParserImpl.access$setState$p(this.this$0, this.this$0.new ParseIndentlessSequenceEntryValue(this.this$0, this.token));
            val var10000: ParserImpl = this.this$0;
            val var10001: Token = ParserImpl.access$getScanner$p(this.this$0).next();
            var2 = ParserImpl.access$produceCommentEvent(var10000, var10001 as CommentToken);
         } else if (!ParserImpl.access$getScanner$p(this.this$0).checkToken(Token.ID.BlockEntry, Token.ID.Key, Token.ID.Value, Token.ID.BlockEnd)) {
            ParserImpl.access$getStates$p(this.this$0).addLast(this.this$0.new ParseIndentlessSequenceEntryKey(this.this$0));
            var2 = this.this$0.new ParseBlockNode(this.this$0).produce();
         } else {
            ParserImpl.access$setState$p(this.this$0, this.this$0.new ParseIndentlessSequenceEntryKey(this.this$0));
            var2 = ParserImpl.access$processEmptyScalar(this.this$0, this.token.getEndMark());
         }

         return var2;
      }
   }

   private inner class ParseStreamStart : Production {
      init {
         this.this$0 = `this$0`;
      }

      public override fun produce(): Event {
         val var10000: Token = ParserImpl.access$getScanner$p(this.this$0).next();
         val event: Event = new StreamStartEvent((var10000 as StreamStartToken).getStartMark(), (var10000 as StreamStartToken).getEndMark());
         ParserImpl.access$setState$p(this.this$0, this.this$0.new ParseImplicitDocumentStart(this.this$0));
         return event;
      }
   }
}
