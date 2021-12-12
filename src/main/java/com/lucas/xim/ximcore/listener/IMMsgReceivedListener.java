package com.lucas.xim.ximcore.listener;


import com.lucas.xim.ximcore.bean.IMMsg;

/**
 * @Description:
 * @Auther: LucasXu
 * @email: 18140041@bjtu.edu.cn
 * @github: https://github.com/LucasXu01
 * @Date: 2021/12/09/15:50 下午
 */
public interface IMMsgReceivedListener {

    void onMsgReceived(IMMsg msg);

}
