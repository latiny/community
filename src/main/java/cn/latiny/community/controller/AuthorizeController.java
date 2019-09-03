package cn.latiny.community.controller;

import cn.latiny.community.domain.UserDomain;
import cn.latiny.community.model.AccessTokenDTO;
import cn.latiny.community.model.GithubUser;
import cn.latiny.community.provider.GithubProvider;
import cn.latiny.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Latiny
 * @version 1.0
 * @description: AuthorizeController
 * @date 2019/9/2 17:45
 */
@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;
    @Autowired
    private UserService userService;
    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.redirect.uri}")
    private String redirectUri;
    @Value("${github.client.secret}")
    private String clientSecret;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                           HttpServletRequest request) {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(redirectUri);
        accessTokenDTO.setState(state);
        accessTokenDTO.setClient_secret(clientSecret);
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GithubUser user = githubProvider.getUser(accessToken);
        if (null != user) {
            request.getSession().setAttribute("user", user);
            UserDomain domain = new UserDomain();
            domain.setUserId(String.valueOf(user.getId()));
            domain.setName(user.getName());
            domain.setToken(accessToken);
            domain.setCreateTime(System.currentTimeMillis());
            domain.setUpdateTime(System.currentTimeMillis());
            userService.insert(domain);
        }
        return "redirect:/index";
    }
}
