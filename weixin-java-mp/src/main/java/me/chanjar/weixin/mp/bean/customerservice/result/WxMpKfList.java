package me.chanjar.weixin.mp.bean.customerservice.result;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.google.gson.annotations.SerializedName;

import me.chanjar.weixin.mp.util.json.WxMpGsonBuilder;
/**
 * 
 * @author Binary Wang
 *
 */
public class WxMpKfList {
  @SerializedName("kf_list")
  private List<WxMpKfInfo> kfList;

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this);
  }

  public List<WxMpKfInfo> getKfList() {
    return this.kfList;
  }

  public void setKfList(List<WxMpKfInfo> kfList) {
    this.kfList = kfList;
  }  
  
  public static WxMpKfList fromJson(String json) {
    return WxMpGsonBuilder.INSTANCE.create().fromJson(json, WxMpKfList.class);
  }
}
