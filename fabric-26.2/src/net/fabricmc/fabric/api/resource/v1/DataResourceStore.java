package net.fabricmc.fabric.api.resource.v1;

public interface DataResourceStore {
   default <T> T getOrThrow(DataResourceStore.Key<T> key) {
      throw new AssertionError("Implemented in Mixin");
   }

   public static final class Key<T> {
   }

   public interface Mutable extends DataResourceStore {
      <T> void put(DataResourceStore.Key<T> var1, T var2);
   }
}
