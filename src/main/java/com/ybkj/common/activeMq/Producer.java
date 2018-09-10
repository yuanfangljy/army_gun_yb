package com.ybkj.common.activeMq;

import com.alibaba.fastjson.JSONObject;
import com.ybkj.common.activeMq.messageBody.*;
import com.ybkj.common.constant.StatusCodeEnum;
import com.ybkj.common.model.BaseModel;
import com.ybkj.common.util.DataTool;
import com.ybkj.common.util.ProgressiveIncreaseNumber;
import com.ybkj.common.util.TokenUtils;
import com.ybkj.gun.mapper.GunMapper;
import com.ybkj.gun.model.Gun;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.jms.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;

@SuppressWarnings("all")
@Slf4j
@Component
@EnableScheduling
@Transactional
public class Producer {

    @Autowired private JmsMessagingTemplate jmsMessagingTemplate;
    @Autowired private Queue storageQueue;
    @Autowired private Queue deliveryQueue;
    @Autowired private Queue helpFindQueue;
    @Autowired private Queue startAndStopQueue;
    @Autowired private DataTool dataTool;
    @Autowired private ProgressiveIncreaseNumber progressiveIncreaseNumber;
    @Autowired HttpServletRequest requests;
    @Autowired GunMapper gunMapper;
    @Value("${powerAlarmLevel}") private String powerAlarmLevel;
    @Value("${transmittingPower}") private String transmittingPower;
    @Value("${broadcastInterval}") private String broadcastInterval;
    @Value("${connectionInterval}") private String connectionInterval;
    @Value("${connectionTimeout}") private String connectionTimeout;
    @Value("${softwareversion}") private String softwareversion;
    @Value("${heartbeat}") private String heartbeat;
    @Value("${powerSampling}") private String powerSampling;

    /*HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();*/

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
        Gun gun = gunMapper.selectGunByBluetoothMac(bluetoothMac);
        if(gun==null){
            baseModel.setErrorMessage("该枪的枪支类型不存在");
            baseModel.setStatus(StatusCodeEnum.Fail.getStatusCode());
            return baseModel;
        }
        HttpSession session = requests.getSession();

        AuthCodeMessage authCodeMessageBody = new AuthCodeMessage();
        AuthCodeMessageBody messageBody = new AuthCodeMessageBody();
        for (int i = 0; i < 1; i++) {
            //报文体
            messageBody.setReserve("11");
            messageBody.setBluetoothMac(bluetoothMac);
            messageBody.setGunTag(gun.getGunModel()+"+"+gunTag);
            messageBody.setApplyTime(applyTime);
            messageBody.setDeadlineTime(deadlineTime);
            messageBody.setDeviceNo(deviceNo);
            messageBody.setPowerAlarmLevel(powerAlarmLevel);//电量报警级别
            messageBody.setTransmittingPower(transmittingPower);//发射功率
            messageBody.setBroadcastInterval(broadcastInterval);//广播间隔
            messageBody.setConnectionInterval(connectionInterval);//连接间隔
            messageBody.setConnectionTimeout(connectionTimeout);//连接超时
            messageBody.setSoftwareversion(softwareversion);//软硬件版本
            messageBody.setHeartbeat(heartbeat);//心跳间隔
            messageBody.setPowerSampling(powerSampling);
            messageBody.setSystemTime(dataTool.dateToString());
            messageBody.setMatchTime("11");
            messageBody.setSafeCode("11");

            authCodeMessageBody.setUserName((String)session.getAttribute("userName"));
            authCodeMessageBody.setServiceType("BTOFFPOSITIONALARM");//报文唯一标识：默认.BTOFFPOSITIONALARM
            authCodeMessageBody.setFormatVersion("1.0");//格式版本
            authCodeMessageBody.setDeviceType(1);//设备类型：1.随行设备 2.离位报警器 3.腕表
            authCodeMessageBody.setSerialNumber(dataTool.dateToString() + progressiveIncreaseNumber.getNumber(i));//交易流水号:yyyyMMddHHmmss+循环递增0-9999
            authCodeMessageBody.setMessageType("03");//报文类型： 03:出库下发报文
            authCodeMessageBody.setMessageBody(messageBody);//报文消息体
            authCodeMessageBody.setSendTime(dataTool.dateToString());//发报时间：系统时间
            authCodeMessageBody.setSessionToken(TokenUtils.channelSessionDigest());
            String jsonString = JSONObject.toJSONString(authCodeMessageBody);
            //一对一发送
            try {
                jmsMessagingTemplate.convertAndSend(storageQueue, jsonString);
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
        HttpSession session = requests.getSession();
        BaseModel baseModel = new BaseModel();
        ServerInWareHouseMessage authCodeMessageBody = new ServerInWareHouseMessage();
        ServerInWareHouseBody messageBody = new ServerInWareHouseBody();
        for (int i = 0; i < 1; i++) {
            //报文体
            messageBody.setBluetoothMac(bluetoothMac);
            messageBody.setAuthCode(TokenUtils.channelSessionDigest());
            messageBody.setDeviceNo(deviceNo);

            authCodeMessageBody.setUserName((String)session.getAttribute("userName"));
            authCodeMessageBody.setServiceType("BTOFFPOSITIONALARM");//报文唯一标识：默认.BTOFFPOSITIONALARM
            authCodeMessageBody.setFormatVersion("1.0");//格式版本
            authCodeMessageBody.setDeviceType(1);//设备类型：1.随行设备 2.离位报警器 3.腕表
            authCodeMessageBody.setSerialNumber(dataTool.dateToString() + progressiveIncreaseNumber.getNumber(i));//交易流水号:yyyyMMddHHmmss+循环递增0-9999
            authCodeMessageBody.setMessageType("09");//报文类型： 09:入库下发报文
            authCodeMessageBody.setMessageBody(messageBody);//报文消息体
            authCodeMessageBody.setSendTime(dataTool.dateToString());//发报时间：系统时间
            authCodeMessageBody.setSessionToken(TokenUtils.channelSessionDigest());
            String jsonString = JSONObject.toJSONString(authCodeMessageBody);
            try {
                jmsMessagingTemplate.convertAndSend(storageQueue, jsonString);
                log.info("************入库报文信息**************：" + jsonString);
            } catch (Exception e) {
                baseModel.setStatus(StatusCodeEnum.Fail.getStatusCode());
                e.printStackTrace();
            }
            return baseModel;
        }
        return baseModel;
    }


    /**
     * 协助查找报文消息
     *
     * @param body
     * @return
     */
    public BaseModel sendMessageMinistrantFind(JSONObject body) throws Exception {
        BaseModel baseModel = new BaseModel();
        ServerOffLocationSearchMessage serverMessageBody = new ServerOffLocationSearchMessage();
        ServerOffLocationSearchBody messageBody = new ServerOffLocationSearchBody();
    //   String[] split = body.split(",");
        for (int i = 0; i < 1; i++) {
            //报文体
            try {
                messageBody.setAssDeviceNo(body.getString("assDeviceNo"));
                messageBody.setBluetoothMac(body.getString("bluetoothMac"));
                messageBody.setLo(body.getString("lo"));
                messageBody.setLa(body.getString("la"));
                messageBody.setLostDeviceNo(body.getString("lostDeviceNo"));
                messageBody.setLostphone(body.getString("lostphone"));
                messageBody.setLostTime(body.getString("lostTime"));
                messageBody.setLostGunTag(body.getString("lostGunTag"));
            } catch (Exception e) {
                e.printStackTrace();
                baseModel.setStatus(StatusCodeEnum.Fail.getStatusCode());
            }

            serverMessageBody.setServiceType("BTOFFPOSITIONALARM");//报文唯一标识：默认.BTOFFPOSITIONALARM
            serverMessageBody.setFormatVersion("1.0");//格式版本
            serverMessageBody.setDeviceType(1);//设备类型：1.随行设备 2.离位报警器 3.腕表
            serverMessageBody.setSerialNumber(dataTool.dateToString() + progressiveIncreaseNumber.getNumber(i));//交易流水号:yyyyMMddHHmmss+循环递增0-9999
            serverMessageBody.setMessageType("19");//报文类型： 09:入库下发报文
            serverMessageBody.setMessageBody(messageBody);//报文消息体
            serverMessageBody.setSendTime(dataTool.dateToString());//发报时间：系统时间
            serverMessageBody.setSessionToken(TokenUtils.channelSessionDigest());
            String jsonString = JSONObject.toJSONString(serverMessageBody);
            try {
                jmsMessagingTemplate.convertAndSend(storageQueue, jsonString);
                log.info("************协助查找报文信息**************：" + jsonString);
            } catch (Exception e) {
                baseModel.setStatus(StatusCodeEnum.Fail.getStatusCode());
                e.printStackTrace();
            }
            return baseModel;
        }
        return baseModel;
    }


    /**
     * 离位报警启停操作
     * @param state        启停状态：[1/0（停止/重启）
     * @param bluetoothMac
     * @return
     * @throws ParseException
     */
    public BaseModel sendMessageOffNormalAlarmStartAndStop(String state, String bluetoothMac) throws ParseException {
        BaseModel baseModel = new BaseModel();
        ServerOffLocationWarningStartStopMessage message = new ServerOffLocationWarningStartStopMessage();
        ServerOffLocationWarningStartStopBody messageBody = new ServerOffLocationWarningStartStopBody();

        for (int i = 0; i < 1; i++) {
            //报文体
            messageBody.setReserve(state);
            messageBody.setBluetoothMac(bluetoothMac);
            messageBody.setAuthCode(TokenUtils.channelSessionDigest());

            message.setServiceType("BTOFFPOSITIONALARM");//报文唯一标识：默认.BTOFFPOSITIONALARM
            message.setFormatVersion("1.0");//格式版本
            message.setDeviceType(1);//设备类型：1.随行设备 2.离位报警器 3.腕表
            message.setSerialNumber(dataTool.dateToString() + progressiveIncreaseNumber.getNumber(i));//交易流水号:yyyyMMddHHmmss+循环递增0-9999
            message.setMessageType("17");//报文类型： 09:入库下发报文
            message.setMessageBody(messageBody);//报文消息体
            message.setSendTime(dataTool.dateToString());//发报时间：系统时间
            message.setSessionToken(TokenUtils.channelSessionDigest());
            String jsonString = JSONObject.toJSONString(message);
            try {
                jmsMessagingTemplate.convertAndSend(storageQueue, jsonString);
                log.info("************离位报警启停报文信息**************：" + jsonString);
            } catch (Exception e) {
                baseModel.setStatus(StatusCodeEnum.Fail.getStatusCode());
                e.printStackTrace();
            }
            return baseModel;
        }
        return baseModel;
    }

    /**
     * 射弹数报文申请
     * @param gunMac
     * @return
     */
    public BaseModel sendMessageBulletNumberApply( String gunMac) throws Exception{
        BaseModel baseModel = new BaseModel();
        DeviceBulletNumGetBody messageBody=new DeviceBulletNumGetBody();
        DeviceBulletNumGetMessage message=new DeviceBulletNumGetMessage();

        for (int i = 0; i < 1; i++) {
            //报文体
            messageBody.setBluetoothMac(gunMac);
            messageBody.setAuthCode(TokenUtils.channelSessionDigest());

            message.setServiceType("BTOFFPOSITIONALARM");//报文唯一标识：默认.BTOFFPOSITIONALARM
            message.setFormatVersion("1.0");//格式版本
            message.setDeviceType(1);//设备类型：1.随行设备 2.离位报警器 3.腕表
            message.setSerialNumber(dataTool.dateToString() + progressiveIncreaseNumber.getNumber(i));//交易流水号:yyyyMMddHHmmss+循环递增0-9999
            message.setMessageType("25");//报文类型： 上报射弹计数器响应报文
            message.setMessageBody(messageBody);//报文消息体
            message.setSendTime(dataTool.dateToString());//发报时间：系统时间
            message.setSessionToken(TokenUtils.channelSessionDigest());
            String jsonString = JSONObject.toJSONString(message);
            try {
                jmsMessagingTemplate.convertAndSend(storageQueue, jsonString);
                log.info("************射弹数报文信息**************：" + jsonString);
            } catch (Exception e) {
                baseModel.setStatus(StatusCodeEnum.Fail.getStatusCode());
                e.printStackTrace();
            }
            return baseModel;
        }
        return baseModel;
    }

}

