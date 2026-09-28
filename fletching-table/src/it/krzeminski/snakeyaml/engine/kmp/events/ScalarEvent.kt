package it.krzeminski.snakeyaml.engine.kmp.events

import it.krzeminski.snakeyaml.engine.kmp.common.Anchor
import it.krzeminski.snakeyaml.engine.kmp.common.CharConstants
import it.krzeminski.snakeyaml.engine.kmp.common.ScalarStyle
import it.krzeminski.snakeyaml.engine.kmp.events.Event.ID
import it.krzeminski.snakeyaml.engine.kmp.exceptions.Mark
import it.krzeminski.snakeyaml.engine.kmp.internal.utils.CharSequenceExtensionsKt
import java.util.ArrayList
import kotlin.jvm.internal.SourceDebugExtension

@SourceDebugExtension(["SMAP\nScalarEvent.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ScalarEvent.kt\nit/krzeminski/snakeyaml/engine/kmp/events/ScalarEvent\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,98:1\n774#2:99\n865#2,2:100\n*S KotlinDebug\n*F\n+ 1 ScalarEvent.kt\nit/krzeminski/snakeyaml/engine/kmp/events/ScalarEvent\n*L\n92#1:99\n92#1:100,2\n*E\n"])
public class ScalarEvent @JvmOverloads  public constructor(anchor: Anchor?,
   tag: String?,
   implicit: ImplicitTuple,
   value: String,
   scalarStyle: ScalarStyle,
   startMark: Mark? = null,
   endMark: Mark? = null
) : NodeEvent(anchor, startMark, endMark) {
   public final val tag: String?
   public final val implicit: ImplicitTuple
   public final val value: String
   public final val scalarStyle: ScalarStyle

   public open val eventId: ID
      public open get() {
         return Event.ID.Scalar;
      }


   public final val plain: Boolean
      public final get() {
         return this.scalarStyle === ScalarStyle.PLAIN;
      }


   public final val literal: Boolean
      public final get() {
         return this.scalarStyle === ScalarStyle.LITERAL;
      }


   public final val sQuoted: Boolean
      public final get() {
         return this.scalarStyle === ScalarStyle.SINGLE_QUOTED;
      }


   public final val dQuoted: Boolean
      public final get() {
         return this.scalarStyle === ScalarStyle.DOUBLE_QUOTED;
      }


   public final val folded: Boolean
      public final get() {
         return this.scalarStyle === ScalarStyle.FOLDED;
      }


   public final val json: Boolean
      public final get() {
         return this.scalarStyle === ScalarStyle.JSON_SCALAR_STYLE;
      }


   init {
      this.tag = tag;
      this.implicit = implicit;
      this.value = value;
      this.scalarStyle = scalarStyle;
   }

   public override fun toString(): String {
      val var1: StringBuilder = new StringBuilder();
      var1.append("=VAL");
      if (this.getAnchor() != null) {
         var1.append(" &${this.getAnchor()}");
      }

      if (this.implicit.bothFalse() && this.tag != null) {
         var1.append(" <${this.tag}>");
      }

      var1.append(" ");
      var1.append(this.scalarStyle.toString());
      var1.append(this.escapedValue());
      return var1.toString();
   }

   public fun escapedValue(): String {
      val `$this$filter$iv`: java.lang.Iterable = CharSequenceExtensionsKt.toCodePoints(this.value);
      val `destination$iv$iv`: java.util.Collection = new ArrayList();

      for (Object element$iv$iv : $this$filter$iv) {
         if ((`element$iv$iv` as java.lang.Number).intValue() < 65535) {
            `destination$iv$iv`.add(`element$iv$iv`);
         }
      }

      return CollectionsKt.joinToString$default(`destination$iv$iv` as java.util.List, "", null, null, 0, null, ScalarEvent::escapedValue$lambda$2, 30, null);
   }

   @JvmOverloads
   fun ScalarEvent(anchor: Anchor?, tag: java.lang.String?, implicit: ImplicitTuple, value: java.lang.String, scalarStyle: ScalarStyle, startMark: Mark?) {
      this(anchor, tag, implicit, value, scalarStyle, startMark, null, 64, null);
   }

   @JvmOverloads
   fun ScalarEvent(anchor: Anchor?, tag: java.lang.String?, implicit: ImplicitTuple, value: java.lang.String, scalarStyle: ScalarStyle) {
      this(anchor, tag, implicit, value, scalarStyle, null, null, 96, null);
   }

   @JvmStatic
   fun `escapedValue$lambda$2`(ch: Int): java.lang.CharSequence {
      return CharConstants.Companion.escapeChar((char)ch);
   }
}
