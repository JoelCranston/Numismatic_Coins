package it.krzeminski.snakeyaml.engine.kmp.nodes

import it.krzeminski.snakeyaml.engine.kmp.comments.CommentLine
import it.krzeminski.snakeyaml.engine.kmp.common.Anchor
import it.krzeminski.snakeyaml.engine.kmp.exceptions.Mark
import java.util.HashMap

public sealed class Node @JvmOverloads  protected constructor(tag: Tag, startMark: Mark?, endMark: Mark?, resolved: Boolean = true) {
   public final var tag: Tag
      internal set

   public final val startMark: Mark?

   public final var endMark: Mark?
      private set

   protected final var resolved: Boolean
      private set

   public final var isRecursive: Boolean
      internal set

   public final var anchor: Anchor?
      internal set

   public final var inLineComments: List<CommentLine>?
      internal set

   public final var blockComments: List<CommentLine>?
      internal set

   public final var endComments: List<CommentLine>?
      internal set

   private final var properties: MutableMap<String, Any>?
   public abstract val nodeType: NodeType

   init {
      this.tag = tag;
      this.startMark = startMark;
      this.endMark = endMark;
      this.resolved = resolved;
   }

   public fun setProperty(key: String, value: Any): Any? {
      if (this.properties == null) {
         this.properties = new HashMap<>();
      }

      val var10000: java.util.Map = this.properties;
      return var10000.put(key, value);
   }

   public fun getProperty(key: String): Any? {
      return if (this.properties != null) this.properties.get(key) else null;
   }

   public fun isResolved(): Boolean {
      return this.resolved;
   }

   @JvmOverloads
   fun Node(tag: Tag, startMark: Mark, endMark: Mark) {
      this(tag, startMark, endMark, false, 8, null);
   }
}
