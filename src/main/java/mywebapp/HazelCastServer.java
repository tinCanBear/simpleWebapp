package mywebapp;

import com.hazelcast.config.Config;
import com.hazelcast.config.FileSystemXmlConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

import java.io.FileNotFoundException;
import java.util.Map;

class HazelCastServer {

    private static HazelCastServer instance = new HazelCastServer();
    private HazelcastInstance hzInstance = null;

    private HazelCastServer(){
        Config cfg = null;
        try {
            cfg = new FileSystemXmlConfig("/var/lib/jetty/webapps/root/WEB-INF/hazelcast.xml");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("hz xml file not found");
            cfg = new Config();
        }
        hzInstance = Hazelcast.newHazelcastInstance(cfg);
    }

    /* Static 'instance' method */
    static HazelCastServer getInstance() {
        return instance;
    }

    int insertData(String str){
        Map<Integer, String> dataMap = hzInstance.getMap("data");
        int newIndex = dataMap.size();
        dataMap.put(newIndex, str);
        return newIndex;
    }
    String getData(int index){
        Map<Integer, String> dataMap = hzInstance.getMap("data");
        if (dataMap == null || dataMap.size() == 0 || dataMap.get(index)==null){
            return "No such index yet.";
        }
        return dataMap.get(index);
    }
}