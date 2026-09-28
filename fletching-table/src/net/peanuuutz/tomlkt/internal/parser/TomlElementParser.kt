package net.peanuuutz.tomlkt.internal.parser

import java.util.ArrayList
import java.util.LinkedHashMap
import kotlin.contracts.InvocationKind
import kotlin.jvm.internal.SourceDebugExtension
import net.peanuuutz.tomlkt.NativeDateTime_jvmKt
import net.peanuuutz.tomlkt.Toml
import net.peanuuutz.tomlkt.TomlArray
import net.peanuuutz.tomlkt.TomlElement
import net.peanuuutz.tomlkt.TomlElementKt
import net.peanuuutz.tomlkt.TomlLiteral
import net.peanuuutz.tomlkt.TomlNull
import net.peanuuutz.tomlkt.TomlReader
import net.peanuuutz.tomlkt.TomlTable
import net.peanuuutz.tomlkt.internal.StringUtilsKt
import net.peanuuutz.tomlkt.internal.TomlSerializationExceptionsKt

@SourceDebugExtension(["SMAP\nTomlElementParser.kt\nKotlin\n*S Kotlin\n*F\n+ 1 TomlElementParser.kt\nnet/peanuuutz/tomlkt/internal/parser/TomlElementParser\n+ 2 _Strings.kt\nkotlin/text/StringsKt___StringsKt\n*L\n1#1,1069:1\n103#1,4:1070\n115#1,4:1074\n115#1,4:1078\n115#1,4:1082\n103#1,4:1086\n115#1,4:1090\n103#1,4:1094\n115#1,4:1098\n115#1,4:1102\n115#1,4:1106\n115#1,4:1110\n115#1,4:1114\n103#1,4:1118\n115#1,4:1133\n115#1,4:1137\n115#1,4:1141\n103#1,4:1145\n103#1,4:1149\n103#1,4:1153\n103#1,4:1157\n115#1,4:1161\n115#1,4:1165\n115#1,4:1169\n115#1,4:1173\n115#1,4:1177\n115#1,4:1181\n115#1,4:1185\n103#1,4:1189\n115#1,4:1193\n115#1,4:1197\n103#1,4:1201\n115#1,4:1205\n115#1,4:1209\n115#1,4:1213\n103#1,4:1217\n115#1,4:1221\n115#1,4:1225\n103#1,4:1229\n103#1,4:1233\n103#1,4:1237\n103#1,4:1241\n103#1,4:1245\n103#1,4:1249\n103#1,4:1253\n103#1,4:1257\n115#1,4:1261\n103#1,4:1265\n103#1,4:1269\n103#1,4:1273\n103#1,4:1277\n103#1,4:1281\n103#1,4:1285\n103#1,4:1289\n103#1,4:1293\n103#1,4:1297\n103#1,4:1301\n115#1,4:1305\n115#1,4:1309\n103#1,4:1313\n115#1,4:1317\n115#1,4:1321\n115#1,4:1325\n103#1,4:1329\n488#2,11:1122\n*S KotlinDebug\n*F\n+ 1 TomlElementParser.kt\nnet/peanuuutz/tomlkt/internal/parser/TomlElementParser\n*L\n126#1:1070,4\n127#1:1074,4\n154#1:1078,4\n158#1:1082,4\n173#1:1086,4\n240#1:1090,4\n245#1:1094,4\n265#1:1098,4\n270#1:1102,4\n275#1:1106,4\n280#1:1110,4\n285#1:1114,4\n294#1:1118,4\n358#1:1133,4\n364#1:1137,4\n368#1:1141,4\n381#1:1145,4\n396#1:1149,4\n408#1:1153,4\n435#1:1157,4\n520#1:1161,4\n529#1:1165,4\n535#1:1169,4\n572#1:1173,4\n579#1:1177,4\n584#1:1181,4\n589#1:1185,4\n593#1:1189,4\n596#1:1193,4\n604#1:1197,4\n608#1:1201,4\n611#1:1205,4\n628#1:1209,4\n633#1:1213,4\n635#1:1217,4\n638#1:1221,4\n671#1:1225,4\n758#1:1229,4\n769#1:1233,4\n772#1:1237,4\n797#1:1241,4\n806#1:1245,4\n818#1:1249,4\n825#1:1253,4\n836#1:1257,4\n839#1:1261,4\n858#1:1265,4\n869#1:1269,4\n880#1:1273,4\n883#1:1277,4\n905#1:1281,4\n912#1:1285,4\n924#1:1289,4\n931#1:1293,4\n946#1:1297,4\n970#1:1301,4\n971#1:1305,4\n979#1:1309,4\n993#1:1313,4\n1015#1:1317,4\n1021#1:1321,4\n1030#1:1325,4\n1043#1:1329,4\n323#1:1122,11\n*E\n"])
internal class TomlElementParser(toml: Toml, reader: TomlReader) {
   private final val toml: Toml
   private final val reader: TomlReader
   private final var currentChar: Char
   private final var previousChar: Char
   private final var currentLineNumber: Int
   private final var isEof: Boolean

   init {
      this.toml = toml;
      this.reader = reader;
      this.currentLineNumber = 1;
   }

   private fun proceed() {
      if (!this.isEof) {
         val code: Int = this.reader.read();
         if (code != -1) {
            this.previousChar = this.currentChar;
            this.currentChar = (char)code;
         } else {
            this.isEof = true;
         }
      }
   }

   private fun getCurrent(): Char {
      return this.currentChar;
   }

   private fun getPrevious(): Char {
      return this.previousChar;
   }

   private fun throwIncomplete(): Nothing {
      TomlSerializationExceptionsKt.throwIncomplete(this.currentLineNumber);
      throw new KotlinNothingValueException();
   }

   private inline fun throwIncompleteIf(predicate: () -> Boolean) {
      contract {
         callsInPlace(predicate, InvocationKind.EXACTLY_ONCE)
      }

      if (predicate.invoke() as java.lang.Boolean) {
         this.throwIncomplete();
         throw new KotlinNothingValueException();
      }
   }

   private fun throwUnexpectedToken(token: Char): Nothing {
      TomlSerializationExceptionsKt.throwUnexpectedToken(token, this.currentLineNumber);
      throw new KotlinNothingValueException();
   }

   private inline fun throwUnexpectedTokenIf(token: Char, predicate: (Char) -> Boolean) {
      contract {
         callsInPlace(predicate, InvocationKind.EXACTLY_ONCE)
      }

      if (predicate.invoke(token) as java.lang.Boolean) {
         this.throwUnexpectedToken(token);
         throw new KotlinNothingValueException();
      }
   }

   private fun throwConflictEntry(path: List<String>): Nothing {
      TomlSerializationExceptionsKt.throwConflictEntry(path, this.currentLineNumber);
      throw new KotlinNothingValueException();
   }

   private fun expectNext(expectedToken: Char) {
      this.proceed();
      if (this.isEof) {
         this.throwIncomplete();
         throw new KotlinNothingValueException();
      } else {
         val var7: Char = this.getCurrent();
         if (var7 != expectedToken) {
            this.throwUnexpectedToken(var7);
            throw new KotlinNothingValueException();
         }
      }
   }

   private fun expectNext(expectedTokens: String) {
      var var2: Int = 0;

      for (int var3 = expectedTokens.length(); var2 < var3; var2++) {
         this.expectNext(expectedTokens.charAt(var2));
      }
   }

   public fun parse(): TomlTable {
      val tree: KeyNode = new KeyNode("", false);
      val arrayOfTableIndices: java.util.Map = new LinkedHashMap();
      var currentTablePath: java.util.List = null;
      this.proceed();

      while (!this.isEof) {
         val current: Char = this.getCurrent();
         if (current == ' ' || current == '\t') {
            this.proceed();
         } else if (current == '\n') {
            val var12: Int = this.currentLineNumber++;
            this.proceed();
         } else if (current == '\r') {
            this.proceed();
            if (this.isEof || this.getCurrent() != '\n') {
               this.throwUnexpectedToken(current);
               throw new KotlinNothingValueException();
            }
         } else if (StringsKt.contains$default("abcdefghijklmnopqrstuvwxyz-_ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789", current, false, 2, null)
            || current == '"'
            || current == '\'') {
            val var11: java.util.List = this.parsePath();
            val var17: Char = this.getCurrent();
            if (var17 != '=') {
               this.throwUnexpectedToken(var17);
               throw new KotlinNothingValueException();
            }

            this.proceed();
            val var23: ValueNode = new ValueNode(CollectionsKt.last(var11), this.parseValue(false));
            val var26: java.util.List = if (currentTablePath != null) CollectionsKt.plus(currentTablePath, var11) else var11;
            if (!TreeNodeKt.addByPath(tree, var26, var23, arrayOfTableIndices)) {
               this.throwConflictEntry(var26);
               throw new KotlinNothingValueException();
            }
         } else if (current == '#') {
            this.parseComment();
         } else {
            if (current != '[') {
               this.throwUnexpectedToken(current);
               throw new KotlinNothingValueException();
            }

            this.proceed();
            if (this.isEof) {
               this.throwIncomplete();
               throw new KotlinNothingValueException();
            }

            val isArrayOfTable: Boolean = this.getCurrent() == '[';
            if (isArrayOfTable) {
               this.proceed();
            }

            val var13: java.util.List = this.parseTableHead(isArrayOfTable);
            if (!isArrayOfTable) {
               if (!TreeNodeKt.addByPath(tree, var13, new KeyNode(CollectionsKt.last(var13), true), arrayOfTableIndices)) {
                  this.throwConflictEntry(var13);
                  throw new KotlinNothingValueException();
               }
            } else {
               val var16: java.util.Iterator = arrayOfTableIndices.keySet().iterator();
               val var20: java.util.Iterator = var16;

               while (var20.hasNext()) {
                  val node: java.util.List = var20.next() as java.util.List;
                  if (!(node == var13) && node.containsAll(var13)) {
                     var16.remove();
                  }
               }

               val var21: Int = arrayOfTableIndices.get(var13) as Int;
               if (var21 == null) {
                  arrayOfTableIndices.put(var13, 0);
                  if (!TreeNodeKt.addByPath(tree, var13, new ArrayNode(CollectionsKt.last(var13)), arrayOfTableIndices)) {
                     this.throwConflictEntry(var13);
                     throw new KotlinNothingValueException();
                  }
               } else {
                  arrayOfTableIndices.put(var13, var21 + 1);
               }

               ArrayNode.add$default(TreeNodeKt.getByPath(tree, var13, arrayOfTableIndices), new KeyNode("", false), null, 2, null);
            }

            currentTablePath = var13;
         }
      }

      return TomlElementKt.TomlTable(tree);
   }

   private fun parseTableHead(isArrayOfTable: Boolean): List<String> {
      var path: Any = null;
      var justEnded: Boolean = false;

      label42:
      while (!this.isEof) {
         val `this_$iv`: Char = this.getCurrent();
         switch (current) {
            case '\t':
            case ' ':
               this.proceed();
               break;
            case '\n':
               this.throwIncomplete();
               throw new KotlinNothingValueException();
            case ']':
               if (isArrayOfTable) {
                  this.expectNext(']');
               }

               justEnded = true;
               this.proceed();
               break label42;
            default:
               if (path != null) {
                  this.throwUnexpectedToken(`this_$iv`);
                  throw new KotlinNothingValueException();
               }

               path = this.parsePath();
         }
      }

      if (path == null || !justEnded) {
         this.throwIncomplete();
         throw new KotlinNothingValueException();
      } else {
         return (java.util.List<java.lang.String>)path;
      }
   }

   private fun parsePath(): List<String> {
      val path: java.util.List = new ArrayList();
      var justEnded: Boolean = false;
      var var10: Boolean = true;

      while (!this.isEof) {
         val `this_$iv`: Char = this.getCurrent();
         if (`this_$iv` != ' ' && `this_$iv` != '\t') {
            if (`this_$iv` == '\n') {
               this.throwIncomplete();
               throw new KotlinNothingValueException();
            }

            if (StringsKt.contains$default("abcdefghijklmnopqrstuvwxyz-_ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789", `this_$iv`, false, 2, null)) {
               if (!var10) {
                  this.throwUnexpectedToken(`this_$iv`);
                  throw new KotlinNothingValueException();
               }

               path.add(this.parseBareKey());
               var10 = false;
            } else if (`this_$iv` == '"') {
               if (!var10) {
                  this.throwUnexpectedToken(`this_$iv`);
                  throw new KotlinNothingValueException();
               }

               path.add(this.parseStringKey());
               var10 = false;
            } else if (`this_$iv` == '\'') {
               if (!var10) {
                  this.throwUnexpectedToken(`this_$iv`);
                  throw new KotlinNothingValueException();
               }

               path.add(this.parseLiteralStringKey());
               var10 = false;
            } else {
               if (`this_$iv` != '.') {
                  if (`this_$iv` != '=' && `this_$iv` != ']') {
                     this.throwUnexpectedToken(`this_$iv`);
                     throw new KotlinNothingValueException();
                  }

                  if (var10) {
                     this.throwUnexpectedToken(`this_$iv`);
                     throw new KotlinNothingValueException();
                  }

                  justEnded = true;
                  break;
               }

               if (var10) {
                  this.throwUnexpectedToken(`this_$iv`);
                  throw new KotlinNothingValueException();
               }

               var10 = true;
               this.proceed();
            }
         } else {
            this.proceed();
         }
      }

      if (!justEnded) {
         this.throwIncomplete();
         throw new KotlinNothingValueException();
      } else {
         return path;
      }
   }

   private fun parseBareKey(): String {
      val builder: StringBuilder = new StringBuilder();

      label32:
      while (!this.isEof) {
         val result: Char = this.getCurrent();
         switch (current) {
            case '\t':
            case ' ':
            case '.':
            case '=':
            case ']':
               break label32;
            case '\n':
               this.throwIncomplete();
               throw new KotlinNothingValueException();
            case '\r':
               this.throwUnexpectedToken(result);
               throw new KotlinNothingValueException();
            default:
               builder.append(result);
               this.proceed();
         }
      }

      val var10000: java.lang.String = builder.toString();
      if (!StringUtilsKt.getBareKeyRegex().matches(var10000)) {
         val var5: java.lang.String = "abcdefghijklmnopqrstuvwxyz-_ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
         val `$this$filterNotTo$iv$iv`: java.lang.CharSequence = var10000;
         val `destination$iv$iv`: Appendable = new StringBuilder();

         for (int var10 = 0; var10 < $this$filterNotTo$iv$iv.length(); var10++) {
            val `element$iv$iv`: Char = `$this$filterNotTo$iv$iv`.charAt(var10);
            if (!StringsKt.contains$default(var5, `element$iv$iv`, false, 2, null)) {
               `destination$iv$iv`.append(`element$iv$iv`);
            }
         }

         this.throwUnexpectedToken((`destination$iv$iv` as StringBuilder).toString().charAt(0));
         throw new KotlinNothingValueException();
      } else {
         return var10000;
      }
   }

   private fun parseStringKey(): String {
      return this.parseStringValue().getContent();
   }

   private fun parseLiteralStringKey(): String {
      return this.parseLiteralStringValue().getContent();
   }

   private fun parseValue(isInsideStructure: Boolean): TomlElement {
      var element: Any = null;

      label125:
      while (!this.isEof) {
         val `this_$iv`: Char = this.getCurrent();
         switch (current) {
            case '\t':
            case ' ':
               this.proceed();
               break;
            case '\n':
               break label125;
            case '\r':
               this.proceed();
               if (this.isEof || this.getCurrent() != '\n') {
                  this.throwUnexpectedToken(`this_$iv`);
                  throw new KotlinNothingValueException();
               }
               break;
            case '#':
               this.parseComment();
               break;
            case ',':
            case ']':
            case '}':
               if (!isInsideStructure) {
                  this.throwUnexpectedToken(`this_$iv`);
                  throw new KotlinNothingValueException();
               }
               break label125;
            default:
               if (element != null) {
                  this.throwUnexpectedToken(`this_$iv`);
                  throw new KotlinNothingValueException();
               }

               var var10000: TomlElement;
               if (`this_$iv` != 't' && `this_$iv` != 'f') {
                  if (StringsKt.contains$default("0123456789", `this_$iv`, false, 2, null)) {
                     var10000 = this.parseNumberOrDateTimeValue(null);
                  } else if (`this_$iv` == 'i') {
                     var10000 = this.parseSpecialNumberValue(null);
                  } else if (`this_$iv` == 'n') {
                     this.proceed();
                     if (this.isEof) {
                        this.throwIncomplete();
                        throw new KotlinNothingValueException();
                     }

                     val var5: Char = this.getCurrent();
                     switch (second) {
                        case 'a':
                           var10000 = this.parseSpecialNumberValue(null);
                           break;
                        case 'u':
                           var10000 = this.parseNullValue();
                           break;
                        default:
                           this.throwUnexpectedToken(var5);
                           throw new KotlinNothingValueException();
                     }
                  } else if (`this_$iv` == '+' || `this_$iv` == '-') {
                     this.proceed();
                     if (this.isEof) {
                        this.throwIncomplete();
                        throw new KotlinNothingValueException();
                     }

                     val var9: Char = this.getCurrent();
                     val var20: TomlLiteral;
                     if (StringsKt.contains$default("0123456789", var9, false, 2, null)) {
                        var20 = this.parseNumberOrDateTimeValue(`this_$iv`);
                     } else if (var9 == 'i') {
                        var20 = this.parseSpecialNumberValue(`this_$iv`);
                     } else {
                        if (var9 != 'n') {
                           this.throwUnexpectedToken(var9);
                           throw new KotlinNothingValueException();
                        }

                        this.proceed();
                        if (this.isEof) {
                           this.throwIncomplete();
                           throw new KotlinNothingValueException();
                        }

                        var20 = this.parseSpecialNumberValue(`this_$iv`);
                     }

                     var10000 = var20;
                  } else if (`this_$iv` == '"') {
                     var10000 = this.parseStringValue();
                  } else if (`this_$iv` == '\'') {
                     var10000 = this.parseLiteralStringValue();
                  } else if (`this_$iv` == '[') {
                     var10000 = this.parseArrayValue();
                  } else {
                     if (`this_$iv` != '{') {
                        this.throwUnexpectedToken(`this_$iv`);
                        throw new KotlinNothingValueException();
                     }

                     var10000 = this.parseInlineTableValue();
                  }
               } else {
                  var10000 = this.parseBooleanValue();
               }

               element = var10000;
         }
      }

      if (element == null) {
         this.throwIncomplete();
         throw new KotlinNothingValueException();
      } else {
         return (TomlElement)element;
      }
   }

   private fun parseBooleanValue(): TomlLiteral {
      val current: Char = this.getCurrent();
      var var10000: TomlLiteral;
      switch (current) {
         case 'f':
            this.expectNext("alse");
            this.proceed();
            var10000 = TomlElementKt.TomlLiteral(false);
            break;
         case 't':
            this.expectNext("rue");
            this.proceed();
            var10000 = TomlElementKt.TomlLiteral(true);
            break;
         default:
            this.throwUnexpectedToken(current);
            throw new KotlinNothingValueException();
      }

      return var10000;
   }

   private fun parseSpecialNumberValue(sign: Char?): TomlLiteral {
      val current: Char = this.getCurrent();
      var var10000: TomlLiteral;
      switch (current) {
         case 'a':
            this.expectNext('n');
            this.proceed();
            var10000 = new TomlLiteral("nan", TomlLiteral.Type.Float);
            break;
         case 'i':
            val content: java.lang.String = if (sign == null) "inf" else "$signinf";
            this.expectNext("nf");
            this.proceed();
            var10000 = new TomlLiteral(content, TomlLiteral.Type.Float);
            break;
         default:
            this.throwUnexpectedToken(current);
            throw new KotlinNothingValueException();
      }

      return var10000;
   }

   private fun parseNumberOrDateTimeValue(sign: Char?): TomlLiteral {
      val builder: StringBuilder = new StringBuilder();
      if (this.getCurrent() == '0') {
         this.proceed();
         if (this.isEof) {
            return new TomlLiteral(if (sign == null) "0" else "$sign0", TomlLiteral.Type.Integer);
         }

         switch (this.getCurrent()) {
            case 'b':
               this.proceed();
               return this.parseNumberValue(builder, 2, sign);
            case 'o':
               this.proceed();
               return this.parseNumberValue(builder, 8, sign);
            case 'x':
               this.proceed();
               return this.parseNumberValue(builder, 16, sign);
            default:
               builder.append('0');
         }
      }

      var isNumber: Boolean = true;

      while (!this.isEof) {
         val current: Char = this.getCurrent();
         if (current == ' ' || current == '\t' || current == '\n' || current == ',' || current == '#' || current == ']' || current == '}') {
            break;
         }

         if (current != '\r') {
            if (!StringsKt.contains$default("0123456789", current, false, 2, null)) {
               if (current != '-' && current != '+') {
                  if (StringsKt.contains$default("Tt:Zz", current, false, 2, null)) {
                     if (sign != null) {
                        this.throwUnexpectedToken(current);
                        throw new KotlinNothingValueException();
                     }

                     isNumber = false;
                  } else if (!StringsKt.contains$default(".acdefABCDEF_", current, false, 2, null)) {
                     this.throwUnexpectedToken(current);
                     throw new KotlinNothingValueException();
                  }
                  break;
               }

               val `this_$iv`: Char = this.getPrevious();
               if (`this_$iv` == 'e' && `this_$iv` == 'E') {
                  break;
               }

               if (sign != null) {
                  this.throwUnexpectedToken(current);
                  throw new KotlinNothingValueException();
               }

               isNumber = false;
               break;
            }

            builder.append(current);
            this.proceed();
         } else {
            this.proceed();
            if (this.isEof || this.getCurrent() != '\n') {
               this.throwUnexpectedToken(current);
               throw new KotlinNothingValueException();
            }
         }
      }

      return if (isNumber) this.parseNumberValue(builder, 10, sign) else this.parseDateTimeValue(builder);
   }

   private fun parseNumberValue(builder: StringBuilder, radix: Int, sign: Char?): TomlLiteral {
      var isDouble: Boolean = false;
      var isExponent: Boolean = false;

      label159:
      while (!this.isEof) {
         val result: Char = this.getCurrent();
         switch (current) {
            case '\t':
            case '\n':
            case ' ':
            case '#':
            case ',':
            case ']':
            case '}':
               break label159;
            case '\r':
               this.proceed();
               if (this.isEof || this.getCurrent() != '\n') {
                  this.throwUnexpectedToken(result);
                  throw new KotlinNothingValueException();
               }
               break;
            case '.':
               if (isDouble || isExponent || radix != 10 || !StringsKt.contains$default("0123456789", this.getPrevious(), false, 2, null)) {
                  this.throwUnexpectedToken(result);
                  throw new KotlinNothingValueException();
               }

               this.proceed();
               if (this.isEof) {
                  this.throwIncomplete();
                  throw new KotlinNothingValueException();
               }

               val nextx: Char = this.getCurrent();
               if (!StringsKt.contains$default("0123456789", nextx, false, 2, null)) {
                  this.throwUnexpectedToken(nextx);
                  throw new KotlinNothingValueException();
               }

               builder.append(result).append(nextx);
               isDouble = true;
               this.proceed();
               break;
            case '0':
            case '1':
               builder.append(result);
               this.proceed();
               break;
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
               if (radix == 2) {
                  this.throwUnexpectedToken(result);
                  throw new KotlinNothingValueException();
               }

               builder.append(result);
               this.proceed();
               break;
            case '8':
            case '9':
               if (radix <= 8) {
                  this.throwUnexpectedToken(result);
                  throw new KotlinNothingValueException();
               }

               builder.append(result);
               this.proceed();
               break;
            case 'A':
            case 'B':
            case 'C':
            case 'D':
            case 'F':
            case 'a':
            case 'b':
            case 'c':
            case 'd':
            case 'f':
               if (radix <= 10) {
                  this.throwUnexpectedToken(result);
                  throw new KotlinNothingValueException();
               }

               builder.append(result);
               this.proceed();
               break;
            case 'E':
            case 'e':
               switch (radix) {
                  case 10:
                     if (isExponent || !StringsKt.contains$default("0123456789", this.getPrevious(), false, 2, null)) {
                        this.throwUnexpectedToken(result);
                        throw new KotlinNothingValueException();
                     }

                     this.proceed();
                     if (this.isEof) {
                        this.throwIncomplete();
                        throw new KotlinNothingValueException();
                     }

                     val var17: Char = this.getCurrent();
                     if (!StringsKt.contains$default("0123456789-+", var17, false, 2, null)) {
                        this.throwUnexpectedToken(var17);
                        throw new KotlinNothingValueException();
                     }

                     builder.append(result).append(var17);
                     isExponent = true;
                     if (var17 == '-') {
                        isDouble = true;
                     }
                     break;
                  case 16:
                     builder.append(result);
                     break;
                  default:
                     this.throwUnexpectedToken(result);
                     throw new KotlinNothingValueException();
               }

               this.proceed();
               break;
            case '_':
               if (!StringsKt.contains$default("0123456789abcdefABCDEF", this.getPrevious(), false, 2, null)) {
                  this.throwUnexpectedToken(result);
                  throw new KotlinNothingValueException();
               }

               this.proceed();
               if (this.isEof) {
                  this.throwIncomplete();
                  throw new KotlinNothingValueException();
               }

               val next: Char = this.getCurrent();
               if (!StringsKt.contains$default("0123456789abcdefABCDEF", next, false, 2, null)) {
                  this.throwUnexpectedToken(next);
                  throw new KotlinNothingValueException();
               }
               break;
            default:
               this.throwUnexpectedToken(result);
               throw new KotlinNothingValueException();
         }
      }

      val var10000: java.lang.String = builder.toString();
      if (sign != null) {
         if (sign == '-') {
            return StringUtilsKt.createNumberTomlLiteral(var10000, false, radix, isDouble, isExponent);
         }
      }

      return StringUtilsKt.createNumberTomlLiteral(var10000, true, radix, isDouble, isExponent);
   }

   private fun parseDateTimeValue(builder: StringBuilder): TomlLiteral {
      var hasDate: Boolean = false;
      var hasTime: Boolean = false;
      var hasOffset: Boolean = false;
      var hasDateTimeSeparator: Boolean = false;

      while (!this.isEof) {
         val result: Char = this.getCurrent();
         if (result == '\t' || result == '\n' || result == ',' || result == '#' || result == ']' || result == '}') {
            break;
         }

         if (result != '\r') {
            if (result == ' ') {
               if (!hasDate || hasDateTimeSeparator) {
                  break;
               }

               hasDateTimeSeparator = true;
               builder.append('T');
               this.proceed();
            } else if (StringsKt.contains$default("0123456789", result, false, 2, null)) {
               builder.append(result);
               this.proceed();
            } else if (result == '-') {
               if (!hasTime) {
                  hasDate = true;
               } else {
                  hasOffset = true;
               }

               builder.append(result);
               this.proceed();
            } else if (result == 'T' || result == 't') {
               hasDateTimeSeparator = true;
               builder.append('T');
               this.proceed();
            } else if (result == ':') {
               if (!hasOffset) {
                  hasTime = true;
               }

               builder.append(result);
               this.proceed();
            } else if (result == '.') {
               builder.append(result);
               this.proceed();
            } else if (result != 'Z' && result != 'z') {
               if (result != '+') {
                  this.throwUnexpectedToken(result);
                  throw new KotlinNothingValueException();
               }

               hasOffset = true;
               builder.append(result);
               this.proceed();
            } else {
               hasOffset = true;
               builder.append('Z');
               this.proceed();
            }
         } else {
            this.proceed();
            if (this.isEof || this.getCurrent() != '\n') {
               this.throwUnexpectedToken(result);
               throw new KotlinNothingValueException();
            }
         }
      }

      val var10000: java.lang.String = builder.toString();
      val var13: TomlLiteral.Type;
      if (hasDate && hasTime) {
         if (!hasOffset) {
            NativeDateTime_jvmKt.NativeLocalDateTime(var10000);
            var13 = TomlLiteral.Type.LocalDateTime;
         } else {
            NativeDateTime_jvmKt.NativeOffsetDateTime(var10000);
            var13 = TomlLiteral.Type.OffsetDateTime;
         }
      } else if (hasDate) {
         NativeDateTime_jvmKt.NativeLocalDate(var10000);
         var13 = TomlLiteral.Type.LocalDate;
      } else {
         if (!hasTime) {
            throw new IllegalStateException(("Malformed date time: $var10000").toString());
         }

         NativeDateTime_jvmKt.NativeLocalTime(var10000);
         var13 = TomlLiteral.Type.LocalTime;
      }

      return new TomlLiteral(var10000, var13);
   }

   private fun parseStringValue(): TomlLiteral {
      this.proceed();
      if (this.isEof) {
         this.throwIncomplete();
         throw new KotlinNothingValueException();
      } else {
         val builder: StringBuilder = new StringBuilder();
         val var15: Boolean;
         if (this.getCurrent() != '"') {
            var15 = false;
         } else {
            this.proceed();
            if (this.isEof || this.getCurrent() != '"') {
               return TomlElementKt.TomlLiteral("");
            }

            var15 = true;
            this.proceed();
            if (this.isEof) {
               this.throwIncomplete();
               throw new KotlinNothingValueException();
            }

            if (this.getCurrent() == '\r') {
               this.proceed();
               if (this.isEof) {
                  this.throwIncomplete();
                  throw new KotlinNothingValueException();
               }

               if (this.getCurrent() != '\n') {
                  builder.append('\r');
               }
            }

            if (this.getCurrent() == '\n') {
               val trim: Int = this.currentLineNumber++;
               this.proceed();
            }
         }

         var var16: Boolean = false;
         var var18: Boolean = false;

         label112:
         while (!this.isEof) {
            val var20: Char = this.getCurrent();
            switch (current) {
               case '\t':
               case ' ':
                  if (!var16) {
                     builder.append(var20);
                  }

                  this.proceed();
                  break;
               case '\n':
                  if (!var15) {
                     this.throwIncomplete();
                     throw new KotlinNothingValueException();
                  }

                  if (!var16) {
                     builder.append(var20);
                  }

                  val var23: Int = this.currentLineNumber++;
                  this.proceed();
                  break;
               case '\r':
                  this.proceed();
                  if (this.isEof) {
                     this.throwIncomplete();
                     throw new KotlinNothingValueException();
                  }

                  if (this.getCurrent() != '\n') {
                     builder.append(var20);
                  }
                  break;
               case '"':
                  if (!var15) {
                     var18 = true;
                     this.proceed();
                     break label112;
                  }

                  this.proceed();
                  if (this.isEof) {
                     this.throwIncomplete();
                     throw new KotlinNothingValueException();
                  }

                  val var22: Char = this.getCurrent();
                  if (var22 != '"') {
                     builder.append(var20);
                     break;
                  } else {
                     this.proceed();
                     if (this.isEof) {
                        this.throwIncomplete();
                        throw new KotlinNothingValueException();
                     }

                     if (this.getCurrent() != '"') {
                        builder.append(var20).append(var22);
                        break;
                     }

                     var18 = true;
                     this.proceed();
                     break label112;
                  }
               case '\\':
                  this.proceed();
                  if (this.isEof) {
                     this.throwIncomplete();
                     throw new KotlinNothingValueException();
                  }

                  val content: Char = this.getCurrent();
                  switch (next) {
                     case '\t':
                     case '\n':
                     case ' ':
                        if (!var15) {
                           this.throwUnexpectedToken(var20);
                           throw new KotlinNothingValueException();
                        }

                        var16 = true;
                        continue;
                     case '"':
                     case 'U':
                     case '\\':
                     case 'b':
                     case 'f':
                     case 'n':
                     case 'r':
                     case 't':
                     case 'u':
                        builder.append(var20).append(content);
                        this.proceed();
                        continue;
                     default:
                        this.throwUnexpectedToken(var20);
                        throw new KotlinNothingValueException();
                  }
               default:
                  builder.append(var20);
                  var16 = false;
                  this.proceed();
            }
         }

         if (!var18) {
            this.throwIncomplete();
            throw new KotlinNothingValueException();
         } else {
            val var10000: java.lang.String = builder.toString();
            return TomlElementKt.TomlLiteral(StringUtilsKt.unescape(var10000));
         }
      }
   }

   private fun parseLiteralStringValue(): TomlLiteral {
      this.proceed();
      if (this.isEof) {
         this.throwIncomplete();
         throw new KotlinNothingValueException();
      } else {
         val builder: StringBuilder = new StringBuilder();
         val var12: Boolean;
         if (this.getCurrent() != '\'') {
            var12 = false;
         } else {
            this.proceed();
            if (this.isEof || this.getCurrent() != '\'') {
               return TomlElementKt.TomlLiteral("");
            }

            var12 = true;
            this.proceed();
            if (this.isEof) {
               this.throwIncomplete();
               throw new KotlinNothingValueException();
            }

            if (this.getCurrent() == '\r') {
               this.proceed();
               if (this.isEof) {
                  this.throwIncomplete();
                  throw new KotlinNothingValueException();
               }

               if (this.getCurrent() != '\n') {
                  builder.append('\r');
               }
            }

            if (this.getCurrent() == '\n') {
               val justEnded: Int = this.currentLineNumber++;
               this.proceed();
            }
         }

         var var13: Boolean = false;

         label86:
         while (!this.isEof) {
            val var15: Char = this.getCurrent();
            switch (current) {
               case '\t':
               case ' ':
                  builder.append(var15);
                  this.proceed();
                  break;
               case '\n':
                  if (!var12) {
                     this.throwIncomplete();
                     throw new KotlinNothingValueException();
                  }

                  builder.append(var15);
                  val var19: Int = this.currentLineNumber++;
                  this.proceed();
                  break;
               case '\r':
                  this.proceed();
                  if (this.isEof) {
                     this.throwIncomplete();
                     throw new KotlinNothingValueException();
                  }

                  if (this.getCurrent() != '\n') {
                     builder.append(var15);
                  }
                  break;
               case '\'':
                  if (!var12) {
                     var13 = true;
                     this.proceed();
                     break label86;
                  }

                  this.proceed();
                  if (this.isEof) {
                     this.throwIncomplete();
                     throw new KotlinNothingValueException();
                  }

                  val var18: Char = this.getCurrent();
                  if (var18 != '\'') {
                     builder.append(var15);
                     break;
                  } else {
                     this.proceed();
                     if (this.isEof) {
                        this.throwIncomplete();
                        throw new KotlinNothingValueException();
                     }

                     if (this.getCurrent() != '\'') {
                        builder.append(var15).append(var18);
                        break;
                     }

                     var13 = true;
                     this.proceed();
                     break label86;
                  }
               default:
                  builder.append(var15);
                  this.proceed();
            }
         }

         if (!var13) {
            this.throwIncomplete();
            throw new KotlinNothingValueException();
         } else {
            val var10000: java.lang.String = builder.toString();
            return TomlElementKt.TomlLiteral(var10000);
         }
      }
   }

   private fun parseArrayValue(): TomlArray {
      this.proceed();
      val builder: java.util.List = new ArrayList();
      var var10: Boolean = true;
      var justEnded: Boolean = false;

      label45:
      while (!this.isEof) {
         val `this_$iv`: Char = this.getCurrent();
         switch (current) {
            case '\t':
            case ' ':
               this.proceed();
               break;
            case '\n':
               val var11: Int = this.currentLineNumber++;
               this.proceed();
               break;
            case '\r':
               this.proceed();
               if (this.isEof) {
                  this.throwIncomplete();
                  throw new KotlinNothingValueException();
               }

               if (this.getCurrent() != '\n') {
                  this.throwUnexpectedToken(`this_$iv`);
                  throw new KotlinNothingValueException();
               }
               break;
            case '#':
               this.parseComment();
               break;
            case ',':
               if (var10) {
                  this.throwUnexpectedToken(`this_$iv`);
                  throw new KotlinNothingValueException();
               }

               var10 = true;
               this.proceed();
               break;
            case ']':
               justEnded = true;
               this.proceed();
               break label45;
            default:
               builder.add(this.parseValue(true));
               var10 = false;
         }
      }

      if (!justEnded) {
         this.throwIncomplete();
         throw new KotlinNothingValueException();
      } else {
         return new TomlArray(builder, null, 2, null);
      }
   }

   private fun parseInlineTableValue(): TomlTable {
      this.proceed();
      val builder: KeyNode = new KeyNode("", false);
      var var12: Boolean = true;
      var var13: Boolean = true;
      var justEnded: Boolean = false;

      label57:
      while (!this.isEof) {
         val `this_$iv`: Char = this.getCurrent();
         switch (current) {
            case '\t':
            case ' ':
               this.proceed();
               break;
            case '\n':
               this.throwIncomplete();
               throw new KotlinNothingValueException();
            case '#':
               this.throwUnexpectedToken(`this_$iv`);
               throw new KotlinNothingValueException();
            case ',':
               if (var12) {
                  this.throwUnexpectedToken(`this_$iv`);
                  throw new KotlinNothingValueException();
               }

               var12 = true;
               this.proceed();
               break;
            case '}':
               if (var12 && !var13) {
                  this.throwUnexpectedToken(`this_$iv`);
                  throw new KotlinNothingValueException();
               }

               justEnded = true;
               this.proceed();
               break label57;
            default:
               val `$i$f$throwIncompleteIf`: java.util.List = this.parsePath();
               val value: Char = this.getCurrent();
               if (value != '=') {
                  this.throwUnexpectedToken(value);
                  throw new KotlinNothingValueException();
               }

               this.proceed();
               if (!TreeNodeKt.addByPath(
                  builder, `$i$f$throwIncompleteIf`, new ValueNode(CollectionsKt.last(`$i$f$throwIncompleteIf`), this.parseValue(true)), null
               )) {
                  this.throwConflictEntry(`$i$f$throwIncompleteIf`);
                  throw new KotlinNothingValueException();
               }

               var12 = false;
               var13 = false;
         }
      }

      if (!justEnded) {
         this.throwIncomplete();
         throw new KotlinNothingValueException();
      } else {
         return TomlElementKt.TomlTable(builder);
      }
   }

   private fun parseNullValue(): TomlNull {
      this.expectNext("ll");
      this.proceed();
      return TomlNull.INSTANCE;
   }

   private fun parseComment() {
      this.proceed();

      while (!this.isEof && this.getCurrent() != '\n') {
         this.proceed();
      }
   }
}
