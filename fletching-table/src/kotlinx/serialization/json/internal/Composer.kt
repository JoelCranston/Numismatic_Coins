package kotlinx.serialization.json.internal

internal open class Composer(writer: InternalJsonWriter) {
   internal final val writer: InternalJsonWriter

   public final var writingFirst: Boolean
      internal final set(value) {
         this.writingFirst = var1;
      }


   init {
      this.writer = writer;
      this.writingFirst = true;
   }

   public open fun indent() {
      this.writingFirst = true;
   }

   public open fun unIndent() {
   }

   public open fun nextItem() {
      this.writingFirst = false;
   }

   public open fun nextItemIfNotFirst() {
      this.writingFirst = false;
   }

   public open fun space() {
   }

   public fun print(v: Char) {
      this.writer.writeChar(v);
   }

   public fun print(v: String) {
      this.writer.write(v);
   }

   public open fun print(v: Float) {
      this.writer.write(java.lang.String.valueOf(v));
   }

   public open fun print(v: Double) {
      this.writer.write(java.lang.String.valueOf(v));
   }

   public open fun print(v: Byte) {
      this.writer.writeLong((long)v);
   }

   public open fun print(v: Short) {
      this.writer.writeLong((long)v);
   }

   public open fun print(v: Int) {
      this.writer.writeLong((long)v);
   }

   public open fun print(v: Long) {
      this.writer.writeLong(v);
   }

   public open fun print(v: Boolean) {
      this.writer.write(java.lang.String.valueOf(v));
   }

   public open fun printQuoted(value: String) {
      this.writer.writeQuoted(value);
   }
}
