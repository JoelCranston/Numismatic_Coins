package dev.kikugie.fletching_table.transformer.accessconverter

import java.util.ArrayList
import kotlin.jvm.internal.SourceDebugExtension

@SourceDebugExtension(["SMAP\nAccessWidener.kt\nKotlin\n*S Kotlin\n*F\n+ 1 AccessWidener.kt\ndev/kikugie/fletching_table/transformer/accessconverter/AccessWidener\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,48:1\n1563#2:49\n1634#2,3:50\n*S KotlinDebug\n*F\n+ 1 AccessWidener.kt\ndev/kikugie/fletching_table/transformer/accessconverter/AccessWidener\n*L\n7#1:49\n7#1:50,3\n*E\n"])
internal data class AccessWidener(header: dev.kikugie.fletching_table.transformer.accessconverter.AccessWidener.Header,
   entries: List<dev.kikugie.fletching_table.transformer.accessconverter.AccessWidener.Entry>
) {
   public final val header: dev.kikugie.fletching_table.transformer.accessconverter.AccessWidener.Header
   public final val entries: List<dev.kikugie.fletching_table.transformer.accessconverter.AccessWidener.Entry>

   init {
      this.header = header;
      this.entries = entries;
   }

   public fun convert(): AccessTransformer {
      val `$this$map$iv`: java.lang.Iterable = this.entries;
      val `destination$iv$iv`: java.util.Collection = new ArrayList(CollectionsKt.collectionSizeOrDefault(this.entries, 10));

      for (Object item$iv$iv : $this$map$iv) {
         `destination$iv$iv`.add((`item$iv$iv` as AccessWidener.Entry).convert());
      }

      return new AccessTransformer(`destination$iv$iv` as MutableList<AccessTransformer.Entry>);
   }

   public operator fun component1(): dev.kikugie.fletching_table.transformer.accessconverter.AccessWidener.Header {
      return this.header;
   }

   public operator fun component2(): List<dev.kikugie.fletching_table.transformer.accessconverter.AccessWidener.Entry> {
      return this.entries;
   }

   public fun copy(
      header: dev.kikugie.fletching_table.transformer.accessconverter.AccessWidener.Header = this.header,
      entries: List<dev.kikugie.fletching_table.transformer.accessconverter.AccessWidener.Entry> = this.entries
   ): AccessWidener {
      return new AccessWidener(header, entries);
   }

   public override fun toString(): String {
      return "AccessWidener(header=${this.header}, entries=${this.entries})";
   }

   public override fun hashCode(): Int {
      return this.header.hashCode() * 31 + this.entries.hashCode();
   }

   public override operator fun equals(other: Any?): Boolean {
      if (this === other) {
         return true;
      } else if (other !is AccessWidener) {
         return false;
      } else {
         val var2: AccessWidener = other as AccessWidener;
         if (!(this.header == (other as AccessWidener).header)) {
            return false;
         } else {
            return this.entries == var2.entries;
         }
      }
   }

   public data class ClassEntry(transitive: Boolean, modifier: AwTokenType, className: String) : AccessWidener.Entry {
      public open val transitive: Boolean
      public open val modifier: AwTokenType
      public final val className: String

      init {
         this.transitive = transitive;
         this.modifier = modifier;
         this.className = className;
      }

      public open fun convert(): AccessTransformer.ClassEntry {
         return new AccessTransformer.ClassEntry(this.getModifier(), this.getModifier() === AwTokenType.EXTENDABLE, this.className);
      }

      public operator fun component1(): Boolean {
         return this.transitive;
      }

      public operator fun component2(): AwTokenType {
         return this.modifier;
      }

      public operator fun component3(): String {
         return this.className;
      }

      public fun copy(transitive: Boolean = this.transitive, modifier: AwTokenType = this.modifier, className: String = this.className): dev.kikugie.fletching_table.transformer.accessconverter.AccessWidener.ClassEntry {
         return new AccessWidener.ClassEntry(transitive, modifier, className);
      }

      public override fun toString(): String {
         return "ClassEntry(transitive=${this.transitive}, modifier=${this.modifier}, className=${this.className})";
      }

      public override fun hashCode(): Int {
         return (java.lang.Boolean.hashCode(this.transitive) * 31 + this.modifier.hashCode()) * 31 + this.className.hashCode();
      }

      public override operator fun equals(other: Any?): Boolean {
         if (this === other) {
            return true;
         } else if (other !is AccessWidener.ClassEntry) {
            return false;
         } else {
            val var2: AccessWidener.ClassEntry = other as AccessWidener.ClassEntry;
            if (this.transitive != (other as AccessWidener.ClassEntry).transitive) {
               return false;
            } else if (this.modifier != var2.modifier) {
               return false;
            } else {
               return this.className == var2.className;
            }
         }
      }
   }

   public sealed interface Entry {
      public val transitive: Boolean
      public val modifier: AwTokenType

      public abstract fun convert(): AccessTransformer.Entry {
      }
   }

   public data class FieldEntry(transitive: Boolean, modifier: AwTokenType, className: String, fieldName: String, fieldDesc: String) : AccessWidener.Entry {
      public open val transitive: Boolean
      public open val modifier: AwTokenType
      public final val className: String
      public final val fieldName: String
      public final val fieldDesc: String

      init {
         this.transitive = transitive;
         this.modifier = modifier;
         this.className = className;
         this.fieldName = fieldName;
         this.fieldDesc = fieldDesc;
      }

      public open fun convert(): AccessTransformer.FieldEntry {
         return new AccessTransformer.FieldEntry(this.getModifier(), this.getModifier() === AwTokenType.MUTABLE, this.className, this.fieldName);
      }

      public operator fun component1(): Boolean {
         return this.transitive;
      }

      public operator fun component2(): AwTokenType {
         return this.modifier;
      }

      public operator fun component3(): String {
         return this.className;
      }

      public operator fun component4(): String {
         return this.fieldName;
      }

      public operator fun component5(): String {
         return this.fieldDesc;
      }

      public fun copy(
         transitive: Boolean = this.transitive,
         modifier: AwTokenType = this.modifier,
         className: String = this.className,
         fieldName: String = this.fieldName,
         fieldDesc: String = this.fieldDesc
      ): dev.kikugie.fletching_table.transformer.accessconverter.AccessWidener.FieldEntry {
         return new AccessWidener.FieldEntry(transitive, modifier, className, fieldName, fieldDesc);
      }

      public override fun toString(): String {
         return "FieldEntry(transitive=${this.transitive}, modifier=${this.modifier}, className=${this.className}, fieldName=${this.fieldName}, fieldDesc=${this.fieldDesc})";
      }

      public override fun hashCode(): Int {
         return (
                  ((java.lang.Boolean.hashCode(this.transitive) * 31 + this.modifier.hashCode()) * 31 + this.className.hashCode()) * 31
                     + this.fieldName.hashCode()
               )
               * 31
            + this.fieldDesc.hashCode();
      }

      public override operator fun equals(other: Any?): Boolean {
         if (this === other) {
            return true;
         } else if (other !is AccessWidener.FieldEntry) {
            return false;
         } else {
            val var2: AccessWidener.FieldEntry = other as AccessWidener.FieldEntry;
            if (this.transitive != (other as AccessWidener.FieldEntry).transitive) {
               return false;
            } else if (this.modifier != var2.modifier) {
               return false;
            } else if (!(this.className == var2.className)) {
               return false;
            } else if (!(this.fieldName == var2.fieldName)) {
               return false;
            } else {
               return this.fieldDesc == var2.fieldDesc;
            }
         }
      }
   }

   public data class Header(version: Int, namespace: String) {
      public final val version: Int
      public final val namespace: String

      init {
         this.version = version;
         this.namespace = namespace;
      }

      public operator fun component1(): Int {
         return this.version;
      }

      public operator fun component2(): String {
         return this.namespace;
      }

      public fun copy(version: Int = this.version, namespace: String = this.namespace): dev.kikugie.fletching_table.transformer.accessconverter.AccessWidener.Header {
         return new AccessWidener.Header(version, namespace);
      }

      public override fun toString(): String {
         return "Header(version=${this.version}, namespace=${this.namespace})";
      }

      public override fun hashCode(): Int {
         return Integer.hashCode(this.version) * 31 + this.namespace.hashCode();
      }

      public override operator fun equals(other: Any?): Boolean {
         if (this === other) {
            return true;
         } else if (other !is AccessWidener.Header) {
            return false;
         } else {
            val var2: AccessWidener.Header = other as AccessWidener.Header;
            if (this.version != (other as AccessWidener.Header).version) {
               return false;
            } else {
               return this.namespace == var2.namespace;
            }
         }
      }
   }

   public data class MethodEntry(transitive: Boolean, modifier: AwTokenType, className: String, methodName: String, methodDesc: String) : AccessWidener.Entry {
      public open val transitive: Boolean
      public open val modifier: AwTokenType
      public final val className: String
      public final val methodName: String
      public final val methodDesc: String

      init {
         this.transitive = transitive;
         this.modifier = modifier;
         this.className = className;
         this.methodName = methodName;
         this.methodDesc = methodDesc;
      }

      public open fun convert(): AccessTransformer.MethodEntry {
         return new AccessTransformer.MethodEntry(
            this.getModifier(), this.getModifier() === AwTokenType.EXTENDABLE, this.className, this.methodName, this.methodDesc
         );
      }

      public operator fun component1(): Boolean {
         return this.transitive;
      }

      public operator fun component2(): AwTokenType {
         return this.modifier;
      }

      public operator fun component3(): String {
         return this.className;
      }

      public operator fun component4(): String {
         return this.methodName;
      }

      public operator fun component5(): String {
         return this.methodDesc;
      }

      public fun copy(
         transitive: Boolean = this.transitive,
         modifier: AwTokenType = this.modifier,
         className: String = this.className,
         methodName: String = this.methodName,
         methodDesc: String = this.methodDesc
      ): dev.kikugie.fletching_table.transformer.accessconverter.AccessWidener.MethodEntry {
         return new AccessWidener.MethodEntry(transitive, modifier, className, methodName, methodDesc);
      }

      public override fun toString(): String {
         return "MethodEntry(transitive=${this.transitive}, modifier=${this.modifier}, className=${this.className}, methodName=${this.methodName}, methodDesc=${this.methodDesc})";
      }

      public override fun hashCode(): Int {
         return (
                  ((java.lang.Boolean.hashCode(this.transitive) * 31 + this.modifier.hashCode()) * 31 + this.className.hashCode()) * 31
                     + this.methodName.hashCode()
               )
               * 31
            + this.methodDesc.hashCode();
      }

      public override operator fun equals(other: Any?): Boolean {
         if (this === other) {
            return true;
         } else if (other !is AccessWidener.MethodEntry) {
            return false;
         } else {
            val var2: AccessWidener.MethodEntry = other as AccessWidener.MethodEntry;
            if (this.transitive != (other as AccessWidener.MethodEntry).transitive) {
               return false;
            } else if (this.modifier != var2.modifier) {
               return false;
            } else if (!(this.className == var2.className)) {
               return false;
            } else if (!(this.methodName == var2.methodName)) {
               return false;
            } else {
               return this.methodDesc == var2.methodDesc;
            }
         }
      }
   }
}
