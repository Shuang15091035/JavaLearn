input {
    jdbc {
        jdbc_connection_string => "jdbc:oracle:thin:@//192.168.1.16:1521/db"
        jdbc_driver_library => "/home/oracle/elasticsearch-jdbc-2.3.4.1/lib/ojdbc6.jar"
        jdbc_driver_class => "Java::oracle.jdbc.driver.OracleDriver"
        record_last_run => "true"
        jdbc_user =>  "jpym"
        jdbc_password => "jpym"
        tracking_column => "id"
        clean_run => "false"
    }
}
output {
    stdout {
        codec => dots {}
    }
    elasticsearch {
        hosts => ["http://10.8.0.34:9200"]
        index => "nginx_elastic_stack_example"
        document_type => "logs"
        template_name => "nginx_elastic_stack_example"
        template_overwrite => true
    }
}


docker run -it -v ~/logstash/config/logstash.yml:/usr/share/logstash/config/logstash.yml -v ~/logstash/pipeline/:/usr/share/logstash/pipeline/ -v ~/logstash/files/:/usr/share/logstash/data/ -p 9600:9600 4470777ac65e


(?<custom>[0-9A-F]{10,11})

String
    name => "hello world"
Path
    my_path => "/tmp/logstash"
URI
    my_uri => "http://foo:bar@example.net"
Password
    my_password => "password"
Number
    prot => 33
Hash
    match => {
        "field1" => "value1"
        "field2" => "value2"
    }
codec
    codec => "json"
Bytes
    my_bytes => "1123"
    my_bytes => "10MiB"
    my_bytes => "100Kib"
    my_bytes => "100Kib"
Boolean
    ssl_enable => true
List
    path => [ "/var/log/messages", "/var/log/*.log" ]
Array
    users => [ {id => 1, name => bob}, {id => 2, name => jane} ]

filebeat:
    input（勘探者）/harvester（收割者）
    scan_frequency:
        搜索新文件的评率：默认为5min，
        关闭的文件再次更改，将会启动一个新的harvester,继续收集最新的更改
    close_inactive
        :在指定时间内文件没有更改，关闭文件操作，

filebeat.inputs:
  -type: log
    paths:
      - /var/log/*.log

启动modules.d中模块
    filebeat modules enable nginx apache mysql