package io.ktor.util

private abstract class AttributesJvmBase : Attributes {
   protected abstract val map: MutableMap<AttributeKey<*>, Any?>

   public final val allKeys: List<AttributeKey<*>>
      public final get() {
         return kotlin.collections.CollectionsKt.toList(this.getMap().keySet());
      }


   open fun AttributesJvmBase() {
   }

   public override fun <T : Any> getOrNull(key: AttributeKey<Any>): Any? {
      return (T)this.getMap().get(key);
   }

   public override operator fun contains(key: AttributeKey<*>): Boolean {
      return this.getMap().containsKey(key);
   }

   public override fun <T : Any> put(key: AttributeKey<Any>, value: Any) {
      this.getMap().put(key, value);
   }

   public override fun <T : Any> remove(key: AttributeKey<Any>) {
      this.getMap().remove(key);
   }
}
