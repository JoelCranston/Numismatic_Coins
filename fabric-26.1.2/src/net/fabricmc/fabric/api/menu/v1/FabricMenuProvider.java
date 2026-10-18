package net.fabricmc.fabric.api.menu.v1;

public interface FabricMenuProvider {
   default boolean shouldCloseCurrentScreen() {
      return true;
   }
}
