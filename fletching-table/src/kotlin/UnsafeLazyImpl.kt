package kotlin

import java.io.InvalidObjectException
import java.io.ObjectInputStream
import java.io.Serializable
import kotlin.jvm.functions.Function0

internal class UnsafeLazyImpl<T>(initializer: () -> Any) : Lazy<T>, Serializable {
   private final var initializer: (() -> Any)?
   private final var _value: Any?

   public open val value: Any
      public open get() {
         if (this._value === UNINITIALIZED_VALUE.INSTANCE) {
            val var10001: Function0 = this.initializer;
            this._value = var10001.invoke();
            this.initializer = null;
         }

         return (T)this._value;
      }


   init {
      this.initializer = initializer;
      this._value = UNINITIALIZED_VALUE.INSTANCE;
   }

   public override fun isInitialized(): Boolean {
      return this._value != UNINITIALIZED_VALUE.INSTANCE;
   }

   public override fun toString(): String {
      return if (this.isInitialized()) java.lang.String.valueOf(this.getValue()) else "Lazy value not initialized yet.";
   }

   private fun writeReplace(): Any {
      return new InitializedLazyImpl(this.getValue());
   }

   private fun readObject(input: ObjectInputStream) {
      throw new InvalidObjectException("Deserialization is supported via proxy only");
   }
}
