package kotlin.io.path

import java.nio.file.CopyOption
import java.nio.file.Files
import java.nio.file.LinkOption
import java.nio.file.Path
import java.util.Arrays

@ExperimentalPathApi
private object DefaultCopyActionContext : CopyActionContext {
   public override fun Path.copyToIgnoringExistingDirectory(target: Path, followLinks: Boolean): CopyActionResult {
      val options: Array<LinkOption> = LinkFollowing.INSTANCE.toLinkOptions(followLinks);
      val var10001: Array<LinkOption> = Arrays.copyOf(options, options.length);
      if (Files.isDirectory(`$this$copyToIgnoringExistingDirectory`, Arrays.copyOf(var10001, var10001.length))) {
         val var6: Array<LinkOption> = new LinkOption[]{LinkOption.NOFOLLOW_LINKS};
         if (Files.isDirectory(target, Arrays.copyOf(var6, var6.length))) {
            return CopyActionResult.CONTINUE;
         }
      }

      val var10002: Array<CopyOption> = Arrays.copyOf(options, options.length);
      return CopyActionResult.CONTINUE;
   }
}
