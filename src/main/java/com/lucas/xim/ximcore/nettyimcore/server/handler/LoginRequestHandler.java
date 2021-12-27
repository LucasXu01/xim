package com.lucas.xim.ximcore.nettyimcore.server.handler;

import com.lucas.xim.XimApplication;
import com.lucas.xim.core.system.User;
import com.lucas.xim.service.RedisTemplateService;
import com.lucas.xim.service.UserService;
import com.lucas.xim.utils.JWTUtil;
import com.lucas.xim.utils.SpringUtil;
import com.lucas.xim.ximcore.nettyimcore.protocal.request.LoginRequestPacket;
import com.lucas.xim.ximcore.nettyimcore.protocal.response.LoginResponsePacket;
import com.lucas.xim.ximcore.nettyimcore.session.Session;
import com.lucas.xim.ximcore.nettyimcore.util.SessionUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.lucas.xim.BaseConstants.*;


@ChannelHandler.Sharable
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {

    private static final Logger logger = LoggerFactory.getLogger(XimApplication.class);

    RedisTemplateService redisTemplateService=(RedisTemplateService) SpringUtil.getBean(RedisTemplateService.class);
    UserService userService=(UserService) SpringUtil.getBean(UserService.class);

    public static final LoginRequestHandler INSTANCE = new LoginRequestHandler();

    protected LoginRequestHandler() {
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket loginRequestPacket) {
        // 规定前端只传 mobile  和  token
        LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
        loginResponsePacket.setVersion(loginRequestPacket.getVersion());
        loginResponsePacket.setUserId(loginRequestPacket.getUserId());


        User user = userService.queryUserByUid(loginRequestPacket.getUserId());
        if (user == null) {
            // 如果不存在，返回用户不存在
            loginResponsePacket.setSuccess(false);
            loginResponsePacket.setReason(FAIL_LOGIN_WRONG_USER+"");
            logger.warn("[" + loginRequestPacket.getUserId() + "]登陆失败，用户不存在！");
        }else if (!JWTUtil.verify(loginRequestPacket.getToken())){
            // token 错误
            loginResponsePacket.setSuccess(false);
            loginResponsePacket.setReason(FAIL_LOGIN_WRONG_SIG+"");
            logger.warn("[" + loginRequestPacket.getUserId() + "]登陆失败，用户token错误！");
        }else {
            loginResponsePacket.setSuccess(true);
            loginResponsePacket.setMobile(user.getMobile());
            loginResponsePacket.setReason(SEC_LOGIN+"");

            // 存redis   maintain online members list
            redisTemplateService.set_add("online_members", user.get_id());
            // TODO: 2021/12/16 这里绑定的是mobile，需要改成uid
            SessionUtil.bindSession(new Session(user.get_id(), loginRequestPacket.getMobile()), ctx.channel());

            logger.warn("[" + loginRequestPacket.getUserId() + "]登陆成功");
        }


        // 登录响应
        ctx.writeAndFlush(loginResponsePacket);
    }


    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        SessionUtil.unBindSession(ctx.channel());
    }
}
