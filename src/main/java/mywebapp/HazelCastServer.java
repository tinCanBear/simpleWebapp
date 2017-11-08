package mywebapp;

import com.hazelcast.config.Config;
import com.hazelcast.config.XmlConfigBuilder;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

import java.io.IOException;
import java.util.Map;

class HazelCastServer {

    private static HazelCastServer instance = new HazelCastServer();
    private HazelcastInstance hzInstance = null;

    private HazelCastServer(){
//        Config cfg = new Config();
        Config cfg = null;
        try {
            cfg = new XmlConfigBuilder("/var/lib/jetty/webapps/root/WEB-INF/hazelcast.xml").build();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        NetworkConfig networkConfig = cfg.getNetworkConfig();
//        networkConfig.setPort(5701);
//        networkConfig.setPortAutoIncrement(true);
//        networkConfig.setPortCount(100);
//        JoinConfig joinConfig = networkConfig.getJoin();
//        joinConfig.getMulticastConfig().setEnabled(false);
//        joinConfig.getTcpIpConfig().setEnabled(false);
//        DiscoveryConfig discoveryConfig = joinConfig.getDiscoveryConfig();
//        HazelcastKubernetesDiscoveryStrategyFactory factory = new HazelcastKubernetesDiscoveryStrategyFactory();
//        DiscoveryStrategyConfig strategyConfig = new DiscoveryStrategyConfig(factory);
//        strategyConfig.addProperty("service-name", "jetty");
//        strategyConfig.addProperty("service-label-name", "app");
//        strategyConfig.addProperty("service-label-value", "jetty");
//        discoveryConfig.addDiscoveryStrategyConfig(strategyConfig);
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