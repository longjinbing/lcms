package com.ljb.swagger2;

import com.ljb.annotion.MenuDescription;
import org.springframework.stereotype.Controller;

/**
 * 作者: @author longjinbin <br>
 * 时间: 2018/12/17<br>
 * 描述: <br>
 */
@Controller
@MenuDescription(group="开发工具",name="接口管理", action ="swagger/swagger-ui.html")
public class SwaggerController {

}
