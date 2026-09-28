package net.peanuuutz.tomlkt.internal.emitter

import java.util.ArrayList
import java.util.LinkedHashMap
import java.util.Map.Entry
import kotlin.jvm.internal.SourceDebugExtension
import net.peanuuutz.tomlkt.TomlArray
import net.peanuuutz.tomlkt.TomlBlockArray
import net.peanuuutz.tomlkt.TomlComment
import net.peanuuutz.tomlkt.TomlElement
import net.peanuuutz.tomlkt.TomlInline
import net.peanuuutz.tomlkt.TomlInteger
import net.peanuuutz.tomlkt.TomlLiteralString
import net.peanuuutz.tomlkt.TomlMultilineString
import net.peanuuutz.tomlkt.TomlNull
import net.peanuuutz.tomlkt.TomlTable
import net.peanuuutz.tomlkt.internal.StringUtilsKt

@SourceDebugExtension(["SMAP\nTomlElementEmitter.kt\nKotlin\n*S Kotlin\n*F\n+ 1 TomlElementEmitter.kt\nnet/peanuuutz/tomlkt/internal/emitter/TomlTableEmitter\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,690:1\n1878#2,3:691\n1563#2:694\n1634#2,3:695\n1740#2,3:698\n2746#2,3:701\n1878#2,3:704\n1878#2,3:707\n*S KotlinDebug\n*F\n+ 1 TomlElementEmitter.kt\nnet/peanuuutz/tomlkt/internal/emitter/TomlTableEmitter\n*L\n424#1:691,3\n495#1:694\n495#1:695,3\n519#1:698,3\n520#1:701,3\n559#1:704,3\n585#1:707,3\n*E\n"])
private class TomlTableEmitter(delegate: AbstractTomlElementEmitter, path: List<String>) : AbstractTomlElementEmitter(delegate.getToml(), delegate.getWriter()) {
   private final val path: List<String>
   private final var comment: TomlComment?

   init {
      this.path = path;
   }

   public override fun createArrayEmitter(array: TomlArray): AbstractTomlElementEmitter {
      val var10000: AbstractTomlElementEmitter;
      if (!this.isInline() && !array.isEmpty()) {
         val var3: TomlBlockArray = this.getBlockArray();
         var10000 = new TomlBlockArrayEmitter(this, if (var3 != null) var3.itemsPerLine() else this.getToml().getConfig().getItemsPerLineInBlockArray());
      } else {
         var10000 = new TomlInlineArrayEmitter(this);
      }

      return var10000;
   }

   public override fun createTableEmitter(table: TomlTable): AbstractTomlElementEmitter {
      return new TomlInlineTableEmitter(this);
   }

   public override fun emitTable(table: TomlTable) {
      val explicitNulls: Boolean = this.getToml().getConfig().getExplicitNulls();
      val annotations: java.util.Map = table.getAnnotations();
      val remainingComments: java.util.Map = new LinkedHashMap();
      val tables: java.util.Map = new LinkedHashMap();
      val arrayOfTables: java.util.Map = new LinkedHashMap();
      var hasNormalEntry: Boolean = false;
      val `$this$forEachIndexed$iv`: java.lang.Iterable = table.entrySet();
      val `index$iv`: Int = 0;

      for (Object item$iv : $this$forEachIndexed$iv) {
         if (`index$iv`++ < 0) {
            CollectionsKt.throwIndexOverflow();
         }

         val key: java.lang.String = (`item$iv` as Entry).getKey() as java.lang.String;
         val element: TomlElement = (`item$iv` as Entry).getValue() as TomlElement;
         if (element !is TomlNull || explicitNulls) {
            val elementAnnotations: java.util.List = annotations.get(key) as java.util.List;
            if (elementAnnotations != null) {
               this.processAnnotations(elementAnnotations);
            }

            val emitted: Boolean = this.tryEmitEntry(key, element, remainingComments, tables, arrayOfTables, hasNormalEntry);
            this.comment = null;
            this.setInline(false);
            this.setBlockArray(null);
            this.setStringMultiline(false);
            this.setStringLiteral(false);
            this.setIntegerRepresentation(null);
            if (!hasNormalEntry) {
               hasNormalEntry = emitted;
            }
         }
      }

      if (!tables.isEmpty()) {
         if (hasNormalEntry) {
            this.getWriter().writeLineFeed();
         }

         this.emitInnerTables(remainingComments, tables);
      }

      if (!arrayOfTables.isEmpty()) {
         if (hasNormalEntry || !tables.isEmpty()) {
            this.getWriter().writeLineFeed();
         }

         this.emitInnerArrayOfTables(remainingComments, arrayOfTables);
      }
   }

   private fun processAnnotations(annotations: List<Annotation>) {
      for (java.lang.annotation.Annotation annotation : annotations) {
         if (annotation is TomlComment) {
            this.comment = annotation as TomlComment;
         } else if (annotation is TomlInline) {
            this.setInline(true);
         } else if (annotation is TomlBlockArray) {
            this.setBlockArray(annotation as TomlBlockArray);
         } else if (annotation is TomlMultilineString) {
            this.setStringMultiline(true);
         } else if (annotation is TomlLiteralString) {
            this.setStringLiteral(true);
         } else if (annotation is TomlInteger) {
            this.setIntegerRepresentation(annotation as TomlInteger);
         }
      }
   }

   private fun emitComment(comment: TomlComment) {
      val var13: java.lang.Iterable = StringsKt.split$default(StringsKt.trimIndent(comment.text()), new char[]{'\n'}, false, 0, 6, null);
      val `destination$iv$iv`: java.util.Collection = new ArrayList(CollectionsKt.collectionSizeOrDefault(var13, 10));

      for (Object item$iv$iv : $this$map$iv) {
         `destination$iv$iv`.add(StringUtilsKt.escape$default(`item$iv$iv` as java.lang.String, false, 1, null));
      }

      for (java.lang.String line : (java.util.List)destination$iv$iv) {
         this.getWriter().startComment();
         this.getWriter().writeSpace();
         this.getWriter().writeString(var15);
         this.getWriter().writeLineFeed();
      }
   }

   private fun tryEmitEntry(
      key: String,
      element: TomlElement,
      remainingComments: MutableMap<String, TomlComment>,
      tables: MutableMap<String, TomlTable>,
      arrayOfTables: MutableMap<String, TomlArray>,
      hasNormalEntry: Boolean
   ): Boolean {
      val comment: TomlComment = this.comment;
      if (element is TomlArray) {
         var var24: Boolean;
         label91: {
            if (!this.isInline() && this.getBlockArray() == null && !(element as java.util.Collection).isEmpty()) {
               var `$this$none$iv`: java.lang.Iterable = element as java.lang.Iterable;
               if (element as java.lang.Iterable is java.util.Collection && ((element as java.lang.Iterable) as java.util.Collection).isEmpty()) {
                  var24 = true;
               } else {
                  val var12: java.util.Iterator = `$this$none$iv`.iterator();

                  while (true) {
                     if (!var12.hasNext()) {
                        var24 = true;
                        break;
                     }

                     if ((var12.next() as TomlElement) !is TomlTable) {
                        var24 = false;
                        break;
                     }
                  }
               }

               if (var24) {
                  `$this$none$iv` = CollectionsKt.flatten((element as TomlArray).getAnnotations());
                  if (`$this$none$iv` is java.util.Collection && (`$this$none$iv` as java.util.Collection).isEmpty()) {
                     var24 = true;
                  } else {
                     val var19: java.util.Iterator = `$this$none$iv`.iterator();

                     while (true) {
                        if (!var19.hasNext()) {
                           var24 = true;
                           break;
                        }

                        if (var19.next() as java.lang.annotation.Annotation is TomlInline) {
                           var24 = false;
                           break;
                        }
                     }
                  }

                  if (var24) {
                     var24 = true;
                     break label91;
                  }
               }
            }

            var24 = false;
         }

         if (var24) {
            arrayOfTables.put(key, element);
            if (comment != null) {
               remainingComments.put(key, comment);
            }

            return false;
         }
      } else if (element is TomlTable && !this.isInline() && !(element as java.util.Map).isEmpty()) {
         tables.put(key, element);
         if (comment != null) {
            remainingComments.put(key, comment);
         }

         return false;
      }

      if (hasNormalEntry) {
         this.getWriter().writeLineFeed();
      }

      if (this.comment != null) {
         this.emitComment(this.comment);
      }

      TomlElementEmitterKt.access$startEntry(this.getWriter(), key);
      this.emitElement(element);
      return true;
   }

   private fun emitInnerTables(remainingComments: Map<String, TomlComment>, tables: Map<String, TomlTable>) {
      val path: java.util.List = this.path;
      val lastIndex: Int = tables.size() - 1;
      val `$this$forEachIndexed$iv`: java.lang.Iterable = tables.entrySet();
      var `index$iv`: Int = 0;

      for (Object item$iv : $this$forEachIndexed$iv) {
         val var10: Int = `index$iv`++;
         if (var10 < 0) {
            CollectionsKt.throwIndexOverflow();
         }

         val key: java.lang.String = (`item$iv` as Entry).getKey() as java.lang.String;
         val table: TomlTable = (`item$iv` as Entry).getValue() as TomlTable;
         val comment: TomlComment = remainingComments.get(key) as TomlComment;
         if (comment != null) {
            this.getWriter().writeLineFeed();
            this.emitComment(comment);
         }

         val innerPath: java.util.List = CollectionsKt.plus(path, key);
         this.getWriter().writeLineFeed();
         TomlElementEmitterKt.access$writeRegularTableHead(this.getWriter(), innerPath);
         this.getWriter().writeLineFeed();
         new TomlTableEmitter(this, innerPath).emitTable(table);
         if (var10 < lastIndex) {
            this.getWriter().writeLineFeed();
         }
      }
   }

   private fun emitInnerArrayOfTables(remainingComments: Map<String, TomlComment>, arrayOfTables: Map<String, TomlArray>) {
      val path: java.util.List = this.path;
      val lastIndex: Int = arrayOfTables.size() - 1;
      val `$this$forEachIndexed$iv`: java.lang.Iterable = arrayOfTables.entrySet();
      var `index$iv`: Int = 0;

      for (Object item$iv : $this$forEachIndexed$iv) {
         val var10: Int = `index$iv`++;
         if (var10 < 0) {
            CollectionsKt.throwIndexOverflow();
         }

         val key: java.lang.String = (`item$iv` as Entry).getKey() as java.lang.String;
         val array: TomlArray = (`item$iv` as Entry).getValue() as TomlArray;
         val comment: TomlComment = remainingComments.get(key) as TomlComment;
         if (comment != null) {
            this.getWriter().writeLineFeed();
            this.emitComment(comment);
         }

         new TomlArrayOfTableEmitter(this, CollectionsKt.plus(path, key)).emitArray(array);
         if (var10 < lastIndex) {
            this.getWriter().writeLineFeed();
         }
      }
   }
}
