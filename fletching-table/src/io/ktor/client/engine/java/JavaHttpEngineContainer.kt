package io.ktor.client.engine.java

import io.ktor.client.HttpClientEngineContainer
import io.ktor.client.engine.HttpClientEngineFactory

public class JavaHttpEngineContainer : HttpClientEngineContainer {
   public open val factory: HttpClientEngineFactory<*> = Java.INSTANCE as HttpClientEngineFactory

   public override fun toString(): String {
      return "Java";
   }
}
