package com.ybkj.common.activeMq;

import com.alibaba.fastjson.JSONObject;
import com.ybkj.common.activeMq.messageBody.AuthCodeMessage;
import com.ybkj.common.activeMq.messageBody.AuthCodeMessageBody;
import com.ybkj.common.activeMq.messageBody.ServerInWareHouseBody;
import com.ybkj.common.activeMq.messageBody.ServerInWareHouseMessage;
import com.ybkj.common.constant.StatusCodeEnum;
import com.ybkj.common.model.BaseModel;
import com.ybkj.common.util.DataTool;
import com.ybkj.common.util.ProgressiveIncreaseNumber;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.jms.*;
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
     *
     * @param bluetoothMac:枪支蓝牙
     * @param gunTag：枪编码
     * @param applyTime：领用时间
     * @param deadlineTime：归还时间
     * @throws MessageEOFException
     */
    public BaseModel sendMessageDelivery(String bluetoothMac, String gunTag, String applyTime, String deadlineTime, String deviceNo) throws ParseException {
        BaseModel baseModel = new BaseModel();
        AuthCodeMessage authCodeMessageBody = new AuthCodeMessage();
        AuthCodeMessageBody messageBody = new AuthCodeMessageBody();
        for (int i = 0; i < 1; i++) {
            //报文体
            messageBody.setReserve("11");
            messageBody.setBluetoothMac(bluetoothMac);
            messageBody.setGunTag(gunTag);
            messageBody.setApplyTime(applyTime);
            messageBody.setDeadlineTime(deadlineTime);
            messageBody.setDeviceNo(deviceNo);
            messageBody.setPowerAlarmLevel("11");
            messageBody.setTransmittingPower("11");
            messageBody.setBroadcastInterval("11");
            messageBody.setConnectionInterval("11");
            messageBody.setConnectionTimeout("11");
            messageBody.setSoftwareversion("11");
            messageBody.setHeartbeat("11");
            messageBody.setPowerSampling("11");
            messageBody.setSystemTime(dataTool.dateToString());
            messageBody.setMatchTime("11");
            messageBody.setSafeCode("11");

            authCodeMessageBody.setServiceType("BTOFFPOSITIONALARM");//报文唯一标识：默认.BTOFFPOSITIONALARM
            authCodeMessageBody.setFormatVersion("1.0");//格式版本
            authCodeMessageBody.setDeviceType(1);//设备类型：1.随行设备 2.离位报警器 3.腕表
            authCodeMessageBody.setSerialNumber(dataTool.dateToString() + progressiveIncreaseNumber.getNumber(i));//交易流水号:yyyyMMddHHmmss+循环递增0-9999
            authCodeMessageBody.setMessageType("03");//报文类型： 03:出库下发报文
            authCodeMessageBody.setMessageBody(messageBody);//报文消息体
            authCodeMessageBody.setSendTime(dataTool.dateToString());//发报时间：系统时间

            String jsonString = JSONObject.toJSONString(authCodeMessageBody);
            //一对一发送
            try {
                jmsMessagingTemplate.convertAndSend(queue, jsonString);
                log.info("************出库报文信息**************：" + jsonString);
            } catch (Exception e) {
                baseModel.setStatus(StatusCodeEnum.Fail.getStatusCode());
                e.printStackTrace();
            }
            return baseModel;
        }
        // String reuslt = "测试消息队列：" + System.currentTimeMillis();
        return baseModel;
    }

    /**
     * 入库信息报文
     *
     * @param bluetoothMac:枪支ID
     * @param authCode:授权码
     */
    public BaseModel sendMessageStorage(String bluetoothMac, String authCode, String deviceNo) throws MessageEOFException, ParseException {
        BaseModel baseModel = new BaseModel();
        ServerInWareHouseMessage authCodeMessageBody = new ServerInWareHouseMessage();
        ServerInWareHouseBody messageBody = new ServerInWareHouseBody();
        for (int i = 0; i < 1; i++) {
            //报文体
            messageBody.setBluetoothMac(bluetoothMac);
            messageBody.setAuthCode(authCode);
            messageBody.setDeviceNo(deviceNo);

            authCodeMessageBody.setServiceType("BTOFFPOSITIONALARM");//报文唯一标识：默认.BTOFFPOSITIONALARM
            authCodeMessageBody.setFormatVersion("1.0");//格式版本
            authCodeMessageBody.setDeviceType(1);//设备类型：1.随行设备 2.离位报警器 3.腕表
            authCodeMessageBody.setSerialNumber(dataTool.dateToString() + progressiveIncreaseNumber.getNumber(i));//交易流水号:yyyyMMddHHmmss+循环递增0-9999
            authCodeMessageBody.setMessageType("09");//报文类型： 09:入库下发报文
            authCodeMessageBody.setMessageBody(messageBody);//报文消息体
            authCodeMessageBody.setSendTime(dataTool.dateToString());//发报时间：系统时间

            String jsonString = JSONObject.toJSONString(authCodeMessageBody);
            try {
                jmsMessagingTemplate.convertAndSend(queue, jsonString);
                log.info("************入库报文信息**************：" + jsonString);
            } catch (Exception e) {
                baseModel.setStatus(StatusCodeEnum.Fail.getStatusCode());
                e.printStackTrace();
            }
            return baseModel;
        }
        return baseModel;
    }


}

