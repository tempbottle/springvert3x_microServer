package com.solodream.spring.vertx.vertx;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.solodream.spring.vertx.jpa.domain.ClientAccountInfoDto;
import com.solodream.spring.vertx.jpa.domain.JobInfoDto;
import com.solodream.spring.vertx.req.JsonReq;
import com.solodream.spring.vertx.req.client.UserLoginRequestParam;
import com.solodream.spring.vertx.resp.BaseResp;
import com.solodream.spring.vertx.resp.job.JobResponse;
import com.solodream.spring.vertx.resp.login.LoginResponse;
import com.solodream.spring.vertx.service.UserService;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.json.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * LoginVerticle
 *
 * @author Young
 * @date 2015/11/24 0024
 */
@Component
public class JobVerticle extends AbstractVerticle {
    private static final Logger LOGGER = LoggerFactory.getLogger(JobVerticle.class);

    public void start() {
        vertx.eventBus().consumer("jobs", message -> {
            LOGGER.info("Received a message: {}, {}", message.body(), message.headers());
            try {
//                JsonObject jsonString = (JsonObject) message.body();
//                JsonReq<UserLoginRequestParam> obj = JSON.parseObject(jsonString.toString(), new TypeReference<JsonReq<UserLoginRequestParam>>() {
//                });
//                String username = obj.getParam().getUsername();
//                String password = obj.getParam().getPassword();
//                LOGGER.info("username is {},password is {}", username, password);

                JobInfoDto dto = new JobInfoDto();
                dto.setId(1);
                dto.setCompanyId(1);
                dto.setCompanyName("alibaba");
                dto.setJobAddress("文一西路");
                dto.setJobDatail("java开发工程师,有架构经验");
                dto.setJobExperience("3~5年");
                dto.setJobLocation("杭州");
                dto.setJobTags("高福利,高期权,高薪酬");
                dto.setJobTitle("java工程师");
                dto.setReleaseDate(new Date());
                dto.setSalaryRange("300000元");



                JobInfoDto dto2 = new JobInfoDto();
                dto2.setId(2);
                dto2.setCompanyId(2);
                dto2.setCompanyName("华为");
                dto2.setJobAddress("文一西路");
                dto2.setJobDatail("云计算开发,有架构经验");
                dto2.setJobExperience("1~5年");
                dto2.setJobLocation("杭州");
                dto2.setJobTags("高福利,高期权,高薪酬");
                dto2.setJobTitle("云计算工程师");
                dto2.setReleaseDate(new Date());
                dto2.setSalaryRange("250000元");


                List<JobInfoDto> list=new ArrayList<>();
                list.add(dto);
                list.add(dto2);

                BaseResp<JobResponse> resp=new BaseResp<JobResponse>();
                JobResponse response = new JobResponse();
                resp.setData(response);
                response.setTotal(list.size());
                response.setDataList(list);
                response.setCode(0);
                message.reply(JSON.toJSONString(resp));
            } catch (Exception e) {
                LOGGER.error("convert error.", e);
            }
        });
    }
}
