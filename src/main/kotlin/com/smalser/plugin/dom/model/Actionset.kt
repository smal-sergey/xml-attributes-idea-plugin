package com.smalser.plugin.dom.model

import com.intellij.util.xml.*

interface Actionset : DomElement {
    fun getActions() : List<Action>

    @Required
    @NameValue
    @Attribute("name")
    fun getAttributeName(): GenericAttributeValue<String>
}