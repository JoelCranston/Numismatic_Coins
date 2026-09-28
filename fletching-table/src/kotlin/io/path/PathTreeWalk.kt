package kotlin.io.path

import java.nio.file.FileSystemLoopException
import java.nio.file.Files
import java.nio.file.LinkOption
import java.nio.file.Path
import java.util.Arrays
import kotlin.io.path.PathTreeWalk.dfsIterator.1
import kotlin.jvm.internal.InlineMarker

internal class PathTreeWalk(start: Path, vararg options: Any) : Sequence<Path> {
   private final val start: Path
   private final val options: Array<out PathWalkOption>

   private final val followLinks: Boolean
      private final get() {
         return ArraysKt.contains(this.options, PathWalkOption.FOLLOW_LINKS);
      }


   private final val linkOptions: Array<LinkOption>
      private final get() {
         return LinkFollowing.INSTANCE.toLinkOptions(this.getFollowLinks());
      }


   private final val includeDirectories: Boolean
      private final get() {
         return ArraysKt.contains(this.options, PathWalkOption.INCLUDE_DIRECTORIES);
      }


   private final val isBFS: Boolean
      private final get() {
         return ArraysKt.contains(this.options, PathWalkOption.BREADTH_FIRST);
      }


   init {
      this.start = start;
      this.options = options;
   }

   public override operator fun iterator(): Iterator<Path> {
      return if (this.isBFS()) this.bfsIterator() else this.dfsIterator();
   }

   private suspend inline fun SequenceScope<Path>.yieldIfNeeded(node: PathNode, entriesReader: DirectoryEntriesReader, entriesAction: (List<PathNode>) -> Unit) {
      val path: Path = node.getPath();
      if (node.getParent() != null) {
         PathsKt.checkFileName(path);
      }

      var var9: Array<LinkOption> = access$getLinkOptions(this);
      var9 = Arrays.copyOf(var9, var9.length);
      if (Files.isDirectory(path, Arrays.copyOf(var9, var9.length))) {
         if (PathTreeWalkKt.access$createsCycle(node)) {
            throw new FileSystemLoopException(path.toString());
         }

         if (access$getIncludeDirectories(this)) {
            InlineMarker.mark(0);
            `$this$yieldIfNeeded`.yield(path, `$completion`);
            InlineMarker.mark(1);
         }

         var9 = access$getLinkOptions(this);
         var9 = Arrays.copyOf(var9, var9.length);
         if (Files.isDirectory(path, Arrays.copyOf(var9, var9.length))) {
            entriesAction.invoke(entriesReader.readEntries(node));
         }
      } else {
         var9 = new LinkOption[]{LinkOption.NOFOLLOW_LINKS};
         if (Files.exists(path, Arrays.copyOf(var9, var9.length))) {
            InlineMarker.mark(0);
            `$this$yieldIfNeeded`.yield(path, `$completion`);
            InlineMarker.mark(1);
            return Unit.INSTANCE;
         }
      }

      return Unit.INSTANCE;
   }

   private fun dfsIterator(): Iterator<Path> {
      return SequencesKt.iterator(new 1(this, null));
   }

   private fun bfsIterator(): Iterator<Path> {
      return SequencesKt.iterator(new kotlin.io.path.PathTreeWalk.bfsIterator.1(this, null));
   }
}
