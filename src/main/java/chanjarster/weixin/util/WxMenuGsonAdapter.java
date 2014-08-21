/*
 * KINGSTAR MEDIA SOLUTIONS Co.,LTD. Copyright c 2005-2013. All rights reserved.
 *
 * This source code is the property of KINGSTAR MEDIA SOLUTIONS LTD. It is intended
 * only for the use of KINGSTAR MEDIA application development. Reengineering, reproduction
 * arose from modification of the original source, or other redistribution of this source
 * is not permitted without written permission of the KINGSTAR MEDIA SOLUTIONS LTD.
 */
package chanjarster.weixin.util;

import java.lang.reflect.Type;

import chanjarster.weixin.bean.WxMenu;
import chanjarster.weixin.bean.WxMenu.WxMenuButton;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

/**
 * 
 * @author qianjia
 *
 */
public class WxMenuGsonAdapter implements JsonSerializer<WxMenu>, JsonDeserializer<WxMenu> {

  public JsonElement serialize(WxMenu menu, Type typeOfSrc, JsonSerializationContext context) {
    JsonObject json = new JsonObject();

    JsonArray buttonArray = new JsonArray();
    for (WxMenuButton button : menu.getButton()) {
      JsonObject buttonJson = convertToJson(button);
      buttonArray.add(buttonJson);
    }
    json.add("button", buttonArray);
    
    return json;
  }

  protected JsonObject convertToJson(WxMenuButton button) {
    JsonObject buttonJson = new JsonObject();
    buttonJson.addProperty("type", button.getType());
    buttonJson.addProperty("name", button.getName());
    buttonJson.addProperty("key", button.getKey());
    buttonJson.addProperty("url", button.getUrl());
    if (button.getSub_button() != null && button.getSub_button().size() > 0) {
      JsonArray buttonArray = new JsonArray();
      for (WxMenuButton sub_button : button.getSub_button()) {
        buttonArray.add(convertToJson(sub_button));
      }
      buttonJson.add("sub_button", buttonArray);
    }
    return buttonJson;
  }

  public WxMenu deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
    WxMenu menu = new WxMenu();
    JsonArray buttonsJson = json.getAsJsonObject().get("button").getAsJsonArray();
    for (int i = 0; i < buttonsJson.size(); i++) {
      JsonObject buttonJson = buttonsJson.get(i).getAsJsonObject();
      WxMenuButton button = convertFromJson(buttonJson);
      menu.getButton().add(button);
      if (buttonJson.get("sub_button") == null || buttonJson.get("sub_button").isJsonNull()) {
        continue;
      }
      JsonArray sub_buttonsJson = buttonJson.get("sub_button").getAsJsonArray();
      for (int j = 0; j < sub_buttonsJson.size(); j++) {
        JsonObject sub_buttonJson = sub_buttonsJson.get(j).getAsJsonObject();
        button.getSub_button().add(convertFromJson(sub_buttonJson));
      }
    }
    return menu;
  }
  
  protected WxMenuButton convertFromJson(JsonObject json) {
    WxMenuButton button = new WxMenuButton();
    button.setName(GsonHelper.getString(json, "name"));
    button.setKey(GsonHelper.getString(json, "key"));
    button.setUrl(GsonHelper.getString(json, "url"));
    button.setType(GsonHelper.getString(json, "type"));
    return button;
  }

}