package dev.kikugie.commons.collections

import java.util.NoSuchElementException

@PublishedApi
internal inline fun <T : Any> notNullElement(element: T?, message: () -> String): T {
   if (element == null) {
      throw new NoSuchElementException(message.invoke() as java.lang.String);
   } else {
      return (T)element;
   }
}
