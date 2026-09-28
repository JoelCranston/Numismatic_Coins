package it.krzeminski.snakeyaml.engine.kmp.scanner

import it.krzeminski.snakeyaml.engine.kmp.api.LoadSettings
import it.krzeminski.snakeyaml.engine.kmp.comments.CommentType
import it.krzeminski.snakeyaml.engine.kmp.common.Anchor
import it.krzeminski.snakeyaml.engine.kmp.common.CharConstants
import it.krzeminski.snakeyaml.engine.kmp.common.ScalarStyle
import it.krzeminski.snakeyaml.engine.kmp.common.UriEncoder
import it.krzeminski.snakeyaml.engine.kmp.exceptions.Mark
import it.krzeminski.snakeyaml.engine.kmp.exceptions.ScannerException
import it.krzeminski.snakeyaml.engine.kmp.exceptions.YamlEngineException
import it.krzeminski.snakeyaml.engine.kmp.internal.utils.Character
import it.krzeminski.snakeyaml.engine.kmp.tokens.AliasToken
import it.krzeminski.snakeyaml.engine.kmp.tokens.AnchorToken
import it.krzeminski.snakeyaml.engine.kmp.tokens.BlockEndToken
import it.krzeminski.snakeyaml.engine.kmp.tokens.BlockEntryToken
import it.krzeminski.snakeyaml.engine.kmp.tokens.BlockMappingStartToken
import it.krzeminski.snakeyaml.engine.kmp.tokens.BlockSequenceStartToken
import it.krzeminski.snakeyaml.engine.kmp.tokens.CommentToken
import it.krzeminski.snakeyaml.engine.kmp.tokens.DirectiveToken
import it.krzeminski.snakeyaml.engine.kmp.tokens.DocumentEndToken
import it.krzeminski.snakeyaml.engine.kmp.tokens.DocumentStartToken
import it.krzeminski.snakeyaml.engine.kmp.tokens.FlowEntryToken
import it.krzeminski.snakeyaml.engine.kmp.tokens.FlowMappingEndToken
import it.krzeminski.snakeyaml.engine.kmp.tokens.FlowMappingStartToken
import it.krzeminski.snakeyaml.engine.kmp.tokens.FlowSequenceEndToken
import it.krzeminski.snakeyaml.engine.kmp.tokens.FlowSequenceStartToken
import it.krzeminski.snakeyaml.engine.kmp.tokens.KeyToken
import it.krzeminski.snakeyaml.engine.kmp.tokens.ScalarToken
import it.krzeminski.snakeyaml.engine.kmp.tokens.StreamEndToken
import it.krzeminski.snakeyaml.engine.kmp.tokens.StreamStartToken
import it.krzeminski.snakeyaml.engine.kmp.tokens.TagToken
import it.krzeminski.snakeyaml.engine.kmp.tokens.TagTuple
import it.krzeminski.snakeyaml.engine.kmp.tokens.Token
import it.krzeminski.snakeyaml.engine.kmp.tokens.ValueToken
import it.krzeminski.snakeyaml.engine.kmp.tokens.DirectiveToken.TagDirective
import it.krzeminski.snakeyaml.engine.kmp.tokens.DirectiveToken.YamlDirective
import it.krzeminski.snakeyaml.engine.kmp.tokens.Token.ID
import java.io.Serializable
import java.nio.charset.CharacterCodingException
import java.util.ArrayList
import java.util.LinkedHashMap
import java.util.NoSuchElementException
import java.util.Map.Entry
import kotlin.jvm.internal.SourceDebugExtension
import okio.Buffer

@SourceDebugExtension(["SMAP\nScannerImpl.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ScannerImpl.kt\nit/krzeminski/snakeyaml/engine/kmp/scanner/ScannerImpl\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,2320:1\n774#2:2321\n865#2,2:2322\n*S KotlinDebug\n*F\n+ 1 ScannerImpl.kt\nit/krzeminski/snakeyaml/engine/kmp/scanner/ScannerImpl\n*L\n2217#1:2321\n2217#1:2322,2\n*E\n"])
public class ScannerImpl(settings: LoadSettings, reader: StreamReader) : Scanner {
   private final val settings: LoadSettings
   private final val reader: StreamReader
   private final val tokens: ArrayDeque<Token>
   private final val indents: ArrayDeque<Int>
   private final val possibleSimpleKeys: MutableMap<Int, SimpleKey>
   private final var done: Boolean
   private final var flowLevel: Int
   private final var lastToken: Token?
   private final var tokensTaken: Int
   private final var indent: Int
   private final var allowSimpleKey: Boolean

   init {
      this.settings = settings;
      this.reader = reader;
      this.tokens = new ArrayDeque<>(100);
      this.indents = new ArrayDeque<>(10);
      this.possibleSimpleKeys = new LinkedHashMap<>();
      this.indent = -1;
      this.allowSimpleKey = true;
      this.fetchStreamStart();
   }

   public override fun checkToken(choice: ID): Boolean {
      while (this.needMoreTokens()) {
         this.fetchMoreTokens();
      }

      if (!this.tokens.isEmpty()) {
         return this.tokens.get(0).getTokenId() === choice;
      } else {
         return false;
      }
   }

   public override fun checkToken(vararg choices: ID): Boolean {
      while (this.needMoreTokens()) {
         this.fetchMoreTokens();
      }

      val var10000: Token = this.tokens.firstOrNull();
      val firstTokenId: Token.ID = if (var10000 != null) var10000.getTokenId() else null;
      return firstTokenId != null && (choices.length == 0 || ArraysKt.contains(choices, firstTokenId));
   }

   public override fun peekToken(): Token {
      while (this.needMoreTokens()) {
         this.fetchMoreTokens();
      }

      return this.tokens.first();
   }

   public override operator fun hasNext(): Boolean {
      return this.checkToken();
   }

   public override operator fun next(): Token {
      val var1: Int = this.tokensTaken++;
      val var10000: Token = this.tokens.removeFirstOrNull();
      if (var10000 == null) {
         throw new NoSuchElementException("No more Tokens found.");
      } else {
         return var10000;
      }
   }

   public override fun resetDocumentIndex() {
      this.reader.resetDocumentIndex();
   }

   private fun addToken(token: Token) {
      this.lastToken = token;
      this.tokens.addLast(token);
   }

   private fun addToken(index: Int, token: Token) {
      if (index == this.tokens.size()) {
         this.lastToken = token;
      }

      this.tokens.add(index, token);
   }

   private fun addAllTokens(tokens: List<Token>) {
      this.lastToken = CollectionsKt.last(tokens);
      CollectionsKt.addAll(this.tokens, tokens);
   }

   private fun isBlockContext(): Boolean {
      return this.flowLevel == 0;
   }

   private fun isFlowContext(): Boolean {
      return !this.isBlockContext();
   }

   private fun needMoreTokens(): Boolean {
      if (this.done) {
         return false;
      } else if (this.tokens.isEmpty()) {
         return true;
      } else {
         this.stalePossibleSimpleKeys();
         val var10000: Int = this.nextPossibleSimpleKey();
         val var1: Int = this.tokensTaken;
         if (var10000 != null) {
            if (var10000 == var1) {
               return true;
            }
         }

         return false;
      }
   }

   private fun fetchMoreTokens() {
      if (this.reader.getDocumentIndex() > this.settings.getCodePointLimit()) {
         throw new YamlEngineException("The incoming YAML document exceeds the limit: ${this.settings.getCodePointLimit()} code points.");
      } else {
         this.scanToNextToken();
         this.stalePossibleSimpleKeys();
         this.unwindIndent(this.reader.getColumn());
         val c: Int = this.reader.peek();
         if (c == 0) {
            this.fetchStreamEnd();
         } else {
            switch ((char)c) {
               case '!':
                  this.fetchTag();
                  return;
               case '"':
                  this.fetchDouble();
                  return;
               case '%':
                  if (this.checkDirective()) {
                     this.fetchDirective();
                     return;
                  }
                  break;
               case '&':
                  this.fetchAnchor();
                  return;
               case '\'':
                  this.fetchSingle();
                  return;
               case '*':
                  this.fetchAlias();
                  return;
               case ',':
                  this.fetchFlowEntry();
                  return;
               case '-':
                  if (this.checkDocumentStart()) {
                     this.fetchDocumentStart();
                     return;
                  }

                  if (this.checkBlockEntry()) {
                     this.fetchBlockEntry();
                     return;
                  }
                  break;
               case '.':
                  if (this.checkDocumentEnd()) {
                     this.fetchDocumentEnd();
                     return;
                  }
                  break;
               case ':':
                  if (this.checkValue()) {
                     this.fetchValue();
                     return;
                  }
                  break;
               case '>':
                  if (this.isBlockContext()) {
                     this.fetchFolded();
                     return;
                  }
                  break;
               case '?':
                  if (this.checkKey()) {
                     this.fetchKey();
                     return;
                  }
                  break;
               case '[':
                  this.fetchFlowSequenceStart();
                  return;
               case ']':
                  this.fetchFlowSequenceEnd();
                  return;
               case '{':
                  this.fetchFlowMappingStart();
                  return;
               case '|':
                  if (this.isBlockContext()) {
                     this.fetchLiteral();
                     return;
                  }
                  break;
               case '}':
                  this.fetchFlowMappingEnd();
                  return;
               default:
            }

            if (this.checkPlain()) {
               this.fetchPlain();
            } else {
               var chRepresentation: java.lang.String = CharConstants.Companion.escapeChar(ArraysKt.first(Character.INSTANCE.toChars$snakeyaml_engine_kmp(c)));
               if (c == 9) {
                  chRepresentation = "$chRepresentation(TAB)";
               }

               throw new ScannerException(
                  "found character '$chRepresentation' that cannot start any token. (Do not use $chRepresentation for indentation)",
                  this.reader.getMark(),
                  "while scanning for the next token",
                  null,
                  null,
                  16,
                  null
               );
            }
         }
      }
   }

   private fun nextPossibleSimpleKey(): Int? {
      val var10000: SimpleKey = CollectionsKt.firstOrNull(this.possibleSimpleKeys.values());
      return if (var10000 != null) var10000.getTokenNumber() else null;
   }

   private fun stalePossibleSimpleKeys() {
      val iterator: java.util.Iterator = this.possibleSimpleKeys.entrySet().iterator();

      while (iterator.hasNext()) {
         val key: SimpleKey = (iterator.next() as Entry).getValue() as SimpleKey;
         if (key.getLine() != this.reader.getLine() || this.reader.getIndex() - key.getIndex() > 1024) {
            if (key.isRequired()) {
               throw new ScannerException("could not find expected ':'", this.reader.getMark(), "while scanning a simple key", key.getMark(), null, 16, null);
            }

            iterator.remove();
         }
      }
   }

   private fun savePossibleSimpleKey() {
      val required: Boolean = this.isBlockContext() && this.indent == this.reader.getColumn();
      if (!this.allowSimpleKey && required) {
         throw new YamlEngineException("A simple key is required only if it is the first token in the current line");
      } else {
         if (this.allowSimpleKey) {
            this.removePossibleSimpleKey();
            this.possibleSimpleKeys
               .put(
                  this.flowLevel,
                  new SimpleKey(
                     this.tokensTaken + this.tokens.size(),
                     required,
                     this.reader.getIndex(),
                     this.reader.getLine(),
                     this.reader.getColumn(),
                     this.reader.getMark()
                  )
               );
         }
      }
   }

   private fun removePossibleSimpleKey() {
      val key: SimpleKey = this.possibleSimpleKeys.remove(this.flowLevel);
      if (key != null && key.isRequired()) {
         throw new ScannerException("could not find expected ':'", this.reader.getMark(), "while scanning a simple key", key.getMark(), null, 16, null);
      }
   }

   private fun unwindIndent(col: Int) {
      if (!this.isFlowContext()) {
         while (this.indent > col) {
            val mark: Mark = this.reader.getMark();
            this.indent = this.indents.removeLast().intValue();
            this.addToken(new BlockEndToken(mark, mark));
         }
      }
   }

   private fun addIndent(column: Int): Boolean {
      if (this.indent >= column) {
         return false;
      } else {
         this.indents.addLast(this.indent);
         this.indent = column;
         return true;
      }
   }

   private fun fetchStreamStart() {
      val mark: Mark = this.reader.getMark();
      this.addToken(new StreamStartToken(mark, mark));
   }

   private fun fetchStreamEnd() {
      this.unwindIndent(-1);
      this.removePossibleSimpleKey();
      this.allowSimpleKey = false;
      this.possibleSimpleKeys.clear();
      val mark: Mark = this.reader.getMark();
      this.addToken(new StreamEndToken(mark, mark));
      this.done = true;
   }

   private fun fetchDirective() {
      this.unwindIndent(-1);
      this.removePossibleSimpleKey();
      this.allowSimpleKey = false;
      this.addAllTokens(this.scanDirective());
   }

   private fun fetchDocumentStart() {
      this.fetchDocumentIndicator(true);
   }

   private fun fetchDocumentEnd() {
      this.fetchDocumentIndicator(false);
   }

   private fun fetchDocumentIndicator(isDocumentStart: Boolean) {
      this.unwindIndent(-1);
      this.removePossibleSimpleKey();
      this.allowSimpleKey = false;
      val startMark: Mark = this.reader.getMark();
      this.reader.forward(3);
      val endMark: Mark = this.reader.getMark();
      this.addToken(if (isDocumentStart) new DocumentStartToken(startMark, endMark) else new DocumentEndToken(startMark, endMark));
   }

   private fun fetchFlowSequenceStart() {
      this.fetchFlowCollectionStart(false);
   }

   private fun fetchFlowMappingStart() {
      this.fetchFlowCollectionStart(true);
   }

   private fun fetchFlowCollectionStart(isMappingStart: Boolean) {
      this.savePossibleSimpleKey();
      val startMark: Int = this.flowLevel++;
      this.allowSimpleKey = true;
      val var5: Mark = this.reader.getMark();
      this.reader.forward(1);
      val endMark: Mark = this.reader.getMark();
      this.addToken(if (isMappingStart) new FlowMappingStartToken(var5, endMark) else new FlowSequenceStartToken(var5, endMark));
   }

   private fun fetchFlowSequenceEnd() {
      this.fetchFlowCollectionEnd(false);
   }

   private fun fetchFlowMappingEnd() {
      this.fetchFlowCollectionEnd(true);
   }

   private fun fetchFlowCollectionEnd(isMappingEnd: Boolean) {
      this.removePossibleSimpleKey();
      this.flowLevel += -1;
      this.allowSimpleKey = false;
      val var5: Mark = this.reader.getMark();
      StreamReader.forward$default(this.reader, 0, 1, null);
      val endMark: Mark = this.reader.getMark();
      this.addToken(if (isMappingEnd) new FlowMappingEndToken(var5, endMark) else new FlowSequenceEndToken(var5, endMark));
   }

   private fun fetchFlowEntry() {
      this.allowSimpleKey = true;
      this.removePossibleSimpleKey();
      val startMark: Mark = this.reader.getMark();
      StreamReader.forward$default(this.reader, 0, 1, null);
      this.addToken(new FlowEntryToken(startMark, this.reader.getMark()));
   }

   private fun fetchBlockEntry() {
      if (this.isBlockContext()) {
         if (!this.allowSimpleKey) {
            throw new ScannerException("", null, "sequence entries are not allowed here", this.reader.getMark(), null, 16, null);
         }

         if (this.addIndent(this.reader.getColumn())) {
            val startMark: Mark = this.reader.getMark();
            this.addToken(new BlockSequenceStartToken(startMark, startMark));
         }
      }

      this.allowSimpleKey = true;
      this.removePossibleSimpleKey();
      val var4: Mark = this.reader.getMark();
      StreamReader.forward$default(this.reader, 0, 1, null);
      this.addToken(new BlockEntryToken(var4, this.reader.getMark()));
   }

   private fun fetchKey() {
      if (this.isBlockContext()) {
         if (!this.allowSimpleKey) {
            throw new ScannerException("mapping keys are not allowed here", this.reader.getMark(), null, null, null, 28, null);
         }

         if (this.addIndent(this.reader.getColumn())) {
            val startMark: Mark = this.reader.getMark();
            this.addToken(new BlockMappingStartToken(startMark, startMark));
         }
      }

      this.allowSimpleKey = this.isBlockContext();
      this.removePossibleSimpleKey();
      val var4: Mark = this.reader.getMark();
      StreamReader.forward$default(this.reader, 0, 1, null);
      this.addToken(new KeyToken(var4, this.reader.getMark()));
   }

   private fun fetchValue() {
      val key: SimpleKey = this.possibleSimpleKeys.remove(this.flowLevel);
      if (key != null) {
         this.addToken(key.getTokenNumber() - this.tokensTaken, new KeyToken(key.getMark(), key.getMark()));
         if (this.isBlockContext() && this.addIndent(key.getColumn())) {
            this.addToken(key.getTokenNumber() - this.tokensTaken, new BlockMappingStartToken(key.getMark(), key.getMark()));
         }

         this.allowSimpleKey = false;
      } else {
         if (this.isBlockContext() && !this.allowSimpleKey) {
            throw new ScannerException("mapping values are not allowed here", this.reader.getMark(), null, null, null, 28, null);
         }

         if (this.isBlockContext() && this.addIndent(this.reader.getColumn())) {
            val startMark: Mark = this.reader.getMark();
            this.addToken(new BlockMappingStartToken(startMark, startMark));
         }

         this.allowSimpleKey = this.isBlockContext();
         this.removePossibleSimpleKey();
      }

      val var5: Mark = this.reader.getMark();
      StreamReader.forward$default(this.reader, 0, 1, null);
      this.addToken(new ValueToken(var5, this.reader.getMark()));
   }

   private fun fetchAlias() {
      this.savePossibleSimpleKey();
      this.allowSimpleKey = false;
      this.addToken(this.scanAnchor(false));
   }

   private fun fetchAnchor() {
      this.savePossibleSimpleKey();
      this.allowSimpleKey = false;
      this.addToken(this.scanAnchor(true));
   }

   private fun fetchTag() {
      this.savePossibleSimpleKey();
      this.allowSimpleKey = false;
      this.addToken(this.scanTag());
   }

   private fun fetchLiteral() {
      this.fetchBlockScalar(ScalarStyle.LITERAL);
   }

   private fun fetchFolded() {
      this.fetchBlockScalar(ScalarStyle.FOLDED);
   }

   private fun fetchBlockScalar(style: ScalarStyle) {
      this.allowSimpleKey = true;
      this.removePossibleSimpleKey();
      this.addAllTokens(this.scanBlockScalar(style));
   }

   private fun fetchSingle() {
      this.fetchFlowScalar(ScalarStyle.SINGLE_QUOTED);
   }

   private fun fetchDouble() {
      this.fetchFlowScalar(ScalarStyle.DOUBLE_QUOTED);
   }

   private fun fetchFlowScalar(style: ScalarStyle?) {
      this.savePossibleSimpleKey();
      this.allowSimpleKey = false;
      this.addToken(this.scanFlowScalar(style));
   }

   private fun fetchPlain() {
      this.savePossibleSimpleKey();
      this.allowSimpleKey = false;
      this.addToken(this.scanPlain());
   }

   private fun checkDirective(): Boolean {
      return this.reader.getColumn() == 0;
   }

   private fun checkDocumentStart(): Boolean {
      return this.checkDirective() && "---" == this.reader.prefix(3) && CharConstants.NULL_BL_T_LINEBR.has(this.reader.peek(3));
   }

   private fun checkDocumentEnd(): Boolean {
      return this.checkDirective() && "..." == this.reader.prefix(3) && CharConstants.NULL_BL_T_LINEBR.has(this.reader.peek(3));
   }

   private fun checkBlockEntry(): Boolean {
      return CharConstants.NULL_BL_T_LINEBR.has(this.reader.peek(1));
   }

   private fun checkKey(): Boolean {
      return CharConstants.NULL_BL_T_LINEBR.has(this.reader.peek(1));
   }

   private fun checkValue(): Boolean {
      return this.isFlowContext() || CharConstants.NULL_BL_T_LINEBR.has(this.reader.peek(1));
   }

   private fun checkPlain(): Boolean {
      val c: Int = this.reader.peek();
      return CharConstants.NULL_BL_T_LINEBR.hasNo(c, "-?:,[]{}#&*!|>'\"%@`")
         || (
            if (this.isBlockContext())
               CharConstants.NULL_BL_T_LINEBR.hasNo(this.reader.peek(1)) && StringsKt.contains$default("-?:", (char)c, false, 2, null)
               else
               CharConstants.NULL_BL_T_LINEBR.hasNo(this.reader.peek(1), ",]") && StringsKt.contains$default("-?", (char)c, false, 2, null)
         );
   }

   private fun scanToNextToken() {
      var found: Boolean = false;
      var inlineStartColumn: Int = -1;

      while (!found) {
         val startMark: Mark = this.reader.getMark();
         val columnBeforeComment: Int = this.reader.getColumn();
         var commentSeen: Boolean = false;
         var ff: Int = 0;

         while (this.reader.peek(ff) == 32) {
            ff++;
         }

         if (this.reader.peek(ff) == 9 && this.isFlowContext()) {
            ff++;
         }

         if (ff > 0) {
            this.reader.forward(ff);
         }

         if (this.reader.peek() == 35) {
            commentSeen = true;
            val var9: CommentType;
            if (columnBeforeComment == 0
               || this.lastToken != null && (if (this.lastToken != null) this.lastToken.getTokenId() else null) === Token.ID.BlockEntry) {
               if (inlineStartColumn == this.reader.getColumn()) {
                  var9 = CommentType.IN_LINE;
               } else {
                  inlineStartColumn = -1;
                  var9 = CommentType.BLOCK;
               }
            } else {
               var9 = CommentType.IN_LINE;
               inlineStartColumn = this.reader.getColumn();
            }

            val token: CommentToken = this.scanComment(var9);
            if (this.settings.getParseComments()) {
               this.addToken(token);
            }
         }

         val var10: java.lang.String = this.scanLineBreak();
         if (var10 != null) {
            if (this.settings.getParseComments() && !commentSeen && columnBeforeComment == 0) {
               this.addToken(new CommentToken(CommentType.BLANK_LINE, var10, startMark, this.reader.getMark()));
            }

            if (this.isBlockContext()) {
               this.allowSimpleKey = true;
            }
         } else {
            found = true;
         }
      }
   }

   private fun scanComment(type: CommentType): CommentToken {
      val startMark: Mark = this.reader.getMark();
      StreamReader.forward$default(this.reader, 0, 1, null);
      var length: Int = 0;

      while (CharConstants.NULL_OR_LINEBR.hasNo(this.reader.peek(length))) {
         length++;
      }

      return new CommentToken(type, this.reader.prefixForward(length), startMark, this.reader.getMark());
   }

   private fun scanDirective(): List<Token> {
      val startMark: Mark = this.reader.getMark();
      StreamReader.forward$default(this.reader, 0, 1, null);
      val name: java.lang.String = this.scanDirectiveName(startMark);
      val var8: Mark;
      val var9: DirectiveToken.TokenValue;
      if ("YAML" == name) {
         var9 = this.scanYamlDirectiveValue(startMark);
         var8 = this.reader.getMark();
      } else if ("TAG" == name) {
         var9 = this.scanTagDirectiveValue(startMark);
         var8 = this.reader.getMark();
      } else {
         var8 = this.reader.getMark();
         var commentToken: Int = 0;

         while (CharConstants.NULL_OR_LINEBR.hasNo(this.reader.peek(ff))) {
            commentToken++;
         }

         if (commentToken > 0) {
            this.reader.forward(commentToken);
         }

         var9 = null;
      }

      return this.makeTokenList(new DirectiveToken(var9, startMark, var8), this.scanDirectiveIgnoredLine(startMark));
   }

   private fun scanDirectiveName(startMark: Mark?): String {
      var length: Int = 0;
      var c: Int = this.reader.peek(0);

      while (CharConstants.ALPHA.has(c)) {
         c = this.reader.peek(++length);
      }

      if (length == 0) {
         throw new ScannerException(
            "while scanning a directive",
            startMark,
            "expected alphabetic or numeric character, but found ${StringsKt.concatToString(Character.INSTANCE.toChars$snakeyaml_engine_kmp(c))}($c)",
            this.reader.getMark(),
            null,
            16,
            null
         );
      } else {
         val value: java.lang.String = this.reader.prefixForward(length);
         c = this.reader.peek();
         if (CharConstants.NULL_BL_LINEBR.hasNo(c)) {
            throw new ScannerException(
               "while scanning a directive",
               startMark,
               "expected alphabetic or numeric character, but found ${StringsKt.concatToString(Character.INSTANCE.toChars$snakeyaml_engine_kmp(c))}($c)",
               this.reader.getMark(),
               null,
               16,
               null
            );
         } else {
            return value;
         }
      }
   }

   private fun scanYamlDirectiveValue(startMark: Mark?): YamlDirective {
      while (this.reader.peek() == 32) {
         StreamReader.forward$default(this.reader, 0, 1, null);
      }

      val major: Int = this.scanYamlDirectiveNumber(startMark);
      var c: Int = this.reader.peek();
      if (c != 46) {
         throw new ScannerException(
            "while scanning a directive",
            startMark,
            "expected a digit or '.', but found ${StringsKt.concatToString(Character.INSTANCE.toChars$snakeyaml_engine_kmp(c))}($c)",
            this.reader.getMark(),
            null,
            16,
            null
         );
      } else {
         StreamReader.forward$default(this.reader, 0, 1, null);
         val minor: Int = this.scanYamlDirectiveNumber(startMark);
         c = this.reader.peek();
         if (CharConstants.NULL_BL_LINEBR.hasNo(c)) {
            throw new ScannerException(
               "while scanning a directive",
               startMark,
               "expected a digit or ' ', but found ${StringsKt.concatToString(Character.INSTANCE.toChars$snakeyaml_engine_kmp(c))}($c)",
               this.reader.getMark(),
               null,
               16,
               null
            );
         } else {
            return new DirectiveToken.YamlDirective(major, minor);
         }
      }
   }

   private fun scanYamlDirectiveNumber(startMark: Mark?): Int {
      val c: Int = this.reader.peek();
      if (!java.lang.Character.isDigit((char)c)) {
         throw new ScannerException(
            "while scanning a directive",
            startMark,
            "expected a digit, but found ${StringsKt.concatToString(Character.INSTANCE.toChars$snakeyaml_engine_kmp(c))}${40}$c${41}",
            this.reader.getMark(),
            null,
            16,
            null
         );
      } else {
         var length: Int = 0;

         while (java.lang.Character.isDigit((char)this.reader.peek(length))) {
            length++;
         }

         val number: java.lang.String = this.reader.prefixForward(length);
         if (length > 3) {
            throw new ScannerException(
               "while scanning a YAML directive",
               startMark,
               "found a number which cannot represent a valid version: $number",
               this.reader.getMark(),
               null,
               16,
               null
            );
         } else {
            return Integer.parseInt(number);
         }
      }
   }

   private fun scanTagDirectiveValue(startMark: Mark?): TagDirective {
      while (this.reader.peek() == 32) {
         StreamReader.forward$default(this.reader, 0, 1, null);
      }

      while (this.reader.peek() == 32) {
         StreamReader.forward$default(this.reader, 0, 1, null);
      }

      return new DirectiveToken.TagDirective(this.scanTagDirectiveHandle(startMark), this.scanTagDirectivePrefix(startMark));
   }

   private fun scanTagDirectiveHandle(startMark: Mark?): String {
      val value: java.lang.String = this.scanTagHandle("directive", startMark);
      val c: Int = this.reader.peek();
      if (c != 32) {
         throw new ScannerException(
            "while scanning a directive",
            startMark,
            "expected ' ', but found ${StringsKt.concatToString(Character.INSTANCE.toChars$snakeyaml_engine_kmp(c))}($c)",
            this.reader.getMark(),
            null,
            16,
            null
         );
      } else {
         return value;
      }
   }

   private fun scanTagDirectivePrefix(startMark: Mark?): String {
      val value: java.lang.String = this.scanTagUri("directive", CharConstants.URI_CHARS_FOR_TAG_PREFIX, startMark);
      val c: Int = this.reader.peek();
      if (CharConstants.NULL_BL_LINEBR.hasNo(c)) {
         throw new ScannerException(
            "while scanning a directive",
            startMark,
            "expected ' ', but found ${StringsKt.concatToString(Character.INSTANCE.toChars$snakeyaml_engine_kmp(c))}($c)",
            this.reader.getMark(),
            null,
            16,
            null
         );
      } else {
         return value;
      }
   }

   private fun scanDirectiveIgnoredLine(startMark: Mark?): CommentToken? {
      while (this.reader.peek() == 32) {
         StreamReader.forward$default(this.reader, 0, 1, null);
      }

      var commentToken: CommentToken = null;
      if (this.reader.peek() == 35) {
         val c: CommentToken = this.scanComment(CommentType.IN_LINE);
         if (this.settings.getParseComments()) {
            commentToken = c;
         }
      }

      val var5: Int = this.reader.peek();
      if (this.scanLineBreak() == null && var5 != 0) {
         throw new ScannerException(
            "while scanning a directive",
            startMark,
            "expected a comment or a line break, but found ${StringsKt.concatToString(Character.INSTANCE.toChars$snakeyaml_engine_kmp(var5))}($var5)",
            this.reader.getMark(),
            null,
            16,
            null
         );
      } else {
         return commentToken;
      }
   }

   private fun scanAnchor(isAnchor: Boolean): Token {
      val startMark: Mark = this.reader.getMark();
      val name: java.lang.String = if (this.reader.peek() == 42) "alias" else "anchor";
      StreamReader.forward$default(this.reader, 0, 1, null);
      var length: Int = 0;
      var c: Int = this.reader.peek(0);

      while (CharConstants.NULL_BL_T_LINEBR.hasNo(c, ",[]{}/.*&")) {
         c = this.reader.peek(++length);
      }

      if (length == 0) {
         throw new ScannerException(
            "while scanning an $name",
            startMark,
            "unexpected character found ${StringsKt.concatToString(Character.INSTANCE.toChars$snakeyaml_engine_kmp(c))}($c)",
            this.reader.getMark(),
            null,
            16,
            null
         );
      } else {
         val value: java.lang.String = this.reader.prefixForward(length);
         c = this.reader.peek();
         if (CharConstants.NULL_BL_T_LINEBR.hasNo(c, "?:,]}%@`")) {
            throw new ScannerException(
               "while scanning an $name",
               startMark,
               "unexpected character found ${StringsKt.concatToString(Character.INSTANCE.toChars$snakeyaml_engine_kmp(c))}($c)",
               this.reader.getMark(),
               null,
               16,
               null
            );
         } else {
            val endMark: Mark = this.reader.getMark();
            return if (isAnchor) new AnchorToken(new Anchor(value), startMark, endMark) else new AliasToken(new Anchor(value), startMark, endMark);
         }
      }
   }

   private fun scanTag(): Token {
      val startMark: Mark = this.reader.getMark();
      var c: Int = this.reader.peek(1);
      val var9: java.lang.String;
      val var10: java.lang.String;
      if (c == 60) {
         this.reader.forward(2);
         var10 = this.scanTagUri("tag", CharConstants.URI_CHARS_FOR_TAG_PREFIX, startMark);
         c = this.reader.peek();
         if (c != 62) {
            throw new ScannerException(
               "while scanning a tag",
               startMark,
               "expected '>', but found '${StringsKt.concatToString(Character.INSTANCE.toChars$snakeyaml_engine_kmp(c))}' ($c)",
               this.reader.getMark(),
               null,
               16,
               null
            );
         }

         var9 = null;
         StreamReader.forward$default(this.reader, 0, 1, null);
      } else if (CharConstants.NULL_BL_T_LINEBR.has(c)) {
         var10 = "!";
         var9 = null;
         StreamReader.forward$default(this.reader, 0, 1, null);
      } else {
         var var11: Int = 1;

         var endMark: Boolean;
         for (useHandle = false; CharConstants.NULL_BL_LINEBR.hasNo(c); c = this.reader.peek(++length)) {
            if (c == 33) {
               endMark = true;
               break;
            }
         }

         if (endMark) {
            var9 = this.scanTagHandle("tag", startMark);
         } else {
            var9 = "!";
            StreamReader.forward$default(this.reader, 0, 1, null);
         }

         var10 = this.scanTagUri("tag", CharConstants.URI_CHARS_FOR_TAG_SUFFIX, startMark);
      }

      c = this.reader.peek();
      if (CharConstants.NULL_BL_LINEBR.hasNo(c)) {
         throw new ScannerException(
            "while scanning a tag",
            startMark,
            "expected ' ', but found '${StringsKt.concatToString(Character.INSTANCE.toChars$snakeyaml_engine_kmp(c))}' ($c)",
            this.reader.getMark(),
            null,
            16,
            null
         );
      } else {
         return new TagToken(new TagTuple(var9, var10), startMark, this.reader.getMark());
      }
   }

   private fun scanBlockScalar(style: ScalarStyle): List<Token> {
      val stringBuilder: StringBuilder = new StringBuilder();
      val startMark: Mark = this.reader.getMark();
      StreamReader.forward$default(this.reader, 0, 1, null);
      val chomping: Chomping = this.scanBlockScalarIndicators(startMark);
      val commentToken: CommentToken = this.scanBlockScalarIgnoredLine(startMark);
      val minIndent: Int = RangesKt.coerceAtLeast(this.indent + 1, 1);
      val lineBreak: Int = chomping.getIncrement();
      var var15: java.lang.String;
      val var17: Int;
      var var18: Mark;
      if (lineBreak == null) {
         val scalarToken: BreakIntentHolder = this.scanBlockScalarIndentation();
         var15 = scalarToken.getBreaks();
         var18 = scalarToken.getEndMark();
         var17 = RangesKt.coerceAtLeast(minIndent, scalarToken.getMaxIndent());
      } else {
         var17 = minIndent + lineBreak - 1;
         val var20: BreakIntentHolder = this.scanBlockScalarBreaks(var17);
         var15 = var20.getBreaks();
         var18 = var20.getEndMark();
      }

      var var19: java.lang.String = null;

      while (this.reader.getColumn() == var17 && this.reader.peek() != 0) {
         stringBuilder.append(var15);
         val var21: Boolean = !StringsKt.contains$default(" \t", (char)this.reader.peek(), false, 2, null);
         var length: Int = 0;

         while (CharConstants.NULL_OR_LINEBR.hasNo(this.reader.peek(length))) {
            length++;
         }

         stringBuilder.append(this.reader.prefixForward(length));
         var19 = this.scanLineBreak();
         val brme: BreakIntentHolder = this.scanBlockScalarBreaks(var17);
         var15 = brme.getBreaks();
         var18 = brme.getEndMark();
         if (this.reader.getColumn() != var17 || this.reader.peek() == 0) {
            break;
         }

         if (style != ScalarStyle.FOLDED || !("\n" == var19) || !var21 || StringsKt.contains$default(" \t", (char)this.reader.peek(), false, 2, null)) {
            var var10001: java.lang.String = var19;
            if (var19 == null) {
               var10001 = "";
            }

            stringBuilder.append(var10001);
         } else if (var15.length() == 0) {
            stringBuilder.append(' ');
         }
      }

      if (chomping.getAddExistingFinalLineBreak()) {
         var var24: java.lang.String = var19;
         if (var19 == null) {
            var24 = "";
         }

         stringBuilder.append(var24);
      }

      if (chomping.getRetainTrailingEmptyLines()) {
         stringBuilder.append(var15);
      }

      val var10002: java.lang.String = stringBuilder.toString();
      return this.makeTokenList(commentToken, new ScalarToken(var10002, false, startMark, var18, style));
   }

   private fun scanBlockScalarIndicators(startMark: Mark?): Chomping {
      var c: Int = this.reader.peek();
      var var6: Int;
      val var7: Int;
      if (c == 45 || c == 43) {
         var6 = c;
         StreamReader.forward$default(this.reader, 0, 1, null);
         c = this.reader.peek();
         if (java.lang.Character.isDigit((char)c)) {
            val var11: Int = Integer.parseInt(StringsKt.concatToString(Character.INSTANCE.toChars$snakeyaml_engine_kmp(c)));
            if (var11 == 0) {
               throw new ScannerException(
                  "while scanning a block scalar",
                  startMark,
                  "expected indentation indicator in the range 1-9, but found 0",
                  this.reader.getMark(),
                  null,
                  16,
                  null
               );
            }

            var7 = var11;
            StreamReader.forward$default(this.reader, 0, 1, null);
         } else {
            var7 = null;
         }
      } else if (java.lang.Character.isDigit((char)c)) {
         val s: Int = Integer.parseInt(StringsKt.concatToString(Character.INSTANCE.toChars$snakeyaml_engine_kmp(c)));
         if (s == 0) {
            throw new ScannerException(
               "while scanning a block scalar",
               startMark,
               "expected indentation indicator in the range 1-9, but found 0",
               this.reader.getMark(),
               null,
               16,
               null
            );
         }

         var7 = s;
         StreamReader.forward$default(this.reader, 0, 1, null);
         c = this.reader.peek();
         switch (c) {
            case 43:
            case 45:
               var6 = c;
               StreamReader.forward$default(this.reader, 0, 1, null);
               break;
            case 44:
            default:
               var6 = null;
         }
      } else {
         var7 = null;
         var6 = null;
      }

      c = this.reader.peek();
      if (CharConstants.NULL_BL_LINEBR.hasNo(c)) {
         throw new ScannerException(
            "while scanning a block scalar",
            startMark,
            "expected chomping or indentation indicators, but found ${StringsKt.concatToString(Character.INSTANCE.toChars$snakeyaml_engine_kmp(c))}($c)",
            this.reader.getMark(),
            null,
            16,
            null
         );
      } else {
         val var10000: Chomping = ScannerImplKt.access$Chomping(var6, var7);
         if (var10000 == null) {
            throw new IllegalArgumentException("Unexpected block chomping indicator: $var6");
         } else {
            return var10000;
         }
      }
   }

   private fun scanBlockScalarIgnoredLine(startMark: Mark?): CommentToken? {
      while (this.reader.peek() == 32) {
         StreamReader.forward$default(this.reader, 0, 1, null);
      }

      val commentToken: CommentToken = if (this.reader.peek() == 35) this.scanComment(CommentType.IN_LINE) else null;
      val c: Int = this.reader.peek();
      if (this.scanLineBreak() == null && c != 0) {
         throw new ScannerException(
            "while scanning a block scalar",
            startMark,
            "expected a comment or a line break, but found ${StringsKt.concatToString(Character.INSTANCE.toChars$snakeyaml_engine_kmp(c))}($c)",
            this.reader.getMark(),
            null,
            16,
            null
         );
      } else {
         return commentToken;
      }
   }

   private fun scanBlockScalarIndentation(): BreakIntentHolder {
      val chunks: StringBuilder = new StringBuilder();
      var maxIndentOnEmptyLine: Int = 0;
      var endMark: Mark = this.reader.getMark();

      while (CharConstants.LINEBR.has(this.reader.peek(), " \r")) {
         if (this.reader.peek() != 32) {
            var var10001: java.lang.String = this.scanLineBreak();
            if (var10001 == null) {
               var10001 = "";
            }

            chunks.append(var10001);
            endMark = this.reader.getMark();
         } else {
            StreamReader.forward$default(this.reader, 0, 1, null);
            if (this.reader.getColumn() > maxIndentOnEmptyLine) {
               maxIndentOnEmptyLine = this.reader.getColumn();
            }
         }
      }

      val indent: Int = this.reader.getColumn();
      if (1 <= indent && indent < maxIndentOnEmptyLine) {
         throw new ScannerException(
            "while scanning a block scalar",
            endMark,
            " the leading empty lines contain more spaces ($maxIndentOnEmptyLine) than the first non-empty line ($indent).",
            this.reader.getMark(),
            null,
            16,
            null
         );
      } else {
         val var10002: java.lang.String = chunks.toString();
         return new BreakIntentHolder(var10002, indent, endMark);
      }
   }

   private fun scanBlockScalarBreaks(indent: Int): BreakIntentHolder {
      val chunks: StringBuilder = new StringBuilder();
      var endMark: Mark = this.reader.getMark();

      for (int col = this.reader.getColumn(); col < indent && this.reader.peek() == 32; col++) {
         StreamReader.forward$default(this.reader, 0, 1, null);
      }

      while (true) {
         val var10000: java.lang.String = this.scanLineBreak();
         if (var10000 == null) {
            val var10002: java.lang.String = chunks.toString();
            return new BreakIntentHolder(var10002, -1, endMark);
         }

         chunks.append(var10000);
         endMark = this.reader.getMark();

         for (int var6 = this.reader.getColumn(); var6 < indent && this.reader.peek() == 32; var6++) {
            StreamReader.forward$default(this.reader, 0, 1, null);
         }
      }
   }

   private fun scanFlowScalar(style: ScalarStyle): Token {
      val doubleValue: Boolean = style === ScalarStyle.DOUBLE_QUOTED;
      val startMark: Mark = this.reader.getMark();
      val quote: Int = this.reader.peek();
      StreamReader.forward$default(this.reader, 0, 1, null);
      val endMark: StringBuilder = new StringBuilder();
      val `$this$scanFlowScalar_u24lambda_u240`: StringBuilder = endMark;
      this.scanFlowScalarNonSpaces(doubleValue, startMark, endMark);

      while (this.reader.peek() != quote) {
         this.scanFlowScalarSpaces(startMark, `$this$scanFlowScalar_u24lambda_u240`);
         this.scanFlowScalarNonSpaces(doubleValue, startMark, `$this$scanFlowScalar_u24lambda_u240`);
      }

      val chunks: java.lang.String = endMark.toString();
      StreamReader.forward$default(this.reader, 0, 1, null);
      return new ScalarToken(chunks, false, startMark, this.reader.getMark(), style);
   }

   private fun scanFlowScalarNonSpaces(doubleQuoted: Boolean, startMark: Mark?, chunks: StringBuilder) {
      while (true) {
         var length: Int = 0;

         while (CharConstants.NULL_BL_T_LINEBR.hasNo(this.reader.peek(length), "'\"\\")) {
            length++;
         }

         if (length != 0) {
            chunks.append(this.reader.prefixForward(length));
         }

         var c: Int = this.reader.peek();
         if (!doubleQuoted && c == 39 && this.reader.peek(1) == 39) {
            chunks.append('\'');
            this.reader.forward(2);
         } else if ((!doubleQuoted || c != 39) && (doubleQuoted || !StringsKt.contains$default("\"\\", (char)c, false, 2, null))) {
            if (doubleQuoted && c == 92) {
               StreamReader.forward$default(this.reader, 0, 1, null);
               c = this.reader.peek();
               if (!Character.INSTANCE.isSupplementaryCodePoint$snakeyaml_engine_kmp(c) && CharConstants.ESCAPE_REPLACEMENTS.containsKey((char)c)) {
                  chunks.append(CharConstants.ESCAPE_REPLACEMENTS.get((char)c));
                  StreamReader.forward$default(this.reader, 0, 1, null);
                  continue;
               }

               if (!Character.INSTANCE.isSupplementaryCodePoint$snakeyaml_engine_kmp(c) && CharConstants.ESCAPE_CODES.containsKey((char)c)) {
                  val var10000: Any = CharConstants.ESCAPE_CODES.get((char)c);
                  length = (var10000 as java.lang.Number).intValue();
                  StreamReader.forward$default(this.reader, 0, 1, null);
                  val var11: java.lang.String = this.reader.prefix(length);
                  if (NOT_HEXA.containsMatchIn(var11)) {
                     throw new ScannerException(
                        "while scanning a double-quoted scalar",
                        startMark,
                        "expected escape sequence of $length hexadecimal numbers, but found: $var11",
                        this.reader.getMark(),
                        null,
                        16,
                        null
                     );
                  }

                  try {
                     chunks.appendCodePoint(Integer.parseInt(var11, CharsKt.checkRadix(16)));
                     this.reader.forward(length);
                     continue;
                  } catch (var8: IllegalArgumentException) {
                     throw new ScannerException(
                        "while scanning a double-quoted scalar", startMark, "found unknown escape character $var11", this.reader.getMark(), null, 16, null
                     );
                  }
               }

               if (c == 9) {
                  chunks.append('\t');
                  StreamReader.forward$default(this.reader, 0, 1, null);
                  continue;
               }

               if (this.scanLineBreak() != null) {
                  chunks.append(this.scanFlowScalarBreaks(startMark));
                  continue;
               }

               throw new ScannerException(
                  "while scanning a double-quoted scalar",
                  startMark,
                  "found unknown escape character ${StringsKt.concatToString(Character.INSTANCE.toChars$snakeyaml_engine_kmp(c))}($c)",
                  this.reader.getMark(),
                  null,
                  16,
                  null
               );
            }

            return;
         } else {
            chunks.appendCodePoint(c);
            StreamReader.forward$default(this.reader, 0, 1, null);
         }
      }
   }

   private fun scanFlowScalarSpaces(startMark: Mark?, chunks: StringBuilder) {
      var length: Int = 0;

      while (StringsKt.contains$default(" \t", (char)this.reader.peek(length), false, 2, null)) {
         length++;
      }

      val whitespaces: java.lang.String = this.reader.prefixForward(length);
      if (this.reader.peek() == 0) {
         throw new ScannerException("found unexpected end of stream", this.reader.getMark(), "while scanning a quoted scalar", startMark, null, 16, null);
      } else {
         val lineBreakOpt: java.lang.String = this.scanLineBreak();
         if (lineBreakOpt != null) {
            val breaks: java.lang.String = this.scanFlowScalarBreaks(startMark);
            if (!("\n" == lineBreakOpt)) {
               chunks.append(lineBreakOpt);
            } else if (breaks.length() == 0) {
               chunks.append(' ');
            }

            chunks.append(breaks);
         } else {
            chunks.append(whitespaces);
         }
      }
   }

   private fun scanFlowScalarBreaks(startMark: Mark?): String {
      val chunks: StringBuilder = new StringBuilder();

      while (true) {
         val prefix: java.lang.String = this.reader.prefix(3);
         if (("---" == prefix || "..." == prefix) && CharConstants.NULL_BL_T_LINEBR.has(this.reader.peek(3))) {
            throw new ScannerException(
               "while scanning a quoted scalar", startMark, "found unexpected document separator", this.reader.getMark(), null, 16, null
            );
         }

         while (StringsKt.contains$default(" \t", (char)this.reader.peek(), false, 2, null)) {
            StreamReader.forward$default(this.reader, 0, 1, null);
         }

         var var10000: java.lang.String = this.scanLineBreak();
         if (var10000 == null) {
            var10000 = chunks.toString();
            return var10000;
         }

         chunks.append(var10000);
      }
   }

   private fun scanPlain(): Token {
      val chunks: StringBuilder = new StringBuilder();
      val startMark: Mark = this.reader.getMark();
      var endMark: Mark = startMark;
      val plainIndent: Int = this.indent + 1;
      var spaces: java.lang.String = "";

      label55:
      while (true) {
         var length: Int = 0;
         if (this.reader.peek() == 35) {
            break;
         }

         while (true) {
            val var8: Int = this.reader.peek(length);
            if (CharConstants.NULL_BL_T_LINEBR.has(var8)
               || var8 == 58 && CharConstants.NULL_BL_T_LINEBR.has(this.reader.peek(length + 1), if (this.isFlowContext()) ",[]{}" else "")
               || this.isFlowContext() && StringsKt.contains$default(",[]{}", (char)var8, false, 2, null)) {
               if (length == 0) {
                  break label55;
               }

               this.allowSimpleKey = false;
               chunks.append(spaces);
               chunks.append(this.reader.prefixForward(length));
               endMark = this.reader.getMark();
               spaces = this.scanPlainSpaces();
               if (spaces.length() == 0 || this.reader.peek() == 35 || this.isBlockContext() && this.reader.getColumn() < plainIndent) {
                  break label55;
               }
               break;
            }

            length++;
         }
      }

      val var10002: java.lang.String = chunks.toString();
      return new ScalarToken(var10002, true, startMark, endMark, null, 16, null);
   }

   private fun atEndOfPlain(): Boolean {
      var wsLength: Int = 0;
      var wsColumn: Int = this.reader.getColumn();

      while (true) {
         var extra: Int = this.reader.peek(wsLength);
         if (extra == 0 || !CharConstants.NULL_BL_T_LINEBR.has(extra)) {
            if (this.reader.peek(wsLength) != 35 && this.reader.peek(wsLength + 1) != 0 && (!this.isBlockContext() || wsColumn >= this.indent)) {
               if (this.isBlockContext()) {
                  extra = 1;

                  while (true) {
                     val cx: Int = this.reader.peek(wsLength + extra);
                     if (cx == 0 || CharConstants.NULL_BL_T_LINEBR.has(cx)) {
                        break;
                     }

                     if (cx == 58 && CharConstants.NULL_BL_T_LINEBR.has(this.reader.peek(wsLength + extra + 1))) {
                        return true;
                     }

                     extra++;
                  }
               }

               return false;
            } else {
               return true;
            }
         }

         wsLength++;
         if (!CharConstants.LINEBR.has(extra) && (extra != 13 || this.reader.peek(wsLength + 1) != 10) && extra != 65279) {
            wsColumn++;
         } else {
            wsColumn = 0;
         }
      }
   }

   private fun scanPlainSpaces(): String {
      var length: Int = 0;

      while (StringsKt.contains$default(" \t", (char)this.reader.peek(length), false, 2, null)) {
         length++;
      }

      val whitespaces: java.lang.String = this.reader.prefixForward(length);
      var var10000: java.lang.String = this.scanLineBreak();
      if (var10000 == null) {
         return whitespaces;
      } else {
         this.allowSimpleKey = true;
         var prefix: java.lang.String = this.reader.prefix(3);
         if (!("---" == prefix) && (!("..." == prefix) || !CharConstants.NULL_BL_T_LINEBR.has(this.reader.peek(3)))) {
            if (this.settings.getParseComments() && this.atEndOfPlain()) {
               return "";
            } else {
               val breaks: StringBuilder = new StringBuilder();

               do {
                  while (StringsKt.contains$default(" \t", (char)this.reader.peek(), false, 2, null)) {
                     StreamReader.forward$default(this.reader, 0, 1, null);
                  }

                  val lbOpt: java.lang.String = this.scanLineBreak();
                  if (lbOpt == null) {
                     if (!("\n" == var10000)) {
                        var10000 = "$var10000$breaks";
                     } else if (breaks.length() == 0) {
                        var10000 = " ";
                     } else {
                        var10000 = breaks.toString();
                     }

                     return var10000;
                  }

                  breaks.append(lbOpt);
                  prefix = this.reader.prefix(3);
               } while (!("---" == prefix) && (!("..." == prefix) || !CharConstants.NULL_BL_T_LINEBR.has(this.reader.peek(3))));

               return "";
            }
         } else {
            return "";
         }
      }
   }

   private fun scanTagHandle(name: String, startMark: Mark?): String {
      var c: Int = this.reader.peek();
      if (c != 33) {
         throw new ScannerException(
            "while scanning a $name",
            startMark,
            "expected '!', but found ${StringsKt.concatToString(Character.INSTANCE.toChars$snakeyaml_engine_kmp(c))}($c)",
            this.reader.getMark(),
            null,
            16,
            null
         );
      } else {
         var length: Int = 1;
         c = this.reader.peek(1);
         if (c != 32) {
            while (CharConstants.ALPHA.has(c)) {
               c = this.reader.peek(++length);
            }

            if (c != 33) {
               this.reader.forward(length);
               throw new ScannerException(
                  "while scanning a $name",
                  startMark,
                  "expected '!', but found ${StringsKt.concatToString(Character.INSTANCE.toChars$snakeyaml_engine_kmp(c))}($c)",
                  this.reader.getMark(),
                  null,
                  16,
                  null
               );
            }

            length++;
         }

         return this.reader.prefixForward(length);
      }
   }

   private fun scanTagUri(name: String, range: CharConstants, startMark: Mark?): String {
      val chunks: StringBuilder = new StringBuilder();
      var length: Int = 0;

      var c: Int;
      for (c = this.reader.peek(0); range.has(c); c = this.reader.peek(length)) {
         if (c == 37) {
            chunks.append(this.reader.prefixForward(length));
            length = 0;
            val var10000: Serializable = chunks.append(this.scanUriEscapes(name, startMark));
         } else {
            val var9: Serializable = length++;
         }
      }

      if (length != 0) {
         chunks.append(this.reader.prefixForward(length));
      }

      if (chunks.length() == 0) {
         throw new ScannerException(
            "while scanning a $name",
            startMark,
            "expected URI, but found ${StringsKt.concatToString(Character.INSTANCE.toChars$snakeyaml_engine_kmp(c))}($c)",
            this.reader.getMark(),
            null,
            16,
            null
         );
      } else {
         val var10: java.lang.String = chunks.toString();
         return var10;
      }
   }

   private fun scanUriEscapes(name: String, startMark: Mark?): String {
      var length: Int = 1;

      while (this.reader.peek(length * 3) == 37) {
         length++;
      }

      val buff: Buffer;
      for (buff = new Buffer(); this.reader.peek() == 37; this.reader.forward(2)) {
         StreamReader.forward$default(this.reader, 0, 1, null);

         try {
            buff.writeByte(Integer.parseInt(this.reader.prefix(2), CharsKt.checkRadix(16)));
         } catch (var13: NumberFormatException) {
            val c1: Int = this.reader.peek();
            val s1: java.lang.String = StringsKt.concatToString(Character.INSTANCE.toChars$snakeyaml_engine_kmp(c1));
            val c2: Int = this.reader.peek(1);
            throw new ScannerException(
               "while scanning a $name",
               startMark,
               "expected URI escape sequence of 2 hexadecimal numbers, but found $s1($c1) and ${StringsKt.concatToString(
                  Character.INSTANCE.toChars$snakeyaml_engine_kmp(c2)
               )}($c2)",
               this.reader.getMark(),
               null,
               16,
               null
            );
         }
      }

      buff.flush();

      try {
         return UriEncoder.decode(buff);
      } catch (var12: CharacterCodingException) {
         throw new ScannerException("while scanning a $name", startMark, "expected URI in UTF-8: ${var12.getMessage()}", this.reader.getMark(), null, 16, null);
      }
   }

   private fun scanLineBreak(): String? {
      val c: Int = this.reader.peek();
      switch (c) {
         case 10:
         case 13:
         case 133:
            if (c == 13 && 10 == this.reader.peek(1)) {
               this.reader.forward(2);
            } else {
               StreamReader.forward$default(this.reader, 0, 1, null);
            }

            return "\n";
         default:
            return null;
      }
   }

   private fun makeTokenList(vararg tokens: Token?): List<Token> {
      val notNullTokens: java.util.List = ArraysKt.filterNotNull(tokens);
      val var10000: java.util.List;
      if (!this.settings.getParseComments()) {
         val `$this$filter$iv`: java.lang.Iterable = notNullTokens;
         val `destination$iv$iv`: java.util.Collection = new ArrayList();

         for (Object element$iv$iv : $this$filter$iv) {
            if ((`element$iv$iv` as Token) !is CommentToken) {
               `destination$iv$iv`.add(`element$iv$iv`);
            }
         }

         var10000 = `destination$iv$iv` as java.util.List;
      } else {
         var10000 = notNullTokens;
      }

      return var10000;
   }

   override fun remove() {
      throw new UnsupportedOperationException("Operation is not supported for read-only collection");
   }

   public companion object {
      private const val DIRECTIVE_PREFIX: String
      private const val EXPECTED_ALPHA_ERROR_PREFIX: String
      private const val SCANNING_SCALAR: String
      private const val SCANNING_PREFIX: String
      private final val NOT_HEXA: Regex
   }
}
