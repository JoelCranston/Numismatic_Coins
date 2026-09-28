package dev.kikugie.fletching_table.transformer.accessconverter

internal data class AccessTransformer(entries: List<dev.kikugie.fletching_table.transformer.accessconverter.AccessTransformer.Entry>) {
   public final val entries: List<dev.kikugie.fletching_table.transformer.accessconverter.AccessTransformer.Entry>

   init {
      this.entries = entries;
   }

   public operator fun component1(): List<dev.kikugie.fletching_table.transformer.accessconverter.AccessTransformer.Entry> {
      return this.entries;
   }

   public fun copy(entries: List<dev.kikugie.fletching_table.transformer.accessconverter.AccessTransformer.Entry> = this.entries): AccessTransformer {
      return new AccessTransformer(entries);
   }

   public override fun toString(): String {
      return "AccessTransformer(entries=${this.entries})";
   }

   public override fun hashCode(): Int {
      return this.entries.hashCode();
   }

   public override operator fun equals(other: Any?): Boolean {
      if (this === other) {
         return true;
      } else if (other !is AccessTransformer) {
         return false;
      } else {
         return this.entries == (other as AccessTransformer).entries;
      }
   }

   public data class ClassEntry(visibility: AwTokenType, nonFinal: Boolean, className: String) : AccessTransformer.Entry {
      public open val visibility: AwTokenType
      public open val nonFinal: Boolean
      public final val className: String

      init {
         this.visibility = visibility;
         this.nonFinal = nonFinal;
         this.className = className;
      }

      public open fun merge(other: dev.kikugie.fletching_table.transformer.accessconverter.AccessTransformer.Entry?): dev.kikugie.fletching_table.transformer.accessconverter.AccessTransformer.ClassEntry {
         return if (other !is AccessTransformer.ClassEntry)
            this
            else
            copy$default(
               this,
               ComparisonsKt.minOf(this.getVisibility(), (other as AccessTransformer.ClassEntry).getVisibility()),
               this.getNonFinal() || (other as AccessTransformer.ClassEntry).getNonFinal(),
               null,
               4,
               null
            );
      }

      public override fun toAtString(): String {
         return "${AccessTransformerKt.access$getModifier(this)} ${StringsKt.replace$default(this.className, '/', '.', false, 4, null)}";
      }

      public operator fun component1(): AwTokenType {
         return this.visibility;
      }

      public operator fun component2(): Boolean {
         return this.nonFinal;
      }

      public operator fun component3(): String {
         return this.className;
      }

      public fun copy(visibility: AwTokenType = this.visibility, nonFinal: Boolean = this.nonFinal, className: String = this.className): dev.kikugie.fletching_table.transformer.accessconverter.AccessTransformer.ClassEntry {
         return new AccessTransformer.ClassEntry(visibility, nonFinal, className);
      }

      public override fun toString(): String {
         return "ClassEntry(visibility=${this.visibility}, nonFinal=${this.nonFinal}, className=${this.className})";
      }

      public override fun hashCode(): Int {
         return (this.visibility.hashCode() * 31 + java.lang.Boolean.hashCode(this.nonFinal)) * 31 + this.className.hashCode();
      }

      public override operator fun equals(other: Any?): Boolean {
         if (this === other) {
            return true;
         } else if (other !is AccessTransformer.ClassEntry) {
            return false;
         } else {
            val var2: AccessTransformer.ClassEntry = other as AccessTransformer.ClassEntry;
            if (this.visibility != (other as AccessTransformer.ClassEntry).visibility) {
               return false;
            } else if (this.nonFinal != var2.nonFinal) {
               return false;
            } else {
               return this.className == var2.className;
            }
         }
      }
   }

   public sealed interface Entry {
      public val visibility: AwTokenType
      public val nonFinal: Boolean

      public abstract fun merge(other: dev.kikugie.fletching_table.transformer.accessconverter.AccessTransformer.Entry?): dev.kikugie.fletching_table.transformer.accessconverter.AccessTransformer.Entry {
      }

      public abstract fun toAtString(): String {
      }
   }

   public data class FieldEntry(visibility: AwTokenType, nonFinal: Boolean, className: String, fieldName: String) : AccessTransformer.Entry {
      public open val visibility: AwTokenType
      public open val nonFinal: Boolean
      public final val className: String
      public final val fieldName: String

      init {
         this.visibility = visibility;
         this.nonFinal = nonFinal;
         this.className = className;
         this.fieldName = fieldName;
      }

      public open fun merge(other: dev.kikugie.fletching_table.transformer.accessconverter.AccessTransformer.Entry?): dev.kikugie.fletching_table.transformer.accessconverter.AccessTransformer.FieldEntry {
         return if (other !is AccessTransformer.FieldEntry)
            this
            else
            copy$default(
               this,
               ComparisonsKt.minOf(this.getVisibility(), (other as AccessTransformer.FieldEntry).getVisibility()),
               this.getNonFinal() || (other as AccessTransformer.FieldEntry).getNonFinal(),
               null,
               null,
               12,
               null
            );
      }

      public override fun toAtString(): String {
         return "${AccessTransformerKt.access$getModifier(this)} ${StringsKt.replace$default(this.className, '/', '.', false, 4, null)} ${this.fieldName}";
      }

      public operator fun component1(): AwTokenType {
         return this.visibility;
      }

      public operator fun component2(): Boolean {
         return this.nonFinal;
      }

      public operator fun component3(): String {
         return this.className;
      }

      public operator fun component4(): String {
         return this.fieldName;
      }

      public fun copy(
         visibility: AwTokenType = this.visibility,
         nonFinal: Boolean = this.nonFinal,
         className: String = this.className,
         fieldName: String = this.fieldName
      ): dev.kikugie.fletching_table.transformer.accessconverter.AccessTransformer.FieldEntry {
         return new AccessTransformer.FieldEntry(visibility, nonFinal, className, fieldName);
      }

      public override fun toString(): String {
         return "FieldEntry(visibility=${this.visibility}, nonFinal=${this.nonFinal}, className=${this.className}, fieldName=${this.fieldName})";
      }

      public override fun hashCode(): Int {
         return ((this.visibility.hashCode() * 31 + java.lang.Boolean.hashCode(this.nonFinal)) * 31 + this.className.hashCode()) * 31
            + this.fieldName.hashCode();
      }

      public override operator fun equals(other: Any?): Boolean {
         if (this === other) {
            return true;
         } else if (other !is AccessTransformer.FieldEntry) {
            return false;
         } else {
            val var2: AccessTransformer.FieldEntry = other as AccessTransformer.FieldEntry;
            if (this.visibility != (other as AccessTransformer.FieldEntry).visibility) {
               return false;
            } else if (this.nonFinal != var2.nonFinal) {
               return false;
            } else if (!(this.className == var2.className)) {
               return false;
            } else {
               return this.fieldName == var2.fieldName;
            }
         }
      }
   }

   public data class MethodEntry(visibility: AwTokenType, nonFinal: Boolean, className: String, methodName: String, methodDesc: String) :
      AccessTransformer.Entry {
      public open val visibility: AwTokenType
      public open val nonFinal: Boolean
      public final val className: String
      public final val methodName: String
      public final val methodDesc: String

      init {
         this.visibility = visibility;
         this.nonFinal = nonFinal;
         this.className = className;
         this.methodName = methodName;
         this.methodDesc = methodDesc;
      }

      public open fun merge(other: dev.kikugie.fletching_table.transformer.accessconverter.AccessTransformer.Entry?): dev.kikugie.fletching_table.transformer.accessconverter.AccessTransformer.MethodEntry {
         return if (other !is AccessTransformer.MethodEntry)
            this
            else
            copy$default(
               this,
               ComparisonsKt.minOf(this.getVisibility(), (other as AccessTransformer.MethodEntry).getVisibility()),
               this.getNonFinal() || (other as AccessTransformer.MethodEntry).getNonFinal(),
               null,
               null,
               null,
               28,
               null
            );
      }

      public override fun toAtString(): String {
         return "${AccessTransformerKt.access$getModifier(this)} ${StringsKt.replace$default(this.className, '/', '.', false, 4, null)} ${this.methodName}${this.methodDesc}";
      }

      public operator fun component1(): AwTokenType {
         return this.visibility;
      }

      public operator fun component2(): Boolean {
         return this.nonFinal;
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
         visibility: AwTokenType = this.visibility,
         nonFinal: Boolean = this.nonFinal,
         className: String = this.className,
         methodName: String = this.methodName,
         methodDesc: String = this.methodDesc
      ): dev.kikugie.fletching_table.transformer.accessconverter.AccessTransformer.MethodEntry {
         return new AccessTransformer.MethodEntry(visibility, nonFinal, className, methodName, methodDesc);
      }

      public override fun toString(): String {
         return "MethodEntry(visibility=${this.visibility}, nonFinal=${this.nonFinal}, className=${this.className}, methodName=${this.methodName}, methodDesc=${this.methodDesc})";
      }

      public override fun hashCode(): Int {
         return (
                  ((this.visibility.hashCode() * 31 + java.lang.Boolean.hashCode(this.nonFinal)) * 31 + this.className.hashCode()) * 31
                     + this.methodName.hashCode()
               )
               * 31
            + this.methodDesc.hashCode();
      }

      public override operator fun equals(other: Any?): Boolean {
         if (this === other) {
            return true;
         } else if (other !is AccessTransformer.MethodEntry) {
            return false;
         } else {
            val var2: AccessTransformer.MethodEntry = other as AccessTransformer.MethodEntry;
            if (this.visibility != (other as AccessTransformer.MethodEntry).visibility) {
               return false;
            } else if (this.nonFinal != var2.nonFinal) {
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
