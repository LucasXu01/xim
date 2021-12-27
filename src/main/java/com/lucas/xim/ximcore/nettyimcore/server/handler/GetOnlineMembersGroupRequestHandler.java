package com.lucas.xim.ximcore.nettyimcore.server.handler;

import com.lucas.xim.XimApplication;
import com.lucas.xim.service.RedisTemplateService;
import com.lucas.xim.service.UserService;
import com.lucas.xim.utils.SpringUtil;
import com.lucas.xim.ximcore.nettyimcore.protocal.request.CreateGroupRequestPacket;
import com.lucas.xim.ximcore.nettyimcore.protocal.request.GetOnlineMembersRequestPacket;
import com.lucas.xim.ximcore.nettyimcore.protocal.response.CreateGroupResponsePacket;
import com.lucas.xim.ximcore.nettyimcore.protocal.response.GetOnlineMembersResponsePacket;
import com.lucas.xim.ximcore.nettyimcore.util.IDUtil;
import com.lucas.xim.ximcore.nettyimcore.util.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 获取在线人员列表
 */
@ChannelHandler.Sharable
public class GetOnlineMembersGroupRequestHandler extends SimpleChannelInboundHandler<GetOnlineMembersRequestPacket> {
    public static final GetOnlineMembersGroupRequestHandler INSTANCE = new GetOnlineMembersGroupRequestHandler();

    private GetOnlineMembersGroupRequestHandler() {

    }

    private static final Logger logger = LoggerFactory.getLogger(XimApplication.class);
    RedisTemplateService redisTemplateService=(RedisTemplateService) SpringUtil.getBean(RedisTemplateService.class);


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GetOnlineMembersRequestPacket getOnlineMembersRequestPacket) {

        List<String> userNameList = new ArrayList<>();
        Set<String> set =  redisTemplateService.set_get("online_members");


        GetOnlineMembersResponsePacket getOnlineMembersResponsePacket = new GetOnlineMembersResponsePacket();
        getOnlineMembersResponsePacket.setSuccess(true);
        getOnlineMembersResponsePacket.setUidset(set);

        // 登录响应
        ctx.writeAndFlush(getOnlineMembersResponsePacket);
        logger.debug("获取在线人员列表成功！");
    }
}
