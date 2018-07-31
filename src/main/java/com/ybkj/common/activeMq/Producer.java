package com.ybkj.common.activeMq;

import com.alibaba.fastjson.JSONObject;
import com.ybkj.common.mqMessage.AuthCodeMessage;
import com.ybkj.common.mqMessage.AuthCodeMessageBody;
import com.ybkj.common.mqMessage.ServerInWareHouseBody;
import com.ybkj.common.mqMessage.ServerInWareHouseMessage;
import com.ybkj.common.util.DataTool;
import com.ybkj.common.util.ProgressiveIncreaseNumber;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.jms.MessageEOFException;
import javax.jms.Queue;
import java.text.ParseException;

@SuppressWarnings("all")
@Slf4j
@Component
@EnableScheduling
@Transactional
public class Producer {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;
    @Autowired
    private Queue queue;
    @Autowired
    private DataTool dataTool;
    @Autowired
    private ProgressiveIncreaseNumber progressiveIncreaseNumber;



    //@Scheduled(fixedDelay=5000)

    /**
     * 出库报文消息
     * @param bluetoothMac:枪支蓝牙
     * @param gunTag：枪编码
     * @param applyTime：领用时间
     * @param deadlineTime：归还时间
     * @throws MessageEOFException
     */
    public boolean sendMessageDelivery(String bluetoothMac, String gunTag, String applyTime, String deadlineTime,String deviceNo) throws MessageEOFException, ParseException {
        AuthCodeMessage authCodeMessageBody = new AuthCodeMessage();
        AuthCodeMessageBody authSunBody = new AuthCodeMessageBody();
        for (int i = 0; i < 1; i++) {
            //报文体
            authSunBody.setReserve("");
            authSunBody.setBluetoothMac(bluetoothMac);
            authSunBody.setGunTag(gunTag);
            authSunBody.setApplyTime(applyTime);
            authSunBody.setDeadlineTime(deadlineTime);
            authSunBody.setDeivceNo(deviceNo);
            authSunBody.setPowerAlarmLevel("");
            authSunBody.setTransmittingPower("");
            authSunBody.setBroadcastInterval("");
            authSunBody.setConnectionInterval("");
            authSunBody.setConnectionTimeout("");
            authSunBody.setSoftwareversion("");
            authSunBody.setHeartbeat("");
            authSunBody.setPowerSampling("");
            authSunBody.setSystemTime(dataTool.dateToString());
            authSunBody.setMatchTime("");
            authSunBody.setSafeCode("");

            authCodeMessageBody.setServiceType("BTOFFPOSITIONALARM");//报文唯一标识：默认.BTOFFPOSITIONALARM
            authCodeMessageBody.setFormatVersion("1.0");//格式版本
            authCodeMessageBody.setDeviceType(1);//设备类型：1.随行设备 2.离位报警器 3.腕表
            authCodeMessageBody.setSerialNumber(dataTool.dateToString()+progressiveIncreaseNumber.getNumber(i));//交易流水号:yyyyMMddHHmmss+循环递增0-9999
            authCodeMessageBody.setMessageType("03");//报文类型： 03:出库下发报文
            authCodeMessageBody.setMessageBody(authSunBody);//报文消息体
            authCodeMessageBody.setSendTime(dataTool.dateToString());//发报时间：系统时间

            String jsonString = JSONObject.toJSONString(authCodeMessageBody);
            //一对一发送
            jmsMessagingTemplate.convertAndSend(queue,jsonString);
            log.info("************出库报文信息**************：" + jsonString);
            return true;
        }
       // String reuslt = "测试消息队列：" + System.currentTimeMillis();
        return false;
    }

    /**
     * 入库信息报文
     * @param bluetoothMac:枪支ID
     * @param authCode:授权码
     */
    public boolean sendMessageStorage(String bluetoothMac,String authCode,String deviceNo) throws MessageEOFException, ParseException{
        ServerInWareHouseMessage authCodeMessageBody = new ServerInWareHouseMessage();
        ServerInWareHouseBody inWareHouseBody = new ServerInWareHouseBody();
        for (int i = 0; i < 1; i++) {
            //报文体
            inWareHouseBody.setBluetoothMac(bluetoothMac);
            inWareHouseBody.setAuthCode(authCode);
            inWareHouseBody.setDeivceNo(deviceNo);

            authCodeMessageBody.setServiceType("BTOFFPOSITIONALARM");//报文唯一标识：默认.BTOFFPOSITIONALARM
            authCodeMessageBody.setFormatVersion("1.0");//格式版本
            authCodeMessageBody.setDeviceType(1);//设备类型：1.随行设备 2.离位报警器 3.腕表
            authCodeMessageBody.setSerialNumber(dataTool.dateToString()+progressiveIncreaseNumber.getNumber(i));//交易流水号:yyyyMMddHHmmss+循环递增0-9999
            authCodeMessageBody.setMessageType("09");//报文类型： 09:入库下发报文
            authCodeMessageBody.setServerInWareHouseBody(inWareHouseBody);//报文消息体
            authCodeMessageBody.setSendTime(dataTool.dateToString());//发报时间：系统时间

            String jsonString = JSONObject.toJSONString(authCodeMessageBody);
            jmsMessagingTemplate.convertAndSend(queue,jsonString);
            log.info("************入库报文信息**************：" + jsonString);
            return true;
        }
        return false;
    }


}

