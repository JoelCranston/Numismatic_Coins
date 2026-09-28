package io.ktor.client.engine.java

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.HttpClientEngineFactory

public data object Java : HttpClientEngineFactory<JavaHttpConfig> {
   public override fun create(block: (JavaHttpConfig) -> Unit): HttpClientEngine {
      val var2: JavaHttpConfig = new JavaHttpConfig();
      block.invoke(var2);
      return new JavaHttpEngine(var2);
   }

   public override fun toString(): String {
      return "Java";
   }

   public override fun hashCode(): Int {
      return -664764789;
   }

   public override operator fun equals(other: Any?): Boolean {
      if (this === other) {
         return true;
      } else {
         return other is Java;
      }
   }
}
