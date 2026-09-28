package net.peanuuutz.tomlkt.internal.emitter

import java.util.ArrayList
import kotlin.jvm.internal.SourceDebugExtension
import net.peanuuutz.tomlkt.TomlArray
import net.peanuuutz.tomlkt.TomlComment
import net.peanuuutz.tomlkt.TomlElement
import net.peanuuutz.tomlkt.TomlInteger
import net.peanuuutz.tomlkt.TomlLiteralString
import net.peanuuutz.tomlkt.TomlNull
import net.peanuuutz.tomlkt.internal.StringUtilsKt

@SourceDebugExtension(["SMAP\nTomlElementEmitter.kt\nKotlin\n*S Kotlin\n*F\n+ 1 TomlElementEmitter.kt\nnet/peanuuutz/tomlkt/internal/emitter/TomlBlockArrayEmitter\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,690:1\n1878#2,3:691\n1563#2:694\n1634#2,3:695\n*S KotlinDebug\n*F\n+ 1 TomlElementEmitter.kt\nnet/peanuuutz/tomlkt/internal/emitter/TomlBlockArrayEmitter\n*L\n321#1:691,3\n380#1:694\n380#1:695,3\n*E\n"])
private class TomlBlockArrayEmitter(delegate: AbstractTomlElementEmitter, itemsPerLine: Int) : AbstractTomlElementEmitter(
      delegate.getToml(), delegate.getWriter()
   ) {
   private final val itemsPerLine: Int

   init {
      this.itemsPerLine = itemsPerLine;
   }

   public override fun emitArray(array: TomlArray) {
      this.getWriter().startArray();
      this.getWriter().writeLineFeed();
      val explicitNulls: Boolean = this.getToml().getConfig().getExplicitNulls();
      val indentation: java.lang.String = this.getToml().getConfig().getIndentation-o8wLciY();
      val itemsPerLine: Int = this.itemsPerLine;
      val annotations: java.util.List = array.getAnnotations();
      val lastIndex: Int = array.size() - 1;
      var currentLineItemCount: Int = 0;
      val `$this$forEachIndexed$iv`: java.lang.Iterable = array;
      var `index$iv`: Int = 0;

      for (Object item$iv : $this$forEachIndexed$iv) {
         val var13: Int = `index$iv`++;
         if (var13 < 0) {
            CollectionsKt.throwIndexOverflow();
         }

         val element: TomlElement = `item$iv` as TomlElement;
         if ((`item$iv` as TomlElement) !is TomlNull || explicitNulls) {
            val elementAnnotations: java.util.List = CollectionsKt.getOrNull(annotations, var13);
            if (elementAnnotations != null) {
               this.processAnnotations(elementAnnotations);
            }

            if (currentLineItemCount == 0) {
               this.getWriter().writeIndentation-bsYWt4I(indentation);
            } else {
               this.getWriter().writeSpace();
            }

            this.emitElement(element);
            this.setStringLiteral(false);
            this.setIntegerRepresentation(null);
            if (var13 < lastIndex) {
               this.getWriter().writeElementSeparator();
            }

            if (++currentLineItemCount >= itemsPerLine) {
               this.getWriter().writeLineFeed();
               currentLineItemCount = 0;
            }
         }
      }

      if (currentLineItemCount != 0) {
         this.getWriter().writeLineFeed();
      }

      this.getWriter().endArray();
   }

   private fun processAnnotations(annotations: List<Annotation>) {
      var comment: TomlComment = null;

      for (java.lang.annotation.Annotation annotation : annotations) {
         if (annotation is TomlComment) {
            comment = annotation as TomlComment;
         } else if (annotation is TomlLiteralString) {
            this.setStringLiteral(true);
         } else if (annotation is TomlInteger) {
            this.setIntegerRepresentation(annotation as TomlInteger);
         }
      }

      if (comment != null && this.itemsPerLine == 1) {
         this.emitComment(comment);
      }
   }

   private fun emitComment(comment: TomlComment) {
      val indentation: java.lang.String = this.getToml().getConfig().getIndentation-o8wLciY();
      val var14: java.lang.Iterable = StringsKt.split$default(StringsKt.trimIndent(comment.text()), new char[]{'\n'}, false, 0, 6, null);
      val `destination$iv$iv`: java.util.Collection = new ArrayList(CollectionsKt.collectionSizeOrDefault(var14, 10));

      for (Object item$iv$iv : $this$map$iv) {
         `destination$iv$iv`.add(StringUtilsKt.escape$default(`item$iv$iv` as java.lang.String, false, 1, null));
      }

      for (java.lang.String line : (java.util.List)destination$iv$iv) {
         this.getWriter().writeIndentation-bsYWt4I(indentation);
         this.getWriter().startComment();
         this.getWriter().writeSpace();
         this.getWriter().writeString(var16);
         this.getWriter().writeLineFeed();
      }
   }
}
