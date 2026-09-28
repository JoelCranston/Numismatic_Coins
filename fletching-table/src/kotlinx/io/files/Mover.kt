package kotlinx.io.files

private interface Mover {
   public abstract fun move(source: Path, destination: Path) {
   }
}
