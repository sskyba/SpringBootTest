/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package TestAssignment;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class RoamingCheckControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void noParamRoamingCheckShouldReturnBadRequest() throws Exception {
        this.mockMvc.perform(post("/roamingCheck")).andDo(print()).andExpect(status().isBadRequest());
    }

    @Test
    public void getRoamingCheckShouldReturnMethodNotAllowed() throws Exception {
        this.mockMvc.perform(get("/roamingCheck").param("subscriberNumber", "1234").param("ipAddress", "1.2.3.4"))
                .andDo(print()).andExpect(status().isMethodNotAllowed());
    }

    @Test
    public void unknownIPRoamingCheckShouldReturnROAMING() throws Exception {
        this.mockMvc.perform(post("/roamingCheck").param("subscriberNumber", "1234").param("ipAddress", "1.2.3.4"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.subscriberNumber").value("1234"))
                .andExpect(jsonPath("$.location").value("ROAMING"));
    }

    @Test
    public void homeIPRoamingCheckShouldReturnHOME() throws Exception {
        this.mockMvc.perform(post("/roamingCheck").param("subscriberNumber", "1234").param("ipAddress", "202.142.4.15"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.subscriberNumber").value("1234"))
                .andExpect(jsonPath("$.location").value("HOME"));
    }
}
