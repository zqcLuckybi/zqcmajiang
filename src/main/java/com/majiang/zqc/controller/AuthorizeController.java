package com.majiang.zqc.controller;

import com.majiang.zqc.dto.AccessTokenDTO;
import com.majiang.zqc.dto.GithubUser;
import com.majiang.zqc.provider.GetHubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorizeController {
    @Autowired
    private GetHubProvider getHubProvider;
    @Value("${github.client.id}")
    private String clientid;
    @Value("${github.client.secretid}")
    private String secretid;
    @Value("${github.client.sru}")
    private String sru;

    @RequestMapping("/callback")
    public String callback(@RequestParam(name="code") String code,@RequestParam(name ="state") String state){
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setCode(code);
        accessTokenDTO.setClientId(clientid);
        accessTokenDTO.setClientSecret(secretid);
        accessTokenDTO.setState(state);
        accessTokenDTO.setRedirectUri(sru);
        String accessToken = getHubProvider.getAccessToken(accessTokenDTO);
        GithubUser user = getHubProvider.getUser(accessToken);
        String name = user.getName();
        System.out.println(name);
        return "index";
    }
}
