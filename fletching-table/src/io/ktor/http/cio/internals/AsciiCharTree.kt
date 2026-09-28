package io.ktor.http.cio.internals

import java.util.ArrayList
import java.util.LinkedHashMap
import java.util.NoSuchElementException
import java.util.Map.Entry
import kotlin.jvm.internal.SourceDebugExtension

internal class AsciiCharTree<T>(root: io.ktor.http.cio.internals.AsciiCharTree.Node<Any>) {
   public final val root: io.ktor.http.cio.internals.AsciiCharTree.Node<Any>

   init {
      this.root = root;
   }

   public fun search(sequence: CharSequence, fromIdx: Int = 0, end: Int = sequence.length(), lowerCase: Boolean = false, stopPredicate: (Char, Int) -> Boolean): List<
         Any
      > {
      if (sequence.length() == 0) {
         throw new IllegalArgumentException("Couldn't search in char tree for empty string");
      } else {
         var node: AsciiCharTree.Node = this.root;

         for (int index = fromIdx; index < end; index++) {
            val current: Char = sequence.charAt(index);
            if (stopPredicate.invoke(current, Integer.valueOf(current)) as java.lang.Boolean) {
               break;
            }

            var var10000: AsciiCharTree.Node = node.getArray()[current];
            if (var10000 == null) {
               var10000 = if (lowerCase) node.getArray()[Character.toLowerCase(current)] else null;
               if (var10000 == null) {
                  return CollectionsKt.emptyList();
               }
            }

            node = var10000;
         }

         return node.getExact();
      }
   }

   @SourceDebugExtension(["SMAP\nAsciiCharTree.kt\nKotlin\n*S Kotlin\n*F\n+ 1 AsciiCharTree.kt\nio/ktor/http/cio/internals/AsciiCharTree$Companion\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n+ 4 _Maps.kt\nkotlin/collections/MapsKt___MapsKt\n*L\n1#1,73:1\n1999#2,14:74\n1761#2,3:88\n1491#2:91\n1516#2,3:92\n1519#2,3:102\n774#2:106\n865#2,2:107\n774#2:109\n865#2,2:110\n382#3,7:95\n216#4:105\n217#4:112\n*S KotlinDebug\n*F\n+ 1 AsciiCharTree.kt\nio/ktor/http/cio/internals/AsciiCharTree$Companion\n*L\n44#1:74,14\n47#1:88,3\n63#1:91\n63#1:92,3\n63#1:102,3\n66#1:106\n66#1:107,2\n68#1:109\n68#1:110,2\n63#1:95,7\n63#1:105\n63#1:112\n*E\n"])
   public companion object {
      public fun <T : CharSequence> build(from: List<Any>): AsciiCharTree<Any> {
         return this.build(from, AsciiCharTree.Companion::build$lambda$0, AsciiCharTree.Companion::build$lambda$1);
      }

      public fun <T : Any> build(from: List<Any>, length: (Any) -> Int, charAt: (Any, Int) -> Char): AsciiCharTree<Any> {
         val `element$iv`: java.util.Iterator = from.iterator();
         val var10000: Any;
         if (!`element$iv`.hasNext()) {
            var10000 = null;
         } else {
            var it: Any = `element$iv`.next();
            if (!`element$iv`.hasNext()) {
               var10000 = it;
            } else {
               var var10: java.lang.Comparable = length.invoke(it) as java.lang.Comparable;

               do {
                  val `e$iv`: Any = `element$iv`.next();
                  val `v$iv`: java.lang.Comparable = length.invoke(`e$iv`) as java.lang.Comparable;
                  if (var10.compareTo(`v$iv`) < 0) {
                     it = `e$iv`;
                     var10 = `v$iv`;
                  }
               } while (iterator$iv.hasNext());

               var10000 = it;
            }
         }

         if (var10000 == null) {
            throw new NoSuchElementException("Unable to build char tree from an empty list");
         } else {
            val maxLen: Int = (length.invoke(var10000) as java.lang.Number).intValue();
            val root: java.lang.Iterable = from;
            var var18: Boolean;
            if (from is java.util.Collection && (from as java.util.Collection).isEmpty()) {
               var18 = false;
            } else {
               label65: {
                  for (Object element$ivx : root) {
                     if ((length.invoke(`element$ivx`) as java.lang.Number).intValue() == 0) {
                        var18 = true;
                        break label65;
                     }
                  }

                  var18 = false;
               }
            }

            if (var18) {
               throw new IllegalArgumentException("There should be no empty entries");
            } else {
               val var13: ArrayList = new ArrayList();
               this.build(var13, from, maxLen, 0, length, charAt);
               var13.trimToSize();
               return new AsciiCharTree<>(new AsciiCharTree.Node<>('\u0000', CollectionsKt.emptyList(), var13));
            }
         }
      }

      private fun <T : Any> build(
         resultList: MutableList<io.ktor.http.cio.internals.AsciiCharTree.Node<Any>>,
         from: List<Any>,
         maxLength: Int,
         idx: Int,
         length: (Any) -> Int,
         charAt: (Any, Int) -> Char
      ) {
         val `$this$forEach$iv`: java.lang.Iterable = from;
         val `element$iv`: java.util.Map = new LinkedHashMap();

         for (Object element$iv$iv : $this$groupBy$iv) {
            val children: Any = charAt.invoke(ch, idx) as Character;
            val `$i$f$filter`: Any = `element$iv`.get(children);
            val var10000: Any;
            if (`$i$f$filter` == null) {
               val var44: Any = new ArrayList();
               `element$iv`.put(children, var44);
               var10000 = var44;
            } else {
               var10000 = `$i$f$filter`;
            }

            (var10000 as java.util.List).add(ch);
         }

         for (Entry element$ivx : destination$iv$iv.entrySet()) {
            val var37: Char = `element$ivx`.getKey() as Character;
            val list: java.util.List = `element$ivx`.getValue() as java.util.List;
            val var38: Int = idx + 1;
            val var39: ArrayList = new ArrayList();
            val var10001: java.util.List = var39;
            var `$this$filter$iv`: java.lang.Iterable = list;
            val var41: AsciiCharTree.Companion = AsciiCharTree.Companion;
            var `destination$iv$ivx`: java.util.Collection = new ArrayList();

            for (Object element$iv$iv : $this$filter$iv) {
               if ((length.invoke(`element$iv$iv`) as java.lang.Number).intValue() > var38) {
                  `destination$iv$ivx`.add(`element$iv$iv`);
               }
            }

            var41.build(var10001, `destination$iv$ivx` as MutableList<T>, maxLength, var38, length, charAt);
            var39.trimToSize();
            `$this$filter$iv` = list;
            `destination$iv$ivx` = new ArrayList();

            for (Object element$iv$ivx : $this$filter$iv) {
               if ((length.invoke(`element$iv$ivx`) as java.lang.Number).intValue() == var38) {
                  `destination$iv$ivx`.add(`element$iv$ivx`);
               }
            }

            resultList.add(new AsciiCharTree.Node(var37, `destination$iv$ivx` as MutableList<T>, var39));
         }
      }

      @JvmStatic
      fun `build$lambda$0`(it: java.lang.CharSequence): Int {
         return it.length();
      }

      @JvmStatic
      fun `build$lambda$1`(s: java.lang.CharSequence, idx: Int): Char {
         return s.charAt(idx);
      }
   }

   @SourceDebugExtension(["SMAP\nAsciiCharTree.kt\nKotlin\n*S Kotlin\n*F\n+ 1 AsciiCharTree.kt\nio/ktor/http/cio/internals/AsciiCharTree$Node\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,73:1\n669#2,11:74\n*S KotlinDebug\n*F\n+ 1 AsciiCharTree.kt\nio/ktor/http/cio/internals/AsciiCharTree$Node\n*L\n9#1:74,11\n*E\n"])
   public class Node<T>(ch: Char, exact: List<Any>, children: List<io.ktor.http.cio.internals.AsciiCharTree.Node<Any>>) {
      public final val ch: Char
      public final val exact: List<Any>
      public final val children: List<io.ktor.http.cio.internals.AsciiCharTree.Node<Any>>
      public final val array: Array<io.ktor.http.cio.internals.AsciiCharTree.Node<Any>?>

      init {
         this.ch = ch;
         this.exact = exact;
         this.children = children;
         var var4: Int = 0;

         val var5: Array<AsciiCharTree.Node>;
         for (var5 = new AsciiCharTree.Node[256]; var4 < 256; var4++) {
            val var6: Int = var4;
            val `$this$singleOrNull$iv`: java.lang.Iterable = this.children;
            var `single$iv`: Any = null;
            var `found$iv`: Boolean = false;

            var var10000: Any;
            label33: {
               for (Object element$iv : $this$singleOrNull$iv) {
                  if ((`element$iv` as AsciiCharTree.Node).ch == var6) {
                     if (`found$iv`) {
                        var10000 = null;
                        break label33;
                     }

                     `single$iv` = `element$iv`;
                     `found$iv` = true;
                  }
               }

               var10000 = if (!`found$iv`) null else `single$iv`;
            }

            var5[var4] = (AsciiCharTree.Node)var10000;
         }

         this.array = var5;
      }
   }
}
