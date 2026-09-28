package kotlinx.serialization.modules

import kotlinx.serialization.KSerializer

internal sealed class ContextualProvider protected constructor() {
   public abstract operator fun invoke(typeArgumentsSerializers: List<KSerializer<*>>): KSerializer<*> {
   }

   public class Argless(serializer: KSerializer<*>) : ContextualProvider() {
      public final val serializer: KSerializer<*>

      init {
         this.serializer = serializer;
      }

      public override operator fun invoke(typeArgumentsSerializers: List<KSerializer<*>>): KSerializer<*> {
         return this.serializer;
      }

      public override operator fun equals(other: Any?): Boolean {
         return other is ContextualProvider.Argless && (other as ContextualProvider.Argless).serializer == this.serializer;
      }

      public override fun hashCode(): Int {
         return this.serializer.hashCode();
      }
   }

   public class WithTypeArguments(provider: (List<KSerializer<*>>) -> KSerializer<*>) : ContextualProvider() {
      public final val provider: (List<KSerializer<*>>) -> KSerializer<*>

      init {
         this.provider = provider;
      }

      public override operator fun invoke(typeArgumentsSerializers: List<KSerializer<*>>): KSerializer<*> {
         return this.provider.invoke(typeArgumentsSerializers);
      }
   }
}
