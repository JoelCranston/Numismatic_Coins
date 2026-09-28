package io.ktor.serialization

import io.ktor.util.reflect.TypeInfo
import io.ktor.websocket.Frame
import java.nio.charset.Charset
import kotlin.coroutines.Continuation

public interface WebsocketContentConverter {
   public open suspend fun serialize(charset: Charset, typeInfo: TypeInfo, value: Any?): Frame {
      return serialize$suspendImpl(this, charset, typeInfo, value, `$completion`);
   }

   public abstract suspend fun deserialize(charset: Charset, typeInfo: TypeInfo, content: Frame): Any? {
   }

   public abstract fun isApplicable(frame: Frame): Boolean {
   }

   // $VF: Class flags could not be determined
   internal class DefaultImpls {
      @Deprecated
      @JvmStatic
      fun serialize(`$this`: WebsocketContentConverter, charset: Charset, typeInfo: TypeInfo, value: Any?, `$completion`: Continuation<? super Frame>): Any {
         return WebsocketContentConverter.access$serialize$jd(`$this`, charset, typeInfo, value, `$completion`);
      }
   }
}
