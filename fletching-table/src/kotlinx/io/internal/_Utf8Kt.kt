@file:SourceDebugExtension(["SMAP\n-Utf8.kt\nKotlin\n*S Kotlin\n*F\n+ 1 -Utf8.kt\nkotlinx/io/internal/_Utf8Kt\n+ 2 -Util.kt\nkotlinx/io/_UtilKt\n*L\n1#1,405:1\n111#1,17:406\n129#1:424\n252#1,9:425\n52#1:434\n261#1,13:436\n132#1,3:449\n288#1,4:452\n52#1:456\n294#1,10:457\n52#1:467\n304#1,5:468\n52#1:473\n309#1,23:474\n137#1,3:497\n347#1,3:500\n140#1,12:503\n350#1:515\n52#1:516\n353#1,2:517\n52#1:519\n357#1,10:520\n52#1:530\n367#1,5:531\n52#1:536\n372#1,5:537\n52#1:542\n377#1,28:543\n157#1,6:571\n252#1,9:579\n52#1:588\n261#1,13:590\n288#1,4:604\n52#1:608\n294#1,10:610\n52#1:620\n304#1,5:621\n52#1:626\n309#1,23:627\n347#1,4:651\n52#1:655\n353#1,2:657\n52#1:659\n357#1,10:660\n52#1:670\n367#1,5:671\n52#1:676\n372#1,5:677\n52#1:682\n377#1,28:683\n252#1,9:712\n52#1:721\n261#1,13:723\n288#1,4:737\n52#1:741\n294#1,10:743\n52#1:753\n304#1,5:754\n52#1:759\n309#1,23:760\n347#1,4:784\n52#1:788\n353#1,2:790\n52#1:792\n357#1,10:793\n52#1:803\n367#1,5:804\n52#1:809\n372#1,5:810\n52#1:815\n377#1,28:816\n52#1:844\n52#1:846\n52#1:848\n52#1:850\n52#1:852\n52#1:854\n52#1:856\n52#1:858\n52#1:860\n89#2:423\n95#2:435\n95#2:577\n89#2:578\n95#2:589\n89#2:603\n95#2:609\n89#2:650\n95#2:656\n89#2:711\n95#2:722\n89#2:736\n95#2:742\n89#2:783\n95#2:789\n95#2:845\n95#2:847\n95#2:849\n95#2:851\n95#2:853\n95#2:855\n95#2:857\n95#2:859\n95#2:861\n*S KotlinDebug\n*F\n+ 1 -Utf8.kt\nkotlinx/io/internal/_Utf8Kt\n*L\n34#1:406,17\n34#1:424\n34#1:425,9\n34#1:434\n34#1:436,13\n34#1:449,3\n34#1:452,4\n34#1:456\n34#1:457,10\n34#1:467\n34#1:468,5\n34#1:473\n34#1:474,23\n34#1:497,3\n34#1:500,3\n34#1:503,12\n34#1:515\n34#1:516\n34#1:517,2\n34#1:519\n34#1:520,10\n34#1:530\n34#1:531,5\n34#1:536\n34#1:537,5\n34#1:542\n34#1:543,28\n34#1:571,6\n77#1:579,9\n77#1:588\n77#1:590,13\n82#1:604,4\n82#1:608\n82#1:610,10\n82#1:620\n82#1:621,5\n82#1:626\n82#1:627,23\n87#1:651,4\n87#1:655\n87#1:657,2\n87#1:659\n87#1:660,10\n87#1:670\n87#1:671,5\n87#1:676\n87#1:677,5\n87#1:682\n87#1:683,28\n129#1:712,9\n129#1:721\n129#1:723,13\n134#1:737,4\n134#1:741\n134#1:743,10\n134#1:753\n134#1:754,5\n134#1:759\n134#1:760,23\n139#1:784,4\n139#1:788\n139#1:790,2\n139#1:792\n139#1:793,10\n139#1:803\n139#1:804,5\n139#1:809\n139#1:810,5\n139#1:815\n139#1:816,28\n260#1:844\n291#1:846\n303#1:848\n308#1:850\n350#1:852\n354#1:854\n366#1:856\n371#1:858\n376#1:860\n34#1:423\n34#1:435\n52#1:577\n75#1:578\n77#1:589\n80#1:603\n82#1:609\n85#1:650\n87#1:656\n127#1:711\n129#1:722\n132#1:736\n134#1:742\n137#1:783\n139#1:789\n260#1:845\n291#1:847\n303#1:849\n308#1:851\n350#1:853\n354#1:855\n366#1:857\n371#1:859\n376#1:861\n*E\n"])

package kotlinx.io.internal

import kotlin.jvm.internal.SourceDebugExtension

internal const val REPLACEMENT_BYTE: Byte = 63
internal const val REPLACEMENT_CHARACTER: Char = '�'
internal const val REPLACEMENT_CODE_POINT: Int = 65533
internal const val HIGH_SURROGATE_HEADER: Int = 55232
internal const val LOG_SURROGATE_HEADER: Int = 56320
internal const val MASK_2BYTES: Int = 3968
internal const val MASK_3BYTES: Int = -123008
internal const val MASK_4BYTES: Int = 3678080

internal fun ByteArray.commonToUtf8String(beginIndex: Int = 0, endIndex: Int = `$this$commonToUtf8String`.length): String {
   if (beginIndex >= 0 && endIndex <= `$this$commonToUtf8String`.length && beginIndex <= endIndex) {
      val chars: CharArray = new char[endIndex - beginIndex];
      var length: Int = 0;
      val `$this$processUtf16Chars$iv`: ByteArray = `$this$commonToUtf8String`;
      var `index$iv`: Int = beginIndex;

      while (index$iv < endIndex) {
         val `b0$iv`: Byte = `$this$processUtf16Chars$iv`[`index$iv`];
         if (`$this$processUtf16Chars$iv`[`index$iv`] >= 0) {
            chars[length++] = (char)`b0$iv`;
            `index$iv`++;

            while (index$iv < endIndex && $this$processUtf16Chars$iv[index$iv] >= 0) {
               chars[length++] = (char)`$this$processUtf16Chars$iv`[`index$iv`++];
            }
         } else if (`b0$iv` shr 5 == -2) {
            val var234: Int;
            val var236: Byte;
            if (endIndex <= `index$iv` + 1) {
               chars[length++] = (char)'�';
               var234 = `index$iv`;
               var236 = 1;
            } else {
               val var202: Byte = `$this$processUtf16Chars$iv`[`index$iv`];
               val var207: Byte = `$this$processUtf16Chars$iv`[`index$iv` + 1];
               if ((`$this$processUtf16Chars$iv`[`index$iv` + 1] and 192) != 128) {
                  chars[length++] = (char)'�';
                  var234 = `index$iv`;
                  var236 = 1;
               } else {
                  val var212: Int = 3968 xor var207 xor var202 shl 6;
                  if ((3968 xor var207 xor var202 shl 6) < 128) {
                     chars[length++] = (char)'�';
                     var234 = `index$iv`;
                  } else {
                     chars[length++] = (char)var212;
                     var234 = `index$iv`;
                  }

                  var236 = 2;
               }
            }

            `index$iv` = var234 + var236;
         } else if (`b0$iv` shr 4 == -2) {
            var var233: Int;
            var var235: Byte;
            label201:
            if (endIndex <= `index$iv` + 2) {
               chars[length++] = (char)'�';
               var233 = `index$iv`;
               var235 = (byte)(if (endIndex > `index$iv` + 1 && (`$this$processUtf16Chars$iv`[`index$iv` + 1] and 192) == 128) 2 else 1);
               break label201;
            } else {
               val var201: Byte = `$this$processUtf16Chars$iv`[`index$iv`];
               val var206: Byte = `$this$processUtf16Chars$iv`[`index$iv` + 1];
               if ((`$this$processUtf16Chars$iv`[`index$iv` + 1] and 192) != 128) {
                  chars[length++] = (char)'�';
                  var233 = `index$iv`;
                  var235 = 1;
               } else {
                  val var210: Byte = `$this$processUtf16Chars$iv`[`index$iv` + 2];
                  if ((`$this$processUtf16Chars$iv`[`index$iv` + 2] and 192) != 128) {
                     chars[length++] = (char)'�';
                     var233 = `index$iv`;
                     var235 = 2;
                  } else {
                     val var218: Int = -123008 xor var210 xor var206 shl 6 xor var201 shl 12;
                     if ((-123008 xor var210 xor var206 shl 6 xor var201 shl 12) < 2048) {
                        chars[length++] = (char)'�';
                        var233 = `index$iv`;
                     } else if (55296 <= var218 && var218 < 57344) {
                        chars[length++] = (char)'�';
                        var233 = `index$iv`;
                     } else {
                        chars[length++] = (char)var218;
                        var233 = `index$iv`;
                     }

                     var235 = 3;
                  }
               }
            }

            `index$iv` = var233 + var235;
         } else if (`b0$iv` shr 3 != -2) {
            chars[length++] = '�';
            `index$iv`++;
         } else {
            var var10000: Int;
            var var10001: Byte;
            label214:
            if (endIndex <= `index$iv` + 3) {
               if ('�' != '�') {
                  chars[length++] = (char)(('�' ushr 10) + 55232);
                  chars[length++] = (char)(('�' and 1023) + 56320);
               } else {
                  chars[length++] = '�';
               }

               var10000 = `index$iv`;
               var10001 = (byte)(if (endIndex <= `index$iv` + 1 || (`$this$processUtf16Chars$iv`[`index$iv` + 1] and 192) != 128)
                  1
                  else
                  (if (endIndex > `index$iv` + 2 && (`$this$processUtf16Chars$iv`[`index$iv` + 2] and 192) == 128) 3 else 2));
               break label214;
            } else {
               val var199: Byte = `$this$processUtf16Chars$iv`[`index$iv`];
               val var204: Byte = `$this$processUtf16Chars$iv`[`index$iv` + 1];
               if ((`$this$processUtf16Chars$iv`[`index$iv` + 1] and 192) != 128) {
                  if ('�' != '�') {
                     chars[length++] = (char)(('�' ushr 10) + 55232);
                     chars[length++] = (char)(('�' and 1023) + 56320);
                  } else {
                     chars[length++] = '�';
                  }

                  var10000 = `index$iv`;
                  var10001 = 1;
               } else {
                  val var208: Byte = `$this$processUtf16Chars$iv`[`index$iv` + 2];
                  if ((`$this$processUtf16Chars$iv`[`index$iv` + 2] and 192) != 128) {
                     if ('�' != '�') {
                        chars[length++] = (char)(('�' ushr 10) + 55232);
                        chars[length++] = (char)(('�' and 1023) + 56320);
                     } else {
                        chars[length++] = '�';
                     }

                     var10000 = `index$iv`;
                     var10001 = 2;
                  } else {
                     val var215: Byte = `$this$processUtf16Chars$iv`[`index$iv` + 3];
                     if ((`$this$processUtf16Chars$iv`[`index$iv` + 3] and 192) != 128) {
                        if ('�' != '�') {
                           chars[length++] = (char)(('�' ushr 10) + 55232);
                           chars[length++] = (char)(('�' and 1023) + 56320);
                        } else {
                           chars[length++] = '�';
                        }

                        var10000 = `index$iv`;
                        var10001 = 3;
                     } else {
                        val var222: Int = 3678080 xor var215 xor var208 shl 6 xor var204 shl 12 xor var199 shl 18;
                        if ((3678080 xor var215 xor var208 shl 6 xor var204 shl 12 xor var199 shl 18) > 1114111) {
                           if ('�' != '�') {
                              chars[length++] = (char)(('�' ushr 10) + 55232);
                              chars[length++] = (char)(('�' and 1023) + 56320);
                           } else {
                              chars[length++] = '�';
                           }

                           var10000 = `index$iv`;
                        } else if (55296 <= var222 && var222 < 57344) {
                           if ('�' != '�') {
                              chars[length++] = (char)(('�' ushr 10) + 55232);
                              chars[length++] = (char)(('�' and 1023) + 56320);
                           } else {
                              chars[length++] = '�';
                           }

                           var10000 = `index$iv`;
                        } else if (var222 < 65536) {
                           if ('�' != '�') {
                              chars[length++] = (char)(('�' ushr 10) + 55232);
                              chars[length++] = (char)(('�' and 1023) + 56320);
                           } else {
                              chars[length++] = '�';
                           }

                           var10000 = `index$iv`;
                        } else {
                           if (var222 != 65533) {
                              chars[length++] = (char)((var222 ushr 10) + 55232);
                              chars[length++] = (char)((var222 and 1023) + 56320);
                           } else {
                              chars[length++] = '�';
                           }

                           var10000 = `index$iv`;
                        }

                        var10001 = 4;
                     }
                  }
               }
            }

            `index$iv` = var10000 + var10001;
         }
      }

      return StringsKt.concatToString(chars, 0, length);
   } else {
      throw new IndexOutOfBoundsException("size=${`$this$commonToUtf8String`.length} beginIndex=$beginIndex endIndex=$endIndex");
   }
}

@JvmSynthetic
fun `commonToUtf8String$default`(var0: ByteArray, var1: Int, var2: Int, var3: Int, var4: Any): java.lang.String {
   if ((var3 and 1) != 0) {
      var1 = 0;
   }

   if ((var3 and 2) != 0) {
      var2 = var0.length;
   }

   return commonToUtf8String(var0, var1, var2);
}

internal inline fun isIsoControl(codePoint: Int): Boolean {
   return 0 <= codePoint && codePoint < 32 || 127 <= codePoint && codePoint < 160;
}

internal inline fun isUtf8Continuation(byte: Byte): Boolean {
   return (var0 and 192) == 128;
}

internal inline fun ByteArray.processUtf8CodePoints(beginIndex: Int, endIndex: Int, yield: (Int) -> Unit) {
   var index: Int = beginIndex;

   while (index < endIndex) {
      val b0: Byte = `$this$processUtf8CodePoints`[index];
      if (`$this$processUtf8CodePoints`[index] >= 0) {
         yield.invoke(Integer.valueOf(b0));
         index++;

         while (index < endIndex && $this$processUtf8CodePoints[index] >= 0) {
            yield.invoke(Integer.valueOf(`$this$processUtf8CodePoints`[index++]));
         }
      } else if (b0 shr 5 == -2) {
         val var112: Int;
         val var114: Byte;
         if (endIndex <= index + 1) {
            yield.invoke(65533);
            var112 = index;
            var114 = 1;
         } else {
            val var64: Byte = `$this$processUtf8CodePoints`[index];
            val var69: Byte = `$this$processUtf8CodePoints`[index + 1];
            if ((`$this$processUtf8CodePoints`[index + 1] and 192) != 128) {
               yield.invoke(65533);
               var112 = index;
               var114 = 1;
            } else {
               val var74: Int = 3968 xor var69 xor var64 shl 6;
               if ((3968 xor var69 xor var64 shl 6) < 128) {
                  yield.invoke(65533);
                  var112 = index;
               } else {
                  yield.invoke(var74);
                  var112 = index;
               }

               var114 = 2;
            }
         }

         index = var112 + var114;
      } else if (b0 shr 4 == -2) {
         var var111: Int;
         var var113: Byte;
         label151:
         if (endIndex <= index + 2) {
            yield.invoke(65533);
            var111 = index;
            var113 = (byte)(if (endIndex > index + 1 && (`$this$processUtf8CodePoints`[index + 1] and 192) == 128) 2 else 1);
            break label151;
         } else {
            val var63: Byte = `$this$processUtf8CodePoints`[index];
            val var68: Byte = `$this$processUtf8CodePoints`[index + 1];
            if ((`$this$processUtf8CodePoints`[index + 1] and 192) != 128) {
               yield.invoke(65533);
               var111 = index;
               var113 = 1;
            } else {
               val var72: Byte = `$this$processUtf8CodePoints`[index + 2];
               if ((`$this$processUtf8CodePoints`[index + 2] and 192) != 128) {
                  yield.invoke(65533);
                  var111 = index;
                  var113 = 2;
               } else {
                  val var80: Int = -123008 xor var72 xor var68 shl 6 xor var63 shl 12;
                  if ((-123008 xor var72 xor var68 shl 6 xor var63 shl 12) < 2048) {
                     yield.invoke(65533);
                     var111 = index;
                  } else if (55296 <= var80 && var80 < 57344) {
                     yield.invoke(65533);
                     var111 = index;
                  } else {
                     yield.invoke(var80);
                     var111 = index;
                  }

                  var113 = 3;
               }
            }
         }

         index = var111 + var113;
      } else if (b0 shr 3 != -2) {
         yield.invoke(65533);
         index++;
      } else {
         var var10000: Int;
         var var10001: Byte;
         label163:
         if (endIndex <= index + 3) {
            yield.invoke(65533);
            var10000 = index;
            var10001 = (byte)(if (endIndex <= index + 1 || (`$this$processUtf8CodePoints`[index + 1] and 192) != 128)
               1
               else
               (if (endIndex > index + 2 && (`$this$processUtf8CodePoints`[index + 2] and 192) == 128) 3 else 2));
            break label163;
         } else {
            val var61: Byte = `$this$processUtf8CodePoints`[index];
            val var66: Byte = `$this$processUtf8CodePoints`[index + 1];
            if ((`$this$processUtf8CodePoints`[index + 1] and 192) != 128) {
               yield.invoke(65533);
               var10000 = index;
               var10001 = 1;
            } else {
               val var70: Byte = `$this$processUtf8CodePoints`[index + 2];
               if ((`$this$processUtf8CodePoints`[index + 2] and 192) != 128) {
                  yield.invoke(65533);
                  var10000 = index;
                  var10001 = 2;
               } else {
                  val var77: Byte = `$this$processUtf8CodePoints`[index + 3];
                  if ((`$this$processUtf8CodePoints`[index + 3] and 192) != 128) {
                     yield.invoke(65533);
                     var10000 = index;
                     var10001 = 3;
                  } else {
                     val var84: Int = 3678080 xor var77 xor var70 shl 6 xor var66 shl 12 xor var61 shl 18;
                     if ((3678080 xor var77 xor var70 shl 6 xor var66 shl 12 xor var61 shl 18) > 1114111) {
                        yield.invoke(65533);
                        var10000 = index;
                     } else if (55296 <= var84 && var84 < 57344) {
                        yield.invoke(65533);
                        var10000 = index;
                     } else if (var84 < 65536) {
                        yield.invoke(65533);
                        var10000 = index;
                     } else {
                        yield.invoke(var84);
                        var10000 = index;
                     }

                     var10001 = 4;
                  }
               }
            }
         }

         index = var10000 + var10001;
      }
   }
}

internal inline fun ByteArray.processUtf16Chars(beginIndex: Int, endIndex: Int, yield: (Char) -> Unit) {
   var index: Int = beginIndex;

   while (index < endIndex) {
      val b0: Byte = `$this$processUtf16Chars`[index];
      if (`$this$processUtf16Chars`[index] >= 0) {
         yield.invoke((char)b0);
         index++;

         while (index < endIndex && $this$processUtf16Chars[index] >= 0) {
            yield.invoke((char)`$this$processUtf16Chars`[index++]);
         }
      } else if (b0 shr 5 == -2) {
         val var112: Int;
         val var114: Byte;
         if (endIndex <= index + 1) {
            yield.invoke((char)'�');
            var112 = index;
            var114 = 1;
         } else {
            val var64: Byte = `$this$processUtf16Chars`[index];
            val var69: Byte = `$this$processUtf16Chars`[index + 1];
            if ((`$this$processUtf16Chars`[index + 1] and 192) != 128) {
               yield.invoke((char)'�');
               var112 = index;
               var114 = 1;
            } else {
               val var74: Int = 3968 xor var69 xor var64 shl 6;
               if ((3968 xor var69 xor var64 shl 6) < 128) {
                  yield.invoke((char)'�');
                  var112 = index;
               } else {
                  yield.invoke((char)var74);
                  var112 = index;
               }

               var114 = 2;
            }
         }

         index = var112 + var114;
      } else if (b0 shr 4 == -2) {
         var var111: Int;
         var var113: Byte;
         label190:
         if (endIndex <= index + 2) {
            yield.invoke((char)'�');
            var111 = index;
            var113 = (byte)(if (endIndex > index + 1 && (`$this$processUtf16Chars`[index + 1] and 192) == 128) 2 else 1);
            break label190;
         } else {
            val var63: Byte = `$this$processUtf16Chars`[index];
            val var68: Byte = `$this$processUtf16Chars`[index + 1];
            if ((`$this$processUtf16Chars`[index + 1] and 192) != 128) {
               yield.invoke((char)'�');
               var111 = index;
               var113 = 1;
            } else {
               val var72: Byte = `$this$processUtf16Chars`[index + 2];
               if ((`$this$processUtf16Chars`[index + 2] and 192) != 128) {
                  yield.invoke((char)'�');
                  var111 = index;
                  var113 = 2;
               } else {
                  val var80: Int = -123008 xor var72 xor var68 shl 6 xor var63 shl 12;
                  if ((-123008 xor var72 xor var68 shl 6 xor var63 shl 12) < 2048) {
                     yield.invoke((char)'�');
                     var111 = index;
                  } else if (55296 <= var80 && var80 < 57344) {
                     yield.invoke((char)'�');
                     var111 = index;
                  } else {
                     yield.invoke((char)var80);
                     var111 = index;
                  }

                  var113 = 3;
               }
            }
         }

         index = var111 + var113;
      } else if (b0 shr 3 != -2) {
         yield.invoke('�');
         index++;
      } else {
         var var10000: Int;
         var var10001: Byte;
         label203:
         if (endIndex <= index + 3) {
            if ('�' != '�') {
               yield.invoke((char)(('�' ushr 10) + 55232));
               yield.invoke((char)(('�' and 1023) + 56320));
            } else {
               yield.invoke('�');
            }

            var10000 = index;
            var10001 = (byte)(if (endIndex <= index + 1 || (`$this$processUtf16Chars`[index + 1] and 192) != 128)
               1
               else
               (if (endIndex > index + 2 && (`$this$processUtf16Chars`[index + 2] and 192) == 128) 3 else 2));
            break label203;
         } else {
            val var61: Byte = `$this$processUtf16Chars`[index];
            val var66: Byte = `$this$processUtf16Chars`[index + 1];
            if ((`$this$processUtf16Chars`[index + 1] and 192) != 128) {
               if ('�' != '�') {
                  yield.invoke((char)(('�' ushr 10) + 55232));
                  yield.invoke((char)(('�' and 1023) + 56320));
               } else {
                  yield.invoke('�');
               }

               var10000 = index;
               var10001 = 1;
            } else {
               val var70: Byte = `$this$processUtf16Chars`[index + 2];
               if ((`$this$processUtf16Chars`[index + 2] and 192) != 128) {
                  if ('�' != '�') {
                     yield.invoke((char)(('�' ushr 10) + 55232));
                     yield.invoke((char)(('�' and 1023) + 56320));
                  } else {
                     yield.invoke('�');
                  }

                  var10000 = index;
                  var10001 = 2;
               } else {
                  val var77: Byte = `$this$processUtf16Chars`[index + 3];
                  if ((`$this$processUtf16Chars`[index + 3] and 192) != 128) {
                     if ('�' != '�') {
                        yield.invoke((char)(('�' ushr 10) + 55232));
                        yield.invoke((char)(('�' and 1023) + 56320));
                     } else {
                        yield.invoke('�');
                     }

                     var10000 = index;
                     var10001 = 3;
                  } else {
                     val var84: Int = 3678080 xor var77 xor var70 shl 6 xor var66 shl 12 xor var61 shl 18;
                     if ((3678080 xor var77 xor var70 shl 6 xor var66 shl 12 xor var61 shl 18) > 1114111) {
                        if ('�' != '�') {
                           yield.invoke((char)(('�' ushr 10) + 55232));
                           yield.invoke((char)(('�' and 1023) + 56320));
                        } else {
                           yield.invoke('�');
                        }

                        var10000 = index;
                     } else if (55296 <= var84 && var84 < 57344) {
                        if ('�' != '�') {
                           yield.invoke((char)(('�' ushr 10) + 55232));
                           yield.invoke((char)(('�' and 1023) + 56320));
                        } else {
                           yield.invoke('�');
                        }

                        var10000 = index;
                     } else if (var84 < 65536) {
                        if ('�' != '�') {
                           yield.invoke((char)(('�' ushr 10) + 55232));
                           yield.invoke((char)(('�' and 1023) + 56320));
                        } else {
                           yield.invoke('�');
                        }

                        var10000 = index;
                     } else {
                        if (var84 != 65533) {
                           yield.invoke((char)((var84 ushr 10) + 55232));
                           yield.invoke((char)((var84 and 1023) + 56320));
                        } else {
                           yield.invoke('�');
                        }

                        var10000 = index;
                     }

                     var10001 = 4;
                  }
               }
            }
         }

         index = var10000 + var10001;
      }
   }
}

internal inline fun ByteArray.process2Utf8Bytes(beginIndex: Int, endIndex: Int, yield: (Int) -> Unit): Int {
   if (endIndex <= beginIndex + 1) {
      yield.invoke(65533);
      return 1;
   } else {
      val b0: Byte = `$this$process2Utf8Bytes`[beginIndex];
      val b1: Byte = `$this$process2Utf8Bytes`[beginIndex + 1];
      if ((`$this$process2Utf8Bytes`[beginIndex + 1] and 192) != 128) {
         yield.invoke(65533);
         return 1;
      } else {
         val var11: Int = 3968 xor b1 xor b0 shl 6;
         if ((3968 xor b1 xor b0 shl 6) < 128) {
            yield.invoke(65533);
         } else {
            yield.invoke(var11);
         }

         return 2;
      }
   }
}

internal inline fun ByteArray.process3Utf8Bytes(beginIndex: Int, endIndex: Int, yield: (Int) -> Unit): Int {
   label52:
   if (endIndex <= beginIndex + 2) {
      yield.invoke(65533);
      return if (endIndex > beginIndex + 1 && (`$this$process3Utf8Bytes`[beginIndex + 1] and 192) == 128) 2 else 1;
   } else {
      val b0: Byte = `$this$process3Utf8Bytes`[beginIndex];
      val b1: Byte = `$this$process3Utf8Bytes`[beginIndex + 1];
      if ((`$this$process3Utf8Bytes`[beginIndex + 1] and 192) != 128) {
         yield.invoke(65533);
         return 1;
      } else {
         val var14: Byte = `$this$process3Utf8Bytes`[beginIndex + 2];
         if ((`$this$process3Utf8Bytes`[beginIndex + 2] and 192) != 128) {
            yield.invoke(65533);
            return 2;
         } else {
            val var15: Int = -123008 xor var14 xor b1 shl 6 xor b0 shl 12;
            if ((-123008 xor var14 xor b1 shl 6 xor b0 shl 12) < 2048) {
               yield.invoke(65533);
            } else if (55296 <= var15 && var15 < 57344) {
               yield.invoke(65533);
            } else {
               yield.invoke(var15);
            }

            return 3;
         }
      }
   }
}

internal inline fun ByteArray.process4Utf8Bytes(beginIndex: Int, endIndex: Int, yield: (Int) -> Unit): Int {
   label75:
   if (endIndex <= beginIndex + 3) {
      yield.invoke(65533);
      if (endIndex <= beginIndex + 1 || (`$this$process4Utf8Bytes`[beginIndex + 1] and 192) != 128) {
         return 1;
      } else {
         return if (endIndex > beginIndex + 2 && (`$this$process4Utf8Bytes`[beginIndex + 2] and 192) == 128) 3 else 2;
      }
   } else {
      val b0: Byte = `$this$process4Utf8Bytes`[beginIndex];
      val b1: Byte = `$this$process4Utf8Bytes`[beginIndex + 1];
      if ((`$this$process4Utf8Bytes`[beginIndex + 1] and 192) != 128) {
         yield.invoke(65533);
         return 1;
      } else {
         val var17: Byte = `$this$process4Utf8Bytes`[beginIndex + 2];
         if ((`$this$process4Utf8Bytes`[beginIndex + 2] and 192) != 128) {
            yield.invoke(65533);
            return 2;
         } else {
            val var18: Byte = `$this$process4Utf8Bytes`[beginIndex + 3];
            if ((`$this$process4Utf8Bytes`[beginIndex + 3] and 192) != 128) {
               yield.invoke(65533);
               return 3;
            } else {
               val var22: Int = 3678080 xor var18 xor var17 shl 6 xor b1 shl 12 xor b0 shl 18;
               if ((3678080 xor var18 xor var17 shl 6 xor b1 shl 12 xor b0 shl 18) > 1114111) {
                  yield.invoke(65533);
               } else if (55296 <= var22 && var22 < 57344) {
                  yield.invoke(65533);
               } else if (var22 < 65536) {
                  yield.invoke(65533);
               } else {
                  yield.invoke(var22);
               }

               return 4;
            }
         }
      }
   }
}
