package me.chanjar.weixin.enterprise.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import me.chanjar.weixin.enterprise.api.WxConsts;
import me.chanjar.weixin.enterprise.util.xml.AdapterCDATA;

@XmlRootElement(name = "xml")
@XmlAccessorType(XmlAccessType.FIELD)
public class WxXmlOutTextMessage extends WxXmlOutMessage {
  
  @XmlElement(name="Content")
  @XmlJavaTypeAdapter(AdapterCDATA.class)
  private String content;

  public WxXmlOutTextMessage() {
    this.msgType = WxConsts.XML_MSG_TEXT;
  }
  
  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  
}
