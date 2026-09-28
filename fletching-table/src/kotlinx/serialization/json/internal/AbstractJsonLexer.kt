package kotlinx.serialization.json.internal

import java.util.ArrayList
import kotlin.jvm.internal.InlineMarker
import kotlin.jvm.internal.SourceDebugExtension

@SourceDebugExtension(["SMAP\nAbstractJsonLexer.kt\nKotlin\n*S Kotlin\n*F\n+ 1 AbstractJsonLexer.kt\nkotlinx/serialization/json/internal/AbstractJsonLexer\n+ 2 AbstractJsonLexer.kt\nkotlinx/serialization/json/internal/AbstractJsonLexer$fail$1\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,763:1\n226#1,10:764\n755#1,5:775\n226#1,10:780\n226#1,10:792\n229#2:774\n229#2:790\n1#3:791\n*S KotlinDebug\n*F\n+ 1 AbstractJsonLexer.kt\nkotlinx/serialization/json/internal/AbstractJsonLexer\n*L\n206#1:764,10\n216#1:775,5\n223#1:780,10\n685#1:792,10\n206#1:774\n223#1:790\n*E\n"])
internal abstract class AbstractJsonLexer {
   protected abstract val source: CharSequence

   internal final var currentPosition: Int
      private set

   public final val path: JsonPath = new JsonPath()
   private final var peekedString: String?

   protected final var escapedString: StringBuilder = new StringBuilder()
      internal set

   protected inline fun Char.isWs(): Boolean {
      return `$this$isWs` == ' ' || `$this$isWs` == '\n' || `$this$isWs` == '\r' || `$this$isWs` == '\t';
   }

   public open fun ensureHaveChars() {
   }

   public fun isNotEof(): Boolean {
      return this.peekNextToken() != 10;
   }

   public abstract fun prefetchOrEof(position: Int): Int {
   }

   public abstract fun canConsumeValue(): Boolean {
   }

   public abstract fun consumeNextToken(): Byte {
   }

   public fun tryConsumeComma(): Boolean {
      val current: Int = this.skipWhitespaces();
      val source: java.lang.CharSequence = this.getSource();
      if (current < source.length() && current != -1) {
         if (source.charAt(current) == ',') {
            this.currentPosition++;
            return true;
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   protected fun isValidValueStart(c: Char): Boolean {
      var var10000: Boolean;
      switch (c) {
         case ',':
         case ':':
         case ']':
         case '}':
            var10000 = false;
            break;
         default:
            var10000 = true;
      }

      return var10000;
   }

   public fun expectEof() {
      if (this.consumeNextToken() != 10) {
         fail$default(this, "Expected EOF after parsing, but had ${this.getSource().charAt(this.currentPosition - 1)} instead", 0, null, 6, null);
         throw new KotlinNothingValueException();
      }
   }

   public fun consumeNextToken(expected: Byte): Byte {
      val token: Byte = this.consumeNextToken();
      if (token == expected) {
         return token;
      } else {
         val `expected$iv`: java.lang.String = AbstractJsonLexerKt.tokenDescription(expected);
         val `position$iv`: Int = this.currentPosition - 1;
         fail$default(
            this,
            "Expected $`expected$iv`, but had '${if (this.currentPosition != access$getSource(this).length() && `position$iv` >= 0)
               java.lang.String.valueOf(access$getSource(this).charAt(`position$iv`))
               else
               "EOF"}' instead",
            `position$iv`,
            null,
            4,
            null
         );
         throw new KotlinNothingValueException();
      }
   }

   public abstract fun consumeNextToken(expected: Char) {
   }

   protected fun unexpectedToken(expected: Char) {
      // $VF: Couldn't be decompiled
      // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
      // java.lang.ClassCastException: class org.jetbrains.java.decompiler.modules.decompiler.exps.AssignmentExprent cannot be cast to class org.jetbrains.java.decompiler.modules.decompiler.exps.IfExprent (org.jetbrains.java.decompiler.modules.decompiler.exps.AssignmentExprent and org.jetbrains.java.decompiler.modules.decompiler.exps.IfExprent are in unnamed module of loader 'app')
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.IfStatement.initExprents(IfStatement.java:276)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.initStatementExprents(ExprProcessor.java:189)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.initStatementExprents(ExprProcessor.java:192)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.initStatementExprents(ExprProcessor.java:192)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.initStatementExprents(ExprProcessor.java:192)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.initStatementExprents(ExprProcessor.java:192)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.initStatementExprents(ExprProcessor.java:192)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.processStatement(ExprProcessor.java:148)
      //
      // Bytecode:
      // 00: aload 0
      // 01: getfield kotlinx/serialization/json/internal/AbstractJsonLexer.currentPosition I
      // 04: ifle 67
      // 07: iload 1
      // 08: bipush 34
      // 0a: if_icmpne 67
      // 0d: aload 0
      // 0e: astore 3
      // 0f: bipush 0
      // 10: istore 4
      // 12: aload 3
      // 13: getfield kotlinx/serialization/json/internal/AbstractJsonLexer.currentPosition I
      // 16: istore 5
      // 18: nop
      // 19: bipush 0
      // 1a: istore 6
      // 1c: aload 0
      // 1d: getfield kotlinx/serialization/json/internal/AbstractJsonLexer.currentPosition I
      // 20: istore 7
      // 22: aload 0
      // 23: iload 7
      // 25: bipush -1
      // 26: iadd
      // 27: putfield kotlinx/serialization/json/internal/AbstractJsonLexer.currentPosition I
      // 2a: aload 0
      // 2b: invokevirtual kotlinx/serialization/json/internal/AbstractJsonLexer.consumeStringLenient ()Ljava/lang/String;
      // 2e: astore 6
      // 30: aload 3
      // 31: iload 5
      // 33: putfield kotlinx/serialization/json/internal/AbstractJsonLexer.currentPosition I
      // 36: aload 6
      // 38: goto 46
      // 3b: astore 7
      // 3d: aload 3
      // 3e: iload 5
      // 40: putfield kotlinx/serialization/json/internal/AbstractJsonLexer.currentPosition I
      // 43: aload 7
      // 45: athrow
      // 46: astore 2
      // 47: aload 2
      // 48: ldc "null"
      // 4a: invokestatic kotlin/jvm/internal/Intrinsics.areEqual (Ljava/lang/Object;Ljava/lang/Object;)Z
      // 4d: ifeq 67
      // 50: aload 0
      // 51: ldc "Expected string literal but 'null' literal was found"
      // 53: aload 0
      // 54: getfield kotlinx/serialization/json/internal/AbstractJsonLexer.currentPosition I
      // 57: bipush 1
      // 58: isub
      // 59: ldc "Use 'coerceInputValues = true' in 'Json {}' builder to coerce nulls if property has a default value."
      // 5b: invokevirtual kotlinx/serialization/json/internal/AbstractJsonLexer.fail (Ljava/lang/String;ILjava/lang/String;)Ljava/lang/Void;
      // 5e: pop
      // 5f: new kotlin/KotlinNothingValueException
      // 62: dup
      // 63: invokespecial kotlin/KotlinNothingValueException.<init> ()V
      // 66: athrow
      // 67: aload 0
      // 68: astore 2
      // 69: iload 1
      // 6a: invokestatic kotlinx/serialization/json/internal/AbstractJsonLexerKt.charToTokenClass (C)B
      // 6d: istore 3
      // 6e: bipush 1
      // 6f: istore 4
      // 71: bipush 0
      // 72: istore 6
      // 74: iload 3
      // 75: invokestatic kotlinx/serialization/json/internal/AbstractJsonLexerKt.tokenDescription (B)Ljava/lang/String;
      // 78: astore 7
      // 7a: aload 2
      // 7b: getfield kotlinx/serialization/json/internal/AbstractJsonLexer.currentPosition I
      // 7e: bipush 1
      // 7f: isub
      // 80: istore 8
      // 82: aload 2
      // 83: getfield kotlinx/serialization/json/internal/AbstractJsonLexer.currentPosition I
      // 86: aload 2
      // 87: invokestatic kotlinx/serialization/json/internal/AbstractJsonLexer.access$getSource (Lkotlinx/serialization/json/internal/AbstractJsonLexer;)Ljava/lang/CharSequence;
      // 8a: invokeinterface java/lang/CharSequence.length ()I 1
      // 8f: if_icmpeq 97
      // 92: iload 8
      // 94: ifge 9c
      // 97: ldc "EOF"
      // 99: goto aa
      // 9c: aload 2
      // 9d: invokestatic kotlinx/serialization/json/internal/AbstractJsonLexer.access$getSource (Lkotlinx/serialization/json/internal/AbstractJsonLexer;)Ljava/lang/CharSequence;
      // a0: iload 8
      // a2: invokeinterface java/lang/CharSequence.charAt (I)C 2
      // a7: invokestatic java/lang/String.valueOf (C)Ljava/lang/String;
      // aa: astore 9
      // ac: aload 2
      // ad: aload 7
      // af: aload 9
      // b1: astore 10
      // b3: astore 11
      // b5: astore 13
      // b7: bipush 0
      // b8: istore 12
      // ba: aload 11
      // bc: aload 10
      // be: astore 10
      // c0: astore 11
      // c2: new java/lang/StringBuilder
      // c5: dup
      // c6: invokespecial java/lang/StringBuilder.<init> ()V
      // c9: ldc "Expected "
      // cb: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // ce: aload 11
      // d0: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // d3: ldc ", but had '"
      // d5: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // d8: aload 10
      // da: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // dd: ldc "' instead"
      // df: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // e2: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // e5: aload 13
      // e7: swap
      // e8: iload 8
      // ea: aconst_null
      // eb: bipush 4
      // ec: aconst_null
      // ed: invokestatic kotlinx/serialization/json/internal/AbstractJsonLexer.fail$default (Lkotlinx/serialization/json/internal/AbstractJsonLexer;Ljava/lang/String;ILjava/lang/String;ILjava/lang/Object;)Ljava/lang/Void;
      // f0: pop
      // f1: new kotlin/KotlinNothingValueException
      // f4: dup
      // f5: invokespecial kotlin/KotlinNothingValueException.<init> ()V
      // f8: athrow
   }

   internal inline fun fail(expectedToken: Byte, wasConsumed: Boolean = ..., message: (String, String) -> String = ...): Nothing {
      val expected: java.lang.String = AbstractJsonLexerKt.tokenDescription(expectedToken);
      val position: Int = if (wasConsumed) this.currentPosition - 1 else this.currentPosition;
      fail$default(
         this,
         message.invoke(
            expected,
            if (this.currentPosition != access$getSource(this).length() && position >= 0)
               java.lang.String.valueOf(access$getSource(this).charAt(position))
               else
               "EOF"
         ) as java.lang.String,
         position,
         null,
         4,
         null
      );
      throw new KotlinNothingValueException();
   }

   public open fun peekNextToken(): Byte {
      val source: java.lang.CharSequence = this.getSource();
      var var4: Int = this.currentPosition;

      while (true) {
         var4 = this.prefetchOrEof(var4);
         if (var4 == -1) {
            this.currentPosition = var4;
            return 10;
         }

         val ch: Char = source.charAt(var4);
         switch (ch) {
            case '\t':
            case '\n':
            case '\r':
            case ' ':
               var4++;
               break;
            default:
               this.currentPosition = var4;
               return AbstractJsonLexerKt.charToTokenClass(ch);
         }
      }
   }

   public fun tryConsumeNull(doConsume: Boolean = true): Boolean {
      val var5: Int = this.prefetchOrEof(this.skipWhitespaces());
      val len: Int = this.getSource().length() - var5;
      if (len >= 4 && var5 != -1) {
         for (int i = 0; i < 4; i++) {
            if ("null".charAt(i) != this.getSource().charAt(var5 + i)) {
               return false;
            }
         }

         if (len > 4 && AbstractJsonLexerKt.charToTokenClass(this.getSource().charAt(var5 + 4)) == 0) {
            return false;
         } else {
            if (doConsume) {
               this.currentPosition = var5 + 4;
            }

            return true;
         }
      } else {
         return false;
      }
   }

   public abstract fun skipWhitespaces(): Int {
   }

   public abstract fun peekLeadingMatchingValue(keyToMatch: String, isLenient: Boolean): String? {
   }

   public fun peekString(isLenient: Boolean): String? {
      val token: Byte = this.peekNextToken();
      val var10000: java.lang.String;
      if (isLenient) {
         if (token != 1 && token != 0) {
            return null;
         }

         var10000 = this.consumeStringLenient();
      } else {
         if (token != 1) {
            return null;
         }

         var10000 = this.consumeString();
      }

      this.peekedString = var10000;
      return var10000;
   }

   public fun discardPeeked() {
      this.peekedString = null;
   }

   public open fun indexOf(char: Char, startPos: Int): Int {
      return StringsKt.indexOf$default(this.getSource(), var1, startPos, false, 4, null);
   }

   public open fun substring(startPos: Int, endPos: Int): String {
      return this.getSource().subSequence(startPos, endPos).toString();
   }

   public abstract fun consumeKeyString(): String {
   }

   private fun insideString(isLenient: Boolean, char: Char): Boolean {
      return if (isLenient) AbstractJsonLexerKt.charToTokenClass(var2) == 0 else var2 != '"';
   }

   public open fun consumeStringChunked(isLenient: Boolean, consumeChunk: (String) -> Unit) {
      if (!isLenient || this.peekNextToken() == 0) {
         if (!isLenient) {
            this.consumeNextToken('"');
         }

         var currentPosition: Int = this.currentPosition;
         var lastPosition: Int = this.currentPosition;
         var var6: Char = this.getSource().charAt(currentPosition);

         var usedAppend: Boolean;
         for (usedAppend = false; this.insideString(isLenient, char); char = this.getSource().charAt(currentPosition)) {
            if (!isLenient && var6 == '\\') {
               usedAppend = true;
               currentPosition = this.prefetchOrEof(this.appendEscape(lastPosition, currentPosition));
               lastPosition = currentPosition;
            } else {
               currentPosition++;
            }

            if (currentPosition >= this.getSource().length()) {
               this.writeRange(lastPosition, currentPosition, usedAppend, consumeChunk);
               usedAppend = false;
               currentPosition = this.prefetchOrEof(currentPosition);
               if (currentPosition == -1) {
                  fail$default(this, "EOF", currentPosition, null, 4, null);
                  throw new KotlinNothingValueException();
               }

               lastPosition = currentPosition;
            }
         }

         this.writeRange(lastPosition, currentPosition, usedAppend, consumeChunk);
         this.currentPosition = currentPosition;
         if (!isLenient) {
            this.consumeNextToken('"');
         }
      }
   }

   private fun writeRange(fromIndex: Int, toIndex: Int, currentChunkHasEscape: Boolean, consumeChunk: (String) -> Unit) {
      if (currentChunkHasEscape) {
         consumeChunk.invoke(this.decodedString(fromIndex, toIndex));
      } else {
         consumeChunk.invoke(this.substring(fromIndex, toIndex));
      }
   }

   public fun consumeString(): String {
      return if (this.peekedString != null) this.takePeeked() else this.consumeKeyString();
   }

   protected fun consumeString(source: CharSequence, startPosition: Int, current: Int): String {
      var currentPosition: Int = current;
      var lastPosition: Int = startPosition;
      var var6: Char = source.charAt(current);

      var usedAppend: Boolean;
      for (usedAppend = false; char != '"'; char = source.charAt(currentPosition)) {
         if (var6 == '\\') {
            usedAppend = true;
            currentPosition = this.prefetchOrEof(this.appendEscape(lastPosition, currentPosition));
            if (currentPosition == -1) {
               fail$default(this, "Unexpected EOF", currentPosition, null, 4, null);
               throw new KotlinNothingValueException();
            }

            lastPosition = currentPosition;
         } else if (++currentPosition >= source.length()) {
            usedAppend = true;
            this.appendRange(lastPosition, currentPosition);
            currentPosition = this.prefetchOrEof(currentPosition);
            if (currentPosition == -1) {
               fail$default(this, "Unexpected EOF", currentPosition, null, 4, null);
               throw new KotlinNothingValueException();
            }

            lastPosition = currentPosition;
         }
      }

      val string: java.lang.String = if (!usedAppend) this.substring(lastPosition, currentPosition) else this.decodedString(lastPosition, currentPosition);
      this.currentPosition = currentPosition + 1;
      return string;
   }

   private fun appendEscape(lastPosition: Int, current: Int): Int {
      this.appendRange(lastPosition, current);
      return this.appendEsc(current + 1);
   }

   private fun decodedString(lastPosition: Int, currentPosition: Int): String {
      this.appendRange(lastPosition, currentPosition);
      val var10000: java.lang.String = this.escapedString.toString();
      this.escapedString.setLength(0);
      return var10000;
   }

   private fun takePeeked(): String {
      val var10000: java.lang.String = this.peekedString;
      this.peekedString = null;
      return var10000;
   }

   public fun consumeStringLenientNotNull(): String {
      val result: java.lang.String = this.consumeStringLenient();
      if (result == "null" && this.wasUnquotedString()) {
         fail$default(this, "Unexpected 'null' value instead of string literal", 0, null, 6, null);
         throw new KotlinNothingValueException();
      } else {
         return result;
      }
   }

   private fun wasUnquotedString(): Boolean {
      return this.getSource().charAt(this.currentPosition - 1) != '"';
   }

   public fun consumeStringLenient(): String {
      if (this.peekedString != null) {
         return this.takePeeked();
      } else {
         var current: Int = this.skipWhitespaces();
         if (current < this.getSource().length() && current != -1) {
            val token: Byte = AbstractJsonLexerKt.charToTokenClass(this.getSource().charAt(current));
            if (token == 1) {
               return this.consumeString();
            } else if (token != 0) {
               fail$default(this, "Expected beginning of the string, but got ${this.getSource().charAt(current)}", 0, null, 6, null);
               throw new KotlinNothingValueException();
            } else {
               var usedAppend: Boolean = false;

               while (AbstractJsonLexerKt.charToTokenClass(this.getSource().charAt(current)) == 0) {
                  if (++current >= this.getSource().length()) {
                     usedAppend = true;
                     this.appendRange(this.currentPosition, current);
                     val result: Int = this.prefetchOrEof(current);
                     if (result == -1) {
                        this.currentPosition = current;
                        return this.decodedString(0, 0);
                     }

                     current = result;
                  }
               }

               val var5: java.lang.String = if (!usedAppend)
                  this.substring(this.currentPosition, current)
                  else
                  this.decodedString(this.currentPosition, current);
               this.currentPosition = current;
               return var5;
            }
         } else {
            fail$default(this, "EOF", current, null, 4, null);
            throw new KotlinNothingValueException();
         }
      }
   }

   protected open fun appendRange(fromIndex: Int, toIndex: Int) {
      this.escapedString.append(this.getSource(), fromIndex, toIndex);
   }

   private fun appendEsc(startPosition: Int): Int {
      var currentPosition: Int = this.prefetchOrEof(startPosition);
      if (currentPosition == -1) {
         fail$default(this, "Expected escape sequence to continue, got EOF", 0, null, 6, null);
         throw new KotlinNothingValueException();
      } else {
         val currentChar: Char = this.getSource().charAt(currentPosition++);
         if (currentChar == 'u') {
            return this.appendHex(this.getSource(), currentPosition);
         } else {
            val c: Char = AbstractJsonLexerKt.escapeToChar(currentChar);
            if (c == 0) {
               fail$default(this, "Invalid escaped char '$currentChar'", 0, null, 6, null);
               throw new KotlinNothingValueException();
            } else {
               this.escapedString.append(c);
               return currentPosition;
            }
         }
      }
   }

   private fun appendHex(source: CharSequence, startPos: Int): Int {
      if (startPos + 4 >= source.length()) {
         this.currentPosition = startPos;
         this.ensureHaveChars();
         if (this.currentPosition + 4 >= source.length()) {
            fail$default(this, "Unexpected EOF during unicode escape", 0, null, 6, null);
            throw new KotlinNothingValueException();
         } else {
            return this.appendHex(source, this.currentPosition);
         }
      } else {
         this.escapedString
            .append(
               (char)(
                  (this.fromHexChar(source, startPos) shl 12)
                     + (this.fromHexChar(source, startPos + 1) shl 8)
                     + (this.fromHexChar(source, startPos + 2) shl 4)
                     + this.fromHexChar(source, startPos + 3)
               )
            );
         return startPos + 4;
      }
   }

   internal inline fun require(condition: Boolean, position: Int = ..., message: () -> String) {
      if (!condition) {
         fail$default(this, message.invoke() as java.lang.String, position, null, 4, null);
         throw new KotlinNothingValueException();
      }
   }

   private fun fromHexChar(source: CharSequence, currentPosition: Int): Int {
      val character: Char = source.charAt(currentPosition);
      val var10000: Int;
      if ('0' <= character && character < ':') {
         var10000 = character - '0';
      } else if ('a' <= character && character < 'g') {
         var10000 = character - 'a' + 10;
      } else {
         if ('A' > character || character >= 'G') {
            fail$default(this, "Invalid toHexChar char '$character' in unicode escape", 0, null, 6, null);
            throw new KotlinNothingValueException();
         }

         var10000 = character - 'A' + 10;
      }

      return var10000;
   }

   public fun skipElement(allowLenientStrings: Boolean) {
      val tokenStack: java.util.List = new ArrayList();
      var lastToken: Byte = this.peekNextToken();
      if (lastToken != 8 && lastToken != 6) {
         this.consumeStringLenient();
      } else {
         while (true) {
            lastToken = this.peekNextToken();
            if (lastToken == 1) {
               if (allowLenientStrings) {
                  this.consumeStringLenient();
               } else {
                  this.consumeKeyString();
               }
            } else {
               if (lastToken == 8 || lastToken == 6) {
                  tokenStack.add(lastToken);
               } else if (lastToken == 9) {
                  if (CollectionsKt.<java.lang.Number>last(tokenStack).byteValue() != 8) {
                     throw JsonExceptionsKt.JsonDecodingException(this.currentPosition, "found ] instead of } at path: ${this.path}", this.getSource());
                  }

                  CollectionsKt.removeLast(tokenStack);
               } else if (lastToken == 7) {
                  if (CollectionsKt.<java.lang.Number>last(tokenStack).byteValue() != 6) {
                     throw JsonExceptionsKt.JsonDecodingException(this.currentPosition, "found } instead of ] at path: ${this.path}", this.getSource());
                  }

                  CollectionsKt.removeLast(tokenStack);
               } else if (lastToken == 10) {
                  fail$default(this, "Unexpected end of input due to malformed JSON during ignoring unknown keys", 0, null, 6, null);
                  throw new KotlinNothingValueException();
               }

               this.consumeNextToken();
               if (tokenStack.size() == 0) {
                  return;
               }
            }
         }
      }
   }

   public override fun toString(): String {
      return "JsonReader(source='${this.getSource()}', currentPosition=${this.currentPosition})";
   }

   public fun failOnUnknownKey(key: String) {
      val lastIndexOf: Int = StringsKt.lastIndexOf$default(this.substring(0, this.currentPosition), key, 0, false, 6, null);
      throw new JsonDecodingException(
         "Encountered an unknown key '$key' at offset $lastIndexOf at path: ${this.path.getPath()}\nUse 'ignoreUnknownKeys = true' in 'Json {}' builder or '@JsonIgnoreUnknownKeys' annotation to ignore unknown keys.\nJSON input: ${JsonExceptionsKt.minify(
            this.getSource(), lastIndexOf
         )}"
      );
   }

   public fun fail(message: String, position: Int = this.currentPosition, hint: String = ""): Nothing {
      throw JsonExceptionsKt.JsonDecodingException(position, "$message at path: ${this.path.getPath()}${if (hint.length() == 0) "" else "
$hint"}", this.getSource());
   }

   public fun consumeNumericLiteral(): Long {
      var var14: Int = this.prefetchOrEof(this.skipWhitespaces());
      if (var14 < this.getSource().length() && var14 != -1) {
         val var10000: Boolean;
         if (this.getSource().charAt(var14) == '"') {
            if (++var14 == this.getSource().length()) {
               fail$default(this, "EOF", 0, null, 6, null);
               throw new KotlinNothingValueException();
            }

            var10000 = true;
         } else {
            var10000 = false;
         }

         var accumulator: Long = 0L;
         var exponentAccumulator: Long = 0L;
         var isNegative: Boolean = false;
         var isExponentPositive: Boolean = false;
         var hasExponent: Boolean = false;
         val start: Int = var14;

         while (var14 != this.getSource().length()) {
            val hasChars: Char = this.getSource().charAt(var14);
            if ((hasChars == 'e' || hasChars == 'E') && !hasExponent) {
               if (var14 == start) {
                  fail$default(this, "Unexpected symbol $hasChars in numeric literal", 0, null, 6, null);
                  throw new KotlinNothingValueException();
               }

               isExponentPositive = true;
               hasExponent = true;
               var14++;
            } else if (hasChars == '-' && hasExponent) {
               if (var14 == start) {
                  fail$default(this, "Unexpected symbol '-' in numeric literal", 0, null, 6, null);
                  throw new KotlinNothingValueException();
               }

               isExponentPositive = false;
               var14++;
            } else if (hasChars == '+' && hasExponent) {
               if (var14 == start) {
                  fail$default(this, "Unexpected symbol '+' in numeric literal", 0, null, 6, null);
                  throw new KotlinNothingValueException();
               }

               isExponentPositive = true;
               var14++;
            } else if (hasChars == '-') {
               if (var14 != start) {
                  fail$default(this, "Unexpected symbol '-' in numeric literal", 0, null, 6, null);
                  throw new KotlinNothingValueException();
               }

               isNegative = true;
               var14++;
            } else {
               if (AbstractJsonLexerKt.charToTokenClass(hasChars) != 0) {
                  break;
               }

               var14++;
               val digit: Int = hasChars - '0';
               if (0 > hasChars - '0' || hasChars - '0' >= 10) {
                  fail$default(this, "Unexpected symbol '$hasChars' in numeric literal", 0, null, 6, null);
                  throw new KotlinNothingValueException();
               }

               if (hasExponent) {
                  exponentAccumulator = exponentAccumulator * 10 + digit;
               } else {
                  accumulator = accumulator * 10 - digit;
                  if (accumulator > 0L) {
                     fail$default(this, "Numeric value overflow", 0, null, 6, null);
                     throw new KotlinNothingValueException();
                  }
               }
            }
         }

         val var15: Boolean = var14 != start;
         if (start != var14 && (!isNegative || start != var14 - 1)) {
            if (var10000) {
               if (!var15) {
                  fail$default(this, "EOF", 0, null, 6, null);
                  throw new KotlinNothingValueException();
               }

               if (this.getSource().charAt(var14) != '"') {
                  fail$default(this, "Expected closing quotation mark", 0, null, 6, null);
                  throw new KotlinNothingValueException();
               }

               var14++;
            }

            this.currentPosition = var14;
            if (hasExponent) {
               val var16: Double = accumulator * consumeNumericLiteral$calculateExponent(exponentAccumulator, isExponentPositive);
               if (var16 > 9.223372E18F || var16 < -9.223372E18F) {
                  fail$default(this, "Numeric value overflow", 0, null, 6, null);
                  throw new KotlinNothingValueException();
               }

               if (Math.floor(var16) != var16) {
                  fail$default(this, "Can't convert $var16 to Long", 0, null, 6, null);
                  throw new KotlinNothingValueException();
               }

               accumulator = (long)var16;
            }

            val var17: Long;
            if (isNegative) {
               var17 = accumulator;
            } else {
               if (accumulator == java.lang.Long.MIN_VALUE) {
                  fail$default(this, "Numeric value overflow", 0, null, 6, null);
                  throw new KotlinNothingValueException();
               }

               var17 = -accumulator;
            }

            return var17;
         } else {
            fail$default(this, "Expected numeric literal", 0, null, 6, null);
            throw new KotlinNothingValueException();
         }
      } else {
         fail$default(this, "EOF", 0, null, 6, null);
         throw new KotlinNothingValueException();
      }
   }

   public fun consumeNumericLiteralFully(): Long {
      val result: Long = this.consumeNumericLiteral();
      if (this.consumeNextToken() == 10) {
         return result;
      } else {
         val `expected$iv`: java.lang.String = AbstractJsonLexerKt.tokenDescription((byte)10);
         val `position$iv`: Int = this.currentPosition - 1;
         fail$default(
            this,
            "Expected input to contain a single valid number, but got '${if (this.currentPosition != access$getSource(this).length() && `position$iv` >= 0)
               java.lang.String.valueOf(access$getSource(this).charAt(`position$iv`))
               else
               "EOF"}' after it",
            `position$iv`,
            null,
            4,
            null
         );
         throw new KotlinNothingValueException();
      }
   }

   public fun consumeBoolean(): Boolean {
      return this.consumeBoolean(this.skipWhitespaces());
   }

   public fun consumeBooleanLenient(): Boolean {
      var current: Int = this.skipWhitespaces();
      if (current == this.getSource().length()) {
         fail$default(this, "EOF", 0, null, 6, null);
         throw new KotlinNothingValueException();
      } else {
         val var10000: Boolean;
         if (this.getSource().charAt(current) == '"') {
            current++;
            var10000 = true;
         } else {
            var10000 = false;
         }

         val result: Boolean = this.consumeBoolean(current);
         if (var10000) {
            if (this.currentPosition == this.getSource().length()) {
               fail$default(this, "EOF", 0, null, 6, null);
               throw new KotlinNothingValueException();
            }

            if (this.getSource().charAt(this.currentPosition) != '"') {
               fail$default(this, "Expected closing quotation mark", 0, null, 6, null);
               throw new KotlinNothingValueException();
            }

            this.currentPosition++;
         }

         return result;
      }
   }

   private fun consumeBoolean(start: Int): Boolean {
      val current: Int = this.prefetchOrEof(start);
      if (current < this.getSource().length() && current != -1) {
         var var4: Boolean;
         switch (this.getSource().charAt(current++) | 32) {
            case 102:
               this.consumeBooleanLiteral("alse", current);
               var4 = false;
               break;
            case 116:
               this.consumeBooleanLiteral("rue", current);
               var4 = true;
               break;
            default:
               fail$default(this, "Expected valid boolean literal prefix, but had '${this.consumeStringLenient()}'", 0, null, 6, null);
               throw new KotlinNothingValueException();
         }

         return var4;
      } else {
         fail$default(this, "EOF", 0, null, 6, null);
         throw new KotlinNothingValueException();
      }
   }

   private fun consumeBooleanLiteral(literalSuffix: String, current: Int) {
      if (this.getSource().length() - current < literalSuffix.length()) {
         fail$default(this, "Unexpected end of boolean literal", 0, null, 6, null);
         throw new KotlinNothingValueException();
      } else {
         var i: Int = 0;

         for (int var4 = literalSuffix.length(); i < var4; i++) {
            if (literalSuffix.charAt(i) != (this.getSource().charAt(current + i) or 32)) {
               fail$default(this, "Expected valid boolean literal prefix, but had '${this.consumeStringLenient()}'", 0, null, 6, null);
               throw new KotlinNothingValueException();
            }
         }

         this.currentPosition = current + literalSuffix.length();
      }
   }

   private inline fun <T> withPositionRollback(action: () -> T): T {
      label14: {
         val snapshot: Int = this.currentPosition;

         try {
            val var4: Any = action.invoke();
         } catch (var6: java.lang.Throwable) {
            InlineMarker.finallyStart(1);
            this.currentPosition = snapshot;
            InlineMarker.finallyEnd(1);
         }

         InlineMarker.finallyStart(1);
         this.currentPosition = snapshot;
         InlineMarker.finallyEnd(1);
      }
   }

   @JvmStatic
   fun `consumeNumericLiteral$calculateExponent`(exponentAccumulator: Long, isExponentPositive: Boolean): Double {
      val var10000: Double;
      if (!isExponentPositive) {
         var10000 = Math.pow(10.0, -((double)exponentAccumulator));
      } else {
         if (!isExponentPositive) {
            throw new NoWhenBranchMatchedException();
         }

         var10000 = Math.pow(10.0, (double)exponentAccumulator);
      }

      return var10000;
   }
}
