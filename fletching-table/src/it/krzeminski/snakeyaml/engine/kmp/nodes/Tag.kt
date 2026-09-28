package it.krzeminski.snakeyaml.engine.kmp.nodes

import it.krzeminski.snakeyaml.engine.kmp.common.UriEncoder
import kotlin.jvm.internal.SourceDebugExtension

@SourceDebugExtension(["SMAP\nTag.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Tag.kt\nit/krzeminski/snakeyaml/engine/kmp/nodes/Tag\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 Strings.kt\nkotlin/text/StringsKt__StringsKt\n*L\n1#1,89:1\n1#2:90\n106#3:91\n78#3,22:92\n*S KotlinDebug\n*F\n+ 1 Tag.kt\nit/krzeminski/snakeyaml/engine/kmp/nodes/Tag\n*L\n25#1:91\n25#1:92,22\n*E\n"])
public class Tag(tag: String) {
   public final val value: String

   public constructor(prefix: String, tag: String) : this("$prefix${UriEncoder.encode(tag)}")
   public override fun toString(): String {
      return this.value;
   }

   public override operator fun equals(other: Any?): Boolean {
      return other is Tag && this.value == (other as Tag).value;
   }

   public override fun hashCode(): Int {
      return this.value.hashCode();
   }

   public companion object {
      public const val PREFIX: String
      public final val MERGE: Tag
      public final val SET: Tag
      public final val BINARY: Tag
      public final val INT: Tag
      public final val FLOAT: Tag
      public final val BOOL: Tag
      public final val NULL: Tag
      public final val STR: Tag
      public final val SEQ: Tag
      public final val MAP: Tag
      public final val COMMENT: Tag
      public final val ENV_TAG: Tag

      public fun forType(fqn: String): Tag {
         return new Tag("tag:yaml.org,2002:", fqn);
      }
   }
}
