package it.krzeminski.snakeyaml.engine.kmp.emitter

import it.krzeminski.snakeyaml.engine.kmp.api.DumpSettings
import it.krzeminski.snakeyaml.engine.kmp.api.StreamDataWriter
import it.krzeminski.snakeyaml.engine.kmp.comments.CommentEventsCollector
import it.krzeminski.snakeyaml.engine.kmp.comments.CommentLine
import it.krzeminski.snakeyaml.engine.kmp.comments.CommentType
import it.krzeminski.snakeyaml.engine.kmp.common.Anchor
import it.krzeminski.snakeyaml.engine.kmp.common.CharConstants
import it.krzeminski.snakeyaml.engine.kmp.common.ScalarStyle
import it.krzeminski.snakeyaml.engine.kmp.common.SpecVersion
import it.krzeminski.snakeyaml.engine.kmp.events.AliasEvent
import it.krzeminski.snakeyaml.engine.kmp.events.CollectionEndEvent
import it.krzeminski.snakeyaml.engine.kmp.events.CollectionStartEvent
import it.krzeminski.snakeyaml.engine.kmp.events.CommentEvent
import it.krzeminski.snakeyaml.engine.kmp.events.DocumentEndEvent
import it.krzeminski.snakeyaml.engine.kmp.events.DocumentStartEvent
import it.krzeminski.snakeyaml.engine.kmp.events.Event
import it.krzeminski.snakeyaml.engine.kmp.events.MappingStartEvent
import it.krzeminski.snakeyaml.engine.kmp.events.NodeEvent
import it.krzeminski.snakeyaml.engine.kmp.events.ScalarEvent
import it.krzeminski.snakeyaml.engine.kmp.events.SequenceStartEvent
import it.krzeminski.snakeyaml.engine.kmp.events.StreamEndEvent
import it.krzeminski.snakeyaml.engine.kmp.events.StreamStartEvent
import it.krzeminski.snakeyaml.engine.kmp.events.Event.ID
import it.krzeminski.snakeyaml.engine.kmp.exceptions.EmitterException
import it.krzeminski.snakeyaml.engine.kmp.exceptions.YamlEngineException
import it.krzeminski.snakeyaml.engine.kmp.internal.utils.CharSequenceExtensionsKt
import it.krzeminski.snakeyaml.engine.kmp.internal.utils.Character
import it.krzeminski.snakeyaml.engine.kmp.nodes.Tag
import it.krzeminski.snakeyaml.engine.kmp.scanner.StreamReader
import java.util.LinkedHashMap
import java.util.Map.Entry
import kotlin.jvm.internal.Intrinsics
import kotlin.jvm.internal.SourceDebugExtension

@SourceDebugExtension(["SMAP\nEmitter.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Emitter.kt\nit/krzeminski/snakeyaml/engine/kmp/emitter/Emitter\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,1618:1\n295#2,2:1619\n*S KotlinDebug\n*F\n+ 1 Emitter.kt\nit/krzeminski/snakeyaml/engine/kmp/emitter/Emitter\n*L\n934#1:1619,2\n*E\n"])
public class Emitter(opts: DumpSettings, stream: StreamDataWriter) : Emitable {
   private final val opts: DumpSettings
   private final val stream: StreamDataWriter
   private final val states: ArrayDeque<EmitterState>
   private final var state: EmitterState
   private final val events: ArrayDeque<Event>
   private final var event: Event?
   private final val indents: ArrayDeque<Int?>
   private final var indent: Int?
   private final var flowLevel: Int
   private final var rootContext: Boolean
   private final var mappingContext: Boolean
   private final var simpleKeyContext: Boolean
   private final var column: Int
   private final var whitespace: Boolean
   private final var indention: Boolean
   private final var openEnded: Boolean
   private final val canonical: Boolean
   private final val multiLineFlow: Boolean
   private final val allowUnicode: Boolean
   private final val bestIndent: Int

   private final val indicatorIndent: Int
      private final get() {
         return this.opts.getIndicatorIndent();
      }


   private final val indentWithIndicator: Boolean
      private final get() {
         return this.opts.getIndentWithIndicator();
      }


   private final val bestWidth: Int

   private final val bestLineBreak: String
      private final get() {
         return this.opts.getBestLineBreak();
      }


   private final val splitLines: Boolean
      private final get() {
         return this.opts.isSplitLines();
      }


   private final val maxSimpleKeyLength: Int
      private final get() {
         return this.opts.getMaxSimpleKeyLength();
      }


   private final val emitComments: Boolean
      private final get() {
         return this.opts.getDumpComments();
      }


   private final var tagPrefixes: MutableMap<String?, String>
   private final var preparedAnchor: Anchor?
   private final var preparedTag: String?
   private final var analysis: ScalarAnalysis?
   private final var scalarStyle: ScalarStyle?
   private final val blockCommentsCollector: CommentEventsCollector
   private final val inlineCommentsCollector: CommentEventsCollector

   init {
      this.opts = opts;
      this.stream = stream;
      this.states = new ArrayDeque<>(100);
      this.state = new Emitter.ExpectStreamStart(this);
      this.events = new ArrayDeque<>(100);
      this.indents = new ArrayDeque<>(100);
      this.whitespace = true;
      this.indention = true;
      this.canonical = this.opts.isCanonical();
      this.multiLineFlow = this.opts.isMultiLineFlow();
      this.allowUnicode = this.opts.isUseUnicodeEncoding();
      val var3: IntRange = VALID_INDENT_RANGE;
      val var4: Int = VALID_INDENT_RANGE.getFirst();
      val var5: Int = var3.getLast();
      val var6: Int = this.opts.getIndent();
      this.bestIndent = if (var4 <= var6 && var6 <= var5) this.opts.getIndent() else 2;
      this.bestWidth = if (this.opts.getWidth() > this.bestIndent * 2) this.opts.getWidth() else 80;
      this.tagPrefixes = new LinkedHashMap<>();
      this.blockCommentsCollector = new CommentEventsCollector(this.events, CommentType.BLANK_LINE, CommentType.BLOCK);
      this.inlineCommentsCollector = new CommentEventsCollector(this.events, CommentType.IN_LINE);
   }

   public override fun emit(event: Event) {
      this.events.add(event);

      while (!this.needMoreEvents()) {
         this.event = this.events.removeFirst();
         this.state.expect();
         this.event = null;
      }
   }

   private fun needMoreEvents(): Boolean {
      if (this.events.isEmpty()) {
         return true;
      } else {
         val iter: java.util.Iterator = this.events.iterator();

         var event: Event;
         for (event = (Event)iter.next(); event instanceof CommentEvent; event = (Event)iter.next()) {
            if (!iter.hasNext()) {
               return true;
            }
         }

         return if (event is DocumentStartEvent)
            this.needEvents(iter, 1)
            else
            (
               if (event is SequenceStartEvent)
                  this.needEvents(iter, 2)
                  else
                  (
                     if (event is MappingStartEvent)
                        this.needEvents(iter, 3)
                        else
                        (
                           if (event is StreamStartEvent)
                              this.needEvents(iter, 2)
                              else
                              event !is StreamEndEvent && this.getEmitComments() && this.needEvents(iter, 1)
                        )
                  )
            );
      }
   }

   private fun needEvents(iter: Iterator<Event>, count: Int): Boolean {
      var level: Int = 0;
      var actualCount: Int = 0;
      val var5: java.util.Iterator = iter;

      while (var5.hasNext()) {
         val event: Event = var5.next() as Event;
         if (event !is CommentEvent) {
            actualCount++;
            if (event is DocumentStartEvent || event is CollectionStartEvent) {
               level++;
            } else if (event is DocumentEndEvent || event is CollectionEndEvent) {
               level--;
            } else if (event is StreamEndEvent) {
               level = -1;
            }

            if (level < 0) {
               return false;
            }
         }
      }

      return actualCount < count;
   }

   private fun increaseIndent(isFlow: Boolean = false, indentless: Boolean = false) {
      this.indents.addLast(this.indent);
      if (this.indent == null) {
         this.indent = if (isFlow) this.bestIndent else 0;
      } else if (!indentless) {
         val var10001: Int = this.indent;
         this.indent = var10001 + this.bestIndent;
      }
   }

   private fun expectNode(root: Boolean = false, mapping: Boolean = false, simpleKey: Boolean = false) {
      this.rootContext = root;
      this.mappingContext = mapping;
      this.simpleKeyContext = simpleKey;
      val var10000: Event.ID = if (this.event != null) this.event.getEventId() else null;
      switch (var10000 == null ? -1 : Emitter.WhenMappings.$EnumSwitchMapping$0[var10000.ordinal()]) {
         case 1:
            this.expectAlias(simpleKey);
            break;
         case 2:
         case 3:
         case 4:
            this.processAnchor();
            this.processTag();
            val var10001: Event = this.event;
            this.handleNodeEvent(var10001.getEventId());
            break;
         default:
            throw new EmitterException("expected NodeEvent, but got ${if (this.event != null) this.event.getEventId() else null}");
      }
   }

   private fun handleNodeEvent(id: ID) {
      switch (Emitter.WhenMappings.$EnumSwitchMapping$0[id.ordinal()]) {
         case 2:
            this.expectScalar();
            break;
         case 3:
            if (this.flowLevel == 0 && !this.canonical) {
               val var2: Event = this.event;
               if (!(var2 as SequenceStartEvent).isFlow() && !this.checkEmptySequence()) {
                  this.expectBlockSequence();
                  break;
               }
            }

            this.expectFlowSequence();
            break;
         case 4:
            if (this.flowLevel == 0 && !this.canonical) {
               val var10000: Event = this.event;
               if (!(var10000 as MappingStartEvent).isFlow() && !this.checkEmptyMapping()) {
                  this.expectBlockMapping();
                  break;
               }
            }

            this.expectFlowMapping();
            break;
         default:
            throw new IllegalStateException(("Unexpected Event.ID $id").toString());
      }
   }

   private fun expectAlias(simpleKey: Boolean) {
      if (this.event is AliasEvent) {
         this.processAlias(simpleKey);
         this.state = this.states.removeLast();
      } else {
         throw new EmitterException("Expecting Alias.");
      }
   }

   private fun expectScalar() {
      increaseIndent$default(this, true, false, 2, null);
      val var10001: Event = this.event;
      this.processScalar(var10001 as ScalarEvent);
      this.indent = this.indents.removeLastOrNull();
      this.state = this.states.removeLast();
   }

   private fun expectFlowSequence() {
      writeIndicator$default(this, "[", true, true, false, 8, null);
      val var1: Int = this.flowLevel++;
      increaseIndent$default(this, true, false, 2, null);
      if (this.multiLineFlow) {
         this.writeIndent();
      }

      this.state = new Emitter.ExpectFirstFlowSequenceItem(this);
   }

   private fun expectFlowMapping() {
      writeIndicator$default(this, "{", true, true, false, 8, null);
      val var1: Int = this.flowLevel++;
      increaseIndent$default(this, true, false, 2, null);
      if (this.multiLineFlow) {
         this.writeIndent();
      }

      this.state = new Emitter.ExpectFirstFlowMappingKey(this);
   }

   private fun expectBlockSequence() {
      increaseIndent$default(this, false, this.mappingContext && !this.indention, 1, null);
      this.state = new Emitter.ExpectFirstBlockSequenceItem(this);
   }

   private fun expectBlockMapping() {
      increaseIndent$default(this, false, false, 3, null);
      this.state = new Emitter.ExpectFirstBlockMappingKey(this);
   }

   private fun checkEmptySequence(): Boolean {
      return (if (this.event != null) this.event.getEventId() else null) === Event.ID.SequenceStart
         && !this.events.isEmpty()
         && this.events.first().getEventId() === Event.ID.SequenceEnd;
   }

   private fun checkEmptyMapping(): Boolean {
      return (if (this.event != null) this.event.getEventId() else null) === Event.ID.MappingStart
         && !this.events.isEmpty()
         && this.events.first().getEventId() === Event.ID.MappingEnd;
   }

   private fun checkSimpleKey(): Boolean {
      var length: Int = 0;
      if (this.event is NodeEvent) {
         val var10000: Event = this.event;
         val tag: Anchor = (var10000 as NodeEvent).getAnchor();
         if (tag != null) {
            if (this.preparedAnchor == null) {
               this.preparedAnchor = tag;
            }

            length = 0 + tag.getValue().length();
         }
      }

      val var5: java.lang.String;
      if ((if (this.event != null) this.event.getEventId() else null) === Event.ID.Scalar) {
         val var4: Event = this.event;
         var5 = (var4 as ScalarEvent).getTag();
      } else if (this.event is CollectionStartEvent) {
         val var6: Event = this.event;
         var5 = (var6 as CollectionStartEvent).getTag();
      } else {
         var5 = null;
      }

      if (var5 != null) {
         if (this.preparedTag == null) {
            this.preparedTag = this.prepareTag(var5);
         }

         val var10001: java.lang.String = this.preparedTag;
         length += var10001.length();
      }

      if ((if (this.event != null) this.event.getEventId() else null) === Event.ID.Scalar) {
         if (this.analysis == null) {
            val var10002: Event = this.event;
            this.analysis = this.analyzeScalar((var10002 as ScalarEvent).getValue());
         }

         val var10: ScalarAnalysis = this.analysis;
         length += var10.getScalar().length();
      }

      if (length < this.getMaxSimpleKeyLength()) {
         if ((if (this.event != null) this.event.getEventId() else null) === Event.ID.Alias) {
            return true;
         }

         if ((if (this.event != null) this.event.getEventId() else null) === Event.ID.Scalar) {
            val var7: ScalarAnalysis = this.analysis;
            if (!var7.getEmpty()) {
               val var8: ScalarAnalysis = this.analysis;
               if (!var8.getMultiline()) {
                  return true;
               }
            }
         }

         if (this.checkEmptySequence() || this.checkEmptyMapping()) {
            return true;
         }
      }

      return false;
   }

   private fun processAnchorOrAlias(indicator: String, trailingWhitespace: Boolean) {
      val var10000: Event = this.event;
      val anchor: Anchor = (var10000 as NodeEvent).getAnchor();
      if (anchor != null) {
         if (this.preparedAnchor == null) {
            this.preparedAnchor = anchor;
         }

         writeIndicator$default(this, "$indicator$anchor", true, false, false, 12, null);
      }

      this.preparedAnchor = null;
      if (trailingWhitespace) {
         this.writeWhitespace(1);
      }
   }

   private fun processAnchor() {
      this.processAnchorOrAlias("&", false);
   }

   private fun processAlias(simpleKey: Boolean) {
      this.processAnchorOrAlias("*", simpleKey);
   }

   private fun processTag() {
      var var3: java.lang.String;
      if ((if (this.event != null) this.event.getEventId() else null) === Event.ID.Scalar) {
         val var6: Event = this.event;
         val var4: ScalarEvent = var6 as ScalarEvent;
         var3 = (var6 as ScalarEvent).getTag();
         if (this.scalarStyle == null) {
            this.scalarStyle = this.chooseScalarStyle(var4);
         }

         if ((!this.canonical || var3 == null)
            && (
               this.scalarStyle === ScalarStyle.PLAIN && var4.getImplicit().canOmitTagInPlainScalar()
                  || this.scalarStyle != ScalarStyle.PLAIN && var4.getImplicit().canOmitTagInNonPlainScalar()
            )) {
            this.preparedTag = null;
            return;
         }

         if (var4.getImplicit().canOmitTagInPlainScalar() && var3 == null) {
            var3 = "!";
            this.preparedTag = null;
         }
      } else {
         val var10000: Event = this.event;
         val evx: CollectionStartEvent = var10000 as CollectionStartEvent;
         var3 = (var10000 as CollectionStartEvent).getTag();
         if ((!this.canonical || var3 == null) && evx.isImplicit()) {
            this.preparedTag = null;
            return;
         }
      }

      if (var3 == null) {
         throw new EmitterException("tag is not specified");
      } else {
         var var7: java.lang.String = this.preparedTag;
         if (this.preparedTag == null) {
            var7 = this.prepareTag(var3);
         }

         writeIndicator$default(this, var7, true, false, false, 12, null);
      }
   }

   private fun chooseScalarStyle(ev: ScalarEvent): ScalarStyle? {
      if (this.analysis == null) {
         this.analysis = this.analyzeScalar(ev.getValue());
      }

      if ((ev.getPlain() || !ev.getDQuoted()) && !this.canonical) {
         if (ev.getJson() && ev.getTag() == Tag.STR.getValue()) {
            return ScalarStyle.DOUBLE_QUOTED;
         } else {
            label76:
            if ((ev.getPlain() || ev.getJson()) && ev.getImplicit().canOmitTagInPlainScalar()) {
               if (this.simpleKeyContext) {
                  var var10000: ScalarAnalysis = this.analysis;
                  if (var10000.getEmpty()) {
                     break label76;
                  }

                  var10000 = this.analysis;
                  if (var10000.getMultiline()) {
                     break label76;
                  }
               }

               if (this.flowLevel != 0) {
                  val var3: ScalarAnalysis = this.analysis;
                  if (var3.getAllowFlowPlain()) {
                     return ScalarStyle.PLAIN;
                  }
               }

               if (this.flowLevel == 0) {
                  val var4: ScalarAnalysis = this.analysis;
                  if (var4.getAllowBlockPlain()) {
                     return ScalarStyle.PLAIN;
                  }
               }
            }

            if ((ev.getLiteral() || ev.getFolded()) && this.flowLevel == 0 && !this.simpleKeyContext) {
               val var5: ScalarAnalysis = this.analysis;
               if (var5.getAllowBlock()) {
                  return ev.getScalarStyle();
               }
            }

            if (ev.getPlain() || ev.getSQuoted()) {
               var var6: ScalarAnalysis = this.analysis;
               if (var6.getAllowSingleQuoted()) {
                  if (!this.simpleKeyContext) {
                     return ScalarStyle.SINGLE_QUOTED;
                  }

                  var6 = this.analysis;
                  if (!var6.getMultiline()) {
                     return ScalarStyle.SINGLE_QUOTED;
                  }
               }
            }

            return ScalarStyle.DOUBLE_QUOTED;
         }
      } else {
         return ScalarStyle.DOUBLE_QUOTED;
      }
   }

   private fun processScalar(ev: ScalarEvent) {
      if (this.analysis == null) {
         this.analysis = this.analyzeScalar(ev.getValue());
      }

      val split: Boolean = !this.simpleKeyContext && this.getSplitLines();
      switch (this.scalarStyle == null ? -1 : Emitter.WhenMappings.$EnumSwitchMapping$1[this.scalarStyle.ordinal()]) {
         case 1:
            val var6: ScalarAnalysis = this.analysis;
            this.writePlain(var6.getScalar(), split);
            break;
         case 2:
            val var5: ScalarAnalysis = this.analysis;
            this.writeDoubleQuoted(var5.getScalar(), split);
            break;
         case 3:
            val var4: ScalarAnalysis = this.analysis;
            this.writeSingleQuoted(var4.getScalar(), split);
            break;
         case 4:
            val var3: ScalarAnalysis = this.analysis;
            this.writeFolded(var3.getScalar(), split);
            break;
         case 5:
            val var10001: ScalarAnalysis = this.analysis;
            this.writeLiteral(var10001.getScalar());
            break;
         default:
            throw new YamlEngineException("Unexpected scalarStyle: ${this.scalarStyle}");
      }

      this.analysis = null;
      this.scalarStyle = null;
   }

   private fun prepareVersion(version: SpecVersion): String {
      if (version.getMajor() != 1) {
         throw new EmitterException("unsupported YAML version: $version");
      } else {
         return version.getRepresentation();
      }
   }

   private fun prepareTag(tag: String): String {
      if (tag.length() == 0) {
         throw new EmitterException("tag must not be empty");
      } else if ("!" == tag) {
         return tag;
      } else {
         val var5: java.util.Iterator = this.tagPrefixes.keySet().iterator();

         var var10000: Any;
         while (true) {
            if (!var5.hasNext()) {
               var10000 = null;
               break;
            }

            val `element$iv`: Any = var5.next();
            if (`element$iv` as java.lang.String != null
               && StringsKt.startsWith$default(tag, `element$iv` as java.lang.String, false, 2, null)
               && ("!" == `element$iv` as java.lang.String || (`element$iv` as java.lang.String).length() < tag.length())) {
               var10000 = `element$iv`;
               break;
            }
         }

         val matchedPrefix: java.lang.String = var10000 as java.lang.String;
         val var10: java.lang.String;
         val var12: java.lang.String;
         if (var10000 as java.lang.String != null) {
            var10 = this.tagPrefixes.get(matchedPrefix);
            var10000 = tag.substring(matchedPrefix.length());
            var12 = (java.lang.String)var10000;
         } else {
            var10 = null;
            var12 = tag;
         }

         return if (var10 != null) "$var10$var12" else "!<$var12>";
      }
   }

   private fun analyzeScalar(scalar: String): ScalarAnalysis {
      if (scalar.length() == 0) {
         return new ScalarAnalysis(scalar, true, false, false, true, true, false);
      } else {
         var blockIndicators: Boolean = false;
         var flowIndicators: Boolean = false;
         var lineBreaks: Boolean = false;
         var specialCharacters: Boolean = false;
         var leadingSpace: Boolean = false;
         var leadingBreak: Boolean = false;
         var trailingSpace: Boolean = false;
         var trailingBreak: Boolean = false;
         var breakSpace: Boolean = false;
         var spaceBreak: Boolean = false;
         if (StringsKt.startsWith$default(scalar, "---", false, 2, null) || StringsKt.startsWith$default(scalar, "...", false, 2, null)) {
            blockIndicators = true;
            flowIndicators = true;
         }

         var precededByWhitespace: Boolean = true;
         var followedByWhitespace: Boolean = scalar.length() == 1 || CharConstants.NULL_BL_T_LINEBR.has(CharSequenceExtensionsKt.codePointAt(scalar, 1));
         var previousSpace: Boolean = false;
         var previousBreak: Boolean = false;
         var index: Int = 0;

         while (index < scalar.length()) {
            val allowFlowPlain: Int = CharSequenceExtensionsKt.codePointAt(scalar, index);
            if (index == 0) {
               if (StringsKt.contains$default("#,[]{}&*!|>'\"%@`", (char)allowFlowPlain, false, 2, null)) {
                  flowIndicators = true;
                  blockIndicators = true;
               }

               switch (c) {
                  case 58:
                  case 63:
                     flowIndicators = true;
                     if (followedByWhitespace) {
                        blockIndicators = true;
                     }
                  default:
                     if (allowFlowPlain == 45 && followedByWhitespace) {
                        flowIndicators = true;
                        blockIndicators = true;
                     }
               }
            } else {
               if (StringsKt.contains$default(",?[]{}", (char)allowFlowPlain, false, 2, null)) {
                  flowIndicators = true;
               }

               if (allowFlowPlain == 58) {
                  flowIndicators = true;
                  if (followedByWhitespace) {
                     blockIndicators = true;
                  }
               }

               if (allowFlowPlain == 35 && precededByWhitespace) {
                  flowIndicators = true;
                  blockIndicators = true;
               }
            }

            val allowBlockPlain: Boolean = CharConstants.LINEBR.has(allowFlowPlain);
            if (allowBlockPlain) {
               lineBreaks = true;
            }

            if (allowFlowPlain != 10 && (32 > allowFlowPlain || allowFlowPlain >= 127)) {
               if (allowFlowPlain != 133
                  && (160 > allowFlowPlain || allowFlowPlain >= 55296)
                  && (57344 > allowFlowPlain || allowFlowPlain >= 65534)
                  && (65536 > allowFlowPlain || allowFlowPlain >= 1114112)) {
                  specialCharacters = true;
               } else if (!this.allowUnicode) {
                  specialCharacters = true;
               }
            }

            if (allowFlowPlain == 32) {
               if (index == 0) {
                  leadingSpace = true;
               }

               if (index == scalar.length() - 1) {
                  trailingSpace = true;
               }

               if (previousBreak) {
                  breakSpace = true;
               }

               previousSpace = true;
               previousBreak = false;
            } else if (allowBlockPlain) {
               if (index == 0) {
                  leadingBreak = true;
               }

               if (index == scalar.length() - 1) {
                  trailingBreak = true;
               }

               if (previousSpace) {
                  spaceBreak = true;
               }

               previousSpace = false;
               previousBreak = true;
            } else {
               previousSpace = false;
               previousBreak = false;
            }

            index += Character.INSTANCE.charCount$snakeyaml_engine_kmp(allowFlowPlain);
            precededByWhitespace = CharConstants.NULL_BL_T.has(allowFlowPlain) || allowBlockPlain;
            followedByWhitespace = true;
            if (index + 1 < scalar.length()) {
               val allowSingleQuoted: Int = index + Character.INSTANCE.charCount$snakeyaml_engine_kmp(CharSequenceExtensionsKt.codePointAt(scalar, index));
               if (allowSingleQuoted < scalar.length()) {
                  followedByWhitespace = CharConstants.NULL_BL_T.has(CharSequenceExtensionsKt.codePointAt(scalar, allowSingleQuoted)) || allowBlockPlain;
               }
            }
         }

         var var21: Boolean = true;
         var var22: Boolean = true;
         var var23: Boolean = true;
         var allowBlock: Boolean = true;
         if (leadingSpace || leadingBreak || trailingSpace || trailingBreak) {
            var22 = false;
            var21 = false;
         }

         if (trailingSpace) {
            allowBlock = false;
         }

         if (breakSpace) {
            var23 = false;
            var22 = false;
            var21 = false;
         }

         if (spaceBreak || specialCharacters) {
            allowBlock = false;
            var23 = false;
            var22 = false;
            var21 = false;
         }

         if (lineBreaks) {
            var21 = false;
         }

         if (flowIndicators) {
            var21 = false;
         }

         if (blockIndicators) {
            var22 = false;
         }

         return new ScalarAnalysis(scalar, false, lineBreaks, var21, var22, var23, allowBlock);
      }
   }

   private fun flushStream() {
      this.stream.flush();
   }

   private fun writeStreamStart() {
   }

   private fun writeStreamEnd() {
      this.flushStream();
   }

   private fun writeIndicator(indicator: String, needWhitespace: Boolean = false, whitespace: Boolean = false, indentation: Boolean = false) {
      if (!this.whitespace && needWhitespace) {
         val var5: Int = this.column++;
         this.stream.write(" ");
      }

      this.whitespace = whitespace;
      this.indention = this.indention && indentation;
      this.column = this.column + indicator.length();
      this.openEnded = false;
      this.stream.write(indicator);
   }

   private fun writeIndent(): Int {
      val indentToWrite: Int = if (this.indent != null) this.indent else 0;
      if (!this.indention || this.column > indentToWrite || this.column == indentToWrite && !this.whitespace) {
         writeLineBreak$default(this, null, 1, null);
      }

      val whitespaces: Int = indentToWrite - this.column;
      this.writeWhitespace(indentToWrite - this.column);
      return whitespaces;
   }

   private fun writeWhitespace(length: Int) {
      if (length > 0) {
         this.whitespace = true;
         this.stream.write(StringsKt.repeat(" ", length));
         this.column += length;
      }
   }

   private fun writeLineBreak(data: String? = null) {
      this.whitespace = true;
      this.indention = true;
      this.column = 0;
      var var10001: java.lang.String = data;
      if (data == null) {
         var10001 = this.getBestLineBreak();
      }

      this.stream.write(var10001);
   }

   public fun writeVersionDirective(versionText: String) {
      this.stream.write("%YAML $versionText");
      writeLineBreak$default(this, null, 1, null);
   }

   public fun writeTagDirective(handleText: String, prefixText: String) {
      this.stream.write("%TAG $handleText $prefixText");
      writeLineBreak$default(this, null, 1, null);
   }

   private fun writeSingleQuoted(text: String, split: Boolean) {
      // $VF: Couldn't be decompiled
      // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
      // java.lang.IndexOutOfBoundsException: Index 0 out of bounds for length 0
      //   at java.base/jdk.internal.util.Preconditions.outOfBounds(Preconditions.java:100)
      //   at java.base/jdk.internal.util.Preconditions.outOfBoundsCheckIndex(Preconditions.java:106)
      //   at java.base/jdk.internal.util.Preconditions.checkIndex(Preconditions.java:302)
      //   at java.base/java.util.Objects.checkIndex(Objects.java:365)
      //   at java.base/java.util.ArrayList.get(ArrayList.java:428)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.DoStatement.getInitExprent(DoStatement.java:267)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.DoStatement.getSequentialObjects(DoStatement.java:163)
      //   at org.jetbrains.java.decompiler.modules.decompiler.StackVarsProcessor.setVersionsToNull(StackVarsProcessor.java:96)
      //   at org.jetbrains.java.decompiler.modules.decompiler.StackVarsProcessor.setVersionsToNull(StackVarsProcessor.java:98)
      //   at org.jetbrains.java.decompiler.modules.decompiler.StackVarsProcessor.setVersionsToNull(StackVarsProcessor.java:98)
      //
      // Bytecode:
      // 000: aload 0
      // 001: ldc_w "'"
      // 004: bipush 1
      // 005: bipush 0
      // 006: bipush 0
      // 007: bipush 12
      // 009: aconst_null
      // 00a: invokestatic it/krzeminski/snakeyaml/engine/kmp/emitter/Emitter.writeIndicator$default (Lit/krzeminski/snakeyaml/engine/kmp/emitter/Emitter;Ljava/lang/String;ZZZILjava/lang/Object;)V
      // 00d: bipush 0
      // 00e: istore 3
      // 00f: bipush 0
      // 010: istore 4
      // 012: bipush 0
      // 013: istore 5
      // 015: bipush 0
      // 016: istore 6
      // 018: bipush 0
      // 019: istore 7
      // 01b: iload 6
      // 01d: aload 1
      // 01e: invokevirtual java/lang/String.length ()I
      // 021: if_icmpgt 19e
      // 024: bipush 0
      // 025: istore 7
      // 027: iload 6
      // 029: aload 1
      // 02a: invokevirtual java/lang/String.length ()I
      // 02d: if_icmpge 038
      // 030: aload 1
      // 031: iload 6
      // 033: invokevirtual java/lang/String.charAt (I)C
      // 036: istore 7
      // 038: iload 3
      // 039: ifeq 098
      // 03c: iload 7
      // 03e: bipush 32
      // 040: if_icmpeq 159
      // 043: iload 5
      // 045: bipush 1
      // 046: iadd
      // 047: iload 6
      // 049: if_icmpne 071
      // 04c: aload 0
      // 04d: getfield it/krzeminski/snakeyaml/engine/kmp/emitter/Emitter.column I
      // 050: aload 0
      // 051: getfield it/krzeminski/snakeyaml/engine/kmp/emitter/Emitter.bestWidth I
      // 054: if_icmple 071
      // 057: iload 2
      // 058: ifeq 071
      // 05b: iload 5
      // 05d: ifeq 071
      // 060: iload 6
      // 062: aload 1
      // 063: invokevirtual java/lang/String.length ()I
      // 066: if_icmpeq 071
      // 069: aload 0
      // 06a: invokespecial it/krzeminski/snakeyaml/engine/kmp/emitter/Emitter.writeIndent ()I
      // 06d: pop
      // 06e: goto 091
      // 071: iload 6
      // 073: iload 5
      // 075: isub
      // 076: istore 8
      // 078: aload 0
      // 079: aload 0
      // 07a: getfield it/krzeminski/snakeyaml/engine/kmp/emitter/Emitter.column I
      // 07d: iload 8
      // 07f: iadd
      // 080: putfield it/krzeminski/snakeyaml/engine/kmp/emitter/Emitter.column I
      // 083: aload 0
      // 084: getfield it/krzeminski/snakeyaml/engine/kmp/emitter/Emitter.stream Lit/krzeminski/snakeyaml/engine/kmp/api/StreamDataWriter;
      // 087: aload 1
      // 088: iload 5
      // 08a: iload 8
      // 08c: invokeinterface it/krzeminski/snakeyaml/engine/kmp/api/StreamDataWriter.write (Ljava/lang/String;II)V 4
      // 091: iload 6
      // 093: istore 5
      // 095: goto 159
      // 098: iload 4
      // 09a: ifeq 120
      // 09d: iload 7
      // 09f: ifeq 0ad
      // 0a2: getstatic it/krzeminski/snakeyaml/engine/kmp/common/CharConstants.LINEBR Lit/krzeminski/snakeyaml/engine/kmp/common/CharConstants;
      // 0a5: iload 7
      // 0a7: invokevirtual it/krzeminski/snakeyaml/engine/kmp/common/CharConstants.hasNo (I)Z
      // 0aa: ifeq 159
      // 0ad: aload 1
      // 0ae: iload 5
      // 0b0: invokevirtual java/lang/String.charAt (I)C
      // 0b3: bipush 10
      // 0b5: if_icmpne 0bf
      // 0b8: aload 0
      // 0b9: aconst_null
      // 0ba: bipush 1
      // 0bb: aconst_null
      // 0bc: invokestatic it/krzeminski/snakeyaml/engine/kmp/emitter/Emitter.writeLineBreak$default (Lit/krzeminski/snakeyaml/engine/kmp/emitter/Emitter;Ljava/lang/String;ILjava/lang/Object;)V
      // 0bf: aload 1
      // 0c0: iload 5
      // 0c2: iload 6
      // 0c4: invokevirtual java/lang/String.substring (II)Ljava/lang/String;
      // 0c7: dup
      // 0c8: ldc_w "substring(...)"
      // 0cb: invokestatic kotlin/jvm/internal/Intrinsics.checkNotNullExpressionValue (Ljava/lang/Object;Ljava/lang/String;)V
      // 0ce: astore 8
      // 0d0: aload 8
      // 0d2: invokevirtual java/lang/String.toCharArray ()[C
      // 0d5: dup
      // 0d6: ldc_w "toCharArray(...)"
      // 0d9: invokestatic kotlin/jvm/internal/Intrinsics.checkNotNullExpressionValue (Ljava/lang/Object;Ljava/lang/String;)V
      // 0dc: astore 9
      // 0de: bipush 0
      // 0df: istore 10
      // 0e1: aload 9
      // 0e3: arraylength
      // 0e4: istore 11
      // 0e6: iload 10
      // 0e8: iload 11
      // 0ea: if_icmpge 114
      // 0ed: aload 9
      // 0ef: iload 10
      // 0f1: caload
      // 0f2: istore 12
      // 0f4: iload 12
      // 0f6: bipush 10
      // 0f8: if_icmpne 105
      // 0fb: aload 0
      // 0fc: aconst_null
      // 0fd: bipush 1
      // 0fe: aconst_null
      // 0ff: invokestatic it/krzeminski/snakeyaml/engine/kmp/emitter/Emitter.writeLineBreak$default (Lit/krzeminski/snakeyaml/engine/kmp/emitter/Emitter;Ljava/lang/String;ILjava/lang/Object;)V
      // 102: goto 10e
      // 105: aload 0
      // 106: iload 12
      // 108: invokestatic java/lang/String.valueOf (C)Ljava/lang/String;
      // 10b: invokespecial it/krzeminski/snakeyaml/engine/kmp/emitter/Emitter.writeLineBreak (Ljava/lang/String;)V
      // 10e: iinc 10 1
      // 111: goto 0e6
      // 114: aload 0
      // 115: invokespecial it/krzeminski/snakeyaml/engine/kmp/emitter/Emitter.writeIndent ()I
      // 118: pop
      // 119: iload 6
      // 11b: istore 5
      // 11d: goto 159
      // 120: getstatic it/krzeminski/snakeyaml/engine/kmp/common/CharConstants.LINEBR Lit/krzeminski/snakeyaml/engine/kmp/common/CharConstants;
      // 123: iload 7
      // 125: ldc_w "\u0000 '"
      // 128: invokevirtual it/krzeminski/snakeyaml/engine/kmp/common/CharConstants.has (ILjava/lang/String;)Z
      // 12b: ifeq 159
      // 12e: iload 5
      // 130: iload 6
      // 132: if_icmpge 159
      // 135: iload 6
      // 137: iload 5
      // 139: isub
      // 13a: istore 8
      // 13c: aload 0
      // 13d: aload 0
      // 13e: getfield it/krzeminski/snakeyaml/engine/kmp/emitter/Emitter.column I
      // 141: iload 8
      // 143: iadd
      // 144: putfield it/krzeminski/snakeyaml/engine/kmp/emitter/Emitter.column I
      // 147: aload 0
      // 148: getfield it/krzeminski/snakeyaml/engine/kmp/emitter/Emitter.stream Lit/krzeminski/snakeyaml/engine/kmp/api/StreamDataWriter;
      // 14b: aload 1
      // 14c: iload 5
      // 14e: iload 8
      // 150: invokeinterface it/krzeminski/snakeyaml/engine/kmp/api/StreamDataWriter.write (Ljava/lang/String;II)V 4
      // 155: iload 6
      // 157: istore 5
      // 159: iload 7
      // 15b: bipush 39
      // 15d: if_icmpne 17c
      // 160: aload 0
      // 161: aload 0
      // 162: getfield it/krzeminski/snakeyaml/engine/kmp/emitter/Emitter.column I
      // 165: bipush 2
      // 166: iadd
      // 167: putfield it/krzeminski/snakeyaml/engine/kmp/emitter/Emitter.column I
      // 16a: aload 0
      // 16b: getfield it/krzeminski/snakeyaml/engine/kmp/emitter/Emitter.stream Lit/krzeminski/snakeyaml/engine/kmp/api/StreamDataWriter;
      // 16e: ldc_w "''"
      // 171: invokeinterface it/krzeminski/snakeyaml/engine/kmp/api/StreamDataWriter.write (Ljava/lang/String;)V 2
      // 176: iload 6
      // 178: bipush 1
      // 179: iadd
      // 17a: istore 5
      // 17c: iload 7
      // 17e: ifeq 198
      // 181: iload 7
      // 183: bipush 32
      // 185: if_icmpne 18c
      // 188: bipush 1
      // 189: goto 18d
      // 18c: bipush 0
      // 18d: istore 3
      // 18e: getstatic it/krzeminski/snakeyaml/engine/kmp/common/CharConstants.LINEBR Lit/krzeminski/snakeyaml/engine/kmp/common/CharConstants;
      // 191: iload 7
      // 193: invokevirtual it/krzeminski/snakeyaml/engine/kmp/common/CharConstants.has (I)Z
      // 196: istore 4
      // 198: iinc 6 1
      // 19b: goto 01b
      // 19e: aload 0
      // 19f: ldc_w "'"
      // 1a2: bipush 0
      // 1a3: bipush 0
      // 1a4: bipush 0
      // 1a5: bipush 14
      // 1a7: aconst_null
      // 1a8: invokestatic it/krzeminski/snakeyaml/engine/kmp/emitter/Emitter.writeIndicator$default (Lit/krzeminski/snakeyaml/engine/kmp/emitter/Emitter;Ljava/lang/String;ZZZILjava/lang/Object;)V
      // 1ab: return
   }

   private fun writeDoubleQuoted(text: String, split: Boolean) {
      writeIndicator$default(this, "\"", true, false, false, 12, null);
      var start: Int = 0;

      for (int end = 0; end <= text.length(); end++) {
         var ch: java.lang.Character = null;
         if (end < text.length()) {
            ch = text.charAt(end);
         }

         if (ch == null
            || StringsKt.contains$default("\"\\\u0085\u2028\u2029\ufeff", ch, false, 2, null)
            || Intrinsics.compare(32, ch) > 0
            || Intrinsics.compare(ch, 126) > 0) {
            if (start < end) {
               this.column += end - start;
               this.stream.write(text, start, end - start);
               start = end;
            }

            if (ch != null) {
               val var10: java.lang.String;
               if (ESCAPE_REPLACEMENTS.containsKey(ch)) {
                  var10 = "\${ESCAPE_REPLACEMENTS.get(ch)}";
               } else {
                  val var10000: Int = if (java.lang.Character.isHighSurrogate(ch) && end + 1 < text.length())
                     Character.INSTANCE.toCodePoint$snakeyaml_engine_kmp(ch, text.charAt(end + 1))
                     else
                     ch;
                  if (this.allowUnicode && StreamReader.Companion.isPrintable(var10000)) {
                     var10 = StringsKt.concatToString(Character.INSTANCE.toChars$snakeyaml_engine_kmp(var10000));
                     if (Character.INSTANCE.charCount$snakeyaml_engine_kmp(var10000) == 2) {
                        end++;
                     }
                  } else {
                     val var20: java.lang.String;
                     if (Intrinsics.compare(ch, 255) <= 0) {
                        val var18: StringBuilder = new StringBuilder().append('0');
                        var var10001: java.lang.String = Integer.toString(ch, CharsKt.checkRadix(16));
                        val var15: java.lang.String = var18.append(var10001).toString();
                        val var19: StringBuilder = new StringBuilder().append("\\x");
                        var10001 = var15.substring(var15.length() - 2);
                        var20 = var19.append(var10001).toString();
                     } else if (Character.INSTANCE.charCount$snakeyaml_engine_kmp(var10000) == 2) {
                        end++;
                        val var21: StringBuilder = new StringBuilder().append("000");
                        var var28: java.lang.String = Integer.toString(var10000, CharsKt.checkRadix(16));
                        val var16: java.lang.String = var21.append(var28).toString();
                        val var22: StringBuilder = new StringBuilder().append("\\U");
                        var28 = var16.substring(var16.length() - 8);
                        var20 = var22.append(var28).toString();
                     } else {
                        val var23: StringBuilder = new StringBuilder().append("000");
                        var var30: java.lang.String = Integer.toString(ch, CharsKt.checkRadix(16));
                        val var17: java.lang.String = var23.append(var30).toString();
                        val var24: StringBuilder = new StringBuilder().append("\\u");
                        var30 = var17.substring(var17.length() - 4);
                        var20 = var24.append(var30).toString();
                     }

                     var10 = var20;
                  }
               }

               this.column = this.column + var10.length();
               this.stream.write(var10);
               start = end + 1;
            }
         }

         if (0 < end && end < text.length() - 1) {
            label70: {
               if (ch != null) {
                  if (ch == ' ') {
                     break label70;
                  }
               }

               if (start < end) {
                  continue;
               }
            }

            if (this.column + (end - start) > this.bestWidth && split) {
               val var25: java.lang.String;
               if (start >= end) {
                  var25 = "\\";
               } else {
                  val var26: StringBuilder = new StringBuilder();
                  val var32: java.lang.String = text.substring(start, end);
                  var25 = var26.append(var32).append('\\').toString();
               }

               if (start < end) {
                  start = end;
               }

               this.column = this.column + var25.length();
               this.stream.write(var25);
               this.writeIndent();
               this.whitespace = false;
               this.indention = false;
               if (text.charAt(start) == ' ') {
                  this.column = this.column + "\\".length();
                  this.stream.write("\\");
               }
            }
         }
      }

      writeIndicator$default(this, "\"", false, false, false, 14, null);
   }

   private fun writeCommentLines(commentLines: List<CommentLine>): Boolean {
      var wroteComment: Boolean = false;
      if (this.getEmitComments()) {
         var indentColumns: Int = 0;
         var prevColumns: Int = 0;
         var firstComment: Boolean = true;

         for (CommentLine commentLine : commentLines) {
            if (commentLine.commentType != CommentType.BLANK_LINE) {
               if (firstComment) {
                  firstComment = false;
                  writeIndicator$default(this, "#", commentLine.commentType === CommentType.IN_LINE, false, false, 12, null);
                  indentColumns = if (this.column > 0) this.column - 1 else 0;
               } else {
                  this.writeWhitespace(indentColumns - prevColumns);
                  writeIndicator$default(this, "#", false, false, false, 14, null);
               }

               this.stream.write(commentLine.value);
               writeLineBreak$default(this, null, 1, null);
               prevColumns = 0;
            } else {
               writeLineBreak$default(this, null, 1, null);
               prevColumns = this.writeIndent();
            }

            wroteComment = true;
         }
      }

      return wroteComment;
   }

   private fun writeBlockComment() {
      if (!this.blockCommentsCollector.isEmpty()) {
         this.writeIndent();
         this.writeCommentLines(this.blockCommentsCollector.consume());
      }
   }

   private fun writeInlineComments(): Boolean {
      return this.writeCommentLines(this.inlineCommentsCollector.consume());
   }

   private fun determineBlockHints(text: String): String {
      val hints: StringBuilder = new StringBuilder();
      if (CharConstants.LINEBR.has(StringsKt.first(text), " ")) {
         hints.append(this.bestIndent);
      }

      if (CharConstants.LINEBR.hasNo(StringsKt.last(text))) {
         hints.append("-");
      } else if (text.length() == 1 || CharConstants.LINEBR.has(text.charAt(text.length() - 2))) {
         hints.append("+");
      }

      val var10000: java.lang.String = hints.toString();
      return var10000;
   }

   private fun writeFolded(text: String, split: Boolean) {
      val hints: java.lang.String = this.determineBlockHints(text);
      writeIndicator$default(this, ">$hints", true, false, false, 12, null);
      if (StringsKt.endsWith$default(hints, '+', false, 2, null)) {
         this.openEnded = true;
      }

      if (!this.writeInlineComments()) {
         writeLineBreak$default(this, null, 1, null);
      }

      var leadingSpace: Boolean = true;
      var spaces: Boolean = false;
      var breaks: Boolean = true;
      var start: Int = 0;

      for (int end = 0; end <= text.length(); end++) {
         var ch: Char = 0;
         if (end < text.length()) {
            ch = text.charAt(end);
         }

         if (breaks) {
            if (ch == 0 || CharConstants.LINEBR.hasNo(ch)) {
               if (!leadingSpace && ch != 0 && ch != ' ' && text.charAt(start) == '\n') {
                  writeLineBreak$default(this, null, 1, null);
               }

               leadingSpace = ch == ' ';
               val var10000: java.lang.String = text.substring(start, end);
               val var17: CharArray = var10000.toCharArray();

               for (char br : var17) {
                  this.writeLineBreak(if (br == '\n') null else java.lang.String.valueOf(br));
               }

               if (ch != 0) {
                  this.writeIndent();
               }

               start = end;
            }
         } else if (spaces) {
            if (ch != ' ') {
               if (start + 1 == end && this.column > this.bestWidth && split) {
                  this.writeIndent();
               } else {
                  this.column += end - start;
                  this.stream.write(text, start, end - start);
               }

               start = end;
            }
         } else if (CharConstants.LINEBR.has(ch, "\u0000 ")) {
            this.column += end - start;
            this.stream.write(text, start, end - start);
            if (ch == 0) {
               writeLineBreak$default(this, null, 1, null);
            }

            start = end;
         }

         if (ch != 0) {
            breaks = CharConstants.LINEBR.has(ch);
            spaces = ch == ' ';
         }
      }
   }

   private fun writeLiteral(text: String) {
      val hints: java.lang.String = this.determineBlockHints(text);
      writeIndicator$default(this, "|$hints", true, false, false, 12, null);
      if (StringsKt.endsWith$default(hints, '+', false, 2, null)) {
         this.openEnded = true;
      }

      if (!this.writeInlineComments()) {
         writeLineBreak$default(this, null, 1, null);
      }

      var breaks: Boolean = true;
      var start: Int = 0;

      for (int end = 0; end <= text.length(); end++) {
         var ch: Char = 0;
         if (end < text.length()) {
            ch = text.charAt(end);
         }

         if (!breaks) {
            if (ch == 0 || CharConstants.LINEBR.has(ch)) {
               this.stream.write(text, start, end - start);
               if (ch == 0) {
                  writeLineBreak$default(this, null, 1, null);
               }

               start = end;
            }
         } else if (ch == 0 || CharConstants.LINEBR.hasNo(ch)) {
            val var10000: java.lang.String = text.substring(start, end);
            val var12: CharArray = var10000.toCharArray();

            for (char br : var12) {
               if (br == '\n') {
                  writeLineBreak$default(this, null, 1, null);
               } else {
                  this.writeLineBreak(java.lang.String.valueOf(br));
               }
            }

            if (ch != 0) {
               this.writeIndent();
            }

            start = end;
         }

         if (ch != 0) {
            breaks = CharConstants.LINEBR.has(ch);
         }
      }
   }

   private fun writePlain(text: String, split: Boolean) {
      if (this.rootContext) {
         this.openEnded = true;
      }

      if (text.length() != 0) {
         if (!this.whitespace) {
            val spaces: Int = this.column++;
            this.stream.write(" ");
         }

         this.whitespace = false;
         this.indention = false;
         var var13: Boolean = false;
         var breaks: Boolean = false;
         var start: Int = 0;

         for (int end = 0; end <= text.length(); end++) {
            var ch: Char = 0;
            if (end < text.length()) {
               ch = text.charAt(end);
            }

            if (var13) {
               if (ch != ' ') {
                  if (start + 1 == end && this.column > this.bestWidth && split) {
                     this.writeIndent();
                     this.whitespace = false;
                     this.indention = false;
                  } else {
                     this.column += end - start;
                     this.stream.write(text, start, end - start);
                  }

                  start = end;
               }
            } else if (!breaks) {
               if (CharConstants.LINEBR.has(ch, "\u0000 ")) {
                  this.column += end - start;
                  this.stream.write(text, start, end - start);
                  start = end;
               }
            } else if (CharConstants.LINEBR.hasNo(ch)) {
               if (text.charAt(start) == '\n') {
                  writeLineBreak$default(this, null, 1, null);
               }

               val var10000: java.lang.String = text.substring(start, end);
               val var16: CharArray = var10000.toCharArray();

               for (char br : var16) {
                  this.writeLineBreak(if (br == '\n') null else java.lang.String.valueOf(br));
               }

               this.writeIndent();
               this.whitespace = false;
               this.indention = false;
               start = end;
            }

            if (ch != 0) {
               var13 = ch == ' ';
               breaks = CharConstants.LINEBR.has(ch);
            }
         }
      }
   }

   public companion object {
      private final val ESCAPE_REPLACEMENTS: Map<Char, String>
      private final val DEFAULT_TAG_PREFIXES: Map<String, String>
      public final val VALID_INDENT_RANGE: IntRange
      public final val VALID_INDICATOR_INDENT_RANGE: IntRange
      private const val DEFAULT_INDENT: Int
      private const val DEFAULT_WIDTH: Int
      private const val SPACE: String
      private final val HANDLE_FORMAT: Regex
   }

   private inner class ExpectBlockMappingKey(first: Boolean) : EmitterState {
      private final val first: Boolean

      init {
         this.this$0 = `this$0`;
         this.first = first;
      }

      public override fun expect() {
         Emitter.access$setEvent$p(
            this.this$0, Emitter.access$getBlockCommentsCollector$p(this.this$0).collectEventsAndPoll(Emitter.access$getEvent$p(this.this$0))
         );
         Emitter.access$writeBlockComment(this.this$0);
         if (!this.first) {
            val var10000: Event = Emitter.access$getEvent$p(this.this$0);
            if ((if (var10000 != null) var10000.getEventId() else null) === Event.ID.MappingEnd) {
               Emitter.access$setIndent$p(this.this$0, Emitter.access$getIndents$p(this.this$0).removeLastOrNull() as Int);
               Emitter.access$setState$p(this.this$0, Emitter.access$getStates$p(this.this$0).removeLast() as EmitterState);
               return;
            }
         }

         Emitter.access$writeIndent(this.this$0);
         if (Emitter.access$checkSimpleKey(this.this$0)) {
            Emitter.access$getStates$p(this.this$0).addLast(this.this$0.new ExpectBlockMappingSimpleValue(this.this$0));
            Emitter.expectNode$default(this.this$0, false, true, true, 1, null);
         } else {
            Emitter.writeIndicator$default(this.this$0, "?", true, false, true, 4, null);
            Emitter.access$getStates$p(this.this$0).addLast(this.this$0.new ExpectBlockMappingValue(this.this$0));
            Emitter.expectNode$default(this.this$0, false, true, false, 5, null);
         }
      }
   }

   private inner class ExpectBlockMappingSimpleValue : EmitterState {
      init {
         this.this$0 = `this$0`;
      }

      public override fun expect() {
         Emitter.writeIndicator$default(this.this$0, ":", false, false, false, 14, null);
         Emitter.access$setEvent$p(
            this.this$0, Emitter.access$getInlineCommentsCollector$p(this.this$0).collectEventsAndPoll(Emitter.access$getEvent$p(this.this$0))
         );
         val var10001: Event = Emitter.access$getEvent$p(this.this$0);
         if (!this.isFoldedOrLiteral(var10001) && Emitter.access$writeInlineComments(this.this$0)) {
            Emitter.increaseIndent$default(this.this$0, true, false, 2, null);
            Emitter.access$writeIndent(this.this$0);
            Emitter.access$setIndent$p(this.this$0, Emitter.access$getIndents$p(this.this$0).removeLastOrNull() as Int);
         }

         Emitter.access$setEvent$p(
            this.this$0, Emitter.access$getBlockCommentsCollector$p(this.this$0).collectEventsAndPoll(Emitter.access$getEvent$p(this.this$0))
         );
         if (!Emitter.access$getBlockCommentsCollector$p(this.this$0).isEmpty()) {
            Emitter.increaseIndent$default(this.this$0, true, false, 2, null);
            Emitter.access$writeBlockComment(this.this$0);
            Emitter.access$writeIndent(this.this$0);
            Emitter.access$setIndent$p(this.this$0, Emitter.access$getIndents$p(this.this$0).removeLastOrNull() as Int);
         }

         Emitter.access$getStates$p(this.this$0).addLast(this.this$0.new ExpectBlockMappingKey((boolean)this.this$0, false));
         Emitter.expectNode$default(this.this$0, false, true, false, 5, null);
         Emitter.access$getInlineCommentsCollector$p(this.this$0).collectEvents();
         Emitter.access$writeInlineComments(this.this$0);
      }

      private fun isFoldedOrLiteral(event: Event): Boolean {
         return event is ScalarEvent
            && ((event as ScalarEvent).getScalarStyle() === ScalarStyle.FOLDED || (event as ScalarEvent).getScalarStyle() === ScalarStyle.LITERAL);
      }
   }

   private inner class ExpectBlockMappingValue : EmitterState {
      init {
         this.this$0 = `this$0`;
      }

      public override fun expect() {
         Emitter.access$writeIndent(this.this$0);
         Emitter.writeIndicator$default(this.this$0, ":", true, false, true, 4, null);
         Emitter.access$setEvent$p(
            this.this$0, Emitter.access$getInlineCommentsCollector$p(this.this$0).collectEventsAndPoll(Emitter.access$getEvent$p(this.this$0))
         );
         Emitter.access$writeInlineComments(this.this$0);
         Emitter.access$setEvent$p(
            this.this$0, Emitter.access$getBlockCommentsCollector$p(this.this$0).collectEventsAndPoll(Emitter.access$getEvent$p(this.this$0))
         );
         Emitter.access$writeBlockComment(this.this$0);
         Emitter.access$getStates$p(this.this$0).addLast(this.this$0.new ExpectBlockMappingKey((boolean)this.this$0, false));
         Emitter.expectNode$default(this.this$0, false, true, false, 5, null);
         Emitter.access$getInlineCommentsCollector$p(this.this$0).collectEvents(Emitter.access$getEvent$p(this.this$0));
         Emitter.access$writeInlineComments(this.this$0);
      }
   }

   private inner class ExpectBlockSequenceItem(first: Boolean) : EmitterState {
      private final val first: Boolean

      init {
         this.this$0 = `this$0`;
         this.first = first;
      }

      public override fun expect() {
         if (!this.first) {
            val var10000: Event = Emitter.access$getEvent$p(this.this$0);
            if ((if (var10000 != null) var10000.getEventId() else null) === Event.ID.SequenceEnd) {
               Emitter.access$setIndent$p(this.this$0, Emitter.access$getIndents$p(this.this$0).removeLastOrNull() as Int);
               Emitter.access$setState$p(this.this$0, Emitter.access$getStates$p(this.this$0).removeLast() as EmitterState);
               return;
            }
         }

         if (Emitter.access$getEvent$p(this.this$0) is CommentEvent) {
            Emitter.access$getBlockCommentsCollector$p(this.this$0).collectEvents(Emitter.access$getEvent$p(this.this$0));
         } else {
            Emitter.access$writeIndent(this.this$0);
            if (!Emitter.access$getIndentWithIndicator(this.this$0) || this.first) {
               Emitter.access$writeWhitespace(this.this$0, Emitter.access$getIndicatorIndent(this.this$0));
            }

            Emitter.writeIndicator$default(this.this$0, "-", true, false, true, 4, null);
            if (Emitter.access$getIndentWithIndicator(this.this$0) && this.first) {
               val var2: Emitter = this.this$0;
               val var10001: Int = Emitter.access$getIndent$p(this.this$0);
               Emitter.access$setIndent$p(var2, var10001 + Emitter.access$getIndicatorIndent(this.this$0));
            }

            if (!Emitter.access$getBlockCommentsCollector$p(this.this$0).isEmpty()) {
               Emitter.increaseIndent$default(this.this$0, false, false, 3, null);
               Emitter.access$writeBlockComment(this.this$0);
               if (Emitter.access$getEvent$p(this.this$0) is ScalarEvent) {
                  val var3: Event = Emitter.access$getEvent$p(this.this$0);
                  val scalarEvent: ScalarEvent = var3 as ScalarEvent;
                  Emitter.access$setAnalysis$p(this.this$0, Emitter.access$analyzeScalar(this.this$0, (var3 as ScalarEvent).getValue()));
                  if (Emitter.access$getScalarStyle$p(this.this$0) == null) {
                     Emitter.access$setScalarStyle$p(this.this$0, Emitter.access$chooseScalarStyle(this.this$0, scalarEvent));
                  }

                  val var4: ScalarAnalysis = Emitter.access$getAnalysis$p(this.this$0);
                  if (!var4.getEmpty()
                     || Emitter.access$getScalarStyle$p(this.this$0) === ScalarStyle.SINGLE_QUOTED
                     || Emitter.access$getScalarStyle$p(this.this$0) === ScalarStyle.DOUBLE_QUOTED) {
                     Emitter.access$writeIndent(this.this$0);
                  }
               }

               Emitter.access$setIndent$p(this.this$0, Emitter.access$getIndents$p(this.this$0).removeLastOrNull() as Int);
            }

            Emitter.access$getStates$p(this.this$0).addLast(this.this$0.new ExpectBlockSequenceItem((boolean)this.this$0, false));
            Emitter.expectNode$default(this.this$0, false, false, false, 7, null);
            Emitter.access$getInlineCommentsCollector$p(this.this$0).collectEvents();
            Emitter.access$writeInlineComments(this.this$0);
         }
      }
   }

   private inner class ExpectDocumentEnd : EmitterState {
      init {
         this.this$0 = `this$0`;
      }

      public override fun expect() {
         Emitter.access$setEvent$p(
            this.this$0, Emitter.access$getBlockCommentsCollector$p(this.this$0).collectEventsAndPoll(Emitter.access$getEvent$p(this.this$0))
         );
         Emitter.access$writeBlockComment(this.this$0);
         var var10000: Event = Emitter.access$getEvent$p(this.this$0);
         if ((if (var10000 != null) var10000.getEventId() else null) === Event.ID.DocumentEnd) {
            Emitter.access$writeIndent(this.this$0);
            var10000 = Emitter.access$getEvent$p(this.this$0);
            if ((var10000 as DocumentEndEvent).isExplicit()) {
               Emitter.writeIndicator$default(this.this$0, "...", true, false, false, 12, null);
               Emitter.access$writeIndent(this.this$0);
            }

            Emitter.access$flushStream(this.this$0);
            Emitter.access$setState$p(this.this$0, this.this$0.new ExpectDocumentStart((boolean)this.this$0, false));
         } else {
            throw new EmitterException("expected DocumentEndEvent, but got ${Emitter.access$getEvent$p(this.this$0)}");
         }
      }
   }

   private inner class ExpectDocumentRoot : EmitterState {
      init {
         this.this$0 = `this$0`;
      }

      public override fun expect() {
         Emitter.access$setEvent$p(
            this.this$0, Emitter.access$getBlockCommentsCollector$p(this.this$0).collectEventsAndPoll(Emitter.access$getEvent$p(this.this$0))
         );
         if (!Emitter.access$getBlockCommentsCollector$p(this.this$0).isEmpty()) {
            Emitter.access$writeBlockComment(this.this$0);
            if (Emitter.access$getEvent$p(this.this$0) is DocumentEndEvent) {
               this.this$0.new ExpectDocumentEnd(this.this$0).expect();
               return;
            }
         }

         Emitter.access$getStates$p(this.this$0).addLast(this.this$0.new ExpectDocumentEnd(this.this$0));
         Emitter.expectNode$default(this.this$0, true, false, false, 6, null);
      }
   }

   private inner class ExpectDocumentStart(first: Boolean) : EmitterState {
      private final val first: Boolean

      init {
         this.this$0 = `this$0`;
         this.first = first;
      }

      public override fun expect() {
         var var10000: Event = Emitter.access$getEvent$p(this.this$0);
         if ((if (var10000 != null) var10000.getEventId() else null) === Event.ID.DocumentStart) {
            var10000 = Emitter.access$getEvent$p(this.this$0);
            this.handleDocumentStartEvent(var10000 as DocumentStartEvent);
            Emitter.access$setState$p(this.this$0, this.this$0.new ExpectDocumentRoot(this.this$0));
         } else {
            var10000 = Emitter.access$getEvent$p(this.this$0);
            if ((if (var10000 != null) var10000.getEventId() else null) === Event.ID.StreamEnd) {
               Emitter.access$writeStreamEnd(this.this$0);
               Emitter.access$setState$p(this.this$0, this.this$0.new ExpectNothing(this.this$0));
            } else {
               if (Emitter.access$getEvent$p(this.this$0) !is CommentEvent) {
                  throw new EmitterException("expected DocumentStartEvent, but got ${Emitter.access$getEvent$p(this.this$0)}");
               }

               Emitter.access$getBlockCommentsCollector$p(this.this$0).collectEvents(Emitter.access$getEvent$p(this.this$0));
               Emitter.access$writeBlockComment(this.this$0);
            }
         }
      }

      private fun handleDocumentStartEvent(ev: DocumentStartEvent) {
         if ((ev.getSpecVersion() != null || !ev.getTags().isEmpty()) && Emitter.access$getOpenEnded$p(this.this$0)) {
            Emitter.writeIndicator$default(this.this$0, "...", true, false, false, 12, null);
            Emitter.access$writeIndent(this.this$0);
         }

         if (ev.getSpecVersion() != null) {
            this.this$0.writeVersionDirective(Emitter.access$prepareVersion(this.this$0, ev.getSpecVersion()));
         }

         Emitter.access$setTagPrefixes$p(this.this$0, MapsKt.toMutableMap(Emitter.access$getDEFAULT_TAG_PREFIXES$cp()));
         if (!ev.getTags().isEmpty()) {
            this.handleTagDirectives(ev.getTags());
         }

         if (!this.first
            || ev.getExplicit()
            || Emitter.access$getCanonical$p(this.this$0)
            || ev.getSpecVersion() != null
            || !ev.getTags().isEmpty()
            || this.checkEmptyDocument()) {
            Emitter.access$writeIndent(this.this$0);
            Emitter.writeIndicator$default(this.this$0, "---", true, false, false, 12, null);
            if (Emitter.access$getCanonical$p(this.this$0)) {
               Emitter.access$writeIndent(this.this$0);
            }
         }
      }

      private fun handleTagDirectives(tags: Map<String, String>) {
         for (Entry var3 : tags.entrySet()) {
            val handle: java.lang.String = var3.getKey() as java.lang.String;
            val prefix: java.lang.String = var3.getValue() as java.lang.String;
            Emitter.access$getTagPrefixes$p(this.this$0).put(prefix, handle);
            this.checkTagHandle(handle);
            this.checkTagPrefix(prefix);
            this.this$0.writeTagDirective(handle, prefix);
         }
      }

      private fun checkTagHandle(handle: String) {
         if (handle.length() == 0) {
            throw new EmitterException("tag handle must not be empty");
         } else if (!StringsKt.startsWith$default(handle, '!', false, 2, null) || !StringsKt.endsWith$default(handle, '!', false, 2, null)) {
            throw new EmitterException("tag handle must start and end with '!': $handle");
         } else if (!(handle == "!") && !Emitter.access$getHANDLE_FORMAT$cp().matches(handle)) {
            throw new EmitterException("invalid character in the tag handle: $handle");
         }
      }

      private fun checkTagPrefix(prefix: String) {
         if (prefix.length() == 0) {
            throw new EmitterException("tag prefix must not be empty");
         }
      }

      private fun checkEmptyDocument(): Boolean {
         val var10000: Event = Emitter.access$getEvent$p(this.this$0);
         if ((if (var10000 != null) var10000.getEventId() else null) === Event.ID.DocumentStart && !Emitter.access$getEvents$p(this.this$0).isEmpty()) {
            val nextEvent: Event = Emitter.access$getEvents$p(this.this$0).first() as Event;
            if (nextEvent.getEventId() != Event.ID.Scalar) {
               return false;
            } else {
               return (nextEvent as ScalarEvent).getAnchor() == null
                  && (nextEvent as ScalarEvent).getTag() == null
                  && (nextEvent as ScalarEvent).getValue().length() == 0;
            }
         } else {
            return false;
         }
      }
   }

   private inner class ExpectFirstBlockMappingKey : EmitterState {
      init {
         this.this$0 = `this$0`;
      }

      public override fun expect() {
         this.this$0.new ExpectBlockMappingKey((boolean)this.this$0, true).expect();
      }
   }

   private inner class ExpectFirstBlockSequenceItem : EmitterState {
      init {
         this.this$0 = `this$0`;
      }

      public override fun expect() {
         this.this$0.new ExpectBlockSequenceItem((boolean)this.this$0, true).expect();
      }
   }

   private inner class ExpectFirstDocumentStart : EmitterState {
      init {
         this.this$0 = `this$0`;
      }

      public override fun expect() {
         this.this$0.new ExpectDocumentStart((boolean)this.this$0, true).expect();
      }
   }

   private inner class ExpectFirstFlowMappingKey : EmitterState {
      init {
         this.this$0 = `this$0`;
      }

      public override fun expect() {
         Emitter.access$setEvent$p(
            this.this$0, Emitter.access$getBlockCommentsCollector$p(this.this$0).collectEventsAndPoll(Emitter.access$getEvent$p(this.this$0))
         );
         Emitter.access$writeBlockComment(this.this$0);
         val var10000: Event = Emitter.access$getEvent$p(this.this$0);
         if ((if (var10000 != null) var10000.getEventId() else null) === Event.ID.MappingEnd) {
            Emitter.access$setIndent$p(this.this$0, Emitter.access$getIndents$p(this.this$0).removeLastOrNull() as Int);
            Emitter.access$setFlowLevel$p(this.this$0, Emitter.access$getFlowLevel$p(this.this$0) + -1);
            Emitter.writeIndicator$default(this.this$0, "}", false, false, false, 14, null);
            Emitter.access$getInlineCommentsCollector$p(this.this$0).collectEvents();
            Emitter.access$writeInlineComments(this.this$0);
            Emitter.access$setState$p(this.this$0, Emitter.access$getStates$p(this.this$0).removeLast() as EmitterState);
         } else {
            if (Emitter.access$getCanonical$p(this.this$0)
               || Emitter.access$getColumn$p(this.this$0) > Emitter.access$getBestWidth$p(this.this$0) && Emitter.access$getSplitLines(this.this$0)
               || Emitter.access$getMultiLineFlow$p(this.this$0)) {
               Emitter.access$writeIndent(this.this$0);
            }

            if (!Emitter.access$getCanonical$p(this.this$0) && Emitter.access$checkSimpleKey(this.this$0)) {
               Emitter.access$getStates$p(this.this$0).addLast(this.this$0.new ExpectFlowMappingSimpleValue(this.this$0));
               Emitter.expectNode$default(this.this$0, false, true, true, 1, null);
            } else {
               Emitter.writeIndicator$default(this.this$0, "?", true, false, false, 12, null);
               Emitter.access$getStates$p(this.this$0).addLast(this.this$0.new ExpectFlowMappingValue(this.this$0));
               Emitter.expectNode$default(this.this$0, false, true, false, 5, null);
            }
         }
      }
   }

   private inner class ExpectFirstFlowSequenceItem : EmitterState {
      init {
         this.this$0 = `this$0`;
      }

      public override fun expect() {
         val var10000: Event = Emitter.access$getEvent$p(this.this$0);
         if ((if (var10000 != null) var10000.getEventId() else null) === Event.ID.SequenceEnd) {
            Emitter.access$setIndent$p(this.this$0, Emitter.access$getIndents$p(this.this$0).removeLastOrNull() as Int);
            Emitter.access$setFlowLevel$p(this.this$0, Emitter.access$getFlowLevel$p(this.this$0) + -1);
            Emitter.writeIndicator$default(this.this$0, "]", false, false, false, 14, null);
            Emitter.access$getInlineCommentsCollector$p(this.this$0).collectEvents();
            Emitter.access$writeInlineComments(this.this$0);
            Emitter.access$setState$p(this.this$0, Emitter.access$getStates$p(this.this$0).removeLast() as EmitterState);
         } else if (Emitter.access$getEvent$p(this.this$0) is CommentEvent) {
            Emitter.access$getBlockCommentsCollector$p(this.this$0).collectEvents(Emitter.access$getEvent$p(this.this$0));
            Emitter.access$writeBlockComment(this.this$0);
         } else {
            if (Emitter.access$getCanonical$p(this.this$0)
               || Emitter.access$getColumn$p(this.this$0) > Emitter.access$getBestWidth$p(this.this$0) && Emitter.access$getSplitLines(this.this$0)
               || Emitter.access$getMultiLineFlow$p(this.this$0)) {
               Emitter.access$writeIndent(this.this$0);
            }

            Emitter.access$getStates$p(this.this$0).addLast(this.this$0.new ExpectFlowSequenceItem(this.this$0));
            Emitter.expectNode$default(this.this$0, false, false, false, 7, null);
            Emitter.access$setEvent$p(
               this.this$0, Emitter.access$getInlineCommentsCollector$p(this.this$0).collectEvents(Emitter.access$getEvent$p(this.this$0))
            );
            Emitter.access$writeInlineComments(this.this$0);
         }
      }
   }

   private inner class ExpectFlowMappingKey : EmitterState {
      init {
         this.this$0 = `this$0`;
      }

      public override fun expect() {
         val var10000: Event = Emitter.access$getEvent$p(this.this$0);
         if ((if (var10000 != null) var10000.getEventId() else null) === Event.ID.MappingEnd) {
            Emitter.access$setIndent$p(this.this$0, Emitter.access$getIndents$p(this.this$0).removeLastOrNull() as Int);
            Emitter.access$setFlowLevel$p(this.this$0, Emitter.access$getFlowLevel$p(this.this$0) + -1);
            if (Emitter.access$getCanonical$p(this.this$0)) {
               Emitter.writeIndicator$default(this.this$0, ",", false, false, false, 14, null);
               Emitter.access$writeIndent(this.this$0);
            }

            if (Emitter.access$getMultiLineFlow$p(this.this$0)) {
               Emitter.access$writeIndent(this.this$0);
            }

            Emitter.writeIndicator$default(this.this$0, "}", false, false, false, 14, null);
            Emitter.access$getInlineCommentsCollector$p(this.this$0).collectEvents();
            Emitter.access$writeInlineComments(this.this$0);
            Emitter.access$setState$p(this.this$0, Emitter.access$getStates$p(this.this$0).removeLast() as EmitterState);
         } else {
            Emitter.writeIndicator$default(this.this$0, ",", false, false, false, 14, null);
            Emitter.access$setEvent$p(
               this.this$0, Emitter.access$getBlockCommentsCollector$p(this.this$0).collectEventsAndPoll(Emitter.access$getEvent$p(this.this$0))
            );
            Emitter.access$writeBlockComment(this.this$0);
            if (Emitter.access$getCanonical$p(this.this$0)
               || Emitter.access$getColumn$p(this.this$0) > Emitter.access$getBestWidth$p(this.this$0) && Emitter.access$getSplitLines(this.this$0)
               || Emitter.access$getMultiLineFlow$p(this.this$0)) {
               Emitter.access$writeIndent(this.this$0);
            }

            if (!Emitter.access$getCanonical$p(this.this$0) && Emitter.access$checkSimpleKey(this.this$0)) {
               Emitter.access$getStates$p(this.this$0).addLast(this.this$0.new ExpectFlowMappingSimpleValue(this.this$0));
               Emitter.expectNode$default(this.this$0, false, true, true, 1, null);
            } else {
               Emitter.writeIndicator$default(this.this$0, "?", true, false, false, 12, null);
               Emitter.access$getStates$p(this.this$0).addLast(this.this$0.new ExpectFlowMappingValue(this.this$0));
               Emitter.expectNode$default(this.this$0, false, true, false, 5, null);
            }
         }
      }
   }

   private inner class ExpectFlowMappingSimpleValue : EmitterState {
      init {
         this.this$0 = `this$0`;
      }

      public override fun expect() {
         Emitter.writeIndicator$default(this.this$0, ":", false, false, false, 14, null);
         Emitter.access$setEvent$p(
            this.this$0, Emitter.access$getInlineCommentsCollector$p(this.this$0).collectEventsAndPoll(Emitter.access$getEvent$p(this.this$0))
         );
         Emitter.access$writeInlineComments(this.this$0);
         Emitter.access$getStates$p(this.this$0).addLast(this.this$0.new ExpectFlowMappingKey(this.this$0));
         Emitter.expectNode$default(this.this$0, false, true, false, 5, null);
         Emitter.access$getInlineCommentsCollector$p(this.this$0).collectEvents();
         Emitter.access$writeInlineComments(this.this$0);
      }
   }

   private inner class ExpectFlowMappingValue : EmitterState {
      init {
         this.this$0 = `this$0`;
      }

      public override fun expect() {
         if (Emitter.access$getCanonical$p(this.this$0)
            || Emitter.access$getColumn$p(this.this$0) > Emitter.access$getBestWidth$p(this.this$0)
            || Emitter.access$getMultiLineFlow$p(this.this$0)) {
            Emitter.access$writeIndent(this.this$0);
         }

         Emitter.writeIndicator$default(this.this$0, ":", true, false, false, 12, null);
         Emitter.access$setEvent$p(
            this.this$0, Emitter.access$getInlineCommentsCollector$p(this.this$0).collectEventsAndPoll(Emitter.access$getEvent$p(this.this$0))
         );
         Emitter.access$writeInlineComments(this.this$0);
         Emitter.access$getStates$p(this.this$0).addLast(this.this$0.new ExpectFlowMappingKey(this.this$0));
         Emitter.expectNode$default(this.this$0, false, true, false, 5, null);
         Emitter.access$getInlineCommentsCollector$p(this.this$0).collectEvents(Emitter.access$getEvent$p(this.this$0));
         Emitter.access$writeInlineComments(this.this$0);
      }
   }

   private inner class ExpectFlowSequenceItem : EmitterState {
      init {
         this.this$0 = `this$0`;
      }

      public override fun expect() {
         val var10000: Event = Emitter.access$getEvent$p(this.this$0);
         if ((if (var10000 != null) var10000.getEventId() else null) === Event.ID.SequenceEnd) {
            Emitter.access$setIndent$p(this.this$0, Emitter.access$getIndents$p(this.this$0).removeLastOrNull() as Int);
            Emitter.access$setFlowLevel$p(this.this$0, Emitter.access$getFlowLevel$p(this.this$0) + -1);
            if (Emitter.access$getCanonical$p(this.this$0)) {
               Emitter.writeIndicator$default(this.this$0, ",", false, false, false, 14, null);
               Emitter.access$writeIndent(this.this$0);
            } else if (Emitter.access$getMultiLineFlow$p(this.this$0)) {
               Emitter.access$writeIndent(this.this$0);
            }

            Emitter.writeIndicator$default(this.this$0, "]", false, false, false, 14, null);
            Emitter.access$getInlineCommentsCollector$p(this.this$0).collectEvents();
            Emitter.access$writeInlineComments(this.this$0);
            if (Emitter.access$getMultiLineFlow$p(this.this$0)) {
               Emitter.access$writeIndent(this.this$0);
            }

            Emitter.access$setState$p(this.this$0, Emitter.access$getStates$p(this.this$0).removeLast() as EmitterState);
         } else if (Emitter.access$getEvent$p(this.this$0) is CommentEvent) {
            Emitter.access$setEvent$p(
               this.this$0, Emitter.access$getBlockCommentsCollector$p(this.this$0).collectEvents(Emitter.access$getEvent$p(this.this$0))
            );
         } else {
            Emitter.writeIndicator$default(this.this$0, ",", false, false, false, 14, null);
            Emitter.access$writeBlockComment(this.this$0);
            if (Emitter.access$getCanonical$p(this.this$0)
               || Emitter.access$getColumn$p(this.this$0) > Emitter.access$getBestWidth$p(this.this$0) && Emitter.access$getSplitLines(this.this$0)
               || Emitter.access$getMultiLineFlow$p(this.this$0)) {
               Emitter.access$writeIndent(this.this$0);
            }

            Emitter.access$getStates$p(this.this$0).addLast(this.this$0.new ExpectFlowSequenceItem(this.this$0));
            Emitter.expectNode$default(this.this$0, false, false, false, 7, null);
            Emitter.access$setEvent$p(
               this.this$0, Emitter.access$getInlineCommentsCollector$p(this.this$0).collectEvents(Emitter.access$getEvent$p(this.this$0))
            );
            Emitter.access$writeInlineComments(this.this$0);
         }
      }
   }

   private inner class ExpectNothing : EmitterState {
      init {
         this.this$0 = `this$0`;
      }

      public override fun expect() {
         throw new EmitterException("expecting nothing, but got ${Emitter.access$getEvent$p(this.this$0)}");
      }
   }

   private inner class ExpectStreamStart : EmitterState {
      init {
         this.this$0 = `this$0`;
      }

      public override fun expect() {
         val var10000: Event = Emitter.access$getEvent$p(this.this$0);
         if ((if (var10000 != null) var10000.getEventId() else null) === Event.ID.StreamStart) {
            Emitter.access$writeStreamStart(this.this$0);
            Emitter.access$setState$p(this.this$0, this.this$0.new ExpectFirstDocumentStart(this.this$0));
         } else {
            throw new EmitterException("expected StreamStartEvent, but got ${Emitter.access$getEvent$p(this.this$0)}");
         }
      }
   }
}
