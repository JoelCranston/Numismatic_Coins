package kotlin.collections.builders

import java.io.InvalidObjectException
import java.io.NotSerializableException
import java.io.ObjectInputStream
import java.io.Serializable
import kotlin.jvm.internal.markers.KMutableSet

internal class SetBuilder<E> internal constructor(backing: MapBuilder<Any, *>) : AbstractMutableSet<E>, java.util.Set<E>, Serializable, KMutableSet {
   private final val backing: MapBuilder<Any, *>

   public open val size: Int
      public open get() {
         return this.backing.size();
      }


   init {
      this.backing = backing;
   }

   public constructor() : this(new MapBuilder<>())
   public constructor(initialCapacity: Int) : this(new MapBuilder<>(initialCapacity))
   public fun build(): Set<Any> {
      this.backing.build();
      return if (this.size() > 0) this else Empty;
   }

   private fun writeReplace(): Any {
      if (this.backing.isReadOnly$kotlin_stdlib()) {
         return new SerializedCollection(this, 1);
      } else {
         throw new NotSerializableException("The set cannot be serialized while it is being built.");
      }
   }

   private fun readObject(input: ObjectInputStream) {
      throw new InvalidObjectException("Deserialization is supported via proxy only");
   }

   public override fun isEmpty(): Boolean {
      return this.backing.isEmpty();
   }

   public override operator fun contains(element: Any): Boolean {
      return this.backing.containsKey(element);
   }

   public override fun clear() {
      this.backing.clear();
   }

   public override fun add(element: Any): Boolean {
      return this.backing.addKey$kotlin_stdlib((E)element) >= 0;
   }

   public override fun remove(element: Any): Boolean {
      return this.backing.removeKey$kotlin_stdlib((E)element);
   }

   public override operator fun iterator(): MutableIterator<Any> {
      return this.backing.keysIterator$kotlin_stdlib() as MutableIterator<E>;
   }

   public override fun addAll(elements: Collection<Any>): Boolean {
      this.backing.checkIsMutable$kotlin_stdlib();
      return super.addAll(elements);
   }

   public override fun removeAll(elements: Collection<Any>): Boolean {
      this.backing.checkIsMutable$kotlin_stdlib();
      return super.removeAll(elements);
   }

   public override fun retainAll(elements: Collection<Any>): Boolean {
      this.backing.checkIsMutable$kotlin_stdlib();
      return super.retainAll(elements);
   }

   private companion object {
      private final val Empty: SetBuilder<Nothing>
   }
}
