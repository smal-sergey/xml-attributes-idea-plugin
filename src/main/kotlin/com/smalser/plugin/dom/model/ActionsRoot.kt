package com.smalser.plugin.dom.model

import com.intellij.util.xml.DefinesXml
import com.intellij.util.xml.DomElement
import com.intellij.util.xml.SubTagList


@DefinesXml
interface ActionsRoot : DomElement {
    @SubTagList("actionset")
    fun getActionsets(): List<Actionset>
}