package com.ljb.sso;

import org.springframework.security.oauth2.provider.AuthorizationRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 作者: @author longjinbin <br>
 * 时间: 2018/11/16<br>
 * 描述: <br>
 */
@Controller
@RequestMapping("oauth")
@SessionAttributes("authorizationRequest")
public class OauthController {

    @RequestMapping("/confirm")
    public String access(Map<String, Object> map, Model model, HttpServletRequest request){
        AuthorizationRequest authorizationRequest = (AuthorizationRequest)map.get("authorizationRequest");
        model.addAttribute("clientId", authorizationRequest.getClientId());
        model.addAttribute("scope", authorizationRequest.getScope());
        return "oauth/confirm";
    }

}
