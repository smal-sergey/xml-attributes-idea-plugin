package com.smalser.plugin.dom

import com.intellij.openapi.diagnostic.logger
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiFileSystemItem
import com.intellij.psi.search.FilenameIndex
import com.intellij.psi.search.GlobalSearchScope
import com.intellij.psi.xml.XmlFile
import com.intellij.util.Processor
import com.intellij.util.xml.DomFileDescription
import com.intellij.util.xml.DomManager
import com.smalser.plugin.dom.model.ActionsRoot


class ActionsDomFileDescription : DomFileDescription<ActionsRoot>(ActionsRoot::class.java, "actions") {
    fun isMyFile(file: XmlFile, module: Module?): Boolean {
        return file.name == "actions.xml"
    }
}
internal val log = logger<ActionsDomFileDescription>()

fun processActionsFiles(
    project: Project,
    scope: GlobalSearchScope?,
    processor: Processor<ActionsRoot?>
): Boolean {
    val manager = DomManager.getDomManager(project)
    log.info("Invoking processActionsFiles")

    return FilenameIndex.processFilesByName(
        "actions.xml", false, true,
        { it: PsiFileSystemItem? ->
            val file = manager.getFileElement(it as XmlFile?, ActionsRoot::class.java)
            file == null || processor.process(file.getRootElement())
        }, scope!!, project
    )
}