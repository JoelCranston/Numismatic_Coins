package net.fabricmc.fabric.api.resource.v1.reloader;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import net.minecraft.server.packs.resources.PreparableReloadListener;
import net.minecraft.server.packs.resources.PreparableReloadListener.PreparationBarrier;
import net.minecraft.server.packs.resources.PreparableReloadListener.SharedState;

public abstract class SimpleReloadListener<T> implements PreparableReloadListener {
   public final CompletableFuture<Void> reload(SharedState state, Executor prepareExecutor, PreparationBarrier preparationBarrier, Executor applyExecutor) {
      CompletableFuture<T> prepareStep = CompletableFuture.supplyAsync(() -> this.prepare(state), prepareExecutor);
      return prepareStep.<Object>thenCompose(preparationBarrier::wait).thenAcceptAsync(prepared -> this.apply((T)prepared, state), applyExecutor);
   }

   protected abstract T prepare(SharedState var1);

   protected abstract void apply(T var1, SharedState var2);
}
