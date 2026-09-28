package kotlin.properties

import kotlin.properties.Delegates.observable.1
import kotlin.reflect.KProperty

public object Delegates {
   public fun <T : Any> notNull(): ReadWriteProperty<Any?, T> {
      return new NotNullVar();
   }

   public inline fun <T> observable(initialValue: T, crossinline onChange: (KProperty<*>, T, T) -> Unit): ReadWriteProperty<Any?, T> {
      return new 1(initialValue, onChange);
   }

   public inline fun <T> vetoable(initialValue: T, crossinline onChange: (KProperty<*>, T, T) -> Boolean): ReadWriteProperty<Any?, T> {
      return new kotlin.properties.Delegates.vetoable.1(initialValue, onChange);
   }
}
