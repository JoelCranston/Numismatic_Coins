package kotlin.internal

import java.io.InvalidObjectException

@InlineOnly
internal inline fun throwReadObjectNotSupported(): Nothing {
   throw new InvalidObjectException("Deserialization is supported via proxy only");
}

@InlineOnly
internal inline fun wrapAsDeserializationException(action: () -> Unit) {
   try {
      action.invoke();
   } catch (var2: java.lang.Throwable) {
      val var10000: java.lang.Throwable = new InvalidObjectException(var2.getMessage()).initCause(var2);
      throw var10000;
   }
}
