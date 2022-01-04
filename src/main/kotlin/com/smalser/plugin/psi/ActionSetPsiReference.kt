package com.smalser.plugin.psi

import com.intellij.openapi.diagnostic.logger
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiReferenceBase
import com.intellij.psi.XmlElementVisitor
import com.intellij.psi.util.PsiTreeUtil
import com.intellij.psi.util.parentOfType
import com.intellij.psi.xml.XmlAttribute
import com.intellij.psi.xml.XmlFile
import com.intellij.psi.xml.XmlTag

class ActionSetPsiReference(element: XmlAttribute, val id: Int) : PsiReferenceBase<XmlAttribute>(element, TextRange(0, 10)) {
    private val log = logger<ActionSetPsiReference>()

    override fun resolve(): PsiElement? {
        val actionSetName = element.value
        if (actionSetName?.endsWith("ActionSet") != true) {
            return null
        }

        log.info("<$id> Resolving ${toStringAttribute(element)}")
        val rootPsi = element.parentOfType<XmlFile>()!!

        var result: XmlAttribute? = null
        val visitor = object : XmlElementVisitor() {
            override fun visitXmlTag(tag: XmlTag?) {
//                super.visitXmlTag(tag)
                if (tag != null && tag.name == "actionset") {
                    if (actionSetName == tag.getAttributeValue("name")) {
                        result = tag.getAttribute("name")
                    }
                }
            }
        }
        PsiTreeUtil.findChildrenOfType(rootPsi, XmlTag::class.java).forEach { it.accept(visitor) }

        log.info("<$id> result: <${result!!.parent.name} ${toStringAttribute(result)} ... />")
        return result
    }
}