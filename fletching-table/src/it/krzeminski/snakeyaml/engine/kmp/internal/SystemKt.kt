package it.krzeminski.snakeyaml.engine.kmp.internal

internal fun identityHashCode(any: Any?): IdentityHashCode {
   val var10000: Int;
   if (any == null) {
      var10000 = IdentityHashCode.constructor-impl(0);
   } else {
      if (!hasIdentityHashCode(any)) {
         throw new IllegalArgumentException(
            "identity hash code cannot be computed for primitives and strings (type: ${(any.getClass()::class).getSimpleName()}${41}"
         );
      }

      var10000 = System_jvmKt.objectIdentityHashCode(any);
   }

   return var10000;
}

internal fun hasIdentityHashCode(any: Any?): Boolean {
   return any !is java.lang.Byte
      && any !is java.lang.Short
      && any !is Int
      && any !is java.lang.Long
      && any !is java.lang.Float
      && any !is java.lang.Double
      && any !is java.lang.Boolean
      && any !is Character
      && any !is java.lang.String;
}
