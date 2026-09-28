package io.ktor.http.content

public interface MultiPartData {
   public abstract suspend fun readPart(): PartData? {
   }

   public object Empty : MultiPartData {
      public override suspend fun readPart(): PartData? {
         return null;
      }
   }
}
