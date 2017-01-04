var setPromotionTypeValue = function() {

    var voucherConfig = "";
    $.ajax({
        method:"POST",
        url: ctx + "/sysinfo/getSetting",
        data:{key:"voucherPreferential"},
        async:false,
        success:function(resp){
            voucherConfig = resp;
        },
        dataType : "text",
        error:function(resp){
            $.showMessage(resp);
        }
    });

    promotionTypeArray = [];
    //
    $("#promotionTypeStr").val("");
    var itemArray = [];
    var itemZone = $(".singleProperty");
    for(var m1=0;m1<itemZone.length;m1++){
        cule = $(itemZone[m1]);
        var item = {};
        item.promotionType  = cule.find("*[name^=promotionType]").val();//todo 1
        //add
        promotionTypeArray.push(item.promotionType);
        item.conditionQuantity = cule.find("*[name^=conditionQuantity]").val();//todo 2
        item.conditionAmount = cule.find("*[name^=conditionAmount]").val();//todo 3
        item.promotePrice = cule.find("*[name^=promotePrice]").val();//todo 4
        var saleOn = cule.find("*[name^=saleOn]").val();
        if(saleOn !=null && saleOn!='')
        {
            item.saleOff = 1-parseFloat(saleOn)*0.1;
        }
        item.freeQuantity = cule.find("*[name^=freeQuantity]").val();//todo 5
        //new
        fillDescriptionToItem(cule, item);

        item.discountAmount = cule.find("*[name^=discountAmount]").val();//todo 6
        // sets itemValue
        var itemValue = [];
        switch (item.promotionType) {
            case 'voucher':
            case 'voucher_uncapped':
                var  forms = cule.find('.card-div-table-tr:gt(0)');
                var isLegal = true;

                var badItem = new Array();
                forms.each(function(i,f){
                    itemObj = {};
                    itemObj.batch_no = $(f).find("input[name^=batch_no]").val();
                    itemObj.quantity = $(f).find("input[name^=quantity]").val();
                    itemObj.over_amount = $(f).find("input[name^=over_amount]").val();
                    itemObj.discount = $(f).find("input[name^=discount]").val();
                    itemObj.active_time = $(f).find("input[name^=active_time]").val();
                    itemObj.expire_time = $(f).find("input[name^=expire_time]").val();
                    itemObj.capped = $(f).find("input[name^=capped]").val();
                    itemObj.source = $(f).find("input[name^=source]").val();
                    var lidu = parseFloat(itemObj.discount) / parseFloat(itemObj.over_amount);
                    if(voucherConfig != "" && parseInt(voucherConfig) != 0 && lidu > parseFloat(voucherConfig) / 100){
                        isLegal = false;
//							badItem.push("批次号:" + itemObj.batch_no + ", 满" + itemObj.over_amount + "元减" + itemObj.discount + "元, 优惠比例<font color='red'>" + parseInt(lidu * 100) + "%</font>");
                        badItem.push("批次号" + itemObj.batch_no + " , </font> <font color='red' style='font-weight: bold;'>优惠比例 >" + voucherConfig + "%</font>, 请修改返券配置内容");
                        return true;
                    }
                    itemValue.push(itemObj);
                });
                if(!isLegal){
//						showError(badItem.join("<br>") + "<br>如上现金券超过<font color='red'>" + voucherConfig + "%</font>优惠力度, 请检查!");
                    showError(badItem.join("<br>"));
                    return false;
                }
                break;
            case 'gift':
                vals = cule.find('*[name^=sku_no]');
                if(!checkSkuNoAndHashId(null,vals,itemValue)){
                    return false;
                }

                //海淘自营创建赠品规则  仓库验证 TODO
                var isHasGlobal = hasBooth(["globalmall", "jumeiglobal"]);
                if(isHasGlobal && !isGlobalGiftCheckPass(vals, "globalSelf")){
                    return false;
                }
                //自营 满赠 赠品价格是否大于0 校验
                var isHasSelfBooth = hasBooth(["mall", "deal", "globalmall", "jumeiglobal"]);
                if(isHasSelfBooth && !isGlobalGiftCheckPass(vals, "self")){
                    return false;
                }
                break;
            case 'red_envelope':
                vals = $.trim(cule.find('*[name^=hash_id]').val());
                var tmpArray = vals.split("\n");
                var reqHashId = [];
                var quantityArray = [];
                for(var m2=0;m2<tmpArray.length;m2++){
                    var hash_id_quantity = tmpArray[m2].split(",");
                    if(hash_id_quantity.length==2){
                        reqHashId.push(hash_id_quantity[0]);
                        quantityArray.push(hash_id_quantity[1]);
                    }else{
                        showError("有不符合格式要求的数据格式【hash_id,数量】");
                        return ;
                    }
                }

                if(reqHashId!=null && reqHashId.length>0){
                    result = getPriceByHashIds(reqHashId.join(","));
                    if(result.code){
                        var resultData = result.data;
                        var validHashId = [];
                        var hashIdObj = [];
                        for(var i=0;i<resultData.length;i++){
                            if(!resultData[i].flag){
                                validHashId.push(resultData[i].hash_id);
                            }
                            else {
                                hashIdObj.push(resultData[i]);
                            }
                        }

                        if(validHashId.length>0){
                            showError("无效的hashId:"+validHashId.join(','));
                            return;
                        }

                        for(var j=0;j<hashIdObj.length;j++){
                            itemValueObj = {};
                            itemValueObj.deal_hash_id =	hashIdObj[j].hash_id;
                            for(var m = 0 ;m<reqHashId.length;m++){
                                if(reqHashId[m] ==hashIdObj[j].hash_id ){
                                    itemValueObj.quantity = quantityArray[m];
                                }
                            }
                            itemValueObj.deal_price = hashIdObj[j].item_price;
                            itemValue.push(itemValueObj);
                        }
                    }
                }
                break;
            case 'gift_random':
                vals = $.trim(cule.find('*[name^=sku_no]').val());
                itemValue = $.trim(vals).split("\n");
                //hry add the length check
                if(itemValue.length >config.gift_random_size){
                    showError('随机赠品最多支持'+config.gift_random_size+'个SKU_NO');
                    return false;
                }

                if(!validateSkuNo(itemValue.join(","))){
                    return false;
                }
                break;
            case 'over_qty_offer_best_price'://校验X元Y件规则配置的产品ID是否已存在相同规则_release_011
                if(!checkProductIdOrBrandIdIsExist()){
                    return false;
                }
                //将X元Y件的promote_price 同步到 conditionAmount属性中
                item.conditionAmount = item.promotePrice;//todo 7
                //判断x元y件促销形式有无门槛相同或是优惠相同的
                if(!isXYPromotionLegal(itemArray, item)){
                    showError('x元y件促销形式有相同门槛或是优惠, 请确认!');
                    return false;
                }
                break;
            case 'over_specialoffer':
                var vals = $.trim(cule.find('*[name^=hash_id]').val());
                if(!checkspecialoffer(cule.find('*[name^=hash_id]').parent().next(),vals,itemValue, true)) {
                    return false;
                }
                $.each($.trim(vals).split("\n"), function(i, item){
                    var itemData = item.split(",");
                    if(itemData.length != 2)
                        return true;
                    itemValue.push({"deal_hash_id":$.trim(itemData[0]), "sku":$.trim(itemData[1])});
                });

                break;
            default: break;
        }

        item.itemValue = itemValue;//todo 8
        itemArray.push(item);
    }

    if(itemArray.length>0){
        var promotionTypeJson=JSON.stringify(itemArray);
        $("#promotionTypeStr").val(promotionTypeJson);
        return true;
    }else{
        showError('当前没有规则配置');
        return false;
    }
}/**
 * Created by admin on 2016/12/7.
 */
