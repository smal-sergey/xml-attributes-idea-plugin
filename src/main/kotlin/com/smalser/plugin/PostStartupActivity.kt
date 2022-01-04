package com.smalser.plugin

import com.intellij.openapi.diagnostic.logger
import com.intellij.openapi.project.Project
import com.intellij.openapi.startup.StartupActivity
import com.intellij.openapi.vfs.LocalFileSystem
import com.intellij.psi.PsiManager
import com.intellij.psi.xml.XmlFile
import com.intellij.util.xml.DomManager
import com.smalser.plugin.dom.model.ActionsRoot
import java.io.File


class PostStartupActivity : StartupActivity {
    private val log = logger<PostStartupActivity>()

    override fun runActivity(project: Project) {
        log.info("I'm started!!!!")

        val manager = DomManager.getDomManager(project)
//        val root: ActionsRoot = manager.getFileElement<DomElement>(file)!!.rootElement
//        val rootPsi = project.file.parentOfType<XmlFile>()!!


//        LocalFileSystem.getInstance().findFileByIoFile()
        log.info(project.projectFile?.path)

        val file = LocalFileSystem.getInstance().findFileByIoFile(File("D:\\code\\1tmp\\plugin-test\\src\\main\\resources\\actions.xml"))!!
//file.
        val xmlFile: XmlFile = PsiManager.getInstance(project).findFile(file) as XmlFile

        val root: ActionsRoot = manager.getFileElement(xmlFile, ActionsRoot::class.java)?.rootElement!!

        val actionSets = root.getActionsets()
        log.info(actionSets.toString())
        val ref = actionSets.get(1).getActions().get(1).getAttributeActionSetRef()
        log.info("ref str=" + ref.stringValue)
        log.info("ref val=" + ref.value)


//        manager.getFileElement<DomElement>()
//        val bars: List<Bar> = root.getFoo().getBars()
//        if (bars.size > 1) {
//            val s: String = bars[1].getValue()
//             do something
//        }
    }
}