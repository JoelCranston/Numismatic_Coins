package dev.kikugie.commons.result

public inline fun invalidArg(message: Any): Nothing {
   throw new IllegalArgumentException(message.toString());
}

public inline fun invalidIndex(message: Any): Nothing {
   throw new IndexOutOfBoundsException(message.toString());
}

public inline fun unsupportedOperation(message: Any): Nothing {
   throw new UnsupportedOperationException(message.toString());
}
