package it.krzeminski.snakeyaml.engine.kmp.nodes

import it.krzeminski.snakeyaml.engine.kmp.common.ScalarStyle
import it.krzeminski.snakeyaml.engine.kmp.exceptions.Mark

public class ScalarNode @JvmOverloads  public constructor(tag: Tag,
   value: String,
   scalarStyle: ScalarStyle,
   resolved: Boolean = true,
   startMark: Mark? = null,
   endMark: Mark? = null
) : Node(tag, startMark, endMark, resolved) {
   public final val value: String
   public final val scalarStyle: ScalarStyle

   public open val nodeType: NodeType
      public open get() {
         return NodeType.SCALAR;
      }


   public final val isPlain: Boolean
      public final get() {
         return this.scalarStyle === ScalarStyle.PLAIN;
      }


   init {
      this.value = value;
      this.scalarStyle = scalarStyle;
   }

   public override fun toString(): String {
      return "<${(this.getClass()::class).getSimpleName()} (tag=${this.getTag()}, value=${this.value})>";
   }

   @JvmOverloads
   fun ScalarNode(tag: Tag, value: java.lang.String, scalarStyle: ScalarStyle, resolved: Boolean, startMark: Mark?) {
      this(tag, value, scalarStyle, resolved, startMark, null, 32, null);
   }

   @JvmOverloads
   fun ScalarNode(tag: Tag, value: java.lang.String, scalarStyle: ScalarStyle, resolved: Boolean) {
      this(tag, value, scalarStyle, resolved, null, null, 48, null);
   }

   @JvmOverloads
   fun ScalarNode(tag: Tag, value: java.lang.String, scalarStyle: ScalarStyle) {
      this(tag, value, scalarStyle, false, null, null, 56, null);
   }
}
