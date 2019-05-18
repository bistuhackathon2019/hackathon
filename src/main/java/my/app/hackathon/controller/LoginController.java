package my.app.hackathon.controller;

import com.alibaba.fastjson.JSONObject;
import my.app.hackathon.entity.User;
import my.app.hackathon.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class LoginController {
    @Autowired
    UserRepository userRepository;

    @RequestMapping("/user/check/{phone}")
    public String userCheckByPhone(@PathVariable("phone") String phone){
        User user = userRepository.findByPhone(phone);
        Map<String,String> map = new HashMap<>();
        if(user!=null) {
            map.put("msg", "succuss");
            map.put("isExist", "true");
        }else{
            map.put("msg", "fail");
            map.put("isExist", "false");
        }
        return JSONObject.toJSONString(map);
    }

    /**
     * 判断是否登录
     * @param id
     * @param sessionKey
     * @return
     */
//    @RequestMapping("/check")
    public boolean userCheckSession(Integer id,String sessionKey){
        Map<String,String> res = new HashMap<>();//返回信息
        if (id!=null && !StringUtils.isEmpty(sessionKey)) {
            Optional<User> user = userRepository.findById(id);
            if(Objects.equals(user.get().getSessionKey(),sessionKey)){//sessionKey验证
                return true;
            }else{
                return false;
            }
        }else{
            //不存在
            return false;
        }
    }

    @RequestMapping("/login")
    public String login(User user,@RequestParam Map<String,String> map){
        Map<String,Object> resMap = new HashMap<>();
        if (StringUtils.isEmpty(user.getPhone())){//判断手机是否为空
            resMap.put("msg","手机号为空");
            resMap.put("loginState","false");
            return JSONObject.toJSONString(resMap);
        }
        if (userRepository.findByPhone(user.getPhone())==null){//没有用户则添加
            String sessionKey = user.getPhone()+new Date().getTime();
            user.setSessionKey(sessionKey);
            userRepository.save(user);
            resMap.put("msg","登录成功");
            resMap.put("loginState","true");
            resMap.put("sessionKey",user.getSessionKey());
            resMap.put("user",user);
            return JSONObject.toJSONString(resMap);//有用户则返回sessionkey
        }else{//用户存在
            User resUser = userRepository.findByPhone(user.getPhone());//数据库中的用户
            String sessionKey = resUser.getPhone()+new Date().getTime();
            resUser.setSessionKey(sessionKey);
            resMap.put("msg","登录成功");
            resMap.put("loginState","true");
            resMap.put("sessionKey",resUser.getSessionKey());
            resMap.put("user",resUser);
            userRepository.save(resUser);
            return JSONObject.toJSONString(resMap);
        }
    }
}
