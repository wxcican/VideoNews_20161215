package com.fuicuiedu.idedemo.videonews_20161215.bombapi.other;

//// URL编码参数
//'where= {
//       "查询字段": {
//          "$inQuery": {
//                 "where": {
//                      "objectId": 新闻id
//                      },
//                      "className": "表名"
//                  }
//          }
//        }'
public class InQuery {

    private String field;//查询字段
    private String className;//表名
    private String objectId;//新闻id

    public InQuery(String field, String className, String objectId) {
        this.field = field;
        this.className = className;
        this.objectId = objectId;
    }

    //    {
//       "查询字段": {
//          "$inQuery": {
//                 "where": {
//                      "objectId": 用户id
//                      },
//                      "className": "表名"
//                  }
//          }
//        }'

    final String LIKE_IN_QUERY =
            "{\"%s\" : { \"$inQuery\": {\"where\" :{\"objectId\":\"%s\" },\"className\" : \"%s\"}}}";

    @Override
    public String toString() {
        return String.format(LIKE_IN_QUERY,field,objectId,className);
    }
}
