package kotlin

import java.io.InvalidObjectException
import java.io.ObjectInputStream
import java.io.Serializable
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater

private class SafePublicationLazyImpl<T>(initializer: () -> Any) : Lazy<T>, Serializable {
   private final var initializer: (() -> Any)?
   private final var _value: Any?
   private final val final: Any

   public open val value: Any
      public open get() {
         if (this._value != UNINITIALIZED_VALUE.INSTANCE) {
            return (T)this._value;
         } else {
            if (this.initializer != null) {
               val newValue: Any = this.initializer.invoke();
               if (valueUpdater.compareAndSet(this, UNINITIALIZED_VALUE.INSTANCE, newValue)) {
                  this.initializer = null;
                  return (T)newValue;
               }
            }

            return (T)this._value;
         }
      }


   init {
      this.initializer = initializer;
      this._value = UNINITIALIZED_VALUE.INSTANCE;
      this.final = UNINITIALIZED_VALUE.INSTANCE;
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

   public companion object {
      private final val valueUpdater: AtomicReferenceFieldUpdater<SafePublicationLazyImpl<*>, Any>
   }
}
