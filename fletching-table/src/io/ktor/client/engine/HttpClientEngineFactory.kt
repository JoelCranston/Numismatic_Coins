package io.ktor.client.engine

public interface HttpClientEngineFactory<T extends HttpClientEngineConfig> {
   public abstract fun create(block: (Any) -> Unit = HttpClientEngineFactory::create$lambda$0): HttpClientEngine {
   }

   @JvmDefault
   @JvmStatic
   fun `create$lambda$0`(var0: HttpClientEngineConfig): Unit {
      return Unit.INSTANCE;
   }

   // $VF: Class flags could not be determined
   internal class DefaultImpls
}
