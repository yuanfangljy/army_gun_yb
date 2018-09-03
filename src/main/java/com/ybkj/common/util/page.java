/*
package com.ybkj.common.util;

import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageInfo;

public class page {

    PageInfo pageInfo = new PageInfo();

    int row =  buriedOperationLog.getRow();
    int page =  buriedOperationLog.getPage();

    long count = 0;
    //查询条数
    Long count1=buriedOperationLogMapper.getAllBuriedOperationLogCount(buriedOperationLog);
if(count1!=null){
        count=count1;
    }

    int totalPage = (int) Math.ceil(count*1.00/row);

    totalPage = totalPage==0?1:totalPage;

    page = page>totalPage?totalPage:page;

    long start = (page-1)*(long)row;
    long end = page*(long)row;

 buriedOperationLog.setStart(start);
 buriedOperationLog.setEnd(end);
    //结果
    List<BuriedOperationLog>  buriedOperationList = buriedOperationLogMapper.getBuriedOperationLogByBuriedOperationLog(buriedOperationLog);
 pageInfo.setTotalPage(totalPage);
 pageInfo.setPage(page);
 pageInfo.setRow(row);
 pageInfo.setTotal(count);
 pageInfo.setRows(JSONArray.toJSON(buriedOperationList));

 return pageInfo;
}
*/
