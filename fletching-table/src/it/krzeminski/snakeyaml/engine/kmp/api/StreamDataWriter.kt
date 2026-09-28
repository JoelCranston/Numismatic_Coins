package it.krzeminski.snakeyaml.engine.kmp.api

public interface StreamDataWriter {
   public open fun flush() {
   }

   public abstract fun write(str: String) {
   }

   public abstract fun write(str: String, off: Int, len: Int) {
   }

   // $VF: Class flags could not be determined
   internal class DefaultImpls {
      @Deprecated
      @JvmStatic
      fun flush(`$this`: StreamDataWriter) {
         StreamDataWriter.access$flush$jd(`$this`);
      }
   }
}
