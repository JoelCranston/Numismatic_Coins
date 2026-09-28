package net.peanuuutz.tomlkt.internal

import kotlin.reflect.KClass
import kotlinx.serialization.descriptors.SerialKind

private const val PolymorphicCollection: String = "Collection-like type cannot be polymorphic"

internal fun throwSubclassNotRegistered(subclass: KClass<*>, baseClass: KClass<*>): Nothing {
   throw new IllegalStateException(
      ("Class ${subclass.getSimpleName()} is not registered for polymorphic serialization in the scope of ${baseClass.getSimpleName()}. Mark ${baseClass.getSimpleName()} as sealed or register ${subclass.getSimpleName()} in a serializers module (and switch out the default one in the Toml {  } factory function)")
         .toString()
   );
}

internal fun throwNonPrimitiveKey(key: Any?): Nothing {
   throw new NonPrimitiveKeyException(java.lang.String.valueOf(key));
}

internal fun throwUnsupportedSerialKind(kind: SerialKind): Nothing {
   throw new UnsupportedSerialKindException(kind.toString());
}

internal fun throwUnsupportedSerialKind(message: String): Nothing {
   throw new UnsupportedSerialKindException(message);
}

internal fun throwPolymorphicCollection(): Nothing {
   throw new PolymorphicCollectionException();
}

internal fun throwUnexpectedToken(token: Char, line: Int): Nothing {
   throw new UnexpectedTokenException("'${if (token != '\'') StringUtilsKt.escape$default(token, false, 1, null) else "\\'"}' (L$line)");
}

internal fun throwIncomplete(line: Int): Nothing {
   throw new IncompleteException("(L$line)");
}

internal fun throwConflictEntry(path: List<String>, line: Int): Nothing {
   throw new ConflictEntryException(
      CollectionsKt.joinToString$default(path, ".", null, " (L$line)", 0, null, TomlSerializationExceptionsKt::throwConflictEntry$lambda$0, 26, null)
   );
}

internal fun throwUnknownKey(key: String): Nothing {
   throw new UnknownKeyException(key);
}

fun `throwConflictEntry$lambda$0`(key: java.lang.String): java.lang.CharSequence {
   return StringUtilsKt.doubleQuotedIfNotPure(StringUtilsKt.escape$default(key, false, 1, null));
}
