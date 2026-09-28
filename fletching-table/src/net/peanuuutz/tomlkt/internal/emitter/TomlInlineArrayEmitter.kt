package net.peanuuutz.tomlkt.internal.emitter

import kotlin.jvm.internal.SourceDebugExtension
import net.peanuuutz.tomlkt.TomlArray
import net.peanuuutz.tomlkt.TomlElement
import net.peanuuutz.tomlkt.TomlInteger
import net.peanuuutz.tomlkt.TomlLiteralString
import net.peanuuutz.tomlkt.TomlNull

@SourceDebugExtension(["SMAP\nTomlElementEmitter.kt\nKotlin\n*S Kotlin\n*F\n+ 1 TomlElementEmitter.kt\nnet/peanuuutz/tomlkt/internal/emitter/TomlInlineArrayEmitter\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,690:1\n1878#2,3:691\n*S KotlinDebug\n*F\n+ 1 TomlElementEmitter.kt\nnet/peanuuutz/tomlkt/internal/emitter/TomlInlineArrayEmitter\n*L\n266#1:691,3\n*E\n"])
private class TomlInlineArrayEmitter(delegate: AbstractTomlElementEmitter) : AbstractTomlElementEmitter(delegate.getToml(), delegate.getWriter()) {
   public override fun emitArray(array: TomlArray) {
      this.getWriter().startArray();
      this.getWriter().writeSpace();
      val explicitNulls: Boolean = this.getToml().getConfig().getExplicitNulls();
      val annotations: java.util.List = array.getAnnotations();
      val lastIndex: Int = array.size() - 1;
      val `$this$forEachIndexed$iv`: java.lang.Iterable = array;
      var `index$iv`: Int = 0;

      for (Object item$iv : $this$forEachIndexed$iv) {
         val var10: Int = `index$iv`++;
         if (var10 < 0) {
            CollectionsKt.throwIndexOverflow();
         }

         val element: TomlElement = `item$iv` as TomlElement;
         if ((`item$iv` as TomlElement) !is TomlNull || explicitNulls) {
            val elementAnnotations: java.util.List = CollectionsKt.getOrNull(annotations, var10);
            if (elementAnnotations != null) {
               this.processAnnotations(elementAnnotations);
            }

            this.emitElement(element);
            this.setStringLiteral(false);
            this.setIntegerRepresentation(null);
            if (var10 < lastIndex) {
               this.getWriter().writeElementSeparator();
               this.getWriter().writeSpace();
            }
         }
      }

      this.getWriter().writeSpace();
      this.getWriter().endArray();
   }

   private fun processAnnotations(annotations: List<Annotation>) {
      for (java.lang.annotation.Annotation annotation : annotations) {
         if (annotation is TomlLiteralString) {
            this.setStringLiteral(true);
         } else if (annotation is TomlInteger) {
            this.setIntegerRepresentation(annotation as TomlInteger);
         }
      }
   }
}
