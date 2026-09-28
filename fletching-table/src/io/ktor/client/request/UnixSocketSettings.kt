package io.ktor.client.request

import io.ktor.utils.io.InternalAPI

@InternalAPI
public class UnixSocketSettings(path: String) {
   public final val path: String

   init {
      this.path = path;
   }
}
