package dev.kikugie.fletching_table.transformer.language

private fun Iterable<Key>.join(): String {
   return CollectionsKt.joinToString$default(`$this$join`, ".", null, null, 0, null, KeysKt::join$lambda$0, 30, null);
}

fun `join$lambda$0`(it: Key): java.lang.CharSequence {
   return if (it is StringKey) (it as StringKey).unbox-impl() else (it as TemplateKey).getPath();
}

@JvmSynthetic
fun `access$join`(`$receiver`: java.lang.Iterable): java.lang.String {
   return join(`$receiver`);
}
