package com.example.guangda.controller;

import com.example.guangda.entity.China;
import com.example.guangda.entity.Client;
import com.example.guangda.entity.Linkman;
import com.example.guangda.entity.LinkmanKey;
import com.example.guangda.mapper.ChinaMapper;
import com.example.guangda.mapper.ClientMapper;
import com.example.guangda.mapper.LinkmanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author asus
 * @create 2021-07-27 15:28
 */

@RestController
@CrossOrigin
public class ClientController {
    @Autowired
    private ClientMapper clientMapper;
    @Autowired
    private LinkmanMapper linkmanMapper;
    @Autowired
    private ChinaMapper chinaMapper;
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 省市联查
     */
    @RequestMapping("/selectChina")
    public List<China> selectChina(int pid){
        List<China> chinaList = chinaMapper.selectChina(pid);
        return chinaList;
    }

    /**
     * 查询所有
     * @return
     */
    @RequestMapping("/selectClient")
    public List<Client> selectClientList(Client client){
        String key = "client";
        ValueOperations<String, Object> operations = redisTemplate.opsForValue();
        Boolean aBoolean = redisTemplate.hasKey(key);
        if (aBoolean){
            System.out.println("从缓存中查看");
            List<Client> clien = (List<Client>) operations.get("client");
            return clien;
        }else {
            System.out.println("从数据库中查看");
            List<Client> clients = clientMapper.selectClientList(client);
            operations.set(key, clients,5, TimeUnit.HOURS);
            return clients;
        }

    }

    /**
     * 查询某个公司的数据
     * @return
     */
    @RequestMapping("/getClientById")
    public Client getClientById(int precustomerno){
        String key = "user_" + precustomerno;

        ValueOperations<String, Client> operations = redisTemplate.opsForValue();

        //判断redis中是否有键为key的缓存
        boolean hasKey = redisTemplate.hasKey(key);

        if (hasKey) {
            Client client = operations.get(key);
            System.out.println("从缓存中获得数据："+client.getPrecustomername());
            System.out.println("------------------------------------");
            return client;
        } else {
            Client client = clientMapper.gatselectClientList(precustomerno);
            System.out.println("查询数据库获得数据："+client.getPrecustomername());
            System.out.println("------------------------------------");

            // 写入缓存
            operations.set(key, client, 5, TimeUnit.HOURS);
            return client;
        }
    }

    /**
     * 添加客户
     * @param client
     */
    @RequestMapping("/addClient")
    public String  addClient(Client client){
        int result = 1;
        ValueOperations<String, Object> operations = redisTemplate.opsForValue();
        int radom = (int)(Math.random()*1000000000);
        client.setPrecustomerno(radom);
        clientMapper.addClient(client);
        List<Linkman> linkman = client.getLinkman();
       for (Linkman lin: linkman){
           if(lin.getBirthday() != null){
               int radoml = (int)(Math.random()*1000000000);
               lin.setPrecustomerno(radom);
               lin.setLinkorderno(radoml);
               linkmanMapper.addLinkman(lin);
           }
       }
        if (result != 0) {
            String key = "user_" + client.getPrecustomerno();
            boolean haskey = redisTemplate.hasKey(key);
            if (haskey) {
                redisTemplate.delete(key);
                System.out.println("删除缓存中的key-----------> " + key);
            }
            // 再将更新后的数据加入缓存
            List<Client> clients = clientMapper.selectClientList(client);
            if (clients != null) {
                operations.set(key, clients, 3, TimeUnit.HOURS);
            }
        }
       return "1";
    }

    /**
     * 删除所有
     */
    @RequestMapping("/deleteClient")
    public String deleteClient(int precustomerno){
        int result = 1;
        LinkmanKey linkey = new LinkmanKey();
        linkey.setPrecustomerno(precustomerno);
        linkmanMapper.deleteLinkman(linkey);
        clientMapper.deleteClient(precustomerno);
        String key = "user_" + precustomerno;
        if (result != 0) {
            boolean hasKey = redisTemplate.hasKey(key);
            if (hasKey) {
                redisTemplate.delete(key);
                System.out.println("删除了缓存中的key:" + key);
            }
        }
        return "1";
    }

    /**
     * 删除联系人
     */
    @RequestMapping("/deleteLinkman")
    public void deleteLinkman(){
        LinkmanKey key = new LinkmanKey();
        key.setPrecustomerno(1000);
        key.setLinkorderno(1);
        linkmanMapper.deleteLinkman(key);
    }

    /**
     * 修改
     */
    @RequestMapping("/updateClient")
    public String updateClient(Client client){
        int result = 1;
        ValueOperations<String, Client> operations = redisTemplate.opsForValue();
        clientMapper.updateByPrimaryKey(client);
        for (Linkman lin: client.getLinkman()) {
            linkmanMapper.updateByPrimaryKey(lin);
        }
        if (result != 0) {
            String key = "user_" + client.getPrecustomerno();
            boolean haskey = redisTemplate.hasKey(key);
            if (haskey) {
                redisTemplate.delete(key);
                System.out.println("删除缓存中的key-----------> " + key);
            }
            // 再将更新后的数据加入缓存
            Client cli = clientMapper.gatselectClientList(client.getPrecustomerno());
            if (cli != null) {
                operations.set(key, cli, 3, TimeUnit.HOURS);
            }
        }
        return "1";
    }

}
