package com.yyx;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.Table;
import org.junit.Before;
import org.junit.Test;


public class hbasetest {
    static Configuration conf = null;
    private Connection connection = null;
    private Table table = null;
    @Before
    public void setup() throws  Exception{
        conf = HBaseConfiguration.create();
        conf.set("hbase.zookeeper.quorum","hadoop0,hadoop1,hadoop2");
        conf.set("hbase.zookeeper.property.clientPort","2181");
        connection = ConnectionFactory.createConnection(conf);
        table = connection.getTable(TableName.valueOf("test"));
    }

    @Test
    public void createTable() throws Exception {
        //创建表管理类
        HBaseAdmin admin = new HBaseAdmin(conf);
        //创建表描述类
        TableName tableName = TableName.valueOf("test2");
        HTableDescriptor descriptor = new HTableDescriptor(tableName);
        //创建列族描述类
        HColumnDescriptor family = new HColumnDescriptor("info1");
        //将列族添加到表
        descriptor.addFamily(family);
        admin.createTable(descriptor);

    }

}
