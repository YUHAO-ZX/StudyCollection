package tools;

import java.lang.reflect.Method;
import java.util.Date;

/**
 * Created by xinz on 2016/8/1.
 */
public class CodeGen {
    public static void main(String[] args) {
        setGen(CashCouponGrabRecordModel.class,"dbRecord");
    }

    public static void getGen(Class c,String t){
        Method[] mes = c.getMethods();
        for(Method me : mes){
            if(me.getName().contains("get")){
                System.out.println(t+"."+me.getName()+"();");
            }
        }
    }

    public static void setGen(Class c,String t){
        Method[] mes = c.getMethods();
        for(Method me : mes){
            if(me.getName().contains("set")){
                System.out.println(t+"."+me.getName()+"();");
            }
        }
    }


    class CashCouponGrabRecordModel {

        /**
         * 状态：创建成功
         */
        public static final short STATUS_CREATED = 0;

        /**
         * 状态：抢券成功
         */
        public static final short STATUS_GRAB_SUCCESSFUL = 1;

        /**
         * 状态：兑换现金券失败
         */
        public static final short STATUS_REDEEM_FAILED = 2;

        /**
         * 状态：兑换现金券成功
         */
        public static final short STATUS_REDEEM_SUCCESSFUL = 3;

        /**
         * 唯一标识
         */
        private Long id;

        /**
         * 订单号
         */
        private String orderCode;

        /**
         * 抢现金券的用户ID
         */
        private int grabUid;

        /**
         * 抢券成功时间
         */
        private Date grabTime;

        /**
         * 客户端平台
         */
        private String platfrom;

        /**
         * 客户端版本号
         */
        private String clientV;

        /**
         * 状态： 0：创建成功 1：抢成功 2：兑换失败 3：兑换现金券成功
         */
        private short status;

        /**
         * 操作信息
         */
        private String msg;

        /**
         * 现金券面额
         */
        private double denomination;

        /**
         * 抢券成功订单号
         */
        private String grabItemCode;

        /**
         * 发券者ID
         */
        private int ownUid;

        /**
         * 其它信息
         */
        private String attr;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getOrderCode() {
            return orderCode;
        }

        public void setOrderCode(String orderCode) {
            this.orderCode = orderCode;
        }

        public int getGrabUid() {
            return grabUid;
        }

        public void setGrabUid(int grabUid) {
            this.grabUid = grabUid;
        }

        public Date getGrabTime() {
            return grabTime;
        }

        public void setGrabTime(Date grabTime) {
            this.grabTime = grabTime;
        }

        public String getPlatfrom() {
            return platfrom;
        }

        public void setPlatfrom(String platfrom) {
            this.platfrom = platfrom;
        }

        public String getClientV() {
            return clientV;
        }

        public void setClientV(String clientV) {
            this.clientV = clientV;
        }

        public short getStatus() {
            return status;
        }

        public void setStatus(short status) {
            this.status = status;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public double getDenomination() {
            return denomination;
        }

        public void setDenomination(double denomination) {
            this.denomination = denomination;
        }

        public String getGrabItemCode() {
            return grabItemCode;
        }

        public void setGrabItemCode(String grabItemCode) {
            this.grabItemCode = grabItemCode;
        }

        public int getOwnUid() {
            return ownUid;
        }

        public void setOwnUid(int ownUid) {
            this.ownUid = ownUid;
        }

        public String getAttr() {
            return attr;
        }

        public void setAttr(String attr) {
            this.attr = attr;
        }

        @Override
        public String toString() {
            return "CashCouponGrabRecordModel [id=" + id + ", orderCode="
                    + orderCode + ", grabUid=" + grabUid + ", grabTime=" + grabTime
                    + ", platfrom=" + platfrom + ", client_v=" + clientV
                    + ", status=" + status + ", msg=" + msg + ", denomination="
                    + denomination + ", grabItemCode=" + grabItemCode + ", ownUid="
                    + ownUid + ", attr=" + attr + "]";
        }
    }
}
