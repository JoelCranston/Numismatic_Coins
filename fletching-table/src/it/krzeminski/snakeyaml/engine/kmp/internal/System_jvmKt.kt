package it.krzeminski.snakeyaml.engine.kmp.internal

internal fun getEnvironmentVariable(key: String): String? {
   return System.getenv(key);
}

internal fun objectIdentityHashCode(any: Any): IdentityHashCode {
   return IdentityHashCode.constructor-impl(System.identityHashCode(any));
}
