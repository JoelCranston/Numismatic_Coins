package net.fabricmc.fabric.api.resource.v1;

public interface DataResourceStore {
   <T> T getOrThrow(DataResourceStore.Key<T> var1);

   public static final class Key<T> {
   }

   public interface Mutable extends DataResourceStore {
      <T> void put(DataResourceStore.Key<T> var1, T var2);
   }
}
