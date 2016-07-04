package me.chanjar.weixin.mp.api;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;

import me.chanjar.weixin.common.util.xml.XStreamInitializer;
import me.chanjar.weixin.mp.api.impl.WxMpKefuServiceImpl;

public class ApiTestModule implements Module {

  @Override
  public void configure(Binder binder) {
    try (InputStream is1 = ClassLoader
        .getSystemResourceAsStream("test-config.xml")) {
      WxXmlMpInMemoryConfigStorage config = fromXml(
          WxXmlMpInMemoryConfigStorage.class, is1);
      WxMpServiceImpl wxService = new WxMpServiceImpl();
      wxService.setWxMpConfigStorage(config);
      WxMpKefuService customerService = new WxMpKefuServiceImpl();
      wxService.setCustomerService(customerService);

      binder.bind(WxMpServiceImpl.class).toInstance(wxService);
      binder.bind(WxMpConfigStorage.class).toInstance(config);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static <T> T fromXml(Class<T> clazz, InputStream is) {
    XStream xstream = XStreamInitializer.getInstance();
    xstream.alias("xml", clazz);
    xstream.processAnnotations(clazz);
    return (T) xstream.fromXML(is);
  }

  @XStreamAlias("xml")
  public static class WxXmlMpInMemoryConfigStorage
      extends WxMpInMemoryConfigStorage {

    protected String openId;

    public String getOpenId() {
      return this.openId;
    }

    public void setOpenId(String openId) {
      this.openId = openId;
    }

    @Override
    public String toString() {
      return ToStringBuilder.reflectionToString(this);
    }

  }

}
