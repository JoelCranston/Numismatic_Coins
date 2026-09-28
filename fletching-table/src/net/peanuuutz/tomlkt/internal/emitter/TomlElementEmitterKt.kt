package net.peanuuutz.tomlkt.internal.emitter

import net.peanuuutz.tomlkt.TomlWriter

private fun TomlWriter.startEntry(key: String) {
   `$this$startEntry`.writeKey(key);
   `$this$startEntry`.writeSpace();
   `$this$startEntry`.writeKeyValueSeparator();
   `$this$startEntry`.writeSpace();
}

private fun TomlWriter.writeRegularTableHead(path: List<String>) {
   `$this$writeRegularTableHead`.startRegularTableHead();
   writePath(`$this$writeRegularTableHead`, path);
   `$this$writeRegularTableHead`.endRegularTableHead();
}

private fun TomlWriter.writeArrayOfTableHead(path: List<String>) {
   `$this$writeArrayOfTableHead`.startArrayOfTableHead();
   writePath(`$this$writeArrayOfTableHead`, path);
   `$this$writeArrayOfTableHead`.endArrayOfTableHead();
}

private fun TomlWriter.writePath(path: List<String>) {
   val lastIndex: Int = CollectionsKt.getLastIndex(path);

   for (int i = 0; i < lastIndex; i++) {
      `$this$writePath`.writeKey(path.get(i) as java.lang.String);
      `$this$writePath`.writeKeySeparator();
   }

   `$this$writePath`.writeKey(path.get(lastIndex) as java.lang.String);
}

@JvmSynthetic
fun `access$startEntry`(`$receiver`: TomlWriter, key: java.lang.String) {
   startEntry(`$receiver`, key);
}

@JvmSynthetic
fun `access$writeRegularTableHead`(`$receiver`: TomlWriter, path: java.util.List) {
   writeRegularTableHead(`$receiver`, path);
}

@JvmSynthetic
fun `access$writeArrayOfTableHead`(`$receiver`: TomlWriter, path: java.util.List) {
   writeArrayOfTableHead(`$receiver`, path);
}
