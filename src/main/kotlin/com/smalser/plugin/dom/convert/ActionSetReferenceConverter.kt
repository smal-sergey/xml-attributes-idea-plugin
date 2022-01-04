package com.smalser.plugin.dom.convert

import com.intellij.openapi.diagnostic.logger
import com.intellij.psi.util.parentOfType
import com.intellij.psi.xml.XmlFile
import com.intellij.util.xml.ConvertContext
import com.intellij.util.xml.DomManager
import com.intellij.util.xml.ResolvingConverter
import com.smalser.plugin.dom.model.ActionsRoot
import com.smalser.plugin.dom.model.Actionset
import com.smalser.plugin.psi.toStringAttribute

class ActionSetReferenceConverter : ResolvingConverter<Actionset>() {
    private val log = logger<ActionSetReferenceConverter>()

    override fun toString(t: Actionset?, context: ConvertContext?): String? = t?.getAttributeName()?.stringValue

    override fun fromString(value: String?, context: ConvertContext?): Actionset? {
        val element = context?.xmlElement
        if (value == null || context == null || element == null) {
            return null
        }

        log.info("Resolving value=$value on context.xmlElement: ${toStringAttribute(element)}")
        val rootPsi = element.parentOfType<XmlFile>()!!

        val manager = DomManager.getDomManager(context.project)
        val root: ActionsRoot = manager.getFileElement(rootPsi, ActionsRoot::class.java)?.rootElement!!
        return root.getActionsets().find {
            it.getAttributeName().stringValue == value
        }
    }

    override fun getVariants(context: ConvertContext?): MutableCollection<out Actionset> {
        val element = context?.xmlElement
        if (context == null || element == null) {
            return ArrayList()
        }

        val rootPsi = element.parentOfType<XmlFile>()!!

        val manager = DomManager.getDomManager(context.project)
        val root: ActionsRoot = manager.getFileElement(rootPsi, ActionsRoot::class.java)?.rootElement!!
        return root.getActionsets().toMutableList()
    }
}