package chanjarster.weixin.bean;

import org.apache.http.util.Asserts;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import chanjarster.weixin.bean.WxMenu.WxMenuButton;

@Test
public class WxMenuTest {

  @Test(dataProvider="json")
  public void testFromJson(String json) {
    WxMenu menu = WxMenu.fromJson(json);
    Assert.assertEquals(menu.getButton().size(), 3);
  }
  
  @Test(dataProvider="json")
  public void testToJson(String json) {
    WxMenu menu = new WxMenu();
    WxMenuButton button1 = new WxMenuButton();
    button1.setType("click");
    button1.setName("今日歌曲");
    button1.setKey("V1001_TODAY_MUSIC");
    
    WxMenuButton button2 = new WxMenuButton();
    button2.setType("click");
    button2.setName("歌手简介");
    button2.setKey("V1001_TODAY_SINGER");
    
    WxMenuButton button3 = new WxMenuButton();
    button3.setName("菜单");
    
    menu.getButton().add(button1);
    menu.getButton().add(button2);
    menu.getButton().add(button3);
    
    WxMenuButton button31 = new WxMenuButton();
    button31.setType("view");
    button31.setName("搜索");
    button31.setUrl("http://www.soso.com/");
    
    WxMenuButton button32 = new WxMenuButton();
    button32.setType("view");
    button32.setName("视频");
    button32.setUrl("http://v.qq.com/");
    
    WxMenuButton button33 = new WxMenuButton();
    button33.setType("click");
    button33.setName("赞一下我们");
    button33.setKey("V1001_GOOD");
    
    button3.getSub_button().add(button31);
    button3.getSub_button().add(button32);
    button3.getSub_button().add(button33);
    
    System.out.println(menu.toJson());
    Assert.assertEquals(menu.toJson(), json);
  }
  
  @DataProvider(name="json")
  public Object[][] getMenuJson() {
    String json = 
        "{"
            +"\"button\":["
              +"{"
                +"\"type\":\"click\","
                +"\"name\":\"今日歌曲\","
                +"\"key\":\"V1001_TODAY_MUSIC\""
              +"},"
              +"{"
                +"\"type\":\"click\","
                +"\"name\":\"歌手简介\","
                +"\"key\":\"V1001_TODAY_SINGER\""
              +"},"
              +"{"
                +"\"name\":\"菜单\","
                +"\"sub_button\":["
                  +"{"
                    +"\"type\":\"view\","
                    +"\"name\":\"搜索\","
                    +"\"url\":\"http://www.soso.com/\""
                  +"},"
                  +"{"
                    +"\"type\":\"view\","
                    +"\"name\":\"视频\","
                    +"\"url\":\"http://v.qq.com/\""
                  +"},"
                  +"{"
                  +"\"type\":\"click\","
                  +"\"name\":\"赞一下我们\","
                  +"\"key\":\"V1001_GOOD\""
                  +"}"
                +"]"
              +"}"
            +"]"
        +"}";
    return new Object[][] {
        new Object[] { json }
    };
  }
  
}
