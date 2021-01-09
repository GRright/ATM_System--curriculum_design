package com.example.bank.controller;

import com.example.bank.dao.User;
import com.example.bank.mapper.userMapper;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;

@RestController
public class BankController {

    @Resource
    private userMapper userMapper;

//    @Resource
//    private MessageService messageService;
protected Logger logger = LogManager.getLogger(BankController.class);

    @ResponseBody
    @RequestMapping("/login1")
    public Boolean login(User user , HttpServletRequest request){
       User users = userMapper.selectByPrimaryKey(user.getAccount());
        if (users == null) {
            logger.info("user不存在");
            return false;
        }
        if(users.getPassword().equals(user.getPassword())){
            HttpSession session = request.getSession();
            session.setAttribute("account" , user.getAccount());
            return true;
        }else {
            return false;
        }
    }


    /**
     * 存钱
     * @param account 卡号
     * @param pay_password 支付密码
     * @param money 存款金额
     * @return 是否成功
     */
    @ResponseBody
    @RequestMapping("/saveMoney")
    public Boolean saveMoney(User user){
        User users = userMapper.selectByPrimaryKey(user.getAccount());
        if (users == null) {
            return false;
        }
        BigDecimal Money = users.getMoney().add(user.getMoney());
        users.setMoney(Money);
        int i =userMapper.updateByPrimaryKey(users);
        return i==1 ;

    }

    /**
     * 取钱
     * @param account 卡号
     * @param pay_password 支付密码
     * @param money 取钱金额
     * @return 成功与否
     */
    @ResponseBody
    @RequestMapping("/withdrawMoney")
    public Boolean withdrawMoney( User user){
        User users = userMapper.selectByPrimaryKey(user.getAccount());
        if (users == null) {
            return false;
        }
        if(users.getPay_password().equals(user.getPassword())){
            BigDecimal Money = users.getMoney().subtract(user.getMoney());
            users.setMoney(Money);
            int i =userMapper.updateByPrimaryKey(users);
            return i==1 ;
        }else{
            return false;
        }
    }

    @ResponseBody
    @RequestMapping("/show")
    public User show(HttpServletRequest request){
        HttpSession session = request.getSession();
        return userMapper.selectByPrimaryKey(session.getAttribute("account"));
    }

    /**
     *转账
     * @return 是否成功
     */
    @ResponseBody
    @RequestMapping("/Transfer")
    public Boolean Transfer( User user){
        User users = userMapper.selectByPrimaryKey(user.getAccount());
        if (users == null) {
            return false;
        }
        if(users.getPay_password().equals(user.getPay_password())){
            User otherUser = userMapper.selectByPrimaryKey(user.getOtherAccount());
            User myUser = userMapper.selectByPrimaryKey(user.getAccount());
            if(myUser.getMoney().compareTo(user.getMoney())==-1){
                return false;
            }else {
                BigDecimal Money = otherUser.getMoney().add(user.getMoney());
                otherUser.setMoney(Money);
                System.out.println(otherUser.getAccount());
                myUser.setMoney(myUser.getMoney().subtract(user.getMoney()));
                userMapper.updateByPrimaryKey(myUser);
                int i = userMapper.updateByPrimaryKey(otherUser);
                return i == 1;
            }
        }else{
            return false;
        }
    }

}
