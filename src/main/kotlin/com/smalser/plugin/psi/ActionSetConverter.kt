package com.smalser.plugin.psi

import com.intellij.psi.xml.XmlAttribute
import com.intellij.util.xml.ConvertContext
import com.intellij.util.xml.ResolvingConverter

class ActionSetConverter(): ResolvingConverter<XmlAttribute>() {
    override fun toString(t: XmlAttribute?, context: ConvertContext?): String? {
        TODO("Not yet implemented")
    }

    override fun fromString(s: String?, context: ConvertContext?): XmlAttribute? {
        TODO("Not yet implemented")
    }

    override fun getVariants(context: ConvertContext?): MutableCollection<out XmlAttribute> {
        TODO("Not yet implemented")
    }
}