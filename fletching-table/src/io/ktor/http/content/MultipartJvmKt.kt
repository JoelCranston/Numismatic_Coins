package io.ktor.http.content

import io.ktor.utils.io.jvm.javaio.BlockingKt
import java.io.InputStream

@Deprecated(
   message = "This API uses blocking InputStream. Please use provider() directly."
)
public final val streamProvider: () -> InputStream
   public final get() {
      return MultipartJvmKt::_get_streamProvider_$lambda$0;
   }


fun `_get_streamProvider_$lambda$0`(`$this_streamProvider`: PartData.FileItem): InputStream {
   return BlockingKt.toInputStream$default(`$this_streamProvider`.getProvider().invoke(), null, 1, null);
}
