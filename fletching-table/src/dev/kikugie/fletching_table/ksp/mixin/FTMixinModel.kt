package dev.kikugie.fletching_table.ksp.mixin

import dev.kikugie.fletching_table.annotation.MixinEnvironment
import dev.kikugie.fletching_table.annotation.MixinEnvironment.Env
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable

@Serializable
public data class FTMixinModel(implementation: String, definition: String = "default", environment: Env = MixinEnvironment.Env.DEFAULT) {
   public final val implementation: String
   public final val definition: String
   public final val environment: Env

   init {
      this.implementation = implementation;
      this.definition = definition;
      this.environment = environment;
   }

   public operator fun component1(): String {
      return this.implementation;
   }

   public operator fun component2(): String {
      return this.definition;
   }

   public operator fun component3(): Env {
      return this.environment;
   }

   public fun copy(implementation: String = this.implementation, definition: String = this.definition, environment: Env = this.environment): FTMixinModel {
      return new FTMixinModel(implementation, definition, environment);
   }

   public override fun toString(): String {
      return "FTMixinModel(implementation=${this.implementation}, definition=${this.definition}, environment=${this.environment})";
   }

   public override fun hashCode(): Int {
      return (this.implementation.hashCode() * 31 + this.definition.hashCode()) * 31 + this.environment.hashCode();
   }

   public override operator fun equals(other: Any?): Boolean {
      if (this === other) {
         return true;
      } else if (other !is FTMixinModel) {
         return false;
      } else {
         val var2: FTMixinModel = other as FTMixinModel;
         if (!(this.implementation == (other as FTMixinModel).implementation)) {
            return false;
         } else if (!(this.definition == var2.definition)) {
            return false;
         } else {
            return this.environment === var2.environment;
         }
      }
   }

   public companion object {
      public const val MIXIN_CONFIG: String
      public const val MIXIN_DEF_FQ: String
      public final val LIST_SERIALIZER: KSerializer<List<FTMixinModel>>

      public fun serializer(): KSerializer<FTMixinModel> {
         return FTMixinModel.$serializer.INSTANCE;
      }
   }
}
