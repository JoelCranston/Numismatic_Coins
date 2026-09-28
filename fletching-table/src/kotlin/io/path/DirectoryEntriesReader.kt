package kotlin.io.path

import java.nio.file.FileVisitResult
import java.nio.file.FileVisitor
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.SimpleFileVisitor
import java.nio.file.attribute.BasicFileAttributes
import kotlin.jvm.internal.SourceDebugExtension

@SourceDebugExtension(["SMAP\nPathTreeWalk.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PathTreeWalk.kt\nkotlin/io/path/DirectoryEntriesReader\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,180:1\n1#2:181\n*E\n"])
private class DirectoryEntriesReader(followLinks: Boolean) : SimpleFileVisitor<Path> {
   public final val followLinks: Boolean
   private final var directoryNode: PathNode?
   private final var entries: ArrayDeque<PathNode>

   init {
      this.followLinks = followLinks;
      this.entries = new ArrayDeque<>();
   }

   public fun readEntries(directoryNode: PathNode): List<PathNode> {
      this.directoryNode = directoryNode;
      Files.walkFileTree(directoryNode.getPath(), LinkFollowing.INSTANCE.toVisitOptions(this.followLinks), 1, this as FileVisitor<? super Path>);
      this.entries.removeFirst();
      val var2: ArrayDeque = this.entries;
      this.entries = new ArrayDeque<>();
      return var2;
   }

   public open fun preVisitDirectory(dir: Path, attrs: BasicFileAttributes): FileVisitResult {
      this.entries.add(new PathNode(dir, attrs.fileKey(), this.directoryNode));
      val var10000: FileVisitResult = super.preVisitDirectory(dir, attrs);
      return var10000;
   }

   public open fun visitFile(file: Path, attrs: BasicFileAttributes): FileVisitResult {
      this.entries.add(new PathNode(file, null, this.directoryNode));
      val var10000: FileVisitResult = super.visitFile(file, attrs);
      return var10000;
   }
}
