package com.shengq.covid19;

import com.shengq.covid19.dao.ClientUser;
import com.shengq.covid19.dto.ClientUserDTO;
import com.shengq.covid19.mapper.ClientUserMapper;
import com.shengq.covid19.service.ClientUserService;
import net.bytebuddy.build.ToStringPlugin;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.jnlp.ClipboardService;
import java.util.List;

@MapperScan("com.shengq.covid19.mapper")
@SpringBootTest
public class ClientServiceDataTest {
    @Autowired
    ClientUserService service;
    @Test
    void testAddUser(){
        ClientUser clientUser = new ClientUser(4,"sheng","15223333333",
                "358888222288882282");
        int count = service.addClinetUser(clientUser);
        System.out.println("testAddUser:"+count);
    }
    @Test
    void testfindById(){
        ClientUserDTO count = service.findById(1);
        System.out.println("testfindById:"+count);
    }
    @Test
    void testfindByName(){
        ClientUserDTO count = service.findByName("shengxiu");
        System.out.println("testfindByName:"+count);
    }
    @Test
    void testfindByMoblie(){
        ClientUserDTO count = service.findByMobile("19911111111");
        System.out.println("testfindByMoblie:"+count);
    }
    @Test
    void listUser(){
        List<ClientUserDTO> userDTOList = service.findAllUser();
        System.out.print("listUser:");
        userDTOList.forEach(System.out::println);
    }
    @Test
    void updateClientUser(){
        ClientUserDTO clientUserDTO = new ClientUserDTO(1,"shengxiu","19911111111",
                1,1,1);
        int count = service.updateClientUser(clientUserDTO);
        System.out.println("updateClientUser:"+count);
    }
    @Test
    void delClientUserById(){
        int count = service.delClientUserById(2);
        System.out.println("delClientuserById:"+count);
    }
    @Test
    void delClientUserByMobile(){
        int count = service.delClientUserByMobile("11111111111");
        System.out.println("delClientUserByMoblie:"+count);
    }
    @Test
    void testUpdateUserStatus(){
        ClientUserDTO clientUserDTO = new ClientUserDTO(1,null,null,1,1,1);
        int count = service.updateClientUserStatus(clientUserDTO);
        System.out.println("testUpdateUserStatus:"+count);
    }
}

