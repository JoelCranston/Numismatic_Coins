package dev.kikugie.fletching_table.ksp.entrypoint

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable

@Serializable
public data class FTEntrypointModel(kind: String, reference: String, adapter: String = "java") {
   public final val kind: String
   public final val reference: String
   public final val adapter: String

   init {
      this.kind = kind;
      this.reference = reference;
      this.adapter = adapter;
   }

   public operator fun component1(): String {
      return this.kind;
   }

   public operator fun component2(): String {
      return this.reference;
   }

   public operator fun component3(): String {
      return this.adapter;
   }

   public fun copy(kind: String = this.kind, reference: String = this.reference, adapter: String = this.adapter): FTEntrypointModel {
      return new FTEntrypointModel(kind, reference, adapter);
   }

   public override fun toString(): String {
      return "FTEntrypointModel(kind=${this.kind}, reference=${this.reference}, adapter=${this.adapter})";
   }

   public override fun hashCode(): Int {
      return (this.kind.hashCode() * 31 + this.reference.hashCode()) * 31 + this.adapter.hashCode();
   }

   public override operator fun equals(other: Any?): Boolean {
      if (this === other) {
         return true;
      } else if (other !is FTEntrypointModel) {
         return false;
      } else {
         val var2: FTEntrypointModel = other as FTEntrypointModel;
         if (!(this.kind == (other as FTEntrypointModel).kind)) {
            return false;
         } else if (!(this.reference == var2.reference)) {
            return false;
         } else {
            return this.adapter == var2.adapter;
         }
      }
   }

   public companion object {
      public const val ENTRYPOINT_CONFIG: String
      public const val ENTRYPOINT_FQ: String
      public final val LIST_SERIALIZER: KSerializer<List<FTEntrypointModel>>

      public fun serializer(): KSerializer<FTEntrypointModel> {
         return FTEntrypointModel.$serializer.INSTANCE;
      }
   }
}
