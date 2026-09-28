package net.peanuuutz.tomlkt.internal.emitter

import java.util.Map.Entry
import kotlin.jvm.internal.SourceDebugExtension
import net.peanuuutz.tomlkt.TomlElement
import net.peanuuutz.tomlkt.TomlInteger
import net.peanuuutz.tomlkt.TomlLiteralString
import net.peanuuutz.tomlkt.TomlNull
import net.peanuuutz.tomlkt.TomlTable

@SourceDebugExtension(["SMAP\nTomlElementEmitter.kt\nKotlin\n*S Kotlin\n*F\n+ 1 TomlElementEmitter.kt\nnet/peanuuutz/tomlkt/internal/emitter/TomlInlineTableEmitter\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,690:1\n1878#2,3:691\n*S KotlinDebug\n*F\n+ 1 TomlElementEmitter.kt\nnet/peanuuutz/tomlkt/internal/emitter/TomlInlineTableEmitter\n*L\n214#1:691,3\n*E\n"])
private class TomlInlineTableEmitter(delegate: AbstractTomlElementEmitter) : AbstractTomlElementEmitter(delegate.getToml(), delegate.getWriter()) {
   public override fun emitTable(table: TomlTable) {
      this.getWriter().startInlineTable();
      this.getWriter().writeSpace();
      val explicitNulls: Boolean = this.getToml().getConfig().getExplicitNulls();
      val annotations: java.util.Map = table.getAnnotations();
      val lastIndex: Int = table.size() - 1;
      val `$this$forEachIndexed$iv`: java.lang.Iterable = table.entrySet();
      var `index$iv`: Int = 0;

      for (Object item$iv : $this$forEachIndexed$iv) {
         val var10: Int = `index$iv`++;
         if (var10 < 0) {
            CollectionsKt.throwIndexOverflow();
         }

         val key: java.lang.String = (`item$iv` as Entry).getKey() as java.lang.String;
         val element: TomlElement = (`item$iv` as Entry).getValue() as TomlElement;
         if (element !is TomlNull || explicitNulls) {
            val elementAnnotations: java.util.List = annotations.get(key) as java.util.List;
            if (elementAnnotations != null) {
               this.processAnnotations(elementAnnotations);
            }

            TomlElementEmitterKt.access$startEntry(this.getWriter(), key);
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
      this.getWriter().endInlineTable();
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
