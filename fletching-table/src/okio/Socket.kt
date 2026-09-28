package okio

public interface Socket {
   public val source: Source
   public val sink: Sink

   public abstract fun cancel() {
   }
}
