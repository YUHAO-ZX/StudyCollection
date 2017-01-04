package test;

import com.jumei.parrot.codec.ThriftField;
import com.jumei.parrot.service.ThriftMethod;
import com.jumei.parrot.service.ThriftService;

/**
 * Created by xinz on 2016/12/7.
 */
@ThriftService("PromotionAdminService")
public interface PromotionAdminService {
    /**
     * 新增规则
     * @param ruleJson
     * @param ruleItemJson
     * @param type
     * @return
     */
    @ThriftMethod(value = "addRule")
    public String addRule(@ThriftField(value = 1, name = "ruleJson") String ruleJson,
                          @ThriftField(value = 2, name = "ruleItemJson") String ruleItemJson,
                          @ThriftField(value = 3, name = "type") String type) throws Exception;

    /**
     * 更新规则
     * @param ruleJson
     * @param ruleItemJson
     * @param type
     * @return
     */
    @ThriftMethod(value = "updateRule")
    public String updateRule(@ThriftField(value = 1, name = "ruleJson") String ruleJson,
                             @ThriftField(value = 2, name = "ruleItemJson") String ruleItemJson,
                             @ThriftField(value = 3, name = "type") String type);

    /**
     * 获取规则
     * @param params
     * @return
     */
    @ThriftMethod(value = "getRule")
    public String getRule(@ThriftField(value = 1, name = "params") String params);

    /**
     * 审核规则
     * @param params
     * @return
     */
    @ThriftMethod(value = "auditRule")
    public String auditRule(@ThriftField(value = 1, name = "params") String params);

    /**
     * 查询规则
     * @param conditionJSON
     * @return
     */
    @ThriftMethod(value = "queryRule")
    public String queryRule(@ThriftField(value = 1, name = "conditionJSON") String conditionJSON);
}
