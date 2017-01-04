package test;

import com.jumei.parrot.codec.ThriftField;
import com.jumei.parrot.service.ThriftMethod;
import com.jumei.parrot.service.ThriftService;

@ThriftService("PromotionService")
public interface PromotionService
{
    @ThriftMethod(value = "pricing")
    String pricing(
            @ThriftField(value = 1, name = "message") final String message
    ) throws org.apache.thrift.TException;
    
    @ThriftMethod(value = "historyPricing")
    String historyPricing(
            @ThriftField(value = 1, name = "message") final String message,
            @ThriftField(value = 2, name = "ruleIdArray") final String ruleIdArray
    ) throws org.apache.thrift.TException;
}