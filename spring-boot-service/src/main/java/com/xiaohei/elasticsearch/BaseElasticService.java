package com.xiaohei.elasticsearch;
import com.alibaba.fastjson.JSON;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.IndicesOptions;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.reindex.DeleteByQueryRequest;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
/**
 * @author : WiuLuS
 * @version : v1.0 05.19.2020
 * @discription :
 * @date : 2020-05-19 16:21:15
 * @email : m13886933623@163.com
 */
@Component
public class BaseElasticService {

    @Autowired
    RestHighLevelClient restHighLevelClient ;

    /**
     * @author WiuLuS
     * @date 2019/10/17 17:30
     * @param index   索引名称
     * @param str    索引描述
     * @return void
     */
    public void createIndex(String index,String str){
        try {
            if (isExistsIndex(index)) {
                // todo: 日志输出
                return;
            }
            CreateIndexRequest request = new CreateIndexRequest(index);
            buildSetting(request);
            request.mapping(str, XContentType.JSON);
            CreateIndexResponse res = restHighLevelClient.indices().create(request, RequestOptions.DEFAULT);
            if (!res.isAcknowledged()) {
                throw new RuntimeException("初始化失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /** 断某个index是否存在
     * @author WiuLuS
     * @date 2019/10/17 17:27
     * @param index index名
     * @return boolean
     */
    public boolean isExistsIndex(String index) throws Exception {
        return restHighLevelClient.indices().exists(new GetIndexRequest(index),RequestOptions.DEFAULT);
    }

    /** 设置分片
     * @author WiuLuS
     * @date 2019/10/17 19:27
     * @param request
     */
    public void buildSetting(CreateIndexRequest request){
        request.settings(Settings.builder().put("index.number_of_shards",3)
                .put("index.number_of_replicas",2));
    }
    /**
     * @author WiuLuS
     * @date 2019/10/17 17:27
     * @param index index
     * @param entity    对象
     */
    public void insertOrUpdateOne(String index, ElasticEntity entity) {
        IndexRequest request = new IndexRequest(index);
        // todo: 日志输出
        request.id(entity.getId());
        request.source(entity.getData(), XContentType.JSON);
//        request.source(JSON.toJSONString(entity.getData()), XContentType.JSON);
        try {
            restHighLevelClient.index(request, RequestOptions.DEFAULT);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    /** 批量插入数据
     * @author WiuLuS
     * @date 2019/10/17 17:26
     * @param index index
     * @param list 带插入列表
     */
    public void insertBatch(String index, List<ElasticEntity> list) {
        BulkRequest request = new BulkRequest();
        list.forEach(id -> request.add(new IndexRequest(index).id(id.getId())
                .source(id.getData(), XContentType.JSON)));
        try {
            restHighLevelClient.bulk(request, RequestOptions.DEFAULT);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /** 批量删除
     * @author WiuLuS
     * @param index index
     * @param idList    待删除列表
     */
    public <T> void deleteBatch(String index , String type , Collection<T> idList) {
        BulkRequest request = new BulkRequest();
        idList.forEach(id -> request.add(new DeleteRequest(index , type , id.toString())));
        try {
            restHighLevelClient.bulk(request, RequestOptions.DEFAULT);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @author WiuLuS
     * @param index index
     * @param builder   查询参数
     * @param c 结果类对象
     */
    public <T> List<T> search(String index, SearchSourceBuilder builder, Class<T> c) {
        SearchRequest request = new SearchRequest(index);
        request.source(builder);
        try {
            SearchResponse response = restHighLevelClient.search(request, RequestOptions.DEFAULT);
            SearchHit[] hits = response.getHits().getHits();
            List<T> res = new ArrayList<>(hits.length);
            for (SearchHit hit : hits) {
                res.add(JSON.parseObject(hit.getSourceAsString(), c));
            }
            return res;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /** 删除index
     * @author WiuLuS
     * @date 2019/10/17 17:13
     * @param index
     */
    public void deleteIndex(String index) {
        try {
            if (isExistsIndex(index)) {
                // todo: 日志输出
                return;
            }
            restHighLevelClient.indices().delete(new DeleteIndexRequest(index), RequestOptions.DEFAULT);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * @author WiuLuS
     * @date 2019/10/17 17:13
     * @param index
     * @param builder
     */
    public void deleteByQuery(String index, QueryBuilder builder) {

        DeleteByQueryRequest request = new DeleteByQueryRequest(index);
        request.setQuery(builder);
        //设置批量操作数量,最大为10000
        request.setBatchSize(10000);
        request.setConflicts("proceed");
        try {
            restHighLevelClient.deleteByQuery(request, RequestOptions.DEFAULT);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



}
