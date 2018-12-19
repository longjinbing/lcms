package com.ljb.actuator;

import com.ljb.Base.BaseController;
import com.ljb.utils.R;
import com.ljb.utils.SizeUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * 作者: @author longjinbin <br>
 * 时间: 2018/11/23<br>
 * 描述: <br>
 */
@RestController
@RequestMapping("/actuator")
public class DramController extends BaseController {
    private static DecimalFormat DECIMALFORMAT = new DecimalFormat("#.##");

    @RequestMapping("/info")
    public R info(){
        Map<String,Object> map=new HashMap<>();
        return R.ok().put("data", map);
    }

    @RequestMapping("/jvm")
    public R jvm(){
        Map<String,Object> map=new HashMap<>();
        map.put("totalMemory", SizeUtils.convertSize(Runtime.getRuntime().totalMemory()));
        map.put("freeMemory", SizeUtils.convertSize(Runtime.getRuntime().freeMemory()));
        map.put("maxMemory", SizeUtils.convertSize(Runtime.getRuntime().maxMemory()));
        return R.ok().put("data", map);
    }
    @RequestMapping("/disk")
    public R disk(HttpServletRequest request) {
        String path=request.getServletContext().getRealPath("/");
        File file = new File(path);
        Map<String, String> map = new HashMap<String, String>();
        long freeSpace = file.getFreeSpace();
        long totalSpace = file.getTotalSpace();
        long usableSpace = totalSpace - freeSpace;
        map.put("freeMemory", freeSpace / 1024 / 1024 / 1024 + "G");// 空闲空间
        map.put("usageMemory", usableSpace / 1024 / 1024 / 1024 + "G");// 可用空间
        map.put("totalMemory", totalSpace / 1024 / 1024 / 1024 + "G");// 总空间
        map.put("percent", DECIMALFORMAT.format(((double) usableSpace / (double) totalSpace) * 100) + "%");// 总空间
       return R.ok().put("data", map);
    }
}
