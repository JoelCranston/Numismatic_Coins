package net.peanuuutz.tomlkt.internal.emitter

import java.util.ArrayList
import kotlin.jvm.internal.SourceDebugExtension
import net.peanuuutz.tomlkt.TomlArray
import net.peanuuutz.tomlkt.TomlComment
import net.peanuuutz.tomlkt.TomlElement
import net.peanuuutz.tomlkt.TomlTable
import net.peanuuutz.tomlkt.internal.StringUtilsKt

@SourceDebugExtension(["SMAP\nTomlElementEmitter.kt\nKotlin\n*S Kotlin\n*F\n+ 1 TomlElementEmitter.kt\nnet/peanuuutz/tomlkt/internal/emitter/TomlArrayOfTableEmitter\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,690:1\n1878#2,3:691\n1563#2:694\n1634#2,3:695\n*S KotlinDebug\n*F\n+ 1 TomlElementEmitter.kt\nnet/peanuuutz/tomlkt/internal/emitter/TomlArrayOfTableEmitter\n*L\n617#1:691,3\n651#1:694\n651#1:695,3\n*E\n"])
private class TomlArrayOfTableEmitter(delegate: AbstractTomlElementEmitter, path: List<String>) : AbstractTomlElementEmitter(
      delegate.getToml(), delegate.getWriter()
   ) {
   private final val path: List<String>

   init {
      this.path = path;
   }

   public override fun createTableEmitter(table: TomlTable): AbstractTomlElementEmitter {
      return new TomlTableEmitter(this, this.path);
   }

   public override fun emitArray(array: TomlArray) {
      val path: java.util.List = this.path;
      val annotations: java.util.List = array.getAnnotations();
      val lastIndex: Int = array.size() - 1;
      val `$this$forEachIndexed$iv`: java.lang.Iterable = array;
      var `index$iv`: Int = 0;

      for (Object item$iv : $this$forEachIndexed$iv) {
         val var10: Int = `index$iv`++;
         if (var10 < 0) {
            CollectionsKt.throwIndexOverflow();
         }

         val table: TomlElement = `item$iv` as TomlElement;
         val elementAnnotations: java.util.List = CollectionsKt.getOrNull(annotations, var10);
         if (elementAnnotations != null) {
            this.processAnnotations(elementAnnotations);
         }

         this.getWriter().writeLineFeed();
         TomlElementEmitterKt.access$writeArrayOfTableHead(this.getWriter(), path);
         this.getWriter().writeLineFeed();
         this.createTableEmitter(table as TomlTable).emitTable(table as TomlTable);
         if (var10 < lastIndex) {
            this.getWriter().writeLineFeed();
         }
      }
   }

   private fun processAnnotations(annotations: List<Annotation>) {
      var comment: TomlComment = null;

      for (java.lang.annotation.Annotation annotation : annotations) {
         if (annotation is TomlComment) {
            comment = annotation as TomlComment;
         }
      }

      if (comment != null) {
         this.getWriter().writeLineFeed();
         this.emitComment(comment);
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
}
