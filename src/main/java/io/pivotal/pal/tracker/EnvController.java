package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class EnvController {

    public String port;
    public String memoryLimit;
    public String cfInstanceIndex;
    public String cfInstanceAddress;



    public EnvController(
            @Value("${port:NOT SET}") String port,
            @Value("${memory.limit:NOT SET}") String memoryLimit,
            @Value("${cf.instance.index:NOT SET}") String cfInstanceIndex,
            @Value("${cf.instance.addr:NOT SET}") String cfInstanceAddress
    ) {
        this.port = port;
        this.memoryLimit = memoryLimit;
        this.cfInstanceIndex = cfInstanceIndex;
        this.cfInstanceAddress = cfInstanceAddress;
    }
    @GetMapping("/env")
    public Map<String,String> getEnv(){
        Map<String,String> map=new HashMap<>();
        map.put("PORT",this.port);
        map.put("MEMORY_LIMIT",this.memoryLimit);
        map.put("CF_INSTANCE_INDEX",this.cfInstanceIndex);
        map.put("CF_INSTANCE_ADDR",this.cfInstanceAddress);
        return map;
    }


}
